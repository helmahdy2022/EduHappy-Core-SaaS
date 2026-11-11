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
import model.beans.admin.Admin_Details;
import model.beans.admin.Admin_Privilege;
import model.beans.admin.Admins;
import model.beans.admin.Privilege_List;
import model.dbHelpers.DbConnector;

public class AdminManagement {
	public ArrayList<String> getAdminPrivileges(int admin_id){
		ArrayList<String> AdminPrivileges = new ArrayList<String>();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAdminPrivilege = connection.createStatement();){

				String sAdminPrivilege = "Select * from Features where Feature_id in (Select Feature_id from Admin_Features where Admin_id="+admin_id+")";
				ResultSet resultAdminPrivilege = statementAdminPrivilege.executeQuery(sAdminPrivilege);
				while(resultAdminPrivilege.next())
				{
					AdminPrivileges.add(resultAdminPrivilege.getString("Feature_Name"));
				} 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AdminPrivileges;
	}

	public Admins getAdmins(int account_id){
		String query = "select * from Admins where account_id="+account_id+" and account_owner=0";
		
		Admins Admins = new Admins(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementTeacher = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);

			ArrayList<Integer> Admin_id = new ArrayList<Integer>();
			ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	
	    	while(result.next()) 
	    	{
	    		Admin_id.add(result.getInt("Admin_id"));
	    		First_Name.add(result.getString("First_Name")); 		    		
	    		Last_Name.add(result.getString("Last_Name"));    		    		
	    		Username.add(result.getString("Username"));    		
	        } 
	    	Admins.setAdmin_id(Admin_id);
	    	Admins.setFirst_Name(First_Name);
	    	Admins.setLast_Name(Last_Name);
	    	Admins.setUsername(Username);
	    	Admins.setAccount_id(account_id);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Admins;
	}
	public Privilege_List getPrivilegeList(){
		String query = "select * from Features order by Feature_id";
		Privilege_List Privilege_List = new Privilege_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> feature_id = new ArrayList<Integer>();
			ArrayList<String> feature_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
    			feature_id.add(result.getInt("Feature_id"));    		
    			feature_Name.add(result.getString("Feature_Name")); 
		    }
    		Privilege_List.setFeature_id(feature_id);
    		Privilege_List.setFeature_Name(feature_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Privilege_List;
	}

