package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Group_Details;
import model.beans.admin.Groups;
import model.beans.admin.Students;
import model.dbHelpers.DbConnector;

public class GroupManagement {
	public Group_Details getGroup(int group_id){
	String query = "select * from "+"`Groups`"+" where Group_id="+group_id;
	Group_Details group_Details = new Group_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementGroupStudents = connection.createStatement()){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			group_Details.setGroup_id(group_id);
			group_Details.setGroup_Name(result.getString("Group_Name"));
			group_Details.setCourse_id(result.getInt("Course_id"));
		}       

		////////////////Retrieve students in that group /////////////////////
		String sGroupStudents = "select * from students where student_id in (select student_id from Student_Groups where Group_id="+group_id+")"
				+ " and student_id in(select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id=(select course_id from "+"`Groups`"+" where group_id="+group_id+" ))))";
		ResultSet resultGroupStudents = statementGroupStudents.executeQuery(sGroupStudents);
		Students Students = new Students();
    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
    	ArrayList<String> First_Name = new ArrayList<String>();
    	ArrayList<String> Last_Name = new ArrayList<String>();
    	ArrayList<String> Username = new ArrayList<String>();
		while (resultGroupStudents.next())
		{
    		Student_id.add(resultGroupStudents.getInt("Student_id"));


    		
    		First_Name.add(resultGroupStudents.getString("First_Name"));
    		
    		
    		Last_Name.add(resultGroupStudents.getString("Last_Name"));
    		
    		
    		Username.add(resultGroupStudents.getString("Username"));
    		
		}
		Students.setStudent_id(Student_id);
		Students.setFirst_Name(First_Name);
		Students.setLast_Name(Last_Name);
		Students.setUsername(Username);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return group_Details;
}
	public Groups getGroupList(int course_id){
		String query = "select * from "+"`Groups`"+" where Course_id="+course_id;
		Groups Groups = new Groups(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> group_id = new ArrayList<Integer>();
	    	ArrayList<String> group_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		group_id.add(result.getInt("Group_id"));

	    		group_Name.add(result.getString("Group_Name"));	    		
	        } 
	    	Groups.setGroup_id(group_id);
	    	Groups.setGroup_Name(group_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Groups;	
}
	public Students getGroupStudents(int group_id){
	String query = "select * from students where student_id in (select student_id from Student_Groups where Group_id="+group_id+")"
			+ " and student_id in(select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id=(select course_id from "+"`Groups`"+" where group_id="+group_id+" ))))";
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

	
	public  ArrayList<ArrayList<String>> getStudentGroups(ArrayList<Integer> Student_id){
		ArrayList<ArrayList<String>> Groups = new ArrayList<ArrayList<String>>();
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementCourseGroup = connection.createStatement();){
					  
		//////////////////////Retrieve Student Groups////////////////////////////////////
		int j=0;
		for (int student_id : Student_id)
		{
			Statement statementStudentGroups = null;
			ResultSet resultStudentGroups = null;
	    	statementStudentGroups = connection.createStatement();
			String sStudentGroups = "select * from "+"`Groups`"+" where Group_id in (select Group_id from Student_Groups where Student_id ="+student_id+")"
					+ " and Group_id in (select group_id from "+"`Groups`"+" where course_id in (select course_id from courses where grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+"))))";
			resultStudentGroups = statementStudentGroups.executeQuery(sStudentGroups);
			while (resultStudentGroups.next())
			{
				/////fill in the student groups array////////	
	    		Groups.add(new ArrayList<String>());
	    		Groups.get(j).add(0,null);
	    		Groups.get(j).set(0, String.valueOf(student_id));
	    		Groups.add(new ArrayList<String>());
	    		Groups.get(j).add(1,null);
	    		Groups.get(j).set(1, resultStudentGroups.getString("Group_id"));
	    		
	    		Groups.get(j).add(2,null);
	    		Groups.get(j).set(2, resultStudentGroups.getString("Group_Name"));
	    		///////////////////////Get the Course name for each group /////////////////////////////
	    		String sCourseGroup = "select Course_Name from Courses where Course_id="+resultStudentGroups.getString("Course_id");
	    		ResultSet resultCourseGroup = statementCourseGroup.executeQuery(sCourseGroup);
	    		while(resultCourseGroup.next())
	    		{
		           	Groups.get(j).add(3,null);
		        	Groups.get(j).set(3, resultCourseGroup.getString("Course_Name"));
	    		}
				j=j+1;
			}
		}

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Groups;	
}
	
	
public int createGroup(Group_Details group_Details){
	String query = "INSERT INTO "+"`Groups`"+" (Group_id, Group_Name, Course_id, Group_Index) VALUES (Null,?, ?, ?)";
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);
			Statement statementMaxGroupIndex = connection.createStatement();){
	
		String sMaxGroupIndex = "select max(group_index) from "+"`Groups`"+" where course_id="+group_Details.getCourse_id();
		ResultSet resultMaxGroupIndex = statementMaxGroupIndex.executeQuery(sMaxGroupIndex);
		int maxGroupIndex = 0;
		while(resultMaxGroupIndex.next())
		{
			 maxGroupIndex=resultMaxGroupIndex.getInt(1) + 1;
		}
		pstatement.setString(1, group_Details.getGroup_Name());
		pstatement.setInt(2, group_Details.getCourse_id());
		pstatement.setInt(3, maxGroupIndex);
        updateQuery = pstatement.executeUpdate();
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Group_Course_Unique"))
			updateQuery = -1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int createGroupByParent(int course_id){
	String query = "INSERT INTO "+"`Groups`"+" (Group_id, Group_Name, Course_id, Group_Index, Created_By_Parent, start_date, class_id) VALUES (Null,?, ?, ?, ?, ?, ?)";
	int updateQuery = 0;
	int group_id=0;
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			Statement statementMaxGroupIndex = connection.createStatement();
			Statement statementClassDetails = connection.createStatement();
			Statement statementGetNumberOfGroups = connection.createStatement();
			Statement statementUpdateClassNumberOfGroups = connection.createStatement();){
		
		//////////////////////////////Create Group Name //////////////////////
		String sMaxGroupIndex = "select max(group_index) from "+"`Groups`"+" where course_id="+course_id;
		ResultSet resultMaxGroupIndex = statementMaxGroupIndex.executeQuery(sMaxGroupIndex);
		int maxGroupIndex = 0;
		String group_Name = null;
		while(resultMaxGroupIndex.next())
		{
			 maxGroupIndex=resultMaxGroupIndex.getInt(1) + 1;
			group_Name = "Group "+maxGroupIndex;
		}
		Date today = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek==6)
		{
			calendar.add(Calendar.DAY_OF_YEAR, 15);
		}
		else
		{
			calendar.add(Calendar.DAY_OF_YEAR, 14);
		}		
		Date start_date = calendar.getTime();
		//////////////////////////////////////////////////////////////////////
		pstatement.setString(1, group_Name);
		pstatement.setInt(2, course_id);
		pstatement.setInt(3, maxGroupIndex);
		pstatement.setInt(4, 1);
		pstatement.setDate(5, new java.sql.Date(start_date.getTime()));	
		///////Get the class_id///////////////////////////////////////
		int class_id=0;
		String sClassDetails = "SELECT * FROM classes WHERE course_id="+course_id+" and no_of_groups<9";
		ResultSet resultClassDetails = statementClassDetails.executeQuery(sClassDetails);
		while(resultClassDetails.next())
		{
			class_id=resultClassDetails.getInt("class_id");
		}
		//////////create new class if we didn't find one from the above//////////////
		if(class_id==0)
		{
			ClassManagement classManagement = new ClassManagement();
			class_id = classManagement.createClassByParent(course_id);
		}
		pstatement.setInt(6, class_id);
		/////////////////////////////////////////////////////////////////////////////////////
		updateQuery = pstatement.executeUpdate();
		if(updateQuery!=0)
		{
			ResultSet resultGroup_id = pstatement.getGeneratedKeys();
			if(resultGroup_id.next()) 
			{
			       group_id = resultGroup_id.getInt(1);
			       ////////////update class number of groups////////////////
			       String sGetNumberOfGroups = "select no_of_groups from classes where class_id="+class_id;
			       ResultSet resultGetNumberOfGroups = statementGetNumberOfGroups.executeQuery(sGetNumberOfGroups);
			       int current_no_of_groups = 0;
			       while(resultGetNumberOfGroups.next())
			       {
			    	   current_no_of_groups = resultGetNumberOfGroups.getInt("No_Of_Groups");    	  
			       }
			       int no_of_groups = current_no_of_groups+1;
		    	   String sUpdateClassNumberOfGroups = "update classes set no_of_groups ="+no_of_groups+" where class_id = "+class_id;
		    	   //pstatement.setInt(1, no_of_groups);
		           updateQuery = statementUpdateClassNumberOfGroups.executeUpdate(sUpdateClassNumberOfGroups);		
			}
		}
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Group_Course_Unique"))
			updateQuery = -1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return group_id;
}

