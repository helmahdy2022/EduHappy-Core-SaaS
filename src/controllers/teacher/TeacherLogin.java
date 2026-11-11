package controllers.teacher;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.teacher.Login;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.TeacherManagement;
import model.dbHelpers.teacher.CheckLoginQuery;

/**
 * Servlet implementation class TeacherLogin
 */
@WebServlet("/TeacherLogin")
public class TeacherLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherLogin() {
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
       	int teacher_id=0;
       	String message=null;
       	String address=null;
       	if(session.getAttribute("Teacher_Session")!=null)
       	{  		
       		if(request.getParameter("username")==null&&request.getParameter("password")==null)
       		{
       			teacher_id = (Integer)session.getAttribute("Teacher_Session");
       		}
       		else
       		{
       			boolean loggedIn = true;
       			message="You are already logged in. Please log out and try again.";
       			message= message +"<br/>" +"<a href='"+request.getContextPath()+"/Teacher/Teacher_Signout.jsp'>Logout Here</a>";
       			address = "/Teacher/Teacher_Login.jsp?message="+message+"&&loggedIn="+loggedIn;
       	    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
       	    	dispatcher.forward(request, response);
       	    	return;
       		}
       	}      	
       	Account_Details account_Details = new Account_Details();
		if(teacher_id!=0)
		{
			AccountManagement accountManagement = new AccountManagement();
			TeacherManagement teacherManagement = new TeacherManagement();
        	int admin_id = teacherManagement.getTeacherAdmin(teacher_id);
			int account_id = accountManagement.getAdminAccount(admin_id);
			account_Details = accountManagement.getAccountDetails(account_id);
			if(session.getAttribute("Teacher_Account_Details")!=null)
			{
				Account_Details Teacher_Account_Details = (Account_Details) session.getAttribute("Teacher_Account_Details");
	        	account_Details.setTeacher_id(teacher_id);
	        	account_Details.setEmployee_id(Teacher_Account_Details.getEmployee_id());
	        	account_Details.setFirst_Name(Teacher_Account_Details.getFirst_Name());
				account_Details.setLast_Name(Teacher_Account_Details.getLast_Name());
	        	account_Details.setFileName(Teacher_Account_Details.getFileName());
	        	account_Details.setPassword(Teacher_Account_Details.getPassword());
	        	account_Details.setTeacher_First_Login(Teacher_Account_Details.getTeacher_First_Login());
	        	account_Details.setAccount_id(account_id);
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
	    	Login login = new Login();
	    	login.setUsername(username);
	    	login.setPassword(password);			
	    	CheckLoginQuery checkLogin = new CheckLoginQuery();	    	
	    	account_Details = checkLogin.doCheckLogin(login);
	    	teacher_id = account_Details.getTeacher_id();
		}

    	int subscribed = account_Details.getSubscribed();
		int activated=0;
		activated = account_Details.getActivated();
		String url = request.getParameter("url");
	    boolean trial_Valid = false;
	    boolean subscription_Valid = false;		
		if (teacher_id == 0) 
		{
    		message="Login information is not correct. Please try again.";
    		address = "/Teacher/Teacher_Login.jsp?message="+message;
		} 
		else  
		{
			session.setAttribute("Teacher_Session", teacher_id);  
			if(activated==0)
    		{
    			message="Account is not activated. Please contact the account owner to activate the account";
    			address ="/Account/Display_Signin.jsp?message="+message+"&&User=Teacher";
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
        		    	address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Teacher&&AgreementPending="+account_Details.isAgreementPending();
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
    					address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Teacher&&AgreementPending="+account_Details.isAgreementPending();   
    				}
    			}
    			
    			if(trial_Valid==true || subscription_Valid==true )
    			{
    				if(account_Details.getTeacher_First_Login()==1)
    				{
    					address = "/Teacher/Profile/Change_Password.jsp?First_Login=1";
    				}
    				else
    				{
        				address = "/Teacher_Authority?url="+url;
    				}
    			}
    			session.setAttribute("Teacher_Account_Details", account_Details);
    		}	 
		 }
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);

	}

}
