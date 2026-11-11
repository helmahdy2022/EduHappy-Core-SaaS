package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.teacher.Teacher_Students;
import model.dbHelpers.admin.MessagesManagement;

/**
 * Servlet implementation class Get_Teacher_Students
 */
@WebServlet("/Get_Teacher_Students")
public class Get_Teacher_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		int class_id = Integer.parseInt(request.getParameter("Class_id"));
		int course_id = Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}		
		MessagesManagement messagesManagement = new MessagesManagement();
		Teacher_Students Teacher_Students = new Teacher_Students();
		Teacher_Students = messagesManagement.getTeacherStudents(class_id, course_id, group_id);
		
    	request.setAttribute("Teacher_Students", Teacher_Students);	
    	String address = "/Teacher/Messages/Display_Teacher_Students.jsp?Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id;
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
