package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.School_Years;
import model.dbHelpers.admin.SchoolYearManagement;

/**
 * Servlet implementation class List_School_Years_Grades
 */
@WebServlet("/List_School_Years_Grades")
public class List_School_Years_Grades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_School_Years_Grades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id = 0;
		if(request.getParameter("School_id")!=null)
		{
			school_id = Integer.parseInt(request.getParameter("School_id"));
		}
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		School_Years School_Years = new School_Years();
		School_Years = schoolYearManagement.getSchoolYearsList(school_id);
    	request.setAttribute("School_Years", School_Years);
		String address;
			 
		address = "/Admin/Grades/Display_School_Years_Grades.jsp";
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
