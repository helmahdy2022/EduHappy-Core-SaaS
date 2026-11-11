package model.beans.teacher;

import java.util.ArrayList;

public class Teacher_School {
	String School_Name = null;
	private ArrayList<String> school_list =null;
	/**
	 * @return the school_Name
	 */
	public String getSchool_Name() {
		return School_Name;
	}
	/**
	 * @param school_Name the school_Name to set
	 */
	public void setSchool_Name(String school_Name) {
		School_Name = school_Name;
	}
	/**
	 * @return the school_list
	 */
	public ArrayList<String> getSchool_list() {
		return school_list;
	}
	/**
	 * @param school_list the school_list to set
	 */
	public void setSchool_list(ArrayList<String> school_list) {
		this.school_list = school_list;
	}
}
