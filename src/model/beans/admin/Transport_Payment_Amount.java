package model.beans.admin;

import java.util.ArrayList;
import java.util.Date;

public class Transport_Payment_Amount {
	ArrayList<Integer> route_id = new ArrayList<Integer>();
	ArrayList<Double> transport_Fees = new ArrayList<Double>();
	ArrayList<Integer> receipt_id = new ArrayList<Integer>();
	ArrayList<Date> receipt_Date = new ArrayList<Date>();
	ArrayList<Double> payment_Transport_Amount = new ArrayList<Double>();
	ArrayList<Double> payment_Transport_Discount = new ArrayList<Double>();
	ArrayList<Double> payment_Transport_Fine = new ArrayList<Double>();
	ArrayList<Integer> student_id = new ArrayList<Integer>();
	ArrayList<String> student_Name = new ArrayList<String>();
	double totalPayment = 0.00;
	/**
	 * @return the route_id
	 */
	public ArrayList<Integer> getRoute_id() {
		return route_id;
	}
	/**
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(ArrayList<Integer> route_id) {
		this.route_id = route_id;
	}
	/**
	 * @return the transport_Fees
	 */
	public ArrayList<Double> getTransport_Fees() {
		return transport_Fees;
	}
	/**
	 * @param transport_Fees the transport_Fees to set
	 */
	public void setTransport_Fees(ArrayList<Double> transport_Fees) {
		this.transport_Fees = transport_Fees;
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
	 * @return the payment_Transport_Amount
	 */
	public ArrayList<Double> getPayment_Transport_Amount() {
		return payment_Transport_Amount;
	}
	/**
	 * @param payment_Transport_Amount the payment_Transport_Amount to set
	 */
	public void setPayment_Transport_Amount(ArrayList<Double> payment_Transport_Amount) {
		this.payment_Transport_Amount = payment_Transport_Amount;
	}
	/**
	 * @return the payment_Transport_Discount
	 */
	public ArrayList<Double> getPayment_Transport_Discount() {
		return payment_Transport_Discount;
	}
	/**
	 * @param payment_Transport_Discount the payment_Transport_Discount to set
	 */
	public void setPayment_Transport_Discount(ArrayList<Double> payment_Transport_Discount) {
		this.payment_Transport_Discount = payment_Transport_Discount;
	}
	/**
	 * @return the payment_Transport_Fine
	 */
	public ArrayList<Double> getPayment_Transport_Fine() {
		return payment_Transport_Fine;
	}
	/**
	 * @param payment_Transport_Fine the payment_Transport_Fine to set
	 */
	public void setPayment_Transport_Fine(ArrayList<Double> payment_Transport_Fine) {
		this.payment_Transport_Fine = payment_Transport_Fine;
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
	
}
