package model.beans.admin;

import java.util.ArrayList;

public class School_Years {
	ArrayList<Integer> School_Year_id = new ArrayList<Integer>();
	ArrayList<String> Year_Name = new ArrayList<String>();
	/**
	 * @return the school_Year_id
	 */
	public ArrayList<Integer> getSchool_Year_id() {
		return School_Year_id;
	}
	/**
	 * @param school_Year_id the school_Year_id to set
	 */
	public void setSchool_Year_id(ArrayList<Integer> school_Year_id) {
		School_Year_id = school_Year_id;
	}
	/**
	 * @return the year_Name
	 */
	public ArrayList<String> getYear_Name() {
		return Year_Name;
	}
	/**
	 * @param year_Name the year_Name to set
	 */
	public void setYear_Name(ArrayList<String> year_Name) {
		Year_Name = year_Name;
	}
}
