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
import javax.servlet.http.HttpSession;

import model.beans.admin.Department_List;
import model.beans.admin.Payroll_List;
import model.beans.admin.Teacher_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.HRManagement;
import model.dbHelpers.admin.PayrollManagement;
import model.dbHelpers.admin.TeacherManagement;

/**
 * Servlet implementation class Get_Teacher_Details
 */
@WebServlet("/Get_Teacher_Details")
public class Get_Teacher_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeacherManagement teacherManagement = new TeacherManagement();
		int teacher_id = 0;
		if(request.getParameter("Teacher_id")!=null&&request.getParameter("Teacher_id")!="")
		{
			teacher_id = Integer.parseInt(request.getParameter("Teacher_id")) ;
		}
		int employee_id = 0;
		if(teacher_id==0 && request.getParameter("Employee_id")!=null)
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id")) ;
			teacher_id = teacherManagement.getEmployeeTeacher(employee_id);
		}
		Teacher_Details Teacher_Details = new Teacher_Details();
		Teacher_Details = teacherManagement.getTeacher(teacher_id);

    	//////////////////////Retrieve Teacher Schools //////////////////////////////////
    	ArrayList<Integer> TeacherSchool_id = new ArrayList<Integer>();
    	TeacherSchool_id = teacherManagement.getTeacherSchools(teacher_id);
    	Teacher_Details.setSchool_id(TeacherSchool_id);
    	////////////////////////////////////////////////////////////////////////////////
    	
		//////////////////////////Retrieve the Teacher photo //////////////////////////////////////
    	HttpSession session= request.getSession();
    	int admin_id = 0;
    	String source = request.getParameter("Source");
    	if(source!=null&&source.equalsIgnoreCase("view"))
    	{
    		admin_id = teacherManagement.getTeacherAdmin(teacher_id);
    	}
    	else
    	{
    		admin_id = (Integer) session.getAttribute("Admin_Session");
    	}    	
    	AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getAdminAccount(admin_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Teachers/Photos/"+teacher_id);
		File[] list = jsp.listFiles();
		String fileName = null;
		if (list!=null)
		{
			for(int i=0;i<list.length;i++)
			{
				 fileName= list[i].getName();
			}
		}	
		Teacher_Details.setFileName(fileName);
		Teacher_Details.setAccount_id(account_id);				 

		request.setAttribute("Teacher_Details", Teacher_Details);		
		//////////////////////Get payrolls/////////////////////////////
		PayrollManagement payrollManagement = new PayrollManagement();
		Payroll_List Payroll_List = new Payroll_List(); 
		Payroll_List = payrollManagement.getTeacherPayrolls(teacher_id);
	    request.setAttribute("Payroll_List", Payroll_List);
		///////////////////////////////////////////////////////////////
	    
	    ///////////////////////Get Departments/////////////////////////
	    HRManagement hrManagement = new HRManagement();
	    Department_List Department_List = new Department_List();
	    Department_List = hrManagement.getDepartmentsByPayroll(Teacher_Details.getPayroll_id());
	    request.setAttribute("Department_List", Department_List);
	    ///////////////////////////////////////////////////////////////
		
		String address =null;
		
		if (source!=null&&source.equalsIgnoreCase("view"))
   		{
   			address = "/Teacher/Profile/View_Profile.jsp";
   		}
   		else
   		{
   			address = "/Admin/HR/Edit_Teacher.jsp?Teacher_id="+teacher_id;
   		}   
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
