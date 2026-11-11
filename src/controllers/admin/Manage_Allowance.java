package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Allowance_Details;
import model.beans.admin.Allowance_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Manage_Allowance
 */
@WebServlet("/Manage_Allowance")
public class Manage_Allowance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Allowance() {
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
    	int allowance_id=0;
    	if(request.getParameter("Allowance_id")!=null && !request.getParameter("Allowance_id").equals(""))
    	{
    		allowance_id = Integer.parseInt(request.getParameter("Allowance_id"));
    	}	
		int payroll_id=0;
    	if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}
		String allowance_Name = request.getParameter("Allowance_Name");
		String allowance_Description = null;
		if(request.getParameter("Allowance_Description")!=null && !request.getParameter("Allowance_Description").equals(""))
		{
			allowance_Description = request.getParameter("Allowance_Description");	
		}
		String allowance_Type = request.getParameter("Allowance_Type");
		String period = request.getParameter("Period");
		double amount=-1;

		if(allowance_Type.equals("Fixed"))
		{
			if(request.getParameter("Fixed_Amount")!=null && !request.getParameter("Fixed_Amount").equals(""))
			{
				amount = Double.parseDouble(request.getParameter("Fixed_Amount"));
			}
		}
		else if (allowance_Type.equals("Percentage"))
		{
			if(request.getParameter("Percentage_Amount")!=null && !request.getParameter("Percentage_Amount").equals(""))
			{
				amount = Double.parseDouble(request.getParameter("Percentage_Amount"));
			}
		}
		else
		{
			amount = -1;
		}
		HRManagement hrManagement = new HRManagement();
		Allowance_Details Allowance_Details = new Allowance_Details();
		Allowance_Details.setAllowance_id(allowance_id);
		Allowance_Details.setAllowance_Name(allowance_Name);
		Allowance_Details.setAllowance_Description(allowance_Description);
		Allowance_Details.setAllowance_Type(allowance_Type);
		Allowance_Details.setAmount(amount);
		Allowance_Details.setPeriod(period);
		Allowance_Details.setPayroll_id(payroll_id);
		
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (allowance_id==0)
			{			
				updateQuery = hrManagement.createAllowance(Allowance_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Allowance Type created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Allowance Type name. Allowance Type not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Allowance Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }			
			}
		    else
		    {
		    	updateQuery = hrManagement.updateAllowance(Allowance_Details, allowance_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Allowance Type updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Allowance Type name. Allowance Type not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Allowance Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
		}
	        
		else
		{
			updateQuery = hrManagement.deleteAllowance(allowance_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Allowance Type successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Allowance Type. Only account owner is authorized to delete Allowance Type.";
			}
			else
			{
				message = "Something wrong happend. Allowance Type was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Leaves///////////////////////////////
		Allowance_List Allowance_List = new Allowance_List(); 
		Allowance_List = hrManagement.getAllowanceList(payroll_id);
		request.setAttribute("Allowance_List", Allowance_List);
    	/////////////////////////////End retrieve List of Leaves///////////////////////////////  	
	    String address = "/Admin/HR/Allowance.jsp?message="+message;
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
