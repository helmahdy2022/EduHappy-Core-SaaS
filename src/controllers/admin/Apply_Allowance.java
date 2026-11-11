package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Allowance_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Apply_Allowance
 */
@WebServlet("/Apply_Allowance")
public class Apply_Allowance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apply_Allowance() {
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
		Allowance_List Allowance_List = new Allowance_List();
		HRManagement hrManagement = new HRManagement();
		int payroll_id=0;
		String message = null;
		if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}
    	int employee_id=0;
    	if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
    	{
    		employee_id = Integer.parseInt(request.getParameter("Employee_id"));
    	}
		if(payroll_id==0 && employee_id!=0)
		{
			payroll_id = hrManagement.getEmployeePayroll(employee_id);
		}
		Allowance_List = hrManagement.getAllowanceList(payroll_id);
		for (Integer Allowance_id : Allowance_List.getAllowance_id()) 
    	{
    		int allowance_id = 0;
    		double amount = -1;
    		int payroll_Month = -1;
    		if(request.getParameter("Allowance"+Allowance_id)!=null&&!request.getParameter("Allowance"+Allowance_id).equals(""))
			{
    			allowance_id = Integer.parseInt(request.getParameter("Allowance"+Allowance_id));
    			if(request.getParameter("Amount"+Allowance_id)!=null&&!request.getParameter("Amount"+Allowance_id).equals(""))
    			{
    				amount = Double.parseDouble(request.getParameter("Amount"+Allowance_id));
    			}
    			if(request.getParameter("Payroll_Month"+Allowance_id)!=null&&!request.getParameter("Payroll_Month"+Allowance_id).equals(""))
    			{
    				payroll_Month = Integer.parseInt(request.getParameter("Payroll_Month"+Allowance_id));
    			}
			}
			if (allowance_id!=0)
			{
				boolean employeeAllowanceExist = false;
				employeeAllowanceExist = hrManagement.checkEmployeeAllowance(allowance_id, employee_id);
				if (employeeAllowanceExist==false)
				{
					hrManagement.createEmployeeAllowance(allowance_id, employee_id, amount, payroll_Month);

				}
				else
				{
					hrManagement.updateEmployeeAllowance(allowance_id, employee_id, amount, payroll_Month);
				}
			}
			else
			{
				//Delete
				allowance_id  = Integer.parseInt(request.getParameter("hiddenAllowance"+Allowance_id));
				hrManagement.deleteEmployeeAllowance(allowance_id, employee_id);
			}
			message = "Allowance Applied successfully";
    	}
		String address="/Admin/HR/Apply_Allowance.jsp?message="+message;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
