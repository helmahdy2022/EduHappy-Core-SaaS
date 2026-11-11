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
import model.beans.admin.Income_Statement;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class Get_Income_Statement
 */
@WebServlet("/Get_Income_Statement")
public class Get_Income_Statement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Income_Statement() {
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
		String income_Statement_Date_From=request.getParameter("Income_Statement_Date_From");
		String income_Statement_Date_To=request.getParameter("Income_Statement_Date_To");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dIncome_Statement_Date_From=null;
		Date dIncome_Statement_Date_To=null;
		try 
		{
			if(income_Statement_Date_From!=null&&!income_Statement_Date_From.equals(""))
				dIncome_Statement_Date_From = formater.parse(income_Statement_Date_From);
			if(income_Statement_Date_To!=null&&!income_Statement_Date_To.equals(""))
				dIncome_Statement_Date_To = formater.parse(income_Statement_Date_To);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
		Income_Statement Income_Statement = new Income_Statement();
		Income_Statement = financialAccountingManagement.getIncomeStatement(school_id, dIncome_Statement_Date_From, dIncome_Statement_Date_To);
    	request.setAttribute("Income_Statement", Income_Statement);
    	
		String address = "/Admin/Financial_Accounting/Display_Income_Statement.jsp";
    	RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
