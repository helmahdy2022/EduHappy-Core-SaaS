package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.student.Attendance_Sessions;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAttendance;

/**
 * Servlet implementation class Get_Attendance_Grading
 */
@WebServlet("/Get_Attendance_Grading")
public class Get_Attendance_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Attendance_Grading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int course_id= Integer.parseInt(request.getParameter("Course_id"));
		int student_id = Integer.parseInt(request.getParameter("Student_id"));
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		int current_school_year_id = schoolYearManagement.getCurrentSchoolYearID(school_id);
		ServiceAttendance serviceAttendance = new ServiceAttendance();
		Attendance_Sessions Attendance_Grading = new Attendance_Sessions();
		Attendance_Grading = serviceAttendance.getAttendance(student_id, course_id, current_school_year_id);
		request.setAttribute("Attendance_Grading", Attendance_Grading);
		String address = "/Teacher/Assignments/Display_Attendance_Grading.jsp";
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
