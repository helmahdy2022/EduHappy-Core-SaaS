package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Expenses_List;
import model.dbHelpers.admin.ExpenseManagement;

/**
 * Servlet implementation class List_Expenses
 */
@WebServlet("/List_Expenses")
public class List_Expenses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Expenses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int expense_Type_id=0;
    	if(request.getParameter("Expense_Type_id")!=null && !request.getParameter("Expense_Type_id").equals(""))
    	{
    		expense_Type_id = Integer.parseInt(request.getParameter("Expense_Type_id"));
    	}
    	ExpenseManagement expenseManagement = new ExpenseManagement();
		Expenses_List Expenses_List = new Expenses_List(); 
		Expenses_List = expenseManagement.getExpensesList(expense_Type_id);
		request.setAttribute("Expenses_List", Expenses_List); 	
		String address = "/Admin/Expenses/Display_Expenses_List.jsp";
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
