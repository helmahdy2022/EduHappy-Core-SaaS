package model.beans.admin;

import java.util.ArrayList;

public class Balance_Sheet {
	ArrayList<Integer> financial_Asset_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Asset_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Asset_Amount = new ArrayList<Double>();	
	double totalAssetAmount = 0.00;
	ArrayList<Integer> financial_Liabilities_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Liabilities_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Liabilities_Amount = new ArrayList<Double>();	
	double totalLiabilitiesAmount = 0.00;
	ArrayList<Integer> financial_Equity_Account_id = new ArrayList<Integer>();
	ArrayList<String> financial_Equity_Account_Name = new ArrayList<String>();
	ArrayList<Double> financial_Equity_Amount = new ArrayList<Double>();	
	double subTotalEquityAmount = 0.00;
	double totalEquityAmount = 0.00;
	double netIncome =0.00;
	double liabilityPlusEquity=0.00;
	/**
	 * @return the financial_Asset_Account_id
	 */
	public ArrayList<Integer> getFinancial_Asset_Account_id() {
		return financial_Asset_Account_id;
	}
	/**
	 * @param financial_Asset_Account_id the financial_Asset_Account_id to set
	 */
	public void setFinancial_Asset_Account_id(ArrayList<Integer> financial_Asset_Account_id) {
		this.financial_Asset_Account_id = financial_Asset_Account_id;
	}
	/**
	 * @return the financial_Asset_Account_Name
	 */
	public ArrayList<String> getFinancial_Asset_Account_Name() {
		return financial_Asset_Account_Name;
	}
	/**
	 * @param financial_Asset_Account_Name the financial_Asset_Account_Name to set
	 */
	public void setFinancial_Asset_Account_Name(ArrayList<String> financial_Asset_Account_Name) {
		this.financial_Asset_Account_Name = financial_Asset_Account_Name;
	}
	/**
	 * @return the financial_Asset_Amount
	 */
	public ArrayList<Double> getFinancial_Asset_Amount() {
		return financial_Asset_Amount;
	}
	/**
	 * @param financial_Asset_Amount the financial_Asset_Amount to set
	 */
	public void setFinancial_Asset_Amount(ArrayList<Double> financial_Asset_Amount) {
		this.financial_Asset_Amount = financial_Asset_Amount;
	}
	/**
	 * @return the totalAssetAmount
	 */
	public double getTotalAssetAmount() {
		return totalAssetAmount;
	}
	/**
	 * @param totalAssetAmount the totalAssetAmount to set
	 */
	public void setTotalAssetAmount(double totalAssetAmount) {
		this.totalAssetAmount = totalAssetAmount;
	}
	/**
	 * @return the financial_Liabilities_Account_id
	 */
	public ArrayList<Integer> getFinancial_Liabilities_Account_id() {
		return financial_Liabilities_Account_id;
	}
	/**
	 * @param financial_Liabilities_Account_id the financial_Liabilities_Account_id to set
	 */
	public void setFinancial_Liabilities_Account_id(ArrayList<Integer> financial_Liabilities_Account_id) {
		this.financial_Liabilities_Account_id = financial_Liabilities_Account_id;
	}
	/**
	 * @return the financial_Liabilities_Account_Name
	 */
	public ArrayList<String> getFinancial_Liabilities_Account_Name() {
		return financial_Liabilities_Account_Name;
	}
	/**
	 * @param financial_Liabilities_Account_Name the financial_Liabilities_Account_Name to set
	 */
	public void setFinancial_Liabilities_Account_Name(ArrayList<String> financial_Liabilities_Account_Name) {
		this.financial_Liabilities_Account_Name = financial_Liabilities_Account_Name;
	}
	/**
	 * @return the financial_Liabilities_Amount
	 */
	public ArrayList<Double> getFinancial_Liabilities_Amount() {
		return financial_Liabilities_Amount;
	}
	/**
	 * @param financial_Liabilities_Amount the financial_Liabilities_Amount to set
	 */
	public void setFinancial_Liabilities_Amount(ArrayList<Double> financial_Liabilities_Amount) {
		this.financial_Liabilities_Amount = financial_Liabilities_Amount;
	}
	/**
	 * @return the totalLiabilitiesAmount
	 */
	public double getTotalLiabilitiesAmount() {
		return totalLiabilitiesAmount;
	}
	/**
	 * @param totalLiabilitiesAmount the totalLiabilitiesAmount to set
	 */
	public void setTotalLiabilitiesAmount(double totalLiabilitiesAmount) {
		this.totalLiabilitiesAmount = totalLiabilitiesAmount;
	}
	/**
	 * @return the financial_Equity_Account_id
	 */
	public ArrayList<Integer> getFinancial_Equity_Account_id() {
		return financial_Equity_Account_id;
	}
	/**
	 * @param financial_Equity_Account_id the financial_Equity_Account_id to set
	 */
	public void setFinancial_Equity_Account_id(ArrayList<Integer> financial_Equity_Account_id) {
		this.financial_Equity_Account_id = financial_Equity_Account_id;
	}
	/**
	 * @return the financial_Equity_Account_Name
	 */
	public ArrayList<String> getFinancial_Equity_Account_Name() {
		return financial_Equity_Account_Name;
	}
	/**
	 * @param financial_Equity_Account_Name the financial_Equity_Account_Name to set
	 */
	public void setFinancial_Equity_Account_Name(ArrayList<String> financial_Equity_Account_Name) {
		this.financial_Equity_Account_Name = financial_Equity_Account_Name;
	}
	/**
	 * @return the financial_Equity_Amount
	 */
	public ArrayList<Double> getFinancial_Equity_Amount() {
		return financial_Equity_Amount;
	}
	/**
	 * @param financial_Equity_Amount the financial_Equity_Amount to set
	 */
	public void setFinancial_Equity_Amount(ArrayList<Double> financial_Equity_Amount) {
		this.financial_Equity_Amount = financial_Equity_Amount;
	}
	/**
	 * @return the subTotalEquityAmount
	 */
	public double getSubTotalEquityAmount() {
		return subTotalEquityAmount;
	}
	/**
	 * @param subTotalEquityAmount the subTotalEquityAmount to set
	 */
	public void setSubTotalEquityAmount(double subTotalEquityAmount) {
		this.subTotalEquityAmount = subTotalEquityAmount;
	}
	/**
	 * @return the totalEquityAmount
	 */
	public double getTotalEquityAmount() {
		return totalEquityAmount;
	}
	/**
	 * @param totalEquityAmount the totalEquityAmount to set
	 */
	public void setTotalEquityAmount(double totalEquityAmount) {
		this.totalEquityAmount = totalEquityAmount;
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
	/**
	 * @return the liabilityPlusEquity
	 */
	public double getLiabilityPlusEquity() {
		return liabilityPlusEquity;
	}
	/**
	 * @param liabilityPlusEquity the liabilityPlusEquity to set
	 */
	public void setLiabilityPlusEquity(double liabilityPlusEquity) {
		this.liabilityPlusEquity = liabilityPlusEquity;
	}


}
