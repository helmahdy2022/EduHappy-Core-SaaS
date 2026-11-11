package model.beans.admin;

public class Department_Details {
	int Department_id;
	int school_id;
	String Department_Name;
	String Department_Description;
	/**
	 * @return the department_id
	 */
	public int getDepartment_id() {
		return Department_id;
	}
	/**
	 * @param department_id the department_id to set
	 */
	public void setDepartment_id(int department_id) {
		Department_id = department_id;
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
	 * @return the department_Name
	 */
	public String getDepartment_Name() {
		return Department_Name;
	}
	/**
	 * @param department_Name the department_Name to set
	 */
	public void setDepartment_Name(String department_Name) {
		Department_Name = department_Name;
	}
	/**
	 * @return the department_Description
	 */
	public String getDepartment_Description() {
		return Department_Description;
	}
	/**
	 * @param department_Description the department_Description to set
	 */
	public void setDepartment_Description(String department_Description) {
		Department_Description = department_Description;
	}
	
}
