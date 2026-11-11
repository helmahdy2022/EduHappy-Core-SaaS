package model.dbHelpers.admin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import model.beans.admin.School_Years;
import model.beans.admin.Students;
import model.beans.student.Student_Grading;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.student.ServiceReportCard;

public class YearEndProcedures {
	public String produceFinalGrading(int school_id){
		int updateQuery = 0;
		String message=null;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchoolStudents = connection.createStatement();
			Statement statementsStudentGrade = connection.createStatement();
			Statement statementStudentHistory = connection.createStatement();){
			String sSchoolStudents = "select * from Students where Class_id in (Select Class_id from Classes where Grade_id in(Select Grade_id from Grades where School_id="+school_id+") and class_id is not null)";
			ResultSet resultSchoolStudents = statementSchoolStudents.executeQuery(sSchoolStudents);
			ServiceReportCard serviceReportCard = new ServiceReportCard();
			Student_Grading Student_Grading = new Student_Grading();
			if (school_id==102)
			{
				message="This option is not available for the Demo.";
			}
			else
			{
				connection.setAutoCommit(false);
				
				while(resultSchoolStudents.next())
				{
					Student_Grading = serviceReportCard.getReportCard(resultSchoolStudents.getInt("Student_id"),0);
					StudentManagement studentManagement= new StudentManagement(); 
					int class_id = Integer.parseInt(studentManagement.getStudentClass(resultSchoolStudents.getInt("Student_id")));
					//update student_grade_yeare table with the final grade ///////////////////////
					String sStudentGrade = "select Grade_id from Grades where Grade_id=(select Grade_id from Classes where Class_id="+class_id+")";
					ResultSet resultsStudentGrade = statementsStudentGrade.executeQuery(sStudentGrade);
					int grade_id=0;
					while(resultsStudentGrade.next())
					{
						grade_id = resultsStudentGrade.getInt("Grade_id");
					}
					String sStudentHistory = "select * from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and School_year_id="+Student_Grading.getSchool_year_id();
					ResultSet resultStudentHistory = statementStudentHistory.executeQuery(sStudentHistory);
					if(!resultStudentHistory.isBeforeFirst())
					{
						String sStudentFirstRecord = "Insert into Student_Grade_Year (Student_id, School_id, Grade_id, School_Year_id, Class_id, Final_Grading) VALUES (?, ?, ?, ?,?,?)";
						PreparedStatement pstatement = connection.prepareStatement(sStudentFirstRecord);
						pstatement.setInt(1, resultSchoolStudents.getInt("Student_id"));			
						pstatement.setInt(2, school_id);
						pstatement.setInt(3, grade_id);
						pstatement.setInt(4, Student_Grading.getSchool_year_id());
						pstatement.setInt(5, class_id);
						pstatement.setDouble(6, Student_Grading.getOverall_Grade());
						if(Student_Grading.getSchool_year_id()!=0)
						{
							updateQuery = pstatement.executeUpdate();
							//String sDeleteOthers = "Delete from Student_Grade_Year where student_id="+resultSchoolStudents.getInt("Student_id")+" and School_id<>"+school_id+" and Final_Grading is null and graduated <> 1 order by school_year_id desc limit 1";
							//pstatement.executeUpdate(sDeleteOthers);						
						}
						pstatement.close();
						if(updateQuery==0) break;
					}
					else
					{
						String sStudentFinalGrading = "update Student_Grade_Year set Final_Grading=? where Student_id="+resultSchoolStudents.getInt("Student_id")+" and Grade_id="+grade_id+" and School_year_id="+Student_Grading.getSchool_year_id();
						PreparedStatement pstatement = connection.prepareStatement(sStudentFinalGrading);
						pstatement.setDouble(1, Student_Grading.getOverall_Grade());
						updateQuery = pstatement.executeUpdate();
						pstatement.close();
						if(updateQuery==0) break;
					}
					
				}
				if(updateQuery!=0)
				{
					connection.commit();
					message="Final Grading Produced Successfully";
				}
				else
				{
					connection.rollback();
					message= "Failed to produce Final Grading";
				}				
			}

			////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public boolean checkFinalGradingReport(int school_id){
		boolean reportProduced = false;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementcheckReport = connection.createStatement();){			
			
			///////////////////////////Get School Year Id/////////////////////////////////
			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	int school_year_id = 0;
			while(resultSchoolYear.next()) 
	    	{    		
	    		school_year_id = resultSchoolYear.getInt("School_Year_id");	   
	        } 	       
			/////////////////////////////////////////////////////////////////////////////
	    	String scheckReport = "select * from student_grade_year where school_year_id="+school_year_id+" and final_grading is null";
			ResultSet resultcheckReport = statementcheckReport.executeQuery(scheckReport);
			if(!resultcheckReport.isBeforeFirst())
			{
				reportProduced=true;
			}
			////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportProduced;
	}
	public Students produceFinalGradingReport(int school_id, int grade_id, int previous_school_year_id){
		Students Students = new Students();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementGradeStudents = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementFinalGrading = connection.createStatement();
				Statement statementPassingGrade = connection.createStatement();
				Statement statementPromoted = connection.createStatement();
				Statement statementGraduated = connection.createStatement();){			
			int school_year_id = 0;
			String sSchoolYear = null;
			if(previous_school_year_id==0)
			{
				///////////////////////////Get School Year Id/////////////////////////////////
				Date today = new Date();
		    	sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
		    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";       
				/////////////////////////////////////////////////////////////////////////////
			}
			else
			{			
		    	sSchoolYear = "select * from School_year where school_year_id="+previous_school_year_id; 
			}
			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);	    	
	    	String year_Name = null;
			while(resultSchoolYear.next()) 
	    	{    		
	    		school_year_id = resultSchoolYear.getInt("School_Year_id");	   
	    		year_Name = resultSchoolYear.getString("Year_Name");
	        }
			/////////////////////////////Get Grade Passing Score/////////////////////////
	    	String sPassingGrade = "select Passing_Grade from Grades where Grade_id="+grade_id;
			ResultSet resultPassingGrade = statementPassingGrade.executeQuery(sPassingGrade);
			int passing_Grade = 0;
			while(resultPassingGrade.next())
			{
				passing_Grade = resultPassingGrade.getInt("Passing_Grade");
			}
			////////////////////////////////////////////////////////////////////////////
			//String sGradeStudents = "select * from Students where Class_id in (select class_id from classes where grade_id="+grade_id+")";
			String sGradeStudents = "select * from Students where Student_id in (select student_id from student_grade_year where grade_id="+grade_id+" and school_year_id="+school_year_id+")";
			ResultSet resultGradeStudents = statementGradeStudents.executeQuery(sGradeStudents);
			ArrayList<Integer> Student_id = new ArrayList<Integer>();
			ArrayList<String> First_Name = new ArrayList<String>();
			ArrayList<String> Last_Name = new ArrayList<String>();
			ArrayList<String> Username = new ArrayList<String>();
			ArrayList<Integer> Class_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
			ArrayList<String> FileName = new ArrayList<String>();
			ArrayList<Integer> Final_Grading = new ArrayList<Integer>();
			ArrayList<String> Passing_Status = new ArrayList<String>();
			while (resultGradeStudents.next())
			{
			
				Student_id.add(resultGradeStudents.getInt("Student_id"));
				
				First_Name.add(resultGradeStudents.getString("First_Name"));
			
				Last_Name.add(resultGradeStudents.getString("Last_Name"));
				
				Username.add(resultGradeStudents.getString("Username"));
				
				Class_id.add(resultGradeStudents.getInt("Class_id"));
				
	    		School_id.add(school_id);
	    		AccountManagement accountManagement = new AccountManagement();
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
	    		
				///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
				FileName.add(resultGradeStudents.getString("Fake_File_Name"));
				//////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////Get Student Final Grading///////////////////////////////////////////////////
				String sFinalGrading = "select Final_Grading, Graduated from student_grade_year where school_id="+school_id+" and school_year_id="+school_year_id+" and grade_id="+grade_id+" and student_id="+resultGradeStudents.getInt("Student_id");
				ResultSet resultFinalGrading = statementFinalGrading.executeQuery(sFinalGrading);
				while(resultFinalGrading.next())
				{
					Final_Grading.add(resultFinalGrading.getInt("Final_Grading"));
					if(resultFinalGrading.getInt("Final_Grading")>=passing_Grade)
					{
						String sGraduated = "select Graduated from student_grade_year where school_id="+school_id+" and grade_id="+grade_id+" and student_id="+resultGradeStudents.getInt("Student_id")+" and Graduated=1";
						ResultSet resultGraduated = statementGraduated.executeQuery(sGraduated);
						if(resultGraduated.isBeforeFirst())
						{
							Passing_Status.add("Graduated");
						}
						else
						{
							Passing_Status.add("Passed");
						}
						
					}
					else
					{
						Passing_Status.add("Failed");
					}
				}
				//////////////////////////////////////////////////////////////////////////////////////////////
			}
			/////////////////////Check if  the promotion done already for this school year////////////////////////////////////
			String sPromoted = "select * from _school_year where school_year_id="+school_year_id;
			ResultSet resultPromoted = statementPromoted.executeQuery(sPromoted);
			boolean promoted = false;
			if(resultPromoted.isBeforeFirst())
			{
				promoted = true;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////
			Students.setStudent_id(Student_id);
			Students.setFirst_Name(First_Name);
			Students.setLast_Name(Last_Name);
			Students.setUsername(Username);
			Students.setClass_id(Class_id);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
			Students.setFileName(FileName);
			Students.setFinal_Grading(Final_Grading);
			Students.setYear_Name(year_Name);
			Students.setPassing_Grade(passing_Grade);	
			Students.setPassing_Status(Passing_Status);
			Students.setPromoted(promoted);
			////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	public String promoteStudents(int school_id, String appPath){
		int updateQuery = 0;
		String message="";
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSchoolStudents = connection.createStatement();
			Statement statementSchoolYear = connection.createStatement();
				Statement statementNextSchoolYear = connection.createStatement();
				Statement statementNo_Of_Grades = connection.createStatement();
				Statement statementsStudentPosition = connection.createStatement();
				Statement statementsNextGrade = connection.createStatement();				
				Statement statementsStudentGrade = connection.createStatement();				
				Statement statementsStudentAlreadyPromoted = connection.createStatement();
				Statement statementAssignmentSubmission = connection.createStatement();){
			
			////////////////////Get Current School Year ////////////////////////////////////////////////
			Date today = new Date();
	    	//String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' and School_id="+school_id;
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	int school_year_id = 0;
	    	int next_School_Year_id = 0;
	    	java.sql.Date year_Start = null;
	    	java.sql.Date year_End = null;
	    	while (resultSchoolYear.next())
	    	{
	    		school_year_id = resultSchoolYear.getInt("School_Year_id");
	    		year_Start = resultSchoolYear.getDate("Year_Start");
	    		year_End = resultSchoolYear.getDate("Year_End");
				///////////////////Get Next School Year id //////////////////////////////////////
		    	String sNextSchoolYear = "select * from School_year where Year_Start>'"+resultSchoolYear.getDate("Year_Start")+"' and School_id="+school_id+" order by year_start asc LIMIT 1";

		    	ResultSet resultNextSchoolYear = statementNextSchoolYear.executeQuery(sNextSchoolYear);
		    	
		    	while (resultNextSchoolYear.next())
		    	{
		    		next_School_Year_id = resultNextSchoolYear.getInt("School_Year_id");
		    	}
	    	}
	    	String sNo_Of_Grades = "Select count(*) from grades where school_id="+school_id;
	    	ResultSet resultNo_Of_Grades = statementNo_Of_Grades.executeQuery(sNo_Of_Grades);
	    	int no_Of_Grades = 0;
	    	while(resultNo_Of_Grades.next())
	    	{
	    		no_Of_Grades = resultNo_Of_Grades.getInt(1);
	    	}    	
			/////////////////////////////////////////////////////////////////////////////////////////////
	    	
			String sSchoolStudents = "select * from Students where Class_id in (Select Class_id from Classes where Grade_id in(Select Grade_id from Grades where School_id="+school_id+"))";
			ResultSet resultSchoolStudents = statementSchoolStudents.executeQuery(sSchoolStudents);
			if(!resultSchoolStudents.isBeforeFirst())
			{
				sSchoolStudents = "select * from student_grade_year where school_id="+school_id+" and school_year_id="+school_year_id+" and Final_Grading is not null and class_id is not null";
				resultSchoolStudents = statementSchoolStudents.executeQuery(sSchoolStudents);
			}

	    	if(next_School_Year_id!=0 || no_Of_Grades == 1)
	    	{
		    	connection.setAutoCommit(false);
				while(resultSchoolStudents.next())
				{
					String sStudentGrade = "select * from Grades where Grade_id=(select Grade_id from Classes where Class_id="+resultSchoolStudents.getInt("Class_id")+")";
					ResultSet resultsStudentGrade = statementsStudentGrade.executeQuery(sStudentGrade);
					int grade_id = 0;
					int grade_Index = 0;
					int passing_Grade = 0;
					while(resultsStudentGrade.next())
					{
						grade_id = resultsStudentGrade.getInt("Grade_id");
						grade_Index = resultsStudentGrade.getInt("Grade_Index");
						passing_Grade = resultsStudentGrade.getInt("Passing_Grade");
					}

					///////////////////Get Next Grade ////////////////////////////
					String sNextGrade = "select * from Grades where Grade_Index >"+grade_Index+" and school_id="+school_id;
					ResultSet resultsNextGrade = statementsNextGrade.executeQuery(sNextGrade);
					int next_Grade_id=0;
					boolean hasNextGrade = false;
					while(resultsNextGrade.next())
					{
						next_Grade_id = resultsNextGrade.getInt("Grade_id");
						hasNextGrade = true;
						break;
					}
					/////////////////////////////////////////////////////////////
					String sStudentPosition = "select * from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and Grade_id="+grade_id+" and School_Year_id="+school_year_id+" and Final_Grading is not null";
					ResultSet resultsStudentPosition = statementsStudentPosition.executeQuery(sStudentPosition);
					int final_Grading = -1;
					while (resultsStudentPosition.next())
					{
						final_Grading = resultsStudentPosition.getInt("Final_Grading");
					}
					/////////////////Check if the student is qualified for the next Grade /////////////////////
					Boolean student_Qualified = false;
					if(final_Grading!=-1 && final_Grading >= passing_Grade)
					{
						student_Qualified = true;
					}
					//////////////////////////////////////////////////////////////////////////////////////////

					/////////////////Insert new record in the Student_Grade_Year with the new school year and  the appropriate Grade ////////////////					
					if(final_Grading!=-1)
					{
						String sStudentAlreadyPromoted = null;
						if(student_Qualified)
						{
							sStudentAlreadyPromoted = "select * from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and school_id="+school_id+" and Grade_id="+next_Grade_id+" and School_Year_id="+next_School_Year_id+" and Final_Grading is null";
						}
						else
						{
							sStudentAlreadyPromoted = "select * from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and school_id="+school_id+" and Grade_id="+grade_id+" and School_Year_id="+next_School_Year_id+" and Final_Grading is null";
						}
						ResultSet resultsStudentAlreadyPromoted = statementsStudentAlreadyPromoted.executeQuery(sStudentAlreadyPromoted);
						String sDeleteStudentPromotion = null;
						if(resultsStudentAlreadyPromoted.isBeforeFirst())
						{
							 
							if(student_Qualified)
							{
								sDeleteStudentPromotion="delete from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and school_id="+school_id+" and Grade_id="+next_Grade_id+" and School_Year_id="+next_School_Year_id+" and Final_Grading is null";								
							}
							else
							{
								sDeleteStudentPromotion="delete from Student_Grade_Year where Student_id="+resultSchoolStudents.getInt("Student_id")+" and school_id="+school_id+" and Grade_id="+grade_id+" and School_Year_id="+next_School_Year_id+" and Final_Grading is null";

							}
							PreparedStatement pstatementDeleteStudentPromotion = connection.prepareStatement(sDeleteStudentPromotion);
							updateQuery = pstatementDeleteStudentPromotion.executeUpdate();
							pstatementDeleteStudentPromotion.close();
						}
						String sStudentPromotion = "INSERT INTO Student_Grade_Year (Student_id, Grade_id, School_Year_id, Graduated, School_id) VALUES (?, ?, ?, ?,?)";
						PreparedStatement pstatementStudentPromotion = connection.prepareStatement(sStudentPromotion);
						pstatementStudentPromotion.setInt(1, resultSchoolStudents.getInt("Student_id"));
						if(student_Qualified==true)
						{
							if(hasNextGrade)
							{
								pstatementStudentPromotion.setInt(2, next_Grade_id);
								pstatementStudentPromotion.setInt(3, next_School_Year_id);
								pstatementStudentPromotion.setInt(4, 0);
							}
							else
							{
								pstatementStudentPromotion.setInt(2, grade_id);
								pstatementStudentPromotion.setInt(3, school_year_id);
								pstatementStudentPromotion.setInt(4, 1);
							}
						}
						else
						{
							pstatementStudentPromotion.setInt(2, grade_id);
							pstatementStudentPromotion.setInt(3, next_School_Year_id);
							pstatementStudentPromotion.setInt(4, 0);
						}
						
						//pstatementStudentPromotion.setInt(3, next_School_Year_id);
						pstatementStudentPromotion.setInt(5, school_id);
						updateQuery = pstatementStudentPromotion.executeUpdate();
						pstatementStudentPromotion.close();
					}
					else
					{
						message="Please produce the finale grading first before trying to promote the students.<br>";
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				}
				/////////////////////Copy the History ////////////////////////////////////////////////////////////////////////
				/////////////////////First Delete existing historical data related to this school year before inserting the new data////////////////////
				String sResetHistoricalData = "Delete from _school_year where school_year_id="+school_year_id;
				PreparedStatement pstatementResetHistoricalData = connection.prepareStatement(sResetHistoricalData);
				pstatementResetHistoricalData.executeUpdate();
				pstatementResetHistoricalData.close();
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////1-Insert into _school_year/////////////////////////////////////////////////////////////////////////
				String sSchool_Year = "INSERT INTO _school_year SELECT * FROM school_year where school_year_id="+school_year_id;
				PreparedStatement pstatementSchool_Year = connection.prepareStatement(sSchool_Year);
				pstatementSchool_Year.executeUpdate();
				pstatementSchool_Year.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////2-Insert into _year_semesters///////////////////////////////////////////////////////////////////
				String sYear_Semesters = "INSERT INTO _year_semesters SELECT * FROM year_semesters where school_year_id="+school_year_id;
				PreparedStatement pstatementYear_Semesters = connection.prepareStatement(sYear_Semesters);
				pstatementYear_Semesters.executeUpdate();
				pstatementYear_Semesters.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////3-Insert into _year_terms///////////////////////////////////////////////////////////////////////
				/////////////////////Manually Delete existing historical data since there is a bug in MySql////////////////////
				String sResetYTermsData = "Delete from _year_terms where school_year_id="+school_year_id;
				PreparedStatement pstatementResetYTermsData = connection.prepareStatement(sResetYTermsData);
				pstatementResetYTermsData.executeUpdate();
				pstatementResetYTermsData.close();
				String sYear_Terms = "INSERT INTO _year_terms SELECT * FROM year_terms where school_year_id="+school_year_id;
				PreparedStatement pstatementYear_Terms = connection.prepareStatement(sYear_Terms);
				pstatementYear_Terms.executeUpdate();
				pstatementYear_Terms.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////4-Insert into _school///////////////////////////////////////////////////////////////////////
				String sSchool = "INSERT INTO _school SELECT *, "+school_year_id+" FROM school where school_id="+school_id;
				PreparedStatement pstatementSchool = connection.prepareStatement(sSchool);
				pstatementSchool.executeUpdate();
				pstatementSchool.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////5-Insert into _grades///////////////////////////////////////////////////////////////////////
				String sGrades = "INSERT INTO _grades SELECT *, "+school_year_id+" FROM grades where school_id="+school_id;
				PreparedStatement pstatementGrades = connection.prepareStatement(sGrades);
				pstatementGrades.executeUpdate();
				pstatementGrades.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////6-Insert into _grade_semesters///////////////////////////////////////////////////////////////////////
				String sGrade_Semesters = "INSERT INTO _grade_semesters SELECT * FROM grade_semesters where School_Year_id="+school_year_id+" and grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementGrade_Semesters = connection.prepareStatement(sGrade_Semesters);
				pstatementGrade_Semesters.executeUpdate();
				pstatementGrade_Semesters.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////7-Insert into _grade_terms///////////////////////////////////////////////////////////////////////
				/////////////////////Manually Delete existing historical data since there is a bug in MySql////////////////////
				String sResetGTermsData = "Delete from _grade_terms where school_year_id="+school_year_id;
				PreparedStatement pstatementResetGTermsData = connection.prepareStatement(sResetGTermsData);
				pstatementResetGTermsData.executeUpdate();
				pstatementResetGTermsData.close();
				String sGrade_Terms = "INSERT INTO _grade_terms SELECT * FROM grade_terms where School_Year_id="+school_year_id+" and grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementGrade_Terms = connection.prepareStatement(sGrade_Terms);
				pstatementGrade_Terms.executeUpdate();
				pstatementGrade_Terms.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////8-Insert into _grade_session///////////////////////////////////////////////////////////////////////
				String sGrade_Sessions = "INSERT INTO _grade_sessions SELECT *, "+school_year_id+" FROM grade_sessions where grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementGrade_Sessions = connection.prepareStatement(sGrade_Sessions);
				pstatementGrade_Sessions.executeUpdate();
				pstatementGrade_Sessions.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////9-Insert into _grading_scales///////////////////////////////////////////////////////////////////////
				String sGraing_Scales = "INSERT INTO _grading_scales SELECT *, "+school_year_id+" FROM grading_scales where grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementGraing_Scales = connection.prepareStatement(sGraing_Scales);
				pstatementGraing_Scales.executeUpdate();
				pstatementGraing_Scales.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////10-Insert into _classes///////////////////////////////////////////////////////////////////////
				String sClasses = "INSERT INTO _classes SELECT *, "+school_year_id+" FROM classes where grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementClasses = connection.prepareStatement(sClasses);
				pstatementClasses.executeUpdate();
				pstatementClasses.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////11-Insert into _divisions///////////////////////////////////////////////////////////////////////
				String sDivisions = "INSERT INTO _divisions SELECT *, "+school_year_id+" FROM divisions where grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementDivisions = connection.prepareStatement(sDivisions);
				pstatementDivisions.executeUpdate();
				pstatementDivisions.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////12-Insert into _courses///////////////////////////////////////////////////////////////////////
				String sCourses = "INSERT INTO _courses SELECT *, "+school_year_id+" FROM courses where grade_id in (select grade_id from grades where school_id="+school_id+")";
				PreparedStatement pstatementCourses = connection.prepareStatement(sCourses);
				pstatementCourses.executeUpdate();
				pstatementCourses.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////13-Insert into _course_semesterexam///////////////////////////////////////////////////////////////////////
				String sCourse_Semesterexam = "INSERT INTO _course_semesterexam SELECT *, "+school_year_id+" FROM course_semesterexam where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementCourse_Semesterexam = connection.prepareStatement(sCourse_Semesterexam);
				pstatementCourse_Semesterexam.executeUpdate();
				pstatementCourse_Semesterexam.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////14-Insert into _course_units///////////////////////////////////////////////////////////////////////
				String sCourse_units = "INSERT INTO _course_units SELECT *, "+school_year_id+" FROM course_units where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementCourse_units = connection.prepareStatement(sCourse_units);
				pstatementCourse_units.executeUpdate();
				pstatementCourse_units.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////15-Insert into _division_courses///////////////////////////////////////////////////////////////////////
				String sDivision_Courses = "INSERT INTO _division_courses SELECT *, "+school_year_id+" FROM division_courses where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementDivision_Courses = connection.prepareStatement(sDivision_Courses);
				pstatementDivision_Courses.executeUpdate();
				pstatementDivision_Courses.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////16-Insert into _attendance///////////////////////////////////////////////////////////////////////
				String sAttendance = "INSERT INTO _attendance SELECT *, "+school_year_id+" FROM attendance where Session_Date>='"+new java.sql.Date(year_Start.getTime())+"' and Session_Date<='"+new java.sql.Date(year_End.getTime())+"' and course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementAttendance = connection.prepareStatement(sAttendance);
				pstatementAttendance.executeUpdate();
				pstatementAttendance.close();
				//////////////Need to delete transaction Data after copy////////////////
				String sResetAttendance = "Delete from attendance where Session_Date>='"+new java.sql.Date(year_Start.getTime())+"' and Session_Date<='"+new java.sql.Date(year_End.getTime())+"' and course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementResetAttendance = connection.prepareStatement(sResetAttendance);
				pstatementResetAttendance.executeUpdate();
				pstatementResetAttendance.close();
				///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////17-Insert into _groups///////////////////////////////////////////////////////////////////////
				String sGroups = "INSERT INTO _groups SELECT *, "+school_year_id+" from "+"`Groups`"+" where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementGroups = connection.prepareStatement(sGroups);
				pstatementGroups.executeUpdate();
				pstatementGroups.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////18-Insert into _lessons///////////////////////////////////////////////////////////////////////
				String sLessons = "INSERT INTO _lessons SELECT *, "+school_year_id+" FROM lessons where unit_id in (select unit_id from course_units where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+")))";
				PreparedStatement pstatementLessons = connection.prepareStatement(sLessons);
				pstatementLessons.executeUpdate();
				pstatementLessons.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////19-Insert into _assignment_category///////////////////////////////////////////////////////////////////////
				String sAssignment_Category = "INSERT INTO _assignment_category SELECT *, "+school_year_id+" FROM assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementAssignment_Category = connection.prepareStatement(sAssignment_Category);
				pstatementAssignment_Category.executeUpdate();
				pstatementAssignment_Category.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////20-Insert into _assignments///////////////////////////////////////////////////////////////////////
				String sAssignments = "INSERT INTO _assignments SELECT *, "+school_year_id+" FROM assignments where category_id in (select category_id from assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+")))";
				PreparedStatement pstatementAssignments = connection.prepareStatement(sAssignments);
				pstatementAssignments.executeUpdate();
				pstatementAssignments.close();
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////21-Insert into _assignments_classes///////////////////////////////////////////////////////////////////////
				String sAssignments_Classes = "INSERT INTO _assignments_classes SELECT *, "+school_year_id+" FROM assignments_classes where Due_Date>='"+new java.sql.Date(year_Start.getTime())+"' and Due_Date<='"+new java.sql.Date(year_End.getTime())+"' and assignment_id in (select assignment_id from assignments where category_id in (select category_id from assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))))";
				PreparedStatement pstatementAssignments_Classes = connection.prepareStatement(sAssignments_Classes);
				pstatementAssignments_Classes.executeUpdate();
				pstatementAssignments_Classes.close();
				//////////////Need to delete transaction Data after copy////////////////
				String sResetAssignments_classes = "Delete from assignments_classes where Due_Date>='"+new java.sql.Date(year_Start.getTime())+"' and Due_Date<='"+new java.sql.Date(year_End.getTime())+"' and assignment_id in (select assignment_id from assignments where category_id in (select category_id from assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))))";
				PreparedStatement pstatementResetAssignments_classes = connection.prepareStatement(sResetAssignments_classes);
				pstatementResetAssignments_classes.executeUpdate();
				pstatementResetAssignments_classes.close();
				///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////22-Insert into _student_assignment_grade///////////////////////////////////////////////////////////////////////
				String sStudent_Assignment_Grade = "INSERT INTO _student_assignment_grade SELECT *, "+school_year_id+" FROM student_assignment_grade where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and assignment_id in (select assignment_id from assignments where category_id in (select category_id from assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))))";
				PreparedStatement pstatementStudent_Assignment_Grade = connection.prepareStatement(sStudent_Assignment_Grade);
				pstatementStudent_Assignment_Grade.executeUpdate();
				pstatementStudent_Assignment_Grade.close();
				//////////////Need to delete transaction Data after copy////////////////
				String sResetStudent_Assignment_Grade = "Delete from student_assignment_grade where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and assignment_id in (select assignment_id from assignments where category_id in (select category_id from assignment_category where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))))";
				PreparedStatement pstatementResetStudent_Assignment_Grade = connection.prepareStatement(sResetStudent_Assignment_Grade);
				pstatementResetStudent_Assignment_Grade.executeUpdate();
				pstatementResetStudent_Assignment_Grade.close();
				///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////23-Insert into _student_electives///////////////////////////////////////////////////////////////////////
				String sStudent_Electives = "INSERT INTO _student_electives SELECT *, "+school_year_id+" FROM student_electives where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementStudent_Electives = connection.prepareStatement(sStudent_Electives);
				pstatementStudent_Electives.executeUpdate();
				pstatementStudent_Electives.close();
				//////////////Need to delete transaction Data after copy////////////////
				String sResetStudentElectives = "Delete from Student_Electives where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+"))";
				PreparedStatement pstatementResetStudentElectives = connection.prepareStatement(sResetStudentElectives);
				pstatementResetStudentElectives.executeUpdate();
				pstatementResetStudentElectives.close();
				///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////24-Insert into _student_semesterexam///////////////////////////////////////////////////////////////////////
				String sStudent_SemesterExam = "INSERT INTO _student_semesterexam SELECT *, "+school_year_id+" FROM student_semesterexam where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and exam_id in (select exam_id from course_semesterexam where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+")))";
				PreparedStatement pstatementStudent_SemesterExam = connection.prepareStatement(sStudent_SemesterExam);
				pstatementStudent_SemesterExam.executeUpdate();
				pstatementStudent_SemesterExam.close();
				//////////////Need to delete transaction Data after copy////////////////
				String sResetStudent_SemesterExam = "Delete from student_semesterexam where student_id in (select student_id from students where class_id in(select class_id from classes where grade_id in (select grade_id from grades where school_id="+school_id+"))) and exam_id in (select exam_id from course_semesterexam where course_id in (select course_id from courses where grade_id in (select grade_id from grades where school_id="+school_id+")))";
				PreparedStatement pstatementResetStudent_SemesterExam = connection.prepareStatement(sResetStudent_SemesterExam);
				pstatementResetStudent_SemesterExam.executeUpdate();
				pstatementResetStudent_SemesterExam.close();
				///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////Set Students Class to NULL////////////////////////////////////////////////////////////////////////////////////////////////
				if(updateQuery!=0)
				{
					resultSchoolStudents.beforeFirst();
					while(resultSchoolStudents.next())
					{
						////////////////////Set Class_id NULL in Students table /////////////////////////////////////////////////
						String sResetStudentClass = "update Students set Class_id=? where Student_id="+resultSchoolStudents.getInt("Student_id");
						PreparedStatement pstatementResetStudentClass = connection.prepareStatement(sResetStudentClass);
						pstatementResetStudentClass.setNull(1,java.sql.Types.NULL);
						updateQuery = pstatementResetStudentClass.executeUpdate();
						pstatementResetStudentClass.close();				
						/////////////////Reset Student Groups///////////////////////////////////////////////////////////////////////////////////////////////////
						String sResetStudentGroups = "Delete from Student_Groups where Student_id="+resultSchoolStudents.getInt("Student_id");
						PreparedStatement pstatementResetStudentGroups = connection.prepareStatement(sResetStudentGroups);
						pstatementResetStudentGroups.executeUpdate();
						pstatementResetStudentGroups.close();
						if(updateQuery==0)
						{
							break;
						}
					
					}
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(updateQuery!=0)
				{
					connection.commit();
					//////////////////////Delete physical folders related to student assignment submissions///////////////////////////////////////////////////////////////
					String sAssignmentSubmission = "select * from _assignments_classes where school_year_id="+school_year_id;
					ResultSet resultsAssignmentSubmission = statementAssignmentSubmission.executeQuery(sAssignmentSubmission);
					while(resultsAssignmentSubmission.next())
					{
						/////Delete the folders////////////////
						
						File jsp = null;
						AccountManagement accountManagement = new AccountManagement();
						int account_id = accountManagement.getSchoolAccount(school_id);
						jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/AssignmentSubmissions/"+resultsAssignmentSubmission.getInt("Id"));
						FileUtils.deleteQuietly(jsp); 
						//////////////////////////////////////
						
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					message="Students Promotion Successful";
				}
				else
				{
					connection.rollback();
					message= message+ "Students Promotion Failed";
				}
	    	}
	    	else
	    	{
	    		message="There is no new school year to promote the students to. Please create new school year";
	    	}

			////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public Students getGradeStudents(int grade_id){
		Students Students = new Students();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementGradeStudents = connection.createStatement();
				Statement statementStudentSchool = connection.createStatement();){
			String sGradeStudents = "select * from Students where Class_id is NULL and Student_id in (Select Student_id from Student_Grade_Year where Grade_id="+grade_id+" and Class_id is NULL and Graduated = 0)";
			ResultSet resultSchoolStudents = statementGradeStudents.executeQuery(sGradeStudents);
			ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> first_Name = new ArrayList<String>();
			ArrayList<String> last_Name = new ArrayList<String>();
			ArrayList<String> username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
			
			while(resultSchoolStudents.next())
			{
				student_id.add(resultSchoolStudents.getInt("Student_id"));
				first_Name.add(resultSchoolStudents.getString("First_Name"));
				last_Name.add(resultSchoolStudents.getString("Last_Name"));
				username.add(resultSchoolStudents.getString("Username"));
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
				String sStudentSchool = "Select school_id from Student_Grade_Year where Grade_id="+grade_id+" and student_id="+resultSchoolStudents.getInt("Student_id")+" and Class_id is NULL and Graduated = 0";
				ResultSet resultStudentSchool = statementStudentSchool.executeQuery(sStudentSchool );
				int school_id=0;
				while(resultStudentSchool.next())
				{
					school_id = resultStudentSchool.getInt("School_id");
				}
	    		School_id.add(school_id);	    		
	    		/////////////////////////////////////////////////////////////////////////////
	    		AccountManagement accountManagement = new AccountManagement();
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(resultSchoolStudents.getString("Fake_File_Name"));
			}
			Students.setStudent_id(student_id);
			Students.setFirst_Name(first_Name);
			Students.setLast_Name(last_Name);
			Students.setUsername(username);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	
	public int updateStudentClass(int student_id, int class_id){
		int updateQuery=0;
		try (Connection connection = DbConnector.connectToDb();){
			String sUpdateStudentClass = "update Students set Class_id=? where Student_id="+student_id;
			PreparedStatement pstatementUpdateStudentClass = connection.prepareStatement(sUpdateStudentClass);
			pstatementUpdateStudentClass.setInt(1,class_id);
			updateQuery = pstatementUpdateStudentClass.executeUpdate();
			pstatementUpdateStudentClass.close();
			
			String sUpdateStudentGradeYearClass = "update Student_Grade_Year set Class_id=? where Student_id="+student_id+" and Class_id is Null";
			PreparedStatement pstatementUpdateStudentGradeYearClass = connection.prepareStatement(sUpdateStudentGradeYearClass);
			pstatementUpdateStudentGradeYearClass.setInt(1,class_id);
			updateQuery = pstatementUpdateStudentGradeYearClass.executeUpdate();
			pstatementUpdateStudentGradeYearClass.close();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public School_Years getPreviousYears(int school_id, int current_school_year_id){
		School_Years School_Years = new School_Years();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementPreviousYears = connection.createStatement();){

			ArrayList<Integer> Previous_Years_id = new ArrayList<Integer>();
			ArrayList<String> Previous_Years_Name = new ArrayList<String>();
			String sPreviousYears = "select * from school_year where school_year_id <> "+current_school_year_id+" and school_year_id in(select school_year_id from student_grade_year where school_id="+school_id+" and final_grading is not null)";
			ResultSet resultPreviousYears = statementPreviousYears.executeQuery(sPreviousYears);
			while(resultPreviousYears.next())
			{
				Previous_Years_id.add(resultPreviousYears.getInt("School_year_id"));
				Previous_Years_Name.add(resultPreviousYears.getString("Year_Name"));
			}
			School_Years.setSchool_Year_id(Previous_Years_id);
			School_Years.setYear_Name(Previous_Years_Name);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return School_Years;
	}
}
