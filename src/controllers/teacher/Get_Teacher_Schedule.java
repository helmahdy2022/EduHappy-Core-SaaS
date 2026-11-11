package controllers.teacher;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.core5.http.ParseException;

import model.beans.teacher.Teacher_Schedule;
import model.dbHelpers.teacher.ServiceSchedule;

/**
 * Servlet implementation class Get_Teacher_Schedule
 */
@WebServlet("/Get_Teacher_Schedule")
public class Get_Teacher_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		HttpSession session= request.getSession();
		int teacher_id=(Integer) session.getAttribute("Teacher_Session");
	    String clientHours = request.getParameter("clientHours");	
	    String sclientDay = request.getParameter("clientDay");
	    String clientMinutes = request.getParameter("clientMinutes");	
	    String sclientTime = clientHours+":"+clientMinutes;
	    String contextPath = request.getContextPath();
		ServiceSchedule serviceSchedule = new ServiceSchedule();
		Teacher_Schedule Teacher_Schedule = new Teacher_Schedule();
		try {
			Teacher_Schedule = serviceSchedule.getTeacherSchedule(teacher_id, sclientTime, sclientDay, contextPath);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
		request.setAttribute("Teacher_Schedule", Teacher_Schedule);
		String address = null;
		address = "/Teacher/Schedule/Display_Teacher_Schedule.jsp";
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
