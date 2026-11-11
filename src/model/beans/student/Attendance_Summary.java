package model.beans.student;

import java.util.ArrayList;

public class Attendance_Summary {
	ArrayList<ArrayList<String>> AttendanceSummary = new ArrayList<ArrayList<String>>();

	/**
	 * @return the attendanceSummary
	 */
	public ArrayList<ArrayList<String>> getAttendanceSummary() {
		return AttendanceSummary;
	}

	/**
	 * @param attendanceSummary the attendanceSummary to set
	 */
	public void setAttendanceSummary(ArrayList<ArrayList<String>> attendanceSummary) {
		AttendanceSummary = attendanceSummary;
	}
}
