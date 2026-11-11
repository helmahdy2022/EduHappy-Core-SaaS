package controllers.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Manage_Overtime
 */
@WebServlet("/Manage_Overtime")
public class Manage_Overtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Overtime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		HttpSession session= request.getSession();
    	int admin_id = (Integer) session.getAttribute("Admin_Session");
		int overtime_id=0;
		int employee_id = 0;
		String message=null;
		if(request.getParameter("Overtime_id")!=null && !request.getParameter("Overtime_id").equals(""))
		{
			overtime_id = Integer.parseInt(request.getParameter("Overtime_id"));
		}
		if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id"));
		}
		double overtime_Hours = 0.0;
		if(request.getParameter("Overtime_Hours")!=null && !request.getParameter("Overtime_Hours").equals(""))
		{
			overtime_Hours = Double.parseDouble(request.getParameter("Overtime_Hours"));
		}
		HRManagement hrManagement = new HRManagement();
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
		
			String overtime_Date = request.getParameter("Overtime_Date");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date dOvertime_Date = null;
			try {
				dOvertime_Date = formater.parse(overtime_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

			if(overtime_id==0)
			{
				hrManagement.applyOvertime(employee_id, dOvertime_Date, overtime_Hours);
				message = "Employee Overtime applied successfully.";
			}
			else
			{
				hrManagement.updateOvertime(overtime_id, dOvertime_Date, overtime_Hours);
				message = "Employee Overtime updated successfully.";
			}
		}
		else
		{
			int updateQuery = hrManagement.deleteOvertime(overtime_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Employee Overtime deleted successfully";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Employee Overtime. Only account owner is authorized to delete Employee Overtime.";
			}
			else
			{
				message = "Something wrong happend. Employee Overtime was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
		}

		 String address = "/Admin/HR/Overtime.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
