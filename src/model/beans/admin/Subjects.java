package model.beans.admin;

import java.util.ArrayList;

public class Subjects {
	ArrayList<Integer> Subject_id = new ArrayList<Integer>();
	ArrayList<String> Subject_Name = new ArrayList<String>();
	/**
	 * @return the subject_id
	 */
	public ArrayList<Integer> getSubject_id() {
		return Subject_id;
	}
	/**
	 * @param subject_id the subject_id to set
	 */
	public void setSubject_id(ArrayList<Integer> subject_id) {
		Subject_id = subject_id;
	}
	/**
	 * @return the subject_Name
	 */
	public ArrayList<String> getSubject_Name() {
		return Subject_Name;
	}
	/**
	 * @param subject_Name the subject_Name to set
	 */
	public void setSubject_Name(ArrayList<String> subject_Name) {
		Subject_Name = subject_Name;
	}
}
