package model.dbHelpers.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;




import model.beans.student.Student_Grading;
import model.dbHelpers.DbConnector;

public class ServiceReportCard {
	public Student_Grading getReportCard(int student_id, int course_id) {
		
		Student_Grading Student_Grading = new Student_Grading();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGradeID = connection.createStatement();
				Statement statementSutdentGrade = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementGradeTerms = connection.createStatement();
				Statement statementStudentCourses = connection.createStatement();
				Statement statementCategories = connection.createStatement();
				Statement statementGradeSum = connection.createStatement();
				Statement statementMaxGradeSum = connection.createStatement();
				Statement statementSumSessionGrades = connection.createStatement();
				Statement statementSessionGrade = connection.createStatement();
				Statement statementGradingScale = connection.createStatement();
				Statement statementSemesters = connection.createStatement();
				Statement statementYearSemesters = connection.createStatement();
				Statement statementSemesterExam = connection.createStatement();
				Statement statementStudentSemesterExam = connection.createStatement();
				Statement statementSemesterExamGradingScale = connection.createStatement();
				Statement statementSemesterGradingScale = connection.createStatement();
				Statement statementFinalCourseGradingScale = connection.createStatement();
				Statement statementCatGradeSum = connection.createStatement();
				Statement statementCatMaxGradeSum = connection.createStatement();				
				Statement statementClassDivision = connection.createStatement();
				Statement statementPreviousYears = connection.createStatement();){
			
			String sGradeID = "select Grade_id from Classes where Class_id=(select class_id from Students where Student_id="+student_id+")";;
			ResultSet resultGradeID = statementGradeID.executeQuery(sGradeID);
			int grade_id=0;
			while(resultGradeID.next())
			{
				grade_id =resultGradeID.getInt("Grade_id");
			}
			if(grade_id==0)
			{
				/////////////////////////////////////Get the student school ////////////////////////////////////
				String sSutdentGrade = "select Grade_id from student_grade_year where final_grading is null and student_id="+student_id;
				ResultSet resultSutdentGrade = statementSutdentGrade.executeQuery(sSutdentGrade);
				while(resultSutdentGrade.next())
				{
					grade_id = resultSutdentGrade.getInt("Grade_id");
				}
				//////////////////////////////////////////////////////////////////////////////////////////////////
			}
			Date today = new Date();
	    	String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
	    			+ "and School_id=(select school_id from Grades where "
	    			+ "Grade_id="+grade_id+") ORDER BY Year_Start DESC LIMIT 1";
	    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
	    	int school_year_id=0;
	    	Date year_Start = null;
	    	Date year_End = null;
	    	String year_Name = null;
	    	while(resultSchoolYear.next())
	    	{
		    	school_year_id = resultSchoolYear.getInt("School_year_id");	
		    	year_Start = resultSchoolYear.getDate("Year_Start");
		    	year_End = resultSchoolYear.getDate("Year_End");
		    	year_Name = resultSchoolYear.getString("Year_Name");
	    	}
	    	Date term_Start = null;
	    	Date term_End = null;
	    	String term_Name = null;
	    	Boolean termsExist = true;
	    	String sGradeTerms = "select * from Grade_Terms where School_year_id="+school_year_id+" and Grade_id="+grade_id+" order by Term_Start";
	    	ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);

	    	LinkedHashMap<String, ArrayList<ArrayList<String>>> Term_Grading_Map = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
	    	ArrayList<ArrayList<String>> Term_Grading = new ArrayList<ArrayList<String>>();
			////////////////////////Get Semesters ///////////////////
    		String sSemesters=null;
    		sSemesters="select * from Grade_Semesters where school_year_id="+school_year_id+" and Grade_id="+grade_id;
    		ResultSet resultSemesters = statementSemesters.executeQuery(sSemesters);

    		if(!resultGradeTerms.isBeforeFirst())
    		{
	    		sGradeTerms="select * from year_terms where school_year_id="+school_year_id+" order by Term_Start";
	    		resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
	    		if(!resultGradeTerms.isBeforeFirst())
	    		{
	        		sGradeTerms="select * from school_year where school_year_id="+school_year_id;
		    		resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
		    		termsExist = false;
		    		sSemesters="select * from school_year where school_year_id="+school_year_id;
    	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
	    		}
	    		else
	    		{
	    			String sYearSemesters = "select * from Year_Semesters where School_year_id="+school_year_id;
	    			ResultSet resultYearSemesters = statementYearSemesters.executeQuery(sYearSemesters);
	    			if(resultYearSemesters.isBeforeFirst())
	    			{
	    				resultSemesters = resultYearSemesters;
	    			}
	    			else
	    			{
    	   	    		sSemesters="select * from school_year where school_year_id="+school_year_id;
        	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
	    			}
	    		}
    		}
    		if(resultGradeTerms.isBeforeFirst()&&!resultSemesters.isBeforeFirst())
    		{
   	    		sSemesters="select * from school_year where school_year_id="+school_year_id;
	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
    		}
	    	////////////////////////////////////////////////////////
    		int semester_id=0;

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
			if(course_id==0)
			{
				if (division_id==0)
		    	{	
			    	sStudentCourses = "select * from courses where Report_Display=1 and "
			    	    	+ "((grade_id ="+grade_id+" and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id="+grade_id+")))";
		    	}
		    	else
		    	{
			    	sStudentCourses = "select * from courses where Report_Display=1 and "
			    	    	+ "((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+") and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id="+grade_id+")))";
		    	}
			}
			else
			{
				sStudentCourses = "select * from courses where course_id="+course_id;
			}

			
