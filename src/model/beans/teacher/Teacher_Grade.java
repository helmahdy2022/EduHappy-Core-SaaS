package model.beans.teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher_Grade {
	HashMap<String, ArrayList<ArrayList<String>>> grade_list =new HashMap<String, ArrayList<ArrayList<String>>>();

	/**
	 * @return the grade_list
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getGrade_list() {
		return grade_list;
	}

	/**
	 * @param grade_list the grade_list to set
	 */
	public void setGrade_list(
			HashMap<String, ArrayList<ArrayList<String>>> grade_list) {
		this.grade_list = grade_list;
	}
}
