package model.beans.teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher_Class {
	HashMap<String,ArrayList<ArrayList<String>>> class_list =new HashMap<String, ArrayList<ArrayList<String>>>();

	/**
	 * @return the class_list
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getClass_list() {
		return class_list;
	}

	/**
	 * @param class_list the class_list to set
	 */
	public void setClass_list(
			HashMap<String, ArrayList<ArrayList<String>>> class_list) {
		this.class_list = class_list;
	}
}
