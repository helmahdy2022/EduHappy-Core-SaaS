package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Fee_Types_List;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class Get_Fee_Types
 */
@WebServlet("/Get_Fee_Types")
public class Get_Fee_Types extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Fee_Types() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    /////////////////////////////Retrieve List of Fee Types///////////////////////////////
		HttpSession session= request.getSession();
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getAdminAccount(admin_id);
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(account_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	     String address = "/Admin/Fees/Fee_Types.jsp";
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
