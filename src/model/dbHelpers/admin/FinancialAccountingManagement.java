package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.beans.admin.Balance_Sheet;
import model.beans.admin.Financial_Account_Details;
import model.beans.admin.Financial_Accounts_List;
import model.beans.admin.Financial_Categories_List;
import model.beans.admin.General_Ledger;
import model.beans.admin.Income_Statement;
import model.beans.admin.Journal_Entry;
import model.beans.admin.Trial_Balance;
import model.dbHelpers.DbConnector;

public class FinancialAccountingManagement {
	public Financial_Categories_List getFinancialCategoriesList(){
		String query = "select * from Financial_Categories";
		Financial_Categories_List Financial_Categories_List = new Financial_Categories_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> financial_Category_id = new ArrayList<Integer>();
	    	ArrayList<String> financial_Category_Name = new ArrayList<String>();
    		while (result.next())
	    	{			
    			financial_Category_id.add(result.getInt("Financial_Category_id"));	    		
    			financial_Category_Name.add(result.getString("Financial_Category_Name"));   		
		    }
    		Financial_Categories_List.setFinancial_Category_id(financial_Category_id);
    		Financial_Categories_List.setFinancial_Category_Name(financial_Category_Name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Financial_Categories_List;
	}
	public Financial_Accounts_List getFinancialAccountsList(int financial_Category_id, int school_id ){
		String query = "select * from Financial_Accounts where Financial_Category_id="+financial_Category_id+" and School_id="+school_id;
		Financial_Accounts_List Financial_Accounts_List = new Financial_Accounts_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> financial_Account_id = new ArrayList<Integer>();
	    	ArrayList<String> financial_Account_Name = new ArrayList<String>();
	    	ArrayList<String> financial_Account_Description = new ArrayList<String>();
    		while (result.next())
	    	{			
    			financial_Account_id.add(result.getInt("Financial_Account_id"));	    		
    			financial_Account_Name.add(result.getString("Financial_Account_Name")); 
    			financial_Account_Description.add(result.getString("Financial_Account_Description"));
		    }
    		Financial_Accounts_List.setFinancial_Account_id(financial_Account_id);
    		Financial_Accounts_List.setFinancial_Account_Name(financial_Account_Name);
    		Financial_Accounts_List.setFinancial_Account_Description(financial_Account_Description);
    		Financial_Accounts_List.setFinancial_Category_id(financial_Category_id);
    		Financial_Accounts_List.setSchool_id(school_id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Financial_Accounts_List;
	}

	public Financial_Accounts_List getAllFinancialAccountsList(int school_id ){
		String query = "select * from Financial_Accounts where School_id="+school_id;
		Financial_Accounts_List Financial_Accounts_List = new Financial_Accounts_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
			ArrayList<Integer> financial_Account_id = new ArrayList<Integer>();
	    	ArrayList<String> financial_Account_Name = new ArrayList<String>();
	    	ArrayList<String> financial_Account_Description = new ArrayList<String>();
	    	ArrayList<Integer> financial_Category = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			financial_Account_id.add(result.getInt("Financial_Account_id"));	    		
    			financial_Account_Name.add(result.getString("Financial_Account_Name")); 
    			financial_Account_Description.add(result.getString("Financial_Account_Description"));
    			financial_Category.add(result.getInt("Financial_Category_id"));
		    }
    		Financial_Accounts_List.setFinancial_Account_id(financial_Account_id);
    		Financial_Accounts_List.setFinancial_Account_Name(financial_Account_Name);
    		Financial_Accounts_List.setFinancial_Account_Description(financial_Account_Description);
    		Financial_Accounts_List.setFinancial_Category(financial_Category);
    		Financial_Accounts_List.setSchool_id(school_id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Financial_Accounts_List;
	}

	public int createFinancialAccount(Financial_Account_Details Financial_Account_Details){
		String query = "INSERT INTO Financial_Accounts (Financial_Account_id, Financial_Account_Name, Financial_Account_Description, Financial_Category_id, School_id) VALUES (Null,?, ?, ?, ?)";
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setString(1, Financial_Account_Details.getFinancial_Account_Name());
			if(Financial_Account_Details.getFinancial_Account_Description()!=null && !Financial_Account_Details.getFinancial_Account_Description().equals(""))
			{
				pstatement.setString(2, Financial_Account_Details.getFinancial_Account_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}			
			pstatement.setInt(3, Financial_Account_Details.getFinancial_Category_id());
			pstatement.setInt(4, Financial_Account_Details.getSchool_id());			
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
			
	public int updateFinancialAccount(Financial_Account_Details Financial_Account_Details, int financial_Account_id){
		String query = "update Financial_Accounts set Financial_Account_Name=?, Financial_Account_Description=? where Financial_Account_id="+financial_Account_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setString(1, Financial_Account_Details.getFinancial_Account_Name());
			if(Financial_Account_Details.getFinancial_Account_Description()!=null && !Financial_Account_Details.getFinancial_Account_Description().equals(""))
			{
				pstatement.setString(2, Financial_Account_Details.getFinancial_Account_Description());
			}
			else
			{
				pstatement.setNull(2, java.sql.Types.NULL);
			}	
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

	public int deleteFinancialAccount(int financial_Account_id){
		String query = "Delete from Financial_Accounts where Financial_Account_id="+financial_Account_id;
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

	public int createJournalEntry(Journal_Entry Journal_Entry){
		String query = "INSERT INTO General_Journal (Entry_id, Entry_Date, Debit_Account, Credit_Account, Amount, Details) VALUES (NULL, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			
			String entry_Date = Journal_Entry.getEntry_Date();
			
			java.util.Date dEntry_Date=null;
			try {
				dEntry_Date = formater.parse(entry_Date);							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatement.setDate(1, new java.sql.Date(dEntry_Date.getTime()));	
			pstatement.setInt(2, Journal_Entry.getDebit_Account_id());
			pstatement.setInt(3, Journal_Entry.getCredit_Account_id());
			pstatement.setDouble(4, Journal_Entry.getJournal_Amount());	
			pstatement.setString(5, Journal_Entry.getJournal_Details());
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

	public int updateJournalEntry(Journal_Entry Journal_Entry, int entry_id){
		String query = "update General_Journal set Entry_Date=?, Debit_Account=?, Credit_Account=?, Amount=?, Details=? where entry_id="+entry_id;
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			
			String entry_Date = Journal_Entry.getEntry_Date();
			
			java.util.Date dEntry_Date=null;
			try {
				dEntry_Date = formater.parse(entry_Date);							
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			pstatement.setDate(1, new java.sql.Date(dEntry_Date.getTime()));	
			pstatement.setInt(2, Journal_Entry.getDebit_Account_id());
			pstatement.setInt(3, Journal_Entry.getCredit_Account_id());
			pstatement.setDouble(4, Journal_Entry.getJournal_Amount());	
			pstatement.setString(5, Journal_Entry.getJournal_Details());
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
	
	public int deleteJournalEntry(int entry_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from General_Journal where Entry_id="+entry_id;
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
	public Trial_Balance getTrialBalance(int school_id, Date trial_Balance_Date_From, Date trial_Balance_Date_To){
		
		Trial_Balance Trial_Balance = new Trial_Balance(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementFinancialAccounts = connection.createStatement();			 
			 Statement statementDebitAmount = connection.createStatement();
			 Statement statementCreditAmount = connection.createStatement();){
			ArrayList<Integer> financial_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Account_Debit = new ArrayList<Double>();
	    	ArrayList<Double> financial_Account_Credit = new ArrayList<Double>();
			
    		double totalDebitAmount = 0.00;
    		double totalCreditAmount = 0.00;
    		String sFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id;
	    	ResultSet resultFinancialAccounts = statementFinancialAccounts.executeQuery(sFinancialAccounts);
	    	while (resultFinancialAccounts.next())
			{
				/////////////Get sum of all debit entries amounts/////////////////////////////////////
	    		String sDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(trial_Balance_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(trial_Balance_Date_To.getTime())+"'";
				ResultSet resultDebitAmount = statementDebitAmount.executeQuery(sDebitAmount);
	    		double debitAmount=0.00;
				while (resultDebitAmount.next())
		    	{			
			    	debitAmount = resultDebitAmount.getDouble(1);
			    }
				String sCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(trial_Balance_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(trial_Balance_Date_To.getTime())+"'";
				ResultSet resultCreditAmount = statementCreditAmount.executeQuery(sCreditAmount);
	    		double creditAmount=0.00;
				while (resultCreditAmount.next())
		    	{			
					creditAmount = resultCreditAmount.getDouble(1);
			    }
				double netDebitCreditAmount = debitAmount - creditAmount;
				
    			financial_Account_id.add(resultFinancialAccounts.getInt("Financial_Account_id"));
    			financial_Account_Name.add(resultFinancialAccounts.getString("Financial_Account_Name"));
    			if(netDebitCreditAmount!=0)
    			{
        			if(netDebitCreditAmount>0)
        			{
        				financial_Account_Debit.add((double) Math.round(netDebitCreditAmount*100)/100);
        				totalDebitAmount = totalDebitAmount + netDebitCreditAmount;
        				financial_Account_Credit.add(null);
        			}
        			else
        			{
        				financial_Account_Credit.add((double) Math.round(netDebitCreditAmount*100)/100*(-1));
        				totalCreditAmount = totalCreditAmount + (netDebitCreditAmount*(-1));
        				financial_Account_Debit.add(null);
        			}			
    			}
    			else
    			{
    				financial_Account_Debit.add(null);
    				financial_Account_Credit.add(null);
    			}    			    		
			}
	    	Trial_Balance.setFinancial_Account_id(financial_Account_id);
	    	Trial_Balance.setFinancial_Account_Name(financial_Account_Name);
	    	Trial_Balance.setFinancial_Account_Debit(financial_Account_Debit);
	    	Trial_Balance.setFinancial_Account_Credit(financial_Account_Credit);
	    	Trial_Balance.setTotalDebitAmount((double) Math.round(totalDebitAmount*100)/100);
	    	Trial_Balance.setTotalCreditAmount((double) Math.round(totalCreditAmount*100)/100);       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Trial_Balance;
	}
	
	public General_Ledger getGeneralLedger(int school_id, int financial_Account_id, Date general_Ledger_Date_From, Date general_Ledger_Date_To){
		
		General_Ledger General_Ledger = new General_Ledger(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementAccountLedger = connection.createStatement();			 
			 Statement statementDebitAmount = connection.createStatement();
			 Statement statementCreditAmount = connection.createStatement();			
			 Statement statementAccountName = connection.createStatement();){
			ArrayList<Integer> entry_id = new ArrayList<Integer>();
			ArrayList<String> journal_Date = new ArrayList<String>();
			ArrayList<String> journal_Details = new ArrayList<String>();
			ArrayList<Double> debit_Amount = new ArrayList<Double>();
	    	ArrayList<Double> credit_Amount = new ArrayList<Double>();
	    	ArrayList<Double> balance_Amount = new ArrayList<Double>();
	    	
	    	if(general_Ledger_Date_From!=null && general_Ledger_Date_To!=null)
    		{
	    		String sDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+financial_Account_id+" and Entry_Date<'"+new java.sql.Date(general_Ledger_Date_From.getTime())+"'";
				ResultSet resultDebitAmount = statementDebitAmount.executeQuery(sDebitAmount);
	    		double debitAmount=0.00;
				while (resultDebitAmount.next())
		    	{			
			    	debitAmount = resultDebitAmount.getDouble(1);
			    }
				String sCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+financial_Account_id+" and Entry_Date<'"+new java.sql.Date(general_Ledger_Date_From.getTime())+"'";
				ResultSet resultCreditAmount = statementCreditAmount.executeQuery(sCreditAmount);
	    		double creditAmount=0.00;
				while (resultCreditAmount.next())
		    	{			
					creditAmount = resultCreditAmount.getDouble(1);
			    }
				double balance = debitAmount - creditAmount;
	    		
	    		String sAccountLedger = "select *, DATE_FORMAT(Entry_Date, '%m/%d/%Y') AS Journal_Date from General_Journal where (Debit_Account="+financial_Account_id+" or Credit_Account="+financial_Account_id+") and Entry_Date>='"+new java.sql.Date(general_Ledger_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(general_Ledger_Date_To.getTime())+"'";		
		    	ResultSet resultAccountLedger = statementAccountLedger.executeQuery(sAccountLedger);
		    	while (resultAccountLedger.next())
				{
					
		    		entry_id.add(resultAccountLedger.getInt("Entry_id"));
		    		journal_Date.add(resultAccountLedger.getString("Journal_Date"));
		    		journal_Details.add(resultAccountLedger.getString("Details"));
		    		if(resultAccountLedger.getInt("Debit_Account")==financial_Account_id)
		    		{
		    			debit_Amount.add((double) Math.round(resultAccountLedger.getDouble("Amount")*100)/100);	
		    			credit_Amount.add(null);
		    			balance = balance+resultAccountLedger.getDouble("Amount");
		    			balance_Amount.add((double) Math.round(balance*100)/100);
		    		}
		    		else
		    		{
		    			credit_Amount.add((double) Math.round(resultAccountLedger.getDouble("Amount")*100)/100);
		    			debit_Amount.add(null);	
		    			balance = balance-resultAccountLedger.getDouble("Amount");
		    			balance_Amount.add((double) Math.round(balance*100)/100);
		    		}  			    		
				}
    		}
	    	///////////////Financial Account Name ///////////////////////////
    		String sAccountName = "Select Financial_Account_Name from Financial_Accounts where Financial_Account_id="+financial_Account_id;
			ResultSet resultAccountName = statementAccountName.executeQuery(sAccountName);
	    	String financial_Account_Name=null;			
	    	while(resultAccountName.next())
	    	{
	    		financial_Account_Name = resultAccountName.getString("Financial_Account_Name");
	    	}
			////////////////////////////////////////////////////////////////////
	    	General_Ledger.setEntry_id(entry_id);
	    	General_Ledger.setJournal_Date(journal_Date);
	    	General_Ledger.setJournal_Details(journal_Details);
	    	General_Ledger.setDebit_Amount(debit_Amount);
	    	General_Ledger.setCredit_Amount(credit_Amount);
	    	General_Ledger.setBalance_Amount(balance_Amount);
	    	General_Ledger.setFinancial_Account_Name(financial_Account_Name);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return General_Ledger;
	}
	public Balance_Sheet getBalanceSheet(int school_id, Date balance_Sheet_Date_From, Date balance_Sheet_Date_To){
		
		Balance_Sheet Balance_Sheet = new Balance_Sheet(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementAssetsFinancialAccounts = connection.createStatement();	
			 Statement statementLiabilitiesFinancialAccounts = connection.createStatement();
			 Statement statementEquityFinancialAccounts = connection.createStatement();
			 Statement statementRevenueFinancialAccounts = connection.createStatement();	
			 Statement statementRevenueDebitAmount = connection.createStatement();
			 Statement statementRevenueCreditAmount = connection.createStatement();
			 Statement statementExpensesFinancialAccounts = connection.createStatement();
			 Statement statementExpensesDebitAmount = connection.createStatement();
			 Statement statementExpensesCreditAmount = connection.createStatement();
			 Statement statementDebitAmount = connection.createStatement();
			 Statement statementCreditAmount = connection.createStatement();){
			ArrayList<Integer> financial_Asset_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Asset_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Asset_Amount = new ArrayList<Double>();	
    		double totalAssetAmount = 0.00;
			ArrayList<Integer> financial_Liabilities_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Liabilities_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Liabilities_Amount = new ArrayList<Double>();	
    		double totalLiabilitiesAmount = 0.00;
			ArrayList<Integer> financial_Equity_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Equity_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Equity_Amount = new ArrayList<Double>();	
			double subTotalEquityAmount = 0.00;
			double totalEquityAmount = 0.00;
			double netIncome =0.00;
			double liabilityPlusEquity=0.00;
			if(balance_Sheet_Date_From!=null && balance_Sheet_Date_To!=null)
			{
				//////////////////////////////////////////Assets//////////////////////////////////////////////////////////////////////////////
	    		String sAssetsFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=1";
		    	ResultSet resultAssetsFinancialAccounts = statementAssetsFinancialAccounts.executeQuery(sAssetsFinancialAccounts);
		    	while (resultAssetsFinancialAccounts.next())
				{
					/////////////Get sum of all debit entries amounts/////////////////////////////////////
		    		String sDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultAssetsFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultDebitAmount = statementDebitAmount.executeQuery(sDebitAmount);
		    		double debitAmount=0.00;
					while (resultDebitAmount.next())
			    	{			
				    	debitAmount = resultDebitAmount.getDouble(1);
				    }
					String sCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultAssetsFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultCreditAmount = statementCreditAmount.executeQuery(sCreditAmount);
		    		double creditAmount=0.00;
					while (resultCreditAmount.next())
			    	{			
						creditAmount = resultCreditAmount.getDouble(1);
				    }
					double netAssetAmount = debitAmount - creditAmount;
					
	    			financial_Asset_Account_id.add(resultAssetsFinancialAccounts.getInt("Financial_Account_id"));
	    			financial_Asset_Account_Name.add(resultAssetsFinancialAccounts.getString("Financial_Account_Name"));				
	    			financial_Asset_Amount.add((double) Math.round(netAssetAmount*100)/100);
					totalAssetAmount = (double) Math.round((totalAssetAmount + netAssetAmount)*100)/100;  			    		
				}
		    	Balance_Sheet.setFinancial_Asset_Account_id(financial_Asset_Account_id);
		    	Balance_Sheet.setFinancial_Asset_Account_Name(financial_Asset_Account_Name);
		    	Balance_Sheet.setFinancial_Asset_Amount(financial_Asset_Amount);
		    	Balance_Sheet.setTotalAssetAmount(totalAssetAmount);
		    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	
		    	////////////////////////////////////////////Liabilities//////////////////////////////////////////////////////////////////
	    		String sLiabilitiesFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=2";
		    	ResultSet resultLiabilitiesFinancialAccounts = statementLiabilitiesFinancialAccounts.executeQuery(sLiabilitiesFinancialAccounts);
		    	while (resultLiabilitiesFinancialAccounts.next())
				{
					/////////////Get sum of all debit entries amounts/////////////////////////////////////
		    		String sDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultLiabilitiesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultDebitAmount = statementDebitAmount.executeQuery(sDebitAmount);
		    		double debitAmount=0.00;
					while (resultDebitAmount.next())
			    	{			
				    	debitAmount = resultDebitAmount.getDouble(1);
				    }
					String sCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultLiabilitiesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultCreditAmount = statementCreditAmount.executeQuery(sCreditAmount);
		    		double creditAmount=0.00;
					while (resultCreditAmount.next())
			    	{			
						creditAmount = resultCreditAmount.getDouble(1);
				    }
					double netLiabilitiesAmount = creditAmount - debitAmount;
					
	    			financial_Liabilities_Account_id.add(resultLiabilitiesFinancialAccounts.getInt("Financial_Account_id"));
	    			financial_Liabilities_Account_Name.add(resultLiabilitiesFinancialAccounts.getString("Financial_Account_Name"));				
	    			financial_Liabilities_Amount.add((double) Math.round(netLiabilitiesAmount*100)/100);
					totalLiabilitiesAmount = (double) Math.round((totalLiabilitiesAmount + netLiabilitiesAmount)*100)/100; 
				}
		    	Balance_Sheet.setFinancial_Liabilities_Account_id(financial_Liabilities_Account_id);
		    	Balance_Sheet.setFinancial_Liabilities_Account_Name(financial_Liabilities_Account_Name);
		    	Balance_Sheet.setFinancial_Liabilities_Amount(financial_Liabilities_Amount);
		    	Balance_Sheet.setTotalLiabilitiesAmount(totalLiabilitiesAmount);
		    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
		    	////////////////////////////////////////////Equity//////////////////////////////////////////////////////////////////
	    		String sEquityFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=3";
		    	ResultSet resultEquityFinancialAccounts = statementEquityFinancialAccounts.executeQuery(sEquityFinancialAccounts);
		    	while (resultEquityFinancialAccounts.next())
				{
					/////////////Get sum of all debit entries amounts/////////////////////////////////////
		    		String sDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultEquityFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultDebitAmount = statementDebitAmount.executeQuery(sDebitAmount);
		    		double debitAmount=0.00;
					while (resultDebitAmount.next())
			    	{			
				    	debitAmount = resultDebitAmount.getDouble(1);
				    }
					String sCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultEquityFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultCreditAmount = statementCreditAmount.executeQuery(sCreditAmount);
		    		double creditAmount=0.00;
					while (resultCreditAmount.next())
			    	{			
						creditAmount = resultCreditAmount.getDouble(1);
				    }
					double netEquityAmount = creditAmount - debitAmount;
					
	    			financial_Equity_Account_id.add(resultEquityFinancialAccounts.getInt("Financial_Account_id"));
	    			financial_Equity_Account_Name.add(resultEquityFinancialAccounts.getString("Financial_Account_Name"));				
	    			financial_Equity_Amount.add((double) Math.round(netEquityAmount*100)/100);
					subTotalEquityAmount = (double) Math.round((subTotalEquityAmount + netEquityAmount)*100)/100; 
				}
				///////////////////////////////Get the Net Income (Revenue - Expenses)///////////////////////////////////////////////
		   		
				String sRevenueFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=4";
		    	ResultSet resultRevenueFinancialAccounts = statementRevenueFinancialAccounts.executeQuery(sRevenueFinancialAccounts);
		    	double netRevenueAmount=0.00;
		    	while(resultRevenueFinancialAccounts.next())
				{
		    		String sRevenueDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultRevenueFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultRevenueDebitAmount = statementRevenueDebitAmount.executeQuery(sRevenueDebitAmount);
		    		double debitRevenueAmount=0.00;
					while (resultRevenueDebitAmount.next())
			    	{			
				    	debitRevenueAmount = resultRevenueDebitAmount.getDouble(1);
				    }
					String sRevenueCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultRevenueFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultRevenueCreditAmount = statementRevenueCreditAmount.executeQuery(sRevenueCreditAmount);
		    		double creditRevenueAmount=0.00;
					while (resultRevenueCreditAmount.next())
			    	{			
						creditRevenueAmount = resultRevenueCreditAmount.getDouble(1);
				    }
					netRevenueAmount = netRevenueAmount + creditRevenueAmount - debitRevenueAmount;
				}
				
				String sExpensesFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=5";
		    	ResultSet resultExpensesFinancialAccounts = statementExpensesFinancialAccounts.executeQuery(sExpensesFinancialAccounts);
		    	double netExpensesAmount=0.00;
		    	while(resultExpensesFinancialAccounts.next())
				{
		    		String sExpensesDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultExpensesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultExpensesDebitAmount = statementExpensesDebitAmount.executeQuery(sExpensesDebitAmount);
		    		double debitExpensesAmount=0.00;
					while (resultExpensesDebitAmount.next())
			    	{			
				    	debitExpensesAmount = resultExpensesDebitAmount.getDouble(1);
				    }
					String sExpensesCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultExpensesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(balance_Sheet_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(balance_Sheet_Date_To.getTime())+"'";
					ResultSet resultExpensesCreditAmount = statementExpensesCreditAmount.executeQuery(sExpensesCreditAmount);
		    		double creditExpensesAmount=0.00;
					while (resultExpensesCreditAmount.next())
			    	{			
						creditExpensesAmount = resultExpensesCreditAmount.getDouble(1);
				    }
					netExpensesAmount = netExpensesAmount + debitExpensesAmount - creditExpensesAmount;
				}
		    	netIncome = (double) Math.round((netRevenueAmount - netExpensesAmount)*100)/100;
		    	totalEquityAmount = (double) Math.round((subTotalEquityAmount + netIncome)*100)/100;
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	
		    	/////////////////////////////////////Liability+Equity//////////////////////////////////////////////////////////////
		    	liabilityPlusEquity  = (double) Math.round((totalLiabilitiesAmount + totalEquityAmount)*100)/100;
		    	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
	    	Balance_Sheet.setFinancial_Equity_Account_id(financial_Equity_Account_id);
	    	Balance_Sheet.setFinancial_Equity_Account_Name(financial_Equity_Account_Name);
	    	Balance_Sheet.setFinancial_Equity_Amount(financial_Equity_Amount);
	    	Balance_Sheet.setSubTotalEquityAmount(subTotalEquityAmount);
	    	Balance_Sheet.setTotalEquityAmount(totalEquityAmount);
	    	Balance_Sheet.setNetIncome(netIncome);
	    	Balance_Sheet.setLiabilityPlusEquity(liabilityPlusEquity);
	    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Balance_Sheet;
	}
	public Income_Statement getIncomeStatement(int school_id, Date income_Statement_Date_From, Date income_Statement_Date_To){
		
		Income_Statement Income_Statement = new Income_Statement(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementRevenueFinancialAccounts = connection.createStatement();	
			 Statement statementRevenueDebitAmount = connection.createStatement();
			 Statement statementRevenueCreditAmount = connection.createStatement();
			 Statement statementExpensesFinancialAccounts = connection.createStatement();
			 Statement statementExpensesDebitAmount = connection.createStatement();
			 Statement statementExpensesCreditAmount = connection.createStatement();){
			
			ArrayList<Integer> financial_Expenses_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Expenses_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Expenses_Amount = new ArrayList<Double>();	
			ArrayList<Integer> financial_Revenue_Account_id = new ArrayList<Integer>();
			ArrayList<String> financial_Revenue_Account_Name = new ArrayList<String>();
			ArrayList<Double> financial_Revenue_Amount = new ArrayList<Double>();	
			double totalExpensesAmount = 0.00;	
			double totalRevenueAmount = 0.00;	
			double netIncome =0.00;
			if(income_Statement_Date_From!=null && income_Statement_Date_To!=null)
			{
		    	///////////////////////////////////Revenue/////////////////////////////////////////////////////////////////////////////////////////
				String sRevenueFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=4";
		    	ResultSet resultRevenueFinancialAccounts = statementRevenueFinancialAccounts.executeQuery(sRevenueFinancialAccounts);
		    	while(resultRevenueFinancialAccounts.next())
				{
		    		String sRevenueDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultRevenueFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(income_Statement_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(income_Statement_Date_To.getTime())+"'";
					ResultSet resultRevenueDebitAmount = statementRevenueDebitAmount.executeQuery(sRevenueDebitAmount);
		    		double debitRevenueAmount=0.00;
					while (resultRevenueDebitAmount.next())
			    	{			
				    	debitRevenueAmount = resultRevenueDebitAmount.getDouble(1);
				    }
					String sRevenueCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultRevenueFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(income_Statement_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(income_Statement_Date_To.getTime())+"'";
					ResultSet resultRevenueCreditAmount = statementRevenueCreditAmount.executeQuery(sRevenueCreditAmount);
		    		double creditRevenueAmount=0.00;
					while (resultRevenueCreditAmount.next())
			    	{			
						creditRevenueAmount = resultRevenueCreditAmount.getDouble(1);
				    }
					double netRevenueAmount = creditRevenueAmount - debitRevenueAmount;
					financial_Revenue_Amount.add((double) Math.round(netRevenueAmount*100)/100);
					totalRevenueAmount = (double) Math.round((totalRevenueAmount + netRevenueAmount)*100)/100;  
					financial_Revenue_Account_id.add(resultRevenueFinancialAccounts.getInt("Financial_Account_id"));
					financial_Revenue_Account_Name.add(resultRevenueFinancialAccounts.getString("Financial_Account_Name"));
				}
				//////////////////////////////////////////Expenses//////////////////////////////////////////////////////////////////////////////
				String sExpensesFinancialAccounts = "select * from Financial_Accounts where School_id="+school_id+" and Financial_Category_id=5";
		    	ResultSet resultExpensesFinancialAccounts = statementExpensesFinancialAccounts.executeQuery(sExpensesFinancialAccounts);
		    	while(resultExpensesFinancialAccounts.next())
				{
		    		String sExpensesDebitAmount = "Select sum(Amount) from General_Journal where Debit_Account="+resultExpensesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(income_Statement_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(income_Statement_Date_To.getTime())+"'";
					ResultSet resultExpensesDebitAmount = statementExpensesDebitAmount.executeQuery(sExpensesDebitAmount);
		    		double debitExpensesAmount=0.00;
					while (resultExpensesDebitAmount.next())
			    	{			
				    	debitExpensesAmount = resultExpensesDebitAmount.getDouble(1);
				    }
					String sExpensesCreditAmount = "Select sum(Amount) from General_Journal where Credit_Account="+resultExpensesFinancialAccounts.getInt("Financial_Account_id")+" and Entry_Date>='"+new java.sql.Date(income_Statement_Date_From.getTime())+"' and Entry_Date<='"+new java.sql.Date(income_Statement_Date_To.getTime())+"'";
					ResultSet resultExpensesCreditAmount = statementExpensesCreditAmount.executeQuery(sExpensesCreditAmount);
		    		double creditExpensesAmount=0.00;
					while (resultExpensesCreditAmount.next())
			    	{			
						creditExpensesAmount = resultExpensesCreditAmount.getDouble(1);
				    }
					double netExpenseAmount = debitExpensesAmount - creditExpensesAmount;
					financial_Expenses_Amount.add((double) Math.round(netExpenseAmount*100)/100);
					totalExpensesAmount = (double) Math.round((totalExpensesAmount + netExpenseAmount)*100)/100;  
					financial_Expenses_Account_id.add(resultExpensesFinancialAccounts.getInt("Financial_Account_id"));
					financial_Expenses_Account_Name.add(resultExpensesFinancialAccounts.getString("Financial_Account_Name"));
				}
		    	Income_Statement.setFinancial_Expenses_Account_id(financial_Expenses_Account_id);
		    	Income_Statement.setFinancial_Expenses_Account_Name(financial_Expenses_Account_Name);
		    	Income_Statement.setFinancial_Expenses_Amount(financial_Expenses_Amount);
		    	Income_Statement.setTotalExpensesAmount(totalExpensesAmount);
		    	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    	
		    	netIncome = (double) Math.round((totalRevenueAmount - totalExpensesAmount)*100)/100;
		    	Income_Statement.setFinancial_Revenue_Account_id(financial_Revenue_Account_id);
		    	Income_Statement.setFinancial_Revenue_Account_Name(financial_Revenue_Account_Name);
		    	Income_Statement.setFinancial_Revenue_Amount(financial_Revenue_Amount);
		    	Income_Statement.setTotalRevenueAmount(totalRevenueAmount);	
		    	Income_Statement.setNetIncome(netIncome);
			}
	    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Income_Statement;
	}
}
