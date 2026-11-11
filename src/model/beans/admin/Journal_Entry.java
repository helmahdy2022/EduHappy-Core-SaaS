package model.beans.admin;

public class Journal_Entry {
	int entry_id;
	int school_id;
	int debit_Account_id;
	int credit_Account_id;
	String entry_Date;
	double journal_Amount;
	String journal_Details;
	/**
	 * @return the entry_id
	 */
	public int getEntry_id() {
		return entry_id;
	}
	/**
	 * @param entry_id the entry_id to set
	 */
	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
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
	/**
	 * @return the entry_Date
	 */
	public String getEntry_Date() {
		return entry_Date;
	}
	/**
	 * @param entry_Date the entry_Date to set
	 */
	public void setEntry_Date(String entry_Date) {
		this.entry_Date = entry_Date;
	}
	/**
	 * @return the journal_Amount
	 */
	public double getJournal_Amount() {
		return journal_Amount;
	}
	/**
	 * @param journal_Amount the journal_Amount to set
	 */
	public void setJournal_Amount(double journal_Amount) {
		this.journal_Amount = journal_Amount;
	}
	/**
	 * @return the journal_Details
	 */
	public String getJournal_Details() {
		return journal_Details;
	}
	/**
	 * @param journal_Details the journal_Details to set
	 */
	public void setJournal_Details(String journal_Details) {
		this.journal_Details = journal_Details;
	}

}
