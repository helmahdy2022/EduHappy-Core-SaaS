package controllers.parent;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class Unsubscribe_Course
 */
@WebServlet("/Unsubscribe_Course")
public class Unsubscribe_Course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unsubscribe_Course() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id=0;
		int student_id=0;
		String message=null;
		course_id = Integer.parseInt(request.getParameter("Course_id"));
		student_id = Integer.parseInt(request.getParameter("Student_id"));
		CourseManagement courseManagement = new CourseManagement();
		int updateQuery = courseManagement.unsubscribeChildFromElective(course_id, student_id);
    	if (updateQuery!=0 && updateQuery!=-1)
    		message = "Subscription for the selected subject has been canceled. You won't be charged for the subscription fees anymore.";
    	else
    	{
        	if(updateQuery==-1)
        	{
        		message = "Duplicate Entry. Subscription was not canceled";
        	}
        	else
        	{
        		message = "Subscription not canceled";
        	}
    	}
    	String address ="/Parent/Subscriptions/Display_Unsubscribe_Subject.jsp?message="+message;
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
