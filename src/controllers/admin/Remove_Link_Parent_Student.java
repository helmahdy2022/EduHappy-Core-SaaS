package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Students;
import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class Remove_Link_Parent_Student
 */
@WebServlet("/Remove_Link_Parent_Student")
public class Remove_Link_Parent_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove_Link_Parent_Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int parent_id=0;
		if(request.getParameter("Parent_id")!=null && !request.getParameter("Parent_id").equals(""))
		{
			parent_id = Integer.parseInt(request.getParameter("Parent_id"));
		}
		ParentManagement parentManagement = new ParentManagement();
		Students Students = new Students();
		Students = parentManagement.getParentStudents(parent_id);
    	request.setAttribute("Students", Students);
		String address = null;
		address = "/Admin/Parents/Remove_Link_Parent_Student.jsp?Parent_id="+parent_id;	 
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
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
