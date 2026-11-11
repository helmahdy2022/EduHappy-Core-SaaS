package model.beans.admin;

import java.util.ArrayList;

public class Grades {
	ArrayList<Integer> Grade_id = new ArrayList<Integer>();
	/**
	 * @return the grade_id
	 */
	public ArrayList<Integer> getGrade_id() {
		return Grade_id;
	}
	/**
	 * @param grade_id the grade_id to set
	 */
	public void setGrade_id(ArrayList<Integer> grade_id) {
		Grade_id = grade_id;
	}
	/**
	 * @return the grade_Name
	 */
	public ArrayList<String> getGrade_Name() {
		return Grade_Name;
	}
	/**
	 * @param grade_Name the grade_Name to set
	 */
	public void setGrade_Name(ArrayList<String> grade_Name) {
		Grade_Name = grade_Name;
	}
	ArrayList<String> Grade_Name = new ArrayList<String>();
}
