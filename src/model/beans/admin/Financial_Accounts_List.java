package model.beans.admin;

import java.util.ArrayList;

public class Financial_Accounts_List {
	ArrayList<Integer> financial_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Account_Name = new ArrayList<String>();
	ArrayList<String> financial_Account_Description = new ArrayList<String>();
	ArrayList<Integer> financial_Category = new ArrayList<Integer>();
	int financial_Category_id;
	int school_id;
	/**
	 * @return the financial_Account_id
	 */
	public ArrayList<Integer> getFinancial_Account_id() {
		return financial_Account_id;
	}
	/**
	 * @param financial_Account_id the financial_Account_id to set
	 */
	public void setFinancial_Account_id(ArrayList<Integer> financial_Account_id) {
		this.financial_Account_id = financial_Account_id;
	}
	/**
	 * @return the financial_Account_Name
	 */
	public ArrayList<String> getFinancial_Account_Name() {
		return financial_Account_Name;
	}
	/**
	 * @param financial_Account_Name the financial_Account_Name to set
	 */
	public void setFinancial_Account_Name(ArrayList<String> financial_Account_Name) {
		this.financial_Account_Name = financial_Account_Name;
	}
	/**
	 * @return the financial_Account_Description
	 */
	public ArrayList<String> getFinancial_Account_Description() {
		return financial_Account_Description;
	}
	/**
	 * @param financial_Account_Description the financial_Account_Description to set
	 */
	public void setFinancial_Account_Description(ArrayList<String> financial_Account_Description) {
		this.financial_Account_Description = financial_Account_Description;
	}

	
	/**
	 * @return the financial_Category
	 */
	public ArrayList<Integer> getFinancial_Category() {
		return financial_Category;
	}
	/**
	 * @param financial_Category the financial_Category to set
	 */
	public void setFinancial_Category(ArrayList<Integer> financial_Category) {
		this.financial_Category = financial_Category;
	}
	/**
	 * @return the financial_Category_id
	 */
	public int getFinancial_Category_id() {
		return financial_Category_id;
	}
	/**
	 * @param financial_Category_id the financial_Category_id to set
	 */
	public void setFinancial_Category_id(int financial_Category_id) {
		this.financial_Category_id = financial_Category_id;
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
