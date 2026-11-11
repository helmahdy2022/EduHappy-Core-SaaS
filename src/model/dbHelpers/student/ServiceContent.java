package model.dbHelpers.student;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.beans.student.Course_Content;
import model.beans.student.Course_Structure;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.FileUploadManagement;

public class ServiceContent {
	public Course_Content getContent(int student_id, String appPath){
		Course_Content Course_Content = new Course_Content();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudentCourses = connection.createStatement();
				Statement statementCourseBooks = connection.createStatement();
				Statement statementCourseLessons = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();){
			
			ArrayList<ArrayList<String>> CourseList = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> CourseContent = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> CourseLessons = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> BookFiles = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> LessonFiles = new ArrayList<ArrayList<String>>();
	
			
	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
	    	String sClassDivision = "select * from Classes where  Class_id=(select class_id from students where student_id="+student_id+")";
			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
			int division_id = 0;
			//resultClassDivision.next();
			while(resultClassDivision.next())
			{
				division_id = resultClassDivision.getInt("Division_id");
			}			
	    	////////////////////////////////////////////////////////////////////////////////////////////////////
			String sStudentCourses=null;
			if (division_id==0)
	    	{	
		    	sStudentCourses = "select * from courses where "
		    	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
		    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
	    	}
	    	else
	    	{
		    	sStudentCourses = "select * from courses where "
		    	    	+ "((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+") and Elective=0)"
		    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
	    	}			
