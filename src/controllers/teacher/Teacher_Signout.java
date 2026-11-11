package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Teacher_Signout
 */
@WebServlet("/Teacher_Signout")
public class Teacher_Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_Signout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		
		if(session.getAttribute("Teacher_Session")!=null)
		{
			session.removeAttribute("Teacher_Session");
			session.removeAttribute("TeacherAuthority");
			session.removeAttribute("TeacherContent");
			session.removeAttribute("newTeacherMessagesCount");
			session.removeAttribute("Session_Date");
			session.removeAttribute("Class_id");
			session.removeAttribute("Course_id");
			session.removeAttribute("Group_id");
			session.removeAttribute("Grade_id");
			session.removeAttribute("Session_id");
	    	session.removeAttribute("Teacher_Account_Details");
		}
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Teacher/Teacher_Login.jsp");
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
