package controllers.account;

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
 * Servlet implementation class Get_Licenses_Details
 */
@WebServlet("/Get_Licenses_Details")
public class Get_Licenses_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Licenses_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();
		Account_Details account_Details = new Account_Details();
		int account_id = accountManagement.getAdminAccount(admin_id);
		account_Details = accountManagement.getAccountDetails(account_id);
		request.setAttribute("Account_Details", account_Details);
		String address = "/Account/Display_Licenses_Details.jsp";
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
