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

import model.beans.teacher.Assignment_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.AssignmentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Get_Assignment_Details
 */
@WebServlet("/Get_Assignment_Details")
public class Get_Assignment_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Assignment_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		String page=request.getParameter("Page");
		int assignment_id= 0;
		if(request.getParameter("Assignment_id")!=null)
		{
			assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));
		}		
		String grade_id=request.getParameter("Grade_id");
		String class_id=request.getParameter("Class_id");
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		String group_id=request.getParameter("Group_id");
		String lesson_id=request.getParameter("Lesson_id");
		String category_id=request.getParameter("Category_id");
		String type=request.getParameter("type");
		
		//////////////////////////Retrieve the Assignment Subject and Instruction (Text) //////////////////
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		Assignment_Details Assignment_Details= new Assignment_Details();
		Assignment_Details = assignmentManagement.getAssignmentDetails(assignment_id);

		//////////////////////////Retrieve the book files //////////////////////////////////////
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Assignments/" +assignment_id);
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
				 realFileName.add(FileUploadManagement.getRealFileName("Assignments","Assignment_id", assignment_id, null, -1, list[i].getName()));
				 file_id.add(FileUploadManagement.getFile_id("Assignments","Assignment_id", assignment_id, null, -1, list[i].getName()));
				 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
				 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
				 fileCategory.add(file_Category);
				 //////////////////////////////////////////////////////////////////////////////////////////////////////////
			 }
		 }	
		 Assignment_Details.setFileName(fileName);
		 Assignment_Details.setFileExt(fileExt);
		 Assignment_Details.setRealFileName(realFileName);
		 Assignment_Details.setFile_id(file_id);
		 Assignment_Details.setFileCategory(fileCategory);
		////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("Assignment_Details", Assignment_Details);
		String address=null;
		if(page.equals("Give"))
		{
			address = "/List_Assignment_Dates?Assignment_id="+assignment_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page; 
			
		}
		if(page.equals("Edit")||page.equals("Edit_Standard")||page.equals("New") || page.equals("New_Standard"))
		{
			address = "/Teacher/Assignments/Display_Assignment.jsp?Assignment_id="+assignment_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page+"&&type="+type;
			
		}
		if(page.equals("Grade"))
		{
			address = "/Get_Grading?Assignment_id="+assignment_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id+"&&Class_id="+class_id+"&&Course_id="+course_id+"&&Group_id="+group_id+"&&Lesson_id="+lesson_id+"&&Category_id="+category_id+"&&Page="+page; 
			
		}
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
