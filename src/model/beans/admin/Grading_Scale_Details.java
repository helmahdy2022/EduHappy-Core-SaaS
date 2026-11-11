package model.beans.admin;

public class Grading_Scale_Details {
int grading_Scale_id;
int grade_id;
int low;
int high;
String letter;
String description;
/**
 * @return the grading_Scale_id
 */
public int getGrading_Scale_id() {
	return grading_Scale_id;
}
/**
 * @param grading_Scale_id the grading_Scale_id to set
 */
public void setGrading_Scale_id(int grading_Scale_id) {
	this.grading_Scale_id = grading_Scale_id;
}
/**
 * @return the grade_id
 */
public int getGrade_id() {
	return grade_id;
}
/**
 * @param grade_id the grade_id to set
 */
public void setGrade_id(int grade_id) {
	this.grade_id = grade_id;
}
/**
 * @return the low
 */
public int getLow() {
	return low;
}
/**
 * @param low the low to set
 */
public void setLow(int low) {
	this.low = low;
}
/**
 * @return the high
 */
public int getHigh() {
	return high;
}
/**
 * @param high the high to set
 */
public void setHigh(int high) {
	this.high = high;
}
/**
 * @return the letter
 */
public String getLetter() {
	return letter;
}
/**
 * @param letter the letter to set
 */
public void setLetter(String letter) {
	this.letter = letter;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
}
