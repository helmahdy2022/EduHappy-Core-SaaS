package model.dbHelpers.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.beans.student.Attendance_Sessions;
import model.beans.student.Attendance_Summary;
import model.dbHelpers.DbConnector;

public class ServiceAttendance {
	public Attendance_Summary getAttendanceSummary(int student_id){
		Attendance_Summary Attendance_Summary = new Attendance_Summary();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudentCourses = connection.createStatement();
				Statement statementSessionCompleted = connection.createStatement();
				Statement statementSessionPresent = connection.createStatement();
				Statement statementSessionAbsent = connection.createStatement();
				Statement statementSessionExcused = connection.createStatement();
				Statement statementSessionLate = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();){
			
			ArrayList<ArrayList<String>> AttendanceSummary = new ArrayList<ArrayList<String>>();

	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
	    	String sClassDivision = "select * from Classes where  Class_id=(select class_id from students where student_id="+student_id+")";
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			while(resultClassDivision.next())
			{
				division_id = resultClassDivision.getInt("Division_id");
			}			
	    	////////////////////////////////////////////////////////////////////////////////////////////////////
			String sStudentCourses=null;
			if (division_id==0)
	    	{	
		    	sStudentCourses = "select * from courses where "
		    	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
		    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
	    	}
	    	else
	    	{
		    	sStudentCourses = "select * from courses where "
		    	    	+ "((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+") and Elective=0)"
		    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
	    	}			
			
