package controllers.teacher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Students;
import model.beans.student.Student_Grading;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceReportCard;

/**
 * Servlet implementation class Get_Grading_Report
 */
@WebServlet("/Get_Grading_Report")
public class Get_Grading_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grading_Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}		int class_id = 0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		int course_id = 0;
		if(request.getParameter("Course_id")!=null && !request.getParameter("Course_id").equals(""))
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		int group_id = 0;
		if(request.getParameter("Group_id")!=null&&!request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}		
		ServiceReportCard serviceReportCard = new ServiceReportCard();
		Student_Grading student_Grading = new Student_Grading();
		
		StudentManagement studentManagement = new StudentManagement();
		Students students = new Students();
		students = studentManagement.getStudentsByClassCourseGroup(class_id, course_id, group_id);
		ArrayList<Integer> studensList = students.getStudent_id();	    	
		ArrayList<Student_Grading> course_Grading = new ArrayList<Student_Grading>();
		for (int student_id : studensList)
		{
			student_Grading = serviceReportCard.getReportCard(student_id, course_id);
			course_Grading.add(student_Grading);
		}
		getServletContext().setAttribute("Course_Grading", course_Grading);
		getServletContext().setAttribute("Students", students);
	    String address = "/Teacher/Assignments/Display_Grading_Report.jsp?Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id;	    
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
