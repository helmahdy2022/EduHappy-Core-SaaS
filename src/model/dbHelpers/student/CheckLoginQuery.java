package model.dbHelpers.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import model.beans.account.Account_Details;
import model.beans.student.Login;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.StudentManagement;

public class CheckLoginQuery {
	public Account_Details doCheckLogin(Login login){
		int student_id=0;
		Account_Details account_Details = new Account_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementAdmin = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementPromoted = connection.createStatement();){
	
			
			String sStudent = "select * from Students where Username='"+login.getUsername()+"' and BINARY Password='"+login.getPassword()+"'";
			
			ResultSet resultset = statement.executeQuery(sStudent);
			
	        while(resultset.next()) 
	        {
	        	student_id = resultset.getInt("Student_id");
	        	StudentManagement studentManagement = new StudentManagement();
	        	int admin_id = studentManagement.getStudentAdmin(student_id);
	        	int school_id = studentManagement.getStudentSchool(student_id);
				String sAdmin = "select * from Admins where admin_id="+admin_id;				
				ResultSet resultsetAdmin = statementAdmin.executeQuery(sAdmin);
	        	AccountManagement accountManagement = new AccountManagement();
	        	int account_id=0;
	        	while(resultsetAdmin.next())
	        	{
	        		account_id = resultsetAdmin.getInt("account_id");
	        		account_Details = accountManagement.getAccountDetails(account_id);
	        	}	       	
	        	account_Details.setStudent_id(student_id);
	        	account_Details.setFirst_Name(resultset.getString("First_Name"));
	        	account_Details.setLast_Name(resultset.getString("Last_Name"));
	        	account_Details.setFileName(resultset.getString("Fake_File_Name"));
	        	account_Details.setPassword(resultset.getString("Password"));
	        	account_Details.setStudent_First_Login(resultset.getInt("First_Login"));
	        	account_Details.setAccount_id(account_id);
	        	account_Details.setSchool_id(school_id);
				///////////////////////////Get School Year Id/////////////////////////////////
				Date today = new Date();
		    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
		    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
				ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
		    	int school_year_id = 0;
		    	String year_Name = null;
				while(resultSchoolYear.next()) 
		    	{    		
		    		school_year_id = resultSchoolYear.getInt("School_Year_id");	   
		    		year_Name = resultSchoolYear.getString("Year_Name");
		        } 	       
				/////////////////////////////////////////////////////////////////////////////
	        	/////////////////////Check if the student already promoted for the current school year////////////////////////
				String sPromoted = "select * from _school_year where school_year_id="+school_year_id;
				ResultSet resultPromoted = statementPromoted.executeQuery(sPromoted);
				boolean promoted = false;
				if(resultPromoted.isBeforeFirst())
				{
					promoted = true;
				}
				/////////////////////////////////////////////////////////////////////////////////////////////
				account_Details.setStudent_Promoted(promoted);
				account_Details.setYear_Name(year_Name);
				///////////////////////////////////////////////////////////////////////////////////////////////////////////
	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account_Details;
		
	}
}
