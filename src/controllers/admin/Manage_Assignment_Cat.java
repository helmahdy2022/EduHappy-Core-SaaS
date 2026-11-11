package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Assignment_Category_Details;
import model.beans.teacher.Content_Container;
import model.dbHelpers.admin.AssignmentCatManagement;

/**
 * Servlet implementation class Manage_Assignment_Cat
 */
@WebServlet("/Manage_Assignment_Cat")
public class Manage_Assignment_Cat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Assignment_Cat() {
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
		int category_id=0;
		if(request.getParameter("Category_id")!=null)
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		int course_id = 0;
		if(request.getParameter("Course")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course"));
		}
		int index=0;
		if(request.getParameter("Index")!=null)
		{
			index = Integer.parseInt(request.getParameter("Index"));
		}
		
		String message = null;

		AssignmentCatManagement assignmentCatManagement = new AssignmentCatManagement();
		
		HttpSession session= request.getSession();
		Content_Container Content_Container = new Content_Container();
		Content_Container = (Content_Container) session.getAttribute("TeacherContent");	

		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{


			
			String category_Name = request.getParameter("Category_Name");
			int category_Grade = 0;
			if(request.getParameter("Category_Grade")!=null)
			{
				category_Grade = Integer.parseInt(request.getParameter("Category_Grade"));
			}
			
			int contribute = 0;
			if(request.getParameter("Contribute")!=null)
			{
				contribute = 1;
			}
			int create_Authority = 0;
			if(request.getParameter("Create_Authority")!=null)
			{
				create_Authority = 1;
			}
			///////////////populate the bean ///////////////////////
			
			Assignment_Category_Details assignment_Category_Details = new Assignment_Category_Details();
			
			assignment_Category_Details.setCategory_id(category_id);
			assignment_Category_Details.setCategory_Name(category_Name);
			assignment_Category_Details.setCategory_Grade(category_Grade);
			assignment_Category_Details.setContribute(contribute);
			assignment_Category_Details.setCreate_Authority(create_Authority);
			assignment_Category_Details.setCourse_id(course_id);
			
			///////////////end populate the bean ///////////////////////
			if (category_id==0)
			{
				Content_Container = assignmentCatManagement.createAssignmentCat(assignment_Category_Details, Content_Container);
				session.setAttribute("TeacherContent", Content_Container);
				if (Content_Container.getUpdateQuery()!=0 && Content_Container.getUpdateQuery()!=-1)
		        {
		        	message = "New Assignment Category created successfully";
		        }
		        else
		        {
		        	if(Content_Container.getUpdateQuery()==-1)
		        	{
		        		message = "Duplicate Entry for the Category name for same Course. Assignment Category was not created";
		        	}
		        	else
		        	{
			        	message = "New Assignment Category was not created";
		        	}
		        }
			}
			else
			{
				Content_Container = assignmentCatManagement.updateAssignmentCat(assignment_Category_Details, category_id, Content_Container, index);
				session.setAttribute("TeacherContent", Content_Container);
				if (Content_Container.getUpdateQuery()!=0 && Content_Container.getUpdateQuery()!=-1)
				{
		    		message = "Assignment Category updated successfully";
				}
		    	else
		    	{
		    		if(Content_Container.getUpdateQuery()==-1)
		    		{
		    			message = "Duplicate Entry for the Category name for same Course. Assignment Category was not updated";
		    		}
		    		else
		    		{
		    			message = "Assignment Category was not updated";
		    		}
		    	}		    		
			}	    		
		}
		else
		{
			Content_Container = assignmentCatManagement.deleteAssignmentCat(category_id, course_id, Content_Container, index);
			if (Content_Container.getUpdateQuery()!=0)
				message = "Assignment Category successfully deleted";
			else
				message = "Assignment Category was not deleted";
		}
		String address=null;
   		if(request.getParameter("FromTeacher").equals("Yes"))
   		{
   			//if(ConfirmDelete==null)
   			//{
   	   			PrintWriter out = response.getWriter();
   	   			out.println("<meta http-equiv='refresh' content='0'/>");
   	   			session.setAttribute("message", message);
   	   			out.println("<script>");
   	   			out.println("document.getElementById('DeleteMessage').innerHTML= '';");
   	   			out.println("</script>");
   			//}
/*   			else
   			{
   				session.setAttribute("message", message);
   	   	 		StringBuffer url =  request.getRequestURL();
   	   	   	  	if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
   	   			{
   	   	   	  		url.replace(0, 4, "https");
   	   			}   	   	  		
   	   	   	  	String uri = request.getRequestURI();
   	   	   	  	String ctx = request.getContextPath();
   	   	   	  	String base = url.substring(0, url.length() - uri.length() + ctx.length());
   				address = base+"/Teacher/Course_Content/Manage_Assignment_Category.jsp?Page=Assignment_Category";  	  
   				response.sendRedirect(address);
   			}*/
   		}
   		else
   		{
   			address = "/Admin/Assignment_Category/Display_Manage_Assignment_Cat.jsp?message="+message;
   	  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
   	  		dispatcher.forward(request, response);
   		}

  		return;	 
	}

}
