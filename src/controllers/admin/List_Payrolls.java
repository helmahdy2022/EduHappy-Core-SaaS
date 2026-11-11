package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Payroll_List;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class List_Payrolls
 */
@WebServlet("/List_Payrolls")
public class List_Payrolls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Payrolls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int school_id=0;
    	if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School_id"));
    	}
		PayrollManagement payrollManagement = new PayrollManagement();
		Payroll_List Payroll_List = new Payroll_List(); 
		Payroll_List = payrollManagement.getPayrollList(school_id);
		request.setAttribute("Payroll_List", Payroll_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	    String address = null;
	    if(request.getParameter("Dropdown")!=null &&!request.getParameter("Dropdown").equals(""))
	    {
	    	address="/Admin/HR/Display_Payroll_List.jsp";
	    }
	    else
	    {
	    	address="/Admin/HR/Display_Payrolls.jsp";
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