/*			
	    	String sStudentCourses = "select * from courses where "
	    	+ "((grade_id =(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")) and Elective=0)"
	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id=(select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")))))";
*/	    	ResultSet resultStudentCourses = statementStudentCourses.executeQuery(sStudentCourses);
			int i=0;
			int j=0;
			int n=0;
			int l=0;
			int r=0;
			AccountManagement accountManagement = new AccountManagement();
			int account_id = 0;
			int school_id = 0;
			while (resultStudentCourses.next())
			{
				CourseList.add(new ArrayList<String>());
				CourseList.get(i).add(0,null);
				CourseList.get(i).set(0, resultStudentCourses.getString("Course_id"));
				CourseList.get(i).add(1,null);
				CourseList.get(i).set(1, resultStudentCourses.getString("Course_Name"));
				if(i==0)
				{
					account_id = accountManagement.getAccountID(resultStudentCourses.getInt("Course_id"));
					school_id = accountManagement.getSchoolID(resultStudentCourses.getInt("Course_id"));
				}
				i=i+1;
				///////////////////////////////////Get Course Books ///////////////////////////////////////////////////////////
		    	String sCourseBooks = "select * from Books where Course_id="+resultStudentCourses.getString("Course_id");
		    	ResultSet resultCourseBooks = statementCourseBooks.executeQuery(sCourseBooks);
				while (resultCourseBooks.next())
				{
					CourseContent.add(new ArrayList<String>());
					CourseContent.get(j).add(0,null);
					CourseContent.get(j).set(0, resultCourseBooks.getString("Course_id"));
					CourseContent.get(j).add(1,null);
					CourseContent.get(j).set(1, resultCourseBooks.getString("Book_id"));
					CourseContent.get(j).add(2,null);
					CourseContent.get(j).set(2, resultCourseBooks.getString("Title"));
					j=j+1;
					//////////////////////////Retrieve the book files //////////////////////////////////////
					File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Books/"+resultCourseBooks.getString("Book_id"));
					File[] list = jsp.listFiles();
					 if (list!=null)
					 {
						 for(int m=0;m<list.length;m++)
						 {							 
							BookFiles.add(new ArrayList<String>());
							BookFiles.get(l).add(0,null);
							BookFiles.get(l).set(0, resultCourseBooks.getString("Book_id"));
							BookFiles.get(l).add(1,null);
							BookFiles.get(l).set(1, list[m].getName());
							String fileExtension = list[m].getName().substring(list[m].getName().indexOf(".")+1);
							BookFiles.get(l).add(2,null);							
							BookFiles.get(l).set(2, fileExtension);
							String real_File_Name = FileUploadManagement.getRealFileName("Books", "Book_id", resultCourseBooks.getInt("Book_id"), null, -1, list[m].getName());
							BookFiles.get(l).add(3,null);
							BookFiles.get(l).set(3, real_File_Name);
							int file_id = FileUploadManagement.getFile_id("Books", "Book_id", resultCourseBooks.getInt("Book_id"), null, -1, list[m].getName());
							BookFiles.get(l).add(4,null);
							BookFiles.get(l).set(4, String.valueOf(file_id));
							///////////////////////////Get file Category/////////////////////////////////////////////////////////////
							String file_Category = FileUploadManagement.getFile_Category(fileExtension);
							//////////////////////////////////////////////////////////////////////////////////////////////////////////							 
							BookFiles.get(l).add(5,null);
							BookFiles.get(l).set(5, file_Category);
							l=l+1;
						 }
					 }
					////////////////////////////////////////////////////////////////////////////////////////
				}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			
			///////////////////////////////////Get Course Lessons ///////////////////////////////////////////////////////////
				Date today = new Date();
		    	String sCourseLessons = "select * from Lessons where Unit_id in (select Unit_id from Course_Units where Course_id="+resultStudentCourses.getString("Course_id")+") and Lesson_Start <= '"+new java.sql.Date(today.getTime())+"' and Lesson_End >= '"+new java.sql.Date(today.getTime())+"'";
		    	ResultSet resultCourseLessons = statementCourseLessons.executeQuery(sCourseLessons);
				while (resultCourseLessons.next())
				{
					CourseLessons.add(new ArrayList<String>());
					CourseLessons.get(n).add(0,null);
					CourseLessons.get(n).set(0, resultStudentCourses.getString("Course_id"));
					CourseLessons.get(n).add(1,null);
					CourseLessons.get(n).set(1, resultCourseLessons.getString("Lesson_id"));
					CourseLessons.get(n).add(2,null);
					CourseLessons.get(n).set(2, resultCourseLessons.getString("Lesson_Name"));
					CourseLessons.get(n).add(3,null);
					CourseLessons.get(n).set(3, resultCourseLessons.getString("Lesson_Start"));
					CourseLessons.get(n).add(4,null);
					CourseLessons.get(n).set(4, resultCourseLessons.getString("Lesson_End"));
					CourseLessons.get(n).add(5,null);
					CourseLessons.get(n).set(5, resultCourseLessons.getString("Description"));
					n=n+1;
					//////////////////////////Retrieve the book files //////////////////////////////////////
					File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Lessons/"+resultCourseLessons.getString("Lesson_id"));
					File[] list = jsp.listFiles();
					 if (list!=null)
					 {
						 for(int k=0;k<list.length;k++)
						 {							 
							 LessonFiles.add(new ArrayList<String>());
							 LessonFiles.get(r).add(0,null);
							 LessonFiles.get(r).set(0, resultCourseLessons.getString("Lesson_id"));
							 LessonFiles.get(r).add(1,null);
							 LessonFiles.get(r).set(1, list[k].getName());
							 String fileExtension = list[k].getName().substring(list[k].getName().indexOf(".")+1);
							 LessonFiles.get(r).add(2,null);
							 LessonFiles.get(r).set(2, fileExtension);
							 String real_File_Name = FileUploadManagement.getRealFileName("Lessons", "Lesson_id", resultCourseLessons.getInt("Lesson_id"), null, -1, list[k].getName());
							 LessonFiles.get(r).add(3,null);
							 LessonFiles.get(r).set(3, real_File_Name);
							 int file_id = FileUploadManagement.getFile_id("Lessons", "Lesson_id", resultCourseLessons.getInt("Lesson_id"), null, -1, list[k].getName());
							 LessonFiles.get(r).add(4,null);
							 LessonFiles.get(r).set(4, String.valueOf(file_id));
							 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
							 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
							 //////////////////////////////////////////////////////////////////////////////////////////////////////////							 
							 LessonFiles.get(r).add(5,null);
							 LessonFiles.get(r).set(5, file_Category);
							 r=r+1;
						 }
					 }
					////////////////////////////////////////////////////////////////////////////////////////
				}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			}
			Course_Content.setCourseList(CourseList);
			Course_Content.setCourseContent(CourseContent);
			Course_Content.setCourseLessons(CourseLessons);
			Course_Content.setBookFiles(BookFiles);
			Course_Content.setLessonFiles(LessonFiles);
			Course_Content.setAccount_id(account_id);
			Course_Content.setSchool_id(school_id);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Course_Content;
		
	}
	
	public Course_Structure getLessons(int course_id){
		Course_Structure course_Structure = new Course_Structure();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementCourseName = connection.createStatement();
				Statement statementUnits = connection.createStatement();
				Statement statementLessons = connection.createStatement();){
			
			ArrayList<ArrayList<String>> Course_Structure_List = new ArrayList<ArrayList<String>>();
	
			///////Get Course Name ///////////////////////////////////////////////
	    	String sCourseName = "select course_name from Courses where course_id="+course_id;
			ResultSet resultCourseName = statementCourseName.executeQuery(sCourseName);
			String course_Name=null;
			while(resultCourseName.next())
			{
				course_Name = resultCourseName.getString("Course_Name");
			}
	    	/////////////////////////Get Units ///////////////////////////////////
	    	String sUnits = "select * from Course_Units where course_id="+course_id;
			ResultSet resultUnits = statementUnits.executeQuery(sUnits);
			int i=0;
			while(resultUnits.next())
			{
				/////////////////Get All lessons///////////////////////////
				
		    	String sLessons = "select * from Lessons where unit_id="+resultUnits.getInt("Unit_id");
				ResultSet resultLessons = statementLessons.executeQuery(sLessons);
				while(resultLessons.next())
				{					
					Course_Structure_List.add(new ArrayList<String>());
					Course_Structure_List.get(i).add(0,null);
					Course_Structure_List.get(i).set(0, String.valueOf(course_id));
					Course_Structure_List.get(i).add(1,null);
					Course_Structure_List.get(i).set(1, course_Name);
					Course_Structure_List.get(i).add(2,null);
					Course_Structure_List.get(i).set(2, resultUnits.getString("Unit_id"));
					Course_Structure_List.get(i).add(3,null);
					Course_Structure_List.get(i).set(3, resultUnits.getString("Unit_Name"));
					Course_Structure_List.get(i).add(4,null);
					Course_Structure_List.get(i).set(4, resultLessons.getString("Lesson_id"));
					Course_Structure_List.get(i).add(5,null);
					Course_Structure_List.get(i).set(5, resultLessons.getString("Lesson_Name"));
					Course_Structure_List.get(i).add(6,null);
					Course_Structure_List.get(i).set(6, resultLessons.getString("Lesson_Start"));
					Course_Structure_List.get(i).add(7,null);
					Course_Structure_List.get(i).set(7, resultLessons.getString("Lesson_End"));
					i=i+1;
				}
			}

			course_Structure.setCourse_Structure_List(Course_Structure_List);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_Structure;
		
	}
	
}
