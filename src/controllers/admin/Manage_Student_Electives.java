package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class Manage_Student_Electives
 */
@WebServlet("/Manage_Student_Electives")
public class Manage_Student_Electives extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Student_Electives() {
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
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id= Integer.parseInt(request.getParameter("Student_id"));
		}
		
		String RemoveFromElective = request.getParameter("RemoveFromElective");

		String message = null;
		int updateQuery = 0;
		String address=null;
		CourseManagement courseManagement = new CourseManagement();
		if (RemoveFromElective==null)
		{
			message = courseManagement.addStudentToElective(class_id, course_id, request);
			address = "/Admin/Students/Display_Manage_Student_Electives.jsp?message="+message;
		}
	    else
	    {
	    	updateQuery = courseManagement.deleteStudentFromElective(student_id, course_id);
			if(updateQuery!=0)
			{
				message = "Student removed from the Elective successfully - updateQuery="+updateQuery;
			}
			else
			{
				message = "Student was not removed from the Elective - updateQuery="+updateQuery;
			}
			address = "/Admin/Students/Display_Student_Electives.jsp";
	    }
   		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	
	}

}
