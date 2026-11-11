package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import model.beans.admin.Authority_Report;
import model.beans.admin.Teacher_Details;
import model.beans.admin.Teacher_School_Grade;
import model.dbHelpers.DbConnector;

public class TeacherAuthorityManagement {

	public Teacher_School_Grade getTeacherAuthority(int teacher_id, int admin_id){
		Teacher_School_Grade Teacher_School_Grade = new Teacher_School_Grade();	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTS = connection.createStatement();
				Statement statementSchool_Grades = connection.createStatement();
				Statement statementTeacher_Grades = connection.createStatement();
				Statement statementTeacher_Classes = connection.createStatement();
				Statement statementTeacher_Content = connection.createStatement();
				Statement statementGrade_Classes = connection.createStatement();
				Statement statementGrade_Courses = connection.createStatement();
				Statement statementAssignedCourses = connection.createStatement();
				Statement statementCourse_Groups = connection.createStatement();
				Statement statementAssignedGroups = connection.createStatement();
				Statement statementTeacher_Courses = connection.createStatement();			
				Statement statementClassDivision = connection.createStatement();				
				Statement statementCourseInDivision = connection.createStatement();				
				Statement statementDivisionName = connection.createStatement();){

	    	String sTeacherSchools = "select * from school where school_id in (select school_id from teacher_school where teacher_id="+teacher_id+" order by School_id asc) and school_id in(select school_id from admin_school where Admin_id="+admin_id+")";
	    	ResultSet resultTS = statementTS.executeQuery(sTeacherSchools);
	    	ArrayList<String> School_id = new ArrayList<String>();
	    	ArrayList<String> Teacher_Grades = new ArrayList<String>();
	    	ArrayList<String> Teacher_Classes = new ArrayList<String>();
	    	ArrayList<String> Teacher_Content = new ArrayList<String>();
    		ArrayList<ArrayList<String>> Teacher_Courses = new ArrayList<ArrayList<String>>();
	    	ArrayList<String> School_Name = new ArrayList<String>();
	    	HashMap<String, ArrayList<ArrayList<String>>> Grademap = new HashMap<String, ArrayList<ArrayList<String>>>();
	    	HashMap<String, ArrayList<ArrayList<String>>> Classmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	    	HashMap<String, ArrayList<ArrayList<String>>> Coursemap = new HashMap<String, ArrayList<ArrayList<String>>>();
	    	HashMap<String, ArrayList<ArrayList<String>>> GradeCoursesmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	    	
	    	while(resultTS.next()) 
	        {
	    		School_id.add(resultTS.getString("School_id"));	
		    	School_Name.add(resultTS.getString("Name"));

		    	String sSchool_Grades = "select * from Grades where school_id ="+resultTS.getString("School_id")+" order by grade_index asc";
		    	ResultSet resultSchool_Grades = statementSchool_Grades.executeQuery(sSchool_Grades);
        		ArrayList<ArrayList<String>> GradeList = new ArrayList<ArrayList<String>>();
    			int i=0;
    			while(resultSchool_Grades.next()) 
		        {
    		    	GradeList.add(new ArrayList<String>());
    		    	GradeList.get(i).add(0,null);
	    			GradeList.get(i).set(0, resultSchool_Grades.getString("Grade_Name"));
	    			GradeList.get(i).add(1,null);
	    			GradeList.get(i).set(1, resultSchool_Grades.getString("Grade_id"));
	    			Grademap.put(resultTS.getString("School_id"), GradeList);
	    			i=i+1;
		        }
	        }
			
    	    String sTeacher_Grades = "select Grade_id from Teacher_Grades where Teacher_id ="+teacher_id;
    	    ResultSet resultTeacher_Grades = statementTeacher_Grades.executeQuery(sTeacher_Grades);	

        	String sTeacher_Classes = "select Class_id from Teacher_Classes where Teacher_id ="+teacher_id;
        	ResultSet resultTeacher_Classes = statementTeacher_Classes.executeQuery(sTeacher_Classes);	
			String Assigned=null;
			while(resultTeacher_Classes.next()) 
        	{
        		Teacher_Classes.add(resultTeacher_Classes.getString("Class_id"));
        	}   
					
        	String sTeacher_Content = "select Course_id from Teacher_Content where Teacher_id ="+teacher_id;
        	ResultSet resultTeacher_Content = statementTeacher_Content.executeQuery(sTeacher_Content);	
			while(resultTeacher_Content.next()) 
        	{
        		Teacher_Content.add(resultTeacher_Content.getString("Course_id"));
        	}    	    
			
    	    while(resultTeacher_Grades.next()) 
    	    {
    	    		Teacher_Grades.add(resultTeacher_Grades.getString("Grade_id"));
        	    	String sGrade_Classes = "select * from Classes where Grade_id ="+resultTeacher_Grades.getString("Grade_id");
        	    	ResultSet resultGrade_Classes = statementGrade_Classes.executeQuery(sGrade_Classes);	
            		ArrayList<ArrayList<String>> ClassList = new ArrayList<ArrayList<String>>();
        			int j=0;
        			while(resultGrade_Classes.next()) 
    		        {
        		    	ClassList.add(new ArrayList<String>());
        		    	ClassList.get(j).add(0,null);
        		    	ClassList.get(j).set(0, resultGrade_Classes.getString("Class_Name"));
        		    	ClassList.get(j).add(1,null);
        		    	ClassList.get(j).set(1, resultGrade_Classes.getString("Class_id"));
        		    	
        				///////////////////////////Get Division Name///////////////////////////////////////
        				String sDivisionName = "select * from divisions where division_id=(select division_id from classes where class_id="+resultGrade_Classes.getString("Class_id")+")";
        				ResultSet resultDivisionName = statementDivisionName.executeQuery(sDivisionName);
            			String division_Name = null;
            			while(resultDivisionName.next())
        				{
        					division_Name = resultDivisionName.getString("Division_Name");
        				}
        		    	ClassList.get(j).add(2,null);
        		    	ClassList.get(j).set(2, division_Name);
        				///////////////////////////////////////////////////////////////////////////////////
    	    			Classmap.put(resultTeacher_Grades.getString("Grade_id"), ClassList);
    	    			
    	    			j=j+1;
    		        }

        			

        			
        			
        			String sGrade_Courses = "select * from Courses where Grade_id ="+resultTeacher_Grades.getString("Grade_id");
        	    	ResultSet resultGrade_Courses = statementGrade_Courses.executeQuery(sGrade_Courses);	
        	    	ArrayList<ArrayList<String>> GradeCoursesList = new ArrayList<ArrayList<String>>();
        	    	ArrayList<ArrayList<String>> CourseList = new ArrayList<ArrayList<String>>();
        			int n=0;
        			int l=0;
        			while(resultGrade_Courses.next()) 
        			{
        				
        				GradeCoursesList.add(new ArrayList<String>());
        				GradeCoursesList.get(l).add(0,null);
        				GradeCoursesList.get(l).set(0, resultGrade_Courses.getString("Course_Name"));
        				GradeCoursesList.get(l).add(1,null);
        				GradeCoursesList.get(l).set(1, resultGrade_Courses.getString("Course_id"));
        				GradeCoursesmap.put(resultTeacher_Grades.getString("Grade_id"), GradeCoursesList);
    	    			l=l+1;
    	    			
    	    			////////////////////////////////////////////////////////////
            				
    					//Check if the course is already assigned to another teacher for the same class//
        				String Assigned_Class_id = null;
        				resultTeacher_Classes.beforeFirst();
    	    			while(resultTeacher_Classes.next()) 
    	            	{
                   	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
                	    	String sClassDivision = "select * from Classes where  Class_id="+ resultTeacher_Classes.getString("Class_id");
                			ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
                			int division_id = 0;
                			resultClassDivision.next();
                			division_id = resultClassDivision.getInt("Division_id");
                	    	////////////////////////////////////////////////////////////////////////////////////////////////////
                			boolean isCourseInDivision = false;
                			if (division_id!=0)
                	    	{  	
                				String sCourseInDivision = "select * from division_Courses where division_id="+division_id+" and course_id="+resultGrade_Courses.getString("Course_id");
                				ResultSet resultCourseInDivision = statementCourseInDivision.executeQuery(sCourseInDivision);
                				if(resultCourseInDivision.isBeforeFirst())
                				{
                					isCourseInDivision=true;
                				}             				
                	    	}
                			
                			if(division_id==0 || isCourseInDivision==true)
                			{
            	    			String sAssignedCourses = "select * from Teacher_Classes_Courses where Course_id="+resultGrade_Courses.getString("Course_id")+" and Teacher_id <>"+teacher_id+" and class_id="+resultTeacher_Classes.getString("Class_id");
            					ResultSet resultAssignedCourses = statementAssignedCourses.executeQuery(sAssignedCourses);
            					if (resultAssignedCourses.isBeforeFirst())
            					{
            						Assigned="1"; 
            						Assigned_Class_id = resultTeacher_Classes.getString("Class_id");        						
            					}
            					else
            					{
            						Assigned="0";

            					} 
      
            					///////////////////Check if the course has groups - Retrieve these groups - fill in the array ///////////
                    	    	String sCourse_Groups = "select * from "+"`Groups`"+" where Course_id="+resultGrade_Courses.getString("Course_id");
                    	    	ResultSet resultCourse_Groups = statementCourse_Groups.executeQuery(sCourse_Groups);	
                				if (resultCourse_Groups.isBeforeFirst())
                				{
                        	    	while (resultCourse_Groups.next())
                    				{
                    	    			String sAssignedGroups = "select * from Teacher_Classes_Courses where Course_id="+resultGrade_Courses.getString("Course_id")+" and Teacher_id <>"+teacher_id+" and class_id="+resultTeacher_Classes.getString("Class_id")+" and Group_id="+resultCourse_Groups.getString("Group_id");
                    					ResultSet resultAssignedGroups = statementAssignedGroups.executeQuery(sAssignedGroups);
                    					
                    					if (resultAssignedGroups.isBeforeFirst())
                    					{
                    						Assigned="1"; 
                    						Assigned_Class_id = resultTeacher_Classes.getString("Class_id");                						
                    					}
                    					else
                    					{
                    						Assigned="0";

                    					}                       			
                    					
                    					CourseList.add(new ArrayList<String>());
                        				CourseList.get(n).add(0,null);
                        				CourseList.get(n).set(0, resultGrade_Courses.getString("Course_Name"));
                        				CourseList.get(n).add(1,null);
                        				CourseList.get(n).set(1, resultGrade_Courses.getString("Course_id"));            				
                           				CourseList.get(n).add(2,null);
                        				CourseList.get(n).set(2, Assigned);  
                           				CourseList.get(n).add(3,null);
                        				CourseList.get(n).set(3, Assigned_Class_id);
                           				CourseList.get(n).add(4,null);
                        				CourseList.get(n).set(4, resultTeacher_Classes.getString("Class_id"));
                           				CourseList.get(n).add(5,null);
                        				CourseList.get(n).set(5, resultCourse_Groups.getString("Group_id"));
                           				CourseList.get(n).add(6,null);
                        				CourseList.get(n).set(6, resultCourse_Groups.getString("Group_Name"));
                        				                       				
                        				Coursemap.put(resultTeacher_Grades.getString("Grade_id"), CourseList);
                    	    			n=n+1;
                    				} 
                				}

                				////////////////////////////////////////////////////////////////////////////////////////////////////////
                				else
                				{
                       				CourseList.add(new ArrayList<String>());
                    				CourseList.get(n).add(0,null);
                    				CourseList.get(n).set(0, resultGrade_Courses.getString("Course_Name"));
                    				CourseList.get(n).add(1,null);
                    				CourseList.get(n).set(1, resultGrade_Courses.getString("Course_id"));            				
                       				CourseList.get(n).add(2,null);
                    				CourseList.get(n).set(2, Assigned);  
                       				CourseList.get(n).add(3,null);
                    				CourseList.get(n).set(3, Assigned_Class_id);
                       				CourseList.get(n).add(4,null);
                    				CourseList.get(n).set(4, resultTeacher_Classes.getString("Class_id"));
                      				/////////////////////Added when working on Teacher module ///////
                    				CourseList.get(n).add(5,null);
                    				CourseList.get(n).set(5, null);
                    				
                      				CourseList.get(n).add(6,null);
                    				CourseList.get(n).set(6, null);
                    				//////////////////////////////////////////////////////////////////////
                    				
                    				Coursemap.put(resultTeacher_Grades.getString("Grade_id"), CourseList);
                	    			n=n+1;	
                				}                				
                			}
    	            	}   	    			 
        			} 
    	    }

            String sTeacher_Courses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id;
            ResultSet resultTeacher_Courses = statementTeacher_Courses.executeQuery(sTeacher_Courses);	
            int m=0;
            while(resultTeacher_Courses.next()) 
            {
            	    		
            	Teacher_Courses.add(new ArrayList<String>());
            	Teacher_Courses.get(m).add(0,null);
            	Teacher_Courses.get(m).set(0, resultTeacher_Courses.getString("Class_id"));
            	Teacher_Courses.get(m).add(1,null);
            	Teacher_Courses.get(m).set(1, resultTeacher_Courses.getString("Course_id"));
            	Teacher_Courses.get(m).add(2,null);
            	Teacher_Courses.get(m).set(2, resultTeacher_Courses.getString("Group_id"));
        	    m=m+1;
            }	
			
	    	Teacher_School_Grade.setSchool_id(School_id);
	    	Teacher_School_Grade.setSchool_Name(School_Name);
			Teacher_School_Grade.setTeacher_Classes(Teacher_Classes);
			Teacher_School_Grade.setTeacher_Content(Teacher_Content);
			Teacher_School_Grade.setGrademap(Grademap);
			Teacher_School_Grade.setClassmap(Classmap);
			Teacher_School_Grade.setTeacher_Grades(Teacher_Grades);
			Teacher_School_Grade.setGradeCoursesmap(GradeCoursesmap);
   			Teacher_School_Grade.setCoursemap(Coursemap);
   			Teacher_School_Grade.setTeacher_Courses(Teacher_Courses);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teacher_School_Grade;
	}
	
