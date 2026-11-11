package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Courses;
import model.beans.admin.Subject_Details;
import model.beans.admin.Subjects;
import model.dbHelpers.DbConnector;

public class SubjectManagement {
	public Subject_Details getSubject(int subject_id){
	String query = "select * from Subjects where Subject_id="+subject_id;
	Subject_Details subject_Details = new Subject_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			subject_Details.setSubject_id(subject_id);
			subject_Details.setSubject_Name(result.getString("Subject_Name"));
			subject_Details.setGrade_id(result.getInt("Grade_id"));
		}
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return subject_Details;
}

	public Subjects getSubjectList(int grade_id){
		String query = "select * from Subjects where Grade_id="+grade_id;
		Subjects Subjects = new Subjects(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> subject_id = new ArrayList<Integer>();
	    	ArrayList<String> subject_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		subject_id.add(result.getInt("Subject_id"));

	    		subject_Name.add(result.getString("Subject_Name"));	    		
	        } 
	    	Subjects.setSubject_id(subject_id);
	    	Subjects.setSubject_Name(subject_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Subjects;	
	}	
public int createSubject(Subject_Details subject_Details, HttpServletRequest request){
	String query = "INSERT INTO Subjects (Subject_id, Subject_Name, Grade_id) VALUES (Null,?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			Statement statementCourses = connection.createStatement();){
		
		pstatement.setString(1, subject_Details.getSubject_Name());
		pstatement.setInt(2, subject_Details.getGrade_id());
        updateQuery = pstatement.executeUpdate();
        
		ResultSet resultSubject_id = pstatement.getGeneratedKeys();
		int subject_id=0;
		if(resultSubject_id.next()) 
		{
		       subject_id = resultSubject_id.getInt(1);
		}
		//////////////////////////////Retrieve all Courses related to this Grade ////////////////
    	String sCourses = "select * from Courses where Grade_id='"+subject_Details.getGrade_id()+"' and Subject_id is null";
    	ResultSet resultCourses = statementCourses.executeQuery(sCourses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Update Courses with the Subject id //////////////////////////
    	while (resultCourses.next())
    	{
	    	String Course_id = request.getParameter("Course_id"+resultCourses.getString("Course_id"));
    		String sUpdateCourse=null;
    		if (Course_id!=null)
    		{
		    	sUpdateCourse = "update Courses set Subject_id='"+subject_id+"' where Course_id="+Course_id;
		    	//pstatement = connection.prepareStatement(sUpdateCourse);			
				updateQuery = pstatement.executeUpdate(sUpdateCourse);
    		}
    	}
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Subject_Grade_Unique"))
			updateQuery = -1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int updateSubject(Subject_Details subject_Details, int subject_id, HttpServletRequest request){
	String query = "update Subjects set Subject_Name=? where Subject_id="+subject_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);
			Statement statementCourses = connection.createStatement();){
		
		pstatement.setString(1, subject_Details.getSubject_Name());
        updateQuery = pstatement.executeUpdate();
        
		//////////////////////////////Retrieve all Courses related to this Grade ////////////////
    	//String sCourses = "select * from Courses where Grade_id='"+subject_Details.getGrade_id()+"' and Subject_id is null";
    	String sCourses = "select * from Courses where Grade_id='"+subject_Details.getGrade_id()+"' and (Subject_id="+subject_id+" or Subject_id is null)";
    	ResultSet resultCourses = statementCourses.executeQuery(sCourses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Update Courses with the Subject id //////////////////////////
    	while (resultCourses.next())
    	{
	    	String Course_id = request.getParameter("Course_id"+resultCourses.getString("Course_id"));
    		String sUpdateCourse=null;
    		if (Course_id!=null)
    		{
		    	sUpdateCourse = "update Courses set Subject_id='"+subject_id+"' where Course_id="+Course_id;			
				updateQuery = pstatement.executeUpdate(sUpdateCourse);
    		}
    		else
    		{
    			String x = request.getParameter("hiddenCourse_id"+resultCourses.getString("Course_id"));
		    	sUpdateCourse = "update Courses set Subject_id=NULL where Course_id="+x;
				updateQuery = pstatement.executeUpdate(sUpdateCourse);
    		}
    	}
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Subject_Grade_Unique"))
			updateQuery = -1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteSubject(int subject_id){
		String query = "Delete from Subjects where Subject_id="+subject_id;
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

public Courses doGetSubjectCourses(int grade_id, int subject_id){
	Courses Courses = new Courses();
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
		String query = "select * from Courses where Grade_id='"+grade_id+"' and (Subject_id="+subject_id+" or Subject_id is null)";
		
		ResultSet resultset = statement.executeQuery(query);
	   	ArrayList<Integer> course_id = new ArrayList<Integer>();
    	ArrayList<String> course_Name = new ArrayList<String>();
    	ArrayList<Integer> course_Subject_id = new ArrayList<Integer>();
        while(resultset.next()) {
    		
        	course_id.add(Integer.parseInt(resultset.getString("Course_id")));
        	course_Name.add(resultset.getString("Course_Name"));
        	course_Subject_id.add(resultset.getInt("Subject_id"));
    		
        } 
        Courses.setCourse_id(course_id);
        Courses.setCourse_Name(course_Name);
        Courses.setCourse_Subject_id(course_Subject_id);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Courses;
}
public Courses doGetCoursesNoSubject(int grade_id){
	Courses Courses = new Courses();
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
		String query = "select * from Courses where Grade_id='"+grade_id+"' and Subject_id is null";
		
		ResultSet resultset = statement.executeQuery(query);
	   	ArrayList<Integer> course_id = new ArrayList<Integer>();
    	ArrayList<String> course_Name = new ArrayList<String>();
    	ArrayList<Integer> course_Subject_id = new ArrayList<Integer>();
        while(resultset.next()) {
    		
        	course_id.add(Integer.parseInt(resultset.getString("Course_id")));
        	course_Name.add(resultset.getString("Course_Name"));
    		
        } 
        Courses.setCourse_id(course_id);
        Courses.setCourse_Name(course_Name);
        Courses.setCourse_Subject_id(course_Subject_id);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Courses;
}
}
