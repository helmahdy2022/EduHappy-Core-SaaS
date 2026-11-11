package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.YearEndProcedures;

/**
 * Servlet implementation class Promote_Students
 */
@WebServlet("/Promote_Students")
public class Promote_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Promote_Students() {
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
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		String message = null;
		int school_id=0;
		if(request.getParameter("School_id")!=null&&!request.getParameter("School_id").equals(""))
		{
			school_id=Integer.parseInt(request.getParameter("School_id"));	
		}
		if(school_id==0)
		{
			message="Please select the school first";
		}
		else
		{
			YearEndProcedures yearEndProcedures = new YearEndProcedures();
			
			message = yearEndProcedures.promoteStudents(school_id, appPath);
		}
   		String address = "/Admin/YearEnd/Display_Promote_Students.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
