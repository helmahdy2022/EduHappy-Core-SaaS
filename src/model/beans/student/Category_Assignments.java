package model.beans.student;

import java.util.ArrayList;

public class Category_Assignments {
	ArrayList<ArrayList<String>> CategoryAssignmentList = new ArrayList<ArrayList<String>>();
	String Category_Name;
	String Course_Name;

	/**
	 * @return the categoryAssignmentList
	 */
	public ArrayList<ArrayList<String>> getCategoryAssignmentList() {
		return CategoryAssignmentList;
	}

	/**
	 * @param categoryAssignmentList the categoryAssignmentList to set
	 */
	public void setCategoryAssignmentList(
			ArrayList<ArrayList<String>> categoryAssignmentList) {
		CategoryAssignmentList = categoryAssignmentList;
	}

	/**
	 * @return the category_Name
	 */
	public String getCategory_Name() {
		return Category_Name;
	}

	/**
	 * @param category_Name the category_Name to set
	 */
	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}

	/**
	 * @return the course_Name
	 */
	public String getCourse_Name() {
		return Course_Name;
	}

	/**
	 * @param course_Name the course_Name to set
	 */
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	
}
