package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.admin.Message_Details;
import model.dbHelpers.admin.MessagesManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Manage_Messages
 */
@WebServlet("/Manage_Messages")
public class Manage_Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int account_id = 0;
		String account_Owner = request.getParameter("Account_Owner");
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
		request.setCharacterEncoding("UTF-8");
		String user_Type = request.getParameter("User_Type");
		int user_id=0;
		if(request.getParameter("Group_Message")==null || !request.getParameter("Group_Message").equals("1"))
		{
			user_id = Integer.parseInt(request.getParameter("User_id"));
			String message_Body = request.getParameter("message_Body");
			Message_Details message_Details = new Message_Details();
			message_Details.setAccount_id(account_id);
			message_Details.setAccount_Owner(account_Owner);
			int send_To_Parent = 0;
			if(request.getParameter("Send_To_Parent")!=null)
			{
				send_To_Parent = Integer.parseInt(request.getParameter("Send_To_Parent"));
			}
			if(send_To_Parent==1)
			{
				StudentManagement studentManagement = new StudentManagement();
				ArrayList<Integer> parent_id= new ArrayList<Integer>();
				parent_id = studentManagement.getStudentParent(user_id);
				for (int sparent_id : parent_id)
				{
					user_id = sparent_id;
					user_Type = "Parent";
					message_Details.setUser_id(user_id);
					message_Details.setUser_Type(user_Type);					
					message_Details.setMessage_Body(message_Body);
					MessagesManagement messagesManagement = new MessagesManagement();
					messagesManagement.createMessage(message_Details);
				}
				PrintWriter out = response.getWriter();
				out.print("Message sent to the selected student's parents");

			}
			else
			{
				message_Details.setUser_id(user_id);
				message_Details.setUser_Type(user_Type);				
				message_Details.setMessage_Body(message_Body);
				MessagesManagement messagesManagement = new MessagesManagement();
				messagesManagement.createMessage(message_Details);				
			}

		}
		else
		{
			PrintWriter out = response.getWriter();
			String[] users_id = request.getParameter("User_id").split(",");
			int send_To_Parent = 0;
			if(request.getParameter("Send_To_Parent")!=null)
			{
				send_To_Parent = Integer.parseInt(request.getParameter("Send_To_Parent"));
			}
			for (int i=0; i<users_id.length; i++)
			{
				String message_Body = request.getParameter("message_Body");
				Message_Details message_Details = new Message_Details();
				message_Details.setAccount_id(account_id);
				message_Details.setAccount_Owner(account_Owner);
				if(send_To_Parent==1)
				{
					StudentManagement studentManagement = new StudentManagement();
					ArrayList<Integer> parent_id= new ArrayList<Integer>();
					parent_id = studentManagement.getStudentParent(Integer.parseInt(users_id[i]));
					for (int sparent_id : parent_id)
					{
						user_id = sparent_id;
						user_Type = "Parent";
						message_Details.setUser_id(user_id);
						message_Details.setUser_Type(user_Type);					
						message_Details.setMessage_Body(message_Body);
						MessagesManagement messagesManagement = new MessagesManagement();
						messagesManagement.createMessage(message_Details);
					}
				}
				else
				{
					message_Details.setUser_id(Integer.parseInt(users_id[i]));
					message_Details.setUser_Type(user_Type);
					message_Details.setMessage_Body(message_Body);
					if(users_id.length>1)
					{
						message_Details.setGroup_Message(1);
					}			
					MessagesManagement messagesManagement = new MessagesManagement();
					messagesManagement.createMessage(message_Details);
				}
			}
			if(send_To_Parent==0)
				out.print("Message sent to the selected students");
			else
				out.print("Message sent to the selected student's parents");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
