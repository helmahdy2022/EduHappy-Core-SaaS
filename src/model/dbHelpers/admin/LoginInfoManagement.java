package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dbHelpers.DbConnector;

public class LoginInfoManagement {
	public boolean checkUsername(String username){
		boolean usernameInUse=false;
		try (Connection connection = DbConnector.connectToDb();			
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInParent = connection.createStatement();		
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

		    String sUserExistInStudent = "select * from Students where Username='"+username+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);

		    String sUserExistInParent = "select * from Parents where Username='"+username+"'";
		    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);
		    
			String sUserExistInTeacher = "select * from Teachers where Username='"+username+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

		    String sUserExistInAdmin = "select * from Admins where Username='"+username+"'";
		    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);

		    
		    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInParent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
		    {
		    	usernameInUse=true;
		    }
		}
		catch (SQLException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	return usernameInUse;
	}
}
