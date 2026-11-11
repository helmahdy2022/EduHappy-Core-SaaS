package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.beans.admin.Admins;
import model.beans.admin.Message_Details;
import model.beans.admin.Messages_List;
import model.beans.admin.Senders;
import model.beans.admin.Teachers;
import model.beans.parent.Child;
import model.beans.teacher.Teacher_Students;
import model.dbHelpers.DbConnector;

public class MessagesManagement {
	public int createMessage(Message_Details message_Details){
		String query = "INSERT INTO Messages (Message_id, Sender_Student_id, Recepient_Student_id, Sender_Teacher_id, Recepient_Teacher_id, Sender_Admin_id, Recepient_Admin_id, Sender_Parent_id, Recepient_Parent_id, Message_Body, Message_Date, Message_Time, Group_Message, InConversation) VALUES (Null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statement = connection.createStatement();){
			
			String account_Owner = message_Details.getAccount_Owner();
			String user_Type = message_Details.getUser_Type();
			
			String sRecentInConv = "select message_id from Messages where ((sender_"+account_Owner+"_id="+message_Details.getAccount_id()+" and recepient_"+user_Type+"_id="+message_Details.getUser_id()+") or (recepient_"+account_Owner+"_id="+message_Details.getAccount_id()+" and sender_"+user_Type+"_id="+message_Details.getUser_id()+")) and InConversation=1";			
			ResultSet resultset = statement.executeQuery(sRecentInConv);
			
			int lastInConv = 0;
			
			while (resultset.next())
			{
				lastInConv = resultset.getInt(1);
			}
			
			if(account_Owner.equals("Student"))
			{
				pstatement.setInt(1, message_Details.getAccount_id()) ;
				pstatement.setNull(3, java.sql.Types.NULL);
				pstatement.setNull(5, java.sql.Types.NULL);
				pstatement.setNull(7, java.sql.Types.NULL);
			}
			
			if(account_Owner.equals("Teacher"))
			{
				pstatement.setInt(3, message_Details.getAccount_id()) ;
				pstatement.setNull(1, java.sql.Types.NULL);
				pstatement.setNull(5, java.sql.Types.NULL);
				pstatement.setNull(7, java.sql.Types.NULL);
			}	
			
			if(account_Owner.equals("Admin"))
			{
				pstatement.setInt(5, message_Details.getAccount_id()) ;
				pstatement.setNull(1, java.sql.Types.NULL);
				pstatement.setNull(3, java.sql.Types.NULL);
				pstatement.setNull(7, java.sql.Types.NULL);
			}	
			
			if(account_Owner.equals("Parent"))
			{
				pstatement.setInt(7, message_Details.getAccount_id()) ;
				pstatement.setNull(1, java.sql.Types.NULL);
				pstatement.setNull(3, java.sql.Types.NULL);
				pstatement.setNull(5, java.sql.Types.NULL);
			}	
			
			if(user_Type.equals("Student"))
			{
				pstatement.setInt(2, message_Details.getUser_id());
				pstatement.setNull(4, java.sql.Types.NULL);
				pstatement.setNull(6, java.sql.Types.NULL);
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			if(user_Type.equals("Teacher"))
			{
				pstatement.setInt(4, message_Details.getUser_id());
				pstatement.setNull(2, java.sql.Types.NULL);
				pstatement.setNull(6, java.sql.Types.NULL);
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			if(user_Type.equals("Admin"))
			{
				pstatement.setInt(6, message_Details.getUser_id());
				pstatement.setNull(2, java.sql.Types.NULL);
				pstatement.setNull(4, java.sql.Types.NULL);
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			if(user_Type.equals("Parent"))
			{
				pstatement.setInt(8, message_Details.getUser_id());
				pstatement.setNull(2, java.sql.Types.NULL);
				pstatement.setNull(4, java.sql.Types.NULL);
				pstatement.setNull(6, java.sql.Types.NULL);
			}
			pstatement.setString(9, message_Details.getMessage_Body());
			Date today = new Date();
			pstatement.setDate(10, new java.sql.Date(today.getTime()));
	        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
	        String time = localDateFormat.format(today);
	        pstatement.setString(11, time);
	        if(message_Details.getGroup_Message()==1)
	        {
	        	pstatement.setInt(12, 1);
	        }
	        else
	        {
	        	pstatement.setInt(12, 0);
	        }
	        pstatement.setInt(13, 1);
	        updateQuery = pstatement.executeUpdate();
	        if(updateQuery!=0 && lastInConv!=0)
	        {
	        	
		           String updateInConv = "update messages set InConversation = 0 where  message_id="+lastInConv;
		           pstatement.executeUpdate(updateInConv);       	
	        }
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Messages_List getMessages(int user_id, int account_id, String user_Type, String account_Owner){
		Messages_List messages_List = new Messages_List();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			
			String query = "select *, DATE_FORMAT(Message_Date, '%d %M %Y') AS Msg_Date, Time_FORMAT(Message_Time, '%h:%i %p') AS Msg_Time from Messages "
					+ "where (sender_"+user_Type+"_id="+user_id+" and recepient_"+account_Owner+"_id="+account_id+") or (sender_"+account_Owner+"_id="+account_id+" and recepient_"+user_Type+"_id="+user_id+") order by message_id" ;
			
			ResultSet resultset = statement.executeQuery(query);
		   	ArrayList<Integer> Message_id = new ArrayList<Integer>();
	    	ArrayList<String> Message_Body = new ArrayList<String>();
	    	ArrayList<Integer> Sender_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Recepient_id = new ArrayList<Integer>();
	    	ArrayList<String> Message_Date = new ArrayList<String>();
	    	ArrayList<String> Message_Time = new ArrayList<String>();
	        while(resultset.next()) {
	    		
	        	Message_id.add(resultset.getInt("Message_id"));
	        	Message_Body.add(resultset.getString("Message_Body"));
	        	if(resultset.getInt("Sender_Student_id")!=0)
	        	{
	        		Sender_id.add(resultset.getInt("Sender_Student_id"));
	        	}
	        	if(resultset.getInt("Sender_Teacher_id")!=0)
	        	{
	        		Sender_id.add(resultset.getInt("Sender_Teacher_id"));
	        	}   
	        	
	        	if(resultset.getInt("Sender_Admin_id")!=0)
	        	{
	        		Sender_id.add(resultset.getInt("Sender_Admin_id"));
	        	} 

	        	if(resultset.getInt("Sender_Parent_id")!=0)
	        	{
	        		Sender_id.add(resultset.getInt("Sender_Parent_id"));
	        	} 
	        	
	        	if(resultset.getInt("Recepient_Student_id")!=0)
	        	{
	        		Recepient_id.add(resultset.getInt("Recepient_Student_id"));
	        	}
	        	if(resultset.getInt("Recepient_Teacher_id")!=0)
	        	{
	        		Recepient_id.add(resultset.getInt("Recepient_Teacher_id"));
	        	}
	        	if(resultset.getInt("Recepient_Admin_id")!=0)
	        	{
	        		Recepient_id.add(resultset.getInt("Recepient_Admin_id"));
	        	}
	        	if(resultset.getInt("Recepient_Parent_id")!=0)
	        	{
	        		Recepient_id.add(resultset.getInt("Recepient_Parent_id"));
	        	} 
	        	
	        	Message_Date.add(resultset.getString("Msg_Date"));
	        	Message_Time.add(resultset.getString("Msg_Time"));
	        	
	        	String sMarkRead = "update Messages set message_Read=? where Message_id="+resultset.getInt("Message_id")+" and  (sender_"+account_Owner+"_id<>"+account_id+" or sender_"+account_Owner+"_id is null)";
	        	PreparedStatement pstatement = connection.prepareStatement(sMarkRead);
	    		pstatement.setInt(1, 1);
	            pstatement.executeUpdate();
	            pstatement.close();
	        	
	        } 
	        messages_List.setMessage_id(Message_id);
	        messages_List.setMessage_Body(Message_Body);
	        messages_List.setSender_id(Sender_id);
	        messages_List.setRecepient_id(Recepient_id);
	        messages_List.setMessage_Date(Message_Date);
	        messages_List.setMessage_Time(Message_Time);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages_List;
	}
	
	public Senders getSenders(int account_id, String account_Owner){
		String query = null;
		if(account_Owner.equals("Teacher") || account_Owner.equals("Admin"))
		{
			query = "Select * from Messages where (recepient_"+account_Owner+"_id="+account_id+" or Sender_"+account_Owner+"_id="+account_id+") and InConversation=1 and Group_Message=0 order by message_id desc";
		}
		else
		{
			query = "Select * from Messages where (recepient_"+account_Owner+"_id="+account_id+" or Sender_"+account_Owner+"_id="+account_id+") and InConversation=1 order by message_id desc";
		}
		
		
		Senders Senders = new Senders(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementNewMessages = connection.createStatement();
				Statement statementStudent = connection.createStatement();
				Statement statementTeacher = connection.createStatement();
				Statement statementAdmin = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> Student_First_Name = new ArrayList<String>();
	    	ArrayList<String> Student_Last_Name = new ArrayList<String>();
	    	ArrayList<String> Student_Username = new ArrayList<String>();
	    	ArrayList<Integer> Student_NewMessages = new ArrayList<Integer>();
			
	    	ArrayList<Integer> Teacher_id = new ArrayList<Integer>();
	    	ArrayList<String> Teacher_First_Name = new ArrayList<String>();
	    	ArrayList<String> Teacher_Last_Name = new ArrayList<String>();
	    	ArrayList<String> Teacher_Username = new ArrayList<String>();
	    	ArrayList<Integer> Teacher_NewMessages = new ArrayList<Integer>();
	    	
			ArrayList<Integer> Admin_id = new ArrayList<Integer>();
	    	ArrayList<String> Admin_First_Name = new ArrayList<String>();
	    	ArrayList<String> Admin_Last_Name = new ArrayList<String>();
	    	ArrayList<String> Admin_Username = new ArrayList<String>();
	    	ArrayList<Integer> Admin_NewMessages = new ArrayList<Integer>();
	    	
			ArrayList<Integer> Parent_id = new ArrayList<Integer>();
	    	ArrayList<String> Parent_First_Name = new ArrayList<String>();
	    	ArrayList<String> Parent_Last_Name = new ArrayList<String>();
	    	ArrayList<String> Parent_Username = new ArrayList<String>();
	    	ArrayList<Integer> Parent_NewMessages = new ArrayList<Integer>();
	    	while(result.next()) 
	    	{
	    		if(result.getInt("Sender_Student_id")!=0 || result.getInt("Recepient_Student_id")!=0)
	    		{
		    		String sStudent = "select * from Students where student_id="+result.getInt("Sender_Student_id")+" or student_id="+result.getInt("Recepient_Student_id");
		    		ResultSet resultStudent = statementStudent.executeQuery(sStudent);
		    		while(resultStudent.next())
		    		{
			    		if(!Student_id.contains(resultStudent.getInt("Student_id")))
			    		{
				    		Student_id.add(resultStudent.getInt("Student_id"));	    		
				    		Student_First_Name.add(resultStudent.getString("First_Name"));			    		
				    		Student_Last_Name.add(resultStudent.getString("Last_Name"));
				    		Student_Username.add(resultStudent.getString("Username"));
				    		String sNewMessages = "select count(*) from messages where Sender_Student_id="+resultStudent.getInt("Student_id")+" and recepient_"+account_Owner+"_id="+account_id+" and Message_Read=0";
				    		ResultSet resultNewMessages = statementNewMessages.executeQuery(sNewMessages);
				    		while(resultNewMessages.next())
				    		{
				    			Student_NewMessages.add(resultNewMessages.getInt(1));
				    		}	
			    		}
		
		    		}
	
	    		}
	    		if(result.getInt("Sender_Teacher_id")!=0 || result.getInt("Recepient_Teacher_id")!=0)
	    		{
		    		String sTeacher = "select * from Teachers where Teacher_id="+result.getInt("Sender_Teacher_id")+" or Teacher_id="+result.getInt("Recepient_Teacher_id");
		    		ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
		    		while(resultTeacher.next())
		    		{
			    		if(!Teacher_id.contains(resultTeacher.getInt("Teacher_id")))
			    		{
			    			Teacher_id.add(resultTeacher.getInt("Teacher_id"));	    		
			    			Teacher_First_Name.add(resultTeacher.getString("First_Name"));			    		
			    			Teacher_Last_Name.add(resultTeacher.getString("Last_Name"));
			    			Teacher_Username.add(resultTeacher.getString("Username"));
				    		String sNewMessages = "select count(*) from messages where Sender_Teacher_id="+resultTeacher.getInt("Teacher_id")+" and recepient_"+account_Owner+"_id="+account_id+" and Message_Read=0";
				    		ResultSet resultNewMessages = statementNewMessages.executeQuery(sNewMessages);
				    		while(resultNewMessages.next())
				    		{
				    			Teacher_NewMessages.add(resultNewMessages.getInt(1));
				    		}	
			    		}
		
		    		}
	
	    		}
	    		
	    		if(result.getInt("Sender_Admin_id")!=0 || result.getInt("Recepient_Admin_id")!=0)
	    		{
		    		String sAdmin = "select * from Admins where Admin_id="+result.getInt("Sender_Admin_id")+" or Admin_id="+result.getInt("Recepient_Admin_id");
		    		ResultSet resultAdmin = statementAdmin.executeQuery(sAdmin);
		    		while(resultAdmin.next())
		    		{
			    		if(!Admin_id.contains(resultAdmin.getInt("Admin_id")))
			    		{
			    			Admin_id.add(resultAdmin.getInt("Admin_id"));	    		
			    			Admin_First_Name.add(resultAdmin.getString("First_Name"));			    		
			    			Admin_Last_Name.add(resultAdmin.getString("Last_Name"));
			    			Admin_Username.add(resultAdmin.getString("Username"));
				    		String sNewMessages = "select count(*) from messages where Sender_Admin_id="+resultAdmin.getInt("Admin_id")+" and recepient_"+account_Owner+"_id="+account_id+" and Message_Read=0";
				    		ResultSet resultNewMessages = statementNewMessages.executeQuery(sNewMessages);
				    		while(resultNewMessages.next())
				    		{
				    			Admin_NewMessages.add(resultNewMessages.getInt(1));
				    		}	
			    		}
		
		    		}
	
	    		}
	    		if(result.getInt("Sender_Parent_id")!=0 || result.getInt("Recepient_Parent_id")!=0)
	    		{
		    		String sParent = "select * from Parents where Parent_id="+result.getInt("Sender_Parent_id")+" or Parent_id="+result.getInt("Recepient_Parent_id");
		    		ResultSet resultParent = statementAdmin.executeQuery(sParent);
		    		while(resultParent.next())
		    		{
			    		if(!Parent_id.contains(resultParent.getInt("Parent_id")))
			    		{
			    			Parent_id.add(resultParent.getInt("Parent_id"));	    		
			    			Parent_First_Name.add(resultParent.getString("First_Name"));			    		
			    			Parent_Last_Name.add(resultParent.getString("Last_Name"));
			    			Parent_Username.add(resultParent.getString("Username"));
				    		String sNewMessages = "select count(*) from messages where Sender_Parent_id="+resultParent.getInt("Parent_id")+" and recepient_"+account_Owner+"_id="+account_id+" and Message_Read=0";
				    		ResultSet resultNewMessages = statementNewMessages.executeQuery(sNewMessages);
				    		while(resultNewMessages.next())
				    		{
				    			Parent_NewMessages.add(resultNewMessages.getInt(1));
				    		}	
			    		}
		
		    		}
	
	    		}
	    	
	    	} 
	    	Senders.setStudent_id(Student_id);
	    	Senders.setStudent_First_Name(Student_First_Name);
	    	Senders.setStudent_Last_Name(Student_Last_Name);
	    	Senders.setStudent_Username(Student_Username);
	    	Senders.setStudent_NewMessages(Student_NewMessages);
	    	
	    	Senders.setTeacher_id(Teacher_id);
	    	Senders.setTeacher_First_Name(Teacher_First_Name);
	    	Senders.setTeacher_Last_Name(Teacher_Last_Name);
	    	Senders.setTeacher_Username(Teacher_Username);
	    	Senders.setTeacher_NewMessages(Teacher_NewMessages);
	    	
	    	Senders.setAdmin_id(Admin_id);
	    	Senders.setAdmin_First_Name(Admin_First_Name);
	    	Senders.setAdmin_Last_Name(Admin_Last_Name);
	    	Senders.setAdmin_Username(Admin_Username);
	    	Senders.setAdmin_NewMessages(Admin_NewMessages);
	    	
	    	Senders.setParent_id(Parent_id);
	    	Senders.setParent_First_Name(Parent_First_Name);
	    	Senders.setParent_Last_Name(Parent_Last_Name);
	    	Senders.setParent_Username(Parent_Username);
	    	Senders.setParent_NewMessages(Parent_NewMessages);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Senders;
	}
	
	public int getNewMessagesCount(int account_id, String account_Owner){
		String query = "select count(*) from messages where Message_Read=0 and recepient_"+account_Owner+"_id="+account_id;
		int newMessagesCount = -1;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
	    	while(result.next()) 
	    	{
	    		newMessagesCount = result.getInt(1);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newMessagesCount;
	}
	
	public Teacher_Students getTeacherStudents(int class_id, int course_id, int group_id){
		Teacher_Students Teacher_Students = new Teacher_Students();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudents = connection.createStatement();
				Statement statementElective = connection.createStatement();){

			String sStudents = null;
			
			if (group_id!=0)
	    	{			
				sStudents = "select * from students where student_id in (select student_id from Student_Groups where Group_id="+group_id+")"
						+ " and student_id in(select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id=(select course_id from "+"`Groups`"+" where group_id="+group_id+" ))))";
	    	}
	    	else
	    	{
				/////////////////Check if the Course is Elective ///////////////////
				String sElective = "select Elective from Courses where Course_id="+course_id;
				ResultSet resultElective = statementElective.executeQuery(sElective);
				resultElective.next();
	    		if (resultElective.getString("Elective").equals("1"))
	    		{
	    			sStudents = "select * from Students where Student_id in (Select Student_id from Student_Electives where Course_id="+course_id+")"
	    					+ " and student_id in (select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id="+course_id+")))";

	    		}
	    		else
	    		{
	    			sStudents = "select * from Students where class_id="+class_id;
	    		}
	    	}
			
	    	
			ResultSet resultStudents = statementStudents.executeQuery(sStudents);
	    	

	    	ArrayList<String> Student_id = new ArrayList<String>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();    	
	    	while (resultStudents.next())
	    	{

	    		Student_id.add(resultStudents.getString("Student_id"));    		
	    		First_Name.add(resultStudents.getString("First_Name"));	
	    		Last_Name.add(resultStudents.getString("Last_Name"));
		    }
	    	Teacher_Students.setStudent_id(Student_id);
	    	Teacher_Students.setFirst_Name(First_Name);
	    	Teacher_Students.setLast_Name(Last_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teacher_Students;
	}
	
	public Teachers getTeachers(int admin_id){
		Teachers Teachers = new Teachers();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeachers = connection.createStatement();){

			//String sTeachers = "select * from Teachers order by First_Name";
			String sTeachers = "select * from Teachers where Teacher_id in (select Teacher_id from Teacher_School where School_id in(select School_id from Admin_School where Admin_id="+admin_id+")) order by First_Name";
			ResultSet resultTeachers = statementTeachers.executeQuery(sTeachers);
	    	

	    	ArrayList<Integer> Teacher_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>(); 
	    	while (resultTeachers.next())
	    	{

	    		Teacher_id.add(resultTeachers.getInt("Teacher_id"));    		
	    		First_Name.add(resultTeachers.getString("First_Name"));	
	    		Last_Name.add(resultTeachers.getString("Last_Name"));
	    		Username.add(resultTeachers.getString("Username"));
		    }
	    	Teachers.setTeacher_id(Teacher_id);
	    	Teachers.setFirst_Name(First_Name);
	    	Teachers.setLast_Name(Last_Name);
	    	Teachers.setUsername(Username);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teachers;
	}
	
	public Admins getAdmins(int admin_id){
		Admins Admins = new Admins();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAdmins = connection.createStatement();){

			String sAdmins = "select * from Admins where Account_id = (Select Account_id from admins where admin_id="+admin_id+") order by First_Name";
			ResultSet resultAdmins = statementAdmins.executeQuery(sAdmins);
	    	

	    	ArrayList<Integer> Admin_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>(); 
	    	while (resultAdmins.next())
	    	{

	    		Admin_id.add(resultAdmins.getInt("Admin_id"));    		
	    		First_Name.add(resultAdmins.getString("First_Name"));	
	    		Last_Name.add(resultAdmins.getString("Last_Name"));
	    		Username.add(resultAdmins.getString("Username"));
		    }
	    	Admins.setAdmin_id(Admin_id);
	    	Admins.setFirst_Name(First_Name);
	    	Admins.setLast_Name(Last_Name);
	    	Admins.setUsername(Username);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Admins;
	}

	public Teachers getTeachersForParent(Child child){
		Teachers Teachers = new Teachers();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeachers = connection.createStatement();){
		
			ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> student_First_Name = new ArrayList<String>();
			ArrayList<String> student_Last_Name = new ArrayList<String>();
			student_id = child.getStudent_id();
			student_First_Name = child.getFirst_Name();
			student_Last_Name = child.getLast_Name();
			ArrayList<ArrayList<String>> teachers_List = new ArrayList<ArrayList<String>>();
			int i=0;
			for (int sstudent_id : student_id)
			{
				String sTeachers = "Select * from teachers where teacher_id in(select teacher_id from teacher_classes where class_id=(select class_id from students where student_id="+sstudent_id+"))";
				ResultSet resultTeachers = statementTeachers.executeQuery(sTeachers);

					while (resultTeachers.next())
			    	{
			    		teachers_List.add(new ArrayList<String>());
			    		teachers_List.get(i).add(0,null);
			    		teachers_List.get(i).set(0, String.valueOf(sstudent_id));
			    		teachers_List.get(i).add(1,null);
			    		teachers_List.get(i).set(1,resultTeachers.getString("Teacher_id"));
			    		teachers_List.get(i).add(2,null);
			    		teachers_List.get(i).set(2, resultTeachers.getString("First_Name"));
			    		teachers_List.get(i).add(3,null);
			    		teachers_List.get(i).set(3, resultTeachers.getString("Last_Name"));
			    		teachers_List.get(i).add(4,null);
			    		teachers_List.get(i).set(4, resultTeachers.getString("Username"));
				    	i=i+1;
				    }
			}  	
			Teachers.setStudent_id(student_id);
			Teachers.setStudent_First_Name(student_First_Name);
			Teachers.setStudent_Last_Name(student_Last_Name);
			Teachers.setTeachers_List(teachers_List);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teachers;
	}


	public Admins getAdminsForParent(int account_id){
		Admins Admins = new Admins();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAdmins = connection.createStatement();){

			String sAdmins = "select * from Admins where Account_id = "+account_id+" order by First_Name";
			ResultSet resultAdmins = statementAdmins.executeQuery(sAdmins);
	    	

	    	ArrayList<Integer> Admin_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>(); 
	    	while (resultAdmins.next())
	    	{

	    		Admin_id.add(resultAdmins.getInt("Admin_id"));    		
	    		First_Name.add(resultAdmins.getString("First_Name"));	
	    		Last_Name.add(resultAdmins.getString("Last_Name"));
	    		Username.add(resultAdmins.getString("Username"));
		    }
	    	Admins.setAdmin_id(Admin_id);
	    	Admins.setFirst_Name(First_Name);
	    	Admins.setLast_Name(Last_Name);
	    	Admins.setUsername(Username);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Admins;
	}
}
