package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.LoginInfoManagement;

/**
 * Servlet implementation class Check_Username
 */
@WebServlet("/Check_Username")
public class Check_Username extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Username() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("Username");
		LoginInfoManagement loginInfoManagement = new LoginInfoManagement();
		boolean usernameInUse = loginInfoManagement.checkUsername(username);
		PrintWriter out = response.getWriter();	
		if(username!=null&&username.length()<8)
		{
			out.print("<font color='red'>Username must be at least 8 characters</font>");
			out.print("<input type='hidden' name='OkToSubmit' id='OkToSubmit' value='NO'>");
		}
		else
		{
			if(usernameInUse==true)
			{		
				out.print("<font color='red'>Username is not available, choose another one</font>");
				out.print("<input type='hidden' name='OkToSubmit' id='OkToSubmit' value='NO'>");
			}
			else
			{
				out.print("<img src='"+request.getContextPath()+"/images/correct_sign.gif'> <font color='green'>Username is available</font>");
				out.print("<input type='hidden'  name='OkToSubmit' id='OkToSubmit' value='OK'>");
			}
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
