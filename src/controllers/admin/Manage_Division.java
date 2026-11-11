package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Division_Details;
import model.dbHelpers.admin.DivisionManagement;

/**
 * Servlet implementation class Manage_Division
 */
@WebServlet("/Manage_Division")
public class Manage_Division extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Division() {
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
		int division_id=0;
		if(request.getParameter("Division_id")!=null)
		{
			division_id = Integer.parseInt(request.getParameter("Division_id"));
		}
		String message = null;

		DivisionManagement divisionManagement = new DivisionManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			//int school_id = 0;
			//if(request.getParameter("School_id")!=null)
			//{
			//	school_id = Integer.parseInt(request.getParameter("School_id"));
			//}
			
			int grade_id=0;
			if(request.getParameter("Grade")!=null)
			{
				grade_id = Integer.parseInt(request.getParameter("Grade"));
			}
			String division_Name = request.getParameter("Division_Name");

			///////////////populate the bean ///////////////////////
			
			Division_Details division_Details = new Division_Details();
			division_Details.setDivision_id(division_id);
			division_Details.setDivision_Name(division_Name);
			division_Details.setGrade_id(grade_id);
			///////////////end populate the bean ///////////////////////
			if (division_id==0)
			{
				updateQuery = divisionManagement.createDivision(division_Details, request);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Division created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entery for the Division for the same Grade. Division was not created";
		        	}
		        	else
		        	{
		        		message = "New Division was not created";
		        	}
		        }
			}
			else
			{
				updateQuery = divisionManagement.updateDivision(division_Details, division_id, request);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    	{
		    		message = "Division updated successfully";
		    	}

		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entery for the Division for the same Grade. Division was not updated";
		        	}
		        	else
		        	{
		        		message = "Division was not updated";
		        	}
		    	}		    		
			}
	
	    		
		}
		else
		{
			updateQuery = divisionManagement.deleteDivision(division_id);
			if (updateQuery!=0)
				message = "Division successfully deleted";
			else
				message = "Division was not deleted";
		}
   		String address = "/Admin/Divisions/Display_Manage_Division.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
