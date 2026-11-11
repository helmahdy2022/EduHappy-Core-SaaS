package model.beans.admin;

import java.util.ArrayList;

public class Bank_Accounts_List {
	ArrayList<Integer> bank_Account_id = new ArrayList<Integer>();
	ArrayList<String> bank_Account_Number = new ArrayList<String>();
	/**
	 * @return the bank_Account_id
	 */
	public ArrayList<Integer> getBank_Account_id() {
		return bank_Account_id;
	}
	/**
	 * @param bank_Account_id the bank_Account_id to set
	 */
	public void setBank_Account_id(ArrayList<Integer> bank_Account_id) {
		this.bank_Account_id = bank_Account_id;
	}
	/**
	 * @return the bank_Account_Number
	 */
	public ArrayList<String> getBank_Account_Number() {
		return bank_Account_Number;
	}
	/**
	 * @param bank_Account_Number the bank_Account_Number to set
	 */
	public void setBank_Account_Number(ArrayList<String> bank_Account_Number) {
		this.bank_Account_Number = bank_Account_Number;
	}
	
}
