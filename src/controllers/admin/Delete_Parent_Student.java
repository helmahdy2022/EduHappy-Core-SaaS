package controllers.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class Delete_Parent_Student
 */
@WebServlet("/Delete_Parent_Student")
public class Delete_Parent_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_Parent_Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int parent_id=0;
		if(request.getParameter("Parent_id")!=null)
		{
			parent_id = Integer.parseInt(request.getParameter("Parent_id"));
		}
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id= Integer.parseInt(request.getParameter("Student_id"));
		}
		ParentManagement parentManagement = new ParentManagement();
		
		parentManagement.deleteParentStudentLink(parent_id, student_id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
