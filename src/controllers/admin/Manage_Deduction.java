package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Deduction_Details;
import model.beans.admin.Deduction_List;
import model.dbHelpers.admin.HRManagement;

/**
 * Servlet implementation class Manage_Deduction
 */
@WebServlet("/Manage_Deduction")
public class Manage_Deduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Deduction() {
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
    	int deduction_id=0;
    	if(request.getParameter("Deduction_id")!=null && !request.getParameter("Deduction_id").equals(""))
    	{
    		deduction_id = Integer.parseInt(request.getParameter("Deduction_id"));
    	}	
		int payroll_id=0;
    	if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
    	{
    		payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
    	}
		String deduction_Name = request.getParameter("Deduction_Name");
		String deduction_Description = null;
		if(request.getParameter("Deduction_Description")!=null && !request.getParameter("Deduction_Description").equals(""))
		{
			deduction_Description = request.getParameter("Deduction_Description");	
		}
		String deduction_Type = request.getParameter("Deduction_Type");
		String period = request.getParameter("Period");
		double amount=-1;

		if(deduction_Type.equals("Fixed"))
		{
			if(request.getParameter("Fixed_Amount")!=null && !request.getParameter("Fixed_Amount").equals(""))
			{
				amount = Double.parseDouble(request.getParameter("Fixed_Amount"));
			}
		}
		else if (deduction_Type.equals("Percentage"))
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
		Deduction_Details Deduction_Details = new Deduction_Details();
		Deduction_Details.setDeduction_id(deduction_id);
		Deduction_Details.setDeduction_Name(deduction_Name);
		Deduction_Details.setDeduction_Description(deduction_Description);
		Deduction_Details.setDeduction_Type(deduction_Type);
		Deduction_Details.setAmount(amount);
		Deduction_Details.setPeriod(period);
		Deduction_Details.setPayroll_id(payroll_id);
		
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (deduction_id==0)
			{			
				updateQuery = hrManagement.createDeduction(Deduction_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Deduction Type created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Deduction Type name. Deduction Type not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Deduction Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }			
			}
		    else
		    {
		    	updateQuery = hrManagement.updateDeduction(Deduction_Details, deduction_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Deduction Type updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Deduction Type name. Deduction Type not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Deduction Type was not created, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
		}
	        
		else
		{
			updateQuery = hrManagement.deleteDeduction(deduction_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Deduction Type successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Deduction Type. Only account owner is authorized to delete Deduction Type.";
			}
			else
			{
				message = "Something wrong happend. Deduction Type was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Leaves///////////////////////////////
		Deduction_List Deduction_List = new Deduction_List(); 
		Deduction_List = hrManagement.getDeductionList(payroll_id);
		request.setAttribute("Deduction_List", Deduction_List);
    	/////////////////////////////End retrieve List of Leaves///////////////////////////////  	
	    String address = "/Admin/HR/Deduction.jsp?message="+message;
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
