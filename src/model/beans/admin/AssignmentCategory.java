package model.beans.admin;

import java.util.ArrayList;

public class AssignmentCategory {
	ArrayList<Integer> Category_id = new ArrayList<Integer>();
	ArrayList<String> Category_Name = new ArrayList<String>();
	/**
	 * @return the category_id
	 */
	public ArrayList<Integer> getCategory_id() {
		return Category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(ArrayList<Integer> category_id) {
		Category_id = category_id;
	}
	/**
	 * @return the category_Name
	 */
	public ArrayList<String> getCategory_Name() {
		return Category_Name;
	}
	/**
	 * @param category_Name the category_Name to set
	 */
	public void setCategory_Name(ArrayList<String> category_Name) {
		Category_Name = category_Name;
	}
}
