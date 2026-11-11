package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Student_Signout
 */
@WebServlet("/Student_Signout")
public class Student_Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Signout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		
		if(session.getAttribute("Student_Session")!=null)
		{
			session.removeAttribute("Student_Session");
			session.removeAttribute("newStudentMessagesCount");
			session.removeAttribute("student_Score");
			session.removeAttribute("score_Scale");
	        session.removeAttribute("Attempt_id");  
	        session.removeAttribute("Assignment_id");
	        session.removeAttribute("max_Grade");
	        session.removeAttribute("score_Scale");
	        session.removeAttribute("message");
	    	session.removeAttribute("Student_Account_Details");
		}
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Student/Student_Login.jsp");
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
