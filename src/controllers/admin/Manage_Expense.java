package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Expense_Details;
import model.dbHelpers.admin.ExpenseManagement;

/**
 * Servlet implementation class Manage_Expense
 */
@WebServlet("/Manage_Expense")
public class Manage_Expense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Expense() {
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
    	int expense_id=0;
    	if(request.getParameter("Expense_id")!=null && !request.getParameter("Expense_id").equals(""))
    	{
    		expense_id = Integer.parseInt(request.getParameter("Expense_id"));
    	}
		int admin_id = (Integer) session.getAttribute("Admin_Session");					
		String expense_Date = request.getParameter("Expense_Date");
		String reference_Number = request.getParameter("Reference_Number");
    	int expense_Type_id=0;
    	if(request.getParameter("Expense_Type_id")!=null && !request.getParameter("Expense_Type_id").equals(""))
    	{
    		expense_Type_id = Integer.parseInt(request.getParameter("Expense_Type_id"));
    	}
		String paid_To = request.getParameter("Paid_To");
    	
		double expense_Amount = -1;
		if(request.getParameter("Expense_Amount")!=null && !request.getParameter("Expense_Amount").equals(""))
		{
			expense_Amount = Double.parseDouble(request.getParameter("Expense_Amount"));
		}
		
		String payment_Method = request.getParameter("Payment_Method");
		String phone_Number = request.getParameter("Phone_Number");
		String expense_Details = request.getParameter("Expense_Details");
		///////////////populate the bean ///////////////////////
		Expense_Details Expense_Details = new Expense_Details();
		Expense_Details.setExpense_Date(expense_Date);
		Expense_Details.setReference_Number(reference_Number);
		Expense_Details.setExpense_Type_id(expense_Type_id);
		Expense_Details.setPaid_To(paid_To);
		Expense_Details.setExpense_Amount(expense_Amount);
		Expense_Details.setPayment_Method(payment_Method);
		Expense_Details.setPhone_Number(phone_Number);
		Expense_Details.setExpense_Details(expense_Details);
		///////////////end populate the bean ///////////////////////
		ExpenseManagement expenseManagement = new ExpenseManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (expense_id==0)
			{
				
				updateQuery = expenseManagement.createExpense(Expense_Details);
		        if (updateQuery!=0)
		        {
		        	message = "New Expense created successfully";
		        }
		        else
		        {
		        	message = "Something wrong happend. New Expense was not created, please try again. You may need to logout and login again to renew your session.";	        	
		        }
				
			}
		    else
		    {
		    	updateQuery = expenseManagement.updateExpense(Expense_Details, expense_id);
		        if (updateQuery!=0)
		        {
		        	message = "Expense updated successfully";
		        }
		        else
		        {
		        	message = "Something wrong happend. Expense was not updated, please try again. You may need to logout and login again to renew your session.";	        	
		        }
		    }
		}	        
		else
		{
			updateQuery = expenseManagement.deleteExpense(expense_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Expense successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Expense. Only account owner is authorized to delete Expenses.";
			}
			else
			{
				message = "Something wrong happend. Expense was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		} 	
	     String address = "/Admin/Expenses/Manage_Expenses.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}
}
