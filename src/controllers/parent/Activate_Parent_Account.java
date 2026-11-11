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
 * Servlet implementation class Activate_Parent_Account
 */
@WebServlet("/Activate_Parent_Account")
public class Activate_Parent_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activate_Parent_Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Activation_Key = request.getParameter("Activation_Key");
		ParentManagement parentManagement = new ParentManagement();
		///////////Get base URL //////////
		StringBuffer url = request.getRequestURL();
		if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
		{
			//url.replace(0, 4, "https");
			url.replace(0, 4, "http"); //We have added that temporarliy just to run the live test demo account. In case for production with https, remove the comment from the above line and add comment in this line instead.
	
		}
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length());
		//////////////////////////////////
		String message = parentManagement.activateParentAccount(Activation_Key, base);
   		String address = "/Academy/Display_Parent_Activation.jsp?message="+message;
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
