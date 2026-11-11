package controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import model.beans.admin.Admin_Details;
import model.beans.admin.Schools;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.AdminManagement;
import model.dbHelpers.admin.GetSchools;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Admin
 */
@WebServlet("/Manage_Admin")
public class Manage_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Admin() {
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
		long totalFilesSize = 1024*1024*50;
		long fileSize = 1024*1024*10;
		int fileQty = 10;
		int autoIncValue=-1;
        @SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");	
		ArrayList<String> uploadMessage = new ArrayList<String>();
		
		int admin_id = 0;
		if (request.getParameter("Admin_id")!=null)
		{
			admin_id =Integer.parseInt(request.getParameter("Admin_id")) ;
		}
		/////////////////////////////////Employee Details to be inserted in Employee table/////////////////////////////////////
		HttpSession session= request.getSession();
		int LoggedIn_admin_id = (Integer) session.getAttribute("Admin_Session");	
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(LoggedIn_admin_id);	
		int payroll_id = 0;
		
		if(request.getParameter("Payroll_id")!=null&&!request.getParameter("Payroll_id").equals(""))
		{
			payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
		}
		
		int department_id = 0;
		if(request.getParameter("Department_id")!=null&&!request.getParameter("Department_id").equals(""))
		{
			department_id = Integer.parseInt(request.getParameter("Department_id"));
		}			
		String designation = request.getParameter("Designation");
		String joining_Date = request.getParameter("Joining_Date");
		double initial_Basic = Double.parseDouble(request.getParameter("Initial_Basic"));
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String first_Name = request.getParameter("First_Name");
		String last_Name = request.getParameter("Last_Name");
		int country_id = Integer.parseInt(request.getParameter("Country_id"));	
		String city = request.getParameter("City");
		int pO_Box = 0;
		if(request.getParameter("PO_Box")!=null && !request.getParameter("PO_Box").equals(""))
		{
			pO_Box = Integer.parseInt(request.getParameter("PO_Box"));	
		}	
		String full_Address = request.getParameter("Full_Address");	
		String gender = request.getParameter("Gender");
		String email = null;
		if(request.getParameter("Email")!=null && !request.getParameter("Email").equals(""))
		{
			email = request.getParameter("Email");
		}
		String tel = null;
		if(request.getParameter("Tel")!=null && !request.getParameter("Tel").equals(""))
		{
			tel = request.getParameter("Tel");
		}
		String mobile = null;
		if(request.getParameter("Mobile")!=null && !request.getParameter("Mobile").equals(""))
		{
			mobile = request.getParameter("Mobile");
		}
		String birth_Date = request.getParameter("Birth_Date");
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String owner_password = request.getParameter("Owner_Password");


		///////////////populate the bean ///////////////////////
		Admin_Details Admin_Details = new Admin_Details();
		Admin_Details.setAccount_id(account_id);
		Admin_Details.setPayroll_id(payroll_id);
		Admin_Details.setDepartment_id(department_id);
		Admin_Details.setDesignation(designation);
		Admin_Details.setJoining_Date(joining_Date);
		Admin_Details.setInitial_Basic(initial_Basic);
		Admin_Details.setAdmin_id(admin_id);
		Admin_Details.setFirst_Name(first_Name);
		Admin_Details.setLast_Name(last_Name);
		Admin_Details.setCountry_id(country_id);
		Admin_Details.setCity(city);
		Admin_Details.setPO_Box(pO_Box);
		Admin_Details.setFull_Address(full_Address);
		Admin_Details.setGender(gender);
		Admin_Details.setEmail(email);
		Admin_Details.setTel(tel);
		Admin_Details.setMobile(mobile);
		Admin_Details.setBirth_Date(birth_Date);
		Admin_Details.setUsername(username);
		Admin_Details.setPassword(password);
		
		///////////////end populate the bean ///////////////////////
		AdminManagement adminManagement = new AdminManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (admin_id==0)
			{		        		        
    			autoIncValue = adminManagement.createAdmin(Admin_Details);
		        if (autoIncValue!=-1 && autoIncValue!=-2)
		        {
		        	message = "New Admin Profile created successfully";
		        	admin_id = autoIncValue;
		        }
		        else
		        {
		        	if(autoIncValue==-2)
		        	{
		        		message = "Duplicate Entry for the Admin Username. Admin Profile was not created";
		        	}
		        	else
		        	{
		        		message = "Failed to create New Admin Profile";
		        	}		        	
		        }
		        
				
			}
		    else
		    {
		    	updateQuery = adminManagement.updateAdmin(Admin_Details, admin_id, owner_password);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Admin Profile updated successfully";
		        	//Account_Details account_Details = accountManagement.getAccountDetails(account_id);	
		        	//session.setAttribute("Account_Details", account_Details);
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Admin username. Admin Profile was not updated";
		        	}
		        	else
		        	{
		        		message = "Failed to update New Admin Profile";
		        	}
		        	
		        }
		    	
		    }

	        if ((updateQuery != 0 || (autoIncValue!=-1 && autoIncValue!=-2)) && (owner_password==null || owner_password.equals("") ||!owner_password.equals("Owner")))
	        {	
		    	/////////////////////////////Retrieve Schools///////////////////////////////
		    	GetSchools schools = new GetSchools();
		    	Schools Schools = new Schools();
		    	Schools = schools.doGetSchools(LoggedIn_admin_id);
		    	ArrayList<Integer> SchoolsIdList = Schools.getSchool_id();
		    	/////////////////////////////End retrieve Schools///////////////////////////////
		    	
		    	for (Integer school_id : SchoolsIdList) 
		    	{
		    		int School_id = 0;
		    		String x = request.getParameter("school"+school_id);
		    		if(x!=null)
					{
						School_id = Integer.parseInt(request.getParameter("school"+school_id));
					}

					if (School_id!=0)
					{
						boolean adminSchoolExist = false;
						adminSchoolExist = adminManagement.checkAdminSchool(School_id, admin_id);
						if (adminSchoolExist==false)
						{
							adminManagement.createAdminSchool(School_id, admin_id);
	
						}
					}
					else
					{
						//Delete
						School_id  = Integer.parseInt(request.getParameter("hiddenschool"+school_id));
						adminManagement.deleteAdminSchool(School_id, admin_id);
					}
		    	}
	        
		    	///////////////////////////deal with file upload here////////////////////////////////////////////////
		    	
		    	try {
					uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, -1 , "Admins","Photos", "Admin_id", null, admin_id, -1, items, totalFilesSize, fileSize, fileQty);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        }
		}
	        
		else
		{
			updateQuery = adminManagement.deleteAdmin(admin_id);
			if (updateQuery!=0)
			{
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Admins/Photos/" +admin_id);
				String[]entries = directory.list();
				if(entries!=null)
				{
					for(String s: entries){
					    File currentFile = new File(directory.getPath(),s);
					    currentFile.delete();
					}
				}
				directory.delete(); 	 
				message = "Admin Profile successfully deleted";	
			}
			
			else
			{
				message = "Admin Profile was not deleted";
			}
		}
	    	
		String address = "/Admin/HR/Manage_Employee.jsp";
	    request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
