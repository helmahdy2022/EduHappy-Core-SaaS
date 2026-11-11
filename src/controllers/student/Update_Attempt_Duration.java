package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.student.ServiceAssignment;

/**
 * Servlet implementation class Update_Attempt_Duration
 */
@WebServlet("/Update_Attempt_Duration")
public class Update_Attempt_Duration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_Attempt_Duration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int attempt_Duration = -1;
		int attempt_id = 0;
		int student_Score = -1;
		float score_Scale = -1;
		int max_Score =-1;
		
		if(request.getParameter("attempt_Duration")!=null && !request.getParameter("attempt_Duration").equals("-1"))
		{
			attempt_Duration = Integer.parseInt(request.getParameter("attempt_Duration"));
		}
		if(request.getParameter("student_Score")!=null&&!request.getParameter("student_Score").equals(""))
		{
			student_Score =(int) Float.parseFloat(request.getParameter("student_Score"));
		}
		if(session.getAttribute("student_Score")!=null)
		{
			student_Score = (Integer) session.getAttribute("student_Score");
		}
		
		if(request.getParameter("score_Scale")!=null)
		{
			score_Scale = Float.parseFloat(request.getParameter("score_Scale"));
		}
		if(session.getAttribute("score_Scale")!=null)
		{
			score_Scale = (Float) session.getAttribute("score_Scale");
		}
		
		if(request.getParameter("max_Score")!=null)
		{
			max_Score = Integer.parseInt(request.getParameter("max_Score"));
		}
		attempt_id = (Integer) session.getAttribute("Attempt_id");
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		serviceAssignment.updateAttemptDuration(attempt_id, attempt_Duration, Math.round(score_Scale*100));
		if(request.getParameter("unload")!=null && request.getParameter("unload").equals("0"))
		{
			int rounded_Score = Math.round(score_Scale*100);
			String address = "/Student/Assignments/Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score+"&&score_Scale="+score_Scale+"&&rounded_Score="+rounded_Score;
			RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

}
