package model.beans.admin;

import java.util.ArrayList;

public class Expenses_List {
	ArrayList<Integer> expense_id = new ArrayList<Integer>();
	ArrayList<String> expense_Date = new ArrayList<String>();
	ArrayList<String> reference_Number = new ArrayList<String>();
	ArrayList<String> expense_Type_Name = new ArrayList<String>();
	ArrayList<String> paid_To = new ArrayList<String>();
	ArrayList<Double> expense_Amount = new ArrayList<Double>();
	ArrayList<String> payment_Method = new ArrayList<String>();
	ArrayList<String> phone_Number = new ArrayList<String>();
	ArrayList<String> expense_Details = new ArrayList<String>();
	int expense_Type_id;
	/**
	 * @return the expense_id
	 */
	public ArrayList<Integer> getExpense_id() {
		return expense_id;
	}
	/**
	 * @param expense_id the expense_id to set
	 */
	public void setExpense_id(ArrayList<Integer> expense_id) {
		this.expense_id = expense_id;
	}
	/**
	 * @return the expense_Date
	 */
	public ArrayList<String> getExpense_Date() {
		return expense_Date;
	}
	/**
	 * @param expense_Date the expense_Date to set
	 */
	public void setExpense_Date(ArrayList<String> expense_Date) {
		this.expense_Date = expense_Date;
	}
	/**
	 * @return the reference_Number
	 */
	public ArrayList<String> getReference_Number() {
		return reference_Number;
	}
	/**
	 * @param reference_Number the reference_Number to set
	 */
	public void setReference_Number(ArrayList<String> reference_Number) {
		this.reference_Number = reference_Number;
	}

	/**
	 * @return the expense_Type_Name
	 */
	public ArrayList<String> getExpense_Type_Name() {
		return expense_Type_Name;
	}
	/**
	 * @param expense_Type_Name the expense_Type_Name to set
	 */
	public void setExpense_Type_Name(ArrayList<String> expense_Type_Name) {
		this.expense_Type_Name = expense_Type_Name;
	}
	/**
	 * @return the paid_To
	 */
	public ArrayList<String> getPaid_To() {
		return paid_To;
	}
	/**
	 * @param paid_To the paid_To to set
	 */
	public void setPaid_To(ArrayList<String> paid_To) {
		this.paid_To = paid_To;
	}
	/**
	 * @return the expense_Amount
	 */
	public ArrayList<Double> getExpense_Amount() {
		return expense_Amount;
	}
	/**
	 * @param expense_Amount the expense_Amount to set
	 */
	public void setExpense_Amount(ArrayList<Double> expense_Amount) {
		this.expense_Amount = expense_Amount;
	}
	/**
	 * @return the payment_Method
	 */
	public ArrayList<String> getPayment_Method() {
		return payment_Method;
	}
	/**
	 * @param payment_Method the payment_Method to set
	 */
	public void setPayment_Method(ArrayList<String> payment_Method) {
		this.payment_Method = payment_Method;
	}
	/**
	 * @return the phone_Number
	 */
	public ArrayList<String> getPhone_Number() {
		return phone_Number;
	}
	/**
	 * @param phone_Number the phone_Number to set
	 */
	public void setPhone_Number(ArrayList<String> phone_Number) {
		this.phone_Number = phone_Number;
	}
	/**
	 * @return the expense_Details
	 */
	public ArrayList<String> getExpense_Details() {
		return expense_Details;
	}
	/**
	 * @param expense_Details the expense_Details to set
	 */
	public void setExpense_Details(ArrayList<String> expense_Details) {
		this.expense_Details = expense_Details;
	}
	/**
	 * @return the expense_Type_id
	 */
	public int getExpense_Type_id() {
		return expense_Type_id;
	}
	/**
	 * @param expense_Type_id the expense_Type_id to set
	 */
	public void setExpense_Type_id(int expense_Type_id) {
		this.expense_Type_id = expense_Type_id;
	}
	
}
