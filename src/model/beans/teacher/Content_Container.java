package model.beans.teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class Content_Container implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
	HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap = new HashMap<String, ArrayList<ArrayList<String>>>();
	HashMap<String, ArrayList<ArrayList<String>>> contentunitmap = new HashMap<String, ArrayList<ArrayList<String>>>();
	int updateQuery;
	/**
	 * @return the updateQuery
	 */
	public int getUpdateQuery() {
		return updateQuery;
	}
	/**
	 * @param updateQuery the updateQuery to set
	 */
	public void setUpdateQuery(int updateQuery) {
		this.updateQuery = updateQuery;
	}
	/**
	 * @return the contentList
	 */
	public ArrayList<ArrayList<String>> getContentList() {
		return contentList;
	}
	/**
	 * @param contentList the contentList to set
	 */
	public void setContentList(ArrayList<ArrayList<String>> contentList) {
		this.contentList = contentList;
	}
	/**
	 * @return the contentcategorymap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getContentcategorymap() {
		return contentcategorymap;
	}
	/**
	 * @param contentcategorymap the contentcategorymap to set
	 */
	public void setContentcategorymap(
			HashMap<String, ArrayList<ArrayList<String>>> contentcategorymap) {
		this.contentcategorymap = contentcategorymap;
	}
	/**
	 * @return the contentunitmap
	 */
	public HashMap<String, ArrayList<ArrayList<String>>> getContentunitmap() {
		return contentunitmap;
	}
	/**
	 * @param contentunitmap the contentunitmap to set
	 */
	public void setContentunitmap(
			HashMap<String, ArrayList<ArrayList<String>>> contentunitmap) {
		this.contentunitmap = contentunitmap;
	}
}
