package model.beans.admin;

public class Assignment_Category_Details {
	int Category_id;
	String Category_Name;
	int Category_Grade;
	int Contribute;
	int Create_Authority;
	int Course_id;
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
	 * @return the category_id
	 */
	public int getCategory_id() {
		return Category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		Category_id = category_id;
	}
	/**
	 * @return the category_Name
	 */
	public String getCategory_Name() {
		return Category_Name;
	}
	/**
	 * @param category_Name the category_Name to set
	 */
	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}
	/**
	 * @return the category_Grade
	 */
	public int getCategory_Grade() {
		return Category_Grade;
	}
	/**
	 * @param category_Grade the category_Grade to set
	 */
	public void setCategory_Grade(int category_Grade) {
		Category_Grade = category_Grade;
	}
	/**
	 * @return the contribute
	 */
	public int getContribute() {
		return Contribute;
	}
	/**
	 * @param contribute the contribute to set
	 */
	public void setContribute(int contribute) {
		Contribute = contribute;
	}
	/**
	 * @return the create_Authority
	 */
	public int getCreate_Authority() {
		return Create_Authority;
	}
	/**
	 * @param create_Authority the create_Authority to set
	 */
	public void setCreate_Authority(int create_Authority) {
		Create_Authority = create_Authority;
	}
}
