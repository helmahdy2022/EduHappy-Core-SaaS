package model.beans.student;

import java.util.ArrayList;
import java.util.HashMap;

public class Assignments {
	
	ArrayList<ArrayList<String>> CourseAssignments = new ArrayList<ArrayList<String>>();
	HashMap<String, ArrayList<ArrayList<String>>> AssignmentAttemptsmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	ArrayList<ArrayList<String>> AssignmentFiles = new ArrayList<ArrayList<String>>();	
	int Account_id;
	int School_id;

	/**
	 * @return the assignmentFiles
	 */
	public ArrayList<ArrayList<String>> getAssignmentFiles() {
		return AssignmentFiles;
	}

	/**
	 * @param assignmentFiles the assignmentFiles to set
	 */
	public void setAssignmentFiles(ArrayList<ArrayList<String>> assignmentFiles) {
		AssignmentFiles = assignmentFiles;
	}

	/**
	 * @return the courseAssignments
	 */
	public ArrayList<ArrayList<String>> getCourseAssignments() {
		return CourseAssignments;
	}

	/**
	 * @param courseAssignments the courseAssignments to set
	 */
	public void setCourseAssignments(ArrayList<ArrayList<String>> courseAssignments) {
		CourseAssignments = courseAssignments;
	}
	/**
	 * @return the assignmentAttemptsmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getAssignmentAttemptsmap() {
		return AssignmentAttemptsmap;
	}

	/**
	 * @param assignmentAttemptsmap the assignmentAttemptsmap to set
	 */
	public void setAssignmentAttemptsmap(
			HashMap<String, ArrayList<ArrayList<String>>> assignmentAttemptsmap) {
		AssignmentAttemptsmap = assignmentAttemptsmap;
	}
	
	public int getAccount_id() {
		return Account_id;
	}

	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}

	public int getSchool_id() {
		return School_id;
	}

	public void setSchool_id(int school_id) {
		School_id = school_id;
	}
}
