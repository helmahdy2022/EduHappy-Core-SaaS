package model.dbHelpers.admin;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.beans.admin.Class_Schedule;
import model.beans.admin.Teacher_Details;
import model.dbHelpers.DbConnector;

public class ScheduleManagement {
	public Class_Schedule getSessionsByClass(Class_Schedule class_Schedule, int class_id){
	String query = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End from Grade_Sessions where Grade_id =(select Grade_id from Classes where Class_id="+ class_id+") order by Time_From";
	ArrayList<ArrayList<String>> ExistingSessions = new ArrayList<ArrayList<String>>();
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		int j=0;	
		while (result.next())
		{
			ExistingSessions.add(new ArrayList<String>());
			ExistingSessions.get(j).add(0,null);
			ExistingSessions.get(j).set(0, result.getString("Session_id"));
			ExistingSessions.get(j).add(1,null);
			ExistingSessions.get(j).set(1, result.getString("Time_Start"));
			ExistingSessions.get(j).add(2,null);
			ExistingSessions.get(j).set(2, result.getString("Time_End"));
			j=j+1;
		}
		int Session_Count =ExistingSessions.size();
		class_Schedule.setSession_Count(Session_Count);
		class_Schedule.setSessions(ExistingSessions);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return class_Schedule;
	}
	
	public Class_Schedule getWeekDaysByClass(Class_Schedule class_Schedule, int class_id){
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	
			
			String query = "select * from week_days where Day_id NOT IN "
					+ "(select day_id from school_weekend where school_id="
					+ "(select school_id from grades where Grade_id ="
					+ "(select Grade_id from Classes where Class_id="+ class_id+"))) order by day_id";
			
			ResultSet result = statement.executeQuery(query);
			result.last();
			int Days_Count = result.getRow();
			class_Schedule.setDays_Count(Days_Count);
			result.beforeFirst();
			
			ArrayList<Integer> Day_id = new ArrayList<Integer>();
			ArrayList<String> Day_Name = new ArrayList<String>();
	    	while(result.next()) 
	    	{
	    		Day_id.add(result.getInt("Day_id"));   		
	    		Day_Name.add(result.getString("Day_Name"));
	        } 
	    	class_Schedule.setDay_id(Day_id);
	    	class_Schedule.setDay_Name(Day_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_Schedule;
	}
	
	public Class_Schedule getScheduleCourses(Class_Schedule class_Schedule, int class_id){
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementClassDivision = connection.createStatement();
			 Statement statementCourses = connection.createStatement();
			 Statement statementCourseGroups = connection.createStatement()){
			
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
		    	sCourses = "select * from courses where Grade_id=(select Grade_id from Classes where Class_id="+ class_id+")";	    		
	    	}
	    	else
	    	{
		    	sCourses = "select * from courses where Course_id in (select Course_id from Division_Courses where Division_id="+division_id+")";

	    	}
			
			ResultSet resultCourses = statementCourses.executeQuery(sCourses);
			
			ArrayList<Integer> Course_id = new ArrayList<Integer>();
			ArrayList<Integer> Elective = new ArrayList<Integer>();
	    	ArrayList<String> Course_Name = new ArrayList<String>();
			ArrayList<Integer> Group_id = new ArrayList<Integer>();
	    	ArrayList<String> Group_Name = new ArrayList<String>();
	    	while(resultCourses.next())
			{
				String sCourseGroups = "select * from "+"`Groups`"+" where Course_id="+resultCourses.getString("Course_id");
				ResultSet resultCourseGroups = statementCourseGroups.executeQuery(sCourseGroups);
				if (resultCourseGroups.isBeforeFirst())
				{
					while (resultCourseGroups.next())
					{
			    		Course_id.add(resultCourses.getInt("Course_id"));
			    		Course_Name.add(resultCourses.getString("Course_Name"));
			    		Elective.add(resultCourses.getInt("Elective"));
			    		Group_id.add(resultCourseGroups.getInt("Group_id"));
			    		Group_Name.add(resultCourseGroups.getString("Group_Name"));

					}
					
				}
				else
				{
		    		Course_id.add(resultCourses.getInt("Course_id"));
		    		Course_Name.add(resultCourses.getString("Course_Name"));
		    		Elective.add(resultCourses.getInt("Elective"));
		    		Group_id.add(null);
		    		Group_Name.add(null);
				}

			}
    		class_Schedule.setCourse_id(Course_id);
    		class_Schedule.setCourse_Name(Course_Name);
    		class_Schedule.setElective(Elective);
    		class_Schedule.setGroup_id(Group_id);
    		class_Schedule.setGroup_Name(Group_Name);
	    	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_Schedule;
	}
	
