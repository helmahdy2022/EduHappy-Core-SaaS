package model.dbHelpers.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.beans.admin.Students;
import model.beans.teacher.Attendance_Sessions;
import model.beans.teacher.Attendance_Students;
import model.beans.teacher.Current_Session;
import model.dbHelpers.DbConnector;
import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.StudentManagement;

public class AttendanceManagement {
	public String validateDate(Date date, int grade_id){
		String date_Case = "DateValid";
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementGradeTerms = connection.createStatement();
				Statement statementDateInTerm = connection.createStatement();
				Statement statementDateIsHoliday = connection.createStatement();
				Statement statementDateIsDayOff = connection.createStatement();
				Statement statementDateIsWE = connection.createStatement();
				Statement statementYearTerms = connection.createStatement();){

			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id=(select school_id from Grades where "
	    			+ "Grade_id="+grade_id+")";
	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);	    	
	    	int school_year_id=0;
	    	while(resultSchoolYear.next())
	    	{
		    	school_year_id = resultSchoolYear.getInt("School_year_id");	
	    	}
	    	
			String sGradeTerms = "select * from Grade_Terms where School_year_id="+school_year_id+" and Grade_id="+grade_id;
	    	ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
	    	String sDateInTerm = null;
	    	if(resultGradeTerms.isBeforeFirst())
	    	{
	    		sDateInTerm = "select * from Grade_Terms where Term_Start<='"+new java.sql.Date(date.getTime())+"' and Term_End>='"+new java.sql.Date(date.getTime())+"' and grade_id="+grade_id;
	    	}
	    	else
	    	{			
	    		String sYearTerms = "select * from Year_Terms where School_year_id="+school_year_id;
	    		ResultSet resultYearTerms = statementYearTerms.executeQuery(sYearTerms);
	    		if(resultYearTerms.isBeforeFirst())
	    		{
	    			sDateInTerm = "select * from year_Terms where Term_Start<='"+new java.sql.Date(date.getTime())+"' and Term_End>='"+new java.sql.Date(date.getTime())+"' and school_year_id="+school_year_id;
	    		}
	    		else
	    		{
	    			sDateInTerm = "select * from school_year where year_Start<='"+new java.sql.Date(date.getTime())+"' and year_End>='"+new java.sql.Date(date.getTime())+"' and school_year_id="+school_year_id;
	    		}    		
	    	}
	    	
	    	ResultSet resultDateInTerm = statementDateInTerm.executeQuery(sDateInTerm);
	    	if(!resultDateInTerm.isBeforeFirst())
	    	{
	    		date_Case = "NotInTerm";
	    	}
	    	else
	    	{
	    		/////Check Holidays /////////////////////
		    	String sDateIsHoliday = "select * from National_Holidays where Holiday_Start<='"+new java.sql.Date(date.getTime())+"' and Holiday_End>='"+new java.sql.Date(date.getTime())+"'"
		    			+ " and country_id=(select country_id from school where school_id=(select school_id from grades where grade_id="+grade_id+"))"
		    					+ " and Account_id=(select account_id from school where school_id=(select school_id from grades where grade_id="+grade_id+"))";
		    	ResultSet resultDateIsHoliday = statementDateIsHoliday.executeQuery(sDateIsHoliday);
		    	if(resultDateIsHoliday.isBeforeFirst())
		    	{
		    		date_Case = "Holiday";
		    	}
		    	else
		    	{
			    	String sDateIsDayOff = "select * from Daysoff where Daysoff_Start<='"+new java.sql.Date(date.getTime())+"' and Daysoff_End>='"+new java.sql.Date(date.getTime())+"' and (All_Schools = 1 or Daysoff_id in (select Daysoff_id from school_grade_daysoff where grade_id="+grade_id+"))"
			    			+ " and Account_id=(select account_id from school where school_id=(select school_id from grades where grade_id="+grade_id+"))";
			    	ResultSet resultDateIsDayOff = statementDateIsDayOff.executeQuery(sDateIsDayOff);
			    	if(resultDateIsDayOff.isBeforeFirst())
			    	{
			    		date_Case = "Daysoff";
			    	}
			    	else
			    	{
			    		////check week ends ////////////////
			    		Format formatter = new SimpleDateFormat("EEEE"); 
			    	    String day_Name = formatter.format(date);
				    	String sDateIsWE = "Select * from school_weekend where school_id=(select school_id from grades where grade_id="+grade_id+") and Day_id=(select day_id from week_days where Day_Name='"+day_Name+"')";
				    	ResultSet resultDateIsWE = statementDateIsWE.executeQuery(sDateIsWE);
				    	if(resultDateIsWE.isBeforeFirst())
				    	{
				    		date_Case = "WeekEnd";
				    	}
			    	}
		    	}
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date_Case;
	}
	
	public Current_Session getCurrentSession(int teacher_id, String day_Name, String sclientTime ) throws ParseException{
		Current_Session current_Session = new Current_Session();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementDay = connection.createStatement();
				Statement statementSession = connection.createStatement();
				Statement statementSessionTime = connection.createStatement();){
			int day_id=0;
	    	String sDay = "select * from Week_Days where Day_Name='"+day_Name+"'";
	    	ResultSet resultDay = statementDay.executeQuery(sDay);
	    	while (resultDay.next())
	    	{
	    		day_id = resultDay.getInt("Day_id");
	    	}
	    	
	    	///////////////Get Current Client time not the server time ///////////////////////////
	    	//Calendar now = Calendar.getInstance(); 
	    	//now.add(Calendar.MINUTE, timeOffset*(-1));
	    	//Timestamp clientTime = new java.sql.Timestamp(now.getTimeInMillis());
	    	
	    	
	    	
	    	DateFormat formatter = new SimpleDateFormat("HH:mm");
	    	java.sql.Time clientTime = new java.sql.Time(formatter.parse(sclientTime).getTime());
	    	///////////////////////////////////////////////////////////////////////////////////////
	    	//String sSessions = "select * from schedule where teacher_id="+teacher_id+" and Day_id="+day_id+" and Session_id in(select session_id from grade_sessions where time_from <= curtime() and time_to >= curtime())";

	    	String sSessions = "select * from schedule where teacher_id="+teacher_id+" and Day_id="+day_id+" and Session_id in(select session_id from grade_sessions where time_from <= '"+clientTime+"' and time_to >= '"+clientTime+"')";
	    	
	    	ResultSet resultSession = statementSession.executeQuery(sSessions);
	    	
	    	GradeManagement gradeManagent = new GradeManagement();
	    	
	    	while (resultSession.next())
	    	{
	    		current_Session.setSession_id(resultSession.getInt("session_id"));
	    		current_Session.setClass_id(resultSession.getInt("Class_id"));
	    		current_Session.setCourse_id(resultSession.getInt("Course_id"));
	    		current_Session.setGroup_id(resultSession.getInt("Group_id"));
	    		
	    		int grade_id = gradeManagent.getGradeByClass(resultSession.getInt("Class_id"));
	    		current_Session.setGrade_id(grade_id);	 
	    		
	    		
		    	String sSessionTime = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End from grade_sessions where session_id = "+resultSession.getInt("session_id");
		    	
		    	ResultSet resultSessionTime = statementSessionTime.executeQuery(sSessionTime);
	    		while(resultSessionTime.next())
	    		{
			    	current_Session.setTime_From(resultSessionTime.getString("Time_Start"));
		    		current_Session.setTime_To(resultSessionTime.getString("Time_End"));
	    		}
	    	}

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_Session;
	}
	
	public Attendance_Students getAttendanceStudents(int class_id, int course_id, int group_id, int session_id, Date session_Date ){
		Attendance_Students attendance_Students = new Attendance_Students();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudents = connection.createStatement();
				Statement statementElective = connection.createStatement();
				Statement statementExistingAtt = connection.createStatement();){

			StudentManagement studentManagement = new StudentManagement();
			Students students = new Students();
			students = studentManagement.getStudentsByClassCourseGroup(class_id, course_id, group_id);
			ArrayList<Integer> studensList = students.getStudent_id();
			ArrayList<String> Status = new ArrayList<String>();	    	
			for (int student_id : studensList)
			{
	    		String sExistingAtt = "select * from Attendance where Student_id="+student_id+" and Session_Date='"+new java.sql.Date(session_Date.getTime())+"' and Session_id="+session_id;
	    		ResultSet resultExistingAtt = statementExistingAtt.executeQuery(sExistingAtt);	
	    		if (resultExistingAtt.isBeforeFirst())
	    		{
		    		resultExistingAtt.next();
		    		Status.add(resultExistingAtt.getString("Status"));
	    		}
	    		else
	    		{
	    			Status.add(null);
	    		}
			}			
			attendance_Students.setStudent_id(students.getStudent_id());
	    	attendance_Students.setFirst_Name(students.getFirst_Name());
	    	attendance_Students.setLast_Name(students.getLast_Name());
	    	attendance_Students.setUsername(students.getUsername());
    		attendance_Students.setAccount_id(students.getAccount_id());
    		attendance_Students.setSchool_id(students.getSchool_id());
    		attendance_Students.setFileName(students.getFileName());
	    	attendance_Students.setStatus(Status);	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attendance_Students;
	}
	
	public Attendance_Sessions getSessionsList(int teacher_id, int class_id, int course_id, int group_id, String day_Name){
		Attendance_Sessions attendance_Sessions = new Attendance_Sessions();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementDay = connection.createStatement();
				Statement statementSession = connection.createStatement();){

	    	String sDay = "select * from Week_Days where Day_Name='"+day_Name+"'";
	    	ResultSet resultDay = statementDay.executeQuery(sDay);
	    	int day_id=0;
	    	while (resultDay.next())
	    	{
	    		if(resultDay.getString("Day_id")!=null)
	    		{
	    			day_id = Integer.parseInt(resultDay.getString("Day_id"));
	    		}
	    		
	    	}
	    	String sSessions = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End from grade_sessions where session_id in (select session_id from schedule where teacher_id="+teacher_id+" and class_id="+class_id+" and course_id="+course_id+" and (Group_id="+group_id+" or Group_id is null) and Day_id="+day_id+")";
	    	ResultSet resultSession = statementSession.executeQuery(sSessions);
	    	
	    	ArrayList<String> Session_id = new ArrayList<String>();
	    	ArrayList<String> Time_From = new ArrayList<String>();
	    	ArrayList<String> Time_To = new ArrayList<String>();
	    	resultSession.last();
	    	int session_Count = resultSession.getRow();
	    	resultSession.beforeFirst();
	    	while (resultSession.next())
	    	{
	    		Session_id.add(resultSession.getString("session_id"));
	    		Time_From.add(resultSession.getString("Time_Start"));	
	    		Time_To.add(resultSession.getString("Time_End"));
	    	}
	    	attendance_Sessions.setSession_id(Session_id);
	    	attendance_Sessions.setTime_From(Time_From);
	    	attendance_Sessions.setTime_To(Time_To);
	    	attendance_Sessions.setSession_Count(session_Count);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attendance_Sessions;
	}
	
	public void manageAttendance(String student_id, int class_id,  int course_id, int group_id, int session_id, int teacher_id, Date dSession_Date, String status, String function)
	{
 
		try (Connection connection = DbConnector.connectToDb();
				Statement statementDay = connection.createStatement();
				Statement statementStatusGrade = connection.createStatement();
				Statement statementElective = connection.createStatement();
				Statement statementStudents = connection.createStatement();
				Statement statementExistingAtt = connection.createStatement();){
			String sStatusGrade=null;
			if (status.equals("Present"))
			{
				sStatusGrade = "select Present_Grade from Courses where Course_id="+course_id;
			}
			if (status.equals("Absent"))
			{
				sStatusGrade = "select Absent_Grade from Courses where Course_id="+course_id;
			}
			if (status.equals("Excused"))
			{
				sStatusGrade = "select Excused_Grade from Courses where Course_id="+course_id;
			}
			if (status.equals("Late"))
			{
				sStatusGrade = "select Late_Grade from Courses where Course_id="+course_id;
			}
			String Session_Grade = null;
			if (!status.equals("reset"))
			{
				ResultSet resultStatusGrade = statementStatusGrade.executeQuery(sStatusGrade);
				
				while(resultStatusGrade.next())
				{
					Session_Grade = resultStatusGrade.getString(1);
				}
			}		
	    	if (student_id.equals("All"))
	    	{				
				StudentManagement studentManagement = new StudentManagement();
				Students students = new Students();
				students = studentManagement.getStudentsByClassCourseGroup(class_id, course_id, group_id);
				ArrayList<Integer> studensList = students.getStudent_id();    	
				for (int student : studensList)
	    		
				{
		    		if(!function.equals("Delete"))
		    		{
			    		String sExistingAtt = "select * from Attendance where Student_id="+student+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;
			    		ResultSet resultExistingAtt = statementExistingAtt.executeQuery(sExistingAtt);	
			    		PreparedStatement pstatement = null;
			    		String sAttendance = null;
			    		
			    		if (!resultExistingAtt.isBeforeFirst())
			    		{
			    			sAttendance = "INSERT INTO Attendance (Student_id, Session_Date, Session_id, Class_id, Course_id, Teacher_id, Status, Session_Grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";										
			    			pstatement = connection.prepareStatement(sAttendance);
			    			pstatement.setInt(1, student);
			    			pstatement.setDate(2,new java.sql.Date(dSession_Date.getTime()));
			    			pstatement.setInt(3, session_id);
			    			pstatement.setInt(4, class_id);
			    			pstatement.setInt(5, course_id);
			    			pstatement.setInt(6, teacher_id);
			    			pstatement.setString(7, status);
			    			pstatement.setString(8, Session_Grade);
			    			pstatement.executeUpdate();
			    		}
			    		else
			    		{
			    			sAttendance = "update Attendance set Class_id=?, Course_id=?, Teacher_id=?, Status=?, Session_Grade=? where Student_id="+student+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;
			    			pstatement = connection.prepareStatement(sAttendance);	
			    			pstatement.setInt(1, class_id);
			    			pstatement.setInt(2, course_id);
			    			pstatement.setInt(3, teacher_id);
			    			pstatement.setString(4, status);
			    			pstatement.setString(5, Session_Grade);
			    			pstatement.executeUpdate();
			    		}
			    		pstatement.close();
		    		}
		    		else
		    		{
		    			////Delete///////
						String sDeleteAttendance = "Delete from Attendance where Student_id="+student+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;
						PreparedStatement psDeleteAttendance = null;
						psDeleteAttendance = connection.prepareStatement(sDeleteAttendance);
						psDeleteAttendance.executeUpdate();
						psDeleteAttendance.close();
		    		}

				}
	    	}
	    	else
	    	{
	    		if (!status.equals("reset"))
	    		{
	        		String sExistingAtt = "select * from Attendance where Student_id="+student_id+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;
	        		ResultSet resultExistingAtt = statementExistingAtt.executeQuery(sExistingAtt);	
	        		PreparedStatement pstatement = null;
	        		String sAttendance = null;
	        		if (!resultExistingAtt.isBeforeFirst())
	        		{
	        			sAttendance = "INSERT INTO Attendance (Student_id, Session_Date, Session_id, Class_id, Course_id, Teacher_id, Status, Session_Grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";										
	        			pstatement = connection.prepareStatement(sAttendance);
	        			pstatement.setString(1, student_id);
	        			pstatement.setDate(2,new java.sql.Date(dSession_Date.getTime()));
	        			pstatement.setInt(3, session_id);
	        			pstatement.setInt(4, class_id);
	        			pstatement.setInt(5, course_id);
	        			pstatement.setInt(6, teacher_id);
	        			pstatement.setString(7, status);
	        			pstatement.setString(8, Session_Grade);
	        			pstatement.executeUpdate();
	        		}
	        		else
	        		{
	        			sAttendance = "update Attendance set Class_id=?, Course_id=?, Teacher_id=?, Status=?, Session_Grade=? where Student_id="+student_id+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;
	        			pstatement = connection.prepareStatement(sAttendance);
	        			pstatement.setInt(1, class_id);
	        			pstatement.setInt(2, course_id);
	        			pstatement.setInt(3, teacher_id);
	        			pstatement.setString(4, status);
	        			pstatement.setString(5, Session_Grade);
	        			pstatement.executeUpdate();
	        		}
	        		pstatement.close();
	    		}
	    		else
	    		{
	    			////Delete///////
						String sDeleteAttendance = "Delete from Attendance where Student_id="+student_id+" and Session_Date='"+new java.sql.Date(dSession_Date.getTime())+"' and Session_id="+session_id;;
						PreparedStatement psDeleteAttendance = null;
						psDeleteAttendance = connection.prepareStatement(sDeleteAttendance);
						psDeleteAttendance.executeUpdate();
						psDeleteAttendance.close();
	    		}
	    	}
	    		
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
