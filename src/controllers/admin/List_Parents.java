package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Parents;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class List_Parents
 */
@WebServlet("/List_Parents")
public class List_Parents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Parents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_First_Name = request.getParameter("First_Name");
		if (s_First_Name == "")
		{
			s_First_Name = "%";
		}
		String s_Last_Name = request.getParameter("Last_Name");
		if (s_Last_Name == "")
		{
			s_Last_Name = "%";
		}
		String s_Username = request.getParameter("Username");
		if (s_Username == "")
		{
			s_Username = "%";
		}
		String s_Parent_id = request.getParameter("Parent_id");
		if (s_Parent_id == "")
		{
			s_Parent_id = "%";
		}
		Parents Parents = new Parents();
		ParentManagement parentManagement = new ParentManagement();
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);
		Parents = parentManagement.getParentsList(s_First_Name, s_Last_Name, s_Username, s_Parent_id, account_id);
    	request.setAttribute("Parents", Parents);
		String address;
		String Page = request.getParameter("Page");
		if(Page!=null && Page.equals("Message"))
		{
			address = "/Admin/Messages/Display_Parents_Message.jsp";
		}
		else
		{
			address = "/Admin/Parents/Display_Parent.jsp?Page="+Page;
		}		 
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
