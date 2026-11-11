package model.beans.teacher;

import java.util.ArrayList;

public class Books {
ArrayList<Integer> Book_id = new ArrayList<Integer>();
ArrayList<String> Title = new ArrayList<String>();
ArrayList<String> ISBN = new ArrayList<String>();
ArrayList<String> Author = new ArrayList<String>();
/**
 * @return the book_id
 */
public ArrayList<Integer> getBook_id() {
	return Book_id;
}
/**
 * @param book_id the book_id to set
 */
public void setBook_id(ArrayList<Integer> book_id) {
	Book_id = book_id;
}
/**
 * @return the title
 */
public ArrayList<String> getTitle() {
	return Title;
}
/**
 * @param title the title to set
 */
public void setTitle(ArrayList<String> title) {
	Title = title;
}
public ArrayList<String> getISBN() {
	return ISBN;
}
public void setISBN(ArrayList<String> iSBN) {
	ISBN = iSBN;
}
public ArrayList<String> getAuthor() {
	return Author;
}
public void setAuthor(ArrayList<String> author) {
	Author = author;
}

}
