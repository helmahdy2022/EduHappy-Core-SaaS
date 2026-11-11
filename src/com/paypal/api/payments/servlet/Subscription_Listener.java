package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import model.beans.account.Account_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.account.EmailSender;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.AgreementStateDescriptor;
import com.paypal.api.payments.ipn.IpnException;
import com.paypal.api.payments.ipn.IpnHandler;
import com.paypal.api.payments.ipn.IpnInfo;
import com.paypal.api.payments.util.GenerateAccessToken;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

/**
 * Servlet implementation class Subscription_Listener
 */
@WebServlet("/Subscription_Listener")
public class Subscription_Listener extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static final Logger LOGGER = Logger.getLogger(Subscription_Response.class);

	public void init(ServletConfig servletConfig) throws ServletException {
		// ##Load Configuration
		// Load SDK configuration for
		// the resource. This intialization code can be
		// done as Init Servlet.
		InputStream is = Subscription_Response.class.getResourceAsStream("/resources/sdk_config.properties");
		try {
			PayPalResource.initConfig(is);
		} catch (PayPalRESTException e) {
			LOGGER.fatal(e.getMessage());
		}
	}
    public Subscription_Listener() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			IpnInfo ipnInfo = new IpnInfo();
	        IpnHandler ipnHandler = new IpnHandler();	        	       	
	        String sInitialAgreementPayment = "insert into Account_Payment(Account_id, txn_id, payment_status, product_name, initial_payment_amount, currency, payment_date, next_payment_date, expiry_Date, payment_cycle, agreement_id, payer_id, State, System_Date, amount_per_cycle) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        String sAgreementRecurringPayment = "insert into Agreement_Recurring_Payment (txn_id, agreement_id, payment_status, mc_gross, payment_date, next_payment_date, expiry_Date, System_Date) values(?,?,?,?,?,?,?,?)";
	        try (Connection connection = DbConnector.connectToDb();
	        		Statement statementAccountID = connection.createStatement();
	        		PreparedStatement pstatementInitialAgreementPayment = connection.prepareStatement(sInitialAgreementPayment);
	        		PreparedStatement pstatementAgreementRecurringPayment = connection.prepareStatement(sAgreementRecurringPayment);
	        		Statement statementtxnID = connection.createStatement();
	        		Statement statementOldAgeementState = connection.createStatement();
	        		){
	        	ipnInfo = ipnHandler.handleIpn(request);
	        	String txnId = ipnInfo.getTxnId();   
	        	String paymentStatus = ipnInfo.getPaymentStatus();
	        	String product_name = ipnInfo.getItemName();
	        	String mc_gross = ipnInfo.getPaymentAmount();
	        	String mc_currency = ipnInfo.getPaymentCurrency();
	        	String payment_date = ipnInfo.getPaymentDate();
	        	String next_payment_date = ipnInfo.getNextPaymentDate();								
	        	String payment_cycle = ipnInfo.getPaymentCycle();
	        	String recurring_payment_id = ipnInfo.getRecurringPaymentId();
	        	String payer_id = ipnInfo.getPayerId();
	        	String profile_status = ipnInfo.getProfile_status();
	        	String amount_per_cycle = ipnInfo.getAmount_per_cycle();
	        	String txn_type = ipnInfo.getTxn_type();
	        											
	        	///////////////////////////Get the Account ID using recurring_payment_id//////////////////////////////////////
	        	String sAccountID = "Select * from Account_Agreement where agreement_id='"+ipnInfo.getRecurringPaymentId()+"'";
	        	ResultSet resultAccountID = statementAccountID.executeQuery(sAccountID);
	        	int account_id = 0;
	        	int no_Of_Students = 0;
	        	int frequencyInterval=0;
	        	String state=null;
	        	String old_agreement_id = null;
	        	while(resultAccountID.next())
	        	{
	        		account_id = resultAccountID.getInt("Account_id");
	        		no_Of_Students = resultAccountID.getInt("No_Of_Students");
	        		frequencyInterval = resultAccountID.getInt("FrequencyInterval");
	        		state = resultAccountID.getString("State");
	        		old_agreement_id = resultAccountID.getString("old_agreement_id");
	        	}
	        	////////////////////Handle Cancellation ipn///////////////////////////////////////////////////////////////////////////////////////
	        	String remarks = null;
	        	if(profile_status!=null && profile_status.equalsIgnoreCase("Cancelled"))
	        	{
	        		String sCancelAgreement = null;
	        		String sAgreementState = null;
	        		if(state!=null&&state.equalsIgnoreCase("Updating"))
	    	        {
	    	        	sCancelAgreement = "update Account_Payment set State='Updating', Cancel_Date=? where agreement_id='"+recurring_payment_id+"' and state='Active'";
	    	        }
	    	        else
	    	        {
	    	        	sCancelAgreement = "update Account_Payment set State='Cancelled', Cancel_Date=? where agreement_id='"+recurring_payment_id+"' and state='Active'";
	    	        	sAgreementState = "update Account_Agreement set State='Cancelled' where Account_id="+account_id+" and agreement_id='"+recurring_payment_id+"'";
    					PreparedStatement pstatementAgreementState = connection.prepareStatement(sAgreementState);
    					pstatementAgreementState.executeUpdate();
    					pstatementAgreementState.close();
	    	        }	        		
	    	        PreparedStatement pstatementCancelAgreement = connection.prepareStatement(sCancelAgreement);
	    	        Date today = new Date();
		        	java.sql.Date cancel_Date = new java.sql.Date(today.getTime());
		        	pstatementCancelAgreement.setDate(1, cancel_Date);		        	
		        	pstatementCancelAgreement.executeUpdate();
		        	pstatementCancelAgreement.close();
	        	}
	    		///////////////////Handle Create agreement ipn////////////////////////////////////////////////////////////////////////////////////
	        	else
	        	{	        	
	        		String sExistingTxnID = null;
	        		if(txnId==null&&txn_type!=null&&txn_type.equalsIgnoreCase("recurring_payment_profile_created")&&mc_gross.equals("0.00"))
	        		{
	        			txnId = "NA-"+ UUID.randomUUID().toString();
	        		}
	        		if(ipnInfo.getPaymentType()!=null && ipnInfo.getPaymentType().equalsIgnoreCase("Initial"))
	        		{
		        		sExistingTxnID = "select * from Account_Payment where txn_id='"+txnId+"'";
	        		}
	        		else
	        		{
		        		sExistingTxnID = "select * from Agreement_Recurring_Payment where txn_id='"+txnId+"'";
	        		}        	
		        	ResultSet resultExistingTxnID = statementtxnID.executeQuery(sExistingTxnID);

		        	if(txnId!=null && !resultExistingTxnID.isBeforeFirst())
		    		{
		    			int updateQuery = 0;
			        	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MMM dd, yyyy z");
						java.sql.Date expiry_Date = new java.sql.Date(dateFormat.parse(next_payment_date).getTime());
			        	Date today = new Date();
			        	java.sql.Date system_date = new java.sql.Date(today.getTime());
						String Subscription_Renewal = "";
			        	if(ipnInfo.getPaymentType()!=null && ipnInfo.getPaymentType().equalsIgnoreCase("Initial"))
		        		{
			    			pstatementInitialAgreementPayment.setInt(1, account_id);
			    			pstatementInitialAgreementPayment.setString(2, txnId);
			    			
			    			if(paymentStatus==null&&txn_type!=null&&txn_type.equalsIgnoreCase("recurring_payment_profile_created")&&mc_gross.equals("0.00"))
			    			{
			    				paymentStatus="Completed";
			    			}
			    			pstatementInitialAgreementPayment.setString(3, paymentStatus);	        		    		
			        		pstatementInitialAgreementPayment.setString(4, product_name);
			        		pstatementInitialAgreementPayment.setString(5, mc_gross);
			        		pstatementInitialAgreementPayment.setString(6, mc_currency);
			        		pstatementInitialAgreementPayment.setString(7, payment_date);
			        		pstatementInitialAgreementPayment.setString(8, next_payment_date);
							pstatementInitialAgreementPayment.setDate(9, expiry_Date);							
							pstatementInitialAgreementPayment.setString(10, payment_cycle);
							pstatementInitialAgreementPayment.setString(11, recurring_payment_id);
							pstatementInitialAgreementPayment.setString(12, payer_id);
							
				        	if((paymentStatus!=null && paymentStatus.equalsIgnoreCase("Completed")) || (txn_type!=null&&txn_type.equalsIgnoreCase("recurring_payment_profile_created")&&mc_gross.equals("0.00")))
				        	{
				        		pstatementInitialAgreementPayment.setString(13, "Active");
				        	}
				        	else
				        	{
				        		pstatementInitialAgreementPayment.setString(13, "Unkown");    		
				        	}

				        	pstatementInitialAgreementPayment.setDate(14, system_date);
				        	pstatementInitialAgreementPayment.setString(15, amount_per_cycle);
		    				try
		    				{
			    				updateQuery = pstatementInitialAgreementPayment.executeUpdate();			    				
		    				}
		    				catch (SQLException e) 
		    				{
		    					updateQuery=0;
		    				}
		    				if(updateQuery!=0)
		    				{
		    					if((paymentStatus!=null&&paymentStatus.equals("Completed")) || (txn_type!=null&&txn_type.equalsIgnoreCase("recurring_payment_profile_created")&&mc_gross.equals("0.00")))
		    					{
			    					////////////////Select state of old agreement //////////////////////////////////////////////////////////////////
		    			        	String sOldAgeementState = "select * from Account_Agreement where agreement_id='"+old_agreement_id+"'";	        	
		    			        	ResultSet resultOldAgeementState = statementOldAgeementState.executeQuery(sOldAgeementState);
		    			        	String old_agreement_state=null;
		    			        	while(resultOldAgeementState.next())
		    			        	{
		    			        		old_agreement_state = resultOldAgeementState.getString("state");
		    			        	}
			    					
			    					//////////////////////update Account_Payment table with remarks for the old cancelled agreement/////////////////
			    					if(old_agreement_state!=null&&old_agreement_state.equalsIgnoreCase("Updating"))
			    					{	
			    						String sAgreementRemarks = "update Account_Payment set remarks=?, state=? where agreement_id='"+old_agreement_id+"'";
			    						PreparedStatement pstatementAgreementRemarks = connection.prepareStatement(sAgreementRemarks);
			    						remarks="This agreement was updated to a new agreement with agreement_id="+recurring_payment_id;
			    						pstatementAgreementRemarks.setString(1, remarks);
			    						pstatementAgreementRemarks.setString(2, "Updated");
			    						pstatementAgreementRemarks.executeUpdate();
			    						pstatementAgreementRemarks.close();
			    						
				    					String sAgreementState = "update Account_Agreement set State='Updated' where Account_id="+account_id+" and agreement_id='"+old_agreement_id+"' and State='Updating'";
				    					PreparedStatement pstatementAgreementState = connection.prepareStatement(sAgreementState);
				    					pstatementAgreementState.executeUpdate();
				    					pstatementAgreementState.close();
			    					}
			    					//////////////////////////Update Account_Agreement table/////////////////////////////////
			    					String sAgreementState = "update Account_Agreement set State='Active' where Account_id="+account_id+" and agreement_id='"+recurring_payment_id+"' and State='Pending'";
			    					PreparedStatement pstatementAgreementState = connection.prepareStatement(sAgreementState);
			    					pstatementAgreementState.executeUpdate();
			    					pstatementAgreementState.close();
			    					/////////////////////////Update Account table //////////////////////////////////////////
				    				String sAccount = "update Accounts set Subscribed=?, Expiry_Date=?, No_of_Students=?, FrequencyInterval=?, Last_Payment=?, Last_Payment_Date=?, amount_per_cycle=? where account_id="+account_id;
				    				PreparedStatement pstatementAccount = connection.prepareStatement(sAccount);
				    				pstatementAccount.setInt(1, 1);
				    				pstatementAccount.setDate(2, expiry_Date);
				    				pstatementAccount.setInt(3, no_Of_Students);
				    				pstatementAccount.setInt(4, frequencyInterval);
				    				pstatementAccount.setInt(5, (int)Double.parseDouble(mc_gross));
				    				pstatementAccount.setDate(6, system_date);
				    				pstatementAccount.setInt(7, (int) Double.parseDouble(amount_per_cycle));
				    				try
				    				{
					    				updateQuery = pstatementAccount.executeUpdate();			    				
				    				}
				    				catch (SQLException e) 
				    				{
				    					updateQuery=0;
				    				}
				    				pstatementAccount.close();
		    					}
		    				}
		        		}
		        		//////////////////////////////Handle the recurring payment//////////////////////////////////////////////////////////////
			        	else
		        		{
		        			Subscription_Renewal=" Renewal";
		        			pstatementAgreementRecurringPayment.setString(1, txnId);
		        			pstatementAgreementRecurringPayment.setString(2, recurring_payment_id);
		        			pstatementAgreementRecurringPayment.setString(3, paymentStatus);	        		    		
		        			pstatementAgreementRecurringPayment.setString(4, mc_gross);
		        			pstatementAgreementRecurringPayment.setString(5, payment_date);
		        			pstatementAgreementRecurringPayment.setString(6, next_payment_date);
		        			pstatementAgreementRecurringPayment.setDate(7, expiry_Date);
		        			pstatementAgreementRecurringPayment.setDate(8, system_date);
		    				try
		    				{
			    				updateQuery = pstatementAgreementRecurringPayment.executeUpdate();			    				
		    				}
		    				catch (SQLException e) 
		    				{
		    					updateQuery=0;
		    				}
		    				
		    				if(updateQuery!=0)
		    				{
		    					if(paymentStatus!=null&&paymentStatus.equals("Completed"))
		    					{
			    					/////////////////////////Update Account_Agreement table //////////////////////////////////////////
				    				String sAccountAgreement = "update Account_Agreement set Expiry_Date=? where account_id="+account_id+" agreement_id='"+recurring_payment_id+"'";
				    				PreparedStatement pstatementAccountAgreement = connection.prepareStatement(sAccountAgreement);
				    				pstatementAccountAgreement.setDate(1, expiry_Date);
				    				try
				    				{
					    				updateQuery = pstatementAccountAgreement.executeUpdate();			    				
				    				}
				    				catch (SQLException e) 
				    				{
				    					updateQuery=0;
				    				}
				    				pstatementAccountAgreement.close();
			    					/////////////////////////Update Account table //////////////////////////////////////////
				    				String sAccount = "update Accounts set Expiry_Date=?, Last_Payment=?, Last_Payment_Date=? where account_id="+account_id;
				    				PreparedStatement pstatementAccount = connection.prepareStatement(sAccount);
				    				pstatementAccount.setDate(1, expiry_Date);
				    				pstatementAccount.setInt(2, (int)Double.parseDouble(mc_gross));
				    				pstatementAccount.setDate(3, system_date);
				    				try
				    				{
					    				updateQuery = pstatementAccount.executeUpdate();			    				
				    				}
				    				catch (SQLException e) 
				    				{
				    					updateQuery=0;
				    				}
				    				pstatementAccount.close();				    				
		    					}
		    					else
		    					{
		    						///////Here we should cancel the agreement /////////////////////////////////////
		    						
		    						String accessToken = null;
		    						accessToken = GenerateAccessToken.getAccessToken();		
		    						@SuppressWarnings("deprecation")
		    						APIContext apiContext = new APIContext(accessToken);
		    						Agreement agreement = Agreement.get(apiContext, recurring_payment_id);
		    						AgreementStateDescriptor agreementStateDescriptor = new AgreementStateDescriptor();
		    						agreementStateDescriptor.setNote("Cancelling the agreement");
		    						agreement.cancel(apiContext, agreementStateDescriptor);
		    						
		    					}
		    				}
		        		}
		    			if(updateQuery!=0)
		    			{
		    				if(paymentStatus!=null&& paymentStatus.equals("Completed"))
		    				{
			    				///////////////Send success email ////////////////////////
			    				if(updateQuery!=0)
			    				{
			    					String msg_subject = "Your Successful EduHappy Subscription"+Subscription_Renewal;
			    					String msg_body = "\nThank you for your EduHappy Subscription"+Subscription_Renewal+".<br/><br/>";
			    					msg_body = msg_body + "You have full access to your account until "+expiry_Date+" and your subscription will be automatically renewed on "+next_payment_date+".<br/><br/>";
			    					msg_body = msg_body + "If at anytime you need to cancel your subscription, please go to your Profile Settings and click on Cancel Subscription.<br/><br/>";
			    					msg_body = msg_body + "Should you need to updated subscription to change the billing cycle or to purchase more students licenses, please go to your Profile Settings and click on Update Subscription. "
			    							+ "You will have the option to add more students with no minimum or maximum limits restriction. You will also be able to upgrade the billing cycle as needed. "
			    							+ "However, you will not be able to downgrade the billing cycle option.<br/><br/>";
			    					msg_body = msg_body + "To check the Licenses Information and know all the details about the students licenses, please go to your Profile Settings and click on Licenses Information.<br/><br/>";
			    					msg_body = msg_body+ "If you need any help, please contact us at info@eduhappy.com.<br/><br/>";
			    					msg_body = msg_body+ "Best of Luck,<br/><br/>";
			    					msg_body = msg_body+ "EduHappy Team<br/><br/>";
			    					msg_body = msg_body+ "<a href='http://www.edu-happy.com'>http://www.edu-happy.com</a><br/><br/>";
			    					msg_body = msg_body+ "Email: info@edu-happy.com";
			    					EmailSender emailsender = new EmailSender();
			    					AccountManagement accountManagement = new AccountManagement();
			    					Account_Details account_Details = new Account_Details();
			    					account_Details = accountManagement.getAccountDetails(account_id);
			    					emailsender.sendemail(account_Details.getEmail(), msg_subject, msg_body);
			    					/////////////Send success message to EduHappy team/////////////////
			    					emailsender.sendemail("info@edu-happy.com", "New Subscription"+Subscription_Renewal+" Successfull", "You have new subscription"+Subscription_Renewal+". Account ID = "+account_id+" Txn_id = "+txnId+" Agreement_id = "+recurring_payment_id);
			    				}
			    				else
			    				{
			    					String msg_subject = "Your EduHappy Subscription"+Subscription_Renewal;
			    					String msg_body = "\nThank you for your EduHappy Subscription"+Subscription_Renewal+".<br/><br/>";
			    					msg_body = msg_body+ "Although the payment is completed and the Subscription"+Subscription_Renewal+" is successful, something wrong happened in our database.<br/><br/>";
			    					msg_body = msg_body+ "We are working on it to fix the issue as soon as possible.<br/><br/>";
			    					msg_body = msg_body+ "We appreciate your patience.<br/><br/>";
			    					msg_body = msg_body+ "If you need any help, please contact us at info@eduhappy.com.<br/><br/>";
			    					msg_body = msg_body+ "EduHappy Team<br/><br/>";
			    					msg_body = msg_body+ "<a href='http://www.edu-happy.com'>http://www.edu-happy.com</a><br/><br/>";
			    					msg_body = msg_body+ "Email: info@edu-happy.com";
			    					EmailSender emailsender = new EmailSender();
			    					AccountManagement accountManagement = new AccountManagement();
			    					Account_Details account_Details = new Account_Details();
			    					account_Details = accountManagement.getAccountDetails(account_id);
			    					emailsender.sendemail(account_Details.getEmail(), msg_subject, msg_body);
			    					/////////////Send failure message to EduHappy team/////////////////
			    					String error_msg_subject = "Failure to update accounts table while Subscription"+Subscription_Renewal+" is Successful";
			    					String error_msg_body = "\nCustomer Account_id = "+account_id+" Txn_id = "+txnId+" Agreement_id="+recurring_payment_id;
			    					emailsender.sendemail("info@edu-happy.com", error_msg_subject, error_msg_body);
			    				}
			    				//////////////////////////////////////////////////////////
		    				}
		    				else
		    				{
		    					String msg_subject = "Your EduHappy Subscription"+Subscription_Renewal;
		    					String msg_body = "\nPayment is not Completed. sorry, your Subscription"+Subscription_Renewal+" was not successful";
		    					EmailSender emailsender = new EmailSender();
		    					AccountManagement accountManagement = new AccountManagement();
		    					Account_Details account_Details = new Account_Details();
		    					account_Details = accountManagement.getAccountDetails(account_id);
		    					emailsender.sendemail(account_Details.getEmail(), msg_subject, msg_body);
		    					/////////////Send failure message to EduHappy team/////////////////
		    					emailsender.sendemail("info@edu-happy.com", "Subscription"+Subscription_Renewal+" Not Successful", "Customer with Account ID = "+account_id+" failed new subscription"+Subscription_Renewal+" Txn_id = "+txnId+" Agreement_id = "+recurring_payment_id);		    					
		    				}

		    			}
		    			else
		    			{
		    				if(paymentStatus!=null&& paymentStatus.equals("Completed"))
		    				{
		    					String msg_subject = "Your EduHappy Subscription"+Subscription_Renewal;
		    					String msg_body = "\nThank you for your EduHappy Subscription"+Subscription_Renewal+".<br/><br/>";
		    					msg_body = msg_body+ "Although the payment is completed and the Subscription"+Subscription_Renewal+" is successful, something wrong happened in our database.<br/><br/>";
		    					msg_body = msg_body+ "We are working on it to fix the issue as soon as possible.<br/><br/>";
		    					msg_body = msg_body+ "We appreciate your patience.<br/><br/>";
		    					msg_body = msg_body+ "If you need any help, please contact us at info@eduhappy.com.<br/><br/>";
		    					msg_body = msg_body+ "EduHappy Team<br/><br/>";
		    					msg_body = msg_body+ "<a href='http://www.edu-happy.com'>http://www.edu-happy.com</a><br/><br/>";
		    					msg_body = msg_body+ "Email: info@edu-happy.com";
		    					EmailSender emailsender = new EmailSender();
		    					AccountManagement accountManagement = new AccountManagement();
		    					Account_Details account_Details = new Account_Details();
		    					account_Details = accountManagement.getAccountDetails(account_id);
		    					emailsender.sendemail(account_Details.getEmail(), msg_subject, msg_body);
		    					/////////////Send failure message to EduHappy team/////////////////
		    					String error_msg_subject = "Failure to create transaction record while Subscription"+Subscription_Renewal+" is Successful";
		    					String error_msg_body = "\nCustomer Account_id = "+account_id+" Txn_id = "+txnId+" Agreement_id="+recurring_payment_id;
		    					emailsender.sendemail("info@edu-happy.com", error_msg_subject, error_msg_body);
		    				}
		    			}
		    		}
	        	}	        		    			    		
	    		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    			
			} 
	        catch (IpnException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		catch (SQLException e) 
	        {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PayPalRESTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}

