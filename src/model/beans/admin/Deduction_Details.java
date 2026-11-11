package model.beans.admin;

public class Deduction_Details {
	int Deduction_id;
	int Payroll_id;
	String Deduction_Name;
	String Deduction_Description;
	String Deduction_Type;
	double Amount=-1;
	String Period;
	/**
	 * @return the deduction_id
	 */
	public int getDeduction_id() {
		return Deduction_id;
	}
	/**
	 * @param deduction_id the deduction_id to set
	 */
	public void setDeduction_id(int deduction_id) {
		Deduction_id = deduction_id;
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
	 * @return the deduction_Name
	 */
	public String getDeduction_Name() {
		return Deduction_Name;
	}
	/**
	 * @param deduction_Name the deduction_Name to set
	 */
	public void setDeduction_Name(String deduction_Name) {
		Deduction_Name = deduction_Name;
	}
	/**
	 * @return the deduction_Description
	 */
	public String getDeduction_Description() {
		return Deduction_Description;
	}
	/**
	 * @param deduction_Description the deduction_Description to set
	 */
	public void setDeduction_Description(String deduction_Description) {
		Deduction_Description = deduction_Description;
	}
	/**
	 * @return the deduction_Type
	 */
	public String getDeduction_Type() {
		return Deduction_Type;
	}
	/**
	 * @param deduction_Type the deduction_Type to set
	 */
	public void setDeduction_Type(String deduction_Type) {
		Deduction_Type = deduction_Type;
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
