package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Year_Terms;
import model.dbHelpers.admin.SchoolYearManagement;

/**
 * Servlet implementation class List_Year_Terms
 */
@WebServlet("/List_Year_Terms")
public class List_Year_Terms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Year_Terms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int school_year_id=0;
		if(request.getParameter("School_year_id")!=null)
		{
			school_year_id = Integer.parseInt(request.getParameter("School_year_id"));
		}
		
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		Year_Terms Year_Terms = new Year_Terms();
		
		Year_Terms = schoolYearManagement.getTermsNoSemesters(school_year_id);
		request.setAttribute("Year_Terms", Year_Terms);
		String address;
		address = "/Admin/Academic_Year/Display_Year_Terms.jsp";
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
