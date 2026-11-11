package model.beans.admin;

import java.util.ArrayList;

public class Employee_Allowance {
	ArrayList<Integer> employee_Allowance_id = new ArrayList<Integer>();
	ArrayList<Double> amount = new ArrayList<Double>();
	ArrayList<Integer> payroll_Month = new ArrayList<Integer>();
	/**
	 * @return the employee_Allowance_id
	 */
	public ArrayList<Integer> getEmployee_Allowance_id() {
		return employee_Allowance_id;
	}
	/**
	 * @param employee_Allowance_id the employee_Allowance_id to set
	 */
	public void setEmployee_Allowance_id(ArrayList<Integer> employee_Allowance_id) {
		this.employee_Allowance_id = employee_Allowance_id;
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
	 * @return the payroll_Month
	 */
	public ArrayList<Integer> getPayroll_Month() {
		return payroll_Month;
	}
	/**
	 * @param payroll_Month the payroll_Month to set
	 */
	public void setPayroll_Month(ArrayList<Integer> payroll_Month) {
		this.payroll_Month = payroll_Month;
	}
	
}
