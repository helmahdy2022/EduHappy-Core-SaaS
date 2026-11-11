package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Forgot_Password
 */
@WebServlet("/Forgot_Password")
public class Forgot_Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgot_Password() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		///////////Get base URL //////////
		StringBuffer url = request.getRequestURL();
		if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
		{
			url.replace(0, 4, "https");
		}
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length());
		/////////////////////////////////////////////////////////////////////////////////
		AccountManagement accountManagement = new AccountManagement();
		int admin_id = accountManagement.getAdminByUsername(username);
		String message=null;
		if(admin_id==0)
		{
			message="This username does not exist in our database.<br/><a href='javascript:window.history.back();'>Back</a>";
		}
		else
		{
			message = accountManagement.setPasswordToken(admin_id, username, base);			
		}
		String address = "/Admin/Display_Forgot_Password.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
