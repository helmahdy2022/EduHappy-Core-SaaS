package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Course_Details;
import model.beans.admin.Courses;
import model.beans.admin.Students;
import model.dbHelpers.DbConnector;

public class CourseManagement {
	public Courses getCourseList(int grade_id){
		String query = "select * from Courses where Grade_id="+grade_id;
		Courses Courses = new Courses(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> course_id = new ArrayList<Integer>();
	    	ArrayList<String> course_Name = new ArrayList<String>();
	    	ArrayList<Integer> course_Price = new ArrayList<Integer>();
	    	while(result.next()) {
	    		course_id.add(result.getInt("Course_id"));

	    		course_Name.add(result.getString("Course_Name"));	
	    		
	    		course_Price.add(result.getInt("Price"));
	        } 
	    	Courses.setCourse_id(course_id);
	    	Courses.setCourse_Name(course_Name);
	    	Courses.setCourse_Price(course_Price);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Courses;	
	}
	
	public Courses getElectiveCoursesList(int grade_id){
		String query = "select * from Courses where Grade_id="+grade_id+" and Elective='1'";
		Courses Courses = new Courses(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> course_id = new ArrayList<Integer>();
	    	ArrayList<String> course_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		course_id.add(result.getInt("Course_id"));

	    		course_Name.add(result.getString("Course_Name"));	    		
	        } 
	    	Courses.setCourse_id(course_id);
	    	Courses.setCourse_Name(course_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Courses;	
	}
	public Courses getCoursesWithGroups(int class_id){

		Courses Courses = new Courses(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementGrade = connection.createStatement();
				Statement statementCourses = connection.createStatement();
				Statement statementCourseGroups = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();){
			String sGrade = "Select Grade_id from classes where Class_id="+class_id;			
			ResultSet resultGrade = statementGrade.executeQuery(sGrade);
			resultGrade.next();
			int grade_id=resultGrade.getInt("Grade_id");

	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
	    	String sClassDivision = "select * from Classes where  Class_id="+ class_id;
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			resultClassDivision.next();
			division_id = resultClassDivision.getInt("Division_id");
	    	////////////////////////////////////////////////////////////////////////////////////////////////////
	    	String sCourses=null;
			if (division_id==0)
	    	{
				sCourses = "select * from Courses where Grade_id="+grade_id;	    		
	    	}
	    	else
	    	{
		    	sCourses = "select * from courses where Course_id in (select Course_id from Division_Courses where Division_id="+division_id+")";

	    	}			
			ResultSet resultCourses = statementCourses.executeQuery(sCourses);
	    	ArrayList<Integer> Course_id = new ArrayList<Integer>();
	    	ArrayList<String> Course_Name = new ArrayList<String>();
	    	while(resultCourses.next()) 
	    	{

	    		////////////////Check if the Course has groups /////////////////////////
	        	String sCourseGroups = "select * from "+"`Groups`"+" where Course_id="+resultCourses.getInt("Course_id");
	        	ResultSet resultCourseGroups = statementCourseGroups.executeQuery(sCourseGroups);
	        	if (resultCourseGroups.isBeforeFirst())
	        	{
	 
	    	   		Course_id.add(resultCourses.getInt("Course_id"));
	        		Courses.setCourse_id(Course_id);
	        		
	        		Course_Name.add(resultCourses.getString("Course_Name"));
	        		Courses.setCourse_Name(Course_Name);
	    		}
	 
	        } 
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Courses;	
	}
	
	public Course_Details getCourse(int course_id){
	String query = "select * from Courses where Course_id="+course_id;
	Course_Details course_Details = new Course_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			course_Details.setCourse_id(course_id);
			course_Details.setGrade_id(result.getInt("Grade_id"));
			course_Details.setCourse_Name(result.getString("Course_Name"));
			course_Details.setCourse_Grade(result.getInt("course_Grade"));
			course_Details.setAttendance_Weight(result.getInt("attendance_Weight"));
			course_Details.setAttendance_Grade(result.getInt("attendance_Grade"));
			course_Details.setPresent_Grade(result.getInt("present_Grade"));
			course_Details.setAbsent_Grade(result.getInt("absent_Grade"));
			course_Details.setLate_Grade(result.getInt("late_Grade"));
			course_Details.setExcused_Grade(result.getInt("excused_Grade"));
			course_Details.setContribute(result.getInt("contribute"));
			course_Details.setReport_Display(result.getInt("Report_Display"));
			course_Details.setSubject_id(result.getInt("subject_id"));
			course_Details.setElective(result.getInt("elective"));
			course_Details.setPrice(result.getInt("price"));
		}
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return course_Details;
}

public int createCourse(Course_Details course_Details){
	String query = "INSERT INTO Courses (Course_id, Course_Name, Grade_id, Course_Grade, Contribute, Attendance_Weight, Attendance_Grade, Present_Grade, Absent_Grade, Late_Grade, Excused_Grade, Subject_id, Elective, Report_Display, Price) VALUES (Null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		pstatement.setString(1, course_Details.getCourse_Name());
		pstatement.setInt(2, course_Details.getGrade_id());
		pstatement.setInt(3, course_Details.getCourse_Grade());
		pstatement.setInt(4, course_Details.getContribute());
		pstatement.setInt(5, course_Details.getAttendance_Weight());
		pstatement.setInt(6, course_Details.getAttendance_Grade());
		pstatement.setInt(7, course_Details.getPresent_Grade());
		pstatement.setInt(8, course_Details.getAbsent_Grade());
		pstatement.setInt(9, course_Details.getLate_Grade());
		pstatement.setInt(10, course_Details.getExcused_Grade());
		
		if(course_Details.getSubject_id()==0)
		{
			pstatement.setNull(11, java.sql.Types.NULL);
		}
		else
		{
			pstatement.setInt(11, course_Details.getSubject_id());
		}
		
		pstatement.setInt(12, course_Details.getElective());
		pstatement.setInt(13, course_Details.getReport_Display());
		if(course_Details.getPrice()==0)
		{
			pstatement.setNull(14, java.sql.Types.NULL);
		}
		else
		{
			pstatement.setInt(14, course_Details.getPrice());
		}
		updateQuery = pstatement.executeUpdate();
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Course_Grade_Unique"))
			updateQuery=-1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int updateCourse(Course_Details course_Details, int course_id){
	String query = "update Courses set Course_Name=?, Grade_id=?, Course_Grade=?, Contribute=?, Attendance_Weight =?, Attendance_Grade=?, Present_Grade=?, Absent_Grade=?, Late_Grade=?, Excused_Grade=?, Subject_id=? , Elective=?, Report_Display=?, Price=? where Course_id="+course_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		/////////////////////Get old status grades /////////////////////////////////////////		
		Course_Details original_course_Details = new Course_Details();
		original_course_Details = getCourse(course_id);
		int present_Grade = original_course_Details.getPresent_Grade();
		int absent_Grade = original_course_Details.getAbsent_Grade();
		int late_Grade = original_course_Details.getLate_Grade();
		int excused_Grade = original_course_Details.getExcused_Grade();		
		///////////////////////////////////////////////////////////////////////////////////
		
		
		pstatement.setString(1, course_Details.getCourse_Name());
		pstatement.setInt(2, course_Details.getGrade_id());
		pstatement.setInt(3, course_Details.getCourse_Grade());
		pstatement.setInt(4, course_Details.getContribute());
		pstatement.setInt(5, course_Details.getAttendance_Weight());
		pstatement.setInt(6, course_Details.getAttendance_Grade());
		pstatement.setInt(7, course_Details.getPresent_Grade());
		pstatement.setInt(8, course_Details.getAbsent_Grade());
		pstatement.setInt(9, course_Details.getLate_Grade());
		pstatement.setInt(10, course_Details.getExcused_Grade());
		if(course_Details.getSubject_id()==0)
		{
			pstatement.setNull(11, java.sql.Types.NULL);
		}
		else
		{
			pstatement.setInt(11, course_Details.getSubject_id());
		}
		pstatement.setInt(12, course_Details.getElective());
		pstatement.setInt(13, course_Details.getReport_Display());
		if(course_Details.getPrice()==0)
		{
			pstatement.setNull(14, java.sql.Types.NULL);
		}
		else
		{
			pstatement.setInt(14, course_Details.getPrice());
		}
        updateQuery = pstatement.executeUpdate();
       /////////////////////////////////////////Update graded sessions if there is a change in any of the statuses grades/////////////
       if(present_Grade!=course_Details.getPresent_Grade()) 
       {
    	   String sPresentSessions = "update attendance set session_grade=? where status='Present' and course_id="+course_id;
    	   PreparedStatement pstatementPresentSessions = connection.prepareStatement(sPresentSessions);
    	   pstatementPresentSessions.setInt(1, course_Details.getPresent_Grade()); 
    	   pstatementPresentSessions.executeUpdate();
    	   pstatementPresentSessions.close();
       }
       if(absent_Grade!=course_Details.getAbsent_Grade()) 
       {
    	   String sAbsentSessions = "update attendance set session_grade=? where status='Absent' and course_id="+course_id;
    	   PreparedStatement pstatementAbsentSessions = connection.prepareStatement(sAbsentSessions);
    	   pstatementAbsentSessions.setInt(1, course_Details.getAbsent_Grade());
    	   pstatementAbsentSessions.executeUpdate();
    	   pstatementAbsentSessions.close();
       }
       if(late_Grade!=course_Details.getLate_Grade()) 
       {
    	   String sLateSessions = "update attendance set session_grade=? where status='Late' and course_id="+course_id;
    	   PreparedStatement pstatementLateSessions = connection.prepareStatement(sLateSessions);
    	   pstatementLateSessions.setInt(1, course_Details.getLate_Grade()); 
    	   pstatementLateSessions.executeUpdate();
    	   pstatementLateSessions.close();
       }
       if(excused_Grade!=course_Details.getExcused_Grade()) 
       {
    	   String sExcusedSessions = "update attendance set session_grade=? where status='Excused' and course_id="+course_id;
    	   PreparedStatement pstatementExcusedSessions = connection.prepareStatement(sExcusedSessions);
    	   pstatementExcusedSessions.setInt(1, course_Details.getExcused_Grade()); 
    	   pstatementExcusedSessions.executeUpdate();
    	   pstatementExcusedSessions.close();
       }
       //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Course_Grade_Unique"))
			updateQuery=-1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteCourse(int course_id){
	String query = "Delete from Courses where Course_id="+course_id;
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
public Courses getElectiveCourses(int class_id) {
	Courses Courses = new Courses(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statementGrade = connection.createStatement();
			Statement statementCourses = connection.createStatement();){
		String sGrade = "Select Grade_id from classes where Class_id="+class_id;			
		ResultSet resultGrade = statementGrade.executeQuery(sGrade);
		resultGrade.next();
		int grade_id=resultGrade.getInt("Grade_id");    	
		String sCourses = "select * from Courses where Grade_id="+grade_id+" and Elective='1'";	    		
		
		ResultSet resultCourses = statementCourses.executeQuery(sCourses);
    	ArrayList<Integer> Course_id = new ArrayList<Integer>();
    	ArrayList<String> Course_Name = new ArrayList<String>();
    	while(resultCourses.next()) 
    	{
	   		Course_id.add(resultCourses.getInt("Course_id"));
    		Courses.setCourse_id(Course_id);
    		
    		Course_Name.add(resultCourses.getString("Course_Name"));
    		Courses.setCourse_Name(Course_Name); 
        } 
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Courses;	
}

public  ArrayList<ArrayList<String>> getStudentElectives(ArrayList<Integer> Student_id){
	ArrayList<ArrayList<String>> Electives = new ArrayList<ArrayList<String>>();
try (Connection connection = DbConnector.connectToDb();
	 Statement statement = connection.createStatement();){
				  
	//////////////////////Retrieve Student Electives////////////////////////////////////
	int j=0;
	for (int student_id : Student_id)
	{
		Statement statementStudentElectives = null;
		ResultSet resultStudentElectives = null;
    	statementStudentElectives = connection.createStatement();
		String sStudentElectives = "select * from Courses where Course_id in (select Course_id from Student_Electives where Student_id ="+student_id+")"
				+ " and Course_id in (select course_id from courses where grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))";
		resultStudentElectives = statementStudentElectives.executeQuery(sStudentElectives);
		while (resultStudentElectives.next())
		{
			/////fill in the student Electives array////////	
			Electives.add(new ArrayList<String>());
			Electives.get(j).add(0,null);
			Electives.get(j).set(0, String.valueOf(student_id));
			Electives.add(new ArrayList<String>());
			Electives.get(j).add(1,null);
			Electives.get(j).set(1, resultStudentElectives.getString("Course_id"));
    		
			Electives.get(j).add(2,null);
			Electives.get(j).set(2, resultStudentElectives.getString("Course_Name"));
			j=j+1;
		}
	}

	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return Electives;	
}

public Students getElectiveStudents(int course_id){
String query = "select * from students where student_id in (select student_id from Student_Electives where Course_id="+course_id+")"
		+ " and student_id in(select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id="+course_id+")))";
Students Students = new Students();
try (Connection connection = DbConnector.connectToDb();
	 Statement statement = connection.createStatement();){
				
	ResultSet result = statement.executeQuery(query);      
	////////////////Retrieve students in that group /////////////////////
	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	while (result.next())
	{
		Student_id.add(result.getInt("Student_id"));
		First_Name.add(result.getString("First_Name"));
		Last_Name.add(result.getString("Last_Name"));
		Username.add(result.getString("Username"));
		
	}
	Students.setStudent_id(Student_id);
	Students.setFirst_Name(First_Name);
	Students.setLast_Name(Last_Name);
	Students.setUsername(Username);
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return Students;	
}
public boolean checkStudentElective(int student_id, int course_id){
	String sCheckStudentElectives = "select * from Student_Electives where student_id="+student_id+" and course_id="+course_id;
	boolean studentElectiveExist=false;
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(sCheckStudentElectives);
		if (result.isBeforeFirst())
		{
			studentElectiveExist = true;
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return studentElectiveExist;
}
public String addStudentToElective(int class_id, int course_id, HttpServletRequest request){
	String query = "select Student_id from Students where Class_id="+class_id;
	String StudentGroups = "INSERT INTO Student_Electives (Course_id, Student_id) VALUES (?, ?)";
	int updateQuery = 0;
	String message="";

	
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			PreparedStatement pstatement = connection.prepareStatement(StudentGroups);
			Statement statementCheckStudentElectives = connection.createStatement();){
		
		ResultSet result = statement.executeQuery(query); 
       	while (result.next())
    	{
       		boolean studentExistsInElective = false;
       		int student_id=0;
    		if(request.getParameter("Student_id"+result.getString("Student_id"))!=null)
    		{
    			student_id = Integer.parseInt(request.getParameter("Student_id"+result.getString("Student_id")));
    			String sCheckStudentElectives = "select * from Student_Electives where student_id="+student_id+" and course_id="+course_id;
    			ResultSet resultCheckStudentElectives = statementCheckStudentElectives.executeQuery(sCheckStudentElectives);
				if(resultCheckStudentElectives.isBeforeFirst())
				{
					studentExistsInElective = true;
				}
    		}
			pstatement.setInt(1, course_id);
			pstatement.setInt(2, student_id);
			if (student_id!=0)
			{
				if(studentExistsInElective==false)
				{
					updateQuery = pstatement.executeUpdate();
					if(updateQuery!=0)
						message= message +"Student_id "+student_id+" has been added to the selected Elective Course<br>";
					else
						message= message +"Student_id "+student_id+" not added to the selected Elective Course<br>";
				}
				else
				{
					message= message + "Student_id "+student_id+" not added. She/He is already assigned to the same elective course<br>";
				}
			}
    	}
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}

public int deleteStudentFromElective(int student_id, int course_id){
	String query = "Delete from Student_Electives where course_id="+course_id+" and Student_id="+student_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query)){
		
		updateQuery = pstatement.executeUpdate();
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int addParentChildToElective(int student_id, int course_id, int parent_id){
	String StudentGroups = "INSERT INTO Student_Electives (Course_id, Student_id, Subscription_Date, Payment_Renewal_Date, Parent_id) VALUES (?, ?, ?, ?, ?)";
	int updateQuery = 0;	
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			PreparedStatement pstatement = connection.prepareStatement(StudentGroups);
			Statement statementCheckStudentElectives = connection.createStatement();){

			pstatement.setInt(1, course_id);
			pstatement.setInt(2, student_id);
			
			Date today = new Date();
			pstatement.setDate(3, new java.sql.Date(today.getTime()));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);            
			calendar.add(Calendar.WEEK_OF_YEAR , 4);
			Date payment_Renewal_Date = calendar.getTime();
			pstatement.setDate(4, new java.sql.Date(payment_Renewal_Date.getTime()));
			pstatement.setInt(5, parent_id);
			updateQuery = pstatement.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int setCourseEndDate(int course_id, int student_id, Date group_start_date){
	String query = "update Student_Electives set course_end_date=? where course_id="+course_id+" and student_id="+student_id;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(group_start_date);            
		calendar.add(Calendar.WEEK_OF_YEAR , 4);
		Date course_end_date = calendar.getTime();
		pstatement.setDate(1, new java.sql.Date(course_end_date.getTime()));
		updateQuery = pstatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
public int setCourseRenewalDates(int course_id, int student_id){
	String query = "update Student_Electives set course_end_date=?, payment_renewal_date=? where course_id="+course_id+" and student_id="+student_id;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);
			Statement statementCourseCurrentDates = connection.createStatement();){

		String sCourseCurrentDates = "select course_end_date, payment_renewal_date from Student_Electives where canceled<>1 and course_id="+course_id+" and student_id="+student_id;
		ResultSet resultCourseCurrentDates = statementCourseCurrentDates.executeQuery(sCourseCurrentDates);
		while(resultCourseCurrentDates.next())
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(resultCourseCurrentDates.getDate("course_end_date"));            
			calendar.add(Calendar.WEEK_OF_YEAR , 4);
			Date course_end_date = calendar.getTime();
			pstatement.setDate(1, new java.sql.Date(course_end_date.getTime()));
			
			calendar.setTime(resultCourseCurrentDates.getDate("payment_renewal_date"));            
			calendar.add(Calendar.WEEK_OF_YEAR , 4);
			Date payment_renewal_date = calendar.getTime();
			pstatement.setDate(2, new java.sql.Date(payment_renewal_date.getTime()));
			
			updateQuery = pstatement.executeUpdate();
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
public Courses getStudentSubscribedElectives(int student_id) throws ParseException{
	String sStudentElectives = "select * from Courses where Course_id in (select Course_id from Student_Electives where Student_id ="+student_id+")"
			+ " and Course_id in (select course_id from courses where grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))";

	Courses Courses = new Courses(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementSubscribedCourse = connection.createStatement();
			Statement statementStudentGroup = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(sStudentElectives);
	   	ArrayList<Integer> course_id = new ArrayList<Integer>();
    	ArrayList<String> course_Name = new ArrayList<String>();
    	ArrayList<Integer> course_Price = new ArrayList<Integer>();
    	ArrayList<Integer> course_Canceled = new ArrayList<Integer>();
    	ArrayList<String> course_end_date = new ArrayList<String>();
    	ArrayList<String> payment_renewal_date = new ArrayList<String>();
	   	ArrayList<Integer> group_id = new ArrayList<Integer>();
    	ArrayList<String> group_Name = new ArrayList<String>();
    	while(result.next()) {
    		course_id.add(result.getInt("Course_id"));

    		course_Name.add(result.getString("Course_Name"));	
    		
    		course_Price.add(result.getInt("Price"));
    		////////////Check if the course subscription canceled//////////////////
    		String sSubscribedCourse = "Select * from student_electives where course_id="+result.getInt("Course_id")+" and Student_id="+student_id;
    		ResultSet resultSubscribedCourse = statementSubscribedCourse.executeQuery(sSubscribedCourse);
    		while(resultSubscribedCourse.next())
    		{
    			course_Canceled.add(resultSubscribedCourse.getInt("Canceled"));
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			String course_end_Date = sdf.format(resultSubscribedCourse.getDate("course_end_date"));
    			String payment_renewal_Date = sdf.format(resultSubscribedCourse.getDate("payment_renewal_date"));
    			course_end_date.add(course_end_Date);
    			payment_renewal_date.add(payment_renewal_Date);
    			///////Get Group Name/////////////////////////////////////////
    			String sStudentGroup = "select * from "+"`Groups`"+" where course_id="+resultSubscribedCourse.getInt("Course_id")+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
    			ResultSet resultStudentGroup = statementStudentGroup.executeQuery(sStudentGroup);
    			int Group_id=0;
    			String Group_Name = null;
    			while(resultStudentGroup.next())
    			{
    				Group_id = resultStudentGroup.getInt("group_id");
    				Group_Name = resultStudentGroup.getString("group_name");
    			}
        		group_id.add(Group_id);

        		group_Name.add(Group_Name);
    		}
    		//////////////////////////////////////////////////////////////////////
        } 
    	Courses.setCourse_id(course_id);
    	Courses.setCourse_Name(course_Name);
    	Courses.setCourse_Price(course_Price);
    	Courses.setCourse_Canceled(course_Canceled);
    	Courses.setCourse_end_date(course_end_date);
    	Courses.setPayment_renewal_date(payment_renewal_date);
    	Courses.setGroup_id(group_id);
    	Courses.setGroup_Name(group_Name);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Courses;	
}
public Courses getNonSubscribedCourseList(int grade_id, int student_id){
	String query = "select * from Courses where Grade_id="+grade_id+" and course_id not in (select course_id from student_electives where student_id="+student_id+")";
	Courses Courses = new Courses(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
	   	ArrayList<Integer> course_id = new ArrayList<Integer>();
    	ArrayList<String> course_Name = new ArrayList<String>();
    	ArrayList<Integer> course_Price = new ArrayList<Integer>();
    	while(result.next()) {
    		course_id.add(result.getInt("Course_id"));

    		course_Name.add(result.getString("Course_Name"));	
    		
    		course_Price.add(result.getInt("Price"));
        } 
    	Courses.setCourse_id(course_id);
    	Courses.setCourse_Name(course_Name);
    	Courses.setCourse_Price(course_Price);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Courses;	
}
public int unsubscribeChildFromElective(int course_id, int student_id){
	String query = "update Student_Electives set canceled=?, cancel_date=? where course_id="+course_id+" and student_id="+student_id;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		Date today = new Date();
		pstatement.setInt(1, 1);
		pstatement.setDate(2, new java.sql.Date(today.getTime()));
		updateQuery = pstatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
public int resubscribeChildIntoElective(int course_id, int student_id){
	String query = "update Student_Electives set canceled=?, cancel_date=? where course_id="+course_id+" and student_id="+student_id;
	int updateQuery = 0;		
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		//Date today = new Date();
		pstatement.setInt(1, 0);
		pstatement.setDate(2, null);
		updateQuery = pstatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
}
