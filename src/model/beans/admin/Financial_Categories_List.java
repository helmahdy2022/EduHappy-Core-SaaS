package model.beans.admin;

import java.util.ArrayList;

public class Financial_Categories_List {
	ArrayList<Integer> financial_Category_id = new ArrayList<Integer>();
	ArrayList<String> financial_Category_Name = new ArrayList<String>();
	/**
	 * @return the financial_Category_id
	 */
	public ArrayList<Integer> getFinancial_Category_id() {
		return financial_Category_id;
	}
	/**
	 * @param financial_Category_id the financial_Category_id to set
	 */
	public void setFinancial_Category_id(ArrayList<Integer> financial_Category_id) {
		this.financial_Category_id = financial_Category_id;
	}
	/**
	 * @return the financial_Category_Name
	 */
	public ArrayList<String> getFinancial_Category_Name() {
		return financial_Category_Name;
	}
	/**
	 * @param financial_Category_Name the financial_Category_Name to set
	 */
	public void setFinancial_Category_Name(ArrayList<String> financial_Category_Name) {
		this.financial_Category_Name = financial_Category_Name;
	}
	
}
