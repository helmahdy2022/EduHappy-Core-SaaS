package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Leave_Details;
import model.beans.admin.Leave_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Manage_Leave
 */
@WebServlet("/Manage_Leave")
public class Manage_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Leave() {
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
    	int leave_id=0;
    	if(request.getParameter("Leave_id")!=null && !request.getParameter("Leave_id").equals(""))
    	{
    		leave_id = Integer.parseInt(request.getParameter("Leave_id"));
    	}	
		int payroll_id=0;
    	if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}
		String leave_Name = request.getParameter("Leave_Name");
		String leave_Description = null;
		if(request.getParameter("Leave_Description")!=null && !request.getParameter("Leave_Description").equals(""))
		{
			leave_Description = request.getParameter("Leave_Description");	
		}
		String payment_Type = request.getParameter("Payment_Type");
		String period = request.getParameter("Period");
		String Balance_Type = request.getParameter("Balance");
		double all_Employees_Balance=-1;
		ArrayList<Integer> leave_Balance_id_List = new ArrayList<Integer>();
		ArrayList<Double> balance_Years_List = new ArrayList<Double>();
		ArrayList<Double> balance_Count_List = new ArrayList<Double>();
		if(Balance_Type.equals("All Employees"))
		{
			if(request.getParameter("All_Employees_Balance")!=null && !request.getParameter("All_Employees_Balance").equals(""))
			{
				all_Employees_Balance = Double.parseDouble(request.getParameter("All_Employees_Balance"));
			}
		}
		else
		{
			//////////////////////Deal with Experienced Based Balance////////////////////////////////
			int balance_qty = 0;
			if(request.getParameter("Balance_qty")!=null)
			{
				balance_qty = Integer.parseInt(request.getParameter("Balance_qty"));
			}
			for (int i=1; i < balance_qty+1; i++)
			{
				if(request.getParameter("Balance_Years"+i)!=null&&!request.getParameter("Balance_Years"+i).equals("")&&request.getParameter("Balance_Count"+i)!=null&&!request.getParameter("Balance_Count"+i).equals(""))
				{
					int leave_Balance_id=0;
					if(request.getParameter("Leave_Balance_id"+i)!=null&&!request.getParameter("Leave_Balance_id"+i).equals(""))
					{
						leave_Balance_id= Integer.parseInt(request.getParameter("Leave_Balance_id"+i));
					}			
					double balance_Years= Double.parseDouble(request.getParameter("Balance_Years"+i));
					double balance_Count = Double.parseDouble(request.getParameter("Balance_Count"+i));
					leave_Balance_id_List.add(leave_Balance_id);
					balance_Years_List.add(balance_Years);
					balance_Count_List.add(balance_Count);	
				}	
			}	
			//////////////////////////////////////////////////////////////////////////////////////////	
		}
		double carry_Forward=-1;
		if(request.getParameter("Carry_Forward")!=null && !request.getParameter("Carry_Forward").equals(""))
		{
			carry_Forward = Double.parseDouble(request.getParameter("Carry_Forward"));
		}
		HRManagement hrManagement = new HRManagement();
		Leave_Details Leave_Details = new Leave_Details();
		Leave_Details.setLeave_id(leave_id);
		Leave_Details.setLeave_Name(leave_Name);
		Leave_Details.setLeave_Description(leave_Description);
		Leave_Details.setPayment_Type(payment_Type);
		Leave_Details.setPeriod(period);
		Leave_Details.setAll_Employees_Balance(all_Employees_Balance);
		Leave_Details.setPayroll_id(payroll_id);
		Leave_Details.setLeave_Balance_id_List(leave_Balance_id_List);
		Leave_Details.setBalance_Years_List(balance_Years_List);
		Leave_Details.setBalance_Count_List(balance_Count_List);
		Leave_Details.setCarry_Forward(carry_Forward);
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (leave_id==0)
			{			
				updateQuery = hrManagement.createLeave(Leave_Details);
		        if (updateQuery!=0 && updateQuery!=-1 && updateQuery!=-2)
		        {
		        	message = "New Leave Type created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Leave Type name. Leave Type not created";
		        	}
		        	else if(updateQuery==-2)
		        	{
		        		message="Duplicate Entry for Leave Balance Years. Although the Leave Type is created, the duplicate Leave Balance is not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Leave Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }			
			}
		    else
		    {
		    	updateQuery = hrManagement.updateLeave(Leave_Details, leave_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Leave Type updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Leave Type name. Leave Type not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Leave Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
		}
	        
		else
		{
			updateQuery = hrManagement.deleteLeave(leave_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Leave Type successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Leave Type. Only account owner is authorized to delete Leave Type.";
			}
			else
			{
				message = "Something wrong happend. Leave Type was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Leaves///////////////////////////////
		Leave_List Leave_List = new Leave_List(); 
		Leave_List = hrManagement.getLeaveList(payroll_id);
		request.setAttribute("Leave_List", Leave_List);
    	/////////////////////////////End retrieve List of Leaves///////////////////////////////  	
	    String address = "/Admin/HR/Leave.jsp?message="+message;
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
