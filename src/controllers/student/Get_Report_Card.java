package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.admin.Grade_Details;
import model.beans.student.Student_Grading;
import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceReportCard;

/**
 * Servlet implementation class Get_Report_Card
 */
@WebServlet("/Get_Report_Card")
public class Get_Report_Card extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Report_Card() {
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
		source = request.getParameter("Source");
		if(request.getParameter("Student_id")!=null&&!request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");
		}		
		ServiceReportCard serviceReportCard = new ServiceReportCard();
		Student_Grading Student_Grading = new Student_Grading();
		String promoted = request.getParameter("Promoted");
		if(promoted==null)
		{
			promoted = "false";
		}
		
		boolean student_Promoted=false;
		String year_Name = null;
		if(session.getAttribute("Student_Account_Details")!=null)
		{
			Account_Details Student_Account_Details = (Account_Details) session.getAttribute("Student_Account_Details");
			student_Promoted = Student_Account_Details.isStudent_Promoted();
			year_Name = Student_Account_Details.getYear_Name();
		}
		String final_Report = "No";
		int previous_school_year_id = 0;
		if(request.getParameter("Previous_school_year_id")!=null&&!request.getParameter("Previous_school_year_id").equals(""))
		{
			previous_school_year_id = Integer.parseInt(request.getParameter("Previous_school_year_id"));
		}
		int current_school_year_id = 0;
		if(request.getParameter("Current_school_year_id")!=null&&!request.getParameter("Current_school_year_id").equals(""))
		{
			current_school_year_id = Integer.parseInt(request.getParameter("Current_school_year_id"));
		}
		if(((promoted!=null&&promoted.equals("false"))&&!student_Promoted)&&(previous_school_year_id==0||previous_school_year_id==current_school_year_id))
		{
			///////////Get Student Grade////////////////////
			StudentManagement studentManagement = new StudentManagement();
			int grade_id = studentManagement.getStudentGrade(student_id);
			GradeManagement gradeManagement = new GradeManagement();
			Grade_Details Grade_Details = new Grade_Details();
			Grade_Details = gradeManagement.getGrade(grade_id);
			int displayHideReport = Grade_Details.getDisplayHideReport();
			//////////Check DisplayHideReport from the grade record/////////////////
			if(displayHideReport == 0 && source!=null && (source.equals("Student") || source.equals("Parent")))
			{
				request.setAttribute("displayHideReport", displayHideReport);
			}
			else
			{
				Student_Grading = serviceReportCard.getReportCard(student_id, 0);
			}

		}
		else
		{			
			Student_Grading = serviceReportCard.getFinalReportCard(student_id, 0, previous_school_year_id);
			final_Report = "Yes";
		}
		getServletContext().setAttribute("Student_Grading", Student_Grading);
		if(previous_school_year_id==0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Student/Grading/Student_Grading.jsp?Student_id="+student_id+"&&Final_Report="+final_Report+"&&Year_Name="+year_Name+"&&Promoted="+promoted+"&&Source="+source);
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Student/Grading/Grading_Report.jsp?Student_id="+student_id+"&&Final_Report="+final_Report+"&&Year_Name="+year_Name+"&&Promoted="+promoted+"Source="+source);
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
