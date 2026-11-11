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

import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.AdminManagement;
import model.dbHelpers.admin.CheckLoginQuery;
import model.dbHelpers.admin.GetCountries;
import model.dbHelpers.admin.GetDays;
import model.dbHelpers.admin.GetSchools;
import model.dbHelpers.admin.MessagesManagement;
import model.beans.account.Account_Details;
import model.beans.admin.Countries;
import model.beans.admin.Days;
import model.beans.admin.Login;
import model.beans.admin.Schools;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
       	HttpSession session= request.getSession();
       	int admin_id=0;
    	String address=null;
       	String message=null;
       	if(session.getAttribute("Admin_Session")!=null)
       	{
       		if(request.getParameter("username")==null&&request.getParameter("password")==null)
       		{
           		admin_id = (Integer)session.getAttribute("Admin_Session");
       		}
       		else
       		{
       			admin_id = (Integer)session.getAttribute("Admin_Session");
       			boolean loggedIn = true;
       			message="You are already logged in. Please log out and try again.";
       			message= message +"<br/>" +"<a href='"+request.getContextPath()+"/Admin/Admin_Signout.jsp' style='color:red'>Logout Here</a>";
       			address = "/Admin/Admin_Login.jsp?message="+message+"&&loggedIn="+loggedIn;
       	    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
       	    	dispatcher.forward(request, response);
       	    	return;
       		}

       	}      	
       	Account_Details account_Details = new Account_Details();
		if(admin_id!=0)
		{
			AccountManagement accountManagement = new AccountManagement();
			int account_id = accountManagement.getAdminAccount(admin_id);
			account_Details = accountManagement.getAccountDetails(account_id);			
		}
		else
		{
			String username=null;
			String password = null;
			username = request.getParameter("username");
			password = request.getParameter("password");
	    	Login login = new Login();
	    	login.setUsername(username);
	    	login.setPassword(password);			
	    	CheckLoginQuery checkLogin = new CheckLoginQuery();	    	
	    	account_Details = checkLogin.doCheckLogin(login);
	    	admin_id = account_Details.getAdmin_id();
		}

    	int subscribed = account_Details.getSubscribed();
    	String url = request.getParameter("url");
    	boolean trial_Valid = false;
    	boolean subscription_Valid = false;
    	////////////////////////////////////
    	
    	int activated=0;  	
    	activated = account_Details.getActivated();
    	if(admin_id==0)
    	{
    		message="Login information is incorrect. Please try again.";
    		address = "/Admin/Admin_Login.jsp?message="+message;
    	}
    	else
    	{
            //session.setMaxInactiveInterval(60*60);
            session.setAttribute("Admin_Session", admin_id);
            if(activated==0)
    		{
    			message="Please activate your account";
    			address ="/Account/Display_Signin.jsp?message="+message+"&&User=Admin";
    		}
    		else
    		{ 
    			
    			String expiry_message=null;
    			if(subscribed==0)
    			{
    				////customer is in trial//////
    				if(account_Details.isTrial_Valid())
    				{
    					///Login as trial ///////////////////
    					trial_Valid = true;
    				}
    				else
    				{
    					//////////////////////////To open EduHappy for FREE ///////////////////////////////////
    					//subscription_Valid=true;
    					//account_Details.setSubscription_Valid(true);
    					///////////////////////////////////////////////////////////////////////////////////////
    					//////////First check if there is subscription request in progress/////////////////////
    					if(account_Details.isAgreementPending())
    					{
    						expiry_message = "<br/>We are still processing your subscription request.";
    						expiry_message = expiry_message + "Kindly check again within couple of minutes.";
    						expiry_message = expiry_message + "<br/>Thank you for your patience.";

    					}
    					else
    					{
    						expiry_message = "Your trial period expired. Please subscribe.";
    					}
        		    	address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Admin&&AgreementPending="+account_Details.isAgreementPending();
    				}
    			}
    			else
    			{
    				///user is subscribed////////////
    				if(account_Details.isSubscription_Valid())
    				{
    					subscription_Valid=true;
    				}
    				else
    				{
    					//////////First check if there is subscription request in progress/////////////////////
    					if(account_Details.isAgreementPending())
    					{
    						expiry_message = "<br/>We are still processing your subscription request.";
    						expiry_message = expiry_message + "Kindly check again within couple of minutes.";
    						expiry_message = expiry_message + "<br/>Thank you for your patience.";

    					}
    					else
    					{
        					////////////////////Subscription Expired, Please renew////////////
        					expiry_message = "Your subscription period expired. Please renew your subscription.";
    					}
    					address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Admin&&AgreementPending="+account_Details.isAgreementPending()+"&&Existing_Licenses="+account_Details.getNo_Of_Students();   
    				}
    			}
    			
    			if(trial_Valid==true || subscription_Valid==true )
    			{
    				//////////////////////////Retrieve the Admin photo //////////////////////////////////////    		
    				String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
    				File jsp = new File(appPath+"uploadFiles/Accounts/"+account_Details.getAccount_id()+"/Admins/Photos/"+account_Details.getAdmin_id());
    				File[] list = jsp.listFiles();
    				String fileName = null;
    				 if (list!=null)
    				 {
    					 for(int i=0;i<list.length;i++)
    					 {
    						 fileName= list[i].getName();
    					 }
    				 }	
    				 account_Details.setFileName(fileName);	
    				////////////////////////////////////////////////////////////////////////////////////////
    				
    				MessagesManagement messagesManagement = new MessagesManagement();
    	    		int newMessagesCount = messagesManagement.getNewMessagesCount(admin_id, "Admin");
    	    		session.setAttribute("newAdminMessagesCount", newMessagesCount);
    	    	    /////////////////////////////Retrieve Schools///////////////////////////////
    	        	GetSchools schools = new GetSchools();
    	        	Schools Schools = new Schools();
    	        	Schools = schools.doGetSchools(admin_id);
    	        	session.setAttribute("Schools", Schools);	 
    	        	/////////////////////////////End retrieve Schools///////////////////////////////
    	        	
    	        	/////////////////////////////Retrieve Countries///////////////////////////////
    	        	GetCountries countries = new GetCountries();
    	        	Countries Countries = new Countries();
    	        	Countries = countries.doGetCountries();
    	        	//getServletContext().setAttribute("Countries", Countries);
    	        	session.setAttribute("Countries", Countries);
    	        	//////////////////////////////////////////////////////////
    	        	GetDays days = new GetDays();
    	        	Days Days = new Days();
    	        	Days = days.doGetDays();
    	        	//getServletContext().setAttribute("Days", Days);
    	        	session.setAttribute("Days", Days);
    	        	/////////////////////////////End retrieve Days///////////////////////////////
    	        	/////////////////////////////Get Admin Privileges////////////////////////////////////////////////////////////////////////////
    	        	AdminManagement adminManagement = new AdminManagement();
    	        	ArrayList<String> AdminPrivileges = new ArrayList<String>();
    	        	AdminPrivileges = adminManagement.getAdminPrivileges(admin_id);
    	        	session.setAttribute("AdminPrivileges", AdminPrivileges);
    	        	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    			    if(url!=null&& !url.equals("") &&!url.equals("/Admin/Admin_Login.jsp") &&!url.equals("/Admin/Admin_Signout.jsp"))
    			    {
    			    	address =  url;
    			    }
    			    else
    			    {
    			    	address = "/Admin/Admin_Main.jsp";
    			    }
    			}
    		}
			session.setAttribute("Account_Details", account_Details);
    	}
    	///////////////////////////////////
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}