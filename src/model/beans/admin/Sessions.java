package model.beans.admin;

import java.sql.Time;

public class Sessions {
Time Time_From;
Time Time_To;
int Grade_id;
/**
 * @return the time_From
 */
public Time getTime_From() {
	return Time_From;
}
/**
 * @param time_From the time_From to set
 */
public void setTime_From(Time time_From) {
	Time_From = time_From;
}
/**
 * @return the time_To
 */
public Time getTime_To() {
	return Time_To;
}
/**
 * @param time_To the time_To to set
 */
public void setTime_To(Time time_To) {
	Time_To = time_To;
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
}
