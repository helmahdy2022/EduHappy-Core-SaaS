package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.teacher.Content_Container;
import model.dbHelpers.teacher.ContentManagement;

/**
 * Servlet implementation class Manage_Unit
 */
@WebServlet("/Manage_Unit")
public class Manage_Unit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Unit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int course_id= 0;
		if(request.getParameter("Course_id")!=null && !request.getParameter("Course_id").equals("null"))
		{
			
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		
		int units_qty = 0;
		if(request.getParameter("Units_qty")!=null)
		{
			units_qty = Integer.parseInt(request.getParameter("Units_qty"));
		}
		int cell_no=0;
		if(request.getParameter("i")!=null)
		{
			cell_no = Integer.parseInt(request.getParameter("i"));
		}
		String delete = request.getParameter("Delete");

		String unit_Name=null;
		int unit_id=0;
		String edit=null;
		String message=null;
		HttpSession session= request.getSession();
		Content_Container Content_Container = new Content_Container();
		Content_Container = (Content_Container) session.getAttribute("TeacherContent");	
		ContentManagement contentManagement = new ContentManagement();
		if (delete!=null)
		{
			unit_id = Integer.parseInt(request.getParameter("Unit_id"));
			Content_Container = contentManagement.deleteUnit(unit_id, course_id, cell_no, Content_Container);
			session.setAttribute("TeacherContent", Content_Container);
			if(Content_Container.getUpdateQuery()!=0)
			{
				message = "Deleted Successfully";	  			
			}
		}
		else
		{
			for (int i=1; i < units_qty+1; i++)
			{				
				
				unit_Name= request.getParameter("Unit_Name"+i);

				if(request.getParameter("Unit_id"+i)!=null && !request.getParameter("Unit_id"+i).equals(""))
		    	{
		    		unit_id= Integer.parseInt(request.getParameter("Unit_id"+i));
		    	}
				edit = request.getParameter("Edit"+i);
				if (edit==null)
				{
					edit= "0";
				}
				if (!(edit.equals("1")||edit.equals("0") ))
				{
					if(unit_Name!=null)
					{
						Content_Container = contentManagement.createUnit(course_id, unit_Name, Content_Container);
						session.setAttribute("TeacherContent", Content_Container);
						if (Content_Container.getUpdateQuery()!=0 && Content_Container.getUpdateQuery()!=-1)
						{
							message = "New unit created Successfully";
						}
						else
						{
							if(Content_Container.getUpdateQuery()==-1)
							{
								message = "Duplicate Entry for the Unit name for same Course. Unit was not created";
							}
							else
							{
								message = "Failed to create new unit";
							}
						}					
					}
				}
				else
				{	
					if(unit_Name!=null)
					{
						Content_Container = contentManagement.updateUnit(unit_id, course_id, unit_Name, i, Content_Container);
						session.setAttribute("TeacherContent", Content_Container);
						if (Content_Container.getUpdateQuery()!=0 && Content_Container.getUpdateQuery()!=-1)
						{
							message = "Unit updated Successfully";
						}
						else
						{
							if(Content_Container.getUpdateQuery()==-1)
							{
								message = "Duplicate Entry for the Unit name for same Course. Unit was not updated";
							}
							else
							{
								message = "Failed to update unit";
							}
						}	
					}				
				}
			}
		}
		String page=request.getParameter("Page");
		String address = null;
		if(delete==null)
		{
			address = "/Teacher/Course_Content/Manage_Unit.jsp?message="+message+"&&Page="+page+"&&Delete="+delete+"&&Units_qty"+units_qty;
		}
		else
		{
			address = "/Teacher/Course_Content/Display_Units.jsp?Units_no="+(units_qty-1)+"&&Course_id="+course_id+"&&Edit=1";
			
		}
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
