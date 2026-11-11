package model.beans.teacher;

import java.util.ArrayList;

public class Lessons_List {
	ArrayList<String> Lesson_Name = new ArrayList<String>();
	ArrayList<String> Lesson_id = new ArrayList<String>();
	ArrayList<String> Lesson_Start = new ArrayList<String>();
	ArrayList<String> Lesson_End = new ArrayList<String>();
	ArrayList<String> Description = new ArrayList<String>();
	int Unit_id;

	/**
	 * @return the lesson_Name
	 */
	public ArrayList<String> getLesson_Name() {
		return Lesson_Name;
	}
	/**
	 * @param lesson_Name the lesson_Name to set
	 */
	public void setLesson_Name(ArrayList<String> lesson_Name) {
		Lesson_Name = lesson_Name;
	}
	/**
	 * @return the lesson_id
	 */
	public ArrayList<String> getLesson_id() {
		return Lesson_id;
	}
	/**
	 * @param lesson_id the lesson_id to set
	 */
	public void setLesson_id(ArrayList<String> lesson_id) {
		Lesson_id = lesson_id;
	}
	/**
	 * @return the lesson_Start
	 */
	public ArrayList<String> getLesson_Start() {
		return Lesson_Start;
	}
	/**
	 * @param lesson_Start the lesson_Start to set
	 */
	public void setLesson_Start(ArrayList<String> lesson_Start) {
		Lesson_Start = lesson_Start;
	}
	/**
	 * @return the lesson_End
	 */
	public ArrayList<String> getLesson_End() {
		return Lesson_End;
	}
	/**
	 * @param lesson_End the lesson_End to set
	 */
	public void setLesson_End(ArrayList<String> lesson_End) {
		Lesson_End = lesson_End;
	}
	/**
	 * @return the description
	 */
	public ArrayList<String> getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(ArrayList<String> description) {
		Description = description;
	}
	/**
	 * @return the unit_id
	 */
	public int getUnit_id() {
		return Unit_id;
	}
	/**
	 * @param unit_id the unit_id to set
	 */
	public void setUnit_id(int unit_id) {
		Unit_id = unit_id;
	}
}
