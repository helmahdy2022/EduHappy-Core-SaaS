package model.beans.admin;

import java.util.ArrayList;

public class Grade_Semesters {
   	ArrayList<Integer> semester_id = new ArrayList<Integer>();
	ArrayList<String> semester_Name = new ArrayList<String>();
	/**
	 * @return the semester_id
	 */
	public ArrayList<Integer> getSemester_id() {
		return semester_id;
	}
	/**
	 * @param semester_id the semester_id to set
	 */
	public void setSemester_id(ArrayList<Integer> semester_id) {
		this.semester_id = semester_id;
	}
	/**
	 * @return the semester_Name
	 */
	public ArrayList<String> getSemester_Name() {
		return semester_Name;
	}
	/**
	 * @param semester_Name the semester_Name to set
	 */
	public void setSemester_Name(ArrayList<String> semester_Name) {
		this.semester_Name = semester_Name;
	}
}
