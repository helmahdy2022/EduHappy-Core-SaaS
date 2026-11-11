package model.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;

public class Countries implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> Country_id = new ArrayList<Integer>();
	ArrayList<String> Country_Name = new ArrayList<String>();
	/**
	 * @return the country_id
	 */
	public ArrayList<Integer> getCountry_id() {
		return Country_id;
	}
	/**
	 * @param country_id the country_id to set
	 */
	public void setCountry_id(ArrayList<Integer> country_id) {
		Country_id = country_id;
	}
	/**
	 * @return the country_Name
	 */
	public ArrayList<String> getCountry_Name() {
		return Country_Name;
	}
	/**
	 * @param country_Name the country_Name to set
	 */
	public void setCountry_Name(ArrayList<String> country_Name) {
		Country_Name = country_Name;
	}
}
