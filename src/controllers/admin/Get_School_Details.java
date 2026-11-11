package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.School_Weekend;
import model.beans.admin.School_Details;
import model.dbHelpers.admin.SchoolManagement;

/**
 * Servlet implementation class Get_School_Details
 */
@WebServlet("/Get_School_Details")
public class Get_School_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_School_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id = Integer.parseInt(request.getParameter("School_id"));
		String Print = request.getParameter("Print");
		/////////////////////////////Retrieve Schools///////////////////////////////
		SchoolManagement schoolManagement = new SchoolManagement();
    	School_Details school_Details = new School_Details();
    	school_Details = schoolManagement.getSchool(school_id);
	    /////////////////////////////End retrieve Schools///////////////////////////////
    	school_Details.setSchool_id(school_id);		
		request.setAttribute("School_Details", school_Details); 	
    	/////////////////////////////Retrieve School Weekends//////////
    	School_Weekend school_Weekend = new School_Weekend(); 
    	school_Weekend = schoolManagement.getSchoolWeekEnd(school_id);
    	request.setAttribute("School_Weekend", school_Weekend);
    	///////////////////////////////////////////////////////////////
    	
		String address=null;
		if (Print!=null)
   		{
   			address = "/Admin/Schools/Print_School.jsp";
   		}
   		else
   		{
   			address = "/Admin/Schools/Display_School_Details.jsp?School_id="+school_id;
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