/*			String sStudentCourses = "select * from courses where "
	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
*/	    	ResultSet resultStudentCourses = statementStudentCourses.executeQuery(sStudentCourses);

			int i=0;
			while (resultStudentCourses.next())
			{
				String Session_Completed=null;
				String Session_Present = null;
				String Session_Absent = null;
				String Session_Excused = null;
				String Session_Late = null;

		    	String sSessionCompleted = "select count(session_id) from attendance where course_id="+resultStudentCourses.getString("Course_id")+" and student_id="+student_id;
		    	ResultSet resultSessionCompleted = statementSessionCompleted.executeQuery(sSessionCompleted);
				while (resultSessionCompleted.next())
				{
					Session_Completed = resultSessionCompleted.getString(1);
				}

		    	String sSessionPresent = "select count(session_id) from attendance where course_id="+resultStudentCourses.getString("Course_id")+" and student_id="+student_id+" and status='Present'";
		    	ResultSet resultSessionPresent = statementSessionPresent.executeQuery(sSessionPresent);
				while (resultSessionPresent.next())
				{
					Session_Present = resultSessionPresent.getString(1);
				}

		    	String sSessionAbsent = "select count(session_id) from attendance where course_id="+resultStudentCourses.getString("Course_id")+" and student_id="+student_id+" and status='Absent'";
		    	ResultSet resultSessionAbsent = statementSessionAbsent.executeQuery(sSessionAbsent);
				while (resultSessionAbsent.next())
				{
					Session_Absent = resultSessionAbsent.getString(1);
				}

		    	String sSessionExcused = "select count(session_id) from attendance where course_id="+resultStudentCourses.getString("Course_id")+" and student_id="+student_id+" and status='Excused'";
		    	ResultSet resultSessionExcused = statementSessionExcused.executeQuery(sSessionExcused);
				while (resultSessionExcused.next())
				{
					Session_Excused = resultSessionExcused.getString(1);
				}

		    	String sSessionLate = "select count(session_id) from attendance where course_id="+resultStudentCourses.getString("Course_id")+" and student_id="+student_id+" and status='Late'";
		    	ResultSet resultSessionLate = statementSessionLate.executeQuery(sSessionLate);
				while (resultSessionLate.next())
				{
					Session_Late = resultSessionLate.getString(1);
				}			
				AttendanceSummary.add(new ArrayList<String>());
				AttendanceSummary.get(i).add(0,null);
				AttendanceSummary.get(i).set(0, resultStudentCourses.getString("Course_id"));
				AttendanceSummary.get(i).add(1,null);
				AttendanceSummary.get(i).set(1, resultStudentCourses.getString("Course_Name"));
				AttendanceSummary.get(i).add(2,null);
				AttendanceSummary.get(i).set(2, Session_Completed);
				AttendanceSummary.get(i).add(3,null);
				AttendanceSummary.get(i).set(3, Session_Present);
				AttendanceSummary.get(i).add(4,null);
				AttendanceSummary.get(i).set(4, Session_Absent);
				AttendanceSummary.get(i).add(5,null);
				AttendanceSummary.get(i).set(5,Session_Excused);
				AttendanceSummary.get(i).add(6,null);
				AttendanceSummary.get(i).set(6,Session_Late);
				i=i+1;
			}
			
			Attendance_Summary.setAttendanceSummary(AttendanceSummary);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Attendance_Summary;
		
	}
	
	public Attendance_Sessions getAttendance(int student_id, int course_id, int current_school_year_id){
		Attendance_Sessions Attendance_Sessions = new Attendance_Sessions();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAttendance = connection.createStatement();
				Statement statementAttendanceGrade = connection.createStatement();
				Statement statementSessionTime = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();){

	    	String sSchoolYear = "select * from School_year where school_year_id="+current_school_year_id;
	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	Date year_Start = null;
	    	Date year_End = null;
	    	while(resultSchoolYear.next())
	    	{	
		    	year_Start = resultSchoolYear.getDate("Year_Start");
		    	year_End = resultSchoolYear.getDate("Year_End");
	    	}
	    	
	    	String sAttendance = "select * from Attendance where Course_id="+course_id+" and Student_id="+student_id+" and Session_Date>='"+year_Start+"' and Session_Date<='"+year_End+"'";
	    	ResultSet resultAttendance = statementAttendance.executeQuery(sAttendance);

	    	String sAttendanceGrade = "select Attendance_Grade, Course_Name from Courses where Course_id="+course_id;
	    	ResultSet resultAttendanceGrade = statementAttendanceGrade.executeQuery(sAttendanceGrade);
			String Attendance_Grade = null;
			String course_Name = null;
			while (resultAttendanceGrade.next())
			{
				Attendance_Grade = resultAttendanceGrade.getString("Attendance_Grade");
				course_Name = resultAttendanceGrade.getString("Course_Name");
			}
			ArrayList<ArrayList<String>> AttendanceList = new ArrayList<ArrayList<String>>();
			int i=0;
			while (resultAttendance.next())
			{

		    	String sSessionTime = "select * from grade_sessions where session_id="+resultAttendance.getString("Session_id");
		    	ResultSet resultSessionTime = statementSessionTime.executeQuery(sSessionTime);
				String Time_From = null;
				String Time_To = null;
				while (resultSessionTime.next())
				{
					Time_From = resultSessionTime.getString("Time_From");
					Time_To = resultSessionTime.getString("Time_To");
				}
				double Attendance_Grade_Percentage = (double)Integer.parseInt(resultAttendance.getString("Session_Grade")) / (double)Integer.parseInt(Attendance_Grade)*100.0;
				AttendanceList.add(new ArrayList<String>());
				AttendanceList.get(i).add(0,null);
				AttendanceList.get(i).set(0, resultAttendance.getString("Session_id"));
				AttendanceList.get(i).add(1,null);
				AttendanceList.get(i).set(1, resultAttendance.getString("Session_Date"));
				AttendanceList.get(i).add(2,null);
				AttendanceList.get(i).set(2, Time_From);
				AttendanceList.get(i).add(3,null);
				AttendanceList.get(i).set(3, Time_To);
				AttendanceList.get(i).add(4,null);
				AttendanceList.get(i).set(4, resultAttendance.getString("Status"));
				AttendanceList.get(i).add(5,null);
				AttendanceList.get(i).set(5,resultAttendance.getString("Session_Grade"));
				AttendanceList.get(i).add(6,null);
				AttendanceList.get(i).set(6,Attendance_Grade);
				AttendanceList.get(i).add(7,null);
				AttendanceList.get(i).set(7,String.valueOf(Math.round(Attendance_Grade_Percentage)));
				i=i+1;	
			}
			Attendance_Sessions.setAttendanceList(AttendanceList);
			Attendance_Sessions.setCourse_Name(course_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Attendance_Sessions;
		
	}
	
	public Attendance_Sessions getFinalAttendance(int student_id, int course_id, int school_year_id){
		Attendance_Sessions Attendance_Sessions = new Attendance_Sessions();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAttendance = connection.createStatement();
				Statement statementAttendanceGrade = connection.createStatement();
				Statement statementSessionTime = connection.createStatement();){

	    	String sAttendance = "select * from _Attendance where school_year_id="+school_year_id+" and Course_id="+course_id+" and Student_id="+student_id;
	    	ResultSet resultAttendance = statementAttendance.executeQuery(sAttendance);

	    	String sAttendanceGrade = "select Attendance_Grade, Course_Name from _Courses where school_year_id="+school_year_id+" and Course_id="+course_id;
	    	ResultSet resultAttendanceGrade = statementAttendanceGrade.executeQuery(sAttendanceGrade);
			String Attendance_Grade = null;
			String course_Name = null;
			while (resultAttendanceGrade.next())
			{
				Attendance_Grade = resultAttendanceGrade.getString("Attendance_Grade");
				course_Name = resultAttendanceGrade.getString("Course_Name");
			}
			ArrayList<ArrayList<String>> AttendanceList = new ArrayList<ArrayList<String>>();
			int i=0;
			while (resultAttendance.next())
			{

		    	String sSessionTime = "select * from _grade_sessions where school_year_id="+school_year_id+" and session_id="+resultAttendance.getString("Session_id");
		    	ResultSet resultSessionTime = statementSessionTime.executeQuery(sSessionTime);
				String Time_From = null;
				String Time_To = null;
				while (resultSessionTime.next())
				{
					Time_From = resultSessionTime.getString("Time_From");
					Time_To = resultSessionTime.getString("Time_To");
				}
				double Attendance_Grade_Percentage = (double)Integer.parseInt(resultAttendance.getString("Session_Grade")) / (double)Integer.parseInt(Attendance_Grade)*100.0;
				AttendanceList.add(new ArrayList<String>());
				AttendanceList.get(i).add(0,null);
				AttendanceList.get(i).set(0, resultAttendance.getString("Session_id"));
				AttendanceList.get(i).add(1,null);
				AttendanceList.get(i).set(1, resultAttendance.getString("Session_Date"));
				AttendanceList.get(i).add(2,null);
				AttendanceList.get(i).set(2, Time_From);
				AttendanceList.get(i).add(3,null);
				AttendanceList.get(i).set(3, Time_To);
				AttendanceList.get(i).add(4,null);
				AttendanceList.get(i).set(4, resultAttendance.getString("Status"));
				AttendanceList.get(i).add(5,null);
				AttendanceList.get(i).set(5,resultAttendance.getString("Session_Grade"));
				AttendanceList.get(i).add(6,null);
				AttendanceList.get(i).set(6,Attendance_Grade);
				AttendanceList.get(i).add(7,null);
				AttendanceList.get(i).set(7,String.valueOf(Math.round(Attendance_Grade_Percentage)));
				i=i+1;	
			}
			Attendance_Sessions.setAttendanceList(AttendanceList);
			Attendance_Sessions.setCourse_Name(course_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Attendance_Sessions;
		
	}
}
