package model.beans.admin;

public class Expense_Types {
	String expense_Type_Name;
	String expense_Type_Description;
	int school_id;
	int debit_Account_id;
	int credit_Account_id;
	/**
	 * @return the expense_Type_Name
	 */
	public String getExpense_Type_Name() {
		return expense_Type_Name;
	}
	/**
	 * @param expense_Type_Name the expense_Type_Name to set
	 */
	public void setExpense_Type_Name(String expense_Type_Name) {
		this.expense_Type_Name = expense_Type_Name;
	}
	/**
	 * @return the expense_Type_Description
	 */
	public String getExpense_Type_Description() {
		return expense_Type_Description;
	}
	/**
	 * @param expense_Type_Description the expense_Type_Description to set
	 */
	public void setExpense_Type_Description(String expense_Type_Description) {
		this.expense_Type_Description = expense_Type_Description;
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
	/**
	 * @return the debit_Account_id
	 */
	public int getDebit_Account_id() {
		return debit_Account_id;
	}
	/**
	 * @param debit_Account_id the debit_Account_id to set
	 */
	public void setDebit_Account_id(int debit_Account_id) {
		this.debit_Account_id = debit_Account_id;
	}
	/**
	 * @return the credit_Account_id
	 */
	public int getCredit_Account_id() {
		return credit_Account_id;
	}
	/**
	 * @param credit_Account_id the credit_Account_id to set
	 */
	public void setCredit_Account_id(int credit_Account_id) {
		this.credit_Account_id = credit_Account_id;
	}
	
}
