package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Department_Details;
import model.beans.admin.Department_List;
import model.dbHelpers.admin.HRManagement;


/**
 * Servlet implementation class Manage_Department
 */
@WebServlet("/Manage_Department")
public class Manage_Department extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Department() {
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
		request.setCharacterEncoding("UTF-8");		
		HttpSession session= request.getSession();
    	int admin_id = (Integer) session.getAttribute("Admin_Session");
    	int department_id=0;
    	if(request.getParameter("Department_id")!=null && !request.getParameter("Department_id").equals(""))
    	{
    		department_id = Integer.parseInt(request.getParameter("Department_id"));
    	}	
		int school_id=0;
    	if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School"));
    	}
		String department_Name = request.getParameter("Department_Name");
		String department_Description = null;
		if(request.getParameter("Department_Description")!=null && !request.getParameter("Department_Description").equals(""))
		{
			department_Description = request.getParameter("Department_Description");	
		}
		HRManagement hrManagement = new HRManagement();
		Department_Details Department_Details = new Department_Details();
		Department_Details.setDepartment_id(department_id);
		Department_Details.setDepartment_Name(department_Name);
		Department_Details.setDepartment_Description(department_Description);
		Department_Details.setSchool_id(school_id);
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (department_id==0)
			{
				
				updateQuery = hrManagement.createDepartment(Department_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Department created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Department name. Department not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Department was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }
				
			}
		    else
		    {
		    	updateQuery = hrManagement.updateDepartment(Department_Details, department_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Department updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Department name. Department not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Department was not created, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
		}
	        
		else
		{
			updateQuery = hrManagement.deleteDepartment(department_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Department successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Department. Only account owner is authorized to delete Department.";
			}
			else
			{
				message = "Something wrong happend. Department was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Departments///////////////////////////////
		Department_List Department_List = new Department_List(); 
		Department_List = hrManagement.getDepartmentList(school_id);
		request.setAttribute("Department_List", Department_List);
    	/////////////////////////////End retrieve List of Departments///////////////////////////////  	
	    String address = "/Admin/HR/Department.jsp?message="+message;
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
