package model.dbHelpers.admin;

import java.sql.*;

import model.beans.account.Account_Details;
import model.beans.admin.Login;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

public class CheckLoginQuery {
	public Account_Details doCheckLogin(Login login){
		//int admin_id=0;
    	Account_Details account_Details = new Account_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementAdmin = connection.createStatement();
				Statement statementAccount = connection.createStatement();){
	
			
			String sAdmin = "select * from Admins where Username='"+login.getUsername()+"' and BINARY Password='"+login.getPassword()+"'";
			
			ResultSet resultsetAdmin = statementAdmin.executeQuery(sAdmin);
			
	        while(resultsetAdmin.next()) 
	        {	        
	        	AccountManagement accountManagement = new AccountManagement();
	        	account_Details = accountManagement.getAccountDetails(resultsetAdmin.getInt("account_id"));
	        	account_Details.setEmail(resultsetAdmin.getString("Username"));
	        	account_Details.setFirst_Name(resultsetAdmin.getString("First_Name"));
	        	account_Details.setLast_Name(resultsetAdmin.getString("Last_Name"));
	        	account_Details.setAdmin_id(resultsetAdmin.getInt("Admin_id"));
	        	account_Details.setAccount_id(resultsetAdmin.getInt("Account_id"));
	        	account_Details.setAccount_Owner(resultsetAdmin.getInt("Account_Owner"));	
	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account_Details;
		
	}
}
