package controllers.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.account.Account_Details;
import model.dbHelpers.admin.TeacherManagement;

/**
 * Servlet implementation class Teacher_Change_Password
 */
@WebServlet("/Teacher_Change_Password")
public class Teacher_Change_Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_Change_Password() {
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
		String old_Password = request.getParameter("Old_Password");
		String new_Password = request.getParameter("New_Password");
		String confirm_New_Password = request.getParameter("Confirm_New_Password");
		String first_Login = request.getParameter("First_Login");
		HttpSession session= request.getSession();
		int teacher_id = (Integer) session.getAttribute("Teacher_Session");
		Account_Details teacher_Account_Details = (Account_Details) session.getAttribute("Teacher_Account_Details");
		String password = teacher_Account_Details.getPassword();
		String message=null;
		boolean passwordUpdated=false;
		if(old_Password==null||old_Password.length()<8||new_Password==null||new_Password.length()<8||confirm_New_Password==null||confirm_New_Password.length()<8)
		{
			message="Password fields cannot be null and cannot be less than 8 characters, please fill in all passwords fields with no less than 8 characters and try again.<br/><a href='javascript:window.history.back();'>Back</a>";
		}
		else
		{
			if(old_Password.equals(password))
			{
				if(password.equals(new_Password))
				{
					message="New Password cannot be the same as the old password. Please choose another password.<br/><a href='javascript:window.history.back();'>Back</a>";
				}
				else
				{
					if(new_Password.equals(confirm_New_Password))
					{
						TeacherManagement teacherManagement = new TeacherManagement();
						int updateQuery = teacherManagement.updateTeacherPassword(teacher_id, new_Password);
						
						if(updateQuery!=0)
						{
							message="Password updated successfully";
							teacher_Account_Details.setPassword(new_Password);
							teacher_Account_Details.setTeacher_First_Login(0);
							passwordUpdated=true;
						}
						else
						{
							message="Failed to update password, please try again.<br/><a href='javascript:window.history.back();'>Back</a>";
						}
					}
					else
					{
						message="New password is not matching the Confirm new password. Please try again.<br/><a href='javascript:window.history.back();'>Back</a>";
					}
				}
			}
			else
			{
				message = "Old password you have entered is not matching the password in the file. Please enter the correct password.<br/><a href='javascript:window.history.back();'>Back</a>";
			}
		}		
		String address=null;
		if(passwordUpdated==true&&first_Login!=null&&first_Login.equals("1"))
		{
			address = "/Teacher_Authority?message="+message;
		}
		else
		{
			address = "/Teacher/Profile/Display_Change_Password.jsp?message="+message;
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
