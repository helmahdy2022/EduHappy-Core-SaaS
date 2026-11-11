package model.beans.admin;

public class Financial_Account_Details {
	int Financial_Account_id;
	int Financial_Category_id; 
	String Financial_Account_Name;
	String Financial_Account_Description;
	int school_id;
	/**
	 * @return the financial_Account_id
	 */
	public int getFinancial_Account_id() {
		return Financial_Account_id;
	}
	/**
	 * @param financial_Account_id the financial_Account_id to set
	 */
	public void setFinancial_Account_id(int financial_Account_id) {
		Financial_Account_id = financial_Account_id;
	}
	/**
	 * @return the financial_Category_id
	 */
	public int getFinancial_Category_id() {
		return Financial_Category_id;
	}
	/**
	 * @param financial_Category_id the financial_Category_id to set
	 */
	public void setFinancial_Category_id(int financial_Category_id) {
		Financial_Category_id = financial_Category_id;
	}
	/**
	 * @return the financial_Account_Name
	 */
	public String getFinancial_Account_Name() {
		return Financial_Account_Name;
	}
	/**
	 * @param financial_Account_Name the financial_Account_Name to set
	 */
	public void setFinancial_Account_Name(String financial_Account_Name) {
		Financial_Account_Name = financial_Account_Name;
	}
	/**
	 * @return the financial_Account_Description
	 */
	public String getFinancial_Account_Description() {
		return Financial_Account_Description;
	}
	/**
	 * @param financial_Account_Description the financial_Account_Description to set
	 */
	public void setFinancial_Account_Description(String financial_Account_Description) {
		Financial_Account_Description = financial_Account_Description;
	}
	/**
	 * @return the school_id
	 */
	public int getSchool_id() {
		return school_id;
	}
	/**
	 * @param school_id the school_id to set
	 */
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	
}
