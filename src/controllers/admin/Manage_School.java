package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Days;
import model.beans.admin.School_Details;
import model.beans.admin.Schools;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.GetDays;
import model.dbHelpers.admin.GetSchools;
import model.dbHelpers.admin.ScheduleManagement;
import model.dbHelpers.admin.SchoolManagement;

/**
 * Servlet implementation class Manage_School
 */
@WebServlet("/Manage_School")
public class Manage_School extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_School() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		int school_id = 0;
		if (request.getParameter("School_id")!=null)
		{
			school_id =Integer.parseInt(request.getParameter("School_id")) ;
		}
		
		String name = request.getParameter("name");
		int country_id = Integer.parseInt(request.getParameter("Country_id"));	
		String city = request.getParameter("City");
		int pO_Box = -1;
		if(request.getParameter("PO_Box")!=null && !request.getParameter("PO_Box").equals(""))
		{
			pO_Box = Integer.parseInt(request.getParameter("PO_Box"));	
		}				
		String full_Address = null;
		if(request.getParameter("Full_Address")!=null && !request.getParameter("Full_Address").equals(""))
		{
			full_Address = request.getParameter("Full_Address");	
		}
		String website = null;
		if(request.getParameter("Website")!=null && !request.getParameter("Website").equals(""))
		{
			website = request.getParameter("Website");	
		}
		String email = null;
		if(request.getParameter("Email")!=null && !request.getParameter("Email").equals(""))
		{
			email = request.getParameter("Email");	
		}	
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount((Integer) session.getAttribute("Admin_Session"));
		///////////////populate the bean ///////////////////////
		School_Details school_Details = new School_Details();
		school_Details.setName(name);
		school_Details.setCountry_id(country_id);
		school_Details.setCity(city);
		school_Details.setPO_Box(pO_Box);
		school_Details.setFull_Address(full_Address);
		school_Details.setWebsite(website);
		school_Details.setEmail(email);
		school_Details.setAdmin_id((Integer) session.getAttribute("Admin_Session"));
		school_Details.setAccount_id(account_id);
		///////////////end populate the bean ///////////////////////
		SchoolManagement schoolManagement = new SchoolManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (school_id==0)
			{
				
				updateQuery = schoolManagement.createSchool(school_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New School created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for School name. School not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New School was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }
				
			}
		    else
		    {
		    	updateQuery = schoolManagement.updateSchool(school_Details, school_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "School updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for School name. School not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New School was not created, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
	        if (updateQuery != 0 && updateQuery!=-1)
	        {	
	       
		    	///////////////get school_id by name //////////////////
		    	school_id = schoolManagement.getSchoolByName(name);
		    	//////////////////////////////////////////////////////
		    	/////////////////////////////Retrieve Days///////////////////////////////
		    	GetDays days = new GetDays();
		    	Days Days = new Days();
		    	Days = days.doGetDays();
		    	ArrayList<Integer> DaysIdList = Days.getDay_id();
		    	/////////////////////////////End retrieve Days///////////////////////////////
		    	String Day_id = null;
		    	for (Integer day_id : DaysIdList) 
		    	{
					Day_id = request.getParameter("Weekend"+day_id);
					
					if (Day_id!=null)
					{
						boolean weekendExist = false;
						weekendExist = schoolManagement.checkSchoolWeekEnd(Day_id, school_id);
						if (weekendExist==false)
						{
							schoolManagement.createSchoolWeekEnd(Day_id, school_id);
							ScheduleManagement scheduleManagement = new ScheduleManagement();
							scheduleManagement.deleteScheduleSessionsInWeekEnds(school_id, day_id);	
						}
					}
					else
					{
						//Delete
						Day_id  =request.getParameter("hiddenWeekend"+day_id);
						schoolManagement.deleteSchoolWeekEnd(Day_id, school_id);
					}
		    	}
	        }
		}
	        
		else
		{
			updateQuery = schoolManagement.deleteSchool(school_id, (Integer) session.getAttribute("Admin_Session"));
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "School successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this school. Only account owner is authorized to delete schools.";
			}
			else
			{
				message = "Something wrong happend. New School was not created, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve Schools///////////////////////////////
    	GetSchools schools = new GetSchools();
    	Schools Schools = new Schools();
    	Schools = schools.doGetSchools((Integer) session.getAttribute("Admin_Session"));
    	session.setAttribute("Schools", Schools);	 
    	/////////////////////////////End retrieve Schools///////////////////////////////
  	
	     String address = "/Admin/Schools/Manage_School.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
