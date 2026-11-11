package com.paymob.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class Payment_Response
 */
@WebServlet("/Payment_Response")
public class Payment_Response extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment_Response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			int order_id = Integer.parseInt(request.getParameter("order")); 
			ParentManagement parentManagement = new ParentManagement();
			int parent_id = parentManagement.getParentByOrder(order_id);
			System.out.println("Parent_id = :" + parent_id);
			int api_source = parentManagement.getapisourceyOrder(order_id);
			String txn_response_code = null;
			String message=null;
			switch(api_source) 
			{
			   case 0 :
				   message="Transaction is successful."
				   		+ "<br>Thank you for your subscription and payment."
				   		+ "<br>Please provide your child with the following link to login to his/her student portal using the username and password you've just created:"
				   		+ "<br><a href='https://eduhappylms.com/student' target='_blank'>https://eduhappylms.com/student</a>"
				   		+ "<br>Please check your email for more details."
				   		+ "<br>You can also go to Manage Subscriptions area to manage your child's subscription and enrollment.";
			      break; 
			   
			   case 1 :
				    txn_response_code = parentManagement.getErrorByOrder(order_id);
					message="Transaction failed with the following error code: "+txn_response_code+". Please try again or use different credit card";
			      break; 
			      
			   case 2 :
					txn_response_code = parentManagement.getErrorByOrder(order_id);
					message="Transaction failed when we tried to use your  previouse billing data to process your order with the following error code: "+txn_response_code+". Please use different credit card";
			      break; 			      

			   case 4 :
					int transaction_id = Integer.parseInt(request.getParameter("id")); 
					int fees = Integer.parseInt(request.getParameter("amount_cents")); 
					PaymentManagement paymentManagement = new PaymentManagement();
					paymentManagement.RefundTransaction(transaction_id, fees);
					message="We're so sorry that the transaction faild due to technical issue from our side. We have refunded the full amount and you are not charged any amount. Kindly, try again after a while";
				  break; 
			}
			///////////Get base URL //////////////////////////////////////////////////////////////////////
			StringBuffer url = request.getRequestURL();
			if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
			{
				url.replace(0, 4, "https");
			}
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length());
			/////////////////////////////////////////////////////////////////////////////////////////////
			String address = base+"/Refresh_Parent?message="+message+"&&Parent_id="+parent_id;
			//String address = base+"/Refresh_Parent?message="+message+"&&Parent_id="+parent_id;
			response.sendRedirect(address);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
