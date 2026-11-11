package model.beans.admin;

import java.util.ArrayList;

public class General_Ledger {
	ArrayList<Integer> entry_id = new ArrayList<Integer>();
	ArrayList<String> journal_Date = new ArrayList<String>();
	ArrayList<String> journal_Details = new ArrayList<String>();
	ArrayList<Double> debit_Amount = new ArrayList<Double>();
	ArrayList<Double> credit_Amount = new ArrayList<Double>();
	ArrayList<Double> balance_Amount = new ArrayList<Double>();
	String Financial_Account_Name;
	/**
	 * @return the entry_id
	 */
	public ArrayList<Integer> getEntry_id() {
		return entry_id;
	}
	/**
	 * @param entry_id the entry_id to set
	 */
	public void setEntry_id(ArrayList<Integer> entry_id) {
		this.entry_id = entry_id;
	}
	/**
	 * @return the journal_Date
	 */
	public ArrayList<String> getJournal_Date() {
		return journal_Date;
	}
	/**
	 * @param journal_Date the journal_Date to set
	 */
	public void setJournal_Date(ArrayList<String> journal_Date) {
		this.journal_Date = journal_Date;
	}
	/**
	 * @return the journal_Details
	 */
	public ArrayList<String> getJournal_Details() {
		return journal_Details;
	}
	/**
	 * @param journal_Details the journal_Details to set
	 */
	public void setJournal_Details(ArrayList<String> journal_Details) {
		this.journal_Details = journal_Details;
	}
	/**
	 * @return the debit_Amount
	 */
	public ArrayList<Double> getDebit_Amount() {
		return debit_Amount;
	}
	/**
	 * @param debit_Amount the debit_Amount to set
	 */
	public void setDebit_Amount(ArrayList<Double> debit_Amount) {
		this.debit_Amount = debit_Amount;
	}
	/**
	 * @return the credit_Amount
	 */
	public ArrayList<Double> getCredit_Amount() {
		return credit_Amount;
	}
	/**
	 * @param credit_Amount the credit_Amount to set
	 */
	public void setCredit_Amount(ArrayList<Double> credit_Amount) {
		this.credit_Amount = credit_Amount;
	}
	/**
	 * @return the balance_Amount
	 */
	public ArrayList<Double> getBalance_Amount() {
		return balance_Amount;
	}
	/**
	 * @param balance_Amount the balance_Amount to set
	 */
	public void setBalance_Amount(ArrayList<Double> balance_Amount) {
		this.balance_Amount = balance_Amount;
	}
	/**
	 * @return the financial_Account_Name
	 */
	public String getFinancial_Account_Name() {
		return Financial_Account_Name;
	}
	/**
	 * @param financial_Account_Name the financial_Account_Name to set
	 */
	public void setFinancial_Account_Name(String financial_Account_Name) {
		Financial_Account_Name = financial_Account_Name;
	}
	
}
