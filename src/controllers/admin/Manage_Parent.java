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
import model.beans.admin.Parent_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Parent
 */
@WebServlet("/Manage_Parent")
public class Manage_Parent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Parent() {
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
		
		
        @SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");	
		ArrayList<String> uploadMessage = new ArrayList<String>();
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");	
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);	
		int parent_id=0;
		if(request.getParameter("Parent_id")!=null)
		{
			parent_id = Integer.parseInt(request.getParameter("Parent_id"));
		}

		String message = null;

		ParentManagement parentManagement = new ParentManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		
		if(ConfirmDelete==null)
		{
			
			String first_Name = request.getParameter("First_Name");
			String last_Name = request.getParameter("Last_Name");	
			int country_id = 0;
			if(request.getParameter("Country_id")!=null && !request.getParameter("Country_id").equals(""))
			{
				country_id = Integer.parseInt(request.getParameter("Country_id"));
			}
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
			Parent_Details parent_Details = new Parent_Details();
			parent_Details.setFirst_Name(first_Name);
			parent_Details.setLast_Name(last_Name);
			parent_Details.setCountry_id(country_id);
			parent_Details.setCity(city);
			parent_Details.setPO_Box(pO_Box);
			parent_Details.setFull_Address(full_Address);
			parent_Details.setGender(gender);
			parent_Details.setEmail(email);
			parent_Details.setTel(tel);
			parent_Details.setMobile(mobile);
			parent_Details.setBirth_Date(birth_Date);
			parent_Details.setUsername(username);
			parent_Details.setPassword(password);
			parent_Details.setAccount_id(account_id);
			///////////////end populate the bean ///////////////////////
    		if (parent_id==0)
			{
    			parent_Details.setFirst_Login(1);
    			int autoIncValue = parentManagement.createParent(parent_Details);
		        if (autoIncValue!=-1 && autoIncValue!=-2)
		        {
		        	message = "New Parent Profile created successfully";
		        	parent_id = autoIncValue;
		        }
		        else
		        {
		        	if(autoIncValue==-2)
		        	{
		        		message = "Duplicate Entry for the Parent Username. Parent Profile was not created";
		        	}
		        	else
		        	{
		        		message = "Failed to create new Parent Profile";
		        	}		        	
		        }   			
			}
			else
			{
				updateQuery = parentManagement.updateParent(parent_Details, parent_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Parent Profile updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Parent Username. Parent was not updated";
		        	}
		        	else
		        	{
		        		message = "Failed to update Parent Profile";
		        	}	
		    	}    	
			}
			
    		///////////////////////////deal with file upload here////////////////////////////////////////////////
    		if(parent_id!=0 && parent_id!=-1)
    		{			    		

    			try {				
    				uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, -1, "Parents","Photos", "Parent_id", null, parent_id, -1, items, totalFilesSize, fileSize, fileQty);
 
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
			updateQuery = parentManagement.deleteParent(parent_id);
			if (updateQuery!=0)
			{				
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Parents/Photos/" +parent_id);
				String[]entries = directory.list();
				if(entries!=null)
				{
					for(String s: entries){
					    File currentFile = new File(directory.getPath(),s);
					    currentFile.delete();
					}
				}
				directory.delete(); 	 
				message = "Parent Profile successfully deleted";	
			}
				
			else
			{
				message = "Failed to delete Parent Profile";
			}
		}

	    String address = "/Admin/Parents/Manage_Parent.jsp";
	    request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);

		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	
	}
}
