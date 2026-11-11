package model.dbHelpers.teacher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.hc.core5.http.ParseException;
import model.beans.teacher.Teacher_Schedule;
import model.dbHelpers.DbConnector;


public class ServiceSchedule {
	@SuppressWarnings("static-access")
	public Teacher_Schedule getTeacherSchedule(int teacher_id, String sclientTime, String sclientDay, String contextPath) throws ParseException, java.text.ParseException, UnsupportedEncodingException, NoSuchAlgorithmException{
		Teacher_Schedule Teacher_Schedule = new Teacher_Schedule();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSessions = connection.createStatement();
				Statement statementWeekDays = connection.createStatement();
				Statement statementCourses = connection.createStatement();
				Statement statementGroup = connection.createStatement();
				Statement statementClass = connection.createStatement();
				Statement statementGrade = connection.createStatement();
				Statement statementSchool = connection.createStatement();
				Statement statementIsCurrentSession = connection.createStatement();
				Statement statementDay = connection.createStatement();				
				Statement statementTeacherName = connection.createStatement();
				Statement statementUserPass = connection.createStatement();){
			
			///////////////////////////////Array for Sessions//////////////////////////////////////////////////
			String sSessions = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End from Grade_Sessions where Session_id in (select session_id from Schedule where Teacher_id ="+ teacher_id+") order by Time_From";
			ResultSet ResultSessions = statementSessions.executeQuery(sSessions);	
			ResultSessions.last();
			int Session_Count =ResultSessions.getRow();
			
			Teacher_Schedule.setSession_Count(Session_Count);
			ResultSessions.beforeFirst();
			
			ArrayList<ArrayList<String>> Sessions = new ArrayList<ArrayList<String>>();
			
			int j=0;
			while (ResultSessions.next())
			{
				Sessions.add(new ArrayList<String>());
				Sessions.get(j).add(0,null);
				Sessions.get(j).set(0, ResultSessions.getString("Session_id"));
				Sessions.get(j).add(1,null);
				Sessions.get(j).set(1, ResultSessions.getString("Time_Start"));
				Sessions.get(j).add(2,null);
				Sessions.get(j).set(2, ResultSessions.getString("Time_End"));		
				j=j+1;
			}
			Teacher_Schedule.setSessions(Sessions);
			///////////////////////////End Array for Sessions /////////////////////////////////////////////////
			
			///////////////////////////////Array for Week Days//////////////////////////////////////////////////			
			String sWeekDays = "select * from week_days where day_id in (select day_id from schedule where Teacher_id="+teacher_id+") order by Day_id";
			ResultSet resultWeekDays = statementWeekDays.executeQuery(sWeekDays);
			resultWeekDays.last();
			int Days_Count =resultWeekDays.getRow();
			resultWeekDays.beforeFirst();

			Teacher_Schedule.setDays_Count(Days_Count);
		
			ArrayList<String> Day_id = new ArrayList<String>();
			ArrayList<String> Day_Name = new ArrayList<String>();
			
			while(resultWeekDays.next()) 
			{
				Day_id.add(resultWeekDays.getString("Day_id"));			
				Day_Name.add(resultWeekDays.getString("Day_Name"));
			}
			Teacher_Schedule.setDay_id(Day_id);
			Teacher_Schedule.setDay_Name(Day_Name);
			///////////////////////////End Array for Week Days /////////////////////////////////////////////////
			
			///////////////////////////////////////////// Get the course from Schedule for the same session same day/////////////////////////
			int m=0;
			ArrayList<ArrayList<String>> CourseExist = new ArrayList<ArrayList<String>>();
			
			resultWeekDays.beforeFirst();
			while (resultWeekDays.next())
			{
				ResultSessions.beforeFirst();
				while (ResultSessions.next())
				{
					String sCourses = "select * from Courses where Course_id =(select course_id from Schedule where day_id="+resultWeekDays.getString("day_id") +" and session_id="+ResultSessions.getString("session_id")+ " and Teacher_id="+teacher_id+")";
					ResultSet resultCourses = statementCourses.executeQuery(sCourses);
					String Course_id=null;
					String Course_Name=null;
					String Elective = null;
					String Group_id=null;
					String Group_Name=null;
					String callApiURL = null;
					String isCurrentSession = "false";
					while (resultCourses.next())
					{
						Course_id = resultCourses.getString("Course_id");
						Course_Name = resultCourses.getString("Course_Name");
						Elective = resultCourses.getString("Elective");
					}
				
				///////////////////Get Group Name if there is a group /////////////////////////////////////////////////
				
					String sGroup = "select * from "+"`Groups`"+" where Group_id =(select Group_id from Schedule where day_id="+resultWeekDays.getString("day_id") +" and session_id="+ResultSessions.getString("session_id")+ " and Teacher_id="+teacher_id+")";
					ResultSet resultGroup = statementGroup.executeQuery(sGroup);
					while (resultGroup.next())
					{
						Group_id = resultGroup.getString("Group_id");
						Group_Name = resultGroup.getString("Group_Name");
					}
				//////////////////////////////////////////////////////////////////////////////////////////////////////
				
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(0,null);
					CourseExist.get(m).set(0, resultWeekDays.getString("Day_id"));
								
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(1,null);
					CourseExist.get(m).set(1, ResultSessions.getString("Session_id"));
					
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(2,null);
					CourseExist.get(m).set(2, Course_id);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(3,null);
					CourseExist.get(m).set(3, Group_id);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(4,null);
					CourseExist.get(m).set(4, Course_Name);		
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(5,null);
					CourseExist.get(m).set(5, Elective);	
					
				////////////////////////Get Class //////////////////////////////////
					String sClass= "select Class_Name, Class_id from Classes where Class_id =(select class_id from Schedule where day_id="+resultWeekDays.getString("day_id") +" and session_id="+ResultSessions.getString("session_id")+ " and Teacher_id="+teacher_id+")";
					ResultSet resultClass = statementClass.executeQuery(sClass);
					String Class_id=null;
					String Class_Name=null;
					String Grade_id=null;
					String Grade_Name=null;
					String School_Name=null;
					while (resultClass.next())
					{
						Class_id = resultClass.getString("Class_id");
						Class_Name = resultClass.getString("Class_Name");
						
						//////Get Grade /////////////
						
						String sGrade= "select Grade_Name, Grade_id from Grades where Grade_id =(select Grade_id from Classes where Class_id="+Class_id+")";
						ResultSet resultGrade = statementGrade.executeQuery(sGrade);
						while (resultGrade.next())
						{
							Grade_id = resultGrade.getString("Grade_id");
							Grade_Name = resultGrade.getString("Grade_Name");
						}
						//////////////////////////////
						/////Get School //////////////
						String sSchool= "select Name from School where School_id =(select School_id from Grades where Grade_id="+Grade_id+")";
						ResultSet resultSchool = statementSchool.executeQuery(sSchool);
						while (resultSchool.next())
						{
							School_Name = resultSchool.getString("Name");
						}
						/////////////////////////////

						
						////////////////Add the Virtual Class URL to the current session only////////////////////				    	
						String day_Name = null;
						switch(sclientDay) {
						  case "0":
							  day_Name = "Sunday";
						    break;
						  case "1":
							  day_Name = "Monday";
						    break;
						  case "2":
							  day_Name = "Tuesday";
						    break;
						  case "3":
							  day_Name = "Wednesday";
						    break;
						  case "4":
							  day_Name = "Thursday";
						    break;
						  case "5":
							  day_Name = "Friday";
						    break;
						  case "6":
							  day_Name = "Saturday";
						    break;
						}
						//Date date = null;
						//date = new Date();
						//Format formatter = new SimpleDateFormat("EEEE"); 
					    //String day_Name = formatter.format(date);
				    	DateFormat formatterForClientTime = new SimpleDateFormat("HH:mm");
				    	java.sql.Time clientTime = new java.sql.Time(formatterForClientTime.parse(sclientTime).getTime());
					    int day_id=0;
				    	String sDay = "select * from Week_Days where Day_Name='"+day_Name+"'";
				    	ResultSet resultDay = statementDay.executeQuery(sDay);
				    	while(resultDay.next())
					    {
					    	day_id = resultDay.getInt("Day_id");
					    }
				    	String sIsCurrentSessions = "select * from Schedule where class_id="+Class_id+" and day_id="+day_id+" and session_id="+ResultSessions.getInt("Session_id")+" and session_id in (select session_id from grade_sessions where time_from <= '"+clientTime+"' and time_to >= '"+clientTime+"')";
				    	
				    	ResultSet resultIsCurrentSessions = statementIsCurrentSession.executeQuery(sIsCurrentSessions);				    	
				    	if(resultIsCurrentSessions.isBeforeFirst() && day_id==resultWeekDays.getInt("Day_id"))
						{
				    		String classPart = "Class"+Class_id;
				    		String sessionPart = "Session"+ ResultSessions.getInt("Session_id");
				    		String dayPart = "Day"+day_id;
				    		String coursePart = "Course"+Course_id;
				    		String groupPart = "Group"+Group_id;
				    		String meeting_id_Without_Client_Id = null;
				    		if(Group_id!=null && !Group_id.equals(null))
				    		{
				    			meeting_id_Without_Client_Id = classPart+sessionPart+dayPart+coursePart+groupPart;
				    		}
				    		else
				    		{
				    			meeting_id_Without_Client_Id = classPart+sessionPart+dayPart+coursePart;
				    		}
				    		
				    		String attendeeName = null;
				    		

				    					    		
				    		String jointType = "teacher";
				    		String sTeacherName = "select First_Name, Last_Name from Teachers where Teacher_id='"+teacher_id+"'";
					    	ResultSet resultTeacherName = statementTeacherName.executeQuery(sTeacherName);
					    	while(resultTeacherName.next())
					    	{
					    		attendeeName = resultTeacherName.getString("First_Name")+" " + resultTeacherName.getString("Last_Name");
					    	}
				    		String meetingName = null;
				    		 if(Group_Name!=null && !Group_Name.equals(null))
				    		 {
				    			 meetingName = "Course: "+ Course_Name +" - " + "Group: "+ Group_Name + " - " + "Class: "+ Class_Name +" - " + "Teacher: "+ attendeeName;
				    		 }
				    		 else
				    		 {
				    			 meetingName = "Course: "+ Course_Name +" - " + "Class: "+ Class_Name +" - " + "Teacher: "+ attendeeName;
				    		 }
					    		String sUserPass = "select teacherPass, studentPass from Schedule where Class_id='"+Class_id+"' and Session_id="+ResultSessions.getInt("Session_id")+" and Day_id="+day_id;
						    	ResultSet resultUserPass = statementUserPass.executeQuery(sUserPass);	
						    	String teacherPass = null;
						    	String studentPass = null;
						    	while(resultUserPass.next())
						    	{
						    		teacherPass = resultUserPass.getString("teacherPass");
						    		studentPass = resultUserPass.getString("studentPass");
						    	}
						    	callApiURL = contextPath+ "/CreateMeetingURL?meeting_id_Without_Client_Id="+meeting_id_Without_Client_Id+"&&attendeeName="+attendeeName+"&&meetingName="+meetingName+"&&jointType="+jointType+"&&teacherPass="+teacherPass+"&&studentPass="+studentPass;	
						    	isCurrentSession = "true";
						}
						//////////////////////////////////////////////////////////////////
					}
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(6,null);
					CourseExist.get(m).set(6, Class_id);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(7,null);
					CourseExist.get(m).set(7, Class_Name);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(8,null);
					CourseExist.get(m).set(8, Grade_Name);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(9,null);
					CourseExist.get(m).set(9, School_Name);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(10,null);
					CourseExist.get(m).set(10, Group_Name);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(11,null);
					CourseExist.get(m).set(11, isCurrentSession);
					
					CourseExist.add(new ArrayList<String>());
					CourseExist.get(m).add(12,null);
					CourseExist.get(m).set(12, callApiURL);
					
					m=m+1;				
				}
			
			}
			
			Teacher_Schedule.setCourseExist(CourseExist);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teacher_Schedule;
	}
}
