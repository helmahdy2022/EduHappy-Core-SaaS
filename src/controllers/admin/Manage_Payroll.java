package controllers.admin;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Payroll_Details;
import model.beans.admin.Payroll_List;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class Manage_Payroll
 */
@WebServlet("/Manage_Payroll")
public class Manage_Payroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Payroll() {
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
    	int payroll_id=0;
    	if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}	
		int school_id=0;
    	if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School"));
    	}
		String payroll_Name = request.getParameter("Payroll_Name");
		String payroll_Description = null;
		if(request.getParameter("Payroll_Description")!=null && !request.getParameter("Payroll_Description").equals(""))
		{
			payroll_Description = request.getParameter("Payroll_Description");	
		}
		double working_Days=0;
		if(request.getParameter("Working_Days")!=null && !request.getParameter("Working_Days").equals(""))
		{
			working_Days = Double.parseDouble(request.getParameter("Working_Days"));
		}

		DateFormat sdf = new SimpleDateFormat("HH:mm");
		Time dWorking_Time_From = null;
		Time dWorking_Time_To  = null;
		try {
			if(request.getParameter("Working_Time_From")!=null && !request.getParameter("Working_Time_From").equals(""))
			{
				dWorking_Time_From = new Time(sdf.parse(request.getParameter("Working_Time_From")).getTime());
			}
			if(request.getParameter("Working_Time_To")!=null && !request.getParameter("Working_Time_To").equals(""))
			{
				dWorking_Time_To = new Time(sdf.parse(request.getParameter("Working_Time_To")).getTime());
			}			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		double overtime_Normal=0;
		if(request.getParameter("Overtime_Normal")!=null && !request.getParameter("Overtime_Normal").equals(""))
		{
			overtime_Normal = Double.parseDouble(request.getParameter("Overtime_Normal"));
		}
		double overtime_Weekend=0;
		if(request.getParameter("Overtime_Weekend")!=null && !request.getParameter("Overtime_Weekend").equals(""))
		{
			overtime_Weekend = Double.parseDouble(request.getParameter("Overtime_Weekend"));
		}
		double overtime_Holidays=0;
		if(request.getParameter("Overtime_Holidays")!=null && !request.getParameter("Overtime_Holidays").equals(""))
		{
			overtime_Holidays = Double.parseDouble(request.getParameter("Overtime_Holidays"));
		}
		double overtime_Daysoff=0;
		if(request.getParameter("Overtime_Daysoff")!=null && !request.getParameter("Overtime_Daysoff").equals(""))
		{
			overtime_Daysoff = Double.parseDouble(request.getParameter("Overtime_Daysoff"));
		}
		int debit_Account_id=0;
		if(request.getParameter("Debit_Account_id")!=null && !request.getParameter("Debit_Account_id").equals(""))
		{
			debit_Account_id = Integer.parseInt(request.getParameter("Debit_Account_id"));
		}
		int credit_Account_id=0;
		if(request.getParameter("Credit_Account_id")!=null && !request.getParameter("Credit_Account_id").equals(""))
		{
			credit_Account_id = Integer.parseInt(request.getParameter("Credit_Account_id"));
		}
		PayrollManagement payrollManagement = new PayrollManagement();
		Payroll_Details Payroll_Details = new Payroll_Details();
		Payroll_Details.setPayroll_id(payroll_id);
		Payroll_Details.setPayroll_Name(payroll_Name);
		Payroll_Details.setPayroll_Description(payroll_Description);
		Payroll_Details.setSchool_id(school_id);
		Payroll_Details.setWorking_Time_From(dWorking_Time_From);
		Payroll_Details.setWorking_Time_To(dWorking_Time_To);
		Payroll_Details.setWorking_Days(working_Days);
		Payroll_Details.setOvertime_Normal(overtime_Normal);
		Payroll_Details.setOvertime_Weekend(overtime_Weekend);
		Payroll_Details.setOvertime_Holidays(overtime_Holidays);
		Payroll_Details.setOvertime_Daysoff(overtime_Daysoff);
		Payroll_Details.setDebit_Account_id(debit_Account_id);
		Payroll_Details.setCredit_Account_id(credit_Account_id);
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if(dWorking_Time_From.compareTo(dWorking_Time_To)<0)
			{
				if(debit_Account_id==credit_Account_id)
				{
					message="Debit Account cannot be the same as Credit Account. Fee Type not created/updated";
				}
				else
				{
					if (payroll_id==0)
					{
						
						updateQuery = payrollManagement.createPayroll(Payroll_Details);
				        if (updateQuery!=0 && updateQuery!=-1)
				        {
				        	message = "New Payroll created successfully";
				        }
				        else
				        {
				        	if(updateQuery==-1)
				        	{
				        		message="Duplicate Entry for Payroll name. Payroll not created";
				        	}
				        	else
				        	{
				        		message = "Something wrong happend. New Payroll was not created, please try again. You may need to logout and login again to renew your session.";
				        	}	        	
				        }
						
					}
				    else
				    {
				    	updateQuery = payrollManagement.updatePayroll(Payroll_Details, payroll_id);
				        if (updateQuery!=0 && updateQuery!=-1)
				        {
				        	message = "Payroll updated successfully";
				        }
				        else
				        {
				        	if(updateQuery==-1)
				        	{
				        		message="Duplicate Entry for Payroll name. Payroll not updated";
				        	}
				        	else
				        	{
				        		message = "Something wrong happend. New Payroll was not created, please try again. You may need to logout and login again to renew your session.";
				        	}		        	
				        }
				    }
				}
			}
			else
			{
				message="Day working time to must be after Day working time from. Payroll not created/updated";
			}
		}
	        
		else
		{
			updateQuery = payrollManagement.deletePayroll(payroll_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Payroll successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Payroll. Only account owner is authorized to delete Payroll.";
			}
			else
			{
				message = "Something wrong happend. Payroll was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Payrolls///////////////////////////////
		Payroll_List Payroll_List = new Payroll_List(); 
		Payroll_List = payrollManagement.getPayrollList(school_id);
		request.setAttribute("Payroll_List", Payroll_List);
    	/////////////////////////////End retrieve List of Payroll///////////////////////////////  	
	    String address = "/Admin/HR/Payroll.jsp?message="+message;
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
