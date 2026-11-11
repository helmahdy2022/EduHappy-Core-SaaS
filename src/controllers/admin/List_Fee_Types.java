package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Types_List;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class List_Fee_Types
 */
@WebServlet("/List_Fee_Types")
public class List_Fee_Types extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Fee_Types() {
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
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
	    String address = "/Admin/Fees/Display_Fee_Types.jsp";
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
