package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Financial_Account_Details;
import model.dbHelpers.admin.FinancialAccountingManagement;


/**
 * Servlet implementation class Manage_Financial_Account
 */
@WebServlet("/Manage_Financial_Account")
public class Manage_Financial_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Financial_Account() {
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
		int financial_Account_id=0;
		if(request.getParameter("Financial_Account_id")!=null && !request.getParameter("Financial_Account_id").equals(""))
		{
			financial_Account_id = Integer.parseInt(request.getParameter("Financial_Account_id"));
		}
		String message = null;

		FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			int financial_Category_id=0;
			if(request.getParameter("Financial_Category_id")!=null && !request.getParameter("Financial_Category_id").equals(""))
			{
				financial_Category_id = Integer.parseInt(request.getParameter("Financial_Category_id"));
			}
			int school_id=0;
			if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
			{
				school_id = Integer.parseInt(request.getParameter("School_id"));
			}
			String financial_Account_Name = request.getParameter("Financial_Account_Name");
			String financial_Account_Description = request.getParameter("Financial_Account_Description");
			///////////////populate the bean ///////////////////////
			
			Financial_Account_Details Financial_Account_Details = new Financial_Account_Details();
			Financial_Account_Details.setFinancial_Account_id(financial_Account_id);
			Financial_Account_Details.setFinancial_Account_Name(financial_Account_Name);
			Financial_Account_Details.setFinancial_Account_Description(financial_Account_Description);
			Financial_Account_Details.setFinancial_Category_id(financial_Category_id);
			Financial_Account_Details.setSchool_id(school_id);
			///////////////end populate the bean ///////////////////////
			if (financial_Account_id==0)
			{
				updateQuery = financialAccountingManagement.createFinancialAccount(Financial_Account_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Financial Account created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Financial Account name for the same school. Financial Account was not created";
		        	}
		        	else
		        	{
			        	message = "New Financial Account was not created";
		        	}
		        }
			}
			else
			{
				updateQuery = financialAccountingManagement.updateFinancialAccount(Financial_Account_Details, financial_Account_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Financial Account updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Financial Account name for the same school. Financial Account was not updated";
		        	}
		        	else
		        	{
		        		message = "Financial Account was not updated";
		        	}
		    	}		    		
			}
	
	    		
		}
		else
		{
			updateQuery = financialAccountingManagement.deleteFinancialAccount(financial_Account_id);
			if (updateQuery!=0)
				message = "Financial Account successfully deleted";
			else
				message = "Financial Account was not deleted";
		}
   		String address = "/Admin/Financial_Accounting/Manage_Financial_Accounts.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	
	}

}
