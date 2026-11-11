package controllers.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.student.ServiceAssignment;
import model.dbHelpers.teacher.GradingManagement;

/**
 * Servlet implementation class SubmitQuizScore
 */
@WebServlet("/SubmitQuizScore")
public class SubmitQuizScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuizScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int assignment_Grade = -1;
		//int max_Score = -1;
		int student_id = 0;
		int assignment_id = 0;
		int attempt_id = 0;
		int updateQuery = 0;
		String message = null;
		int studentGrading = -1;
		int attempt_Duration = -1;
		int max_Grade = (Integer) session.getAttribute("max_Grade");
		float score_Scale = Float.parseFloat(request.getParameter("score_Scale"));
		assignment_Grade = max_Grade* Math.round(score_Scale*100)/100  ;//Integer.parseInt(request.getParameter("student_Score"));
		student_id = (Integer) session.getAttribute("Student_Session");
		assignment_id = (Integer) session.getAttribute("Assignment_id"); 
		attempt_id = (Integer) session.getAttribute("Attempt_id");
		if(request.getParameter("attempt_Duration")!=null)
		{
			attempt_Duration = Integer.parseInt(request.getParameter("attempt_Duration"));
		}
		GradingManagement gradingManagement = new GradingManagement();
		studentGrading = gradingManagement.getStudentGrading(assignment_id, student_id);
		if(studentGrading==-1)
		{
			updateQuery = gradingManagement.createGrading(assignment_id, student_id, assignment_Grade, null);
			if(updateQuery!=0)
			{
				message="Quiz score has been successfully entered";
			}
			else
			{
				message="Quiz score not entered";
			}
		}
		else
		{
			if(assignment_Grade > studentGrading)
			{
				updateQuery = gradingManagement.updateGrading(assignment_id, student_id, assignment_Grade, null);
				if(updateQuery!=0)
				{
					message="Quiz score has been successfully updated";
				}
				else
				{
					message="Quiz score not updated";
				}
			}
			else
			{
				message = "You have already scored higher than or equal to this score in previous attempts. Score not saved";
				updateQuery=1;
			}
		}
		if(updateQuery!=0)
		{
			ServiceAssignment serviceAssignment = new ServiceAssignment();
			//serviceAssignment.updateAttempt(attempt_id, assignment_Grade, attempt_Duration);
			serviceAssignment.updateAttempt(attempt_id, Math.round(score_Scale*100), attempt_Duration);
			session.removeAttribute("Attempt_id");
		}
		session.setAttribute("message", message);	
 		StringBuffer url =  request.getRequestURL();
/*   	  	if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
		{
   	  		url.replace(0, 4, "https");
		}   */	   	  		
   	  	String uri = request.getRequestURI();
   	  	String ctx = request.getContextPath();
   	  	String base = url.substring(0, url.length() - uri.length() + ctx.length());
   	   	String address = base+"/Student/Assignments/Initiate_Assignments.jsp"; 	  
		response.sendRedirect(address);		
	}

}
