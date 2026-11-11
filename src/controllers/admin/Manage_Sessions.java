package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Sessions_Details;
import model.dbHelpers.admin.SessionManagement;


/**
 * Servlet implementation class Manage_Sessions
 */
@WebServlet("/Manage_Sessions")
public class Manage_Sessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Sessions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String  Sessions_no =null;
		Sessions_no = request.getParameter("Sessions_no");
		String last_i;
		String  Add_New =null;
		Add_New = request.getParameter("Add_New");
		int  grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		String  Delete = request.getParameter("Delete");
		int n;
		int Sessions;
		String No_Existing_Sessions = "0";
		String address;			
		SessionManagement sessionManagement = new SessionManagement();
		Sessions_Details Sessions_Details = new Sessions_Details();
		Sessions_Details = sessionManagement.getSessions(grade_id);
		if (((Sessions_Details.isSession_Exists()==false) || (Add_New!=null)) && Delete==null)
		{			
			if (Add_New!=null)
			{
				Sessions_no="1";
				last_i=request.getParameter("last_i");
			}
			else
			{
				last_i="0";
				No_Existing_Sessions = "1";
			}
			n = 1+ Integer.parseInt(last_i);
			Sessions = Integer.parseInt(Sessions_no)+Integer.parseInt(last_i);	
			address = "/Admin/Schedule/Display_Sessions_Details.jsp?n="+n+"&&Sessions="+Sessions+"&&No_Existing_Sessions="+No_Existing_Sessions+"&&Grade_id="+grade_id+"&&Function="+Add_New;
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else
		{
			int Session_qty = Sessions_Details.getSession_qty();
			if (Delete!=null)
			{
				//String Row_no = request.getParameter("i");
				int session_id=0;
				if(request.getParameter("Session_id")!=null && !request.getParameter("Session_id").equals("undefined") && !request.getParameter("Session_id").equals(""))
				{
					session_id = Integer.parseInt(request.getParameter("Session_id"));
					sessionManagement.deleteSession(session_id);
					address = "/Admin/Schedule/Display_Sessions_Details.jsp?Function=Delete";
					RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
					dispatcher.forward(request, response);
				}
			}
			else
			{
				request.setAttribute("Sessions_Details", Sessions_Details);
				address = "/Admin/Schedule/Display_Sessions_Details.jsp?Session_qty="+Session_qty+"&&Grade_id="+grade_id;
				RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
		}
			 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
