package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Year_Semester_Details;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Manage_Year_Semester
 */
@WebServlet("/Manage_Year_Semester")
public class Manage_Year_Semester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Year_Semester() {
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
			
			Year_Semester_Details year_Semester_Details = new Year_Semester_Details();
			year_Semester_Details.setSemester_id(semester_id);
			year_Semester_Details.setSemester_Name(semester_Name);
			year_Semester_Details.setSchool_Year_id(school_year_id);
			///////////////end populate the bean ///////////////////////
			if (semester_id==0)
			{
				updateQuery = semesterManagement.createYearSemester(year_Semester_Details, request);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Year Semester created successfully";
		        }
		        else
		        {
		        	if (updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Semester Name for the same School Year. Semester not created";
		        	}
		        	else
		        	{
		        		message = "New Year Semester was not created";
		        	}
		        }
			}
			else
			{
				updateQuery = semesterManagement.updateYearSemester(year_Semester_Details, semester_id, request);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Year Semseter updated successfully";
		    	else
		    	{
		    		if (updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Semester Name for the same School Year. Semster not updated";
		        	}
		        	else
		        	{
			    		message = "Year Semester was not updated";		        		
		        	}		    		
		    	}
			}
	
	    		
		}
		else
		{
			updateQuery = semesterManagement.deleteYearSemester(semester_id);
			if (updateQuery!=0)
				message = "Year Semester successfully deleted";
			else
				message = "Year Semester was not deleted";
		}
   		String address = "/Admin/Academic_Year/Display_Manage_Year_Semester.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