public int updateGroup(Group_Details group_Details, int group_id){
	String query = "update "+"`Groups`"+" set Group_Name=? where group_id="+group_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		pstatement.setString(1, group_Details.getGroup_Name());
        updateQuery = pstatement.executeUpdate();
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Group_Course_Unique"))
			updateQuery = -1;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteGroup(int group_id){
	String query = "Delete from "+"`Groups`"+" where Group_id="+group_id;
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
public String addStudentToGroup(int class_id, int course_id, int group_id, HttpServletRequest request){
	String query = "select Student_id from Students where Class_id="+class_id;
	String StudentGroups = "INSERT INTO Student_Groups (Group_id, Student_id) VALUES (?, ?)";
	int updateQuery = 0;
	String message="";

	
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			PreparedStatement pstatement = connection.prepareStatement(StudentGroups);
			Statement statementCheckStudentGroups = connection.createStatement();){
		
		ResultSet result = statement.executeQuery(query); 
       	while (result.next())
    	{
       		boolean studentHasGroup = false;
       		int student_id=0;
    		if(request.getParameter("Student_id"+result.getString("Student_id"))!=null)
    		{
    			student_id = Integer.parseInt(request.getParameter("Student_id"+result.getString("Student_id")));
    			String sCheckStudentGroups = "select * from Student_Groups where student_id="+student_id+" and group_id in (select group_id from "+"`Groups`"+" where course_id="+course_id+")";
    			ResultSet resultCheckStudentGroups = statementCheckStudentGroups.executeQuery(sCheckStudentGroups);
				if(resultCheckStudentGroups.isBeforeFirst())
				{
					studentHasGroup = true;
				}
    		}
			pstatement.setInt(1, group_id);
			pstatement.setInt(2, student_id);
			if (student_id!=0)
			{
				if(studentHasGroup==false)
				{
					updateQuery = pstatement.executeUpdate();
					if(updateQuery!=0)
						message= message +"Student_id "+student_id+" has been added to the group<br>";
					else
						message= message +"Student_id "+student_id+" not added to the group<br>";
				}
				else
				{
					message= message + "Student_id "+student_id+" not added. She/He is already assigned to another group under the same course<br>";
				}
			}
    	}
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
public Date addParentChildToGroup (int student_id, int course_id){
	String StudentGroups = "INSERT INTO Student_Groups (Group_id, Student_id) VALUES (?, ?)";
	int updateQuery = 0;
	Date start_date = new Date();	
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			PreparedStatement pstatement = connection.prepareStatement(StudentGroups);
			Statement statementCheckStudentGroups = connection.createStatement();
			Statement statementCourseGroups = connection.createStatement();
			Statement statementGroupLessThanTen = connection.createStatement();
			Statement statementGroupStartDate = connection.createStatement();
			Statement statementStratDate = connection.createStatement();
			Statement statementStudentClass = connection.createStatement();
			Statement statementUpdateStudentClass = connection.createStatement();){
		
       		boolean studentHasGroup = false;
       		int group_id = 0; 
			String sCheckStudentGroups = "select * from Student_Groups where student_id="+student_id+" and group_id in (select group_id from "+"`Groups`"+" where course_id="+course_id+")";
			ResultSet resultCheckStudentGroups = statementCheckStudentGroups.executeQuery(sCheckStudentGroups);
			if(resultCheckStudentGroups.isBeforeFirst())
			{
				studentHasGroup = true;
			}   		
			if (student_id!=0)
			{
				if(studentHasGroup==false)
				{
					////////////////////Decide what group will be assigned to that student///////////
					/////Select the group which has students less than 10////////////////
					String sCourseGroups = "select group_id from "+"`Groups`"+" where course_id="+course_id+" and Created_By_Parent=1 order by start_date DESC";
					ResultSet resultCourseGroups = statementCourseGroups.executeQuery(sCourseGroups);
					while (resultCourseGroups.next())
					{
						String sGroupLessThanTen = "SELECT count(student_id) FROM student_groups WHERE group_id="+resultCourseGroups.getInt("Group_id");
						ResultSet resultGroupLessThanTen = statementGroupLessThanTen.executeQuery(sGroupLessThanTen);
						while(resultGroupLessThanTen.next())
						{
							if(resultGroupLessThanTen.getInt(1) <10)
							{
								String sStratDate = "select start_date from "+"`Groups`"+" where group_id="+resultCourseGroups.getInt("Group_id");
								ResultSet resultStratDate = statementStratDate.executeQuery(sStratDate);
								Date today = new Date();
								while(resultStratDate.next())
								{
									if((resultStratDate.getDate("start_date")!=null && today.compareTo(resultStratDate.getDate("start_date"))<0) || resultStratDate.getDate("start_date")==null)
									{
										group_id = resultCourseGroups.getInt("Group_id");
										break;
									}
									
								}
							}
						}							
					}
					////////////in case there is no groups found then create new one////////////////////////////////////
					if(group_id==0)
					{
						////create new group//////////
						
						group_id = createGroupByParent(course_id);
					}
					/////////////////////////////////Get the Group Start Date///////////////////////////////////////////
					if(group_id!=0)
					{
						pstatement.setInt(1, group_id);
						pstatement.setInt(2, student_id);
						updateQuery = pstatement.executeUpdate();
						if(updateQuery!=0)
						{
							String sGroupStartDate = "SELECT * FROM "+"`Groups`"+" WHERE group_id="+group_id;
							ResultSet resultGroupStartDate = statementGroupStartDate.executeQuery(sGroupStartDate);
							
							while(resultGroupStartDate.next())
							{
								start_date = resultGroupStartDate.getDate("start_date");
								int class_id = resultGroupStartDate.getInt("class_id");
								//////////////update student class if it's null/////////////////
								String sStudentClass = "select class_id from students where student_id="+student_id;
								ResultSet resultStudentClass = statementStudentClass.executeQuery(sStudentClass);
								int current_class_id=0;
								while(resultStudentClass.next())
								{
									current_class_id = resultStudentClass.getInt("class_id");
								}
								if(current_class_id ==0)
								{
							    	   String sUpdateStudentClass = "update students set class_id ="+class_id+" where student_id = "+student_id;
							    	   //pstatement.setInt(1, class_id);
							           updateQuery = statementUpdateStudentClass.executeUpdate(sUpdateStudentClass);		
								}
							}
						}
					}
					else
					{
						start_date = null;
					}
					///////////////////////////////////////////////////////////////////////////////////////////////////					
				}
			}
    	
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return start_date;
}
public int deleteStudentFromGroup(int student_id, int group_id){
	String query = "Delete from Student_Groups where Group_id="+group_id+" and Student_id="+student_id;
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

}
