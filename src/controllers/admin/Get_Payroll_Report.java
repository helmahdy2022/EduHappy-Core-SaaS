package controllers.admin;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Allowance_List;
import model.beans.admin.Deduction_List;
import model.beans.admin.Payroll;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class Get_Payroll_Report
 */
@WebServlet("/Get_Payroll_Report")
public class Get_Payroll_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Payroll_Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int monthly_Payroll_id=0;
		if(request.getParameter("Monthly_Payroll_id")!=null && !request.getParameter("Monthly_Payroll_id").equals(""))
    	{
			monthly_Payroll_id = Integer.parseInt(request.getParameter("Monthly_Payroll_id"));
    	}		
		PayrollManagement payrollManagement = new PayrollManagement();

		Allowance_List Allowance_List = new Allowance_List(); 
		Allowance_List = payrollManagement.getMonthlyAllowanceList(monthly_Payroll_id);
		request.setAttribute("Allowance_List", Allowance_List);
		Deduction_List Deduction_List = new Deduction_List(); 
		Deduction_List = payrollManagement.getMonthlyDeductionList(monthly_Payroll_id);
		request.setAttribute("Deduction_List", Deduction_List);

		Payroll Payroll = new Payroll();
		Payroll = payrollManagement.getPayrollReport(monthly_Payroll_id);
		request.setAttribute("Payroll", Payroll);
		/////////////Get current year and month////////////	
		Format formatter = new SimpleDateFormat("YYYY");
		String current_Year = formatter.format(new Date());
		formatter = new SimpleDateFormat("MMMM");
		String current_Month = formatter.format(new Date());
		String payroll_Year = null;
		String payroll_Month = null;
		if(Payroll.getMonthly_Payroll_Date()!=null && !Payroll.getMonthly_Payroll_Date().equals(""))
		{			
			formatter = new SimpleDateFormat("YYYY");
			payroll_Year = formatter.format(Payroll.getMonthly_Payroll_Date());
			formatter = new SimpleDateFormat("MMMM");
			payroll_Month = formatter.format(Payroll.getMonthly_Payroll_Date());			
		}

		//////////////////////////////////////////////////
		String address="/Admin/HR/Display_Payroll_Report.jsp?Monthly_Payroll_id="+monthly_Payroll_id+"&&Payroll_Month="+payroll_Month+"&&Payroll_Year="+payroll_Year+"&&Current_Year="+current_Year+"&&Current_Month="+current_Month;
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