	public Class_Schedule getScheduleExistingCourses(Class_Schedule class_Schedule, int admin_id, int class_id, String sclientTime, String sclientDay, String contextPath) throws ParseException{
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSessions = connection.createStatement();
			 Statement statementWeekDays = connection.createStatement();
			 Statement statementClassDivision = connection.createStatement();
			 Statement statementCourses = connection.createStatement();
			 Statement statementCourseGroups = connection.createStatement();
			 Statement statementCourseExist = connection.createStatement();
			 Statement statementTeacher = connection.createStatement();
			 Statement statementElectiveConflict = connection.createStatement();
			 Statement statementCourse_Name = connection.createStatement();
			 Statement statementClass_Name = connection.createStatement();
			 Statement statementGroup_Name = connection.createStatement();
			 Statement statementCourseElective = connection.createStatement();
			 Statement statementDay = connection.createStatement();
			 Statement statementIsCurrentSession = connection.createStatement();
			 Statement statementUserPass = connection.createStatement();
			 Statement statementAdminName = connection.createStatement();
			 Statement statementCourseName = connection.createStatement();				
			 Statement statementClassName = connection.createStatement();){
			
			
			String sSessions = "select * from Grade_Sessions where Grade_id =(select Grade_id from Classes where Class_id="+ class_id+") order by Time_From";
			ResultSet ResultSessions = statementSessions.executeQuery(sSessions);
			
			String sWeekDays = "select * from week_days where Day_id NOT IN "
					+ "(select day_id from school_weekend where school_id="
					+ "(select school_id from grades where Grade_id ="
					+ "(select Grade_id from Classes where Class_id="+ class_id+"))) order by day_id";
			ResultSet ResultWeekDays = statementWeekDays.executeQuery(sWeekDays);

	    	String sClassDivision = "select * from Classes where  Class_id="+ class_id;
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			resultClassDivision.next();
			division_id = resultClassDivision.getInt("Division_id");
	    	String sCourses=null;
			if (division_id==0)
	    	{
		    	sCourses = "select * from courses where Grade_id=(select Grade_id from Classes where Class_id="+ class_id+")";	    		
	    	}
	    	else
	    	{
		    	sCourses = "select * from courses where Course_id in (select Course_id from Division_Courses where Division_id="+division_id+")";

	    	}
			
			ResultSet resultCourses = statementCourses.executeQuery(sCourses);
			
			int m=0;
			ArrayList<ArrayList<String>> CourseExist = new ArrayList<ArrayList<String>>();

			ResultWeekDays.beforeFirst();
			while (ResultWeekDays.next())
	    	{
				ResultSessions.beforeFirst();
				while (ResultSessions.next())
		    	{

					resultCourses.beforeFirst();
			    	if(!resultCourses.isBeforeFirst())
			    	{
						String sBreaks = "select Course_id, Group_id from Schedule "
								+ "where Class_id="+class_id+" "
								+ "and Session_id="+ResultSessions.getString("Session_id")+" "
								+ "and Day_id="+ResultWeekDays.getString("Day_id")+" "
								+ "and Course_id is null";
						ResultSet resultCourseExist = statementCourseExist.executeQuery(sBreaks);
						while (resultCourseExist.next())
						{
							CourseExist.add(new ArrayList<String>());
							CourseExist.get(m).add(0,null);
							CourseExist.get(m).set(0, ResultWeekDays.getString("Day_id"));
										
							CourseExist.get(m).add(1,null);
							CourseExist.get(m).set(1, ResultSessions.getString("Session_id"));

							CourseExist.get(m).add(2,null);
							CourseExist.get(m).set(2, null);
							CourseExist.get(m).add(3,null);
							CourseExist.get(m).set(3, null);
							CourseExist.get(m).add(4,null);
							CourseExist.get(m).set(4, null);
							CourseExist.get(m).add(5,null);
							CourseExist.get(m).set(5, null);
							m=m+1;
						}
			    	}
			    	else
			    	{
		    			while(resultCourses.next())
		    			{	


							String sCourseGroups = "select * from "+"`Groups`"+" where Course_id="+resultCourses.getString("Course_id");
							ResultSet resultCourseGroups = null;
							resultCourseGroups = statementCourseGroups.executeQuery(sCourseGroups);
							if (resultCourseGroups.isBeforeFirst())
							{
								while (resultCourseGroups.next())
								{
									String sCourseExist = "select Course_id, Group_id from Schedule "
											+ "where Class_id="+class_id+" "
											+ "and Session_id="+ResultSessions.getString("Session_id")+" "
											+ "and Day_id="+ResultWeekDays.getString("Day_id")+" "
											+ "and (Course_id="+resultCourses.getString("Course_id")+" or Course_id is null)"
											+ " and Group_id="+resultCourseGroups.getString("Group_id");
									ResultSet resultCourseExist = statementCourseExist.executeQuery(sCourseExist);
											
									while (resultCourseExist.next())
									{
										CourseExist.add(new ArrayList<String>());
										CourseExist.get(m).add(0,null);
										CourseExist.get(m).set(0, ResultWeekDays.getString("Day_id"));
													
										CourseExist.get(m).add(1,null);
										CourseExist.get(m).set(1, ResultSessions.getString("Session_id"));

										CourseExist.get(m).add(2,null);
										CourseExist.get(m).set(2, resultCourseExist.getString("Course_id"));
										
										CourseExist.get(m).add(3,null);
										CourseExist.get(m).set(3, resultCourseGroups.getString("Group_id"));
										
										String sTeacher = "select Teacher_id, First_name, Last_Name from Teachers where Teacher_id ="
												+ "(select Teacher_id from schedule where day_id="+ResultWeekDays.getString("Day_id")+" and session_id="+ResultSessions.getString("Session_id")+" and class_id="+class_id+" "
														+ "and course_id="+resultCourseExist.getString("Course_id")
														+" and Group_id="+resultCourseGroups.getString("Group_id")+")";
										ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
										String Teacher_Name = null;
										while (resultTeacher.next())
										{
											String First_Name= resultTeacher.getString("First_Name");
											String Last_Name= resultTeacher.getString("Last_Name");
											Teacher_Name = First_Name+" "+Last_Name;
											
										}
										CourseExist.get(m).add(4,null);
										CourseExist.get(m).set(4, Teacher_Name);
										
										////////Check if this Course is in Conflict with other Courses in same session /////////////////
										String sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+ResultSessions.getString("Session_id")+" and Day_id="+ResultWeekDays.getString("Day_id")+""
												+ " and ((Course_id in(Select Course_id from Student_Electives where Student_id in(Select Student_id from Student_Groups where Group_id="+resultCourseExist.getString("Group_id")+")"
													+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Groups where Group_id="+resultCourseExist.getString("Group_id")+"))"
														+ " or Group_id is null))"
												+ " or(Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0)"
														+ " and Class_id in (Select Class_id from Students where Student_id in(Select Student_id from Student_Groups where Group_id="+resultCourseExist.getString("Group_id")+"))"
																+ " and Group_id is null)"
												+ " or (Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0))"
														+ " and Group_id in(Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Groups where Group_id="+resultCourseExist.getString("Group_id")+"))))"
												+ " and Class_id <> "+class_id+"";

										//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
										ResultSet resultElectiveConflict = statementElectiveConflict.executeQuery(sElectiveConflict);
										if (resultElectiveConflict.isBeforeFirst())
										{
											String Course_id_Conflict="";
											while (resultElectiveConflict.next())
											{
												String sCourse_Name = "select Course_Name from Courses where Course_id="+resultElectiveConflict.getString("Course_id");	
												ResultSet resultCourse_Name = statementCourse_Name.executeQuery(sCourse_Name);
												String Course_Name_Conflict=null;
												while (resultCourse_Name.next())
												{
													Course_Name_Conflict = resultCourse_Name.getString("Course_Name");
												}
												String sClass_Name = "select Class_Name from Classes where Class_id="+resultElectiveConflict.getString("Class_id");	
												ResultSet resultClass_Name = statementClass_Name.executeQuery(sClass_Name);
												String Class_Name_Conflict=null;
												while (resultClass_Name.next())
												{
													Class_Name_Conflict = resultClass_Name.getString("Class_Name");
												}
												String sGroup_Name = "select Group_Name from "+"`Groups`"+" where Group_id="+resultElectiveConflict.getString("Group_id");	
												ResultSet resultGroup_Name = statementGroup_Name.executeQuery(sGroup_Name);
												String Group_Name_Conflict="";
												while (resultGroup_Name.next())
												{
													Group_Name_Conflict = resultGroup_Name.getString("Group_Name");
												}
												Course_id_Conflict=Course_id_Conflict+"Course: "+Course_Name_Conflict+" "+Group_Name_Conflict+" - Class: "+Class_Name_Conflict+"<br/> ";
											}										
											
											CourseExist.get(m).add(5,null);
											CourseExist.get(m).set(5, "<font size='2' color='red'>In Conflict with:<br/> "+ Course_id_Conflict+"</font>");
										}
										else
										{
											CourseExist.get(m).add(5,null);
											CourseExist.get(m).set(5, null);
										}
										/////////////////////////////////////////////////////////////////////////////////////////////////
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
										String callApiURL = null;
								    	DateFormat formatterForClientTime = new SimpleDateFormat("HH:mm");
								    	java.sql.Time clientTime = new java.sql.Time(formatterForClientTime.parse(sclientTime).getTime());
									    int day_id=0;
								    	String sDay = "select * from Week_Days where Day_Name='"+day_Name+"'";
								    	ResultSet resultDay = statementDay.executeQuery(sDay);
								    	while(resultDay.next())
									    {
									    	day_id = resultDay.getInt("Day_id");
									    }
								    	String isCurrentSession = "false";
								    	String sIsCurrentSessions = "select * from Schedule where class_id="+class_id+" and day_id="+day_id+" and session_id="+ResultSessions.getInt("Session_id")+" and session_id in (select session_id from grade_sessions where time_from <= '"+clientTime+"' and time_to >= '"+clientTime+"')";
								    	ResultSet resultIsCurrentSessions = statementIsCurrentSession.executeQuery(sIsCurrentSessions);				    	
								    	if(resultIsCurrentSessions.isBeforeFirst() && day_id==ResultWeekDays.getInt("Day_id"))
										{
								    		String classPart = "Class"+class_id;
								    		String sessionPart = "Session"+ ResultSessions.getInt("Session_id");
								    		String dayPart = "Day"+day_id;
								    		String coursePart = "Course"+resultCourses.getString("Course_id");
								    		String groupPart = "Group"+resultCourseGroups.getString("Group_id");
								    		String meeting_id_Without_Client_Id = null;
								    		meeting_id_Without_Client_Id = classPart+sessionPart+dayPart+coursePart+groupPart;								    		
								    		String attendeeName = null;								    					    		
								    		String jointType = "teacher";
								    		String meetingName = null;
											String sAdminName = "select First_Name, Last_Name from Admins where Admin_id="+admin_id;	
											ResultSet resultAdminName = statementAdminName.executeQuery(sAdminName);
								    		while(resultAdminName.next())
								    		{
								    			attendeeName = resultAdminName.getString("First_Name")+" "+ resultAdminName.getString("Last_Name");
								    		}
											String sCourse_Name = "select Course_Name from Courses where Course_id="+resultCourseExist.getString("Course_id");	
											ResultSet resultCourseName = statementCourseName.executeQuery(sCourse_Name);
								    		String sClassName = "select class_name from classes where class_id="+class_id;
								    		ResultSet resultClassName = statementClassName.executeQuery(sClassName);
								    		String Course_Name = null;
								    		while(resultCourseName.next())
								    		{
								    			Course_Name = resultCourseName.getString("Course_Name");
								    		}							    		
								    		String Class_Name = null;
								    		while(resultClassName.next())
								    		{
								    			Class_Name = resultClassName.getString("Class_Name");
								    		}

								    		meetingName = "Course: "+ Course_Name +" - " + "Group: "+ resultCourseGroups.getString("Group_Name") + " - " + "Class: "+ Class_Name +" - " + "Teacher: "+ attendeeName;
								    		String sUserPass = "select teacherPass, studentPass from Schedule where Class_id='"+class_id+"' and Session_id="+ResultSessions.getInt("Session_id")+" and Day_id="+day_id;
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
										CourseExist.get(m).add(6,null);
										CourseExist.get(m).set(6, callApiURL);

										CourseExist.get(m).add(7,null);
										CourseExist.get(m).set(7, isCurrentSession);
										//////////////////////////////////////////////////////////////////
										m=m+1;

									}							
								}
							}
							else
							{
								String sCourseExist = "select Course_id, Group_id from Schedule "
										+ "where Class_id="+class_id+" "
										+ "and Session_id="+ResultSessions.getString("Session_id")+" "
										+ "and Day_id="+ResultWeekDays.getString("Day_id")+" "
										+ "and (Course_id="+resultCourses.getString("Course_id")+" or Course_id is null)";
								ResultSet resultCourseExist = statementCourseExist.executeQuery(sCourseExist);
										
								while (resultCourseExist.next())
								{
									CourseExist.add(new ArrayList<String>());
									CourseExist.get(m).add(0,null);
									CourseExist.get(m).set(0, ResultWeekDays.getString("Day_id"));
												
									CourseExist.get(m).add(1,null);
									CourseExist.get(m).set(1, ResultSessions.getString("Session_id"));

									CourseExist.get(m).add(2,null);
									CourseExist.get(m).set(2, resultCourseExist.getString("Course_id"));								
									String sTeacher = "select Teacher_id, First_name, Last_Name from Teachers where Teacher_id ="
											+ "(select Teacher_id from schedule where day_id="+ResultWeekDays.getString("Day_id")+" and session_id="+ResultSessions.getString("Session_id")+" and class_id="+class_id+" "
													+ "and course_id="+resultCourseExist.getString("Course_id")+")";	
									ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
									String Teacher_Name = null;
									while (resultTeacher.next())
									{
										String First_Name= resultTeacher.getString("First_Name");;
										String Last_Name= resultTeacher.getString("Last_Name");
										Teacher_Name = First_Name+" "+Last_Name;
										
									}

									CourseExist.get(m).add(3,null);
									CourseExist.get(m).set(3, null);

									CourseExist.get(m).add(4,null);
									CourseExist.get(m).set(4, Teacher_Name);
									
									////////Check if this Course is in Conflict with other Courses in same session /////////////////
									String sCourseElective = "select Elective from Courses where Course_id="+resultCourseExist.getString("Course_id");
									ResultSet resultCourseElective = statementCourseElective.executeQuery(sCourseElective);
									String Course_Elective="0";
									while (resultCourseElective.next())
									{
										Course_Elective=resultCourseElective.getString("Elective");
									}
									
									String sElectiveConflict=null;
									if(Course_Elective.equals("1"))
									{

										sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+ResultSessions.getString("Session_id")+" and Day_id="+ResultWeekDays.getString("Day_id")+""
												+ " and ((Course_id in(Select Course_id from Student_Electives where Student_id in(Select Student_id from Student_Electives where Course_id="+resultCourseExist.getString("Course_id")+")"
													+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Electives where Course_id="+resultCourseExist.getString("Course_id")+"))"
														+ " or Group_id is null))"
												+ " or(Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0)"
														+ " and Class_id in (Select Class_id from Students where Student_id in(Select Student_id from Student_Electives where Course_id="+resultCourseExist.getString("Course_id")+"))"
																+ " and Group_id is null)"
												+ " or (Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0))"
														+ " and Group_id in(Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Electives where Course_id="+resultCourseExist.getString("Course_id")+"))))"
												+ " and Class_id <> "+class_id+"";
									}
									else
									{
										sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+ResultSessions.getString("Session_id")+" and Day_id="+ResultWeekDays.getString("Day_id")+""
												+ " and ((Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0) and Group_id in (select Group_id from Students where Student_id in (Select Student_id from Students where Class_id = "+class_id+")))"
												+ " or Course_id in (Select Course_id from Student_Electives where Student_id in (Select Student_id from Students where Class_id="+class_id+")))"
												+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Students where Class_id="+class_id+")) or Group_id is null)"
												+ " and Class_id <> "+class_id+"";
									}
									
									ResultSet resultElectiveConflict = statementElectiveConflict.executeQuery(sElectiveConflict);
									if (resultElectiveConflict.isBeforeFirst())
									{
										String Course_id_Conflict="";
										while (resultElectiveConflict.next())
										{
											String sCourse_Name = "select Course_Name from Courses where Course_id="+resultElectiveConflict.getString("Course_id");	
											ResultSet resultCourse_Name = statementCourse_Name.executeQuery(sCourse_Name);
											String Course_Name_Conflict=null;
											while (resultCourse_Name.next())
											{
												Course_Name_Conflict = resultCourse_Name.getString("Course_Name");
											}
											String sClass_Name = "select Class_Name from Classes where Class_id="+resultElectiveConflict.getString("Class_id");	
											ResultSet resultClass_Name = statementClass_Name.executeQuery(sClass_Name);
											String Class_Name_Conflict=null;
											while (resultClass_Name.next())
											{
												Class_Name_Conflict = resultClass_Name.getString("Class_Name");
											}
											
											String sGroup_Name = "select Group_Name from "+"`Groups`"+" where Group_id="+resultElectiveConflict.getString("Group_id");	
											ResultSet resultGroup_Name = null;
											resultGroup_Name = statementGroup_Name.executeQuery(sGroup_Name);
											String Group_Name_Conflict="";
											while (resultGroup_Name.next())
											{
												Group_Name_Conflict = resultGroup_Name.getString("Group_Name");
											}

											Course_id_Conflict=Course_id_Conflict+"Course: "+Course_Name_Conflict+" "+ Group_Name_Conflict+ " - Class: "+Class_Name_Conflict+"<br/> ";
										}
										CourseExist.get(m).add(5,null);
										CourseExist.get(m).set(5, "<font size='2' color='red'>In Conflict with:<br/> "+ Course_id_Conflict+"</font>");
									}
									else
									{
										CourseExist.get(m).add(5,null);
										CourseExist.get(m).set(5, null);
									}
									/////////////////////////////////////////////////////////////////////////////////////////////////
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
									String callApiURL = null;
							    	DateFormat formatterForClientTime = new SimpleDateFormat("HH:mm");
							    	java.sql.Time clientTime = new java.sql.Time(formatterForClientTime.parse(sclientTime).getTime());
								    int day_id=0;
							    	String sDay = "select * from Week_Days where Day_Name='"+day_Name+"'";
							    	ResultSet resultDay = statementDay.executeQuery(sDay);
							    	while(resultDay.next())
								    {
								    	day_id = resultDay.getInt("Day_id");
								    }
							    	String isCurrentSession = "false";
							    	String sIsCurrentSessions = "select * from Schedule where class_id="+class_id+" and day_id="+day_id+" and session_id="+ResultSessions.getInt("Session_id")+" and session_id in (select session_id from grade_sessions where time_from <= '"+clientTime+"' and time_to >= '"+clientTime+"')";
							    	ResultSet resultIsCurrentSessions = statementIsCurrentSession.executeQuery(sIsCurrentSessions);				    	
							    	if(resultIsCurrentSessions.isBeforeFirst() && day_id==ResultWeekDays.getInt("Day_id"))
									{
							    		String classPart = "Class"+class_id;
							    		String sessionPart = "Session"+ ResultSessions.getInt("Session_id");
							    		String dayPart = "Day"+day_id;
							    		String coursePart = "Course"+resultCourses.getString("Course_id");
							    		String meeting_id_Without_Client_Id = null;
							    		meeting_id_Without_Client_Id = classPart+sessionPart+dayPart+coursePart;
							    		String attendeeName = null;			    		
							    		String jointType = "teacher";
										String sAdminName = "select First_Name, Last_Name from Admins where Admin_id="+admin_id;	
										ResultSet resultAdminName = statementAdminName.executeQuery(sAdminName);
							    		while(resultAdminName.next())
							    		{
							    			attendeeName = resultAdminName.getString("First_Name")+" "+ resultAdminName.getString("Last_Name");
							    		}
							    		String meetingName = null;
										String sCourse_Name = "select Course_Name from Courses where Course_id="+resultCourseExist.getString("Course_id");	
										ResultSet resultCourseName = statementCourseName.executeQuery(sCourse_Name);
							    		String sClassName = "select class_name from classes where class_id="+class_id;
							    		ResultSet resultClassName = statementClassName.executeQuery(sClassName);
							    		String Course_Name = null;
							    		while(resultCourseName.next())
							    		{
							    			Course_Name = resultCourseName.getString("Course_Name");
							    		}							    		
							    		String Class_Name = null;
							    		while(resultClassName.next())
							    		{
							    			Class_Name = resultClassName.getString("Class_Name");
							    		}
							    		meetingName = "Course: "+ Course_Name +" - " + "Class: "+ Class_Name +" - " + "Teacher: "+ attendeeName;
							    		String sUserPass = "select teacherPass, studentPass from Schedule where Class_id='"+class_id+"' and Session_id="+ResultSessions.getInt("Session_id")+" and Day_id="+day_id;
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
									CourseExist.get(m).add(6,null);
									CourseExist.get(m).set(6, callApiURL);

									CourseExist.get(m).add(7,null);
									CourseExist.get(m).set(7, isCurrentSession);
									//////////////////////////////////////////////////////////////////
									
									m=m+1;

								}		
							}	
		    			}
			    	}	
		    	}
	    	}
			class_Schedule.setCourseExist(CourseExist);


	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_Schedule;
	}
	
	public Teacher_Details getScheduleTeacher(int class_id, int course_id, int group_id){
		String query=null;
		if (group_id==0)
		{
			query = "select Teacher_id, First_name, Last_Name from Teachers where Teacher_id =(select Teacher_id from Teacher_Classes_Courses where class_id="+class_id+" and course_id="+course_id+" and Group_id is null)";
			
		}
		else
		{
			query = "select Teacher_id, First_name, Last_Name from Teachers where Teacher_id =(select Teacher_id from Teacher_Classes_Courses where class_id="+class_id+" and course_id="+course_id+" and Group_id="+group_id+")";

		}			
		Teacher_Details teacher_Details = new Teacher_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);	
			while(result.next())
			{
				teacher_Details.setTeacher_id(result.getInt("Teacher_id"));
				teacher_Details.setFirst_Name(result.getString("first_Name"));
				teacher_Details.setLast_Name(result.getString("last_Name"));	
			}  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher_Details;
		}
	public String createSchedule(String CG, int class_id, int session_id, int day_id, int teacher_id, int i, String message) throws UnsupportedEncodingException{

		String Schedule = "INSERT INTO Schedule (Class_id, Session_id, Day_id, Course_id, Teacher_id, Group_id, teacherPass, studentPass) VALUES (?,?, ?, ?,?,?,?,?)";
		int course_id=0;
		int group_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(Schedule);
				Statement statementCourseExist = connection.createStatement();
				Statement statementSessions = connection.createStatement();
				Statement statementExistingSessions = connection.createStatement();
				Statement statementTeacherConflictClass = connection.createStatement();
				Statement statementCourseType = connection.createStatement();
				Statement statementElectiveConflict = connection.createStatement();
				Statement statementStudentConflictClass = connection.createStatement();
				Statement statementAnyCourseExist = connection.createStatement();
				Statement statementTeacherGrade = connection.createStatement();
				Statement statementTeacherSchool = connection.createStatement();
				Statement statementStudentGrade = connection.createStatement();
				Statement statementStudentSchool = connection.createStatement();){
			
			if (!CG.equals("Select Course")&&!CG.equals(null))
			{
				if(!CG.equals("Break"))
				{
					String[] parts = CG.split(",");
					String sCourse_id = parts[0]; 
					String sGroup_id = parts[1]; 
					//sCourse_id=sCourse_id.substring(1, sCourse_id.length()-1);
					//sGroup_id=sGroup_id.substring(1, sGroup_id.length()-1);
						if (sGroup_id.equals(""))
						{
							sGroup_id=null;
						}
					course_id = Integer.parseInt(sCourse_id);
					if(sGroup_id!=null)
					{
						group_id = Integer.parseInt(sGroup_id);
					}
				}

				String sCourseExist=null;
				if (!CG.equals(null)&&!CG.equals("Break"))
				{
					
					sCourseExist = "select * from Schedule where Class_id="+class_id+" and Session_id="+session_id+" and Day_id="+day_id+" and Course_id="+course_id +"  and (Group_id="+group_id+" or Group_id is null)";
				}
				else
				{
					sCourseExist = "select * from Schedule where Class_id="+class_id+" "
							+ "and Session_id="+session_id+" and Day_id="+day_id+" and Course_id is null" ;						
				}
				ResultSet resultCourseExist = statementCourseExist.executeQuery(sCourseExist);
				int Teacher_Conflict=0;
				if (!resultCourseExist.isBeforeFirst())
				{
					//////////Check if the teacher's sessions are in Conflicts ///////////////////////////////////
					
					//Get session time from and time to from which is related to the session we are trying to insert
					String sSessions = "select * from Grade_Sessions where session_id="+session_id;
					ResultSet resultSessions = statementSessions.executeQuery(sSessions);
					//SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
					Time Time_From =null;
					Time Time_To = null;
					//Date dTime_From=null;
					//Date dTime_To=null;
					
					while (resultSessions.next())
					{
						Time_From = resultSessions.getTime("Time_From");
						Time_To = resultSessions.getTime("Time_To");
						//dTime_From = formater.parse(Time_From);
						//dTime_To = formater.parse(Time_To);
						//Get all sessions related to this teacher and this day from the schedule
					}
					String sExistingSessions = "select * from Grade_Sessions where session_id in(select session_id from schedule where teacher_id="+teacher_id +" and day_id="+day_id+" and class_id<>"+class_id+")";
					ResultSet resultExistingSessions = statementExistingSessions.executeQuery(sExistingSessions);
					//compare the session we are trying to insert with the already existing sessions related to this teach in the same day
					ResultSet resultTeacherConflictClass = null;
					String Teacher_Conflict_Class=null;
					while (resultExistingSessions.next())
					{
						Time ExFrom = resultExistingSessions.getTime("Time_From");
						Time ExTo = resultExistingSessions.getTime("Time_To");
						//Date dExFrom = formater.parse(ExFrom);
						//Date dExTo = formater.parse(ExTo);
						int num1 = Time_From.compareTo(ExFrom);
						int num2 = ExTo.compareTo(Time_From);
						int num3 = Time_To.compareTo(ExFrom);
						int num4 = ExTo.compareTo(Time_To);

						if (((num1>=0)&&(num2>0))||((num3>0)&&(num4>=0))||((num1<=0)&&(num4<=0)))
						{	
							
							Teacher_Conflict = 1;
							
							String sTeacherConflictClass = "select Class_id, Class_Name from Classes where Class_id=(select Class_id from Schedule where Session_id="+resultExistingSessions.getString("Session_id")+" and Teacher_id="+teacher_id+" and Day_id="+day_id+")";
							resultTeacherConflictClass = statementTeacherConflictClass.executeQuery(sTeacherConflictClass);
							resultTeacherConflictClass.next();
							Teacher_Conflict_Class=resultTeacherConflictClass.getString("Class_Name");
						}
					}
					
					//////////////Check for conflict between sessions of Elective or Core courses /////////////////////////////
					
					String sCourseType = "select Elective from Courses where Course_id="+course_id;
					ResultSet resultCourseType = statementCourseType.executeQuery(sCourseType);
					String Elective="0";
					while(resultCourseType.next())
					{
						Elective = resultCourseType.getString("Elective");
					}
					
					String sElectiveConflict=null;
					if (group_id==0 && course_id!=0)
					{
						
						if(Elective.equals("1"))
						{

							sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+session_id+" and Day_id="+day_id+""
									+ " and ((Course_id in(Select Course_id from Student_Electives where Student_id in(Select Student_id from Student_Electives where Course_id="+course_id+")"
										+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Electives where Course_id="+course_id+"))"
											+ " or Group_id is null))"
									+ " or(Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0)"
											+ " and Class_id in (Select Class_id from Students where Student_id in(Select Student_id from Student_Electives where Course_id="+course_id+"))"
													+ " and Group_id is null)"
									+ " or (Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0))"
											+ " and Group_id in(Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Electives where Course_id="+course_id+"))))"
									+ " and Class_id <> "+class_id+"";
						}
						else
						{
							sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+session_id+" and Day_id="+day_id+""
									+ " and ((Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0) and Group_id in (select Group_id from Students where Student_id in (Select Student_id from Students where Class_id = "+class_id+")))"
									+ " or Course_id in (Select Course_id from Student_Electives where Student_id in (Select Student_id from Students where Class_id="+class_id+")))"
									+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Students where Class_id="+class_id+")) or Group_id is null)"
									+ " and Class_id <> "+class_id+"";
						}

					}
					else
					{
						
						sElectiveConflict="Select Course_id, Group_id, Class_id from Schedule where Session_id="+session_id+" and Day_id="+day_id+""
								+ " and ((Course_id in(Select Course_id from Student_Electives where Student_id in(Select Student_id from Student_Groups where Group_id="+group_id+")"
									+ " and (Group_id in (Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Groups where Group_id="+group_id+"))"
										+ " or Group_id is null))"
								+ " or(Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0)"
										+ " and Class_id in (Select Class_id from Students where Student_id in(Select Student_id from Student_Groups where Group_id="+group_id+"))"
												+ " and Group_id is null)"
								+ " or (Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+class_id+") and Elective=0))"
										+ " and Group_id in(Select Group_id from Student_Groups where Student_id in (Select Student_id from Student_Groups where Group_id="+group_id+"))))"
								+ " and Class_id <> "+class_id+"";
					}
					ResultSet resultElectiveConflict = statementElectiveConflict.executeQuery(sElectiveConflict);
					int Elective_Conflict=0;
					
					String Student_Conflict_Class=null;
					if (resultElectiveConflict.isBeforeFirst())
					{
						Elective_Conflict=1;
						resultElectiveConflict.next();
						String sStudentConflictClass = "select Class_Name from Classes where Class_id="+resultElectiveConflict.getString("Class_id");
						ResultSet resultStudentConflictClass = statementStudentConflictClass.executeQuery(sStudentConflictClass);
						resultStudentConflictClass.next();
						Student_Conflict_Class=resultStudentConflictClass.getString("Class_Name");

					}
					//////////////////////////////////////////////////////////////////////////////////////////////////
					
					String sAnyCourseExist = "select Course_id from Schedule where Class_id="+class_id+" and Session_id="+session_id+" and Day_id="+day_id;
					ResultSet resultAnyCourseExist = statementAnyCourseExist.executeQuery(sAnyCourseExist);
					if (resultAnyCourseExist.isBeforeFirst() && Elective_Conflict!=1 && Teacher_Conflict!=1)
					{
						String sDeleteExistingCourse = "Delete from Schedule where Class_id="+class_id+" and Session_id="+session_id+" and Day_id="+day_id;
						PreparedStatement psDeleteExistingCourse = null;
						psDeleteExistingCourse = connection.prepareStatement(sDeleteExistingCourse);
						psDeleteExistingCourse.executeUpdate();
						psDeleteExistingCourse.close();
					}
					///////////////////////////////////////////////////////////////////////////////////////////////////
					//String Schedule = "INSERT INTO Schedule (Class_id, Session_id, Day_id, Course_id, Teacher_id, Group_id) VALUES (?,?, ?, ?,?,?)";
					pstatement.setInt(1, class_id);
					pstatement.setInt(2, session_id);
					pstatement.setInt(3, day_id);
					if (course_id==0)
					{
						pstatement.setString(4, null);							
					}
					else
					{
						pstatement.setInt(4, course_id);
					}

					if (teacher_id==0)
					{
						pstatement.setString(5, null);
					}
					else
					{	
	
						pstatement.setInt(5, teacher_id);
			
					}
					
					if (group_id==0)
					{
						pstatement.setString(6, null);
					}
					else
					{
						pstatement.setInt(6, group_id);
					}
					////////////////////Insert teacherPass and studentPass that are used for online meeting////////////////
		    		String meeting_id = null;
		    		String sClass_id = String.valueOf(class_id);
		    		String sSession_id = String.valueOf(session_id);
		    		String sDay_id = String.valueOf(day_id);
		    		String sCourse_id = String.valueOf(course_id);
		    		String sGroup_id = String.valueOf(group_id);
		    		String classPart = "Class"+sClass_id;
		    		String sessionPart = "Session"+ sSession_id;
		    		String dayPart = "Day"+sDay_id;
		    		String coursePart = "Course"+sCourse_id;
		    		String groupPart = "Group"+sGroup_id;
		    		
		    		if(group_id!=0)
		    		{
		    			meeting_id = classPart+sessionPart+dayPart+coursePart+groupPart;
		    		}
		    		else
		    		{
		    			meeting_id = classPart+sessionPart+dayPart+coursePart;
		    		}	
					String plain_teacherPass = meeting_id+"_teacher";
			    	String plain_studentPass = meeting_id+"_student";			    	
			    	byte[] bytesOfteacherPass = plain_teacherPass.getBytes("UTF-8");
			    	byte[] bytesOfstudentPass = plain_studentPass.getBytes("UTF-8");
			    	MessageDigest md = null;
					try {
						md = MessageDigest.getInstance("MD5");
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	byte[] teacherPassByte = md.digest(bytesOfteacherPass);
			    	byte[] studentPassByte = md.digest(bytesOfstudentPass);
			    	
			    	BigInteger bigIntTeacher= new BigInteger(1,teacherPassByte);
			    	String teacherPass = bigIntTeacher.toString(16);
			    	// Now we need to zero pad it if you actually want the full 32 chars.
			    	while(teacherPass.length() < 32 ){
			    		teacherPass = "0"+teacherPass;
			    	}
			    	
			    	BigInteger bigIntStudent= new BigInteger(1,studentPassByte);
			    	String studentPass = bigIntStudent.toString(16);
			    	// Now we need to zero pad it if you actually want the full 32 chars.
			    	while(studentPass.length() < 32 ){
			    		studentPass = "0"+studentPass;
			    	}						
			    	pstatement.setString(7, teacherPass);
			    	pstatement.setString(8, studentPass);					
					///////////////////////////////////////////////////////////////////////////////////////////////////////
					if (Teacher_Conflict ==1)
					{
						
						////////////////Get the Teacher Grade related to the Class which is in conflict/////////////////////
						String sTeacherGrade = "select Grade_id from Classes where Class_id="+resultTeacherConflictClass.getString("Class_id");
						ResultSet resultTeacherGrade = statementTeacherGrade.executeQuery(sTeacherGrade);
						resultTeacherGrade.next();
						///////////////////////////////////////////////////////////////////////////////////////////
						
						////////////////Get the Teacher School related to the Grade which is in conflict/////////////////////
						String sTeacherSchool = "select School_id from Grades where Grade_id="+resultTeacherGrade.getString("Grade_id");
						ResultSet resultTeacherSchool = statementTeacherSchool.executeQuery(sTeacherSchool);
						resultTeacherSchool.next();
						///////////////////////////////////////////////////////////////////////////////////////////

						
						message = message + "<br><b>Session No. "+i+":</b> The teacher you are trying to assign is busy in the same time in Class"
								+ " <a href='javascript:showSchedule("+resultTeacherConflictClass.getString("Class_id")+","+resultTeacherGrade.getString("Grade_id")+" ,"+resultTeacherSchool.getString("School_id")+")'>"
								+ "<font color='blue'> "+Teacher_Conflict_Class+"</font></a>";
					}
					else
						if (Elective_Conflict==1)
						{
							
							////////////////Get the Student Grade related to the Class which is in conflict/////////////////////
							String sStudentGrade = "select Grade_id from Classes where Class_id="+resultElectiveConflict.getString("Class_id");
							ResultSet resultStudentGrade = statementStudentGrade.executeQuery(sStudentGrade);
							resultStudentGrade.next();
							///////////////////////////////////////////////////////////////////////////////////////////
							
							////////////////Get the Student School related to the Grade which is in conflict/////////////////////
							String sStudentSchool = "select School_id from Grades where Grade_id="+resultStudentGrade.getString("Grade_id");
							ResultSet resultStudentSchool = statementStudentSchool.executeQuery(sStudentSchool);
							resultStudentSchool.next();
							///////////////////////////////////////////////////////////////////////////////////////////

							message = message + "<br><b>Session No. "+i+":</b> Sessions are in Conflict - <br>There are Students in the course you've selected studying another Course in same session in Class "
							+ " <a href='javascript:showSchedule("+resultElectiveConflict.getString("Class_id")+","+resultStudentGrade.getString("Grade_id")+" ,"+resultStudentSchool.getString("School_id")+")'>"
							+ "<font color='blue'> "+Student_Conflict_Class+"</font></a>";
						}
						else
						{

							pstatement.executeUpdate();	
							message = message + "<br><b>Session No. "+i+":</b> Session Updated";
						}
				
				}
			}
			else
			{
				//Delete
				String sAnyCourseExist = "select Course_id from Schedule where Class_id="+class_id+" and Session_id="+session_id+" and Day_id="+day_id;
				ResultSet resultAnyCourseExist = statementAnyCourseExist.executeQuery(sAnyCourseExist);
				if(resultAnyCourseExist.isBeforeFirst())
				{
					String sDeleteExistingCourse = "Delete from Schedule where Class_id="+class_id+" and Session_id="+session_id+" and Day_id="+day_id;
					PreparedStatement psDeleteExistingCourse = null;
					psDeleteExistingCourse = connection.prepareStatement(sDeleteExistingCourse);
					psDeleteExistingCourse.executeUpdate();
					
					message = message+"<br><b>Session No. "+i+":</b> Session Deleted";
				}

				
			}						
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		}

	public void deleteScheduleSessionsInWeekEnds(int school_id, int day_id){
		String query=null;
		query = "delete from schedule where day_id="+day_id+" and class_id in (select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))";			
		try (Connection connection = DbConnector.connectToDb();){
			PreparedStatement psDeleteScheduleSessions = null;
			psDeleteScheduleSessions = connection.prepareStatement(query);
			psDeleteScheduleSessions.executeUpdate();
			psDeleteScheduleSessions.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
