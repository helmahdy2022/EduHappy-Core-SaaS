package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Teacher_School_Grade;
import model.dbHelpers.admin.TeacherAuthorityManagement;


/**
 * Servlet implementation class Get_Assign_Teacher
 */
@WebServlet("/Get_Teacher_Authority")
public class Get_Teacher_Authority extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Authority() {
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
		int teacher_id = 0;
		if(request.getParameter("Teacher_id")!=null)
		{
			teacher_id = Integer.parseInt(request.getParameter("Teacher_id"));
		}
		
		TeacherAuthorityManagement teacherAuthorityManagement = new TeacherAuthorityManagement();
		Teacher_School_Grade teacher_School_Grade = new Teacher_School_Grade();
		teacher_School_Grade = teacherAuthorityManagement.getTeacherAuthority(teacher_id, admin_id);
    	
		request.setAttribute("Teacher_School_Grade", teacher_School_Grade);
  		String Page = request.getParameter("Page");
  		String address=null;
  		if (Page.equals("Assign_Grades"))
  		{
  			address = "/Admin/Teachers/Display_Teacher_Assign_Grades.jsp?Teacher_id="+teacher_id;
  		}
  		if (Page.equals("Assign_Classes"))
  		{
  			address = "/Admin/Teachers/Display_Teacher_Assign_Classes.jsp?Teacher_id="+teacher_id;
  		}   
  		if (Page.equals("Assign_Courses"))
  		{
  			address = "/Admin/Teachers/Display_Teacher_Assign_Courses.jsp?Teacher_id="+teacher_id;
  		}  
  		if (Page.equals("Assign_Content"))
  		{
  			address = "/Admin/Teachers/Display_Teacher_Assign_Content.jsp?Teacher_id="+teacher_id;
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
