package controllers.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dbHelpers.admin.DaysoffManagement;

/**
 * Servlet implementation class Manage_Daysoff
 */
@WebServlet("/Manage_Daysoff")
public class Manage_Daysoff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Daysoff() {
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
		String message = "";
		int daysoff_id = 0;
		if(request.getParameter("Daysoff_id")!=null)
		{
			daysoff_id = Integer.parseInt(request.getParameter("Daysoff_id"));
		}
		String Daysoff_Start = request.getParameter("Daysoff_Start");
		String Daysoff_End = request.getParameter("Daysoff_End");
		String daysoff_Name = request.getParameter("Daysoff_Name");	
		int all_Schools = 0;
		if(request.getParameter("All_Schools")!=null)
		{
			all_Schools = 1;
		}
			 		
		//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		if ((Daysoff_Start!=null)&&(Daysoff_End!=null)&&(!Daysoff_Start.equals(""))&&(!Daysoff_End.equals("")))
		{
			Date dDaysoff_Start=null;
			Date dDaysoff_End=null;
			try {
				dDaysoff_Start = formater.parse(Daysoff_Start);
				dDaysoff_End = formater.parse(Daysoff_End);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DaysoffManagement daysoffManagement = new DaysoffManagement();
			message = daysoffManagement.manageDaysoff(daysoff_id, admin_id, daysoff_Name, dDaysoff_Start, dDaysoff_End, all_Schools, request);

		}
		else
		{
			message = message + "Please enter valid day off dates";
		}
		
    	
	String address = "/Admin/Academic_Year/Display_Insert_Daysoff.jsp?message="+message;
	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
	dispatcher.forward(request, response);
	}

}
