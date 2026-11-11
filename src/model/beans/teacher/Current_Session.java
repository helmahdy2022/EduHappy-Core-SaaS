package model.beans.teacher;


public class Current_Session {

int Session_id;	
int Class_id;
int Course_id;
int Group_id;
int Grade_id;
String Time_From;
String Time_To;

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
 * @return the session_id
 */
public int getSession_id() {
	return Session_id;
}
/**
 * @param session_id the session_id to set
 */
public void setSession_id(int session_id) {
	Session_id = session_id;
}
/**
 * @return the class_id
 */
public int getClass_id() {
	return Class_id;
}
/**
 * @param class_id the class_id to set
 */
public void setClass_id(int class_id) {
	Class_id = class_id;
}
/**
 * @return the course_id
 */
public int getCourse_id() {
	return Course_id;
}
/**
 * @param course_id the course_id to set
 */
public void setCourse_id(int course_id) {
	Course_id = course_id;
}
/**
 * @return the group_id
 */
public int getGroup_id() {
	return Group_id;
}
/**
 * @param group_id the group_id to set
 */
public void setGroup_id(int group_id) {
	Group_id = group_id;
}


/**
 * @return the time_From
 */
public String getTime_From() {
	return Time_From;
}
/**
 * @param string the time_From to set
 */
public void setTime_From(String string) {
	Time_From = string;
}
/**
 * @return the time_To
 */
public String getTime_To() {
	return Time_To;
}
/**
 * @param string the time_To to set
 */
public void setTime_To(String string) {
	Time_To = string;
}

}
