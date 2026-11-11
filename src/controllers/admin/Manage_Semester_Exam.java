package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Semester_Exam_Details;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Manage_Semester_Exam
 */
@WebServlet("/Manage_Semester_Exam")
public class Manage_Semester_Exam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Semester_Exam() {
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
		HttpSession session= request.getSession();
		int updateQuery=0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		String semester_Source = (String) session.getAttribute("Semester_Source");
		int course_id = Integer.parseInt(request.getParameter("Course"));
		int semester_id = Integer.parseInt(request.getParameter("Semester_id"));
		int exam_Grade = Integer.parseInt(request.getParameter("Exam_Grade"));
		int exam_Contribution = Integer.parseInt(request.getParameter("Exam_Contribution"));
		Semester_Exam_Details semester_Exam_Details = new Semester_Exam_Details();

		SemesterManagement semesterManagement = new SemesterManagement();
    	////////////
		int exam_id=0;
		if(request.getParameter("Exam_id")!=null)
		{
			exam_id = Integer.parseInt(request.getParameter("Exam_id"));
		}
		String action=null;
		if(exam_Grade<=0)
		{
			message="Exam Grade must be greater than 0";
		}
		else
		{
			if(ConfirmDelete==null)
			{
				///////////////populate the bean ///////////////////////
				semester_Exam_Details.setCourse_id(course_id);
				semester_Exam_Details.setSemester_id(semester_id);
				semester_Exam_Details.setSemester_Source(semester_Source);
				semester_Exam_Details.setExam_Grade(exam_Grade);
				semester_Exam_Details.setExam_Contribution(exam_Contribution);
				///////////////end populate the bean ///////////////////////
				
				if (exam_id==0)
				{
					updateQuery = semesterManagement.createSemesterExam(semester_Exam_Details);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "New Semester Exam created successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
				        	message = "Duplicate Entry for the same Semester Exam for the same Course. Semester Exam not entered";
			        	}
			        	else
			        	{
				        	message = "Failed to create new Semester Exam";
			        	}
			        }
			        action="Create";
				}
				else
				{
					updateQuery = semesterManagement.updateSemesterExam(semester_Exam_Details, exam_id);
			    	if (updateQuery!=0 && updateQuery!=-1)
			    	{
			    		message = "Semseter Exam updated successfully";
			    	}
			    		
			    	else
			    	{
			    		if(updateQuery==-1)
			    		{
			    			message = "Duplicate Entry for the same Semester Exam for the same Course. Semester Exam not updated";
			    		}
			    		else
			    		{
				    		message = "Semester Exam was not updated";
			    		}
			    	}
			    	action="Update";

				}
		
		    		
			}
			else
			{
				updateQuery = semesterManagement.deleteSemesterExam(exam_id); 
				if (updateQuery!=0)
				{
					message = "Semester Exam successfully deleted";
				}
					
				else
				{
					message = "Semester Exam was not deleted";
				}
					
				action="Delete";
			}
		}		
		//////////
		
		
		
	    String address = "/Admin/Courses/Display_Manage_Semester_Exam.jsp?message="+message+"&&Action="+action;
	    RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);

	}

}
