package model.beans.admin;

import java.util.ArrayList;

public class Expense_Types_List {
	ArrayList<Integer> expense_Type_id = new ArrayList<Integer>();
	ArrayList<String> expense_Type_Name = new ArrayList<String>();
	ArrayList<String> expense_Type_Description = new ArrayList<String>();
	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
	/**
	 * @return the expense_Type_id
	 */
	public ArrayList<Integer> getExpense_Type_id() {
		return expense_Type_id;
	}
	/**
	 * @param expense_Type_id the expense_Type_id to set
	 */
	public void setExpense_Type_id(ArrayList<Integer> expense_Type_id) {
		this.expense_Type_id = expense_Type_id;
	}
	/**
	 * @return the expense_Type_Name
	 */
	public ArrayList<String> getExpense_Type_Name() {
		return expense_Type_Name;
	}
	/**
	 * @param expense_Type_Name the expense_Type_Name to set
	 */
	public void setExpense_Type_Name(ArrayList<String> expense_Type_Name) {
		this.expense_Type_Name = expense_Type_Name;
	}
	/**
	 * @return the expense_Type_Description
	 */
	public ArrayList<String> getExpense_Type_Description() {
		return expense_Type_Description;
	}
	/**
	 * @param expense_Type_Description the expense_Type_Description to set
	 */
	public void setExpense_Type_Description(ArrayList<String> expense_Type_Description) {
		this.expense_Type_Description = expense_Type_Description;
	}
	/**
	 * @return the debit_Account
	 */
	public ArrayList<Integer> getDebit_Account() {
		return debit_Account;
	}
	/**
	 * @param debit_Account the debit_Account to set
	 */
	public void setDebit_Account(ArrayList<Integer> debit_Account) {
		this.debit_Account = debit_Account;
	}
	/**
	 * @return the credit_Account
	 */
	public ArrayList<Integer> getCredit_Account() {
		return credit_Account;
	}
	/**
	 * @param credit_Account the credit_Account to set
	 */
	public void setCredit_Account(ArrayList<Integer> credit_Account) {
		this.credit_Account = credit_Account;
	}
	
}
