package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Overtime_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class List_Overtime
 */
@WebServlet("/List_Overtime")
public class List_Overtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Overtime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int employee_id = 0;
		if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id"));
		}
		HRManagement hrManagement = new HRManagement();
		Overtime_List Overtime_List = new Overtime_List();
		Overtime_List = hrManagement.getOvertimeList(employee_id);
		request.setAttribute("Overtime_List", Overtime_List);	
		String address = "/Admin/HR/Display_Overtime_List.jsp";
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
