package controllers.student;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.student.ServiceAssignment;


/**
 * Servlet implementation class Start_Attempt
 */
@WebServlet("/Start_Attempt")
public class Start_Attempt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start_Attempt() {
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
		HttpSession session= request.getSession();
		session.removeAttribute("student_Score");
		session.removeAttribute("score_Scale");
		int student_id=0;
		int assignment_id=0;
		int attempt_id = -1;
		int assignment_Duration = -1;
		int attempt_Duration = -1;
		int max_Grade = -1;
		student_id = (Integer) session.getAttribute("Student_Session");
		assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));
		int course_id = Integer.parseInt(request.getParameter("Course_id"));
		if(request.getParameter("Assignment_Duration")!=null && !request.getParameter("Assignment_Duration").equals(""))
		{
			assignment_Duration = Integer.parseInt(request.getParameter("Assignment_Duration"));
		}
		if(request.getParameter("max_Grade")!=null && !request.getParameter("max_Grade").equals(""))
		{
			max_Grade = Integer.parseInt(request.getParameter("max_Grade"));
		}
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		attempt_id = serviceAssignment.getAttemptInProgress(student_id, assignment_id);
		
		if(attempt_id==-1)
		{
			attempt_id = serviceAssignment.createAttempt(student_id, assignment_id, assignment_Duration);
		}
      	
		if(attempt_id!=-1)
		{
	        session.setAttribute("Attempt_id", attempt_id);  
	        session.setAttribute("Assignment_id", assignment_id);
	        session.setAttribute("max_Grade", max_Grade);
			if(serviceAssignment.getAttemptDuration(attempt_id)==0)
			{
				//session.setAttribute("student_Score", serviceAssignment.getAttemptScore(attempt_id));
				session.setAttribute("score_Scale", serviceAssignment.getAttemptScore(attempt_id)/100);
			}
			attempt_Duration=serviceAssignment.getAttemptDuration(attempt_id);
		}
		else
		{
			session.removeAttribute("Attempt_id");
			session.removeAttribute("Assignment_id");
			//session.removeAttribute("student_Score");
			session.removeAttribute("score_Scale");
		}
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		String address = "/uploadFiles/Scorm/player.jsp?Attempt_Duration="+attempt_Duration+"&&Account_id="+account_id+"&&School_id="+school_id;
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
