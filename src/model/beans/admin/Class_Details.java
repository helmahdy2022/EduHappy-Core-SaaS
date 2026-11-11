package model.beans.admin;

public class Class_Details {
	int Class_id = 0;
	String Class_Name = null;
	int Grade_id = 0; 
	String DeleteClass = null;
	
	/**
	 * @return the class_id
	 */
	public int getClass_id() {
		return Class_id;
	}
	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(int class_id) {
		Class_id = class_id;
	}
	/**
	 * @return the class_Name
	 */
	public String getClass_Name() {
		return Class_Name;
	}
	/**
	 * @param class_Name the class_Name to set
	 */
	public void setClass_Name(String class_Name) {
		Class_Name = class_Name;
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

	/**
	 * @return the deleteClass
	 */
	public String getDeleteClass() {
		return DeleteClass;
	}
	/**
	 * @param deleteClass the deleteClass to set
	 */
	public void setDeleteClass(String deleteClass) {
		DeleteClass = deleteClass;
	}
}
