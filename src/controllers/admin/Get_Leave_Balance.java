package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Leave_Balance_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Get_Leave_Balance
 */
@WebServlet("/Get_Leave_Balance")
public class Get_Leave_Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Leave_Balance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int leave_id = 0;
		if(request.getParameter("Leave_id")!=null && !request.getParameter("Leave_id").equals(""))
		{
			leave_id = Integer.parseInt(request.getParameter("Leave_id")) ;
		}
		HRManagement hrManagement = new HRManagement();
		Leave_Balance_List Leave_Balance_List = new Leave_Balance_List();
		Leave_Balance_List = hrManagement.getLeaveBalance(leave_id);
		request.setAttribute("Leave_Balance_List", Leave_Balance_List);
		String address = "/Admin/HR/Display_Balance.jsp?Edit=Yes&&Balance_no="+Leave_Balance_List.getBalance_no();	
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
