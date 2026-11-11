package controllers.parent;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class Parent_Forgot_Password
 */
@WebServlet("/Parent_Forgot_Password")
public class Parent_Forgot_Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parent_Forgot_Password() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		ParentManagement parentManagement = new ParentManagement();
		int parent_id = parentManagement.getParentByUsername(username);
		String message=null;
		if(parent_id==0)
		{
			message="This username does not exist in our database.<br/><a href='javascript:window.history.back();'>Back</a>";
		}
		else
		{
			message = parentManagement.setPasswordToken(parent_id, username, base);			
		}
		String address = "/Parent/Display_Forgot_Password.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
