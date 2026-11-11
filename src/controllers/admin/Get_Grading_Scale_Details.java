package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grading_Scale_Details;
import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class Get_Grading_Scale_Details
 */
@WebServlet("/Get_Grading_Scale_Details")
public class Get_Grading_Scale_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grading_Scale_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grading_Scale_id = Integer.parseInt(request.getParameter("Grading_Scale_id"));
		GradeManagement gradeManagement = new GradeManagement();
		Grading_Scale_Details Grading_Scale_Details = new Grading_Scale_Details();
		Grading_Scale_Details = gradeManagement.getGradingScaleDetails(grading_Scale_id);
		request.setAttribute("Grading_Scale_Details", Grading_Scale_Details);
		RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Admin/Grades/Display_Grading_Scale_Details.jsp?Grading_Scale_id="+grading_Scale_id);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
