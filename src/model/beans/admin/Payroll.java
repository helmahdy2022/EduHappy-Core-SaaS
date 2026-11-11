package model.beans.admin;

import java.util.ArrayList;
import java.util.Date;

public class Payroll {
	Date monthly_Payroll_Date = null;
	String payroll_Month;
	String payroll_Year;
	ArrayList<Integer> employee_id = new ArrayList<Integer>();
	ArrayList<String> first_Name = new ArrayList<String>();
	ArrayList<String> last_Name = new ArrayList<String>();
	ArrayList<Double> basic_Salary = new ArrayList<Double>();
	ArrayList<Double> unpaid_Leave = new ArrayList<Double>();
	ArrayList<Double> overtime_Earning = new ArrayList<Double>();
	ArrayList<ArrayList<String>> employee_Allowance_Amount = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> employee_Deduction_Amount = new ArrayList<ArrayList<String>>();
	ArrayList<Double> net_Salary = new ArrayList<Double>();
	double Total_Salaries;
	/**
	 * @return the monthly_Payroll_Date
	 */
	public Date getMonthly_Payroll_Date() {
		return monthly_Payroll_Date;
	}
	/**
	 * @param monthly_Payroll_Date the monthly_Payroll_Date to set
	 */
	public void setMonthly_Payroll_Date(Date monthly_Payroll_Date) {
		this.monthly_Payroll_Date = monthly_Payroll_Date;
	}

	/**
	 * @return the payroll_Month
	 */
	public String getPayroll_Month() {
		return payroll_Month;
	}
	/**
	 * @param payroll_Month the payroll_Month to set
	 */
	public void setPayroll_Month(String payroll_Month) {
		this.payroll_Month = payroll_Month;
	}
	/**
	 * @return the payroll_Year
	 */
	public String getPayroll_Year() {
		return payroll_Year;
	}
	/**
	 * @param payroll_Year the payroll_Year to set
	 */
	public void setPayroll_Year(String payroll_Year) {
		this.payroll_Year = payroll_Year;
	}
	/**
	 * @return the employee_id
	 */
	public ArrayList<Integer> getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(ArrayList<Integer> employee_id) {
		this.employee_id = employee_id;
	}

	/**
	 * @return the first_Name
	 */
	public ArrayList<String> getFirst_Name() {
		return first_Name;
	}
	/**
	 * @param first_Name the first_Name to set
	 */
	public void setFirst_Name(ArrayList<String> first_Name) {
		this.first_Name = first_Name;
	}
	/**
	 * @return the last_Name
	 */
	public ArrayList<String> getLast_Name() {
		return last_Name;
	}
	/**
	 * @param last_Name the last_Name to set
	 */
	public void setLast_Name(ArrayList<String> last_Name) {
		this.last_Name = last_Name;
	}
	/**
	 * @return the basic_Salary
	 */
	public ArrayList<Double> getBasic_Salary() {
		return basic_Salary;
	}
	/**
	 * @param basic_Salary the basic_Salary to set
	 */
	public void setBasic_Salary(ArrayList<Double> basic_Salary) {
		this.basic_Salary = basic_Salary;
	}
	/**
	 * @return the unpaid_Leave
	 */
	public ArrayList<Double> getUnpaid_Leave() {
		return unpaid_Leave;
	}
	/**
	 * @param unpaid_Leave the unpaid_Leave to set
	 */
	public void setUnpaid_Leave(ArrayList<Double> unpaid_Leave) {
		this.unpaid_Leave = unpaid_Leave;
	}
	/**
	 * @return the overtime_Earning
	 */
	public ArrayList<Double> getOvertime_Earning() {
		return overtime_Earning;
	}
	/**
	 * @param overtime_Earning the overtime_Earning to set
	 */
	public void setOvertime_Earning(ArrayList<Double> overtime_Earning) {
		this.overtime_Earning = overtime_Earning;
	}
	/**
	 * @return the employee_Allowance_Amount
	 */
	public ArrayList<ArrayList<String>> getEmployee_Allowance_Amount() {
		return employee_Allowance_Amount;
	}
	/**
	 * @param employee_Allowance_Amount the employee_Allowance_Amount to set
	 */
	public void setEmployee_Allowance_Amount(ArrayList<ArrayList<String>> employee_Allowance_Amount) {
		this.employee_Allowance_Amount = employee_Allowance_Amount;
	}
	/**
	 * @return the employee_Deduction_Amount
	 */
	public ArrayList<ArrayList<String>> getEmployee_Deduction_Amount() {
		return employee_Deduction_Amount;
	}
	/**
	 * @param employee_Deduction_Amount the employee_Deduction_Amount to set
	 */
	public void setEmployee_Deduction_Amount(ArrayList<ArrayList<String>> employee_Deduction_Amount) {
		this.employee_Deduction_Amount = employee_Deduction_Amount;
	}
	/**
	 * @return the net_Salary
	 */
	public ArrayList<Double> getNet_Salary() {
		return net_Salary;
	}
	/**
	 * @param net_Salary the net_Salary to set
	 */
	public void setNet_Salary(ArrayList<Double> net_Salary) {
		this.net_Salary = net_Salary;
	}
	/**
	 * @return the total_Salaries
	 */
	public double getTotal_Salaries() {
		return Total_Salaries;
	}
	/**
	 * @param total_Salaries the total_Salaries to set
	 */
	public void setTotal_Salaries(double total_Salaries) {
		Total_Salaries = total_Salaries;
	}

	
}
