package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.account.Account_Details;
import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Subscription_Cancel
 */
@WebServlet("/Subscription_Cancel")
public class Subscription_Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscription_Cancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		int account_id=0;
		if(request.getParameter("Account_id")!=null)
		{
			account_id = Integer.parseInt(request.getParameter("Account_id"));
		}
		////////////////////////Delete null record from Account_Agreement table///////////////////
/*		String sOldAgreement = "select old_agreement_id from Account_Agreement where token='"+request.getParameter("token")+"' and account_id="+account_id+" and agreement_id is null";
		String sAccountAgreement = "delete from Account_Agreement where token='"+request.getParameter("token")+"' and account_id="+account_id+" and agreement_id is null";
		try(Connection connection = DbConnector.connectToDb();
        		PreparedStatement pstatement = connection.prepareStatement(sAccountAgreement);
				Statement statementOldAgreement = connection.createStatement();) {
				
			ResultSet resultOldAgreement = statementOldAgreement.executeQuery(sOldAgreement);
			String old_agreement_id=null;
			while(resultOldAgreement.next())
			{
				old_agreement_id = resultOldAgreement.getString("old_agreement_id");
			}
			if(old_agreement_id!=null)
			{
				String sAccountAgreementState = "update Account_Agreement set state='Active' where agreement_id='"+old_agreement_id+"'";
				PreparedStatement pstatementAccountAgreementState = connection.prepareStatement(sAccountAgreementState);
				pstatementAccountAgreementState.executeUpdate();
				pstatementAccountAgreementState.close();
				
				String sAccountPaymentState = "update Account_Payment set state='Active' where recurring_payment_id='"+old_agreement_id+"'";
				PreparedStatement pstatementAccountPaymentState = connection.prepareStatement(sAccountPaymentState);
				pstatementAccountPaymentState.executeUpdate();
				pstatementAccountPaymentState.close();
			}
			pstatement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//////////////////////////////////////////////////////////////////////////////////////////		
		AccountManagement accountManagement = new AccountManagement();
		Account_Details account_Details = new Account_Details();
		account_Details = accountManagement.getAccountDetails(account_id);
		Date trial_Date = account_Details.getTrial_Date();
		int trial_Days = account_Details.getTrial_Days();
	    Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(trial_Date);
	    cal.add(Calendar.DAY_OF_MONTH, trial_Days);
	    Date expirationDate = cal.getTime();
	    String address = null;
	    String message="Subscription Cancelled. We are sorry that the subscription was not successful.";
	    if((date.compareTo(expirationDate)>0 && account_Details.getSubscribed()==0) || (account_Details.getExpiry_Date()!=null && (new java.sql.Date(date.getTime())).compareTo(account_Details.getExpiry_Date())>0))
	    {
	    	address ="/Account/Subscription.jsp?message="+message;
	    }
	    else
	    {
	    	if((account_Details.getExpiry_Date()!=null && (new java.sql.Date(date.getTime())).compareTo(account_Details.getExpiry_Date())<=0))
	    	{
	    		message = "Subscription Update Cancelled. Your current subscription remains the same with no changes.";;
	    	}
	    	address = "/Subscription_Details?message_Cancel="+message;
	    }
		
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
