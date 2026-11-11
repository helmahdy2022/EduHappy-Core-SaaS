package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Daysoff;
import model.dbHelpers.admin.DaysoffManagement;

/**
 * Servlet implementation class List_Daysoff
 */
@WebServlet("/List_Daysoff")
public class List_Daysoff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Daysoff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		DaysoffManagement daysoffManagement = new DaysoffManagement();
		Daysoff Daysoff = new Daysoff();
		Daysoff = daysoffManagement.getDaysoffList((Integer) session.getAttribute("Admin_Session"));
    	request.setAttribute("Daysoff", Daysoff);
		String address;
			 
		address = "/Admin/Academic_Year/Edit_Daysoff.jsp";
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
