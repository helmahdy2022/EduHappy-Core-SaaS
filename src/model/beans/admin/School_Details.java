package model.beans.admin;

public class School_Details {
	int School_id;
	String name;
	String City;
	int PO_Box;
	String Full_Address;
	String Website; 
	String Email;
	int Country_id;
	int Admin_id;
	int account_id;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the website
	 */
	public String getWebsite() {
		return Website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		Website = website;
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
	 * @return the admin_id
	 */
	public int getAdmin_id() {
		return Admin_id;
	}
	/**
	 * @param admin_id the admin_id to set
	 */	
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

}
