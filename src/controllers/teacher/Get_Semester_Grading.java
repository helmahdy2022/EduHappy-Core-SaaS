package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.teacher.Student_List;
import model.dbHelpers.teacher.GradingManagement;

/**
 * Servlet implementation class Get_Semester_Grading
 */
@WebServlet("/Get_Semester_Grading")
public class Get_Semester_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Semester_Grading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int semester_Exam_id=Integer.parseInt(request.getParameter("Semester_Exam_id"));
		int grade_id=Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		
		Student_List Student_List = new Student_List();
		GradingManagement gradingManagement = new GradingManagement();
		Student_List = gradingManagement.getSemesterGrading(semester_Exam_id, class_id, course_id, group_id);
		request.setAttribute("Students", Student_List);	


		String address = "/Teacher/Assignments/Grade_Semester_Exam.jsp?Semester_Exam_id="+semester_Exam_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id; 
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
 		return;	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
