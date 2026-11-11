package model.beans.admin;

import java.sql.Time;

public class Payroll_Details {
	int payroll_id;
	int school_id;
	String payroll_Name;
	String payroll_Description;
	double Working_Days;
	Time Working_Time_From;
	Time Working_Time_To;
	double Overtime_Normal;
	double Overtime_Weekend;
	double Overtime_Holidays;
	double Overtime_Daysoff;
	int Debit_Account_id;
	int Credit_Account_id;
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
	 * @return the school_id
	 */
	public int getSchool_id() {
		return school_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	/**
	 * @return the payroll_Name
	 */
	public String getPayroll_Name() {
		return payroll_Name;
	}
	/**
	 * @param payroll_Name the payroll_Name to set
	 */
	public void setPayroll_Name(String payroll_Name) {
		this.payroll_Name = payroll_Name;
	}
	/**
	 * @return the payroll_Description
	 */
	public String getPayroll_Description() {
		return payroll_Description;
	}
	/**
	 * @param payroll_Description the payroll_Description to set
	 */
	public void setPayroll_Description(String payroll_Description) {
		this.payroll_Description = payroll_Description;
	}
	/**
	 * @return the working_Days
	 */
	public double getWorking_Days() {
		return Working_Days;
	}
	/**
	 * @param working_Days the working_Days to set
	 */
	public void setWorking_Days(double working_Days) {
		Working_Days = working_Days;
	}
	/**
	 * @return the working_Time_From
	 */
	public Time getWorking_Time_From() {
		return Working_Time_From;
	}
	/**
	 * @param working_Time_From the working_Time_From to set
	 */
	public void setWorking_Time_From(Time working_Time_From) {
		Working_Time_From = working_Time_From;
	}
	/**
	 * @return the working_Time_To
	 */
	public Time getWorking_Time_To() {
		return Working_Time_To;
	}
	/**
	 * @param working_Time_To the working_Time_To to set
	 */
	public void setWorking_Time_To(Time working_Time_To) {
		Working_Time_To = working_Time_To;
	}
	/**
	 * @return the overtime_Normal
	 */
	public double getOvertime_Normal() {
		return Overtime_Normal;
	}
	/**
	 * @param overtime_Normal the overtime_Normal to set
	 */
	public void setOvertime_Normal(double overtime_Normal) {
		Overtime_Normal = overtime_Normal;
	}
	/**
	 * @return the overtime_Weekend
	 */
	public double getOvertime_Weekend() {
		return Overtime_Weekend;
	}
	/**
	 * @param overtime_Weekend the overtime_Weekend to set
	 */
	public void setOvertime_Weekend(double overtime_Weekend) {
		Overtime_Weekend = overtime_Weekend;
	}
	/**
	 * @return the overtime_Holidays
	 */
	public double getOvertime_Holidays() {
		return Overtime_Holidays;
	}
	/**
	 * @param overtime_Holidays the overtime_Holidays to set
	 */
	public void setOvertime_Holidays(double overtime_Holidays) {
		Overtime_Holidays = overtime_Holidays;
	}
	/**
	 * @return the overtime_Daysoff
	 */
	public double getOvertime_Daysoff() {
		return Overtime_Daysoff;
	}
	/**
	 * @param overtime_Daysoff the overtime_Daysoff to set
	 */
	public void setOvertime_Daysoff(double overtime_Daysoff) {
		Overtime_Daysoff = overtime_Daysoff;
	}
	/**
	 * @return the debit_Account_id
	 */
	public int getDebit_Account_id() {
		return Debit_Account_id;
	}
	/**
	 * @param debit_Account_id the debit_Account_id to set
	 */
	public void setDebit_Account_id(int debit_Account_id) {
		Debit_Account_id = debit_Account_id;
	}
	/**
	 * @return the credit_Account_id
	 */
	public int getCredit_Account_id() {
		return Credit_Account_id;
	}
	/**
	 * @param credit_Account_id the credit_Account_id to set
	 */
	public void setCredit_Account_id(int credit_Account_id) {
		Credit_Account_id = credit_Account_id;
	}

}
