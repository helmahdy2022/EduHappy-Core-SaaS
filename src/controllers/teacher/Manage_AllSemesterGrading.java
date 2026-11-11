package controllers.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.teacher.Student_List;
import model.dbHelpers.teacher.GradingManagement;

/**
 * Servlet implementation class Manage_AllSemesterGrading
 */
@WebServlet("/Manage_AllSemesterGrading")
public class Manage_AllSemesterGrading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_AllSemesterGrading() {
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
		
		int semester_Exam_id = 0;
		if(request.getParameter("Semester_Exam_id")!=null)
		{
			semester_Exam_id = Integer.parseInt(request.getParameter("Semester_Exam_id"));
		}
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		String selectedRow = request.getParameter("selectedRow");
		Student_List Student_List = new Student_List();
		GradingManagement gradingManagement = new GradingManagement();
		
		Student_List = gradingManagement.getSemesterGrading(semester_Exam_id, class_id, course_id, group_id);
		ArrayList<ArrayList<String>> StudentList = new ArrayList<ArrayList<String>>();
		StudentList = Student_List.getStudentList();
		int updateQuery = 0;
		String message = null;
		
		Iterator<ArrayList<String>> it = StudentList.iterator();
		int student_id=0;
		while (it.hasNext()) 
		{
			
			ArrayList<String> strList = it.next();
			Iterator<String> itString = strList.iterator();
			
			while(itString.hasNext())
			{
				student_id = Integer.parseInt(itString.next());
				break;
			}
			int student_Grade = -1;
			if(request.getParameter("Student_Grade"+student_id)!=null && !request.getParameter("Student_Grade"+student_id).equals(""))
			{
				student_Grade = Integer.parseInt(request.getParameter("Student_Grade"+student_id));
			}
			int semester_Exam_Grade = Integer.parseInt(request.getParameter("Semester_Exam_Grade"));
			if(request.getParameter("submit").equals("Grade All"))
			{
				if (semester_Exam_id!=0 && student_Grade<=semester_Exam_Grade  && student_Grade>=0)
				{
					int studentGrading = -1;
					studentGrading = gradingManagement.getStudentSemesterGrading(semester_Exam_id, student_id);

					if (studentGrading == -1)
					{
						updateQuery = gradingManagement.createSemesterGrading(semester_Exam_id, student_id, student_Grade);	
						if (updateQuery!=0)
						{
							message = "Entered Successfully<br>"
									+ "<a href='javascript:gradeSemester("+semester_Exam_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\",\""+selectedRow+"\")'><font color='blue'>Back</font></a>";
						}
						else
						{
							message = "Failed";
						}		
					}
					else
					{
						//update
						if(student_Grade!=-1)
						{
							updateQuery = gradingManagement.updateSemesterGrading(semester_Exam_id, student_id, student_Grade);
							message = "Updated Successfully<br>"
								+ "<a href='javascript:gradeSemester("+semester_Exam_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\",\""+selectedRow+"\")'><font color='blue'>Back</font></a>";
						}
					}

				}
				else
				{
					message = "There are no changes on the grading<br>"
							+ "<a href='javascript:gradeSemester("+semester_Exam_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\",\""+selectedRow+"\")'><font color='blue'>Back</font></a>";

				}
			}
			else
			{
				//Delete
				updateQuery = gradingManagement.deleteSemesterGrading(semester_Exam_id, student_id);
				message = "Deleted Successfully<br>"
						+ "<a href='javascript:gradeSemester("+semester_Exam_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\",\""+selectedRow+"\")'><font color='blue'>Back</font></a>";
			}
		}
		String address = "/Teacher/Assignments/Display_Manage_AllSemesterGrading.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);

	
	}

}
