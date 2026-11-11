package model.dbHelpers.Parent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.account.Account_Details;
import model.beans.parent.Child;
import model.beans.parent.Login;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

public class CheckLoginQuery {
	public Account_Details doCheckLogin(Login login){
		int parent_id=0;
    	int account_id=0;
		Account_Details account_Details = new Account_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementAdmin = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementPromoted = connection.createStatement();
				Statement statementParentChildren = connection.createStatement();){
	
			
			String sParent = "select * from Parents where Username='"+login.getUsername()+"' and BINARY Password='"+login.getPassword()+"'";
			
			ResultSet resultset = statement.executeQuery(sParent);

	        while(resultset.next()) 
	        {
	        	parent_id = resultset.getInt("Parent_id");
	        	account_id = resultset.getInt("Account_id"); 	
				AccountManagement accountManagement = new AccountManagement();
				account_Details = accountManagement.getAccountDetails(account_id);
	        	account_Details.setParent_id(parent_id);
	        	account_Details.setFirst_Name(resultset.getString("First_Name"));
	        	account_Details.setLast_Name(resultset.getString("Last_Name"));
	        	account_Details.setFileName(resultset.getString("Fake_File_Name"));
	        	account_Details.setPassword(resultset.getString("Password"));
	        	account_Details.setParent_First_Login(resultset.getInt("First_Login"));
	        	account_Details.setAccount_id(account_id);
	        	if (resultset.getString("Saved_Token")!=null && !resultset.getString("Saved_Token").isEmpty() && resultset.getInt("Bad_Saved_Token")!=1)
	        	{
	        		account_Details.setHas_saved_token(true);
	        	}
	        	else
	        	{
	        		account_Details.setHas_saved_token(false);
	        	}
	        	/////////////////////Get Parent's children/////////////////////////////////////////////
	        	String sParentChildren = "select * from Students where student_id in (select student_id from student_parent where parent_id="+parent_id+")";
	        	ResultSet resultsetParentChildren = statementParentChildren.executeQuery(sParentChildren);
	        	ArrayList<Integer> student_id = new ArrayList<Integer>();
	        	ArrayList<String> first_Name = new ArrayList<String>();
	        	ArrayList<String> last_Name = new ArrayList<String>();
	        	while(resultsetParentChildren.next())
	        	{
	        		student_id.add(resultsetParentChildren.getInt("Student_id"));
	        		first_Name.add(resultsetParentChildren.getString("First_Name"));
	        		last_Name.add(resultsetParentChildren.getString("Last_Name"));
	        	}
	        	Child child = new Child();
	        	child.setStudent_id(student_id);
	        	child.setFirst_Name(first_Name);
	        	child.setLast_Name(last_Name);
	        	account_Details.setChild(child);
	        	//////////////////////////////////////////////////////////////////////////////////////
	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account_Details;
		
	}
}
