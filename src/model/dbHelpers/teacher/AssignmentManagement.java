package model.dbHelpers.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.beans.teacher.Assignment_Dates;
import model.beans.teacher.Assignment_Details;
import model.beans.teacher.Assignments;
import model.dbHelpers.DbConnector;

public class AssignmentManagement {
	public Assignments getAssignmentsList(int teacher_id, int course_id, String category_id, String lesson_id, String lesson_idSQL, String page){
		Assignments Assignments = new Assignments();
		if (category_id==null || category_id=="")
		{
			category_id = "%";
		}
		if(lesson_id == null)
		{
			lesson_id="";
		}
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAssignments = connection.createStatement();){

			String sAssignment=null;
			if(page.equals("Edit"))
			{
				sAssignment = "select * from Assignments where Course_id='"+course_id+"'"+lesson_idSQL+lesson_id+" and Category_id like '"+category_id+"' and Teacher_id="+teacher_id;
			}
			else if(page.equals("Edit_Standard"))
			{
				//sAssignment = "select * from Assignments where Standard=1 and Course_id='"+course_id+"'"+lesson_idSQL+lesson_id+" and Category_id like '"+category_id+"' and Teacher_id="+teacher_id;
				sAssignment = "select * from Assignments where Standard=1 and Course_id='"+course_id+"'"+lesson_idSQL+lesson_id+" and Category_id like '"+category_id+"'";
			}
			else
			{
				sAssignment = "select * from Assignments where Course_id='"+course_id+"'"+lesson_idSQL+lesson_id+" and Category_id like '"+category_id+"' and (Teacher_id="+teacher_id+" or Standard=1)";
			}
			ResultSet resultAssignments = statementAssignments.executeQuery(sAssignment);
			
	    	ArrayList<String> Subject = new ArrayList<String>();
	    	ArrayList<Integer> Assignment_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Standard = new ArrayList<Integer>();
	    	ArrayList<Integer> Max_Grade = new ArrayList<Integer>();
			while (resultAssignments.next())
			{
				//out.print("Subject: "+resultAssignments.getString("Subject"));
				Subject.add(resultAssignments.getString("Subject"));
				Assignment_id.add(resultAssignments.getInt("Assignment_id"));
				Standard.add(resultAssignments.getInt("Standard"));
				Max_Grade.add(resultAssignments.getInt("Max_Grade"));

				Assignments.setSubject(Subject);
				Assignments.setAssignment_id(Assignment_id);
				Assignments.setStandard(Standard);
				Assignments.setMax_Grade(Max_Grade);
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Assignments;
	}
	
	public Assignment_Details getAssignmentDetails(int Assignment_id){
		Assignment_Details Assignment_Details= new Assignment_Details();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAssignments = connection.createStatement();
				Statement statementAssignment_Details = connection.createStatement();){
			
			String sAssignment_Details = "select * from Assignments where Assignment_id="+Assignment_id;
			ResultSet resultAssignment_Details = statementAssignment_Details.executeQuery(sAssignment_Details);

			Assignment_Details.setAssignment_id(Assignment_id);
			if(resultAssignment_Details.isBeforeFirst())
			{
				resultAssignment_Details.next();
				Assignment_Details.setSubject(resultAssignment_Details.getString("Subject"));
				Assignment_Details.setText(resultAssignment_Details.getString("Text"));
				Assignment_Details.setMax_Grade(resultAssignment_Details.getString("Max_Grade"));
				Assignment_Details.setAttempts_Allowed(resultAssignment_Details.getInt("Attempts_Allowed"));
				Assignment_Details.setDuration(resultAssignment_Details.getInt("Duration")/60);
				Assignment_Details.setCategory_id(resultAssignment_Details.getInt("Category_id"));
			}
			else
			{
				Assignment_Details.setSubject(null);
				Assignment_Details.setText(null);
				Assignment_Details.setMax_Grade(null);
				Assignment_Details.setAttempts_Allowed(0);
				Assignment_Details.setDuration(0);
			}

			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Assignment_Details;
	}
	
	public int createAssignment(int teacher_id, int course_id, int category_id, int lesson_id, String subject, String text, int max_Grade, int attempts_Allowed, int duration, String type){
		int updateQuery = 0;
		int autoIncValue = -1;
		String sAssignment = "INSERT INTO Assignments (Assignment_id, Category_id, Course_id, Lesson_id, Max_Grade, Subject, Text, Teacher_id, Attempts_Allowed, Duration, Standard) VALUES (Null, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sAssignment, Statement.RETURN_GENERATED_KEYS);){	
			pstatement.setInt(1, category_id);
			pstatement.setInt(2, course_id);
			if(lesson_id==0)
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(3, lesson_id);
			}
			pstatement.setInt(4, max_Grade);
			pstatement.setString(5, subject);
			pstatement.setString(6, text);
			
			if(teacher_id==0)
			{
				pstatement.setNull(7, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(7, teacher_id);
			}
			
						
			if(attempts_Allowed==-1)
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(8, attempts_Allowed);
			}
			
			if(duration==-1)
			{
				pstatement.setNull(9, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(9, duration*60);
			}
			if(type!=null && type.equals("Standard"))
			{
				pstatement.setInt(10, 1);
			}
			else
			{
				pstatement.setInt(10, 0);
			}
			
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				ResultSet resultAssignment_id = pstatement.getGeneratedKeys();
				if(resultAssignment_id.next()) 
				{
				       autoIncValue = resultAssignment_id.getInt(1);
				       resultAssignment_id.close();
				}
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}
	public int updateAssignment(int teacher_id, int assignment_id, int course_id, int category_id, int lesson_id, String subject, String text, int max_Grade, int attempts_Allowed, int duration){
		int updateQuery = 0;
		String query = "update Assignments set Category_id=?, Course_id=?, Lesson_id=?, Max_Grade=?, Subject=?, Text=?, Edited_By=? , Attempts_Allowed=?, Duration=? where Assignment_id="+assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOldMaxGrade = connection.createStatement();
				Statement statementStudentsGrading = connection.createStatement();){
			String sOldMaxGrade = "select max_Grade from assignments where Assignment_id="+assignment_id;
			ResultSet resultOldMaxGrade = statementOldMaxGrade.executeQuery(sOldMaxGrade);
			int oldMaxGrade = -1;
			while(resultOldMaxGrade.next())
			{
				oldMaxGrade = resultOldMaxGrade.getInt("max_Grade");
			}
			pstatement.setInt(1, category_id);
			pstatement.setInt(2, course_id);
			if(lesson_id==0)
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(3, lesson_id);
			}
			pstatement.setInt(4, max_Grade);
			pstatement.setString(5, subject);
			
			pstatement.setString(5, subject);
			pstatement.setString(6, text);
			pstatement.setInt(7, teacher_id);
			
			if(attempts_Allowed==-1)
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(8, attempts_Allowed);
			}
			
