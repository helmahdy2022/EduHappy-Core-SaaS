package controllers.teacher;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import model.beans.teacher.Book_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.ContentManagement;
import model.dbHelpers.teacher.FileUploadManagement;



/**
 * Servlet implementation class Manage_Book
 */
@WebServlet("/Manage_Book")  
public class Manage_Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		long totalFilesSize = 1024*1024*500;
		long fileSize = 1024*1024*100;
		int fileQty = 10;
		
		ArrayList<String> uploadMessage = new ArrayList<String>();
		
        @SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
        
		String message=null;	

    		int book_id= 0;
    		int course_id= 0;
    		String title = null;
    		String iSBN= null;
    		String author= null;
    		String publisher= null;	
    		String pub_Date= null;	
    		/////////////////////////////
            if(request.getParameter("Book_id")!=null)
            {
            	book_id = Integer.parseInt(request.getParameter("Book_id"));
            }
            
            if(request.getParameter("Course_id")!=null)
            {
            	course_id = Integer.parseInt(request.getParameter("Course_id"));
            }
            
            title = request.getParameter("Title");
            iSBN = request.getParameter("ISBN");
            author = request.getParameter("Author");
            publisher = request.getParameter("Publisher");
            pub_Date = request.getParameter("Pub_Date");     
    		////////////////////////////
			if(title!=null)
			{
	            Book_Details Book_Details = new Book_Details();
	    		Book_Details.setBook_id(book_id);
	    		Book_Details.setCourse_id(course_id);
	    		Book_Details.setTitle(title);
	    		Book_Details.setISBN(iSBN);
	    		Book_Details.setAuthor(author);
	    		Book_Details.setPublisher(publisher);
	    		Book_Details.setPub_Date(pub_Date);
	    		
	    		ContentManagement contentManagement = new ContentManagement();
	    		AccountManagement accountManagement = new AccountManagement();
	    		int account_id = accountManagement.getAccountID(course_id);
	    		int school_id = accountManagement.getSchoolID(course_id);
	    		int updateQuery = 0;
	    		int autoIncValue=-1;
	    		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
	    		if(request.getParameter("submit_button")!=null && !request.getParameter("submit_button").equals("Delete Book"))
	    		{
		    		if(book_id==0)
		    		{
		    			try 
		    			{
		    				autoIncValue = contentManagement.createBook(Book_Details);
		    			} catch (ParseException e) 
		    			{
		    				e.printStackTrace();
		    			}	

		    			book_id = autoIncValue;

		    			if(book_id!=-1)
		    			{
		    				message = "New eBook created Successfully";	
		    			}
		    			else
		    			{
		    				message = "Failed to create new eBook";
		    			}
		    		}
		    		else
		    		{
		    			try 
		    			{
		    				updateQuery = contentManagement.updateBook(Book_Details, book_id);
		    			} catch (ParseException e) 
		    			{
		    				e.printStackTrace();
		    			}
		    			if(updateQuery!=0)
		    			{
		    				message = "eBook updated Successfully";
		    			}
		    			else
		    			{
		    				message = "Failed to update the eBook";
		    			}
		    		} 
		    		///////////////////////////deal with file upload here////////////////////////////////////////////////
		    		if(book_id!=0 && book_id!=-1)
		    		{			    		
			        	try {
							uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "Books" , null,"book_id", null, book_id, -1, items, totalFilesSize, fileSize, fileQty);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileUploadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
	    		} 
	    		else
	    		{
	    			//Delete
	    			int updateQueryDelete = contentManagement.deleteBook(book_id);
	    			if(updateQueryDelete!=0)
	    			{				
	    				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Books/" +book_id);
	    				String[]entries = directory.list();
	    				for(String s: entries){
	    				    File currentFile = new File(directory.getPath(),s);
	    				    currentFile.delete();
	    				}
	    				directory.delete(); 	 
	    				message="Book Deleted Successfully";		
	    			}	
	    		}
			}
    		String address = "Teacher/Course_Content/Manage_Book.jsp?message="+message+"&&uploadMessage="+uploadMessage;
    		request.setAttribute("message", message);
    		request.setAttribute("uploadMessage", uploadMessage);
    		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	   		dispatcher.forward(request, response);
	}
}