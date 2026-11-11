package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dbHelpers.admin.ParentManagement;


/**
 * Servlet implementation class Link_Parent_Student
 */
@WebServlet("/Link_Parent_Student")
public class Link_Parent_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Link_Parent_Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int parent_id = 0;
		parent_id = Integer.parseInt(request.getParameter("Parent_id"));
		String[] users_id = request.getParameter("User_id").split(",");
		for (int i=0; i<users_id.length; i++)
		{	
			ParentManagement parentManagement = new ParentManagement();
			parentManagement.linkParentStudent(Integer.parseInt(users_id[i]), parent_id);			
		}
		String address = null;
		address = "/Admin/Parents/Display_Link_Parent_Student.jsp";	 
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
