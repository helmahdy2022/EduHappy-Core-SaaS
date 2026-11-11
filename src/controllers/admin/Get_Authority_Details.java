package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Authority_Report;
import model.dbHelpers.admin.TeacherAuthorityManagement;

/**
 * Servlet implementation class Get_Authority_Details
 */
@WebServlet("/Get_Authority_Details")
public class Get_Authority_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Authority_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		int course_id = 0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		int class_id = 0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		TeacherAuthorityManagement teacherAuthorityManagement = new TeacherAuthorityManagement();
		Authority_Report authority_Report = new Authority_Report();	
		authority_Report = teacherAuthorityManagement.getAuthorityPerGrade(grade_id, course_id, class_id,0);
		request.setAttribute("Authority_Report", authority_Report);
   		String address = "/Admin/Teachers/Display_Authority_Report.jsp"; 		
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
