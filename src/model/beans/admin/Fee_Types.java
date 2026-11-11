package model.beans.admin;

public class Fee_Types {
	String fee_Name;
	String fee_Description;
	String payment_Mode;
	int school_id;
	int debit_Account_id;
	int credit_Account_id;
	/**
	 * @return the fee_Name
	 */
	public String getFee_Name() {
		return fee_Name;
	}
	/**
	 * @param fee_Name the fee_Name to set
	 */
	public void setFee_Name(String fee_Name) {
		this.fee_Name = fee_Name;
	}
	/**
	 * @return the fee_Description
	 */
	public String getFee_Description() {
		return fee_Description;
	}
	/**
	 * @param fee_Description the fee_Description to set
	 */
	public void setFee_Description(String fee_Description) {
		this.fee_Description = fee_Description;
	}
	/**
	 * @return the payment_Mode
	 */
	public String getPayment_Mode() {
		return payment_Mode;
	}
	/**
	 * @param payment_Mode the payment_Mode to set
	 */
	public void setPayment_Mode(String payment_Mode) {
		this.payment_Mode = payment_Mode;
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
