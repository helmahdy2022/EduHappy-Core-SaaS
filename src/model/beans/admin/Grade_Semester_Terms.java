package model.beans.admin;

import java.util.ArrayList;

public class Grade_Semester_Terms {
	ArrayList<Integer> Grade_Term_id = new ArrayList<Integer>();
	ArrayList<String> Grade_Term_Name = new ArrayList<String>();
	ArrayList<String> Term_Semester_id = new ArrayList<String>();
	/**
	 * @return the grade_Term_id
	 */
	public ArrayList<Integer> getGrade_Term_id() {
		return Grade_Term_id;
	}
	/**
	 * @param grade_Term_id the grade_Term_id to set
	 */
	public void setGrade_Term_id(ArrayList<Integer> grade_Term_id) {
		Grade_Term_id = grade_Term_id;
	}
	/**
	 * @return the grade_Term_Name
	 */
	public ArrayList<String> getGrade_Term_Name() {
		return Grade_Term_Name;
	}
	/**
	 * @param grade_Term_Name the grade_Term_Name to set
	 */
	public void setGrade_Term_Name(ArrayList<String> grade_Term_Name) {
		Grade_Term_Name = grade_Term_Name;
	}
	/**
	 * @return the term_Semester_id
	 */
	public ArrayList<String> getTerm_Semester_id() {
		return Term_Semester_id;
	}
	/**
	 * @param term_Semester_id the term_Semester_id to set
	 */
	public void setTerm_Semester_id(ArrayList<String> term_Semester_id) {
		Term_Semester_id = term_Semester_id;
	}
}
