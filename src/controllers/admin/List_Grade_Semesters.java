package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Semesters;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class List_Grade_Semesters
 */
@WebServlet("/List_Grade_Semesters")
public class List_Grade_Semesters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Grade_Semesters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_year_id=0;
		if(request.getParameter("School_Year_id")!=null)
		{
			school_year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		SemesterManagement semesterManagement = new SemesterManagement();
		Grade_Semesters Grade_Semesters = new Grade_Semesters();
		Grade_Semesters = semesterManagement.getGradeSemesterList(school_year_id, grade_id);
    	request.setAttribute("Grade_Semesters", Grade_Semesters);
		String address;
		address = "/Global/Display_Grade_Semesters.jsp";
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
