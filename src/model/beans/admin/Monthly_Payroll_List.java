package model.beans.admin;

import java.util.ArrayList;

public class Monthly_Payroll_List {
	ArrayList<Integer> monthly_Payroll_id = new ArrayList<Integer>();
	ArrayList<String> report_Month = new ArrayList<String>();
	/**
	 * @return the monthly_Payroll_id
	 */
	public ArrayList<Integer> getMonthly_Payroll_id() {
		return monthly_Payroll_id;
	}
	/**
	 * @param monthly_Payroll_id the monthly_Payroll_id to set
	 */
	public void setMonthly_Payroll_id(ArrayList<Integer> monthly_Payroll_id) {
		this.monthly_Payroll_id = monthly_Payroll_id;
	}
	/**
	 * @return the report_Month
	 */
	public ArrayList<String> getReport_Month() {
		return report_Month;
	}
	/**
	 * @param report_Month the report_Month to set
	 */
	public void setReport_Month(ArrayList<String> report_Month) {
		this.report_Month = report_Month;
	}

	
}
