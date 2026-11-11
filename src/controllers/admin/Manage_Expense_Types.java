package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Expense_Types;
import model.beans.admin.Expense_Types_List;
import model.dbHelpers.admin.ExpenseManagement;

/**
 * Servlet implementation class Manage_Expense_Types
 */
@WebServlet("/Manage_Expense_Types")
public class Manage_Expense_Types extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Expense_Types() {
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
    	int expense_Type_id=0;
    	if(request.getParameter("Expense_Type_id")!=null && !request.getParameter("Expense_Type_id").equals(""))
    	{
    		expense_Type_id = Integer.parseInt(request.getParameter("Expense_Type_id"));
    	}	
		int school_id=0;
    	if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School"));
    	}
		String expense_Type_Name = request.getParameter("Expense_Type_Name");
		String expense_Type_Description = null;
		if(request.getParameter("Expense_Type_Description")!=null && !request.getParameter("Expense_Type_Description").equals(""))
		{
			expense_Type_Description = request.getParameter("Expense_Type_Description");	
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
		ExpenseManagement expenseManagement = new ExpenseManagement();
		Expense_Types Expense_Types = new Expense_Types();
		Expense_Types.setSchool_id(school_id);
		Expense_Types.setExpense_Type_Name(expense_Type_Name);
		Expense_Types.setExpense_Type_Description(expense_Type_Description);
		Expense_Types.setDebit_Account_id(debit_Account_id);
		Expense_Types.setCredit_Account_id(credit_Account_id);
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
				if (expense_Type_id==0)
				{
					
					updateQuery = expenseManagement.createExpenseType(Expense_Types);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "New Expense Type created successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
			        		message="Duplicate Entry for Expense Type name. Expense Type not created";
			        	}
			        	else
			        	{
			        		message = "Something wrong happend. New Expense Type was not created, please try again. You may need to logout and login again to renew your session.";
			        	}	        	
			        }
					
				}
			    else
			    {
			    	updateQuery = expenseManagement.updateExpenseType(Expense_Types, expense_Type_id);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "Expense Type updated successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
			        		message="Duplicate Entry for Expense Type name. Expense Type not updated";
			        	}
			        	else
			        	{
			        		message = "Something wrong happend. New Expense Type was not created, please try again. You may need to logout and login again to renew your session.";
			        	}		        	
			        }
			    }
			}
		}
	        
		else
		{
			updateQuery = expenseManagement.deleteExpenseType(expense_Type_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Expense Type successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Expense Type. Only account owner is authorized to delete Expense Types.";
			}
			else
			{
				message = "Something wrong happend. Expense Type was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		}
	    /////////////////////////////Retrieve List of Fee Types///////////////////////////////
		Expense_Types_List Expense_Types_List = new Expense_Types_List(); 
		Expense_Types_List = expenseManagement.getExpenseTypesList(school_id);
		request.setAttribute("Expense_Types_List", Expense_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	     String address = "/Admin/Expenses/Expense_Types.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
