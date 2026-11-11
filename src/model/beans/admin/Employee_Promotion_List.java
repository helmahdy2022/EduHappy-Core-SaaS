package model.beans.admin;

import java.util.ArrayList;

public class Employee_Promotion_List {
	ArrayList<Integer> employee_Promotion_id = new ArrayList<Integer>();
	ArrayList<String> promotion_Date = new ArrayList<String>();
	ArrayList<Double> basic_Salary = new ArrayList<Double>();
	ArrayList<String> promotion_Designation = new ArrayList<String>();
	int employee_id;
	/**
	 * @return the employee_Promotion_id
	 */
	public ArrayList<Integer> getEmployee_Promotion_id() {
		return employee_Promotion_id;
	}
	/**
	 * @param employee_Promotion_id the employee_Promotion_id to set
	 */
	public void setEmployee_Promotion_id(ArrayList<Integer> employee_Promotion_id) {
		this.employee_Promotion_id = employee_Promotion_id;
	}
	/**
	 * @return the promotion_Date
	 */
	public ArrayList<String> getPromotion_Date() {
		return promotion_Date;
	}
	/**
	 * @param promotion_Date the promotion_Date to set
	 */
	public void setPromotion_Date(ArrayList<String> promotion_Date) {
		this.promotion_Date = promotion_Date;
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
	 * @return the promotion_Designation
	 */
	public ArrayList<String> getPromotion_Designation() {
		return promotion_Designation;
	}
	/**
	 * @param promotion_Designation the promotion_Designation to set
	 */
	public void setPromotion_Designation(ArrayList<String> promotion_Designation) {
		this.promotion_Designation = promotion_Designation;
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
