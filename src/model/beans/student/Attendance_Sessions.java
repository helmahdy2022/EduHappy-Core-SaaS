package model.beans.student;

import java.util.ArrayList;

public class Attendance_Sessions {
	ArrayList<ArrayList<String>> AttendanceList = new ArrayList<ArrayList<String>>();
	String Course_Name;

	/**
	 * @return the attendanceList
	 */
	public ArrayList<ArrayList<String>> getAttendanceList() {
		return AttendanceList;
	}

	/**
	 * @param attendanceList the attendanceList to set
	 */
	public void setAttendanceList(ArrayList<ArrayList<String>> attendanceList) {
		AttendanceList = attendanceList;
	}

	/**
	 * @return the course_Name
	 */
	public String getCourse_Name() {
		return Course_Name;
	}

	/**
	 * @param course_Name the course_Name to set
	 */
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	
}
