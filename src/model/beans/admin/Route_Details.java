package model.beans.admin;

public class Route_Details {
	int Route_id;
	int Vehicle_id; 
	String Route_Name;
	int driver_id;
	int matron_id;
	int debit_Account_id;
	int credit_Account_id;
	/**
	 * @return the route_id
	 */
	public int getRoute_id() {
		return Route_id;
	}
	/**
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(int route_id) {
		Route_id = route_id;
	}
	/**
	 * @return the vehicle_id
	 */
	public int getVehicle_id() {
		return Vehicle_id;
	}
	/**
	 * @param vehicle_id the vehicle_id to set
	 */
	public void setVehicle_id(int vehicle_id) {
		Vehicle_id = vehicle_id;
	}
	/**
	 * @return the route_Name
	 */
	public String getRoute_Name() {
		return Route_Name;
	}
	/**
	 * @param route_Name the route_Name to set
	 */
	public void setRoute_Name(String route_Name) {
		Route_Name = route_Name;
	}

	/**
	 * @return the driver_id
	 */
	public int getDriver_id() {
		return driver_id;
	}
	/**
	 * @param driver_id the driver_id to set
	 */
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	/**
	 * @return the matron_id
	 */
	public int getMatron_id() {
		return matron_id;
	}
	/**
	 * @param matron_id the matron_id to set
	 */
	public void setMatron_id(int matron_id) {
		this.matron_id = matron_id;
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
