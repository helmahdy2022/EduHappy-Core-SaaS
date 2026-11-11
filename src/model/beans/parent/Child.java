package model.beans.parent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Child implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> student_id = new ArrayList<Integer>();
	ArrayList<String> first_Name = new ArrayList<String>();
	ArrayList<String> last_Name = new ArrayList<String>();
	HashMap<Integer, ArrayList<ArrayList<String>>> dueRenewal = new HashMap<Integer, ArrayList<ArrayList<String>>>(); 
	public ArrayList<Integer> getStudent_id() {
		return student_id;
	}
	public void setStudent_id(ArrayList<Integer> student_id) {
		this.student_id = student_id;
	}
	public ArrayList<String> getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(ArrayList<String> first_Name) {
		this.first_Name = first_Name;
	}
	public ArrayList<String> getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(ArrayList<String> last_Name) {
		this.last_Name = last_Name;
	}
	public HashMap<Integer, ArrayList<ArrayList<String>>> getDueRenewal() {
		return dueRenewal;
	}
	public void setDueRenewal(HashMap<Integer, ArrayList<ArrayList<String>>> dueRenewal) {
		this.dueRenewal = dueRenewal;
	}
}
