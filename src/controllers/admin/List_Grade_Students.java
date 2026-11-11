package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Students;
import model.dbHelpers.admin.YearEndProcedures;

/**
 * Servlet implementation class List_Grade_Students
 */
@WebServlet("/List_Grade_Students")
public class List_Grade_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Grade_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		
		YearEndProcedures yearEndProcedures = new YearEndProcedures();
		Students Students = new Students();
		Students = yearEndProcedures.getGradeStudents(grade_id);
		request.setAttribute("Students", Students);
		
		String address; 
		address = "/Admin/YearEnd/Display_Grade_Students.jsp";
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
