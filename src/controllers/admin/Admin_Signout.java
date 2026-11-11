package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Sign_Out
 */
@WebServlet("/Admin_Signout")
public class Admin_Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Signout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		
		if(session.getAttribute("Admin_Session")!=null)
		{
			session.removeAttribute("Admin_Session");
			session.removeAttribute("newAdminMessagesCount");
			session.removeAttribute("Schools");
			session.removeAttribute("Countries");
			session.removeAttribute("Days");
	    	session.removeAttribute("Subscription_Valid");
	    	session.removeAttribute("Subscription_Expiry");
	    	session.removeAttribute("Trial_Valid");
	    	session.removeAttribute("Trial_Expiry");
	    	session.removeAttribute("Semester_Source");
	    	session.removeAttribute("message");
	    	session.removeAttribute("Account_Details");
		}
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Admin/Admin_Login.jsp");
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
