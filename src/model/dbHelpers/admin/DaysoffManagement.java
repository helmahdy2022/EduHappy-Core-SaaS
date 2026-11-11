package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Daysoff;
import model.beans.admin.Daysoff_Details;
import model.beans.admin.Daysoff_Schools_Grades;
import model.dbHelpers.DbConnector;

public class DaysoffManagement {
	public Daysoff_Schools_Grades getDaysoffSchoolsGrades(int daysoff_id, int admin_id){
		 
	    Daysoff_Schools_Grades Daysoff_Schools_Grades = new Daysoff_Schools_Grades();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchools = connection.createStatement();
				Statement statementGrades = connection.createStatement();
				Statement statementExistingGrade = connection.createStatement();){
			
		    String sSchools = "select * from School where school_id in (select school_id from admin_school where admin_id="+admin_id+") order by School_id";
		    ResultSet resultSchools = statementSchools.executeQuery(sSchools);			  
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> School_Name = new ArrayList<String>();
	    	HashMap<String, ArrayList<ArrayList<String>>> Grademap = new HashMap<String, ArrayList<ArrayList<String>>>();
	    	
	    	while (resultSchools.next())
	    	{
	    		School_id.add(resultSchools.getInt("School_id"));
	    		School_Name.add(resultSchools.getString("Name"));

		    	String sGrades = "select * from Grades where School_id='"+resultSchools.getString("School_id")+"' order by grade_index asc";
		    	ResultSet resultGrades = statementGrades.executeQuery(sGrades);
		    	ArrayList<ArrayList<String>> GradeList = new ArrayList<ArrayList<String>>();
		    	int j=0;
		    	while(resultGrades.next()) 
		    	{
			    	GradeList.add(new ArrayList<String>());
			    	GradeList.get(j).add(0,null);
	    			GradeList.get(j).set(0, resultGrades.getString("Grade_Name"));
	    			GradeList.get(j).add(1,null);
	    			GradeList.get(j).set(1, resultGrades.getString("Grade_id"));
	    			
	    			if (daysoff_id!=0)
	    			{
				  			String sExistingGrade = "select Grade_id from school_grade_daysoff where daysoff_id="+daysoff_id+" and Grade_id="+resultGrades.getString("grade_id");
				  			ResultSet resultExistingGrade = statementExistingGrade.executeQuery(sExistingGrade);
				  			String Grade_Exist;
				  			if (!resultExistingGrade.isBeforeFirst())
				  			{
				  				Grade_Exist="0";
				  			}
				  			else
				  			{
				  				Grade_Exist="1";
				  			}
			    			GradeList.get(j).add(2,null);
			    			GradeList.get(j).set(2, Grade_Exist);
	    			}
	    			Grademap.put(resultSchools.getString("School_id"), GradeList);
	    			j=j+1;
	    		
		    	}
	    	}
    		Daysoff_Schools_Grades.setSchool_id(School_id);
    		Daysoff_Schools_Grades.setSchool_Name(School_Name);
	    	Daysoff_Schools_Grades.setGrademap(Grademap);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Daysoff_Schools_Grades;
	}
	
