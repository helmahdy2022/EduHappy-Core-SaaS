package model.beans.admin;

import java.util.ArrayList;

public class Leave_Details {
	int Leave_id;
	int Payroll_id;
	String Leave_Name;
	String Leave_Description;
	String Payment_Type;
	String Period;
	double Carry_Forward=-1;
	int CF_Effective_Year;
	double All_Employees_Balance=-1;
	ArrayList<Integer> leave_Balance_id_List = new ArrayList<Integer>();
	ArrayList<Double> balance_Years_List = new ArrayList<Double>();
	ArrayList<Double> balance_Count_List = new ArrayList<Double>();
	
	/**
	 * @return the leave_id
	 */
	public int getLeave_id() {
		return Leave_id;
	}
	/**
	 * @param leave_id the leave_id to set
	 */
	public void setLeave_id(int leave_id) {
		Leave_id = leave_id;
	}
	/**
	 * @return the payroll_id
	 */
	public int getPayroll_id() {
		return Payroll_id;
	}
	/**
	 * @param payroll_id the payroll_id to set
	 */
	public void setPayroll_id(int payroll_id) {
		Payroll_id = payroll_id;
	}
	/**
	 * @return the leave_Name
	 */
	public String getLeave_Name() {
		return Leave_Name;
	}
	/**
	 * @param leave_Name the leave_Name to set
	 */
	public void setLeave_Name(String leave_Name) {
		Leave_Name = leave_Name;
	}
	/**
	 * @return the leave_Description
	 */
	public String getLeave_Description() {
		return Leave_Description;
	}
	/**
	 * @param leave_Description the leave_Description to set
	 */
	public void setLeave_Description(String leave_Description) {
		Leave_Description = leave_Description;
	}
	/**
	 * @return the payment_Type
	 */
	public String getPayment_Type() {
		return Payment_Type;
	}
	/**
	 * @param payment_Type the payment_Type to set
	 */
	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}

	/**
	 * @return the all_Employees_Balance
	 */
	public double getAll_Employees_Balance() {
		return All_Employees_Balance;
	}
	/**
	 * @param all_Employees_Balance the all_Employees_Balance to set
	 */
	public void setAll_Employees_Balance(double all_Employees_Balance) {
		All_Employees_Balance = all_Employees_Balance;
	}

	/**
	 * @return the carry_Forward
	 */
	public double getCarry_Forward() {
		return Carry_Forward;
	}
	/**
	 * @param carry_Forward the carry_Forward to set
	 */
	public void setCarry_Forward(double carry_Forward) {
		Carry_Forward = carry_Forward;
	}

	/**
	 * @return the cF_Effective_Year
	 */
	public int getCF_Effective_Year() {
		return CF_Effective_Year;
	}
	/**
	 * @param cF_Effective_Year the cF_Effective_Year to set
	 */
	public void setCF_Effective_Year(int cF_Effective_Year) {
		CF_Effective_Year = cF_Effective_Year;
	}
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return Period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		Period = period;
	}

	/**
	 * @return the leave_Balance_id_List
	 */
	public ArrayList<Integer> getLeave_Balance_id_List() {
		return leave_Balance_id_List;
	}
	/**
	 * @param leave_Balance_id_List the leave_Balance_id_List to set
	 */
	public void setLeave_Balance_id_List(ArrayList<Integer> leave_Balance_id_List) {
		this.leave_Balance_id_List = leave_Balance_id_List;
	}
	/**
	 * @return the balance_Years_List
	 */
	public ArrayList<Double> getBalance_Years_List() {
		return balance_Years_List;
	}
	/**
	 * @param balance_Years_List the balance_Years_List to set
	 */
	public void setBalance_Years_List(ArrayList<Double> balance_Years_List) {
		this.balance_Years_List = balance_Years_List;
	}
	/**
	 * @return the balance_Count_List
	 */
	public ArrayList<Double> getBalance_Count_List() {
		return balance_Count_List;
	}
	/**
	 * @param balance_Count_List the balance_Count_List to set
	 */
	public void setBalance_Count_List(ArrayList<Double> balance_Count_List) {
		this.balance_Count_List = balance_Count_List;
	}
	
}
