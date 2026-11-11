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
import model.beans.admin.Route_Details;
import model.beans.admin.Routes_List;
import model.beans.admin.Transport_Fees;
import model.beans.admin.Vehicle_Details;
import model.beans.admin.Vehicles_List;
import model.dbHelpers.DbConnector;

public class TransportManagement {
	public int createVehicle(Vehicle_Details Vehicle_Details){
		String query = "INSERT INTO Vehicle (Vehicle_id, School_id, Vehicle_Name, Registration_Number, Registration_Date, Registration_Expiry, Registration_Owner, Chassis_Number, Engine_Number, Model_Name, Model_Year, Cubic_Capacity, Vehicle_Color, Insurance_Number, Insurance_Date, Insurance_Expiry) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String Registration_Date = Vehicle_Details.getRegistration_Date();
			String Registration_Expiry  = Vehicle_Details.getRegistration_Expiry();
			String Insurance_Date = Vehicle_Details.getInsurance_Date();
			String Insurance_Expiry  = Vehicle_Details.getInsurance_Expiry();
			java.util.Date dRegistration_Date=null;
			java.util.Date dRegistration_Expiry=null;
			java.util.Date dInsurance_Date=null;
			java.util.Date dInsurance_Expiry=null;
			try {
				dRegistration_Date = formater.parse(Registration_Date);
				dRegistration_Expiry = formater.parse(Registration_Expiry);
				if(Insurance_Date!=null && !Insurance_Date.equals(""))
				{
					dInsurance_Date = formater.parse(Insurance_Date);
				}
				if(Insurance_Expiry!=null && !Insurance_Expiry.equals(""))
				{
					dInsurance_Expiry = formater.parse(Insurance_Expiry);
				}							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatement.setInt(1, Vehicle_Details.getSchool_id());
			pstatement.setString(2, Vehicle_Details.getVehicle_Name());
			pstatement.setString(3, Vehicle_Details.getRegistration_Number());
			pstatement.setDate(4, new Date(dRegistration_Date.getTime()));
			pstatement.setDate(5, new Date(dRegistration_Expiry.getTime()));			
			if(Vehicle_Details.getRegistration_Owner()!=null && !Vehicle_Details.getRegistration_Owner().equals(""))
			{
				pstatement.setString(6, Vehicle_Details.getRegistration_Owner());
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
			}		
			if(Vehicle_Details.getChassis_Number()!=null && !Vehicle_Details.getChassis_Number().equals(""))
			{
				pstatement.setString(7, Vehicle_Details.getChassis_Number());
			}
			else
			{
				pstatement.setNull(7, java.sql.Types.NULL);
			}				
			if(Vehicle_Details.getEngine_Number()!=null && !Vehicle_Details.getEngine_Number().equals(""))
			{
				pstatement.setString(8, Vehicle_Details.getEngine_Number());
			}
			else
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getModel_Name()!=null && !Vehicle_Details.getModel_Name().equals(""))
			{
				pstatement.setString(9, Vehicle_Details.getModel_Name());
			}
			else
			{
				pstatement.setNull(9, java.sql.Types.NULL);
			}			
			if(Vehicle_Details.getModel_Year()!=0)
			{
				pstatement.setInt(10, Vehicle_Details.getModel_Year());
			}
			else
			{
				pstatement.setNull(10, java.sql.Types.NULL);
			}	
			if(Vehicle_Details.getCubic_Capacity()!=0)
			{
				pstatement.setInt(11, Vehicle_Details.getCubic_Capacity());
			}
			else
			{
				pstatement.setNull(11, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getVehicle_Color()!=null && !Vehicle_Details.getVehicle_Color().equals(""))
			{
				pstatement.setString(12, Vehicle_Details.getVehicle_Color());
			}
			else
			{
				pstatement.setNull(12, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getInsurance_Number()!=null && !Vehicle_Details.getInsurance_Number().equals(""))
			{
				pstatement.setString(13, Vehicle_Details.getInsurance_Number());
			}
			else
			{
				pstatement.setNull(13, java.sql.Types.NULL);
			}			
			
			if(dInsurance_Date!=null)
			{
				pstatement.setDate(14,new java.sql.Date(dInsurance_Date.getTime()));
			}
			else
			{
				pstatement.setNull(14,java.sql.Types.NULL);
			}
			if(dInsurance_Expiry!=null)
			{
				pstatement.setDate(15,new java.sql.Date(dInsurance_Expiry.getTime()));
			}
			else
			{
				pstatement.setNull(15,java.sql.Types.NULL);
			}
			updateQuery = pstatement.executeUpdate();
	       
		}
		catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateVehicle(Vehicle_Details Vehicle_Details, int vehicle_id){
		String query = "update Vehicle set School_id=?, Vehicle_Name=?, Registration_Number=?, Registration_Date=?, Registration_Expiry=?, Registration_Owner=?, Chassis_Number=?, Engine_Number=?, Model_Name=?, Model_Year=?, Cubic_Capacity=?, Vehicle_Color=?, Insurance_Number=?, Insurance_Date=?, Insurance_Expiry=? where Vehicle_id="+vehicle_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String Registration_Date = Vehicle_Details.getRegistration_Date();
			String Registration_Expiry  = Vehicle_Details.getRegistration_Expiry();
			String Insurance_Date = Vehicle_Details.getInsurance_Date();
			String Insurance_Expiry  = Vehicle_Details.getInsurance_Expiry();
			java.util.Date dRegistration_Date=null;
			java.util.Date dRegistration_Expiry=null;
			java.util.Date dInsurance_Date=null;
			java.util.Date dInsurance_Expiry=null;
			try {
				dRegistration_Date = formater.parse(Registration_Date);
				dRegistration_Expiry = formater.parse(Registration_Expiry);
				if(Insurance_Date!=null && !Insurance_Date.equals(""))
				{
					dInsurance_Date = formater.parse(Insurance_Date);
				}
				if(Insurance_Expiry!=null && !Insurance_Expiry.equals(""))
				{
					dInsurance_Expiry = formater.parse(Insurance_Expiry);
				}							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pstatement.setInt(1, Vehicle_Details.getSchool_id());
			pstatement.setString(2, Vehicle_Details.getVehicle_Name());
			pstatement.setString(3, Vehicle_Details.getRegistration_Number());
			pstatement.setDate(4, new Date(dRegistration_Date.getTime()));
			pstatement.setDate(5, new Date(dRegistration_Expiry.getTime()));			
			if(Vehicle_Details.getRegistration_Owner()!=null && !Vehicle_Details.getRegistration_Owner().equals(""))
			{
				pstatement.setString(6, Vehicle_Details.getRegistration_Owner());
			}
			else
			{
				pstatement.setNull(6, java.sql.Types.NULL);
			}		
			if(Vehicle_Details.getChassis_Number()!=null && !Vehicle_Details.getChassis_Number().equals(""))
			{
				pstatement.setString(7, Vehicle_Details.getChassis_Number());
			}
			else
			{
				pstatement.setNull(7, java.sql.Types.NULL);
			}				
			if(Vehicle_Details.getEngine_Number()!=null && !Vehicle_Details.getEngine_Number().equals(""))
			{
				pstatement.setString(8, Vehicle_Details.getEngine_Number());
			}
			else
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getModel_Name()!=null && !Vehicle_Details.getModel_Name().equals(""))
			{
				pstatement.setString(9, Vehicle_Details.getModel_Name());
			}
			else
			{
				pstatement.setNull(9, java.sql.Types.NULL);
			}			
			if(Vehicle_Details.getModel_Year()!=0)
			{
				pstatement.setInt(10, Vehicle_Details.getModel_Year());
			}
			else
			{
				pstatement.setNull(10, java.sql.Types.NULL);
			}	
			if(Vehicle_Details.getCubic_Capacity()!=0)
			{
				pstatement.setInt(11, Vehicle_Details.getCubic_Capacity());
			}
			else
			{
				pstatement.setNull(11, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getVehicle_Color()!=null && !Vehicle_Details.getVehicle_Color().equals(""))
			{
				pstatement.setString(12, Vehicle_Details.getVehicle_Color());
			}
			else
			{
				pstatement.setNull(12, java.sql.Types.NULL);
			}
			if(Vehicle_Details.getInsurance_Number()!=null && !Vehicle_Details.getInsurance_Number().equals(""))
			{
				pstatement.setString(13, Vehicle_Details.getInsurance_Number());
			}
			else
			{
				pstatement.setNull(13, java.sql.Types.NULL);
			}			
			
			if(dInsurance_Date!=null)
			{
				pstatement.setDate(14,new java.sql.Date(dInsurance_Date.getTime()));
			}
			else
			{
				pstatement.setNull(14,java.sql.Types.NULL);
			}
			if(dInsurance_Expiry!=null)
			{
				pstatement.setDate(15,new java.sql.Date(dInsurance_Expiry.getTime()));
			}
			else
			{
				pstatement.setNull(15,java.sql.Types.NULL);
			}
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

	public int deleteVehicle(int vehicle_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Vehicle where Vehicle_id="+vehicle_id;
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

	public Vehicles_List getVehiclesList(int school_id ){
		String query = "select * from Vehicle where school_id="+school_id;
		Vehicles_List Vehicles_List = new Vehicles_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> vehicle_id = new ArrayList<Integer>();
	    	ArrayList<String> vehicle_Name = new ArrayList<String>();
	    	ArrayList<String> registration_Number = new ArrayList<String>();
	    	ArrayList<String> registration_Date = new ArrayList<String>();
	    	ArrayList<String> registration_Expiry = new ArrayList<String>();
	    	ArrayList<String> registration_Owner = new ArrayList<String>();
	    	ArrayList<String> chassis_Number = new ArrayList<String>();
	    	ArrayList<String> engine_Number = new ArrayList<String>();
	    	ArrayList<String> model_Name = new ArrayList<String>();
	    	ArrayList<Integer> model_Year = new ArrayList<Integer>();
	    	ArrayList<Integer> cubic_Capacity = new ArrayList<Integer>();
	    	ArrayList<String> vehicle_Color = new ArrayList<String>();
	    	ArrayList<String> insurance_Number = new ArrayList<String>();
	    	ArrayList<String> insurance_Date = new ArrayList<String>();
	    	ArrayList<String> insurance_Expiry = new ArrayList<String>();
    		while (result.next())
	    	{			
    			vehicle_id.add(result.getInt("Vehicle_id"));	    		
    			vehicle_Name.add(result.getString("Vehicle_Name"));   		
    			registration_Number.add(result.getString("Registration_Number"));
    			registration_Date.add(result.getString("Registration_Date"));
    			registration_Expiry.add(result.getString("Registration_Expiry"));
    			registration_Owner.add(result.getString("Registration_Owner"));
    			chassis_Number.add(result.getString("Chassis_Number"));
    			engine_Number.add(result.getString("Engine_Number"));
    			model_Name.add(result.getString("Model_Name"));
    			model_Year.add(result.getInt("Model_Year"));
    			cubic_Capacity.add(result.getInt("Cubic_Capacity"));
    			vehicle_Color.add(result.getString("Vehicle_Color"));
    			insurance_Number.add(result.getString("Insurance_Number"));
    			insurance_Date.add(result.getString("Insurance_Date"));
    			insurance_Expiry.add(result.getString("Insurance_Expiry"));
		    }
    		Vehicles_List.setVehicle_id(vehicle_id);
    		Vehicles_List.setVehicle_Name(vehicle_Name);
    		Vehicles_List.setRegistration_Number(registration_Number);
    		Vehicles_List.setRegistration_Date(registration_Date);
    		Vehicles_List.setRegistration_Expiry(registration_Expiry);
    		Vehicles_List.setRegistration_Owner(registration_Owner);
    		Vehicles_List.setChassis_Number(chassis_Number);
    		Vehicles_List.setEngine_Number(engine_Number);
    		Vehicles_List.setModel_Name(model_Name);
    		Vehicles_List.setModel_Year(model_Year);
    		Vehicles_List.setCubic_Capacity(cubic_Capacity);
    		Vehicles_List.setVehicle_Color(vehicle_Color);	
    		Vehicles_List.setInsurance_Number(insurance_Number);
    		Vehicles_List.setInsurance_Date(insurance_Date);
    		Vehicles_List.setInsurance_Expiry(insurance_Expiry);
    		Vehicles_List.setSchool_id(school_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Vehicles_List;
	}

	public int createRoute(Route_Details Route_Details){
		String query = "INSERT INTO Vehicle_Route (Route_id, Route_Name, Vehicle_id, Driver_id, Matron_id, Debit_Account, Credit_Account) VALUES (Null,?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setString(1, Route_Details.getRoute_Name());
			pstatement.setInt(2, Route_Details.getVehicle_id());
			if(Route_Details.getDriver_id()!=0)
			{
				pstatement.setInt(3, Route_Details.getDriver_id());
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}
			if(Route_Details.getMatron_id()!=0)
			{
				pstatement.setInt(4, Route_Details.getMatron_id());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}	
			pstatement.setInt(5, Route_Details.getDebit_Account_id());
			pstatement.setInt(6, Route_Details.getCredit_Account_id());
	        updateQuery = pstatement.executeUpdate();
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Route_Vehicle_Unique"))
				updateQuery=-1;		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
			
	public int updateRoute(Route_Details Route_Details, int route_id){
		String query = "update Vehicle_Route set Route_Name=?, Driver_id=?, Matron_id=?, Debit_Account=?, Credit_Account=? where Route_id="+route_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setString(1, Route_Details.getRoute_Name());
			if(Route_Details.getDriver_id()!=0)
			{
				pstatement.setInt(2, Route_Details.getDriver_id());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}
			if(Route_Details.getMatron_id()!=0)
			{
				pstatement.setInt(3, Route_Details.getMatron_id());
			}
			else
			{
				pstatement.setNull(3, java.sql.Types.NULL);
			}			
			pstatement.setInt(4, Route_Details.getDebit_Account_id());
			pstatement.setInt(5, Route_Details.getCredit_Account_id());
	        updateQuery = pstatement.executeUpdate();
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Route_Vehicle_Unique"))
				updateQuery=-1;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteRoute(int route_id){
		String query = "Delete from Vehicle_Route where Route_id="+route_id;
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
	public Routes_List getRoutesList(int vehicle_id ){
		String query = "select * from Vehicle_Route where vehicle_id="+vehicle_id;
		Routes_List Routes_List = new Routes_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementSchool_id = connection.createStatement();
				Statement statementDriver = connection.createStatement();
				Statement statementMatron = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> route_id = new ArrayList<Integer>();
	    	ArrayList<String> route_Name = new ArrayList<String>();
	    	ArrayList<Integer> driver_id = new ArrayList<Integer>();
	    	ArrayList<String> driver_Name = new ArrayList<String>();
	    	ArrayList<Integer> matron_id = new ArrayList<Integer>();
	    	ArrayList<String> matron_Name = new ArrayList<String>();
	    	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	    	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			route_id.add(result.getInt("Route_id"));	    		
    			route_Name.add(result.getString("Route_Name"));
    			driver_id.add(result.getInt("Driver_id"));
    			matron_id.add(result.getInt("Matron_id"));
    			debit_Account.add(result.getInt("Debit_Account"));
    			credit_Account.add(result.getInt("Credit_Account"));   
    			//////////////////Get Driver Name and Matron Name/////////////////////////////////////////////////////////
    			if(result.getString("Driver_id")!=null)
    			{
        			String sDriver = "Select First_Name, Last_Name from Admins where admin_id="+result.getInt("Driver_id");
        			ResultSet resultDriver = statementDriver.executeQuery(sDriver);	
        			while(resultDriver.next())
        			{
        				driver_Name.add(resultDriver.getString("First_Name")+" "+resultDriver.getString("Last_Name"));
        			}
    			}
    			else
    			{
    				driver_Name.add(null);
    			}

    			if(result.getString("Matron_id")!=null)
    			{
        			String sMatron = "Select First_Name, Last_Name from Admins where admin_id="+result.getInt("Matron_id");
        			ResultSet resultMatron = statementMatron.executeQuery(sMatron);	
        			while(resultMatron.next())
        			{
        				matron_Name.add(resultMatron.getString("First_Name")+" "+resultMatron.getString("Last_Name"));
        			}
    			}
    			else
    			{
    				matron_Name.add(null);
    			}

    			////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    }
    		Routes_List.setRoute_id(route_id);
    		Routes_List.setRoute_Name(route_Name);
    		Routes_List.setVehicle_id(vehicle_id);
    		Routes_List.setDriver_id(driver_id);
    		Routes_List.setDriver_Name(driver_Name);
    		Routes_List.setMatron_id(matron_id);
    		Routes_List.setMatron_Name(matron_Name);
    		Routes_List.setDebit_Account(debit_Account);
    		Routes_List.setCredit_Account(credit_Account);
    		String sSchool_Id = "select school_id from vehicle where vehicle_id="+vehicle_id;
    		ResultSet resultSchool_id = statementSchool_id.executeQuery(sSchool_Id);	
    		int school_id=0;
    		while(resultSchool_id.next())
    		{
    			school_id = resultSchool_id.getInt("School_id");
    		}
    		Routes_List.setSchool_id(school_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Routes_List;
	}

	public Routes_List getRoutesListBySchool(int school_id ){
		String query = "select * from Vehicle_Route where vehicle_id in (select vehicle_id from vehicle where school_id="+school_id+")";
		Routes_List Routes_List = new Routes_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				Statement statementVehicleName = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> route_id = new ArrayList<Integer>();
	    	ArrayList<String> route_Name = new ArrayList<String>();
	    	ArrayList<String> vehicle_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
				String vehicleName = null;
    			String sVehicleName = "Select Vehicle_Name from Vehicle where Vehicle_id="+result.getInt("Vehicle_id");
				ResultSet resultVehicleName = statementVehicleName.executeQuery(sVehicleName);
				while(resultVehicleName.next())
				{
					vehicleName = resultVehicleName.getString("Vehicle_Name");
				}
    			route_id.add(result.getInt("Route_id"));	    		
    			route_Name.add(result.getString("Route_Name"));
    			vehicle_Name.add(vehicleName);

		    }
    		Routes_List.setRoute_id(route_id);
    		Routes_List.setRoute_Name(route_Name);
    		Routes_List.setVehicle_Name(vehicle_Name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Routes_List;
	}
	
	public Transport_Fees getTransportFees(int school_id, int school_Year_id){
		String query = "select * from Transport_Fees where route_id in (select route_id from Vehicle_Route where vehicle_id in (select vehicle_id from vehicle where school_id="+school_id+")) and School_Year_id="+school_Year_id;
		Transport_Fees Transport_Fees = new Transport_Fees(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> route_id = new ArrayList<Integer>();
	    	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	    	ArrayList<Double> transport_Fees = new ArrayList<Double>();
    		while (result.next())
	    	{			   			
    			route_id.add(result.getInt("Route_id"));	    				
    			school_year_id.add(result.getInt("School_Year_id"));
    			transport_Fees.add(result.getDouble("Transport_Fee_Amount"));
  	
		    }
    		Transport_Fees.setRoute_id(route_id);
    		Transport_Fees.setSchool_year_id(school_year_id);
    		Transport_Fees.setTransport_Fees(transport_Fees);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Transport_Fees;
	}
	public Transport_Fees getStudentTransportFees(int school_Year_id, int student_id){
		String query = "select * from Transport_Fees where route_id = (select route_id from students where student_id="+student_id+") and School_Year_id="+school_Year_id;
		Transport_Fees Transport_Fees = new Transport_Fees(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> route_id = new ArrayList<Integer>();
	    	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	    	ArrayList<Double> transport_Fees = new ArrayList<Double>();
    		while (result.next())
	    	{			   			
    			route_id.add(result.getInt("Route_id"));	    				
    			school_year_id.add(result.getInt("School_Year_id"));
    			transport_Fees.add(result.getDouble("Transport_Fee_Amount"));
  	
		    }
    		Transport_Fees.setRoute_id(route_id);
    		Transport_Fees.setSchool_year_id(school_year_id);
    		Transport_Fees.setTransport_Fees(transport_Fees);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Transport_Fees;
	}	
	public int manageTransportFees(int route_id, int school_Year_id, double transport_Fees){

		int updateQuery = 0;
		String FeeStructureCreate = "INSERT INTO Transport_Fees (Route_id, School_Year_id, Transport_Fee_Amount) VALUES (?,?, ?)";
		String FeeStructureUpdate = "Update Transport_Fees set Transport_Fee_Amount=? where Route_id="+route_id+" and School_Year_id="+school_Year_id;
		String FeeStructureDelete = "Delete from Transport_Fees where Route_id="+route_id+" and School_Year_id="+school_Year_id;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatementCreate = connection.prepareStatement(FeeStructureCreate);
				PreparedStatement pstatementUpdate = connection.prepareStatement(FeeStructureUpdate);
				PreparedStatement pstatementDelete = connection.prepareStatement(FeeStructureDelete);
				Statement statementFeeAmountExist = connection.createStatement();){

				String sFeeAmountExist = "select * from Transport_Fees where Route_id="+route_id+" and School_Year_id="+school_Year_id;
				ResultSet resultFeeAmountExist = statementFeeAmountExist.executeQuery(sFeeAmountExist);
				if (!resultFeeAmountExist.isBeforeFirst())
				{
					if(transport_Fees!=-1)
					{
						pstatementCreate.setInt(1, route_id);
						pstatementCreate.setInt(2, school_Year_id);
						pstatementCreate.setDouble(3, transport_Fees);
						updateQuery = pstatementCreate.executeUpdate();	
					}
								
				}
				else
				{
					if(transport_Fees!=-1)
					{
						pstatementUpdate.setDouble(1, transport_Fees);
						updateQuery = pstatementUpdate.executeUpdate();	
					}
					else
					{
						updateQuery = pstatementDelete.executeUpdate();	
					}						
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
}
