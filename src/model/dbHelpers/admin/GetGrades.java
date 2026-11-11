package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Grades;

import model.dbHelpers.DbConnector;

public class GetGrades {
	public Grades doGetGrades(int school_id){
		Grades Grades = new Grades();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			String query = "select * from Grades where school_id="+school_id+" order by Grade_Index";
			
			ResultSet resultset = statement.executeQuery(query);
		   	ArrayList<Integer> Grade_id = new ArrayList<Integer>();
	    	ArrayList<String> Grade_Name = new ArrayList<String>();
	        while(resultset.next()) {
	    		
	        	Grade_id.add(Integer.parseInt(resultset.getString("Grade_id")));
	        	Grade_Name.add(resultset.getString("Grade_Name"));
	    		
	        } 
	        Grades.setGrade_id(Grade_id);
	        Grades.setGrade_Name(Grade_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Grades;
	}
}
