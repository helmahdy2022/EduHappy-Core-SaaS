package controllers.student;

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
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAssignment;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Assignment_Submission
 */
@WebServlet("/Manage_Assignment_Submission")
public class Manage_Assignment_Submission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Assignment_Submission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		long totalFilesSize = 1024*1024*10;
		long fileSize = 1024*1024*10;
		int fileQty = 10;
		
		ArrayList<String> uploadMessage = new ArrayList<String>();
		
        @SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
		String message=null;
		int give_Assignment_id= 0;
		if(request.getParameter("Give_Assignment_id")!=null)
		{
			give_Assignment_id = Integer.parseInt(request.getParameter("Give_Assignment_id"));	
		}		
		HttpSession session= request.getSession();
		int student_id= 0;
		if(session.getAttribute("Student_Session")!=null)
		{
			student_id=(Integer) session.getAttribute("Student_Session");
		}		
		String submission_Notes= request.getParameter("Submission_Notes");
		int updateQuery = 0;	
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);		
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getSchoolAccount(school_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		boolean isSubmissionExist = false;
		isSubmissionExist = serviceAssignment.checkSubmission(student_id, give_Assignment_id);
		if(isSubmissionExist==false)
		{
			updateQuery = serviceAssignment.createAssignmentSubmission(student_id, give_Assignment_id, submission_Notes);
			if(updateQuery!=0)
			{
				message = "Assignment submission created successfully";
			}
			else
			{
				message = "Failed to create Assignment submission";
			}
		}
		else
		{
			updateQuery = serviceAssignment.updateAssignmentSubmission(student_id, give_Assignment_id, submission_Notes);
			if(updateQuery!=0)
			{
				message = "Assignment submission updated successfully";
			}
			else
			{
				message = "Failed to update Assignment submission";
			}
		}		
		if(updateQuery!=0)
		{
			
    		///////////////////////////deal with file upload here////////////////////////////////////////////////
        	try {
				//uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "Students","AssignmentSubmissions", "Student_id", "Give_Assignment_id", student_id, give_Assignment_id, items, totalFilesSize, fileSize, fileQty);
        		uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "AssignmentSubmissions", "Students", "Give_Assignment_id", "Student_id", give_Assignment_id, student_id, items, totalFilesSize, fileSize, fileQty);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String address = null;
		address = "Student/Assignments/Display_Manage_Assignment_Submission.jsp?message="+message+"&&uploadMessage="+uploadMessage;
		request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
   		dispatcher.forward(request, response);	
	}

}
