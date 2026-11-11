package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.util.GenerateAccessToken;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import model.beans.account.Account_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Subscription_Request
 */
@WebServlet("/Subscription_Request")
public class Subscription_Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(Subscription_Request.class);


	//private static final boolean Object = false;
	Map<String, String> map = new HashMap<String, String>();

	public void init(ServletConfig servletConfig) throws ServletException {
		// ##Load Configuration
		// Load SDK configuration for
		// the resource. This intialization code can be
		// done as Init Servlet.
		InputStream is = Subscription_Request.class.getResourceAsStream("/resources/sdk_config.properties");
		try {
			PayPalResource.initConfig(is);
		} catch (PayPalRESTException e) {
			LOGGER.fatal(e.getMessage());
		}

	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Subscription_Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session= req.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);
		Account_Details account_Details = new Account_Details();
		account_Details = accountManagement.getAccountDetails(account_id);
		APIContext apiContext = null;
		String accessToken = null;
		String address=null;
		//String sAccountToken = "insert into Account_Agreement(Account_id, token, No_Of_Students, FrequencyInterval, State, old_agreement_id) values(?,?,?,?,?,?)";
		
		/////////////////////////////////////////////////////////////////////////////////////////////////	
		int studentsNumber = 0;
		int extraStudentsNumber=0;
		int remaining_Amount=0;
		int min_Number_Of_Students = account_Details.getNo_Of_Students();
		if(min_Number_Of_Students==200)
		{
			min_Number_Of_Students = 200;
		}
		if(request.getParameter("Number_Of_Students")!=null && !request.getParameter("Number_Of_Students").equals(""))
		{
			studentsNumber = Integer.parseInt(request.getParameter("Number_Of_Students"));
		}
		if(request.getParameter("Number_Of_Extra_Students")!=null && !request.getParameter("Number_Of_Extra_Students").equals(""))
		{
			extraStudentsNumber = Integer.parseInt(request.getParameter("Number_Of_Extra_Students"));
			int no_Of_Students = account_Details.getNo_Of_Students();
			studentsNumber = no_Of_Students + extraStudentsNumber;
			remaining_Amount = account_Details.getRemaining_Amount();
		}
		int frequencyInterval = 0;
		if(request.getParameter("Subscription_Option")!=null && !request.getParameter("Subscription_Option").equals(""))
		{
			frequencyInterval = Integer.parseInt(request.getParameter("Subscription_Option"));
		}
		double feesPerStudent = 2;		
		////////////////////////////////////////////////////////////////////////////////////////////////
		if(studentsNumber<min_Number_Of_Students || frequencyInterval==0)
		{						
	    	String message=null;
	    	if(studentsNumber<min_Number_Of_Students)
	    	{
	    		message = "You have entered number of students less than "+min_Number_Of_Students+". Please enter a valid number of students greater than or equal "+min_Number_Of_Students+" to proceed with the subscription";
	    	}
	    	if(frequencyInterval==0)
	    	{
	    		message = "You didn't select the subscription option. Please choose one";
	    	}
	    	if(studentsNumber<min_Number_Of_Students && frequencyInterval==0)
	    	{
	    		message = "You have entered number of students less than "+min_Number_Of_Students+". Please enter a valid number of students greater than or equal "+min_Number_Of_Students+" to proceed with the subscription";
	    		message= message+"<br>You didn't select the subscription option. Please choose one";
	    	}
			address = "/Account/Subscription_Details.jsp?message="+message;
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	 		dispatcher.forward(request, response);
		}
		else
		{
			try(Connection connection = DbConnector.connectToDb();) {		
				accessToken = GenerateAccessToken.getAccessToken();
				
				apiContext = new APIContext(accessToken);
				Plan plan = new Plan();
				if(account_Details.getAgreement_id()!=null)
				{
					plan.setName("EduHappy Subsription Update");
					plan.setDescription("EduHappy Subsription Plan Update");
				}
				else
				{
					plan.setName("EduHappy Subsription");
					plan.setDescription("EduHappy Subsription Plan");
				}
				plan.setType("INFINITE");
				///////////////////////////////////////////////////////////
				
				String totalAmount = String.valueOf((int)(studentsNumber*feesPerStudent*frequencyInterval));
				PaymentDefinition paymentDefinition = new PaymentDefinition();
				paymentDefinition.setName("Regular Payments");
				paymentDefinition.setType("REGULAR");
				//paymentDefinition.setFrequency("Day");
				paymentDefinition.setFrequency("Month");
				paymentDefinition.setFrequencyInterval(String.valueOf(frequencyInterval));
				paymentDefinition.setCycles("0");
				Currency subscriptionAmount = new Currency("USD", totalAmount);
				paymentDefinition.setAmount(subscriptionAmount);
				MerchantPreferences merchantPreferences = new MerchantPreferences();
				merchantPreferences.setAutoBillAmount("YES");
				merchantPreferences.setInitialFailAmountAction("CANCEL");
				merchantPreferences.setAutoBillAmount("NO");
				merchantPreferences.setMaxFailAttempts("0");
				String setupFees = String.valueOf((int)Double.parseDouble(totalAmount)-remaining_Amount);
				Currency setupAmount = new Currency("USD", setupFees);
				merchantPreferences.setSetupFee(setupAmount);
				ChargeModels chargeModelShipping = new ChargeModels();
				chargeModelShipping.setType("SHIPPING");
				Currency shippingAmount = new Currency("USD", "0");
				chargeModelShipping.setAmount(shippingAmount);
				
				ChargeModels chargeModelTax = new ChargeModels();
				chargeModelTax.setType("TAX");
				Currency taxAmount = new Currency("USD", "0");
				chargeModelTax.setAmount(taxAmount);
				List<ChargeModels> chargeModels = new ArrayList<>();
				chargeModels.add(0, chargeModelShipping);
				chargeModels.add(1, chargeModelTax);
				paymentDefinition.setChargeModels(chargeModels);
				String guid = UUID.randomUUID().toString().replaceAll("-", "");
				
				StringBuffer url = request.getRequestURL();
				String protocol = null;
				String serverPort = null;
				if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
				{
					protocol = "s://"; 
					//protocol = "://";
					serverPort = "";
				}
				else
				{
					protocol = "://";
					serverPort = ":" + request.getServerPort();
				}
				merchantPreferences.setCancelUrl(request.getScheme() + protocol
						+ request.getServerName() + serverPort
						+ request.getContextPath() + "/Subscription_Cancel?guid=" + guid+"&&Account_id="+account_id);
				merchantPreferences.setReturnUrl(request.getScheme() + protocol
						+ request.getServerName() + serverPort
						+ request.getContextPath() + "/Subscription_Response?guid=" + guid+"&&Account_id="+account_id+"&&No_Of_Students="+studentsNumber+"&&FrequencyInterval="+frequencyInterval);	
				List<PaymentDefinition> paymentDefinitions = new ArrayList<>();
				paymentDefinitions.add(0, paymentDefinition);
				plan.setPaymentDefinitions(paymentDefinitions);
				plan.setMerchantPreferences(merchantPreferences);

				Plan createdPlan = plan.create(apiContext);
				
				Patch patch = new Patch();
				patch.setPath("/");
				patch.setOp("replace");			
				Plan newPlan = new Plan();
				newPlan.setState("ACTIVE");
				patch.setValue(newPlan);
							
				List<Patch> patchRequest = new ArrayList<>();
				patchRequest.add(patch);
				createdPlan.update(apiContext, patchRequest);
				
				Plan updatedPlan = Plan.get(apiContext, createdPlan.getId());
				
				////////////////Create Agreement /////////////////////////////////////
				Agreement agreement = new Agreement();
				agreement.setName("EduHappy Subscription Agreement");
				String Subscription_Update=".";
				if(account_Details.getAgreement_id()!=null)
				{
					Subscription_Update=" Update - settlement amount $"+setupFees+".";
				}
				agreement.setDescription("EduHappy Subscription"+Subscription_Update+" Total amount = $" +totalAmount+" for "+studentsNumber+" Students. Billing every "+frequencyInterval+" Month(s)");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
			    Calendar cal = Calendar.getInstance(); 
			    cal.setTime(new Date()); 
			    //cal.add(Calendar.SECOND, 15);
			    cal.add(Calendar.MONTH, frequencyInterval);
			    cal.getTime();			
				agreement.setStartDate(dateFormat.format(cal.getTime()));			
				Plan agreementPlan = new Plan();
				agreementPlan.setId(updatedPlan.getId());
				agreement.setPlan(agreementPlan);			
				Payer payer = new Payer();
				payer.setPaymentMethod("paypal");
				agreement.setPayer(payer);	
				Agreement createdAgreement = agreement.create(apiContext);
				////////////////////////////////Link the token with the account_id//////////////////////////////////////			
/*	        	pstatement.setInt(1, account_id);
	    		pstatement.setString(2, createdAgreement.getToken());
	    		pstatement.setInt(3, studentsNumber);
	    		pstatement.setInt(4, frequencyInterval);
	    		pstatement.setString(5, "Creating");
	    		pstatement.setString(6, account_Details.getAgreement_id());
	    		pstatement.executeUpdate();*/
/*	    		if(account_Details.getAccount_id()!=0)
	    		{
	    			String sAgreementState = "update Account_Agreement set state=? where agreement_id='"+account_Details.getAgreement_id()+"'";
	    			PreparedStatement pstatementAgreementState = connection.prepareStatement(sAgreementState);
	    			pstatementAgreementState.setString(1, "Updating");
	    			pstatementAgreementState.executeUpdate();
	    			pstatementAgreementState.close();
	    		}*/
	    		////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    		List<Links> x = createdAgreement.getLinks();
				Iterator<Links> links = x.iterator();
				while (links.hasNext()) {
					Links link = links.next();
					if (link.getRel().equalsIgnoreCase("approval_url")) {
						request.setAttribute("redirectURL", link.getHref());
						address = link.getHref();
					}
				}
				ResultPrinter.addResult(request, response, "Payment with PayPal", Plan.getLastRequest(), Plan.getLastResponse(), null);
				map.put(guid, updatedPlan.getId());			
			}
			catch (SQLException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (PayPalRESTException e) {
				//req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect(address);
		}
	}
}
