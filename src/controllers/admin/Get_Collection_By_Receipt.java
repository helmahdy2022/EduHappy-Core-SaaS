package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Payment_Amount;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class Get_Collection_By_Receipt
 */
@WebServlet("/Get_Collection_By_Receipt")
public class Get_Collection_By_Receipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Collection_By_Receipt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FeesManagement feesManagement = new FeesManagement();
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null && !request.getParameter("School_Year_id").equals(""))
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null && !request.getParameter("Grade_id").equals(""))
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		int receipt_id = 0;
		if(request.getParameter("Receipt_id")!=null && !request.getParameter("Receipt_id").equals(""))
		{
			receipt_id = Integer.parseInt(request.getParameter("Receipt_id"));
		}  	
    	/////////////////Get Fee Payments ////////////////////////////////////////////////////////
    	Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount();
    	Fee_Payment_Amount = feesManagement.getCollectionsByReceipt(grade_id, school_Year_id, receipt_id);
    	request.setAttribute("Fee_Payment_Amount", Fee_Payment_Amount);
    	/////////////////////////////////////////////////////////////////////////////////////////
    	
    	String address = "/Admin/Fees/Display_Collection_By_Receipt.jsp";
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
