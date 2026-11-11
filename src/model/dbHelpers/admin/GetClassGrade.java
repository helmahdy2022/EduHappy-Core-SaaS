package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dbHelpers.DbConnector;

public class GetClassGrade {
	public int doGetClassGrade(int class_id){
		int grade_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSutdentGrade = connection.createStatement();){
	
			
			String query = "select * from Grades where Grade_id=(select grade_id from Classes where class_id="+class_id+")";
			
			ResultSet resultset = statement.executeQuery(query);
	        
			while(resultset.next()) 
	        {
	    		grade_id = resultset.getInt("Grade_id");   		

	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade_id;
	}
}
