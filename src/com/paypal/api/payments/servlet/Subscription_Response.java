package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import com.paypal.api.payments.AgreementStateDescriptor;
import com.paypal.api.payments.util.GenerateAccessToken;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import model.beans.account.Account_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Subscription_Response
 */
@WebServlet("/Subscription_Response")
public class Subscription_Response extends HttpServlet {
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
	
	public Subscription_Response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultPrinter.addResult(request, response, "Payment with PayPal", Agreement.getLastRequest(), Agreement.getLastResponse(), null);
		String accessToken = null;
		String old_Agreement_id = null;
		//String sAccountAgreement = "update Account_Agreement set agreement_id=?, State=? where token='"+request.getParameter("token")+"'";
		String sAccountAgreement = "Insert into Account_Agreement (Account_id, Agreement_id, token, state, No_Of_Students, FrequencyInterval, Old_Agreement_id) values(?,?,?,?,?,?,?)";
		try(Connection connection = DbConnector.connectToDb();
        		PreparedStatement pstatement = connection.prepareStatement(sAccountAgreement);) {
			
			accessToken = GenerateAccessToken.getAccessToken();		
			@SuppressWarnings("deprecation")
			APIContext apiContext = new APIContext(accessToken);
			Agreement agreement = Agreement.execute(apiContext, request.getParameter("token"));
    		int account_id = Integer.parseInt(request.getParameter("Account_id"));
    		int no_Of_Students = Integer.parseInt(request.getParameter("No_Of_Students"));
    		int frequencyInterval = Integer.parseInt(request.getParameter("FrequencyInterval"));
    		
    		AccountManagement accountManagement = new AccountManagement();
    		Account_Details account_Details = new Account_Details();
    		account_Details = accountManagement.getAccountDetails(account_id);
    		old_Agreement_id = account_Details.getAgreement_id();
    		
    		pstatement.setInt(1, account_id);
    		pstatement.setString(2, agreement.getId());
    		pstatement.setString(3, request.getParameter("token"));
			pstatement.setString(4, "Pending");
    		pstatement.setInt(5, no_Of_Students);
    		pstatement.setInt(6, frequencyInterval);
    		pstatement.setString(7, account_Details.getAgreement_id());
    		pstatement.executeUpdate();  	
    		
    		
    		if(account_Details.getAgreement_id()!=null)
    		{
    			String sAgreementState = "update Account_Agreement set state=? where agreement_id='"+account_Details.getAgreement_id()+"'";
    			PreparedStatement pstatementAgreementState = connection.prepareStatement(sAgreementState);
    			pstatementAgreementState.setString(1, "Updating");
    			pstatementAgreementState.executeUpdate();
    			pstatementAgreementState.close();
    		}
    		///////////Cancel previous agreement if we are updating the subscription////////////////////
    		if(old_Agreement_id!=null && !account_Details.getAgreement_State().equalsIgnoreCase("Cancelled"))
    		{
        		Agreement old_Agreement = Agreement.get(apiContext, old_Agreement_id);
        		AgreementStateDescriptor agreementStateDescriptor = new AgreementStateDescriptor();
        		agreementStateDescriptor.setNote("Cancelling the agreement");
        		old_Agreement.cancel(apiContext, agreementStateDescriptor);
    		}
    		////////////////////////////////////////////////////////////////////////////////////////////
    		
		}
			catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		HttpSession session = request.getSession();
		session.setAttribute("Update_Account_Details", "1");
    	String address = "/Account/Display_Subscription_Response.jsp";
		if(old_Agreement_id!=null)
		{
			String message = "Thank you for updating your subscription. We are processing your request and will activate the new subscription settings in a moment.";
			address = "/Subscription_Details?message_SubscriptionUpdate="+message;
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
