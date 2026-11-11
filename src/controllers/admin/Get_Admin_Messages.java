package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Admins;
import model.beans.admin.Senders;
import model.beans.admin.Teachers;
import model.dbHelpers.admin.MessagesManagement;

/**
 * Servlet implementation class Get_Admin_Messages
 */
@WebServlet("/Get_Admin_Messages")
public class Get_Admin_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Admin_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int recepient_Admin_id = (Integer) session.getAttribute("Admin_Session");
		Senders Senders = new Senders();
		MessagesManagement messagesManagement = new MessagesManagement();
		Senders = messagesManagement.getSenders(recepient_Admin_id, "Admin");
		request.setAttribute("Senders", Senders);
	
		Teachers Teachers = new Teachers();
		Teachers = messagesManagement.getTeachers(recepient_Admin_id);
		request.setAttribute("Teachers", Teachers);
		
		Admins Admins = new Admins();
		Admins = messagesManagement.getAdmins(recepient_Admin_id);
		request.setAttribute("Admins", Admins);

		int newMessagesCount = messagesManagement.getNewMessagesCount(recepient_Admin_id, "Admin");
		session.setAttribute("newAdminMessagesCount", newMessagesCount);
		
		String address = "/Admin/Messages/Admin_Messages.jsp";
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
