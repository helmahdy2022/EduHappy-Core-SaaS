package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Teachers;
import model.dbHelpers.admin.TeacherManagement;


/**
 * Servlet implementation class List_Teachers
 */
@WebServlet("/List_Teachers")
public class List_Teachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Teachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
/*		String School_id = request.getParameter("School_id");
		String Grade_id = request.getParameter("Grade_id");
		String Class_id = request.getParameter("Class_id");
		String Course_id = request.getParameter("Course_id");
		String s_First_Name = request.getParameter("First_Name");
		if (s_First_Name == "")
		{
			s_First_Name = "%";
		}
		String s_Last_Name = request.getParameter("Last_Name");
		if (s_Last_Name == "")
		{
			s_Last_Name = "%";
		}
		String s_Username = request.getParameter("Username");
		if (s_Username == "")
		{
			s_Username = "%";
		}
		String s_Teacher_id = request.getParameter("Teacher_id");
		if (s_Teacher_id == "")
		{
			s_Teacher_id = "%";
		}
		Teachers = teacherManagement.getTeachersList(s_First_Name, s_Last_Name, s_Username, s_Teacher_id,Class_id, School_id, Grade_id, Course_id, admin_id);*/

		Teachers Teachers = new Teachers();
		TeacherManagement teacherManagement = new TeacherManagement();
		Teachers = teacherManagement.getTeachersList(admin_id);
		request.setAttribute("Teachers", Teachers);

		String address;
		String Page = request.getParameter("Page"); 
		address = "/Admin/Teachers/Display_Teacher.jsp?Page="+Page;
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
