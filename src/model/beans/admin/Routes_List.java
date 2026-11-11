package model.beans.admin;

import java.util.ArrayList;

public class Routes_List {
	ArrayList<Integer> route_id = new ArrayList<Integer>();
	ArrayList<String> route_Name = new ArrayList<String>();
	int vehicle_id;
	int school_id;
	ArrayList<String> vehicle_Name = new ArrayList<String>();
	ArrayList<Integer> driver_id = new ArrayList<Integer>();
	ArrayList<String> driver_Name = new ArrayList<String>();
	ArrayList<Integer> matron_id = new ArrayList<Integer>();
	ArrayList<String> matron_Name = new ArrayList<String>();
	ArrayList<Integer> debit_Account = new ArrayList<Integer>();
	ArrayList<Integer> credit_Account = new ArrayList<Integer>();
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
	 * @return the route_Name
	 */
	public ArrayList<String> getRoute_Name() {
		return route_Name;
	}
	/**
	 * @param route_Name the route_Name to set
	 */
	public void setRoute_Name(ArrayList<String> route_Name) {
		this.route_Name = route_Name;
	}
	/**
	 * @return the vehicle_id
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}
	/**
	 * @param vehicle_id the vehicle_id to set
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
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
	 * @return the vehicle_Name
	 */
	public ArrayList<String> getVehicle_Name() {
		return vehicle_Name;
	}
	/**
	 * @param vehicle_Name the vehicle_Name to set
	 */
	public void setVehicle_Name(ArrayList<String> vehicle_Name) {
		this.vehicle_Name = vehicle_Name;
	}

	/**
	 * @return the driver_id
	 */
	public ArrayList<Integer> getDriver_id() {
		return driver_id;
	}
	/**
	 * @param driver_id the driver_id to set
	 */
	public void setDriver_id(ArrayList<Integer> driver_id) {
		this.driver_id = driver_id;
	}
	/**
	 * @return the driver_Name
	 */
	public ArrayList<String> getDriver_Name() {
		return driver_Name;
	}
	/**
	 * @param driver_Name the driver_Name to set
	 */
	public void setDriver_Name(ArrayList<String> driver_Name) {
		this.driver_Name = driver_Name;
	}
	/**
	 * @return the matron_id
	 */
	public ArrayList<Integer> getMatron_id() {
		return matron_id;
	}
	/**
	 * @param matron_id the matron_id to set
	 */
	public void setMatron_id(ArrayList<Integer> matron_id) {
		this.matron_id = matron_id;
	}
	/**
	 * @return the matron_Name
	 */
	public ArrayList<String> getMatron_Name() {
		return matron_Name;
	}
	/**
	 * @param matron_Name the matron_Name to set
	 */
	public void setMatron_Name(ArrayList<String> matron_Name) {
		this.matron_Name = matron_Name;
	}
	/**
	 * @return the debit_Account
	 */
	public ArrayList<Integer> getDebit_Account() {
		return debit_Account;
	}
	/**
	 * @param debit_Account the debit_Account to set
	 */
	public void setDebit_Account(ArrayList<Integer> debit_Account) {
		this.debit_Account = debit_Account;
	}
	/**
	 * @return the credit_Account
	 */
	public ArrayList<Integer> getCredit_Account() {
		return credit_Account;
	}
	/**
	 * @param credit_Account the credit_Account to set
	 */
	public void setCredit_Account(ArrayList<Integer> credit_Account) {
		this.credit_Account = credit_Account;
	}
	
}
