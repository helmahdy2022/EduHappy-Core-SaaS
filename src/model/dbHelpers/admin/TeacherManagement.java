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

import model.beans.admin.Teacher_Details;
import model.beans.admin.Teachers;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

public class TeacherManagement {
	public Teachers getTeachersList(String first_Name, String last_Name, String username, String teacher_id, String class_id, String school_id, String grade_id, String course_id, int admin_id){
/*		String query = "select * from Teachers where First_Name like '%"+first_Name+"%' and Last_Name like '%"+last_Name+"%' and Username like '%"+username+"%' and Teacher_id like '"+ teacher_id+"' "
				+ "and Teacher_id in(select teacher_id from Teacher_School where school_id like '"+school_id+"') "
						+ "and Teacher_id in (select teacher_id from teacher_Grades where grade_id like '"+grade_id+"') "
								+ "and Teacher_id in (select Teacher_id from Teacher_Classes where class_id like '"+class_id+"') "
										+ "and Teacher_id in (select teacher_id from Teacher_Classes_Courses where class_id like '"+class_id+"' and course_id like '"+course_id+"')";*/
		String query = "select * from Teachers where First_Name like '%"+first_Name+"%' and Last_Name like '%"+last_Name+"%' and Username like '%"+username+"%' and Teacher_id like '"+ teacher_id+"' and Teacher_id in (select Teacher_id from Teacher_School where School_id in(Select School_id from Admin_School where Admin_id="+admin_id+"))";
		if(!school_id.equals(""))
			query = query + "and Teacher_id in(select teacher_id from Teacher_School where school_id like '"+school_id+"') ";
		if(!grade_id.equals(""))
			query = query + "and Teacher_id in (select teacher_id from teacher_Grades where grade_id like '"+grade_id+"') ";
		if(!class_id.equals(""))
			query = query + "and Teacher_id in (select Teacher_id from Teacher_Classes where class_id like '"+class_id+"') ";
		if(!course_id.equals(""))
			query = query + "and Teacher_id in (select teacher_id from Teacher_Classes_Courses where class_id like '"+class_id+"' and course_id like '"+course_id+"')";
		
		Teachers Teachers = new Teachers(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> Teacher_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
	    	
	    	while(result.next()) {
	    		Teacher_id.add(result.getInt("Teacher_id"));

	
	    		First_Name.add(result.getString("First_Name"));
	    		
	    		
	    		Last_Name.add(result.getString("Last_Name"));
	    		
	    		
	    		Username.add(result.getString("Username"));
	    		
		    	//////////////////Teacher Account////////////////////////////////
		    	//int admin_id = getTeacherAdmin(result.getInt("Teacher_id"));
		    	AccountManagement accountManagement = new AccountManagement();
		    	int account_id = accountManagement.getAdminAccount(admin_id);
		    	Account_id.add(account_id);
		    	/////////////////////////////////////////////////////////////////
		    	FileName.add(result.getString("Fake_File_Name"));
	        } 
	    	Teachers.setTeacher_id(Teacher_id);
	    	Teachers.setFirst_Name(First_Name);
	    	Teachers.setLast_Name(Last_Name);
	    	Teachers.setUsername(Username);
	    	Teachers.setAccount_id(Account_id);
	    	Teachers.setFileName(FileName);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teachers;
	}
	public Teachers getTeachersList(int admin_id){
		
    	//////////////////Teacher Account////////////////////////////////
    	//int admin_id = getTeacherAdmin(result.getInt("Teacher_id"));
    	AccountManagement accountManagement = new AccountManagement();
    	int account_id = accountManagement.getAdminAccount(admin_id);  	
    	/////////////////////////////////////////////////////////////////
		String query = "select * from Teachers where Employee_id in (select Employee_id from Employees where account_id="+account_id+") and Employee_id in (select Employee_id from Teachers where Teacher_id in (Select Teacher_id from teacher_school where school_id in(select school_id from admin_school where Admin_id="+admin_id+")))";
		
		Teachers Teachers = new Teachers(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> Teacher_id = new ArrayList<Integer>();
	    	ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
	    	
	    	while(result.next()) {
	    		Teacher_id.add(result.getInt("Teacher_id"));
	    		First_Name.add(result.getString("First_Name"));
	    		Last_Name.add(result.getString("Last_Name"));    		
	    		Username.add(result.getString("Username"));
	    		Account_id.add(account_id);
		    	FileName.add(result.getString("Fake_File_Name"));
	        } 
	    	Teachers.setTeacher_id(Teacher_id);
	    	Teachers.setFirst_Name(First_Name);
	    	Teachers.setLast_Name(Last_Name);
	    	Teachers.setUsername(Username);
	    	Teachers.setAccount_id(Account_id);
	    	Teachers.setFileName(FileName);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Teachers;
	}
	public Teacher_Details getTeacher(int teacher_id){
		String sEmployee = "Select * from Employees where employee_id=(select employee_id from teachers where teacher_id="+teacher_id+")";
		String sTeacher = "select *, DATE_FORMAT(Birth_date, '%Y-%m-%d') AS B_date from teachers where teacher_id="+teacher_id;
		
		Teacher_Details teacher_Details = new Teacher_Details();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementEmployee = connection.createStatement();
				Statement statementTeacher = connection.createStatement();
				Statement statementCountry = connection.createStatement();){
						
			///////////////////////////////Get Employee details///////////////////////////////////////////
			ResultSet resultEmployee = statementEmployee.executeQuery(sEmployee);
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
			///////////////populate the bean ///////////////////////
			while(resultTeacher.next())
			{
				while(resultEmployee.next())
				{				
					teacher_Details.setPayroll_id(resultEmployee.getInt("Payroll_id"));
					teacher_Details.setDepartment_id(resultEmployee.getInt("Department_id"));
					teacher_Details.setDesignation(resultEmployee.getString("Designation"));
					teacher_Details.setJoining_Date(resultEmployee.getString("Joining_Date"));
					teacher_Details.setInitial_Basic(resultEmployee.getDouble("Initial_Basic"));
				}					
				teacher_Details.setTeacher_id(teacher_id);
				teacher_Details.setFirst_Name(resultTeacher.getString("first_Name"));
				teacher_Details.setLast_Name(resultTeacher.getString("last_Name"));
				teacher_Details.setCountry_id(resultTeacher.getInt("Country_id"));
				teacher_Details.setCity(resultTeacher.getString("City"));
				teacher_Details.setPO_Box(resultTeacher.getInt("pO_Box"));
				teacher_Details.setFull_Address(resultTeacher.getString("full_Address"));
				teacher_Details.setGender(resultTeacher.getString("gender"));
				teacher_Details.setEmail(resultTeacher.getString("email"));
				teacher_Details.setTel(resultTeacher.getString("tel"));
				teacher_Details.setMobile(resultTeacher.getString("mobile"));
				teacher_Details.setBirth_Date(resultTeacher.getString("B_Date"));
				teacher_Details.setUsername(resultTeacher.getString("username"));
				teacher_Details.setPassword(resultTeacher.getString("password"));
				teacher_Details.setFirst_Login(resultTeacher.getInt("First_Login"));
				
				String sCountry = "Select Country_Name from Country where country_id="+resultTeacher.getInt("Country_id");
				ResultSet resultCountry = statementCountry.executeQuery(sCountry);
				String country_Name = null;
				while(resultCountry.next())
				{
					country_Name = resultCountry.getString("Country_Name");
				}
				
				teacher_Details.setCountry_Name(country_Name);
		    	//////////////////Teacher Account////////////////////////////////
		    	int admin_id = getTeacherAdmin(teacher_id);
		    	AccountManagement accountManagement = new AccountManagement();
		    	int account_id = accountManagement.getAdminAccount(admin_id);
		    	teacher_Details.setAccount_id(account_id);
		    	teacher_Details.setFileName(resultTeacher.getString("Fake_File_Name"));
			}
			///////////////end populate the bean ///////////////////////

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher_Details;
	}
	public int getTeacherByUsername(String username){
		String query = "select * from teachers where username='"+username+"'";
		int teacher_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			result.next();
			teacher_id = result.getInt("Teacher_id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher_id;
	}
	public boolean checkTeacherSchool(int school_id, int teacher_id){
		String query = "select school_id from teacher_school where teacher_id="+teacher_id+" and School_id="+school_id;
		boolean teacherSchoolExist=false;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			if (result.isBeforeFirst())
			{
				teacherSchoolExist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherSchoolExist;
	}
	
	public int getTeacherAdmin(int teacher_id){
		String query = "select admin_id from admin_school where school_id in (select school_id from teacher_school where teacher_id = "+teacher_id+")";
		int admin_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				admin_id = result.getInt("Admin_id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin_id;
	}
	
	public int createTeacher(Teacher_Details teacher_Details){
		String sEmployee = "INSERT INTO Employees (Employee_id, Account_id, Payroll_id, Department_id, Employee_Group, Designation, Joining_Date, Initial_Basic) VALUES (NULL,?, ?, ?, ?, ?, ?, ?)";
		String sTeacher = "INSERT INTO Teachers (Teacher_id, First_Name, Last_Name, Country_id, City, PO_Box, full_address, Gender, email, tel, mobile, Birth_Date, Username, Password, employee_id) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQueryEmployee = 0;
		int updateQueryTeacher = 0;
		int employee_id=-1;
		int teacher_id=-1;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstatementTeacher = connection.prepareStatement(sTeacher, Statement.RETURN_GENERATED_KEYS);
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInParent = connection.createStatement();
				Statement statementUserExistInAdmin = connection.createStatement();){

			connection.setAutoCommit(false);
			////////////////////////////Create Employee record and get Employee_ID////////////////////////////////////////////////////
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dJoining_Date=null;
			try {
				dJoining_Date = formater.parse(teacher_Details.getJoining_Date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatementEmployee.setInt(1, teacher_Details.getAccount_id());
			if(teacher_Details.getPayroll_id()!=0)
			{
				pstatementEmployee.setInt(2, teacher_Details.getPayroll_id());
			}
			else
			{
				pstatementEmployee.setNull(2, java.sql.Types.NULL);
			}
			if(teacher_Details.getDepartment_id()!=0)
			{
				pstatementEmployee.setInt(3, teacher_Details.getDepartment_id());
			}
			else
			{
				pstatementEmployee.setNull(3, java.sql.Types.NULL);
			}
			pstatementEmployee.setString(4, "Teacher");
			pstatementEmployee.setString(5, teacher_Details.getDesignation());
			pstatementEmployee.setDate(6, new Date(dJoining_Date.getTime()));
			pstatementEmployee.setDouble(7, teacher_Details.getInitial_Basic());
			updateQueryEmployee = pstatementEmployee.executeUpdate();
			if(updateQueryEmployee!=0)
			{
				ResultSet resultEmployee_id = pstatementEmployee.getGeneratedKeys();
				if(resultEmployee_id.next()) 
				{
					employee_id = resultEmployee_id.getInt(1);
					resultEmployee_id.close();
				}
			}   
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			String sUserExistInStudent = "select * from Students where Username='"+teacher_Details.getUsername()+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);
		    
		    String sUserExistInParent = "select * from Parents where Username='"+teacher_Details.getUsername()+"'";
		    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);		
		    
		    String sUserExistInAdmin = "select * from Admins where Username='"+teacher_Details.getUsername()+"'";
		    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
		    
		    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInParent.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
		    {
		    	teacher_id=-2;
		    	return teacher_id;
		    }
			String Birth_Date = teacher_Details.getBirth_Date();			
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatementTeacher.setString(1, teacher_Details.getFirst_Name());
			pstatementTeacher.setString(2, teacher_Details.getLast_Name());
			pstatementTeacher.setInt(3, teacher_Details.getCountry_id());
			pstatementTeacher.setString(4, teacher_Details.getCity());
			if(teacher_Details.getPO_Box()!=0)
			{
				pstatementTeacher.setInt(5, teacher_Details.getPO_Box());
			}
			else
			{
				pstatementTeacher.setNull(5, java.sql.Types.NULL);
			}
			pstatementTeacher.setString(6, teacher_Details.getFull_Address());
			pstatementTeacher.setString(7, teacher_Details.getGender());
			pstatementTeacher.setString(8, teacher_Details.getEmail());
			pstatementTeacher.setString(9, teacher_Details.getTel());
			pstatementTeacher.setString(10, teacher_Details.getMobile());
			pstatementTeacher.setDate(11, new Date(Birth_Date_Date.getTime()));
			pstatementTeacher.setString(12, teacher_Details.getUsername());
			pstatementTeacher.setString(13, teacher_Details.getPassword());
			pstatementTeacher.setInt(14, employee_id);
			updateQueryTeacher = pstatementTeacher.executeUpdate();
			if(updateQueryTeacher!=0)
			{
				ResultSet resultTeacher_id = pstatementTeacher.getGeneratedKeys();
				if(resultTeacher_id.next()) 
				{
					teacher_id = resultTeacher_id.getInt(1);
					resultTeacher_id.close();
				}
			} 
			if(updateQueryEmployee!=0 && updateQueryTeacher!=0)
			{
				connection.commit();
			}
			else
			{
				connection.rollback();
			}

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Teacher_Username_Unique"))
				teacher_id=-2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher_id;
	}

	public int updateTeacher(Teacher_Details teacher_Details, int teacher_id){
		String sEmployee = "update Employees set Payroll_id=?, Department_id=?, Designation=?, Joining_Date=?, Initial_Basic=? where Employee_id=(select employee_id from teachers where teacher_id="+teacher_id+")";
		String sTeacher = "update Teachers set First_Name=?, Last_Name=?, Country_id=?, City=?, PO_Box=?, full_address=?, Gender=?, email=?, tel=?, mobile=?, Birth_Date=?, Username=?, Password=? where Teacher_id="+teacher_id;
		int updateQueryTeacher = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee);
			PreparedStatement pstatementTeacher = connection.prepareStatement(sTeacher);
			Statement statementUserExistInParent = connection.createStatement();
				Statement statementUserExistInStudent = connection.createStatement();
			Statement statementUserExistInAdmin = connection.createStatement();){

		////////////////////////////Create Employee record and get Employee_ID////////////////////////////////////////////////////
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dJoining_Date=null;
		try {
			dJoining_Date = formater.parse(teacher_Details.getJoining_Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(teacher_Details.getPayroll_id()!=0)
		{
			pstatementEmployee.setInt(1, teacher_Details.getPayroll_id());
		}
		else
		{
			pstatementEmployee.setNull(1, java.sql.Types.NULL);
		}
		if(teacher_Details.getDepartment_id()!=0)
		{
			pstatementEmployee.setInt(2, teacher_Details.getDepartment_id());
		}
		else
		{
			pstatementEmployee.setNull(2, java.sql.Types.NULL);
		}		
		pstatementEmployee.setString(3, teacher_Details.getDesignation());
		pstatementEmployee.setDate(4, new Date(dJoining_Date.getTime()));
		pstatementEmployee.setDouble(5, teacher_Details.getInitial_Basic());
		pstatementEmployee.executeUpdate();  
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    String sUserExistInStudent = "select * from Students where Username='"+teacher_Details.getUsername()+"'";
	    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);

	    String sUserExistInParent = "select * from Parents where Username='"+teacher_Details.getUsername()+"'";
	    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);	
	    
	    String sUserExistInAdmin = "select * from Admins where Username='"+teacher_Details.getUsername()+"'";
	    ResultSet resultUserExistInAdmin = statementUserExistInAdmin.executeQuery(sUserExistInAdmin);
	    
	    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInParent.isBeforeFirst() || resultUserExistInAdmin.isBeforeFirst())
	    {
	    	updateQueryTeacher=-1;
	    	return updateQueryTeacher;
	    }	
			String Birth_Date = teacher_Details.getBirth_Date();
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstatementTeacher.setString(1, teacher_Details.getFirst_Name());
			pstatementTeacher.setString(2, teacher_Details.getLast_Name());
			pstatementTeacher.setInt(3, teacher_Details.getCountry_id());
			pstatementTeacher.setString(4, teacher_Details.getCity());
			if(teacher_Details.getPO_Box()!=0)
			{
				pstatementTeacher.setInt(5, teacher_Details.getPO_Box());
			}
			else
			{
				pstatementTeacher.setNull(5, java.sql.Types.NULL);
			}
			pstatementTeacher.setString(6, teacher_Details.getFull_Address());
			pstatementTeacher.setString(7, teacher_Details.getGender());
			pstatementTeacher.setString(8, teacher_Details.getEmail());
			pstatementTeacher.setString(9, teacher_Details.getTel());
			pstatementTeacher.setString(10, teacher_Details.getMobile());
			pstatementTeacher.setDate(11, new Date(Birth_Date_Date.getTime()));
			pstatementTeacher.setString(12, teacher_Details.getUsername());
			pstatementTeacher.setString(13, teacher_Details.getPassword());
			updateQueryTeacher = pstatementTeacher.executeUpdate();

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Teacher_Username_Unique"))
				updateQueryTeacher=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQueryTeacher;
	}
	
	public ArrayList<Integer> getTeacherSchools(int teacher_id){
		String query = "select * from Teacher_School where Teacher_id="+teacher_id;
		ArrayList<Integer> TeacherSchoolsId = new ArrayList<Integer>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				TeacherSchoolsId.add(result.getInt("School_id"));
				
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TeacherSchoolsId;
	}
	public int getTeacherOneSchool(int teacher_id){
		String query = "select * from Teacher_School where Teacher_id="+teacher_id+" LIMIT 1";
		int TeacherOneSchoolId = 0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				TeacherOneSchoolId= result.getInt("School_id");
				
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TeacherOneSchoolId;
	}
	public int createTeacherSchool(int school_id, int teacher_id){
		String query =  "INSERT INTO teacher_school (Teacher_id, School_id) VALUES (?,?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setInt(1, teacher_id);
			pstatement.setInt(2, school_id);
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteTeacherSchool(int school_id, int teacher_id){
		String query = "Delete from teacher_school where teacher_id="+teacher_id+" and School_id="+school_id;
		String sEmployee = "update Employees set Payroll_id = ?, Department_id=? where Employee_id=(select Employee_id from teachers where teacher_id="+teacher_id+") and payroll_id in (Select Payroll_id from Payroll where School_id="+school_id+")";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();){
			PreparedStatement pstatement = connection.prepareStatement(query);	
			PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee);
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				String sDeleteExistingGrade = "Delete from Teacher_Grades where Teacher_id ="+teacher_id+" and Grade_id in (select Grade_id from grades where school_id="+school_id+")";
				pstatement = connection.prepareStatement(sDeleteExistingGrade);
				pstatement.executeUpdate();
				String sDeleteExistingClass = "Delete from Teacher_Classes where Teacher_id ="+teacher_id+" and class_id in (select class_id from classes where grade_id in (select Grade_id from grades where school_id="+school_id+"))";
				pstatement = connection.prepareStatement(sDeleteExistingClass);
				pstatement.executeUpdate();
				String sDeleteExistingCourse = "Delete from Teacher_Classes_Courses where Teacher_id ="+teacher_id+" and class_id in (select class_id from classes where grade_id in (select Grade_id from grades where school_id="+school_id+"))";
				pstatement = connection.prepareStatement(sDeleteExistingCourse);
				pstatement.executeUpdate();
				String sDeleteExistingCourseContent = "Delete from Teacher_Content where Teacher_id ="+teacher_id+" and Course_id in (select course_id from courses where grade_id in (select Grade_id from grades where school_id="+school_id+"))";
				pstatement = connection.prepareStatement(sDeleteExistingCourseContent);
				pstatement.executeUpdate();			

				String sSchedule = "update Schedule set Teacher_id=? where class_id in (select class_id from classes where grade_id in (select Grade_id from grades where school_id="+school_id+")) and Teacher_id ="+teacher_id;
				pstatement = connection.prepareStatement(sSchedule);
				pstatement.setNull(1, java.sql.Types.NULL);
				pstatement.executeUpdate();
				pstatement.close();	
				pstatementEmployee.setNull(1, java.sql.Types.NULL);
				pstatementEmployee.setNull(2, java.sql.Types.NULL);
				pstatementEmployee.executeUpdate();
			}

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteTeacher(int teacher_id){
		String query = "Delete from Teachers where Teacher_id="+teacher_id;
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
	
	public int updateTeacherPassword(int teacher_id, String new_Password){
		String query = "update Teachers set password=?, First_Login=0 where Teacher_id="+teacher_id;
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
	public int getEmployeeTeacher(int employee_id){
		String query = "select Teacher_id from Teachers where Employee_id="+employee_id;
		int teacher_id = 0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				teacher_id = result.getInt("Teacher_id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher_id;
	}
}
