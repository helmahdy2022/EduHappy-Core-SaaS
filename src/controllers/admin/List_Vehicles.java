package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Vehicles_List;
import model.dbHelpers.admin.TransportManagement;


/**
 * Servlet implementation class List_Vehicles
 */
@WebServlet("/List_Vehicles")
public class List_Vehicles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Vehicles() {
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
    	TransportManagement transportManagement = new TransportManagement();
		Vehicles_List Vehicles_List = new Vehicles_List(); 
		Vehicles_List = transportManagement.getVehiclesList(school_id);
		request.setAttribute("Vehicles_List", Vehicles_List); 	
		String address = null;
		if(request.getParameter("Dropdown")!=null && !request.getParameter("Dropdown").equals("")) 
	    {
	    	address = "/Admin/Transport/Display_Vehicles.jsp";
	    }
	    else
	    {
	    	address = "/Admin/Transport/Display_Vehicles_List.jsp";
	    }
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
