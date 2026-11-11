package model.beans.admin;

import java.util.ArrayList;

public class Teacher_Details {
	int payroll_id;
	int department_id;
	String designation;
	String joining_Date;
	double initial_Basic;
	
	int Teacher_id = 0;
	String First_Name = null;
	String Last_Name = null;
	int PO_Box = 0;
	String Full_Address = null;
	String Email = null;
	String Tel=null;
	String Mobile=null;
	int Country_id = 0;
	String City = null;
	String Username = null;
	String Password = null;
	String Gender = null;
	String Birth_Date = null;
	ArrayList<Integer> School_id = new ArrayList<Integer>();
	String fileName;
	int Account_id;
	String Country_Name;
	int First_Login;
	////////////////////////////////////////////////////////////////////

	/**
	 * @return the payroll_id
	 */
	public int getPayroll_id() {
		return payroll_id;
	}
	/**
	 * @param payroll_id the payroll_id to set
	 */
	public void setPayroll_id(int payroll_id) {
		this.payroll_id = payroll_id;
	}
	/**
	 * @return the department_id
	 */
	public int getDepartment_id() {
		return department_id;
	}
	/**
	 * @param department_id the department_id to set
	 */
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the joining_Date
	 */
	public String getJoining_Date() {
		return joining_Date;
	}
	/**
	 * @param joining_Date the joining_Date to set
	 */
	public void setJoining_Date(String joining_Date) {
		this.joining_Date = joining_Date;
	}
	/**
	 * @return the initial_Basic
	 */
	public double getInitial_Basic() {
		return initial_Basic;
	}
	/**
	 * @param initial_Basic the initial_Basic to set
	 */
	public void setInitial_Basic(double initial_Basic) {
		this.initial_Basic = initial_Basic;
	}	
	///////////////////////////////////////////////////////////////////
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
	 * @return the teacher_id
	 */
	public int getTeacher_id() {
		return Teacher_id;
	}
	/**
	 * @param teacher_id the teacher_id to set
	 */
	public void setTeacher_id(int teacher_id) {
		Teacher_id = teacher_id;
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
	 * @return the school_id
	 */
	public ArrayList<Integer> getSchool_id() {
		return School_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(ArrayList<Integer> school_id) {
		School_id = school_id;
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
	
}
