package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Subject_Details;
import model.dbHelpers.admin.SubjectManagement;

/**
 * Servlet implementation class Manage_Subject
 */
@WebServlet("/Manage_Subject")
public class Manage_Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Subject() {
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
		int subject_id=0;
		if(request.getParameter("Subject_id")!=null)
		{
			subject_id = Integer.parseInt(request.getParameter("Subject_id"));
		}
		String message = null;

		SubjectManagement subjectManagement = new SubjectManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			int grade_id=0;
			if(request.getParameter("Grade")!=null)
			{
				grade_id = Integer.parseInt(request.getParameter("Grade"));
			}
			String subject_Name = request.getParameter("Subject_Name");

			///////////////populate the bean ///////////////////////
			
			Subject_Details subject_Details = new Subject_Details();
			subject_Details.setSubject_id(subject_id);
			subject_Details.setSubject_Name(subject_Name);
			subject_Details.setGrade_id(grade_id);
			///////////////end populate the bean ///////////////////////
			if (subject_id==0)
			{
				updateQuery = subjectManagement.createSubject(subject_Details, request);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Subject created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Subject for the same Grade. Subject was not created";
		        	}
		        	else
		        	{
		        		message = "New Subject was not created";
		        	}	        	
		        }
			}
			else
			{
				updateQuery = subjectManagement.updateSubject(subject_Details, subject_id, request);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Subject updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Subject for the same Grade. Subject was not updated";
		        	}
		        	else
		        	{
		        		message = "Subject was not updated";
		        	}
		    	}
		    		
			}    		
		}
		else
		{
			updateQuery = subjectManagement.deleteSubject(subject_id);
			if (updateQuery!=0)
				message = "Subject successfully deleted";
			else
				message = "Subject was not deleted";
		}
   		String address = "/Admin/Subjects/Display_Manage_Subject.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
