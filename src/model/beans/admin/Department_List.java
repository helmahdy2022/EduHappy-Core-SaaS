package model.beans.admin;

import java.util.ArrayList;

public class Department_List {
	ArrayList<Integer> Department_id = new ArrayList<Integer>();
	ArrayList<String> Department_Name = new ArrayList<String>();
	ArrayList<String> Department_Description = new ArrayList<String>();
	int school_id;
	/**
	 * @return the department_id
	 */
	public ArrayList<Integer> getDepartment_id() {
		return Department_id;
	}
	/**
	 * @param department_id the department_id to set
	 */
	public void setDepartment_id(ArrayList<Integer> department_id) {
		Department_id = department_id;
	}
	/**
	 * @return the department_Name
	 */
	public ArrayList<String> getDepartment_Name() {
		return Department_Name;
	}
	/**
	 * @param department_Name the department_Name to set
	 */
	public void setDepartment_Name(ArrayList<String> department_Name) {
		Department_Name = department_Name;
	}
	/**
	 * @return the department_Description
	 */
	public ArrayList<String> getDepartment_Description() {
		return Department_Description;
	}
	/**
	 * @param department_Description the department_Description to set
	 */
	public void setDepartment_Description(ArrayList<String> department_Description) {
		Department_Description = department_Description;
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
	
}
