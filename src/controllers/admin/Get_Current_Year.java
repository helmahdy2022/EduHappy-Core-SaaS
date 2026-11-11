package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.SchoolYearManagement;

/**
 * Servlet implementation class Get_Current_Year
 */
@WebServlet("/Get_Current_Year")
public class Get_Current_Year extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Current_Year() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id=0;
		if(request.getParameter("School_id")!=null)
		{
			school_id = Integer.parseInt(request.getParameter("School_id"));
		}
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		String year_Name = schoolYearManagement.getCurrentSchoolYear(school_id);
		//PrintWriter out = response.getWriter();
		String message=null;
		if(year_Name!=null)
		{
			message = "Applied on Academic Year <font size='3' color='red'><b>"+year_Name+"</b></font>";
		}
		else
		{
			message = "No Academic Year available";
		}
   		String address = "/Admin/YearEnd/Display_Current_Year.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
