package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Privilege_List;
import model.dbHelpers.admin.AdminManagement;

/**
 * Servlet implementation class Manage_Privileges
 */
@WebServlet("/Manage_Privileges")
public class Manage_Privileges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Privileges() {
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
		Privilege_List Privilege_List = new Privilege_List();
		AdminManagement adminManagement = new AdminManagement();
		String message = null;
    	int admin_id=0;
    	if(request.getParameter("Admin_id")!=null && !request.getParameter("Admin_id").equals(""))
    	{
    		admin_id = Integer.parseInt(request.getParameter("Admin_id"));
    	}
    	Privilege_List = adminManagement.getPrivilegeList();
		for (Integer Feature_id : Privilege_List.getFeature_id()) 
    	{
    		int feature_id = 0;
    		if(request.getParameter("Feature"+Feature_id)!=null&&!request.getParameter("Feature"+Feature_id).equals(""))
			{
    			feature_id = Integer.parseInt(request.getParameter("Feature"+Feature_id));
			}
			if (feature_id!=0)
			{
				
				boolean adminPrivilegeExist = false;
				adminPrivilegeExist = adminManagement.checkAdminPrivilege(feature_id, admin_id);
				if (adminPrivilegeExist==false)
				{
					adminManagement.createAdminPrivilege(feature_id, admin_id);

				}
			}
			else
			{
				//Delete
				feature_id  = Integer.parseInt(request.getParameter("hiddenFeature"+Feature_id));
				adminManagement.deleteAdminPrivilege(feature_id, admin_id);
			}
			message = "Admin Privilege Applied successfully";
    	}
		String address="/Admin/Privileges/Admin_Privileges.jsp?message="+message;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
