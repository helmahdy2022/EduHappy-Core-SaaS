package model.beans.admin;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher_School_Grade {
	ArrayList<String> School_id = new ArrayList<String>();
	ArrayList<String> School_Name = new ArrayList<String>();
	ArrayList<String> Teacher_Grades = new ArrayList<String>();
	ArrayList<String> Teacher_Classes = new ArrayList<String>();
	ArrayList<String> Teacher_Content = new ArrayList<String>();
	ArrayList<ArrayList<String>> Teacher_Courses = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> GradeCourses = new ArrayList<ArrayList<String>>();
	HashMap<String, ArrayList<ArrayList<String>>> Coursemap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> Classmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> Grademap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> GradeCoursesmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	/**
	 * @return the school_id
	 */
	public ArrayList<String> getSchool_id() {
		return School_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(ArrayList<String> school_id) {
		School_id = school_id;
	}
	/**
	 * @return the school_Name
	 */
	public ArrayList<String> getSchool_Name() {
		return School_Name;
	}
	/**
	 * @param school_Name the school_Name to set
	 */
	public void setSchool_Name(ArrayList<String> school_Name) {
		School_Name = school_Name;
	}
	/**
	 * @return the teacher_Grades
	 */
	public ArrayList<String> getTeacher_Grades() {
		return Teacher_Grades;
	}
	/**
	 * @param teacher_Grades the teacher_Grades to set
	 */
	public void setTeacher_Grades(ArrayList<String> teacher_Grades) {
		Teacher_Grades = teacher_Grades;
	}
	/**
	 * @return the teacher_Classes
	 */
	public ArrayList<String> getTeacher_Classes() {
		return Teacher_Classes;
	}
	/**
	 * @param teacher_Classes the teacher_Classes to set
	 */
	public void setTeacher_Classes(ArrayList<String> teacher_Classes) {
		Teacher_Classes = teacher_Classes;
	}
	/**
	 * @return the teacher_Content
	 */
	public ArrayList<String> getTeacher_Content() {
		return Teacher_Content;
	}
	/**
	 * @param teacher_Content the teacher_Content to set
	 */
	public void setTeacher_Content(ArrayList<String> teacher_Content) {
		Teacher_Content = teacher_Content;
	}
	/**
	 * @return the teacher_Courses
	 */
	public ArrayList<ArrayList<String>> getTeacher_Courses() {
		return Teacher_Courses;
	}
	/**
	 * @param teacher_Courses the teacher_Courses to set
	 */
	public void setTeacher_Courses(ArrayList<ArrayList<String>> teacher_Courses) {
		Teacher_Courses = teacher_Courses;
	}
	/**
	 * @return the gradeCourses
	 */
	public ArrayList<ArrayList<String>> getGradeCourses() {
		return GradeCourses;
	}
	/**
	 * @param gradeCourses the gradeCourses to set
	 */
	public void setGradeCourses(ArrayList<ArrayList<String>> gradeCourses) {
		GradeCourses = gradeCourses;
	}
	/**
	 * @return the coursemap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getCoursemap() {
		return Coursemap;
	}
	/**
	 * @param coursemap the coursemap to set
	 */
	public void setCoursemap(HashMap<String, ArrayList<ArrayList<String>>> coursemap) {
		Coursemap = coursemap;
	}
	/**
	 * @return the classmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getClassmap() {
		return Classmap;
	}
	/**
	 * @param classmap the classmap to set
	 */
	public void setClassmap(HashMap<String, ArrayList<ArrayList<String>>> classmap) {
		Classmap = classmap;
	}
	/**
	 * @return the grademap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getGrademap() {
		return Grademap;
	}
	/**
	 * @param grademap the grademap to set
	 */
	public void setGrademap(HashMap<String, ArrayList<ArrayList<String>>> grademap) {
		Grademap = grademap;
	}
	/**
	 * @return the gradeCoursesmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getGradeCoursesmap() {
		return GradeCoursesmap;
	}
	/**
	 * @param gradeCoursesmap the gradeCoursesmap to set
	 */
	public void setGradeCoursesmap(
			HashMap<String, ArrayList<ArrayList<String>>> gradeCoursesmap) {
		GradeCoursesmap = gradeCoursesmap;
	}
}
