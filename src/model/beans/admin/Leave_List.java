package model.beans.admin;

import java.util.ArrayList;

public class Leave_List {
	ArrayList<Integer> Leave_id = new ArrayList<Integer>();
	ArrayList<String> Leave_Name = new ArrayList<String>();
	ArrayList<String> Leave_Description = new ArrayList<String>();
	ArrayList<String> Payment_Type = new ArrayList<String>();
	ArrayList<String> Period = new ArrayList<String>();
	ArrayList<Double> carry_Forward = new ArrayList<Double>();
	ArrayList<Double> All_Employees_Balance = new ArrayList<Double>();
	ArrayList<Integer> balance_no = new ArrayList<Integer>();
	int payroll_id;
	/**
	 * @return the leave_id
	 */
	public ArrayList<Integer> getLeave_id() {
		return Leave_id;
	}
	/**
	 * @param leave_id the leave_id to set
	 */
	public void setLeave_id(ArrayList<Integer> leave_id) {
		Leave_id = leave_id;
	}
	/**
	 * @return the leave_Name
	 */
	public ArrayList<String> getLeave_Name() {
		return Leave_Name;
	}
	/**
	 * @param leave_Name the leave_Name to set
	 */
	public void setLeave_Name(ArrayList<String> leave_Name) {
		Leave_Name = leave_Name;
	}
	/**
	 * @return the leave_Description
	 */
	public ArrayList<String> getLeave_Description() {
		return Leave_Description;
	}
	/**
	 * @param leave_Description the leave_Description to set
	 */
	public void setLeave_Description(ArrayList<String> leave_Description) {
		Leave_Description = leave_Description;
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
	/**
	 * @return the payment_Type
	 */
	public ArrayList<String> getPayment_Type() {
		return Payment_Type;
	}
	/**
	 * @param payment_Type the payment_Type to set
	 */
	public void setPayment_Type(ArrayList<String> payment_Type) {
		Payment_Type = payment_Type;
	}

	/**
	 * @return the carry_Forward
	 */
	public ArrayList<Double> getCarry_Forward() {
		return carry_Forward;
	}
	/**
	 * @param carry_Forward the carry_Forward to set
	 */
	public void setCarry_Forward(ArrayList<Double> carry_Forward) {
		this.carry_Forward = carry_Forward;
	}
	/**
	 * @return the all_Employees_Balance
	 */
	public ArrayList<Double> getAll_Employees_Balance() {
		return All_Employees_Balance;
	}
	/**
	 * @param all_Employees_Balance the all_Employees_Balance to set
	 */
	public void setAll_Employees_Balance(ArrayList<Double> all_Employees_Balance) {
		All_Employees_Balance = all_Employees_Balance;
	}
	/**
	 * @return the balance_no
	 */
	public ArrayList<Integer> getBalance_no() {
		return balance_no;
	}
	/**
	 * @param balance_no the balance_no to set
	 */
	public void setBalance_no(ArrayList<Integer> balance_no) {
		this.balance_no = balance_no;
	}
	/**
	 * @return the period
	 */
	public ArrayList<String> getPeriod() {
		return Period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(ArrayList<String> period) {
		Period = period;
	}
	
}
