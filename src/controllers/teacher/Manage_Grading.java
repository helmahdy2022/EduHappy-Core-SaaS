package controllers.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.teacher.GradingManagement;

/**
 * Servlet implementation class Manage_Grading
 */
@WebServlet("/Manage_Grading")
public class Manage_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Grading() {
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
		PrintWriter out = response.getWriter();
		int assignment_id = 0;
		if(request.getParameter("Assignment_id")!=null)
		{
			assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));
		}
		
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		String GradeButton = request.getParameter("GradeButton");

		int assignment_Grade = 0;
		if(request.getParameter("Assignment_Grade")!=null && !request.getParameter("Assignment_Grade").equals(""))
		{
			assignment_Grade = Integer.parseInt(request.getParameter("Assignment_Grade"));
		}
		String remarks = null;
		if(request.getParameter("Remarks")!=null && !request.getParameter("Remarks").equals(""))
		{
			remarks = request.getParameter("Remarks");
		}
		int Max_Grade = Integer.parseInt(request.getParameter("Max_Grade"));
		GradingManagement gradingManagement = new GradingManagement();
		int studentGrading = -1;
		studentGrading = gradingManagement.getStudentGrading(assignment_id, student_id);
		if (GradeButton!=null && GradeButton.equals("Grade"))
		{
			if (assignment_id!=0 && assignment_Grade<=Max_Grade  && assignment_Grade>=0)
			{
				if (studentGrading == -1)
				{					
					gradingManagement.createGrading(assignment_id, student_id, assignment_Grade, remarks);		
				}
				else
				{
					//update
					gradingManagement.updateGrading(assignment_id, student_id, assignment_Grade, remarks);
				}
				out.print(assignment_Grade);
				out.print("<input name=Assignment_Grade"+student_id+" type=hidden  id=Assignment_Grade"+student_id+">");

			}
			else
			{
				out.print("Invalid Grade");
				out.print("<input name=Assignment_Grade"+student_id+" type=hidden  id=Assignment_Grade"+student_id+">");
			}
		}
		else
		{
			if (GradeButton.equals("Delete"))
			{
				//Delete
				gradingManagement.deleteGrading(assignment_id, student_id);
				out.print("<input name=Assignment_Grade"+student_id+" type=number required class=form-control min=0 id=Assignment_Grade"+student_id+">");
			}
			else
			{
				if (studentGrading!=-1)
				{
					out.print("<input name=Assignment_Grade"+student_id+" type=number required class=form-control min=0 id=Assignment_Grade"+student_id+" value="+studentGrading+">");							
				}
				else
				{
					out.print("<input name=Assignment_Grade"+student_id+" type=number required class=form-control min=0 id=Assignment_Grade"+student_id+">");
					//out.print("<input name=Remarks"+student_id+" type=number class=form-control max=255 id=Remarks"+student_id+">");
				}
			}
		}
		return;
	}

}
