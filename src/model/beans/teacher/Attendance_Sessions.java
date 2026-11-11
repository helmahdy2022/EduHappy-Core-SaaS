package model.beans.teacher;

import java.util.ArrayList;

public class Attendance_Sessions {
	ArrayList<String> Session_id = new ArrayList<String>();
	ArrayList<String> Time_From = new ArrayList<String>();
	ArrayList<String> Time_To = new ArrayList<String>();
	int Session_Count;

	/**
	 * @return the session_id
	 */
	public ArrayList<String> getSession_id() {
		return Session_id;
	}
	/**
	 * @param session_id the session_id to set
	 */
	public void setSession_id(ArrayList<String> session_id) {
		Session_id = session_id;
	}
	/**
	 * @return the time_From
	 */
	public ArrayList<String> getTime_From() {
		return Time_From;
	}
	/**
	 * @param time_From the time_From to set
	 */
	public void setTime_From(ArrayList<String> time_From) {
		Time_From = time_From;
	}
	/**
	 * @return the time_To
	 */
	public ArrayList<String> getTime_To() {
		return Time_To;
	}
	/**
	 * @param time_To the time_To to set
	 */
	public void setTime_To(ArrayList<String> time_To) {
		Time_To = time_To;
	}
	/**
	 * @return the session_Count
	 */
	public int getSession_Count() {
		return Session_Count;
	}
	/**
	 * @param session_Count the session_Count to set
	 */
	public void setSession_Count(int session_Count) {
		Session_Count = session_Count;
	}
}
