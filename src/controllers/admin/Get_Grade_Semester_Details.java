package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Semester_Details;
import model.beans.admin.Grade_Semester_Terms;
import model.dbHelpers.admin.SemesterManagement;

/**
 * Servlet implementation class Get_Grade_Semester_Details
 */
@WebServlet("/Get_Grade_Semester_Details")
public class Get_Grade_Semester_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grade_Semester_Details() {
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
		Grade_Semester_Details Grade_Semester_Details = new Grade_Semester_Details();
		Grade_Semester_Details = semesterManagement.getGradeSemester(semester_id);
		
		Grade_Semester_Terms Grade_Semester_Terms = new Grade_Semester_Terms();
		Grade_Semester_Terms = semesterManagement.getGradeSemesterTerms(Grade_Semester_Details.getSchool_Year_id(), Grade_Semester_Details.getGrade_id() , semester_id);
    	
		request.setAttribute("Grade_Semester_Details", Grade_Semester_Details);
		request.setAttribute("Grade_Semester_Terms", Grade_Semester_Terms);
		
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Grades/Print_Semester.jsp";
   		}
   		else
   		{
   			address = "/Admin/Grades/Display_Grade_Semester_Details.jsp?Semester_id="+semester_id;
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
