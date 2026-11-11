package controllers.admin;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.admin.HolidayManagement;

/**
 * Servlet implementation class Manage_Holiday
 */
@WebServlet("/Manage_Holiday")
public class Manage_Holiday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Holiday() {
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
		int admin_id = (Integer) session.getAttribute("Admin_Session");
		String message=null;	
		int country_id = 0;
		if(request.getParameter("Country_id")!=null)
		{
			country_id = Integer.parseInt(request.getParameter("Country_id"));
		}
		String Holidays_qty = request.getParameter("Holidays_qty");
		String delete = request.getParameter("Delete");
		int holidays=0;
		if(Holidays_qty!=null&&!Holidays_qty.equals("null"))
		{
			holidays = Integer.parseInt(Holidays_qty); 			
		}

		HolidayManagement holidayManagement = new HolidayManagement();
		try {
			message = holidayManagement.manageHoliday(country_id, holidays, delete, request, admin_id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		String address = "/Admin/Academic_Year/Display_Insert_Holiday.jsp?message="+message;
 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
 		dispatcher.forward(request, response);
 		return;	
	}

}
