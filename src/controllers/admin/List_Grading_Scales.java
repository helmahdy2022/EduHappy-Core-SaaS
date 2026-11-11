package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grading_Scales;
import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class List_Grading_Scales
 */
@WebServlet("/List_Grading_Scales")
public class List_Grading_Scales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Grading_Scales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		GradeManagement gradeManagement = new GradeManagement();
		Grading_Scales Grading_Scales = new Grading_Scales();
		Grading_Scales = gradeManagement.getGradingScales(grade_id);
		request.setAttribute("Grading_Scales", Grading_Scales);
		RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Admin/Grades/Display_Grading_Scales.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
