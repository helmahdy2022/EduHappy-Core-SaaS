package controllers.parent;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Courses;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Get_Subscription_Courses
 */
@WebServlet("/Get_Subscription_Courses")
public class Get_Subscription_Courses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Subscription_Courses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int student_id = 0;
		if (request.getParameter("Student_id")!=null)
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		StudentManagement studentManagement = new StudentManagement();
		int grade_id = studentManagement.getStudentGrade(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Courses Courses = new Courses();
		Courses = courseManagement.getNonSubscribedCourseList(grade_id, student_id);
		request.setAttribute("Courses", Courses);
		try {
			Courses SubscribedCourses = courseManagement.getStudentSubscribedElectives(student_id);
			request.setAttribute("SubscribedCourses", SubscribedCourses);
			String address;
			address = "/Parent/Subscriptions/Manage_Subscriptions.jsp?Student_id="+student_id;
			RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
