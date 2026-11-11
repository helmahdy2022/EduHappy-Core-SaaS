package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Routes_List;
import model.dbHelpers.admin.TransportManagement;


/**
 * Servlet implementation class List_Routes
 */
@WebServlet("/List_Routes")
public class List_Routes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Routes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int vehicle_id=0;
    	if(request.getParameter("Vehicle_id")!=null && !request.getParameter("Vehicle_id").equals(""))
    	{
    		vehicle_id = Integer.parseInt(request.getParameter("Vehicle_id"));
    	}
		TransportManagement transportManagement = new TransportManagement();
		Routes_List Routes_List = new Routes_List(); 
		Routes_List = transportManagement.getRoutesList(vehicle_id);
		request.setAttribute("Routes_List", Routes_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
		 String address = "/Admin/Transport/Display_Routes_List.jsp";
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
