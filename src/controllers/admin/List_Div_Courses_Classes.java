package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Classes;
import model.beans.admin.Courses;
import model.dbHelpers.admin.ClassManagement;
import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class List_Div_Courses_Classes
 */
@WebServlet("/List_Div_Courses_Classes")
public class List_Div_Courses_Classes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Div_Courses_Classes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		CourseManagement courseManagement = new CourseManagement();
		Courses Courses = new Courses();
		Courses = courseManagement.getCourseList(grade_id);
		request.setAttribute("Courses", Courses);
		
		ClassManagement classManagement = new ClassManagement();
		Classes Classes = new Classes();
		Classes = classManagement.getClassesNoDivision(grade_id);
		request.setAttribute("Classes", Classes);
		
		String address;
		address = "/Admin/Divisions/Display_Division_Courses_Classes.jsp";
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
