package model.beans.teacher;

public class Assignment_Dates {
	String Display_Date = null;
	String Start_Date = null;
	String Due_Date = null;
	String Disappear_Date = null;
	int Online_Submission_Option=0;
	int Give_Assignment_id = 0;
	/**
	 * @return the display_Date
	 */
	public String getDisplay_Date() {
		return Display_Date;
	}
	/**
	 * @param display_Date the display_Date to set
	 */
	public void setDisplay_Date(String display_Date) {
		Display_Date = display_Date;
	}
	/**
	 * @return the start_Date
	 */
	public String getStart_Date() {
		return Start_Date;
	}
	/**
	 * @param start_Date the start_Date to set
	 */
	public void setStart_Date(String start_Date) {
		Start_Date = start_Date;
	}
	/**
	 * @return the due_Date
	 */
	public String getDue_Date() {
		return Due_Date;
	}
	/**
	 * @param due_Date the due_Date to set
	 */
	public void setDue_Date(String due_Date) {
		Due_Date = due_Date;
	}
	/**
	 * @return the disappear_Date
	 */
	public String getDisappear_Date() {
		return Disappear_Date;
	}
	/**
	 * @param disappear_Date the disappear_Date to set
	 */
	public void setDisappear_Date(String disappear_Date) {
		Disappear_Date = disappear_Date;
	}
	public int getOnline_Submission_Option() {
		return Online_Submission_Option;
	}
	public void setOnline_Submission_Option(int online_Submission_Option) {
		Online_Submission_Option = online_Submission_Option;
	}
	public int getGive_Assignment_id() {
		return Give_Assignment_id;
	}
	public void setGive_Assignment_id(int give_Assignment_id) {
		Give_Assignment_id = give_Assignment_id;
	}

}
