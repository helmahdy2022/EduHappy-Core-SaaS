package model.beans.admin;

public class Grade_Semester_Details {
	int Semester_id = 0;
	String Semester_Name = null;
	int School_Year_id = 0;
	int Grade_id;
	boolean HasGradedExams;
	
	/**
	 * @return the semester_id
	 */
	public int getSemester_id() {
		return Semester_id;
	}
	/**
	 * @param semester_id the semester_id to set
	 */
	public void setSemester_id(int semester_id) {
		Semester_id = semester_id;
	}
	/**
	 * @return the semester_Name
	 */
	public String getSemester_Name() {
		return Semester_Name;
	}
	/**
	 * @param semester_Name the semester_Name to set
	 */
	public void setSemester_Name(String semester_Name) {
		Semester_Name = semester_Name;
	}
	/**
	 * @return the school_Year_id
	 */
	public int getSchool_Year_id() {
		return School_Year_id;
	}
	/**
	 * @param school_Year_id the school_Year_id to set
	 */
	public void setSchool_Year_id(int school_Year_id) {
		School_Year_id = school_Year_id;
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
	 * @return the hasGradedExams
	 */
	public boolean isHasGradedExams() {
		return HasGradedExams;
	}
	/**
	 * @param hasGradedExams the hasGradedExams to set
	 */
	public void setHasGradedExams(boolean hasGradedExams) {
		HasGradedExams = hasGradedExams;
	}
}
