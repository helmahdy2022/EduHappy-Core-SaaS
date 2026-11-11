package controllers.teacher;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.teacher.Attendance_Sessions;
import model.beans.teacher.Attendance_Students;
import model.dbHelpers.teacher.AttendanceManagement;

/**
 * Servlet implementation class Display_Students
 */
@WebServlet("/Display_Students")
public class Display_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session= request.getSession();
		int session_id = 0;
		if(request.getParameter("Session_id")!=null)
		{
			session_id = Integer.parseInt(request.getParameter("Session_id"));
		}
		
		session.setAttribute("Session_id", session_id);
		int class_id = (Integer) session.getAttribute("Class_id");
		int course_id = (Integer) session.getAttribute("Course_id");
		int group_id = (Integer) session.getAttribute("Group_id");
		String session_Date=(String) session.getAttribute("Session_Date");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dSession_Date=null;
		String strSession_Date = null;
		try {
			dSession_Date = formater.parse(session_Date);			
			strSession_Date = formater.format(dSession_Date);
			Date d = formater.parse(strSession_Date);
			formater.applyPattern("EEE, MMM dd, yyyy");
			strSession_Date = formater.format(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		AttendanceManagement attendanceManagement = new AttendanceManagement();
		Attendance_Students attendance_Students = new Attendance_Students();
		attendance_Students = attendanceManagement.getAttendanceStudents(class_id, course_id, group_id, session_id, dSession_Date);
		
    	request.setAttribute("AttendanceStudents", attendance_Students);	
    	Attendance_Sessions attendanceSessions = new Attendance_Sessions();
    	attendanceSessions = (Attendance_Sessions) request.getAttribute("AttendanceSessions");
    	String time_From=null;
    	String time_To=null;
    	if(attendanceSessions!=null&&attendanceSessions.getSession_Count()==1)
    	{
        	time_From = attendanceSessions.getTime_From().get(0);
        	time_To = attendanceSessions.getTime_To().get(0);
    	}
    	else
    	{
    		time_From = request.getParameter("Time_From");
    		time_To = request.getParameter("Time_To");
    	}
    	String address = "/Teacher/Attendance/Display_Attendance_Students.jsp?Session_Date="+strSession_Date+"&&Time_From="+time_From+"&&Time_To="+time_To;
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
