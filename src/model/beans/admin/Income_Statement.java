package model.beans.admin;

import java.util.ArrayList;

public class Income_Statement {
	ArrayList<Integer> financial_Expenses_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Expenses_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Expenses_Amount = new ArrayList<Double>();	
	double totalExpensesAmount = 0.00;
	ArrayList<Integer> financial_Revenue_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Revenue_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Revenue_Amount = new ArrayList<Double>();		
	double totalRevenueAmount = 0.00;	
	double netIncome =0.00;
	/**
	 * @return the financial_Expenses_Account_id
	 */
	public ArrayList<Integer> getFinancial_Expenses_Account_id() {
		return financial_Expenses_Account_id;
	}
	/**
	 * @param financial_Expenses_Account_id the financial_Expenses_Account_id to set
	 */
	public void setFinancial_Expenses_Account_id(ArrayList<Integer> financial_Expenses_Account_id) {
		this.financial_Expenses_Account_id = financial_Expenses_Account_id;
	}
	/**
	 * @return the financial_Expenses_Account_Name
	 */
	public ArrayList<String> getFinancial_Expenses_Account_Name() {
		return financial_Expenses_Account_Name;
	}
	/**
	 * @param financial_Expenses_Account_Name the financial_Expenses_Account_Name to set
	 */
	public void setFinancial_Expenses_Account_Name(ArrayList<String> financial_Expenses_Account_Name) {
		this.financial_Expenses_Account_Name = financial_Expenses_Account_Name;
	}
	/**
	 * @return the financial_Expenses_Amount
	 */
	public ArrayList<Double> getFinancial_Expenses_Amount() {
		return financial_Expenses_Amount;
	}
	/**
	 * @param financial_Expenses_Amount the financial_Expenses_Amount to set
	 */
	public void setFinancial_Expenses_Amount(ArrayList<Double> financial_Expenses_Amount) {
		this.financial_Expenses_Amount = financial_Expenses_Amount;
	}
	/**
	 * @return the totalExpensesAmount
	 */
	public double getTotalExpensesAmount() {
		return totalExpensesAmount;
	}
	/**
	 * @param totalExpensesAmount the totalExpensesAmount to set
	 */
	public void setTotalExpensesAmount(double totalExpensesAmount) {
		this.totalExpensesAmount = totalExpensesAmount;
	}
	/**
	 * @return the financial_Revenue_Account_id
	 */
	public ArrayList<Integer> getFinancial_Revenue_Account_id() {
		return financial_Revenue_Account_id;
	}
	/**
	 * @param financial_Revenue_Account_id the financial_Revenue_Account_id to set
	 */
	public void setFinancial_Revenue_Account_id(ArrayList<Integer> financial_Revenue_Account_id) {
		this.financial_Revenue_Account_id = financial_Revenue_Account_id;
	}
	/**
	 * @return the financial_Revenue_Account_Name
	 */
	public ArrayList<String> getFinancial_Revenue_Account_Name() {
		return financial_Revenue_Account_Name;
	}
	/**
	 * @param financial_Revenue_Account_Name the financial_Revenue_Account_Name to set
	 */
	public void setFinancial_Revenue_Account_Name(ArrayList<String> financial_Revenue_Account_Name) {
		this.financial_Revenue_Account_Name = financial_Revenue_Account_Name;
	}
	/**
	 * @return the financial_Revenue_Amount
	 */
	public ArrayList<Double> getFinancial_Revenue_Amount() {
		return financial_Revenue_Amount;
	}
	/**
	 * @param financial_Revenue_Amount the financial_Revenue_Amount to set
	 */
	public void setFinancial_Revenue_Amount(ArrayList<Double> financial_Revenue_Amount) {
		this.financial_Revenue_Amount = financial_Revenue_Amount;
	}
	/**
	 * @return the totalRevenueAmount
	 */
	public double getTotalRevenueAmount() {
		return totalRevenueAmount;
	}
	/**
	 * @param totalRevenueAmount the totalRevenueAmount to set
	 */
	public void setTotalRevenueAmount(double totalRevenueAmount) {
		this.totalRevenueAmount = totalRevenueAmount;
	}
	/**
	 * @return the netIncome
	 */
	public double getNetIncome() {
		return netIncome;
	}
	/**
	 * @param netIncome the netIncome to set
	 */
	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
	
}
