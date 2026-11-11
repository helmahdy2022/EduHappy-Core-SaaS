package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Parent_Details;
import model.dbHelpers.admin.ParentManagement;


/**
 * Servlet implementation class Manage_Parent_Account
 */
@WebServlet("/Manage_Parent_Account")
public class Manage_Parent_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Parent_Account() {
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
		//int admin_id = 144;	
		//AccountManagement accountManagement = new AccountManagement();
		int account_id = 258; //accountManagement.getAdminAccount(admin_id);	
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
			String email = null;
			if(request.getParameter("Email")!=null && !request.getParameter("Email").equals(""))
			{
				email = request.getParameter("Email");
			}	
			String mobile = null;
			if(request.getParameter("Mobile")!=null && !request.getParameter("Mobile").equals(""))
			{
				mobile = request.getParameter("Mobile");
			}
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");
			///////////////populate the bean ///////////////////////
			Parent_Details parent_Details = new Parent_Details();
			parent_Details.setFirst_Name(first_Name);
			parent_Details.setLast_Name(last_Name);
			parent_Details.setCountry_id(country_id);
			parent_Details.setCity(city);
			parent_Details.setEmail(email);
			parent_Details.setMobile(mobile);
			parent_Details.setUsername(username);
			parent_Details.setPassword(password);
			parent_Details.setAccount_id(account_id);
			///////////Get base URL //////////
			StringBuffer url = request.getRequestURL();
			if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
			{
				url.replace(0, 4, "https");
			}
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length());
			String partial_base = url.substring(0, url.length() - uri.length());
			//////////////////////////////////
			///////////////end populate the bean ///////////////////////
    		if (parent_id==0)
			{
    			int autoIncValue = parentManagement.createParentAccount(parent_Details, base, partial_base);
		        if (autoIncValue!=-1 && autoIncValue!=-2)
		        {
		        	message = "New Parent account created successfully. Please check your email to activate your account.<br/>Note that some email systems may mistakenly mark this email as Junk Email so please check your Junk Email as well";
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
		}
		else
		{
			updateQuery = parentManagement.deleteParent(parent_id);
			if (updateQuery!=0)
			{				
				message = "Parent Profile successfully deleted";	
			}
				
			else
			{
				message = "Failed to delete Parent Profile";
			}
		}

	    String address = "/Academy/Manage_Parent_Account.jsp";
	    request.setAttribute("message", message);
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		}

}
