package model.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;

public class Schools implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	ArrayList<String> School_Name = new ArrayList<String>();
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
}
