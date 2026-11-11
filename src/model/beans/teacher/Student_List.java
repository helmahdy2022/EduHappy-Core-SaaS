package model.beans.teacher;

import java.util.ArrayList;

public class Student_List {
	ArrayList<ArrayList<String>> StudentList = new ArrayList<ArrayList<String>>();
	int Assignment_id;
	int Semester_Exam_id;
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	
	/**
	 * @return the account_id
	 */
	public ArrayList<Integer> getAccount_id() {
		return Account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(ArrayList<Integer> account_id) {
		Account_id = account_id;
	}
	/**
	 * @return the school_id
	 */
	public ArrayList<Integer> getSchool_id() {
		return School_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(ArrayList<Integer> school_id) {
		School_id = school_id;
	}
	/**
	 * @return the fileName
	 */
	public ArrayList<String> getFileName() {
		return FileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(ArrayList<String> fileName) {
		FileName = fileName;
	}
	/**
	 * @return the semester_Exam_id
	 */
	public int getSemester_Exam_id() {
		return Semester_Exam_id;
	}
	/**
	 * @param semester_Exam_id the semester_Exam_id to set
	 */
	public void setSemester_Exam_id(int semester_Exam_id) {
		Semester_Exam_id = semester_Exam_id;
	}
	int Semester_Exam_Grade;

	/**
	 * @return the studentList
	 */
	public ArrayList<ArrayList<String>> getStudentList() {
		return StudentList;
	}
	/**
	 * @param studentList the studentList to set
	 */
	public void setStudentList(ArrayList<ArrayList<String>> studentList) {
		StudentList = studentList;
	}
	/**
	 * @return the assignment_id
	 */
	public int getAssignment_id() {
		return Assignment_id;
	}
	/**
	 * @param assignment_id the assignment_id to set
	 */
	public void setAssignment_id(int assignment_id) {
		Assignment_id = assignment_id;
	}

	/**
	 * @return the semester_Exam_Grade
	 */
	public int getSemester_Exam_Grade() {
		return Semester_Exam_Grade;
	}
	/**
	 * @param semester_Exam_Grade the semester_Exam_Grade to set
	 */
	public void setSemester_Exam_Grade(int semester_Exam_Grade) {
		Semester_Exam_Grade = semester_Exam_Grade;
	}
}
