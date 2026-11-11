package model.beans.admin;

import java.util.ArrayList;

public class School_Year {
	String Year_Name = null;
	String YS_date = null;
	String YE_date = null;
	ArrayList<ArrayList<String>> Year_Terms = new ArrayList<ArrayList<String>>();
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
	 * @return the yS_date
	 */
	public String getYS_date() {
		return YS_date;
	}
	/**
	 * @param yS_date the yS_date to set
	 */
	public void setYS_date(String yS_date) {
		YS_date = yS_date;
	}
	/**
	 * @return the yE_date
	 */
	public String getYE_date() {
		return YE_date;
	}
	/**
	 * @param yE_date the yE_date to set
	 */
	public void setYE_date(String yE_date) {
		YE_date = yE_date;
	}
	/**
	 * @return the year_Terms
	 */
	public ArrayList<ArrayList<String>> getYear_Terms() {
		return Year_Terms;
	}
	/**
	 * @param year_Terms the year_Terms to set
	 */
	public void setYear_Terms(ArrayList<ArrayList<String>> year_Terms) {
		Year_Terms = year_Terms;
	}
}
