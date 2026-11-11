package model.dbHelpers.teacher;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.teacher.Book_Details;
import model.beans.teacher.Books;
import model.beans.teacher.Content_Container;
import model.beans.teacher.Lesson_Details;
import model.beans.teacher.Lessons_List;
import model.dbHelpers.DbConnector;

public class ContentManagement {
	public Books getBooksList(int course_id){
		Books Books = new Books();
		String query = "select * from Books where Course_id="+course_id;
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			ResultSet result = statement.executeQuery(query);

	    	ArrayList<Integer> book_id = new ArrayList<Integer>();
	    	ArrayList<String> title = new ArrayList<String>();
	    	ArrayList<String> iSBN = new ArrayList<String>();
	    	ArrayList<String> author = new ArrayList<String>();
	    	while (result.next())
			{
				book_id.add(result.getInt("Book_id"));
				title.add(result.getString("Title"));
				iSBN.add(result.getString("ISBN"));
				author.add(result.getString("Author"));
			}
			Books.setBook_id(book_id);
			Books.setTitle(title);
			Books.setISBN(iSBN);
			Books.setAuthor(author);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Books;
	}
	
	public Book_Details getBookDetails(int book_id,int course_id){
		Book_Details Book_Details = new Book_Details();
		String query = "select *, DATE_FORMAT(Pub_Date, '%Y-%m-%d') AS P_date from Books where Book_id="+book_id;
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			ResultSet result = statement.executeQuery(query);

	    	while (result.next())
	    	{
	    		Book_Details.setBook_id(result.getInt("Book_id"));
	    		Book_Details.setCourse_id(result.getInt("Course_id"));
	    		Book_Details.setTitle(result.getString("Title"));
	    		Book_Details.setISBN(result.getString("ISBN"));
	    		Book_Details.setAuthor(result.getString("Author"));
	    		Book_Details.setPublisher(result.getString("Publisher"));
	    		Book_Details.setPub_Date(result.getString("P_date"));	
	    		Book_Details.setFile(result.getString("File"));

	    	}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Book_Details;
	}
	
	public int createBook(Book_Details Book_Details) throws ParseException{
		String query = "INSERT INTO Books (Book_id, Course_id, Title, ISBN, Author, Publisher, Pub_Date, File) VALUES (Null, ?, ?, ?, ?, ?, ?, ?)";	
		int updateQuery = 0;
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
    			pstatement.setInt(1, Book_Details.getCourse_id());
				pstatement.setString(2, Book_Details.getTitle());
				pstatement.setString(3, Book_Details.getISBN());
				pstatement.setString(4, Book_Details.getAuthor());
				pstatement.setString(5, Book_Details.getPublisher());
				String Pub_Date = Book_Details.getPub_Date();
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				if(Pub_Date!=null && !Pub_Date.equals(""))
				{
					Date pub_Date = formater.parse(Pub_Date);
					pstatement.setDate(6,new java.sql.Date(pub_Date.getTime()));
				}
				else
				{
					pstatement.setNull(6, java.sql.Types.NULL);
				}
				pstatement.setString(7, Book_Details.getFile());
				updateQuery = pstatement.executeUpdate();
				if(updateQuery!=0)
				{
					ResultSet resultBook_id = pstatement.getGeneratedKeys();
					if(resultBook_id.next()) 
					{
					       autoIncValue = resultBook_id.getInt(1);
					       resultBook_id.close();
					}
				}    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}
	