	public String manageTeacherGrades(int teacher_id, HttpServletRequest request){
		int updateQuery = 0;
		String message="";
		try (Connection connection = DbConnector.connectToDb();
				Statement statementS = connection.createStatement();
				Statement statementG = connection.createStatement();
				Statement statementExistingGrades = connection.createStatement();){
	
			String sSchool_Id= "select school_id, name from school where school_id IN (select school_id from teacher_school where teacher_id ="+ teacher_id +") order by School_id ";
			ResultSet resultS = statementS.executeQuery(sSchool_Id);
			PreparedStatement pstatement = null;	
			while (resultS.next())
			{

				int school_id = 0; 
				if(request.getParameter("school"+resultS.getString("school_id"))!=null)
				{
					school_id = Integer.parseInt(request.getParameter("school"+resultS.getString("school_id")));
				}
				
				if (school_id !=0)
				{

					String gSchool_Id= "select * from Grades where school_id="+school_id+" order by Grade_Index ";
					ResultSet resultG = statementG.executeQuery(gSchool_Id);
					while (resultG.next())
					{
						int grade_id = 0;
						if(request.getParameter("grade"+resultG.getString("grade_id"))!=null)
						{
							grade_id = Integer.parseInt(request.getParameter("grade"+resultG.getString("grade_id")));
						}		
						if (grade_id != 0)
						{

							//Check if the Grade is assigned before for this teacher///
							String sExistingGrades = "select Grade_id from Teacher_Grades where Teacher_id ="+teacher_id+" and Grade_id="+grade_id;
							ResultSet resultExistingGrades = statementExistingGrades.executeQuery(sExistingGrades);
							////////////////
							if (!resultExistingGrades.isBeforeFirst())
							{							
								String queryTeacher_Grades = "INSERT INTO Teacher_Grades (Teacher_id, Grade_id) VALUES (?, ?)";
								pstatement = connection.prepareStatement(queryTeacher_Grades);
								pstatement.setInt(1, teacher_id);
								pstatement.setInt(2, grade_id);
								updateQuery = pstatement.executeUpdate();
								if (updateQuery != 0)
								{	
									message= message+ "Grade <b>"+resultG.getString("Grade_Name")+"</b> assigned to the teacher"+"<br/>";
							 	}
								pstatement.close();
							}
						}
						else
						{
							//Delete
							int hidden_Grade_id = 0;
							if(request.getParameter("hiddengrade"+resultG.getString("grade_id"))!=null)
							{
								hidden_Grade_id = Integer.parseInt(request.getParameter("hiddengrade"+resultG.getString("grade_id")));
							}	
							String sExistingGrades = "select Grade_id from Teacher_Grades where Teacher_id ="+teacher_id+" and Grade_id="+hidden_Grade_id;
							ResultSet resultExistingGrades = statementExistingGrades.executeQuery(sExistingGrades);
							if (resultExistingGrades.isBeforeFirst())
							{
								String sDeleteExistingGrade = "Delete from Teacher_Grades where Teacher_id ="+teacher_id+" and Grade_id="+hidden_Grade_id;
								PreparedStatement psDeleteExistingGrade = null;
								psDeleteExistingGrade = connection.prepareStatement(sDeleteExistingGrade);
								updateQuery = psDeleteExistingGrade.executeUpdate();
								if(updateQuery!=0)
								{
									message= message+ "Grade <b>"+resultG.getString("Grade_Name")+"</b> removed from the teacher"+"<br/>";
									String sDeleteExistingClass = "Delete from Teacher_Classes where Teacher_id ="+teacher_id+" and class_id in (select class_id from classes where grade_id="+hidden_Grade_id+")";
									pstatement = connection.prepareStatement(sDeleteExistingClass);
									pstatement.executeUpdate();
									String sDeleteExistingCourse = "Delete from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id in (select class_id from classes where grade_id="+hidden_Grade_id+")";
									pstatement = connection.prepareStatement(sDeleteExistingCourse);
									pstatement.executeUpdate();
									
									String sSchedule = "update Schedule set Teacher_id=? where class_id in (select class_id from classes where grade_id ="+hidden_Grade_id+") and Teacher_id ="+teacher_id;
									pstatement = connection.prepareStatement(sSchedule);
									pstatement.setNull(1, java.sql.Types.NULL);
									pstatement.executeUpdate();
									
									String sDeleteExistingCourseContent = "Delete from Teacher_Content where Teacher_id ="+teacher_id+" and Course_id in (select course_id from courses where grade_id="+hidden_Grade_id+")";
									pstatement = connection.prepareStatement(sDeleteExistingCourseContent);
									pstatement.executeUpdate();
								}
								pstatement.close();				
								psDeleteExistingGrade.close();
							}
						}						
					}
				}
			}
			if(updateQuery==0)
			{
				message = "No Changes";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public String manageTeacherClasses(int teacher_id, HttpServletRequest request){
		int updateQuery = 0;
		String message="";
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeacherGrades = connection.createStatement();
				Statement statementClasses = connection.createStatement();
				Statement statementExistingClasses = connection.createStatement();){
		
			String sTeacher_Grades = "select Grade_id, Grade_name from Grades where Grade_id In(select Grade_id from teacher_Grades where teacher_id="+teacher_id+")" ;
			ResultSet resultTeacherGrades = statementTeacherGrades.executeQuery(sTeacher_Grades);				
			while (resultTeacherGrades.next())
			{
				String sClasses = "select Class_id, Class_Name from Classes where grade_id ="+resultTeacherGrades.getString("grade_id");				
				ResultSet resultClasses = null;
				resultClasses = statementClasses.executeQuery(sClasses);
				while (resultClasses.next())
				{
					int class_id = 0;
					if(request.getParameter("Class"+resultClasses.getString("Class_id"))!=null)
					{
						class_id = Integer.parseInt(request.getParameter("Class"+resultClasses.getString("Class_id")));
					}
					int hidden_Class_id = 0;
					if(request.getParameter("hiddenClass"+resultClasses.getString("class_id"))!=null)
					{
						hidden_Class_id = Integer.parseInt(request.getParameter("hiddenClass"+resultClasses.getString("class_id")));
					}
					if (class_id != 0)
					{
						String sExistingClasses = "select Class_id from Teacher_Classes where Teacher_id ="+teacher_id+" and class_id="+class_id;
						ResultSet resultExistingClasses = statementExistingClasses.executeQuery(sExistingClasses);
						if (!resultExistingClasses.isBeforeFirst())
						{
							String queryTeacher_Classes = "INSERT INTO Teacher_Classes (Teacher_id, class_id) VALUES (?,?)";
							PreparedStatement pstatement = connection.prepareStatement(queryTeacher_Classes);
							pstatement.setInt(1, teacher_id);
							pstatement.setInt(2, class_id);
							updateQuery = pstatement.executeUpdate();
							if (updateQuery != 0)
							{	
								message= message+ "Class <b>"+resultClasses.getString("Class_Name")+"</b> assigned to the teacher"+"<br>";
							}
							pstatement.close();
						}
					}
					else
					{
						//Delete	
						String sExistingClasses = "select Class_id from Teacher_Classes where Teacher_id ="+teacher_id+" and class_id="+hidden_Class_id;
						ResultSet resultExistingClasses = statementExistingClasses.executeQuery(sExistingClasses);
						if (resultExistingClasses.isBeforeFirst())
						{
							String sDeleteExistingClass = "Delete from Teacher_Classes where Teacher_id ="+teacher_id+" and class_id="+hidden_Class_id;
							PreparedStatement pstatement = connection.prepareStatement(sDeleteExistingClass);;
							updateQuery = pstatement.executeUpdate();
							if (updateQuery != 0)
							{
								message= message+ "Class <b>"+resultClasses.getString("Class_Name")+"</b> removed from the teacher"+"<br>";
								String sDeleteExistingCourse = "Delete from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+hidden_Class_id;
								pstatement = connection.prepareStatement(sDeleteExistingCourse);
								pstatement.executeUpdate();
								
								String sSchedule = "update Schedule set Teacher_id=? where class_id="+hidden_Class_id+" and Teacher_id ="+teacher_id;
								pstatement = connection.prepareStatement(sSchedule);
								pstatement.setNull(1, java.sql.Types.NULL);
								pstatement.executeUpdate();
								
							}
							pstatement.close();
						}
					}
				}										
			}
			if(updateQuery==0)
			{
				message = "No Changes";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public String manageTeacherCourses(int teacher_id, HttpServletRequest request){
		int updateQuery = 0;
		String message="";
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTC = connection.createStatement();
				Statement statementCG = connection.createStatement();
				Statement statementCo = connection.createStatement();
				Statement statementGroupCourses = connection.createStatement();
				Statement statementExistingCourses = connection.createStatement();
				Statement statementSessionsInConflict = connection.createStatement();
				Statement statementSessionTime = connection.createStatement();
				Statement statementSessionDay = connection.createStatement();
				Statement statementClassName = connection.createStatement();
				Statement statementUnassignedSessions = connection.createStatement();){
		
			String sTeacher_Classes = "select class_id, class_name from classes where class_id In(select class_id from teacher_classes where teacher_id="+teacher_id+")" ;
			ResultSet resultTC = statementTC.executeQuery(sTeacher_Classes);
			PreparedStatement pstatement = null;									
			while (resultTC.next())
			{

				String Class_id = null; 
				Class_id = request.getParameter("class"+resultTC.getString("class_id"));
				String sClassName = "select * from Classes where class_id="+Class_id;
				ResultSet resultClassName = statementClassName.executeQuery(sClassName);
				String class_Name = null;
				while(resultClassName.next())
				{
					class_Name = resultClassName.getString("Class_Name");
				}
				if (Class_id !=null)
				{
					String sClass_Grade = "select grade_id, grade_name from grades where grade_id In(select grade_id from classes where class_id="+Class_id+")";
					ResultSet resultCG = statementCG.executeQuery(sClass_Grade);
					while (resultCG.next())
					{
						String sCourses = "select Course_id, Course_Name from Courses where grade_id ="+resultCG.getString("grade_id");				
						ResultSet resultCo = statementCo.executeQuery(sCourses);
						while (resultCo.next())
						{
							String sGroupCourses = "select * from "+"`Groups`"+" where Course_id ="+resultCo.getString("course_id");				
							ResultSet resultGroupCourses = statementGroupCourses.executeQuery(sGroupCourses);
							if (resultGroupCourses.isBeforeFirst())
							{
								while (resultGroupCourses.next())
								{
									String CG = request.getParameter("course"+resultCo.getString("course_id")+"Group"+resultGroupCourses.getString("Group_id")+"Class"+Class_id);
									if (CG!=null)
									{
										String Course_id = null; 
										String Group_id = null; 
										String[] parts = CG.split(",");
										Course_id = parts[0]; 
										Group_id = parts[1]; 
										Course_id=Course_id.substring(1, Course_id.length()-1);
										Group_id=Group_id.substring(1, Group_id.length()-1);	
										if (Group_id != null)
										{
											String sExistingCourses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+Course_id+" and Group_id="+Group_id;
											ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
											
											if (!resultExistingCourses.isBeforeFirst())
											{
												String sTeacher_Classes_Courses = "INSERT INTO Teacher_Classes_Courses (Teacher_id, class_id, course_id, group_id) VALUES (?,?, ?,?)";
												pstatement = connection.prepareStatement(sTeacher_Classes_Courses);
												pstatement.setInt(1, teacher_id);
												pstatement.setString(2, Class_id);
												pstatement.setString(3, Course_id);
												pstatement.setString(4, Group_id);
												updateQuery = pstatement.executeUpdate();
												if (updateQuery != 0)
												{	
													message= message+ "<div class='courseToTeacher'>Course <b>"+resultCo.getString("Course_Name")+" "+resultGroupCourses.getString("Group_Name")+"</b> for class <b>"+class_Name+" </b>assigned to the teacher"+"<br><br>";
													String sUnassignedSessions = "select * from schedule where class_id="+Class_id+" and Course_id="+Course_id+" and group_id="+Group_id+" and teacher_id is null"; 
													ResultSet resultUnassignedSessions = statementUnassignedSessions.executeQuery(sUnassignedSessions);
													int atLeasetOneSessionDeleted = 0;
													while(resultUnassignedSessions.next())
													{
														String sSessionsInConflict = "select * from Schedule where Teacher_id="+teacher_id+" and session_id="+resultUnassignedSessions.getString("Session_id")+" and day_id="+resultUnassignedSessions.getString("day_id")+" and class_id<>"+Class_id;
														ResultSet resultsSessionInConflict = statementSessionsInConflict.executeQuery(sSessionsInConflict);
														int deleteFromSchedule = 0;
														
														if(resultsSessionInConflict.isBeforeFirst())
														{
															resultsSessionInConflict.next();
															String sDeleteCourseSessions = "Delete from Schedule where session_id = "+resultsSessionInConflict.getInt("Session_id")+" and day_id="+resultsSessionInConflict.getInt("day_id")+" and class_id="+Class_id+" and Course_id="+Course_id+" and Group_id="+Group_id;
															pstatement = connection.prepareStatement(sDeleteCourseSessions);
															deleteFromSchedule = pstatement.executeUpdate();
															String sSessionTime = "select * from grade_sessions where session_id="+resultsSessionInConflict.getInt("Session_id");
															ResultSet resultSessionTime = statementSessionTime.executeQuery(sSessionTime);
															String session_From = null;
															String session_To = null;
															while (resultSessionTime.next())
															{
																session_From = resultSessionTime.getString("Time_From");
																session_To = resultSessionTime.getString("Time_To");
															}
															String sSessionDay = "select * from week_Days where day_id="+resultsSessionInConflict.getInt("Day_id");
															ResultSet resultSessionDay = statementSessionDay.executeQuery(sSessionDay);
															String day_Name = null;
															while(resultSessionDay.next())
															{
																day_Name = resultSessionDay.getString("Day_Name");
															}
															if(deleteFromSchedule!=0)
															{
																message = message + "Session <b>"+session_From+ "-" +session_To +"</b> in <b>"+day_Name+"</b> has been removed from the class<b> "+class_Name+" </b>schedule since this teacher is busy in the same time in another class<br><br>";
																atLeasetOneSessionDeleted = 1;
															}
														}
													}
													String sSchedule = "update Schedule set Teacher_id=? where Class_id="+Class_id+" and Teacher_id is null and Course_id="+Course_id+" and Group_id="+Group_id;
													pstatement = connection.prepareStatement(sSchedule);
													pstatement.setInt(1, teacher_id);
													int updateSchedule  = pstatement.executeUpdate();
													if(updateSchedule!=0)
													{
														if(atLeasetOneSessionDeleted==1)
														{
															message = message +"The rest of the ";
														}
														message = message +"Class Schedule sessions have been updated automatically<br><br>";
													}
													message = message + "</div>";
												}
												pstatement.close();
											}
										}

									}
									else
									{
										//Delete
										String hCG = request.getParameter("hiddencourse"+resultCo.getString("course_id")+"hiddenGroup"+resultGroupCourses.getString("Group_id")+"hiddenClass"+Class_id);
										String hidden_Course_id = null; 
										String hidden_Group_id = null; 
										if (hCG!=null)
										{
											String[] hparts = hCG.split(",");
											hidden_Course_id = hparts[0]; 
											hidden_Group_id = hparts[1]; 
											hidden_Course_id=hidden_Course_id.substring(1, hidden_Course_id.length()-1);
											hidden_Group_id=hidden_Group_id.substring(1, hidden_Group_id.length()-1);													
										}
										String sExistingCourses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+hidden_Course_id+" and Group_id="+hidden_Group_id;
										ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
										if (resultExistingCourses.isBeforeFirst())
										{
											String sDeleteExistingCourse = "Delete from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+hidden_Course_id+" and Group_id="+hidden_Group_id;
											pstatement = connection.prepareStatement(sDeleteExistingCourse);
											updateQuery = pstatement.executeUpdate();
											
											if(updateQuery!=0)
											{
												message= message+ "<div class='courseToTeacher'>Course <b>"+resultCo.getString("Course_Name")+" "+resultGroupCourses.getString("Group_Name")+"</b> removed from the teacher"+"<br>";
												String sSchedule = "update Schedule set Teacher_id=? where Class_id="+Class_id+" and Teacher_id ="+teacher_id+" and Course_id="+hidden_Course_id+" and Group_id="+hidden_Group_id;
												pstatement = connection.prepareStatement(sSchedule);
												pstatement.setNull(1, java.sql.Types.NULL);
												int updateSchedule = pstatement.executeUpdate();
												if(updateSchedule!=0)
												{
													message = message +"Class Schedule sessions have been updated automatically<br>";
												}										
												message=message+"</div>";
											}
										}
	
									}

								}
							}
							else
							{
								String CG = request.getParameter("course"+resultCo.getString("course_id")+"Group"+"Class"+Class_id);
								if (CG!=null)
								{
									String Course_id = null; 
									String Group_id = null; 
									String[] parts = CG.split(",");
									Course_id = parts[0]; 
									Group_id = parts[1]; 
									Course_id=Course_id.substring(1, Course_id.length()-1);
									Group_id=Group_id.substring(1, Group_id.length()-1);
									
									String sExistingCourses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+Course_id;
									ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
									
									if (!resultExistingCourses.isBeforeFirst())
									{
										String sTeacher_Classes_Courses = "INSERT INTO Teacher_Classes_Courses (Teacher_id, class_id, course_id) VALUES (?,?, ?)";
										pstatement = connection.prepareStatement(sTeacher_Classes_Courses);
										pstatement.setInt(1, teacher_id);
										pstatement.setString(2, Class_id);
										pstatement.setString(3, Course_id);
										updateQuery = pstatement.executeUpdate();
										if (updateQuery != 0)
										{	
											message= message+ "<div class='courseToTeacher'>Course <b>"+resultCo.getString("Course_Name")+"</b> for class <b>"+class_Name+" </b>assigned to the teacher"+"<br><br>";
											String sUnassignedSessions = "select * from schedule where class_id="+Class_id+" and Course_id="+Course_id+" and teacher_id is null"; 
											ResultSet resultUnassignedSessions = statementUnassignedSessions.executeQuery(sUnassignedSessions);
											int atLeasetOneSessionDeleted = 0;
											while(resultUnassignedSessions.next())
											{
												String sSessionsInConflict = "select * from Schedule where Teacher_id="+teacher_id+" and session_id="+resultUnassignedSessions.getString("Session_id")+" and day_id="+resultUnassignedSessions.getString("day_id")+" and class_id<>"+Class_id;
												ResultSet resultsSessionInConflict = statementSessionsInConflict.executeQuery(sSessionsInConflict);
												int deleteFromSchedule = 0;
												
												if(resultsSessionInConflict.isBeforeFirst())
												{
													resultsSessionInConflict.next();
													String sDeleteCourseSessions = "Delete from Schedule where session_id = "+resultsSessionInConflict.getInt("Session_id")+" and day_id="+resultsSessionInConflict.getInt("day_id")+" and class_id="+Class_id+" and Course_id="+Course_id;
													pstatement = connection.prepareStatement(sDeleteCourseSessions);
													deleteFromSchedule = pstatement.executeUpdate();
													String sSessionTime = "select * from grade_sessions where session_id="+resultsSessionInConflict.getInt("Session_id");
													ResultSet resultSessionTime = statementSessionTime.executeQuery(sSessionTime);
													String session_From = null;
													String session_To = null;
													while (resultSessionTime.next())
													{
														session_From = resultSessionTime.getString("Time_From");
														session_To = resultSessionTime.getString("Time_To");
													}
													String sSessionDay = "select * from week_Days where day_id="+resultsSessionInConflict.getInt("Day_id");
													ResultSet resultSessionDay = statementSessionDay.executeQuery(sSessionDay);
													String day_Name = null;
													while(resultSessionDay.next())
													{
														day_Name = resultSessionDay.getString("Day_Name");
													}
													if(deleteFromSchedule!=0)
													{
														message = message + "Session <b>"+session_From+ "-" +session_To +"</b> in <b>"+day_Name+"</b> has been removed from the class<b> "+class_Name+" </b>schedule since this teacher is busy in the same time in another class<br><br>";
														atLeasetOneSessionDeleted = 1;
													}
												}
											}
											String sSchedule = "update Schedule set Teacher_id=? where Class_id="+Class_id+" and Teacher_id is null and Course_id="+Course_id;
											pstatement = connection.prepareStatement(sSchedule);
											pstatement.setInt(1, teacher_id);
											int updateSchedule  = pstatement.executeUpdate();
											if(updateSchedule!=0)
											{
												if(atLeasetOneSessionDeleted==1)
												{
													message = message +"The rest of the ";
												}
												message = message +"Class Schedule sessions have been updated automatically<br><br>";
											}
											message = message + "</div>";
										}
										pstatement.close();
									}
								}
								else
								{
									String hCG = request.getParameter("hiddencourse"+resultCo.getString("course_id")+"hiddenGroup"+"hiddenClass"+Class_id);
									String hidden_Course_id = null; 
									String hidden_Group_id = null; 
									if (hCG!=null)
									{
										String[] hparts = hCG.split(",");
										hidden_Course_id = hparts[0]; 
										hidden_Group_id = hparts[1]; 
										hidden_Course_id=hidden_Course_id.substring(1, hidden_Course_id.length()-1);
										hidden_Group_id=hidden_Group_id.substring(1, hidden_Group_id.length()-1);
									}
									
									String sExistingCourses = "select * from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+hidden_Course_id;
									ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
									if(resultExistingCourses.isBeforeFirst())
									{
										String sDeleteExistingCourse = "Delete from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id="+Class_id+" and Course_id="+hidden_Course_id;
										pstatement = connection.prepareStatement(sDeleteExistingCourse);
										updateQuery = pstatement.executeUpdate();
										if (updateQuery != 0)
										{	
											message= message+ "<div class='courseToTeacher'>Course <b>"+resultCo.getString("Course_Name")+"</b> for class <b>"+class_Name+" </b> removed from the teacher"+"<br>";
											String sSchedule = "update Schedule set Teacher_id=? where Class_id="+Class_id+" and Teacher_id ="+teacher_id+" and Course_id="+hidden_Course_id;
											pstatement = connection.prepareStatement(sSchedule);
											pstatement.setNull(1, java.sql.Types.NULL);
											int updateSchedule = pstatement.executeUpdate();
											if(updateSchedule!=0)
											{
												message = message +"Class Schedule sessions have been updated automatically<br>";
											}										
											message=message+"</div>";
										}
									}

								}

							}
						}
					}

				}
											
			}
			if(updateQuery==0)
			{
				message = "No Changes";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}	
	public String manageTeacherContent(int teacher_id, HttpServletRequest request){
		int updateQuery = 0;
		String message="";
		try (Connection connection = DbConnector.connectToDb();
				Statement statementTeacherGrades = connection.createStatement();
				Statement statementCourses = connection.createStatement();
				Statement statementExistingCourses = connection.createStatement();){
		
			String sTeacher_Grades = "select Grade_id, Grade_name from Grades where Grade_id In(select Grade_id from teacher_Grades where teacher_id="+teacher_id+")" ;
			ResultSet resultTeacherGrades = statementTeacherGrades.executeQuery(sTeacher_Grades);
			PreparedStatement pstatement = null;	 			
			while (resultTeacherGrades.next())
			{
				String sCourses = "select Course_id, Course_Name from Courses where grade_id ="+resultTeacherGrades.getInt("grade_id");				
				ResultSet resultCourses = null;
				resultCourses = statementCourses.executeQuery(sCourses);
				while (resultCourses.next())
				{
					int course_id = 0;
					if(request.getParameter("course"+resultCourses.getString("Course_id"))!=null)
					{
						course_id = Integer.parseInt(request.getParameter("course"+resultCourses.getString("Course_id")));
					}
					int hidden_Course_id = 0;
					if(request.getParameter("hiddencourse"+resultCourses.getString("Course_id"))!=null)
					{
						hidden_Course_id = Integer.parseInt(request.getParameter("hiddencourse"+resultCourses.getString("Course_id")));
					}
					if (course_id != 0)
					{
						String sExistingCourses = "select Course_id from Teacher_Content where Teacher_id ="+teacher_id+" and Course_id="+course_id;
						ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
						if (!resultExistingCourses.isBeforeFirst())
						{
							String queryTeacher_Content = "INSERT INTO Teacher_Content (Teacher_id, Course_id) VALUES (?,?)";
							pstatement = connection.prepareStatement(queryTeacher_Content);
							pstatement.setInt(1, teacher_id);
							pstatement.setInt(2, course_id);
							updateQuery = pstatement.executeUpdate();
							if (updateQuery != 0)
							{	
								message= message+ "Course Content Authority for <b>"+resultCourses.getString("Course_Name")+"</b> assigned to the teacher"+"<br>";
							}
							pstatement.close();
						}
					}
					else
					{
						//Delete
						String sExistingCourses = "select Course_id from Teacher_Content where Teacher_id ="+teacher_id+" and Course_id="+hidden_Course_id;
						ResultSet resultExistingCourses = statementExistingCourses.executeQuery(sExistingCourses);
						if (resultExistingCourses.isBeforeFirst())
						{
							String sDeleteExistingCourse = "Delete from Teacher_Content where Teacher_id ="+teacher_id+" and Course_id="+hidden_Course_id;
							pstatement = connection.prepareStatement(sDeleteExistingCourse);
							updateQuery = pstatement.executeUpdate();
							if (updateQuery != 0)
							{
								message= message+ "Course Content Authority for <b>"+resultCourses.getString("Course_Name")+"</b> removed from the teacher"+"<br>";
							}
							pstatement.close();
						}
					}
				}										
			}
			if(updateQuery==0)
			{
				message = "No Changes";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public Authority_Report getAuthorityPerGrade(int grade_id, int param_course_id, int param_class_id, int student_id){
		Authority_Report authority_Report = new Authority_Report();	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGradeCourses = connection.createStatement();
				Statement statementClassDivision = connection.createStatement();
				Statement statementGradeClasses = connection.createStatement();
				Statement statementAssignedTeachers = connection.createStatement();
				Statement statementTeacherContent = connection.createStatement();
				Statement statementStudentGroup = connection.createStatement();){
		String sGradeCourses=null;
		if(param_course_id!=0)
		{
			sGradeCourses = "select * from courses where course_id="+param_course_id;
		}
		else
		{

	    	/////////////////////////Check if the Class is linked to Division ///////////////////////////////////
			int division_id = 0;
			if(student_id!=0)
	    	{
				String sClassDivision = "select * from Classes where  Class_id="+param_class_id;
				ResultSet resultClassDivision = statementClassDivision.executeQuery(sClassDivision);
				while(resultClassDivision.next())
				{
					division_id = resultClassDivision.getInt("Division_id");
				}	
	    	}		
	    	////////////////////////////////////////////////////////////////////////////////////////////////////			
			if (division_id==0)
	    	{	
				if(student_id==0)
				{
					sGradeCourses ="select * from courses where grade_id="+grade_id;
				}
				else
				{
					sGradeCourses = "select * from courses where "
			    	    	+ "((grade_id ="+grade_id+" and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id="+grade_id+")))";
					
				}
	    	}
	    	else
	    	{
	    		if(student_id==0)
	    		{
		    		sGradeCourses = "select * from courses where ((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+"))";    			
	    		}
	    		else
	    		{
		    		sGradeCourses = "select * from courses where "
			    	    	+ "((Course_id in (select Course_id from Division_Courses where Division_id="+division_id+") and Elective=0)"
			    	    	+ " or Course_id in(Select Course_id from Student_Electives where Student_id="+student_id+" and course_id in(select course_id from courses where grade_id="+grade_id+")))";
	    			
	    		}
	    	}			
			
		}
		ResultSet resultGradeCourses = statementGradeCourses.executeQuery(sGradeCourses);
		
		String sGradeClasses=null;
		if(param_class_id!=0)
		{
			sGradeClasses = "select * from classes where class_id="+param_class_id;
		}
		else
		{
			sGradeClasses = "select * from classes where grade_id="+grade_id;
		}
		ResultSet resultGradeClasses = statementGradeClasses.executeQuery(sGradeClasses);
		int course_id = 0;
		String course_Name=null;
		int class_id = 0;
		String class_Name=null;
		ArrayList<Integer> Course_id = new ArrayList<Integer>();
		ArrayList<String> Course_Name = new ArrayList<String>();
		ArrayList<Integer> Class_id = new ArrayList<Integer>();
		ArrayList<String> Class_Name = new ArrayList<String>();		
		ArrayList<ArrayList<String>> Class_Authority_List = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> Content_Authority_List = new ArrayList<ArrayList<String>>();
		int i=0;
		int m=0;
		while(resultGradeCourses.next())
		{
			course_id=resultGradeCourses.getInt("Course_id");
			course_Name = resultGradeCourses.getString("Course_Name");
			Course_id.add(course_id);
			Course_Name.add(course_Name);
			String sStudentGroup = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultStudentGroup = statementStudentGroup.executeQuery(sStudentGroup);
			int group_id=0;
			String group_Name = null;
			while(resultStudentGroup.next())
			{
				group_id = resultStudentGroup.getInt("group_id");
				group_Name = resultStudentGroup.getString("group_name");
			}
			resultGradeClasses.beforeFirst();
			while(resultGradeClasses.next())
			{
				class_id = resultGradeClasses.getInt("Class_id");
				class_Name = resultGradeClasses.getString("Class_Name");
				Class_id.add(class_id);
				Class_Name.add(class_Name);
				String sAssignedTeachers = "select * from Teacher_Classes_Courses where Course_id="+course_id+" and (class_id="+class_id+" or group_id="+group_id+")";
				ResultSet resultAssignedTeachers = statementAssignedTeachers.executeQuery(sAssignedTeachers);
				int class_teacher_id=0;
				while(resultAssignedTeachers.next())
				{
					class_teacher_id=resultAssignedTeachers.getInt("Teacher_id");   
				}
				Class_Authority_List.add(new ArrayList<String>());
				Class_Authority_List.get(i).add(0,null);
				Class_Authority_List.get(i).set(0, String.valueOf(course_id));
				Class_Authority_List.get(i).add(1,null);
				Class_Authority_List.get(i).set(1, String.valueOf(class_id));
				Class_Authority_List.get(i).add(2,null);
				Class_Authority_List.get(i).set(2, class_Name);
				Class_Authority_List.get(i).add(3,null);
				Class_Authority_List.get(i).add(4,null);
				if(class_teacher_id!=0)
				{
					Class_Authority_List.get(i).set(3, String.valueOf(class_teacher_id));
					TeacherManagement teacerManagement = new TeacherManagement();
					Teacher_Details teacher_Details = new Teacher_Details();
					teacher_Details = teacerManagement.getTeacher(class_teacher_id);
					String First_Name = teacher_Details.getFirst_Name();
					String Last_Name = teacher_Details.getLast_Name();
					String gender = teacher_Details.getGender();
					String title = "";
					if(gender!=null && gender.equals("Male"))
					{
						title = "Mr.";
					}
					else
					{
						title = "Ms.";
					}
					String Teacher_Name = title + " " + First_Name+" " + Last_Name;
					Class_Authority_List.get(i).set(4, Teacher_Name);
					Class_Authority_List.get(i).add(5,null);
					Class_Authority_List.get(i).set(5, String.valueOf(teacher_Details.getAccount_id()));
					Class_Authority_List.get(i).add(6,null);
					Class_Authority_List.get(i).set(6, teacher_Details.getFileName());
					
				}					
				else
				{					
					Class_Authority_List.get(i).set(3, null);
					Class_Authority_List.get(i).set(4, "Not Assigned");
					Class_Authority_List.get(i).add(5,null);
					Class_Authority_List.get(i).set(5, null);
					Class_Authority_List.get(i).add(6,null);
					Class_Authority_List.get(i).set(6, null);
				}
				if(group_id!=0)
				{
					Class_Authority_List.get(i).add(7,null);
					Class_Authority_List.get(i).set(7, String.valueOf(group_id));
					Class_Authority_List.get(i).add(8,null);
					Class_Authority_List.get(i).set(8, group_Name);
				}
				else
				{
					Class_Authority_List.get(i).add(7,null);
					Class_Authority_List.get(i).set(7, null);
					Class_Authority_List.get(i).add(8,null);
					Class_Authority_List.get(i).set(8, null);
				}
			    i=i+1;
			}
		    String sTeacherContent = "select Teacher_id from Teacher_Content where Course_id="+course_id;
		    ResultSet resultTeacherContent = statementTeacherContent.executeQuery(sTeacherContent);
		    int content_teacher_id=0;
		    while(resultTeacherContent.next())
		    {
		    	content_teacher_id=resultTeacherContent.getInt("Teacher_id");  				
		    	Content_Authority_List.add(new ArrayList<String>());
				Content_Authority_List.get(m).add(0,null);
				Content_Authority_List.get(m).set(0, String.valueOf(course_id));
				Content_Authority_List.get(m).add(1,null);
				Content_Authority_List.get(m).add(2,null);
				Content_Authority_List.get(m).set(1, String.valueOf(content_teacher_id));
				TeacherManagement teacherManagement = new TeacherManagement();
				Teacher_Details teacher_Details = new Teacher_Details();
				teacher_Details = teacherManagement.getTeacher(content_teacher_id);
				String First_Name = teacher_Details.getFirst_Name();
				String Last_Name = teacher_Details.getLast_Name();
				String gender = teacher_Details.getGender();
				String title = "";
				if(gender!=null && gender.equals("Male"))
				{
					title = "Mr.";
				}
				else
				{
					title = "Ms.";
				}
				String Teacher_Name = title + " " + First_Name+" " + Last_Name;
				Content_Authority_List.get(m).set(2, Teacher_Name);
				Content_Authority_List.get(m).add(3,null);
				Content_Authority_List.get(m).set(3, String.valueOf(teacher_Details.getAccount_id()));
				Content_Authority_List.get(m).add(4,null);
				Content_Authority_List.get(m).set(4, teacher_Details.getFileName());
			    m=m+1;
		    }
		    if(content_teacher_id==0)
		    {
		    	Content_Authority_List.add(new ArrayList<String>());
				Content_Authority_List.get(m).add(0,null);
				Content_Authority_List.get(m).set(0, String.valueOf(course_id));
				Content_Authority_List.get(m).add(1,null);
				Content_Authority_List.get(m).add(2,null);
				Content_Authority_List.get(m).set(1, null);
				Content_Authority_List.get(m).set(2, "Not Assigned");
		    }
		}
		authority_Report.setClass_Authority_List(Class_Authority_List);
		authority_Report.setContent_Authority_List(Content_Authority_List);
		authority_Report.setClass_id(Class_id);
		authority_Report.setCourse_id(Course_id);
		authority_Report.setCourse_Name(Course_Name);
		authority_Report.setClass_Name(Class_Name);
		
	} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authority_Report;
	}
}
