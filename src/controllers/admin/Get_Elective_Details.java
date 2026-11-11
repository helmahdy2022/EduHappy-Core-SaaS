package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Students;
import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class Get_Elective_Details
 */
@WebServlet("/Get_Elective_Details")
public class Get_Elective_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Elective_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id = 0;
		if (request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}	
		CourseManagement courseManagement = new CourseManagement();
		Students Students = new Students();
		Students = courseManagement.getElectiveStudents(course_id);
		request.setAttribute("Students", Students);
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Courses/Print_Elective.jsp";
   		}
   		else
   		{
   			address = "/Admin/Courses/Display_Elective_Details.jsp?Course_id="+course_id;
   		}  		
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
