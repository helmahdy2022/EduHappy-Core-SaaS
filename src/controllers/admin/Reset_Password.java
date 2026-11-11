package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.account.AccountManagement;

/**
 * Servlet implementation class Reset_Password
 */
@WebServlet("/Reset_Password")
public class Reset_Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reset_Password() {
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
		int admin_id=0;
		if(request.getParameter("Admin_id")!=null)
		{
			admin_id = Integer.parseInt(request.getParameter("Admin_id"));
		}
		String Forget_Password_Token = request.getParameter("Forget_Password_Token");
		AccountManagement accountManagement = new AccountManagement();
		String Forget_Password = accountManagement.getPasswordToken(admin_id);
		String message=null;
		if(Forget_Password!=null&&Forget_Password.equals(Forget_Password_Token))
		{
			String new_Password = request.getParameter("New_Password");
			String confirm_New_Password = request.getParameter("Confirm_New_Password");
			if(new_Password==null||new_Password.length()<8||confirm_New_Password==null||confirm_New_Password.length()<8)
			{
				message="Password fields cannot be null and cannot be less than 8 characters, please fill in all passwords fields with no less than 8 characters and try again.<br/><a href='javascript:window.history.back();'>Back</a>";
			}
			else
			{
				if(new_Password.equals(confirm_New_Password))
				{
					
					int updateQuery = accountManagement.updateAdminPassword(admin_id, new_Password);
					
					if(updateQuery!=0)
					{
						message="Password updated successfully, Please <a href='"+request.getContextPath()+"/Admin/Admin_Login.jsp' style='color:red'>login here</a>";
					}
					else
					{
						message="Failed to update password, please try again";
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
			message="Failed to reset password. If you have reset your password before using the same reset password link then please request another reset password link";
		}
		
		String address = "/Admin/Display_Reset_Password.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
