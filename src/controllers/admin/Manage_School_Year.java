package controllers.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.SchoolYearManagement;

/**
 * Servlet implementation class Manage_School_Year
 */
@WebServlet("/Manage_School_Year")
public class Manage_School_Year extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_School_Year() {
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
		String message=null;				
		
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null)
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		int school_id = 0;
		if(request.getParameter("School")!=null)
		{
			school_id=Integer.parseInt(request.getParameter("School"));
		}
		String Year_Name = request.getParameter("Year_Name");
		String Year_Start = request.getParameter("Year_Start");
		String Year_End = request.getParameter("Year_End");
		//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dYear_Start = null;
		Date dYear_End = null;
		try {
			dYear_Start = formater.parse(Year_Start);
			dYear_End = formater.parse(Year_End);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		try {
			message = schoolYearManagement.manageSchoolYear(school_id, school_Year_id, Year_Name, dYear_Start, dYear_End, request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String address = "/Admin/Academic_Year/Display_Insert_School_Year.jsp?message="+message;
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
 		return;	
	}

}
