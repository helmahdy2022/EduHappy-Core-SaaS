package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Student_Details;
import model.beans.admin.Students;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

public class StudentManagement {
	public Students getStudentsList(String first_Name, String last_Name, String username, String student_id,String route_id, String class_id, String school_id, String grade_id, int admin_id ){
		String route_id_String=null;
		String class_id_String=null;
		if(route_id!=null&&!route_id.equals("%"))
		{
			route_id_String = "and Route_id = '"+route_id+"'";
		}
		else
		{
			route_id_String = "and (Route_id like '%"+route_id+"%' or Route_id is null)";
		}
		if(class_id!=null && !class_id.equals("%"))
		{
			class_id_String = "and class_id = '"+class_id+"'";
		}
		else
		{
			class_id_String = "and (class_id like '%"+class_id+"%' or class_id is null)";
		}
		String query = "select * from Students where First_Name like '%"+first_Name+"%' and Last_Name like '%"+last_Name+"%' and Username like '%"+username+"%' "+route_id_String+" and Student_id like '"+ student_id+"' "
				+ " and Student_id not in (select student_id from Student_Grade_Year where Graduated=1 and school_id like '"+school_id+"') and (Class_id like '"+class_id+"' and Class_id in (select class_id from classes where grade_id like '"+grade_id+"') "
						+ "and Class_id in (select class_id from classes where grade_id in(select grade_id from grades where (school_id like '"+school_id+"') "
								+ "and school_id in(select school_id from Admin_School where admin_id="+admin_id+"))) "
										+ "or Student_id in (select student_id from student_grade_year where final_grading is null "+class_id_String+" and school_id like '"+school_id+"' and grade_id like '"+grade_id+"' and school_id in(select school_id from Admin_School where admin_id="+admin_id+")))";
		Students Students = new Students(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);

			
	    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
    		AccountManagement accountManagement = new AccountManagement();
    		while (result.next())
	    	{

	    		Student_id.add(result.getInt("Student_id"));
	    		
	    		First_Name.add(result.getString("First_Name"));
    		
	    		Last_Name.add(result.getString("Last_Name"));
	    		
	    		Username.add(result.getString("Username"));
	    		
	    		Class_id.add(result.getInt("Class_id"));
		    	
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
	    		int IntegerSchool_id = 0;
	    		if(school_id!=null &&!school_id.equals("%"))
	    		{
	    			IntegerSchool_id = Integer.parseInt(school_id);
	    		}
	    		else
	    		{
	    			IntegerSchool_id = getStudentSchool(result.getInt("Student_id"));
	    		}
	    		School_id.add(IntegerSchool_id);
	    		int account_id = accountManagement.getSchoolAccount(IntegerSchool_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(result.getString("Fake_File_Name"));
		    }
	    	Students.setStudent_id(Student_id);
	    	Students.setFirst_Name(First_Name);
	    	Students.setLast_Name(Last_Name);
	    	Students.setUsername(Username);
	    	Students.setClass_id(Class_id);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}

	public Students getStudentByClass(int class_id ){
		String query = "select * from Students where Class_id = '"+class_id+"'";
		Students Students = new Students(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
			ResultSet result = statement.executeQuery(query);
	    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
    		AccountManagement accountManagement = new AccountManagement();
    		while (result.next())
	    	{

	    		Student_id.add(result.getInt("Student_id"));
	    		
	    		First_Name.add(result.getString("First_Name"));
    		
	    		Last_Name.add(result.getString("Last_Name"));
	    		
	    		Username.add(result.getString("Username"));
	    		
	    		Class_id.add(result.getInt("Class_id"));
		    	
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
	    		int school_id = getStudentSchool(result.getInt("Student_id"));
	    		School_id.add(school_id);
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(result.getString("Fake_File_Name"));
		    }
	    	Students.setStudent_id(Student_id);
	    	Students.setFirst_Name(First_Name);
	    	Students.setLast_Name(Last_Name);
	    	Students.setUsername(Username);
	    	Students.setClass_id(Class_id);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	
	public Students getStudentsByClassCourseGroup(int class_id, int course_id, int group_id){
		Students Students = new Students(); 
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudents = connection.createStatement();
				Statement statementElective = connection.createStatement();
				Statement statementExistingAtt = connection.createStatement();){

			String sStudents = null;
			
			if (group_id!=0)
	    	{			
				sStudents = "select * from students where student_id in (select student_id from Student_Groups where Group_id="+group_id+")"
						+ " and student_id in(select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id=(select course_id from "+"`Groups`"+" where group_id="+group_id+" ))))";
	    	}
	    	else
	    	{
				/////////////////Check if the Course is Elective ///////////////////
				String sElective = "select Elective from Courses where Course_id="+course_id;
				ResultSet resultElective = statementElective.executeQuery(sElective);
				resultElective.next();
	    		if (resultElective.getString("Elective").equals("1"))
	    		{
	    			sStudents = "select * from Students where Student_id in (Select Student_id from Student_Electives where Course_id="+course_id+")"
	    					+ " and student_id in (select student_id from students where class_id in (select class_id from classes where grade_id=(select grade_id from courses where course_id="+course_id+")))";

	    		}
	    		else
	    		{
	    			sStudents = "select * from Students where class_id="+class_id;
	    		}
	    	}
			
	    	
			ResultSet resultStudents = statementStudents.executeQuery(sStudents);
	    	
	    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
    		AccountManagement accountManagement = new AccountManagement();
    		while (resultStudents.next())
	    	{

	    		Student_id.add(resultStudents.getInt("Student_id"));
	    		
	    		First_Name.add(resultStudents.getString("First_Name"));
    		
	    		Last_Name.add(resultStudents.getString("Last_Name"));
	    		
	    		Username.add(resultStudents.getString("Username"));
	    		
	    		Class_id.add(resultStudents.getInt("Class_id"));
		    	
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
	    		int school_id = getStudentSchool(resultStudents.getInt("Student_id"));
	    		School_id.add(school_id);
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(resultStudents.getString("Fake_File_Name"));
		    }
	    	Students.setStudent_id(Student_id);
	    	Students.setFirst_Name(First_Name);
	    	Students.setLast_Name(Last_Name);
	    	Students.setUsername(Username);
	    	Students.setClass_id(Class_id);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	public String getStudentClass(int student_id ){
		String query = "select class_id from Students where student_id = '"+student_id+"'";
		String class_id = null;		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementCheckClass = connection.createStatement();
				Statement statementSutdentSchool = connection.createStatement();
				Statement statementSchoolYear = connection.createStatement();
				Statement statementSutdentClass = connection.createStatement();
			 Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query);){
	    	while(result.next()) 
	    	{
	    		class_id = result.getString("Class_id");
				String sCheckClass = "select * from students where student_id="+student_id;
				ResultSet resultCheckClass = statementCheckClass.executeQuery(sCheckClass);
				while(resultCheckClass.next())
				{
					if(resultCheckClass.getString("Class_id")==null)
					{
						/////////////////////////////////////Get the student school ////////////////////////////////////
						String sSutdentSchool = "select school_id from student_grade_year where final_grading is null and student_id="+student_id;
						ResultSet resultSutdentSchool = statementSutdentSchool.executeQuery(sSutdentSchool);
						int school_id = 0;
						while(resultSutdentSchool.next())
						{
							school_id = resultSutdentSchool.getInt("School_id");
						}
						//////////////////////////////////////////////////////////////////////////////////////////////////
						///////////////////////////////Get the current school year/////////////////////////////////////////
						java.util.Date today = new java.util.Date();
				    	String sSchoolYear = "select * from _School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' "
				    			+ "and School_id="+school_id+" ORDER BY Year_Start DESC LIMIT 1";
				    	ResultSet resultSchoolYear = statementSchoolYear.executeQuery(sSchoolYear);
				    	int school_year_id=0;
				    	while(resultSchoolYear.next())
				    	{
					    	school_year_id = resultSchoolYear.getInt("School_year_id");	
				    	}
						///////////////////////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////Get previous Class_id which is the class just before the promotion///////////////////////////
						String sSutdentClass = "select class_id from student_grade_year where student_id="+student_id+" and school_id="+school_id+" and school_year_id="+school_year_id;
						ResultSet resultSutdentClass = statementSutdentClass.executeQuery(sSutdentClass);
						while(resultSutdentClass.next())
						{
							class_id = resultSutdentClass.getString("Class_id");
						}
				    	/////////////////////////////////////////////////////////////////////////////////////////////////	    	
					}
					else
					{
						class_id = resultCheckClass.getString("Class_id");
					}
				}
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_id;
	}

	public int getStudentSchool(int student_id ){

		int school_id = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	    	
			int class_id = 0;
			if(getStudentClass(student_id)!=null)
			{
				class_id = Integer.parseInt(getStudentClass(student_id));
			}
			String query = null;
			if(class_id!=0)
			{
				query = "select school_id from Grades where Grade_id=(select Grade_id from Classes where Class_id="+class_id+")";
			}
			else
			{
				query = "select school_id from Student_Grade_Year where student_id="+student_id+" and class_id is null";
			}			
			ResultSet result = statement.executeQuery(query);
			while(result.next())
	    	{
	    		school_id = result.getInt("School_id");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_id;
	}

	public int getStudentGrade(int student_id ){

		int grade_id = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementGrade = connection.createStatement();
			 Statement statementClass = connection.createStatement();){
			int class_id = 0;
/*			if(getStudentClass(student_id)!=null)
			{
				class_id = Integer.parseInt(getStudentClass(student_id));
			}*/
			String sClass = "Select Class_id from Students where student_id="+student_id;
			ResultSet resultClass = statementClass.executeQuery(sClass);
			while (resultClass.next())
			{
				class_id = resultClass.getInt("Class_id");
			}
			String sGrade=null;
			if(class_id==0)
			{
				sGrade = "select grade_id from student_grade_year where final_grading is null and student_id="+student_id;

			}
			else
			{
				sGrade = "select Grade_id from classes where class_id="+class_id;
			}
			
			ResultSet resultGrade = statementGrade.executeQuery(sGrade);
			while(resultGrade.next()) 
	    	{
	    		grade_id = resultGrade.getInt("Grade_id");
	        } 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade_id;
	}
	
	public int getStudentAdmin(int student_id ){
		int admin_id = 0;		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementStudentAdmin = connection.createStatement();){
	    	
			int class_id = 0;
			if(getStudentClass(student_id)!=null)
			{
				class_id = Integer.parseInt(getStudentClass(student_id));
			}
			String sStudentAdmin = null;
			if(class_id!=0)
			{
				sStudentAdmin = "select admin_id from admin_school where school_id=(select school_id from grades where grade_id=(select grade_id from classes where class_id="+class_id+"))";
			}
			else
			{
				sStudentAdmin = "select admin_id from admin_school where school_id=(select school_id from student_grade_year where student_id="+student_id+" and Graduated=1)";
			}			
			ResultSet resultStudentAdmin = statementStudentAdmin.executeQuery(sStudentAdmin);
			while(resultStudentAdmin.next()) 
			{
	    		admin_id = resultStudentAdmin.getInt("Admin_id");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin_id;
	}
	
	public Student_Details getStudent(int student_id){
		String query = "select *, DATE_FORMAT(Birth_date, '%Y-%m-%d') AS B_date from Students where Student_id="+student_id;
		
		Student_Details student_Details = new Student_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementClass = connection.createStatement();
				Statement statementGrade = connection.createStatement();
				Statement statementSutdentGrade = connection.createStatement();
				Statement statementSchool = connection.createStatement();
				Statement statementCountry = connection.createStatement();
				Statement statementRoute = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{			
				student_Details.setStudent_id(student_id);
				student_Details.setFirst_Name(result.getString("First_Name"));
				student_Details.setLast_Name(result.getString("Last_Name"));
				student_Details.setClass_id(result.getInt("Class_id"));
				student_Details.setCountry_id(result.getInt("Country_id"));
				student_Details.setCity(result.getString("City"));
				student_Details.setPO_Box(result.getInt("Po_Box"));
				student_Details.setFull_Address(result.getString("Full_Address"));
				student_Details.setGender(result.getString("Gender"));
				student_Details.setBirth_Date(result.getString("B_Date"));
				student_Details.setEmail(result.getString("Email"));
				student_Details.setTel(result.getString("Tel"));
				student_Details.setMobile(result.getString("Mobile"));
				student_Details.setRoute_id(result.getInt("Route_id"));
				student_Details.setUsername(result.getString("Username"));
				student_Details.setPassword(result.getString("Password"));
				student_Details.setFileName(result.getString("Fake_File_Name"));
				student_Details.setFirst_Login(result.getInt("First_Login"));
				/////////////////////////Get Class, Grades, School, and Country Names////////////////////////////////////
				String sClass = "Select Class_Name from Classes where class_id="+result.getInt("Class_id");
				ResultSet resultClass = statementClass.executeQuery(sClass);
				String class_Name = null;
				while(resultClass.next())
				{
					class_Name = resultClass.getString("Class_Name");
				}
				String sGrade = "Select Grade_id, Grade_Name from Grades where grade_id=(select grade_id from classes where class_id="+result.getInt("Class_id")+")";
				ResultSet resultGrade = statementGrade.executeQuery(sGrade);
				String grade_Name = null;
				int grade_id=0;
				while(resultGrade.next())
				{
					grade_Name = resultGrade.getString("Grade_Name");
					grade_id = resultGrade.getInt("Grade_id");
				}
				if(grade_Name==null)
				{
					/////////////////////////////////////Get the student school ////////////////////////////////////
					String sSutdentGrade = "select Grade_id from student_grade_year where final_grading is null and student_id="+student_id;
					ResultSet resultSutdentGrade = statementSutdentGrade.executeQuery(sSutdentGrade);
					while(resultSutdentGrade.next())
					{
						grade_id = resultSutdentGrade.getInt("Grade_id");
					}
					sGrade = "Select Grade_Name from Grades where grade_id="+grade_id;
					resultGrade = statementGrade.executeQuery(sGrade);
					while(resultGrade.next())
					{
						grade_Name = resultGrade.getString("Grade_Name");
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////
				}
				//String sSchool = "Select Name from School where school_id=(select school_id from grades where grade_id=(select grade_id from classes where class_id="+result.getInt("Class_id")+"))";
				String sSchool = "Select Name, school_id from School where school_id=(select school_id from grades where grade_id="+grade_id+")";
				ResultSet resultSchool = statementSchool.executeQuery(sSchool);
				String school_Name = null;
				int school_id =0;
				while(resultSchool.next())
				{
					school_Name = resultSchool.getString("name");
					school_id = resultSchool.getInt("School_id");
				}
				String sCountry = "Select Country_Name from Country where country_id="+result.getInt("Country_id");
				ResultSet resultCountry = statementCountry.executeQuery(sCountry);
				String country_Name = null;
				while(resultCountry.next())
				{
					country_Name = resultCountry.getString("Country_Name");
				}
				student_Details.setClass_Name(class_Name);
				student_Details.setGrade_Name(grade_Name);
				student_Details.setSchool_Name(school_Name);	
				student_Details.setCountry_Name(country_Name);
				student_Details.setGrade_id(grade_id);
				student_Details.setSchool_id(school_id);
				///////////////////////Get Route Name////////////////////////////////////
				String sRoute = "Select Route_Name from Vehicle_Route where route_id="+result.getInt("Route_id");
				ResultSet resultRoute = statementRoute.executeQuery(sRoute);
				while(resultRoute.next())
				{
					student_Details.setRoute_Name(resultRoute.getString("Route_Name"));
				}
				//////////////////////////////////////
			}    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student_Details;
	}
	public ArrayList<Integer> getStudentParent(int student_id ){

		ArrayList<Integer> parent_id = new ArrayList<Integer>();		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){

			String query = "select parent_id from student_parent where student_id="+student_id;
			ResultSet result = statement.executeQuery(query);
			while(result.next())
	    	{
	    		parent_id.add(result.getInt("Parent_id"));
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent_id;
	}	
	public int createStudent(Student_Details student_Details){
		String query = "INSERT INTO Students (Student_id, First_Name, Last_Name, Class_id, Country_id, City, PO_Box, full_address, Gender, email, tel, mobile, Birth_Date, Route_id, Username, Password, first_login) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Statement statementAccountStudents = connection.createStatement();
				Statement statementUserExistInParent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

		    String sUserExistInParent = "select * from Parents where Username='"+student_Details.getUsername()+"'";
		    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);

		    String sUserExistInTeacher = "select * from Teachers where Username='"+student_Details.getUsername()+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);
		    
		    String sUserExistInAdmin = "select * from Admins where Username='"+student_Details.getUsername()+"'";
		    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
		    
		    if(resultUserExistInParent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
		    {
		    	autoIncValue=-2;
		    	return autoIncValue;
		    }
		    String Birth_Date = student_Details.getBirth_Date();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatement.setString(1, student_Details.getFirst_Name());
			pstatement.setString(2, student_Details.getLast_Name());
			if(student_Details.getClass_id()!=0)
			{
				pstatement.setInt(3, student_Details.getClass_id());
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}	
			if(student_Details.getCountry_id()!=0)
			{
				pstatement.setInt(4, student_Details.getCountry_id());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}
			pstatement.setString(5, student_Details.getCity());
			if(student_Details.getPO_Box()!=0)
			{
				pstatement.setInt(6, student_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
			}		
			pstatement.setString(7, student_Details.getFull_Address());
			pstatement.setString(8, student_Details.getGender());
			pstatement.setString(9, student_Details.getEmail());
			pstatement.setString(10, student_Details.getTel());
			pstatement.setString(11, student_Details.getMobile());
			pstatement.setDate(12,new Date(Birth_Date_Date.getTime()));
			if(student_Details.getRoute_id()!=0)
			{
				pstatement.setInt(13, student_Details.getRoute_id());
			}
			else
			{
				pstatement.setNull(13, java.sql.Types.NULL);
			}	
			pstatement.setString(14, student_Details.getUsername());
			pstatement.setString(15, student_Details.getPassword());
			if(student_Details.getFirst_Login()==0)
			{
				pstatement.setInt(16, 0);
			}
			else
			{
				pstatement.setInt(16, 1);
			}	
			updateQuery = pstatement.executeUpdate();	        
			if(updateQuery!=0)
			{
				ResultSet resultStudent_id = pstatement.getGeneratedKeys();
				if(resultStudent_id.next()) 
				{
				       autoIncValue = resultStudent_id.getInt(1);
				       resultStudent_id.close();
				       
				       String sAccountStudents = "select No_Of_Created_Students from Accounts where account_id="+student_Details.getAccount_id();
				       ResultSet resultAccountStudents = statementAccountStudents.executeQuery(sAccountStudents);
				       int no_Of_Created_Students = 0;
				       while(resultAccountStudents.next())
				       {
				    	   no_Of_Created_Students = resultAccountStudents.getInt("No_Of_Created_Students");
				       }
				       
				       String sAccount = "update accounts set No_Of_Created_Students=? where account_id="+student_Details.getAccount_id();				       
				       PreparedStatement pstatementAccount = connection.prepareStatement(sAccount);
				       pstatementAccount.setInt(1, no_Of_Created_Students+1);
				       updateQuery = pstatementAccount.executeUpdate();
				       pstatementAccount.close();
				}
			}    	

	       
		}
		catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Student_Username_Unique"))
				autoIncValue=-2;			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}
			
	public int updateStudent(Student_Details student_Details, int student_id){
		String query = "update Students set First_Name=?, Last_Name=?, Class_id=?, Country_id=?, City=?, PO_Box=?, full_address=?, Gender=?, email=?, tel=?, mobile=?, Birth_Date=?, Route_id=?, Username=?, Password=? where Student_id="+student_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementSchoolYearCurrent = connection.createStatement();
				Statement statementSchoolYearMax = connection.createStatement();
				Statement statementCurrentGrade = connection.createStatement();
				Statement statementUserExistInParent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

	    String sUserExistInTeacher = "select * from Teachers where Username='"+student_Details.getUsername()+"'";
	    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);

	    String sUserExistInParent = "select * from Parents where Username='"+student_Details.getUsername()+"'";
	    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);	
	    
	    String sUserExistInAdmin = "select * from Admins where Username='"+student_Details.getUsername()+"'";
	    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
	    
	    if(resultUserExistInParent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
	    {
	    	updateQuery=-1;
	    	return updateQuery;
	    }
			String Birth_Date = student_Details.getBirth_Date();
			//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int school_year_id = 0;
			int current_Grade = 0;
			pstatement.setString(1, student_Details.getFirst_Name());
			pstatement.setString(2, student_Details.getLast_Name());			
			if(student_Details.getClass_id()!=0)
			{
				pstatement.setInt(3, student_Details.getClass_id());
				java.util.Date today = new java.util.Date();
		    	String sSchoolYearCurrent = "select school_year_id from School_year where Year_Start<='"+new java.sql.Date(today.getTime())+"' and Year_End>='"+new java.sql.Date(today.getTime())+"' and School_id="+student_Details.getSchool_id();
		    	String sSchoolYearMax = "select max(school_year_id) from student_grade_year where student_id="+student_id+" and School_id="+student_Details.getSchool_id();

		    	ResultSet resultSchoolYearCurrent = statementSchoolYearCurrent.executeQuery(sSchoolYearCurrent);
		    	
		    	ResultSet resultSchoolYearMax = statementSchoolYearMax.executeQuery(sSchoolYearMax);
		    	
		    	int school_year_current = 0;
		    	int school_year_max = 0;
		    	while (resultSchoolYearCurrent.next())
		    	{
		    		school_year_current = resultSchoolYearCurrent.getInt(1);
		    	}
		    	while (resultSchoolYearMax.next())
		    	{
		    		school_year_max = resultSchoolYearMax.getInt(1);
		    	}
		    	if(school_year_current > school_year_max)
		    	{
		    		school_year_id = school_year_current;
		    	}
		    	else
		    	{
		    		school_year_id = school_year_max;
		    	}
				//////////////////////////Determine the current grade before the update //////////////////////////////
		    	String sCurrentGrade = "select grade_id from classes where class_id=(select class_id from students where student_id="+student_id+")";
		    	ResultSet resultCurrentGrade = statementCurrentGrade.executeQuery(sCurrentGrade);		    	
		    	while(resultCurrentGrade.next())
		    	{
		    		current_Grade = resultCurrentGrade.getInt("Grade_id");
		    	}				
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}
			pstatement.setInt(4, student_Details.getCountry_id());	
			pstatement.setString(5, student_Details.getCity());	
			if(student_Details.getPO_Box()!=0)
			{
				pstatement.setInt(6, student_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
			}
			pstatement.setString(7, student_Details.getFull_Address());
			pstatement.setString(8, student_Details.getGender());
			pstatement.setString(9, student_Details.getEmail());	
			pstatement.setString(10, student_Details.getTel());
			pstatement.setString(11, student_Details.getMobile());
			pstatement.setDate(12,new Date(Birth_Date_Date.getTime()));
			if(student_Details.getRoute_id()!=0)
			{
				pstatement.setInt(13, student_Details.getRoute_id());
			}
			else
			{
				pstatement.setNull(13, java.sql.Types.NULL);
			}	
			pstatement.setString(14, student_Details.getUsername());
			pstatement.setString(15, student_Details.getPassword());
	        updateQuery = pstatement.executeUpdate();
	        if(updateQuery!=0 && student_Details.getClass_id()!=0)
	        {
		    	String sStudentPosition = "update student_grade_year set class_id=?, grade_id=?, school_id=? where student_id="+student_id+" and school_year_id="+school_year_id+" and class_id is not null";
				PreparedStatement pstatementStudentPosition = connection.prepareStatement(sStudentPosition);
				pstatementStudentPosition.setInt(1, student_Details.getClass_id());
				pstatementStudentPosition.setInt(2, student_Details.getGrade_id());
				pstatementStudentPosition.setInt(3, student_Details.getSchool_id());
				pstatementStudentPosition.executeUpdate();
				pstatementStudentPosition.close();
				if(current_Grade != student_Details.getGrade_id())
				{
					/////////////////Reset Student Electives //////////////////////////////////////////////////////////////////
					String sResetStudentElectives = "Delete from Student_Electives where Student_id="+student_id;
					PreparedStatement pstatementResetStudentElectives = connection.prepareStatement(sResetStudentElectives);
					pstatementResetStudentElectives.executeUpdate();
					pstatementResetStudentElectives.close();
					/////////////////Reset Student Groups///////////////////////////////////////////////////////////////////////////////////////////////////
					String sResetStudentGroups = "Delete from Student_Groups where Student_id="+student_id;
					PreparedStatement pstatementResetStudentGroups = connection.prepareStatement(sResetStudentGroups);
					pstatementResetStudentGroups.executeUpdate();
					pstatementResetStudentGroups.close();
				}
	        }
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Student_Username_Unique"))
				updateQuery=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteStudent(int student_id, int account_id){
		String query = "Delete from Students where Student_id="+student_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementAccountStudents = connection.createStatement();){
	
			updateQuery = pstatement.executeUpdate();
			
		       String sAccountStudents = "select No_Of_Created_Students from Accounts where account_id="+account_id;
		       ResultSet resultAccountStudents = statementAccountStudents.executeQuery(sAccountStudents);
		       int no_Of_Created_Students = 0;
		       while(resultAccountStudents.next())
		       {
		    	   no_Of_Created_Students = resultAccountStudents.getInt("No_Of_Created_Students");
		       }		       
		       if(no_Of_Created_Students>0)
		       {
			       String sAccount = "update accounts set No_Of_Created_Students=? where account_id="+account_id;				       
			       PreparedStatement pstatementAccount = connection.prepareStatement(sAccount);
			       pstatementAccount.setInt(1, no_Of_Created_Students-1);
			       updateQuery = pstatementAccount.executeUpdate();
			       pstatementAccount.close();
		       }
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateStudentPassword(int student_id, String new_Password){
		String query = "update Students set password=?, First_Login=0 where Student_id="+student_id;
		int updateQuery = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setString(1, new_Password);
			updateQuery = pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Students getGraduatedStudentsList(String first_Name, String last_Name, String username, String student_id, String school_Year_id, String school_id, int admin_id ){

		String query = "select * from Students where First_Name like '%"+first_Name+"%' and Last_Name like '%"+last_Name+"%' and Username like '%"+username+"%' and Student_id like '"+ student_id+"' and Student_id in (select student_id from Student_Grade_Year where Graduated=1 and school_year_id like'"+school_Year_id+"' and school_id like '"+school_id+"' and school_id in(select school_id from Admin_School where admin_id="+admin_id+"))";
		
		Students Students = new Students(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);

			
	    	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
    		AccountManagement accountManagement = new AccountManagement();
    		while (result.next())
	    	{

	    		Student_id.add(result.getInt("Student_id"));
	    		
	    		First_Name.add(result.getString("First_Name"));
    		
	    		Last_Name.add(result.getString("Last_Name"));
	    		
	    		Username.add(result.getString("Username"));
	    	
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
	    		int IntegerSchool_id = 0;
	    		if(school_id!=null &&!school_id.equals("%"))
	    		{
	    			IntegerSchool_id = Integer.parseInt(school_id);
	    		}
	    		else
	    		{
	    			IntegerSchool_id = getStudentSchool(result.getInt("Student_id"));
	    		}
	    		School_id.add(IntegerSchool_id);
	    		int account_id = accountManagement.getSchoolAccount(IntegerSchool_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(result.getString("Fake_File_Name"));
		    }
	    	Students.setStudent_id(Student_id);
	    	Students.setFirst_Name(First_Name);
	    	Students.setLast_Name(Last_Name);
	    	Students.setUsername(Username);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	public int getStudentByUsername(String username ){

		int student_id = 0;		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){

			String query = "select student_id from Students where username='"+username+"'";
					
			ResultSet result = statement.executeQuery(query);
			while(result.next())
	    	{
	    		student_id = result.getInt("Student_id");
	        } 	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student_id;
	}

}
