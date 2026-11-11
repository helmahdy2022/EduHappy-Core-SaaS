package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Employees;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class List_All_Employees
 */
@WebServlet("/List_All_Employees")
public class List_All_Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_All_Employees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Employees Employees = new Employees();
		HRManagement hrManagement = new HRManagement();
		HttpSession session= request.getSession();
		int admin_id = (Integer)session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();
		int account_id = accountManagement.getAdminAccount(admin_id);
		Employees = hrManagement.getAllEmployees(account_id, admin_id);
		request.setAttribute("Employees", Employees);

		String address = null;
		if(request.getParameter("Dropdown")!=null&&!request.getParameter("Dropdown").equals(""))
		{
			address = "/Admin/HR/Display_Employees_List.jsp";
		}
		else
		{
			address = "/Admin/HR/Display_Employees.jsp";
		}		
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
