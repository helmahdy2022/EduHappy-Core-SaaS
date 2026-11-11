package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Courses;
import model.dbHelpers.admin.SubjectManagement;

/**
 * Servlet implementation class Get_Courses_No_Subject
 */
@WebServlet("/Get_Courses_No_Subject")
public class Get_Courses_No_Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Courses_No_Subject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		Courses Courses = new Courses();
		SubjectManagement subjectManagement = new SubjectManagement();
		Courses = subjectManagement.doGetCoursesNoSubject(grade_id);
		request.setAttribute("Courses", Courses);
		String address;
			 
		address = "/Admin/Subjects/Display_Subject_Courses.jsp";
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
