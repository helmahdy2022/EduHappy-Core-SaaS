package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Courses;
import model.beans.admin.Students;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class List_Student_Electives
 */
@WebServlet("/List_Student_Electives")
public class List_Student_Electives extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Student_Electives() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int class_id = 0;
		if(request.getParameter("Class")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class"));
		}
		StudentManagement studentManagement = new StudentManagement();
		Students Students = new Students();
		Students = studentManagement.getStudentByClass(class_id);
		CourseManagement courseManagement = new CourseManagement();
		ArrayList<ArrayList<String>> electives = new ArrayList<ArrayList<String>>();
		electives = courseManagement.getStudentElectives(Students.getStudent_id());
		
		Students.setElectives(electives);
		request.setAttribute("Students", Students);
		

		Courses Courses = new Courses();
		Courses = courseManagement.getElectiveCourses(class_id);
		request.setAttribute("Courses", Courses);
		
	  	String address;
		 
		address = "/Admin/Students/Display_Student_Electives.jsp?Class_id="+class_id;
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
