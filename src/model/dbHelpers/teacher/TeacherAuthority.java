package model.dbHelpers.teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import model.beans.teacher.Authority_Container;
import model.beans.teacher.Content_Container;
import model.dbHelpers.DbConnector;

public class TeacherAuthority {	
	public Authority_Container getTeacherAuthority(int teacher_id){
		ArrayList<ArrayList<String>> authorityList = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<ArrayList<String>>> categorymap = new HashMap<String, ArrayList<ArrayList<String>>>();
		HashMap<String, ArrayList<ArrayList<String>>> unitmap = new HashMap<String, ArrayList<ArrayList<String>>>();
		HashMap<String, ArrayList<ArrayList<String>>> semesterExamsmap = new HashMap<String, ArrayList<ArrayList<String>>>();
		Authority_Container Authority_Container = new Authority_Container();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeacher_Schools = connection.createStatement();
				Statement statementTeacher_Grades = connection.createStatement();
				Statement statementTeacher_Classes = connection.createStatement();
				Statement statementTeacher_Courses = connection.createStatement();
				Statement statementCourseName = connection.createStatement();
				Statement statementGroupName = connection.createStatement();
				Statement statementCategories = connection.createStatement();
				Statement statementUnits = connection.createStatement();
				Statement statementSemesterExams = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementExamSemesters = connection.createStatement();
				Statement statementGradeTerms = connection.createStatement();){

	    	String sTeacherSchools = "select * from school where school_id in (select school_id from teacher_school where teacher_id="+teacher_id+" order by School_id asc)";
	    	ResultSet resultTeacher_Schools = statementTeacher_Schools.executeQuery(sTeacherSchools);
            int j=0;
            while(resultTeacher_Schools.next()) 
	        {
	    		String school_id = resultTeacher_Schools.getString("School_id");
	    		String school_Name = resultTeacher_Schools.getString("name");
	    		
	    		String sTeacher_Grades = "select * from Grades where School_id = '"+school_id + "' and Grade_id in(Select Grade_id from Teacher_Grades where Teacher_id ="+teacher_id+")";
	    	    ResultSet resultTeacher_Grades = statementTeacher_Grades.executeQuery(sTeacher_Grades);	
	    	    while(resultTeacher_Grades.next())
	    	    {

		    		String grade_id = resultTeacher_Grades.getString("Grade_id");		    	    
		    		String grade_Name = resultTeacher_Grades.getString("Grade_Name");
		    		
	            	String sTeacher_Classes = "select * from Classes where Grade_id='"+ grade_id + "' and Class_id in(Select Class_id from Teacher_Classes where Teacher_id ="+teacher_id+")";
	            	ResultSet resultTeacher_Classes = statementTeacher_Classes.executeQuery(sTeacher_Classes);	

    	            while(resultTeacher_Classes.next()) 
	            	{
			    		String class_id = resultTeacher_Classes.getString("Class_id");			    	    
			    		String class_Name = resultTeacher_Classes.getString("Class_Name");
	    				
	    				String sTeacher_Courses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and Class_id ='"+class_id+"'";
	    	            ResultSet resultTeacher_Courses = statementTeacher_Courses.executeQuery(sTeacher_Courses);	
	    	            while(resultTeacher_Courses.next()) 
	    	            {
    	            		String course_id = resultTeacher_Courses.getString("Course_id");
    	            		String sCourseName = "select Course_Name from Courses where Course_id ='"+course_id+"'";
    			    	    ResultSet resultCourseName = statementCourseName.executeQuery(sCourseName);
    			    	    String course_Name=null;
    			    	    while(resultCourseName.next())
    			    	    {
    			    	    	course_Name = resultCourseName.getString("Course_Name");
    			    	    }    	            		
    	            		authorityList.add(new ArrayList<String>());
    	            		authorityList.get(j).add(0,null);
    	            		authorityList.get(j).set(0, school_id);
    	            		authorityList.get(j).add(1,null);
    	            		authorityList.get(j).set(1, school_Name);
    	            		
    	            		authorityList.get(j).add(2,null);
    	            		authorityList.get(j).set(2, grade_id);
    	            		authorityList.get(j).add(3,null);
    	            		authorityList.get(j).set(3, grade_Name);
    	            		
    	            		authorityList.get(j).add(4,null);
    	            		authorityList.get(j).set(4, class_id);
    	            		authorityList.get(j).add(5,null);
    	            		authorityList.get(j).set(5, class_Name);
    	            		
    	            		authorityList.get(j).add(6,null);
    	            		authorityList.get(j).set(6, course_id);
    	            		authorityList.get(j).add(7,null);
    	            		authorityList.get(j).set(7, course_Name);
    	            		if(resultTeacher_Courses.getString("Group_id")!=null)
	    	            	{	
           	            		String group_id = resultTeacher_Courses.getString("group_id");
        	            		String sGroupName = "select Group_Name from "+"`Groups`"+" where Group_id ='"+group_id+"'";
        			    	    ResultSet resultGroupName = statementGroupName.executeQuery(sGroupName);
        			    	    String group_Name=null;
        			    	    while(resultGroupName.next())
        	            		{
        			    	    	group_Name = resultGroupName.getString("Group_Name");
        	            		}
        			    	    authorityList.get(j).add(8,null);
        			    	    authorityList.get(j).set(8, group_id);
        			    	    authorityList.get(j).add(9,null);
        			    	    authorityList.get(j).set(9, group_Name);
	    	            	}
	    	            	else
	    	            	{
	    	            		authorityList.get(j).add(8,null);
	    	            		authorityList.get(j).set(8, null);
	    	            		authorityList.get(j).add(9,null);
	    	            		authorityList.get(j).set(9, null);
	    	            	}
    	            		j=j+1;

    	            		////////////////////////Get the category related to the course /////////////////////////////////
					    	String sCategories = "select * from Assignment_Category where Course_id ="+resultTeacher_Courses.getString("Course_id");
					    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);
							ArrayList<ArrayList<String>> categoryList = new ArrayList<ArrayList<String>>();
		    	            int r=0;
			    			while(resultCategories.next()) 
					        {
			    	            categoryList.add(new ArrayList<String>());
			    				categoryList.get(r).add(0,null);
			    				categoryList.get(r).set(0, resultCategories.getString("Category_id"));
			    				categoryList.get(r).add(1,null);
			    				categoryList.get(r).set(1, resultCategories.getString("Category_Name"));
			    				categoryList.get(r).add(2,null);
			    				categoryList.get(r).set(2, resultCategories.getString("Create_Authority"));
			    				categorymap.put(resultTeacher_Courses.getString("Course_id"), categoryList);
				    			r=r+1;
					        }
    	            		///////////////////////////////////////////////////////////////////////////////////////////
			    			
    	            		////////////////////////Get the Units related to the course /////////////////////////////////
					    	String sUnits = "select * from Course_Units where Course_id ="+resultTeacher_Courses.getString("Course_id");
					    	ResultSet resultUnits = statementUnits.executeQuery(sUnits);
							ArrayList<ArrayList<String>> unitList = new ArrayList<ArrayList<String>>();
		    	            int n=0;
			    			while(resultUnits.next()) 
					        {
			    				unitList.add(new ArrayList<String>());
			    				unitList.get(n).add(0,null);
			    				unitList.get(n).set(0, resultUnits.getString("Unit_id"));
			    				unitList.get(n).add(1,null);
			    				unitList.get(n).set(1, resultUnits.getString("Unit_Name"));
			    				unitmap.put(resultTeacher_Courses.getString("Course_id"), unitList);
				    			n=n+1;
					        }
    	            		///////////////////////////////////////////////////////////////////////////////////////////
			    			
			    			///////////////////////Get Smester Exams related to the Course ///////////////////////////////////
			    			Date today = new Date();
			    			String sSchoolYear = "select * from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' and School_id="+school_id;
			    			ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
			    			int school_year_id=0;
			    			while(resultSchoolYear.next())
			    	    	{
			    		    	school_year_id = resultSchoolYear.getInt("School_year_id");	
			    	    	}
			    			
			    			String semester_Source = null;
			    			String sExamSemesters = "select * from Grade_Semesters where school_year_id="+school_year_id+" and Grade_id="+grade_id;						
			    			ResultSet resultExamSemesters = statementExamSemesters.executeQuery(sExamSemesters);
			    			semester_Source="Grade";
			    			if(!resultExamSemesters.isBeforeFirst())
			    		   	{
			    				///////First check if this Grade has terms or not////////////////////////
			    				String sGradeTerms = "select * from Grade_Terms where school_year_id="+school_year_id+" and Grade_id="+grade_id;						
			    				ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
			    		   		////////////////////////////////////////////////////////////////////////
			    		   		if(!resultGradeTerms.isBeforeFirst())
			    		   		{
			    					sExamSemesters = "select * from Year_Semesters where school_year_id="+school_year_id;						
			    					resultExamSemesters = statementExamSemesters.executeQuery(sExamSemesters);
			    					semester_Source="Year";
			    		   		}
			    		   	}
			    			int s=0;
			    			ArrayList<ArrayList<String>> semesterExamsList = new ArrayList<ArrayList<String>>();
			    			while (resultExamSemesters.next())
			    			{
			    				String sSemesterExams = null;
			    				if(semester_Source=="Grade")
				    			{
				    				sSemesterExams = "select * from Course_SemesterExam where Course_id ="+resultTeacher_Courses.getString("Course_id")+" and Grade_Semester_id="+resultExamSemesters.getInt("Semester_id");
				    			}
			    				else
			    				{
			    					sSemesterExams = "select * from Course_SemesterExam where Course_id ="+resultTeacher_Courses.getString("Course_id")+" and Year_Semester_id="+resultExamSemesters.getInt("Semester_id");
			    				}

						    	ResultSet resultSemesterExams = statementSemesterExams.executeQuery(sSemesterExams);
								
			    	            
				    			while(resultSemesterExams.next()) 
						        {
				    				semesterExamsList.add(new ArrayList<String>());
				    				semesterExamsList.get(s).add(0,null);
				    				semesterExamsList.get(s).set(0, resultSemesterExams.getString("Exam_id"));
				    				semesterExamsList.get(s).add(1,null);
				    				semesterExamsList.get(s).set(1, resultExamSemesters.getString("Semester_Name"));
				    				semesterExamsList.get(s).add(2,null);
				    				semesterExamsList.get(s).set(2, resultSemesterExams.getString("Exam_Grade"));
				    				semesterExamsmap.put(resultTeacher_Courses.getString("Course_id"), semesterExamsList);
					    			s=s+1;
						        }
			    			}

	    	            		///////////////////////////////////////////////////////////////////////////////////////////
			    			////////////////////////////////////////////////////////////////////////////////////////////////////
	    	            }
	            	} 

	    	    }
			
	        }
            Authority_Container.setAuthorityList(authorityList);
            Authority_Container.setCategorymap(categorymap);
            Authority_Container.setUnitmap(unitmap);
            Authority_Container.setSemesterExamsmap(semesterExamsmap);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Authority_Container;
	}
	
	public Content_Container getTeacherContent(int teacher_id){
		ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap = new HashMap<String, ArrayList<ArrayList<String>>>();
		HashMap<String, ArrayList<ArrayList<String>>> contentunitmap = new HashMap<String, ArrayList<ArrayList<String>>>();
		Content_Container Content_Container = new Content_Container();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeacher_Schools = connection.createStatement();
				Statement statementTeacher_Grades = connection.createStatement();
				Statement statementTeacher_Content = connection.createStatement();
				Statement statementCourseName = connection.createStatement();
				Statement statementCategories = connection.createStatement();
				Statement statementUnits = connection.createStatement();){

	    	String sTeacherSchools = "select * from school where school_id in (select school_id from teacher_school where teacher_id="+teacher_id+" order by School_id asc)";
	    	ResultSet resultTeacher_Schools = statementTeacher_Schools.executeQuery(sTeacherSchools);
            int j=0;
            while(resultTeacher_Schools.next()) 
	        {
	    		String school_id = resultTeacher_Schools.getString("School_id");
	    		String school_Name = resultTeacher_Schools.getString("name");
	    		
	    		String sTeacher_Grades = "select * from Grades where School_id = '"+school_id + "' and Grade_id in(Select Grade_id from Teacher_Grades where Teacher_id ="+teacher_id+")";
	    	    ResultSet resultTeacher_Grades = statementTeacher_Grades.executeQuery(sTeacher_Grades);	
	    	    while(resultTeacher_Grades.next())
	    	    {

		    		String grade_id = resultTeacher_Grades.getString("Grade_id");		    	    
		    		String grade_Name = resultTeacher_Grades.getString("Grade_Name");

			    	String sTeacher_Content = "select * from Courses where Grade_id ="+grade_id+" and Course_id in (select Course_id from teacher_content where teacher_id="+teacher_id+")";
			    	ResultSet resultTeacher_Content = statementTeacher_Content.executeQuery(sTeacher_Content);

    	            while(resultTeacher_Content.next()) 
	            	{


    	            		String course_id = resultTeacher_Content.getString("Course_id");
    	            		String sCourseName = "select Course_Name from Courses where Course_id ='"+course_id+"'";
    			    	    ResultSet resultCourseName = statementCourseName.executeQuery(sCourseName);
    			    	    String course_Name=null;
    			    	    while(resultCourseName.next())
    			    	    {
    			    	    	course_Name = resultCourseName.getString("Course_Name");
    			    	    }    	            		
    			    	    contentList.add(new ArrayList<String>());
    			    	    contentList.get(j).add(0,null);
    			    	    contentList.get(j).set(0, school_id);
    			    	    contentList.get(j).add(1,null);
    			    	    contentList.get(j).set(1, school_Name);
    	            		
    			    	    contentList.get(j).add(2,null);
    			    	    contentList.get(j).set(2, grade_id);
    			    	    contentList.get(j).add(3,null);
    			    	    contentList.get(j).set(3, grade_Name);
    	            		
    			    	    contentList.get(j).add(4,null);
    			    	    contentList.get(j).set(4, course_id);
    			    	    contentList.get(j).add(5,null);
    			    	    contentList.get(j).set(5, course_Name);

    	            		j=j+1;

    	            		////////////////////////Get the category related to the course /////////////////////////////////
					    	String sCategories = "select * from Assignment_Category where Course_id ="+resultTeacher_Content.getString("Course_id");
					    	ResultSet resultCategories = statementCategories.executeQuery(sCategories);
							ArrayList<ArrayList<String>> categoryList = new ArrayList<ArrayList<String>>();
		    	            int r=0;
			    			while(resultCategories.next()) 
					        {
			    	            categoryList.add(new ArrayList<String>());
			    				categoryList.get(r).add(0,null);
			    				categoryList.get(r).set(0, resultCategories.getString("Category_id"));
			    				categoryList.get(r).add(1,null);
			    				categoryList.get(r).set(1, resultCategories.getString("Category_Name"));
			    				categoryList.get(r).add(2,null);
			    				categoryList.get(r).set(2, resultCategories.getString("Create_Authority"));
			    				contentcategorymap.put(resultTeacher_Content.getString("Course_id"), categoryList);
				    			r=r+1;
					        }

    	            		///////////////////////////////////////////////////////////////////////////////////////////
			    			
    	            		////////////////////////Get the Units related to the course /////////////////////////////////
					    	String sUnits = "select * from Course_Units where Course_id ="+resultTeacher_Content.getString("Course_id");
					    	ResultSet resultUnits = statementUnits.executeQuery(sUnits);
							ArrayList<ArrayList<String>> unitList = new ArrayList<ArrayList<String>>();
		    	            int n=0;
			    			while(resultUnits.next()) 
					        {
			    				unitList.add(new ArrayList<String>());
			    				unitList.get(n).add(0,null);
			    				unitList.get(n).set(0, resultUnits.getString("Unit_id"));
			    				unitList.get(n).add(1,null);
			    				unitList.get(n).set(1, resultUnits.getString("Unit_Name"));
				    			n=n+1;
					        }

			    			contentunitmap.put(resultTeacher_Content.getString("Course_id"), unitList);
    	            		///////////////////////////////////////////////////////////////////////////////////////////
	            	} 

	    	    }
			
	        }
            Content_Container.setContentList(contentList);
            Content_Container.setContentcategorymap(contentcategorymap);
            Content_Container.setContentunitmap(contentunitmap);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}
}
