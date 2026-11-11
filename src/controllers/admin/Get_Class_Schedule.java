package controllers.admin;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Class_Schedule;
import model.dbHelpers.admin.ScheduleManagement;


/**
 * Servlet implementation class Get_Class_Schedule
 */
@WebServlet("/Get_Class_Schedule")
public class Get_Class_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Class_Schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
	    String clientHours = request.getParameter("clientHours");	
	    String clientMinutes = request.getParameter("clientMinutes");
	    String sclientDay = request.getParameter("clientDay");	
	    String sclientTime = clientHours+":"+clientMinutes;
	    String contextPath = request.getContextPath();
	    
		Class_Schedule Class_Schedule = new Class_Schedule();
		
		int  class_id = 0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		
		ScheduleManagement scheduleManagement = new ScheduleManagement();
		///////////////////////////////Array for Sessions//////////////////////////////////////////////////
		Class_Schedule = scheduleManagement.getSessionsByClass(Class_Schedule, class_id);	
		///////////////////////////////Array for Week Days//////////////////////////////////////////////////			
		Class_Schedule = scheduleManagement.getWeekDaysByClass(Class_Schedule, class_id);
		///////////////////////////////Array for Courses and Groups/////////////////////////////////////////
		Class_Schedule = scheduleManagement.getScheduleCourses(Class_Schedule, class_id);
		///////////////// Check if the course is already selected for the same session same day/////////////
		try {
			Class_Schedule = scheduleManagement.getScheduleExistingCourses(Class_Schedule, admin_id, class_id, sclientTime, sclientDay, contextPath);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//////////////////End Check if the course is alre... ///////////////////////////////////////////////	
		request.setAttribute("Class_Schedule", Class_Schedule);
		String address = null;
		address = "/Admin/Schedule/Display_Class_Schedule.jsp?Class_id="+class_id;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
