package model.beans.admin;

public class Subject_Details {
	int Subject_id = 0;
	String Subject_Name = null;
	int Grade_id = 0;
	/**
	 * @return the subject_id
	 */
	public int getSubject_id() {
		return Subject_id;
	}
	/**
	 * @param subject_id the subject_id to set
	 */
	public void setSubject_id(int subject_id) {
		Subject_id = subject_id;
	}
	/**
	 * @return the subject_Name
	 */
	public String getSubject_Name() {
		return Subject_Name;
	}
	/**
	 * @param subject_Name the subject_Name to set
	 */
	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}
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
}
