package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Semester_Details;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Manage_Grade_Semester
 */
@WebServlet("/Manage_Grade_Semester")
public class Manage_Grade_Semester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Grade_Semester() {
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
		int semester_id=0;
		if(request.getParameter("Semester_id")!=null)
		{
			semester_id = Integer.parseInt(request.getParameter("Semester_id"));
		}
		
		int grade_id=0;
		if(request.getParameter("Grade")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade"));
		}
		String message = null;

		SemesterManagement semesterManagement = new SemesterManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{

			int school_year_id=0;
			if(request.getParameter("School_Year_id")!=null)
			{
				school_year_id = Integer.parseInt(request.getParameter("School_Year_id"));
			}
			String semester_Name = request.getParameter("Semester_Name");

			///////////////populate the bean ///////////////////////
			
			Grade_Semester_Details grade_Semester_Details = new Grade_Semester_Details();
			grade_Semester_Details.setSemester_id(semester_id);
			grade_Semester_Details.setSemester_Name(semester_Name);
			grade_Semester_Details.setSchool_Year_id(school_year_id);
			grade_Semester_Details.setGrade_id(grade_id);
			///////////////end populate the bean ///////////////////////
			if (semester_id==0)
			{
				updateQuery = semesterManagement.createGradeSemester(grade_Semester_Details, request);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Grade Semester created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Grade Semester for the same Grade same School Year. Grade Semester not created";
		        	}
		        	else
		        	{
		        		message = "New Grade Semester was not created";
		        	}
		        	
		        }
			}
			else
			{
				updateQuery = semesterManagement.updateGradeSemester(grade_Semester_Details, semester_id, request);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    	{
		    		message = "Grade Semseter updated successfully";
		    	}		    		
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Grade Semester for the same Grade same School Year. Grade Semester not updated";
		        	}
		        	else
		        	{
			    		message = "Grade Semester was not updated"+updateQuery;
		        	}
		    	}
			}
	
	    		
		}
		else
		{
			updateQuery = semesterManagement.deleteGradeSemester(semester_id);
			if (updateQuery!=0)
				message = "Grade Semester successfully deleted";
			else
				message = "Grade Semester was not deleted";
		}
   		String address = "/Admin/Grades/Display_Manage_Grade_Semester.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
