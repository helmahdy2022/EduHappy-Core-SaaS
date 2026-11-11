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
import model.dbHelpers.admin.HRManagement;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class Run_Payroll
 */
@WebServlet("/Run_Payroll")
public class Run_Payroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Run_Payroll() {
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
		HRManagement hrManagement = new HRManagement();
		Allowance_List Allowance_List = new Allowance_List(); 
		Allowance_List = hrManagement.getAllowanceList(payroll_id);
		request.setAttribute("Allowance_List", Allowance_List);
		Deduction_List Deduction_List = new Deduction_List(); 
		Deduction_List = hrManagement.getDeductionList(payroll_id);
		request.setAttribute("Deduction_List", Deduction_List);
	
		/////////////Get current year and month////////////
		Format formatter = new SimpleDateFormat("YYYY");
		String payroll_Year = formatter.format(new Date());
		
		formatter = new SimpleDateFormat("MMMM");
		String payroll_Month = formatter.format(new Date());
		//////////////////////////////////////////////////		
		PayrollManagement payrollManagement = new PayrollManagement();
		boolean payroll_Exist = false;
		payroll_Exist = payrollManagement.isPayrollExist(payroll_id, payroll_Month, payroll_Year);
		if(payroll_Exist==false)
		{
			Payroll Payroll = new Payroll();
			Payroll = payrollManagement.runPayroll(payroll_id);
			request.setAttribute("Payroll", Payroll);
		}
		String address="/Admin/HR/Display_Run_Payroll.jsp?Payroll_Exist="+payroll_Exist+"&&Payroll_id="+payroll_id+"&&Payroll_Month="+payroll_Month+"&&Payroll_Year="+payroll_Year;
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
