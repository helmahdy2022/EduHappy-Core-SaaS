package model.beans.admin;

import java.util.ArrayList;
import java.util.HashMap;

public class Daysoff_Schools_Grades {
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	ArrayList<String> School_Name = new ArrayList<String>();
	HashMap<String, ArrayList<ArrayList<String>>> Grademap = new HashMap<String, ArrayList<ArrayList<String>>>();
	/**
	 * @return the school_id
	 */
	public ArrayList<Integer> getSchool_id() {
		return School_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(ArrayList<Integer> school_id) {
		School_id = school_id;
	}
	/**
	 * @return the school_Name
	 */
	public ArrayList<String> getSchool_Name() {
		return School_Name;
	}
	/**
	 * @param school_Name the school_Name to set
	 */
	public void setSchool_Name(ArrayList<String> school_Name) {
		School_Name = school_Name;
	}
	/**
	 * @return the grademap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getGrademap() {
		return Grademap;
	}
	/**
	 * @param grademap the grademap to set
	 */
	public void setGrademap(HashMap<String, ArrayList<ArrayList<String>>> grademap) {
		Grademap = grademap;
	}
}
