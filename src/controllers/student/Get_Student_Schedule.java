package controllers.student;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.student.Student_Schedule;
import model.dbHelpers.student.ServiceSchedule;

/**
 * Servlet implementation class Get_Student_Schedule
 */
@WebServlet("/Get_Student_Schedule")
public class Get_Student_Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Student_Schedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int student_id = 0;
		String source = null;
	    String clientHours = request.getParameter("clientHours");	
	    String clientMinutes = request.getParameter("clientMinutes");
	    String sclientDay = request.getParameter("clientDay");	
	    String sclientTime = clientHours+":"+clientMinutes;
	    String contextPath = request.getContextPath();
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			source = "Parent";
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
			source = "Student";
		}
		
		ServiceSchedule serviceSchedule = new ServiceSchedule();
		Student_Schedule Student_Schedule = new Student_Schedule();
		try {
			Student_Schedule = serviceSchedule.getStudentSchedule(student_id, sclientTime, sclientDay, contextPath);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("Student_Schedule", Student_Schedule);
		String address = null;
		address = "/Student/Schedule/Display_Student_Schedule.jsp?Source="+source+"&&Student_id="+student_id;
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
