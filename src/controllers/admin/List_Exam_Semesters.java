package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Exam_Semesters;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class List_Exam_Semesters
 */
@WebServlet("/List_Exam_Semesters")
public class List_Exam_Semesters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Exam_Semesters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_year_id=0;
		int grade_id=0;
		school_year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		
		SemesterManagement semesterManagement = new SemesterManagement();
		Exam_Semesters Exam_Semesters = new Exam_Semesters(); 
		Exam_Semesters = semesterManagement.getExamSemesterList(school_year_id, grade_id);
    	request.setAttribute("Exam_Semesters", Exam_Semesters);
     	HttpSession session= request.getSession();
        session.setAttribute("Semester_Source", Exam_Semesters.getSemester_Source());
		String address;
		address = "/Global/Display_Exam_Semesters.jsp";
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
