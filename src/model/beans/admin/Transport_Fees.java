package model.beans.admin;

import java.util.ArrayList;

public class Transport_Fees {
	ArrayList<Integer> route_id = new ArrayList<Integer>();
	ArrayList<Integer> school_year_id = new ArrayList<Integer>();
	ArrayList<Double> transport_Fees = new ArrayList<Double>();
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
	
}
