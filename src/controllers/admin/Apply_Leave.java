package controllers.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Leave_Details;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Apply_Leave
 */
@WebServlet("/Apply_Leave")
public class Apply_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apply_Leave() {
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
		int employee_Leave_id=0;
		int employee_id = 0;
		int leave_id = 0;
		String message=null;
		if(request.getParameter("Employee_Leave_id")!=null && !request.getParameter("Employee_Leave_id").equals(""))
		{
			employee_Leave_id = Integer.parseInt(request.getParameter("Employee_Leave_id"));
		}
		if(request.getParameter("Employee_id")!=null && !request.getParameter("Employee_id").equals(""))
		{
			employee_id = Integer.parseInt(request.getParameter("Employee_id"));
		}
		if(request.getParameter("Leave_id")!=null && !request.getParameter("Leave_id").equals(""))
		{
			leave_id = Integer.parseInt(request.getParameter("Leave_id"));
		}
		double leave_Days = 0.0;
		if(request.getParameter("Leave_Days")!=null && !request.getParameter("Leave_Days").equals(""))
		{
			leave_Days = Double.parseDouble(request.getParameter("Leave_Days"));
		}
		HRManagement hrManagement = new HRManagement();
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			Leave_Details Leave_Details = new Leave_Details();
			Leave_Details = hrManagement.getLeaveDetails(leave_id);
			
			String Leave_From = request.getParameter("Leave_From");
			String Leave_To = request.getParameter("Leave_To");
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date dLeave_From = null;
			Date dLeave_To = null;
			try {
				dLeave_From = formater.parse(Leave_From);
				dLeave_To = formater.parse(Leave_To);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			Calendar cal = Calendar.getInstance();
			cal.setTime(dLeave_From);
			int year_From = cal.get(Calendar.YEAR);
			int month_From = cal.get(Calendar.MONTH);
			cal.setTime(dLeave_To);
			int year_To = cal.get(Calendar.YEAR);
			int month_To = cal.get(Calendar.MONTH);
			if(Leave_Details.getPeriod().equals("Annually") && year_From!=year_To)
			{
				message = "This leave period is annually while Leave From date and Leave To date are not in the same year. Leave not applied";
			}
			else if(Leave_Details.getPeriod().equals("Monthly") && month_From!=month_To)
			{
				message = "This leave period is monthly while Leave From date and Leave To date are not in the same month. Leave not applied";
			}
			else
			{
				double employee_Taken_Leave = 0.0;
				double employee_Taken_Leave_Last_Period =0.0;
				if(Leave_Details.getPeriod().equals("Annually"))
				{
					employee_Taken_Leave = hrManagement.getEmployeeYearlyTakenLeave(employee_id, leave_id, year_From, employee_Leave_id);
					employee_Taken_Leave_Last_Period = hrManagement.getEmployeeYearlyTakenLeave(employee_id, leave_id, year_From-1, employee_Leave_id);
				}
				else
				{
					employee_Taken_Leave = hrManagement.getEmployeeMonthlyTakenLeave(employee_id, leave_id, month_From, year_From, employee_Leave_id);
					if(month_From==0)
					{
						employee_Taken_Leave_Last_Period = hrManagement.getEmployeeMonthlyTakenLeave(employee_id, leave_id, 11, year_From, employee_Leave_id);
					}
					else
					{
						employee_Taken_Leave_Last_Period = hrManagement.getEmployeeMonthlyTakenLeave(employee_id, leave_id, month_From-1, year_From, employee_Leave_id);
					}
				}
				double entitled_Balance=0;
				if(Leave_Details.getAll_Employees_Balance()!=-1)
				{
					entitled_Balance = Leave_Details.getAll_Employees_Balance();
				}
				else
				{
					Date joining_Date = hrManagement.getEmployeeJoiningDate(employee_id);
				    long diffInMillies = dLeave_From.getTime()- joining_Date.getTime();		    
				    double years_On_Board = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)/365.00;
				    entitled_Balance = hrManagement.getBalanceByExperience(leave_id, years_On_Board);
				}
				//////////////////////////////////////////Calculate Carry Forward///////////////////////////////////////////////////////
				double left_Over_Balance = 0.0;
				
				if(entitled_Balance > employee_Taken_Leave_Last_Period) 
				{
					left_Over_Balance = entitled_Balance - employee_Taken_Leave_Last_Period;
				}
				double entitled_Carry_Forward=0.0;
				if((Leave_Details.getPeriod().equals("Annually")&&Leave_Details.getCF_Effective_Year() < year_From) || Leave_Details.getPeriod().equals("Monthly"))
				{
					if(Leave_Details.getCarry_Forward()>left_Over_Balance)
					{
						entitled_Carry_Forward = left_Over_Balance;
					}
					else
					{
						entitled_Carry_Forward = Leave_Details.getCarry_Forward();
					}
				}
				entitled_Balance = entitled_Balance + entitled_Carry_Forward;
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				double remaining_Balance = entitled_Balance - employee_Taken_Leave;
				if(leave_Days <= remaining_Balance)
				{
					if(employee_Leave_id==0)
					{
						hrManagement.applyEmployeeLeave(employee_id, leave_id, dLeave_From, dLeave_To, leave_Days);
						message = "Employee Leave applied successfully.";
					}
					else
					{
						hrManagement.updateEmployeeLeave(employee_Leave_id, dLeave_From, dLeave_To, leave_Days);
						message = "Employee Leave updated successfully.";
					}
				}
				else
				{
					message = "Requested leave days exceed the remaining balance. Leave not applied.";
				}
			}
		}
		else
		{
			int updateQuery = hrManagement.deleteEmployeeLeave(employee_Leave_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Employee Leave deleted successfully";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Employee Leave. Only account owner is authorized to delete Employee Leave.";
			}
			else
			{
				message = "Something wrong happend. Employee Leave was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
		}

		 String address = "/Admin/HR/Apply_Leave.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
