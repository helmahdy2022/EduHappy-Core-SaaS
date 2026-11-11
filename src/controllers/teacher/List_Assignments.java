package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.teacher.Assignments;
import model.dbHelpers.teacher.AssignmentManagement;

/**
 * Servlet implementation class List_Assignments
 */
@WebServlet("/List_Assignments")
public class List_Assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Assignments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int teacher_id= 0;
		if(session.getAttribute("Teacher_Session")!=null)
		{
			teacher_id=(Integer) session.getAttribute("Teacher_Session");
		}
		String page=request.getParameter("Page");
		int grade_id=Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=0;
		if(request.getParameter("Class_id")!=null && !request.getParameter("Class_id").equals(""))
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		
		int course_id= Integer.parseInt(request.getParameter("Course_id"));
		String group_id=request.getParameter("Group_id");
		String lesson_id = null;
		if(!request.getParameter("Lesson_id").equals(""))
		{
			lesson_id = request.getParameter("Lesson_id");
		}
		String category_id = null;
		if(!request.getParameter("Category_id").equals(""))
		{
			category_id = request.getParameter("Category_id");
		}
		String lesson_idSQL=null;
		if (lesson_id == null || lesson_id == "" || lesson_id.equals(""))
		{
			
			lesson_idSQL = "";
		}
		else
		{
			lesson_idSQL = "and Lesson_id =";
		}
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		Assignments Assignments = new Assignments();
		Assignments = assignmentManagement.getAssignmentsList(teacher_id, course_id, category_id, lesson_id, lesson_idSQL, page);
		getServletContext().setAttribute("Assignments_List", Assignments);
		String address = "/Teacher/Assignments/Display_Assignments_List.jsp?Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page; 
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
