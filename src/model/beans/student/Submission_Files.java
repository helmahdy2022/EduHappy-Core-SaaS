package model.beans.student;

import java.util.ArrayList;

public class Submission_Files {
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> fileExt = new ArrayList<String>();
	ArrayList<String> realFileName = new ArrayList<String>();
	ArrayList<Integer> file_id = new ArrayList<Integer>();
	ArrayList<String> fileCategory = new ArrayList<String>();
	String submissionNotes = null;
	public ArrayList<String> getFileName() {
		return fileName;
	}
	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}
	public ArrayList<String> getFileExt() {
		return fileExt;
	}
	public void setFileExt(ArrayList<String> fileExt) {
		this.fileExt = fileExt;
	}
	public ArrayList<String> getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(ArrayList<String> realFileName) {
		this.realFileName = realFileName;
	}
	public ArrayList<Integer> getFile_id() {
		return file_id;
	}
	public void setFile_id(ArrayList<Integer> file_id) {
		this.file_id = file_id;
	}
	public ArrayList<String> getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(ArrayList<String> fileCategory) {
		this.fileCategory = fileCategory;
	}
	public String getSubmissionNotes() {
		return submissionNotes;
	}
	public void setSubmissionNotes(String submissionNotes) {
		this.submissionNotes = submissionNotes;
	}
	
	
}
