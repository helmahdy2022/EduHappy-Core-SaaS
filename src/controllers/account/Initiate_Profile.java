package controllers.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Admin_Details;
import model.beans.admin.Department_List;
import model.beans.admin.Payroll_List;
import model.dbHelpers.admin.AdminManagement;
import model.dbHelpers.admin.HRManagement;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class Initiate_Profile
 */
@WebServlet("/Initiate_Profile")
public class Initiate_Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Initiate_Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
		
		AdminManagement adminManagement = new AdminManagement();
		Admin_Details admin_Details = new Admin_Details();
		admin_Details = adminManagement.getAdmin(admin_id);
		request.setAttribute("Admin_Details", admin_Details);
		
		PayrollManagement payrollManagement = new PayrollManagement();
		Payroll_List Payroll_List = new Payroll_List(); 
		//Payroll_List = payrollManagement.getAllPayrolls(admin_Details.getAccount_id());
		Payroll_List = payrollManagement.getAdminPayrolls(admin_id);
		request.setAttribute("Payroll_List", Payroll_List);
	    ///////////////////////Get Departments/////////////////////////
	    HRManagement hrManagement = new HRManagement();
	    Department_List Department_List = new Department_List();
	    Department_List = hrManagement.getDepartmentsByPayroll(admin_Details.getPayroll_id());
	    request.setAttribute("Department_List", Department_List);

	    String address = "/Account/Account_Profile.jsp";
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
