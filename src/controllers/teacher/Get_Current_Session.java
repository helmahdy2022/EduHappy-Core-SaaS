package controllers.teacher;

import java.io.IOException;
import java.text.Format;
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

import model.beans.teacher.Current_Session;
import model.dbHelpers.teacher.AttendanceManagement;

/**
 * Servlet implementation class Get_Current_Session
 */
@WebServlet("/Get_Current_Session")
public class Get_Current_Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Current_Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int teacher_id= 0;
		teacher_id=(Integer) session.getAttribute("Teacher_Session");
		String Session_Date= null;		
		Date date = null;
		date = new Date();
		//Session_Date = new SimpleDateFormat("MM/dd/yyyy").format(date);
		Session_Date = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Format formatter = new SimpleDateFormat("EEEE"); 
	    String day_Name = formatter.format(date);
	  
	    session.setAttribute("Session_Date", Session_Date);
		
	    //int timeOffset = Integer.parseInt(request.getParameter("timeOffset"));	
	    
	    String clientHours = request.getParameter("clientHours");	
	    String clientMinutes = request.getParameter("clientHours");	
	    String sclientTime = clientHours+":"+clientMinutes;
	    
		AttendanceManagement attendanceManagement = new AttendanceManagement();
		Current_Session current_Session = new Current_Session();
		try {
			current_Session = attendanceManagement.getCurrentSession(teacher_id, day_Name, sclientTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int session_id = current_Session.getSession_id();
		session.setAttribute("Class_id", current_Session.getClass_id());
		session.setAttribute("Course_id", current_Session.getCourse_id());
		session.setAttribute("Group_id", current_Session.getGroup_id());
		session.setAttribute("Grade_id", current_Session.getGrade_id());
		String Time_From = current_Session.getTime_From();
		String Time_To = current_Session.getTime_To();
	   	//String address = "/Teacher/Attendance/Attendance.jsp?Session_id="+session_id+"&&Class_id="+current_Session.getClass_id()+"&&Grade_id="+current_Session.getGrade_id()+"&&Course_id="+current_Session.getCourse_id()+"&&Group_id="+current_Session.getGroup_id();
		String address = "/Teacher/Attendance/Attendance.jsp?Session_id="+session_id+"&&Time_From="+Time_From+"&&Time_To="+Time_To;
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
