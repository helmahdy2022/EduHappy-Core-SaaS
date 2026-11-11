package model.beans.admin;

import java.util.ArrayList;

public class Students {
	ArrayList<Integer> Student_id = new ArrayList<Integer>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	ArrayList<ArrayList<String>> Groups = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> Electives = new ArrayList<ArrayList<String>>();
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	ArrayList<Integer> Final_Grading = new ArrayList<Integer>();
	ArrayList<String> Passing_Status = new ArrayList<String>();
	String Year_Name;
	int Passing_Grade;
	boolean promoted;
	

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
	 * @return the student_id
	 */
	public ArrayList<Integer> getStudent_id() {
		return Student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(ArrayList<Integer> student_id) {
		Student_id = student_id;
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
	 * @return the class_id
	 */
	public ArrayList<Integer> getClass_id() {
		return Class_id;
	}
	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(ArrayList<Integer> class_id) {
		Class_id = class_id;
	}
	/**
	 * @return the groups
	 */
	public ArrayList<ArrayList<String>> getGroups() {
		return Groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(ArrayList<ArrayList<String>> groups) {
		Groups = groups;
	}
	/**
	 * @return the electives
	 */
	public ArrayList<ArrayList<String>> getElectives() {
		return Electives;
	}
	/**
	 * @param electives the electives to set
	 */
	public void setElectives(ArrayList<ArrayList<String>> electives) {
		Electives = electives;
	}
	/**
	 * @return the final_Grading
	 */
	public ArrayList<Integer> getFinal_Grading() {
		return Final_Grading;
	}
	/**
	 * @param final_Grading the final_Grading to set
	 */
	public void setFinal_Grading(ArrayList<Integer> final_Grading) {
		Final_Grading = final_Grading;
	}
	/**
	 * @return the year_Name
	 */
	public String getYear_Name() {
		return Year_Name;
	}
	/**
	 * @param year_Name the year_Name to set
	 */
	public void setYear_Name(String year_Name) {
		Year_Name = year_Name;
	}
	/**
	 * @return the passing_Grade
	 */
	public int getPassing_Grade() {
		return Passing_Grade;
	}
	/**
	 * @param passing_Grade the passing_Grade to set
	 */
	public void setPassing_Grade(int passing_Grade) {
		Passing_Grade = passing_Grade;
	}
	/**
	 * @return the passing_Status
	 */
	public ArrayList<String> getPassing_Status() {
		return Passing_Status;
	}
	/**
	 * @param passing_Status the passing_Status to set
	 */
	public void setPassing_Status(ArrayList<String> passing_Status) {
		Passing_Status = passing_Status;
	}
	/**
	 * @return the promoted
	 */
	public boolean isPromoted() {
		return promoted;
	}
	/**
	 * @param promoted the promoted to set
	 */
	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}
	
}
