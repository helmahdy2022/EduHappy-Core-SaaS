package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Course_Details;
import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class Get_Course_Details
 */
@WebServlet("/Get_Course_Details")
public class Get_Course_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Course_Details() {
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
/*		int grade_id = 0;
		if (request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}*/
		CourseManagement courseManagement = new CourseManagement();
		Course_Details Course_Details = new Course_Details();
		Course_Details = courseManagement.getCourse(course_id);
    	request.setAttribute("Course_Details", Course_Details);
    	
/*		SubjectManagement subjectManagement = new SubjectManagement();
		Subjects Subjects = new Subjects();
		Subjects = subjectManagement.getSubjectList(grade_id);
		request.setAttribute("Subjects", Subjects);*/
    	
   		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Courses/Print_Course.jsp";
   		}
   		else
   		{
   			address = "/Admin/Courses/Display_Course_Details.jsp?Course_id="+course_id;
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
