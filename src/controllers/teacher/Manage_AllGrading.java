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
 * Servlet implementation class Manage_AllGrading
 */
@WebServlet("/Manage_AllGrading")
public class Manage_AllGrading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_AllGrading() {
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
		
		
		int assignment_id = 0;
		if(request.getParameter("Assignment_id")!=null)
		{
			assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));
		}
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null)
		{
			lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		}
		int category_id = 0;
		if(request.getParameter("Category_id")!=null)
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		String page = request.getParameter("Page");
		
		Student_List Student_List = new Student_List();
		GradingManagement gradingManagement = new GradingManagement();
		
		Student_List = gradingManagement.getGrading(assignment_id, class_id, course_id, group_id);
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
			int assignment_Grade = -1;
			if(request.getParameter("Assignment_Grade"+student_id)!=null && !request.getParameter("Assignment_Grade"+student_id).equals(""))
			{
				assignment_Grade = Integer.parseInt(request.getParameter("Assignment_Grade"+student_id));
			}
			String remarks = null;
			if(request.getParameter("Remarks"+student_id)!=null && !request.getParameter("Remarks"+student_id).equals(""))
			{
				remarks = request.getParameter("Remarks"+student_id);
			}
			int Max_Grade = Integer.parseInt(request.getParameter("Max_Grade"));
			if(request.getParameter("submit").equals("Grade All"))
			{
				if ((assignment_id!=0 && assignment_Grade<=Max_Grade  && assignment_Grade>=0)||(assignment_Grade==-1&&remarks!=null))
				{
					int studentGrading = -1;
					studentGrading = gradingManagement.getStudentGrading(assignment_id, student_id);

					if (studentGrading == -1)
					{
						updateQuery = gradingManagement.createGrading(assignment_id, student_id, assignment_Grade, remarks);	
						if (updateQuery!=0)
						{
							message = "Entered Successfully<br>"
									+ "<a href='javascript:gradeAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+",\""+page+"\")'><font color='blue'>Back</font></a>";
						}
						else
						{
							message = "Failed";
						}		
					}
					else
					{
						//update
						if(assignment_Grade!=-1 || remarks!=null)
						{
							updateQuery = gradingManagement.updateGrading(assignment_id, student_id, assignment_Grade, remarks);
							message = "Updated Successfully<br>"
								+ "<a href='javascript:gradeAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+",\""+page+"\")'><font color='blue'>Back</font></a>";
						}
					}

				}
				else
				{
					message = "There are no changes on the grading<br>"
							+ "<a href='javascript:gradeAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+",\""+page+"\")'><font color='blue'>Back</font></a>";

				}
			}
			else
			{
				//Delete
				updateQuery = gradingManagement.deleteGrading(assignment_id, student_id);
				message = "Deleted Successfully<br>"
						+ "<a href='javascript:gradeAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+",\""+page+"\")'><font color='blue'>Back</font></a>";
			}
		}
		String address = "/Teacher/Assignments/Display_Insert_Assignment.jsp?message="+message+"&&Page=Grade";
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);

	}

}
