package model.beans.admin;

import java.util.ArrayList;

public class Deduction_List {
	ArrayList<Integer> deduction_id = new ArrayList<Integer>();
	ArrayList<String> deduction_Name = new ArrayList<String>();
	ArrayList<String> deduction_Description = new ArrayList<String>();
	ArrayList<String> deduction_Type = new ArrayList<String>();
	ArrayList<String> period = new ArrayList<String>();
	ArrayList<Double> amount = new ArrayList<Double>();
	int payroll_id;
	/**
	 * @return the deduction_id
	 */
	public ArrayList<Integer> getDeduction_id() {
		return deduction_id;
	}
	/**
	 * @param deduction_id the deduction_id to set
	 */
	public void setDeduction_id(ArrayList<Integer> deduction_id) {
		this.deduction_id = deduction_id;
	}
	/**
	 * @return the deduction_Name
	 */
	public ArrayList<String> getDeduction_Name() {
		return deduction_Name;
	}
	/**
	 * @param deduction_Name the deduction_Name to set
	 */
	public void setDeduction_Name(ArrayList<String> deduction_Name) {
		this.deduction_Name = deduction_Name;
	}
	/**
	 * @return the deduction_Description
	 */
	public ArrayList<String> getDeduction_Description() {
		return deduction_Description;
	}
	/**
	 * @param deduction_Description the deduction_Description to set
	 */
	public void setDeduction_Description(ArrayList<String> deduction_Description) {
		this.deduction_Description = deduction_Description;
	}
	/**
	 * @return the deduction_Type
	 */
	public ArrayList<String> getDeduction_Type() {
		return deduction_Type;
	}
	/**
	 * @param deduction_Type the deduction_Type to set
	 */
	public void setDeduction_Type(ArrayList<String> deduction_Type) {
		this.deduction_Type = deduction_Type;
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
