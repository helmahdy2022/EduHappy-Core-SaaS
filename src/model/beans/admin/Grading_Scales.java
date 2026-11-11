package model.beans.admin;

import java.util.ArrayList;

public class Grading_Scales {
	ArrayList<Integer> Grading_Scale_id = new ArrayList<Integer>();
	ArrayList<String> letter = new ArrayList<String>();
	/**
	 * @return the grading_Scale_id
	 */
	public ArrayList<Integer> getGrading_Scale_id() {
		return Grading_Scale_id;
	}
	/**
	 * @param grading_Scale_id the grading_Scale_id to set
	 */
	public void setGrading_Scale_id(ArrayList<Integer> grading_Scale_id) {
		Grading_Scale_id = grading_Scale_id;
	}
	/**
	 * @return the letter
	 */
	public ArrayList<String> getLetter() {
		return letter;
	}
	/**
	 * @param letter the letter to set
	 */
	public void setLetter(ArrayList<String> letter) {
		this.letter = letter;
	}
}
