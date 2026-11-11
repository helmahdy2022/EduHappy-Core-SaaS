package model.beans.admin;

public class Semester_Exam_Details {
	String Semester_Source;
	int exam_id;
	int course_id;
	int semester_id;
	int exam_Grade;
	int exam_Contribution;
	boolean hasGrades;
	boolean hasTerms;	
	/**
	 * @return the exam_id
	 */
	public int getExam_id() {
		return exam_id;
	}
	/**
	 * @param exam_id the exam_id to set
	 */
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	/**
	 * @return the semester_Source
	 */
	public String getSemester_Source() {
		return Semester_Source;
	}
	/**
	 * @param semester_Source the semester_Source to set
	 */
	public void setSemester_Source(String semester_Source) {
		Semester_Source = semester_Source;
	}
	/**
	 * @return the course_id
	 */
	public int getCourse_id() {
		return course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	/**
	 * @return the semester_id
	 */
	public int getSemester_id() {
		return semester_id;
	}
	/**
	 * @param semester_id the semester_id to set
	 */
	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}
	/**
	 * @return the exam_Grade
	 */
	public int getExam_Grade() {
		return exam_Grade;
	}
	/**
	 * @param exam_Grade the exam_Grade to set
	 */
	public void setExam_Grade(int exam_Grade) {
		this.exam_Grade = exam_Grade;
	}
	/**
	 * @return the exam_Contribution
	 */
	public int getExam_Contribution() {
		return exam_Contribution;
	}
	/**
	 * @param exam_Contribution the exam_Contribution to set
	 */
	public void setExam_Contribution(int exam_Contribution) {
		this.exam_Contribution = exam_Contribution;
	}

	/**
	 * @return the hasGrades
	 */
	public boolean isHasGrades() {
		return hasGrades;
	}
	/**
	 * @param hasGrades the hasGrades to set
	 */
	public void setHasGrades(boolean hasGrades) {
		this.hasGrades = hasGrades;
	}
	/**
	 * @return the hasTerms
	 */
	public boolean isHasTerms() {
		return hasTerms;
	}
	/**
	 * @param hasTerms the hasTerms to set
	 */
	public void setHasTerms(boolean hasTerms) {
		this.hasTerms = hasTerms;
	}
	
}