	public int updateBook(Book_Details Book_Details, int book_id) throws ParseException{
		String query = "update Books set Course_id=?, Title=?, ISBN=?, Author=?, Publisher=?, Pub_Date=?, File=? where Book_id="+book_id;	
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
    			pstatement.setInt(1, Book_Details.getCourse_id());
				pstatement.setString(2, Book_Details.getTitle());
				pstatement.setString(3, Book_Details.getISBN());
				pstatement.setString(4, Book_Details.getAuthor());
				pstatement.setString(5, Book_Details.getPublisher());
				String Pub_Date = Book_Details.getPub_Date();
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				if(Pub_Date!=null && !Pub_Date.equals(""))
				{
					Date pub_Date = formater.parse(Pub_Date);
					pstatement.setDate(6,new java.sql.Date(pub_Date.getTime()));
				}
				else
				{
					pstatement.setNull(6, java.sql.Types.NULL);
				}
				pstatement.setString(7, Book_Details.getFile());
				updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteBook(int book_id){
		String query = "delete from Books where Book_id="+book_id;
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
				updateQuery = pstatement.executeUpdate();  
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Content_Container createUnit(int course_id, String unit_Name, Content_Container Content_Container){
		String query = "INSERT INTO Course_Units (Unit_id, Course_id, Unit_Name) VALUES (Null, ?, ?)";		
		int updateQuery = 0;
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
			pstatement.setInt(1, course_id);
			pstatement.setString(2, unit_Name);
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0)
			{
				ResultSet resultUnit_id = pstatement.getGeneratedKeys();
				if(resultUnit_id.next()) 
				{
				       autoIncValue = resultUnit_id.getInt(1);
				       resultUnit_id.close();
				}
				HashMap<String, ArrayList<ArrayList<String>>> contentunitmap = Content_Container.getContentunitmap();
				ArrayList<ArrayList<String>> unitList = (ArrayList<ArrayList<String>>) contentunitmap.get(String.valueOf(course_id));
				int n=0;
				if(unitList!=null)
				{
					n = unitList.size();
				}
				unitList.add(new ArrayList<String>());
				unitList.get(n).add(0,null);
				unitList.get(n).set(0, String.valueOf(autoIncValue));
				unitList.get(n).add(1,null);
				unitList.get(n).set(1, unit_Name);
				contentunitmap.put(String.valueOf(course_id), unitList);	
				Content_Container.setContentunitmap(contentunitmap);
				Content_Container.setUpdateQuery(updateQuery);
			}			
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Course_Unit_Unique"))
			{
				//Content_Container = new Content_Container();
				Content_Container.setUpdateQuery(-1);	
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}
	
	public Content_Container updateUnit(int unit_id, int course_id, String unit_Name, int i, Content_Container Content_Container){
		String query = "update Course_Units set Unit_Name=? where Unit_id="+unit_id;	
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
				pstatement.setString(1, unit_Name);
				updateQuery = pstatement.executeUpdate();   
				if(updateQuery!=0)
				{
					HashMap<String, ArrayList<ArrayList<String>>> contentunitmap = Content_Container.getContentunitmap();
					ArrayList<ArrayList<String>> unitList = (ArrayList<ArrayList<String>>) contentunitmap.get(String.valueOf(course_id));
					unitList.get(i-1).set(1, unit_Name);
					contentunitmap.put(String.valueOf(course_id), unitList);	
					Content_Container.setContentunitmap(contentunitmap);
					Content_Container.setUpdateQuery(updateQuery);
				}			
		} catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Course_Unit_Unique"))
			{
				//Content_Container = new Content_Container();
				Content_Container.setUpdateQuery(-1);	
			}		
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}
	
	public Content_Container deleteUnit(int unit_id, int course_id, int cell_no, Content_Container Content_Container){
		String query = "delete from Course_Units where Unit_id="+unit_id;
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
				updateQuery = pstatement.executeUpdate();  
				if(updateQuery!=0)
				{
					HashMap<String, ArrayList<ArrayList<String>>> contentunitmap = Content_Container.getContentunitmap();
					ArrayList<ArrayList<String>> unitList = (ArrayList<ArrayList<String>>) contentunitmap.get(String.valueOf(course_id));
					unitList.remove(cell_no-1);
					contentunitmap.put(String.valueOf(course_id), unitList);	
					Content_Container.setContentunitmap(contentunitmap);
					Content_Container.setUpdateQuery(updateQuery);
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Content_Container;
	}
	
	public int createLesson(Lesson_Details Lesson_Details) throws ParseException{
		String query = "INSERT INTO Lessons (Lesson_id, Unit_id, Lesson_Name, Description, Lesson_Start, Lesson_End, video1, video2, video3) VALUES (Null, ?, ?, ?, ?, ?,?,?,?)";	
		int updateQuery = 0;
		int autoIncValue=-1;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
    			pstatement.setInt(1, Lesson_Details.getUnit_id());
				pstatement.setString(2, Lesson_Details.getLesson_Name());
				pstatement.setString(3, Lesson_Details.getDescription());
				String Lesson_Start = Lesson_Details.getLesson_Start();
				String Lesson_End = Lesson_Details.getLesson_End();
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				Date start_Date = formater.parse(Lesson_Start);
				Date end_Date = formater.parse(Lesson_End);
				pstatement.setDate(4,new java.sql.Date(start_Date.getTime()));
				pstatement.setDate(5,new java.sql.Date(end_Date.getTime()));
				pstatement.setString(6, Lesson_Details.getVideo1());
				pstatement.setString(7, Lesson_Details.getVideo2());
				pstatement.setString(8, Lesson_Details.getVideo3());
				updateQuery = pstatement.executeUpdate();
				if(updateQuery!=0)
				{
					ResultSet resultLesson_id = pstatement.getGeneratedKeys();
					if(resultLesson_id.next()) 
					{
					       autoIncValue = resultLesson_id.getInt(1);
					       resultLesson_id.close();
					}
				}    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoIncValue;
	}
	public int updateLesson(Lesson_Details Lesson_Details) throws ParseException{
		String query = "update Lessons set Unit_id=?, Lesson_Name=?, Description=?, Lesson_Start=?, Lesson_End=?, Video1=?, Video2=?, Video3=? where Lesson_id="+Lesson_Details.getLesson_id();	
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
			PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setInt(1, Lesson_Details.getUnit_id());
			pstatement.setString(2, Lesson_Details.getLesson_Name());
			pstatement.setString(3, Lesson_Details.getDescription());
			String Lesson_Start = Lesson_Details.getLesson_Start();
			String Lesson_End = Lesson_Details.getLesson_End();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date start_Date = formater.parse(Lesson_Start);
			Date end_Date = formater.parse(Lesson_End);
			pstatement.setDate(4,new java.sql.Date(start_Date.getTime()));
			pstatement.setDate(5,new java.sql.Date(end_Date.getTime()));
			pstatement.setString(6, Lesson_Details.getVideo1());
			pstatement.setString(7, Lesson_Details.getVideo2());
			pstatement.setString(8, Lesson_Details.getVideo3());
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int deleteLesson(int lesson_id){
		String query = "delete from Lessons where Lesson_id="+lesson_id;
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
				updateQuery = pstatement.executeUpdate();  
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public Lessons_List getLessonsList(int unit_id){
		Lessons_List Lessons_List = new Lessons_List();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementLessons = connection.createStatement();){

	    	String sLessons = "select *, DATE_FORMAT(Lesson_Start, '%m/%d/%Y') AS LS_date , DATE_FORMAT(Lesson_End, '%m/%d/%Y') AS LE_date from Lessons where Unit_id="+unit_id+" order by Lesson_id";
	    	ResultSet resultLessons= statementLessons.executeQuery(sLessons);
	    	ArrayList<String> Lesson_Name = new ArrayList<String>();
	    	ArrayList<String> Lesson_id = new ArrayList<String>();
	    	ArrayList<String> Description = new ArrayList<String>();
	    	ArrayList<String> Lesson_Start = new ArrayList<String>();
	    	ArrayList<String> Lesson_End = new ArrayList<String>();
			if(resultLessons.isBeforeFirst())
			{
		    	while (resultLessons.next())
		    	{
		    		
		    		Lesson_Name.add(resultLessons.getString("Lesson_Name"));
		    		Lesson_id.add(resultLessons.getString("Lesson_id"));
		    		Description.add(resultLessons.getString("Description"));
		    		Lesson_Start.add(resultLessons.getString("LS_Date"));
		    		Lesson_End.add(resultLessons.getString("LE_Date"));

		    	}
	    		Lessons_List.setLesson_Name(Lesson_Name);
	    		Lessons_List.setLesson_id(Lesson_id);
	       		Lessons_List.setLesson_Start(Lesson_Start);
	       		Lessons_List.setLesson_End(Lesson_End);
	    		Lessons_List.setDescription(Description);
	    		Lessons_List.setUnit_id(unit_id);
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Lessons_List;
	}
	public Lesson_Details getLessonDetailst(int lesson_id){
		Lesson_Details Lesson_Details = new Lesson_Details();
		String query = "select *, DATE_FORMAT(Lesson_Start, '%m/%d/%Y') AS LS_date , DATE_FORMAT(Lesson_End, '%m/%d/%Y') AS LE_date from Lessons where Lesson_id="+lesson_id;
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

	    	
	    	ResultSet result= statement.executeQuery(query);
			while(result.next())
			{
				Lesson_Details.setLesson_id(lesson_id);
				Lesson_Details.setUnit_id(result.getInt("Unit_id"));
				Lesson_Details.setLesson_Name(result.getString("Lesson_Name"));
				Lesson_Details.setDescription(result.getString("Description"));
				Lesson_Details.setLesson_Start(result.getString("Lesson_Start"));
				Lesson_Details.setLesson_End(result.getString("Lesson_End"));
				Lesson_Details.setVideo1(result.getString("video1"));
				Lesson_Details.setVideo2(result.getString("video2"));
				Lesson_Details.setVideo3(result.getString("video3"));
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Lesson_Details;
	}
}