			if(duration==-1)
			{
				pstatement.setNull(9, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(9, duration*60);
			}	
			
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0 && max_Grade!=oldMaxGrade)
			{
				double updateRatio = (double) max_Grade / (double) oldMaxGrade;				
				String sStudentsGrading = "select * from Student_Assignment_Grade where assignment_id="+assignment_id;
				ResultSet resultStudentsGrading = statementStudentsGrading.executeQuery(sStudentsGrading);
				int student_id=0;
				PreparedStatement pstatementUpdateGrading=null;
				while(resultStudentsGrading.next())
				{
					student_id = resultStudentsGrading.getInt("Student_id");
					int old_Assignment_Grade = resultStudentsGrading.getInt("Assignment_Grade");
					int new_Assignment_Grade = (int)(Math.round(updateRatio * old_Assignment_Grade));				
					String sUpdateGrading = "update Student_Assignment_Grade set Assignment_Grade=? where Student_id="+student_id+" and assignment_id="+assignment_id;
					pstatementUpdateGrading = connection.prepareStatement(sUpdateGrading);
					pstatementUpdateGrading.setInt(1, new_Assignment_Grade);
					pstatementUpdateGrading.executeUpdate();
				}
				if(pstatementUpdateGrading!=null)
				{
					pstatementUpdateGrading.close();
				}				
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteAssignment(int assignment_id){
		int updateQuery = 0;
		String query = "Delete from Assignments where Assignment_id="+assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){

			updateQuery = pstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Assignment_Dates getAssignmentDates(int assignment_id, int class_id, int course_id, int group_id){
		Assignment_Dates Assignment_Dates= new Assignment_Dates();
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			//String query = "select *, DATE_FORMAT(Display_date, '%m/%d/%Y') AS DisplayD_date, DATE_FORMAT(Start_date, '%m/%d/%Y') AS SD_date, DATE_FORMAT(Due_date, '%m/%d/%Y') AS DD_date, DATE_FORMAT(Disappear_date, '%m/%d/%Y') AS DisappearD_date  from Assignments_Classes where Assignment_id="+assignment_id+" and Class_id="+class_id+" and Course_id="+course_id+" and (Group_id='"+group_id+"' or Group_id is null)";
			String query = "select *, DATE_FORMAT(Display_date, '%Y-%m-%d') AS DisplayD_date, DATE_FORMAT(Start_date, '%Y-%m-%d') AS SD_date, DATE_FORMAT(Due_date, '%Y-%m-%d') AS DD_date, DATE_FORMAT(Disappear_date, '%Y-%m-%d') AS DisappearD_date  from Assignments_Classes where Assignment_id="+assignment_id+" and Class_id="+class_id+" and Course_id="+course_id+" and (Group_id='"+group_id+"' or Group_id is null)";
			ResultSet resultSet = statement.executeQuery(query);

			if(resultSet.isBeforeFirst())
			{					
				resultSet.next();	
				Assignment_Dates.setDisplay_Date(resultSet.getString("DisplayD_date"));
				Assignment_Dates.setStart_Date(resultSet.getString("SD_Date"));
				Assignment_Dates.setDue_Date(resultSet.getString("DD_Date"));
				Assignment_Dates.setDisappear_Date(resultSet.getString("DisappearD_date"));
				Assignment_Dates.setOnline_Submission_Option(resultSet.getInt("Submission_Online"));
				Assignment_Dates.setGive_Assignment_id(resultSet.getInt("id"));
			}
			else
			{
				Assignment_Dates.setDisplay_Date(null);
				Assignment_Dates.setStart_Date(null);
				Assignment_Dates.setDue_Date(null);	
				Assignment_Dates.setDisappear_Date(null);
				Assignment_Dates.setOnline_Submission_Option(0);
				Assignment_Dates.setGive_Assignment_id(0);
				
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Assignment_Dates;
	}
	
	public boolean isAssignmentDatesExist(int assignment_id, int class_id, int course_id, int group_id){
		boolean datesExist = true;
		String query = "select * from Assignments_Classes where Assignment_id="+assignment_id+" and Class_id="+class_id+" and Course_id="+course_id+" and (Group_id='"+group_id+"' or Group_id is null)";
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			ResultSet resultSet = statement.executeQuery(query);
			if(!resultSet.isBeforeFirst())
			{
				datesExist = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datesExist;
	}
	
	public int createAssignmentDates(Date display_Date, Date start_Date, Date due_Date, Date disappear_Date, int assignment_id, int class_id, int course_id, int group_id, int online_Submission_Option){
		int updateQuery = 0;
		String query = "INSERT INTO Assignments_Classes (Assignment_id, Class_id, Course_id, Group_id, Display_Date, Start_Date, Due_Date, Disappear_Date, Submission_Online) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){	
			if (assignment_id!=0)
			{
				pstatement.setInt(1, assignment_id);
			}

			pstatement.setInt(2, class_id);
			pstatement.setInt(3,course_id);
			if (group_id!=0)
			{
				
				pstatement.setInt(4,group_id);
			}
			else
			{
				pstatement.setNull(4,java.sql.Types.NULL);
			}
			if(display_Date!=null)
				pstatement.setDate(5,new java.sql.Date(display_Date.getTime()));
			else
				pstatement.setNull(5,java.sql.Types.NULL);
			if(start_Date!=null)
				pstatement.setDate(6,new java.sql.Date(start_Date.getTime()));
			else
				pstatement.setNull(6,java.sql.Types.NULL);
			
			pstatement.setDate(7,new java.sql.Date(due_Date.getTime()));
			if(disappear_Date!=null)
				pstatement.setDate(8,new java.sql.Date(disappear_Date.getTime()));
			else
				pstatement.setNull(8,java.sql.Types.NULL);
			pstatement.setInt(9, online_Submission_Option);
			updateQuery = 0;
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int updateAssignmentDates(Date display_Date, Date start_Date, Date due_Date, Date disappear_Date, int assignment_id, int class_id, int course_id, int group_id, int online_Submission_Option){
		int updateQuery = 0;
		String query = "update Assignments_Classes set Display_Date=?, Start_Date=?, Due_Date=?, Disappear_Date=?, Submission_Online=? where Assignment_id="+assignment_id+" and Class_id="+class_id+" and Course_id="+course_id+" and (Group_id='"+group_id+"' or Group_id is null)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
			if(display_Date!=null)
				pstatement.setDate(1,new java.sql.Date(display_Date.getTime()));
			else
				pstatement.setNull(1,java.sql.Types.NULL);
			if(start_Date!=null)
				pstatement.setDate(2,new java.sql.Date(start_Date.getTime()));
			else
				pstatement.setNull(2,java.sql.Types.NULL);
			
			pstatement.setDate(3,new java.sql.Date(due_Date.getTime()));
			if(disappear_Date!=null)
				pstatement.setDate(4,new java.sql.Date(disappear_Date.getTime()));
			else
				pstatement.setNull(4,java.sql.Types.NULL);
			pstatement.setInt(5, online_Submission_Option);
			updateQuery = 0;
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteAssignmentDates(int assignment_id, int class_id, int course_id, int group_id){
		int updateQuery = 0;
		String query = "Delete from Assignments_Classes where Assignment_id="+assignment_id+" and Class_id="+class_id+" and Course_id="+course_id+" and (Group_id='"+group_id+"' or Group_id is null)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){

			updateQuery = pstatement.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
		
}
