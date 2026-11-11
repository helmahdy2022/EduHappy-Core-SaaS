package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class Process_Payroll
 */
@WebServlet("/Process_Payroll")
public class Process_Payroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process_Payroll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int updateQuery = 0;
		String message=null;
		PayrollManagement payrollManagement = new PayrollManagement();
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			updateQuery = payrollManagement.processPayroll(request);
			if(updateQuery!=0)
			{
				message="Payroll for this month has been processed and saved succesfully.";
			}
			else
			{
				message="Failed to process Payroll for this month.";
			}
		}
		else
		{
			HttpSession session= request.getSession();
	    	int admin_id = (Integer) session.getAttribute("Admin_Session");
	    	int monthly_Payroll_id=0;
	    	if(request.getParameter("Monthly_Payroll_id")!=null && !request.getParameter("Monthly_Payroll_id").equals(""))
	    	{
	    		monthly_Payroll_id = Integer.parseInt(request.getParameter("Monthly_Payroll_id"));
	    	}	
			updateQuery = payrollManagement.deletePayrollReport(monthly_Payroll_id, admin_id);
			if(updateQuery!=0)
			{
				message="Payroll for this month has been deleted succesfully.";
			}
			else
			{
				message="Failed to delete Payroll for this month.";
			}
		}

		String address="/Admin/HR/Display_Process_Payroll.jsp?message="+message;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
