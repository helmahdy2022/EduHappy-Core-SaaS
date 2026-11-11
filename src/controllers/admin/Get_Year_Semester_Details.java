package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Year_Semester_Details;
import model.beans.admin.Year_Terms;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Get_Year_Semester_Details
 */
@WebServlet("/Get_Year_Semester_Details")
public class Get_Year_Semester_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Year_Semester_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int semester_id=0;
		if(request.getParameter("Semester_id")!=null)
		{
			semester_id = Integer.parseInt(request.getParameter("Semester_id"));
		}
		SemesterManagement semesterManagement = new SemesterManagement();
		Year_Semester_Details Year_Semester_Details = new Year_Semester_Details();
		Year_Semester_Details = semesterManagement.getYearSemester(semester_id);
		Year_Terms Year_Terms = new Year_Terms();
		Year_Terms = semesterManagement.getYearSemesterTerms(Year_Semester_Details.getSchool_Year_id(), semester_id);
    	
		request.setAttribute("Year_Semester_Details", Year_Semester_Details);
		request.setAttribute("Year_Terms", Year_Terms);
		
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Academic_Year/Print_Semester.jsp";
   		}
   		else
   		{
   			address = "/Admin/Academic_Year/Display_Year_Semester_Details.jsp?Semester_id="+semester_id;
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
