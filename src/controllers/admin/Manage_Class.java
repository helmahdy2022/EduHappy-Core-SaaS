package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Class_Details;
import model.dbHelpers.admin.ClassManagement;

/**
 * Servlet implementation class Manage_Class
 */
@WebServlet("/Manage_Class")
public class Manage_Class extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Class() {
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
		int class_id=0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}
		String message = null;

		ClassManagement classManagement = new ClassManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			int grade_id=0;
			if(request.getParameter("Grade")!=null)
			{
				grade_id = Integer.parseInt(request.getParameter("Grade"));
			}
			String class_Name = request.getParameter("Class_Name");

			///////////////populate the bean ///////////////////////
			
			Class_Details class_Details = new Class_Details();
			class_Details.setClass_id(class_id);
			class_Details.setClass_Name(class_Name);
			class_Details.setGrade_id(grade_id);
			///////////////end populate the bean ///////////////////////
			if (class_id==0)
			{
				updateQuery = classManagement.createClass(class_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Class created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Class for the same Grade. Class was not created";
		        	}
		        	else
		        	{
			        	message = "New Class was not created";
		        	}
		        }
			}
			else
			{
				updateQuery = classManagement.updateClass(class_Details, class_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Class updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Class for the same Grade. Class was not updated";
		        	}
		        	else
		        	{
		        		message = "Class was not updated";
		        	}
		    	}		    		
			}
	
	    		
		}
		else
		{
			updateQuery = classManagement.deleteClass(class_id);
			if (updateQuery!=0)
				message = "Class successfully deleted";
			else
				message = "Class was not deleted";
		}
   		String address = "/Admin/Classes/Display_Manage_Class.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
