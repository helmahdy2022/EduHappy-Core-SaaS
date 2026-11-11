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
 * Servlet implementation class Manage_Employee_Promotion
 */
@WebServlet("/Manage_Employee_Promotion")
public class Manage_Employee_Promotion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Employee_Promotion() {
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
		int employee_Promotion_id=0;
		int employee_id = 0;
		String message=null;
		if(request.getParameter("Employee_Promotion_id")!=null && !request.getParameter("Employee_Promotion_id").equals(""))
		{
			employee_Promotion_id = Integer.parseInt(request.getParameter("Employee_Promotion_id"));
		}
		if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id"));
		}
		double basic_Salary = 0.0;
		if(request.getParameter("Basic_Salary")!=null && !request.getParameter("Basic_Salary").equals(""))
		{
			basic_Salary = Double.parseDouble(request.getParameter("Basic_Salary"));
		}
		String promotion_Designation = request.getParameter("Promotion_Designation");
		HRManagement hrManagement = new HRManagement();
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
		
			String promotion_Date = request.getParameter("Promotion_Date");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date dPromotion_Date = null;
			try {
				dPromotion_Date = formater.parse(promotion_Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

			if(employee_Promotion_id==0)
			{
				hrManagement.applyEmployeePromotion(employee_id, dPromotion_Date, basic_Salary, promotion_Designation);
				message = "Employee Promotion applied successfully.";
			}
			else
			{
				hrManagement.updateEmployeePromotion(employee_Promotion_id, dPromotion_Date, basic_Salary, promotion_Designation);
				message = "Employee Promotion updated successfully.";
			}
		}
		else
		{
			int updateQuery = hrManagement.deleteEmployeePromotion(employee_Promotion_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Employee Promotion deleted successfully";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Employee Promotion. Only account owner is authorized to delete Employee Promotion.";
			}
			else
			{
				message = "Something wrong happend. Employee Promotion was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
		}

		 String address = "/Admin/HR/Promote_Employee.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}
}
