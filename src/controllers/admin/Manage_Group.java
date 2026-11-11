package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Group_Details;
import model.dbHelpers.admin.GroupManagement;

/**
 * Servlet implementation class Manage_Group
 */
@WebServlet("/Manage_Group")
public class Manage_Group extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Group() {
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
		int group_id=0;
		if(request.getParameter("Group_id")!=null)
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}
		int course_id=0;
		if(request.getParameter("Course")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course"));
		}
		String group_Name = request.getParameter("Group_Name");
		
		String message = null;

		GroupManagement groupManagement = new GroupManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{

			///////////////populate the bean ///////////////////////
			
			Group_Details group_Details = new Group_Details();
			group_Details.setGroup_id(group_id);
			group_Details.setCourse_id(course_id);
			group_Details.setGroup_Name(group_Name);
			///////////////end populate the bean ///////////////////////
			if (group_id==0)
			{
				updateQuery = groupManagement.createGroup(group_Details);
		        if (updateQuery!=0 && updateQuery!=-1 )
		        {
		        	message = "New Group created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Group for the same Course. Group was not created";
		        	}
		        	else
		        	{
		        		message = "New Group was not created";
		        	}
		        }
			}
			else
			{
				updateQuery = groupManagement.updateGroup(group_Details, group_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Group updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Group for the same Course. Group was not updated";
		        	}
		        	else
		        	{
		        		message = "Group was not updated";
		        	}
		    	}
			}
	
	    		
		}
		else
		{
			updateQuery = groupManagement.deleteGroup(group_id);
			if (updateQuery!=0)
				message = "Group successfully deleted";
			else
				message = "Group was not deleted";
		}
   		String address = "/Admin/Courses/Display_Manage_Group.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	
	}

}
