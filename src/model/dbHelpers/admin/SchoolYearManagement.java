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

import model.beans.admin.School_Year;
import model.beans.admin.School_Year_Dates;
import model.beans.admin.School_Years;
import model.beans.admin.Year_Terms;
import model.dbHelpers.DbConnector;

public class SchoolYearManagement {
	public String manageSchoolYear(int school_id, int school_Year_id, String Year_Name, Date dYear_Start, Date dYear_End, HttpServletRequest request) throws ParseException{
	String message="";
	//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	try (Connection connection = DbConnector.connectToDb();
			Statement statement = connection.createStatement();
			Statement statementAssignmentsCheck = connection.createStatement();
			Statement statementAttendanceCheck = connection.createStatement();
			Statement statementYearDatesBeforeUpdate = connection.createStatement();
			Statement statementInsertedYears = connection.createStatement();
			Statement statementGradeTerms = connection.createStatement();
			Statement statementInsertedTerms = connection.createStatement();){
		
		int updateQuery = 0;
		String sYearDatesBeforeUpdate = "select DATE_FORMAT(Year_Start, '%Y-%m-%d') AS Current_Year_Start, DATE_FORMAT(Year_End, '%Y-%m-%d') AS Current_Year_End from school_year where School_Year_id = "+school_Year_id;
		ResultSet resultYearDatesBeforeUpdate = statementYearDatesBeforeUpdate.executeQuery(sYearDatesBeforeUpdate);	
		Date currentYS = null;
		Date currentYE = null;
		while(resultYearDatesBeforeUpdate.next())
		{
			currentYS = resultYearDatesBeforeUpdate.getDate("Current_Year_Start");
			currentYE = resultYearDatesBeforeUpdate.getDate("Current_Year_End");
		}
		if (dYear_End.compareTo(dYear_Start)>=0)
		{
			////////////////////////////Check for possible overlap ////////////////////////////////////////////
			String sInsertedYears;
			sInsertedYears = "select *, DATE_FORMAT(Year_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Year_End, '%Y-%m-%d') AS E_date  from School_Year where School_id="+school_id+" and School_Year_id<>"+school_Year_id;	
			ResultSet resultInsertedYears = statementInsertedYears.executeQuery(sInsertedYears);
			int Year_Allowed=1;
			while (resultInsertedYears.next())
			{
				
				Date dInsertedYear_Start = formater.parse(resultInsertedYears.getString("S_date"));
				Date dInsertedYear_End = formater.parse(resultInsertedYears.getString("E_date"));
				
				if (((dYear_Start.compareTo(dInsertedYear_Start)>=0)&&(dYear_Start.compareTo(dInsertedYear_End) <0))
						||((dYear_End.compareTo(dInsertedYear_Start)>0)&&(dYear_End.compareTo(dInsertedYear_End) <0))
						||((dYear_Start.compareTo(dInsertedYear_Start)<0)&&(dYear_End.compareTo(dInsertedYear_End) >=0)))
				{
					Year_Allowed=0;
				
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////
			if(Year_Allowed == 1)
			{
				if (school_Year_id==0)
				{

					String queryNewYear = "INSERT INTO school_year (school_year_id, school_id, Year_Name, Year_Start, year_End) VALUES (NULL,?, ?, ?, ?)";
					PreparedStatement pstatement = connection.prepareStatement(queryNewYear);
					pstatement.setInt(1, school_id);
					pstatement.setString(2, Year_Name);
					pstatement.setDate(3,new java.sql.Date(dYear_Start.getTime()));
					pstatement.setDate(4,new java.sql.Date(dYear_End.getTime()));
			        updateQuery = pstatement.executeUpdate();
			        pstatement.close();
			        if (updateQuery != 0)
				    {	
				    	message=message+"New School Year successfully created.<br>";
						String sSchool_year_id = "select school_year_id from school_year where school_id='"+school_id+"' and Year_Name='"+Year_Name+"'";
						ResultSet result = statement.executeQuery(sSchool_year_id);	
						while (result.next())
						{
							school_Year_id = result.getInt("School_Year_id");
						}
				     }
				}
				else
				{
					String sTerms = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS TS_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS VE_date from Year_Terms where School_Year_id="+school_Year_id;					
					ResultSet result = statement.executeQuery(sTerms);	
					int year_Update_Allowed=1;
					while (result.next())
					{
						Date dTerm_Start = formater.parse(result.getString("TS_date"));
						Date dVacation_End = formater.parse(result.getString("VE_date"));
						
						if ((dTerm_Start.compareTo(dYear_Start)>=0)	&& (dYear_End.compareTo(dVacation_End)>=0))
						{
							year_Update_Allowed = 1;
						}
						else
						{
							year_Update_Allowed = 0;
							break;
						}
					}
			    	if(year_Update_Allowed==1)
			    	{
						String sGradeTerms = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS GTS_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS GVE_date from Grade_Terms where School_Year_id="+school_Year_id;					
						ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);	
						while (resultGradeTerms.next())
						{
							Date dGrade_Term_Start = formater.parse(resultGradeTerms.getString("GTS_date"));
							Date dGrade_Vacation_End = formater.parse(resultGradeTerms.getString("GVE_date"));
							
							if ((dGrade_Term_Start.compareTo(dYear_Start)>=0)	&& (dYear_End.compareTo(dGrade_Vacation_End)>=0))
							{
								year_Update_Allowed = 1;
							}
							else
							{
								year_Update_Allowed = 0;
								break;
							}
						}
			    	}
					if (year_Update_Allowed==1)
					{
						/////////////////Before updating the year dates we need to check if there are assignments dates will be outside the school year ////////////////// 
						String sAssignmentsCheck = "select * from Assignments_Classes where Course_id in (select Course_id from Courses where Grade_id in(select grade_id from Grades where School_id in(Select School_id from School_Year where School_Year_id = "+school_Year_id+")))"
								+ " and ((Display_Date < '"+new java.sql.Date(dYear_Start.getTime())+"' and Display_Date >='"+currentYS+"') "
										+ "or (Start_Date < '"+new java.sql.Date(dYear_Start.getTime())+"' and Start_Date >= '"+currentYS+"')  "
												+ "or (Due_Date < '"+new java.sql.Date(dYear_Start.getTime())+"' and Due_Date >= '"+currentYS+"') "
														+ "or (Disappear_Date < '"+new java.sql.Date(dYear_Start.getTime())+"' and Disappear_Date >= '"+currentYS+"')"
										+ "or (Display_Date > '"+new java.sql.Date(dYear_End.getTime())+"' and Display_Date <= '"+currentYE+"') "
												+ "or (Start_Date > '"+new java.sql.Date(dYear_End.getTime())+"' and Start_Date <='"+currentYE+"') "
														+ "or (Due_Date > '"+new java.sql.Date(dYear_End.getTime())+"' and Due_Date <='"+currentYE+"') "
																+ "or (Disappear_Date > '"+new java.sql.Date(dYear_End.getTime())+"' and Disappear_Date <= '"+currentYE+"'))";
						ResultSet resultAssignmentsCheck = statementAssignmentsCheck.executeQuery(sAssignmentsCheck);	
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////Before updating the year dates we need to check if there are attendance dates will be outside the school year ////////////////// 
						String sAttendanceCheck = "select * from attendance where Course_id in (select Course_id from Courses where Grade_id in(select grade_id from Grades where School_id in(Select School_id from School_Year where School_Year_id = "+school_Year_id+")))"
								+ " and ((Session_Date < '"+new java.sql.Date(dYear_Start.getTime())+"' and Session_Date >= '"+currentYS+"' ) or (Session_Date > '"+new java.sql.Date(dYear_End.getTime())+"' and Session_Date <= '"+currentYE+"'))";
						ResultSet resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);	
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						String sSchoolYear = "update School_Year set Year_Name=?, Year_Start=?, Year_End=? where School_Year_id="+school_Year_id;
						PreparedStatement pstatement = connection.prepareStatement(sSchoolYear);
						updateQuery = 0;
						pstatement.setString(1, Year_Name);
						pstatement.setDate(2,new java.sql.Date(dYear_Start.getTime()));
						pstatement.setDate(3,new java.sql.Date(dYear_End.getTime()));
						if(!resultAssignmentsCheck.isBeforeFirst()&&!resultAttendanceCheck.isBeforeFirst())
						{
							updateQuery = pstatement.executeUpdate();
							pstatement.close();
							if(updateQuery!=0)
							{
								message = message+ "School Year Successfully Updated.<br>";
							}
						}
						else
						{
							message = message + "There are assignments and/or attendance sessions with dates outside the new school year dates you are trying to update. Year dates update is not allowed.<br>";
						}
					}
					else
					{
						message = message+ "School Year Start date or End date or both is invalid beacuase they conflict with already inserted Terms.<br>";
					}
				}
			}
			else
			{
				message = message + "School Year you are trying to create/update is overlapping with another already inserted year. Please correct year start and year end dates and try again";
			}

		}
		else
		{
			message= "Year Start Date is after the Year End Date, please correct the dates so that Year Start Date is before Year End Date.<br>";
		}
		if(updateQuery != 0)
		{
			int terms_qty = 0;
			if(request.getParameter("Terms_qty")!=null&&!request.getParameter("Terms_qty").equals("NaN")&&!request.getParameter("Terms_qty").equals("null"))
			{
				terms_qty = Integer.parseInt(request.getParameter("Terms_qty"));
			}
			else
			{
				terms_qty=1;
			}
			/////////////////Before updating the Year Terms dates we need to check if there are attendance dates will be outside the Year Terms Dates ////////////////// 
			boolean hasAtLeastOneTerm = false;
			String sAttendanceCheck="select * from attendance where Course_id in (select Course_id from Courses where Grade_id in (select Grade_id from Grades where school_id="+school_id+") and Grade_id not in (select Grade_id from Grade_Terms where school_year_id="+school_Year_id+"))";
			for (int i=1; i < terms_qty+1; i++)
			{
				if(request.getParameter("Term_Start"+i)!=null && !request.getParameter("Term_Start"+i).equals("") && request.getParameter("Term_End"+i)!=null && !request.getParameter("Term_End"+i).equals(""))
				{
					sAttendanceCheck = sAttendanceCheck + " and ((Session_Date < '"+new java.sql.Date(formater.parse(request.getParameter("Term_Start"+i)).getTime())+"' and Session_Date >= '"+currentYS+"') or (Session_Date > '"+new java.sql.Date(formater.parse(request.getParameter("Term_End"+i)).getTime())+"' and Session_Date <= '"+currentYE+"'))";
					hasAtLeastOneTerm = true;				
				}
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			if(terms_qty!=0)
			{
				ResultSet resultAttendanceCheck = null;
				if(hasAtLeastOneTerm == true)
				{
					resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);
				}	
				if(resultAttendanceCheck!=null && resultAttendanceCheck.isBeforeFirst())
				{
					message = message + "There are attendance sessions with dates outside the new Year terms dates you are trying to insert/update. Term(s) dates are not allowed.<br>";
				}
				else
				{
					for (int i=1; i < terms_qty+1; i++)
					{
						int term_id = 0;
						if(request.getParameter("Term_id"+i)!=null)
						{
							term_id = Integer.parseInt(request.getParameter("Term_id"+i));
						}
						String term_Name = request.getParameter("Term_Name"+i);
						String term_Start = request.getParameter("Term_Start"+i);
						String term_End  = request.getParameter("Term_End"+i);
						String vacation_Start = request.getParameter("Vacation_Start"+i);
						String vacation_End  = request.getParameter("Vacation_End"+i);
						if ((term_Start==""))
						{
							term_Start=null;
						}
						if ((term_Start!=null)&&(term_End!=null)&&(vacation_Start!=null)&&(vacation_End!=null))
						{
							Date dTerm_Start = formater.parse(term_Start);
							Date dTerm_End = formater.parse(term_End);
							Date dVacation_Start = formater.parse(vacation_Start);
							Date dVacation_End = formater.parse(vacation_End);
							if ((dYear_End.compareTo(dVacation_End)>=0)
									&&(dVacation_End.compareTo(dVacation_Start)>=0)
									&&(dVacation_Start.compareTo(dTerm_End)>=0)
									&&(dTerm_End.compareTo(dTerm_Start)>=0)
									&&(dTerm_Start.compareTo(dYear_Start)>=0))
							{
								String sTerms = null;
								String sInsertedTerms;
								//sInsertedTerms = "select *, DATE_FORMAT(Term_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Vacation_End, '%m/%d/%Y') AS VE_date  from Year_Terms where School_Year_id="+school_Year_id+" and Term_id<>"+term_id;
								sInsertedTerms = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS VE_date  from Year_Terms where School_Year_id="+school_Year_id+" and Term_id<>"+term_id;	
								ResultSet resultInsertedTerms = statementInsertedTerms.executeQuery(sInsertedTerms);
								int Term_Allowed=1;
								while (resultInsertedTerms.next())
								{
									
									Date dInsertedTerm_Start = formater.parse(resultInsertedTerms.getString("S_date"));
									Date dInsertedVacation_End = formater.parse(resultInsertedTerms.getString("VE_date"));
									
									if (((dTerm_Start.compareTo(dInsertedTerm_Start)>=0)&&(dTerm_Start.compareTo(dInsertedVacation_End) <0))
											||((dVacation_End.compareTo(dInsertedTerm_Start)>0)&&(dVacation_End.compareTo(dInsertedVacation_End) <0))
											||((dTerm_Start.compareTo(dInsertedTerm_Start)<0)&&(dVacation_End.compareTo(dInsertedVacation_End) >=0)))
									{
										Term_Allowed=0;
									
									}
									
								}
								if (Term_Allowed==1)
								{
									if (term_id==0)
									{
										
										sTerms = "INSERT INTO Year_Terms (Term_id, School_Year_id, Term_Name, Term_Start, Term_End, Vacation_Start, Vacation_End ) VALUES (Null, ?, ?, ?, ?,?,?)";										
										PreparedStatement pstatement = connection.prepareStatement(sTerms);
										pstatement.setInt(1, school_Year_id);
										pstatement.setString(2, term_Name);
										pstatement.setDate(3,new java.sql.Date(dTerm_Start.getTime()));
										pstatement.setDate(4,new java.sql.Date(dTerm_End.getTime()));
										pstatement.setDate(5,new java.sql.Date(dVacation_Start.getTime()));
										pstatement.setDate(6,new java.sql.Date(dVacation_End.getTime()));
										updateQuery = 0;
										updateQuery = pstatement.executeUpdate();
										pstatement.close();
										message = message+ "Term"+i+" Entered Successfully  <br>";
									}
									else
									{
										sTerms = "update Year_Terms set Term_Name=?, Term_Start=?, Term_End=?, Vacation_Start=?, Vacation_End=?, School_Year_id=? where Term_id="+term_id;
										PreparedStatement pstatement = connection.prepareStatement(sTerms);
										updateQuery = 0;
										pstatement.setString(1, term_Name);
										pstatement.setDate(2,new java.sql.Date(dTerm_Start.getTime()));
										pstatement.setDate(3,new java.sql.Date(dTerm_End.getTime()));
										pstatement.setDate(4,new java.sql.Date(dVacation_Start.getTime()));
										pstatement.setDate(5,new java.sql.Date(dVacation_End.getTime()));
										pstatement.setInt(6, school_Year_id);
										updateQuery = pstatement.executeUpdate();	
										pstatement.close();
										message = message+ "Term"+i+" Updated Successfully  <br>";
									}
								}
								else
								{
									message = message+ "Term"+i+": Not Entered. Terms are overlapping. <br>";
								}
							}
							else
							{
								message = message+ "Term"+i+": Term End Date must be after Term Start Date. <br> Vacation Start Date must be after Term End Date. <br> Vacation End Date must be after Vacation Start Date.<br> Term"+i+" not entered/updated <br>";
							}
						}
					}
				}
			}
		}
       
	} catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Year_Term_Unique"))
		message= message+ "Duplicate Entry for Term Name for the same School Year. Year Term not created/updated";	
		if(ee.toString().contains("School_Year_Unique"))
		message= message+ "Duplicate Entry for Year Name for the same School. School Year not created/updated";
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
	
