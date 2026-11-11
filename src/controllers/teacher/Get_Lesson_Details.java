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
import javax.servlet.http.HttpSession;

import model.beans.teacher.Lesson_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.teacher.ContentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Get_Lesson_Details
 */
@WebServlet("/Get_Lesson_Details")
public class Get_Lesson_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Lesson_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id = Integer.parseInt(request.getParameter("Course_id"));
		int lesson_id = Integer.parseInt(request.getParameter("Lesson_id"));
		Lesson_Details Lesson_Details = new Lesson_Details();
		ContentManagement contentManagement = new ContentManagement();
		Lesson_Details = contentManagement.getLessonDetailst(lesson_id);
		//////////////////////////Retrieve the Lesson files //////////////////////////////////////
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAccountID(course_id);
		int school_id = accountManagement.getSchoolID(course_id);
		CourseManagement courseManagement = new CourseManagement();
		int grade_id = courseManagement.getCourse(course_id).getGrade_id();
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Lessons/" +lesson_id);
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
				 realFileName.add(FileUploadManagement.getRealFileName("Lessons","Lesson_id", lesson_id, null, -1, list[i].getName()));
				 file_id.add(FileUploadManagement.getFile_id("Lessons","Lesson_id", lesson_id, null, -1, list[i].getName()));
				 ///////////////////////////Get file Category/////////////////////////////////////////////////////////////
				 String file_Category = FileUploadManagement.getFile_Category(fileExtension);
				 fileCategory.add(file_Category);
				 //////////////////////////////////////////////////////////////////////////////////////////////////////////
			 }
		 }	
		 Lesson_Details.setFileName(fileName);
		 Lesson_Details.setFileExt(fileExt);
		 Lesson_Details.setRealFileName(realFileName);
		 Lesson_Details.setFile_id(file_id);
		 Lesson_Details.setFileCategory(fileCategory);
		////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("Lesson_Details", Lesson_Details);
		String address = null;
		String Page = request.getParameter("Page");
		String Action = request.getParameter("Action");
		////////////////////////////////////For Parents ////////////////////////////////////////
		HttpSession session= request.getSession();
		int student_id = 0;
		String source = null;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			source = "Parent";
		}
		else
		{
			if(session.getAttribute("Student_Session")!=null)
			{
				student_id = (Integer) session.getAttribute("Student_Session");	
				source = "Student";
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////
		if (Page!=null&&Page.equals("Lesson")&&Action!=null&&Action.equals("Edit"))
		{
			address = "/Teacher/Course_Content/Display_Lesson.jsp?Lesson_id="+lesson_id+"&&Course_id="+course_id+"&&Account_id="+account_id+"&&School_id="+school_id+"&&Grade_id="+grade_id; 
		}
		else if(request.getParameter("User")!=null && request.getParameter("User").equals("Student"))
		{
			address = "/Student/Content/Display_Lesson.jsp?Account_id="+account_id+"&&School_id="+school_id+"&&Source="+source+"&&Student_id="+student_id;
		}
		else
		{
    		address = "/Teacher/Course_Content/Display_Lessons_List.jsp"; 			
		}
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
