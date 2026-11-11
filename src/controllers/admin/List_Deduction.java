package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Deduction_List;
import model.beans.admin.Employee_Deduction;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class List_Deduction
 */
@WebServlet("/List_Deduction")
public class List_Deduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Deduction() {
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
    	int employee_id=0;
    	if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
    	{
    		employee_id = Integer.parseInt(request.getParameter("Employee_id"));
    	}
		HRManagement hrManagement = new HRManagement();
		if(payroll_id==0 && employee_id!=0)
		{
			payroll_id = hrManagement.getEmployeePayroll(employee_id);
		}
		Deduction_List Deduction_List = new Deduction_List(); 
		Deduction_List = hrManagement.getDeductionList(payroll_id);
		request.setAttribute("Deduction_List", Deduction_List);
		Employee_Deduction Employee_Deduction = new Employee_Deduction();
		Employee_Deduction = hrManagement.getEmployeeDeduction(employee_id);
		request.setAttribute("Employee_Deduction", Employee_Deduction); 	
	    String address = null;
	    if(request.getParameter("Dropdown")!=null &&!request.getParameter("Dropdown").equals(""))
	    {
	    	address="/Admin/HR/Display_Deduction_List.jsp";
	    }
	    else
	    {
	    	address="/Admin/HR/Display_Deduction.jsp";
	    }
	    
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
