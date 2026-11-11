package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.AssignmentCategory;
import model.beans.admin.Assignment_Category_Details;
import model.beans.teacher.Content_Container;
import model.dbHelpers.DbConnector;

public class AssignmentCatManagement {
		public Assignment_Category_Details getAssignmentCat(int category_id){
		String query = "select * from Assignment_Category where Category_id="+category_id;
		Assignment_Category_Details assignment_Category_Details = new Assignment_Category_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			result.next();
			assignment_Category_Details.setCategory_id(category_id);
			assignment_Category_Details.setCategory_Name(result.getString("Category_Name"));
			assignment_Category_Details.setCategory_Grade(result.getInt("Category_Grade"));
			assignment_Category_Details.setContribute(result.getInt("Contribute"));
			assignment_Category_Details.setCreate_Authority(result.getInt("Create_Authority"));
			assignment_Category_Details.setCourse_id(result.getInt("Course_id"));
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignment_Category_Details;
	}
		public AssignmentCategory getAssignmentCatList(int course_id){
		String query = "select * from Assignment_Category where Course_id="+course_id;
		AssignmentCategory assignmentCategory = new AssignmentCategory(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> category_id = new ArrayList<Integer>();
	    	ArrayList<String> category_Name = new ArrayList<String>();
	    	while(result.next()) 
	    	{
	    		category_id.add(result.getInt("Category_id"));
	    		category_Name.add(result.getString("Category_Name"));
	        } 
    		assignmentCategory.setCategory_id(category_id);
    		assignmentCategory.setCategory_Name(category_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignmentCategory;
	}

		
	public Content_Container createAssignmentCat(Assignment_Category_Details assignment_Category_Details, Content_Container Content_Container){
		String query = "INSERT INTO Assignment_Category (Category_id, Course_id, Category_Name, Category_Grade, Contribute, Create_Authority) VALUES (Null, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);){
			
			pstatement.setInt(1, assignment_Category_Details.getCourse_id());
			pstatement.setString(2, assignment_Category_Details.getCategory_Name());
			pstatement.setInt(3, assignment_Category_Details.getCategory_Grade());
			pstatement.setInt(4, assignment_Category_Details.getContribute());
			pstatement.setInt(5, assignment_Category_Details.getCreate_Authority());
	        updateQuery = pstatement.executeUpdate();
	        //Content_Container = new Content_Container();
	        Content_Container.setUpdateQuery(updateQuery);
	        if(updateQuery!=0)
	        {
				ResultSet resultCategory_id = pstatement.getGeneratedKeys();
				if(resultCategory_id.next()) 
				{
				       autoIncValue = resultCategory_id.getInt(1);
				       resultCategory_id.close();
				}
				if(Content_Container!=null)
				{
					HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap = Content_Container.getContentcategorymap();
					ArrayList<ArrayList<String>> categoryList = (ArrayList<ArrayList<String>>) contentcategorymap.get(String.valueOf(assignment_Category_Details.getCourse_id()));
					int n=0;
					if(categoryList!=null)
					{
						n = categoryList.size();
						categoryList.add(new ArrayList<String>());
						categoryList.get(n).add(0,null);
						categoryList.get(n).set(0, String.valueOf(autoIncValue));
						categoryList.get(n).add(1,null);
						categoryList.get(n).set(1, assignment_Category_Details.getCategory_Name());
					}
					else
					{
						categoryList = new ArrayList<ArrayList<String>>();
						categoryList.add(new ArrayList<String>());
						categoryList.get(0).add(0,null);
						categoryList.get(0).set(0, String.valueOf(autoIncValue));
						categoryList.get(0).add(1,null);
						categoryList.get(0).set(1, assignment_Category_Details.getCategory_Name());
					}
					contentcategorymap.put(String.valueOf(assignment_Category_Details.getCourse_id()), categoryList);				
					Content_Container.setContentcategorymap(contentcategorymap);
				}
	        }	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Category_Course_Unique"))
			{
				//Content_Container = new Content_Container();
				Content_Container.setUpdateQuery(-1);	
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}
			
	public Content_Container updateAssignmentCat(Assignment_Category_Details assignment_Category_Details, int category_id, Content_Container Content_Container, int index){
		String query = "update Assignment_Category set Category_Name=?, Category_Grade=?, Contribute=?, Create_Authority=? where Category_id="+category_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, assignment_Category_Details.getCategory_Name());
			pstatement.setInt(2, assignment_Category_Details.getCategory_Grade());
			pstatement.setInt(3, assignment_Category_Details.getContribute());
			pstatement.setInt(4, assignment_Category_Details.getCreate_Authority());
	        updateQuery = pstatement.executeUpdate();
	        Content_Container.setUpdateQuery(updateQuery);
	        if(updateQuery!=0)
			{
				if(Content_Container!=null)
				{
					HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap = Content_Container.getContentcategorymap();
					ArrayList<ArrayList<String>> categoryList = (ArrayList<ArrayList<String>>) contentcategorymap.get(String.valueOf(assignment_Category_Details.getCourse_id()));
					if(categoryList!=null)
					{
						categoryList.get(index-1).set(1, assignment_Category_Details.getCategory_Name());
					}					
					contentcategorymap.put(String.valueOf(assignment_Category_Details.getCourse_id()), categoryList);	
					Content_Container.setContentcategorymap(contentcategorymap);
				}
			}	
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Category_Course_Unique"))
			{
				Content_Container.setUpdateQuery(-1);	
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}

	public Content_Container deleteAssignmentCat(int category_id, int course_id, Content_Container Content_Container, int index){
		String query = "Delete from Assignment_Category where Category_id="+category_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				if(Content_Container!=null)
				{
					HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap = Content_Container.getContentcategorymap();
					ArrayList<ArrayList<String>> categoryList = (ArrayList<ArrayList<String>>) contentcategorymap.get(String.valueOf(course_id));
					if(categoryList!=null)
						categoryList.remove(index-1);
					contentcategorymap.put(String.valueOf(course_id), categoryList);	
					Content_Container.setContentcategorymap(contentcategorymap);
					Content_Container.setUpdateQuery(updateQuery);
				}
				else
				{
					Content_Container = new Content_Container();
					Content_Container.setUpdateQuery(updateQuery);
				}

			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}


}
