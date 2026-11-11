package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Year_Semesters;

import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class List_Year_Semesters
 */
@WebServlet("/List_Year_Semesters")
public class List_Year_Semesters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Year_Semesters() {
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
		SemesterManagement semesterManagement = new SemesterManagement();
		Year_Semesters Year_Semesters = new Year_Semesters();
		Year_Semesters = semesterManagement.getYearSemesterList(school_year_id);
    	request.setAttribute("Year_Semesters", Year_Semesters);
		String address;
		address = "/Global/Display_Year_Semesters.jsp";
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
