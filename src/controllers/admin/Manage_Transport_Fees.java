package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dbHelpers.admin.TransportManagement;

/**
 * Servlet implementation class Manage_Transport_Fees
 */
@WebServlet("/Manage_Transport_Fees")
public class Manage_Transport_Fees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Transport_Fees() {
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
		int routes_Count=0;
		if(request.getParameter("Routes_Count")!=null)
		{
			routes_Count = Integer.parseInt(request.getParameter("Routes_Count"));
		}
		int school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		for (int i=1; i < routes_Count+1; i++)
		{		
			int route_id=0;
			if(request.getParameter("Route_id"+i)!=null)
			{
				route_id = Integer.parseInt(request.getParameter("Route_id"+i));
			}

			double transport_Fees = -1;
			if(request.getParameter("Transport_Fees"+i)!=null && !request.getParameter("Transport_Fees"+i).equals(""))
			{
				transport_Fees = Double.parseDouble(request.getParameter("Transport_Fees"+i));
			}
			
			TransportManagement transportManagement = new TransportManagement();
			transportManagement.manageTransportFees(route_id, school_Year_id, transport_Fees);
		
		}
		String address = "/Admin/Transport/Transport_Fees.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
