package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Daysoff_Details;
import model.beans.admin.Daysoff_Schools_Grades;
import model.dbHelpers.admin.DaysoffManagement;


/**
 * Servlet implementation class List_Daysoff_Schools_Grades
 */
@WebServlet("/List_Daysoff_Schools_Grades")
public class List_Daysoff_Schools_Grades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Daysoff_Schools_Grades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		int daysoff_id = 0;
		if(request.getParameter("Daysoff_id")!=null)
		{
			daysoff_id = Integer.parseInt(request.getParameter("Daysoff_id"));
		}
		/////////////////////////////Retrieve Schools///////////////////////////////
		DaysoffManagement daysoffManagement = new DaysoffManagement();
		Daysoff_Schools_Grades Daysoff_Schools_Grades = new Daysoff_Schools_Grades();
		Daysoff_Schools_Grades = daysoffManagement.getDaysoffSchoolsGrades(daysoff_id, admin_id);

		request.setAttribute("Daysoff_Schools_Grades", Daysoff_Schools_Grades);	
	    /////////////////////////////End retrieve Schools///////////////////////////////
    	String address = null;

		if (daysoff_id == 0)
		{
			address = "/Admin/Academic_Year/Display_Daysoff_Schools_Grades.jsp";
		}
		else
		{
			Daysoff_Details Daysoff_Details = new Daysoff_Details();
			Daysoff_Details = daysoffManagement.getDaysoffDetails(daysoff_id);
			request.setAttribute("Daysoff_Details", Daysoff_Details);
			address = "/Admin/Academic_Year/Get_Daysoff_Details.jsp";
		}
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
