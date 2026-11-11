package model.beans.teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class Authority_Container implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>> AuthorityList = new ArrayList<ArrayList<String>>();
	HashMap<String, ArrayList<ArrayList<String>>> Categorymap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> unitmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> semesterExamsmap = new HashMap<String, ArrayList<ArrayList<String>>>();


	/**
	 * @return the authorityList
	 */
	public ArrayList<ArrayList<String>> getAuthorityList() {
		return AuthorityList;
	}
	/**
	 * @param authorityList the authorityList to set
	 */
	public void setAuthorityList(ArrayList<ArrayList<String>> authorityList) {
		AuthorityList = authorityList;
	}
	/**
	 * @return the categorymap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getCategorymap() {
		return Categorymap;
	}
	/**
	 * @param categorymap the categorymap to set
	 */
	public void setCategorymap(
			HashMap<String, ArrayList<ArrayList<String>>> categorymap) {
		Categorymap = categorymap;
	}
	/**
	 * @return the unitmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getUnitmap() {
		return unitmap;
	}
	/**
	 * @param unitmap the unitmap to set
	 */
	public void setUnitmap(HashMap<String, ArrayList<ArrayList<String>>> unitmap) {
		this.unitmap = unitmap;
	}
	/**
	 * @return the semesterExamsmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getSemesterExamsmap() {
		return semesterExamsmap;
	}
	/**
	 * @param semesterExamsmap the semesterExamsmap to set
	 */
	public void setSemesterExamsmap(
			HashMap<String, ArrayList<ArrayList<String>>> semesterExamsmap) {
		this.semesterExamsmap = semesterExamsmap;
	}
}
