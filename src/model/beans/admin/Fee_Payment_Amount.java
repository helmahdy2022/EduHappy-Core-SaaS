package model.beans.admin;

import java.util.ArrayList;
import java.util.Date;

public class Fee_Payment_Amount {
	ArrayList<Integer> fee_id = new ArrayList<Integer>();
	ArrayList<String> fee_Name = new ArrayList<String>();
	ArrayList<Double> fee_Amount = new ArrayList<Double>();
	ArrayList<Double> payment_Amount = new ArrayList<Double>();
	ArrayList<Double> payment_Discount = new ArrayList<Double>();
	ArrayList<Double> payment_Fine = new ArrayList<Double>();
	ArrayList<Double> due_Amount = new ArrayList<Double>();
	ArrayList<Boolean> allow_Entry = new ArrayList<Boolean>();
	ArrayList<Integer> receipt_id = new ArrayList<Integer>();
	ArrayList<Date> receipt_Date = new ArrayList<Date>();
	ArrayList<Integer> student_id = new ArrayList<Integer>();
	ArrayList<String> student_Name = new ArrayList<String>();
	ArrayList<ArrayList<String>> student_Payment_Amount = new ArrayList<ArrayList<String>>();
	ArrayList<Double> total_Payment = new ArrayList<Double>();
	double totalPayment;
	
	double TransportFees = 0.00;
	double paidTransportAmount = 0.00;
	double paymentTransportDiscount = 0.00;
	double paymentTransportFine = 0.00;
	double dueTransport = 0.00;
	int MostRecentRoute_id=0;
	/**
	 * @return the fee_id
	 */
	public ArrayList<Integer> getFee_id() {
		return fee_id;
	}
	/**
	 * @param fee_id the fee_id to set
	 */
	public void setFee_id(ArrayList<Integer> fee_id) {
		this.fee_id = fee_id;
	}

	/**
	 * @return the fee_Name
	 */
	public ArrayList<String> getFee_Name() {
		return fee_Name;
	}
	/**
	 * @param fee_Name the fee_Name to set
	 */
	public void setFee_Name(ArrayList<String> fee_Name) {
		this.fee_Name = fee_Name;
	}

	/**
	 * @return the fee_Amount
	 */
	public ArrayList<Double> getFee_Amount() {
		return fee_Amount;
	}
	/**
	 * @param fee_Amount the fee_Amount to set
	 */
	public void setFee_Amount(ArrayList<Double> fee_Amount) {
		this.fee_Amount = fee_Amount;
	}
	/**
	 * @return the payment_Amount
	 */
	public ArrayList<Double> getPayment_Amount() {
		return payment_Amount;
	}
	/**
	 * @param payment_Amount the payment_Amount to set
	 */
	public void setPayment_Amount(ArrayList<Double> payment_Amount) {
		this.payment_Amount = payment_Amount;
	}

