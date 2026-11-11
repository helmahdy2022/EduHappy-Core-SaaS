package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Parent_Details;
import model.beans.admin.Parents;
import model.beans.admin.Students;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.account.EmailSender;

public class ParentManagement {

	public int getParentAccount(int parent_id ){		
		int account_id = 0;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParentAccount = connection.createStatement();){
			String sParentAccount = "select account_id from parents where parent_id="+parent_id;
			ResultSet resultParentAccount = statementParentAccount.executeQuery(sParentAccount);
			while(resultParentAccount.next()) 
			{
	    		account_id = resultParentAccount.getInt("Account_id");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account_id;
	}


	public int updateParentPassword(int parent_id, String new_Password){
		String query = "update Parents set forgot_password=null, password=?, First_Login=0 where Parent_id="+parent_id;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setString(1, new_Password);
			updateQuery = pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Parent_Details getParent(int parent_id){
		String query = "select *, DATE_FORMAT(Birth_date, '%Y-%m-%d') AS B_date from Parents where Parent_id="+parent_id;
		
		Parent_Details parent_Details = new Parent_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementCountry = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{			
				parent_Details.setParent_id(parent_id);
				parent_Details.setAccount_id(result.getInt("Account_id"));
				parent_Details.setFirst_Name(result.getString("First_Name"));
				parent_Details.setLast_Name(result.getString("Last_Name"));
				parent_Details.setCountry_id(result.getInt("Country_id"));
				parent_Details.setCity(result.getString("City"));
				parent_Details.setPO_Box(result.getInt("Po_Box"));
				parent_Details.setFull_Address(result.getString("Full_Address"));
				parent_Details.setGender(result.getString("Gender"));
				parent_Details.setBirth_Date(result.getString("B_Date"));
				parent_Details.setEmail(result.getString("Email"));
				parent_Details.setTel(result.getString("Tel"));
				parent_Details.setMobile(result.getString("Mobile"));
				parent_Details.setUsername(result.getString("Username"));
				parent_Details.setPassword(result.getString("Password"));
				parent_Details.setFileName(result.getString("Fake_File_Name"));
				parent_Details.setFirst_Login(result.getInt("First_Login"));
				
				String sCountry = "Select Country_Name from Country where country_id="+result.getInt("Country_id");
				ResultSet resultCountry = statementCountry.executeQuery(sCountry);
				String country_Name = null;
				while(resultCountry.next())
				{
					country_Name = resultCountry.getString("Country_Name");
				}	
				parent_Details.setCountry_Name(country_Name);

			}    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent_Details;
	}


	public int createParent(Parent_Details parent_Details){
		String query = "INSERT INTO Parents (Parent_id, First_Name, Last_Name, Country_id, City, PO_Box, full_address, Gender, email, tel, mobile, Birth_Date, Username, Password, Account_id, first_login) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Statement statementAccountParents = connection.createStatement();
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

		    String sUserExistInStudent = "select * from Students where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);
		    
		    String sUserExistInTeacher = "select * from Teachers where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

		    String sUserExistInAdmin = "select * from Admins where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
		    
		    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
		    {
		    	autoIncValue=-2;
		    	return autoIncValue;
		    }
		    String Birth_Date = parent_Details.getBirth_Date();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatement.setString(1, parent_Details.getFirst_Name());
			pstatement.setString(2, parent_Details.getLast_Name());

			pstatement.setInt(3, parent_Details.getCountry_id());
			pstatement.setString(4, parent_Details.getCity());
			if(parent_Details.getPO_Box()!=0)
			{
				pstatement.setInt(5, parent_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(5, java.sql.Types.NULL);
			}		
			pstatement.setString(6, parent_Details.getFull_Address());
			pstatement.setString(7, parent_Details.getGender());
			pstatement.setString(8, parent_Details.getEmail());
			pstatement.setString(9, parent_Details.getTel());
			pstatement.setString(10, parent_Details.getMobile());
			pstatement.setDate(11,new Date(Birth_Date_Date.getTime()));
			pstatement.setString(12, parent_Details.getUsername());
			pstatement.setString(13, parent_Details.getPassword());
			pstatement.setInt(14, parent_Details.getAccount_id());
			if(parent_Details.getFirst_Login()==1)
			{
				pstatement.setInt(15, 1);
			}
			else
			{
				pstatement.setInt(15, 0);
			}			
			int updateQuery = pstatement.executeUpdate();	    
			if(updateQuery!=0)
			{
				ResultSet resultParent_id = pstatement.getGeneratedKeys();
				if(resultParent_id.next()) 
				{
				       autoIncValue = resultParent_id.getInt(1);
				       resultParent_id.close();
				}
			} 			
		}
		catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Parent_Username_Unique"))
				autoIncValue=-2;			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}

	public int createParentAccount(Parent_Details parent_Details, String base, String partial_base){
		String query = "INSERT INTO Parents (Parent_id, First_Name, Last_Name, Country_id, City, email, mobile, Username, Password, Account_id, Activation_Key, First_Login) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Statement statementAccountParents = connection.createStatement();
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

		    String sUserExistInStudent = "select * from Students where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);
		    
		    String sUserExistInTeacher = "select * from Teachers where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

		    String sUserExistInAdmin = "select * from Admins where Username='"+parent_Details.getUsername()+"'";
		    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
		    
		    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
		    {
		    	autoIncValue=-2;
		    	return autoIncValue;
		    }
		    String Activation_Key = UUID.randomUUID().toString();
		    connection.setAutoCommit(false);
		    pstatement.setString(1, parent_Details.getFirst_Name());
			pstatement.setString(2, parent_Details.getLast_Name());
			pstatement.setInt(3, parent_Details.getCountry_id());
			pstatement.setString(4, parent_Details.getCity());	
			pstatement.setString(5, parent_Details.getEmail());
			pstatement.setString(6, parent_Details.getMobile());
			pstatement.setString(7, parent_Details.getUsername());
			pstatement.setString(8, parent_Details.getPassword());
			pstatement.setInt(9, parent_Details.getAccount_id());
			pstatement.setString(10, Activation_Key);
			pstatement.setInt(11, 0);
			int updateQuery = pstatement.executeUpdate();	    
			if(updateQuery!=0)
			{
				ResultSet resultParent_id = pstatement.getGeneratedKeys();
				if(resultParent_id.next()) 
				{
				       autoIncValue = resultParent_id.getInt(1);
				       resultParent_id.close();
				}
				String msg_subject = "Your EduHappy Parent account";
				String msg_body = "EduHappy Parent Account<br/><br/>";
				msg_body = msg_body+ "Hi "+parent_Details.getFirst_Name()+"<br/><br/>";
				msg_body = msg_body+ "Welcome to EduHappy Academy, the online platform that connects your child with the private teachers in the subjects you want.<br/><br/>";
				msg_body = msg_body+ "Login Details:<br/><br/>";
				msg_body = msg_body+ "Username: "+parent_Details.getUsername()+"<br/><br/>";
				msg_body = msg_body+ "<a href='"+base+"/Activate_Parent_Account?Activation_Key="+Activation_Key+"'>Please click here to activate your account</a><br/><br/>";
				msg_body = msg_body+ "After you activate your account, you can access the Parent Portal through the following link: <a href='"+partial_base+"/parent'>"+partial_base+"/parent</a><br/><br/>";
				msg_body = msg_body+ "After you create your child's profile, you can access the Student Portal through the following link: <a href='"+partial_base+"/student'>"+partial_base+"/student</a><br/><br/>";
				msg_body = msg_body+ "If you need any help, please contact us at info@edu-happy.com.<br/><br/>";
				msg_body = msg_body+ "Best of Luck,<br/><br/>";
				msg_body = msg_body+ "EduHappy Team<br/><br/>";
				msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
				msg_body = msg_body+ "Email: info@edu-happy.com";
				EmailSender emailsender = new EmailSender();
				int message_sent = emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
				if(message_sent == 1)
				{
					connection.commit();					
				}
				else
				{
					autoIncValue=-1;
					connection.rollback();
				}
			} 			
		}
		catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Parent_Username_Unique"))
				autoIncValue=-2;			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}
	public String activateParentAccount(String Activation_Key, String base) {
		String sAccount = "Select Activated from Parents where Activation_Key='"+Activation_Key+"'";
		String sActivateAccount = "update Parents set Activated=?, Activation_Date=? where Activation_Key='"+Activation_Key+"'";
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

					java.util.Date today =  new java.util.Date();
					pstatement.setInt(1, 1);
					pstatement.setDate(2, new java.sql.Date(today.getTime()));	
					updateQuery = pstatement.executeUpdate();
					if(updateQuery!=0)
					{
						//message="Parent account has been successfully activated.<br/> <a href='"+base+"/Parent/Parent_Login.jsp' style='color:red'>Please Login</a>";
						message="Parent account has been successfully activated.";
					}
					else
					{
						message="Parent account activation failed.";
					}
				}
				else
				{
					message="This Parent account is already activated.";
				}
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public int updateParent(Parent_Details parent_Details, int parent_id){
		String query = "update Parents set First_Name=?, Last_Name=?, Country_id=?, City=?, PO_Box=?, full_address=?, Gender=?, email=?, tel=?, mobile=?, Birth_Date=?, Username=?, Password=? where Parent_id="+parent_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

	    String sUserExistInTeacher = "select * from Teachers where Username='"+parent_Details.getUsername()+"'";
	    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

	    String sUserExistInStudent = "select * from Students where Username='"+parent_Details.getUsername()+"'";
	    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);	
	    
	    String sUserExistInAdmin = "select * from Admins where Username='"+parent_Details.getUsername()+"'";
	    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
	    
	    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
	    {
	    	updateQuery=-1;
	    	return updateQuery;
	    }
			String Birth_Date = parent_Details.getBirth_Date();
			//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstatement.setString(1, parent_Details.getFirst_Name());
			pstatement.setString(2, parent_Details.getLast_Name());			
			pstatement.setInt(3, parent_Details.getCountry_id());	
			pstatement.setString(4, parent_Details.getCity());	
			if(parent_Details.getPO_Box()!=0)
			{
				pstatement.setInt(5, parent_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(5, java.sql.Types.NULL);
			}
			pstatement.setString(6, parent_Details.getFull_Address());
			pstatement.setString(7, parent_Details.getGender());
			pstatement.setString(8, parent_Details.getEmail());	
			pstatement.setString(9, parent_Details.getTel());
			pstatement.setString(10, parent_Details.getMobile());
			pstatement.setDate(11,new Date(Birth_Date_Date.getTime()));	
			pstatement.setString(12, parent_Details.getUsername());
			pstatement.setString(13, parent_Details.getPassword());
	        updateQuery = pstatement.executeUpdate();
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Parent_Username_Unique"))
				updateQuery=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}


	public int deleteParent(int parent_id){
		String query = "Delete from Parents where Parent_id="+parent_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}


	public Parents getParentsList(String first_Name, String last_Name, String username, String parent_id, int account_id){
		String query = "select * from Parents where First_Name like '%"+first_Name+"%' and Last_Name like '%"+last_Name+"%' and Username like '%"+username+"%' and Parent_id like '"+ parent_id+"' and account_id="+account_id;
		Parents Parents = new Parents(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);

			
	    	ArrayList<Integer> Parent_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
    		while (result.next())
	    	{

    			Parent_id.add(result.getInt("Parent_id"));	    		
	    		First_Name.add(result.getString("First_Name"));    		
	    		Last_Name.add(result.getString("Last_Name"));	    		
	    		Username.add(result.getString("Username"));	    	
	    		Account_id.add(result.getInt("Account_id"));
		    	///////////////////Get Parent File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(result.getString("Fake_File_Name"));
		    }
    		Parents.setParent_id(Parent_id);
    		Parents.setParent_First_Name(First_Name);
    		Parents.setParent_Last_Name(Last_Name);
    		Parents.setUsername(Username);
    		Parents.setAccount_id(Account_id);
    		Parents.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Parents;
	}
	
	public int linkParentStudent(int student_id, int parent_id){
		String query = "INSERT INTO Student_Parent (Student_id, Parent_id) VALUES (?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
			 Statement statementLinkExist = connection.createStatement();){

		    String sLinkExist = "select * from Student_Parent where Parent_id="+parent_id+" and Student_id="+student_id;
		    ResultSet resultLinkExist = statementLinkExist.executeQuery(sLinkExist);
		    if(!resultLinkExist.isBeforeFirst())
		    {
			    pstatement.setInt(1, student_id);
				pstatement.setInt(2, parent_id);
				updateQuery = pstatement.executeUpdate();
		    }    			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Students getParentStudents(int parent_id ){		
		Students Students = new Students();	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParentStudents = connection.createStatement();){
			String sParentStudents = "select * from Students where Student_id in (select Student_id from Student_Parent where Parent_id="+parent_id+")";
			ResultSet resultParentStudents = statementParentStudents.executeQuery(sParentStudents);
	    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
			while(resultParentStudents.next()) 
			{
	    		Student_id.add(resultParentStudents.getInt("Student_id"));	    		
	    		First_Name.add(resultParentStudents.getString("First_Name"));   		
	    		Last_Name.add(resultParentStudents.getString("Last_Name"));	    		
	    		Username.add(resultParentStudents.getString("Username"));
	    		FileName.add(resultParentStudents.getString("Fake_File_Name"));
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
	    		StudentManagement studentManagement = new StudentManagement();
	    		int school_id=0;
	    		school_id = studentManagement.getStudentSchool(resultParentStudents.getInt("Student_id"));
	    		School_id.add(school_id);
	    		AccountManagement accountManagement = new AccountManagement();
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	        } 	
	    	Students.setStudent_id(Student_id);
	    	Students.setFirst_Name(First_Name);
	    	Students.setLast_Name(Last_Name);
	    	Students.setUsername(Username);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}

	public int deleteParentStudentLink(int parent_id, int student_id){
		String query = "Delete from Student_Parent where Parent_id="+parent_id+" and Student_id="+student_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int createOrderToken(int order_id, String temp_saved_token ){
		String query = "INSERT INTO Parent_Order (Order_id, temp_saved_token, Order_Date) VALUES (?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
			 Statement statementOrderExist = connection.createStatement();){

		    String sOrderExist = "select * from Parent_Order where Order_id="+order_id+" and temp_saved_token='"+temp_saved_token+"'";
		    ResultSet resultOrderExist = statementOrderExist.executeQuery(sOrderExist);
		    if(!resultOrderExist.isBeforeFirst())
		    {
			    pstatement.setInt(1, order_id);
				pstatement.setString(2, temp_saved_token);
				java.util.Date today = new java.util.Date();
				pstatement.setDate(3, new java.sql.Date(today.getTime()));	
				updateQuery = pstatement.executeUpdate();
		    }    			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}	
	
	public String getTokenByOrder(int order_id){		
		String temp_saved_token = null;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTokenByOrder = connection.createStatement();){
			String sTokenByOrder = "select temp_saved_token from Parent_Order where order_id="+order_id;
			ResultSet resultTokenByOrder = statementTokenByOrder.executeQuery(sTokenByOrder);
			while(resultTokenByOrder.next()) 
			{
	    		temp_saved_token = resultTokenByOrder.getString("temp_saved_token");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp_saved_token;
	}
	public int updateOrderToken(int order_id, String txn_response_code, int api_source){
		String query = "update Parent_Order set txn_response_code=?, api_source=? where order_id="+order_id;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setString(1, txn_response_code);
			pstatement.setInt(2, api_source);
			updateQuery = pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public String getErrorByOrder(int order_id){		
		String txn_response_code = null;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementErrorByOrder = connection.createStatement();){
			String sErrorByOrder = "select txn_response_code from Parent_Order where order_id="+order_id;
			ResultSet resultErrorByOrder = statementErrorByOrder.executeQuery(sErrorByOrder);
			while(resultErrorByOrder.next()) 
			{
				txn_response_code = resultErrorByOrder.getString("txn_response_code");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return txn_response_code;
	}
	public int getapisourceyOrder(int order_id){		
		int api_source = 0;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementapisource = connection.createStatement();){
			String sapisource = "select api_source from Parent_Order where order_id="+order_id;
			ResultSet resultapisource = statementapisource.executeQuery(sapisource);
			while(resultapisource.next()) 
			{
				api_source = resultapisource.getInt("api_source");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return api_source;
	}
	public int createParentOrder(int parent_id, int order_id){
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementOrderExist = connection.createStatement();
			 Statement statementOrder = connection.createStatement();){
			
			java.util.Date today = new java.util.Date();
    	    String sCreateOrder = "INSERT INTO Parent_Order (Parent_id, Order_id, Order_Date) VALUES ("+parent_id+","+order_id+",'"+new java.sql.Date(today.getTime())+"')";
    	    String sUpdateOrder = "update Parent_Order set parent_id="+parent_id+" where order_id="+order_id;
    	    String sOrder = null;
		    String sOrderExist = "select * from Parent_Order where Order_id="+order_id;
		    ResultSet resultOrderExist = statementOrderExist.executeQuery(sOrderExist);
		    if(!resultOrderExist.isBeforeFirst())
		    {
		    	sOrder = sCreateOrder;
		    }    	
		    else
		    {
		    	sOrder = sUpdateOrder;
		    }    	   
	        updateQuery = statementOrder.executeUpdate(sOrder);		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int getParentByOrder(int order_id){		
		int parent_id = 0;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParentByOrder = connection.createStatement();){
			String sParentByOrder = "select parent_id from Parent_Order where order_id="+order_id;
			ResultSet resultParentByOrder = statementParentByOrder.executeQuery(sParentByOrder);
			while(resultParentByOrder.next()) 
			{
	    		parent_id = resultParentByOrder.getInt("Parent_Id");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent_id;
	}
	
	public int setParentSavedToken(int parent_id, String saved_token){
		String query = "update Parents set saved_token=? where Parent_id="+parent_id;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setString(1, saved_token);
			updateQuery = pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int setBadSavedToken(int parent_id, int bad_saved_token){
		String query = "update Parents set bad_saved_token=? where Parent_id="+parent_id;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setInt(1, bad_saved_token);
			updateQuery = pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public String getParentSavedToken(int parent_id){		
		String saved_token = null;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParentSavedToken = connection.createStatement();){
			String sParentSavedToken = "select saved_token from parents where parent_id="+parent_id;
			ResultSet resultParentSavedToken = statementParentSavedToken.executeQuery(sParentSavedToken);
			while(resultParentSavedToken.next()) 
			{
	    		saved_token = resultParentSavedToken.getString("Saved_Token");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saved_token;
	}
	public boolean checkBadtSavedToken(String saved_token){		
		boolean bad_saved_token = false;	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementBadSavedToken = connection.createStatement();){
			String sBadSavedToken = "select bad_saved_token from parents where saved_token='"+saved_token+"'";
			ResultSet resultBadSavedToken = statementBadSavedToken.executeQuery(sBadSavedToken);
			while(resultBadSavedToken.next()) 
			{
	    		int int_bad_saved_token = resultBadSavedToken.getInt("Bad_Saved_Token");
	    		if(int_bad_saved_token==1)
	    		{
	    			bad_saved_token = true;
	    		}
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bad_saved_token;
	}
	public int deleteUnusedToken(int order_id){
		String query = "Delete from Parent_Order where order_id="+order_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int getParentByUsername(String username) {
		String sParent = "Select Parent_id from Parents where Username ='"+username+"'";
		int parent_id=0;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParent = connection.createStatement();){
			ResultSet resultParent = statementParent.executeQuery(sParent);
			while(resultParent.next())
			{
				parent_id = resultParent.getInt("Parent_id");
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent_id;
	}
	public String setPasswordToken(int parent_id, String username, String base){
		String query = "update Parents set forgot_password=? where Parent_id="+parent_id;
		String message=null;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			String forget_Password_Token = UUID.randomUUID().toString();
			connection.setAutoCommit(false);
			pstatement.setString(1, forget_Password_Token);
			updateQuery = pstatement.executeUpdate();
			Parent_Details parent_Details = getParent(parent_id);
			if(updateQuery!=0)
			{
				String msg_subject = "Reset your EduHappy Parent Account password";
				String msg_body = "You have requested to reset your Parent password.<br/><br/>";
				msg_body = msg_body+ "Please click on the following link to rest your password:<br/><br/>";
				msg_body = msg_body+ "<a href='"+base+"/Parent/Parent_Reset_Password.jsp?Parent_id="+parent_id+"&&Forget_Password_Token="+forget_Password_Token+"'>Please click here to reset your password</a><br/><br/>";
				msg_body = msg_body+ "Reagrds,<br/><br/>";
				msg_body = msg_body+ "EduHappy Team<br/><br/>";
				msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
				msg_body = msg_body+ "Email: info@edu-happy.com";
				EmailSender emailsender = new EmailSender();
				int message_sent = emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
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
	public String getPasswordToken(int parent_id) {
		String sParent = "Select Forgot_Password from Parents where Parent_id ="+parent_id;
		String forgot_Password=null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParent = connection.createStatement();){
			ResultSet resultParent = statementParent.executeQuery(sParent);
			while(resultParent.next())
			{
				forgot_Password = resultParent.getString("Forgot_Password");
			}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forgot_Password;
	}
}

