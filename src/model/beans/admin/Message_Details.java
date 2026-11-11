package model.beans.admin;

public class Message_Details {

	int User_id;
	String User_Type;
	int Account_id;
	String Message_Body;
	String Account_Owner;
	int Group_Message;
	/**
	 * @return the group_Message
	 */
	public int getGroup_Message() {
		return Group_Message;
	}
	/**
	 * @param group_Message the group_Message to set
	 */
	public void setGroup_Message(int group_Message) {
		Group_Message = group_Message;
	}
	/**
	 * @return the account_Owner
	 */
	public String getAccount_Owner() {
		return Account_Owner;
	}
	/**
	 * @param account_Owner the account_Owner to set
	 */
	public void setAccount_Owner(String account_Owner) {
		Account_Owner = account_Owner;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return User_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	/**
	 * @return the user_Type
	 */
	public String getUser_Type() {
		return User_Type;
	}
	/**
	 * @param user_Type the user_Type to set
	 */
	public void setUser_Type(String user_Type) {
		User_Type = user_Type;
	}
	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return Account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}
	/**
	 * @return the message_Body
	 */
	public String getMessage_Body() {
		return Message_Body;
	}
	/**
	 * @param message_Body the message_Body to set
	 */
	public void setMessage_Body(String message_Body) {
		Message_Body = message_Body;
	}

}