	public Admin_Privilege getAdminPrivilege(int admin_id){
		String query = "select * from Admin_Features where Admin_id="+admin_id;
		Admin_Privilege Admin_Privilege = new Admin_Privilege();
		ArrayList<Integer> feature_id = new ArrayList<Integer>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				feature_id.add(result.getInt("Feature_id"));							
			}
			Admin_Privilege.setFeature_id(feature_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Admin_Privilege;
	}
	public boolean checkAdminPrivilege(int feature_id, int admin_id){
		boolean adminPrivilegeExist = false;
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementAdminPrivilege = connection.createStatement();){

				String sAdminPrivilege = "Select * from Admin_Features where admin_id="+admin_id+" and Feature_id="+feature_id;
				ResultSet resultAdminPrivilege = statementAdminPrivilege.executeQuery(sAdminPrivilege);
				if(resultAdminPrivilege.isBeforeFirst())
				{
					adminPrivilegeExist = true;
				} 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminPrivilegeExist;
	}
	public int createAdminPrivilege(int feature_id, int admin_id){
		String query =  "INSERT INTO Admin_Features (Admin_id, Feature_id) VALUES (?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setInt(1, admin_id);
			pstatement.setInt(2, feature_id);	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteAdminPrivilege(int feature_id, int admin_id){
		String query = "Delete from Admin_Features where Admin_id="+admin_id+" and Feature_id="+feature_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();){
			PreparedStatement pstatement = connection.prepareStatement(query);	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int createAdmin(Admin_Details admin_Details){
		String sEmployee = "INSERT INTO Employees (Employee_id, Account_id, Payroll_id, Department_id, Employee_Group, Designation, Joining_Date, Initial_Basic) VALUES (NULL,?, ?, ?, ?, ?, ?, ?)";
		String sAdmin = "INSERT INTO Admins (Admin_id, First_Name, Last_Name, Country_id, City, PO_Box, full_address, Gender, email, tel, mobile, Birth_Date, Username, Password, employee_id, account_id) VALUES (NULL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		int updateQueryEmployee = 0;
		int updateQueryAdmin = 0;
		int employee_id=-1;
		int admin_id=-1;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstatementAdmin = connection.prepareStatement(sAdmin, Statement.RETURN_GENERATED_KEYS);
				Statement statementUserExistInStudent = connection.createStatement();
				Statement statementUserExistInParent = connection.createStatement();
				Statement statementUserExistInTeacher = connection.createStatement();){

			connection.setAutoCommit(false);
			////////////////////////////Create Employee record and get Employee_ID////////////////////////////////////////////////////
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dJoining_Date=null;
			try {
				dJoining_Date = formater.parse(admin_Details.getJoining_Date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatementEmployee.setInt(1, admin_Details.getAccount_id());
			if(admin_Details.getPayroll_id()!=0)
			{
				pstatementEmployee.setInt(2, admin_Details.getPayroll_id());
			}
			else
			{
				pstatementEmployee.setNull(2, java.sql.Types.NULL);
			}
			if(admin_Details.getDepartment_id()!=0)
			{
				pstatementEmployee.setInt(3, admin_Details.getDepartment_id());
			}
			else
			{
				pstatementEmployee.setNull(3, java.sql.Types.NULL);
			}
			pstatementEmployee.setString(4, "Admin");
			pstatementEmployee.setString(5, admin_Details.getDesignation());
			pstatementEmployee.setDate(6, new Date(dJoining_Date.getTime()));
			pstatementEmployee.setDouble(7, admin_Details.getInitial_Basic());
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
			
			
			String sUserExistInStudent = "select * from Students where Username='"+admin_Details.getUsername()+"'";
		    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);

		    String sUserExistInParent = "select * from Parents where Username='"+admin_Details.getUsername()+"'";
		    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);	
		    
		    String sUserExistInTeacher = "select * from Teachers where Username='"+admin_Details.getUsername()+"'";
		    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);
		    
		    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInParent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst())
		    {
		    	admin_id=-2;
		    	return admin_id;
		    }
			String Birth_Date = admin_Details.getBirth_Date();			
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatementAdmin.setString(1, admin_Details.getFirst_Name());
			pstatementAdmin.setString(2, admin_Details.getLast_Name());
			pstatementAdmin.setInt(3, admin_Details.getCountry_id());
			pstatementAdmin.setString(4, admin_Details.getCity());
			if(admin_Details.getPO_Box()!=0)
			{
				pstatementAdmin.setInt(5, admin_Details.getPO_Box());
			}
			else
			{
				pstatementAdmin.setNull(5, java.sql.Types.NULL);
			}
			pstatementAdmin.setString(6, admin_Details.getFull_Address());
			pstatementAdmin.setString(7, admin_Details.getGender());
			pstatementAdmin.setString(8, admin_Details.getEmail());
			pstatementAdmin.setString(9, admin_Details.getTel());
			pstatementAdmin.setString(10, admin_Details.getMobile());
			pstatementAdmin.setDate(11, new Date(Birth_Date_Date.getTime()));
			pstatementAdmin.setString(12, admin_Details.getUsername());
			pstatementAdmin.setString(13, admin_Details.getPassword());
			pstatementAdmin.setInt(14, employee_id);
			pstatementAdmin.setInt(15, admin_Details.getAccount_id());
			updateQueryAdmin = pstatementAdmin.executeUpdate();
			if(updateQueryAdmin!=0)
			{
				ResultSet resultAdmin_id = pstatementAdmin.getGeneratedKeys();
				if(resultAdmin_id.next()) 
				{
					admin_id = resultAdmin_id.getInt(1);
					resultAdmin_id.close();
				}
			} 
			if(updateQueryEmployee!=0 && updateQueryAdmin!=0)
			{
				connection.commit();
			}
			else
			{
				connection.rollback();
			}

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Admin_Username_Unique"))
				admin_id=-2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin_id;
	}

