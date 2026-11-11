package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dbHelpers.DbConnector;

public class GetSchoolGrade {
	public int doGetSchoolGrade(int grade_id){
		int school_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select School_id from Grades where grade_id="+grade_id;
			
			ResultSet resultset = statement.executeQuery(query);
	        while(resultset.next()) {
	    		school_id = resultset.getInt("School_id");   		

	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_id;
	}
}