	public String manageDaysoff(int daysoff_id, int admin_id, String daysoff_Name, Date dDaysoff_Start, Date dDaysoff_End, int all_Schools, HttpServletRequest request) {
	String message="";
	String sAccount = "Select Account_id from Admins where Admin_id="+admin_id;
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			Statement statementS = connection.createStatement();
			Statement statementG = connection.createStatement();
			Statement statementExistingDaysoff = connection.createStatement();
			Statement statementAccount = connection.createStatement();){
		
		ResultSet resultAccount = statementAccount.executeQuery(sAccount);
		int account_id=0;
		while(resultAccount.next())
		{
			account_id = resultAccount.getInt("Account_id");
		}		
		int updateQuery = 0;
		
		if (dDaysoff_End.compareTo(dDaysoff_Start)>=0)
		{
			if (daysoff_id==0)
			{

				String queryNewYear = "INSERT INTO Daysoff (Daysoff_id, Daysoff_Start, Daysoff_End, Daysoff_Name, All_Schools, Account_id) VALUES (NULL,?, ?, ?, ?, ?)";
				PreparedStatement pstatement = connection.prepareStatement(queryNewYear);
				pstatement.setDate(1,new java.sql.Date(dDaysoff_Start.getTime()));
				pstatement.setDate(2,new java.sql.Date(dDaysoff_End.getTime()));
				pstatement.setString(3, daysoff_Name);
				pstatement.setInt(4, all_Schools);
				pstatement.setInt(5, account_id);
				updateQuery = pstatement.executeUpdate();
				if(updateQuery!=0)
				{
					message = message+ "Day-off created successfully<br>";
				}
				pstatement.close();
			}
			else
			{
				String sDaysoff = "update Daysoff set Daysoff_Name=?, Daysoff_Start=?, Daysoff_End=?, All_Schools=? where Daysoff_id="+daysoff_id;
				PreparedStatement pstatement = connection.prepareStatement(sDaysoff);
				pstatement.setString(1, daysoff_Name);
				pstatement.setDate(2,new java.sql.Date(dDaysoff_Start.getTime()));
				pstatement.setDate(3,new java.sql.Date(dDaysoff_End.getTime()));
				pstatement.setInt(4, all_Schools);
				updateQuery = pstatement.executeUpdate();
				if(updateQuery!=0)
				{
					message = message + "Daysoff Updated Successfully  <br>";
				}
				pstatement.close();
			}
			
			if (updateQuery != 0 && all_Schools == 0)
			{
				String sDaysoff_id = "select Daysoff_id from Daysoff where Account_id="+account_id+" and Daysoff_Start='"+new java.sql.Date(dDaysoff_Start.getTime())+"' and Daysoff_End='"+new java.sql.Date(dDaysoff_End.getTime())+"'";
				ResultSet result = statement.executeQuery(sDaysoff_id);
				while (result.next())
				{
					daysoff_id = result.getInt("Daysoff_id");
					String sSchool_Id= "select School_id from school order by School_id ";
					ResultSet resultS = statementS.executeQuery(sSchool_Id);
					while (resultS.next())
					{

						String school_id = null; 
						school_id = request.getParameter("school"+resultS.getString("school_id"));
						String gSchool_Id= "select Grade_id from Grades where school_id="+school_id+" order by Grade_Index ";
						ResultSet resultG = statementG.executeQuery(gSchool_Id);
						while (resultG.next())
						{
							String grade_id = request.getParameter("grade"+resultG.getString("grade_id"));
							if (grade_id != null)
							{
								String sExistingDaysoff= "select * from school_grade_daysoff where school_id="+school_id+" and Daysoff_id="+daysoff_id+" and Grade_id="+grade_id;
								ResultSet resultExistingDaysoff = statementExistingDaysoff.executeQuery(sExistingDaysoff);
								if (!resultExistingDaysoff.isBeforeFirst())
								{
									String querySchool_Grade_Daysoff = "INSERT INTO school_grade_daysoff (School_id, Daysoff_id, Grade_id) VALUES (?,?, ?)";
									PreparedStatement pstatement = connection.prepareStatement(querySchool_Grade_Daysoff);
									pstatement.setString(1, school_id);
									pstatement.setInt(2, daysoff_id);
									pstatement.setString(3, grade_id);
									updateQuery = 0;
									updateQuery = pstatement.executeUpdate();	
									pstatement.close();
								}

							}
							else
							{
								//Delete
								String sDeleteExistingDaysoff = "Delete from school_grade_daysoff where Daysoff_id="+daysoff_id+" and Grade_id="+request.getParameter("hiddengrade"+resultG.getString("grade_id"));
								PreparedStatement psDeleteExistingDaysoff = connection.prepareStatement(sDeleteExistingDaysoff);
								updateQuery=0;
								updateQuery = psDeleteExistingDaysoff.executeUpdate();	
								psDeleteExistingDaysoff.close();
							}												
										
						}
					}
				}
						
			}
			else
			{
				//Delete
				String sDeleteExistingDaysoff = "Delete from school_grade_daysoff where Daysoff_id="+daysoff_id;
				PreparedStatement psDeleteExistingDaysoff = connection.prepareStatement(sDeleteExistingDaysoff);
				updateQuery=0;
				updateQuery = psDeleteExistingDaysoff.executeUpdate();						
					
			}
		}
		else
		{
			message= message+ "Day off start date must be before day off end date<br>";
		}
	       
	} catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Daysoff_Dates_Unique"))
		message= message+ "Duplicate Entry for Days-off for the same start and end dates. Days-off not created/updated";	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
	public Daysoff getDaysoffList(int admin_id){
		//String query =  "select *, DATE_FORMAT(Daysoff_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Daysoff_End, '%m/%d/%Y') AS E_date from Daysoff order by Daysoff_Start";
		String query =  "select *, DATE_FORMAT(Daysoff_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Daysoff_End, '%Y-%m-%d') AS E_date from Daysoff where Account_id=(select Account_id from Admins where Admin_id="+admin_id+") order by Daysoff_Start";
		Daysoff Daysoff = new Daysoff();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			
	 		ResultSet result = statement.executeQuery(query);
	    	ArrayList<Integer> Daysoff_id = new ArrayList<Integer>();
	    	ArrayList<String> Daysoff_Name = new ArrayList<String>();
	    	ArrayList<String> S_Date = new ArrayList<String>();
	    	ArrayList<String> E_Date = new ArrayList<String>();
	 		while (result.next())
			{
	 			Daysoff_id.add(result.getInt("Daysoff_id"));
	    		Daysoff.setDaysoff_id(Daysoff_id);
	    		
	    		Daysoff_Name.add(result.getString("Daysoff_Name"));
	    		Daysoff.setDaysoff_Name(Daysoff_Name);	
	    		
	    		S_Date.add(result.getString("S_Date"));
	    		Daysoff.setS_Date(S_Date);
	    		
	    		E_Date.add(result.getString("E_Date"));
	    		Daysoff.setE_Date(E_Date);
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Daysoff;
	}
	
	public Daysoff_Details getDaysoffDetails(int daysoff_id){
		//String query = "select *, DATE_FORMAT(Daysoff_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Daysoff_End, '%m/%d/%Y') AS E_date from Daysoff where Daysoff_id="+daysoff_id;
		String query = "select *, DATE_FORMAT(Daysoff_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Daysoff_End, '%Y-%m-%d') AS E_date from Daysoff where Daysoff_id="+daysoff_id;
		Daysoff_Details Daysoff_Details = new Daysoff_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			
	 		ResultSet result = statement.executeQuery(query);
	 	  	ArrayList<Integer> Daysoff_id = new ArrayList<Integer>();
	    	ArrayList<String> Daysoff_Name = new ArrayList<String>();
	    	ArrayList<String> S_Date = new ArrayList<String>();
	    	ArrayList<String> E_Date = new ArrayList<String>();
	    	ArrayList<Integer> All_Schools = new ArrayList<Integer>();
	 		while (result.next())
			{
	 			Daysoff_id.add(result.getInt("Daysoff_id"));
	    		Daysoff_Name.add(result.getString("Daysoff_Name"));
	    		S_Date.add(result.getString("S_Date"));
	    		E_Date.add(result.getString("E_Date"));
	    		All_Schools.add(result.getInt("All_Schools"));
			}
 			Daysoff_Details.setDaysoff_id(Daysoff_id);
    		Daysoff_Details.setDaysoff_Name(Daysoff_Name);
    		Daysoff_Details.setS_Date(S_Date);
    		Daysoff_Details.setE_Date(E_Date);
    		Daysoff_Details.setAll_Schools(All_Schools);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Daysoff_Details;
	}	
	
	public Daysoff_Details getDaysoffDetailsPerGrade(int grade_id, int admin_id){
		//String query = "select *, DATE_FORMAT(Daysoff_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Daysoff_End, '%m/%d/%Y') AS E_date from Daysoff where Daysoff_id="+daysoff_id;
		String query = "select *, DATE_FORMAT(Daysoff_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Daysoff_End, '%Y-%m-%d') AS E_date from Daysoff where Account_id=(select Account_id from Admins where Admin_id="+admin_id+") and (All_Schools =1 or Daysoff_id in (select Daysoff_id from school_grade_daysoff where grade_id="+grade_id+")) order by Daysoff_Start";
		Daysoff_Details Daysoff_Details = new Daysoff_Details();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			
	 		ResultSet result = statement.executeQuery(query);
	 	  	ArrayList<Integer> Daysoff_id = new ArrayList<Integer>();
	    	ArrayList<String> Daysoff_Name = new ArrayList<String>();
	    	ArrayList<String> S_Date = new ArrayList<String>();
	    	ArrayList<String> E_Date = new ArrayList<String>();
	    	ArrayList<Integer> All_Schools = new ArrayList<Integer>();
	 		while (result.next())
			{
	 			Daysoff_id.add(result.getInt("Daysoff_id"));
	    		Daysoff_Name.add(result.getString("Daysoff_Name"));
	    		S_Date.add(result.getString("S_Date"));
	    		E_Date.add(result.getString("E_Date"));
	    		All_Schools.add(result.getInt("All_Schools"));
			}
 			Daysoff_Details.setDaysoff_id(Daysoff_id);
    		Daysoff_Details.setDaysoff_Name(Daysoff_Name);
    		Daysoff_Details.setS_Date(S_Date);
    		Daysoff_Details.setE_Date(E_Date);
    		Daysoff_Details.setAll_Schools(All_Schools);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Daysoff_Details;
	}	
}
