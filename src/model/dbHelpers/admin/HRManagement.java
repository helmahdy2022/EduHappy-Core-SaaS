package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Allowance_Details;
import model.beans.admin.Allowance_List;
import model.beans.admin.Deduction_Details;
import model.beans.admin.Deduction_List;
import model.beans.admin.Department_Details;
import model.beans.admin.Department_List;
import model.beans.admin.Employee_Allowance;
import model.beans.admin.Employee_Deduction;
import model.beans.admin.Employee_Leave_List;
import model.beans.admin.Employee_Promotion_List;
import model.beans.admin.Employees;
import model.beans.admin.Leave_Balance_List;
import model.beans.admin.Leave_Details;
import model.beans.admin.Leave_List;
import model.beans.admin.Overtime_List;
import model.dbHelpers.DbConnector;

public class HRManagement {
	public int createDepartment(Department_Details Department_Details){
		String query = "INSERT INTO Departments (Department_id, Department_Name, Department_Description, School_id) VALUES (NULL, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, Department_Details.getDepartment_Name());
			if(Department_Details.getDepartment_Description()!=null && !Department_Details.getDepartment_Description().equals(""))
			{
				pstatement.setString(2, Department_Details.getDepartment_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Department_Details.getSchool_id());
			updateQuery = pstatement.executeUpdate();
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateDepartment(Department_Details Department_Details, int department_id){
		String query = "update Departments set Department_Name=?, Department_Description=?, School_id=? where Department_id="+department_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, Department_Details.getDepartment_Name());
			if(Department_Details.getDepartment_Description()!=null && !Department_Details.getDepartment_Description().equals(""))
			{
				pstatement.setString(2, Department_Details.getDepartment_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Department_Details.getSchool_id());
			updateQuery = pstatement.executeUpdate();
	       
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteDepartment(int department_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Departments where Department_id="+department_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Department_List getDepartmentList(int school_id ){
		String query = "select * from Departments where school_id="+school_id;
		Department_List Department_List = new Department_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> department_id = new ArrayList<Integer>();
			ArrayList<String> department_Name = new ArrayList<String>();
			ArrayList<String> department_Description = new ArrayList<String>();
    		while (result.next())
	    	{			
    			department_id.add(result.getInt("Department_id"));    		
    			department_Name.add(result.getString("Department_Name")); 
    			department_Description.add(result.getString("Department_Description"));

		    }
    		Department_List.setDepartment_id(department_id);
    		Department_List.setDepartment_Name(department_Name);
    		Department_List.setDepartment_Description(department_Description);
    		Department_List.setSchool_id(school_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Department_List;
	}
	public Department_List getDepartmentsByPayroll(int payroll_id ){
		String query = "select * from Departments where school_id=(select school_id from payroll where payroll_id="+payroll_id+")";
		Department_List Department_List = new Department_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> department_id = new ArrayList<Integer>();
			ArrayList<String> department_Name = new ArrayList<String>();
			ArrayList<String> department_Description = new ArrayList<String>();
    		while (result.next())
	    	{			
    			department_id.add(result.getInt("Department_id"));    		
    			department_Name.add(result.getString("Department_Name")); 
    			department_Description.add(result.getString("Department_Description"));

		    }
    		Department_List.setDepartment_id(department_id);
    		Department_List.setDepartment_Name(department_Name);
    		Department_List.setDepartment_Description(department_Description);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Department_List;
	}
	public int applyEmployeePromotion(int employee_id, Date dPromotion_Date, double basic_Salary, String promotion_Designation){
		String query = "INSERT INTO Employee_Promotion (Employee_Promotion_id, Employee_id, Promotion_Date, Basic_Salary, Promotion_Designation) VALUES (NULL, ?, ?, ?, ?)";

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setInt(1, employee_id);
			pstatement.setDate(2, new java.sql.Date(dPromotion_Date.getTime()));
			pstatement.setDouble(3, basic_Salary);
			if(promotion_Designation!=null && !promotion_Designation.equals(""))
			{
				pstatement.setString(4, promotion_Designation);
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}		
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateEmployeePromotion(int employee_Promotion_id, Date dPromotion_Date, double basic_Salary, String promotion_Designation){
		String query = "update Employee_Promotion set Promotion_Date=?, Basic_Salary=?, Promotion_Designation=? where Employee_Promotion_id="+employee_Promotion_id;

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setDate(1, new java.sql.Date(dPromotion_Date.getTime()));
			pstatement.setDouble(2, basic_Salary);	
			if(promotion_Designation!=null && !promotion_Designation.equals(""))
			{
				pstatement.setString(3, promotion_Designation);
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}	
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteEmployeePromotion(int employee_Promotion_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Employee_Promotion where Employee_Promotion_id="+employee_Promotion_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Employee_Promotion_List getEmployeePromotionList(int employee_id){
		String query = "select * from Employee_Promotion where employee_id="+employee_id+ " order by Promotion_Date desc";
		Employee_Promotion_List Employee_Promotion_List = new Employee_Promotion_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> employee_Promotion_id = new ArrayList<Integer>();
			ArrayList<String> promotion_Date = new ArrayList<String>();
			ArrayList<Double> basic_Salary = new ArrayList<Double>();
			ArrayList<String> promotion_Designation = new ArrayList<String>();
    		while (result.next())
	    	{			
    			employee_Promotion_id.add(result.getInt("Employee_Promotion_id"));    		
    			promotion_Date.add(result.getString("Promotion_Date")); 
    			basic_Salary.add(result.getDouble("Basic_Salary"));
    			promotion_Designation.add(result.getString("Promotion_Designation"));
		    }
    		Employee_Promotion_List.setEmployee_Promotion_id(employee_Promotion_id);
    		Employee_Promotion_List.setPromotion_Date(promotion_Date);
    		Employee_Promotion_List.setBasic_Salary(basic_Salary);
    		Employee_Promotion_List.setPromotion_Designation(promotion_Designation);
    		Employee_Promotion_List.setEmployee_id(employee_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Employee_Promotion_List;
	}
	
	public int createLeave(Leave_Details Leave_Details){
		String query = "INSERT INTO Leaves (Leave_id, Leave_Name, Leave_Description, Payroll_id, Payment_Type, Period, Carry_forward, CF_Effective_Year, All_Employees_Balance) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sBalance = "INSERT INTO Leave_Balance (Leave_Balance_id, Balance_Years, Balance_Count, Leave_id) VALUES (NULL, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement pstatementBalance = connection.prepareStatement(sBalance);){

			pstatement.setString(1, Leave_Details.getLeave_Name());
			if(Leave_Details.getLeave_Description()!=null && !Leave_Details.getLeave_Description().equals(""))
			{
				pstatement.setString(2, Leave_Details.getLeave_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Leave_Details.getPayroll_id());
			pstatement.setString(4, Leave_Details.getPayment_Type());
			pstatement.setString(5, Leave_Details.getPeriod());
			if(Leave_Details.getCarry_Forward()!=-1)
			{
				pstatement.setDouble(6, Leave_Details.getCarry_Forward());
				/////Insert Carry Forward Effective Date ///////////////////////
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int cF_Effective_Year = cal.get(Calendar.YEAR);
				pstatement.setInt(7, cF_Effective_Year);
				////////////////////////////////////////////////////////////////
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
				pstatement.setNull(7, java.sql.Types.NULL);
			}
			if(Leave_Details.getAll_Employees_Balance()!=-1)
			{
				pstatement.setDouble(8, Leave_Details.getAll_Employees_Balance());
				updateQuery = pstatement.executeUpdate();
			}			
			else
			{
				pstatement.setNull(8, java.sql.Types.NULL);
				updateQuery = pstatement.executeUpdate();
				//////////////////////////Create Balance based on Employee Experience//////////////////////////
				int leave_id=0;
				ResultSet resultLeave_id = pstatement.getGeneratedKeys();
				if(resultLeave_id.next()) 
				{
					leave_id = resultLeave_id.getInt(1);
					resultLeave_id.close();
				}
				Iterator<Double> balance_Years = Leave_Details.getBalance_Years_List().iterator();
				Iterator<Double> balance_Count = Leave_Details.getBalance_Count_List().iterator();
				while (balance_Years.hasNext() && balance_Count.hasNext()) {
					pstatementBalance.setDouble(1, balance_Years.next());
					pstatementBalance.setDouble(2, balance_Count.next());
					pstatementBalance.setInt(3, leave_id);
					pstatementBalance.executeUpdate();
				}
				//////////////////////////////////////////////////////////////////////////////////////////////				
			}

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Leave_Name_Unique"))
			{
				updateQuery=-1;	
			}
			if(ee.toString().contains("Balance_Years_Unique"))
			{
				updateQuery=-2;	
			}			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateLeave(Leave_Details Leave_Details, int leave_id){
		String query = "update Leaves set Leave_Name=?, Leave_Description=?, Payroll_id=?, Payment_Type=?, Period=?, Carry_Forward=?, All_Employees_Balance=? where Leave_id="+leave_id;
		String sBalanceCreate = "INSERT INTO Leave_Balance (Leave_Balance_id, Balance_Years, Balance_Count, Leave_id) VALUES (NULL, ?, ?, ?)";
		String sDeleteLeaveBalance = "Delete from Leave_Balance where Leave_id="+leave_id;	
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
			 PreparedStatement pstatementBalanceCreate = connection.prepareStatement(sBalanceCreate);
			 PreparedStatement pstatementDeleteLeaveBalance = connection.prepareStatement(sDeleteLeaveBalance);){
	
			pstatement.setString(1, Leave_Details.getLeave_Name());
			if(Leave_Details.getLeave_Description()!=null && !Leave_Details.getLeave_Description().equals(""))
			{
				pstatement.setString(2, Leave_Details.getLeave_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Leave_Details.getPayroll_id());
			pstatement.setString(4, Leave_Details.getPayment_Type());
			pstatement.setString(5, Leave_Details.getPeriod());
			if(Leave_Details.getCarry_Forward()!=-1)
			{
				pstatement.setDouble(6, Leave_Details.getCarry_Forward());
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
			}
			if(Leave_Details.getAll_Employees_Balance()!=-1)
			{
				pstatement.setDouble(7, Leave_Details.getAll_Employees_Balance());
				updateQuery = pstatement.executeUpdate();
				pstatementDeleteLeaveBalance.executeUpdate();
			}
			else
			{
				pstatement.setNull(7, java.sql.Types.NULL);
				updateQuery = pstatement.executeUpdate();
				//////////////////////////Create or Update Balance based on Employee Experience//////////////////////////
				Iterator<Integer> leave_Balance_id = Leave_Details.getLeave_Balance_id_List().iterator();
				Iterator<Double> balance_Years = Leave_Details.getBalance_Years_List().iterator();
				Iterator<Double> balance_Count = Leave_Details.getBalance_Count_List().iterator();
				while (balance_Years.hasNext() && balance_Count.hasNext()) 
				{
					int Leave_Balance_id = leave_Balance_id.next();
					double Balance_Years = balance_Years.next();
					double Balance_Count = balance_Count.next();
					if(Leave_Balance_id==0)
					{
						pstatementBalanceCreate.setDouble(1, Balance_Years);
						pstatementBalanceCreate.setDouble(2, Balance_Count);
						pstatementBalanceCreate.setInt(3, leave_id);
						pstatementBalanceCreate.executeUpdate();
					}
					else
					{
						String sBalanceUpdate = "Update Leave_Balance set Balance_Years=?, Balance_Count=? where Leave_Balance_id="+Leave_Balance_id;
						PreparedStatement pstatementBalanceUpdate = connection.prepareStatement(sBalanceUpdate);
						pstatementBalanceUpdate.setDouble(1, Balance_Years);
						pstatementBalanceUpdate.setDouble(2, Balance_Count);
						//pstatementBalanceUpdate.setInt(3, leave_id);
						pstatementBalanceUpdate.executeUpdate();
						pstatementBalanceUpdate.close();
					}
				}				
			}
			//////////////////////////////////////////////////////////////////////////////////////////////
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteLeave(int leave_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Leaves where Leave_id="+leave_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Leave_List getLeaveList(int payroll_id ){
		String query = "select * from Leaves where payroll_id="+payroll_id;
		Leave_List Leave_List = new Leave_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementBalanceNo = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> leave_id = new ArrayList<Integer>();
			ArrayList<String> leave_Name = new ArrayList<String>();
			ArrayList<String> leave_Description = new ArrayList<String>();
			ArrayList<String> payment_Type = new ArrayList<String>();
			ArrayList<String> period = new ArrayList<String>();
			ArrayList<Double> carry_Forward = new ArrayList<Double>();
			ArrayList<Double> all_Employees_Balance = new ArrayList<Double>();
			ArrayList<Integer> balance_no = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			leave_id.add(result.getInt("Leave_id"));    		
    			leave_Name.add(result.getString("Leave_Name")); 
    			leave_Description.add(result.getString("Leave_Description"));
    			payment_Type.add(result.getString("Payment_Type"));
    			period.add(result.getString("Period"));
    			carry_Forward.add(result.getDouble("Carry_Forward"));
    			all_Employees_Balance.add(result.getDouble("All_Employees_Balance"));
    			//////////////Get Leave Balance quantity if any////////////////
    			String sBalanceNo = "select count(Leave_Balance_id) from Leave_Balance where Leave_id="+result.getInt("Leave_id");
    			ResultSet resultBalanceNo = statementBalanceNo.executeQuery(sBalanceNo);
    			int Balance_no=0;
    			while(resultBalanceNo.next())
    			{
    				Balance_no = resultBalanceNo.getInt(1);
    			}
    			balance_no.add(Balance_no);
    			//////////////////////////////////////////////////////////////////

		    }
    		Leave_List.setLeave_id(leave_id);
    		Leave_List.setLeave_Name(leave_Name);
    		Leave_List.setLeave_Description(leave_Description);
    		Leave_List.setPayment_Type(payment_Type);
    		Leave_List.setPeriod(period);
    		Leave_List.setCarry_Forward(carry_Forward);
    		Leave_List.setAll_Employees_Balance(all_Employees_Balance);
    		Leave_List.setPayroll_id(payroll_id);
    		Leave_List.setBalance_no(balance_no);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Leave_List;
	}
	public Leave_Balance_List getLeaveBalance(int leave_id ){
		String query = "select * from Leave_Balance where leave_id="+leave_id+" order by Balance_Years";
		Leave_Balance_List Leave_Balance_List = new Leave_Balance_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> leave_Balance_id = new ArrayList<Integer>();
			ArrayList<Double> balance_Years = new ArrayList<Double>();
			ArrayList<Double> balance_Count = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			leave_Balance_id.add(result.getInt("Leave_Balance_id"));    		
    			balance_Years.add(result.getDouble("Balance_Years")); 
    			balance_Count.add(result.getDouble("Balance_Count"));
		    }
    		Leave_Balance_List.setLeave_Balance_id(leave_Balance_id);
    		Leave_Balance_List.setBalance_Years(balance_Years);
    		Leave_Balance_List.setBalance_Count(balance_Count);
    		Leave_Balance_List.setLeave_id(leave_id);
    		Leave_Balance_List.setBalance_no(leave_Balance_id.size());
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Leave_Balance_List;
	}
	public int deleteLeaveBalance(int leave_Balance_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Leave_Balance where Leave_Balance_id="+leave_Balance_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Employees getAllEmployees(int account_id, int admin_id){
		//String query = "select * from Employees where account_id="+account_id;
		//String query = "select * from Employees where account_id="+account_id+" and Employee_id in (Select Employee_id from Admins where Admin_id in (Select Admin_id from Admin_School where Admin_id="+admin_id+" and school_id in (select School_id from Teacher_School where Teacher_id in(Select Teacher_id from teachers where employee_id in (Select Employee_id from Employees where Account_id="+account_id+")))))";
		String query = "select * from Employees where account_id="+account_id+" and Employee_id in (Select Employee_id from Admins where Admin_id in (Select Admin_id from Admin_School where school_id in (select school_id from Admin_School where Admin_id="+admin_id+"))) or Employee_id in (select Employee_id from Teachers where Teacher_id in (Select Teacher_id from teacher_school where school_id in(select school_id from admin_school where Admin_id="+admin_id+")))";
		Employees Employees = new Employees(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementTeacher = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);

			ArrayList<Integer> Employee_id = new ArrayList<Integer>();
			ArrayList<Integer> TeacherAndAdmin_id = new ArrayList<Integer>();
			//ArrayList<Integer> Admin_id = new ArrayList<Integer>();
			ArrayList<String> Employee_Group = new ArrayList<String>();
			ArrayList<String> First_Name = new ArrayList<String>();
	    	ArrayList<String> Last_Name = new ArrayList<String>();
	    	ArrayList<String> Username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
	    	
	    	while(result.next()) 
	    	{
	    		Employee_id.add(result.getInt("Employee_id"));
				Employee_Group.add(result.getString("Employee_Group"));
	    		if(result.getString("Employee_Group").equals("Teacher"))
	    		{
	    			String sTeacher = "select * from Teachers where employee_id="+result.getInt("Employee_id");
	    			ResultSet resultTeacher = statementTeacher.executeQuery(sTeacher);
	    			while(resultTeacher.next())
	    			{
	    				TeacherAndAdmin_id.add(resultTeacher.getInt("Teacher_id"));
	    	    		First_Name.add(resultTeacher.getString("First_Name")); 		    		
	    	    		Last_Name.add(resultTeacher.getString("Last_Name"));    		    		
	    	    		Username.add(resultTeacher.getString("Username"));    		
	    		    	Account_id.add(account_id);
	    		    	FileName.add(resultTeacher.getString("Fake_File_Name"));
	    			}	    			
	    		}
	    		else
	    		{
	    			String sAdmin = "select * from Admins where employee_id="+result.getInt("Employee_id");
	    			ResultSet resultAdmin = statementTeacher.executeQuery(sAdmin);
	    			while(resultAdmin.next())
	    			{
	    				TeacherAndAdmin_id.add(resultAdmin.getInt("Admin_id"));
	    	    		First_Name.add(resultAdmin.getString("First_Name")); 		    		
	    	    		Last_Name.add(resultAdmin.getString("Last_Name"));    		    		
	    	    		Username.add(resultAdmin.getString("Username"));    		
	    		    	Account_id.add(account_id);
	    		    	FileName.add(resultAdmin.getString("Fake_File_Name"));
	    			}	
	    		}
	        } 
	    	Employees.setEmployee_id(Employee_id);
	    	Employees.setTeacherAndAdmin_id(TeacherAndAdmin_id);
	    	//Employees.setAdmin_id(Admin_id);
	    	Employees.setEmployee_Group(Employee_Group);
	    	Employees.setFirst_Name(First_Name);
	    	Employees.setLast_Name(Last_Name);
	    	Employees.setUsername(Username);
	    	Employees.setAccount_id(Account_id);
	    	Employees.setFileName(FileName);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Employees;
	}

	public int getEmployeePayroll(int employee_id){
		String query = "select payroll_id from Employees where employee_id="+employee_id;		
		int payroll_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
    	
	    	while(result.next()) 
	    	{
	    		payroll_id = result.getInt("Payroll_id");
	        }     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payroll_id;
	}
	public Date getEmployeeJoiningDate(int employee_id){
		String query = "select Joining_Date from Employees where employee_id="+employee_id;		
		Date joining_Date=null;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
    	
	    	while(result.next()) 
	    	{
	    		joining_Date = result.getDate("Joining_Date");
	        }     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return joining_Date;
	}	
	public Leave_Details getLeaveDetails(int leave_id){
		Leave_Details Leave_Details = new Leave_Details();
		String query = "select * from Leaves where leave_id="+leave_id;		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
				
			ResultSet result = statement.executeQuery(query);
    	
	    	while(result.next()) 
	    	{
	    		Leave_Details.setPayroll_id(result.getInt("Payroll_id"));
	    		Leave_Details.setLeave_Name(result.getString("Leave_Name"));
	    		Leave_Details.setLeave_Description(result.getString("Leave_Description"));
	    		Leave_Details.setPayment_Type(result.getString("Payment_Type"));
	    		Leave_Details.setPeriod(result.getString("Period"));
	    		Leave_Details.setCarry_Forward(result.getDouble("Carry_Forward"));
	    		Leave_Details.setCF_Effective_Year(result.getInt("CF_Effective_Year"));
	    		if(result.getString("All_Employees_Balance")!=null)
	    		{
		    		Leave_Details.setAll_Employees_Balance(result.getDouble("All_Employees_Balance"));
	    		}	    		
	        }     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Leave_Details;
	}
	
	public double getEmployeeYearlyTakenLeave(int employee_id, int leave_id, int year_From, int employee_Leave_id){
		String query = "select sum(Leave_Days) from Employee_Leave where EXTRACT(YEAR FROM Leave_From)="+year_From+" and employee_id="+employee_id+" and leave_id="+leave_id+" and Employee_Leave_id<>"+employee_Leave_id;		
		double employee_Taken_Leave=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
    	
	    	while(result.next()) 
	    	{
	    		employee_Taken_Leave = result.getDouble(1);
	        }     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_Taken_Leave;
	}
	public double getEmployeeMonthlyTakenLeave(int employee_id, int leave_id, int month_From, int year_From, int employee_Leave_id){
		String query = "select sum(Leave_Days) from Employee_Leave where EXTRACT(MONTH FROM Leave_From)="+(month_From+1)+" and EXTRACT(YEAR FROM Leave_From)="+year_From+" and employee_id="+employee_id+" and leave_id="+leave_id+" and Employee_Leave_id<>"+employee_Leave_id;		
		double employee_Taken_Leave=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
    	
	    	while(result.next()) 
	    	{
	    		employee_Taken_Leave = result.getDouble(1);
	        }     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_Taken_Leave;
	}
	public double getBalanceByExperience(int leave_id, double years_On_Board){
		String query = "select * from Leave_Balance where leave_id="+leave_id+" order by Balance_Years";
		double entitled_Balance = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
    		while (result.next())
	    	{			
    			if(years_On_Board >= result.getDouble("Balance_Years"))
    			{
    				entitled_Balance = result.getDouble("Balance_Count");
    			}
		    }       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entitled_Balance;
	}
	public int applyEmployeeLeave(int employee_id, int leave_id, Date dLeave_From ,Date dLeave_To, double leave_Days){
		String query = "INSERT INTO Employee_Leave (Employee_Leave_id, Employee_id, Leave_id, Leave_From, Leave_To, Leave_Days) VALUES (NULL, ?, ?, ?, ?, ?)";

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setInt(1, employee_id);
			pstatement.setInt(2, leave_id);
			pstatement.setDate(3, new java.sql.Date(dLeave_From.getTime()));
			pstatement.setDate(4, new java.sql.Date(dLeave_To.getTime()));
			pstatement.setDouble(5, leave_Days);			
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateEmployeeLeave(int employee_Leave_id, Date dLeave_From ,Date dLeave_To, double leave_Days){
		String query = "update Employee_Leave set Leave_From=?, Leave_To=?, Leave_Days=? where employee_Leave_id="+employee_Leave_id;

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setDate(1, new java.sql.Date(dLeave_From.getTime()));
			pstatement.setDate(2, new java.sql.Date(dLeave_To.getTime()));
			pstatement.setDouble(3, leave_Days);			
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteEmployeeLeave(int employee_Leave_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Employee_Leave where Employee_Leave_id="+employee_Leave_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Employee_Leave_List getEmployeeLeaveList(int employee_id, int leave_id){
		String query = "select * from Employee_Leave where employee_id="+employee_id+" and leave_id="+leave_id+" order by Leave_From desc";
		Employee_Leave_List Employee_Leave_List = new Employee_Leave_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementBalanceNo = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> employee_Leave_id = new ArrayList<Integer>();
			ArrayList<String> leave_From = new ArrayList<String>();
			ArrayList<String> leave_To = new ArrayList<String>();
			ArrayList<Double> leave_Days = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			employee_Leave_id.add(result.getInt("Employee_Leave_id"));    		
    			leave_From.add(result.getString("Leave_From")); 
    			leave_To.add(result.getString("Leave_To"));
    			leave_Days.add(result.getDouble("Leave_Days"));
		    }
    		Employee_Leave_List.setEmployee_Leave_id(employee_Leave_id);
    		Employee_Leave_List.setLeave_From(leave_From);
    		Employee_Leave_List.setLeave_To(leave_To);
    		Employee_Leave_List.setLeave_Days(leave_Days);
    		Employee_Leave_List.setEmployee_id(employee_id);
    		Employee_Leave_List.setLeave_id(leave_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Employee_Leave_List;
	}

	public int applyOvertime(int employee_id, Date dOvertime_Date, double overtime_Hours){
		String query = "INSERT INTO Overtime (Overtime_id, Employee_id, Overtime_Date, Overtime_Hours) VALUES (NULL, ?, ?, ?)";

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setInt(1, employee_id);
			pstatement.setDate(2, new java.sql.Date(dOvertime_Date.getTime()));
			pstatement.setDouble(3, overtime_Hours);			
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateOvertime(int overtime_id, Date dOvertime_Date, double overtime_Hours){
		String query = "update Overtime set Overtime_Date=?, Overtime_Hours=? where Overtime_id="+overtime_id;

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setDate(1, new java.sql.Date(dOvertime_Date.getTime()));
			pstatement.setDouble(2, overtime_Hours);			
			pstatement.executeUpdate();
	       
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteOvertime(int overtime_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Overtime where Overtime_id="+overtime_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Overtime_List getOvertimeList(int employee_id){
		String query = "select * from Overtime where employee_id="+employee_id+ " order by Overtime_Date desc";
		Overtime_List Overtime_List = new Overtime_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> overtime_id = new ArrayList<Integer>();
			ArrayList<String> overtime_Date = new ArrayList<String>();
			ArrayList<Double> overtime_Hours = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			overtime_id.add(result.getInt("Overtime_id"));    		
    			overtime_Date.add(result.getString("Overtime_Date")); 
    			overtime_Hours.add(result.getDouble("Overtime_Hours"));
		    }
    		Overtime_List.setOvertime_id(overtime_id);
    		Overtime_List.setOvertime_Date(overtime_Date);
    		Overtime_List.setOvertime_Hours(overtime_Hours);
    		Overtime_List.setEmployee_id(employee_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Overtime_List;
	}

	public int createAllowance(Allowance_Details Allowance_Details){
		String query = "INSERT INTO Allowance (Allowance_id, Allowance_Name, Allowance_Description, Allowance_Type, Amount, Period, Payroll_id) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, Allowance_Details.getAllowance_Name());
			if(Allowance_Details.getAllowance_Description()!=null && !Allowance_Details.getAllowance_Description().equals(""))
			{
				pstatement.setString(2, Allowance_Details.getAllowance_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setString(3, Allowance_Details.getAllowance_Type());
			if(Allowance_Details.getAmount()!=-1)
			{
				pstatement.setDouble(4, Allowance_Details.getAmount());
			}			
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);		
			}
			pstatement.setString(5, Allowance_Details.getPeriod());
			pstatement.setInt(6, Allowance_Details.getPayroll_id());
			updateQuery = pstatement.executeUpdate();	
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
				updateQuery=-1;	
				
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateAllowance(Allowance_Details Allowance_Details, int allowance_id){
		String query = "update Allowance set Allowance_Name=?, Allowance_Description=?, Allowance_Type=?, Amount=?, Period=?, Payroll_id=? where Allowance_id="+allowance_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, Allowance_Details.getAllowance_Name());
			if(Allowance_Details.getAllowance_Description()!=null && !Allowance_Details.getAllowance_Description().equals(""))
			{
				pstatement.setString(2, Allowance_Details.getAllowance_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setString(3, Allowance_Details.getAllowance_Type());
			if(Allowance_Details.getAmount()!=-1)
			{
				pstatement.setDouble(4, Allowance_Details.getAmount());
			}			
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);		
			}
			pstatement.setString(5, Allowance_Details.getPeriod());
			pstatement.setInt(6, Allowance_Details.getPayroll_id());
			updateQuery = pstatement.executeUpdate();
			//////////////////////////////////////////////////////////////////////////////////////////////
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteAllowance(int allowance_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Allowance where Allowance_id="+allowance_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Allowance_List getAllowanceList(int payroll_id ){
		String query = "select * from Allowance where payroll_id="+payroll_id;
		Allowance_List Allowance_List = new Allowance_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> allowance_id = new ArrayList<Integer>();
			ArrayList<String> allowance_Name = new ArrayList<String>();
			ArrayList<String> allowance_Description = new ArrayList<String>();
			ArrayList<String> allowance_Type = new ArrayList<String>();
			ArrayList<String> period = new ArrayList<String>();
			ArrayList<Double> amount = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			allowance_id.add(result.getInt("Allowance_id"));    		
    			allowance_Name.add(result.getString("Allowance_Name")); 
    			allowance_Description.add(result.getString("Allowance_Description"));
    			allowance_Type.add(result.getString("Allowance_Type"));
    			period.add(result.getString("Period"));
    			amount.add(result.getDouble("Amount"));
		    }
    		Allowance_List.setAllowance_id(allowance_id);
    		Allowance_List.setAllowance_Name(allowance_Name);
    		Allowance_List.setAllowance_Description(allowance_Description);
    		Allowance_List.setAllowance_Type(allowance_Type);
    		Allowance_List.setPeriod(period);
    		Allowance_List.setAmount(amount);
    		Allowance_List.setPayroll_id(payroll_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Allowance_List;
	}

	public int createDeduction(Deduction_Details Deduction_Details){
		String query = "INSERT INTO Deduction (Deduction_id, Deduction_Name, Deduction_Description, Deduction_Type, Amount, Period, Payroll_id) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, Deduction_Details.getDeduction_Name());
			if(Deduction_Details.getDeduction_Description()!=null && !Deduction_Details.getDeduction_Description().equals(""))
			{
				pstatement.setString(2, Deduction_Details.getDeduction_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setString(3, Deduction_Details.getDeduction_Type());
			if(Deduction_Details.getAmount()!=-1)
			{
				pstatement.setDouble(4, Deduction_Details.getAmount());
			}			
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);		
			}
			pstatement.setString(5, Deduction_Details.getPeriod());
			pstatement.setInt(6, Deduction_Details.getPayroll_id());
			updateQuery = pstatement.executeUpdate();	
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
				updateQuery=-1;	
				
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateDeduction(Deduction_Details Deduction_Details, int deduction_id){
		String query = "update Deduction set Deduction_Name=?, Deduction_Description=?, Deduction_Type=?, Amount=?, Period=?, Payroll_id=? where Deduction_id="+deduction_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, Deduction_Details.getDeduction_Name());
			if(Deduction_Details.getDeduction_Description()!=null && !Deduction_Details.getDeduction_Description().equals(""))
			{
				pstatement.setString(2, Deduction_Details.getDeduction_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setString(3, Deduction_Details.getDeduction_Type());
			if(Deduction_Details.getAmount()!=-1)
			{
				pstatement.setDouble(4, Deduction_Details.getAmount());
			}			
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);		
			}
			pstatement.setString(5, Deduction_Details.getPeriod());
			pstatement.setInt(6, Deduction_Details.getPayroll_id());
			updateQuery = pstatement.executeUpdate();
			//////////////////////////////////////////////////////////////////////////////////////////////
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteDeduction(int deduction_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Deduction where Deduction_id="+deduction_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOwner = connection.createStatement();){
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					updateQuery = pstatement.executeUpdate();
				}
				else
				{
					updateQuery=-2;
				}
			}	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public Deduction_List getDeductionList(int payroll_id ){
		String query = "select * from Deduction where payroll_id="+payroll_id;
		Deduction_List Deduction_List = new Deduction_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> deduction_id = new ArrayList<Integer>();
			ArrayList<String> deduction_Name = new ArrayList<String>();
			ArrayList<String> deduction_Description = new ArrayList<String>();
			ArrayList<String> deduction_Type = new ArrayList<String>();
			ArrayList<String> period = new ArrayList<String>();
			ArrayList<Double> amount = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			deduction_id.add(result.getInt("Deduction_id"));    		
    			deduction_Name.add(result.getString("Deduction_Name")); 
    			deduction_Description.add(result.getString("Deduction_Description"));
    			deduction_Type.add(result.getString("Deduction_Type"));
    			period.add(result.getString("Period"));
    			amount.add(result.getDouble("Amount"));
		    }
    		Deduction_List.setDeduction_id(deduction_id);
    		Deduction_List.setDeduction_Name(deduction_Name);
    		Deduction_List.setDeduction_Description(deduction_Description);
    		Deduction_List.setDeduction_Type(deduction_Type);
    		Deduction_List.setPeriod(period);
    		Deduction_List.setAmount(amount);
    		Deduction_List.setPayroll_id(payroll_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Deduction_List;
	}
	public boolean checkEmployeeAllowance(int allowance_id, int employee_id){
		String query = "select Allowance_id from Employee_Allowance where employee_id="+employee_id+" and Allowance_id="+allowance_id;
		boolean employeeAllowanceExist=false;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			if (result.isBeforeFirst())
			{
				employeeAllowanceExist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeAllowanceExist;
	}
	public int createEmployeeAllowance(int allowance_id, int employee_id, double amount, int payroll_Month){
		String query =  "INSERT INTO Employee_Allowance (Employee_id, Allowance_id, Amount, Payroll_Month) VALUES (?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setInt(1, employee_id);
			pstatement.setInt(2, allowance_id);
			if(amount!=-1)
			{
				pstatement.setDouble(3, amount);
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}			
			if(payroll_Month!=-1)
			{
				pstatement.setInt(4, payroll_Month);
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateEmployeeAllowance(int allowance_id, int employee_id, double amount, int payroll_Month){
		String query =  "Update Employee_Allowance set Amount=?, Payroll_Month=? where Employee_id="+employee_id+" and Allowance_id="+allowance_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			if(amount!=-1)
			{
				pstatement.setDouble(1, amount);
			}
			else
			{
				pstatement.setNull(1, java.sql.Types.NULL);
			}			
			if(payroll_Month!=-1)
			{
				pstatement.setInt(2, payroll_Month);
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteEmployeeAllowance(int allowance_id, int employee_id){
		String query = "Delete from Employee_Allowance where Employee_id="+employee_id+" and Allowance_id="+allowance_id;
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

	public boolean checkEmployeeDeduction(int deduction_id, int employee_id){
		String query = "select Deduction_id from Employee_Deduction where employee_id="+employee_id+" and Deduction_id="+deduction_id;
		boolean employeeDeductionExist=false;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			if (result.isBeforeFirst())
			{
				employeeDeductionExist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeDeductionExist;
	}
	public int createEmployeeDeduction(int deduction_id, int employee_id, double amount, int payroll_Month){
		String query =  "INSERT INTO Employee_Deduction (Employee_id, Deduction_id, Amount, Payroll_Month) VALUES (?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setInt(1, employee_id);
			pstatement.setInt(2, deduction_id);
			if(amount!=-1)
			{
				pstatement.setDouble(3, amount);
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}			
			if(payroll_Month!=-1)
			{
				pstatement.setInt(4, payroll_Month);
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateEmployeeDeduction(int deduction_id, int employee_id, double amount, int payroll_Month){
		String query =  "Update Employee_Deduction set Amount=?, Payroll_Month=? where Employee_id="+employee_id+" and Deduction_id="+deduction_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			if(amount!=-1)
			{
				pstatement.setDouble(1, amount);
			}
			else
			{
				pstatement.setNull(1, java.sql.Types.NULL);
			}			
			if(payroll_Month!=-1)
			{
				pstatement.setInt(2, payroll_Month);
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteEmployeeDeduction(int deduction_id, int employee_id){
		String query = "Delete from Employee_Deduction where Employee_id="+employee_id+" and Deduction_id="+deduction_id;
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
	public Employee_Allowance getEmployeeAllowance(int employee_id){
		String query = "select * from Employee_Allowance where Employee_id="+employee_id;
		Employee_Allowance Employee_Allowance = new Employee_Allowance();
		ArrayList<Integer> employee_Allowance_id = new ArrayList<Integer>();
		ArrayList<Double> amount = new ArrayList<Double>();
		ArrayList<Integer> payroll_Month = new ArrayList<Integer>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				employee_Allowance_id.add(result.getInt("Allowance_id"));
				if(result.getString("Amount")!=null)
				{
					amount.add(result.getDouble("Amount"));	
				}
				else
				{
					amount.add(null);	
				}
				if(result.getString("Payroll_Month")!=null)
				{
					payroll_Month.add(result.getInt("Payroll_Month"));	
				}
				else
				{
					payroll_Month.add(null);	
				}
							
			}
			Employee_Allowance.setEmployee_Allowance_id(employee_Allowance_id);
			Employee_Allowance.setAmount(amount);
			Employee_Allowance.setPayroll_Month(payroll_Month);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Employee_Allowance;
	}
	public Employee_Deduction getEmployeeDeduction(int employee_id){
		String query = "select * from Employee_Deduction where Employee_id="+employee_id;
		Employee_Deduction Employee_Deduction = new Employee_Deduction();
		ArrayList<Integer> employee_Deduction_id = new ArrayList<Integer>();
		ArrayList<Integer> payroll_Month = new ArrayList<Integer>();
		ArrayList<Double> amount = new ArrayList<Double>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				employee_Deduction_id.add(result.getInt("Deduction_id"));
				if(result.getString("Amount")!=null)
				{
					amount.add(result.getDouble("Amount"));	
				}
				else
				{
					amount.add(null);	
				}
				if(result.getString("Payroll_Month")!=null)
				{
					payroll_Month.add(result.getInt("Payroll_Month"));	
				}
				else
				{
					payroll_Month.add(null);	
				}							
			}
			Employee_Deduction.setEmployee_Deduction_id(employee_Deduction_id);
			Employee_Deduction.setAmount(amount);
			Employee_Deduction.setPayroll_Month(payroll_Month);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Employee_Deduction;
	}
}
