package controllers.parent;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.admin.Schools;
import model.beans.parent.Child;
import model.beans.parent.Login;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.GetSchools;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.Parent.CheckLoginQuery;

/**
 * Servlet implementation class ParentLogin
 */
@WebServlet("/ParentLogin")
public class ParentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentLogin() {
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
       	int parent_id=0;
       	String message=null;
       	String address=null;
       	if(session.getAttribute("Parent_Session")!=null)
       	{    		
       		if(request.getParameter("username")==null&&request.getParameter("password")==null)
       		{
       			parent_id = (Integer)session.getAttribute("Parent_Session");
       		}
       		else
       		{
       			boolean loggedIn = true;
       			message="You are already logged in. Please log out and try again.";
       			message= message +"<br/>" +"<a href='"+request.getContextPath()+"/Parent/Parent_Signout.jsp'>Logout Here</a>";
       			address = "/Parent/Parent_Login.jsp?message="+message+"&&loggedIn="+loggedIn;
       	    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
       	    	dispatcher.forward(request, response);
       	    	return;
       		}
       	}      	
       	Account_Details account_Details = new Account_Details();
    	Login login = new Login();
       	if(parent_id!=0)
		{
			AccountManagement accountManagement = new AccountManagement();
			ParentManagement parentManagement = new ParentManagement();
			int account_id = parentManagement.getParentAccount(parent_id);
			account_Details = accountManagement.getAccountDetails(account_id);
			if(session.getAttribute("Parent_Account_Details")!=null)
			{
				Account_Details Parent_Account_Details = (Account_Details) session.getAttribute("Parent_Account_Details");
	        	account_Details.setParent_id(parent_id);
	        	account_Details.setAccount_id(account_id);
	        	account_Details.setFirst_Name(Parent_Account_Details.getFirst_Name());
				account_Details.setLast_Name(Parent_Account_Details.getLast_Name());
	        	account_Details.setFileName(Parent_Account_Details.getFileName());
	        	account_Details.setPassword(Parent_Account_Details.getPassword());
	        	account_Details.setParent_First_Login(Parent_Account_Details.getParent_First_Login());
			}
			else
			{
				account_Details.setFirst_Name("");
				account_Details.setLast_Name("");
			}
		}
		else
		{
			String username=null;
			String password = null;
			username = request.getParameter("username");
			password = request.getParameter("password");
	    	login.setUsername(username);
	    	login.setPassword(password);			
	    	CheckLoginQuery checkLogin = new CheckLoginQuery();	    	
	    	account_Details = checkLogin.doCheckLogin(login);
	    	parent_id = account_Details.getParent_id();
	    	Child child = account_Details.getChild(); 
	        session.setAttribute("child",child);
		}		
    	int subscribed = account_Details.getSubscribed();
		int activated=0;
		activated = account_Details.getActivated();
    	String url = request.getParameter("url");
		boolean trial_Valid = false;
		boolean subscription_Valid = false;
		if (parent_id == 0) 
		{
    		message="Login information is not correct. Please try again.";
    		address = "/Parent/Parent_Login.jsp?message="+message;
		} 
		else  
		{
			session.setAttribute("Parent_Session", parent_id); 
            if(activated==0)
    		{
    			message="Account is not activated. Please contact the account owner to activate the account";
    			address ="/Account/Display_Signin.jsp?message="+message+"&&User=Parent";
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
    						expiry_message = "<br/>We are still processing the subscription request.";
    						expiry_message = expiry_message + "Kindly check again within couple of minutes.";
    						expiry_message = expiry_message + "<br/>Thank you for your patience.";

    					}
    					else
    					{
    						expiry_message = "Trial Expired. Please contact the account owner to subscribe.";
    					}
        		    	address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Parent&&AgreementPending="+account_Details.isAgreementPending();
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
        					expiry_message = "Subscription period expired. Please contact the account owner to renew the subscription.";
    					}
    					address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Parent&&AgreementPending="+account_Details.isAgreementPending();   
    				}
    			}
    			
    			if(trial_Valid==true || subscription_Valid==true )
    			{
    				if(account_Details.getParent_First_Login()==1)
    				{
    					address = "/Parent/Profile/Change_Password.jsp";
    				}
    				else
    				{
    	 			    if(url!=null&& !url.equals("") &&!url.equals("/Parent/Parent_Login.jsp") &&!url.equals("/Parent/Parent_Signout.jsp"))
    	 			    {
    	 			    	address =  url;
    	 			    }
    	 			    else
    	 			    {
    	 			    	address = "/Parent/Parent_Main.jsp";
    	 			    }
    	 			    /////////////////////////Get Messages Count///////////////////////////////////////////
        				MessagesManagement messagesManagement = new MessagesManagement();
        	    		int newMessagesCount = messagesManagement.getNewMessagesCount(parent_id, "Parent");
        	    		session.setAttribute("newParentMessagesCount", newMessagesCount);
        	    		/////////////////////////////////////////////////////////////////////////////////////
        	    	    /////////////////////////////Retrieve Schools///////////////////////////////
        	        	GetSchools schools = new GetSchools();
        	        	Schools Schools = new Schools();
        	        	Schools = schools.doGetSchoolsByParent(parent_id);
        	        	session.setAttribute("Schools", Schools);	 
        	        	/////////////////////////////End retrieve Schools///////////////////////////////
    				}
    			}
    		}
			session.setAttribute("Parent_Account_Details", account_Details);
			session.setAttribute("Parent_Login", login);
		 }
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