	public School_Years getSchoolYearsList(int school_id){
		School_Years School_Years = new School_Years(); 
		String query = "select * from School_Year where School_id="+school_id+" order by year_start";
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> School_Year_id = new ArrayList<Integer>();
			ArrayList<String> Year_Name = new ArrayList<String>();
	    	while(result.next()) 
	    	{
	    		School_Year_id.add(result.getInt("School_Year_id"));	    		
	    		Year_Name.add(result.getString("Year_Name"));	    		
	        } 
    		School_Years.setSchool_Year_id(School_Year_id);
    		School_Years.setYear_Name(Year_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return School_Years;
	}
	
	public School_Year getSchoolYearDetails(int school_Year_id, int school_id){
		School_Year School_Year = new School_Year(); 
		//String query = "select *, DATE_FORMAT(Year_Start, '%m/%d/%Y') AS YS_date, DATE_FORMAT(Year_End, '%m/%d/%Y') AS YE_date from School_Year where School_Year_id="+school_Year_id+" order by Year_Start";
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementYearTerms = connection.createStatement();
				Statement statementAttendanceCheck = connection.createStatement();){

			if(school_Year_id==0)
			{
				Date today = new Date();
		    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' "
		    			+ "and School_id="+school_id;

		    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
		    	while(resultSchoolYear.next())
		    	{
		    		school_Year_id = resultSchoolYear.getInt("School_year_id");	
		    	}
			}
			String query = "select *, DATE_FORMAT(Year_Start, '%Y-%m-%d') AS YS_date, DATE_FORMAT(Year_End, '%Y-%m-%d') AS YE_date from School_Year where School_Year_id="+school_Year_id+" order by Year_Start";

			ResultSet result = statement.executeQuery(query);
			String Year_Name = null;
			String YS_date = null;
			String YE_date = null;
			while(result.next())
			{
				Year_Name = result.getString("Year_Name");
				YS_date = result.getString("YS_date");
				YE_date = result.getString("YE_date");
			}
			School_Year.setYear_Name(Year_Name);
			School_Year.setYS_date(YS_date);
			School_Year.setYE_date(YE_date);		
			
			//String sYearTerms = "select *, DATE_FORMAT(Term_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Term_End, '%m/%d/%Y') AS E_date, DATE_FORMAT(Vacation_Start, '%m/%d/%Y') AS VS_date, DATE_FORMAT(Vacation_End, '%m/%d/%Y') AS VE_date from Year_Terms where School_Year_id="+school_Year_id+" order by Term_Start";
			String sYearTerms = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Term_End, '%Y-%m-%d') AS E_date, DATE_FORMAT(Vacation_Start, '%Y-%m-%d') AS VS_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS VE_date from Year_Terms where School_Year_id="+school_Year_id+" order by Term_Start";
			ResultSet resultYearTerms = statementYearTerms.executeQuery(sYearTerms);	
    		ArrayList<ArrayList<String>> Year_Terms = new ArrayList<ArrayList<String>>();
    		int j=0;
    		while (resultYearTerms.next())
			{
				/// fill the year term array /////
        		Year_Terms.add(new ArrayList<String>());
        		Year_Terms.get(j).add(0,null);
        		Year_Terms.get(j).set(0, resultYearTerms.getString("Term_id"));
        		Year_Terms.get(j).add(1,null);
        		Year_Terms.get(j).set(1, resultYearTerms.getString("Term_Name"));
        		Year_Terms.get(j).add(2,null);
        		Year_Terms.get(j).set(2, resultYearTerms.getString("S_Date"));
        		Year_Terms.get(j).add(3,null);
        		Year_Terms.get(j).set(3, resultYearTerms.getString("E_Date"));
        		Year_Terms.get(j).add(4,null);
        		Year_Terms.get(j).set(4, resultYearTerms.getString("VS_Date"));
        		Year_Terms.get(j).add(5,null);
        		Year_Terms.get(j).set(5, resultYearTerms.getString("VE_Date"));
				//////////////////////////////////////Check if there are attendance sessions associated with this term ///////////////////////////////////////////
				String sAttendanceCheck = "select * from attendance where Course_id in (select Course_id from Courses where Grade_id in (select Grade_id from Grades where school_id=(select school_id from school_year where school_year_id="+school_Year_id+")) and Grade_id not in (select Grade_id from Grade_Terms where school_year_id="+school_Year_id+")) and (Session_Date >= '"+resultYearTerms.getDate("S_Date")+"' and Session_Date <= '"+resultYearTerms.getDate("E_Date")+"')";
				ResultSet resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);	
				if(resultAttendanceCheck.isBeforeFirst())
				{
					Year_Terms.get(j).add(6,null);
					Year_Terms.get(j).set(6, "hasAttendance");
				}
				else
				{
					Year_Terms.get(j).add(6,null);
					Year_Terms.get(j).set(6, null);
				}
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        		
    			j=j+1;
			}
    		School_Year.setYear_Terms(Year_Terms);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return School_Year;
	}
	
	public int deleteYearTerm(int term_id){
		String query = "Delete from Year_Terms where Term_id="+term_id;
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
	
	public Year_Terms getTermsNoSemesters(int school_year_id){
		String query = "select * from Year_Terms where School_year_id="+school_year_id+" and semester_id is null";
		Year_Terms Year_Terms = new Year_Terms();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> Year_Term_id = new ArrayList<Integer>();
			ArrayList<String> Year_Term_Name = new ArrayList<String>();
			while(result.next()) 
			{
				Year_Term_id.add(result.getInt("Term_id"));

				
				Year_Term_Name.add(result.getString("Term_Name"));
				
		    } 
			Year_Terms.setYear_Term_id(Year_Term_id);
			Year_Terms.setYear_Term_Name(Year_Term_Name);
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Year_Terms;
		}
	public School_Year_Dates getSchoolYearDates(int grade_id){
		School_Year_Dates School_Year_Dates= new School_Year_Dates();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSchoolYear = connection.createStatement();){

			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id=(select school_id from Grades where "
	    			+ "Grade_id="+grade_id+")";

	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	Date year_Start = null;
	    	Date year_End = null;
	    	while(resultSchoolYear.next())
	    	{
		    	year_Start = resultSchoolYear.getDate("Year_Start");
		    	year_End = resultSchoolYear.getDate("Year_End");
	    	}
	    	School_Year_Dates.setYear_Start(year_Start);
	    	School_Year_Dates.setYear_End(year_End);
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return School_Year_Dates;
	}
	public String getCurrentSchoolYear(int school_id){
		String year_Name =null; 
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchoolYear = connection.createStatement();){
			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	while(resultSchoolYear.next()) 
	    	{    		
	    		year_Name = resultSchoolYear.getString("Year_Name");	    		
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return year_Name;
	}
	public int getCurrentSchoolYearID(int school_id){
		int school_year_id =0; 
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchoolYear = connection.createStatement();){
			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	while(resultSchoolYear.next()) 
	    	{    		
	    		school_year_id = resultSchoolYear.getInt("school_year_id");	    		
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_year_id;
	}
	
	public String getSchoolYearName(int school_Year_id){
		String year_Name =null; 
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchoolYear = connection.createStatement();){
	    	String sSchoolYear = "select * from School_year where School_Year_id="+school_Year_id;
			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	while(resultSchoolYear.next()) 
	    	{    		
	    		year_Name = resultSchoolYear.getString("Year_Name");	    		
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return year_Name;
	}
}
