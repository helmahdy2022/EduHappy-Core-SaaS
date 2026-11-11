package model.beans.admin;

import java.util.ArrayList;

public class Employee_Deduction {
	ArrayList<Integer> employee_Deduction_id = new ArrayList<Integer>();
	ArrayList<Double> amount = new ArrayList<Double>();
	ArrayList<Integer> payroll_Month = new ArrayList<Integer>();
	/**
	 * @return the employee_Deduction_id
	 */
	public ArrayList<Integer> getEmployee_Deduction_id() {
		return employee_Deduction_id;
	}
	/**
	 * @param employee_Deduction_id the employee_Deduction_id to set
	 */
	public void setEmployee_Deduction_id(ArrayList<Integer> employee_Deduction_id) {
		this.employee_Deduction_id = employee_Deduction_id;
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
