package com.paypal.api.payments.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;
import model.beans.account.Account_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Cancel_Supscription
 */
@WebServlet("/Cancel_Supscription")
public class Cancel_Supscription extends HttpServlet {
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
	
    public Cancel_Supscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        try (Connection connection = DbConnector.connectToDb();
        		Statement statementAccountID = connection.createStatement();){
			String accessToken = null;
			String agreement_id = null;
			String message = null;
			accessToken = GenerateAccessToken.getAccessToken();		
			@SuppressWarnings("deprecation")
			APIContext apiContext = new APIContext(accessToken);			
			
			HttpSession session= request.getSession();
			int admin_id = (Integer) session.getAttribute("Admin_Session");
			AccountManagement accountManagement = new AccountManagement();
			int account_id = accountManagement.getAdminAccount(admin_id);			
			Account_Details account_Details = new Account_Details();
			account_Details = accountManagement.getAccountDetails(account_id);
			agreement_id = account_Details.getAgreement_id();
			if(account_Details.isAgreementPending())
			{
				message = "There is subscription in progress. Please try again after the subscription request is completed.";
			}
			else
			{
				if(agreement_id!=null && !account_Details.getAgreement_State().equalsIgnoreCase("Cancelled"))
				{
					Agreement agreement = Agreement.get(apiContext, agreement_id);
					AgreementStateDescriptor agreementStateDescriptor = new AgreementStateDescriptor();
					agreementStateDescriptor.setNote("Cancelling the agreement");
					agreement.cancel(apiContext, agreementStateDescriptor);
					//////////////////Set state to Cancelling //////////////////////////////////////////////
					String sCancellingAgreement = "update Account_Agreement set State='Cancelling' where agreement_id='"+agreement_id+"'";
	        		PreparedStatement pstatementCancellingAgreement = connection.prepareStatement(sCancellingAgreement);
	        		int updateQuery = pstatementCancellingAgreement.executeUpdate();
	        		pstatementCancellingAgreement.close();
	        		/////////////////////////////////////////////////////////////
					if(updateQuery!=0)
					{
		        		message = "<br/>You have successfully cancelled your subscription.<br/><br/>";
						message = message+ "We are sorry to see you go.<br/><br/>";
						message = message+ "You will always be able to renew the subscription through the New Subscription link in your profile settings.<br/><br/>";
					}
				}
				else
				{
					message ="Subscription already Cancelled";
				}
			}	
			session.setAttribute("Update_Account_Details", "1");
			String address = "/Account/Display_Cancel_Subscription.jsp?message="+message;
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	 		dispatcher.forward(request, response);
		}

		catch (PayPalRESTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
