package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.student.Category_Assignments;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAssignment;

/**
 * Servlet implementation class Get_Assignments_Grading
 */
@WebServlet("/Get_Assignments_Grading")
public class Get_Assignments_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Assignments_Grading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int category_id= Integer.parseInt(request.getParameter("Category_id"));
		int student_id = Integer.parseInt(request.getParameter("Student_id"));
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		int current_school_year_id = schoolYearManagement.getCurrentSchoolYearID(school_id);
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		Category_Assignments Category_Assignments_Grading = new Category_Assignments();
		Category_Assignments_Grading = serviceAssignment.getCatAssignment(student_id, category_id, current_school_year_id);  		
		request.setAttribute("Category_Assignments_Grading", Category_Assignments_Grading);
		String address = "/Teacher/Assignments/Display_Category_Assignments_Grading.jsp";
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
