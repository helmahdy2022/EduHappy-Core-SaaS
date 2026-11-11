package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Courses;
import model.beans.admin.Subject_Details;
import model.dbHelpers.admin.SubjectManagement;

/**
 * Servlet implementation class Get_Subject_Details
 */
@WebServlet("/Get_Subject_Details")
public class Get_Subject_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Subject_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int subject_id = Integer.parseInt(request.getParameter("Subject_id")) ;
		SubjectManagement subjectManagement = new SubjectManagement();
		Subject_Details Subject_Details = new Subject_Details();
		Subject_Details = subjectManagement.getSubject(subject_id);
		request.setAttribute("Subject_Details", Subject_Details);
		Courses Courses = new Courses();
		Courses = subjectManagement.doGetSubjectCourses(Subject_Details.getGrade_id(), subject_id);
		request.setAttribute("Courses", Courses);
		
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Subjects/Print_Subject.jsp";
   		}
   		else
   		{
   			address = "/Admin/Subjects/Display_Subject_Details.jsp?Subject_id="+subject_id;
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
