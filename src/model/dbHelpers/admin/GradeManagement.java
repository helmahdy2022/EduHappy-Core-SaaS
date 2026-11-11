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

import model.beans.admin.Courses;
import model.beans.admin.Grade_Details;
import model.beans.admin.Grade_Semester_Terms;
import model.beans.admin.Grade_Terms;
import model.beans.admin.Grading_Scale_Details;
import model.beans.admin.Grading_Scales;
import model.dbHelpers.DbConnector;

public class GradeManagement {

	public Grade_Details getGrade(int grade_id){
		String query = "select * from Grades where Grade_id="+grade_id;
		Grade_Details grade_Details = new Grade_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				grade_Details.setGrade_id(grade_id);
				grade_Details.setGrade_Name(result.getString("Grade_Name"));
				grade_Details.setGrade_Index(result.getInt("grade_Index"));
				grade_Details.setPassing_Grade(result.getInt("passing_Grade"));
				grade_Details.setMax_Grade(result.getInt("max_Grade"));
				grade_Details.setSchool_id(result.getInt("school_id"));
				grade_Details.setDisplayHideReport(result.getInt("DisplayHideReport"));
			}      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade_Details;
	}
	public int getGradeByClass(int class_id){
		String query = "select Grade_id from Classes where Class_id="+class_id;
		int grade_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			result.next();
			grade_id = result.getInt("Grade_id");
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade_id;
	}
	public int createGrade(Grade_Details grade_Details){
		String query = "INSERT INTO Grades (Grade_id, school_id, Grade_Name, Passing_Grade, Max_Grade, Grade_Index, DisplayHideReport) VALUES (Null,?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setInt(1, grade_Details.getSchool_id());
			pstatement.setString(2, grade_Details.getGrade_Name());
			pstatement.setInt(3, grade_Details.getPassing_Grade());
			pstatement.setInt(4, grade_Details.getMax_Grade());
			pstatement.setInt(5, grade_Details.getGrade_Index());
			pstatement.setInt(6, grade_Details.getDisplayHideReport());
	        updateQuery = pstatement.executeUpdate();
	       
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("School_Grade_Unique") || ee.toString().contains("School_Index_Unique") || ee.toString().contains("School_Grade_Index_Unique"))
				updateQuery = -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
			
	public int updateGrade(Grade_Details grade_Details, int grade_id){
		String query = "update Grades set Grade_Name=?, Passing_Grade=?, Max_Grade=?, Grade_Index=?, DisplayHideReport=? where Grade_id="+grade_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, grade_Details.getGrade_Name());
			pstatement.setInt(2, grade_Details.getPassing_Grade());
			pstatement.setInt(3, grade_Details.getMax_Grade());
			pstatement.setInt(4, grade_Details.getGrade_Index());
			pstatement.setInt(5, grade_Details.getDisplayHideReport());
	        updateQuery = pstatement.executeUpdate();
	       
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("School_Grade_Unique") || ee.toString().contains("School_Index_Unique") || ee.toString().contains("School_Grade_Index_Unique"))
				updateQuery = -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteGrade(int grade_id){
		String query = "Delete from Grades where Grade_id="+grade_id;
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

	public Grade_Terms getGradeTermsDetails(int grade_id, int school_Year_id) {
		Grade_Terms Grade_Terms = new Grade_Terms();		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementAttendanceCheck = connection.createStatement();){
						
			if(school_Year_id==0)
			{
				Date today = new Date();
		    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' "
		    			+ "and School_id=(select school_id from Grades where "
		    			+ "Grade_id="+grade_id+")";

		    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
		    	while(resultSchoolYear.next())
		    	{
		    		school_Year_id = resultSchoolYear.getInt("School_year_id");	
		    	}
			}
			String query = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Term_End, '%Y-%m-%d') AS E_date, DATE_FORMAT(Vacation_Start, '%Y-%m-%d') AS VS_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS VE_date from Grade_Terms where Grade_id="+grade_id+" and School_Year_id="+school_Year_id+" order by Term_Start";
			ResultSet result = statement.executeQuery(query);
			ArrayList<ArrayList<String>> Grade_Terms_List = new ArrayList<ArrayList<String>>();
			int j=0;
			while (result.next())
        		
				{
						Grade_Terms_List.add(new ArrayList<String>());
						Grade_Terms_List.get(j).add(0,null);
						Grade_Terms_List.get(j).set(0, result.getString("Grade_Term_id"));
						Grade_Terms_List.get(j).add(1,null);
						Grade_Terms_List.get(j).set(1, result.getString("Term_Name"));
						Grade_Terms_List.get(j).add(2,null);
						Grade_Terms_List.get(j).set(2, result.getString("S_Date"));
						Grade_Terms_List.get(j).add(3,null);
						Grade_Terms_List.get(j).set(3, result.getString("E_Date"));
						Grade_Terms_List.get(j).add(4,null);
						Grade_Terms_List.get(j).set(4, result.getString("VS_Date"));
						Grade_Terms_List.get(j).add(5,null);
						Grade_Terms_List.get(j).set(5, result.getString("VE_Date"));
						//////////////////////////////////////Check if there are attendance sessions associated with this term ///////////////////////////////////////////
						String sAttendanceCheck = "select * from attendance where Course_id in (select Course_id from Courses where Grade_id="+grade_id+") and (Session_Date >= '"+result.getDate("S_Date")+"' and Session_Date <= '"+result.getDate("E_Date")+"')";
						ResultSet resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);	
						if(resultAttendanceCheck.isBeforeFirst())
						{
							Grade_Terms_List.get(j).add(6,null);
							Grade_Terms_List.get(j).set(6, "hasAttendance");
						}
						else
						{
							Grade_Terms_List.get(j).add(6,null);
							Grade_Terms_List.get(j).set(6, null);
						}
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						j=j+1;
				}
       		Grade_Terms.setGrade_Terms_List(Grade_Terms_List);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Grade_Terms;
	}	
	
	public Grade_Semester_Terms getGradeTermsNoSemesters(int school_year_id, int grade_id){
		String query = "select * from Grade_Terms where School_year_id="+school_year_id+ " and Grade_id="+grade_id+" and semester_id is null";
		Grade_Semester_Terms Grade_Semester_Terms = new Grade_Semester_Terms();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> Grade_Term_id = new ArrayList<Integer>();
			ArrayList<String> Grade_Term_Name = new ArrayList<String>();
			while(result.next()) 
			{
				Grade_Term_id.add(result.getInt("Grade_Term_id"));

				
				Grade_Term_Name.add(result.getString("Term_Name"));
				
		    } 
			Grade_Semester_Terms.setGrade_Term_id(Grade_Term_id);
			Grade_Semester_Terms.setGrade_Term_Name(Grade_Term_Name);
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Grade_Semester_Terms;
		}
	
	public int deleteGradeTerm(int grade_Term_id){
		String query = "Delete from Grade_Terms where Grade_Term_id="+grade_Term_id;
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
	
	public String manageGradeTerms(int school_Year_id, int grade_id, int terms_qty, HttpServletRequest request) throws ParseException{
	String message="";
	//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	try (Connection connection = DbConnector.connectToDb();
			Statement statementYearDates = connection.createStatement();
			Statement statementInsertedTerms = connection.createStatement();
			Statement statementAttendanceCheck = connection.createStatement();){
		
		int updateQuery = 0;
		String sYearDates = "select *, DATE_FORMAT(Year_Start, '%Y-%m-%d') AS YS_date, DATE_FORMAT(Year_End, '%Y-%m-%d') AS YE_date  from School_Year where School_Year_id="+school_Year_id;	
		ResultSet resultYearDates = statementYearDates.executeQuery(sYearDates);	
		Date dYear_Start=null;
		Date dYear_End=null;
		while (resultYearDates.next())
		{
			dYear_Start = formater.parse(resultYearDates.getString("YS_date"));
			dYear_End = formater.parse(resultYearDates.getString("YE_date"));		
		}
		/////////////////Before updating the Grade Terms dates we need to check if there are attendance dates will be outside the Grade Terms Dates ////////////////// 
		String sAttendanceCheck="select * from attendance where Course_id in (select Course_id from Courses where Grade_id="+grade_id+")";
		for (int i=1; i < terms_qty+1; i++)
		{
			if(request.getParameter("Term_Start"+i)!=null && !request.getParameter("Term_Start"+i).equals("") && request.getParameter("Term_End"+i)!=null && !request.getParameter("Term_End"+i).equals(""))
			sAttendanceCheck = sAttendanceCheck + " and ((Session_Date < '"+new java.sql.Date(formater.parse(request.getParameter("Term_Start"+i)).getTime())+"' and Session_Date >= '"+dYear_Start+"') or (Session_Date > '"+new java.sql.Date(formater.parse(request.getParameter("Term_End"+i)).getTime())+"' and Session_Date <= '"+dYear_End+"'))";
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
		if(terms_qty!=0)
		{
			ResultSet resultAttendanceCheck = statementAttendanceCheck.executeQuery(sAttendanceCheck);	
			if(resultAttendanceCheck.isBeforeFirst())
			{
				message = message + "There are attendance sessions with dates outside the new grade terms dates you are trying to insert/update. Term(s) dates are not allowed.<br>";
			}
			else
			{
				for (int i=1; i < terms_qty+1; i++)
				{
					
					int grade_Term_id = 0;
					if(request.getParameter("Grade_Term_id"+i)!=null)
					{
						grade_Term_id = Integer.parseInt(request.getParameter("Grade_Term_id"+i));
					}
					
					String Term_Name = request.getParameter("Term_Name"+i);
					message = message + "Term Name: "+request.getParameter("Term_Name"+i);
					String Term_Start = request.getParameter("Term_Start"+i);
					String Term_End  = request.getParameter("Term_End"+i);
					String Vacation_Start = request.getParameter("Vacation_Start"+i);
					String Vacation_End  = request.getParameter("Vacation_End"+i);
					if ((Term_Start==""))
					{
						Term_Start=null;
					}
					if ((Term_Start!=null)&&(Term_End!=null)&&(Vacation_Start!=null)&&(Vacation_End!=null))

					{
						Date dTerm_Start = formater.parse(Term_Start);
						Date dTerm_End = formater.parse(Term_End);
						Date dVacation_Start = formater.parse(Vacation_Start);
						Date dVacation_End = formater.parse(Vacation_End);
						if ((dYear_End.compareTo(dVacation_End)>=0)
								&&(dVacation_End.compareTo(dVacation_Start)>=0)
								&&(dVacation_Start.compareTo(dTerm_End)>=0)
								&&(dTerm_End.compareTo(dTerm_Start)>=0)
								&&(dTerm_Start.compareTo(dYear_Start)>=0))
						{	
							String sTerms = null;
							String sInsertedTerms;
							//sInsertedTerms = "select *, DATE_FORMAT(Term_Start, '%m/%d/%Y') AS S_date, DATE_FORMAT(Vacation_End, '%m/%d/%Y') AS VE_date  from Grade_Terms where School_Year_id="+school_Year_id+" and Grade_id="+grade_id+" and Grade_Term_id<>"+grade_Term_id;	
							sInsertedTerms = "select *, DATE_FORMAT(Term_Start, '%Y-%m-%d') AS S_date, DATE_FORMAT(Vacation_End, '%Y-%m-%d') AS VE_date  from Grade_Terms where School_Year_id="+school_Year_id+" and Grade_id="+grade_id+" and Grade_Term_id<>"+grade_Term_id;	
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
								
								if (grade_Term_id==0)
								{
									
									sTerms = "INSERT INTO Grade_Terms (Grade_Term_id, School_Year_id, Grade_id, Term_Name, Term_Start, Term_End, Vacation_Start, Vacation_End ) VALUES (Null, ?, ?, ?, ?, ?,?,?)";										
									PreparedStatement pstatement = connection.prepareStatement(sTerms);
									pstatement.setInt(1, school_Year_id);
									pstatement.setInt(2, grade_id);
									pstatement.setString(3, Term_Name);
									pstatement.setDate(4,new java.sql.Date(dTerm_Start.getTime()));
									pstatement.setDate(5,new java.sql.Date(dTerm_End.getTime()));
									pstatement.setDate(6,new java.sql.Date(dVacation_Start.getTime()));
									pstatement.setDate(7,new java.sql.Date(dVacation_End.getTime()));
									updateQuery = 0;
									updateQuery = pstatement.executeUpdate();
									if(updateQuery!=0)
									{
										message = message + " Entered Successfully  <br>";
									}
									pstatement.close();
								}
								else
								{
									sTerms = "update Grade_Terms set Term_Name=?, Term_Start=?, Term_End=?, Vacation_Start=?, Vacation_End=?, School_Year_id=? where Grade_Term_id="+grade_Term_id;
									PreparedStatement pstatement = connection.prepareStatement(sTerms);
									pstatement.setString(1, Term_Name);
									pstatement.setDate(2,new java.sql.Date(dTerm_Start.getTime()));
									pstatement.setDate(3,new java.sql.Date(dTerm_End.getTime()));
									pstatement.setDate(4,new java.sql.Date(dVacation_Start.getTime()));
									pstatement.setDate(5,new java.sql.Date(dVacation_End.getTime()));
									pstatement.setInt(6, school_Year_id);
									updateQuery = 0;
									//if(!resultAttendanceCheck.isBeforeFirst())
									//{
										updateQuery = pstatement.executeUpdate();
										if(updateQuery!=0)
										{
											message = message + " Updated Successfully  <br>";
										}	
									//}
									//else
									//{
										//message = message + "There are attendance sessions with dates outside the new grade terms dates you are trying to update. Term"+i+" dates update is not allowed.<br>";
									//}
									pstatement.close();
								}
							}
							else
							{
								
								message = message + "Term"+i+": Not Entered. Terms are overlapping. <br>";

								message = message + "Term_Allowed="+Term_Allowed;
							}
						}
						else
						{
							message = message + "Term"+i+": Term End Date must be after Term Start Date. <br> Vacation Start Date must be after Term End Date. <br> Vacation End Date must be after Vacation Start Date.<br> Term"+i+" not entered/updated <br>";
						}

					}

				}
			}
		}
       
	} catch (MySQLIntegrityConstraintViolationException ee) {
		// TODO Auto-generated catch block
		if(ee.toString().contains("Year_Grade_Term_Unique"))
		message= message+ "Duplicate Entry for Term Name for the same Grade same School Year. Grade Term not created/updated";	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
}
	
	public String createGradingScale(Grading_Scale_Details Grading_Scale_Details){
		String message=null;
		String query = "INSERT INTO Grading_Scales (Grading_Scale_id, Grade_id, low, high, letter, description) VALUES (Null,?, ?, ?, ?, ?)";
		int updateQuery = 0;
		Boolean overlap=false;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statement = connection.createStatement();){
			String sOverlap = "select low, high from Grading_Scales where Grade_id="+Grading_Scale_Details.getGrade_id();
			ResultSet resultOverlap = statement.executeQuery(sOverlap);
			int ExHigh = 0;
			int ExLow = 0;
			while(resultOverlap.next())
			{
				ExLow = resultOverlap.getInt("Low");
				ExHigh = resultOverlap.getInt("High");
				if((ExLow<=Grading_Scale_Details.getLow() && Grading_Scale_Details.getLow()<=ExHigh)
						|| (ExLow<=Grading_Scale_Details.getHigh() && Grading_Scale_Details.getHigh()<=ExHigh) 
						|| (Grading_Scale_Details.getLow()<=ExLow && Grading_Scale_Details.getHigh()>=ExHigh))
				{
					overlap=true;
					break;
				}
			}
			if(overlap==false)
			{
				pstatement.setInt(1, Grading_Scale_Details.getGrade_id());
				pstatement.setInt(2, Grading_Scale_Details.getLow());
				pstatement.setInt(3, Grading_Scale_Details.getHigh());
				pstatement.setString(4, Grading_Scale_Details.getLetter());
				pstatement.setString(5, Grading_Scale_Details.getDescription());

		        updateQuery = pstatement.executeUpdate();
		        if(updateQuery!=0)
		        {
		        	message = "Grading Scale successfully created";
		        }
		        else
		        {
		        	message = "Failed to create the grading scale";
		        }
			}
			else
			{
				message="Grade Scale you are trying to create is overlapping with another existing grading scale";
			}

		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Grade_Letter_Unique"))
				message="Duplicate Entry for the Grading Scale for same Grade. Grading Scale not created";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public String updateGradingScale(Grading_Scale_Details Grading_Scale_Details){
		String message=null;
		String query = "Update Grading_Scales set Grade_id=?, low=?, high=?, letter=?, description=? where Grading_Scale_id="+Grading_Scale_Details.getGrading_Scale_id();
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statement = connection.createStatement();){
			Boolean overlap = false;
			String sOverlap = "select low, high from Grading_Scales where Grade_id="+Grading_Scale_Details.getGrade_id()+" and Grading_Scale_id<>"+Grading_Scale_Details.getGrading_Scale_id();
			ResultSet resultOverlap = statement.executeQuery(sOverlap);
			int ExHigh = 0;
			int ExLow = 0;
			while(resultOverlap.next())
			{
				ExLow = resultOverlap.getInt("Low");
				ExHigh = resultOverlap.getInt("High");
				if((ExLow<=Grading_Scale_Details.getLow() && Grading_Scale_Details.getLow()<=ExHigh)
						|| (ExLow<=Grading_Scale_Details.getHigh() && Grading_Scale_Details.getHigh()<=ExHigh) 
						|| (Grading_Scale_Details.getLow()<=ExLow && Grading_Scale_Details.getHigh()>=ExHigh))
				{
					overlap=true;
					break;
				}
			}
			if(overlap==false)
			{
				pstatement.setInt(1, Grading_Scale_Details.getGrade_id());
				pstatement.setInt(2, Grading_Scale_Details.getLow());
				pstatement.setInt(3, Grading_Scale_Details.getHigh());
				pstatement.setString(4, Grading_Scale_Details.getLetter());
				pstatement.setString(5, Grading_Scale_Details.getDescription());
		        
				updateQuery = pstatement.executeUpdate();
		        if(updateQuery!=0)
		        {
		        	message = "Grading Scale successfully updated";
		        }
		        else
		        {
		        	message = "Failed to update the grading scale";
		        }
			}
			else
			{
				message="Grade Scale you are trying to update is overlapping with another existing grading scale";
			}

		}catch (MySQLIntegrityConstraintViolationException ee) {
				// TODO Auto-generated catch block
			if(ee.toString().contains("Grade_Letter_Unique"))
				message="Duplicate Entry for the Grading Scale for same Grade. Grading Scale not updated";
				
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public Grading_Scales getGradingScales(int grade_id){
		String query = "select * from Grading_Scales where Grade_id="+grade_id;
		Grading_Scales grading_Scales = new Grading_Scales(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			ArrayList<Integer> grading_Scale_id = new ArrayList<Integer>();
			ArrayList<String> letter = new ArrayList<String>();
			while(result.next())
			{
				grading_Scale_id.add(result.getInt("grading_scale_id"));
				letter.add(result.getString("letter"));
			}
			grading_Scales.setGrading_Scale_id(grading_Scale_id);
			grading_Scales.setLetter(letter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grading_Scales;
	}
	
	public Grading_Scale_Details getGradingScaleDetails(int grading_Scale_id){
		String query = "select * from Grading_Scales where grading_Scale_id="+grading_Scale_id;
		Grading_Scale_Details grading_Scale_Details = new Grading_Scale_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				grading_Scale_Details.setGrading_Scale_id(grading_Scale_id);
				grading_Scale_Details.setGrade_id(result.getInt("grade_id"));
				grading_Scale_Details.setLow(result.getInt("low"));
				grading_Scale_Details.setHigh(result.getInt("high"));
				grading_Scale_Details.setLetter(result.getString("letter"));
				grading_Scale_Details.setDescription(result.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grading_Scale_Details;
	}
	
	public String deleteGradingScale(int grading_Scale_id){
		String query = "Delete from Grading_Scales where Grading_Scale_id="+grading_Scale_id;
		String message=null;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				message="Grading Scale successfully deleted";
			}
			else
			{
				message = "Failed to delete the grading scale";
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public String manageExamSchedule(ArrayList<ArrayList<String>> ExamScheduleDetails, int grade_id) throws SQLException{
		String message=null;
		PreparedStatement pstatement =null;
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();

				Statement statementExamScheduleExist = connection.createStatement();
				Statement statementExamScheduleCourseExist = connection.createStatement();){
			
			
			String sExamScheduleExist = "select * from Exam_Schedule where Grade_id="+grade_id;
			ResultSet resultExamScheduleExist = statementExamScheduleExist.executeQuery(sExamScheduleExist);
			String sExamSchedule=null;
			String action="";
			if(!resultExamScheduleExist.isBeforeFirst())
			{
				///////////Insert///////////////
				sExamSchedule = "INSERT INTO Exam_Schedule (Grade_id, Exam_Name, Course_id, Exam_Date, Time_From, Time_To, Display) VALUES (?, ?, ?, ?, ?, ?, ?)";
				action = "created";
				pstatement = connection.prepareStatement(sExamSchedule);
		        connection.setAutoCommit(false);
				for(int i=0; i<ExamScheduleDetails.size(); i++)
		        {              
		            for(int j=0; j<ExamScheduleDetails.get(i).size(); j++)
		            {               	
		            	pstatement.setString(j+1, ExamScheduleDetails.get(i).get(j));
		            }
	            	updateQuery = pstatement.executeUpdate();
	            	if(updateQuery==0)
	            	{
	            		break;
	            	}
		        }
			}
			else
			{
				//////////////////Update//////////////

				action = "updated";
				CourseManagement courseManagement = new CourseManagement();
				Courses Courses = new Courses();
				Courses = courseManagement.getCourseList(grade_id);
		        connection.setAutoCommit(false);
				
		        /////////////////Delete from Exam_Schedule the Courses records that exist in Courses while do not exist in the ExamScheduleDetails array ///////////////////////////////
		        for(int courseID : Courses.getCourse_id())
		        {
		        	boolean course_Exist = false;
		        	for(int i=0; i<ExamScheduleDetails.size(); i++)
		        	 {
		        		 String Course_id = ExamScheduleDetails.get(i).get(2);
		        		 if(Course_id.equals(String.valueOf(courseID)))
		        		 {
		        			 course_Exist = true;
		        			 break;
		        		 }	   
		        	 }
	        		 if(course_Exist==false)
	        		 {
	        			 //Delete..////
	        			 String sDeleteCourse = "Delete from Exam_Schedule where course_id="+courseID+" and Grade_id="+grade_id;
	        			 PreparedStatement pstatementDeleteCourse = connection.prepareStatement(sDeleteCourse);
	        			 pstatementDeleteCourse.executeUpdate();
	        			 pstatementDeleteCourse.close();
	        		 }
		        }
		        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        for(int i=0; i<ExamScheduleDetails.size(); i++)
		        {              
					String sExamScheduleCourseExist = "select * from Exam_Schedule where Grade_id="+grade_id+" and course_id="+ExamScheduleDetails.get(i).get(2);
					ResultSet resultExamScheduleCourseExist = statementExamScheduleCourseExist.executeQuery(sExamScheduleCourseExist);
					if(resultExamScheduleCourseExist.isBeforeFirst())
					{
			        	sExamSchedule = "Update Exam_Schedule set Grade_id=?, Exam_Name=?, Course_id=?, Exam_Date=?, Time_From=?, Time_To=?, Display=? where Grade_id="+grade_id+" and Course_id="+ExamScheduleDetails.get(i).get(2);
					}
					else
					{
						sExamSchedule = "INSERT INTO Exam_Schedule (Grade_id, Exam_Name, Course_id, Exam_Date, Time_From, Time_To, Display) VALUES (?, ?, ?, ?, ?, ?, ?)";
					}

					pstatement = connection.prepareStatement(sExamSchedule);
					for(int j=0; j<ExamScheduleDetails.get(i).size(); j++)
		            {               	
		            	pstatement.setString(j+1, ExamScheduleDetails.get(i).get(j));
		            }
	            	updateQuery = pstatement.executeUpdate();
	            	if(updateQuery==0)
	            	{
	            		break;
	            	}
		        }			
			}

			if(updateQuery!=0)
			{
				connection.commit();
		        message="Exam Schedule " +action+ " successfully";
			}
			else
			{
				connection.rollback();
				message="Failed - Exam Schedule was not " +action;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			pstatement.close();
		}
		return message;
	}
	
	public ArrayList<ArrayList<String>> getExamSchedule(int grade_id, int student_id, String source) throws SQLException{
		ArrayList<ArrayList<String>> ExamScheduleDetails = new ArrayList<ArrayList<String>>();
		try (Connection connection = DbConnector.connectToDb();

				Statement statementExamSchedule = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();){
			
			String sExamSchedule=null;
			if(source.equals("Student"))
			{
			
		    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
		    	String sClassDivision = "select * from Classes where  Class_id=(select class_id from students where student_id="+student_id+")";
				ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
				int division_id = 0;
				while(resultClassDivision.next())
				{
					division_id = resultClassDivision.getInt("Division_id");	
				}
		    	////////////////////////////////////////////////////////////////////////////////////////////////////
				if (division_id==0)
		    	{	

					sExamSchedule = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End, Date_Format(Exam_Date, '%M %d, %Y') as Course_Exam_Date from Exam_Schedule where Grade_id="+grade_id+" and course_id in (select course_id from courses where Elective=0) or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+") order by Exam_Date, Time_From";

		    	}
		    	else
		    	{
		    		sExamSchedule = "select *, Time_FORMAT(Time_From, '%h:%i %p') AS Time_Start, Time_FORMAT(Time_To, '%h:%i %p') AS Time_End, Date_Format(Exam_Date, '%M %d, %Y') as Course_Exam_Date from Exam_Schedule where Grade_id="+grade_id+" and course_id in (select course_id from courses where Course_id in (select Course_id from Division_Courses where Division_id="+division_id+" and Elective=0) or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+")) order by Exam_Date, Time_From";
		    	}
			
			}
			else
			{
				sExamSchedule = "select * from Exam_Schedule where Grade_id="+grade_id+" order by Exam_Date, Time_From";
			}

			ResultSet resultExamSchedule = statementExamSchedule.executeQuery(sExamSchedule);
			int i=0;
			while(resultExamSchedule.next())
			{	
				ExamScheduleDetails.add(new ArrayList<String>());
				ExamScheduleDetails.get(i).add(0,null);
				ExamScheduleDetails.get(i).set(0, resultExamSchedule.getString("Grade_id"));
				ExamScheduleDetails.get(i).add(1,null);
				ExamScheduleDetails.get(i).set(1, resultExamSchedule.getString("Exam_Name"));
				ExamScheduleDetails.get(i).add(2,null);
				ExamScheduleDetails.get(i).set(2, resultExamSchedule.getString("Course_id"));

				if(source.equals("Student"))
				{
					ExamScheduleDetails.get(i).add(3,null);
					ExamScheduleDetails.get(i).set(3, resultExamSchedule.getString("Course_Exam_Date"));
					ExamScheduleDetails.get(i).add(4,null);
					ExamScheduleDetails.get(i).set(4, resultExamSchedule.getString("Time_Start"));
					ExamScheduleDetails.get(i).add(5,null);
					ExamScheduleDetails.get(i).set(5, resultExamSchedule.getString("Time_End"));	
				}
				else
				{
					ExamScheduleDetails.get(i).add(3,null);
					ExamScheduleDetails.get(i).set(3, resultExamSchedule.getString("Exam_Date"));
					ExamScheduleDetails.get(i).add(4,null);
					ExamScheduleDetails.get(i).set(4, resultExamSchedule.getString("Time_From"));
					ExamScheduleDetails.get(i).add(5,null);
					ExamScheduleDetails.get(i).set(5, resultExamSchedule.getString("Time_To"));	
				}

				ExamScheduleDetails.get(i).add(6,null);
				ExamScheduleDetails.get(i).set(6, resultExamSchedule.getString("Display"));
				i=i+1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ExamScheduleDetails;
	}
}
