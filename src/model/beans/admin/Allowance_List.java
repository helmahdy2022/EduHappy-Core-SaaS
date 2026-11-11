package model.beans.admin;

import java.util.ArrayList;

public class Allowance_List {
	ArrayList<Integer> allowance_id = new ArrayList<Integer>();
	ArrayList<String> allowance_Name = new ArrayList<String>();
	ArrayList<String> allowance_Description = new ArrayList<String>();
	ArrayList<String> allowance_Type = new ArrayList<String>();
	ArrayList<String> period = new ArrayList<String>();
	ArrayList<Double> amount = new ArrayList<Double>();
	int payroll_id;
	/**
	 * @return the allowance_id
	 */
	public ArrayList<Integer> getAllowance_id() {
		return allowance_id;
	}
	/**
	 * @param allowance_id the allowance_id to set
	 */
	public void setAllowance_id(ArrayList<Integer> allowance_id) {
		this.allowance_id = allowance_id;
	}
	/**
	 * @return the allowance_Name
	 */
	public ArrayList<String> getAllowance_Name() {
		return allowance_Name;
	}
	/**
	 * @param allowance_Name the allowance_Name to set
	 */
	public void setAllowance_Name(ArrayList<String> allowance_Name) {
		this.allowance_Name = allowance_Name;
	}
	/**
	 * @return the allowance_Description
	 */
	public ArrayList<String> getAllowance_Description() {
		return allowance_Description;
	}
	/**
	 * @param allowance_Description the allowance_Description to set
	 */
	public void setAllowance_Description(ArrayList<String> allowance_Description) {
		this.allowance_Description = allowance_Description;
	}
	/**
	 * @return the allowance_Type
	 */
	public ArrayList<String> getAllowance_Type() {
		return allowance_Type;
	}
	/**
	 * @param allowance_Type the allowance_Type to set
	 */
	public void setAllowance_Type(ArrayList<String> allowance_Type) {
		this.allowance_Type = allowance_Type;
	}
	/**
	 * @return the period
	 */
	public ArrayList<String> getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(ArrayList<String> period) {
		this.period = period;
	}
	/**
	 * @return the amount
	 */
	public ArrayList<Double> getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(ArrayList<Double> amount) {
		this.amount = amount;
	}
	/**
	 * @return the payroll_id
	 */
	public int getPayroll_id() {
		return payroll_id;
	}
	/**
	 * @param payroll_id the payroll_id to set
	 */
	public void setPayroll_id(int payroll_id) {
		this.payroll_id = payroll_id;
	}
	
}
