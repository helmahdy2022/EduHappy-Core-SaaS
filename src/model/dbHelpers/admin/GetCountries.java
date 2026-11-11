package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Countries;
import model.dbHelpers.DbConnector;

public class GetCountries {
	public Countries doGetCountries(){
		Countries Countries = new Countries();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select * from country";
			
			ResultSet resultset = statement.executeQuery(query);
	    	ArrayList<Integer> Country_id = new ArrayList<Integer>();
	    	ArrayList<String> Country_Name = new ArrayList<String>();
	        while(resultset.next()) {
	    		
	    		Country_id.add(Integer.parseInt(resultset.getString("Country_id")));
	    		Countries.setCountry_id(Country_id);
	    		
	    		Country_Name.add(resultset.getString("Country_Name"));
	    		Countries.setCountry_Name(Country_Name);
	    		

	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Countries;
	}
}
