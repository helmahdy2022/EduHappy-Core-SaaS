package controllers.teacher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.student.Submission_Files;
import model.beans.teacher.Assignment_Dates;
import model.beans.teacher.Student_List;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAssignment;
import model.dbHelpers.teacher.AssignmentManagement;
import model.dbHelpers.teacher.FileUploadManagement;
import model.dbHelpers.teacher.GradingManagement;

/**
 * Servlet implementation class Get_Grading
 */
@WebServlet("/Get_Grading")
public class Get_Grading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int assignment_id=Integer.parseInt(request.getParameter("Assignment_id"));
		int grade_id=Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		int lesson_id = 0;
		if(request.getParameter("Lesson_id")!=null && !request.getParameter("Lesson_id").equals("null"))
		{
			lesson_id=Integer.parseInt(request.getParameter("Lesson_id"));
		}
		int category_id=0;
		if(request.getParameter("Category_id")!=null && !request.getParameter("Category_id").equals("null"))
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		
		String page=request.getParameter("Page");
		
		Student_List Student_List = new Student_List();
		GradingManagement gradingManagement = new GradingManagement();
		Student_List = gradingManagement.getGrading(assignment_id, class_id, course_id, group_id);
		request.setAttribute("Students", Student_List);	

		Assignment_Dates Assignment_Dates= new Assignment_Dates();		
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		Assignment_Dates = assignmentManagement.getAssignmentDates(assignment_id, class_id, course_id, group_id);
		request.setAttribute("Assignment_Dates", Assignment_Dates);
		
		int give_Assignment_id = Assignment_Dates.getGive_Assignment_id();
		//////////////////Get Student submission files/////////////////////////////////////////////////
		int school_id = 0;
		int account_id = 0;
		int student_id=0;
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		StudentManagement studentManagement = new StudentManagement();
		AccountManagement accountManagement = new AccountManagement();
		ArrayList<ArrayList<String>> Student_List_Array = Student_List.getStudentList();
		HashMap<Integer, Submission_Files> Student_Submission = new HashMap<Integer, Submission_Files>();
	    for (int j = 0; j < Student_List_Array.size(); j++)
	    {
	        if(Student_List_Array.get(j).get(0)!=null)
	        {
	        	student_id = Integer.parseInt(Student_List_Array.get(j).get(0));
	        }
	        String submissionNotes = null;
	        submissionNotes = serviceAssignment.getSubmissionNotes(student_id, give_Assignment_id);	
	        school_id = studentManagement.getStudentSchool(student_id);		
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
			 Submission_Files.setSubmissionNotes(submissionNotes);
			 Submission_Files.setFileName(fileName);
			 Submission_Files.setFileExt(fileExt);
			 Submission_Files.setRealFileName(realFileName);
			 Submission_Files.setFile_id(file_id);
			 Submission_Files.setFileCategory(fileCategory);		
			///////////////////////////////////////////////////////////////////////////////////////////////
			 Student_Submission.put(student_id, Submission_Files);
	    } 	
		request.setAttribute("Student_Submission", Student_Submission);
		request.setAttribute("School_id", school_id);
		request.setAttribute("Account_id", account_id);
		request.setAttribute("Give_Assignment_id", give_Assignment_id);
		String address = "/Teacher/Assignments/Grade_Assignment.jsp?Assignment_id="+assignment_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page; 
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
 		return;	 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
