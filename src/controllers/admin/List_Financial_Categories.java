package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Financial_Categories_List;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class List_Financial_Categories
 */
@WebServlet("/List_Financial_Categories")
public class List_Financial_Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Financial_Categories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		Financial_Categories_List Financial_Categories_List = new Financial_Categories_List(); 
		Financial_Categories_List = financialAccountingManagement.getFinancialCategoriesList();
		request.setAttribute("Financial_Categories_List", Financial_Categories_List); 	
		String address = "/Admin/Financial_Accounting/Display_Financial_Categories_List.jsp";
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
