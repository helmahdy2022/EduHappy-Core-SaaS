package model.beans.admin;

import java.util.ArrayList;

public class Divisions {
	ArrayList<Integer> Division_id = new ArrayList<Integer>();
	ArrayList<String> Division_Name = new ArrayList<String>();
	/**
	 * @return the division_id
	 */
	public ArrayList<Integer> getDivision_id() {
		return Division_id;
	}
	/**
	 * @param division_id the division_id to set
	 */
	public void setDivision_id(ArrayList<Integer> division_id) {
		Division_id = division_id;
	}
	/**
	 * @return the division_Name
	 */
	public ArrayList<String> getDivision_Name() {
		return Division_Name;
	}
	/**
	 * @param division_Name the division_Name to set
	 */
	public void setDivision_Name(ArrayList<String> division_Name) {
		Division_Name = division_Name;
	}
}
