package model.beans.admin;

import java.util.ArrayList;

public class Holidays_Details {
	ArrayList<ArrayList<String>> ExistingHolidays = new ArrayList<ArrayList<String>>();

	/**
	 * @return the existingHolidays
	 */
	public ArrayList<ArrayList<String>> getExistingHolidays() {
		return ExistingHolidays;
	}

	/**
	 * @param existingHolidays the existingHolidays to set
	 */
	public void setExistingHolidays(ArrayList<ArrayList<String>> existingHolidays) {
		ExistingHolidays = existingHolidays;
	}
}
