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
import model.beans.admin.General_Ledger;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class Get_General_Ledger
 */
@WebServlet("/Get_General_Ledger")
public class Get_General_Ledger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_General_Ledger() {
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
		int financial_Account_id = 0;
		if(request.getParameter("Financial_Account_id")!=null && !request.getParameter("Financial_Account_id").equals(""))
		{
			financial_Account_id = Integer.parseInt(request.getParameter("Financial_Account_id"));
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String general_Ledger_Date_From=request.getParameter("General_Ledger_Date_From");
		String general_Ledger_Date_To=request.getParameter("General_Ledger_Date_To");
		Date dGeneral_Ledger_Date_From=null;
		Date dGeneral_Ledger_Date_To=null;
		try 
		{
			if(general_Ledger_Date_From!=null&&!general_Ledger_Date_From.equals(""))
			dGeneral_Ledger_Date_From = formater.parse(general_Ledger_Date_From);
			if(general_Ledger_Date_To!=null&&!general_Ledger_Date_To.equals(""))
			dGeneral_Ledger_Date_To = formater.parse(general_Ledger_Date_To);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
    	/////////////////Get Fee Payments ////////////////////////////////////////////////////////
    	FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
    	General_Ledger General_Ledger = new General_Ledger();
    	General_Ledger = financialAccountingManagement.getGeneralLedger(school_id, financial_Account_id, dGeneral_Ledger_Date_From, dGeneral_Ledger_Date_To);
    	request.setAttribute("General_Ledger", General_Ledger);
    	/////////////////////////////////////////////////////////////////////////////////////////
    	String address = "/Admin/Financial_Accounting/Display_General_Ledger.jsp";
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
