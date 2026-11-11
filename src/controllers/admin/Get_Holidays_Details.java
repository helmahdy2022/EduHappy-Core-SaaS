package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Holidays_Details;
import model.dbHelpers.admin.HolidayManagement;

/**
 * Servlet implementation class Get_Holidays_Details
 */
@WebServlet("/Get_Holidays_Details")
public class Get_Holidays_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Holidays_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int i = 0;
		HttpSession session= request.getSession();
		String  Holidays_no =null;
		Holidays_no = request.getParameter("Holidays_no");
		int holidays_qty = 0;
		if(request.getParameter("Holidays_qty")!=null)
		{
			holidays_qty= Integer.parseInt(request.getParameter("Holidays_qty"));
		}
		String last_i=null;
		last_i=request.getParameter("last_i");
		String  Add_New =null;
		Add_New = request.getParameter("Add_New");
		int country_id = 0;
		if(request.getParameter("Country_id")!=null)
		{
			country_id = Integer.parseInt(request.getParameter("Country_id"));
		}
		String  Delete = request.getParameter("Delete");
		int n;
		int Holidays;
		String No_Existing_Holidays = "0";
		String address;
		HolidayManagement holidayManagement = new HolidayManagement();
		boolean holidaysExist = holidayManagement.isHolidaysExist(country_id, (Integer) session.getAttribute("Admin_Session"));
		if (((!holidaysExist) || (Add_New!=null)) && Delete==null)
		{
			if (Add_New!=null)
			{
				Holidays_no="1";
			}
			else
			{
				last_i="0";
				No_Existing_Holidays = "1";
			}
			n = 1+ Integer.parseInt(last_i);
			Holidays = Integer.parseInt(Holidays_no)+Integer.parseInt(last_i);	
			address = "/Admin/Academic_Year/Display_Holidays_Details.jsp?n="+n+"&&Holidays="+Holidays+"&&No_Existing_Holidays="+No_Existing_Holidays;
	  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	  		dispatcher.forward(request, response);
		}
		else
		{
			if (Delete!=null)
			{
				//String Row_no = request.getParameter("i");
				//resultExistingHolidays.absolute(Integer.parseInt(Row_no));
				int holiday_id= 0;
				if(request.getParameter("Holiday_id")!=null&&!request.getParameter("Holiday_id").equals("undefined"))
				{
					holiday_id = Integer.parseInt(request.getParameter("Holiday_id"));
					//Delete
					int updateQuery = holidayManagement.deleteHoliday(holiday_id);
					if(updateQuery!=0)
					{
						address = "/Admin/Academic_Year/Display_Holidays_Details.jsp?Function=Delete";
				  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				  		dispatcher.forward(request, response);	
					}
				}
				//if (Integer.parseInt(Row_no)<=Integer.parseInt(last_i))
				//{
					
				//}
			}
			else
			{
				Holidays_Details Holidays_Details = new Holidays_Details();
				Holidays_Details = holidayManagement.getHolidayDetails(country_id, (Integer) session.getAttribute("Admin_Session"));
				holidays_qty = Holidays_Details.getExistingHolidays().size();
				request.setAttribute("Holidays_Details", Holidays_Details);
				address = "/Admin/Academic_Year/Display_Holidays_Details.jsp?Holiday_qty="+holidays_qty;
		  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		  		dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
