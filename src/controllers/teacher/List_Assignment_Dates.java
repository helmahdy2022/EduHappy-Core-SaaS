package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.teacher.Assignment_Dates;
import model.dbHelpers.teacher.AssignmentManagement;


/**
 * Servlet implementation class List_Assignment_Dates
 */
@WebServlet("/List_Assignment_Dates")
public class List_Assignment_Dates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Assignment_Dates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int assignment_id=Integer.parseInt(request.getParameter("Assignment_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int school_id=Integer.parseInt(request.getParameter("School_id"));
		int account_id=Integer.parseInt(request.getParameter("Account_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		String page=request.getParameter("Page");
		int grade_id=Integer.parseInt(request.getParameter("Grade_id"));
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null && !request.getParameter("Lesson_id").equals("null"))
		{
			lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		}
		int category_id=0;
		if(request.getParameter("Category_id")!=null && !request.getParameter("Category_id").equals("null"))
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}		
		Assignment_Dates Assignment_Dates= new Assignment_Dates();		
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		Assignment_Dates = assignmentManagement.getAssignmentDates(assignment_id, class_id, course_id, group_id);
		
		request.setAttribute("Assignment_Dates", Assignment_Dates);
		String address = "/Teacher/Assignments/Give_Assignment.jsp?Assignment_id="+assignment_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page; 
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
 		return;	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
