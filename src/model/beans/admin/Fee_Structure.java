package model.beans.admin;

import java.util.ArrayList;

public class Fee_Structure {
	ArrayList<Integer> fee_id = new ArrayList<Integer>();
	ArrayList<Integer> grade_id = new ArrayList<Integer>();
	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	ArrayList<Double> fee_Amount = new ArrayList<Double>();
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
	 * @return the grade_id
	 */
	public ArrayList<Integer> getGrade_id() {
		return grade_id;
	}
	/**
	 * @param grade_id the grade_id to set
	 */
	public void setGrade_id(ArrayList<Integer> grade_id) {
		this.grade_id = grade_id;
	}

	/**
	 * @return the school_year_id
	 */
	public ArrayList<Integer> getSchool_year_id() {
		return school_year_id;
	}
	/**
	 * @param school_year_id the school_year_id to set
	 */
	public void setSchool_year_id(ArrayList<Integer> school_year_id) {
		this.school_year_id = school_year_id;
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
	
}
