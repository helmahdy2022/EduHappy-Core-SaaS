package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Financial_Accounts_List;
import model.dbHelpers.admin.FinancialAccountingManagement;


/**
 * Servlet implementation class List_Financial_Accounts
 */
@WebServlet("/List_Financial_Accounts")
public class List_Financial_Accounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Financial_Accounts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int school_id=0;
    	if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School_id"));
    	}
		int financial_Category_id=0;
    	if(request.getParameter("Financial_Category_id")!=null && !request.getParameter("Financial_Category_id").equals(""))
    	{
    		financial_Category_id = Integer.parseInt(request.getParameter("Financial_Category_id"));
    	}
    	FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		Financial_Accounts_List Financial_Accounts_List = new Financial_Accounts_List(); 
		if(request.getParameter("Dropdown")!=null && !request.getParameter("Dropdown").equals(""))
		{
			Financial_Accounts_List = financialAccountingManagement.getAllFinancialAccountsList(school_id);
		}
		else
		{
			Financial_Accounts_List = financialAccountingManagement.getFinancialAccountsList(financial_Category_id, school_id);
		}
		
		request.setAttribute("Financial_Accounts_List", Financial_Accounts_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
		String address = null;
		if(request.getParameter("Dropdown")!=null && !request.getParameter("Dropdown").equals(""))
		{
			address = "/Admin/Financial_Accounting/Display_financial_Accounts.jsp";
		}
		else
		{
			address = "/Admin/Financial_Accounting/Display_financial_Accounts_List.jsp";
		}
		
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
