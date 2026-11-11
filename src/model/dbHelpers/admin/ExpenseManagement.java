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
import model.beans.admin.Expense_Details;
import model.beans.admin.Expense_Types;
import model.beans.admin.Expense_Types_List;
import model.beans.admin.Expenses_List;
import model.dbHelpers.DbConnector;

public class ExpenseManagement {
	public int createExpenseType(Expense_Types Expense_Types){
		String query = "INSERT INTO Expense_Types (Expense_Type_id, Expense_Type_Name, Expense_Type_Description, School_id, Debit_Account, Credit_Account) VALUES (NULL, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, Expense_Types.getExpense_Type_Name());
			if(Expense_Types.getExpense_Type_Description()!=null && !Expense_Types.getExpense_Type_Description().equals(""))
			{
				pstatement.setString(2, Expense_Types.getExpense_Type_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Expense_Types.getSchool_id());
			pstatement.setInt(4, Expense_Types.getDebit_Account_id());
			pstatement.setInt(5, Expense_Types.getCredit_Account_id());
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
	public int updateExpenseType(Expense_Types Expense_Types, int expense_Type_id){
		String query = "update Expense_types set Expense_Type_Name=?, Expense_Type_Description=?, School_id=?, Debit_Account=?, Credit_Account=? where Expense_Type_id="+expense_Type_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, Expense_Types.getExpense_Type_Name());
			if(Expense_Types.getExpense_Type_Description()!=null && !Expense_Types.getExpense_Type_Description().equals(""))
			{
				pstatement.setString(2, Expense_Types.getExpense_Type_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Expense_Types.getSchool_id());
			pstatement.setInt(4, Expense_Types.getDebit_Account_id());
			pstatement.setInt(5, Expense_Types.getCredit_Account_id());
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

	public int deleteExpenseType(int expense_Type_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Expense_types where Expense_Type_id="+expense_Type_id;
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
	
	public Expense_Types_List getExpenseTypesList(int school_id ){
		String query = "select * from Expense_Types where school_id="+school_id;
		Expense_Types_List Expense_Types_List = new Expense_Types_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> expense_Type_id = new ArrayList<Integer>();
	    	ArrayList<String> expense_Type_Name = new ArrayList<String>();
	    	ArrayList<String> expense_Type_Description = new ArrayList<String>();
	    	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	    	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			expense_Type_id.add(result.getInt("Expense_Type_id"));    		
    			expense_Type_Name.add(result.getString("Expense_Type_Name")); 
    			expense_Type_Description.add(result.getString("Expense_Type_Description"));
    			debit_Account.add(result.getInt("Debit_Account"));
    			credit_Account.add(result.getInt("Credit_Account"));
		    }
    		Expense_Types_List.setExpense_Type_id(expense_Type_id);
    		Expense_Types_List.setExpense_Type_Name(expense_Type_Name);
    		Expense_Types_List.setExpense_Type_Description(expense_Type_Description);
    		Expense_Types_List.setDebit_Account(debit_Account);
    		Expense_Types_List.setCredit_Account(credit_Account);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Expense_Types_List;
	}
	public int createExpense(Expense_Details Expense_Details){
		String query = "INSERT INTO Expenses (Expense_id, Expense_Date, Reference_Number, Expense_Type_id, Paid_To, Expense_Amount, Payment_Method, Phone_Number, Expense_Details) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sCreateExpenseJournal = "INSERT INTO General_Journal (Entry_id, Entry_Date, Debit_Account, Credit_Account, Amount, Details) VALUES (Null, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement pstatementCreateExpenseJournal = connection.prepareStatement(sCreateExpenseJournal, Statement.RETURN_GENERATED_KEYS);
			 Statement statementDebitCreditAccounts = connection.createStatement();){

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String Expense_Date = Expense_Details.getExpense_Date();
			java.util.Date dExpense_Date=null;
			try {
				dExpense_Date = formater.parse(Expense_Date);							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatement.setDate(1, new Date(dExpense_Date.getTime()));			
			if(Expense_Details.getReference_Number()!=null && !Expense_Details.getReference_Number().equals(""))
			{
				pstatement.setString(2, Expense_Details.getReference_Number());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Expense_Details.getExpense_Type_id());
			if(Expense_Details.getPaid_To()!=null && !Expense_Details.getPaid_To().equals(""))
			{
				pstatement.setString(4, Expense_Details.getPaid_To());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}
			pstatement.setDouble(5, Expense_Details.getExpense_Amount());	
			pstatement.setString(6, Expense_Details.getPayment_Method());			
				
			if(Expense_Details.getPhone_Number()!=null && !Expense_Details.getPhone_Number().equals(""))
			{
				pstatement.setString(7, Expense_Details.getPhone_Number());
			}
			else
			{
				pstatement.setNull(7, java.sql.Types.NULL);
			}			
			if(Expense_Details.getExpense_Details()!=null && !Expense_Details.getExpense_Details().equals(""))
			{
				pstatement.setString(8, Expense_Details.getExpense_Details());
			}
			else
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			updateQuery = pstatement.executeUpdate();
			///////////////////////////Create Journal Entry///////////////////////////////////////////////////
			if(updateQuery!=0&&updateQuery!=-1)
			{
				int expense_id=0;
				ResultSet resultExpense_id = pstatement.getGeneratedKeys();
				if(resultExpense_id.next()) 
				{
				       expense_id = resultExpense_id.getInt(1);
				       resultExpense_id.close();
				}
				String sDebitCreditAccounts = "select * from Expense_types where Expense_Type_id="+Expense_Details.getExpense_Type_id();
				ResultSet resultDebitCreditAccounts = statementDebitCreditAccounts.executeQuery(sDebitCreditAccounts);
				while(resultDebitCreditAccounts.next())
				{
					pstatementCreateExpenseJournal.setDate(1, new Date(dExpense_Date.getTime()));
					pstatementCreateExpenseJournal.setInt(2, resultDebitCreditAccounts.getInt("Debit_Account"));
					pstatementCreateExpenseJournal.setInt(3, resultDebitCreditAccounts.getInt("Credit_Account"));
					pstatementCreateExpenseJournal.setDouble(4, Expense_Details.getExpense_Amount());
					pstatementCreateExpenseJournal.setString(5, "Expense for Expense type ("+resultDebitCreditAccounts.getString("Expense_Type_Name")+") ");
					updateQuery = pstatementCreateExpenseJournal.executeUpdate();
					if(updateQuery!=0)
					{
						int journal_Entry_id=0;
						ResultSet resultJournal_Entry_id = pstatementCreateExpenseJournal.getGeneratedKeys();
						if(resultJournal_Entry_id.next()) 
						{
							   journal_Entry_id = resultJournal_Entry_id.getInt(1);
							   resultJournal_Entry_id.close();
						}
						/////////////////////Update newly created expense by the Journal Entry ID///////////////
						String sExpenseJournal = "update Expenses set Journal_Entry_id=? where Expense_id="+expense_id;
						PreparedStatement pstatementExpenseJournal = connection.prepareStatement(sExpenseJournal);
						pstatementExpenseJournal.setInt(1, journal_Entry_id);
						pstatementExpenseJournal.executeUpdate();
						pstatementExpenseJournal.close();
						///////////////////////////////////////////////////////////////////////////////////////
					}
				}
			}
			
			///////////////////////////////////////////////////////////////////////////////////////////////////			
	       
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
	public int updateExpense(Expense_Details Expense_Details, int expense_id){
		String query = "update Expenses set Expense_Date=?, Reference_Number=?, Expense_Type_id=?, Paid_To=?, Expense_Amount=?, Payment_Method=?, Phone_Number=?, Expense_Details=? where Expense_id="+expense_id;
		String sJournal = "update General_Journal set Entry_Date=?, Amount=? where Entry_id=(select Journal_Entry_id from Expenses where expense_id="+expense_id+")";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
			 PreparedStatement pstatementJournal = connection.prepareStatement(sJournal);){


			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			String Expense_Date = Expense_Details.getExpense_Date();
			java.util.Date dExpense_Date=null;
			try {
				dExpense_Date = formater.parse(Expense_Date);							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatement.setDate(1, new Date(dExpense_Date.getTime()));			
			if(Expense_Details.getReference_Number()!=null && !Expense_Details.getReference_Number().equals(""))
			{
				pstatement.setString(2, Expense_Details.getReference_Number());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
			pstatement.setInt(3, Expense_Details.getExpense_Type_id());
			if(Expense_Details.getPaid_To()!=null && !Expense_Details.getPaid_To().equals(""))
			{
				pstatement.setString(4, Expense_Details.getPaid_To());
			}
			else
			{
				pstatement.setNull(4, java.sql.Types.NULL);
			}
			pstatement.setDouble(5, Expense_Details.getExpense_Amount());	
			pstatement.setString(6, Expense_Details.getPayment_Method());			
				
			if(Expense_Details.getPhone_Number()!=null && !Expense_Details.getPhone_Number().equals(""))
			{
				pstatement.setString(7, Expense_Details.getPhone_Number());
			}
			else
			{
				pstatement.setNull(7, java.sql.Types.NULL);
			}			
			if(Expense_Details.getExpense_Details()!=null && !Expense_Details.getExpense_Details().equals(""))
			{
				pstatement.setString(8, Expense_Details.getExpense_Details());
			}
			else
			{
				pstatement.setNull(8, java.sql.Types.NULL);
			}
			updateQuery = pstatement.executeUpdate();
			///////////////////////////Create Journal Entry///////////////////////////////////////////////////
			if(updateQuery!=0&&updateQuery!=-1)
			{

				/////////////////////Update the Journal Entry for the expense///////////////
				pstatementJournal.setDate(1, new Date(dExpense_Date.getTime()));
				pstatementJournal.setDouble(2, Expense_Details.getExpense_Amount());
				pstatementJournal.executeUpdate();
				///////////////////////////////////////////////////////////////////////////////////////
			}
			
			///////////////////////////////////////////////////////////////////////////////////////////////////		       
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
	public int deleteExpense(int expense_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Expenses where Expense_id="+expense_id;
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
	public Expenses_List getExpensesList(int expense_Type_id ){
		String query = "select * from Expenses where Expense_Type_id="+expense_Type_id;
		Expenses_List Expenses_List = new Expenses_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();
			 Statement statementExpenseTypeName = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> expense_id = new ArrayList<Integer>();
	    	ArrayList<String> expense_Date = new ArrayList<String>();
	    	ArrayList<String> reference_Number = new ArrayList<String>();
	    	ArrayList<String> expense_Type_Name = new ArrayList<String>();
	    	ArrayList<String> paid_To = new ArrayList<String>();
	    	ArrayList<Double> expense_Amount = new ArrayList<Double>();
	    	ArrayList<String> payment_Method = new ArrayList<String>();
	    	ArrayList<String> phone_Number = new ArrayList<String>();
	    	ArrayList<String> expense_Details = new ArrayList<String>();
    		while (result.next())
	    	{			
    			expense_id.add(result.getInt("Expense_id"));	    		  		
    			expense_Date.add(result.getString("Expense_Date"));
    			reference_Number.add(result.getString("Reference_Number"));
    			/////////////Get Expense Type Name /////////////////////////////
    			String sExpenseTypeName = "select * from Expense_Types where Expense_Type_id="+result.getInt("Expense_Type_id");
    			ResultSet resultExpenseTypeName = statementExpenseTypeName.executeQuery(sExpenseTypeName);	
    			while(resultExpenseTypeName.next())
    			{
    				expense_Type_Name.add(resultExpenseTypeName.getString("Expense_Type_Name"));
    			}
    			////////////////////////////////////////////////////////////////
    			paid_To.add(result.getString("Paid_To"));
    			expense_Amount.add(result.getDouble("Expense_Amount"));
    			payment_Method.add(result.getString("Payment_Method"));
    			phone_Number.add(result.getString("Phone_Number"));
    			expense_Details.add(result.getString("Expense_Details"));
		    }
    		Expenses_List.setExpense_id(expense_id);
    		Expenses_List.setExpense_Date(expense_Date);
    		Expenses_List.setReference_Number(reference_Number);
    		Expenses_List.setExpense_Type_Name(expense_Type_Name);
    		Expenses_List.setPaid_To(paid_To);
    		Expenses_List.setExpense_Amount(expense_Amount);
    		Expenses_List.setPayment_Method(payment_Method);
    		Expenses_List.setPhone_Number(phone_Number);
    		Expenses_List.setExpense_Details(expense_Details);
    		Expenses_List.setExpense_Type_id(expense_Type_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Expenses_List;
	}
}
