package model.beans.admin;

import java.util.ArrayList;

public class Fee_Types_List {
	ArrayList<Integer> Fee_id = new ArrayList<Integer>();
	ArrayList<String> Fee_Name = new ArrayList<String>();
	ArrayList<String> Fee_Description = new ArrayList<String>();
	ArrayList<String> Payment_Mode = new ArrayList<String>();
	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
	/**
	 * @return the fee_id
	 */
	public ArrayList<Integer> getFee_id() {
		return Fee_id;
	}
	/**
	 * @param fee_id the fee_id to set
	 */
	public void setFee_id(ArrayList<Integer> fee_id) {
		Fee_id = fee_id;
	}
	/**
	 * @return the fee_Name
	 */
	public ArrayList<String> getFee_Name() {
		return Fee_Name;
	}
	/**
	 * @param fee_Name the fee_Name to set
	 */
	public void setFee_Name(ArrayList<String> fee_Name) {
		Fee_Name = fee_Name;
	}
	/**
	 * @return the fee_Description
	 */
	public ArrayList<String> getFee_Description() {
		return Fee_Description;
	}
	/**
	 * @param fee_Description the fee_Description to set
	 */
	public void setFee_Description(ArrayList<String> fee_Description) {
		Fee_Description = fee_Description;
	}
	/**
	 * @return the payment_Mode
	 */
	public ArrayList<String> getPayment_Mode() {
		return Payment_Mode;
	}
	/**
	 * @param payment_Mode the payment_Mode to set
	 */
	public void setPayment_Mode(ArrayList<String> payment_Mode) {
		Payment_Mode = payment_Mode;
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
