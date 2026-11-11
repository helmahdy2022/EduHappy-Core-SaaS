package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Routes_List;
import model.beans.admin.Transport_Fees;
import model.dbHelpers.admin.TransportManagement;

/**
 * Servlet implementation class Get_Transport_Fees
 */
@WebServlet("/Get_Transport_Fees")
public class Get_Transport_Fees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Transport_Fees() {
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
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null && !request.getParameter("School_Year_id").equals(""))
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}			
		TransportManagement transportManagement = new TransportManagement();
		/////////////////////////////Retrieve Routes///////////////////////////////
		Routes_List Routes_List = new Routes_List();
		Routes_List = transportManagement.getRoutesListBySchool(school_id);
    	request.setAttribute("Routes_List", Routes_List);			
		////////////////End Get Grades//////////////////////////////////////////////////////////

    	///////////////Get Transport Fees//////////////////////////////////////////////////////
    	Transport_Fees Transport_Fees = new Transport_Fees();
    	Transport_Fees = transportManagement.getTransportFees(school_id, school_Year_id);
    	request.setAttribute("Transport_Fees", Transport_Fees);	
    	///////////////End Get Fee Structure //////////////////////////////////////////////////
    	String address = "/Admin/Transport/Display_Transport_Fees.jsp";
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
