package model.beans.admin;

import java.util.ArrayList;

public class Groups {
	ArrayList<Integer> Group_id = new ArrayList<Integer>();
	ArrayList<String> Group_Name = new ArrayList<String>();
	/**
	 * @return the group_id
	 */
	public ArrayList<Integer> getGroup_id() {
		return Group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(ArrayList<Integer> group_id) {
		Group_id = group_id;
	}
	/**
	 * @return the group_Name
	 */
	public ArrayList<String> getGroup_Name() {
		return Group_Name;
	}
	/**
	 * @param group_Name the group_Name to set
	 */
	public void setGroup_Name(ArrayList<String> group_Name) {
		Group_Name = group_Name;
	}
}
