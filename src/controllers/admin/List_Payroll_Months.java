package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Monthly_Payroll_List;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class List_Payroll_Months
 */
@WebServlet("/List_Payroll_Months")
public class List_Payroll_Months extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Payroll_Months() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int payroll_id=0;
		if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}	
		PayrollManagement payrollManagement = new PayrollManagement();
		Monthly_Payroll_List Monthly_Payroll_List = new Monthly_Payroll_List();
		Monthly_Payroll_List = payrollManagement.getPayrollMonths(payroll_id);
		request.setAttribute("Monthly_Payroll_List", Monthly_Payroll_List);
		String address="/Admin/HR/Display_Monthly_Payroll_List.jsp";
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
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
