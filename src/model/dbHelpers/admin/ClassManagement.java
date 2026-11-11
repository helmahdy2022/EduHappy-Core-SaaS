package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Class_Details;
import model.beans.admin.Classes;
import model.beans.admin.Course_Details;
import model.dbHelpers.DbConnector;

public class ClassManagement {
	public Class_Details getClass(int class_id){
	String query = "select * from Classes where Class_id="+class_id;
	Class_Details class_Details = new Class_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementStudentsInClass = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			class_Details.setClass_id(class_id);
			class_Details.setClass_Name(result.getString("Class_Name"));
		}   
		//////////Check if the class has students and prevent delete if it has /////////////////////
		String sStudentsInClass = "select student_id from Students where Class_id="+class_id;
		ResultSet resultStudentsInClass = statementStudentsInClass.executeQuery(sStudentsInClass);
		if(resultStudentsInClass.isBeforeFirst())
		{
			class_Details.setDeleteClass("NO");
		}
		else
		{
			class_Details.setDeleteClass("YES");
		}
		////////////////////////////////////////////////////////////////////////////////////////////
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return class_Details;
}
	public int getClassByGrade(int grade_id){
		String query = "SELECT * FROM classes WHERE grade_id="+grade_id+" limit 1";
		int class_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				class_id = result.getInt("Class_id");
			}			
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_id;
	}
	
public int createClass(Class_Details class_Details){
	String query = "INSERT INTO Classes (Class_id, Class_Name, Grade_id) VALUES (Null,?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		pstatement.setString(1, class_Details.getClass_Name());
		pstatement.setInt(2, class_Details.getGrade_id());
        updateQuery = pstatement.executeUpdate();
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Class_Grade_Unique"))
			updateQuery=-1;		
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int updateClass(Class_Details class_Details, int class_id){
	String query = "update Classes set Class_Name=? where Class_id="+class_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		pstatement.setString(1, class_Details.getClass_Name());
        updateQuery = pstatement.executeUpdate();
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Class_Grade_Unique"))
			updateQuery=-1;		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteClass(int class_id){
	String query = "Delete from Classes where Class_id="+class_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){

		updateQuery = pstatement.executeUpdate();

       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public Classes getClassesNoDivision(int grade_id){
String query = "select * from Classes where Grade_id="+grade_id+" and division_id is null";
Classes Classes = new Classes();
try (Connection connection = DbConnector.connectToDb();
	 Statement statement = connection.createStatement();){
				
	ResultSet result = statement.executeQuery(query);
	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	ArrayList<String> Class_Name = new ArrayList<String>();
	while(result.next()) 
	{
		Class_id.add(result.getInt("Class_id"));

		
		Class_Name.add(result.getString("Class_Name"));
		
    } 
	Classes.setClass_id(Class_id);
	Classes.setClass_Name(Class_Name);
   
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return Classes;
}
public int createClassByParent(int course_id){
	String query = "INSERT INTO Classes (Class_id, Class_Name, Grade_id, course_id, class_index, no_of_groups) VALUES (Null,?, ?, ?, ?, ?)";
	int updateQuery = 0;
	int class_id=0;
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			Statement statementLastClassIndex = connection.createStatement();){
		
		String sLastClassIndex = "SELECT MAX(class_index) FROM classes WHERE course_id="+course_id;
		ResultSet resultLastClassIndex = statementLastClassIndex.executeQuery(sLastClassIndex);
		int last_class_index = 0;
		int class_index = 0;
		while(resultLastClassIndex.next())
		{
			last_class_index = resultLastClassIndex.getInt(1);
		}
		class_index = last_class_index + 1;
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();
		course_Details = courseManagement.getCourse(course_id);
		String class_name = "C-" + course_Details.getCourse_Name() +"-"+ Integer.toString(class_index);
		int grade_id = course_Details.getGrade_id();
		pstatement.setString(1, class_name);
		pstatement.setInt(2, grade_id);
		pstatement.setInt(3, course_id);
		pstatement.setInt(4, class_index);
		pstatement.setInt(5, 0);
        updateQuery = pstatement.executeUpdate();
		if(updateQuery!=0)
		{
			ResultSet resultClass_id = pstatement.getGeneratedKeys();
			if(resultClass_id.next()) 
			{
			       class_id = resultClass_id.getInt(1);
			}
		}
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Class_Grade_Unique"))
			updateQuery=-1;		
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return class_id;
}

}
