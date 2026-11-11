package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Assignment_Category_Details;
import model.dbHelpers.admin.AssignmentCatManagement;

/**
 * Servlet implementation class Get_Category_Details
 */
@WebServlet("/Get_Category_Details")
public class Get_Category_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Category_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int category_id = 0;
		if(request.getParameter("Category_id")!=null)
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		int course_id = 0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		int index=0;
		if(request.getParameter("Index")!=null)
		{
			index = Integer.parseInt(request.getParameter("Index"));
		}
		AssignmentCatManagement assignmentCatManagement = new AssignmentCatManagement();
		Assignment_Category_Details Assignment_Category_Details = new Assignment_Category_Details();
		Assignment_Category_Details = assignmentCatManagement.getAssignmentCat(category_id);
		request.setAttribute("Assignment_Category_Details", Assignment_Category_Details);
   		
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Assignment_Category/Print_Assignment_Cat.jsp";
   		}
   		else
   		{
   			if(request.getParameter("FromTeacher")!=null&&request.getParameter("FromTeacher").equals("Yes"))
   			{
   	   			address = "/Teacher/Course_Content/Display_Assignment_Cat_Details.jsp?Category_id="+category_id+"&&Course_id="+course_id+"&&Index="+index;
   			}
   			else
   			{
   	   			address = "/Admin/Assignment_Category/Display_Assignment_Cat_Details.jsp?Category_id="+category_id+"&&Course_id="+course_id+"&&Index="+index;  				
   			}
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
