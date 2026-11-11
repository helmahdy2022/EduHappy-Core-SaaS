package model.beans.admin;

import java.util.ArrayList;

public class Employee_Leave_List {
	ArrayList<Integer> Employee_Leave_id = new ArrayList<Integer>();
	ArrayList<String> Leave_From = new ArrayList<String>();
	ArrayList<String> Leave_To = new ArrayList<String>();
	ArrayList<Double> Leave_Days = new ArrayList<Double>();
	int employee_id;
	int leave_id;
	/**
	 * @return the employee_Leave_id
	 */
	public ArrayList<Integer> getEmployee_Leave_id() {
		return Employee_Leave_id;
	}
	/**
	 * @param employee_Leave_id the employee_Leave_id to set
	 */
	public void setEmployee_Leave_id(ArrayList<Integer> employee_Leave_id) {
		Employee_Leave_id = employee_Leave_id;
	}
	/**
	 * @return the leave_From
	 */
	public ArrayList<String> getLeave_From() {
		return Leave_From;
	}
	/**
	 * @param leave_From the leave_From to set
	 */
	public void setLeave_From(ArrayList<String> leave_From) {
		Leave_From = leave_From;
	}
	/**
	 * @return the leave_To
	 */
	public ArrayList<String> getLeave_To() {
		return Leave_To;
	}
	/**
	 * @param leave_To the leave_To to set
	 */
	public void setLeave_To(ArrayList<String> leave_To) {
		Leave_To = leave_To;
	}
	/**
	 * @return the leave_Days
	 */
	public ArrayList<Double> getLeave_Days() {
		return Leave_Days;
	}
	/**
	 * @param leave_Days the leave_Days to set
	 */
	public void setLeave_Days(ArrayList<Double> leave_Days) {
		Leave_Days = leave_Days;
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
	/**
	 * @return the leave_id
	 */
	public int getLeave_id() {
		return leave_id;
	}
	/**
	 * @param leave_id the leave_id to set
	 */
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	
}
