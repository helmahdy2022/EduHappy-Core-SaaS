package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.teacher.Lessons_List;
import model.dbHelpers.teacher.ContentManagement;

/**
 * Servlet implementation class List_Lessons
 */
@WebServlet("/List_Lessons")
public class List_Lessons extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Lessons() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int unit_id = 0;
		if(request.getParameter("Unit_id")!=null)
		{
			unit_id = Integer.parseInt(request.getParameter("Unit_id"));
		}
		String Grade_id = request.getParameter("Grade_id");
		String Class_id = request.getParameter("Class_id");
		String Course_id = request.getParameter("Course_id");
		String Group_id = request.getParameter("Group_id");
		String Page = request.getParameter("Page");
		String Action = request.getParameter("Action");
		String Lesson = request.getParameter("Lesson_id");

		ContentManagement contentManagement = new ContentManagement();
		Lessons_List Lessons_List = new Lessons_List();
		Lessons_List = contentManagement.getLessonsList(unit_id);
		
		if (Page==null)
		{
			Page="Nothing";
		}

    	getServletContext().setAttribute("Lessons_List", Lessons_List);


		String address = null;
		if (Page.equals("Assignment"))
		{
    		address = "/Teacher/Assignments/Display_Lessons_DD.jsp?Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id;  			
		}
		else if(Page.equals("Lesson")&&Action.equals("Edit"))
		{
			address = "/Teacher/Course_Content/Display_Lessons_Links.jsp?Lesson_id="+Lesson+"&&Course_id="+Course_id; 
		}
		else
		{
    		address = "/Teacher/Course_Content/Display_Lessons_List.jsp?Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id;  			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
