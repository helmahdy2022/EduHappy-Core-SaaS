package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Allowance_List;
import model.beans.admin.Deduction_List;
import model.beans.admin.Monthly_Payroll_List;
import model.beans.admin.Payroll;
import model.beans.admin.Payroll_Details;
import model.beans.admin.Payroll_List;
import model.dbHelpers.DbConnector;

public class PayrollManagement {
	public int createPayroll(Payroll_Details Payroll_Details){
		String query = "INSERT INTO Payroll (Payroll_id, Payroll_Name, Payroll_Description, School_id, Working_Days, Working_Time_From, Working_Time_To, Overtime_Normal, Overtime_Weekend, Overtime_Holidays, Overtime_Daysoff, Debit_Account, Credit_Account) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, Payroll_Details.getPayroll_Name());
			if(Payroll_Details.getPayroll_Description()!=null && !Payroll_Details.getPayroll_Description().equals(""))
			{
				pstatement.setString(2, Payroll_Details.getPayroll_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Payroll_Details.getSchool_id());
			pstatement.setDouble(4, Payroll_Details.getWorking_Days());
			pstatement.setTime(5, Payroll_Details.getWorking_Time_From());
			pstatement.setTime(6, Payroll_Details.getWorking_Time_To());			
			pstatement.setDouble(7, Payroll_Details.getOvertime_Normal());
			pstatement.setDouble(8, Payroll_Details.getOvertime_Weekend());
			pstatement.setDouble(9, Payroll_Details.getOvertime_Holidays());
			pstatement.setDouble(10, Payroll_Details.getOvertime_Daysoff());			
			pstatement.setInt(11, Payroll_Details.getDebit_Account_id());
			pstatement.setInt(12, Payroll_Details.getCredit_Account_id());
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
	public int updatePayroll(Payroll_Details Payroll_Details, int payroll_id){
		String query = "update Payroll set Payroll_Name=?, Payroll_Description=?, School_id=?, Working_Days=?, Working_Time_From=?, Working_Time_To=?, Overtime_Normal=?, Overtime_Weekend=?, Overtime_Holidays=?, Overtime_Daysoff=?, Debit_Account=?, Credit_Account=? where Payroll_id="+payroll_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, Payroll_Details.getPayroll_Name());
			if(Payroll_Details.getPayroll_Description()!=null && !Payroll_Details.getPayroll_Description().equals(""))
			{
				pstatement.setString(2, Payroll_Details.getPayroll_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Payroll_Details.getSchool_id());
			pstatement.setDouble(4, Payroll_Details.getWorking_Days());
			pstatement.setTime(5, Payroll_Details.getWorking_Time_From());
			pstatement.setTime(6, Payroll_Details.getWorking_Time_To());			
			pstatement.setDouble(7, Payroll_Details.getOvertime_Normal());
			pstatement.setDouble(8, Payroll_Details.getOvertime_Weekend());
			pstatement.setDouble(9, Payroll_Details.getOvertime_Holidays());
			pstatement.setDouble(10, Payroll_Details.getOvertime_Daysoff());			
			pstatement.setInt(11, Payroll_Details.getDebit_Account_id());
			pstatement.setInt(12, Payroll_Details.getCredit_Account_id());
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

	public int deletePayroll(int payroll_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Payroll where Payroll_id="+payroll_id;
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
	public Payroll_List getPayrollList(int school_id ){
		String query = "select * from Payroll where school_id="+school_id;
		Payroll_List Payroll_List = new Payroll_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> payroll_id = new ArrayList<Integer>();
			ArrayList<String> payroll_Name = new ArrayList<String>();
			ArrayList<String> payroll_Description = new ArrayList<String>();
			ArrayList<Double> working_Days = new ArrayList<Double>();
			ArrayList<Time> working_Time_From = new ArrayList<Time>();
			ArrayList<Time> working_Time_To = new ArrayList<Time>();
			ArrayList<Double> overtime_Normal = new ArrayList<Double>();
			ArrayList<Double> overtime_Weekend = new ArrayList<Double>();
			ArrayList<Double> overtime_Holidays = new ArrayList<Double>();
			ArrayList<Double> overtime_Daysoff = new ArrayList<Double>();
			ArrayList<Integer> debit_Account = new ArrayList<Integer>();
			ArrayList<Integer> credit_Account = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			payroll_id.add(result.getInt("Payroll_id"));    		
    			payroll_Name.add(result.getString("Payroll_Name")); 
    			payroll_Description.add(result.getString("Payroll_Description"));
    			working_Days.add(result.getDouble("Working_Days"));
    			working_Time_From.add(result.getTime("Working_Time_From"));
    			working_Time_To.add(result.getTime("Working_Time_To"));
    			overtime_Normal.add(result.getDouble("Overtime_Normal"));
    			overtime_Weekend.add(result.getDouble("Overtime_Weekend"));
    			overtime_Holidays.add(result.getDouble("Overtime_Holidays"));
    			overtime_Daysoff.add(result.getDouble("Overtime_Daysoff"));
    			debit_Account.add(result.getInt("Debit_Account"));
    			credit_Account.add(result.getInt("Credit_Account"));
		    }
    		Payroll_List.setPayroll_id(payroll_id);
    		Payroll_List.setPayroll_Name(payroll_Name);
    		Payroll_List.setPayroll_Description(payroll_Description);
    		Payroll_List.setWorking_Days(working_Days);
    		Payroll_List.setWorking_Time_From(working_Time_From);
    		Payroll_List.setWorking_Time_To(working_Time_To);
    		Payroll_List.setOvertime_Normal(overtime_Normal);
    		Payroll_List.setOvertime_Weekend(overtime_Weekend);
    		Payroll_List.setOvertime_Holidays(overtime_Holidays);
    		Payroll_List.setOvertime_Daysoff(overtime_Daysoff);
    		Payroll_List.setDebit_Account(debit_Account);
    		Payroll_List.setCredit_Account(credit_Account);
    		Payroll_List.setSchool_id(school_id);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll_List;
	}	
	public Payroll_List getAllPayrolls(List<String> schoolList){
		//String query = "select * from Payroll where school_id in (select school_id from admin_school where admin_id in(select admin_id from admins where account_id="+account_id+"))";
		//String query = "select * from Payroll where school_id in (select school_id from admin_school where admin_id ="+admin_id+")";
		String schools = null;
		for (String school : schoolList)
		{
			if(school!=null && !school.equals(""))
			{
				if(schoolList.size()==1)
				{
					schools = school;
				}					
				else
				{
					schools = schools + " or school_id='"+school+"'";
				}
					
			}
		}
		String query = "select * from Payroll where school_id="+schools;
		Payroll_List Payroll_List = new Payroll_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchool = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> payroll_id = new ArrayList<Integer>();
			ArrayList<String> payroll_Name = new ArrayList<String>();
			ArrayList<String> payroll_School_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
    			payroll_id.add(result.getInt("Payroll_id"));    		
    			payroll_Name.add(result.getString("Payroll_Name")); 
    			///////////////Get School Name////////////////////////////////
    			String sSchool = "Select Name from school where school_id="+result.getInt("School_id");
    			ResultSet resultSchool = statementSchool.executeQuery(sSchool);
    			String school_Name=null;
    			while(resultSchool.next())
    			{
    				school_Name = resultSchool.getString("Name");
    			}
    			payroll_School_Name.add(school_Name);
    			/////////////////////////////////////////////////////////////
		    }
    		Payroll_List.setPayroll_id(payroll_id);
    		Payroll_List.setPayroll_Name(payroll_Name);
    		Payroll_List.setPayroll_School_Name(payroll_School_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll_List;
	}	

	public Payroll_List getTeacherPayrolls(int teacher_id){
		//String query = "select * from Payroll where school_id in (select school_id from admin_school where admin_id in(select admin_id from admins where account_id="+account_id+"))";
		String query = "select * from Payroll where school_id in (select school_id from teacher_school where teacher_id ="+teacher_id+")";
		Payroll_List Payroll_List = new Payroll_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchool = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> payroll_id = new ArrayList<Integer>();
			ArrayList<String> payroll_Name = new ArrayList<String>();
			ArrayList<String> payroll_School_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
    			payroll_id.add(result.getInt("Payroll_id"));    		
    			payroll_Name.add(result.getString("Payroll_Name")); 
    			///////////////Get School Name////////////////////////////////
    			String sSchool = "Select Name from school where school_id="+result.getInt("School_id");
    			ResultSet resultSchool = statementSchool.executeQuery(sSchool);
    			String school_Name=null;
    			while(resultSchool.next())
    			{
    				school_Name = resultSchool.getString("Name");
    			}
    			payroll_School_Name.add(school_Name);
    			/////////////////////////////////////////////////////////////
		    }
    		Payroll_List.setPayroll_id(payroll_id);
    		Payroll_List.setPayroll_Name(payroll_Name);
    		Payroll_List.setPayroll_School_Name(payroll_School_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll_List;
	}	

	public Payroll_List getAdminPayrolls(int admin_id){
		//String query = "select * from Payroll where school_id in (select school_id from admin_school where admin_id in(select admin_id from admins where account_id="+account_id+"))";
		String query = "select * from Payroll where school_id in (select school_id from admin_school where admin_id ="+admin_id+")";
		Payroll_List Payroll_List = new Payroll_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchool = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> payroll_id = new ArrayList<Integer>();
			ArrayList<String> payroll_Name = new ArrayList<String>();
			ArrayList<String> payroll_School_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
    			payroll_id.add(result.getInt("Payroll_id"));    		
    			payroll_Name.add(result.getString("Payroll_Name")); 
    			///////////////Get School Name////////////////////////////////
    			String sSchool = "Select Name from school where school_id="+result.getInt("School_id");
    			ResultSet resultSchool = statementSchool.executeQuery(sSchool);
    			String school_Name=null;
    			while(resultSchool.next())
    			{
    				school_Name = resultSchool.getString("Name");
    			}
    			payroll_School_Name.add(school_Name);
    			/////////////////////////////////////////////////////////////
		    }
    		Payroll_List.setPayroll_id(payroll_id);
    		Payroll_List.setPayroll_Name(payroll_Name);
    		Payroll_List.setPayroll_School_Name(payroll_School_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll_List;
	}		
	
	public boolean isPayrollExist(int payroll_id, String payroll_Month, String payroll_Year){
		boolean payroll_Exist=false;
		String sParollExist = "Select * from Monthly_Payroll where Payroll_id="+payroll_id+" and Payroll_Month='"+payroll_Month+"' and Payroll_Year='"+payroll_Year+"'";	
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParollExist = connection.createStatement();){
			ResultSet resultParollExist = statementParollExist.executeQuery(sParollExist);
			
			if(resultParollExist.isBeforeFirst())
			{
				payroll_Exist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payroll_Exist;
	}
	
	public Payroll runPayroll(int payroll_id ){
		String sEmployees = "select * from Employees where Payroll_id="+payroll_id;
		Payroll Payroll = new Payroll(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementEmployees = connection.createStatement();
				Statement statementEmployeeName = connection.createStatement();
			 Statement statementLatestBasic = connection.createStatement();
			 Statement statementPrevMonthDate = connection.createStatement();
			 Statement statementEmployeeLeave = connection.createStatement();
			 Statement statementPayrollSettings = connection.createStatement();
			 Statement statementEmployeeOvertime = connection.createStatement();
			 Statement statementWeekEnd = connection.createStatement();
			 Statement statementDateIsHoliday = connection.createStatement();
			 Statement statementDateIsDayOff = connection.createStatement();
			 Statement statementAllowance = connection.createStatement();
			 Statement statementEmployeeAllowance = connection.createStatement();
			 Statement statementDeduction = connection.createStatement();
			 Statement statementEmployeeDeduction = connection.createStatement();){
						
			ResultSet resultEmployees = statementEmployees.executeQuery(sEmployees);			
			ArrayList<Integer> employee_id = new ArrayList<Integer>();
			ArrayList<String> first_Name = new ArrayList<String>();
			ArrayList<String> last_Name = new ArrayList<String>();
			ArrayList<Double> basic_Salary = new ArrayList<Double>();
			ArrayList<Double> unpaid_Leave = new ArrayList<Double>();
			ArrayList<Double> overtime_Earning = new ArrayList<Double>();
			ArrayList<ArrayList<String>> employee_Allowance_Amount = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> employee_Deduction_Amount = new ArrayList<ArrayList<String>>();
			ArrayList<Double> net_Salary = new ArrayList<Double>();
			double net_Earning = 0.0;
			double total_Salaries =  0.0;
			////////////////////Get Allowance Types////////////////////////////////////////////////////////////////////
	    	String sAllowance = "select * from Allowance where Payroll_id="+payroll_id;
	    	ResultSet resultAllowance = statementAllowance.executeQuery(sAllowance);
	    	int j=0;	    	
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////Get Deduction Types////////////////////////////////////////////////////////////////////
	    	String sDeduction = "select * from Deduction where Payroll_id="+payroll_id;
	    	ResultSet resultDeduction = statementDeduction.executeQuery(sDeduction);
	    	int k=0;	    	
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			while (resultEmployees.next())
	    	{			
				String sEmployeeName = null;
				if(resultEmployees.getString("Employee_Group").equals("Teacher"))
    			{
					sEmployeeName = "Select First_Name, Last_Name from Teachers where Employee_id="+resultEmployees.getInt("Employee_id");
    			}
				else
				{
					sEmployeeName = "Select First_Name, Last_Name from Admins where Employee_id="+resultEmployees.getInt("Employee_id");
				}
				ResultSet resultEmployeeName = statementEmployeeName.executeQuery(sEmployeeName);	
				while(resultEmployeeName.next())
				{
					first_Name.add(resultEmployeeName.getString("First_Name"));
					last_Name.add(resultEmployeeName.getString("Last_Name"));
				}
				String sLatestBasic = "select * from Employee_Promotion where Employee_id="+resultEmployees.getInt("Employee_id")+" order by Promotion_Date desc Limit 1";
    			ResultSet resultLatestBasic = statementLatestBasic.executeQuery(sLatestBasic);	
    			double Basic_Salary = -1;
    			while(resultLatestBasic.next())
    			{
    				Basic_Salary = resultLatestBasic.getDouble("Basic_Salary");
    			}
    			if(Basic_Salary==-1)
    			{
    				Basic_Salary = resultEmployees.getDouble("Initial_Basic");
    			}
    			/////////////Get current year and month////////////
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(new Date());
    			int payroll_Year = cal.get(Calendar.YEAR);
    			int payroll_Month = cal.get(Calendar.MONTH);
    			cal.add(Calendar.MONTH ,-1);
    			String payroll_Prev_Month = new SimpleDateFormat("MMMM").format(cal.getTime());
    			Date today = new Date();
    			//////////////////////////////////////////////////
				
    			
    			////////////Calculate the daily earning/////////////////////////////
				String sPayrollSettings = "Select * from Payroll where Payroll_id="+payroll_id;
				ResultSet resultPayrollSettings = statementPayrollSettings.executeQuery(sPayrollSettings);
				double daily_Eraning=0.0;
				double overtime_Weekend=0.0;
				double overtime_Holidays=0.0;
				double overtime_Daysoff=0.0;
				double overtime_Normal=0.0;
				Time working_Time_From=null;
				Time working_Time_To=null;
				double working_Hours=0.0;
				while(resultPayrollSettings.next())
				{
					daily_Eraning = Basic_Salary/(resultPayrollSettings.getDouble("Working_Days"));
					overtime_Weekend = resultPayrollSettings.getDouble("Overtime_Weekend");
					overtime_Holidays = resultPayrollSettings.getDouble("Overtime_Holidays");
					overtime_Daysoff = resultPayrollSettings.getDouble("Overtime_Daysoff");
					overtime_Normal = resultPayrollSettings.getDouble("Overtime_Normal");
					working_Time_From = resultPayrollSettings.getTime("Working_Time_From");
					working_Time_To = resultPayrollSettings.getTime("Working_Time_To");
					long working_Hours_InMillies = (working_Time_To.getTime() - working_Time_From.getTime());
					working_Hours = TimeUnit.HOURS.convert(working_Hours_InMillies, TimeUnit.MILLISECONDS);
				}
				///////////////////////////////////////////////////////////////////	
    			/////////////////////////////Calculate the unpaid Leave////////////
				String sPrevMonthDate = "Select Date from Monthly_Payroll where Payroll_id="+payroll_id+" and Payroll_Month='"+payroll_Prev_Month+"' and Payroll_Year='"+payroll_Year+"'";
				ResultSet resultPrevMonthDate = statementPrevMonthDate.executeQuery(sPrevMonthDate);
				Date prevMonthDate=null;
				while(resultPrevMonthDate.next())
				{
					prevMonthDate = resultPrevMonthDate.getDate("Date");
				}
				String sEmployeeLeave = "select SUM(Leave_Days) from Employee_Leave where Employee_id="+resultEmployees.getInt("Employee_id")
    			+" and EXTRACT(YEAR FROM Leave_From)="+payroll_Year
    			+" and ((EXTRACT(MONTH FROM Leave_From)="+(payroll_Month+1)+" and Leave_From <='"+new java.sql.Date(today.getTime())+"') or (EXTRACT(MONTH FROM Leave_From)="+payroll_Month
    			+" and Leave_From >"+prevMonthDate+"))"
    			+" and Leave_id in (Select leave_id from Leaves where Payment_Type='Unpaid' and Payroll_id="+payroll_id+")";
    			ResultSet resultEmployeeLeave = statementEmployeeLeave.executeQuery(sEmployeeLeave);
    			double unpaid_Leave_Value = 0.0;
    			while(resultEmployeeLeave.next())
    			{
    				unpaid_Leave_Value = daily_Eraning*resultEmployeeLeave.getDouble(1);
    				unpaid_Leave.add((double)Math.round(unpaid_Leave_Value*100)/100);	
    			}
    			////////////////////////////////////////////////////
    			
    			///////////////////Calculate Overtime///////////////////////////////////////////    			
    			double total_Overtime_Earning=0.0;
    			String sEmployeeOvertime = "Select * from Overtime where Employee_id="+resultEmployees.getInt("Employee_id")
    			+" and EXTRACT(YEAR FROM Overtime_Date)="+payroll_Year
    			+" and ((EXTRACT(MONTH FROM Overtime_Date)="+(payroll_Month+1)+" and Overtime_Date <='"+new java.sql.Date(today.getTime())+"') or (EXTRACT(MONTH FROM Overtime_Date)="+payroll_Month
    			+" and Overtime_Date >"+prevMonthDate+"))";
    			ResultSet resultEmployeeOvertime = statementEmployeeOvertime.executeQuery(sEmployeeOvertime);
    		
    			while(resultEmployeeOvertime.next())
    			{
    				String date_Case="";		    		
        			Format formatter = new SimpleDateFormat("EEEE"); 
		    	    String overtime_Day_Name = formatter.format(resultEmployeeOvertime.getDate("Overtime_Date"));
        			
		    	    String sWeekEnd = "Select * from Week_Days where Day_Name='"+overtime_Day_Name+"' and Day_id in (Select Day_id from School_Weekend where School_id=(Select School_id from Payroll where Payroll_id="+payroll_id+"))";
        			ResultSet resultWeekEnd = statementWeekEnd.executeQuery(sWeekEnd);
			    	if(resultWeekEnd.isBeforeFirst())
			    	{
			    		date_Case = "WeekEnd";
			    	}
    				//////////////////////////////////////////////////////
			    	
			    	///////////////////Check Days-off//////////////////////////////
			    	String sDateIsDayOff = "select * from Daysoff where Daysoff_Start<='"+new java.sql.Date(resultEmployeeOvertime.getDate("Overtime_Date").getTime())+"' and Daysoff_End>='"+new java.sql.Date(resultEmployeeOvertime.getDate("Overtime_Date").getTime())+"' and All_Schools = 1"
			    			+ " and Account_id=(select account_id from school where school_id=(select school_id from Payroll where Payroll_id="+payroll_id+"))";
			    	ResultSet resultDateIsDayOff = statementDateIsDayOff.executeQuery(sDateIsDayOff);
			    	if(resultDateIsDayOff.isBeforeFirst())
			    	{
			    		date_Case = "Daysoff";
			    	}
			    	//////////////////////////////////////////////////////////////
		    		
			    	/////Check Holidays /////////////////////
			    	String sDateIsHoliday = "select * from National_Holidays where Holiday_Start<='"+new java.sql.Date(resultEmployeeOvertime.getDate("Overtime_Date").getTime())+"' and Holiday_End>='"+new java.sql.Date(resultEmployeeOvertime.getDate("Overtime_Date").getTime())+"'"
			    			+ " and country_id=(select country_id from school where school_id=(select school_id from Payroll where Payroll_id="+payroll_id+"))"
			    					+ " and Account_id=(select account_id from school where school_id=(select school_id from Payroll where Payroll_id="+payroll_id+"))";
			    	ResultSet resultDateIsHoliday = statementDateIsHoliday.executeQuery(sDateIsHoliday);
			    	if(resultDateIsHoliday.isBeforeFirst())
			    	{
			    		date_Case = "Holiday";
			    	}
			    	////////////////////////////////////////////////////////////////
			    	double overtime_Multiplier = 0.0;
			    	switch(date_Case)
			    	{
			    		case "WeekEnd" :
			    			overtime_Multiplier = overtime_Weekend;
			    			break;
			    		
			    		case "Holiday" :
			    			overtime_Multiplier = overtime_Holidays;
			    			break;
			    		
			    		case "Daysoff" :
			    			overtime_Multiplier = overtime_Daysoff;
			    			break;
			    		
			    		default :
			    			overtime_Multiplier = overtime_Normal;			    	
			    	}
			   
			    	double Overtime_Earning = overtime_Multiplier*resultEmployeeOvertime.getDouble("Overtime_Hours")*(daily_Eraning/working_Hours);
			    	total_Overtime_Earning = total_Overtime_Earning + Overtime_Earning; 
    			}
		    	overtime_Earning.add((double) Math.round(total_Overtime_Earning*100)/100);
    			////////////////////////////////////////////////////////////////////////////////
		    	
		    	///////////////////////////////Get Allowances/////////////////////////////////////////
		    	double total_Allowance = 0.0;
		    	resultAllowance.beforeFirst();
		    	while(resultAllowance.next())
				{
					String sEmployeeAllowance = "Select * from Employee_Allowance where Employee_id="+resultEmployees.getInt("Employee_id")+" and Allowance_id ="+resultAllowance.getInt("Allowance_id");
					ResultSet resultEmployeeAllowance = statementEmployeeAllowance.executeQuery(sEmployeeAllowance);
		    		double allowanceAmount = 0;
	    			while(resultEmployeeAllowance.next())
	    			{		    				
		    			if(resultAllowance.getString("Period").equals("Monthly")||(resultAllowance.getString("Period").equals("Annually") && resultEmployeeAllowance.getInt("Payroll_Month")==(payroll_Month+1)))
		    			{
			    			if(resultAllowance.getString("Allowance_Type").equals("Specific"))
			    			{
		
					    		allowanceAmount = resultEmployeeAllowance.getDouble("Amount");	    			
							    
			    			}
			    			else if(resultAllowance.getString("Allowance_Type").equals("Percentage"))
			    			{
			    				allowanceAmount = (resultAllowance.getDouble("Amount")/100)*Basic_Salary;
			    			}
			    			else
			    			{
			    				allowanceAmount = resultAllowance.getDouble("Amount");
			    			}		    				
		    			}
	    			}		    			
		    		employee_Allowance_Amount.add(new ArrayList<String>());
		    		employee_Allowance_Amount.get(j).add(0,null);
		    		employee_Allowance_Amount.get(j).set(0, resultEmployees.getString("Employee_id"));
		    		employee_Allowance_Amount.get(j).add(1,null);
		    		employee_Allowance_Amount.get(j).set(1, resultAllowance.getString("Allowance_id"));
		    		employee_Allowance_Amount.get(j).add(2,null);
		    		employee_Allowance_Amount.get(j).set(2, String.valueOf((double) Math.round(allowanceAmount*100)/100));
	    			j=j+1;
	    			total_Allowance = total_Allowance + (double) Math.round(allowanceAmount*100)/100;
				}
		    	/////////////////////////////////////////////////////////////////////////////////////

		    	///////////////////////////////Get Deductions/////////////////////////////////////////
		    	double total_Deduction = 0.0;
		    	resultDeduction.beforeFirst();
		    	while(resultDeduction.next())
				{
					String sEmployeeDeduction = "Select * from Employee_Deduction where Employee_id="+resultEmployees.getInt("Employee_id")+" and Deduction_id ="+resultDeduction.getInt("Deduction_id");
					ResultSet resultEmployeeDeduction = statementEmployeeDeduction.executeQuery(sEmployeeDeduction);
		    		double deductionAmount = 0;
	    			while(resultEmployeeDeduction.next())
	    			{		    				
		    			if(resultDeduction.getString("Period").equals("Monthly")||(resultDeduction.getString("Period").equals("Annually") && resultEmployeeDeduction.getInt("Payroll_Month")==(payroll_Month+1)))
		    			{
			    			if(resultDeduction.getString("Deduction_Type").equals("Specific"))
			    			{
		
			    				deductionAmount = resultEmployeeDeduction.getDouble("Amount");	    			
							    
			    			}
			    			else if(resultDeduction.getString("Deduction_Type").equals("Percentage"))
			    			{
			    				deductionAmount = (resultDeduction.getDouble("Amount")/100)*Basic_Salary;
			    			}
			    			else
			    			{
			    				deductionAmount = resultDeduction.getDouble("Amount");
			    			}		    				
		    			}
	    			}		    			
		    		employee_Deduction_Amount.add(new ArrayList<String>());
		    		employee_Deduction_Amount.get(k).add(0,null);
		    		employee_Deduction_Amount.get(k).set(0, resultEmployees.getString("Employee_id"));
		    		employee_Deduction_Amount.get(k).add(1,null);
		    		employee_Deduction_Amount.get(k).set(1, resultDeduction.getString("Deduction_id"));
		    		employee_Deduction_Amount.get(k).add(2,null);
		    		employee_Deduction_Amount.get(k).set(2, String.valueOf((double) Math.round(deductionAmount*100)/100));
	    			k=k+1;
	    			total_Deduction = total_Deduction + (double) Math.round(deductionAmount*100)/100;
				}
		    	/////////////////////////////////////////////////////////////////////////////////////	    			    	
				
		    	//////////////////////////Net Salary///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	net_Earning = (double) Math.round((Basic_Salary - (double)Math.round(unpaid_Leave_Value*100)/100 +(double) Math.round(total_Overtime_Earning*100)/100 + total_Allowance - total_Deduction)*100)/100 ;
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	
		    	/////////////////////////Total Salaries/////////////////////////////////////////////////
		    	total_Salaries = total_Salaries + net_Earning;
		    	///////////////////////////////////////////////////////////////////////////////////////
		    	employee_id.add(resultEmployees.getInt("Employee_id"));
    			basic_Salary.add(Basic_Salary); 
    			net_Salary.add(net_Earning);
	    	}
			Payroll.setEmployee_id(employee_id);
			Payroll.setFirst_Name(first_Name);
			Payroll.setLast_Name(last_Name);
			Payroll.setBasic_Salary(basic_Salary);
			Payroll.setUnpaid_Leave(unpaid_Leave);
			Payroll.setOvertime_Earning(overtime_Earning);
			Payroll.setEmployee_Allowance_Amount(employee_Allowance_Amount);
			Payroll.setEmployee_Deduction_Amount(employee_Deduction_Amount);
			Payroll.setNet_Salary(net_Salary);
			Payroll.setTotal_Salaries((double) Math.round(total_Salaries*100)/100);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll;
	}	

	public int processPayroll(HttpServletRequest request){
		int updateQuery=0;
		String sMonthlyPayroll = "INSERT INTO Monthly_Payroll (Monthly_Payroll_id, Payroll_id, Date, Payroll_Month, Payroll_Year, Total_Salaries) VALUES (NULL, ?, ?, ?, ?, ?)";
		String sEmployeeMonthlyPayroll = "INSERT INTO Employee_Monthly_Payroll (Monthly_Payroll_id, Employee_id, Basic_Salary, Unpaid_Leave, Overtime, Net_Salary) VALUES (?, ?, ?, ?, ?, ?)";
		String sEmployeeMonthlyAllowance = "INSERT INTO Employee_Monthly_Allowance (Monthly_Payroll_id, Employee_id, Allowance_Name, Allowance_Amount) VALUES (?, ?, ?, ?)";
		String sEmployeeMonthlyDeduction = "INSERT INTO Employee_Monthly_Deduction (Monthly_Payroll_id, Employee_id, Deduction_Name, Deduction_Amount) VALUES (?, ?, ?, ?)";
		String sCreatePayrollJournal = "INSERT INTO General_Journal (Entry_id, Entry_Date, Debit_Account, Credit_Account, Amount, Details) VALUES (Null, ?, ?, ?, ?, ?)";
		
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatementMonthlyPayroll = connection.prepareStatement(sMonthlyPayroll, Statement.RETURN_GENERATED_KEYS);
				Statement statementMonthly_Payroll_id = connection.createStatement();
				Statement statementEmployees = connection.createStatement();
				PreparedStatement pstatementEmployeeMonthlyPayroll = connection.prepareStatement(sEmployeeMonthlyPayroll);
				Statement statementEmployeeAllowance = connection.createStatement();
				Statement statementAllowanceName = connection.createStatement();
				PreparedStatement pstatementEmployeeMonthlyAllowance = connection.prepareStatement(sEmployeeMonthlyAllowance);
				Statement statementEmployeeDeduction = connection.createStatement();
				Statement statementDeductionName = connection.createStatement();
				PreparedStatement pstatementEmployeeMonthlyDeduction = connection.prepareStatement(sEmployeeMonthlyDeduction);
				PreparedStatement pstatementCreatePayrollJournal = connection.prepareStatement(sCreatePayrollJournal, Statement.RETURN_GENERATED_KEYS);
				Statement statementDebitCreditAccounts = connection.createStatement();){

			int payroll_id=0;
			if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
	    	{
	    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
	    	}
			connection.setAutoCommit(false);
			/////Create monthly payroll record///////////////////////////////////////////
			pstatementMonthlyPayroll.setInt(1, payroll_id);
			pstatementMonthlyPayroll.setDate(2, new java.sql.Date(new Date().getTime()));
			
			Format formatter = new SimpleDateFormat("YYYY");
			String payroll_Year = formatter.format(new Date());
			
			formatter = new SimpleDateFormat("MMMM");
			String payroll_Month = formatter.format(new Date());
			
			pstatementMonthlyPayroll.setString(3, payroll_Month);
			pstatementMonthlyPayroll.setString(4, payroll_Year);

			double total_Salaries=0;
			if(request.getParameter("Total_Salaries")!=null && !request.getParameter("Total_Salaries").equals(""))
	    	{
				total_Salaries = Double.parseDouble(request.getParameter("Total_Salaries"));
	    	}
			pstatementMonthlyPayroll.setDouble(5, total_Salaries);
			updateQuery = pstatementMonthlyPayroll.executeUpdate();
			////////////////////////////////////////////////////////////////////////////
			int monthly_Payroll_id=0;
			ResultSet resultMonthly_Payroll_id = pstatementMonthlyPayroll.getGeneratedKeys();
			if(resultMonthly_Payroll_id.next()) 
			{
				monthly_Payroll_id = resultMonthly_Payroll_id.getInt(1);
				resultMonthly_Payroll_id.close();
			}
			/////////////////////////////Create Employee Monthly Payroll record//////////////////////////////////////////////////////////////			
			String sEmployees = "select * from Employees where Payroll_id="+payroll_id;
			ResultSet resultEmployees = statementEmployees.executeQuery(sEmployees);
			while(resultEmployees.next())
			{
				int employee_id=0;
				if(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id"))!=null && !request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")).equals(""))
				{
					employee_id = Integer.parseInt(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")));
					double basic_Salary = Double.parseDouble(request.getParameter("Basic_Salary"+resultEmployees.getInt("Employee_id")));
					double unpaid_Leave = Double.parseDouble(request.getParameter("Unpaid_Leave"+resultEmployees.getInt("Employee_id")));
					double overtime = Double.parseDouble(request.getParameter("Overtime"+resultEmployees.getInt("Employee_id")));
					double net_Salary = Double.parseDouble(request.getParameter("Net_Salary"+resultEmployees.getInt("Employee_id")));
					pstatementEmployeeMonthlyPayroll.setInt(1, monthly_Payroll_id);
					pstatementEmployeeMonthlyPayroll.setInt(2, employee_id);
					pstatementEmployeeMonthlyPayroll.setDouble(3, basic_Salary);
					pstatementEmployeeMonthlyPayroll.setDouble(4, unpaid_Leave);
					pstatementEmployeeMonthlyPayroll.setDouble(5, overtime);
					pstatementEmployeeMonthlyPayroll.setDouble(6, net_Salary);
					updateQuery = pstatementEmployeeMonthlyPayroll.executeUpdate();
					//////////////////Get Monthly Employee Allowance Amount/////////////////////////////////////////////
					String sEmployeeAllowance = "Select * from Allowance where Payroll_id="+payroll_id;
					ResultSet resultEmployeeAllowance = statementEmployeeAllowance.executeQuery(sEmployeeAllowance);
					while(resultEmployeeAllowance.next())
					{
						if(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Allowance_id"+resultEmployeeAllowance.getInt("Allowance_id"))!=null 
								&& !request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Allowance_id"+resultEmployeeAllowance.getInt("Allowance_id")).equals(""))
						{			
							double allowance_Amount = Double.parseDouble(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Allowance_Amount"+resultEmployeeAllowance.getInt("Allowance_id")));
							pstatementEmployeeMonthlyAllowance.setInt(1, monthly_Payroll_id);
							pstatementEmployeeMonthlyAllowance.setInt(2, resultEmployees.getInt("Employee_id"));
							pstatementEmployeeMonthlyAllowance.setString(3, resultEmployeeAllowance.getString("Allowance_Name"));
							pstatementEmployeeMonthlyAllowance.setDouble(4, allowance_Amount);						
						}
						else
						{
							pstatementEmployeeMonthlyAllowance.setInt(1, monthly_Payroll_id);
							pstatementEmployeeMonthlyAllowance.setInt(2, resultEmployees.getInt("Employee_id"));
							pstatementEmployeeMonthlyAllowance.setString(3, resultEmployeeAllowance.getString("Allowance_Name"));
							pstatementEmployeeMonthlyAllowance.setDouble(4, 0.0);
						}
						updateQuery = pstatementEmployeeMonthlyAllowance.executeUpdate();	
						
					}
					//////////////////Get Monthly Employee Deduction Amount/////////////////////////////////////////////
					String sEmployeeDeduction = "Select * from Deduction where Payroll_id="+payroll_id;
					ResultSet resultEmployeeDeduction = statementEmployeeDeduction.executeQuery(sEmployeeDeduction);
					while(resultEmployeeDeduction.next())
					{
						if(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Deduction_id"+resultEmployeeDeduction.getInt("Deduction_id"))!=null 
								&& !request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Deduction_id"+resultEmployeeDeduction.getInt("Deduction_id")).equals(""))
						{			
							double deduction_Amount = Double.parseDouble(request.getParameter("Employee_id"+resultEmployees.getInt("Employee_id")+"Deduction_Amount"+resultEmployeeDeduction.getInt("Deduction_id")));
							pstatementEmployeeMonthlyDeduction.setInt(1, monthly_Payroll_id);
							pstatementEmployeeMonthlyDeduction.setInt(2, resultEmployees.getInt("Employee_id"));
							pstatementEmployeeMonthlyDeduction.setString(3, resultEmployeeDeduction.getString("Deduction_Name"));
							pstatementEmployeeMonthlyDeduction.setDouble(4, deduction_Amount);						
						}
						else
						{
							pstatementEmployeeMonthlyDeduction.setInt(1, monthly_Payroll_id);
							pstatementEmployeeMonthlyDeduction.setInt(2, resultEmployees.getInt("Employee_id"));
							pstatementEmployeeMonthlyDeduction.setString(3, resultEmployeeDeduction.getString("Deduction_Name"));
							pstatementEmployeeMonthlyDeduction.setDouble(4, 0.0);	
						}
						updateQuery = pstatementEmployeeMonthlyDeduction.executeUpdate();	
					}
				}
			}
			/////////////////////////////////Debit Credit Posting////////////////////////////////////////////////////////////////////////
			String sDebitCreditAccounts = "select * from Payroll where Payroll_id="+payroll_id;
			ResultSet resultDebitCreditAccounts = statementDebitCreditAccounts.executeQuery(sDebitCreditAccounts);
			while(resultDebitCreditAccounts.next())
			{
				pstatementCreatePayrollJournal.setDate(1, new java.sql.Date(new Date().getTime()));
				pstatementCreatePayrollJournal.setInt(2, resultDebitCreditAccounts.getInt("Debit_Account"));
				pstatementCreatePayrollJournal.setInt(3, resultDebitCreditAccounts.getInt("Credit_Account"));
				pstatementCreatePayrollJournal.setDouble(4, total_Salaries);
				pstatementCreatePayrollJournal.setString(5, "Monthly Salaries for Payroll Name ("+resultDebitCreditAccounts.getString("Payroll_Name")+") for the month of "+payroll_Month+" Year "+payroll_Year);
				updateQuery = pstatementCreatePayrollJournal.executeUpdate();
				if(updateQuery!=0)
				{
					int journal_Entry_id=0;
					ResultSet resultJournal_Entry_id = pstatementCreatePayrollJournal.getGeneratedKeys();
					if(resultJournal_Entry_id.next()) 
					{
						   journal_Entry_id = resultJournal_Entry_id.getInt(1);
						   resultJournal_Entry_id.close();
					}
					/////////////////////Update newly created Monthly Payroll by the Journal Entry ID///////////////
					String sPayrollJournal = "update Monthly_Payroll set Journal_Entry_id=? where Monthly_Payroll_id="+monthly_Payroll_id;
					PreparedStatement pstatementPayrollJournal = connection.prepareStatement(sPayrollJournal);
					pstatementPayrollJournal.setInt(1, journal_Entry_id);
					updateQuery = pstatementPayrollJournal.executeUpdate();
					pstatementPayrollJournal.close();
					///////////////////////////////////////////////////////////////////////////////////////
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(updateQuery!=0)
			{
				connection.commit();
			}
			else
			{
				connection.rollback();
			}
	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}	
	public Monthly_Payroll_List getPayrollMonths(int payroll_id){
		String sParollMonths = "Select * from Monthly_Payroll where payroll_id="+payroll_id;
		Monthly_Payroll_List Monthly_Payroll_List = new Monthly_Payroll_List();
		
		try (Connection connection = DbConnector.connectToDb();
				Statement statementParollMonths = connection.createStatement();){
			ArrayList<Integer> monthly_Payroll_id = new ArrayList<Integer>();
			ArrayList<String> report_Month = new ArrayList<String>();
			ResultSet resultParollMonths = statementParollMonths.executeQuery(sParollMonths);
			
			while (resultParollMonths.next())
			{
				monthly_Payroll_id.add(resultParollMonths.getInt("Monthly_Payroll_id"));
				String payroll_Year = resultParollMonths.getString("Payroll_Year");
				String payroll_Month = resultParollMonths.getString("Payroll_Month");
				report_Month.add(payroll_Month+"-"+payroll_Year);
			}	 
			Monthly_Payroll_List.setMonthly_Payroll_id(monthly_Payroll_id);
			Monthly_Payroll_List.setReport_Month(report_Month);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Monthly_Payroll_List;
	}

	public Payroll getPayrollReport(int monthly_Payroll_id ){
		String sEmployees = "select * from Employees where Payroll_id=(Select Payroll_id from Monthly_Payroll where Monthly_Payroll_id="+monthly_Payroll_id+")";
		String sMonthlyPayrollDate = "select * from Monthly_Payroll where Monthly_Payroll_id="+monthly_Payroll_id;
		Payroll Payroll = new Payroll(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementEmployees = connection.createStatement();
			 Statement statementEmployeeName = connection.createStatement();
			 Statement statementMonthlyPayrollDate = connection.createStatement();
			 Statement statementEmployeeBasics = connection.createStatement();
			 Statement statementAllowance = connection.createStatement();
			 Statement statementEmployeeAllowance = connection.createStatement();
			 Statement statementDeduction = connection.createStatement();
			 Statement statementEmployeeDeduction = connection.createStatement();){
						
			ResultSet resultEmployees = statementEmployees.executeQuery(sEmployees);			
			Date monthly_Payroll_Date = null;
			String payroll_Month = null;
			String payroll_Year = null;
			double total_Salaries=0.0;
			ArrayList<Integer> employee_id = new ArrayList<Integer>();
			ArrayList<String> first_Name = new ArrayList<String>();
			ArrayList<String> last_Name = new ArrayList<String>();
			ArrayList<Double> basic_Salary = new ArrayList<Double>();
			ArrayList<Double> unpaid_Leave = new ArrayList<Double>();
			ArrayList<Double> overtime_Earning = new ArrayList<Double>();
			ArrayList<ArrayList<String>> employee_Allowance_Amount = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> employee_Deduction_Amount = new ArrayList<ArrayList<String>>();
			ArrayList<Double> net_Salary = new ArrayList<Double>();
			
			ResultSet resultMonthlyPayrollDate = statementMonthlyPayrollDate.executeQuery(sMonthlyPayrollDate);
			while(resultMonthlyPayrollDate.next())
			{
				monthly_Payroll_Date = resultMonthlyPayrollDate.getDate("Date");
				payroll_Month = resultMonthlyPayrollDate.getString("Payroll_Month");
				payroll_Year = resultMonthlyPayrollDate.getString("Payroll_Year");
				total_Salaries = resultMonthlyPayrollDate.getDouble("Total_Salaries");
			}
			int j=0;
			int k=0;
			while (resultEmployees.next())
	    	{			
				String sEmployeeName = null;
				if(resultEmployees.getString("Employee_Group").equals("Teacher"))
    			{
					sEmployeeName = "Select First_Name, Last_Name from Teachers where Employee_id="+resultEmployees.getInt("Employee_id");
    			}
				else
				{
					sEmployeeName = "Select First_Name, Last_Name from Admins where Employee_id="+resultEmployees.getInt("Employee_id");
				}
				ResultSet resultEmployeeName = statementEmployeeName.executeQuery(sEmployeeName);	
				while(resultEmployeeName.next())
				{
					first_Name.add(resultEmployeeName.getString("First_Name"));
					last_Name.add(resultEmployeeName.getString("Last_Name"));
				}
				String sEmployeeBasics = "select * from Employee_Monthly_Payroll where Employee_id="+resultEmployees.getInt("Employee_id")+" and Monthly_Payroll_id="+monthly_Payroll_id;
    			ResultSet resultEmployeeBasics = statementEmployeeBasics.executeQuery(sEmployeeBasics);	
    			while(resultEmployeeBasics.next())
    			{
    		    	employee_id.add(resultEmployees.getInt("Employee_id"));
        			basic_Salary.add(resultEmployeeBasics.getDouble("Basic_Salary")); 
        			unpaid_Leave.add(resultEmployeeBasics.getDouble("Unpaid_Leave"));
        			overtime_Earning.add(resultEmployeeBasics.getDouble("Overtime"));   			
        			net_Salary.add(resultEmployeeBasics.getDouble("Net_Salary"));
    			}
    			///////////////////////////////Get Allowances/////////////////////////////////////////
    			String sEmployeeAllowance = "Select * from Employee_Monthly_Allowance where Monthly_Payroll_id="+monthly_Payroll_id+" and Employee_id="+resultEmployees.getInt("Employee_id");
    			ResultSet resultEmployeeAllowance = statementEmployeeAllowance.executeQuery(sEmployeeAllowance);
	    		while(resultEmployeeAllowance.next())
	    		{
	    			employee_Allowance_Amount.add(new ArrayList<String>());
		    		employee_Allowance_Amount.get(j).add(0,null);
		    		employee_Allowance_Amount.get(j).set(0, resultEmployees.getString("Employee_id"));
		    		employee_Allowance_Amount.get(j).add(1,null);
		    		employee_Allowance_Amount.get(j).set(1, resultEmployeeAllowance.getString("Allowance_Name"));
		    		employee_Allowance_Amount.get(j).add(2,null);
		    		employee_Allowance_Amount.get(j).set(2, resultEmployeeAllowance.getString("Allowance_Amount"));
	    			j=j+1;
	    		}
	    		///////////////////////////////Get Deductions/////////////////////////////////////////
				String sEmployeeDeduction = "Select * from Employee_Monthly_Deduction where Monthly_Payroll_id="+monthly_Payroll_id+" and Employee_id="+resultEmployees.getInt("Employee_id");
				ResultSet resultEmployeeDeduction = statementEmployeeDeduction.executeQuery(sEmployeeDeduction);
				while(resultEmployeeDeduction.next())
				{
		    		employee_Deduction_Amount.add(new ArrayList<String>());
		    		employee_Deduction_Amount.get(k).add(0,null);
		    		employee_Deduction_Amount.get(k).set(0, resultEmployees.getString("Employee_id"));
		    		employee_Deduction_Amount.get(k).add(1,null);
		    		employee_Deduction_Amount.get(k).set(1, resultEmployeeDeduction.getString("Deduction_Name"));
		    		employee_Deduction_Amount.get(k).add(2,null);
		    		employee_Deduction_Amount.get(k).set(2, resultEmployeeDeduction.getString("Deduction_Amount"));
		    		k=k+1;
				}
	    	}
			Payroll.setMonthly_Payroll_Date(monthly_Payroll_Date);
			Payroll.setPayroll_Month(payroll_Month);
			Payroll.setPayroll_Year(payroll_Year);
			Payroll.setEmployee_id(employee_id);
			Payroll.setFirst_Name(first_Name);
			Payroll.setLast_Name(last_Name);
			Payroll.setBasic_Salary(basic_Salary);
			Payroll.setUnpaid_Leave(unpaid_Leave);
			Payroll.setOvertime_Earning(overtime_Earning);
			Payroll.setEmployee_Allowance_Amount(employee_Allowance_Amount);
			Payroll.setEmployee_Deduction_Amount(employee_Deduction_Amount);
			Payroll.setNet_Salary(net_Salary);
			Payroll.setTotal_Salaries(total_Salaries);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll;
	}	
	public Allowance_List getMonthlyAllowanceList(int monthly_Payroll_id ){
		String query = "select * from Employee_Monthly_Allowance where Monthly_Payroll_id="+monthly_Payroll_id+" group by Allowance_Name";
		Allowance_List Allowance_List = new Allowance_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<String> allowance_Name = new ArrayList<String>();
    		while (result.next())
	    	{			   		
    			allowance_Name.add(result.getString("Allowance_Name")); 
		    }
    		Allowance_List.setAllowance_Name(allowance_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Allowance_List;
	}
	public Deduction_List getMonthlyDeductionList(int monthly_Payroll_id ){
		String query = "select * from Employee_Monthly_Deduction where Monthly_Payroll_id="+monthly_Payroll_id+" group by Deduction_Name";
		Deduction_List Deduction_List = new Deduction_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<String> deduction_Name = new ArrayList<String>();
    		while (result.next())
	    	{			   		
    			deduction_Name.add(result.getString("Deduction_Name")); 
		    }
    		Deduction_List.setDeduction_Name(deduction_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Deduction_List;
	}
	public int deletePayrollReport(int monthly_Payroll_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Monthly_Payroll where Monthly_Payroll_id="+monthly_Payroll_id;
		String sDeleteJournalPosting = "Delete from General_Journal where Entry_id=(Select Journal_Entry_id from Monthly_Payroll where Monthly_Payroll_id="+monthly_Payroll_id+")";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
			 PreparedStatement pstatementDeleteJournalPosting = connection.prepareStatement(sDeleteJournalPosting);
			 Statement statementOwner = connection.createStatement();){
			
			ResultSet resultOwner = statementOwner.executeQuery(sOwner);
			while (resultOwner.next())
			{
				if (resultOwner.getInt("Account_Owner")==1)
				{
					connection.setAutoCommit(false);
					updateQuery = pstatementDeleteJournalPosting.executeUpdate();
					if(updateQuery!=0)
					{
						updateQuery = pstatement.executeUpdate();
					}
					if(updateQuery!=0)
					{
						connection.commit();
					}
					else
					{
						connection.rollback();
					}
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
}
