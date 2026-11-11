package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Classes;
import model.dbHelpers.DbConnector;

public class GetClasses {
		public Classes doGetClasses(int grade_id){
			Classes Classes = new Classes();
			try (Connection connection = DbConnector.connectToDb();
				 Statement statement = connection.createStatement();){
				String query = "select * from Classes where grade_id="+grade_id;
				
				ResultSet resultset = statement.executeQuery(query);
			   	ArrayList<Integer> Class_id = new ArrayList<Integer>();
		    	ArrayList<String> Class_Name = new ArrayList<String>();
		        while(resultset.next()) {
		    		
		        	Class_id.add(Integer.parseInt(resultset.getString("Class_id")));
		        	Class_Name.add(resultset.getString("Class_Name"));
		    		
		        } 
		        Classes.setClass_id(Class_id);
		        Classes.setClass_Name(Class_Name);
		       
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Classes;
		}
}
