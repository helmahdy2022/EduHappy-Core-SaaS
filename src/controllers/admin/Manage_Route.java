package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Route_Details;
import model.dbHelpers.admin.TransportManagement;

/**
 * Servlet implementation class Manage_Route
 */
@WebServlet("/Manage_Route")
public class Manage_Route extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Route() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int route_id=0;
		if(request.getParameter("Route_id")!=null && !request.getParameter("Route_id").equals(""))
		{
			route_id = Integer.parseInt(request.getParameter("Route_id"));
		}
		String message = null;

		TransportManagement transportManagement = new TransportManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			
			int vehicle_id=0;
			if(request.getParameter("Vehicle_id")!=null && !request.getParameter("Vehicle_id").equals(""))
			{
				vehicle_id = Integer.parseInt(request.getParameter("Vehicle_id"));
			}
			int driver_id=0;
			if(request.getParameter("Driver_id")!=null && !request.getParameter("Driver_id").equals(""))
			{
				driver_id = Integer.parseInt(request.getParameter("Driver_id"));
			}
			int matron_id=0;
			if(request.getParameter("Matron_id")!=null && !request.getParameter("Matron_id").equals(""))
			{
				matron_id = Integer.parseInt(request.getParameter("Matron_id"));
			}
			String route_Name = request.getParameter("Route_Name");
			int debit_Account_id=0;
			if(request.getParameter("Debit_Account_id")!=null && !request.getParameter("Debit_Account_id").equals(""))
			{
				debit_Account_id = Integer.parseInt(request.getParameter("Debit_Account_id"));
			}
			int credit_Account_id=0;
			if(request.getParameter("Credit_Account_id")!=null && !request.getParameter("Credit_Account_id").equals(""))
			{
				credit_Account_id = Integer.parseInt(request.getParameter("Credit_Account_id"));
			}
			///////////////populate the bean ///////////////////////
			
			Route_Details Route_Details = new Route_Details();
			Route_Details.setRoute_id(route_id);
			Route_Details.setRoute_Name(route_Name);
			Route_Details.setDriver_id(driver_id);
			Route_Details.setMatron_id(matron_id);
			Route_Details.setVehicle_id(vehicle_id);
			Route_Details.setDebit_Account_id(debit_Account_id);
			Route_Details.setCredit_Account_id(credit_Account_id);
			///////////////end populate the bean ///////////////////////
			if(debit_Account_id==credit_Account_id)
			{
				message="Debit Account cannot be the same as Credit Account. Vehicle Route not created/updated";
			}
			else
			{
				if (route_id==0)
				{
					updateQuery = transportManagement.createRoute(Route_Details);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "New Vehicle Route created successfully";
			        }
			        else
			        {
			        	if(updateQuery==-1)
			        	{
			        		message = "Duplicate Entry for the Route name for the same Vehicle. Vehicle Route was not created";
			        	}
			        	else
			        	{
				        	message = "New Vehicle Route was not created";
			        	}
			        }
				}
				else
				{
					updateQuery = transportManagement.updateRoute(Route_Details, route_id);
			    	if (updateQuery!=0 && updateQuery!=-1)
			    		message = "Vehicle Route updated successfully";
			    	else
			    	{
			        	if(updateQuery==-1)
			        	{
			        		message = "Duplicate Entry for the Route name for the same Vehicle. Vehicle Route was not updated";
			        	}
			        	else
			        	{
			        		message = "Vehicle Route was not updated";
			        	}
			    	}		    		
				}
			}
	
	    		
		}
		else
		{
			updateQuery = transportManagement.deleteRoute(route_id);
			if (updateQuery!=0)
				message = "Vehicle Route successfully deleted";
			else
				message = "Vehicle Route was not deleted";
		}
   		String address = "/Admin/Transport/Manage_Route.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
