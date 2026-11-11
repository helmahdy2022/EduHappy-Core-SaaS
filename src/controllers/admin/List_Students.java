package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Students;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class List_Students
 */
@WebServlet("/List_Students")
public class List_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String School_id = request.getParameter("School_id");
		String Grade_id = request.getParameter("Grade_id");
		String Class_id = request.getParameter("Class_id");
		if (School_id.equals(""))
		{
			School_id = "%";
		}
		if (Grade_id.equals(""))
		{
			Grade_id = "%";
		}
		if (Class_id.equals(""))
		{
			Class_id = "%";
		}		
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
		String s_Student_id = request.getParameter("Student_id");
		if (s_Student_id == "")
		{
			s_Student_id = "%";
		}
		String s_Route_id = request.getParameter("Route_id");
		if (s_Route_id == "")
		{
			s_Route_id = "%";
		}
		Students Students = new Students();
		StudentManagement studentManagement = new StudentManagement();
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
		Students = studentManagement.getStudentsList(s_First_Name, s_Last_Name, s_Username, s_Student_id, s_Route_id, Class_id, School_id, Grade_id, admin_id);
    	request.setAttribute("Students", Students);
		String address;
		String Page = request.getParameter("Page");
		if(Page!=null && Page.equals("Message"))
		{
			address = "/Admin/Messages/Display_Students_Message.jsp";
		}
		else
		{
			address = "/Admin/Students/Display_Student.jsp?Page="+Page;
		}		 
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
