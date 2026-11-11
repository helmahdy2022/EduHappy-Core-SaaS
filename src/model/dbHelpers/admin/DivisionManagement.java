package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Classes;
import model.beans.admin.Courses;
import model.beans.admin.Division_Details;
import model.beans.admin.Divisions;
import model.dbHelpers.DbConnector;

public class DivisionManagement {
	public Division_Details getDivision(int division_id){
	String query = "select * from Divisions where Division_id="+division_id;
	Division_Details division_Details = new Division_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			division_Details.setDivision_id(division_id);
			division_Details.setDivision_Name(result.getString("Division_Name"));
			division_Details.setGrade_id(result.getInt("Grade_id"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return division_Details;
}

	public Courses getDivisionCourses(int grade_id, int division_id){
		String query = "select * from Courses where Grade_id="+grade_id;
		Courses Courses = new Courses(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementCourseDivisions = connection.createStatement();){
	    	
			ResultSet result = statement.executeQuery(query);
	    	
	    	ArrayList<Integer> course_id = new ArrayList<Integer>();
	    	ArrayList<String> course_Name = new ArrayList<String>();
	    	ArrayList<Integer> course_Division_id = new ArrayList<Integer>();
	    	while(result.next()) 
	    	{
	    		course_id.add(result.getInt("Course_id"));
	    		course_Name.add(result.getString("Course_Name"));
		    	String sCourseDivisions = "select * from Division_Courses where Course_id="+result.getString("Course_id")+" and Division_id="+division_id;
		    	ResultSet resultCourseDivisions = statementCourseDivisions.executeQuery(sCourseDivisions);
		    	if (resultCourseDivisions.isBeforeFirst())
		    	{
			    		course_Division_id.add(1);
		    	}
		    	else
		    	{
		    		course_Division_id.add(0);
		    	}
	        } 
			Courses.setCourse_id(course_id);
			Courses.setCourse_Name(course_Name);
			Courses.setCourse_Division_id(course_Division_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Courses;	
	}		
	
	public Classes getDivisionClasses(int grade_id, int division_id){
		String query = "select * from Classes where Grade_id="+grade_id+" and (Division_id="+division_id+" or Division_id is null)";
		Classes Classes = new Classes(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	    	ResultSet result = statement.executeQuery(query);

	    	ArrayList<Integer> class_id = new ArrayList<Integer>();
	    	ArrayList<String> class_Name = new ArrayList<String>();
	    	ArrayList<Integer> class_Division_id = new ArrayList<Integer>();
	    	while(result.next()) 
	    	{
	    		class_id.add(result.getInt("Class_id"));	
	    		class_Name.add(result.getString("Class_Name"));		
	    		class_Division_id.add(result.getInt("Division_id"));
	        } 
			Classes.setClass_id(class_id);
			Classes.setClass_Name(class_Name);
			Classes.setClass_Division_id(class_Division_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Classes;	
	}		
	
	
	public Divisions getDivisionList(int grade_id){
		String query = "select * from Divisions where Grade_id="+grade_id;
		Divisions Divisions = new Divisions(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> division_id = new ArrayList<Integer>();
	    	ArrayList<String> division_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		division_id.add(result.getInt("Division_id"));

	    		division_Name.add(result.getString("Division_Name"));	    		
	        } 
	    	Divisions.setDivision_id(division_id);
	    	Divisions.setDivision_Name(division_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Divisions;	
	}	
public int createDivision(Division_Details division_Details, HttpServletRequest request){
	String query =  "INSERT INTO Divisions (Division_id, Division_Name, Grade_id) VALUES (Null,?, ?)";
	String sDivision_Courses = "INSERT INTO Division_Courses (Division_id, Course_id) VALUES (?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		 PreparedStatement pstatementDivisionCourse=connection.prepareStatement(sDivision_Courses);
		 Statement statement = connection.createStatement()){
		
		pstatement.setString(1, division_Details.getDivision_Name());
		pstatement.setInt(2, division_Details.getGrade_id());
		updateQuery = pstatement.executeUpdate();
        
		ResultSet resultDivision_id = pstatement.getGeneratedKeys();
		int division_id=0;
		if(resultDivision_id.next()) 
		{
			division_id = resultDivision_id.getInt(1);
		}
		//////////////////////////////Retrieve all Courses related to this Grade ////////////////
    	String sCourses = "select * from Courses where Grade_id="+division_Details.getGrade_id();
    	ResultSet resultCourses = statement.executeQuery(sCourses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Link Courses to the Division id //////////////////////////
    	while (resultCourses.next())
    	{
	    	String Course_id = request.getParameter("Course_id"+resultCourses.getString("Course_id"));
    		if (Course_id!=null)
    		{
				
				pstatementDivisionCourse.setInt(1, division_id);
				pstatementDivisionCourse.setString(2, Course_id);
				updateQuery = pstatementDivisionCourse.executeUpdate();
    		}
    	}
		////////////////////////////////////////////////////////////////////////////
		//////////////////////////////Retrieve all Classes related to this Grade ////////////////
    	String sClasses = "select * from Classes where Grade_id='"+division_Details.getGrade_id()+"' and Division_id is null";
    	ResultSet resultClasses = statement.executeQuery(sClasses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Update Classes with the Division id //////////////////////////
    	while (resultClasses.next())
    	{
	    	String Class_id = request.getParameter("Class_id"+resultClasses.getString("Class_id"));
    		String sUpdateClassDivision=null;
    		if (Class_id!=null)
    		{
		    	sUpdateClassDivision = "update Classes set division_id='"+division_id+"' where Class_id="+Class_id;			
				updateQuery = pstatement.executeUpdate(sUpdateClassDivision);
    		}
    	}
		////////////////////////////////////////////////////////////////////////////
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Division_Grade_Unique"))
			updateQuery=-1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int updateDivision(Division_Details division_Details, int division_id, HttpServletRequest request){
	String query = "update Divisions set Division_Name=? where Division_id="+division_id;
	String sDivision_Courses = "INSERT INTO Division_Courses (Division_id, Course_id) VALUES (?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);
		 Statement statement1 = connection.createStatement();
		 Statement statement2 = connection.createStatement();
			Statement statement3 = connection.createStatement();
		 PreparedStatement pstatementDivisionCourse = connection.prepareStatement(sDivision_Courses)){
		
		pstatement.setString(1, division_Details.getDivision_Name());
        updateQuery = pstatement.executeUpdate();
        
		//////////////////////////////Retrieve all Courses related to this Grade ////////////////
    	String sCourses = "select * from Courses where Grade_id="+division_Details.getGrade_id();
    	ResultSet resultCourses = statement1.executeQuery(sCourses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Update Courses with the Division id //////////////////////////
    	while (resultCourses.next())
    	{
	    	String Course_id = request.getParameter("Course_id"+resultCourses.getString("Course_id"));
    		if (Course_id!=null)
    		{
				
		    	String sCourseDivisions = "select * from Division_Courses where Course_id="+Course_id+" and Division_id="+division_id;
		    	ResultSet resultCourseDivisions = statement2.executeQuery(sCourseDivisions);
		    	
		    	if (!resultCourseDivisions.isBeforeFirst())
		    	{
	    			pstatementDivisionCourse.setInt(1, division_id);
	    			pstatementDivisionCourse.setString(2, Course_id);
					updateQuery = pstatementDivisionCourse.executeUpdate();
		    	}

    		}
    		else
    		{

				String sDeleteDivisionCourse = "Delete from Division_Courses where Division_id="+division_id+" and Course_id="+request.getParameter("hiddenCourse_id"+resultCourses.getString("Course_id"));
				pstatement.executeUpdate(sDeleteDivisionCourse);
				
    		}
    	}
		////////////////////////////////////////////////////////////////////////////
		//////////////////////////////Retrieve all Classes related to this Grade ////////////////
    	String sClasses = "select * from Classes where Grade_id='"+division_Details.getGrade_id()+"' and (Division_id="+division_id+" or Division_id is null)";
    	ResultSet resultClasses = statement3.executeQuery(sClasses);
    	//////////////////////////////////////////////////////////////////////////////////////////
		////////////////Update Classes with the Division id //////////////////////////
    	while (resultClasses.next())
    	{
	    	String Class_id = request.getParameter("Class_id"+resultClasses.getString("Class_id"));
    		String sUpdateClass=null;
    		if (Class_id!=null)
    		{
		    	sUpdateClass = "update Classes set division_id='"+division_id+"' where Class_id="+Class_id;		
				updateQuery = pstatement.executeUpdate(sUpdateClass);
    		}
    		else
    		{
    			sUpdateClass = "update Classes set Division_id=NULL where Class_id="+request.getParameter("hiddenClass_id"+resultClasses.getString("Class_id"));			
				updateQuery = pstatement.executeUpdate(sUpdateClass);
    		}
    	}
		////////////////////////////////////////////////////////////////////////////
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Division_Grade_Unique"))
			updateQuery=-1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteDivision(int division_id){
		String query = "Delete from Divisions where Division_id="+division_id;
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
