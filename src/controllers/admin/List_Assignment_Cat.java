package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.AssignmentCategory;
import model.dbHelpers.admin.AssignmentCatManagement;

/**
 * Servlet implementation class List_Assignment_Cat
 */
@WebServlet("/List_Assignment_Cat")
public class List_Assignment_Cat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Assignment_Cat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id=0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		AssignmentCatManagement assignmentCatManagement = new AssignmentCatManagement();
		AssignmentCategory AssignmentCategory = new AssignmentCategory();
		AssignmentCategory = assignmentCatManagement.getAssignmentCatList(course_id);
		request.setAttribute("AssignmentCategory", AssignmentCategory);
		String address;
		address = "/Global/Display_Assignment_Cat.jsp";
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
