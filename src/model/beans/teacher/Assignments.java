package model.beans.teacher;

import java.util.ArrayList;

public class Assignments {
	ArrayList<String> Subject = new ArrayList<String>();
	ArrayList<Integer> Assignment_id = new ArrayList<Integer>();
	ArrayList<Integer> Standard = new ArrayList<Integer>();
	ArrayList<Integer> Max_Grade = new ArrayList<Integer>();
	/**
	 * @return the standard
	 */
	public ArrayList<Integer> getStandard() {
		return Standard;
	}
	/**
	 * @param standard the standard to set
	 */
	public void setStandard(ArrayList<Integer> standard) {
		Standard = standard;
	}
	/**
	 * @return the subject
	 */
	public ArrayList<String> getSubject() {
		return Subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(ArrayList<String> subject) {
		Subject = subject;
	}
	/**
	 * @return the assignment_id
	 */
	public ArrayList<Integer> getAssignment_id() {
		return Assignment_id;
	}
	/**
	 * @param assignment_id the assignment_id to set
	 */
	public void setAssignment_id(ArrayList<Integer> assignment_id) {
		Assignment_id = assignment_id;
	}
	public ArrayList<Integer> getMax_Grade() {
		return Max_Grade;
	}
	public void setMax_Grade(ArrayList<Integer> max_Grade) {
		Max_Grade = max_Grade;
	}

}
