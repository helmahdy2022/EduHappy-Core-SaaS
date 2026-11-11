package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import model.beans.admin.Sessions;
import model.beans.admin.Sessions_Details;
import model.dbHelpers.DbConnector;

public class SessionManagement {
	public Sessions_Details getSessions(int grade_id){
	String query = "select * from Grade_Sessions where Grade_id="+grade_id+" order by Time_From";
	Sessions_Details sessions_Details = new Sessions_Details(); 
	boolean session_Exists = false;
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementAttendanceCheck = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		
		int session_qty = 0;
		if(result.isBeforeFirst())
		{
			session_Exists=true;
			sessions_Details.setSession_Exists(session_Exists);
			result.last();
			session_qty = result.getRow();
			sessions_Details.setSession_qty(session_qty);
			result.beforeFirst();
		}
		int j=0;
		ArrayList<ArrayList<String>> ExistingSessions = new ArrayList<ArrayList<String>>();
		while (result.next())
		{
			ExistingSessions.add(new ArrayList<String>());
			ExistingSessions.get(j).add(0,null);
			ExistingSessions.get(j).set(0, result.getString("Session_id"));
			ExistingSessions.get(j).add(1,null);
			ExistingSessions.get(j).set(1, result.getString("Time_From"));
			ExistingSessions.get(j).add(2,null);
			ExistingSessions.get(j).set(2, result.getString("Time_To"));
			//////////////////////////////////////Check if there are attendance associated with this session ///////////////////////////////////////////
			String sAttendanceCheck = "select * from attendance where session_id="+result.getString("Session_id");
			ResultSet resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);	
			if(resultAttendanceCheck.isBeforeFirst())
			{
				ExistingSessions.get(j).add(3,null);
				ExistingSessions.get(j).set(3, "hasAttendance");
			}
			else
			{
				ExistingSessions.get(j).add(3,null);
				ExistingSessions.get(j).set(3, null);
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			j=j+1;
		}
		sessions_Details.setExistingSessions(ExistingSessions);
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sessions_Details;
}

	
	public boolean isSessionAllowed(Time dTime_From, Time dTime_To, int session_id, int grade_id){
		String query = null;
		if (session_id==0)
		{ 
			query = "select * from Grade_Sessions where Grade_id="+grade_id;
		}
		else
		{
			query = "select * from Grade_Sessions where Grade_id="+grade_id+" and Session_id<>"+session_id;								
		}	 
	boolean session_Allowed = true;
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
		ResultSet result = statement.executeQuery(query);
		while (result.next())
		{
			Time dInsertedTimeFrom = result.getTime("Time_From");
			Time dInsertedTime_To = result.getTime("Time_To");
			
			if (((dTime_From.compareTo(dInsertedTimeFrom)>=0)&&(dTime_From.compareTo(dInsertedTime_To) <0))
					||((dTime_To.compareTo(dInsertedTimeFrom)>0)&&(dTime_To.compareTo(dInsertedTime_To) <0))
					||((dTime_From.compareTo(dInsertedTimeFrom)<0)&&(dTime_To.compareTo(dInsertedTime_To) >=0)))
			{
				session_Allowed=false;
			}
		}
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return session_Allowed;
}
	
public int createSession(Sessions sessionBean){
	String query = "INSERT INTO Grade_Sessions (Session_id, Time_From, Time_To, Grade_id ) VALUES (Null,?, ?, ?)";	
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){										
		
		pstatement.setTime(1, sessionBean.getTime_From());
		pstatement.setTime(2, sessionBean.getTime_To());
		pstatement.setInt(3, sessionBean.getGrade_id());
		updateQuery = pstatement.executeUpdate();
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}
		
public int updateSession(Sessions sessionBean, int session_id){
	String query = "update Grade_Sessions set Time_From=?, Time_To=?, Grade_id=? where Session_id="+session_id;
	int updateQuery = 0;
	
	try (Connection connection = DbConnector.connectToDb();
		 PreparedStatement pstatement = connection.prepareStatement(query);){
		
		pstatement.setTime(1, sessionBean.getTime_From());
		pstatement.setTime(2, sessionBean.getTime_To());
		pstatement.setInt(3, sessionBean.getGrade_id());
		updateQuery = pstatement.executeUpdate();
       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return updateQuery;
}

public int deleteSession(int session_id){
	String query = "Delete from Grade_Sessions where Session_id="+session_id;
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


}
