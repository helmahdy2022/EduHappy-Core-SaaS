package model.beans.admin;

import java.util.ArrayList;

public class Sessions_Details {
	ArrayList<ArrayList<String>> ExistingSessions = new ArrayList<ArrayList<String>>();
	boolean Session_Exists;
	int Session_qty;

	/**
	 * @return the existingSessions
	 */
	public ArrayList<ArrayList<String>> getExistingSessions() {
		return ExistingSessions;
	}
	/**
	 * @param existingSessions the existingSessions to set
	 */
	public void setExistingSessions(ArrayList<ArrayList<String>> existingSessions) {
		ExistingSessions = existingSessions;
	}
	/**
	 * @return the session_Exists
	 */
	public boolean isSession_Exists() {
		return Session_Exists;
	}
	/**
	 * @param session_Exists the session_Exists to set
	 */
	public void setSession_Exists(boolean session_Exists) {
		Session_Exists = session_Exists;
	}

	/**
	 * @return the session_qty
	 */
	public int getSession_qty() {
		return Session_qty;
	}
	/**
	 * @param session_qty the session_qty to set
	 */
	public void setSession_qty(int session_qty) {
		Session_qty = session_qty;
	}
}
