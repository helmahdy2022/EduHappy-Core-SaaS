package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Messages_List;
import model.dbHelpers.admin.MessagesManagement;

/**
 * Servlet implementation class List_Messages
 */
@WebServlet("/List_Messages")
public class List_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String account_Owner = request.getParameter("Account_Owner");
		String user_Type = request.getParameter("User_Type");
		int account_id = 0;
		if(account_Owner.equals("Student"))
		{
			account_id = (Integer) session.getAttribute("Student_Session");
		}
		if(account_Owner.equals("Teacher"))
		{
			account_id = (Integer) session.getAttribute("Teacher_Session");
		}
		if(account_Owner.equals("Admin"))
		{
			account_id = (Integer) session.getAttribute("Admin_Session");
		}
		if(account_Owner.equals("Parent"))
		{
			account_id = (Integer) session.getAttribute("Parent_Session");
		}
		String address=null;
		if(request.getParameter("Group_Message")==null || !request.getParameter("Group_Message").equals("1"))
		{
			Messages_List messages_List = new Messages_List();
			int user_id = Integer.parseInt(request.getParameter("User_id"));
			MessagesManagement messagesManagement = new MessagesManagement();
			messages_List = messagesManagement.getMessages(user_id, account_id, user_Type, account_Owner);
			request.setAttribute("messages_List", messages_List);
			int newMessagesCount = messagesManagement.getNewMessagesCount(account_id, account_Owner);
			session.setAttribute("new"+account_Owner+"MessagesCount", newMessagesCount);
			address = "/"+account_Owner+"/Messages/Display_Messages.jsp?User_Type="+user_Type;
		}
		else
		{
			String Student_Group = request.getParameter("User_id");
			address = "/"+account_Owner+"/Messages/Display_Messages.jsp?Student_Group="+Student_Group;
		}

  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
