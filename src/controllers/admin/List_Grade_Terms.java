package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Semester_Terms;
import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class List_Grade_Terms
 */
@WebServlet("/List_Grade_Terms")
public class List_Grade_Terms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Grade_Terms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int school_year_id=0;
		if(request.getParameter("School_year_id")!=null)
		{
			school_year_id = Integer.parseInt(request.getParameter("School_year_id"));
		}
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		GradeManagement gradeManagement = new GradeManagement();
		Grade_Semester_Terms Grade_Semester_Terms = new Grade_Semester_Terms();
		
		Grade_Semester_Terms = gradeManagement.getGradeTermsNoSemesters(school_year_id, grade_id);
		request.setAttribute("Grade_Semester_Terms", Grade_Semester_Terms);
		String address;
		address = "/Admin/Grades/Display_Grade_Terms.jsp";
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
