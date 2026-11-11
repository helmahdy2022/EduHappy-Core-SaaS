package controllers.student;

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

import model.beans.student.Assignments;
import model.dbHelpers.student.ServiceAssignment;

/**
 * Servlet implementation class Get_Previous_Assignments
 */
@WebServlet("/Get_Previous_Assignments")
public class Get_Previous_Assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Previous_Assignments() {
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
		//String source = null;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals("") && !request.getParameter("Student_id").equals("0"))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			//source = "Parent";
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
			//source = "Student";
		}
		int course_id=0;
		if(request.getParameter("Course_id")!=null&&!request.getParameter("Course_id").equals(""))
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null&&!request.getParameter("Lesson_id").equals(""))
		{
			lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		}
		Date date_From=null;
		String sDate_From=null;
		Date date_To=null;
		String sDate_To=null;
		if(request.getParameter("Date_From")!=null&&!request.getParameter("Date_From").equals("")&&request.getParameter("Date_To")!=null&&!request.getParameter("Date_To").equals(""))
		{
			sDate_From = request.getParameter("Date_From");
			sDate_To = request.getParameter("Date_To");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date_From = format.parse(sDate_From);
				date_To = format.parse(sDate_To);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		Assignments Assignments = new Assignments();
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		Assignments = serviceAssignment.getAssignment(student_id, appPath, course_id, lesson_id, date_From, date_To);
		
		request.setAttribute("Assignments", Assignments);
		
		String address = "/Student/Assignments/Display_Previous_Assignments.jsp";
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
