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
import model.dbHelpers.admin.GroupManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class List_Student_Groups
 */
@WebServlet("/List_Student_Groups")
public class List_Student_Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Student_Groups() {
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

		GroupManagement groupManagement = new GroupManagement();
		ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
		groups = groupManagement.getStudentGroups(Students.getStudent_id());
		
		Students.setGroups(groups);
		request.setAttribute("Students", Students);
		
		CourseManagement courseManagement = new CourseManagement();
		Courses Courses = new Courses();
		Courses = courseManagement.getCoursesWithGroups(class_id);
		request.setAttribute("Courses", Courses);
		
	  	String address;
		 
		address = "/Admin/Students/Display_Student_Groups.jsp?Class_id="+class_id;
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
