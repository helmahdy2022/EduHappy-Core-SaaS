package model.beans.teacher;

import java.util.ArrayList;

public class Book_Details {
	int Book_id;
	int Course_id;
	String Title;
	String ISBN ;
	String Author;
	String Publisher;
	String Pub_Date;
	String File;
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> fileExt = new ArrayList<String>();
	ArrayList<String> realFileName = new ArrayList<String>();
	ArrayList<Integer> File_id = new ArrayList<Integer>();
	ArrayList<String> fileCategory = new ArrayList<String>();

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
	 * @return the book_id
	 */
	public int getBook_id() {
		return Book_id;
	}
	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(int book_id) {
		Book_id = book_id;
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
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}
	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}
	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		Author = author;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return Publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	/**
	 * @return the pub_Date
	 */
	public String getPub_Date() {
		return Pub_Date;
	}
	/**
	 * @param pub_Date the pub_Date to set
	 */
	public void setPub_Date(String pub_Date) {
		Pub_Date = pub_Date;
	}
	/**
	 * @return the file
	 */
	public String getFile() {
		return File;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		File = file;
	}
	
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
}
