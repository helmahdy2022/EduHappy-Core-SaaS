package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Payment_Amount;
import model.beans.admin.Fee_Structure;
import model.beans.admin.Fee_Types_List;
import model.beans.admin.Transport_Payment_Amount;
import model.dbHelpers.admin.FeesManagement;



/**
 * Servlet implementation class Get_Collection_By_Fee
 */
@WebServlet("/Get_Collection_By_Fee")
public class Get_Collection_By_Fee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Collection_By_Fee() {
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
		/////////////////////////////retrieve List of Fee Types///////////////////////////////////////
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types/////////////////////////////////// 

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
		int fee_id = 0;
		if(request.getParameter("Fee_id")!=null && !request.getParameter("Fee_id").equals(""))
		{
			fee_id = Integer.parseInt(request.getParameter("Fee_id"));
		}
		///////////////Get Fee Structure //////////////////////////////////////////////////////
    	Fee_Structure Fee_Structure = new Fee_Structure();
    	Fee_Structure = feesManagement.getFeeStructurePerGrade(school_id, grade_id, school_Year_id);
    	request.setAttribute("Fee_Structure", Fee_Structure);
    	///////////////End Get Fee Structure //////////////////////////////////////////////////
    	
    	/////////////////Get Fee Payments ////////////////////////////////////////////////////////
    	Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount();
    	Fee_Payment_Amount = feesManagement.getCollectionsByFee(school_id, grade_id, school_Year_id, fee_id);
    	request.setAttribute("Fee_Payment_Amount", Fee_Payment_Amount);
    	/////////////////////////////////////////////////////////////////////////////////////////
 
    	/////////////////Get Transport Payments ////////////////////////////////////////////////////////
    	Transport_Payment_Amount Transport_Payment_Amount = new Transport_Payment_Amount();
    	if(fee_id==0 || fee_id==-1)
    	{
        	Transport_Payment_Amount = feesManagement.getTransportCollectionsByFee(school_id, grade_id, school_Year_id, fee_id);
        	request.setAttribute("Transport_Payment_Amount", Transport_Payment_Amount);
    	}
    	/////////////////////////////////////////////////////////////////////////////////////////
    	String address = "/Admin/Fees/Display_Collection_By_Fee.jsp";
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
