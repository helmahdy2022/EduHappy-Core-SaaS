package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dbHelpers.admin.FeesManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Manage_Fee_Payments
 */
@WebServlet("/Manage_Fee_Payments")
public class Manage_Fee_Payments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Fee_Payments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int student_id = Integer.parseInt(request.getParameter("Student_id"));
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		FeesManagement feesManagement = new FeesManagement();
		int receipt_id = feesManagement.manageFeePayments(request, school_id);
		String message=null;
		if(receipt_id!=-1)
		{
			message="Payment has been created succesffuly with Receipt Number = "+receipt_id;
		}
		else
		{
			message="Failed to create Payment. Please try again";	
		}
		String address = "/Admin/Fees/Display_Manage_Fee_Payments.jsp?message="+message;
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
