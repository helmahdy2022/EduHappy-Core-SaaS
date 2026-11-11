package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Admin_Privilege;
import model.beans.admin.Privilege_List;
import model.dbHelpers.admin.AdminManagement;


/**
 * Servlet implementation class List_Features
 */
@WebServlet("/List_Features")
public class List_Features extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Features() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int admin_id=0;
    	if(request.getParameter("Admin_id")!=null && !request.getParameter("Admin_id").equals(""))
    	{
    		admin_id = Integer.parseInt(request.getParameter("Admin_id"));
    	}
		AdminManagement adminManagement = new AdminManagement();
		Privilege_List Privilege_List = new Privilege_List(); 
		Privilege_List = adminManagement.getPrivilegeList();
		request.setAttribute("Privilege_List", Privilege_List);
		Admin_Privilege Admin_Privilege = new Admin_Privilege();
		Admin_Privilege = adminManagement.getAdminPrivilege(admin_id);
		request.setAttribute("Admin_Privilege", Admin_Privilege);	
	    String address="/Admin/Privileges/Display_Privilege_List.jsp";   
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
