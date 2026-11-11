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
 * Servlet implementation class List_Graduated_Students
 */
@WebServlet("/List_Graduated_Students")
public class List_Graduated_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Graduated_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String School_id = request.getParameter("School_id");
		if (School_id.equals(""))
		{
			School_id = "%";
		}
		String school_Year_id = request.getParameter("School_Year_id");
		if (school_Year_id.equals(""))
		{
			school_Year_id = "%";
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
		Students Students = new Students();
		StudentManagement studentManagement = new StudentManagement();
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
		Students = studentManagement.getGraduatedStudentsList(s_First_Name, s_Last_Name, s_Username, s_Student_id, school_Year_id, School_id, admin_id);
    	request.setAttribute("Students", Students);
		String address;
		String Page = request.getParameter("Page");
		if(Page!=null && Page.equals("Message"))
		{
			address = "/Admin/Messages/Display_Students_Message.jsp";
		}
		else
		{
			address = "/Admin/Students/Display_Graduated_Student.jsp?Page="+Page;
		}		 
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
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
