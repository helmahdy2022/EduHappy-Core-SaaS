package com.paypal.api.payments.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Subscription_Details
 */
@WebServlet("/Subscription_Details")
public class Subscription_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscription_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);
		Account_Details account_Details = new Account_Details();
		account_Details = accountManagement.getAccountDetails(account_id);		
		request.setAttribute("Account_Details", account_Details);		
		String message_Cancel = "";
		message_Cancel = request.getParameter("message_Cancel");
		String message_SubscriptionUpdate = "";
		message_SubscriptionUpdate = request.getParameter("message_SubscriptionUpdate");
		String address = "Account/Subscription_Details.jsp?message_Cancel="+message_Cancel+"&&message_SubscriptionUpdate="+message_SubscriptionUpdate;		  		
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
