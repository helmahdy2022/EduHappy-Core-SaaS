package model.beans.admin;

import java.util.ArrayList;

public class Classes {
   	ArrayList<Integer> Class_id = new ArrayList<Integer>();
	ArrayList<String> Class_Name = new ArrayList<String>();
	ArrayList<Integer> Class_Division_id = new ArrayList<Integer>();
	/**
	 * @return the class_Division_id
	 */
	public ArrayList<Integer> getClass_Division_id() {
		return Class_Division_id;
	}
	/**
	 * @param class_Division_id the class_Division_id to set
	 */
	public void setClass_Division_id(ArrayList<Integer> class_Division_id) {
		Class_Division_id = class_Division_id;
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
