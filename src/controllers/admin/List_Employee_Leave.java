package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Employee_Leave_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class List_Employee_Leave
 */
@WebServlet("/List_Employee_Leave")
public class List_Employee_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Employee_Leave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int employee_id = 0;
		int leave_id = 0;
		if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id"));
		}
		if(request.getParameter("Leave_id")!=null && !request.getParameter("Leave_id").equals(""))
		{
			leave_id = Integer.parseInt(request.getParameter("Leave_id"));
		}
		HRManagement hrManagement = new HRManagement();
		Employee_Leave_List Employee_Leave_List = new Employee_Leave_List();
		Employee_Leave_List = hrManagement.getEmployeeLeaveList(employee_id, leave_id);
		request.setAttribute("Employee_Leave_List", Employee_Leave_List);	
		 String address = "/Admin/HR/Display_Employee_Leave_List.jsp";
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
