package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Details;
import model.dbHelpers.admin.GradeManagement;


/**
 * Servlet implementation class Manage_Grade
 */
@WebServlet("/Manage_Grade")
public class Manage_Grade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Grade() {
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
		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		String message = null;

		GradeManagement gradeManagement = new GradeManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			int school_id=0;
			if(request.getParameter("School_id")!=null)
			{
				school_id = Integer.parseInt(request.getParameter("School_id"));
			}
			int passing_Grade=0;
			if(request.getParameter("Passing_Grade")!=null)
			{
				passing_Grade = Integer.parseInt(request.getParameter("Passing_Grade"));
			}
			int max_Grade=0;
			if(request.getParameter("Max_Grade")!=null)
			{
				max_Grade = Integer.parseInt(request.getParameter("Max_Grade"));
			}
			int grade_Index=0;
			if(request.getParameter("Grade_Index")!=null)
			{
				grade_Index = Integer.parseInt(request.getParameter("Grade_Index"));
			}
			String grade_Name = request.getParameter("Grade_Name");
			int displayHideReport = 1;
			if(request.getParameter("DisplayHideReport")!=null && !request.getParameter("DisplayHideReport").equals(""))
			{
				displayHideReport = Integer.parseInt(request.getParameter("DisplayHideReport"));
			}
			///////////////populate the bean ///////////////////////
			
			Grade_Details grade_Details = new Grade_Details();
			grade_Details.setGrade_id(grade_id);
			grade_Details.setGrade_Index(grade_Index);
			grade_Details.setGrade_Name(grade_Name);
			grade_Details.setPassing_Grade(passing_Grade);
			grade_Details.setMax_Grade(max_Grade);
			grade_Details.setSchool_id(school_id);
			grade_Details.setDisplayHideReport(displayHideReport);
			///////////////end populate the bean ///////////////////////
			if (grade_id==0)
			{
				updateQuery = gradeManagement.createGrade(grade_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Grade created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for same Grade/Grade_Index under the same school. Grade not created";
		        	}
		        	else
		        	{
		        		message = "New Grade was not created";
		        	}
		        	
		        }
			}
			else
			{
				updateQuery = gradeManagement.updateGrade(grade_Details, grade_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Grade updated successfully";
		    	else
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for same Grade/Grade_Index under the same school. Grade not updated";
		        	}
		        	else
		        	{
		        		message = "Grade was not updated";
		        	}
		    		
			}
	
	    		
		}
		else
		{
			updateQuery = gradeManagement.deleteGrade(grade_id);
			if (updateQuery!=0)
				message = "Grade successfully deleted";
			else
				message = "Grade was not deleted";
		}
   		String address = "/Admin/Grades/Display_Manage_Grade.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	
	}

}
