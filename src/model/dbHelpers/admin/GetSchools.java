/**
 * 
 */
package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Schools;
import model.dbHelpers.DbConnector;

/**
 * @author helmahdy
 *
 */
public class GetSchools {
	public Schools doGetSchools(int admin_id){
		Schools Schools = new Schools();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select * from School where School_id in (Select School_id from Admin_School where Admin_id="+admin_id+")";
			
			ResultSet resultset = statement.executeQuery(query);
			ArrayList<Integer> School_id = new ArrayList<Integer>();
		    ArrayList<String> School_Name = new ArrayList<String>();
	        while(resultset.next()) {
	        	
	    	School_id.add(Integer.parseInt(resultset.getString("School_id")));
	    	School_Name.add(resultset.getString("Name"));
	        } 
	    	Schools.setSchool_id(School_id);
	    	Schools.setSchool_Name(School_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Schools;
	}
	public Schools doGetSchoolsByParent(int parent_id){
		Schools Schools = new Schools();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select * from School where account_id=(select account_id from Parents where parent_id="+parent_id+")";
			
			ResultSet resultset = statement.executeQuery(query);
			ArrayList<Integer> School_id = new ArrayList<Integer>();
		    ArrayList<String> School_Name = new ArrayList<String>();
	        while(resultset.next()) {
	        	
	    	School_id.add(Integer.parseInt(resultset.getString("School_id")));
	    	School_Name.add(resultset.getString("Name"));
	        } 
	    	Schools.setSchool_id(School_id);
	    	Schools.setSchool_Name(School_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Schools;
	}
}
