package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Journal_Entry;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class Manage_Journal_Entry
 */
@WebServlet("/Manage_Journal_Entry")
public class Manage_Journal_Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Journal_Entry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");	
		int entry_id=0;
		if(request.getParameter("Journal_Entry_id")!=null && !request.getParameter("Journal_Entry_id").equals(""))
		{
			entry_id = Integer.parseInt(request.getParameter("Journal_Entry_id"));
		}
		String message = null;
		int school_id=0;
		if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
		{
			school_id = Integer.parseInt(request.getParameter("School"));
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
		String entry_Date=request.getParameter("Entry_Date");
		double journal_Amount = -1;
		if(request.getParameter("Journal_Amount")!=null && !request.getParameter("Journal_Amount").equals(""))
		{
			journal_Amount = Double.parseDouble(request.getParameter("Journal_Amount"));
		}
		String journal_Details = request.getParameter("Journal_Details");
		
		FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			if(debit_Account_id==credit_Account_id)
			{
				message = "Debit Account cannot be the same as Credit Account. Journal Entry not created/updated.";
			}
			else
			{
				///////////////populate the bean ///////////////////////			
				Journal_Entry Journal_Entry = new Journal_Entry();
				Journal_Entry.setEntry_id(entry_id);
				Journal_Entry.setDebit_Account_id(debit_Account_id);
				Journal_Entry.setCredit_Account_id(credit_Account_id);
				Journal_Entry.setEntry_Date(entry_Date);
				Journal_Entry.setJournal_Amount(journal_Amount);
				Journal_Entry.setJournal_Details(journal_Details);
				Journal_Entry.setSchool_id(school_id);
				///////////////end populate the bean ///////////////////////
				if (entry_id==0)
				{
					updateQuery = financialAccountingManagement.createJournalEntry(Journal_Entry);
			        if (updateQuery!=0)
			        {
			        	message = "New Journal Entry created successfully";
			        }
			        else
			        {
				        message = "New Journal Entry was not created";
			        }
				}
				else
				{
					updateQuery = financialAccountingManagement.updateJournalEntry(Journal_Entry, entry_id);
			    	if (updateQuery!=0)
			    		message = "Journal Entry updated successfully";
			    	else
			    	{
			        	message = "Journal Entry was not updated";
			    	}		    		
				}				
			}	    		
		}
		else
		{
			updateQuery = financialAccountingManagement.deleteJournalEntry(entry_id, admin_id);

			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Journal Entry successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Journal Entry. Only account owner is authorized to delete Expenses.";
			}
			else
			{
				message = "Something wrong happend. Journal Entry was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
		}
   		String address = "/Admin/Financial_Accounting/Journal_Entry.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
