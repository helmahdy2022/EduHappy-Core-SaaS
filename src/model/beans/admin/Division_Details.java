package model.beans.admin;

public class Division_Details {
	int Division_id = 0;
	String Division_Name = null;
	int Grade_id = 0;
	/**
	 * @return the grade_id
	 */
	public int getGrade_id() {
		return Grade_id;
	}
	/**
	 * @param grade_id the grade_id to set
	 */
	public void setGrade_id(int grade_id) {
		Grade_id = grade_id;
	}
	/**
	 * @return the division_id
	 */
	public int getDivision_id() {
		return Division_id;
	}
	/**
	 * @param division_id the division_id to set
	 */
	public void setDivision_id(int division_id) {
		Division_id = division_id;
	}
	/**
	 * @return the division_Name
	 */
	public String getDivision_Name() {
		return Division_Name;
	}
	/**
	 * @param division_Name the division_Name to set
	 */
	public void setDivision_Name(String division_Name) {
		Division_Name = division_Name;
	}
}
