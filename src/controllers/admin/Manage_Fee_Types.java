package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Fee_Types;
import model.beans.admin.Fee_Types_List;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class Manage_Fee_Types
 */
@WebServlet("/Manage_Fee_Types")
public class Manage_Fee_Types extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Fee_Types() {
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
    	int fee_id=0;
    	if(request.getParameter("Fee_id")!=null && !request.getParameter("Fee_id").equals(""))
    	{
    		fee_id = Integer.parseInt(request.getParameter("Fee_id"));
    	}
    	int school_id=0;
    	if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School"));
    	}
    	int admin_id = (Integer) session.getAttribute("Admin_Session");		
		String fee_Name = request.getParameter("Fee_Name");					
		String fee_Description = null;
		if(request.getParameter("Fee_Description")!=null && !request.getParameter("Fee_Description").equals(""))
		{
			fee_Description = request.getParameter("Fee_Description");	
		}
		String payment_Mode = null;
		payment_Mode = request.getParameter("Payment_Mode");		
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
		///////////////populate the bean ///////////////////////
		Fee_Types fee_Types = new Fee_Types();
		fee_Types.setSchool_id(school_id);;
		fee_Types.setFee_Name(fee_Name);
		fee_Types.setFee_Description(fee_Description);
		fee_Types.setPayment_Mode(payment_Mode);
		fee_Types.setDebit_Account_id(debit_Account_id);
		fee_Types.setCredit_Account_id(credit_Account_id);
		///////////////end populate the bean ///////////////////////
		FeesManagement feesManagement = new FeesManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if(debit_Account_id==credit_Account_id)
			{
				message="Debit Account cannot be the same as Credit Account. Fee Type not created/updated";
			}
			else
			{
				if (fee_id==0)
				{
					
					updateQuery = feesManagement.createFeeType(fee_Types);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "New Fee Type created successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
			        		message="Duplicate Entry for Fee name. Fee Type not created";
			        	}
			        	else
			        	{
			        		message = "Something wrong happend. New Fee Type was not created, please try again. You may need to logout and login again to renew your session.";
			        	}	        	
			        }
					
				}
			    else
			    {
			    	updateQuery = feesManagement.updateFeeType(fee_Types, fee_id);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "Fee Type updated successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
			        		message="Duplicate Entry for Fee name. Fee Type not updated";
			        	}
			        	else
			        	{
			        		message = "Something wrong happend. New Fee Type was not created, please try again. You may need to logout and login again to renew your session.";
			        	}		        	
			        }
			    }
			}
		}
	        
		else
		{
			updateQuery = feesManagement.deleteFeeType(fee_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Fee Type successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Fee Type. Only account owner is authorized to delete Fee Types.";
			}
			else
			{
				message = "Something wrong happend. Fee Type was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Fee Types///////////////////////////////
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	     String address = "/Admin/Fees/Fee_Types.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
