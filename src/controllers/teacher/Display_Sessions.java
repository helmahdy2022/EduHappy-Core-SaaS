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

import model.beans.teacher.Attendance_Sessions;
import model.dbHelpers.teacher.AttendanceManagement;

/**
 * Servlet implementation class Display_Sessions
 */
@WebServlet("/Display_Sessions")
public class Display_Sessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display_Sessions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String message=null;
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));	
		}
		else
		{
			grade_id = (Integer) session.getAttribute("Grade_id");
		}		
		int teacher_id= 0;
		String Session_Date= null;
		if(session.getAttribute("Teacher_Session")!=null)
		{
			teacher_id=(Integer) session.getAttribute("Teacher_Session");	  		
		}
	  	Session_Date = request.getParameter("Session_Date");
		if(Session_Date==null)
		{
		  	Session_Date = (String) session.getAttribute("Session_Date");
		}		
		Date date = null;
		try 
		{
			date = new SimpleDateFormat("yyyy-MM-dd").parse(Session_Date);
			//date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		Format formatter = new SimpleDateFormat("EEEE"); 
	    String day_Name = formatter.format(date);
	    AttendanceManagement attendanceManagement = new AttendanceManagement();
	    /////////////////////Validate Attendance Date //////////////////////////////////////////////////////////////////
	    Boolean attendance_Date_Valid = true;
	    Date today = new Date();
	    if(date.compareTo(new java.util.Date(today.getTime()))>=0)
	    {
	    	message="Date is in the future";
	    	attendance_Date_Valid = false;
	    }
	    else
	    {
	    	///////call method to validate /////////
	    	String date_Case = null;
	    	date_Case = attendanceManagement.validateDate(date, grade_id);
	    	switch(date_Case){
	    	
	    	case "NotInTerm": message="Selected Date is Not In Term"; attendance_Date_Valid = false;
	    	break;
	    	
	    	case "Holiday": message = "Selected Date is a Holiday";attendance_Date_Valid = false;
	    	break;
	    	
	    	case "Daysoff": message = "Selected Date is a Day-off";attendance_Date_Valid = false;
	    	break;
	    	
	    	case "WeekEnd": message = "Selected Date is a Week End";attendance_Date_Valid = false;
	    	break;
	    	
	    	case "DateValid": message = "DateValid";
	    	break;
	    	
	    	}
	    	
	    }
	    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	    String address=null;
	    if(attendance_Date_Valid==true)
	    {
		    
			int class_id = 0;
			if(request.getParameter("Class_id")!=null)
			{
				class_id = Integer.parseInt(request.getParameter("Class_id"));	
			}
			else
			{
				class_id = (Integer) session.getAttribute("Class_id");
			}
		    int course_id = 0;
			if(request.getParameter("Course_id")!=null)
			{
				course_id = Integer.parseInt(request.getParameter("Course_id"));	
			}
			else
			{
				course_id = (Integer) session.getAttribute("Course_id");
			}
			
			int group_id = 0;
			if(request.getParameter("Group_id")!=null&&!request.getParameter("Group_id").equals(""))
			{
				group_id = Integer.parseInt(request.getParameter("Group_id"));
			}
			else
			{
				group_id = (Integer) session.getAttribute("Group_id");
			}
			session.setAttribute("Session_Date", Session_Date);
			session.setAttribute("Class_id", class_id);
			session.setAttribute("Course_id", course_id);
			session.setAttribute("Group_id", group_id);
			session.setAttribute("Grade_id", grade_id);
			
			Attendance_Sessions attendance_Sessions = new Attendance_Sessions();
			String session_id = null;
			if(request.getParameter("Session_id")!=null)
			{
				session_id = request.getParameter("Session_id");
			}
			if(session_id!=null)
			{
				address = "/Display_Students?Session_id="+session_id;
			}
			else
			{
				attendance_Sessions = attendanceManagement.getSessionsList(teacher_id, class_id, course_id, group_id, day_Name);
				if(attendance_Sessions.getSession_Count()==1)
		    	{
		    		address = "/Display_Students?Session_id="+attendance_Sessions.getSession_id().get(0);
		    	}
		    	else
		    	{
		    		address = "/Teacher/Attendance/Display_Attendance_Sessions.jsp";
		    	}
			}						
	    	request.setAttribute("AttendanceSessions", attendance_Sessions);	    	 	
	    }
	    else
	    {
	    	address = "/Teacher/Attendance/Display_Attendance_Message.jsp?message="+message;
	    }
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
