package controllers.teacher;

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
import model.dbHelpers.admin.TeacherManagement;


/**
 * Servlet implementation class Get_Teacher_Messages
 */
@WebServlet("/Get_Teacher_Messages")
public class Get_Teacher_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int recepient_Teacher_id = (Integer) session.getAttribute("Teacher_Session");
		Senders Senders = new Senders();
		MessagesManagement messagesManagement = new MessagesManagement();
		Senders = messagesManagement.getSenders(recepient_Teacher_id, "Teacher");
		request.setAttribute("Senders", Senders);
		TeacherManagement teacherManagement = new TeacherManagement();
		int admin_id = teacherManagement.getTeacherAdmin(recepient_Teacher_id);
		Teachers Teachers = new Teachers();
		Teachers = messagesManagement.getTeachers(admin_id);
		request.setAttribute("Teachers", Teachers);
		
		Admins Admins = new Admins();
		Admins = messagesManagement.getAdmins(admin_id);
		request.setAttribute("Admins", Admins);

		int newMessagesCount = messagesManagement.getNewMessagesCount(recepient_Teacher_id, "Teacher");
		session.setAttribute("newTeacherMessagesCount", newMessagesCount);
		
		String address = "/Teacher/Messages/Teacher_Messages.jsp";
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
