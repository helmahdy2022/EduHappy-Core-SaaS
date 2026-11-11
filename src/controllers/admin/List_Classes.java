package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Classes;
import model.dbHelpers.admin.GetClasses;

/**
 * Servlet implementation class List_Classes
 */
@WebServlet("/List_Classes")
public class List_Classes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Classes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		/////////////////////////////Retrieve Classes///////////////////////////////
    	GetClasses classes = new GetClasses();
    	Classes Classes = new Classes();
    	Classes = classes.doGetClasses(grade_id);
    	request.setAttribute("Classes", Classes);	
	    /////////////////////////////End retrieve Classes///////////////////////////////
		String address;
		address = "/Global/Display_Classes.jsp";
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
