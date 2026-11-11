package model.beans.admin;

import java.sql.Time;
import java.util.ArrayList;

public class Payroll_List {
	ArrayList<Integer> payroll_id = new ArrayList<Integer>();
	ArrayList<String> payroll_Name = new ArrayList<String>();
	ArrayList<String> payroll_Description = new ArrayList<String>();
	ArrayList<Double> working_Days = new ArrayList<Double>();
	ArrayList<Time> working_Time_From = new ArrayList<Time>();
	ArrayList<Time> working_Time_To = new ArrayList<Time>();
	ArrayList<Double> overtime_Normal = new ArrayList<Double>();
	ArrayList<Double> overtime_Weekend = new ArrayList<Double>();
	ArrayList<Double> overtime_Holidays = new ArrayList<Double>();
	ArrayList<Double> overtime_Daysoff = new ArrayList<Double>();
	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
	int school_id;
	ArrayList<String> payroll_School_Name = new ArrayList<String>();
	/**
	 * @return the payroll_id
	 */
	public ArrayList<Integer> getPayroll_id() {
		return payroll_id;
	}
	/**
	 * @param payroll_id the payroll_id to set
	 */
	public void setPayroll_id(ArrayList<Integer> payroll_id) {
		this.payroll_id = payroll_id;
	}
	/**
	 * @return the payroll_Name
	 */
	public ArrayList<String> getPayroll_Name() {
		return payroll_Name;
	}
	/**
	 * @param payroll_Name the payroll_Name to set
	 */
	public void setPayroll_Name(ArrayList<String> payroll_Name) {
		this.payroll_Name = payroll_Name;
	}
	/**
	 * @return the payroll_Description
	 */
	public ArrayList<String> getPayroll_Description() {
		return payroll_Description;
	}
	/**
	 * @param payroll_Description the payroll_Description to set
	 */
	public void setPayroll_Description(ArrayList<String> payroll_Description) {
		this.payroll_Description = payroll_Description;
	}
	/**
	 * @return the working_Days
	 */
	public ArrayList<Double> getWorking_Days() {
		return working_Days;
	}
	/**
	 * @param working_Days the working_Days to set
	 */
	public void setWorking_Days(ArrayList<Double> working_Days) {
		this.working_Days = working_Days;
	}
	/**
	 * @return the working_Time_From
	 */
	public ArrayList<Time> getWorking_Time_From() {
		return working_Time_From;
	}
	/**
	 * @param working_Time_From the working_Time_From to set
	 */
	public void setWorking_Time_From(ArrayList<Time> working_Time_From) {
		this.working_Time_From = working_Time_From;
	}
	/**
	 * @return the working_Time_To
	 */
	public ArrayList<Time> getWorking_Time_To() {
		return working_Time_To;
	}
	/**
	 * @param working_Time_To the working_Time_To to set
	 */
	public void setWorking_Time_To(ArrayList<Time> working_Time_To) {
		this.working_Time_To = working_Time_To;
	}
	/**
	 * @return the overtime_Normal
	 */
	public ArrayList<Double> getOvertime_Normal() {
		return overtime_Normal;
	}
	/**
	 * @param overtime_Normal the overtime_Normal to set
	 */
	public void setOvertime_Normal(ArrayList<Double> overtime_Normal) {
		this.overtime_Normal = overtime_Normal;
	}
	/**
	 * @return the overtime_Weekend
	 */
	public ArrayList<Double> getOvertime_Weekend() {
		return overtime_Weekend;
	}
	/**
	 * @param overtime_Weekend the overtime_Weekend to set
	 */
	public void setOvertime_Weekend(ArrayList<Double> overtime_Weekend) {
		this.overtime_Weekend = overtime_Weekend;
	}
	/**
	 * @return the overtime_Holidays
	 */
	public ArrayList<Double> getOvertime_Holidays() {
		return overtime_Holidays;
	}
	/**
	 * @param overtime_Holidays the overtime_Holidays to set
	 */
	public void setOvertime_Holidays(ArrayList<Double> overtime_Holidays) {
		this.overtime_Holidays = overtime_Holidays;
	}
	/**
	 * @return the overtime_Daysoff
	 */
	public ArrayList<Double> getOvertime_Daysoff() {
		return overtime_Daysoff;
	}
	/**
	 * @param overtime_Daysoff the overtime_Daysoff to set
	 */
	public void setOvertime_Daysoff(ArrayList<Double> overtime_Daysoff) {
		this.overtime_Daysoff = overtime_Daysoff;
	}
	/**
	 * @return the debit_Account
	 */
	public ArrayList<Integer> getDebit_Account() {
		return debit_Account;
	}
	/**
	 * @param debit_Account the debit_Account to set
	 */
	public void setDebit_Account(ArrayList<Integer> debit_Account) {
		this.debit_Account = debit_Account;
	}
	/**
	 * @return the credit_Account
	 */
	public ArrayList<Integer> getCredit_Account() {
		return credit_Account;
	}
	/**
	 * @param credit_Account the credit_Account to set
	 */
	public void setCredit_Account(ArrayList<Integer> credit_Account) {
		this.credit_Account = credit_Account;
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
	 * @return the payroll_School_Name
	 */
	public ArrayList<String> getPayroll_School_Name() {
		return payroll_School_Name;
	}
	/**
	 * @param payroll_School_Name the payroll_School_Name to set
	 */
	public void setPayroll_School_Name(ArrayList<String> payroll_School_Name) {
		this.payroll_School_Name = payroll_School_Name;
	}
}
