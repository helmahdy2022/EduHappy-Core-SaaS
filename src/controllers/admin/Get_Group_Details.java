package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Group_Details;
import model.beans.admin.Students;
import model.dbHelpers.admin.GroupManagement;

/**
 * Servlet implementation class Get_Group_Details
 */
@WebServlet("/Get_Group_Details")
public class Get_Group_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Group_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int group_id = 0;
		if (request.getParameter("Group_id")!=null)
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}	
		GroupManagement groupManagement = new GroupManagement();
		Group_Details Group_Details = new Group_Details();
		Group_Details = groupManagement.getGroup(group_id);
		Students Students = new Students();
		Students = groupManagement.getGroupStudents(group_id);
		request.setAttribute("Group_Details", Group_Details);
		request.setAttribute("Students", Students);
		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Courses/Print_Group.jsp";
   		}
   		else
   		{
   			address = "/Admin/Courses/Display_Group_Details.jsp?Group_id="+group_id;
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
