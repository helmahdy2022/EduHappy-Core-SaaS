package controllers.parent;

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
import model.beans.parent.Child;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.admin.ParentManagement;


/**
 * Servlet implementation class Get_Parent_Messages
 */
@WebServlet("/Get_Parent_Messages")
public class Get_Parent_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Parent_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int recepient_Parent_id = (Integer) session.getAttribute("Parent_Session");
		Senders Senders = new Senders();
		MessagesManagement messagesManagement = new MessagesManagement();
		Senders = messagesManagement.getSenders(recepient_Parent_id, "Parent");
		request.setAttribute("Senders", Senders);
		
		Child child = new Child();
		child = (Child) session.getAttribute("child");
		Teachers Teachers = new Teachers();
		Teachers = messagesManagement.getTeachersForParent(child);
		request.setAttribute("Teachers", Teachers);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		ParentManagement parentManagement = new ParentManagement();
		Admins Admins = new Admins();
		Admins = messagesManagement.getAdminsForParent(parentManagement.getParentAccount(recepient_Parent_id));
		request.setAttribute("Admins", Admins);

		int newMessagesCount = messagesManagement.getNewMessagesCount(recepient_Parent_id, "Parent");
		session.setAttribute("newStudentMessagesCount", newMessagesCount);
		
		String address = "/Parent/Messages/Parent_Messages.jsp";
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
