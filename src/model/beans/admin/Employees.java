package model.beans.admin;

import java.util.ArrayList;

public class Employees {
	ArrayList<Integer> Employee_id = new ArrayList<Integer>();
	ArrayList<Integer> TeacherAndAdmin_id = new ArrayList<Integer>();
	ArrayList<String> Employee_Group = new ArrayList<String>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	/**
	 * @return the employee_id
	 */
	public ArrayList<Integer> getEmployee_id() {
		return Employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(ArrayList<Integer> employee_id) {
		Employee_id = employee_id;
	}

	/**
	 * @return the teacherAndAdmin_id
	 */
	public ArrayList<Integer> getTeacherAndAdmin_id() {
		return TeacherAndAdmin_id;
	}
	/**
	 * @param teacherAndAdmin_id the teacherAndAdmin_id to set
	 */
	public void setTeacherAndAdmin_id(ArrayList<Integer> teacherAndAdmin_id) {
		TeacherAndAdmin_id = teacherAndAdmin_id;
	}
	/**
	 * @return the employee_Group
	 */
	public ArrayList<String> getEmployee_Group() {
		return Employee_Group;
	}
	/**
	 * @param employee_Group the employee_Group to set
	 */
	public void setEmployee_Group(ArrayList<String> employee_Group) {
		Employee_Group = employee_Group;
	}
	/**
	 * @return the first_Name
	 */
	public ArrayList<String> getFirst_Name() {
		return First_Name;
	}
	/**
	 * @param first_Name the first_Name to set
	 */
	public void setFirst_Name(ArrayList<String> first_Name) {
		First_Name = first_Name;
	}
	/**
	 * @return the last_Name
	 */
	public ArrayList<String> getLast_Name() {
		return Last_Name;
	}
	/**
	 * @param last_Name the last_Name to set
	 */
	public void setLast_Name(ArrayList<String> last_Name) {
		Last_Name = last_Name;
	}
	/**
	 * @return the username
	 */
	public ArrayList<String> getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(ArrayList<String> username) {
		Username = username;
	}
	/**
	 * @return the account_id
	 */
	public ArrayList<Integer> getAccount_id() {
		return Account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(ArrayList<Integer> account_id) {
		Account_id = account_id;
	}
	/**
	 * @return the fileName
	 */
	public ArrayList<String> getFileName() {
		return FileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(ArrayList<String> fileName) {
		FileName = fileName;
	}
	
}
