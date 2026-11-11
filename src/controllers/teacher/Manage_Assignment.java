package controllers.teacher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;

import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.AssignmentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Assignment
 */
@WebServlet("/Manage_Assignment")
//@MultipartConfig(maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
/*@MultipartConfig(fileSizeThreshold=1024*1024*10,     
maxFileSize=1024*1024*1,          
maxRequestSize=1024*1024*100) */ 
public class Manage_Assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Assignment() {
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
		int assignment_id= 0;
		if(request.getParameter("Assignment_id")!=null)
		{
			assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));	
		}		
		HttpSession session= request.getSession();
		int teacher_id= 0;
		if(session.getAttribute("Teacher_Session")!=null)
		{
			teacher_id=(Integer) session.getAttribute("Teacher_Session");
		}
		
		int course_id=0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}

		String group_id = request.getParameter("Group_id");
		
		if(group_id==null || group_id.equals(""))
		{
			group_id ="''";
		}
		
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null && !request.getParameter("Lesson_id").equals("") && !request.getParameter("Lesson_id").equals("null"))
		{
			lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		}		
		int category_id=0;
		if(request.getParameter("Category_id")!=null && !request.getParameter("Category_id").equals("") && !request.getParameter("Category_id").equals("null"))
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		int max_Grade= 0;
		if(request.getParameter("Max_Grade")!=null && !request.getParameter("Max_Grade").equals(""))
		{
			max_Grade = Integer.parseInt(request.getParameter("Max_Grade"));
		}	
		int attempts_Allowed = -1;
		if(request.getParameter("Attempts_Allowed")!=null && !request.getParameter("Attempts_Allowed").equals(""))
		{
			attempts_Allowed = Integer.parseInt(request.getParameter("Attempts_Allowed"));
		}
		int duration = -1;
		if(request.getParameter("Duration")!=null && !request.getParameter("Duration").equals(""))
		{
			duration = Integer.parseInt(request.getParameter("Duration"));
		}	
		String subject= request.getParameter("Subject");		
		String text= request.getParameter("Text");
		String type = request.getParameter("type");
		int updateQuery = 0;
		int autoIncValue = -1;
		
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		//////////////////////////deal with file upload here////////////////////////////////////////////////
		if(max_Grade<=0)
		{
			message = "Assignment Max Grade must be greater than 0";
		}
		else
		{
			if(request.getParameter("submit_button")!=null && !request.getParameter("submit_button").equals("Delete Assignment"))
			{
				if (assignment_id==0 )
				{
					if(subject!=null)
					{
						autoIncValue = assignmentManagement.createAssignment(teacher_id, course_id, category_id, lesson_id, subject, text, max_Grade, attempts_Allowed, duration, type);
					}
					assignment_id = autoIncValue;
					if(assignment_id!=-1)
					{
						message = "Assignment created successfully";
					}
				}
				else
				{   					
					updateQuery = assignmentManagement.updateAssignment(teacher_id, assignment_id, course_id, category_id, lesson_id, subject, text, max_Grade, attempts_Allowed, duration);			
					if(updateQuery!=0)
					{
						message = "Assignment Updated Successfully";
								//+ "<a href='javascript:editAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+","+lesson_id+","+category_id+",\""+page+"\")'><font color='blue'>Back</font></a>";
					}											
				}
	    		///////////////////////////deal with file upload here////////////////////////////////////////////////
	    		if(assignment_id!=0 && assignment_id!=-1)
	    		{			    		
		        	try {
						uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "Assignments", null, "Assignment_id", null, assignment_id, -1, items, totalFilesSize, fileSize, fileQty);
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
				assignmentManagement.deleteAssignment(assignment_id);
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Assignments/" +assignment_id);
				FileUtils.deleteQuietly(directory);	 
				message="Assignment Successfully Deleted";					
			}
		}
		String address = null;
		address = "Teacher/Assignments/Display_Manage_Assignment.jsp?message="+message+"&&uploadMessage="+uploadMessage;
		request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
   		dispatcher.forward(request, response);	
	}	
}
