package controllers.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.teacher.AttendanceManagement;

/**
 * Servlet implementation class Manage_Attendance
 */
@WebServlet("/Manage_Attendance")
public class Manage_Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Attendance() {
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		PrintWriter out = response.getWriter();
		String student_id = request.getParameter("Student_id");
		String status = request.getParameter("Status");
		String function = request.getParameter("Function");
		out.print("Student_id="+student_id+"<br>");
		out.print("Status="+status+"<br>");
		
		int teacher_id=(Integer) session.getAttribute("Teacher_Session");
		int class_id=(Integer) session.getAttribute("Class_id");
		int course_id=(Integer) session.getAttribute("Course_id");
		int group_id=(Integer) session.getAttribute("Group_id");
		int session_id=(Integer) session.getAttribute("Session_id");
		String Session_Date=(String) session.getAttribute("Session_Date");

		out.print("Session_Date="+Session_Date+"<br>");
		out.print("Session_id="+session_id+"<br>");
		out.print("Class_id="+class_id+"<br>");
		out.print("Course_id="+course_id+"<br>");
		out.print("Teacher_id="+teacher_id+"<br>");
		
		//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dSession_Date=null;
		try 
		{
			dSession_Date = formater.parse(Session_Date);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		AttendanceManagement attendanceManagement = new AttendanceManagement();
		attendanceManagement.manageAttendance(student_id, class_id, course_id, group_id, session_id, teacher_id, dSession_Date, status, function);
	
	}

}
