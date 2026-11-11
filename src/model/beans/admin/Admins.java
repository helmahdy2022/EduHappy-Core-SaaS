package model.beans.admin;

import java.util.ArrayList;

public class Admins {
	ArrayList<Integer> Admin_id = new ArrayList<Integer>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	ArrayList<String> Username = new ArrayList<String>();
	int Account_id;
	/**
	 * @return the admin_id
	 */
	public ArrayList<Integer> getAdmin_id() {
		return Admin_id;
	}
	/**
	 * @param admin_id the admin_id to set
	 */
	public void setAdmin_id(ArrayList<Integer> admin_id) {
		Admin_id = admin_id;
	}
	/**
	 * @return the first_Name
	 */
	public ArrayList<String> getFirst_Name() {
		return First_Name;
	}
	/**
	 * @param first_Name the first_Name to set
	 */
	public void setFirst_Name(ArrayList<String> first_Name) {
		First_Name = first_Name;
	}
	/**
	 * @return the last_Name
	 */
	public ArrayList<String> getLast_Name() {
		return Last_Name;
	}
	/**
	 * @param last_Name the last_Name to set
	 */
	public void setLast_Name(ArrayList<String> last_Name) {
		Last_Name = last_Name;
	}
	/**
	 * @return the username
	 */
	public ArrayList<String> getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(ArrayList<String> username) {
		Username = username;
	}
	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return Account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}

}
