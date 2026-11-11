package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Classes;
import model.beans.admin.Courses;
import model.beans.admin.Division_Details;
import model.dbHelpers.admin.DivisionManagement;

/**
 * Servlet implementation class Get_Division_Details
 */
@WebServlet("/Get_Division_Details")
public class Get_Division_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Division_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int division_id=0;
		if(request.getParameter("Division_id")!=null)
		{
			division_id = Integer.parseInt(request.getParameter("Division_id"));
		}
		DivisionManagement divisionManagement = new DivisionManagement();
		Division_Details Division_Details = new Division_Details();
		Division_Details = divisionManagement.getDivision(division_id);
		Courses Courses = new Courses();
		Courses = divisionManagement.getDivisionCourses(Division_Details.getGrade_id(), division_id);
		Classes Classes = new Classes();
		Classes = divisionManagement.getDivisionClasses(Division_Details.getGrade_id(), division_id);
    	
		request.setAttribute("Division_Details", Division_Details);
		request.setAttribute("Courses", Courses);
		request.setAttribute("Classes", Classes);
		
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Divisions/Print_Division.jsp";
   		}
   		else
   		{
   			address = "/Admin/Divisions/Display_Division_Details.jsp?Division_id="+division_id;
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
