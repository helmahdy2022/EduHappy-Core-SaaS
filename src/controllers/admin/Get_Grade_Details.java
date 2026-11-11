package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Details;
import model.dbHelpers.admin.GradeManagement;


/**
 * Servlet implementation class Get_Grade_Details
 */
@WebServlet("/Get_Grade_Details")
public class Get_Grade_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grade_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int grade_id = Integer.parseInt(request.getParameter("Grade_id")) ;
		GradeManagement gradeManagement = new GradeManagement();
		Grade_Details Grade_Details = new Grade_Details();
		Grade_Details = gradeManagement.getGrade(grade_id);
		request.setAttribute("Grade_Details", Grade_Details);
		
   		String address=null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Grades/Print_Grade.jsp";
   		}
   		else
   		{
   			address = "/Admin/Grades/Display_Grade_Details.jsp?Grade_id="+grade_id;
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
