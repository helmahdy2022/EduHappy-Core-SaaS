package controllers.student;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.student.Login;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.CheckLoginQuery;


/**
 * Servlet implementation class StudentLogin
 */
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLogin() {
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
       	int student_id=0;
       	String message=null;
       	String address=null;
       	if(session.getAttribute("Student_Session")!=null)
       	{    		
       		if(request.getParameter("username")==null&&request.getParameter("password")==null)
       		{
       			student_id = (Integer)session.getAttribute("Student_Session");
       		}
       		else
       		{
       			boolean loggedIn = true;
       			message="You are already logged in. Please log out and try again.";
       			message= message +"<br/>" +"<a href='"+request.getContextPath()+"/Student/Student_Signout.jsp'>Logout Here</a>";
       			address = "/Student/Student_Login.jsp?message="+message+"&&loggedIn="+loggedIn;
       	    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
       	    	dispatcher.forward(request, response);
       	    	return;
       		}
       	}      	
       	Account_Details account_Details = new Account_Details();
		if(student_id!=0)
		{
			AccountManagement accountManagement = new AccountManagement();
			StudentManagement studentManagement = new StudentManagement();
        	int admin_id = studentManagement.getStudentAdmin(student_id);
			int account_id = accountManagement.getAdminAccount(admin_id);
			account_Details = accountManagement.getAccountDetails(account_id);
			if(session.getAttribute("Student_Account_Details")!=null)
			{
				Account_Details Student_Account_Details = (Account_Details) session.getAttribute("Student_Account_Details");
	        	account_Details.setStudent_id(student_id);
	        	account_Details.setFirst_Name(Student_Account_Details.getFirst_Name());
				account_Details.setLast_Name(Student_Account_Details.getLast_Name());
	        	account_Details.setFileName(Student_Account_Details.getFileName());
	        	account_Details.setPassword(Student_Account_Details.getPassword());
	        	account_Details.setStudent_First_Login(Student_Account_Details.getStudent_First_Login());
	        	account_Details.setAccount_id(account_id);
	        	account_Details.setSchool_id(Student_Account_Details.getSchool_id());
	        	account_Details.setStudent_Promoted(Student_Account_Details.isStudent_Promoted());
	        	account_Details.setYear_Name(Student_Account_Details.getYear_Name());
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
	    	student_id = account_Details.getStudent_id();
		}		
    	int subscribed = account_Details.getSubscribed();
		int activated=0;
		activated = account_Details.getActivated();
    	String url = request.getParameter("url");
		boolean trial_Valid = false;
		boolean subscription_Valid = false;
		if (student_id == 0) 
		{
    		message="Login information is not correct. Please try again.";
    		address = "/Student/Student_Login.jsp?message="+message;
		} 
		else  
		{
			session.setAttribute("Student_Session", student_id); 
            if(activated==0)
    		{
    			message="Account is not activated. Please contact the account owner to activate the account";
    			address ="/Account/Display_Signin.jsp?message="+message+"&&User=Student";
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
        		    	address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Student&&AgreementPending="+account_Details.isAgreementPending();
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
    					address ="/Account/Subscription.jsp?expiry_message="+expiry_message+"&&User=Student&&AgreementPending="+account_Details.isAgreementPending();   
    				}
    			}
    			
    			if(trial_Valid==true || subscription_Valid==true )
    			{
    				if(account_Details.getStudent_First_Login()==1)
    				{
    					address = "/Student/Profile/Change_Password.jsp";
    				}
    				else
    				{
    	 			    if(url!=null&& !url.equals("") &&!url.equals("/Student/Student_Login.jsp") &&!url.equals("/Student/Student_Signout.jsp"))
    	 			    {
    	 			    	address =  url;
    	 			    }
    	 			    else
    	 			    {
    	 			    	address = "/Student/Student_Main.jsp";
    	 			    }
    	 			    /////////////////////////Get Messages Count///////////////////////////////////////////
        				MessagesManagement messagesManagement = new MessagesManagement();
        	    		int newMessagesCount = messagesManagement.getNewMessagesCount(student_id, "Student");
        	    		session.setAttribute("newStudentMessagesCount", newMessagesCount);
        	    		/////////////////////////////////////////////////////////////////////////////////////
    				}
    			}
    		}
			session.setAttribute("Student_Account_Details", account_Details);	 
		 }
		 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);

	}
}
