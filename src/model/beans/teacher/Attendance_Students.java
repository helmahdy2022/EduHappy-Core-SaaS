package model.beans.teacher;

import java.util.ArrayList;

public class Attendance_Students {
	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	ArrayList<String> Status = new ArrayList<String>();


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
	 * @return the school_id
	 */
	public ArrayList<Integer> getSchool_id() {
		return School_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(ArrayList<Integer> school_id) {
		School_id = school_id;
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
	/**
	 * @return the student_id
	 */
	public ArrayList<Integer> getStudent_id() {
		return Student_id;
	}
	/**
	 * @param arrayList the student_id to set
	 */
	public void setStudent_id(ArrayList<Integer> arrayList) {
		Student_id = arrayList;
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
	 * @return the status
	 */
	public ArrayList<String> getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ArrayList<String> status) {
		Status = status;
	}
}
