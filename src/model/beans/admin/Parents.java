package model.beans.admin;

import java.util.ArrayList;

public class Parents {
	ArrayList<Integer> Parent_id = new ArrayList<Integer>();
	ArrayList<String> Parent_First_Name = new ArrayList<String>();
	ArrayList<String> Parent_Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	ArrayList<Integer> Account_id = new ArrayList<Integer>();
	ArrayList<String> FileName = new ArrayList<String>();
	/**
	 * @return the parent_id
	 */
	public ArrayList<Integer> getParent_id() {
		return Parent_id;
	}
	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(ArrayList<Integer> parent_id) {
		Parent_id = parent_id;
	}
	/**
	 * @return the parent_First_Name
	 */
	public ArrayList<String> getParent_First_Name() {
		return Parent_First_Name;
	}
	/**
	 * @param parent_First_Name the parent_First_Name to set
	 */
	public void setParent_First_Name(ArrayList<String> parent_First_Name) {
		Parent_First_Name = parent_First_Name;
	}
	/**
	 * @return the parent_Last_Name
	 */
	public ArrayList<String> getParent_Last_Name() {
		return Parent_Last_Name;
	}
	/**
	 * @param parent_Last_Name the parent_Last_Name to set
	 */
	public void setParent_Last_Name(ArrayList<String> parent_Last_Name) {
		Parent_Last_Name = parent_Last_Name;
	}
	public ArrayList<String> getUsername() {
		return Username;
	}
	public void setUsername(ArrayList<String> username) {
		Username = username;
	}
	public ArrayList<Integer> getAccount_id() {
		return Account_id;
	}
	public void setAccount_id(ArrayList<Integer> account_id) {
		Account_id = account_id;
	}
	public ArrayList<String> getFileName() {
		return FileName;
	}
	public void setFileName(ArrayList<String> fileName) {
		FileName = fileName;
	}
	
}
