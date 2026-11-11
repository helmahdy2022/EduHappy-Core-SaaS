package model.beans.teacher;

import java.util.ArrayList;

public class Assignment_Details {
	String SchoolName = null;
	String GradeName = null;
	String ClassName = null;
	String CourseName = null;
	String GroupName = null;
	String Subject = null;
	String Text = null;
	String Max_Grade = null;
	int Assignment_id;
	int Attempts_Allowed;
	int Duration;
	int Category_id;
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> fileExt = new ArrayList<String>();
	ArrayList<String> realFileName = new ArrayList<String>();
	ArrayList<Integer> file_id = new ArrayList<Integer>();
	ArrayList<String> fileCategory = new ArrayList<String>();

	/**
	 * @return the realFileName
	 */
	public ArrayList<String> getRealFileName() {
		return realFileName;
	}
	/**
	 * @param realFileName the realFileName to set
	 */
	public void setRealFileName(ArrayList<String> realFileName) {
		this.realFileName = realFileName;
	}
	/**
	 * @return the file_id
	 */
	public ArrayList<Integer> getFile_id() {
		return file_id;
	}
	/**
	 * @param file_id the file_id to set
	 */
	public void setFile_id(ArrayList<Integer> file_id) {
		this.file_id = file_id;
	}
	/**
	 * @return the fileName
	 */
	public ArrayList<String> getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileExt
	 */
	public ArrayList<String> getFileExt() {
		return fileExt;
	}
	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(ArrayList<String> fileExt) {
		this.fileExt = fileExt;
	}
	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return SchoolName;
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return GradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		GradeName = gradeName;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return ClassName;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		ClassName = className;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return GroupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return Subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return Text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		Text = text;
	}
	/**
	 * @return the max_Grade
	 */
	public String getMax_Grade() {
		return Max_Grade;
	}
	/**
	 * @param max_Grade the max_Grade to set
	 */
	public void setMax_Grade(String max_Grade) {
		Max_Grade = max_Grade;
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
	 * @return the attempts_Allowed
	 */
	public int getAttempts_Allowed() {
		return Attempts_Allowed;
	}
	/**
	 * @param attempts_Allowed the attempts_Allowed to set
	 */
	public void setAttempts_Allowed(int attempts_Allowed) {
		Attempts_Allowed = attempts_Allowed;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return Duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		Duration = duration;
	}
	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return Category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		Category_id = category_id;
	}

	/**
	 * @return the fileCategory
	 */
	public ArrayList<String> getFileCategory() {
		return fileCategory;
	}
	/**
	 * @param fileCategory the fileCategory to set
	 */
	public void setFileCategory(ArrayList<String> fileCategory) {
		this.fileCategory = fileCategory;
	}
}
