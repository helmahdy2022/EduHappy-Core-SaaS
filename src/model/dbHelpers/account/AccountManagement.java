package model.dbHelpers.account;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.account.Account_Details;
import model.dbHelpers.DbConnector;

public class AccountManagement {

	public String createAccount(Account_Details account_Details, String base, String partial_base) throws UnsupportedEncodingException{
		String account = "INSERT INTO Accounts (account_id, Organization, Activation_Key, Trial_Days) VALUES (NULL,?,?,?)";
		String admin = "INSERT INTO admins (admin_id, account_id, first_name, last_name, username, password, account_owner, Country_id, mobile) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		int autoIncValue = -1;
		String message="Oops..Somthing worng happened during the account creation. Please try again later";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatementAccount = connection.prepareStatement(account, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstatementAdmin = connection.prepareStatement(admin);
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInStudent = connection.createStatement();){

		    String sUserExistInTeacher = "select * from Teachers where Username='"+account_Details.getEmail()+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

		    String sUserExistInStudent = "select * from Students where Username='"+account_Details.getEmail()+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);
		    
		    if(resultUserExistInTeacher.isBeforeFirst() || resultUserExistInStudent.isBeforeFirst())
		    {
		    	message="Error: Duplicate Email/Username record. Please choose another Email/Username";	
		    	return message;
		    }
			String Activation_Key = UUID.randomUUID().toString();
			connection.setAutoCommit(false);
			pstatementAccount.setString(1, account_Details.getOrganization());	
			pstatementAccount.setString(2, Activation_Key);
			pstatementAccount.setInt(3, 7);
			updateQuery = pstatementAccount.executeUpdate();

			if(updateQuery!=0)
			{
				ResultSet resultAccount_id = pstatementAccount.getGeneratedKeys();
				if(resultAccount_id.next()) 
				{
				       autoIncValue = resultAccount_id.getInt(1);
				       resultAccount_id.close();
				}
				pstatementAdmin.setInt(1, autoIncValue);
				pstatementAdmin.setString(2, account_Details.getFirst_Name());
				pstatementAdmin.setString(3, account_Details.getLast_Name());
				pstatementAdmin.setString(4, account_Details.getEmail());
				pstatementAdmin.setString(5, account_Details.getPassword());
				pstatementAdmin.setInt(6, 1);
				pstatementAdmin.setInt(7, account_Details.getCountry_id());	
				pstatementAdmin.setString(8, account_Details.getMobile());
				
				updateQuery = pstatementAdmin.executeUpdate();	
				if(updateQuery!=0)
				{
					String msg_subject = "Your EduHappy account";
					String msg_body = "EduHappy Account<br/><br/>";
					msg_body = msg_body+ "Hi "+account_Details.getFirst_Name()+"<br/><br/>";
					msg_body = msg_body+ "Welcome to EduHappy, The Enterprise School Management System that helps automate all your education processes and deliver high quality learning experience.<br/><br/>";
					msg_body = msg_body+ "A 7-day trial account has been created for you with full access to EduHappy system.<br/><br/>";
					//msg_body = msg_body+ "EduHappy is now 100% FREE so you can enjoy lifetime full access to the system compeletely free.<br/><br/>";
					msg_body = msg_body+ "Login Details:<br/><br/>";
					msg_body = msg_body+ "Username: "+account_Details.getEmail()+"<br/><br/>";
					msg_body = msg_body+ "<a href='"+base+"/Activate_Account?Activation_Key="+Activation_Key+"'>Please click here to activate your account and start your FREE trial period</a><br/><br/>";
					//msg_body = msg_body+ "<a href='"+base+"/Activate_Account?Activation_Key="+Activation_Key+"'>Please click here to activate your account and start your FREE access to the full features of the system</a><br/><br/>";
					msg_body = msg_body+ "After you activate your profile, you can access the School Admin system through the following link: <a href='"+partial_base+"/admin'>"+partial_base+"/admin</a><br/><br/>";
					msg_body = msg_body+ "After you create teacher profile, you can access the Teacher system through the following link: <a href='"+partial_base+"/teacher'>"+partial_base+"/teacher</a><br/><br/>";
					msg_body = msg_body+ "After you create student profile, you can access the Student Portal through the following link: <a href='"+partial_base+"/student'>"+partial_base+"/student</a><br/><br/>";
					msg_body = msg_body+ "After you create parent profile, you can access the Parent Portal through the following link: <a href='"+partial_base+"/parent'>"+partial_base+"/parent</a><br/><br/>";
					msg_body = msg_body+ "If you need any help, please contact us at info@edu-happy.com.<br/><br/>";
					msg_body = msg_body+ "Best of Luck,<br/><br/>";
					msg_body = msg_body+ "EduHappy Team<br/><br/>";
					msg_body = msg_body+ "<a href='https://www.edu-happy.com'>www.edu-happy.com</a><br/><br/>";
					msg_body = msg_body+ "Email: info@edu-happy.com";
					EmailSender emailsender = new EmailSender();
					int message_sent = emailsender.sendemail(account_Details.getEmail(), msg_subject, msg_body);
					if(message_sent == 1)
					{
						connection.commit();
						message = "Account created successfully. Please check your email to activate your account.<br/>Note that some email systems may mistakenly mark this email as Junk Email so please check your Junk Email as well";
					}
					else
					{
						message = "Error sending email. Failed to create the new account";
						connection.rollback();
					}
				}
				else
				{
					connection.rollback();
					message = "Error creating the new account";
				}
			}
			
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Organization"))
			{
				message="Error: Same Organization name is already exist. Please choose another Organization name.";	
			}
			
			else if(ee.toString().contains("Username"))
			{
				message="Error: Duplicate Email/Username record. Please choose another Email/Username";	
			}
			else
			{
				ee.printStackTrace();
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public String updateAccount(Account_Details account_Details) {
		String sEmployee = "INSERT INTO Employees (Employee_id, Account_id, Payroll_id, Department_id, Employee_Group, Designation, Joining_Date, Initial_Basic) VALUES (NULL,?, ?, ?, ?, ?, ?, ?)";
		String sUpdateAdminEmployeeID = "update admins set Employee_id=? where admin_id="+account_Details.getAdmin_id();
		String sUpdateAccount = "update Accounts set Organization=? where account_id="+account_Details.getAccount_id();
		String sUpdateAdmin = "update admins set first_name=?, last_name=?, PO_Box=?, full_address=?, Gender=?,  password=?, Country_id=?, City=?, Tel=?, Mobile=?, Birth_Date=? where account_id="+account_Details.getAccount_id()+" and admin_id="+account_Details.getAdmin_id();
		String sUpdateEmployee = "update Employees set Payroll_id=?, Department_id=?, Designation=?, Joining_Date=?, Initial_Basic=? where Employee_id=(Select Employee_id from Admins where Admin_id="+account_Details.getAdmin_id()+")";
		int updateQuery = 0;
		int updateQueryEmployee=0;
		int employee_id=-1;
		String message="Oops..Somthing worng happened during the account creation. Please try again later";
		try (Connection connection = DbConnector.connectToDb();
			Statement statementAdminEmpID = connection.createStatement();
			PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pstatementUpdateEmployee = connection.prepareStatement(sUpdateEmployee);
			PreparedStatement pstatementAdminEmployee = connection.prepareStatement(sUpdateAdminEmployeeID);
			PreparedStatement pstatementAccount = connection.prepareStatement(sUpdateAccount);
			PreparedStatement pstatementAdmin = connection.prepareStatement(sUpdateAdmin);){

			/////////////Check if the Admin has Employee_id///////////////////////////////////////////////////////////
			String sAdminEmpID = "select Employee_id from Admins where Admin_id="+account_Details.getAdmin_id();
			ResultSet resultAdminEmpID = statementAdminEmpID.executeQuery(sAdminEmpID);
			while(resultAdminEmpID.next())
			{
				if(resultAdminEmpID.getString("Employee_id")==null)
				{
					////////Create Employee record///////////////////////
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date dJoining_Date=null;
					try {
						dJoining_Date = formater.parse(account_Details.getJoining_Date());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					pstatementEmployee.setInt(1, account_Details.getAccount_id());
					if(account_Details.getPayroll_id()!=0)
					{
						pstatementEmployee.setInt(2, account_Details.getPayroll_id());
					}
					else
					{
						pstatementEmployee.setNull(2, java.sql.Types.NULL);
					}
					if(account_Details.getDepartment_id()!=0)
					{
						pstatementEmployee.setInt(3, account_Details.getDepartment_id());
					}
					else
					{
						pstatementEmployee.setNull(3, java.sql.Types.NULL);
					}				
					pstatementEmployee.setString(4, "Admin");
					pstatementEmployee.setString(5, account_Details.getDesignation());
					pstatementEmployee.setDate(6, new java.sql.Date(dJoining_Date.getTime()));
					pstatementEmployee.setDouble(7, account_Details.getInitial_Basic());
					updateQueryEmployee = pstatementEmployee.executeUpdate();
					if(updateQueryEmployee!=0)
					{
						ResultSet resultEmployee_id = pstatementEmployee.getGeneratedKeys();
						if(resultEmployee_id.next()) 
						{
							employee_id = resultEmployee_id.getInt(1);
							resultEmployee_id.close();
						}
						///////////////////Update Admin record with the newly created Empoyee_id/////////////////////////
						pstatementAdminEmployee.setInt(1, employee_id);
						pstatementAdminEmployee.executeUpdate();
					}   				
				}
				else
				{
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date dJoining_Date=null;
					try {
						if(account_Details.getJoining_Date()!=null)
						{
							dJoining_Date = formater.parse(account_Details.getJoining_Date());
						}						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					if(account_Details.getPayroll_id()!=0)
					{
						pstatementUpdateEmployee.setInt(1, account_Details.getPayroll_id());
					}
					else
					{
						pstatementUpdateEmployee.setNull(1, java.sql.Types.NULL);
					}
					if(account_Details.getDepartment_id()!=0)
					{
						pstatementUpdateEmployee.setInt(2, account_Details.getDepartment_id());
					}
					else
					{
						pstatementUpdateEmployee.setNull(2, java.sql.Types.NULL);
					}
					
					pstatementUpdateEmployee.setString(3, account_Details.getDesignation());
					pstatementUpdateEmployee.setDate(4, new java.sql.Date(dJoining_Date.getTime()));
					pstatementUpdateEmployee.setDouble(5, account_Details.getInitial_Basic());
					updateQueryEmployee = pstatementUpdateEmployee.executeUpdate();
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			pstatementAccount.setString(1, account_Details.getOrganization());		
			updateQuery = pstatementAccount.executeUpdate();
			if(updateQuery!=0)
			{
				pstatementAdmin.setString(1, account_Details.getFirst_Name());
				pstatementAdmin.setString(2, account_Details.getLast_Name());				
				if(account_Details.getPO_Box()!=0)
				{
					pstatementAdmin.setInt(3, account_Details.getPO_Box());
				}
				else
				{
					pstatementAdmin.setNull(3, java.sql.Types.NULL);
				}
				pstatementAdmin.setString(4, account_Details.getFull_Address());
				pstatementAdmin.setString(5, account_Details.getGender());			
				pstatementAdmin.setString(6, account_Details.getPassword());
				pstatementAdmin.setInt(7, account_Details.getCountry_id());	
				if(account_Details.getCity()!=null)
				{
					pstatementAdmin.setString(8, account_Details.getCity());
				}
				else
				{
					pstatementAdmin.setNull(8, java.sql.Types.NULL);
				}	
				if(account_Details.getTel()!=null)
				{
					pstatementAdmin.setString(9, account_Details.getTel());	
				}
				else
				{
					pstatementAdmin.setNull(9, java.sql.Types.NULL);
				}
				if(account_Details.getMobile()!=null)
				{
					pstatementAdmin.setString(10, account_Details.getMobile());	
				}
				else
				{
					pstatementAdmin.setNull(10, java.sql.Types.NULL);
				}
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dBirth_Date=null;
				try {
					dBirth_Date = formater.parse(account_Details.getBirth_Date());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				if(account_Details.getBirth_Date()!=null)
				{
					pstatementAdmin.setDate(11, new java.sql.Date(dBirth_Date.getTime()));	
				}
				else
				{
					pstatementAdmin.setNull(11, java.sql.Types.NULL);
				}
				updateQuery = pstatementAdmin.executeUpdate();	
				if(updateQuery!=0)
				{
					message="Account Profile updated successfully";
				}
				else
				{
					message="Failed to update Account Profile";
				}
			}			
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Organization"))
			{
				message="Error: Same Organization name is already exist. Please choose another Organization name.";	
			}
			else
			{
				ee.printStackTrace();
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
public String activateAccount(String Activation_Key, String base) {
	String sAccount = "Select Activated from Accounts where Activation_Key='"+Activation_Key+"'";
	String sActivateAccount = "update accounts set Activated=?, Trial_Date=? where Activation_Key='"+Activation_Key+"'";
	int updateQuery = 0;
	String message=null;
	try (Connection connection = DbConnector.connectToDb();
			PreparedStatement pstatement = connection.prepareStatement(sActivateAccount);
			Statement statementAccount = connection.createStatement();){
		ResultSet resultAccount = statementAccount.executeQuery(sAccount);
		while(resultAccount.next())
		{
			if(resultAccount.getInt("Activated")==0)
			{
				Date today = new Date();
				pstatement.setInt(1, 1);
				pstatement.setDate(2, new java.sql.Date(today.getTime()));	
				updateQuery = pstatement.executeUpdate();
				if(updateQuery!=0)
				{
					message="Account has been successfully activated.<br/> <a href='"+base+"/Admin/Admin_Login.jsp' style='color:red'>Please Login</a>";
				}
				else
				{
					message="Account activation failed.";
				}
			}
			else
			{
				message="This Account is already activated.";
			}
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}

public Account_Details getAccountDetails(int account_id) {
	Date date = new Date();
	java.sql.Date today = new java.sql.Date(date.getTime());
	String sAccount = "select * from accounts where account_id = "+account_id;
	String sGraduatedStudents = "select count(*) from student_grade_year where Graduated=1 and school_id in(select school_id from school where account_id="+account_id+")";
	String sAdmin = "select * from admins where account_id = "+account_id+" and account_owner=1";
	String sAgreement = "select * from Account_Payment where account_id = "+account_id+" and (state='Active' or (state='Cancelled' and expiry_date>='"+today+"'))";
	String sAgreementPending = "select * from Account_Agreement where account_id = "+account_id+" and (state='Pending' or state='Updating')";
	String sAgreementCancelling = "select * from Account_Agreement where account_id = "+account_id+" and state='Cancelling'";
	Account_Details account_Details = new Account_Details();
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAccount = connection.createStatement();
			Statement statementGraduatedStudents = connection.createStatement();
			Statement statementAdmin = connection.createStatement();
			Statement statementEmployee = connection.createStatement();
			Statement statementAgreement = connection.createStatement();
			Statement statementAgreementPending = connection.createStatement();
			Statement statementAgreementCancelling = connection.createStatement();){
		ResultSet resultsetAccount = statementAccount.executeQuery(sAccount);
		ResultSet resultsetGraduatedStudents = statementGraduatedStudents.executeQuery(sGraduatedStudents);
		int no_Of_Graduated_Students = 0;
		while(resultsetAccount.next())
		{
			while(resultsetGraduatedStudents.next())
			{
				no_Of_Graduated_Students = resultsetGraduatedStudents.getInt(1);
			}
			
			int no_Of_Created_Students = resultsetAccount.getInt("No_Of_Created_Students") - no_Of_Graduated_Students;
			
			account_Details.setAccount_id(resultsetAccount.getInt("account_id"));
			account_Details.setActivated(resultsetAccount.getInt("Activated"));
        	account_Details.setOrganization(resultsetAccount.getString("Organization"));       	
        	account_Details.setTrial_Date(resultsetAccount.getDate("Trial_Date"));
        	account_Details.setTrial_Days(resultsetAccount.getInt("Trial_Days"));
        	account_Details.setSubscribed(resultsetAccount.getInt("Subscribed"));
        	account_Details.setExpiry_Date(resultsetAccount.getDate("Expiry_Date"));
        	account_Details.setNo_Of_Students(resultsetAccount.getInt("No_Of_Students"));
        	//account_Details.setNo_Of_Created_Students(resultsetAccount.getInt("No_Of_Created_Students"));
        	account_Details.setNo_Of_Created_Students(no_Of_Created_Students);
        	account_Details.setFrequencyInterval(resultsetAccount.getInt("FrequencyInterval"));
        	account_Details.setLast_Payment(resultsetAccount.getInt("Last_Payment"));
        	account_Details.setAmount_Per_Cycle(resultsetAccount.getInt("Amount_Per_Cycle"));
	    	SimpleDateFormat dateLastPaymentFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	String strLast_Payment_Date = null;
				try {
					if(resultsetAccount.getDate("Last_Payment_Date")!=null)
					{
						strLast_Payment_Date = dateLastPaymentFormat.format(resultsetAccount.getDate("Last_Payment_Date"));
						Date d = dateLastPaymentFormat.parse(strLast_Payment_Date);
						dateLastPaymentFormat.applyPattern("MMM dd, yyyy");
						strLast_Payment_Date = dateLastPaymentFormat.format(d);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
        	account_Details.setLast_Payment_Date(strLast_Payment_Date);
        	//////////////////////////Get difference between Last_Payment_Date and Today's Date in Days/////////////////////////
        	Date last_Payment_Date = resultsetAccount.getDate("Last_Payment_Date");
        	long diffInMillies = 0;
        	if(last_Payment_Date!=null)
        	{
        		diffInMillies = today.getTime() - last_Payment_Date.getTime();
        	}        		
        	int days_Consumed = (int) TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
        	account_Details.setDays_Consumed(days_Consumed);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	/////////////////////////////////////Get number of days left for trial////////////////////////////////////////////////
        	long diffInMilliesfortrial = 0;
        	if(resultsetAccount.getDate("Trial_Date")!=null)
        	{
            	diffInMilliesfortrial = today.getTime() - resultsetAccount.getDate("Trial_Date").getTime();
            	int trial_days_Consumed = (int) TimeUnit.DAYS.convert(diffInMilliesfortrial,TimeUnit.MILLISECONDS);
            	int trialDaysLeft = resultsetAccount.getInt("Trial_Days") -trial_days_Consumed;
            	account_Details.setTrialDays_Left(trialDaysLeft);
        	}
        	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	/////////////////////////Calculate cost till today///////////////////////////////////////////////////////////////////
        	int frequencyInterval = resultsetAccount.getInt("FrequencyInterval");
        	int cost_Till_Today = 0;
        	if(frequencyInterval!=0 && days_Consumed!=0)
        	{
        		//cost_Till_Today = (resultsetAccount.getInt("Last_Payment") / (frequencyInterval*30))*days_Consumed;
        		cost_Till_Today = (resultsetAccount.getInt("Amount_Per_Cycle") / (frequencyInterval*30))*days_Consumed;
        	}      	
        	account_Details.setCost_Till_Today(cost_Till_Today);
        	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	
        	/////////////////////////Calculate remaining amount///////////////////////////////////////////////////////////////////
        	//int remaining_Amount = resultsetAccount.getInt("Last_Payment") - cost_Till_Today;
        	int remaining_Amount = resultsetAccount.getInt("Amount_Per_Cycle") - cost_Till_Today;
        	account_Details.setRemaining_Amount(remaining_Amount);
        	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	ResultSet resultsetAdmin = statementAdmin.executeQuery(sAdmin);
        	while(resultsetAdmin.next())
        	{
        		////////////////////Get Employee Details//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        		String sEmployee = "Select * from Employees where Employee_id=(Select Employee_id from Admins where Admin_id="+resultsetAdmin.getInt("Admin_id")+")";
        		ResultSet resultsetEmployee = statementEmployee.executeQuery(sEmployee);
        		while(resultsetEmployee.next())
        		{
        			account_Details.setPayroll_id(resultsetEmployee.getInt("Payroll_id"));
        			account_Details.setDepartment_id(resultsetEmployee.getInt("Department_id"));
        			account_Details.setDesignation(resultsetEmployee.getString("Designation"));
        			account_Details.setJoining_Date(resultsetEmployee.getString("Joining_Date"));
        			account_Details.setInitial_Basic(resultsetEmployee.getDouble("initial_Basic"));      			
        		}
        		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        		account_Details.setEmail(resultsetAdmin.getString("Username"));
        		account_Details.setPassword(resultsetAdmin.getString("Password"));
	        	account_Details.setFirst_Name(resultsetAdmin.getString("First_Name"));
	        	account_Details.setLast_Name(resultsetAdmin.getString("Last_Name"));
	        	account_Details.setCountry_id(resultsetAdmin.getInt("Country_id"));     	
	        	account_Details.setCity(resultsetAdmin.getString("City"));
	        	account_Details.setTel(resultsetAdmin.getString("Tel"));
	        	account_Details.setMobile(resultsetAdmin.getString("Mobile"));
	        	account_Details.setBirth_Date(resultsetAdmin.getString("Birth_Date"));
	        	account_Details.setPO_Box(resultsetAdmin.getInt("Po_Box"));
	        	account_Details.setFull_Address(resultsetAdmin.getString("Full_Address"));
	        	account_Details.setGender(resultsetAdmin.getString("Gender"));
	        	account_Details.setFileName(resultsetAdmin.getString("Fake_File_Name"));
	        	account_Details.setAdmin_id(resultsetAdmin.getInt("Admin_id"));
	        	account_Details.setAccount_Owner(resultsetAdmin.getInt("Account_Owner"));
        	}
        	
        	ResultSet resultsetAgreement = statementAgreement.executeQuery(sAgreement);
        	while(resultsetAgreement.next())
        	{
        		account_Details.setAgreement_id(resultsetAgreement.getString("Agreement_id"));
        		account_Details.setAgreement_State(resultsetAgreement.getString("state"));
        	}        	
        	ResultSet resultsetAgreementPending = statementAgreementPending.executeQuery(sAgreementPending);
        	if(resultsetAgreementPending.isBeforeFirst())
        	{
        		account_Details.setAgreementPending(true);
        	}
        	else
        	{
        		account_Details.setAgreementPending(false);
        	}        	
        	ResultSet resultsetAgreementCancelling = statementAgreementCancelling.executeQuery(sAgreementCancelling);
        	if(resultsetAgreementCancelling.isBeforeFirst())
        	{
        		account_Details.setAgreementCancelling(true);
        	}
        	else
        	{
        		account_Details.setAgreementCancelling(false);
        	}
        	///////////////////////Check if the trial is valid/////////////////////////////////////////
			Date trial_Date = resultsetAccount.getDate("Trial_Date");
			int trial_Days = resultsetAccount.getInt("Trial_Days");
		    boolean trial_Valid=false;
			if(trial_Date!=null && resultsetAccount.getInt("Subscribed")==0)
			{
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(trial_Date);
			    cal.add(Calendar.DAY_OF_MONTH, trial_Days);
			    Date trial_Expiry = cal.getTime();   				
		    	SimpleDateFormat dateTrialFormat = new SimpleDateFormat("MMM dd, yyyy");
		    	String strTrial_Expiry = null;
			    if(date.compareTo(trial_Expiry)>0)//Trail Expired/
				{
					trial_Valid = false;
				}
				else
				{  					
					trial_Valid = true;
					try {
						strTrial_Expiry = dateTrialFormat.format(trial_Expiry);
						Date d = dateTrialFormat.parse(strTrial_Expiry);
						dateTrialFormat.applyPattern("MMM dd, yyyy");
				    	strTrial_Expiry = dateTrialFormat.format(d);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			    account_Details.setTrial_Valid(trial_Valid);
			    account_Details.setTrial_Expiry(strTrial_Expiry);
			}    
		    //////////////////////////Check if the subscription is valid /////////////////////////////////  			    
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date Now = null;
			try {
				Now = formatter.parse(formatter.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
			Date subscription_Expiry = resultsetAccount.getDate("Expiry_Date");
			if(subscription_Expiry!=null && resultsetAccount.getInt("Subscribed")!=0)
			{
				boolean subscription_Valid;
		    	SimpleDateFormat dateSubscriptionFormat = new SimpleDateFormat("yyyy/MM/dd");
		    	String strSubscription_Expiry = null;
				if(Now.compareTo(subscription_Expiry)>0 ) 
				{
					subscription_Valid = false;
				}
				else
				{   					
					///Subscription valid, Login as Subscribed///////////////////
					subscription_Valid=true;
					try {
						strSubscription_Expiry = dateSubscriptionFormat.format(subscription_Expiry);
						Date d = dateSubscriptionFormat.parse(strSubscription_Expiry);
						dateSubscriptionFormat.applyPattern("MMM dd, yyyy");
				    	strSubscription_Expiry = dateSubscriptionFormat.format(d);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				account_Details.setSubscription_Valid(subscription_Valid);
				account_Details.setSubscription_Expiry(strSubscription_Expiry);
			}
        	//////////////////////////////////////////////////////////////////////////////////////////////
		}		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return account_Details;
}

public int getAccountID(int course_id) {
	//String sAccount = "Select Account_id from Admins where  account_owner=1 and Admin_id in (Select Admin_id from Admin_School where school_id=(select School_id from Grades where Grade_id=(Select Grade_id from Courses where Course_id="+course_id+")))";
	String sAccount = "Select Account_id from school where school_id=(select School_id from Grades where Grade_id=(Select Grade_id from Courses where Course_id="+course_id+"))";
	int account_id=0;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAccount = connection.createStatement();){
		ResultSet resultAccount = statementAccount.executeQuery(sAccount);
		while(resultAccount.next())
		{
			account_id = resultAccount.getInt("Account_id");
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return account_id;
}

public int getSchoolID(int course_id) {
	String sSchool = "select School_id from Grades where Grade_id=(Select Grade_id from Courses where Course_id="+course_id+")";
	int school_id=0;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementSchool = connection.createStatement();){
		ResultSet resultSchool = statementSchool.executeQuery(sSchool);
		while(resultSchool.next())
		{
			school_id = resultSchool.getInt("School_id");
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return school_id;
}

public int getSchoolAccount(int school_id) {
	String sAccount = "Select Account_id from school where school_id="+school_id;
	int account_id=0;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAccount = connection.createStatement();){
		ResultSet resultAccount = statementAccount.executeQuery(sAccount);
		while(resultAccount.next())
		{
			account_id = resultAccount.getInt("Account_id");
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return account_id;
}

public int getAdminAccount(int admin_id) {
	String sAccount = "Select Account_id from Admins where Admin_id ="+admin_id;
	int account_id=0;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAccount = connection.createStatement();){
		ResultSet resultAccount = statementAccount.executeQuery(sAccount);
		while(resultAccount.next())
		{
			account_id = resultAccount.getInt("Account_id");
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return account_id;
}
public int getAdminByUsername(String username) {
	String sAdmin = "Select Admin_id from Admins where Username ='"+username+"'";
	int admin_id=0;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAdmin = connection.createStatement();){
		ResultSet resultAdmin = statementAdmin.executeQuery(sAdmin);
		while(resultAdmin.next())
		{
			admin_id = resultAdmin.getInt("Admin_id");
		}
		

	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return admin_id;
}
public String setPasswordToken(int admin_id, String username, String base){
	String query = "update Admins set forgot_password=? where Admin_id="+admin_id;
	String message=null;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		String forget_Password_Token = UUID.randomUUID().toString();
		connection.setAutoCommit(false);
		pstatement.setString(1, forget_Password_Token);
		updateQuery = pstatement.executeUpdate();
		if(updateQuery!=0)
		{
			String msg_subject = "Reset your EduHappy Account password";
			String msg_body = "You have requested to reset your Admin password.<br/><br/>";
			msg_body = msg_body+ "Please click on the following link to rest your password:<br/><br/>";
			msg_body = msg_body+ "<a href='"+base+"/Admin/Reset_Password.jsp?Admin_id="+admin_id+"&&Forget_Password_Token="+forget_Password_Token+"'>Please click here to reset your password</a><br/><br/>";
			msg_body = msg_body+ "Reagrds,<br/><br/>";
			msg_body = msg_body+ "EduHappy Team<br/><br/>";
			msg_body = msg_body+ "<a href='https://www.edu-happy.com'>www.edu-happy.com</a><br/><br/>";
			msg_body = msg_body+ "Email: info@edu-happy.com";
			EmailSender emailsender = new EmailSender();
			int message_sent = emailsender.sendemail(username, msg_subject, msg_body);
			if(message_sent == 1)
			{
				connection.commit();
				message = "Reset Password link has been sent to your email. Please check your email and follow the link.";
			}
			else
			{
				message = "Error sending Reset Password link. Please try again later.";
				connection.rollback();
			}
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
public String getPasswordToken(int admin_id) {
	String sAdmin = "Select Forgot_Password from Admins where admin_id ="+admin_id;
	String forgot_Password=null;
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAdmin = connection.createStatement();){
		ResultSet resultAdmin = statementAdmin.executeQuery(sAdmin);
		while(resultAdmin.next())
		{
			forgot_Password = resultAdmin.getString("Forgot_Password");
		}		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return forgot_Password;
}
public int updateAdminPassword(int admin_id, String new_Password){
	String query = "update Admins set forgot_password=null, password=? where Admin_id="+admin_id;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){;
		pstatement.setString(1, new_Password);
		updateQuery = pstatement.executeUpdate();		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
}
