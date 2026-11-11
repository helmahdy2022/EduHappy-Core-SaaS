package model.beans.student;

import java.util.ArrayList;

public class Course_Structure {
	ArrayList<ArrayList<String>> Course_Structure_List = new ArrayList<ArrayList<String>>();

	/**
	 * @return the course_Structure_List
	 */
	public ArrayList<ArrayList<String>> getCourse_Structure_List() {
		return Course_Structure_List;
	}

	/**
	 * @param course_Structure_List the course_Structure_List to set
	 */
	public void setCourse_Structure_List(ArrayList<ArrayList<String>> course_Structure_List) {
		Course_Structure_List = course_Structure_List;
	}
	
}
