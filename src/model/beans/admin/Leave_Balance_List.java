package model.beans.admin;

import java.util.ArrayList;

public class Leave_Balance_List {
	ArrayList<Integer> leave_Balance_id = new ArrayList<Integer>();
	ArrayList<Double> balance_Years = new ArrayList<Double>();
	ArrayList<Double> balance_Count = new ArrayList<Double>();
	int Leave_id;
	int balance_no;
	/**
	 * @return the leave_Balance_id
	 */
	public ArrayList<Integer> getLeave_Balance_id() {
		return leave_Balance_id;
	}
	/**
	 * @param leave_Balance_id the leave_Balance_id to set
	 */
	public void setLeave_Balance_id(ArrayList<Integer> leave_Balance_id) {
		this.leave_Balance_id = leave_Balance_id;
	}
	/**
	 * @return the balance_Years
	 */
	public ArrayList<Double> getBalance_Years() {
		return balance_Years;
	}
	/**
	 * @param balance_Years the balance_Years to set
	 */
	public void setBalance_Years(ArrayList<Double> balance_Years) {
		this.balance_Years = balance_Years;
	}
	/**
	 * @return the balance_Count
	 */
	public ArrayList<Double> getBalance_Count() {
		return balance_Count;
	}
	/**
	 * @param balance_Count the balance_Count to set
	 */
	public void setBalance_Count(ArrayList<Double> balance_Count) {
		this.balance_Count = balance_Count;
	}
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
	 * @return the balance_no
	 */
	public int getBalance_no() {
		return balance_no;
	}
	/**
	 * @param balance_no the balance_no to set
	 */
	public void setBalance_no(int balance_no) {
		this.balance_no = balance_no;
	}
	
}
