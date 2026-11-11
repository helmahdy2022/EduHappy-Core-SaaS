package model.beans.teacher;

import java.util.ArrayList;

public class Lesson_Details {
	int Lesson_id;
	int Unit_id;
	String Lesson_Name;
	String Lesson_Start;
	String Lesson_End;
	String Description;
	String Video1;
	String Video2;
	String Video3;
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> fileExt = new ArrayList<String>();
	ArrayList<String> realFileName = new ArrayList<String>();
	ArrayList<Integer> File_id = new ArrayList<Integer>();
	ArrayList<String> fileCategory = new ArrayList<String>();
	
	/**
	 * @return the realFileName
	 */
	public ArrayList<String> getRealFileName() {
		return realFileName;
	}
	/**
	 * @param realFileName the realFileName to set
	 */
	public void setRealFileName(ArrayList<String> realFileName) {
		this.realFileName = realFileName;
	}
	/**
	 * @return the file_id
	 */
	public ArrayList<Integer> getFile_id() {
		return File_id;
	}
	/**
	 * @param file_id the file_id to set
	 */
	public void setFile_id(ArrayList<Integer> file_id) {
		File_id = file_id;
	}
	/**
	 * @return the fileName
	 */
	public ArrayList<String> getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileExt
	 */
	public ArrayList<String> getFileExt() {
		return fileExt;
	}
	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(ArrayList<String> fileExt) {
		this.fileExt = fileExt;
	}
	/**
	 * @return the lesson_id
	 */
	public int getLesson_id() {
		return Lesson_id;
	}
	/**
	 * @param lesson_id the lesson_id to set
	 */
	public void setLesson_id(int lesson_id) {
		Lesson_id = lesson_id;
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
	/**
	 * @return the lesson_Name
	 */
	public String getLesson_Name() {
		return Lesson_Name;
	}
	/**
	 * @param lesson_Name the lesson_Name to set
	 */
	public void setLesson_Name(String lesson_Name) {
		Lesson_Name = lesson_Name;
	}
	/**
	 * @return the lesson_Start
	 */
	public String getLesson_Start() {
		return Lesson_Start;
	}
	/**
	 * @param lesson_Start the lesson_Start to set
	 */
	public void setLesson_Start(String lesson_Start) {
		Lesson_Start = lesson_Start;
	}
	/**
	 * @return the lesson_End
	 */
	public String getLesson_End() {
		return Lesson_End;
	}
	/**
	 * @param lesson_End the lesson_End to set
	 */
	public void setLesson_End(String lesson_End) {
		Lesson_End = lesson_End;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the fileCategory
	 */
	public ArrayList<String> getFileCategory() {
		return fileCategory;
	}
	/**
	 * @param fileCategory the fileCategory to set
	 */
	public void setFileCategory(ArrayList<String> fileCategory) {
		this.fileCategory = fileCategory;
	}
	public String getVideo1() {
		return Video1;
	}
	public void setVideo1(String video1) {
		Video1 = video1;
	}
	public String getVideo2() {
		return Video2;
	}
	public void setVideo2(String video2) {
		Video2 = video2;
	}
	public String getVideo3() {
		return Video3;
	}
	public void setVideo3(String video3) {
		Video3 = video3;
	}

}
