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
import model.beans.admin.Trial_Balance;
import model.dbHelpers.admin.FinancialAccountingManagement;

/**
 * Servlet implementation class Get_Trial_Balance
 */
@WebServlet("/Get_Trial_Balance")
public class Get_Trial_Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Trial_Balance() {
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
		String trial_Balance_Date_From=request.getParameter("Trial_Balance_Date_From");
		String trial_Balance_Date_To=request.getParameter("Trial_Balance_Date_To");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date dTrial_Balance_Date_From=null;
		Date dTrial_Balance_Date_To=null;
		try 
		{
			if(trial_Balance_Date_From!=null&&!trial_Balance_Date_From.equals(""))
				dTrial_Balance_Date_From = formater.parse(trial_Balance_Date_From);
			if(trial_Balance_Date_To!=null&&!trial_Balance_Date_To.equals(""))
				dTrial_Balance_Date_To = formater.parse(trial_Balance_Date_To);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
    	/////////////////////////////////////////////////////////////////////////
    	FinancialAccountingManagement financialAccountingManagement = new FinancialAccountingManagement();
    	Trial_Balance Trial_Balance = new Trial_Balance();
    	Trial_Balance = financialAccountingManagement.getTrialBalance(school_id, dTrial_Balance_Date_From, dTrial_Balance_Date_To);
    	request.setAttribute("Trial_Balance", Trial_Balance);
    	/////////////////////////////////////////////////////////////////////////////////////////
    	String address = "/Admin/Financial_Accounting/Display_Trial_Balance.jsp";
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
