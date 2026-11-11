package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.School_Details;
import model.beans.admin.School_Weekend;
import model.dbHelpers.DbConnector;

public class SchoolManagement {
	public School_Details getSchool(int school_id){
		String query = "select * from school where school_id="+school_id;
		School_Details school_Details = new School_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
				 Statement statementSchoolCountry = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				school_Details.setName(result.getString("name"));
				school_Details.setCountry_id(result.getInt("Country_id"));
				school_Details.setCity(result.getString("City"));
				if(result.getInt("pO_Box")!=0)
				{
					school_Details.setPO_Box(result.getInt("pO_Box"));
				}				
				else
				{
					school_Details.setPO_Box(-1);
				}			
				school_Details.setFull_Address(result.getString("full_Address"));
				school_Details.setWebsite(result.getString("website"));
				school_Details.setEmail(result.getString("email"));
			}
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_Details;
	}
	
	public int getSchoolByName(String name){
		String query = "select * from school where name='"+name+"'";
		int school_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			result.next();
			school_id = result.getInt("School_id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_id;
	}
	
	public int getSchoolByGrade(int grade_id){
		String query = "select School_id from Grades where Grade_id="+grade_id;
		int school_id=0;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			result.next();
			school_id = result.getInt("School_id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_id;
	}
	
	public boolean checkSchoolWeekEnd(String day_id, int school_id){
		String query = "select day_id from School_Weekend where day_id="+day_id+" and School_id="+school_id;
		boolean weekendExist=false;
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			if (result.isBeforeFirst())
			{
				weekendExist = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weekendExist;
	}
	public int createSchool(School_Details school_Details){
		String query = "INSERT INTO school (school_id, Name, Country_id, City, PO_Box, full_address, website, email, account_id) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sAdminSchool = "INSERT INTO admin_school (school_id, admin_id) VALUES (?,?)";
		int updateQuery = 0;
		int autoIncValue = -1;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstatementAdminSchool = connection.prepareStatement(sAdminSchool);){
			connection.setAutoCommit(false);
			pstatement.setString(1, school_Details.getName());
			pstatement.setInt(2, school_Details.getCountry_id());
			pstatement.setString(3, school_Details.getCity());
			if(school_Details.getPO_Box()!=-1)
			{
				pstatement.setInt(4, school_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}			
			pstatement.setString(5, school_Details.getFull_Address());
			pstatement.setString(6, school_Details.getWebsite());
			pstatement.setString(7, school_Details.getEmail());
			pstatement.setInt(8, school_Details.getAccount_id());
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				ResultSet resultSchool_id = pstatement.getGeneratedKeys();
				if(resultSchool_id.next()) 
				{
				       autoIncValue = resultSchool_id.getInt(1);
				       resultSchool_id.close();
				}
				pstatementAdminSchool.setInt(1, autoIncValue);
				pstatementAdminSchool.setInt(2, school_Details.getAdmin_id());
				updateQuery = pstatementAdminSchool.executeUpdate();	
				connection.commit();
			}
			else
			{
				connection.rollback();
			}

	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			updateQuery=-1;			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public School_Weekend getSchoolWeekEnd(int school_id){
		String query = "select * from School_Weekend where School_id="+school_id;
		School_Weekend school_Weekend = new School_Weekend(); 
		ArrayList<Integer> School_Weekend_Days = new ArrayList<Integer>();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
			while (result.next())
			{
				School_Weekend_Days.add(Integer.parseInt(result.getString("Day_id")));
				
			}
			school_Weekend.setSchool_Weekend_Days(School_Weekend_Days);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return school_Weekend;
	}
	public int createSchoolWeekEnd(String day_id, int school_id){
		String query =  "INSERT INTO School_Weekend (day_id, School_id) VALUES (?,?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, day_id);
			pstatement.setInt(2, school_id);
			updateQuery = pstatement.executeUpdate();
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteSchoolWeekEnd(String day_id, int school_id){
		String query = "Delete from School_Weekend where School_id="+school_id+" and Day_id="+day_id;
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
	
	public int updateSchool(School_Details school_Details, int school_id){
		String query = "update school set Name=?, Country_id=?, City=?, PO_Box=?, full_address=?, website=?, email=? where School_id="+school_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, school_Details.getName());
			pstatement.setInt(2, school_Details.getCountry_id());
			pstatement.setString(3, school_Details.getCity());
			if(school_Details.getPO_Box()!=-1)
			{
				pstatement.setInt(4, school_Details.getPO_Box());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}
			pstatement.setString(5, school_Details.getFull_Address());
			pstatement.setString(6, school_Details.getWebsite());
			pstatement.setString(7, school_Details.getEmail());
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
	
	public int deleteSchool(int school_id, int admin_id){
		//String query = "Delete from School where School_id="+school_id;
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from School where School_id="+school_id;
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
}
