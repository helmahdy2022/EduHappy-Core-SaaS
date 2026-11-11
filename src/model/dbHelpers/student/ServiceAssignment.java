package model.dbHelpers.student;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.beans.student.Assignments;
import model.beans.student.Category_Assignments;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.FileUploadManagement;

public class ServiceAssignment {
	public Assignments getAssignment(int student_id, String appPath, int course_id, int lesson_id, Date date_From, Date date_To){
		Assignments Assignments = new Assignments();
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();
				Statement statementAssignmentDates = connection.createStatement();
				Statement statementStudentAssignmentGrade = connection.createStatement();
				Statement statementCourseGroups = connection.createStatement();
				Statement statementStudentGroups = connection.createStatement();
				Statement statementCourseAssignments = connection.createStatement();
				Statement statementAssignmentAttempts = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();){

			AccountManagement accountManagement = new AccountManagement();
			int account_id = 0;
			int school_id = 0;
	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
	    	String sClassDivision = "select * from Classes where  Class_id=(select class_id from students where student_id="+student_id+")";
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			while(resultClassDivision.next())
			{
				division_id = resultClassDivision.getInt("Division_id");	
			}
	    	////////////////////////////////////////////////////////////////////////////////////////////////////
			String sStudentCourses=null;
			if (division_id==0)
	    	{	
		    	if(course_id==0)
		    	{
					sStudentCourses = "select * from courses where "
			    	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
		    	}
		    	else
		    	{
		    		sStudentCourses = "select * from courses where course_id="+course_id;
		    	}
	    	}
	    	else
	    	{
		    	sStudentCourses = "select * from courses where "
		    	    	+ "((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+") and Elective=0)"
		    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
	    	}
			
