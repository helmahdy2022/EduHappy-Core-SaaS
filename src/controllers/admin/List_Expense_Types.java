package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Expense_Types_List;
import model.dbHelpers.admin.ExpenseManagement;

/**
 * Servlet implementation class List_Expense_Types
 */
@WebServlet("/List_Expense_Types")
public class List_Expense_Types extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Expense_Types() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int school_id=0;
    	if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School_id"));
    	}
		ExpenseManagement expenseManagement = new ExpenseManagement();
		Expense_Types_List Expense_Types_List = new Expense_Types_List(); 
		Expense_Types_List = expenseManagement.getExpenseTypesList(school_id);
		request.setAttribute("Expense_Types_List", Expense_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	    String address = null;
	    if(request.getParameter("Dropdown")!=null &&!request.getParameter("Dropdown").equals(""))
	    {
	    	address="/Admin/Expenses/Display_Expense_Types_List.jsp";
	    }
	    else
	    {
	    	address="/Admin/Expenses/Display_Expense_Types.jsp";
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
