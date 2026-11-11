package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grades;
import model.dbHelpers.admin.GetGrades;


/**
 * Servlet implementation class List_Grades
 */
@WebServlet("/List_Grades")
public class List_Grades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Grades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id = Integer.parseInt(request.getParameter("School_id"));
		/////////////////////////////Retrieve Grades///////////////////////////////
    	GetGrades grades = new GetGrades();
    	Grades Grades = new Grades();
    	Grades = grades.doGetGrades(school_id);
    	request.setAttribute("Grades", Grades);	
	    /////////////////////////////End retrieve Grades///////////////////////////////
		 //Enumeration<String> x = request.getHeaderNames();  	
		String address;
		address = "/Admin/Grades/Display_Grades.jsp";
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		//PrintWriter out = response.getWriter();
		//while(x.hasMoreElements())
		//{
		
		//	out.print(x.nextElement()+"<br>");
		//}
		//out.print(request.getHeader("referer"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
