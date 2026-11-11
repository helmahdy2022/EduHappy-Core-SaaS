package controllers.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Balance_Sheet;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class Get_Balance_Sheet
 */
@WebServlet("/Get_Balance_Sheet")
public class Get_Balance_Sheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Balance_Sheet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id = 0;
		if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
		{
			school_id = Integer.parseInt(request.getParameter("School_id"));
		}
		String balance_Sheet_Date_From=request.getParameter("Balance_Sheet_Date_From");
		String balance_Sheet_Date_To=request.getParameter("Balance_Sheet_Date_To");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dBalance_Sheet_Date_From=null;
		Date dBalance_Sheet_Date_To=null;
		try 
		{
			if(balance_Sheet_Date_From!=null&&!balance_Sheet_Date_From.equals(""))
				dBalance_Sheet_Date_From = formater.parse(balance_Sheet_Date_From);
			if(balance_Sheet_Date_To!=null&&!balance_Sheet_Date_To.equals(""))
				dBalance_Sheet_Date_To = formater.parse(balance_Sheet_Date_To);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		Balance_Sheet Balance_Sheet = new Balance_Sheet();
		Balance_Sheet = financialAccountingManagement.getBalanceSheet(school_id, dBalance_Sheet_Date_From,dBalance_Sheet_Date_To);
    	request.setAttribute("Balance_Sheet", Balance_Sheet);
    	
		String address = "/Admin/Financial_Accounting/Display_Balance_Sheet.jsp";
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
