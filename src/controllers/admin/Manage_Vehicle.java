package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Vehicle_Details;
import model.dbHelpers.admin.TransportManagement;


/**
 * Servlet implementation class Manage_Vehicle
 */
@WebServlet("/Manage_Vehicle")
public class Manage_Vehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Vehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
    	int vehicle_id=0;
    	if(request.getParameter("Vehicle_id")!=null && !request.getParameter("Vehicle_id").equals(""))
    	{
    		vehicle_id = Integer.parseInt(request.getParameter("Vehicle_id"));
    	}
    	int school_id=0;
    	if(request.getParameter("School")!=null && !request.getParameter("School").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School"));
    	}
		int admin_id = (Integer) session.getAttribute("Admin_Session");
/*		AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getAdminAccount(admin_id);*/		
		String vehicle_Name = request.getParameter("Vehicle_Name");					
		String registration_Number = request.getParameter("Registration_Number");
		String registration_Date = request.getParameter("Registration_Date");
		String registration_Expiry = request.getParameter("Registration_Expiry");
		String registration_Owner = request.getParameter("Registration_Owner");
		String chassis_Number = request.getParameter("Chassis_Number");
		String engine_Number = request.getParameter("Engine_Number");
		String model_Name = request.getParameter("Model_Name");
    	int model_Year=0;
    	if(request.getParameter("Model_Year")!=null && !request.getParameter("Model_Year").equals(""))
    	{
    		model_Year = Integer.parseInt(request.getParameter("Model_Year"));
    	}
    	int cubic_Capacity=0;
    	if(request.getParameter("Cubic_Capacity")!=null && !request.getParameter("Cubic_Capacity").equals(""))
    	{
    		cubic_Capacity = Integer.parseInt(request.getParameter("Cubic_Capacity"));
    	}
		String vehicle_Color = request.getParameter("Vehicle_Color");
		String insurance_Number = request.getParameter("Insurance_Number");
		String insurance_Date = request.getParameter("Insurance_Date");
		String insurance_Expiry = request.getParameter("Insurance_Expiry");
		///////////////populate the bean ///////////////////////
		Vehicle_Details Vehicle_Details = new Vehicle_Details();
		Vehicle_Details.setSchool_id(school_id);
		Vehicle_Details.setVehicle_Name(vehicle_Name);
		Vehicle_Details.setRegistration_Number(registration_Number);
		Vehicle_Details.setRegistration_Date(registration_Date);
		Vehicle_Details.setRegistration_Expiry(registration_Expiry);
		Vehicle_Details.setRegistration_Owner(registration_Owner);
		Vehicle_Details.setChassis_Number(chassis_Number);
		Vehicle_Details.setEngine_Number(engine_Number);
		Vehicle_Details.setModel_Name(model_Name);
		Vehicle_Details.setModel_Year(model_Year);
		Vehicle_Details.setCubic_Capacity(cubic_Capacity);
		Vehicle_Details.setVehicle_Color(vehicle_Color);
		Vehicle_Details.setInsurance_Number(insurance_Number);
		Vehicle_Details.setInsurance_Date(insurance_Date);
		Vehicle_Details.setInsurance_Expiry(insurance_Expiry);
		///////////////end populate the bean ///////////////////////
		TransportManagement transportManagement = new TransportManagement();
		int updateQuery = 0;
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if (ConfirmDelete == null)
		{
			if (vehicle_id==0)
			{
				
				updateQuery = transportManagement.createVehicle(Vehicle_Details);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "New Vehicle created successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Vehicle name. Vehicle not created";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. New Vehicle was not created, please try again. You may need to logout and login again to renew your session.";
		        	}	        	
		        }
				
			}
		    else
		    {
		    	updateQuery = transportManagement.updateVehicle(Vehicle_Details, vehicle_id);
		        if (updateQuery!=0 && updateQuery!=-1)
		        {
		        	message = "Vehicle updated successfully";
		        }
		        else
		        {
		        	if(updateQuery==-1)
		        	{
		        		message="Duplicate Entry for Vehicle name. Vehicle not updated";
		        	}
		        	else
		        	{
		        		message = "Something wrong happend. Vehicle was not updated, please try again. You may need to logout and login again to renew your session.";
		        	}		        	
		        }
		    }
		}
	        
		else
		{
			updateQuery = transportManagement.deleteVehicle(vehicle_id, admin_id);
			if(updateQuery!=0&&updateQuery!=-2)
			{
				message = "Vehicle successfully deleted";
			}
			else if (updateQuery==-2)
			{
				message = "You are not authorized to delete this Vehicle. Only account owner is authorized to delete Vehicles.";
			}
			else
			{
				message = "Something wrong happend. Vehicle was not deleted, please try again. You may need to logout and login again to renew your session.";
			}
			
		} 	
	     String address = "/Admin/Transport/Manage_Vehicle.jsp?message="+message;
    	 RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		 dispatcher.forward(request, response);
	}

}
