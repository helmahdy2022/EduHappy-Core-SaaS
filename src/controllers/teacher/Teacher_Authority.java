package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.teacher.Authority_Container;
import model.beans.teacher.Content_Container;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.teacher.TeacherAuthority;

/**
 * Servlet implementation class Teacher_Authority
 */
@WebServlet("/Teacher_Authority")
public class Teacher_Authority extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_Authority() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int teacher_id = 0;
		HttpSession session= request.getSession();
		teacher_id = (Integer) session.getAttribute("Teacher_Session");	
		TeacherAuthority teacherAuthority = new TeacherAuthority();
		
		Authority_Container Authority_Container = new Authority_Container();
		Authority_Container = teacherAuthority.getTeacherAuthority(teacher_id);
		session.setAttribute("TeacherAuthority", Authority_Container);
    	
		Content_Container Content_Container = new Content_Container();
		Content_Container = teacherAuthority.getTeacherContent(teacher_id);
		session.setAttribute("TeacherContent", Content_Container);
		MessagesManagement messagesManagement = new MessagesManagement();
		int newMessagesCount = messagesManagement.getNewMessagesCount(teacher_id, "Teacher");
		session.setAttribute("newTeacherMessagesCount", newMessagesCount);
		String url = request.getParameter("url");
		String address;
		if(url!=null && !url.equals("null") && !url.equals("") &&!url.equals("/Teacher/Teacher_Login.jsp") &&!url.equals("/Teacher/Teacher_Signout.jsp"))
	    {
	    	address =  url;
	    }
	    else
	    {
	    	address = "/Teacher/Teacher_Main.jsp";
	    }
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
 		return;	 
 	}
}

