package model.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;

public class Days implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> Day_id = new ArrayList<Integer>();
	ArrayList<String> Day_Name = new ArrayList<String>();
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
}
