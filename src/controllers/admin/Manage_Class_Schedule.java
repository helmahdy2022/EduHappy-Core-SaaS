package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.ScheduleManagement;
import model.dbHelpers.admin.SchoolManagement;

/**
 * Servlet implementation class Manage_Class_Schedule
 */
@WebServlet("/Manage_Class_Schedule")
public class Manage_Class_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Class_Schedule() {
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
		request.setCharacterEncoding("UTF-8");
		String message = "";
		
		int session_Count=0;
		if(request.getParameter("Session_Count")!=null)
		{
			session_Count = Integer.parseInt(request.getParameter("Session_Count"));
		}
		int days_Count=0;
		if(request.getParameter("Days_Count")!=null)
		{
			days_Count = Integer.parseInt(request.getParameter("Days_Count"));
		}
		int x;
		x =session_Count*days_Count;

		int class_id=0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		
		

		for (int i=1; i < x+1; i++)
		{		
			int day_id=0;
			if(request.getParameter("Day_id"+i)!=null)
			{
				day_id = Integer.parseInt(request.getParameter("Day_id"+i));
			}
			int session_id=0;
			if(request.getParameter("Session_id"+i)!=null)
			{
				session_id = Integer.parseInt(request.getParameter("Session_id"+i));
			}
			int teacher_id=0;
			if(request.getParameter("Teacher_id"+i)!=null)
			{
				teacher_id = Integer.parseInt(request.getParameter("Teacher_id"+i));
			}
			
			
			String CG = request.getParameter("Course"+i);

			ScheduleManagement scheduleManagement = new ScheduleManagement();
			message = scheduleManagement.createSchedule(CG, class_id, session_id, day_id, teacher_id, i, message);
		
		}
		if(message.equals(""))
		{
			message="There are no changes done on the schedule";
		}
		
		String address = null;
		////////Get Grade////////////////
		int grade_id=0;
		GradeManagement gradeManagement = new GradeManagement();
		grade_id = gradeManagement.getGradeByClass(class_id);
		///////////////////////////////
		////////Get School////////////////
		int school_id = 0;
		SchoolManagement schoolManagement = new SchoolManagement();
		school_id = schoolManagement.getSchoolByGrade(grade_id);
		///////////////////////////////

		address = "/Admin/Schedule/Display_Insert_Class_Schedule.jsp?message="+message+"&&Class_id="+class_id+"&&Grade_id="+grade_id+"&&School_id="+school_id;
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	
	}

}