	public int updateAdmin(Admin_Details admin_Details, int admin_id, String owner_password){
		String sEmployee = "update Employees set Payroll_id=?, Department_id=?, Designation=?, Joining_Date=?, Initial_Basic=? where Employee_id=(select employee_id from Admins where admin_id="+admin_id+")";
		String sAdmin=null;
		if(owner_password==null || owner_password.equals("") || !owner_password.equals("Owner"))
		{
			sAdmin = "update Admins set First_Name=?, Last_Name=?, Country_id=?, City=?, PO_Box=?, full_address=?, Gender=?, email=?, tel=?, mobile=?, Birth_Date=?, Username=?, Password=? where Admin_id="+admin_id;
		}
		else
		{
			sAdmin = "update Admins set First_Name=?, Last_Name=?, Country_id=?, City=?, PO_Box=?, full_address=?, Gender=?, email=?, tel=?, mobile=?, Birth_Date=? where Admin_id="+admin_id;
		}		
		int updateQueryAdmin = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee);
			PreparedStatement pstatementAdmin = connection.prepareStatement(sAdmin);
			Statement statementUserExistInStudent = connection.createStatement();
			Statement statementUserExistInParent = connection.createStatement();
			Statement statementUserExistInTeacher = connection.createStatement();){

		////////////////////////////Create Employee record and get Employee_ID////////////////////////////////////////////////////
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dJoining_Date=null;
		try {
			dJoining_Date = formater.parse(admin_Details.getJoining_Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(admin_Details.getPayroll_id()!=0)
		{
			pstatementEmployee.setInt(1, admin_Details.getPayroll_id());
		}
		else
		{
			pstatementEmployee.setNull(1, java.sql.Types.NULL);
		}
		if(admin_Details.getDepartment_id()!=0)
		{
			pstatementEmployee.setInt(2, admin_Details.getDepartment_id());
		}
		else
		{
			pstatementEmployee.setNull(2, java.sql.Types.NULL);
		}
		pstatementEmployee.setString(3, admin_Details.getDesignation());
		pstatementEmployee.setDate(4, new Date(dJoining_Date.getTime()));
		pstatementEmployee.setDouble(5, admin_Details.getInitial_Basic());
		pstatementEmployee.executeUpdate();  
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    String sUserExistInStudent = "select * from Students where Username='"+admin_Details.getUsername()+"'";
	    ResultSet resultUserExistInStudent = statementUserExistInStudent.executeQuery(sUserExistInStudent);

	    String sUserExistInParent = "select * from Parents where Username='"+admin_Details.getUsername()+"'";
	    ResultSet resultUserExistInParent = statementUserExistInParent.executeQuery(sUserExistInParent);
	    
	    String sUserExistInTeacher = "select * from Teachers where Username='"+admin_Details.getUsername()+"'";
	    ResultSet resultUserExistInTeacher = statementUserExistInTeacher.executeQuery(sUserExistInTeacher);
	    
	    if(resultUserExistInStudent.isBeforeFirst() || resultUserExistInParent.isBeforeFirst() || resultUserExistInTeacher.isBeforeFirst())
	    {
	    	updateQueryAdmin=-1;
	    	return updateQueryAdmin;
	    }	
			String Birth_Date = admin_Details.getBirth_Date();
			java.util.Date Birth_Date_Date=null;
			try {
				Birth_Date_Date = formater.parse(Birth_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstatementAdmin.setString(1, admin_Details.getFirst_Name());
			pstatementAdmin.setString(2, admin_Details.getLast_Name());
			pstatementAdmin.setInt(3, admin_Details.getCountry_id());
			pstatementAdmin.setString(4, admin_Details.getCity());
			if(admin_Details.getPO_Box()!=0)
			{
				pstatementAdmin.setInt(5, admin_Details.getPO_Box());
			}
			else
			{
				pstatementAdmin.setNull(5, java.sql.Types.NULL);
			}
			pstatementAdmin.setString(6, admin_Details.getFull_Address());
			pstatementAdmin.setString(7, admin_Details.getGender());
			pstatementAdmin.setString(8, admin_Details.getEmail());
			pstatementAdmin.setString(9, admin_Details.getTel());
			pstatementAdmin.setString(10, admin_Details.getMobile());
			pstatementAdmin.setDate(11, new Date(Birth_Date_Date.getTime()));
			if(owner_password==null || owner_password.equals("") || !owner_password.equals("Owner"))
			{
				pstatementAdmin.setString(12, admin_Details.getUsername());
				pstatementAdmin.setString(13, admin_Details.getPassword());
			}
			updateQueryAdmin = pstatementAdmin.executeUpdate();

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Admin_Username_Unique"))
				updateQueryAdmin=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQueryAdmin;
	}
	public int deleteAdmin(int admin_id){
		String query = "Delete from Admins where Admin_id="+admin_id;
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
	public boolean checkAdminSchool(int school_id, int admin_id){
		String query = "select school_id from admin_school where Admin_id="+admin_id+" and School_id="+school_id;
		boolean adminSchoolExist=false;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			if (result.isBeforeFirst())
			{
				adminSchoolExist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminSchoolExist;
	}
	public int createAdminSchool(int school_id, int admin_id){
		String query =  "INSERT INTO admin_school (Admin_id, School_id) VALUES (?,?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setInt(1, admin_id);
			pstatement.setInt(2, school_id);
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteAdminSchool(int school_id, int admin_id){
		String query = "Delete from admin_school where Admin_id="+admin_id+" and School_id="+school_id;
		String sEmployee = "update Employees set Payroll_id = ?, Department_id=? where Employee_id=(select Employee_id from Admins where admin_id="+admin_id+") and payroll_id in (Select Payroll_id from Payroll where School_id="+school_id+")";
			
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();){
			PreparedStatement pstatement = connection.prepareStatement(query);
			PreparedStatement pstatementEmployee = connection.prepareStatement(sEmployee);
			updateQuery = pstatement.executeUpdate();
			pstatementEmployee.setNull(1, java.sql.Types.NULL);
			pstatementEmployee.setNull(2, java.sql.Types.NULL);
			pstatementEmployee.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Admin_Details getAdmin(int admin_id){
		String sEmployee = "Select * from Employees where employee_id=(select employee_id from Admins where Admin_id="+admin_id+")";
		String sAdmin = "select *, DATE_FORMAT(Birth_date, '%Y-%m-%d') AS B_date from Admins where Admin_id="+admin_id;
		
		Admin_Details Admin_Details = new Admin_Details();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementEmployee = connection.createStatement();
				Statement statementAdmin = connection.createStatement();
				Statement statementCountry = connection.createStatement();){
						
			///////////////////////////////Get Employee details///////////////////////////////////////////
			ResultSet resultEmployee = statementEmployee.executeQuery(sEmployee);
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			ResultSet resultAdmin = statementAdmin.executeQuery(sAdmin);
			///////////////populate the bean ///////////////////////
			while(resultAdmin.next())
			{
				while(resultEmployee.next())
				{				
					Admin_Details.setPayroll_id(resultEmployee.getInt("Payroll_id"));
					Admin_Details.setDepartment_id(resultEmployee.getInt("Department_id"));
					Admin_Details.setDesignation(resultEmployee.getString("Designation"));
					Admin_Details.setJoining_Date(resultEmployee.getString("Joining_Date"));
					Admin_Details.setInitial_Basic(resultEmployee.getDouble("Initial_Basic"));
				}					
				Admin_Details.setAccount_id(resultAdmin.getInt("account_id"));
				Admin_Details.setAdmin_id(admin_id);
				Admin_Details.setFirst_Name(resultAdmin.getString("first_Name"));
				Admin_Details.setLast_Name(resultAdmin.getString("last_Name"));
				Admin_Details.setCountry_id(resultAdmin.getInt("Country_id"));
				Admin_Details.setCity(resultAdmin.getString("City"));
				Admin_Details.setPO_Box(resultAdmin.getInt("pO_Box"));
				Admin_Details.setFull_Address(resultAdmin.getString("full_Address"));
				Admin_Details.setGender(resultAdmin.getString("gender"));
				Admin_Details.setEmail(resultAdmin.getString("email"));
				Admin_Details.setTel(resultAdmin.getString("tel"));
				Admin_Details.setMobile(resultAdmin.getString("mobile"));
				Admin_Details.setBirth_Date(resultAdmin.getString("B_Date"));
				Admin_Details.setUsername(resultAdmin.getString("username"));
				Admin_Details.setPassword(resultAdmin.getString("password"));
				Admin_Details.setAccount_Owner(resultAdmin.getInt("Account_Owner"));
				
				String sCountry = "Select Country_Name from Country where country_id="+resultAdmin.getInt("Country_id");
				ResultSet resultCountry = statementCountry.executeQuery(sCountry);
				String country_Name = null;
				while(resultCountry.next())
				{
					country_Name = resultCountry.getString("Country_Name");
				}
				
				Admin_Details.setCountry_Name(country_Name);
		    	Admin_Details.setAccount_id(resultAdmin.getInt("Account_id"));
		    	Admin_Details.setFileName(resultAdmin.getString("Fake_File_Name"));
			}
			///////////////end populate the bean ///////////////////////

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Admin_Details;
	}
	public ArrayList<Integer> getAdminSchools(int admin_id){
		String query = "select * from Admin_School where Admin_id="+admin_id;
		ArrayList<Integer> AdminSchoolsId = new ArrayList<Integer>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				AdminSchoolsId.add(result.getInt("School_id"));
				
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AdminSchoolsId;
	}
	public int getEmployeeAdmin(int employee_id){
		String query = "select Admin_id from Admins where Employee_id="+employee_id;
		int admin_id = 0;
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
}
