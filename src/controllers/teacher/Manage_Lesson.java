package controllers.teacher;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import model.beans.teacher.Lesson_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.ContentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Lesson
 */
@WebServlet("/Manage_Lesson")
public class Manage_Lesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Lesson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		int unit_id= 0;
		if(request.getParameter("h_Unit_id")!=null)
		{
			unit_id = Integer.parseInt(request.getParameter("h_Unit_id"));
		}
		int course_id=0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null && !request.getParameter("Lesson_id").equals("") && !request.getParameter("Lesson_id").equals("null"))
		{
			lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		}		
		String lesson_Name= request.getParameter("Lesson_Name");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String lesson_Start= request.getParameter("Lesson_Start");
		String lesson_End= request.getParameter("Lesson_End");
		Date dLesson_Start =null;
		Date dLesson_End=null;
		try 
		{
			if(lesson_Start!=null)
			dLesson_Start = formater.parse(lesson_Start);
			if(lesson_End!=null)
			dLesson_End = formater.parse(lesson_End);
		} 
		catch (ParseException e1) 
		{
			e1.printStackTrace();
		}		
		String description= request.getParameter("Description");		
		String video1= request.getParameter("Video1");
		String video2= request.getParameter("Video2");
		String video3= request.getParameter("Video3");
		Lesson_Details Lesson_Details = new Lesson_Details();
		Lesson_Details.setLesson_id(lesson_id);
		Lesson_Details.setUnit_id(unit_id);
		Lesson_Details.setLesson_Name(lesson_Name);
		Lesson_Details.setDescription(description);
		Lesson_Details.setLesson_Start(lesson_Start);
		Lesson_Details.setLesson_End(lesson_End);
		Lesson_Details.setVideo1(video1);
		Lesson_Details.setVideo2(video2);
		Lesson_Details.setVideo3(video3);
		int updateQuery = 0;
		int autoIncValue = -1;

		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		ContentManagement contentManagement = new ContentManagement();
		if(request.getParameter("submit_button")!=null && !request.getParameter("submit_button").equals("Delete Lesson"))
		{
			if (dLesson_End.compareTo(dLesson_Start)>=0 )
			{
				if (lesson_id==0 )
				{
					try 
					{
						autoIncValue = contentManagement.createLesson(Lesson_Details);
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}
					if(autoIncValue!=-1)
					{
						message = "Lesson created successfully";
					}
				}
				else
				{
					try 
					{
						updateQuery = contentManagement.updateLesson(Lesson_Details);
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}
					if(updateQuery!=0)
					{
						message = "Updated Successfully <br>";			
					}			
				}
				//////////////////////////deal with file upload here////////////////////////////////////////////////
			    if (lesson_id==0 && autoIncValue!=-1)
			    {
				    lesson_id = autoIncValue;
			    }
			    try 
			    {
			    	uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "Lessons", null, "Lesson_id", null, lesson_id, -1, items, totalFilesSize, fileSize, fileQty);
			    } 
			    catch (IllegalStateException e) 
			    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    } 
			    catch (FileUploadException e) 
			    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			}
			else
			{
				message="Lesson End Date must be after Lesson Start Date";
			}
		}
		else
		{
			//Delete
			int updateQueryDelete = contentManagement.deleteLesson(lesson_id);
			if(updateQueryDelete!=0)
			{				
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Lessons/" +lesson_id);
				String[]entries = directory.list();
				for(String s: entries){
				    File currentFile = new File(directory.getPath(),s);
				    currentFile.delete();
				}
				directory.delete(); 	 
				message="Lesson Deleted Successfully";		
			}					
		}
	  
		String address = "Teacher/Course_Content/Manage_Lesson.jsp?message="+message+"&&uploadMessage="+uploadMessage;
		request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
   		dispatcher.forward(request, response);
	}
}
