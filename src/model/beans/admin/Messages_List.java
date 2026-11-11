package model.beans.admin;

import java.util.ArrayList;

public class Messages_List {
   	ArrayList<Integer> Message_id = new ArrayList<Integer>();
	ArrayList<String> Message_Body = new ArrayList<String>();
	ArrayList<Integer> Sender_id = new ArrayList<Integer>();
	ArrayList<Integer> Recepient_id = new ArrayList<Integer>();
	ArrayList<String> Message_Date = new ArrayList<String>();
	ArrayList<String> Message_Time = new ArrayList<String>();
	/**
	 * @return the sender_id
	 */
	public ArrayList<Integer> getSender_id() {
		return Sender_id;
	}
	/**
	 * @param sender_id the sender_id to set
	 */
	public void setSender_id(ArrayList<Integer> sender_id) {
		Sender_id = sender_id;
	}
	/**
	 * @return the recepient_id
	 */
	public ArrayList<Integer> getRecepient_id() {
		return Recepient_id;
	}
	/**
	 * @param recepient_id the recepient_id to set
	 */
	public void setRecepient_id(ArrayList<Integer> recepient_id) {
		Recepient_id = recepient_id;
	}
	/**
	 * @return the message_Date
	 */
	public ArrayList<String> getMessage_Date() {
		return Message_Date;
	}
	/**
	 * @param message_Date the message_Date to set
	 */
	public void setMessage_Date(ArrayList<String> message_Date) {
		Message_Date = message_Date;
	}
	/**
	 * @return the message_Time
	 */
	public ArrayList<String> getMessage_Time() {
		return Message_Time;
	}
	/**
	 * @param message_Time the message_Time to set
	 */
	public void setMessage_Time(ArrayList<String> message_Time) {
		Message_Time = message_Time;
	}
	/**
	 * @return the message_id
	 */
	public ArrayList<Integer> getMessage_id() {
		return Message_id;
	}
	/**
	 * @param message_id the message_id to set
	 */
	public void setMessage_id(ArrayList<Integer> message_id) {
		Message_id = message_id;
	}
	/**
	 * @return the message_Body
	 */
	public ArrayList<String> getMessage_Body() {
		return Message_Body;
	}
	/**
	 * @param message_Body the message_Body to set
	 */
	public void setMessage_Body(ArrayList<String> message_Body) {
		Message_Body = message_Body;
	}
}
