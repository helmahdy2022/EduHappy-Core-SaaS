package model.beans.admin;

public class Grade_Details {
int School_id;
int Grade_id;
String Grade_Name;
int Passing_Grade;
int Max_Grade;
int Grade_Index;
int DisplayHideReport;
/**
 * @return the passing_Grade
 */
public int getPassing_Grade() {
	return Passing_Grade;
}
/**
 * @param passing_Grade the passing_Grade to set
 */
public void setPassing_Grade(int passing_Grade) {
	Passing_Grade = passing_Grade;
}
/**
 * @return the school_id
 */
public int getSchool_id() {
	return School_id;
}
/**
 * @param school_id the school_id to set
 */
public void setSchool_id(int school_id) {
	School_id = school_id;
}
/**
 * @return the grade_id
 */
public int getGrade_id() {
	return Grade_id;
}
/**
 * @param grade_id the grade_id to set
 */
public void setGrade_id(int grade_id) {
	Grade_id = grade_id;
}
/**
 * @return the grade_Name
 */
public String getGrade_Name() {
	return Grade_Name;
}
/**
 * @param grade_Name the grade_Name to set
 */
public void setGrade_Name(String grade_Name) {
	Grade_Name = grade_Name;
}
/**
 * @return the max_Grade
 */
public int getMax_Grade() {
	return Max_Grade;
}
/**
 * @param max_Grade the max_Grade to set
 */
public void setMax_Grade(int max_Grade) {
	Max_Grade = max_Grade;
}
/**
 * @return the grade_Index
 */
public int getGrade_Index() {
	return Grade_Index;
}
/**
 * @param grade_Index the grade_Index to set
 */
public void setGrade_Index(int grade_Index) {
	Grade_Index = grade_Index;
}
public int getDisplayHideReport() {
	return DisplayHideReport;
}
public void setDisplayHideReport(int displayHideReport) {
	DisplayHideReport = displayHideReport;
}

}
