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
 * Servlet implementation class Resubscribe_Course
 */
@WebServlet("/Resubscribe_Course")
public class Resubscribe_Course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resubscribe_Course() {
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
		int updateQuery = courseManagement.resubscribeChildIntoElective(course_id, student_id);
    	if (updateQuery!=0 && updateQuery!=-1)
    		message = "Undo is successfull. You are back to the subscribed state and you will be automatically billed every 4 weeks for the subscription fees";
    	else
    	{
        	if(updateQuery==-1)
        	{
        		message = "Duplicate Entry. Undo Subscription cancel is not successfull";
        	}
        	else
        	{
        		message = "Subscription not undo";
        	}
    	}
    	String address ="/Parent/Subscriptions/Display_Resubscribe_Subject.jsp?message="+message;
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
