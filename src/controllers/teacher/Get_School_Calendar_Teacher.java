package controllers.teacher;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Holidays_Details;
import model.beans.admin.School_Details;
import model.beans.admin.School_Year;
import model.dbHelpers.admin.HolidayManagement;
import model.dbHelpers.admin.SchoolManagement;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.TeacherManagement;

/**
 * Servlet implementation class Get_School_Calendar_Teacher
 */
@WebServlet("/Get_School_Calendar_Teacher")
public class Get_School_Calendar_Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_School_Calendar_Teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int teacher_id = (Integer) session.getAttribute("Teacher_Session");
		TeacherManagement teacherManagement = new TeacherManagement();
		int school_id = teacherManagement.getTeacherOneSchool(teacher_id);
		//int grade_id = teacherManagement.getStudentGrade(Teacher_id);

		//////////////////////////////Get Grade Terms if exist /////////////////////////////
/*		Grade_Terms Grade_Terms = new Grade_Terms();
		GradeManagement gradeManagement = new GradeManagement();
		Grade_Terms = gradeManagement.getGradeTermsDetails(grade_id, 0);		
		int term_qty = 0;
		boolean grade_Existing_Terms = true;
		term_qty = Grade_Terms.getGrade_Terms_List().size();
		if (term_qty==0)
		{
			grade_Existing_Terms = false;
		}		
		request.setAttribute("Grade_Terms", Grade_Terms);*/
		////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		School_Year School_Year = new School_Year();
		School_Year = schoolYearManagement.getSchoolYearDetails(0, school_id);
		request.setAttribute("School_Year", School_Year);
		///////////////////////////////////////////////////////////////////////////////////////
		
		int admin_id = teacherManagement.getTeacherAdmin(teacher_id);
		SchoolManagement schoolManagement = new SchoolManagement();
		School_Details school_Details = new School_Details();
		school_Details = schoolManagement.getSchool(school_id);
		HolidayManagement holidayManagement = new HolidayManagement();
		Holidays_Details Holidays_Details = new Holidays_Details();
		Holidays_Details = holidayManagement.getHolidayDetails(school_Details.getCountry_id(), admin_id);
		request.setAttribute("Holidays_Details", Holidays_Details);
		//////////////////////////////////////////////////////////////////////////////////////
/*		DaysoffManagement daysoffManagement = new DaysoffManagement();
		Daysoff_Details Daysoff_Details = new Daysoff_Details();
		Daysoff_Details = daysoffManagement.getDaysoffDetailsPerGrade(grade_id, admin_id);
		request.setAttribute("Daysoff_Details", Daysoff_Details);*/
		//////////////////////////////////////////////////////////////////////////////////////
		String address = "/Teacher/Calendar/Display_School_Calendar.jsp";
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
