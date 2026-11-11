package model.beans.student;

import java.util.ArrayList;

public class Course_Content {
	ArrayList<ArrayList<String>> CourseList = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> CourseContent = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> CourseLessons = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> BookFiles = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> LessonFiles = new ArrayList<ArrayList<String>>();
	int Account_id;
	int School_id;

	/**
	 * @return the lessonFiles
	 */
	public ArrayList<ArrayList<String>> getLessonFiles() {
		return LessonFiles;
	}
	/**
	 * @param lessonFiles the lessonFiles to set
	 */
	public void setLessonFiles(ArrayList<ArrayList<String>> lessonFiles) {
		LessonFiles = lessonFiles;
	}
	/**
	 * @return the bookFiles
	 */
	public ArrayList<ArrayList<String>> getBookFiles() {
		return BookFiles;
	}
	/**
	 * @param bookFiles the bookFiles to set
	 */
	public void setBookFiles(ArrayList<ArrayList<String>> bookFiles) {
		BookFiles = bookFiles;
	}
	/**
	 * @return the courseList
	 */
	public ArrayList<ArrayList<String>> getCourseList() {
		return CourseList;
	}
	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(ArrayList<ArrayList<String>> courseList) {
		CourseList = courseList;
	}
	/**
	 * @return the courseContent
	 */
	public ArrayList<ArrayList<String>> getCourseContent() {
		return CourseContent;
	}
	/**
	 * @param courseContent the courseContent to set
	 */
	public void setCourseContent(ArrayList<ArrayList<String>> courseContent) {
		CourseContent = courseContent;
	}
	/**
	 * @return the courseLessons
	 */
	public ArrayList<ArrayList<String>> getCourseLessons() {
		return CourseLessons;
	}
	/**
	 * @param courseLessons the courseLessons to set
	 */
	public void setCourseLessons(ArrayList<ArrayList<String>> courseLessons) {
		CourseLessons = courseLessons;
	}
	
	
	public int getAccount_id() {
		return Account_id;
	}
	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}
	public int getSchool_id() {
		return School_id;
	}
	public void setSchool_id(int school_id) {
		School_id = school_id;
	}
}
