package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.GroupManagement;

/**
 * Servlet implementation class Manage_Student_Groups
 */
@WebServlet("/Manage_Student_Groups")
public class Manage_Student_Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Student_Groups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int class_id = 0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		int course_id=0;
		if(request.getParameter("Course")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course"));
		}
		int group_id = 0;
		if(request.getParameter("Group_id")!=null)
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id= Integer.parseInt(request.getParameter("Student_id"));
		}
		
		String RemoveFromGroup = request.getParameter("RemoveFromGroup");

		String message = null;
		int updateQuery = 0;
		String address=null;
		GroupManagement groupManagement = new GroupManagement();
		if (RemoveFromGroup==null)
		{
			message = groupManagement.addStudentToGroup(class_id, course_id, group_id, request);
/*			if(updateQuery!=0)
			{
				message = "Student(s) assigned to the Group Successfully - updateQuery="+updateQuery;
			}
			else
			{
				message = "Student(s) not assigned to the Group - updateQuery="+updateQuery;
			}*/
			address = "/Admin/Students/Display_Manage_Student_Groups.jsp?message="+message;
		}
	    else
	    {
	    	updateQuery = groupManagement.deleteStudentFromGroup(student_id, group_id);
			if(updateQuery!=0)
			{
				message = "Student removed from the Group successfully - updateQuery="+updateQuery;
			}
			else
			{
				message = "Student was not removed from the Group - updateQuery="+updateQuery;
			}
			address = "/Admin/Students/Display_Student_Groups.jsp";
	    }
   		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	

	}
}
