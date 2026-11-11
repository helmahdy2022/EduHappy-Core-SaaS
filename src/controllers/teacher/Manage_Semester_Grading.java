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
 * Servlet implementation class Manage_Semester_Grading
 */
@WebServlet("/Manage_Semester_Grading")
public class Manage_Semester_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Semester_Grading() {
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
		int semester_Exam_id = 0;
		if(request.getParameter("Semester_Exam_id")!=null)
		{
			semester_Exam_id = Integer.parseInt(request.getParameter("Semester_Exam_id"));
		}
		
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		String GradeButton = request.getParameter("GradeButton");

		int student_Grade = 0;
		if(request.getParameter("Student_Grade")!=null && !request.getParameter("Student_Grade").equals(""))
		{
			student_Grade = Integer.parseInt(request.getParameter("Student_Grade"));
		}
		int semester_Exam_Grade = Integer.parseInt(request.getParameter("Semester_Exam_Grade"));
		GradingManagement gradingManagement = new GradingManagement();
		int studentGrading = -1;
		studentGrading = gradingManagement.getStudentSemesterGrading(semester_Exam_id, student_id);
		if (GradeButton!=null && GradeButton.equals("Grade"))
		{
			if (semester_Exam_id!=0 && student_Grade<=semester_Exam_Grade  && student_Grade>=0)
			{
				if (studentGrading == -1)
				{					
					gradingManagement.createSemesterGrading(semester_Exam_id, student_id, student_Grade);		
				}
				else
				{
					//update
					gradingManagement.updateSemesterGrading(semester_Exam_id, student_id, student_Grade);
				}
				out.print(student_Grade);
				out.print("<input name=Student_Grade"+student_id+" type=hidden  id=Student_Grade"+student_id+">");

			}
			else
			{
				out.print("Invalid Grade");
				out.print("<input name=Student_Grade"+student_id+" type=hidden  id=Student_Grade"+student_id+">");
			}
		}
		else
		{
			if (GradeButton.equals("Delete"))
			{
				//Delete
				gradingManagement.deleteSemesterGrading(semester_Exam_id, student_id);
				out.print("<input name=Student_Grade"+student_id+" type=number class=form-control min=0 id=Student_Grade"+student_id+">");
			}
			else
			{
				if (studentGrading!=-1)
				{
					out.print("<input name=Student_Grade"+student_id+" type=number class=form-control min=0 id=Student_Grade"+student_id+" value="+studentGrading+">");				
				}
				else
				{
					out.print("<input name=Student_Grade"+student_id+" type=number class=form-control min=0 id=Student_Grade"+student_id+">");
				}
			}


		}
		return;
	}

}
