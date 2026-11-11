package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Daysoff_Details;
import model.beans.admin.Grade_Terms;
import model.beans.admin.Holidays_Details;
import model.beans.admin.School_Details;
import model.beans.admin.School_Year;
import model.dbHelpers.admin.DaysoffManagement;
import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.HolidayManagement;
import model.dbHelpers.admin.SchoolManagement;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Get_School_Calendar
 */
@WebServlet("/Get_School_Calendar")
public class Get_School_Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_School_Calendar() {
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
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		int grade_id = studentManagement.getStudentGrade(student_id);

		//////////////////////////////Get Grade Terms if exist /////////////////////////////
		Grade_Terms Grade_Terms = new Grade_Terms();
		GradeManagement gradeManagement = new GradeManagement();
		Grade_Terms = gradeManagement.getGradeTermsDetails(grade_id, 0);		
		int term_qty = 0;
		boolean grade_Existing_Terms = true;
		term_qty = Grade_Terms.getGrade_Terms_List().size();
		if (term_qty==0)
		{
			grade_Existing_Terms = false;
		}		
		request.setAttribute("Grade_Terms", Grade_Terms);
		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		School_Year School_Year = new School_Year();
		School_Year = schoolYearManagement.getSchoolYearDetails(0, school_id);
		request.setAttribute("School_Year", School_Year);
		///////////////////////////////////////////////////////////////////////////////////////
		
		int admin_id = studentManagement.getStudentAdmin(student_id);
		SchoolManagement schoolManagement = new SchoolManagement();
		School_Details school_Details = new School_Details();
		school_Details = schoolManagement.getSchool(school_id);
		HolidayManagement holidayManagement = new HolidayManagement();
		Holidays_Details Holidays_Details = new Holidays_Details();
		Holidays_Details = holidayManagement.getHolidayDetails(school_Details.getCountry_id(), admin_id);
		request.setAttribute("Holidays_Details", Holidays_Details);
		//////////////////////////////////////////////////////////////////////////////////////
		DaysoffManagement daysoffManagement = new DaysoffManagement();
		Daysoff_Details Daysoff_Details = new Daysoff_Details();
		Daysoff_Details = daysoffManagement.getDaysoffDetailsPerGrade(grade_id, admin_id);
		request.setAttribute("Daysoff_Details", Daysoff_Details);
		//////////////////////////////////////////////////////////////////////////////////////
		String address = "/Student/Calendar/Display_School_Calendar.jsp?Grade_Existing_Terms="+grade_Existing_Terms+"&&Source="+source;
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
