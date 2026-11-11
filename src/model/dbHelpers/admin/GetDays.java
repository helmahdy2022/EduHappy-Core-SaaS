package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Days;
import model.dbHelpers.DbConnector;

public class GetDays {
	public Days doGetDays(){
		Days Days = new Days();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select * from week_days order by Day_id";
			
			ResultSet resultset = statement.executeQuery(query);
	    	ArrayList<Integer> Day_id = new ArrayList<Integer>();
	    	ArrayList<String> Day_Name = new ArrayList<String>();
	        while(resultset.next()) {
	    		
	    		Day_id.add(Integer.parseInt(resultset.getString("Day_id")));
	    		Days.setDay_id(Day_id);
	    		
	    		Day_Name.add(resultset.getString("Day_Name"));
	    		Days.setDay_Name(Day_Name);
	    		

	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Days;
	}
}
