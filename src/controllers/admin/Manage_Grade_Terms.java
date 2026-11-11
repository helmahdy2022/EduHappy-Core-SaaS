package controllers.admin;

import java.io.IOException;

import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class Manage_Grade_Terms
 */
@WebServlet("/Manage_Grade_Terms")
public class Manage_Grade_Terms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Grade_Terms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null)
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		String message = "";
		
		int terms_qty = 0;
		if(request.getParameter("Terms_qty")!=null)
		{
			terms_qty = Integer.parseInt(request.getParameter("Terms_qty"));
		} 
		GradeManagement gradeManagement = new GradeManagement();
		try {
			message = gradeManagement.manageGradeTerms(school_Year_id, grade_id, terms_qty, request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 String address = "/Admin/Grades/Display_Insert_Grade_Terms.jsp?message="+message;
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}
}