	/**
	 * @return the payment_Discount
	 */
	public ArrayList<Double> getPayment_Discount() {
		return payment_Discount;
	}
	/**
	 * @param payment_Discount the payment_Discount to set
	 */
	public void setPayment_Discount(ArrayList<Double> payment_Discount) {
		this.payment_Discount = payment_Discount;
	}
	/**
	 * @return the payment_Fine
	 */
	public ArrayList<Double> getPayment_Fine() {
		return payment_Fine;
	}
	/**
	 * @param payment_Fine the payment_Fine to set
	 */
	public void setPayment_Fine(ArrayList<Double> payment_Fine) {
		this.payment_Fine = payment_Fine;
	}
	/**
	 * @return the due_Amount
	 */
	public ArrayList<Double> getDue_Amount() {
		return due_Amount;
	}
	/**
	 * @param due_Amount the due_Amount to set
	 */
	public void setDue_Amount(ArrayList<Double> due_Amount) {
		this.due_Amount = due_Amount;
	}
	/**
	 * @return the allow_Entry
	 */
	public ArrayList<Boolean> getAllow_Entry() {
		return allow_Entry;
	}
	/**
	 * @param allow_Entry the allow_Entry to set
	 */
	public void setAllow_Entry(ArrayList<Boolean> allow_Entry) {
		this.allow_Entry = allow_Entry;
	}
	/**
	 * @return the receipt_id
	 */
	public ArrayList<Integer> getReceipt_id() {
		return receipt_id;
	}
	/**
	 * @param receipt_id the receipt_id to set
	 */
	public void setReceipt_id(ArrayList<Integer> receipt_id) {
		this.receipt_id = receipt_id;
	}
	/**
	 * @return the receipt_Date
	 */
	public ArrayList<Date> getReceipt_Date() {
		return receipt_Date;
	}
	/**
	 * @param receipt_Date the receipt_Date to set
	 */
	public void setReceipt_Date(ArrayList<Date> receipt_Date) {
		this.receipt_Date = receipt_Date;
	}
	/**
	 * @return the student_id
	 */
	public ArrayList<Integer> getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(ArrayList<Integer> student_id) {
		this.student_id = student_id;
	}
	/**
	 * @return the student_Name
	 */
	public ArrayList<String> getStudent_Name() {
		return student_Name;
	}
	/**
	 * @param student_Name the student_Name to set
	 */
	public void setStudent_Name(ArrayList<String> student_Name) {
		this.student_Name = student_Name;
	}
	/**
	 * @return the totalPayment
	 */
	public double getTotalPayment() {
		return totalPayment;
	}
	/**
	 * @param totalPayment the totalPayment to set
	 */
	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}
	/**
	 * @return the student_Payment_Amount
	 */
	public ArrayList<ArrayList<String>> getStudent_Payment_Amount() {
		return student_Payment_Amount;
	}
	/**
	 * @param student_Payment_Amount the student_Payment_Amount to set
	 */
	public void setStudent_Payment_Amount(ArrayList<ArrayList<String>> student_Payment_Amount) {
		this.student_Payment_Amount = student_Payment_Amount;
	}
	/**
	 * @return the total_Payment
	 */
	public ArrayList<Double> getTotal_Payment() {
		return total_Payment;
	}
	/**
	 * @param total_Payment the total_Payment to set
	 */
	public void setTotal_Payment(ArrayList<Double> total_Payment) {
		this.total_Payment = total_Payment;
	}

	/**
	 * @return the transportFees
	 */
	public double getTransportFees() {
		return TransportFees;
	}
	/**
	 * @param transportFees the transportFees to set
	 */
	public void setTransportFees(double transportFees) {
		TransportFees = transportFees;
	}
	/**
	 * @return the paidTransportAmount
	 */
	public double getPaidTransportAmount() {
		return paidTransportAmount;
	}
	/**
	 * @param paidTransportAmount the paidTransportAmount to set
	 */
	public void setPaidTransportAmount(double paidTransportAmount) {
		this.paidTransportAmount = paidTransportAmount;
	}
	/**
	 * @return the paymentTransportDiscount
	 */
	public double getPaymentTransportDiscount() {
		return paymentTransportDiscount;
	}
	/**
	 * @param paymentTransportDiscount the paymentTransportDiscount to set
	 */
	public void setPaymentTransportDiscount(double paymentTransportDiscount) {
		this.paymentTransportDiscount = paymentTransportDiscount;
	}
	/**
	 * @return the paymentTransportFine
	 */
	public double getPaymentTransportFine() {
		return paymentTransportFine;
	}
	/**
	 * @param paymentTransportFine the paymentTransportFine to set
	 */
	public void setPaymentTransportFine(double paymentTransportFine) {
		this.paymentTransportFine = paymentTransportFine;
	}
	/**
	 * @return the dueTransport
	 */
	public double getDueTransport() {
		return dueTransport;
	}
	/**
	 * @param dueTransport the dueTransport to set
	 */
	public void setDueTransport(double dueTransport) {
		this.dueTransport = dueTransport;
	}
	/**
	 * @return the mostRecentRoute_id
	 */
	public int getMostRecentRoute_id() {
		return MostRecentRoute_id;
	}
	/**
	 * @param mostRecentRoute_id the mostRecentRoute_id to set
	 */
	public void setMostRecentRoute_id(int mostRecentRoute_id) {
		MostRecentRoute_id = mostRecentRoute_id;
	}

	
}
