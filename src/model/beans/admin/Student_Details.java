package model.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;

public class Student_Details  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int Student_id = 0;
	String First_Name = null;
	String Last_Name = null;
	int PO_Box = 0;
	String Full_Address = null;
	String Email = null;
	String Tel=null;
	String Mobile=null;
	int Country_id = 0;
	String City;
	int Class_id = 0;
	int route_id = 0;
	String Username = null;
	String Password = null;
	String Gender = null;
	String Birth_Date;
	int Grade_id = 0;
	int School_id = 0;
	String fileName;
	int Account_id;
	String Class_Name;
	String Grade_Name;
	String School_Name;
	String Country_Name;
	int First_Login;
	String Route_Name;
	ArrayList<Integer> SelectedCourseList = new ArrayList<Integer>();
	int parent_id=0;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return Account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}
	/**
	 * @return the student_id
	 */
	public int getStudent_id() {
		return Student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(int student_id) {
		Student_id = student_id;
	}
	/**
	 * @return the first_Name
	 */
	public String getFirst_Name() {
		return First_Name;
	}
	/**
	 * @param first_Name the first_Name to set
	 */
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	/**
	 * @return the last_Name
	 */
	public String getLast_Name() {
		return Last_Name;
	}
	/**
	 * @param last_Name the last_Name to set
	 */
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	/**
	 * @return the pO_Box
	 */
	public int getPO_Box() {
		return PO_Box;
	}
	/**
	 * @param pO_Box the pO_Box to set
	 */
	public void setPO_Box(int pO_Box) {
		PO_Box = pO_Box;
	}
	/**
	 * @return the full_Address
	 */
	public String getFull_Address() {
		return Full_Address;
	}
	/**
	 * @param full_Address the full_Address to set
	 */
	public void setFull_Address(String full_Address) {
		Full_Address = full_Address;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	
	/**
	 * @return the tel
	 */
	public String getTel() {
		return Tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		Tel = tel;
	}
	
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return Mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	/**
	 * @return the country_id
	 */
	public int getCountry_id() {
		return Country_id;
	}
	/**
	 * @param country_id the country_id to set
	 */
	public void setCountry_id(int country_id) {
		Country_id = country_id;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
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
	 * @return the route_id
	 */
	public int getRoute_id() {
		return route_id;
	}
	/**
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return Gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		Gender = gender;
	}
	/**
	 * @return the birth_Date
	 */
	public String getBirth_Date() {
		return Birth_Date;
	}
	/**
	 * @param birth_Date the birth_Date to set
	 */
	public void setBirth_Date(String birth_Date) {
		Birth_Date = birth_Date;
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
	 * @return the class_Name
	 */
	public String getClass_Name() {
		return Class_Name;
	}
	/**
	 * @param class_Name the class_Name to set
	 */
	public void setClass_Name(String class_Name) {
		Class_Name = class_Name;
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
	 * @return the school_Name
	 */
	public String getSchool_Name() {
		return School_Name;
	}
	/**
	 * @param school_Name the school_Name to set
	 */
	public void setSchool_Name(String school_Name) {
		School_Name = school_Name;
	}
	/**
	 * @return the country_Name
	 */
	public String getCountry_Name() {
		return Country_Name;
	}
	/**
	 * @param country_Name the country_Name to set
	 */
	public void setCountry_Name(String country_Name) {
		Country_Name = country_Name;
	}
	/**
	 * @return the first_Login
	 */
	public int getFirst_Login() {
		return First_Login;
	}
	/**
	 * @param first_Login the first_Login to set
	 */
	public void setFirst_Login(int first_Login) {
		First_Login = first_Login;
	}
	/**
	 * @return the route_Name
	 */
	public String getRoute_Name() {
		return Route_Name;
	}
	/**
	 * @param route_Name the route_Name to set
	 */
	public void setRoute_Name(String route_Name) {
		Route_Name = route_Name;
	}

	public ArrayList<Integer> getSelectedCourseList() {
		return SelectedCourseList;
	}

	public void setSelectedCourseList(ArrayList<Integer> selectedCourseList) {
		SelectedCourseList = selectedCourseList;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

}
