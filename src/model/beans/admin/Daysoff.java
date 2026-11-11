package model.beans.admin;

import java.util.ArrayList;


public class Daysoff {
	ArrayList<Integer> Daysoff_id = new ArrayList<Integer>();
	ArrayList<String> Daysoff_Name = new ArrayList<String>();
	ArrayList<String> S_Date = new ArrayList<String>();
	ArrayList<String> E_Date = new ArrayList<String>();
	/**
	 * @return the daysoff_id
	 */
	public ArrayList<Integer> getDaysoff_id() {
		return Daysoff_id;
	}
	/**
	 * @param daysoff_id the daysoff_id to set
	 */
	public void setDaysoff_id(ArrayList<Integer> daysoff_id) {
		Daysoff_id = daysoff_id;
	}
	/**
	 * @return the daysoff_Name
	 */
	public ArrayList<String> getDaysoff_Name() {
		return Daysoff_Name;
	}
	/**
	 * @param daysoff_Name the daysoff_Name to set
	 */
	public void setDaysoff_Name(ArrayList<String> daysoff_Name) {
		Daysoff_Name = daysoff_Name;
	}
	/**
	 * @return the s_Date
	 */
	public ArrayList<String> getS_Date() {
		return S_Date;
	}
	/**
	 * @param s_Date the s_Date to set
	 */
	public void setS_Date(ArrayList<String> s_Date) {
		S_Date = s_Date;
	}
	/**
	 * @return the e_Date
	 */
	public ArrayList<String> getE_Date() {
		return E_Date;
	}
	/**
	 * @param e_Date the e_Date to set
	 */
	public void setE_Date(ArrayList<String> e_Date) {
		E_Date = e_Date;
	}
}