/*	    	String sStudentCourses = "select * from courses where "
	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
*/	    	ResultSet result = statement.executeQuery(sStudentCourses);
			
			ArrayList<ArrayList<String>> CourseAssignments = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> AssignmentFiles = new ArrayList<ArrayList<String>>();		
			HashMap<String, ArrayList<ArrayList<String>>> AssignmentAttemptsmap = new HashMap<String, ArrayList<ArrayList<String>>>();

			int j=0;
			int r=0;
			int p=0;
			while (result.next())
			{
				if(p==0)
				{
					account_id = accountManagement.getAccountID(result.getInt("Course_id"));
					school_id = accountManagement.getSchoolID(result.getInt("Course_id"));
				}
				p=p+1;
				//////////////////////Get Course Groups if it has groups ////////////////////////////////
				String sCourseGroups="select * from "+"`Groups`"+" where Course_id="+result.getString("Course_id");
				ResultSet resultCourseGroups = statementCourseGroups.executeQuery(sCourseGroups);
				String Group_id =null;
				String Group_Name=null;
				if (resultCourseGroups.isBeforeFirst())
				{
					while(resultCourseGroups.next())
					{
						String sStudentGroups="select * from Student_Groups where Group_id="+resultCourseGroups.getString("Group_id")+" and Student_id="+student_id;
						ResultSet resultStudentGroups = statementStudentGroups.executeQuery(sStudentGroups);
						if (resultStudentGroups.isBeforeFirst())
						{
							Group_id = resultCourseGroups.getString("Group_id");
							Group_Name = resultCourseGroups.getString("Group_Name");
						}
						
					}
				}
				////////////////////////////////////////////////////////////////////////////////////////////////////////////

				/////////////////////////////////Get Course Assignments ///////////////////////////////////////////////////////////
			
			    Date today = new Date();
			    String sCourseAssignments =null;
/*			    if(lesson_id==0)
		    	{
				    if(date_From==null&&date_To==null)
				    {
				    	sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and "
						    	+ " Assignment_id in (Select Assignment_id from Assignments_Classes where Display_Date<='"+new java.sql.Date(today.getTime())+"' and Disappear_Date>='"+new java.sql.Date(today.getTime())+"'"
						    			+ " and (class_id=(select class_id from students where student_id="+student_id+") and (group_id="+Group_id+" or group_id is null)))";
				    }
				    else
				    {
				    	sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and "
						    	+ " Assignment_id in (Select Assignment_id from Assignments_Classes where Due_Date>='"+new java.sql.Date(date_From.getTime())+"' and Due_Date<='"+new java.sql.Date(date_To.getTime())+"'"
						    			+ " and (class_id=(select class_id from students where student_id="+student_id+") and (group_id="+Group_id+" or group_id is null)))";
				    }
		    	}
			    else
			    {
				    sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and Lesson_id="+lesson_id;
				}*/
			    if(lesson_id==0&&date_From==null&&date_To==null)
		    	{
			    	sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and "
					    	+ " Assignment_id in (Select Assignment_id from Assignments_Classes where Display_Date<='"+new java.sql.Date(today.getTime())+"' and Disappear_Date>'"+new java.sql.Date(today.getTime())+"'"
					    			+ " and (class_id=(select class_id from students where student_id="+student_id+") and (group_id="+Group_id+" or group_id is null)))";
		    	}
			    else
			    {
				    //sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and Lesson_id="+lesson_id;
			    	if(lesson_id==0)
			    	{
				    	sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and "
						    	+ " Assignment_id in (Select Assignment_id from Assignments_Classes where Due_Date<='"+new java.sql.Date(today.getTime())+"' and Due_Date>='"+new java.sql.Date(date_From.getTime())+"' and Due_Date<='"+new java.sql.Date(date_To.getTime())+"'"
						    			+ " and (class_id=(select class_id from students where student_id="+student_id+") and (group_id="+Group_id+" or group_id is null)))";
			    	}
			    	else if(date_From!=null&&date_To!=null)
			    	{
				    	sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and Lesson_id="+lesson_id+" and "
						    	+ " Assignment_id in (Select Assignment_id from Assignments_Classes where Due_Date<='"+new java.sql.Date(today.getTime())+"' and Due_Date>='"+new java.sql.Date(date_From.getTime())+"' and Due_Date<='"+new java.sql.Date(date_To.getTime())+"'"
						    			+ " and (class_id=(select class_id from students where student_id="+student_id+") and (group_id="+Group_id+" or group_id is null)))";
			    	}
			    	else
			    	{
			    		sCourseAssignments = "select * from assignments where Course_id="+result.getString("Course_id")+" and Lesson_id="+lesson_id;
			    	}

				}			    
		    	ResultSet resultCourseAssignments = statementCourseAssignments.executeQuery(sCourseAssignments);
				if(resultCourseAssignments.isBeforeFirst())
				{
			    	while (resultCourseAssignments.next())
					{
						CourseAssignments.add(new ArrayList<String>());
						CourseAssignments.get(j).add(0,null);
						CourseAssignments.get(j).set(0, resultCourseAssignments.getString("Course_id"));
						CourseAssignments.get(j).add(1,null);
						CourseAssignments.get(j).set(1, result.getString("Course_Name"));
						CourseAssignments.get(j).add(2,null);
						CourseAssignments.get(j).set(2, Group_id);
						CourseAssignments.get(j).add(3,null);
						CourseAssignments.get(j).set(3, Group_Name);
						CourseAssignments.get(j).add(4,null);
						CourseAssignments.get(j).set(4, resultCourseAssignments.getString("Subject"));
						CourseAssignments.get(j).add(5,null);
						CourseAssignments.get(j).set(5, resultCourseAssignments.getString("Max_Grade"));
						
						//////Get Assignment Due Date //////////////////////////
						String sAssignmentDates=null;
						if (Group_id!=null)
				    	{
							sAssignmentDates = "select * from assignments_Classes where"
				    		+ " Assignment_id="+resultCourseAssignments.getString("Assignment_id")+" and Group_id="+Group_id;
				    	}
						else
						{
							if(result.getString("Elective").equals("1"))
							{
								sAssignmentDates = "select * from assignments_Classes where"
							    		+ " Assignment_id="+resultCourseAssignments.getString("Assignment_id")+" and Course_id in (Select Course_id from Student_Electives where Student_id="+student_id+")";
							}
							else
							{
								sAssignmentDates = "select * from assignments_Classes where"
							    		+ " Assignment_id="+resultCourseAssignments.getString("Assignment_id")+" and class_id=(Select Class_id from Students where Student_id="+student_id+")";
							}
						}
						
						ResultSet resultAssignmentDates = statementAssignmentDates.executeQuery(sAssignmentDates);
				    	//resultAssignmentDates.next();
						////////////////////////////////////////////////
						
						//////////////////////////////Check if the assignment graded or not //////////////////////////////////////////////////////////////////
						
						String sStudentAssignmentGrade = "select Assignment_Grade, Remarks from Student_Assignment_Grade where Student_id="+student_id+" and Assignment_id="+resultCourseAssignments.getString("Assignment_id");
						ResultSet resultStudentAssignmentGrade = statementStudentAssignmentGrade.executeQuery(sStudentAssignmentGrade);
						String isAssignmentGraded = "No";
						int assignment_Grade = -1;
						String remarks = null;
						if(resultStudentAssignmentGrade.isBeforeFirst())
						{
							isAssignmentGraded = "Yes";
							resultStudentAssignmentGrade.next();
							assignment_Grade = resultStudentAssignmentGrade.getInt("Assignment_Grade");
							remarks = resultStudentAssignmentGrade.getString("Remarks");
						}
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						while(resultAssignmentDates.next())
						{
							CourseAssignments.get(j).add(6,null);
							CourseAssignments.get(j).set(6, resultAssignmentDates.getString("Start_Date"));
							CourseAssignments.get(j).add(7,null);
							CourseAssignments.get(j).set(7, resultAssignmentDates.getString("Due_Date"));
							CourseAssignments.get(j).add(8,null);
							CourseAssignments.get(j).set(8, resultCourseAssignments.getString("Text"));
							CourseAssignments.get(j).add(9,null);
							CourseAssignments.get(j).set(9, resultCourseAssignments.getString("Assignment_id"));
							CourseAssignments.get(j).add(10,null);
							CourseAssignments.get(j).set(10, resultCourseAssignments.getString("Attempts_Allowed"));
							CourseAssignments.get(j).add(11,null);
							CourseAssignments.get(j).set(11, resultCourseAssignments.getString("Duration"));
							CourseAssignments.get(j).add(12,null);
							CourseAssignments.get(j).set(12, resultAssignmentDates.getString("Submission_Online"));
							CourseAssignments.get(j).add(13,null);
							CourseAssignments.get(j).set(13, resultAssignmentDates.getString("id"));
							CourseAssignments.get(j).add(14,null);
							CourseAssignments.get(j).set(14, isAssignmentGraded);
							CourseAssignments.get(j).add(15,null);
							CourseAssignments.get(j).set(15, String.valueOf(assignment_Grade));
							CourseAssignments.get(j).add(16,null);
							CourseAssignments.get(j).set(16, remarks);							
						}

						j=j+1;
					
						//////////////////////////Retrieve the assignments files //////////////////////////////////////
						File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Assignments/"+resultCourseAssignments.getString("Assignment_id"));
						File[] list = jsp.listFiles();
						 if (list!=null)
						 {
							 for(int m=0;m<list.length;m++)
							 {							 
								 AssignmentFiles.add(new ArrayList<String>());
								 AssignmentFiles.get(r).add(0,null);
								 AssignmentFiles.get(r).set(0, resultCourseAssignments.getString("Assignment_id"));
								 AssignmentFiles.get(r).add(1,null);
								 AssignmentFiles.get(r).set(1, list[m].getName());
								 
								 String fileExtension = list[m].getName().substring(list[m].getName().indexOf(".")+1);
								 AssignmentFiles.get(r).add(2,null);
								 AssignmentFiles.get(r).set(2, fileExtension);
								 String real_File_Name = FileUploadManagement.getRealFileName("Assignments", "Assignment_id", resultCourseAssignments.getInt("Assignment_id"), null, -1, list[m].getName());
								 AssignmentFiles.get(r).add(3,null);
								 AssignmentFiles.get(r).set(3, real_File_Name);
								 int file_id = FileUploadManagement.getFile_id("Assignments", "Assignment_id", resultCourseAssignments.getInt("Assignment_id"), null, -1, list[m].getName());
								 AssignmentFiles.get(r).add(4,null);
								 AssignmentFiles.get(r).set(4, String.valueOf(file_id));
								 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
								 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
								 //////////////////////////////////////////////////////////////////////////////////////////////////////////							 
								 AssignmentFiles.get(r).add(5,null);
								 AssignmentFiles.get(r).set(5, file_Category);
								 r=r+1;
							 }
						 }
						////////////////////////////////////////////////////////////////////////////////////////
						 
						///////////////////Get Assignment Attempts //////////////////////////////////////
						String sAssignmentAttempts = "select * from quiz_attempt where student_id="+student_id+" and Assignment_id="+resultCourseAssignments.getString("Assignment_id")+" order by attempt_id";
						ResultSet resultAssignmentAttempts = statementAssignmentAttempts.executeQuery(sAssignmentAttempts);
						ArrayList<ArrayList<String>> AssignmentAttempts = new ArrayList<ArrayList<String>>();
						int n=0;
						while (resultAssignmentAttempts.next())
						{
							AssignmentAttempts.add(new ArrayList<String>());
							AssignmentAttempts.get(n).add(0,null);
							AssignmentAttempts.get(n).set(0, resultAssignmentAttempts.getString("Attempt_id"));
							AssignmentAttempts.get(n).add(1,null);
							AssignmentAttempts.get(n).set(1, resultAssignmentAttempts.getString("Status"));
							AssignmentAttempts.get(n).add(2,null);
							AssignmentAttempts.get(n).set(2, resultAssignmentAttempts.getString("Attempt_Score"));
							n=n+1;
							AssignmentAttemptsmap.put(resultCourseAssignments.getString("Assignment_id"), AssignmentAttempts);
						}
						
					/////////////////////////////////////////////////////////////////////////////////
					
					}
				}
				else
				{
					CourseAssignments.add(new ArrayList<String>());
					CourseAssignments.get(j).add(0,null);
					CourseAssignments.get(j).set(0, result.getString("Course_id"));
					CourseAssignments.get(j).add(1,null);
					CourseAssignments.get(j).set(1, result.getString("Course_Name"));
					CourseAssignments.get(j).add(2,null);
					CourseAssignments.get(j).set(2, Group_id);
					CourseAssignments.get(j).add(3,null);
					CourseAssignments.get(j).set(3, Group_Name);
					j=j+1;
				}

				Assignments.setCourseAssignments(CourseAssignments);
				Assignments.setAssignmentFiles(AssignmentFiles);
				Assignments.setAssignmentAttemptsmap(AssignmentAttemptsmap);
				Assignments.setAccount_id(account_id);
				Assignments.setSchool_id(school_id);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Assignments;
		
	}
	
	public Category_Assignments getCatAssignment(int student_id, int category_id, int current_school_year_id){
		Category_Assignments Category_Assignments = new Category_Assignments();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementCat_Assignments = connection.createStatement();
				Statement statementCat_Name = connection.createStatement();
				Statement statementCourse_Name = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();){

	    	String sSchoolYear = "select * from School_year where school_year_id="+current_school_year_id;
	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	Date year_Start = null;
	    	Date year_End = null;
	    	//String year_Name = null;
	    	while(resultSchoolYear.next())
	    	{
		    	//school_year_id = resultSchoolYear.getInt("School_year_id");	
		    	year_Start = resultSchoolYear.getDate("Year_Start");
		    	year_End = resultSchoolYear.getDate("Year_End");
		    	//year_Name = resultSchoolYear.getString("Year_Name");
	    	}
	    	
	    	String sCat_Assignments = "SELECT assignments.subject, Student_assignment_Grade.Assignment_Grade, Student_assignment_Grade.Remarks, assignments.max_grade, assignments_classes.due_date "
	    			+ "FROM ((assignments INNER JOIN Student_assignment_Grade "
	    			+ "ON assignments.assignment_id=Student_assignment_Grade.assignment_id "
	    			+ "and Student_assignment_Grade.student_id="+student_id+" and assignments.Category_id="+category_id+") "
	    			+ "INNER JOIN Assignments_Classes "
	    			+ "ON assignments.assignment_id=assignments_classes.assignment_id "
	    			+ "and Assignments_Classes.due_date >='"+year_Start+"' and Assignments_Classes.due_date<='"+year_End+"')";
	    	ResultSet resultCat_Assignments = statementCat_Assignments.executeQuery(sCat_Assignments);
			ArrayList<ArrayList<String>> CategoryAssignmentList = new ArrayList<ArrayList<String>>();
			int i=0;
			while (resultCat_Assignments.next())
			{
				double Percentage = (double) Integer.parseInt(resultCat_Assignments.getString("Assignment_Grade"))/(double) Integer.parseInt(resultCat_Assignments.getString("Max_Grade"))*100.0;
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(0,null);
				CategoryAssignmentList.get(i).set(0, resultCat_Assignments.getString("subject"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(1,null);
				CategoryAssignmentList.get(i).set(1, resultCat_Assignments.getString("Assignment_Grade"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(2,null);
				CategoryAssignmentList.get(i).set(2, resultCat_Assignments.getString("Max_Grade"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(3,null);
				CategoryAssignmentList.get(i).set(3, String.valueOf(Math.round(Percentage)));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(4,null);
				CategoryAssignmentList.get(i).set(4, resultCat_Assignments.getString("Remarks"));
				i=i+1;			
			}
			Category_Assignments.setCategoryAssignmentList(CategoryAssignmentList);
			////////////////////////////Get Category Name and add it to the bean ///////////////////////////
			String sCat_Name = "select Category_Name from Assignment_Category where Category_id="+category_id;
			ResultSet resultCat_Name = statementCat_Name.executeQuery(sCat_Name);
			while(resultCat_Name.next())
			{
				Category_Assignments.setCategory_Name(resultCat_Name.getString("Category_Name"));
			}
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////Get Course Name and add it to the bean ///////////////////////////
			String sCourse_Name = "select Course_Name from Courses where course_id=(select course_id from Assignment_Category where Category_id="+category_id+")";
			ResultSet resultCourse_Name = statementCourse_Name.executeQuery(sCourse_Name);
			while(resultCourse_Name.next())
			{
				Category_Assignments.setCourse_Name(resultCourse_Name.getString("Course_Name"));
			}
			////////////////////////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Category_Assignments;
		
	}
	public Category_Assignments getFinalCatAssignment(int student_id, int category_id, int school_year_id){
		Category_Assignments Category_Assignments = new Category_Assignments();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementCat_Assignments = connection.createStatement();
				Statement statementCat_Name = connection.createStatement();
				Statement statementCourse_Name = connection.createStatement();){
			
	    	String sCat_Assignments = "SELECT _assignments.subject, _Student_assignment_Grade.Assignment_Grade,_assignments.max_grade "
	    			+ "FROM _assignments INNER JOIN _Student_assignment_Grade "
	    			+ "ON (_assignments.assignment_id=_Student_assignment_Grade.assignment_id "
	    			+ "and _Student_assignment_Grade.school_year_id="+school_year_id+" and _assignments.school_year_id="+school_year_id+" and _Student_assignment_Grade.student_id="+student_id+" and _assignments.Category_id="+category_id+")";
	    	ResultSet resultCat_Assignments = statementCat_Assignments.executeQuery(sCat_Assignments);
			ArrayList<ArrayList<String>> CategoryAssignmentList = new ArrayList<ArrayList<String>>();
			int i=0;
			while (resultCat_Assignments.next())
			{
				double Percentage = (double) Integer.parseInt(resultCat_Assignments.getString("Assignment_Grade"))/(double) Integer.parseInt(resultCat_Assignments.getString("Max_Grade"))*100.0;
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(0,null);
				CategoryAssignmentList.get(i).set(0, resultCat_Assignments.getString("subject"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(1,null);
				CategoryAssignmentList.get(i).set(1, resultCat_Assignments.getString("Assignment_Grade"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(2,null);
				CategoryAssignmentList.get(i).set(2, resultCat_Assignments.getString("Max_Grade"));
				CategoryAssignmentList.add(new ArrayList<String>());
				CategoryAssignmentList.get(i).add(3,null);
				CategoryAssignmentList.get(i).set(3, String.valueOf(Math.round(Percentage)));
				i=i+1;			
			}
			Category_Assignments.setCategoryAssignmentList(CategoryAssignmentList);
			////////////////////////////Get Category Name and add it to the bean ///////////////////////////
			String sCat_Name = "select Category_Name from _Assignment_Category where school_year_id="+school_year_id+" and Category_id="+category_id;
			ResultSet resultCat_Name = statementCat_Name.executeQuery(sCat_Name);
			while(resultCat_Name.next())
			{
				Category_Assignments.setCategory_Name(resultCat_Name.getString("Category_Name"));
			}
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////Get Course Name and add it to the bean ///////////////////////////
			String sCourse_Name = "select Course_Name from _Courses where school_year_id="+school_year_id+" and course_id=(select course_id from _Assignment_Category where school_year_id="+school_year_id+" and Category_id="+category_id+")";
			ResultSet resultCourse_Name = statementCourse_Name.executeQuery(sCourse_Name);
			while(resultCourse_Name.next())
			{
				Category_Assignments.setCourse_Name(resultCourse_Name.getString("Course_Name"));
			}
			////////////////////////////////////////////////////////////////////////////////////////////////
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Category_Assignments;
		
	}
	public int createAttempt(int student_id, int assignment_id, int assignment_Duration){
		int updateQuery = 0;
		int attempt_id = -1;
		String sAttempt = "INSERT INTO Quiz_Attempt (Attempt_id, Student_id, Assignment_id, Status, Attempt_Score, Duration) VALUES (Null, ?, ?, ?, ?, ?)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sAttempt, Statement.RETURN_GENERATED_KEYS)){
			
			pstatement.setInt(1, student_id);
			pstatement.setInt(2, assignment_id);
			pstatement.setString(3, "In Progress");
			pstatement.setNull(4, java.sql.Types.NULL);
			if(assignment_Duration==-1)
			{
				pstatement.setNull(5, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(5, assignment_Duration);
			}			
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				ResultSet resultAttempt_id = pstatement.getGeneratedKeys();
				if(resultAttempt_id.next()) 
				{
					attempt_id = resultAttempt_id.getInt(1);
				    resultAttempt_id.close();
				}
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attempt_id;		
	}
	
	public int updateAttempt(int attempt_id, int attempt_Score, int attempt_Duration){
		int updateQuery = 0;
		String sAttempt = "update Quiz_Attempt set Status=?, Attempt_Score=?, Duration=? where attempt_id="+attempt_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sAttempt)){
			
			pstatement.setString(1, "Completed");
			pstatement.setInt(2, attempt_Score);
			if(attempt_Duration==-1)
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(3, attempt_Duration);
			}
			
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;		
	}
	
	public int getAttemptInProgress(int student_id, int assignment_id){
		int attempt_id=-1;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAttemptInProgress = connection.createStatement();){

			String sAttemptInProgress = "select * from Quiz_Attempt where Student_id="+student_id+" and Assignment_id="+assignment_id+" and Status='In Progress'";
			ResultSet resultAttemptInProgress = statementAttemptInProgress.executeQuery(sAttemptInProgress);

			while(resultAttemptInProgress.next())
			{
				attempt_id = resultAttemptInProgress.getInt("Attempt_id");
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attempt_id;
	}
	
	public int getAttemptDuration(int attempt_id){
		int attempt_Duration=-1;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAttemptDuration = connection.createStatement();){

			String sAttemptDuration = "select duration from Quiz_Attempt where attempt_id="+attempt_id;
			ResultSet resultAttemptDuration = statementAttemptDuration.executeQuery(sAttemptDuration);

			while(resultAttemptDuration.next())
			{
				if(resultAttemptDuration.getString("Duration")!=null)
				attempt_Duration = resultAttemptDuration.getInt("Duration");
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attempt_Duration;
	}
	
	public float getAttemptScore(int attempt_id){
		int attempt_Score=-1;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAttemptScore = connection.createStatement();){

			String sAttemptScore = "select Attempt_Score from Quiz_Attempt where attempt_id="+attempt_id;
			ResultSet resultAttemptScore = statementAttemptScore.executeQuery(sAttemptScore);

			while(resultAttemptScore.next())
			{
				attempt_Score = resultAttemptScore.getInt("Attempt_Score");
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attempt_Score;
	}
	
	public void updateAttemptDuration(int attempt_id, int attempt_Duration, int student_Score){
		String sAttemptDuration = "update Quiz_Attempt set Duration=?, Attempt_Score=? where attempt_id="+attempt_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sAttemptDuration)){

			if(attempt_Duration==-1)
			{
				pstatement.setNull(1, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(1, attempt_Duration);
			}
			
			if(student_Score/100==-1)
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setInt(2, student_Score);
			}
			
			pstatement.executeUpdate();
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int createAssignmentSubmission(int student_id, int give_Assignment_id, String submission_Notes){
		int updateQuery = 0;
		String sStudentSubmission = "INSERT INTO Student_Assignment_Submission (Student_id, Give_Assignment_id, Submission_Notes) VALUES (?, ?, ?)";
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sStudentSubmission)){
		
			pstatement.setInt(1, student_id);
			pstatement.setInt(2, give_Assignment_id);	
			pstatement.setString(3, submission_Notes);
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;		
	}	

	public boolean checkSubmission(int student_id, int give_Assignment_id){
		boolean isSubmissionExist=false;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementCheckSubmission = connection.createStatement();){

			String sCheckSubmission = "select * from student_assignment_submission where Student_id="+student_id+" and Give_Assignment_id="+give_Assignment_id;
			ResultSet resultCheckSubmission = statementCheckSubmission.executeQuery(sCheckSubmission);

			if (resultCheckSubmission.isBeforeFirst())
			{
				isSubmissionExist = true;
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSubmissionExist;
	}

	public int updateAssignmentSubmission(int student_id, int give_Assignment_id, String submission_Notes){
		int updateQuery = 0;
		String sStudentSubmission = "update Student_Assignment_Submission set Submission_Notes=? where Student_id="+student_id+" and Give_Assignment_id="+give_Assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(sStudentSubmission)){

			if(submission_Notes==null)
			{
				pstatement.setNull(1, java.sql.Types.NULL);
			}
			else
			{
				pstatement.setString(1, submission_Notes);
			}
			
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;		
	}
	
	public String getSubmissionNotes(int student_id, int give_assignment_id){
		String submissionNotes=null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSubmissionNotes = connection.createStatement();){

			String sSubmissionNotes = "select Submission_Notes from student_assignment_submission where Student_id="+student_id+" and Give_Assignment_id="+give_assignment_id;
			ResultSet resultSubmissionNotes = statementSubmissionNotes.executeQuery(sSubmissionNotes);

			while(resultSubmissionNotes.next())
			{
				submissionNotes = resultSubmissionNotes.getString("Submission_Notes");
			}
			/////////////////////////////////////////////////////////////////////////////////////////
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return submissionNotes;
	}
}
