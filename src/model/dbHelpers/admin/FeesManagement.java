package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Fee_Payment_Amount;
import model.beans.admin.Fee_Structure;
import model.beans.admin.Fee_Types;
import model.beans.admin.Fee_Types_List;
import model.beans.admin.Students;
import model.beans.admin.Transport_Payment_Amount;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AccountManagement;

public class FeesManagement {
	public int createFeeType(Fee_Types fee_Types){
		String query = "INSERT INTO Fee_Types (fee_id, Fee_Name, Fee_Description, Payment_Mode, School_id, Debit_Account, Credit_Account) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){

			pstatement.setString(1, fee_Types.getFee_Name());
			pstatement.setString(2, fee_Types.getFee_Description());
			pstatement.setString(3, fee_Types.getPayment_Mode());
			pstatement.setInt(4, fee_Types.getSchool_id());
			pstatement.setInt(5, fee_Types.getDebit_Account_id());
			pstatement.setInt(6, fee_Types.getCredit_Account_id());
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

	public int updateFeeType(Fee_Types fee_Types, int fee_id){
		String query = "update Fee_types set Fee_Name=?, Fee_Description=?, Payment_Mode=?, School_id=?, Debit_Account=?, Credit_Account=? where Fee_id="+fee_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			pstatement.setString(1, fee_Types.getFee_Name());
			pstatement.setString(2, fee_Types.getFee_Description());
			pstatement.setString(3, fee_Types.getPayment_Mode());
			pstatement.setInt(4, fee_Types.getSchool_id());
			pstatement.setInt(5, fee_Types.getDebit_Account_id());
			pstatement.setInt(6, fee_Types.getCredit_Account_id());
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

	public int deleteFeeType(int fee_id, int admin_id){
		String sOwner = "Select Account_Owner from Admins where Admin_id="+admin_id;
		String query = "Delete from Fee_Types where Fee_id="+fee_id;
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

	public Fee_Types_List getFeeTypesList(int school_id ){
		String query = "select * from Fee_Types where school_id="+school_id;
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> fee_id = new ArrayList<Integer>();
	    	ArrayList<String> fee_Name = new ArrayList<String>();
	    	ArrayList<String> fee_Description = new ArrayList<String>();
	    	ArrayList<String> payment_Mode = new ArrayList<String>();
	    	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	    	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
    		while (result.next())
	    	{			
    			fee_id.add(result.getInt("Fee_id"));	    		
    			fee_Name.add(result.getString("Fee_Name"));    		
    			fee_Description.add(result.getString("Fee_Description"));	    		
    			payment_Mode.add(result.getString("Payment_Mode"));
    			debit_Account.add(result.getInt("Debit_Account"));
    			credit_Account.add(result.getInt("Credit_Account"));
		    }
    		Fee_Types_List.setFee_id(fee_id);
    		Fee_Types_List.setFee_Name(fee_Name);
    		Fee_Types_List.setFee_Description(fee_Description);
    		Fee_Types_List.setPayment_Mode(payment_Mode);
    		Fee_Types_List.setDebit_Account(debit_Account);
    		Fee_Types_List.setCredit_Account(credit_Account);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Types_List;
	}

	public int manageFeeStructure(int fee_id, int grade_id, int school_Year_id, double fee_Amount){

		int updateQuery = 0;
		String FeeStructureCreate = "INSERT INTO Fee_Structure (Fee_id, Grade_id, School_Year_id, Fee_Amount) VALUES (?,?, ?, ?)";
		String FeeStructureUpdate = "Update Fee_Structure set Fee_Amount=? where Fee_id="+fee_id+" and Grade_id="+grade_id+" and School_Year_id="+school_Year_id;
		String FeeStructureDelete = "Delete from Fee_Structure where Fee_id="+fee_id+" and Grade_id="+grade_id+" and School_Year_id="+school_Year_id;
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatementCreate = connection.prepareStatement(FeeStructureCreate);
				PreparedStatement pstatementUpdate = connection.prepareStatement(FeeStructureUpdate);
				PreparedStatement pstatementDelete = connection.prepareStatement(FeeStructureDelete);
				Statement statementFeeAmountExist = connection.createStatement();
				Statement statementFeeTypes = connection.createStatement();){

				String sFeeAmountExist = "select * from Fee_Structure where fee_id="+fee_id+" and Grade_id="+grade_id+" and School_Year_id="+school_Year_id;
				ResultSet resultFeeAmountExist = statementFeeAmountExist.executeQuery(sFeeAmountExist);
				if (!resultFeeAmountExist.isBeforeFirst())
				{
					if(fee_Amount!=-1)
					{
						pstatementCreate.setInt(1, fee_id);
						pstatementCreate.setInt(2, grade_id);
						pstatementCreate.setInt(3, school_Year_id);
						pstatementCreate.setDouble(4, fee_Amount);
						updateQuery = pstatementCreate.executeUpdate();	
					}
								
				}
				else
				{
					if(fee_Amount!=-1)
					{
						pstatementUpdate.setDouble(1, fee_Amount);
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
	public Fee_Structure getFeeStructure(int school_id, int school_Year_id){
		String query = "select * from Fee_Structure where fee_id in (select fee_id from fee_types where school_id="+school_id+") and grade_id in (select grade_id from grades where school_id="+school_id+") and School_Year_id="+school_Year_id;
		Fee_Structure Fee_Structure = new Fee_Structure(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> fee_id = new ArrayList<Integer>();
	    	ArrayList<Integer> grade_id = new ArrayList<Integer>();
	    	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	    	ArrayList<Double> fee_Amount = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			fee_id.add(result.getInt("Fee_id"));	    		
    			grade_id.add(result.getInt("Grade_id"));		
    			school_year_id.add(result.getInt("School_Year_id"));
    			fee_Amount.add(result.getDouble("Fee_Amount"));
  	
		    }
    		Fee_Structure.setFee_id(fee_id);
    		Fee_Structure.setGrade_id(grade_id);
    		Fee_Structure.setFee_Amount(fee_Amount);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Structure;
	}
	public Fee_Structure getFeeStructurePerGrade(int school_id, int single_grade_id, int school_Year_id){
		String query = "select * from Fee_Structure where fee_id in (select fee_id from fee_types where school_id="+school_id+") and grade_id="+single_grade_id+" and School_Year_id="+school_Year_id;
		Fee_Structure Fee_Structure = new Fee_Structure(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);			
	    	ArrayList<Integer> fee_id = new ArrayList<Integer>();
	    	ArrayList<Integer> grade_id = new ArrayList<Integer>();
	    	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	    	ArrayList<Double> fee_Amount = new ArrayList<Double>();
    		while (result.next())
	    	{			
    			fee_id.add(result.getInt("Fee_id"));	    		
    			grade_id.add(result.getInt("Grade_id"));		
    			school_year_id.add(result.getInt("School_Year_id"));
    			fee_Amount.add(result.getDouble("Fee_Amount"));
  	
		    }
    		Fee_Structure.setFee_id(fee_id);
    		Fee_Structure.setGrade_id(grade_id);
    		Fee_Structure.setFee_Amount(fee_Amount);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Structure;
	}
	public Students getGradeStudents(int grade_id){
		Students Students = new Students();
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementGradeStudents = connection.createStatement();
				Statement statementStudentSchool = connection.createStatement();){
			String sGradeStudents = "select * from Students where Class_id in (Select Class_id from Classes where Grade_id="+grade_id+")";
			ResultSet resultSchoolStudents = statementGradeStudents.executeQuery(sGradeStudents);
			ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> first_Name = new ArrayList<String>();
			ArrayList<String> last_Name = new ArrayList<String>();
			ArrayList<String> username = new ArrayList<String>();
	    	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	    	ArrayList<Integer> School_id = new ArrayList<Integer>();
	    	ArrayList<String> FileName = new ArrayList<String>();
			
			while(resultSchoolStudents.next())
			{
				student_id.add(resultSchoolStudents.getInt("Student_id"));
				first_Name.add(resultSchoolStudents.getString("First_Name"));
				last_Name.add(resultSchoolStudents.getString("Last_Name"));
				username.add(resultSchoolStudents.getString("Username"));
		    	////////////////////Get Student Account_id and School_id//////////////////////////////////////
				String sStudentSchool = "Select school_id from Grades where Grade_id="+grade_id;
				ResultSet resultStudentSchool = statementStudentSchool.executeQuery(sStudentSchool );
				int school_id=0;
				while(resultStudentSchool.next())
				{
					school_id = resultStudentSchool.getInt("School_id");
				}
	    		School_id.add(school_id);	    		
	    		/////////////////////////////////////////////////////////////////////////////
	    		AccountManagement accountManagement = new AccountManagement();
	    		int account_id = accountManagement.getSchoolAccount(school_id);
	    		Account_id.add(account_id);
		    	///////////////////Get Student File Name (Photo)//////////////////////////////////////////////
	    		FileName.add(resultSchoolStudents.getString("Fake_File_Name"));
			}
			Students.setStudent_id(student_id);
			Students.setFirst_Name(first_Name);
			Students.setLast_Name(last_Name);
			Students.setUsername(username);
	    	Students.setAccount_id(Account_id);
	    	Students.setSchool_id(School_id);
	    	Students.setFileName(FileName);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	public int manageFeePayments(HttpServletRequest request, int school_id){

		int updateQuery = 0;
		int receipt_id = -1;
		String FeeReceiptCreate = "INSERT INTO Fee_Receipt (Receipt_id, Student_id, School_Year_id, Receipt_Date) VALUES (Null, ?, ?, ?)";
		String FeePaymentCreate = "INSERT INTO Fee_Payments (Receipt_id, Fee_id, Payment_Amount, Payment_Discount, Payment_Fine) VALUES (?, ?, ?, ?, ?)";
		String sCreateFeeJournal = "INSERT INTO General_Journal (Entry_id, Entry_Date, Debit_Account, Credit_Account, Amount, Details) VALUES (Null, ?, ?, ?, ?, ?)";
		String TransportPaymentCreate = "INSERT INTO Transport_Payments (Receipt_id, Route_id, Payment_Amount, Payment_Discount, Payment_Fine) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatementCreateReceipt = connection.prepareStatement(FeeReceiptCreate, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstatementCreatePayment = connection.prepareStatement(FeePaymentCreate);
				PreparedStatement pstatementCreateFeeJournal = connection.prepareStatement(sCreateFeeJournal);
				Statement statementDebitCreditAccounts = connection.createStatement();
				Statement statementStudentName = connection.createStatement();
				Statement statementYearName = connection.createStatement();
				PreparedStatement pstatementCreateTransportPayment = connection.prepareStatement(TransportPaymentCreate);){
			
			int student_id = Integer.parseInt(request.getParameter("Student_id"));
			int school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
			//////////////////Student Name//////////////
			String sStudentName = "Select First_Name, Last_Name from students where student_id="+student_id;
			ResultSet resultStudentName = statementStudentName.executeQuery(sStudentName);
			String student_Name=null;
			while(resultStudentName.next())
			{
				student_Name=resultStudentName.getString("First_Name")+" "+resultStudentName.getString("Last_Name");
			}
			//////////////////Academic Year Name///////
			String sYearName = "Select Year_Name from school_year where School_Year_id="+school_Year_id;
			ResultSet resultYearName = statementYearName.executeQuery(sYearName);
			String year_Name=null;
			while(resultYearName.next())
			{
				year_Name=resultYearName.getString("Year_Name");
			}
			///////////////////////////////////////////
			Date today = new Date();
			connection.setAutoCommit(false);
			pstatementCreateReceipt.setInt(1, student_id);
			pstatementCreateReceipt.setInt(2, school_Year_id);
			pstatementCreateReceipt.setDate(3, new java.sql.Date(today.getTime()));
			updateQuery = pstatementCreateReceipt.executeUpdate();
			int updateQuery1=0;
			int updateQuery2=0;
			if(updateQuery!=0)
			{
				ResultSet resultReceipt_id = pstatementCreateReceipt.getGeneratedKeys();
				if(resultReceipt_id.next()) 
				{
				       receipt_id = resultReceipt_id.getInt(1);
				       resultReceipt_id.close();
				}
				Fee_Types_List Fee_Types_List = new Fee_Types_List();
				Fee_Types_List = getFeeTypesList(school_id);
				for (int fee_id : Fee_Types_List.getFee_id())
				{
					double payment_Amount = -1;
					double payment_Discount = -1;
					double payment_Fine = -1;
					if((request.getParameter("Payment_Amount"+fee_id)!=null&&!request.getParameter("Payment_Amount"+fee_id).equals(""))
							||(request.getParameter("Payment_Discount"+fee_id)!=null&&!request.getParameter("Payment_Discount"+fee_id).equals(""))
							||(request.getParameter("Payment_Fine"+fee_id)!=null&&!request.getParameter("Payment_Fine"+fee_id).equals("")))
					{
						if(request.getParameter("Payment_Amount"+fee_id)!=null&&!request.getParameter("Payment_Amount"+fee_id).equals(""))
						{
							payment_Amount = Double.parseDouble(request.getParameter("Payment_Amount"+fee_id));
						}
						if(request.getParameter("Payment_Discount"+fee_id)!=null&&!request.getParameter("Payment_Discount"+fee_id).equals(""))
						{
							payment_Discount = Double.parseDouble(request.getParameter("Payment_Discount"+fee_id));
						}								
						if(request.getParameter("Payment_Fine"+fee_id)!=null&&!request.getParameter("Payment_Fine"+fee_id).equals(""))
						{
							payment_Fine = Double.parseDouble(request.getParameter("Payment_Fine"+fee_id));
						}	
						if(payment_Amount==-1)
						{
							payment_Amount = 0;
						}
						if(payment_Discount==-1)
						{
							payment_Discount=0;
						}
						if(payment_Fine==-1)
						{
							payment_Fine=0;
						}
						pstatementCreatePayment.setInt(1, receipt_id);
						pstatementCreatePayment.setInt(2, fee_id);
						pstatementCreatePayment.setDouble(3, payment_Amount);
						pstatementCreatePayment.setDouble(4, payment_Discount);
						pstatementCreatePayment.setDouble(5, payment_Fine);
						updateQuery1 = pstatementCreatePayment.executeUpdate();
						///////////////////////////Create Journal Entry///////////////////////////////////////////////////
						if(updateQuery1!=0)
						{
							
							String sDebitCreditAccounts = "select * from fee_types where fee_id="+fee_id;
							ResultSet resultDebitCreditAccounts = statementDebitCreditAccounts.executeQuery(sDebitCreditAccounts);
							while(resultDebitCreditAccounts.next())
							{
								pstatementCreateFeeJournal.setDate(1, new java.sql.Date( new Date().getTime()));
								pstatementCreateFeeJournal.setInt(2, resultDebitCreditAccounts.getInt("Debit_Account"));
								pstatementCreateFeeJournal.setInt(3, resultDebitCreditAccounts.getInt("Credit_Account"));
								pstatementCreateFeeJournal.setDouble(4, payment_Amount);
								pstatementCreateFeeJournal.setString(5, "Receipt Number ("+receipt_id+") Student (ID: "+student_id+" - Name: "+student_Name+") Payment for Fee type ("+resultDebitCreditAccounts.getString("Fee_Name")+") in Academic Year ("+year_Name+")");
								updateQuery1 = pstatementCreateFeeJournal.executeUpdate();
							}
						}
						
						///////////////////////////////////////////////////////////////////////////////////////////////////
					}				
				}
				////////////////////////////Deal with the Transport Payments /////////////////////////////////////////////////
				if((request.getParameter("Transport_Payment_Amount")!=null&&!request.getParameter("Transport_Payment_Amount").equals(""))
						||(request.getParameter("Transport_Payment_Discount")!=null&&!request.getParameter("Transport_Payment_Discount").equals(""))
						||(request.getParameter("Transport_Payment_Fine")!=null&&!request.getParameter("Transport_Payment_Fine").equals("")))
				{
					double paymentTransport_Amount = -1;
					double paymentTransport_Discount = -1;
					double paymentTransport_Fine = -1;
					if(request.getParameter("Transport_Payment_Amount")!=null&&!request.getParameter("Transport_Payment_Amount").equals(""))
					{
						paymentTransport_Amount = Double.parseDouble(request.getParameter("Transport_Payment_Amount"));
					}
					if(request.getParameter("Transport_Payment_Discount")!=null&&!request.getParameter("Transport_Payment_Discount").equals(""))
					{
						paymentTransport_Discount = Double.parseDouble(request.getParameter("Transport_Payment_Discount"));
					}								
					if(request.getParameter("Transport_Payment_Fine")!=null&&!request.getParameter("Transport_Payment_Fine").equals(""))
					{
						paymentTransport_Fine = Double.parseDouble(request.getParameter("Transport_Payment_Fine"));
					}	
					if(paymentTransport_Amount==-1)
					{
						paymentTransport_Amount = 0;
					}
					if(paymentTransport_Discount==-1)
					{
						paymentTransport_Discount=0;
					}
					if(paymentTransport_Fine==-1)
					{
						paymentTransport_Fine=0;
					}
					int route_id=0;
					if(request.getParameter("Route_id")!=null && !request.getParameter("Route_id").equals(""))
					{
						route_id = Integer.parseInt(request.getParameter("Route_id"));
					}
					pstatementCreateTransportPayment.setInt(1, receipt_id);
					pstatementCreateTransportPayment.setInt(2, route_id);
					pstatementCreateTransportPayment.setDouble(3, paymentTransport_Amount);
					pstatementCreateTransportPayment.setDouble(4, paymentTransport_Discount);
					pstatementCreateTransportPayment.setDouble(5, paymentTransport_Fine);
					updateQuery2 = pstatementCreateTransportPayment.executeUpdate();
					///////////////////////////Create Journal Entry///////////////////////////////////////////////////
					if(updateQuery2!=0)
					{
						
						String sDebitCreditAccounts = "select * from vehicle_Route where route_id="+route_id;
						ResultSet resultDebitCreditAccounts = statementDebitCreditAccounts.executeQuery(sDebitCreditAccounts);
						while(resultDebitCreditAccounts.next())
						{
							pstatementCreateFeeJournal.setDate(1, new java.sql.Date( new Date().getTime()));
							pstatementCreateFeeJournal.setInt(2, resultDebitCreditAccounts.getInt("Debit_Account"));
							pstatementCreateFeeJournal.setInt(3, resultDebitCreditAccounts.getInt("Credit_Account"));
							pstatementCreateFeeJournal.setDouble(4, paymentTransport_Amount);
							pstatementCreateFeeJournal.setString(5, "Receipt Number ("+receipt_id+") Student (ID: "+student_id+" - Name: "+student_Name+") Payment for Transport Fees - Route Name ("+resultDebitCreditAccounts.getString("Route_Name")+") in Academic Year ("+year_Name+")");
							updateQuery1 = pstatementCreateFeeJournal.executeUpdate();
						}
					}
					
					///////////////////////////////////////////////////////////////////////////////////////////////////
					
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			if(updateQuery1!=0 || updateQuery2!=0)
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
			receipt_id=-1;
		}
		return receipt_id;
	}

	public Fee_Payment_Amount getFeePaymentAmount(int school_id, int grade_id, int student_id, int school_Year_id){
		
		Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementPaymentAmount = connection.createStatement();
			 Statement statementFeeIDs = connection.createStatement();
			 Statement statementFeeAmount = connection.createStatement();
			 Statement statementYearPaidOnce = connection.createStatement();
			 Statement statementTransportFees = connection.createStatement();
		     Statement statementTransportFeesStudentRoute = connection.createStatement();
			 Statement statementMostRecentRoute = connection.createStatement();
			 Statement statementPaymentTransportAmount = connection.createStatement();){
			
			ArrayList<Integer> fee_id = new ArrayList<Integer>();
	    	ArrayList<Double> payment_Amount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Discount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Fine = new ArrayList<Double>();
	    	ArrayList<Double> due_Amount = new ArrayList<Double>();
	    	ArrayList<Boolean> allow_Entry = new ArrayList<Boolean>();
			String sFeeIDs = "select fee_id, payment_mode from fee_types where school_id="+school_id;
	    	ResultSet resultFeeIDs = statementFeeIDs.executeQuery(sFeeIDs);
	    	while (resultFeeIDs.next())
			{
	    		int school_Year_id_Once = 0;
	    		if(resultFeeIDs.getString("Payment_Mode").equals("Once"))
	    		{
	    			String sYearPaidOnce = "select school_year_id from fee_receipt where student_id="+student_id+" and receipt_id in (select receipt_id from fee_Payments where fee_id="+resultFeeIDs.getInt("Fee_id")+")";
	    			ResultSet resultYearPaidOnce = statementYearPaidOnce.executeQuery(sYearPaidOnce);
	    			while(resultYearPaidOnce.next())
	    			{
	    				school_Year_id_Once = resultYearPaidOnce.getInt("School_Year_id");
	    			}
	    		}
	    		String sFeeAmount = null;
	    		if(school_Year_id_Once==0)
				{
		    		sFeeAmount = "select fee_amount from Fee_Structure where fee_id ="+resultFeeIDs.getInt("Fee_id")+" and grade_id = "+grade_id+" and School_Year_id="+school_Year_id;					
				}
				else
				{
		    		sFeeAmount = "select fee_amount from Fee_Structure where fee_id ="+resultFeeIDs.getInt("Fee_id")+" and grade_id = "+grade_id+" and School_Year_id="+school_Year_id_Once;

				}
	    		ResultSet resultFeeAmount = statementFeeAmount.executeQuery(sFeeAmount);
	    		double feeAmount=0.00;
	    		while(resultFeeAmount.next())
	    		{
		    		feeAmount = resultFeeAmount.getDouble("fee_amount");
		    		
	    		}
				String sPaymentAmount = "Select SUM(Payment_Amount), SUM(Payment_Discount), SUM(Payment_Fine) from fee_payments where Fee_id="+resultFeeIDs.getInt("Fee_id")+" and receipt_id in (select receipt_id from fee_receipt where student_id="+student_id+" and school_year_id="+school_Year_id+")";
				ResultSet resultPaymentAmount = statementPaymentAmount.executeQuery(sPaymentAmount);
	    		double paidAmount = 0.00;
	    		double paymentDiscount = 0.00;
	    		double paymentFine = 0.00;
				boolean allowEntry = false;
	    		while (resultPaymentAmount.next())
		    	{			
	    			paidAmount = resultPaymentAmount.getDouble(1);
	    			paymentDiscount = resultPaymentAmount.getDouble(2);
	    			paymentFine = resultPaymentAmount.getDouble(3);
			    }
	    		double due = 0.00;
	    		if(school_Year_id_Once==0 || school_Year_id_Once==school_Year_id)
		    	{
		    		due = feeAmount - paidAmount - paymentDiscount + paymentFine;
		    		allowEntry=true;
		    	}	    		
		    	fee_id.add(resultFeeIDs.getInt("Fee_id"));	    		
    			payment_Amount.add(paidAmount);
    			payment_Discount.add(paymentDiscount);
    			payment_Fine.add(paymentFine);
    			due_Amount.add(due);
    			allow_Entry.add(allowEntry);
			}
    		//////////////////////////////////////Deal with Transport Payments /////////////////////////////////////////////////////////////

			//String sPaymentTransportAmount = "Select SUM(Payment_Amount), SUM(Payment_Discount), SUM(Payment_Fine) from Transport_payments where Route_id="+route_id+" and receipt_id in (select receipt_id from fee_receipt where student_id="+student_id+" and school_year_id="+school_Year_id+")";
	    	String sPaymentTransportAmount = "Select SUM(Payment_Amount), SUM(Payment_Discount), SUM(Payment_Fine), route_id from Transport_payments where Route_id in (select route_id from vehicle_route where vehicle_id in (select vehicle_id from vehicle where school_id=(select school_id from grades where grade_id="+grade_id+"))) and receipt_id in (select receipt_id from fee_receipt where student_id="+student_id+" and school_year_id="+school_Year_id+")";
			ResultSet resultPaymentTransportAmount = statementPaymentTransportAmount.executeQuery(sPaymentTransportAmount);
    		double paidTransportAmount = -1;
    		double paymentTransportDiscount = -1;
    		double paymentTransportFine = -1;
    		double transportFees = 0.00;
    		while (resultPaymentTransportAmount.next())
	    	{			
    			if(resultPaymentTransportAmount.getString("Route_id")!=null)
    			{
    		    	String sTransportFeesStudentRoute = "select * from Transport_Fees where route_id =(select route_id from students where student_id="+student_id+") and School_Year_id="+school_Year_id;
    		    	ResultSet resultTransportFeesStudentRoute = statementTransportFeesStudentRoute.executeQuery(sTransportFeesStudentRoute);
    		    	if(resultTransportFeesStudentRoute.isBeforeFirst())
    		    	{
        		    	while(resultTransportFeesStudentRoute.next())
        		    	{
        		    		transportFees = resultTransportFeesStudentRoute.getInt("Transport_Fee_Amount");
        		    	}
    		    	}
    		    	else
    		    	{
        				String sMostRecentRoute = "Select route_id from Transport_payments where receipt_id = (select max(receipt_id) from fee_receipt where student_id="+student_id+" and school_year_id="+school_Year_id+")";
        		    	ResultSet resultMostRecentRoute = statementMostRecentRoute.executeQuery(sMostRecentRoute);
        		    	while(resultMostRecentRoute.next())
        		    	{
            		    	String sTransportFees = "select * from Transport_Fees where route_id ="+resultMostRecentRoute.getInt(1)+" and School_Year_id="+school_Year_id;
            		    	ResultSet resultTransportFees = statementTransportFees.executeQuery(sTransportFees);
            		    	while(resultTransportFees.next())
            		    	{
            		    		transportFees = resultTransportFees.getDouble("Transport_Fee_Amount");
            		    		Fee_Payment_Amount.setMostRecentRoute_id(resultMostRecentRoute.getInt(1));
            		    	}
        		    	}
    		    	}
    				paidTransportAmount = resultPaymentTransportAmount.getDouble(1);
        			paymentTransportDiscount = resultPaymentTransportAmount.getDouble(2);
        			paymentTransportFine = resultPaymentTransportAmount.getDouble(3);
    			}
		    }
    		double dueTransport = 0.00;
    		if(paidTransportAmount!=-1 && paymentTransportDiscount!=-1 && paymentTransportFine!=-1)
    		{
    			dueTransport = transportFees - paidTransportAmount - paymentTransportDiscount + paymentTransportFine;
    		}    		
	    	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
	    	Fee_Payment_Amount.setFee_id(fee_id);
    		Fee_Payment_Amount.setPayment_Amount(payment_Amount);
    		Fee_Payment_Amount.setPayment_Discount(payment_Discount);
    		Fee_Payment_Amount.setPayment_Fine(payment_Fine);
    		Fee_Payment_Amount.setDue_Amount(due_Amount);
    		Fee_Payment_Amount.setAllow_Entry(allow_Entry);
    		//////////////////////////////Transport ////////////////////////////////////////
    		Fee_Payment_Amount.setTransportFees(transportFees);
    		Fee_Payment_Amount.setPaidTransportAmount(paidTransportAmount);
    		Fee_Payment_Amount.setPaymentTransportDiscount(paymentTransportDiscount);
    		Fee_Payment_Amount.setPaymentTransportFine(paymentTransportFine);
    		Fee_Payment_Amount.setDueTransport(dueTransport);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Payment_Amount;
	}
	
	public Fee_Payment_Amount getCollectionsByFee(int school_id, int grade_id, int school_Year_id, int Fee_id){
		
		Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementPaymentAmount = connection.createStatement();
			 Statement statementFeeIDs = connection.createStatement();
			 Statement statementFeeAmount = connection.createStatement();
			 Statement statementYearPaidOnce = connection.createStatement();
				Statement statementReceiptDetails = connection.createStatement();
				Statement statementFeeName = connection.createStatement();
				Statement statementStudentName = connection.createStatement();){
			ArrayList<Integer> fee_id = new ArrayList<Integer>();
			ArrayList<String> fee_Name = new ArrayList<String>();
			ArrayList<Double> fee_Amount = new ArrayList<Double>();
			ArrayList<Integer> receipt_id = new ArrayList<Integer>();
			ArrayList<Date> receipt_Date = new ArrayList<Date>();
	    	ArrayList<Double> payment_Amount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Discount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Fine = new ArrayList<Double>();
	    	ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> student_Name = new ArrayList<String>();
			String sFeeIDs = null;
    		double totalPayment = 0.00;
			if(Fee_id!=0)
			{
				sFeeIDs = "select * from fee_types where fee_id="+Fee_id;
			}
			else
			{
				sFeeIDs = "select * from fee_types where school_id="+school_id;
			}
	    	ResultSet resultFeeIDs = statementFeeIDs.executeQuery(sFeeIDs);
	    	while (resultFeeIDs.next())
			{
				String sPaymentAmount = "Select Payment_Amount, Payment_Discount, Payment_Fine, Receipt_id, fee_id from fee_payments where Fee_id="+resultFeeIDs.getInt("Fee_id")+" and receipt_id in (select receipt_id from fee_receipt where student_id in (select student_id from students where class_id in (select class_id from classes where grade_id="+grade_id+")) and school_year_id="+school_Year_id+")";
				ResultSet resultPaymentAmount = statementPaymentAmount.executeQuery(sPaymentAmount);
	    		while (resultPaymentAmount.next())
		    	{			
			    	String sReceiptDetails = "Select * from Fee_Receipt where receipt_id="+resultPaymentAmount.getInt("receipt_id");
			    	ResultSet resultReceiptDetails = statementReceiptDetails.executeQuery(sReceiptDetails);
			    	Date receiptDate = null;
			    	int studentId=0;
			    	String studentName=null;
			    	while(resultReceiptDetails.next())
			    	{
			    		receiptDate = resultReceiptDetails.getDate("Receipt_Date");
			    		studentId = resultReceiptDetails.getInt("Student_id");
			    		String sStudentName = "Select First_Name, Last_Name from Students where student_id="+studentId;
			    		ResultSet resultStudentName = statementStudentName.executeQuery(sStudentName);
			    		while(resultStudentName.next())
			    		{
			    			String studentfName = resultStudentName.getString("First_Name");
			    			String studentlName = resultStudentName.getString("Last_Name");
			    			studentName = studentfName+" " + studentlName;
			    		}
			    	}
			    	String sFeeName = "Select * from Fee_Types where fee_id="+resultPaymentAmount.getInt("fee_id");
			    	ResultSet resultFeeName = statementFeeName.executeQuery(sFeeName);
			    	String feeName = null;
			    	while(resultFeeName.next())
			    	{
			    		feeName = resultFeeName.getString("Fee_Name");
			    	}
		    		String sFeeAmount = null;
			    	sFeeAmount = "select fee_amount from Fee_Structure where fee_id ="+resultPaymentAmount.getInt("fee_id")+" and grade_id = "+grade_id+" and School_Year_id="+school_Year_id;					
		    		ResultSet resultFeeAmount = statementFeeAmount.executeQuery(sFeeAmount);
		    		double feeAmount=0.00;

		    		while(resultFeeAmount.next())
		    		{
			    		feeAmount = resultFeeAmount.getDouble("fee_amount");
			    		
		    		}
	    			fee_id.add(resultFeeIDs.getInt("Fee_id"));	 
			    	receipt_id.add(resultPaymentAmount.getInt("receipt_id"));
			    	receipt_Date.add(receiptDate);
	    			payment_Amount.add(resultPaymentAmount.getDouble("Payment_Amount"));
	    			payment_Discount.add(resultPaymentAmount.getDouble("Payment_Discount"));
	    			payment_Fine.add(resultPaymentAmount.getDouble("Payment_Fine"));
		    		fee_Name.add(feeName);
		    		fee_Amount.add(feeAmount);
		    		student_id.add(studentId);
		    		student_Name.add(studentName);
		    		totalPayment = totalPayment + resultPaymentAmount.getDouble("Payment_Amount");
			    }

			}

    		Fee_Payment_Amount.setFee_id(fee_id);
    		Fee_Payment_Amount.setFee_Name(fee_Name);
    		Fee_Payment_Amount.setFee_Amount(fee_Amount);
    		Fee_Payment_Amount.setPayment_Amount(payment_Amount);
    		Fee_Payment_Amount.setPayment_Discount(payment_Discount);
    		Fee_Payment_Amount.setPayment_Fine(payment_Fine);
    		Fee_Payment_Amount.setReceipt_id(receipt_id);
    		Fee_Payment_Amount.setReceipt_Date(receipt_Date);
    		Fee_Payment_Amount.setStudent_id(student_id);
    		Fee_Payment_Amount.setStudent_Name(student_Name);
    		Fee_Payment_Amount.setTotalPayment(totalPayment);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Payment_Amount;
	}

	public Transport_Payment_Amount getTransportCollectionsByFee(int school_id, int grade_id, int school_Year_id, int Route_id){
		
		Transport_Payment_Amount Transport_Payment_Amount = new Transport_Payment_Amount(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementPaymentAmount = connection.createStatement();
			 Statement statementRouteIDs = connection.createStatement();
			 Statement statementTransportFeeAmount = connection.createStatement();
			 Statement statementReceiptDetails = connection.createStatement();
			 Statement statementStudentName = connection.createStatement();){
			ArrayList<Integer> route_id = new ArrayList<Integer>();
			ArrayList<Double> transport_Fees = new ArrayList<Double>();
			ArrayList<Integer> receipt_id = new ArrayList<Integer>();
			ArrayList<Date> receipt_Date = new ArrayList<Date>();
	    	ArrayList<Double> payment_Transport_Amount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Transport_Discount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Transport_Fine = new ArrayList<Double>();
	    	ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> student_Name = new ArrayList<String>();
			String sRouteIDs = null;
    		double totalPayment = 0.00;

				sRouteIDs = "select * from vehicle_route where vehicle_id in (select vehicle_id from vehicle where school_id=(select school_id from grades where grade_id="+grade_id+"))";

	    	ResultSet resultRouteIDs = statementRouteIDs.executeQuery(sRouteIDs);
	    	while (resultRouteIDs.next())
			{
				String sPaymentAmount = "Select Payment_Amount, Payment_Discount, Payment_Fine, Receipt_id, Route_id from transport_payments where Route_id="+resultRouteIDs.getInt("Route_id")+" and receipt_id in (select receipt_id from fee_receipt where student_id in (select student_id from students where class_id in (select class_id from classes where grade_id="+grade_id+")) and school_year_id="+school_Year_id+")";
				ResultSet resultPaymentAmount = statementPaymentAmount.executeQuery(sPaymentAmount);
	    		while (resultPaymentAmount.next())
		    	{			
			    	String sReceiptDetails = "Select * from Fee_Receipt where receipt_id="+resultPaymentAmount.getInt("receipt_id");
			    	ResultSet resultReceiptDetails = statementReceiptDetails.executeQuery(sReceiptDetails);
			    	Date receiptDate = null;
			    	int studentId=0;
			    	String studentName=null;
			    	while(resultReceiptDetails.next())
			    	{
			    		receiptDate = resultReceiptDetails.getDate("Receipt_Date");
			    		studentId = resultReceiptDetails.getInt("Student_id");
			    		String sStudentName = "Select First_Name, Last_Name from Students where student_id="+studentId;
			    		ResultSet resultStudentName = statementStudentName.executeQuery(sStudentName);
			    		while(resultStudentName.next())
			    		{
			    			String studentfName = resultStudentName.getString("First_Name");
			    			String studentlName = resultStudentName.getString("Last_Name");
			    			studentName = studentfName+" " + studentlName;
			    		}
			    	}
		    		 String sTransportFeeAmount = "select Transport_Fee_amount from Transport_Fees where route_id ="+resultPaymentAmount.getInt("Route_id")+" and School_Year_id="+school_Year_id;					
		    		ResultSet resultTransportFeeAmount = statementTransportFeeAmount.executeQuery(sTransportFeeAmount);
		    		double transportFeeAmount=0.00;

		    		while(resultTransportFeeAmount.next())
		    		{
		    			transportFeeAmount = resultTransportFeeAmount.getDouble("Transport_Fee_amount");
			    		
		    		}
	    			route_id.add(resultRouteIDs.getInt("Route_id"));	 
			    	receipt_id.add(resultPaymentAmount.getInt("receipt_id"));
			    	receipt_Date.add(receiptDate);
			    	payment_Transport_Amount.add(resultPaymentAmount.getDouble("Payment_Amount"));
			    	payment_Transport_Discount.add(resultPaymentAmount.getDouble("Payment_Discount"));
			    	payment_Transport_Fine.add(resultPaymentAmount.getDouble("Payment_Fine"));
			    	transport_Fees.add(transportFeeAmount);
		    		student_id.add(studentId);
		    		student_Name.add(studentName);
		    		totalPayment = totalPayment + resultPaymentAmount.getDouble("Payment_Amount");
			    }

			}
	    	Transport_Payment_Amount.setRoute_id(route_id);
	    	Transport_Payment_Amount.setTransport_Fees(transport_Fees);
	    	Transport_Payment_Amount.setPayment_Transport_Amount(payment_Transport_Amount);
	    	Transport_Payment_Amount.setPayment_Transport_Discount(payment_Transport_Discount);
	    	Transport_Payment_Amount.setPayment_Transport_Fine(payment_Transport_Fine);
	    	Transport_Payment_Amount.setReceipt_id(receipt_id);
	    	Transport_Payment_Amount.setReceipt_Date(receipt_Date);
	    	Transport_Payment_Amount.setStudent_id(student_id);
	    	Transport_Payment_Amount.setStudent_Name(student_Name);
	    	Transport_Payment_Amount.setTotalPayment(totalPayment);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Transport_Payment_Amount;
	}	
	
	public Fee_Payment_Amount getCollectionsByReceipt(int grade_id, int school_Year_id, int Receipt_id){
		
		Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementFeePaymentCount = connection.createStatement();
			 Statement statementPaymentAmount = connection.createStatement();
			 Statement statementReceiptIDs = connection.createStatement();
			 Statement statementFeeAmount = connection.createStatement();
			 Statement statementYearPaidOnce = connection.createStatement();
			 Statement statementReceiptDetails = connection.createStatement();
			 Statement statementFeeName = connection.createStatement();
			 Statement statementStudentName = connection.createStatement();){
			ArrayList<String> fee_Name = new ArrayList<String>();
			ArrayList<Double> fee_Amount = new ArrayList<Double>();
			ArrayList<Integer> receipt_id = new ArrayList<Integer>();
			ArrayList<Date> receipt_Date = new ArrayList<Date>();
	    	ArrayList<Double> payment_Amount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Discount = new ArrayList<Double>();
	    	ArrayList<Double> payment_Fine = new ArrayList<Double>();
	    	ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> student_Name = new ArrayList<String>();
			String sReceiptIDs = null;
    		double totalPayment = 0.00;
			if(Receipt_id!=0)
			{
				sReceiptIDs = "select * from Fee_Receipt where Receipt_id="+Receipt_id;
			}
			else
			{
				sReceiptIDs = "select * from Fee_Receipt where School_Year_id="+school_Year_id+" and Student_id in (select student_id from students where class_id in (select class_id from classes where grade_id="+grade_id+")) order by receipt_id";
			}
	    	ResultSet resultReceiptIDs = statementReceiptIDs.executeQuery(sReceiptIDs);
	    	while (resultReceiptIDs.next())
			{
				String sFeePaymentCount = "Select Count(*) from Fee_payments where Receipt_id="+resultReceiptIDs.getInt("Receipt_id");
				ResultSet resultFeePaymentCount = statementFeePaymentCount.executeQuery(sFeePaymentCount);
				int feePaymentCount = 0;
				while (resultFeePaymentCount.next())
				{
					feePaymentCount = resultFeePaymentCount.getInt(1);
				}				
	    		String sPaymentAmount = "Select Payment_Amount, Payment_Discount, Payment_Fine, Receipt_id, Fee_id from Fee_payments where Fee_payments.Receipt_id="+resultReceiptIDs.getInt("Receipt_id")
	    		+" UNION Select Payment_Amount, Payment_Discount, Payment_Fine, Receipt_id, Route_id from Transport_payments where Transport_payments.Receipt_id="+resultReceiptIDs.getInt("Receipt_id");	    		
	    		ResultSet resultPaymentAmount = statementPaymentAmount.executeQuery(sPaymentAmount);				
	    		int x = 0;
	    		while (resultPaymentAmount.next())
		    	{			
					String sReceiptDetails = "Select * from Fee_Receipt where receipt_id="+resultPaymentAmount.getInt("receipt_id");
			    	ResultSet resultReceiptDetails = statementReceiptDetails.executeQuery(sReceiptDetails);
			    	Date receiptDate = null;
			    	int studentId=0;
			    	String studentName=null;
			    	while(resultReceiptDetails.next())
			    	{
			    		receiptDate = resultReceiptDetails.getDate("Receipt_Date");
			    		studentId = resultReceiptDetails.getInt("Student_id");
			    		String sStudentName = "Select First_Name, Last_Name from Students where student_id="+studentId;
			    		ResultSet resultStudentName = statementStudentName.executeQuery(sStudentName);
			    		while(resultStudentName.next())
			    		{
			    			String studentfName = resultStudentName.getString("First_Name");
			    			String studentlName = resultStudentName.getString("Last_Name");
			    			studentName = studentfName+" " + studentlName;
			    		}
			    	}
			    	String sFeeName = null;
			    	if(x < feePaymentCount)
			    	{
			    		sFeeName = "Select * from Fee_Types where fee_id="+resultPaymentAmount.getInt("Fee_id");
			    	}
			    	else
			    	{
			    		sFeeName = "Select * from Vehicle_Route where Route_id="+resultPaymentAmount.getInt("Fee_id");
			    	}			    	
			    	ResultSet resultFeeName = statementFeeName.executeQuery(sFeeName);
			    	String feeName = null;
			    	while(resultFeeName.next())
			    	{
			    		if(x < feePaymentCount)
			    		{
			    			feeName = resultFeeName.getString("Fee_Name");
			    		}
			    		else
			    		{
			    			feeName = "Transport Fees - "+resultFeeName.getString("Route_Name");
			    		}			    		
			    	}
		    		String sFeeAmount = null;
			    	if(x < feePaymentCount)
			    	{
			    		sFeeAmount = "select fee_amount from Fee_Structure where fee_id ="+resultPaymentAmount.getInt("Fee_id")+" and grade_id = "+grade_id+" and School_Year_id="+school_Year_id;
			    	}
			    	else
			    	{
			    		sFeeAmount = "select Transport_Fee_amount from Transport_Fees where Route_id ="+resultPaymentAmount.getInt("Fee_id")+" and School_Year_id="+school_Year_id;
			    	}		    							
		    		ResultSet resultFeeAmount = statementFeeAmount.executeQuery(sFeeAmount);
		    		double feeAmount=0.00;

		    		while(resultFeeAmount.next())
		    		{
			    		feeAmount = resultFeeAmount.getDouble(1);
			    		
		    		}    			 
			    	receipt_id.add(resultPaymentAmount.getInt("receipt_id"));
			    	receipt_Date.add(receiptDate);
	    			payment_Amount.add(resultPaymentAmount.getDouble("Payment_Amount"));
	    			payment_Discount.add(resultPaymentAmount.getDouble("Payment_Discount"));
	    			payment_Fine.add(resultPaymentAmount.getDouble("Payment_Fine"));
		    		fee_Name.add(feeName);
		    		fee_Amount.add(feeAmount);
		    		student_id.add(studentId);
		    		student_Name.add(studentName);
		    		totalPayment = totalPayment + resultPaymentAmount.getDouble("Payment_Amount");
		    		x = x +1 ;
		    	}

			}
    		Fee_Payment_Amount.setFee_Name(fee_Name);
    		Fee_Payment_Amount.setFee_Amount(fee_Amount);
    		Fee_Payment_Amount.setPayment_Amount(payment_Amount);
    		Fee_Payment_Amount.setPayment_Discount(payment_Discount);
    		Fee_Payment_Amount.setPayment_Fine(payment_Fine);
    		Fee_Payment_Amount.setReceipt_id(receipt_id);
    		Fee_Payment_Amount.setReceipt_Date(receipt_Date);
    		Fee_Payment_Amount.setStudent_id(student_id);
    		Fee_Payment_Amount.setStudent_Name(student_Name);
    		Fee_Payment_Amount.setTotalPayment(totalPayment);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Payment_Amount;
	}

	public Fee_Payment_Amount getCollectionsByStudent(int school_id, int grade_id, int school_Year_id, int Student_id){
		
		Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount(); 		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementPaymentAmount = connection.createStatement();
			 Statement statementGradeStudents = connection.createStatement();
			 Statement statementFeeIDs = connection.createStatement();
			 Statement statementPaymentTransportAmount = connection.createStatement();){
	    	ArrayList<Integer> student_id = new ArrayList<Integer>();
			ArrayList<String> student_Name = new ArrayList<String>();
			ArrayList<Integer> fee_id = new ArrayList<Integer>();
			ArrayList<Double> total_Payment = new ArrayList<Double>();
			double totalPaymentAllStudents = 0.00;
	    	ArrayList<ArrayList<String>> student_Payment_Amount = new ArrayList<ArrayList<String>>();
	    	String sGradeStudents = null;
	    	if(Student_id==0)
	    	{
		    	sGradeStudents = "select * from Students where Class_id in (Select Class_id from Classes where Grade_id="+grade_id+")";
	    	}
	    	else
	    	{
		    	sGradeStudents = "select * from Students where Student_id="+Student_id;
	    	}
	    	ResultSet resultGradeStudents = statementGradeStudents.executeQuery(sGradeStudents);

	    	String sFeeIDs = "select fee_id from fee_types where school_id="+school_id;
	    	ResultSet resultFeeIDs = statementFeeIDs.executeQuery(sFeeIDs);
    		int j=0;
    		while(resultGradeStudents.next())
	    	{
    			double totalPayment = 0.00;
    			resultFeeIDs.beforeFirst();
	    		while (resultFeeIDs.next())
	    		{
					String sPaymentAmount = "Select SUM(Payment_Amount), SUM(Payment_Discount), SUM(Payment_Fine) from fee_payments where Fee_id="+resultFeeIDs.getInt("Fee_id")+" and receipt_id in (select receipt_id from fee_receipt where student_id="+resultGradeStudents.getInt("Student_id")+" and school_year_id="+school_Year_id+")";
					ResultSet resultPaymentAmount = statementPaymentAmount.executeQuery(sPaymentAmount);
		    		double paidAmount = 0.00;
		    		while (resultPaymentAmount.next())
			    	{			
		    			paidAmount = resultPaymentAmount.getDouble(1);
				    }	
		    		student_Payment_Amount.add(new ArrayList<String>());
		    		student_Payment_Amount.get(j).add(0,null);
		    		student_Payment_Amount.get(j).set(0, resultGradeStudents.getString("Student_id"));
		    		student_Payment_Amount.get(j).add(1,null);
		    		student_Payment_Amount.get(j).set(1, String.valueOf(paidAmount));
	    			j=j+1;
	    			totalPayment = totalPayment + paidAmount;
	    			totalPaymentAllStudents = totalPaymentAllStudents + paidAmount;
	    		}
				//////////////////////////////////////Transport Fees /////////////////////////////////////////////////////////////////////////////
		    	String sPaymentTransportAmount = "Select SUM(Payment_Amount), SUM(Payment_Discount), SUM(Payment_Fine), route_id from Transport_payments where Route_id in (select route_id from vehicle_route where vehicle_id in (select vehicle_id from vehicle where school_id=(select school_id from grades where grade_id="+grade_id+"))) and receipt_id in (select receipt_id from fee_receipt where student_id="+resultGradeStudents.getInt("Student_id")+" and school_year_id="+school_Year_id+")";
		    	ResultSet resultPaymentTransportAmount = statementPaymentTransportAmount.executeQuery(sPaymentTransportAmount);
	    		double paidTransportAmount = 0.00;
	    		while (resultPaymentTransportAmount.next())
		    	{			
	    			paidTransportAmount = resultPaymentTransportAmount.getDouble(1);
			    }	
	    		student_Payment_Amount.add(new ArrayList<String>());
	    		student_Payment_Amount.get(j).add(0,null);
	    		student_Payment_Amount.get(j).set(0, resultGradeStudents.getString("Student_id"));
	    		student_Payment_Amount.get(j).add(1,null);
	    		student_Payment_Amount.get(j).set(1, String.valueOf(paidTransportAmount));
	    		j=j+1;
    			totalPayment = totalPayment + paidTransportAmount;
    			totalPaymentAllStudents = totalPaymentAllStudents + paidTransportAmount;
    			///////////////////////////////////////////////////////////////////////////////////
	    		student_id.add(resultGradeStudents.getInt("Student_id"));
	    		student_Name.add(resultGradeStudents.getString("First_Name")+" "+resultGradeStudents.getString("Last_Name"));
	    		total_Payment.add(totalPayment);
	    	}
    		Fee_Payment_Amount.setStudent_id(student_id);
    		Fee_Payment_Amount.setStudent_Name(student_Name);
	    	Fee_Payment_Amount.setFee_id(fee_id);
	    	Fee_Payment_Amount.setStudent_Payment_Amount(student_Payment_Amount);
	    	Fee_Payment_Amount.setTotal_Payment(total_Payment);
	    	Fee_Payment_Amount.setTotalPayment(totalPaymentAllStudents);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Fee_Payment_Amount;
	}
}
