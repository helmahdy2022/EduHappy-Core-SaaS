package model.beans.admin;

import java.util.ArrayList;

public class Authority_Report {
	ArrayList<Integer> Course_id = new ArrayList<Integer>();
	ArrayList<String> Course_Name = new ArrayList<String>();
	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	ArrayList<String> Class_Name = new ArrayList<String>();
	ArrayList<ArrayList<String>> Class_Authority_List = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> Content_Authority_List = new ArrayList<ArrayList<String>>();
	
	/**
	 * @return the class_Authority_List
	 */
	public ArrayList<ArrayList<String>> getClass_Authority_List() {
		return Class_Authority_List;
	}
	/**
	 * @param class_Authority_List the class_Authority_List to set
	 */
	public void setClass_Authority_List(ArrayList<ArrayList<String>> class_Authority_List) {
		Class_Authority_List = class_Authority_List;
	}
	/**
	 * @return the content_Authority_List
	 */
	public ArrayList<ArrayList<String>> getContent_Authority_List() {
		return Content_Authority_List;
	}
	/**
	 * @param content_Authority_List the content_Authority_List to set
	 */
	public void setContent_Authority_List(ArrayList<ArrayList<String>> content_Authority_List) {
		Content_Authority_List = content_Authority_List;
	}
	/**
	 * @return the course_id
	 */
	public ArrayList<Integer> getCourse_id() {
		return Course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(ArrayList<Integer> course_id) {
		Course_id = course_id;
	}
	/**
	 * @return the course_Name
	 */
	public ArrayList<String> getCourse_Name() {
		return Course_Name;
	}
	/**
	 * @param course_Name the course_Name to set
	 */
	public void setCourse_Name(ArrayList<String> course_Name) {
		Course_Name = course_Name;
	}
	/**
	 * @return the class_id
	 */
	public ArrayList<Integer> getClass_id() {
		return Class_id;
	}
	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(ArrayList<Integer> class_id) {
		Class_id = class_id;
	}
	/**
	 * @return the class_Name
	 */
	public ArrayList<String> getClass_Name() {
		return Class_Name;
	}
	/**
	 * @param class_Name the class_Name to set
	 */
	public void setClass_Name(ArrayList<String> class_Name) {
		Class_Name = class_Name;
	}
	
}
