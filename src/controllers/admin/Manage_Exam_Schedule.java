package controllers.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Courses;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class Manage_Exam_Schedule
 */
@WebServlet("/Manage_Exam_Schedule")
public class Manage_Exam_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Exam_Schedule() {
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
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}		
		String exam_Name = request.getParameter("Exam_Name");
		int displayHideExamSchedule = 0;
		if(request.getParameter("DisplayHideExamSchedule")!=null)
		{
			displayHideExamSchedule = 1;
		}
		CourseManagement courseManagement = new CourseManagement();
		Courses Courses = new Courses();
		Courses = courseManagement.getCourseList(grade_id);
		ArrayList<ArrayList<String>> ExamScheduleDetails = new ArrayList<ArrayList<String>>();
		
		if(Courses!=null)
		{
			int i=0;
			
			for(int courseID : Courses.getCourse_id())
			{
				if(
						request.getParameter("Time_From"+courseID)!=null
						&&!request.getParameter("Time_From"+courseID).equals("")
						&&request.getParameter("Time_To"+courseID)!=null
						&&!request.getParameter("Time_To"+courseID).equals("")
						&&request.getParameter("Exam_Date"+courseID)!=null
						&&!request.getParameter("Exam_Date"+courseID).equals("")
				  )
				{
					DateFormat sdf = new SimpleDateFormat("HH:mm");
					Date dTime_From = null;
					Date dTime_To  = null;
					try {
						dTime_From = sdf.parse(request.getParameter("Time_From"+courseID));
						dTime_To = sdf.parse(request.getParameter("Time_To"+courseID));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					if(dTime_From.compareTo(dTime_To)<0)
					{
						int course_id = Integer.parseInt(request.getParameter("Course"+courseID));
						String sExam_Date = request.getParameter("Exam_Date"+courseID);
						String sTime_From = request.getParameter("Time_From"+courseID);
						String sTime_To = request.getParameter("Time_To"+courseID);		
						ExamScheduleDetails.add(new ArrayList<String>());
						ExamScheduleDetails.get(i).add(0,null);
						ExamScheduleDetails.get(i).set(0, String.valueOf(grade_id));
						ExamScheduleDetails.get(i).add(1,null);
						ExamScheduleDetails.get(i).set(1, exam_Name);
						ExamScheduleDetails.get(i).add(2,null);
						ExamScheduleDetails.get(i).set(2, String.valueOf(course_id));
						ExamScheduleDetails.get(i).add(3,null);
						ExamScheduleDetails.get(i).set(3, sExam_Date);
						ExamScheduleDetails.get(i).add(4,null);
						ExamScheduleDetails.get(i).set(4, sTime_From);
						ExamScheduleDetails.get(i).add(5,null);
						ExamScheduleDetails.get(i).set(5, sTime_To);
						ExamScheduleDetails.get(i).add(6,null);
						ExamScheduleDetails.get(i).set(6, String.valueOf(displayHideExamSchedule));
						i=i+1;
					}

				}
			}
			GradeManagement gradeManagement = new GradeManagement();
			try {
				String message = gradeManagement.manageExamSchedule(ExamScheduleDetails, grade_id);
				String address = "/Admin/Grades/Display_Manage_Exam_Schedule.jsp?message="+message;		
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
