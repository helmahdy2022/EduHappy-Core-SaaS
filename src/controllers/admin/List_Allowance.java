package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Allowance_List;
import model.beans.admin.Employee_Allowance;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class List_Allowance
 */
@WebServlet("/List_Allowance")
public class List_Allowance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Allowance() {
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
		Allowance_List Allowance_List = new Allowance_List(); 
		Allowance_List = hrManagement.getAllowanceList(payroll_id);
		request.setAttribute("Allowance_List", Allowance_List);
		Employee_Allowance Employee_Allowance = new Employee_Allowance();
		Employee_Allowance = hrManagement.getEmployeeAllowance(employee_id);
		request.setAttribute("Employee_Allowance", Employee_Allowance);	
	    String address = null;
	    if(request.getParameter("Dropdown")!=null &&!request.getParameter("Dropdown").equals(""))
	    {
	    	address="/Admin/HR/Display_Allowance_List.jsp";
	    }
	    else
	    {
	    	address="/Admin/HR/Display_Allowance.jsp";
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
