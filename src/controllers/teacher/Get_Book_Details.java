package controllers.teacher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.teacher.Book_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.teacher.ContentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Get_Book_Details
 */
@WebServlet("/Get_Book_Details")
public class Get_Book_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Book_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int book_id = Integer.parseInt(request.getParameter("Book_id"));
		int course_id = Integer.parseInt(request.getParameter("Course_id"));
		Book_Details Book_Details = new Book_Details();
		ContentManagement contentManagement = new ContentManagement();
		Book_Details = contentManagement.getBookDetails(book_id, course_id);
		CourseManagement courseManagement = new CourseManagement();
		int grade_id = courseManagement.getCourse(course_id).getGrade_id();
		//////////////////////////Retrieve the book files //////////////////////////////////////
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Books/" +book_id);
		File[] list = jsp.listFiles();
		ArrayList<String> fileName = new ArrayList<String>();
		ArrayList<String> fileExt = new ArrayList<String>();
		ArrayList<String> realFileName = new ArrayList<String>();
		ArrayList<Integer> file_id = new ArrayList<Integer>();
		ArrayList<String> fileCategory = new ArrayList<String>();
		 if (list!=null)
		 {
			 for(int i=0;i<list.length;i++)
			 {
				 fileName.add(list[i].getName());
				 String fileExtension = list[i].getName().substring(list[i].getName().indexOf(".")+1);
				 fileExt.add(fileExtension) ;
				 realFileName.add(FileUploadManagement.getRealFileName("Books","Book_id", book_id, null, -1, list[i].getName()));
				 file_id.add(FileUploadManagement.getFile_id("Books","Book_id", book_id, null, -1, list[i].getName()));
				 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
				 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
				 fileCategory.add(file_Category);
				 //////////////////////////////////////////////////////////////////////////////////////////////////////////
			 }
		 }	
		Book_Details.setFileName(fileName);
		Book_Details.setFileExt(fileExt);
		Book_Details.setRealFileName(realFileName);
		Book_Details.setFile_id(file_id);
		Book_Details.setFileCategory(fileCategory);
		
		////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("Book_Details", Book_Details);
    	String address = "/Teacher/Course_Content/Edit_Book.jsp?Book_id="+book_id+"&&Course_id="+course_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id;
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
