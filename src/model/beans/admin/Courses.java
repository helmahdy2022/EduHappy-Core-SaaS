package model.beans.admin;

import java.util.ArrayList;


public class Courses {
	ArrayList<Integer> Course_id = new ArrayList<Integer>();
	ArrayList<String> Course_Name = new ArrayList<String>();
	ArrayList<Integer> Course_Subject_id = new ArrayList<Integer>();
	ArrayList<Integer> Course_Division_id = new ArrayList<Integer>();
	ArrayList<Integer> Course_Price = new ArrayList<Integer>();
	ArrayList<Integer> course_Canceled = new ArrayList<Integer>();
	ArrayList<String> course_end_date = new ArrayList<String>();
	ArrayList<String> payment_renewal_date = new ArrayList<String>();
   	ArrayList<Integer> group_id = new ArrayList<Integer>();
	ArrayList<String> group_Name = new ArrayList<String>();
	/**
	 * @return the course_id
	 */
	public ArrayList<Integer> getCourse_id() {
		return Course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(ArrayList<Integer> course_id) {
		Course_id = course_id;
	}
	/**
	 * @return the course_Name
	 */
	public ArrayList<String> getCourse_Name() {
		return Course_Name;
	}
	/**
	 * @param course_Name the course_Name to set
	 */
	public void setCourse_Name(ArrayList<String> course_Name) {
		Course_Name = course_Name;
	}
	/**
	 * @return the course_Subject_id
	 */
	public ArrayList<Integer> getCourse_Subject_id() {
		return Course_Subject_id;
	}
	/**
	 * @param course_Subject_id the course_Subject_id to set
	 */
	public void setCourse_Subject_id(ArrayList<Integer> course_Subject_id) {
		Course_Subject_id = course_Subject_id;
	}
	/**
	 * @return the course_Division_id
	 */
	public ArrayList<Integer> getCourse_Division_id() {
		return Course_Division_id;
	}
	/**
	 * @param course_Division_id the course_Division_id to set
	 */
	public void setCourse_Division_id(ArrayList<Integer> course_Division_id) {
		Course_Division_id = course_Division_id;
	}
	public ArrayList<Integer> getCourse_Price() {
		return Course_Price;
	}
	public void setCourse_Price(ArrayList<Integer> course_Price) {
		Course_Price = course_Price;
	}
	public ArrayList<Integer> getCourse_Canceled() {
		return course_Canceled;
	}
	public void setCourse_Canceled(ArrayList<Integer> course_Canceled) {
		this.course_Canceled = course_Canceled;
	}
	public ArrayList<String> getCourse_end_date() {
		return course_end_date;
	}
	public void setCourse_end_date(ArrayList<String> course_end_date) {
		this.course_end_date = course_end_date;
	}
	public ArrayList<String> getPayment_renewal_date() {
		return payment_renewal_date;
	}
	public void setPayment_renewal_date(ArrayList<String> payment_renewal_date) {
		this.payment_renewal_date = payment_renewal_date;
	}
	public ArrayList<Integer> getGroup_id() {
		return group_id;
	}
	public void setGroup_id(ArrayList<Integer> group_id) {
		this.group_id = group_id;
	}
	public ArrayList<String> getGroup_Name() {
		return group_Name;
	}
	public void setGroup_Name(ArrayList<String> group_Name) {
		this.group_Name = group_Name;
	}

}
