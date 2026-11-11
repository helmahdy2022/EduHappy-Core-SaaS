package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Students;
import model.dbHelpers.admin.YearEndProcedures;

/**
 * Servlet implementation class Get_Previous_Grading_Report
 */
@WebServlet("/Get_Previous_Grading_Report")
public class Get_Previous_Grading_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Previous_Grading_Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id=0;
		if(request.getParameter("School_id")!=null)
		{
			school_id = Integer.parseInt(request.getParameter("School_id"));
		}
		int previous_school_year_id=0;
		if(request.getParameter("School_year_id")!=null&&!request.getParameter("School_year_id").equals(""))
		{
			previous_school_year_id = Integer.parseInt(request.getParameter("School_year_id"));
		}
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		YearEndProcedures yearEndProcedures = new YearEndProcedures();
		Students Students = new Students();		
		Students = yearEndProcedures.produceFinalGradingReport(school_id, grade_id, previous_school_year_id);
		request.setAttribute("Students", Students);
		String address = "/Admin/YearEnd/Display_Final_Grading_Report.jsp?isReportProduced=true";
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
