package controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Admin_Details;
import model.beans.admin.Department_List;
import model.beans.admin.Payroll_List;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.AdminManagement;
import model.dbHelpers.admin.HRManagement;
import model.dbHelpers.admin.PayrollManagement;


/**
 * Servlet implementation class Get_Admin_Details
 */
@WebServlet("/Get_Admin_Details")
public class Get_Admin_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Admin_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int admin_id = Integer.parseInt(request.getParameter("Admin_id")) ;
		int employee_id = Integer.parseInt(request.getParameter("Employee_id")) ;
		AdminManagement adminManagement = new AdminManagement();
		int admin_id = adminManagement.getEmployeeAdmin(employee_id);
		Admin_Details Admin_Details = new Admin_Details();
		Admin_Details = adminManagement.getAdmin(admin_id);

    	//////////////////////Retrieve Admin Schools //////////////////////////////////
    	ArrayList<Integer> AdminSchool_id = new ArrayList<Integer>();
    	AdminSchool_id = adminManagement.getAdminSchools(admin_id);
    	Admin_Details.setSchool_id(AdminSchool_id);
    	////////////////////////////////////////////////////////////////////////////////
    	
		//////////////////////////Retrieve the Admin photo //////////////////////////////////////   	
    	AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getAdminAccount(admin_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Admins/Photos/"+admin_id);
		File[] list = jsp.listFiles();
		String fileName = null;
		if (list!=null)
		{
			for(int i=0;i<list.length;i++)
			{
				 fileName= list[i].getName();
			}
		}	
		Admin_Details.setFileName(fileName);
		Admin_Details.setAccount_id(account_id);				 

		request.setAttribute("Admin_Details", Admin_Details);		
		//////////////////////Get payrolls/////////////////////////////
		PayrollManagement payrollManagement = new PayrollManagement();
		Payroll_List Payroll_List = new Payroll_List(); 
		Payroll_List = payrollManagement.getAdminPayrolls(admin_id);
	    request.setAttribute("Payroll_List", Payroll_List);
		///////////////////////////////////////////////////////////////
	    
	    ///////////////////////Get Departments/////////////////////////
	    HRManagement hrManagement = new HRManagement();
	    Department_List Department_List = new Department_List();
	    Department_List = hrManagement.getDepartmentsByPayroll(Admin_Details.getPayroll_id());
	    request.setAttribute("Department_List", Department_List);
	    ///////////////////////////////////////////////////////////////

   		String address = "/Admin/HR/Edit_Admin.jsp?Admin_id="+admin_id;
   		  
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
