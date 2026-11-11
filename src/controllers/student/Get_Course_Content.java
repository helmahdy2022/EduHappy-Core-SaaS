package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.student.Course_Content;
import model.dbHelpers.student.ServiceContent;


/**
 * Servlet implementation class Get_Course_Content
 */
@WebServlet("/Get_Course_Content")
public class Get_Course_Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Course_Content() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
		HttpSession session= request.getSession();
		int student_id = 0;
		String source = null;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			source = "Parent";
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
			source = "Student";
		}		
		Course_Content Course_Content = new Course_Content();
		ServiceContent serviceContent = new ServiceContent();
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		Course_Content = serviceContent.getContent(student_id, appPath);
		request.setAttribute("Course_Content", Course_Content);
		String address = "/Student/Content/Course_Content.jsp?Source="+source+"&&Student_id="+student_id;
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
