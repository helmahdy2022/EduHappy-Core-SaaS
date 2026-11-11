package controllers.student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.student.Assignments;
import model.beans.student.Submission_Files;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAssignment;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Get_One_Assignment
 */
@WebServlet("/Get_One_Assignment")
public class Get_One_Assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_One_Assignment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int student_id = 0;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
		}
		int assignment_id = 0;
		if(request.getParameter("Assignment_id")!=null && !request.getParameter("Assignment_id").equals(""))
		{
			assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));
		}
		int give_Assignment_id = 0;
		if(request.getParameter("Give_Assignment_id")!=null && !request.getParameter("Give_Assignment_id").equals(""))
		{
			give_Assignment_id = Integer.parseInt(request.getParameter("Give_Assignment_id"));
		}
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		Assignments Assignments = new Assignments();
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		Assignments = serviceAssignment.getAssignment(student_id, appPath, 0, 0, null, null);		
		request.setAttribute("Assignments", Assignments);
		
		int school_id = 0;
		int account_id = 0;
		boolean isSubmissionExist = false;
		isSubmissionExist = serviceAssignment.checkSubmission(student_id, give_Assignment_id);
		String submissionNotes = null;
		if(isSubmissionExist == true)
		{
			submissionNotes = serviceAssignment.getSubmissionNotes(student_id, give_Assignment_id);	

			//////////////////////////Retrieve the Assignment Submission files //////////////////////////////////////
			StudentManagement studentManagement = new StudentManagement();
			school_id = studentManagement.getStudentSchool(student_id);		
			AccountManagement accountManagement = new AccountManagement();
			account_id = accountManagement.getSchoolAccount(school_id);
			Submission_Files Submission_Files = new Submission_Files();
			File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/AssignmentSubmissions/" +give_Assignment_id+"/Students/"+student_id);
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
					 fileExt.add(fileExtension);
					 realFileName.add(FileUploadManagement.getRealFileName("AssignmentSubmissions","Give_Assignment_id", give_Assignment_id, "Student_id", student_id , list[i].getName()));
					 file_id.add(FileUploadManagement.getFile_id("AssignmentSubmissions","Give_Assignment_id", give_Assignment_id, "Student_id", student_id, list[i].getName()));
					 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
					 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
					 fileCategory.add(file_Category);
					 //////////////////////////////////////////////////////////////////////////////////////////////////////////
				 }
			 }	
			 Submission_Files.setFileName(fileName);
			 Submission_Files.setFileExt(fileExt);
			 Submission_Files.setRealFileName(realFileName);
			 Submission_Files.setFile_id(file_id);
			 Submission_Files.setFileCategory(fileCategory);
			////////////////////////////////////////////////////////////////////////////////////////
			request.setAttribute("Submission_Files", Submission_Files);
		
		
		}
		request.setAttribute("SubmissionNotes", submissionNotes);
		String address = "/Student/Assignments/Display_One_Assignment.jsp?School_id="+school_id+"&&Account_id="+account_id+"&&Student_id="+student_id+"&&Assignment_id="+assignment_id+"&&Give_Assignment_id="+give_Assignment_id+"&&IsSubmissionExist="+isSubmissionExist;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
