package controllers.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Courses;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Get_Exam_Schedule
 */
@WebServlet("/Get_Exam_Schedule")
public class Get_Exam_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Exam_Schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int student_id = 0;
		String source = null;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			source = "Parent";
		}
		else if(session.getAttribute("Student_Session")!=null) 
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
			source = "Student";
		}		
		
		
		int grade_id=0;
		String address = null;

		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
			address = "/Admin/Grades/Display_Exam_Schedule.jsp";
			source = "Admin";
		}
		if(grade_id==0 && student_id!=0)
		{
			StudentManagement studentManagement = new StudentManagement();
			grade_id = studentManagement.getStudentGrade(student_id);
			address = "/Student/ExamSchedule/Display_Exam_Schedule.jsp?Source="+source+"&&Student_id="+student_id;
			source="Student";
		}
		CourseManagement courseManagement = new CourseManagement();
		Courses Courses = new Courses();
		Courses = courseManagement.getCourseList(grade_id);
		request.setAttribute("Courses", Courses);
		
		GradeManagement gradeManagement = new GradeManagement();
		ArrayList<ArrayList<String>> ExamScheduleDetails = new ArrayList<ArrayList<String>>();
		try {
			ExamScheduleDetails = gradeManagement.getExamSchedule(grade_id, student_id, source);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ExamScheduleDetails", ExamScheduleDetails);	
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
