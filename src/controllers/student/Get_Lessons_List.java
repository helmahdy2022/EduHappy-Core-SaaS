package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.student.Course_Structure;
import model.dbHelpers.student.ServiceContent;

/**
 * Servlet implementation class Get_Lessons_List
 */
@WebServlet("/Get_Lessons_List")
public class Get_Lessons_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Lessons_List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int course_id = Integer.parseInt(request.getParameter("Course_id"));
		Course_Structure course_Structure = new Course_Structure();
		ServiceContent serviceContent = new ServiceContent();		
		course_Structure = serviceContent.getLessons(course_id);
		request.setAttribute("Course_Structure", course_Structure);
		String address = "/Student/Content/Display_Lessons_List.jsp";
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
