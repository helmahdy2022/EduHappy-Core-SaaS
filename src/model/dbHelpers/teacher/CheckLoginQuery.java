package model.dbHelpers.teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.beans.account.Account_Details;
import model.beans.teacher.Login;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.TeacherManagement;

public class CheckLoginQuery {
	public Account_Details doCheckLogin(Login login){
		Account_Details account_Details = new Account_Details();
		int teacher_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementAdmin = connection.createStatement();){
	
			
			String sTeacher = "select * from Teachers where Username='"+login.getUsername()+"' and BINARY Password='"+login.getPassword()+"'";
			
			ResultSet resultset = statement.executeQuery(sTeacher);
			
	        while(resultset.next()) 
	        {
	        	teacher_id = resultset.getInt("Teacher_id");
	        	TeacherManagement teacherManagement = new TeacherManagement();
	        	int admin_id = teacherManagement.getTeacherAdmin(teacher_id);
				String sAdmin = "select * from Admins where admin_id="+admin_id;				
				ResultSet resultsetAdmin = statementAdmin.executeQuery(sAdmin);
	        	AccountManagement accountManagement = new AccountManagement();
	        	int account_id=0;
	        	while(resultsetAdmin.next())
	        	{
	        		account_id = resultsetAdmin.getInt("account_id");
	        		account_Details = accountManagement.getAccountDetails(resultsetAdmin.getInt("account_id"));
	        	}	       	
	        	account_Details.setTeacher_id(teacher_id);
	        	account_Details.setEmployee_id(resultset.getInt("Employee_id"));
	        	account_Details.setFirst_Name(resultset.getString("First_Name"));
	        	account_Details.setLast_Name(resultset.getString("Last_Name"));
	        	account_Details.setFileName(resultset.getString("Fake_File_Name"));
	        	account_Details.setPassword(resultset.getString("Password"));
	        	account_Details.setTeacher_First_Login(resultset.getInt("First_Login"));
	        	account_Details.setAccount_id(account_id);
	        }        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account_Details;
		
	}
}
