package model.beans.admin;

public class Allowance_Details {
	int Allowance_id;
	int Payroll_id;
	String Allowance_Name;
	String Allowance_Description;
	String Allowance_Type;
	double Amount=-1;
	String Period;
	/**
	 * @return the allowance_id
	 */
	public int getAllowance_id() {
		return Allowance_id;
	}
	/**
	 * @param allowance_id the allowance_id to set
	 */
	public void setAllowance_id(int allowance_id) {
		Allowance_id = allowance_id;
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
	 * @return the allowance_Name
	 */
	public String getAllowance_Name() {
		return Allowance_Name;
	}
	/**
	 * @param allowance_Name the allowance_Name to set
	 */
	public void setAllowance_Name(String allowance_Name) {
		Allowance_Name = allowance_Name;
	}
	/**
	 * @return the allowance_Description
	 */
	public String getAllowance_Description() {
		return Allowance_Description;
	}
	/**
	 * @param allowance_Description the allowance_Description to set
	 */
	public void setAllowance_Description(String allowance_Description) {
		Allowance_Description = allowance_Description;
	}
	/**
	 * @return the allowance_Type
	 */
	public String getAllowance_Type() {
		return Allowance_Type;
	}
	/**
	 * @param allowance_Type the allowance_Type to set
	 */
	public void setAllowance_Type(String allowance_Type) {
		Allowance_Type = allowance_Type;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return Amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		Amount = amount;
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

}
