package model.beans.teacher;

import java.util.ArrayList;

public class Teacher_Students {
	ArrayList<String> Student_id = new ArrayList<String>();
	ArrayList<String> First_Name = new ArrayList<String>();
	ArrayList<String> Last_Name = new ArrayList<String>();
	/**
	 * @return the student_id
	 */
	public ArrayList<String> getStudent_id() {
		return Student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(ArrayList<String> student_id) {
		Student_id = student_id;
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
}
