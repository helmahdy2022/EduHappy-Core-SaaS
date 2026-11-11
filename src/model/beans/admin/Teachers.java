package model.beans.admin;

import java.util.ArrayList;

public class Teachers {
	ArrayList<Integer> Teacher_id = new ArrayList<Integer>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	ArrayList<Integer> student_id = new ArrayList<Integer>();
	ArrayList<String> student_First_Name = new ArrayList<String>();
	ArrayList<String> student_Last_Name = new ArrayList<String>();
	ArrayList<ArrayList<String>> Teachers_List = new ArrayList<ArrayList<String>>();	
	/**
	 * @return the teacher_id
	 */
	public ArrayList<Integer> getTeacher_id() {
		return Teacher_id;
	}
	/**
	 * @param teacher_id the teacher_id to set
	 */
	public void setTeacher_id(ArrayList<Integer> teacher_id) {
		Teacher_id = teacher_id;
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

	
	public ArrayList<Integer> getStudent_id() {
		return student_id;
	}
	public void setStudent_id(ArrayList<Integer> student_id) {
		this.student_id = student_id;
	}
	public ArrayList<String> getStudent_First_Name() {
		return student_First_Name;
	}
	public void setStudent_First_Name(ArrayList<String> student_First_Name) {
		this.student_First_Name = student_First_Name;
	}
	public ArrayList<String> getStudent_Last_Name() {
		return student_Last_Name;
	}
	public void setStudent_Last_Name(ArrayList<String> student_Last_Name) {
		this.student_Last_Name = student_Last_Name;
	}
	public ArrayList<ArrayList<String>> getTeachers_List() {
		return Teachers_List;
	}
	public void setTeachers_List(ArrayList<ArrayList<String>> teachers_List) {
		Teachers_List = teachers_List;
	}
	
}
