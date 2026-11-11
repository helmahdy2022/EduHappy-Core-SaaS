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

import model.beans.admin.Schools;
import model.beans.admin.Teacher_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.GetSchools;
import model.dbHelpers.admin.TeacherManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Teacher
 */
@WebServlet("/Manage_Teacher")
public class Manage_Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		
		int teacher_id = 0;
		if (request.getParameter("Teacher_id")!=null)
		{
			teacher_id =Integer.parseInt(request.getParameter("Teacher_id")) ;
		}
		/////////////////////////////////Employee Details to be inserted in Employee table/////////////////////////////////////
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");	
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);	
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

		///////////////populate the bean ///////////////////////
		Teacher_Details teacher_Details = new Teacher_Details();
		teacher_Details.setAccount_id(account_id);
		teacher_Details.setPayroll_id(payroll_id);
		teacher_Details.setDepartment_id(department_id);
		teacher_Details.setDesignation(designation);
		teacher_Details.setJoining_Date(joining_Date);
		teacher_Details.setInitial_Basic(initial_Basic);
		teacher_Details.setTeacher_id(teacher_id);
		teacher_Details.setFirst_Name(first_Name);
		teacher_Details.setLast_Name(last_Name);
		teacher_Details.setCountry_id(country_id);
		teacher_Details.setCity(city);
		teacher_Details.setPO_Box(pO_Box);
		teacher_Details.setFull_Address(full_Address);
		teacher_Details.setGender(gender);
		teacher_Details.setEmail(email);
		teacher_Details.setTel(tel);
		teacher_Details.setMobile(mobile);
		teacher_Details.setBirth_Date(birth_Date);
		teacher_Details.setUsername(username);
		teacher_Details.setPassword(password);
		
		///////////////end populate the bean ///////////////////////
		TeacherManagement teacherManagement = new TeacherManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (teacher_id==0)
			{		        		        
    			autoIncValue = teacherManagement.createTeacher(teacher_Details);
		        if (autoIncValue!=-1 && autoIncValue!=-2)
		        {
		        	message = "New Teacher Profile created successfully";
		        	teacher_id = autoIncValue;
		        }
		        else
		        {
		        	if(autoIncValue==-2)
		        	{
		        		message = "Duplicate Entry for the Teacher Username. Teacher Profile was not created";
		        	}
		        	else
		        	{
		        		message = "Failed to create New Teacher Profile";
		        	}		        	
		        }
		        
				
			}
		    else
		    {
		    	updateQuery = teacherManagement.updateTeacher(teacher_Details, teacher_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Teacher Profile updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Teacher username. Teacher Profile was not updated";
		        	}
		        	else
		        	{
		        		message = "Failed to update New Teacher Profile";
		        	}
		        	
		        }
		    	
		    }

	        if (updateQuery != 0 || (autoIncValue!=-1 && autoIncValue!=-2))
	        {	
		    	///////////////get teacher_id by username //////////////////
		    	//teacher_id = teacherManagement.getTeacherByUsername(username);
		    	//////////////////////////////////////////////////////
		    	/////////////////////////////Retrieve Schools///////////////////////////////
		    	GetSchools schools = new GetSchools();
		    	Schools Schools = new Schools();
		    	Schools = schools.doGetSchools(admin_id);
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
						boolean teacherSchoolExist = false;
						teacherSchoolExist = teacherManagement.checkTeacherSchool(School_id, teacher_id);
						if (teacherSchoolExist==false)
						{
							teacherManagement.createTeacherSchool(School_id, teacher_id);
	
						}
					}
					else
					{
						//Delete
						School_id  = Integer.parseInt(request.getParameter("hiddenschool"+school_id));
						teacherManagement.deleteTeacherSchool(School_id, teacher_id);
					}
		    	}
	        
		    	///////////////////////////deal with file upload here////////////////////////////////////////////////
		    	
		    	try {
					uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, -1 , "Teachers","Photos", "Teacher_id", null, teacher_id, -1, items, totalFilesSize, fileSize, fileQty);
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
			updateQuery = teacherManagement.deleteTeacher(teacher_id);
			if (updateQuery!=0)
			{
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Teachers/Photos/" +teacher_id);
				String[]entries = directory.list();
				if(entries!=null)
				{
					for(String s: entries){
					    File currentFile = new File(directory.getPath(),s);
					    currentFile.delete();
					}
				}
				directory.delete(); 	 
				message = "Teacher Profile successfully deleted";	
			}
			
			else
			{
				message = "Teacher Profile was not deleted";
			}
		}
	    	
		String address = "/Admin/HR/Manage_Employee.jsp";
	    request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
