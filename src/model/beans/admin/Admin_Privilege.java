package model.beans.admin;

import java.util.ArrayList;

public class Admin_Privilege {
	ArrayList<Integer> feature_id = new ArrayList<Integer>();

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
	
}
