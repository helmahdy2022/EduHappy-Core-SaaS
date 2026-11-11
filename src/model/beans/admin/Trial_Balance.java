package model.beans.admin;

import java.util.ArrayList;

public class Trial_Balance {
	ArrayList<Integer> financial_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Account_Debit = new ArrayList<Double>();
	ArrayList<Double> financial_Account_Credit = new ArrayList<Double>();
	double totalDebitAmount = 0.00;
	double totalCreditAmount = 0.00;
	/**
	 * @return the financial_Account_id
	 */
	public ArrayList<Integer> getFinancial_Account_id() {
		return financial_Account_id;
	}
	/**
	 * @param financial_Account_id the financial_Account_id to set
	 */
	public void setFinancial_Account_id(ArrayList<Integer> financial_Account_id) {
		this.financial_Account_id = financial_Account_id;
	}
	/**
	 * @return the financial_Account_Name
	 */
	public ArrayList<String> getFinancial_Account_Name() {
		return financial_Account_Name;
	}
	/**
	 * @param financial_Account_Name the financial_Account_Name to set
	 */
	public void setFinancial_Account_Name(ArrayList<String> financial_Account_Name) {
		this.financial_Account_Name = financial_Account_Name;
	}
	/**
	 * @return the financial_Account_Debit
	 */
	public ArrayList<Double> getFinancial_Account_Debit() {
		return financial_Account_Debit;
	}
	/**
	 * @param financial_Account_Debit the financial_Account_Debit to set
	 */
	public void setFinancial_Account_Debit(ArrayList<Double> financial_Account_Debit) {
		this.financial_Account_Debit = financial_Account_Debit;
	}
	/**
	 * @return the financial_Account_Credit
	 */
	public ArrayList<Double> getFinancial_Account_Credit() {
		return financial_Account_Credit;
	}
	/**
	 * @param financial_Account_Credit the financial_Account_Credit to set
	 */
	public void setFinancial_Account_Credit(ArrayList<Double> financial_Account_Credit) {
		this.financial_Account_Credit = financial_Account_Credit;
	}
	/**
	 * @return the totalDebitAmount
	 */
	public double getTotalDebitAmount() {
		return totalDebitAmount;
	}
	/**
	 * @param totalDebitAmount the totalDebitAmount to set
	 */
	public void setTotalDebitAmount(double totalDebitAmount) {
		this.totalDebitAmount = totalDebitAmount;
	}
	/**
	 * @return the totalCreditAmount
	 */
	public double getTotalCreditAmount() {
		return totalCreditAmount;
	}
	/**
	 * @param totalCreditAmount the totalCreditAmount to set
	 */
	public void setTotalCreditAmount(double totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}
	
}
