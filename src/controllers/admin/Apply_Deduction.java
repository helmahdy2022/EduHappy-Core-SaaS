package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Deduction_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Apply_Deduction
 */
@WebServlet("/Apply_Deduction")
public class Apply_Deduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apply_Deduction() {
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
		Deduction_List Deduction_List = new Deduction_List();
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
		Deduction_List = hrManagement.getDeductionList(payroll_id);
		for (Integer Deduction_id : Deduction_List.getDeduction_id()) 
    	{
    		int deduction_id = 0;
    		double amount = -1;
    		int payroll_Month = -1;
    		if(request.getParameter("Deduction"+Deduction_id)!=null&&!request.getParameter("Deduction"+Deduction_id).equals(""))
			{
    			deduction_id = Integer.parseInt(request.getParameter("Deduction"+Deduction_id));
    			if(request.getParameter("Amount"+Deduction_id)!=null&&!request.getParameter("Amount"+Deduction_id).equals(""))
    			{
    				amount = Double.parseDouble(request.getParameter("Amount"+Deduction_id));
    			}
    			if(request.getParameter("Payroll_Month"+Deduction_id)!=null&&!request.getParameter("Payroll_Month"+Deduction_id).equals(""))
    			{
    				payroll_Month = Integer.parseInt(request.getParameter("Payroll_Month"+Deduction_id));
    			}
			}

			if (deduction_id!=0)
			{
				boolean employeeDeductionExist = false;
				employeeDeductionExist = hrManagement.checkEmployeeDeduction(deduction_id, employee_id);
				if (employeeDeductionExist==false)
				{
					hrManagement.createEmployeeDeduction(deduction_id, employee_id, amount, payroll_Month);

				}
				else
				{
					hrManagement.updateEmployeeDeduction(deduction_id, employee_id, amount, payroll_Month);
				}
			}
			else
			{
				//Delete
				deduction_id  = Integer.parseInt(request.getParameter("hiddenDeduction"+Deduction_id));
				hrManagement.deleteEmployeeDeduction(deduction_id, employee_id);
			}
			message = "Deduction Applied successfully";
    	}
		String address="/Admin/HR/Apply_Deduction.jsp?message="+message;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
