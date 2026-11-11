package model.beans.admin;

import java.util.ArrayList;

public class Year_Terms {
	ArrayList<Integer> Year_Term_id = new ArrayList<Integer>();
	ArrayList<String> Year_Term_Name = new ArrayList<String>();
	ArrayList<String> Term_Semester_id = new ArrayList<String>();

	/**
	 * @return the year_Term_id
	 */
	public ArrayList<Integer> getYear_Term_id() {
		return Year_Term_id;
	}
	/**
	 * @param year_Term_id the year_Term_id to set
	 */
	public void setYear_Term_id(ArrayList<Integer> year_Term_id) {
		Year_Term_id = year_Term_id;
	}
	/**
	 * @return the year_Term_Name
	 */
	public ArrayList<String> getYear_Term_Name() {
		return Year_Term_Name;
	}
	/**
	 * @param year_Term_Name the year_Term_Name to set
	 */
	public void setYear_Term_Name(ArrayList<String> year_Term_Name) {
		Year_Term_Name = year_Term_Name;
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
