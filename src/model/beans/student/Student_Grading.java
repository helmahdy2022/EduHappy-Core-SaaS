package model.beans.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Student_Grading {
	LinkedHashMap<String, ArrayList<ArrayList<String>>> Term_Grading_Map = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
	ArrayList<ArrayList<String>> Grading = new ArrayList<ArrayList<String>>();
	HashMap<String, ArrayList<ArrayList<String>>> CourseCatmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	double Attendance_Grade;
	int Overall_Grade=0;
	int School_year_id;
	String Year_Name;
	ArrayList<Integer> Previous_Years_id = new ArrayList<Integer>();
	ArrayList<String> Previous_Years_Name = new ArrayList<String>();
	/**
	 * @return the term_Grading_Map
	 */
	public LinkedHashMap<String, ArrayList<ArrayList<String>>> getTerm_Grading_Map() {
		return Term_Grading_Map;
	}
	/**
	 * @param term_Grading_Map the term_Grading_Map to set
	 */
	public void setTerm_Grading_Map(
			LinkedHashMap<String, ArrayList<ArrayList<String>>> term_Grading_Map) {
		Term_Grading_Map = term_Grading_Map;
	}
	/**
	 * @return the grading
	 */
	public ArrayList<ArrayList<String>> getGrading() {
		return Grading;
	}
	/**
	 * @param grading the grading to set
	 */
	public void setGrading(ArrayList<ArrayList<String>> grading) {
		Grading = grading;
	}
	/**
	 * @return the courseCatmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getCourseCatmap() {
		return CourseCatmap;
	}
	/**
	 * @param courseCatmap the courseCatmap to set
	 */
	public void setCourseCatmap(
			HashMap<String, ArrayList<ArrayList<String>>> courseCatmap) {
		CourseCatmap = courseCatmap;
	}
	/**
	 * @return the attendance_Grade
	 */
	public double getAttendance_Grade() {
		return Attendance_Grade;
	}
	/**
	 * @param attendance_Grade the attendance_Grade to set
	 */
	public void setAttendance_Grade(double attendance_Grade) {
		Attendance_Grade = attendance_Grade;
	}

	/**
	 * @return the overall_Grade
	 */
	public int getOverall_Grade() {
		return Overall_Grade;
	}
	/**
	 * @param overall_Grade the overall_Grade to set
	 */
	public void setOverall_Grade(int overall_Grade) {
		Overall_Grade = overall_Grade;
	}
	/**
	 * @return the school_year_id
	 */
	public int getSchool_year_id() {
		return School_year_id;
	}
	/**
	 * @param school_year_id the school_year_id to set
	 */
	public void setSchool_year_id(int school_year_id) {
		School_year_id = school_year_id;
	}

	/**
	 * @return the year_Name
	 */
	public String getYear_Name() {
		return Year_Name;
	}
	/**
	 * @param year_Name the year_Name to set
	 */
	public void setYear_Name(String year_Name) {
		Year_Name = year_Name;
	}
	/**
	 * @return the previous_Years_id
	 */
	public ArrayList<Integer> getPrevious_Years_id() {
		return Previous_Years_id;
	}
	/**
	 * @param previous_Years_id the previous_Years_id to set
	 */
	public void setPrevious_Years_id(ArrayList<Integer> previous_Years_id) {
		Previous_Years_id = previous_Years_id;
	}
	/**
	 * @return the previous_Years_Name
	 */
	public ArrayList<String> getPrevious_Years_Name() {
		return Previous_Years_Name;
	}
	/**
	 * @param previous_Years_Name the previous_Years_Name to set
	 */
	public void setPrevious_Years_Name(ArrayList<String> previous_Years_Name) {
		Previous_Years_Name = previous_Years_Name;
	}
	
}