/*	    	String sStudentCourses = "select * from courses where "
	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
*/

	    ResultSet resultStudentCourses = statementStudentCourses.executeQuery(sStudentCourses);
	    	    	
	    LinkedHashMap<String, ArrayList<ArrayList<String>>> CourseCatmap = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
	    ArrayList<ArrayList<String>> CategoryList = null;
	    int Overall_Grade=0;
		double sumCourseGrading=0.0;
		double sumCourseContribution=0.0;
		while (resultStudentCourses.next()) 
	    {
			double Final_Course_Contribution = 0.0;
			double Final_SumCategory_Grade =	0.0;
			double Final_Course_Grade = -1.0;
	
	    	resultSemesters.beforeFirst();
	    	resultGradeTerms.beforeFirst();
    	    int i=0;
    	    Term_Grading=new ArrayList<ArrayList<String>>();
    		double Total_Attendance_Grade = -1.0;  
    		double Total_SumStudentGrades = 0.0;
			double Total_SumSessionGrades = 0.0;
    	    while(resultSemesters.next())
	    	{
				double Sem_SumCategory_Grade = 0.0;
				double Sem_Grade_Contribution=0.0;
				int semesterTermsCount=0;
	    		
				resultGradeTerms.beforeFirst();
	    	    while(resultGradeTerms.next())
	    		{
	    			if(termsExist==true)
	    			{
	    				semester_id = resultGradeTerms.getInt("Semester_id");
	    			}
	    			if(semester_id==resultSemesters.getInt(1))
	    			{
	    				semesterTermsCount = semesterTermsCount+1;
	    			}	
	    		}
	    		resultGradeTerms.beforeFirst();
	    		int termCounter=0;

	    		while(resultGradeTerms.next())
	    		{    			
	    			if(termsExist==true)
	    			{
	    				semester_id = resultGradeTerms.getInt("Semester_id");
	    			}
	    			
	    			if(semester_id==resultSemesters.getInt(1) ||semester_id ==0 )
	    			{
	    				termCounter++;
	    				if(termsExist==true)
	    				{
	    					term_Start = resultGradeTerms.getDate("Term_Start");
	    					term_End = resultGradeTerms.getDate("Term_End");
	    					term_Name = resultGradeTerms.getString("Term_Name");
	    				}
	    				else
	    				{
	    					term_Start = resultGradeTerms.getDate("Year_Start");
	    					term_End = resultGradeTerms.getDate("Year_End");
	    					term_Name = resultGradeTerms.getString("Year_Name");
	    				}
	    	    	////////////////////Get Grading per Term///////////////////////////////////////////////////////////////////////////
	    		    			
	    		    			double Course_Contribution=0.0;
	    		    			double Total_Course_Grades=0.0; 		    			
	    		    				
	    		    	/////////////////////Get Course Grade //////////////////////////////////////////////////
	    		    		    	String sCategories = "select * from assignment_category where Course_id="+resultStudentCourses.getString("Course_id");
	    		    		    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);
	    		    				double Term_SumCategory_Grade = 0.0;
	    		    				double Term_Grade_Contribution=0.0;
	    		    				double Category_Grade=0.0;
	    		    				
	    		    				while (resultCategories.next())
	    		    				{
	    		    					
	    		    					String sGradeSum="select sum(Assignment_Grade), count(Assignment_Grade) from Student_Assignment_Grade where student_id="+student_id+" "
	    		    							+ "and Assignment_id in (select assignment_id from assignments where category_id="+resultCategories.getString("Category_id")+")"
	    		    							+ " and Assignment_id in (select assignment_id from assignments_classes where Due_Date>='"+term_Start+"' and Due_Date<='"+term_End+"')";
	    		    					ResultSet resultGradeSum = statementGradeSum.executeQuery(sGradeSum);

	    		    					Category_Grade = resultCategories.getInt("Category_Grade");

	    		    					String sMaxGradeSum = "select sum(max_grade), count(max_grade) from assignments where category_id="+resultCategories.getString("Category_id")+" and assignment_id in (select assignment_id from Student_Assignment_Grade where student_id="+student_id+""
	    		    							+ " and Assignment_id in (select assignment_id from assignments_classes where Due_Date>='"+term_Start+"' and Due_Date<='"+term_End+"'))";
	    		    					ResultSet resultMaxGradeSum = statementMaxGradeSum.executeQuery(sMaxGradeSum);
	    		    					double GradeSum=0.0;
	    		    					double MaxGradeSum=0.0;
	    		    					int countAssignmentGrade = 0;
	    		    					
	    		    					while (resultGradeSum.next())
	    		    					{
	    		    						GradeSum = resultGradeSum.getInt(1);
	    		    						countAssignmentGrade = resultGradeSum.getInt(2);
	    		    					}
	    		    							
	    		    					while (resultMaxGradeSum.next())
	    		    					{	
	    		    						MaxGradeSum = resultMaxGradeSum.getInt(1);
	    		    					}
	    		    					double percentage=0.0;	

	    		    					if(MaxGradeSum!=0)
	    		    					{
	    		    						percentage = ((double)GradeSum / (double) MaxGradeSum)*100;
	   
	    		    					}
	    		    					
	    		    					
	    		    					if (Category_Grade!=0)
	    		    					{
	    		    						if(countAssignmentGrade!=0)
	    		    						{
		    		    						Term_Grade_Contribution= Term_Grade_Contribution+ Category_Grade*percentage/100.0;
		    		    						Term_SumCategory_Grade = Term_SumCategory_Grade + Category_Grade;
	    		    						}

	    		    					}
	    		    				}

	    		    				double Term_Course_Grade =-1.0;
	    		    				
	    		    				if (Term_SumCategory_Grade!=0)
	    		    				{
	    		    					Term_Course_Grade = Term_Grade_Contribution/Term_SumCategory_Grade*100.0;
	    		    				}			
	    		    	///////////////////////////////////////////////////////////////////////////////////////		
	    		    				
	    		    				
	    		    				///////////////////////Calculate Attendance Grade ///////////////////////////////////////////
	    		    				int SumStudentGrades = 0;
	    		    				int CountSessions = 0;
	    		    				int Session_Grade = 0;
	    		    				String sSumSessionGrades = "select sum(Session_Grade), count(Session_Grade) from attendance where Course_id="+resultStudentCourses.getString("Course_id")+""
	    		    		    			+ " and Student_id="+student_id+" and Session_Date>='"+term_Start+"' and Session_Date<='"+term_End+"'";
	    		    		    	ResultSet resultSumSessionGrades = statementSumSessionGrades.executeQuery(sSumSessionGrades);
	    		    				
	    		    				while (resultSumSessionGrades.next())
	    		    				{
	    		    					SumStudentGrades = resultSumSessionGrades.getInt(1);
    		    						CountSessions = resultSumSessionGrades.getInt(2);	
	    		    				}

	    		    		    	String sSessionGrade = "select Attendance_Grade, Attendance_Weight from Courses where Course_id="+resultStudentCourses.getString("Course_id");
	    		    		    	ResultSet resultSessionGrade = statementSessionGrade.executeQuery(sSessionGrade);
	    		    				double Attendance_Weight=0.0;
	    		    				while (resultSessionGrade.next())
	    		    				{
	    		    					if(resultSessionGrade.getString(1)!=null)
	    		    					{
	    		    						Session_Grade = Integer.parseInt(resultSessionGrade.getString(1));
	    		    						Attendance_Weight = Integer.parseInt(resultSessionGrade.getString(2));
	    		    					}
	    		    				}
	    		    				double Attendance_Grade=0.0;
	    		    				if (Session_Grade!=0 && CountSessions!=0)
	    		    				{
	    		    					Attendance_Grade = ((double)SumStudentGrades / (double)(Session_Grade*CountSessions))*100.0;

		    		    			}
    		    					Total_SumStudentGrades = Total_SumStudentGrades + SumStudentGrades;
	    		    				Total_SumSessionGrades = Total_SumSessionGrades + (Session_Grade*CountSessions);
	    		    				////////////////////////////////////////////////////////////////////////////////////////////
	    		    				double Attendance_Contribution=0.0;
	    		    				Attendance_Contribution = (Attendance_Weight*Attendance_Grade)/100.0;
	    		    				Term_Grade_Contribution = Term_Grade_Contribution + Attendance_Contribution;
	    		    				if(CountSessions!=0)
	    		    				{
	    		    					Term_SumCategory_Grade = Term_SumCategory_Grade + Attendance_Weight;
	    		    				}
	    		    				
	    		    				if (Term_SumCategory_Grade!=0)
	    		    				{
	    		    					Term_Course_Grade = Term_Grade_Contribution/Term_SumCategory_Grade*100.0;
	    		    				}
	    		    				//////////////Get the equivalent Letter ////////////////////
	    		    		    	String sGradingScale = "select Letter from Grading_Scales where grade_id="+grade_id+" and low<="+Math.round(Term_Course_Grade)+" and high>="+Math.round(Term_Course_Grade);
	    		    		    	ResultSet resultGradingScale = statementGradingScale.executeQuery(sGradingScale);
	    		    		    	String term_letter=null;
	    		    		    	while(resultGradingScale.next())
	    		    		    	{
	    		    		    		term_letter = resultGradingScale.getString("Letter");
	    		    		    	}
	    		    			////////////////////////////////////////////////////////////////////////////////////////////
	    		    			
	    		    		   ///////////////////////////Get Semester Exam Grading /////////////////////////////////
	    		    		    	String sSemesterExam = null;
	    		    		    	ResultSet resultSemesterExam = null;
	    		    		    	if(termsExist==true)
	    		    		    	{
		    		    		    	sSemesterExam = "select * from Course_SemesterExam where Course_id="+resultStudentCourses.getString("Course_id")+" and Grade_Semester_id="+semester_id;
		    		    		    	resultSemesterExam = statementSemesterExam.executeQuery(sSemesterExam);
		    		    		    	if(!resultSemesterExam.isBeforeFirst())
		    		    		    	{
		    		    		    		sSemesterExam = "select * from Course_SemesterExam where Course_id="+resultStudentCourses.getString("Course_id")+" and Year_Semester_id="+semester_id;
			    		    		    	resultSemesterExam = statementSemesterExam.executeQuery(sSemesterExam);
		    		    		    	}
	    		    		    	}
	    		    		    	double semster_Exam_Grade=-1.0;
	    		    		    	int exam_Grade=0;
	    		    		    	int exam_Weight=0;
	    		    		    	int student_Semester_Exam_Grade=-1;
	    		    		    	Boolean semester_Exam_Graded = false;
	    		    		    	if(resultSemesterExam!=null)
	    		    		    	{
		    		    		    	while(resultSemesterExam.next())
		    		    		    	{
			    		    		    	String sStudentSemesterExam = "select * from Student_SemesterExam where Exam_id="+resultSemesterExam.getInt("Exam_id")+" and Student_id="+student_id;
			    		    		    	ResultSet resultStudentSemesterExam = statementStudentSemesterExam.executeQuery(sStudentSemesterExam);
			    		    		    	exam_Grade = resultSemesterExam.getInt("Exam_Grade");
			    		    		    	exam_Weight = resultSemesterExam.getInt("Exam_Contribution");
			    		    		    	
			    		    		    	while(resultStudentSemesterExam.next())
			    		    		    	{
			    		    		    		student_Semester_Exam_Grade = resultStudentSemesterExam.getInt("Student_Grade");
			    		    		    		semester_Exam_Graded=true;
			    		    		    	}
			    		    		    	
			    		    		    	if(student_Semester_Exam_Grade!=-1)
			    		    		    	{
				    		    		    	semster_Exam_Grade = ((double) student_Semester_Exam_Grade / (double) exam_Grade)*100 ;
			    		    		    	}
		    		    		    	}
	    		    		    	}
	    		    		    	String sSemesterExamGradingScale = "select Letter from Grading_Scales where grade_id="+grade_id+" and low<="+Math.round(semster_Exam_Grade)+" and high>="+Math.round(semster_Exam_Grade);
	    		    		    	ResultSet resultSemesterExamGradingScale = statementSemesterExamGradingScale.executeQuery(sSemesterExamGradingScale);
	    		    		    	String letterSemesterExam=null;
	    		    		    	while(resultSemesterExamGradingScale.next())
	    		    		    	{
	    		    		    		letterSemesterExam = resultSemesterExamGradingScale.getString("Letter");
	    		    		    	}
	    		    		   ////////////////////////////////////////////////////////////////////////////////////////
	    		    		  ////////////////////////////////////Calculate Semester Grading ////////////////////////
	    		    				
	    		    				Sem_Grade_Contribution = Sem_Grade_Contribution + Term_Grade_Contribution;
	    		    				Sem_SumCategory_Grade = Sem_SumCategory_Grade + Term_SumCategory_Grade;
	    		    		    	//////////////////////////////////////////////////////////////////////////////
	    		    		   /////////////////////////////Calculate Overall Grade///////////////////////////////

	    		    				
	    		    				Course_Contribution =Course_Contribution + Term_Course_Grade*Integer.parseInt(resultStudentCourses.getString("Course_Grade"))/100.0;
	    		    				Total_Course_Grades = Total_Course_Grades + Integer.parseInt(resultStudentCourses.getString("Course_Grade"));
	    		    				
	    		    				
	    		    			//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    		    				Term_Grading.add(new ArrayList<String>());
	    		    				Term_Grading.get(i).add(0,null);
	    		    				Term_Grading.get(i).set(0, resultGradeTerms.getString(1));
	    		    				Term_Grading.get(i).add(1,null);
	    		    				Term_Grading.get(i).set(1, term_Name);
	    		    				
	    		    				Term_Grading.get(i).add(2,null);
	    		    				Term_Grading.get(i).set(2, resultStudentCourses.getString("Course_id"));
	    		    				Term_Grading.get(i).add(3,null);
	    		    				Term_Grading.get(i).set(3, resultStudentCourses.getString("Course_Name"));
	    		
	    		    				Term_Grading.get(i).add(4,null);
	    		    				if(Term_Course_Grade!=-1)
	    		    					Term_Grading.get(i).set(4, String.valueOf(Math.round(Term_Course_Grade)));
	    		    				else
	    		    					Term_Grading.get(i).set(4, null);
	    		    				Term_Grading.get(i).add(5,null);
	    		    				Term_Grading.get(i).set(5, term_letter);
	    		    				
	    		    				double Semester_Grade=-1.0;
	    		    				String letterSemester=null;
	    		    				if(semesterTermsCount==termCounter)
	    		    				{
	    		    					if(semester_Exam_Graded)
	    		    					{
		    		    					//Sem_Grade_Contribution = Sem_Grade_Contribution + student_Semester_Exam_Grade;
		    		    					//Sem_SumCategory_Grade = Sem_SumCategory_Grade+ exam_Grade;
		    		    					Sem_Grade_Contribution = Sem_Grade_Contribution + exam_Weight*semster_Exam_Grade/100;
		    		    					Sem_SumCategory_Grade = Sem_SumCategory_Grade+ exam_Weight;
		    		    					Semester_Grade = (Sem_Grade_Contribution / Sem_SumCategory_Grade)*100.0;
		    		    					
			    		    		    	String sSemesterGradingScale = "select Letter from Grading_Scales where grade_id="+grade_id+" and low<="+Math.round(Semester_Grade)+" and high>="+Math.round(Semester_Grade);
			    		    		    	ResultSet resultSemesterGradingScale = statementSemesterGradingScale.executeQuery(sSemesterGradingScale);
			    		    		    	
			    		    		    	while(resultSemesterGradingScale.next())
			    		    		    	{
			    		    		    		letterSemester = resultSemesterGradingScale.getString("Letter");
			    		    		    	}
	    		    					}
	    		    					
	    		    					Term_Grading.get(i).add(6,null);
		    		    				Term_Grading.get(i).set(6, String.valueOf(semester_id));
		    		    				Term_Grading.get(i).add(7,null);
		    		    				Term_Grading.get(i).set(7, resultSemesters.getString("Semester_Name")+" Exam Grade");
		    		    				Term_Grading.get(i).add(8,null);
		    		    				if(semster_Exam_Grade!=-1)
		    		    				{
		    		    					Term_Grading.get(i).set(8, String.valueOf(Math.round(semster_Exam_Grade))+"%");
		    		    				}
		    		    				else
		    		    				{
		    		    					Term_Grading.get(i).set(8, "-");
		    		    				}
		    		    				
		    		    				Term_Grading.get(i).add(9,null);
		    		    				Term_Grading.get(i).set(9, letterSemesterExam);
		    		    				Term_Grading.get(i).add(10,null);
		    		    				Term_Grading.get(i).set(10, resultSemesters.getString("Semester_Name"));
		    		    				Term_Grading.get(i).add(11,null);
		    		    				if(Semester_Grade!=-1)
		    		    				{
			    		    				Term_Grading.get(i).set(11, String.valueOf(Math.round(Semester_Grade))+"%");
		    		    				}
		    		    				else
		    		    				{
			    		    				Term_Grading.get(i).set(11, "-");
		    		    				}
		    		    				Term_Grading.get(i).add(12,null);
		    		    				Term_Grading.get(i).set(12, letterSemester);
	    		    				}
	    		    				else
	    		    				{
		    		    				Term_Grading.get(i).add(6,null);
		    		    				Term_Grading.get(i).set(6, null);
		    		    				Term_Grading.get(i).add(7,null);
		    		    				Term_Grading.get(i).set(7, null);
		    		    				Term_Grading.get(i).add(8,null);
		    		    				Term_Grading.get(i).set(8, null);
		    		    				Term_Grading.get(i).add(9,null);
		    		    				Term_Grading.get(i).set(9, null);
		    		    				Term_Grading.get(i).add(10,null);
		    		    				Term_Grading.get(i).set(10, null);
		    		    				Term_Grading.get(i).add(11,null);
		    		    				Term_Grading.get(i).set(11, null);
		    		    				Term_Grading.get(i).add(12,null);
		    		    				Term_Grading.get(i).set(12, null);
	    		    				}
	    		    				Final_Course_Contribution = Final_Course_Contribution + Sem_Grade_Contribution;
	    		    				Final_SumCategory_Grade =	Final_SumCategory_Grade + Sem_SumCategory_Grade;
	    		    				if(Final_SumCategory_Grade!=0)
	    		    				{
	    		    					Final_Course_Grade = Final_Course_Contribution/Final_SumCategory_Grade*100;
	    		    				}
	    		    				
	    		    				
	    		    		    	String sFinalCourseGradingScale = "select Letter from Grading_Scales where grade_id="+grade_id+" and low<="+Math.round(Final_Course_Grade)+" and high>="+Math.round(Final_Course_Grade);
	    		    		    	ResultSet resultFinalCourseGradingScale = statementFinalCourseGradingScale.executeQuery(sFinalCourseGradingScale);
	    		    		    	String letterFinalCourse = null;
	    		    		    	while(resultFinalCourseGradingScale.next())
	    		    		    	{
	    		    		    		letterFinalCourse = resultFinalCourseGradingScale.getString("Letter");
	    		    		    	}
	    		    		    	if(resultSemesters.isLast()&&resultGradeTerms.isLast() )
	    		    		    	{
		    		    				Term_Grading.get(i).add(13,null);
		    		    				if(Final_Course_Grade!=-1)
		    		    				{
		    		    					Term_Grading.get(i).set(13, String.valueOf(Math.round(Final_Course_Grade)+"%"));
			    		    				/////////////////////////////Get overall Grade here /////////////////////////////
			    		    				if(resultStudentCourses.getInt("Contribute")==1)
			    		    				{
			    		    					sumCourseGrading = sumCourseGrading + (resultStudentCourses.getInt("Course_Grade")*Final_Course_Grade)/100;
					    		    			sumCourseContribution = sumCourseContribution + resultStudentCourses.getInt("Course_Grade");
			    		    				}
			    		    				//////////////////////////////////////////////////////////////////////////////////
		    		    				}
		    		    				else
		    		    				{
		    		    					Term_Grading.get(i).set(13, "-");
		    		    				}
		    		    				
		    		    				Term_Grading.get(i).add(14,null);
		    		    				Term_Grading.get(i).set(14, letterFinalCourse);
		    		    				

	    		    		    	}
	    		    		    	else
	    		    		    	{
		    		    				Term_Grading.get(i).add(13,null);
		    		    				Term_Grading.get(i).set(13, null);
		    		    				Term_Grading.get(i).add(14,null);
		    		    				Term_Grading.get(i).set(14, null);
	    		    		    	}	    	
	    		    		    	if(Total_SumSessionGrades!=0)
	    		    		    	{
	    		    		    		Total_Attendance_Grade = (Total_SumStudentGrades / Total_SumSessionGrades)*100;
	    		    		    	}
	    		    		    	
	    		    		    	if(resultGradeTerms.isLast())
	    		    				{
		    		    		    	Term_Grading.get(i).add(15,null);
		    		    				if(Total_Attendance_Grade!=-1)
		    		    					Term_Grading.get(i).set(15, String.valueOf(Math.round(Total_Attendance_Grade))+"%");
		    		    				else
		    		    					Term_Grading.get(i).set(15, "-");
		    		    					
	    		    				}
	    		    				else
	    		    				{
	    		    					Term_Grading.get(i).add(15,null);
		    		    				Term_Grading.get(i).set(15, null);
	    		    				}
	    		    				Term_Grading.get(i).add(16,null);
	    		    				Term_Grading.get(i).set(16, String.valueOf(student_id));	    		    		    	
	    		    				i=i+1;

	    		    			//Overall_Grade = Math.round(Course_Contribution / Total_Course_Grades * 100);
	    		    			//Student_Grading.setOverall_Grade(Overall_Grade);
	    			////////////////////End of Get Grading Per Term ///////////////////////////////////////////////////////////////////		
	    			}	    			
	    		} // term loop	    		
	    	}//semester loop
    	    if(course_id==0)
    	    {
    			Term_Grading_Map.put(resultStudentCourses.getString("Course_id"), Term_Grading);
    	    }
    	    else
    	    {
    			Term_Grading_Map.put(String.valueOf(student_id), Term_Grading);
    	    }
		//////////////////////Get Category Grading ////////////////////////////////////////////////////////////////////////
	    	String sCategories = "select * from assignment_category where Course_id="+resultStudentCourses.getString("Course_id");
	    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);		
			CategoryList = new ArrayList<ArrayList<String>>();
			int j=0;
			while (resultCategories.next())
			{
				
				String sCatGradeSum="select sum(Assignment_Grade), count(Assignment_Grade) from Student_Assignment_Grade where student_id="+student_id+" "
						+ "and Assignment_id in (select assignment_id from assignments where category_id="+resultCategories.getString("Category_id")+")"
						+ " and Assignment_id in (select assignment_id from assignments_classes where Due_Date>='"+year_Start+"' and Due_Date<='"+year_End+"')";
				ResultSet resultCatGradeSum = statementCatGradeSum.executeQuery(sCatGradeSum);

				String sCatMaxGradeSum = "select sum(max_grade), count(max_grade) from assignments where category_id="+resultCategories.getString("Category_id")+" and assignment_id in (select assignment_id from Student_Assignment_Grade where student_id="+student_id+""
						+ " and Assignment_id in (select assignment_id from assignments_classes where Due_Date>='"+year_Start+"' and Due_Date<='"+year_End+"'))";
				ResultSet resultCatMaxGradeSum = statementCatMaxGradeSum.executeQuery(sCatMaxGradeSum);
				double CatGradeSum=0.0;
				double CatMaxGradeSum=0.0;
			
				while (resultCatGradeSum.next())
				{
					CatGradeSum = resultCatGradeSum.getInt(1);
				}
						
				while (resultCatMaxGradeSum.next())
				{	
					CatMaxGradeSum = resultCatMaxGradeSum.getInt(1);
				}
				double Catpercentage=0.0;	
				String Category_Final_Grade=null;
				if(CatMaxGradeSum!=0)
				{
					Catpercentage = ((double)CatGradeSum / (double) CatMaxGradeSum)*100;
					Category_Final_Grade = String.valueOf( Math.round(Catpercentage));
				}
				///Populate hashmap for cat grades
				CategoryList.add(new ArrayList<String>());		
				CategoryList.get(j).add(0,null);
				CategoryList.get(j).set(0, resultCategories.getString("Category_id"));
				CategoryList.get(j).add(1,null);
				CategoryList.get(j).set(1, resultCategories.getString("Category_Name"));
				CategoryList.get(j).add(2,null);
				CategoryList.get(j).set(2,Category_Final_Grade);
    			j=j+1;
				///////////					
			}
			if(course_id==0)
			{
				CourseCatmap.put(resultStudentCourses.getString("Course_id"), CategoryList);
			}
			else
			{
				CourseCatmap.put(String.valueOf(student_id), CategoryList);
			}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	    
	    }
		///////////////////////////////////Get Previous School Year that has reports for that student//////////////////////////////////
		ArrayList<Integer> Previous_Years_id = new ArrayList<Integer>();
		ArrayList<String> Previous_Years_Name = new ArrayList<String>();
		String sPreviousYears = "select * from _school_year where school_year_id <> "+school_year_id+" and school_year_id in(select school_year_id from student_grade_year where student_id="+student_id+")";
		ResultSet resultPreviousYears = statementPreviousYears.executeQuery(sPreviousYears);
		while(resultPreviousYears.next())
		{
			Previous_Years_id.add(resultPreviousYears.getInt("School_year_id"));
			Previous_Years_Name.add(resultPreviousYears.getString("Year_Name"));
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Student_Grading.setTerm_Grading_Map(Term_Grading_Map);
		Overall_Grade = (int)Math.round(sumCourseGrading / sumCourseContribution * 100);
		Student_Grading.setOverall_Grade(Overall_Grade);		
		Student_Grading.setSchool_year_id(school_year_id);
		Student_Grading.setYear_Name(year_Name);
		Student_Grading.setCourseCatmap(CourseCatmap);		
		Student_Grading.setPrevious_Years_id(Previous_Years_id);
		Student_Grading.setPrevious_Years_Name(Previous_Years_Name);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Student_Grading;
		
	}
	
	public Student_Grading getFinalReportCard(int student_id, int course_id, int previous_school_year_id) {
		
		Student_Grading Student_Grading = new Student_Grading();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementSutdentSchool = connection.createStatement();
				Statement statementSutdentGrade = connection.createStatement();
				Statement statementGradeID = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementGradeTerms = connection.createStatement();
				Statement statementStudentCourses = connection.createStatement();
				Statement statementCategories = connection.createStatement();
				Statement statementGradeSum = connection.createStatement();
				Statement statementMaxGradeSum = connection.createStatement();
				Statement statementSumSessionGrades = connection.createStatement();
				Statement statementSessionGrade = connection.createStatement();
				Statement statementGradingScale = connection.createStatement();
				Statement statementSemesters = connection.createStatement();
				Statement statementYearSemesters = connection.createStatement();
				Statement statementSemesterExam = connection.createStatement();
				Statement statementStudentSemesterExam = connection.createStatement();
				Statement statementSemesterExamGradingScale = connection.createStatement();
				Statement statementSemesterGradingScale = connection.createStatement();
				Statement statementFinalCourseGradingScale = connection.createStatement();
				Statement statementCatGradeSum = connection.createStatement();
				Statement statementCatMaxGradeSum = connection.createStatement();				
				Statement statementClassDivision = connection.createStatement();
				Statement statementPreviousYears = connection.createStatement();){

			
			/////////////////////////////////////Get the student school ////////////////////////////////////
			String sSutdentSchool = "select school_id from student_grade_year where final_grading is null and student_id="+student_id;
			ResultSet resultSutdentSchool = statementSutdentSchool.executeQuery(sSutdentSchool);
			int school_id = 0;
			while(resultSutdentSchool.next())
			{
				school_id = resultSutdentSchool.getInt("School_id");
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////////////////Get the current school year (or previous school year if previous_school_year_id is not zero)/////////////////////////////////////////
	    	int school_year_id=0;
	    	Date year_Start = null;
	    	Date year_End = null;
	    	String year_Name = null;
	    	if(previous_school_year_id!=0)
			{
		    	String sSchoolYear = "select * from _School_year where school_year_id="+previous_school_year_id;
		    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
		    	while(resultSchoolYear.next())
		    	{
			    	school_year_id = resultSchoolYear.getInt("School_year_id");	
			    	year_Start = resultSchoolYear.getDate("Year_Start");
			    	year_End = resultSchoolYear.getDate("Year_End");
			    	year_Name = resultSchoolYear.getString("Year_Name");
		    	}
			}
			else
			{
				Date today = new Date();
		    	String sSchoolYear = "select * from _School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
		    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
		    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
		    	while(resultSchoolYear.next())
		    	{
			    	school_year_id = resultSchoolYear.getInt("School_year_id");	
			    	year_Start = resultSchoolYear.getDate("Year_Start");
			    	year_End = resultSchoolYear.getDate("Year_End");
			    	year_Name = resultSchoolYear.getString("Year_Name");
		    	}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////Get previous Grade_id and Class_id which are the grade and the class just before the promotion///////////////////////////
			String sSutdentGrade = "select grade_id, class_id from student_grade_year where student_id="+student_id+" and school_id="+school_id+" and school_year_id="+school_year_id;
			ResultSet resultSutdentGrade = statementSutdentGrade.executeQuery(sSutdentGrade);
			int grade_id = 0;
			int class_id = 0;
			while(resultSutdentGrade.next())
			{
				grade_id = resultSutdentGrade.getInt("Grade_id");
				class_id = resultSutdentGrade.getInt("Class_id");
			}
	    	/////////////////////////////////////////////////////////////////////////////////////////////////
	    	
	    	Date term_Start = null;
	    	Date term_End = null;
	    	String term_Name = null;
	    	Boolean termsExist = true;
	    	String sGradeTerms = "select * from _Grade_Terms where School_year_id="+school_year_id+" and Grade_id="+grade_id+" order by Term_Start";
	    	ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);

	    	LinkedHashMap<String, ArrayList<ArrayList<String>>> Term_Grading_Map = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
	    	ArrayList<ArrayList<String>> Term_Grading = new ArrayList<ArrayList<String>>();
			////////////////////////Get Semesters ///////////////////
    		String sSemesters=null;
    		sSemesters="select * from _Grade_Semesters where school_year_id="+school_year_id+" and Grade_id="+grade_id;
    		ResultSet resultSemesters = statementSemesters.executeQuery(sSemesters);

    		if(!resultGradeTerms.isBeforeFirst())
    		{
	    		sGradeTerms="select * from _year_terms where school_year_id="+school_year_id+" order by Term_Start";
	    		resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
	    		if(!resultGradeTerms.isBeforeFirst())
	    		{
	        		sGradeTerms="select * from _school_year where school_year_id="+school_year_id;
		    		resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
		    		termsExist = false;
		    		sSemesters="select * from _school_year where school_year_id="+school_year_id;
    	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
	    		}
	    		else
	    		{
	    			String sYearSemesters = "select * from _Year_Semesters where School_year_id="+school_year_id;
	    			ResultSet resultYearSemesters = statementYearSemesters.executeQuery(sYearSemesters);
	    			if(resultYearSemesters.isBeforeFirst())
	    			{
	    				resultSemesters = resultYearSemesters;
	    			}
	    			else
	    			{
    	   	    		sSemesters="select * from _school_year where school_year_id="+school_year_id;
        	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
	    			}
	    		}
    		}
    		if(resultGradeTerms.isBeforeFirst()&&!resultSemesters.isBeforeFirst())
    		{
   	    		sSemesters="select * from _school_year where school_year_id="+school_year_id;
	    		resultSemesters = statementSemesters.executeQuery(sSemesters);
    		}
	    	////////////////////////////////////////////////////////
    		int semester_id=0;

	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
	    	String sClassDivision = "select * from _Classes where school_year_id="+school_year_id+" and Class_id="+class_id;
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			while(resultClassDivision.next())
			{
				division_id = resultClassDivision.getInt("Division_id");
			}
	    	////////////////////////////////////////////////////////////////////////////////////////////////////
			String sStudentCourses=null;
			if(course_id==0)
			{
				if (division_id==0)
		    	{	
			    	sStudentCourses = "select * from _courses where school_year_id="+school_year_id+" and Report_Display=1 and "
			    	    	+ "((grade_id ="+grade_id+" and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from _Student_Electives where school_year_id="+school_year_id+" and Student_id="+student_id+" and course_id in(select course_id from _courses where school_year_id="+school_year_id+" and grade_id="+grade_id+")))";
		    	}
		    	else
		    	{
			    	sStudentCourses = "select * from _courses where school_year_id="+school_year_id+" and Report_Display=1 and "
			    	    	+ "((Course_id in (select Course_id from _Division_Courses where school_year_id="+school_year_id+" and Division_id="+division_id+") and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from _Student_Electives where school_year_id="+school_year_id+" and Student_id="+student_id+" and course_id in(select course_id from _courses where school_year_id="+school_year_id+" and grade_id="+grade_id+")))";
		    	}
			}
			else
			{
				sStudentCourses = "select * from _courses where school_year_id="+school_year_id+" and course_id="+course_id;
			}
	    
			ResultSet resultStudentCourses = statementStudentCourses.executeQuery(sStudentCourses);
	    	    	
		    LinkedHashMap<String, ArrayList<ArrayList<String>>> CourseCatmap = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		    ArrayList<ArrayList<String>> CategoryList = null;
		    int Overall_Grade=0;
			double sumCourseGrading=0.0;
			double sumCourseContribution=0.0;
			while (resultStudentCourses.next()) 
			{
				double Final_Course_Contribution = 0.0;
				double Final_SumCategory_Grade =	0.0;
				double Final_Course_Grade = -1.0;
		
		    	resultSemesters.beforeFirst();
		    	resultGradeTerms.beforeFirst();
	    	    int i=0;
	    	    Term_Grading=new ArrayList<ArrayList<String>>();
	    		double Total_Attendance_Grade = -1.0;  
	    		double Total_SumStudentGrades = 0.0;
				double Total_SumSessionGrades = 0.0;
	    	    while(resultSemesters.next())
		    	{
					double Sem_SumCategory_Grade = 0.0;
					double Sem_Grade_Contribution=0.0;
					int semesterTermsCount=0;
		    		
					resultGradeTerms.beforeFirst();
		    	    while(resultGradeTerms.next())
		    		{
		    			if(termsExist==true)
		    			{
		    				semester_id = resultGradeTerms.getInt("Semester_id");
		    			}
		    			if(semester_id==resultSemesters.getInt(1))
		    			{
		    				semesterTermsCount = semesterTermsCount+1;
		    			}	
		    		}
		    		resultGradeTerms.beforeFirst();
		    		int termCounter=0;
	
		    		while(resultGradeTerms.next())
		    		{    			
		    			if(termsExist==true)
		    			{
		    				semester_id = resultGradeTerms.getInt("Semester_id");
		    			}
		    			
		    			if(semester_id==resultSemesters.getInt(1) ||semester_id ==0 )
		    			{
		    				termCounter++;
		    				if(termsExist==true)
		    				{
		    					term_Start = resultGradeTerms.getDate("Term_Start");
		    					term_End = resultGradeTerms.getDate("Term_End");
		    					term_Name = resultGradeTerms.getString("Term_Name");
		    				}
		    				else
		    				{
		    					term_Start = resultGradeTerms.getDate("Year_Start");
		    					term_End = resultGradeTerms.getDate("Year_End");
		    					term_Name = resultGradeTerms.getString("Year_Name");
		    				}
		    	    	////////////////////Get Grading per Term///////////////////////////////////////////////////////////////////////////
		    		    			
		    		    			double Course_Contribution=0.0;
		    		    			double Total_Course_Grades=0.0; 		    			
		    		    				
		    		    	/////////////////////Get Course Grade //////////////////////////////////////////////////
		    		    		    	String sCategories = "select * from _assignment_category where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id");
		    		    		    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);
		    		    				double Term_SumCategory_Grade = 0.0;
		    		    				double Term_Grade_Contribution=0.0;
		    		    				double Category_Grade=0.0;
		    		    				
		    		    				while (resultCategories.next())
		    		    				{
		    		    					
		    		    					String sGradeSum="select sum(Assignment_Grade), count(Assignment_Grade) from _Student_Assignment_Grade where school_year_id="+school_year_id+" and student_id="+student_id+" "
		    		    							+ "and Assignment_id in (select assignment_id from _assignments where school_year_id="+school_year_id+" and category_id="+resultCategories.getString("Category_id")+")"
		    		    							+ " and Assignment_id in (select assignment_id from _assignments_classes where school_year_id="+school_year_id+" and Due_Date>='"+term_Start+"' and Due_Date<='"+term_End+"')";
		    		    					ResultSet resultGradeSum = statementGradeSum.executeQuery(sGradeSum);
	
		    		    					Category_Grade = resultCategories.getInt("Category_Grade");
	
		    		    					String sMaxGradeSum = "select sum(max_grade), count(max_grade) from _assignments where school_year_id="+school_year_id+" and category_id="+resultCategories.getString("Category_id")+" and assignment_id in (select assignment_id from _Student_Assignment_Grade where school_year_id="+school_year_id+" and student_id="+student_id+""
		    		    							+ " and Assignment_id in (select assignment_id from _assignments_classes where school_year_id="+school_year_id+" and Due_Date>='"+term_Start+"' and Due_Date<='"+term_End+"'))";
		    		    					ResultSet resultMaxGradeSum = statementMaxGradeSum.executeQuery(sMaxGradeSum);
		    		    					double GradeSum=0.0;
		    		    					double MaxGradeSum=0.0;
		    		    					int countAssignmentGrade = 0;
		    		    					
		    		    					while (resultGradeSum.next())
		    		    					{
		    		    						GradeSum = resultGradeSum.getInt(1);
		    		    						countAssignmentGrade = resultGradeSum.getInt(2);
		    		    					}
		    		    							
		    		    					while (resultMaxGradeSum.next())
		    		    					{	
		    		    						MaxGradeSum = resultMaxGradeSum.getInt(1);
		    		    					}
		    		    					double percentage=0.0;	
	
		    		    					if(MaxGradeSum!=0)
		    		    					{
		    		    						percentage = ((double)GradeSum / (double) MaxGradeSum)*100;
		   
		    		    					}
		    		    					
		    		    					
		    		    					if (Category_Grade!=0)
		    		    					{
		    		    						if(countAssignmentGrade!=0)
		    		    						{
			    		    						Term_Grade_Contribution= Term_Grade_Contribution+ Category_Grade*percentage/100.0;
			    		    						Term_SumCategory_Grade = Term_SumCategory_Grade + Category_Grade;
		    		    						}
	
		    		    					}
		    		    				}
	
		    		    				double Term_Course_Grade =-1.0;
		    		    				
		    		    				if (Term_SumCategory_Grade!=0)
		    		    				{
		    		    					Term_Course_Grade = Term_Grade_Contribution/Term_SumCategory_Grade*100.0;
		    		    				}			
		    		    	///////////////////////////////////////////////////////////////////////////////////////		
		    		    				
		    		    				
		    		    				///////////////////////Calculate Attendance Grade ///////////////////////////////////////////
		    		    				int SumStudentGrades = 0;
		    		    				int CountSessions = 0;
		    		    				int Session_Grade = 0;
		    		    				String sSumSessionGrades = "select sum(Session_Grade), count(Session_Grade) from _attendance where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id")+""
		    		    		    			+ " and Student_id="+student_id+" and Session_Date>='"+term_Start+"' and Session_Date<='"+term_End+"'";
		    		    		    	ResultSet resultSumSessionGrades = statementSumSessionGrades.executeQuery(sSumSessionGrades);
		    		    				
		    		    				while (resultSumSessionGrades.next())
		    		    				{
		    		    					SumStudentGrades = resultSumSessionGrades.getInt(1);
	    		    						CountSessions = resultSumSessionGrades.getInt(2);	
		    		    				}
	
		    		    		    	String sSessionGrade = "select Attendance_Grade, Attendance_Weight from _Courses where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id");
		    		    		    	ResultSet resultSessionGrade = statementSessionGrade.executeQuery(sSessionGrade);
		    		    				double Attendance_Weight=0.0;
		    		    				while (resultSessionGrade.next())
		    		    				{
		    		    					if(resultSessionGrade.getString(1)!=null)
		    		    					{
		    		    						Session_Grade = Integer.parseInt(resultSessionGrade.getString(1));
		    		    						Attendance_Weight = Integer.parseInt(resultSessionGrade.getString(2));
		    		    					}
		    		    				}
		    		    				double Attendance_Grade=0.0;
		    		    				if (Session_Grade!=0 && CountSessions!=0)
		    		    				{
		    		    					Attendance_Grade = ((double)SumStudentGrades / (double)(Session_Grade*CountSessions))*100.0;
	
			    		    			}
	    		    					Total_SumStudentGrades = Total_SumStudentGrades + SumStudentGrades;
		    		    				Total_SumSessionGrades = Total_SumSessionGrades + (Session_Grade*CountSessions);
		    		    				////////////////////////////////////////////////////////////////////////////////////////////
		    		    				double Attendance_Contribution=0.0;
		    		    				Attendance_Contribution = (Attendance_Weight*Attendance_Grade)/100.0;
		    		    				Term_Grade_Contribution = Term_Grade_Contribution + Attendance_Contribution;
		    		    				if(CountSessions!=0)
		    		    				{
		    		    					Term_SumCategory_Grade = Term_SumCategory_Grade + Attendance_Weight;
		    		    				}
		    		    				
		    		    				if (Term_SumCategory_Grade!=0)
		    		    				{
		    		    					Term_Course_Grade = Term_Grade_Contribution/Term_SumCategory_Grade*100.0;
		    		    				}
		    		    				//////////////Get the equivalent Letter ////////////////////
		    		    		    	String sGradingScale = "select Letter from _Grading_Scales where school_year_id="+school_year_id+" and grade_id="+grade_id+" and low<="+Math.round(Term_Course_Grade)+" and high>="+Math.round(Term_Course_Grade);
		    		    		    	ResultSet resultGradingScale = statementGradingScale.executeQuery(sGradingScale);
		    		    		    	String term_letter=null;
		    		    		    	while(resultGradingScale.next())
		    		    		    	{
		    		    		    		term_letter = resultGradingScale.getString("Letter");
		    		    		    	}
		    		    			////////////////////////////////////////////////////////////////////////////////////////////
		    		    			
		    		    		   ///////////////////////////Get Semester Exam Grading /////////////////////////////////
		    		    		    	String sSemesterExam = null;
		    		    		    	ResultSet resultSemesterExam = null;
		    		    		    	if(termsExist==true)
		    		    		    	{
			    		    		    	sSemesterExam = "select * from _Course_SemesterExam where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id")+" and Grade_Semester_id="+semester_id;
			    		    		    	resultSemesterExam = statementSemesterExam.executeQuery(sSemesterExam);
			    		    		    	if(!resultSemesterExam.isBeforeFirst())
			    		    		    	{
			    		    		    		sSemesterExam = "select * from _Course_SemesterExam where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id")+" and Year_Semester_id="+semester_id;
				    		    		    	resultSemesterExam = statementSemesterExam.executeQuery(sSemesterExam);
			    		    		    	}
		    		    		    	}
		    		    		    	double semster_Exam_Grade=-1.0;
		    		    		    	int exam_Grade=0;
		    		    		    	int exam_Weight=0;
		    		    		    	int student_Semester_Exam_Grade=-1;
		    		    		    	Boolean semester_Exam_Graded = false;
		    		    		    	if(resultSemesterExam!=null)
		    		    		    	{
			    		    		    	while(resultSemesterExam.next())
			    		    		    	{
				    		    		    	String sStudentSemesterExam = "select * from _Student_SemesterExam where school_year_id="+school_year_id+" and Exam_id="+resultSemesterExam.getInt("Exam_id")+" and Student_id="+student_id;
				    		    		    	ResultSet resultStudentSemesterExam = statementStudentSemesterExam.executeQuery(sStudentSemesterExam);
				    		    		    	exam_Grade = resultSemesterExam.getInt("Exam_Grade");
				    		    		    	exam_Weight = resultSemesterExam.getInt("Exam_Contribution");
				    		    		    	
				    		    		    	while(resultStudentSemesterExam.next())
				    		    		    	{
				    		    		    		student_Semester_Exam_Grade = resultStudentSemesterExam.getInt("Student_Grade");
				    		    		    		semester_Exam_Graded=true;
				    		    		    	}
				    		    		    	
				    		    		    	if(student_Semester_Exam_Grade!=-1)
				    		    		    	{
					    		    		    	semster_Exam_Grade = ((double) student_Semester_Exam_Grade / (double) exam_Grade)*100 ;
				    		    		    	}
			    		    		    	}
		    		    		    	}
		    		    		    	String sSemesterExamGradingScale = "select Letter from _Grading_Scales where school_year_id="+school_year_id+" and grade_id="+grade_id+" and low<="+Math.round(semster_Exam_Grade)+" and high>="+Math.round(semster_Exam_Grade);
		    		    		    	ResultSet resultSemesterExamGradingScale = statementSemesterExamGradingScale.executeQuery(sSemesterExamGradingScale);
		    		    		    	String letterSemesterExam=null;
		    		    		    	while(resultSemesterExamGradingScale.next())
		    		    		    	{
		    		    		    		letterSemesterExam = resultSemesterExamGradingScale.getString("Letter");
		    		    		    	}
		    		    		   ////////////////////////////////////////////////////////////////////////////////////////
		    		    		  ////////////////////////////////////Calculate Semester Grading ////////////////////////
		    		    				
		    		    				Sem_Grade_Contribution = Sem_Grade_Contribution + Term_Grade_Contribution;
		    		    				Sem_SumCategory_Grade = Sem_SumCategory_Grade + Term_SumCategory_Grade;
		    		    		    	//////////////////////////////////////////////////////////////////////////////
		    		    		   /////////////////////////////Calculate Overall Grade///////////////////////////////
	
		    		    				
		    		    				Course_Contribution =Course_Contribution + Term_Course_Grade*Integer.parseInt(resultStudentCourses.getString("Course_Grade"))/100.0;
		    		    				Total_Course_Grades = Total_Course_Grades + Integer.parseInt(resultStudentCourses.getString("Course_Grade"));
		    		    				
		    		    				
		    		    			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		    		    				Term_Grading.add(new ArrayList<String>());
		    		    				Term_Grading.get(i).add(0,null);
		    		    				Term_Grading.get(i).set(0, resultGradeTerms.getString(1));
		    		    				Term_Grading.get(i).add(1,null);
		    		    				Term_Grading.get(i).set(1, term_Name);
		    		    				
		    		    				Term_Grading.get(i).add(2,null);
		    		    				Term_Grading.get(i).set(2, resultStudentCourses.getString("Course_id"));
		    		    				Term_Grading.get(i).add(3,null);
		    		    				Term_Grading.get(i).set(3, resultStudentCourses.getString("Course_Name"));
		    		
		    		    				Term_Grading.get(i).add(4,null);
		    		    				if(Term_Course_Grade!=-1)
		    		    					Term_Grading.get(i).set(4, String.valueOf(Math.round(Term_Course_Grade)));
		    		    				else
		    		    					Term_Grading.get(i).set(4, null);
		    		    				Term_Grading.get(i).add(5,null);
		    		    				Term_Grading.get(i).set(5, term_letter);
		    		    				
		    		    				double Semester_Grade=-1.0;
		    		    				String letterSemester=null;
		    		    				if(semesterTermsCount==termCounter)
		    		    				{
		    		    					if(semester_Exam_Graded)
		    		    					{
			    		    					//Sem_Grade_Contribution = Sem_Grade_Contribution + student_Semester_Exam_Grade;
			    		    					//Sem_SumCategory_Grade = Sem_SumCategory_Grade+ exam_Grade;
			    		    					Sem_Grade_Contribution = Sem_Grade_Contribution + exam_Weight*semster_Exam_Grade/100;
			    		    					Sem_SumCategory_Grade = Sem_SumCategory_Grade+ exam_Weight;
			    		    					Semester_Grade = (Sem_Grade_Contribution / Sem_SumCategory_Grade)*100.0;
			    		    					
				    		    		    	String sSemesterGradingScale = "select Letter from _Grading_Scales where school_year_id="+school_year_id+" and grade_id="+grade_id+" and low<="+Math.round(Semester_Grade)+" and high>="+Math.round(Semester_Grade);
				    		    		    	ResultSet resultSemesterGradingScale = statementSemesterGradingScale.executeQuery(sSemesterGradingScale);
				    		    		    	
				    		    		    	while(resultSemesterGradingScale.next())
				    		    		    	{
				    		    		    		letterSemester = resultSemesterGradingScale.getString("Letter");
				    		    		    	}
		    		    					}
		    		    					
		    		    					Term_Grading.get(i).add(6,null);
			    		    				Term_Grading.get(i).set(6, String.valueOf(semester_id));
			    		    				Term_Grading.get(i).add(7,null);
			    		    				Term_Grading.get(i).set(7, resultSemesters.getString("Semester_Name")+" Exam Grade");
			    		    				Term_Grading.get(i).add(8,null);
			    		    				if(semster_Exam_Grade!=-1)
			    		    				{
			    		    					Term_Grading.get(i).set(8, String.valueOf(Math.round(semster_Exam_Grade))+"%");
			    		    				}
			    		    				else
			    		    				{
			    		    					Term_Grading.get(i).set(8, "-");
			    		    				}
			    		    				
			    		    				Term_Grading.get(i).add(9,null);
			    		    				Term_Grading.get(i).set(9, letterSemesterExam);
			    		    				Term_Grading.get(i).add(10,null);
			    		    				Term_Grading.get(i).set(10, resultSemesters.getString("Semester_Name"));
			    		    				Term_Grading.get(i).add(11,null);
			    		    				if(Semester_Grade!=-1)
			    		    				{
				    		    				Term_Grading.get(i).set(11, String.valueOf(Math.round(Semester_Grade))+"%");
			    		    				}
			    		    				else
			    		    				{
				    		    				Term_Grading.get(i).set(11, "-");
			    		    				}
			    		    				Term_Grading.get(i).add(12,null);
			    		    				Term_Grading.get(i).set(12, letterSemester);
		    		    				}
		    		    				else
		    		    				{
			    		    				Term_Grading.get(i).add(6,null);
			    		    				Term_Grading.get(i).set(6, null);
			    		    				Term_Grading.get(i).add(7,null);
			    		    				Term_Grading.get(i).set(7, null);
			    		    				Term_Grading.get(i).add(8,null);
			    		    				Term_Grading.get(i).set(8, null);
			    		    				Term_Grading.get(i).add(9,null);
			    		    				Term_Grading.get(i).set(9, null);
			    		    				Term_Grading.get(i).add(10,null);
			    		    				Term_Grading.get(i).set(10, null);
			    		    				Term_Grading.get(i).add(11,null);
			    		    				Term_Grading.get(i).set(11, null);
			    		    				Term_Grading.get(i).add(12,null);
			    		    				Term_Grading.get(i).set(12, null);
		    		    				}
		    		    				Final_Course_Contribution = Final_Course_Contribution + Sem_Grade_Contribution;
		    		    				Final_SumCategory_Grade =	Final_SumCategory_Grade + Sem_SumCategory_Grade;
		    		    				if(Final_SumCategory_Grade!=0)
		    		    				{
		    		    					Final_Course_Grade = Final_Course_Contribution/Final_SumCategory_Grade*100;
		    		    				}
		    		    				
		    		    				
		    		    		    	String sFinalCourseGradingScale = "select Letter from _Grading_Scales where school_year_id="+school_year_id+" and grade_id="+grade_id+" and low<="+Math.round(Final_Course_Grade)+" and high>="+Math.round(Final_Course_Grade);
		    		    		    	ResultSet resultFinalCourseGradingScale = statementFinalCourseGradingScale.executeQuery(sFinalCourseGradingScale);
		    		    		    	String letterFinalCourse = null;
		    		    		    	while(resultFinalCourseGradingScale.next())
		    		    		    	{
		    		    		    		letterFinalCourse = resultFinalCourseGradingScale.getString("Letter");
		    		    		    	}
		    		    		    	if(resultSemesters.isLast()&&resultGradeTerms.isLast() )
		    		    		    	{
			    		    				Term_Grading.get(i).add(13,null);
			    		    				if(Final_Course_Grade!=-1)
			    		    				{
			    		    					Term_Grading.get(i).set(13, String.valueOf(Math.round(Final_Course_Grade)+"%"));
				    		    				/////////////////////////////Get overall Grade here /////////////////////////////
				    		    				if(resultStudentCourses.getInt("Contribute")==1)
				    		    				{
				    		    					sumCourseGrading = sumCourseGrading + (resultStudentCourses.getInt("Course_Grade")*Final_Course_Grade)/100;
						    		    			sumCourseContribution = sumCourseContribution + resultStudentCourses.getInt("Course_Grade");
				    		    				}
				    		    				//////////////////////////////////////////////////////////////////////////////////
			    		    				}
			    		    				else
			    		    				{
			    		    					Term_Grading.get(i).set(13, "-");
			    		    				}
			    		    				
			    		    				Term_Grading.get(i).add(14,null);
			    		    				Term_Grading.get(i).set(14, letterFinalCourse);
			    		    				
	
		    		    		    	}
		    		    		    	else
		    		    		    	{
			    		    				Term_Grading.get(i).add(13,null);
			    		    				Term_Grading.get(i).set(13, null);
			    		    				Term_Grading.get(i).add(14,null);
			    		    				Term_Grading.get(i).set(14, null);
		    		    		    	}	    	
		    		    		    	if(Total_SumSessionGrades!=0)
		    		    		    	{
		    		    		    		Total_Attendance_Grade = (Total_SumStudentGrades / Total_SumSessionGrades)*100;
		    		    		    	}
		    		    		    	
		    		    		    	if(resultGradeTerms.isLast())
		    		    				{
			    		    		    	Term_Grading.get(i).add(15,null);
			    		    				if(Total_Attendance_Grade!=-1)
			    		    					Term_Grading.get(i).set(15, String.valueOf(Math.round(Total_Attendance_Grade))+"%");
			    		    				else
			    		    					Term_Grading.get(i).set(15, "-");
			    		    					
		    		    				}
		    		    				else
		    		    				{
		    		    					Term_Grading.get(i).add(15,null);
			    		    				Term_Grading.get(i).set(15, null);
		    		    				}
		    		    				Term_Grading.get(i).add(16,null);
		    		    				Term_Grading.get(i).set(16, String.valueOf(student_id));	    		    		    	
		    		    				i=i+1;
	
		    		    			//Overall_Grade = Math.round(Course_Contribution / Total_Course_Grades * 100);
		    		    			//Student_Grading.setOverall_Grade(Overall_Grade);
		    			////////////////////End of Get Grading Per Term ///////////////////////////////////////////////////////////////////		
		    			}
	
		    			
		    		} // term loop
		    		
		    	}//semester loop
	    	    if(course_id==0)
	    	    {
	    			Term_Grading_Map.put(resultStudentCourses.getString("Course_id"), Term_Grading);
	    	    }
	    	    else
	    	    {
	    			Term_Grading_Map.put(String.valueOf(student_id), Term_Grading);
	    	    }
			//////////////////////Get Category Grading ////////////////////////////////////////////////////////////////////////
		    	String sCategories = "select * from _assignment_category where school_year_id="+school_year_id+" and Course_id="+resultStudentCourses.getString("Course_id");
		    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);		
				CategoryList = new ArrayList<ArrayList<String>>();
				int j=0;
				while (resultCategories.next())
				{					
					String sCatGradeSum="select sum(Assignment_Grade), count(Assignment_Grade) from _Student_Assignment_Grade where school_year_id="+school_year_id+" and student_id="+student_id+" "
							+ "and Assignment_id in (select assignment_id from _assignments where school_year_id="+school_year_id+" and category_id="+resultCategories.getString("Category_id")+")"
							+ " and Assignment_id in (select assignment_id from _assignments_classes where school_year_id="+school_year_id+" and Due_Date>='"+year_Start+"' and Due_Date<='"+year_End+"')";
					ResultSet resultCatGradeSum = statementCatGradeSum.executeQuery(sCatGradeSum);
	
					String sCatMaxGradeSum = "select sum(max_grade), count(max_grade) from _assignments where school_year_id="+school_year_id+" and category_id="+resultCategories.getString("Category_id")+" and assignment_id in (select assignment_id from _Student_Assignment_Grade where school_year_id="+school_year_id+" and student_id="+student_id+""
							+ " and Assignment_id in (select assignment_id from _assignments_classes where school_year_id="+school_year_id+" and Due_Date>='"+year_Start+"' and Due_Date<='"+year_End+"'))";
					ResultSet resultCatMaxGradeSum = statementCatMaxGradeSum.executeQuery(sCatMaxGradeSum);
					double CatGradeSum=0.0;
					double CatMaxGradeSum=0.0;
				
					while (resultCatGradeSum.next())
					{
						CatGradeSum = resultCatGradeSum.getInt(1);
					}
							
					while (resultCatMaxGradeSum.next())
					{	
						CatMaxGradeSum = resultCatMaxGradeSum.getInt(1);
					}
					double Catpercentage=0.0;	
					String Category_Final_Grade=null;
					if(CatMaxGradeSum!=0)
					{
						Catpercentage = ((double)CatGradeSum / (double) CatMaxGradeSum)*100;
						Category_Final_Grade = String.valueOf( Math.round(Catpercentage));
					}
					///Populate hashmap for cat grades
					CategoryList.add(new ArrayList<String>());		
					CategoryList.get(j).add(0,null);
					CategoryList.get(j).set(0, resultCategories.getString("Category_id"));
					CategoryList.get(j).add(1,null);
					CategoryList.get(j).set(1, resultCategories.getString("Category_Name"));
					CategoryList.get(j).add(2,null);
					CategoryList.get(j).set(2,Category_Final_Grade);
	    			j=j+1;
					///////////					
				}
				if(course_id==0)
				{
					CourseCatmap.put(resultStudentCourses.getString("Course_id"), CategoryList);
				}
				else
				{
					CourseCatmap.put(String.valueOf(student_id), CategoryList);
				}
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			///////////////////////////////////Get Previous School Year that has reports for that student//////////////////////////////////
			ArrayList<Integer> Previous_Years_id = new ArrayList<Integer>();
			ArrayList<String> Previous_Years_Name = new ArrayList<String>();
			String sPreviousYears = "select * from _school_year where school_year_id <> "+school_year_id+" and school_year_id in(select school_year_id from student_grade_year where student_id="+student_id+")";
			ResultSet resultPreviousYears = statementPreviousYears.executeQuery(sPreviousYears);
			while(resultPreviousYears.next())
			{
				Previous_Years_id.add(resultPreviousYears.getInt("School_year_id"));
				Previous_Years_Name.add(resultPreviousYears.getString("Year_Name"));
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Student_Grading.setTerm_Grading_Map(Term_Grading_Map);
			Overall_Grade = (int) Math.round(sumCourseGrading / sumCourseContribution * 100);
			Student_Grading.setOverall_Grade(Overall_Grade);		
			Student_Grading.setSchool_year_id(school_year_id);
			Student_Grading.setYear_Name(year_Name);
			Student_Grading.setCourseCatmap(CourseCatmap);		
			Student_Grading.setPrevious_Years_id(Previous_Years_id);
			Student_Grading.setPrevious_Years_Name(Previous_Years_Name);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Student_Grading;		
	}
}
