package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.student.ServiceAssignment;

/**
 * Servlet implementation class Get_Submission_Notes
 */
@WebServlet("/Get_Submission_Notes")
public class Get_Submission_Notes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Submission_Notes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int student_id = 0;
		int give_Assignment_id = 0;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		if(request.getParameter("Give_Assignment_id")!=null && !request.getParameter("Give_Assignment_id").equals(""))
		{
			give_Assignment_id = Integer.parseInt(request.getParameter("Give_Assignment_id"));
		}
        String submissionNotes = null;
        ServiceAssignment serviceAssignment = new ServiceAssignment();
        submissionNotes = serviceAssignment.getSubmissionNotes(student_id, give_Assignment_id);	
        request.setAttribute("Submission_Notes", submissionNotes);
		String address = "/Teacher/Assignments/Display_Submission_Notes.jsp"; 
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
