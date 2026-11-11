package controllers.student;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.student.Attendance_Summary;
import model.dbHelpers.student.ServiceAttendance;


/**
 * Servlet implementation class Get_Attendance_Summary
 */
@WebServlet("/Get_Attendance_Summary")
public class Get_Attendance_Summary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Attendance_Summary() {
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
		ServiceAttendance serviceAttendance = new ServiceAttendance();
		Attendance_Summary Attendance_Summary = new Attendance_Summary();
		Attendance_Summary = serviceAttendance.getAttendanceSummary(student_id);
		
		request.setAttribute("Attendance_Summary", Attendance_Summary);
 		String address = "/Student/Attendance/Display_Attendance_Summary.jsp?Source="+source+"&&Student_id="+student_id;
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
