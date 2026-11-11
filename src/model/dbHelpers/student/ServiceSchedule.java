package model.dbHelpers.student;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.beans.student.Student_Schedule;
import model.dbHelpers.DbConnector;


public class ServiceSchedule {
	@SuppressWarnings("static-access")
	public Student_Schedule getStudentSchedule(int student_id, String sclientTime, String sclientDay, String contextPath) throws java.text.ParseException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Student_Schedule Student_Schedule = new Student_Schedule();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementClass = connection.createStatement();
				Statement statementSessions = connection.createStatement();
				Statement statementWeekDays = connection.createStatement();
				Statement statementStudentCourse = connection.createStatement();
				Statement statementElectiveName = connection.createStatement();
				Statement statementGroupName = connection.createStatement();
				Statement statementClassName = connection.createStatement();
				Statement statementTeacher = connection.createStatement();			
				Statement statementIsCurrentSession = connection.createStatement();
				Statement statementDay = connection.createStatement();				
				Statement statementStudentName = connection.createStatement();				
				Statement statementUserPass = connection.createStatement();){
			
			String sClass = "select Class_id from Students where Student_id="+student_id;
			ResultSet ResultClass = statementClass.executeQuery(sClass);
			String  Class_id=null;
			ResultClass.next();
			Class_id = ResultClass.getString("Class_id");
/*			StudentManagement studentManagement = new StudentManagement();
			String sClass_id = studentManagement.getStudentClass(student_id);*/
	///////////////////////////////Array for Sessions//////////////////////////////////////////////////
			String sSessions = "select  *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End from Grade_Sessions where Grade_id =(select Grade_id from Classes where Class_id="+ Class_id+") order by Time_From";
			ResultSet ResultSessions = statementSessions.executeQuery(sSessions);
			ResultSessions.last();
			int Session_Count =ResultSessions.getRow();
			
			Student_Schedule.setSession_Count(Session_Count);
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
			Student_Schedule.setSessions(Sessions);
	///////////////////////////End Array for Sessions /////////////////////////////////////////////////
			
	///////////////////////////////Array for Week Days//////////////////////////////////////////////////			
			String sWeekDays = "select * from week_days where Day_id NOT IN "
					+ "(select day_id from school_weekend where school_id="
					+ "(select school_id from grades where Grade_id ="
					+ "(select Grade_id from Classes where Class_id="+ Class_id+"))) order by day_id";
			ResultSet ResultWeekDays = statementWeekDays.executeQuery(sWeekDays);
			ResultWeekDays.last();
			int Days_Count =ResultWeekDays.getRow();

			
			ResultWeekDays.beforeFirst();
			
			ArrayList<String> Day_id = new ArrayList<String>();
	    	ArrayList<String> Day_Name = new ArrayList<String>();
	    	
	    	while(ResultWeekDays.next()) 
	    	{
	    		Day_id.add(ResultWeekDays.getString("Day_id"));    		
	    		Day_Name.add(ResultWeekDays.getString("Day_Name"));
	        }
	///////////////////////////End Array for Week Days /////////////////////////////////////////////////

			
	///////////////////////////////////////////// Get the course from Schedule for the same session same day/////////////////////////
			int m=0;
			ArrayList<ArrayList<String>> CourseExist = new ArrayList<ArrayList<String>>();
			
	    	ResultWeekDays.beforeFirst();
			while (ResultWeekDays.next())
	    	{
				ResultSessions.beforeFirst();
				while (ResultSessions.next())
		    	{

					String Course_Name = "";
					String eClass_id = null;
					String Course_id = null;
					String Group_id = null;
					String Elective = null;
					String Teacher_Name = null;
					String sStudentCourse="Select Course_id, Group_id, Class_id, Teacher_id from Schedule where Session_id="+ResultSessions.getString("Session_id")+" and Day_id="+ResultWeekDays.getString("Day_id")+""
							+ " and ((Course_id in(Select Course_id from Student_Electives where Student_id="+student_id
							+ " and (Group_id in (Select Group_id from Student_Groups where Student_id="+student_id+")"
									+ " or Group_id is null))"
							+ " or(Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+Class_id+") and Elective=0)"
									+ " and Class_id="+Class_id+" and Group_id is null)"
							+ " or (Course_id in(Select Course_id from Courses where Grade_id =(Select Grade_id from Classes where Class_id="+Class_id+") and Elective=0))"
									+ " and Group_id in(Select Group_id from Student_Groups where Student_id="+student_id+")))";

					ResultSet resultStudentCourse = statementStudentCourse.executeQuery(sStudentCourse);
					String x="";
					String VC_Course_Name = null;
					while (resultStudentCourse.next())
					{
						Course_id = resultStudentCourse.getString("Course_id");
						String sElectiveName = "select Course_Name, Elective from Courses where Course_id="+resultStudentCourse.getString("Course_id");
						ResultSet resultElectiveName = statementElectiveName.executeQuery(sElectiveName);
						while(resultElectiveName.next())
						{
							Course_Name =x  + "<font size='2' color='black'><b/>Course: </font>" + resultElectiveName.getString("Course_Name");		
							VC_Course_Name = resultElectiveName.getString("Course_Name");
							if(resultElectiveName.getString("Elective").equals("1"))
							{
								Course_Name=Course_Name+"<font size='3' color='#CC0066'>(Elective)</font>";
							}
							Elective = resultElectiveName.getString("Elective");
						}
						
						String sGroupName = "select Group_Name from "+"`Groups`"+" where Group_id="+resultStudentCourse.getString("Group_id");
						ResultSet resultGroupName = statementGroupName.executeQuery(sGroupName);
						String VC_Group_Name = null;
						while(resultGroupName.next())
						{
							Course_Name =Course_Name+" " + "<font size='2' color='#CC0066'>" +resultGroupName.getString("Group_Name") +"</font>";
							VC_Group_Name = resultGroupName.getString("Group_Name");
						}
						
						String sClassName = "select Class_Name from Classes where Class_id="+resultStudentCourse.getString("Class_id");
						ResultSet resultClassName = statementClassName.executeQuery(sClassName);
						String VC_Class_Name = null;
						while(resultClassName.next())
						{
							Course_Name =Course_Name+ "<br/><font size='2' color='black'><b/>Class: </font><font size='2' color='blue'>" + resultClassName.getString("Class_Name")+"</font>";
							VC_Class_Name = resultClassName.getString("Class_Name");
						}

						String sTeacher=null;

						sTeacher = "select Teacher_id, First_name, Last_Name from Teachers where Teacher_id ="+resultStudentCourse.getString("Teacher_id");
						ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
						String VC_Teacher_Name = null;
						if(resultTeacher.isBeforeFirst())
						{
							while (resultTeacher.next())
							{
								String First_Name= resultTeacher.getString("First_Name");;
								String Last_Name= resultTeacher.getString("Last_Name");
								Teacher_Name = First_Name+" "+Last_Name;
								VC_Teacher_Name = Teacher_Name;
							}
						}
						else
						{
							Teacher_Name = "N/A";
						}

						Course_Name =Course_Name+ "<br/><font size='2' color='black'><b/>Teacher: </font><div style='white-space: pre-wrap;font:14px/1.2 Times New Roman;color:#834099'>" + Teacher_Name +"</div>";

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
						//Date date = null;
						//date = new Date();
						//Format formatter = new SimpleDateFormat("EEEE"); 
					   // String day_Name = formatter.format(date);
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
				    	if(resultIsCurrentSessions.isBeforeFirst() && day_id==ResultWeekDays.getInt("Day_id"))
						{
				    		Group_id = resultStudentCourse.getString("Group_id");
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
				    		String meetingName = null;
				    		 if(VC_Group_Name!=null && !VC_Group_Name.equals(null))
				    		 {
				    			 meetingName = "Course: "+ VC_Course_Name +" - " + "Group: "+ VC_Group_Name + " - " + "Class: "+ VC_Class_Name +" - " + "Teacher: "+ VC_Teacher_Name;
				    		 }
				    		 else
				    		 {
				    			 meetingName = "Course: "+ VC_Course_Name +" - " + "Class: "+ VC_Class_Name +" - " + "Teacher: "+ VC_Teacher_Name;
				    		 }			    					    		
				    		String jointType = "student";
				    		String sStudentName = "select First_Name, Last_Name, Username from Students where Student_id='"+student_id+"'";
					    	ResultSet resultStudentName = statementStudentName.executeQuery(sStudentName);
					    	while(resultStudentName.next())
					    	{
					    		attendeeName = resultStudentName.getString("First_Name")+" " + resultStudentName.getString("Last_Name");
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
							Course_Name =Course_Name+ "<br/><font size='2' color='black'><b/>Online Meeting: </font><a href='"+callApiURL+"' id='OnlineMeeting' target='_blank'>Click Here</a>";								
						}
						//////////////////////////////////////////////////////////////////
						eClass_id = resultStudentCourse.getString("Class_id");
						Course_id = resultStudentCourse.getString("Course_id");
						Group_id = resultStudentCourse.getString("Group_id");
						x = Course_Name+ "<br>"+ "<font size='3' color='red'>In Conflict with:</font><br> "; 
/*						if(Course_id==null)
						{
							Course_Name="<div class='scheduleBreak'> Break </div>";
						}*/
						CourseExist.add(new ArrayList<String>());
						CourseExist.get(m).add(0,null);
						CourseExist.get(m).set(0, ResultWeekDays.getString("Day_id"));
									
						CourseExist.get(m).add(1,null);
						CourseExist.get(m).set(1, ResultSessions.getString("Session_id"));

						CourseExist.get(m).add(2,null);
						CourseExist.get(m).set(2, Course_id);

						CourseExist.get(m).add(3,null);
						CourseExist.get(m).set(3, Group_id);

						CourseExist.get(m).add(4,null);
						CourseExist.get(m).set(4, Course_Name);		

						CourseExist.get(m).add(5,null);
						CourseExist.get(m).set(5, Elective);	

						CourseExist.get(m).add(6,null);
						CourseExist.get(m).set(6, Teacher_Name);
						
						sGroupName = "select Group_Name from "+"`Groups`"+" where Group_id="+Group_id;
						resultGroupName = statementGroupName.executeQuery(sGroupName);
						if (resultGroupName.isBeforeFirst())
						{
							while (resultGroupName.next())
							{
								CourseExist.add(new ArrayList<String>());
								CourseExist.get(m).add(7,null);
								CourseExist.get(m).set(7,  resultGroupName.getString("Group_Name"));
							}
						}
						else
						{
							CourseExist.add(new ArrayList<String>());
							CourseExist.get(m).add(7,null);
							CourseExist.get(m).set(7,  null);
						}

						if (eClass_id==null)
						{
							sClassName = "select Class_Name from Classes where Class_id="+Class_id;										
						}
						else
						{
							sClassName = "select Class_Name from Classes where Class_id="+eClass_id;

						}
						resultClassName = statementClassName.executeQuery(sClassName);
						if (resultClassName.isBeforeFirst())
						{
							while (resultClassName.next())
							{
								CourseExist.add(new ArrayList<String>());
								CourseExist.get(m).add(8,null);
								CourseExist.get(m).set(8,  resultClassName.getString("Class_Name"));
							}
						}
						else
						{
							CourseExist.add(new ArrayList<String>());
							CourseExist.get(m).add(8,null);
							CourseExist.get(m).set(8,  null);
						}
						m=m+1;						
					}
		    	}
	    	}
    		Student_Schedule.setDay_id(Day_id);
    		Student_Schedule.setDay_Name(Day_Name); 
			Student_Schedule.setDays_Count(Days_Count);
			Student_Schedule.setCourseExist(CourseExist);

	/////////////////////////End Check if the course is alre... ////////////////////////////////////////////////////////////////////////////////////////	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Student_Schedule;
	}
}
