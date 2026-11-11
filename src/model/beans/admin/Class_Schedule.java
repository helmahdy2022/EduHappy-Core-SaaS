package model.beans.admin;

import java.util.ArrayList;

public class Class_Schedule {
	int Session_Count = 0;
	int Days_Count=0;
	ArrayList<ArrayList<String>> Sessions = new ArrayList<ArrayList<String>>();

	ArrayList<Integer> Day_id = new ArrayList<Integer>();
	ArrayList<String> Day_Name = new ArrayList<String>();
	
	ArrayList<Integer> Course_id = new ArrayList<Integer>();
	ArrayList<String> Course_Name = new ArrayList<String>();
	ArrayList<Integer> Elective = new ArrayList<Integer>();
	ArrayList<ArrayList<String>> CourseExist = new ArrayList<ArrayList<String>>();
	
	ArrayList<Integer> Group_id = new ArrayList<Integer>();
	ArrayList<String> Group_Name = new ArrayList<String>();
	/**
	 * @return the session_Count
	 */
	public int getSession_Count() {
		return Session_Count;
	}
	/**
	 * @param session_Count the session_Count to set
	 */
	public void setSession_Count(int session_Count) {
		Session_Count = session_Count;
	}
	/**
	 * @return the days_Count
	 */
	public int getDays_Count() {
		return Days_Count;
	}
	/**
	 * @param days_Count the days_Count to set
	 */
	public void setDays_Count(int days_Count) {
		Days_Count = days_Count;
	}
	/**
	 * @return the sessions
	 */
	public ArrayList<ArrayList<String>> getSessions() {
		return Sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(ArrayList<ArrayList<String>> sessions) {
		Sessions = sessions;
	}
	/**
	 * @return the day_id
	 */
	public ArrayList<Integer> getDay_id() {
		return Day_id;
	}
	/**
	 * @param day_id the day_id to set
	 */
	public void setDay_id(ArrayList<Integer> day_id) {
		Day_id = day_id;
	}
	/**
	 * @return the day_Name
	 */
	public ArrayList<String> getDay_Name() {
		return Day_Name;
	}
	/**
	 * @param day_Name the day_Name to set
	 */
	public void setDay_Name(ArrayList<String> day_Name) {
		Day_Name = day_Name;
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
	 * @return the elective
	 */
	public ArrayList<Integer> getElective() {
		return Elective;
	}
	/**
	 * @param elective the elective to set
	 */
	public void setElective(ArrayList<Integer> elective) {
		Elective = elective;
	}
	/**
	 * @return the courseExist
	 */
	public ArrayList<ArrayList<String>> getCourseExist() {
		return CourseExist;
	}
	/**
	 * @param courseExist the courseExist to set
	 */
	public void setCourseExist(ArrayList<ArrayList<String>> courseExist) {
		CourseExist = courseExist;
	}
	/**
	 * @return the group_id
	 */
	public ArrayList<Integer> getGroup_id() {
		return Group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(ArrayList<Integer> group_id) {
		Group_id = group_id;
	}
	/**
	 * @return the group_Name
	 */
	public ArrayList<String> getGroup_Name() {
		return Group_Name;
	}
	/**
	 * @param group_Name the group_Name to set
	 */
	public void setGroup_Name(ArrayList<String> group_Name) {
		Group_Name = group_Name;
	}
}