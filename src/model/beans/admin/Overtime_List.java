package model.beans.admin;

import java.util.ArrayList;

public class Overtime_List {
	ArrayList<Integer> overtime_id = new ArrayList<Integer>();
	ArrayList<String> overtime_Date = new ArrayList<String>();
	ArrayList<Double> overtime_Hours = new ArrayList<Double>();
	int employee_id;
	/**
	 * @return the overtime_id
	 */
	public ArrayList<Integer> getOvertime_id() {
		return overtime_id;
	}
	/**
	 * @param overtime_id the overtime_id to set
	 */
	public void setOvertime_id(ArrayList<Integer> overtime_id) {
		this.overtime_id = overtime_id;
	}
	/**
	 * @return the overtime_Date
	 */
	public ArrayList<String> getOvertime_Date() {
		return overtime_Date;
	}
	/**
	 * @param overtime_Date the overtime_Date to set
	 */
	public void setOvertime_Date(ArrayList<String> overtime_Date) {
		this.overtime_Date = overtime_Date;
	}
	/**
	 * @return the overtime_Hours
	 */
	public ArrayList<Double> getOvertime_Hours() {
		return overtime_Hours;
	}
	/**
	 * @param overtime_Hours the overtime_Hours to set
	 */
	public void setOvertime_Hours(ArrayList<Double> overtime_Hours) {
		this.overtime_Hours = overtime_Hours;
	}
	/**
	 * @return the employee_id
	 */
	public int getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
}
