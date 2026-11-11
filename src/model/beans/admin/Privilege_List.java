package model.beans.admin;

import java.util.ArrayList;

public class Privilege_List {
	ArrayList<Integer> feature_id = new ArrayList<Integer>();
	ArrayList<String> feature_Name = new ArrayList<String>();
	/**
	 * @return the feature_id
	 */
	public ArrayList<Integer> getFeature_id() {
		return feature_id;
	}
	/**
	 * @param feature_id the feature_id to set
	 */
	public void setFeature_id(ArrayList<Integer> feature_id) {
		this.feature_id = feature_id;
	}
	/**
	 * @return the feature_Name
	 */
	public ArrayList<String> getFeature_Name() {
		return feature_Name;
	}
	/**
	 * @param feature_Name the feature_Name to set
	 */
	public void setFeature_Name(ArrayList<String> feature_Name) {
		this.feature_Name = feature_Name;
	}
	
}
