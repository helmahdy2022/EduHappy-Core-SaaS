package controllers.student;

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
import model.beans.admin.Students;
import model.beans.admin.Teachers;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Get_Student_Messages
 */
@WebServlet("/Get_Student_Messages")
public class Get_Student_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Student_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int recepient_Student_id = (Integer) session.getAttribute("Student_Session");
		Senders Senders = new Senders();
		MessagesManagement messagesManagement = new MessagesManagement();
		Senders = messagesManagement.getSenders(recepient_Student_id, "Student");
		request.setAttribute("Senders", Senders);
		
		Students Students = new Students();
		StudentManagement studentManagement = new StudentManagement();
		String class_id = null;
		class_id = studentManagement.getStudentClass(recepient_Student_id);
		int admin_id = studentManagement.getStudentAdmin(recepient_Student_id);
		Students = studentManagement.getStudentsList("%", "%", "%", "%","%", class_id,"%","%", admin_id);
    	request.setAttribute("Students", Students);
    	
    	//int admin_id = studentManagement.getStudentAdmin(recepient_Student_id);
		Teachers Teachers = new Teachers();
		Teachers = messagesManagement.getTeachers(admin_id);
		request.setAttribute("Teachers", Teachers);
		
		Admins Admins = new Admins();
		Admins = messagesManagement.getAdmins(admin_id);
		request.setAttribute("Admins", Admins);

		int newMessagesCount = messagesManagement.getNewMessagesCount(recepient_Student_id, "Student");
		session.setAttribute("newStudentMessagesCount", newMessagesCount);
		
		String address = "/Student/Messages/Student_Messages.jsp";
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
