package controllers.admin;

import java.io.IOException;






import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Semester_Exam_Details;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Get_Semester_Exam_Details
 */
@WebServlet("/Get_Semester_Exam_Details")
public class Get_Semester_Exam_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Semester_Exam_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int semester_id = Integer.parseInt(request.getParameter("Semester_id"));
		HttpSession session= request.getSession();
		String semester_Source = (String) session.getAttribute("Semester_Source");
		SemesterManagement semesterManagement = new SemesterManagement();
		Semester_Exam_Details Semester_Exam_Details = new Semester_Exam_Details();
		Semester_Exam_Details = semesterManagement.getSemesterExam(course_id, semester_id, semester_Source);
		request.setAttribute("Semester_Exam_Details", Semester_Exam_Details);
		String address;
		address = "/Admin/Courses/Display_Semester_Exam_Details.jsp";
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
