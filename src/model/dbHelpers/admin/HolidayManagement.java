package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Holidays_Details;
import model.dbHelpers.DbConnector;

public class HolidayManagement {
	public boolean isHolidaysExist(int country_id, int admin_id){

		//String query = "select *, DATE_FORMAT(Holiday_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Holiday_End, '%m/%d/%Y') AS E_date from National_Holidays where Country_id="+country_id+" order by Holiday_Start";
		String query = "select *, DATE_FORMAT(Holiday_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Holiday_End, '%Y-%m-%d') AS E_date from National_Holidays where Country_id="+country_id+" and Account_id=(select Account_id from Admins where Admin_id="+admin_id+") order by Holiday_Start";
	boolean holidaysExist = true;
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
		ResultSet result = statement.executeQuery(query);
		if(!result.isBeforeFirst())
		{
			holidaysExist = false;
		}

       
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return holidaysExist;
}
	
	public Holidays_Details getHolidayDetails(int country_id, int admin_id){
		 
		//String query = "select *, DATE_FORMAT(Holiday_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Holiday_End, '%m/%d/%Y') AS E_date from National_Holidays where Country_id="+country_id+" order by Holiday_Start";
		String query = "select *, DATE_FORMAT(Holiday_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Holiday_End, '%Y-%m-%d') AS E_date from National_Holidays where Country_id="+country_id+" and Account_id=(select Account_id from Admins where Admin_id="+admin_id+") order by Holiday_Start";
		Holidays_Details Holidays_Details = new Holidays_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement()){
						
			ResultSet result = statement.executeQuery(query);
			int j=0;
    		ArrayList<ArrayList<String>> ExistingHolidays = new ArrayList<ArrayList<String>>();
    		while (result.next())
			{
				ExistingHolidays.add(new ArrayList<String>());
				ExistingHolidays.get(j).add(0,null);
				ExistingHolidays.get(j).set(0, result.getString("Holiday_id"));
				ExistingHolidays.get(j).add(1,null);
				ExistingHolidays.get(j).set(1, result.getString("Holiday_Name"));
				ExistingHolidays.get(j).add(2,null);
				ExistingHolidays.get(j).set(2, result.getString("S_Date"));
				ExistingHolidays.get(j).add(3,null);
				ExistingHolidays.get(j).set(3, result.getString("E_Date"));
				
    			j=j+1;
			}
    		Holidays_Details.setExistingHolidays(ExistingHolidays);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Holidays_Details;
	}
	public int deleteHoliday(int holiday_id){
		String query = "Delete from National_Holidays where Holiday_id="+holiday_id;
		int updateQuery=0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public String manageHoliday(int country_id, int holidays, String delete, HttpServletRequest request, int admin_id) throws ParseException{
	String message="";
	String sAccount = "Select Account_id from Admins where Admin_id="+admin_id;
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	try (Connection connection = DbConnector.connectToDb();
			Statement statementAccount = connection.createStatement();){
		
		int updateQuery = 0;
		
		for (int i=1; i < holidays+1; i++)
		{
			String Holiday_id = request.getParameter("Holiday_id"+i);
			String Holiday_Name = request.getParameter("Holiday_Name"+i);
			String Holiday_Start = request.getParameter("Holiday_Start"+i);
			String Holiday_End  = request.getParameter("Holiday_End"+i);
			if ((Holiday_Start!=null)&&(Holiday_End!=null))
			{
				Date dHoliday_Start = formater.parse(Holiday_Start);
				Date dHoliday_End = formater.parse(Holiday_End);
				if (dHoliday_End.compareTo(dHoliday_Start)>=0)
				{
					String sHolidays = null;
					if (Holiday_id==null)
					{
						
						ResultSet resultAccount = statementAccount.executeQuery(sAccount);
						int account_id=0;
						while(resultAccount.next())
						{
							account_id = resultAccount.getInt("Account_id");
						}
						sHolidays = "INSERT INTO National_Holidays (Holiday_id, Country_id, holiday_Name, Holiday_Start, Holiday_End, Account_id ) VALUES (Null, ?, ?, ?, ?, ?)";										
						PreparedStatement pstatement = connection.prepareStatement(sHolidays);
						pstatement.setInt(1, country_id);
						pstatement.setString(2, Holiday_Name);
						pstatement.setDate(3,new java.sql.Date(dHoliday_Start.getTime()));
						pstatement.setDate(4,new java.sql.Date(dHoliday_End.getTime()));
						pstatement.setInt(5,account_id);
						updateQuery = 0;
						updateQuery = pstatement.executeUpdate();
						if(updateQuery!=0)
						{
							message=message+"Holiday"+i+" Entered Successfully  <br>";
						}
						pstatement.close();
					}
					else
					{
						if (delete!=null)
						{
							message = message + "Logic is correct";
						}
						else
						{
							sHolidays = "update National_Holidays set Holiday_Name=?, Holiday_Start=?, Holiday_End=?, Country_id=? where Holiday_id="+Holiday_id;
							PreparedStatement pstatement = connection.prepareStatement(sHolidays);
							updateQuery = 0;
							pstatement.setString(1, Holiday_Name);
							pstatement.setDate(2,new java.sql.Date(dHoliday_Start.getTime()));
							pstatement.setDate(3,new java.sql.Date(dHoliday_End.getTime()));
							pstatement.setInt(4, country_id);
							updateQuery = pstatement.executeUpdate();
							if(updateQuery!=0)
							{
								message = message + "Holiday"+i+" Updated Successfully  <br>";
							}
							pstatement.close();
							
						}
					}
											
				}
				else
				{
					message = message + "Holiday"+i+"Holiday End Date must be after Holiday Start Day, Holiday not entered <br>";
				}

			}

		}	
       
	}catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Holiday_Dates_Unique"))
		message= message+ "Duplicate Entry for Holiday Name for the same dates same country. Holiday not created/updated";	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
}
