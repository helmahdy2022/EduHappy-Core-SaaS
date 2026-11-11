package controllers.admin;

import java.io.IOException;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Sessions;
import model.dbHelpers.admin.SessionManagement;

/**
 * Servlet implementation class Insert_Sessions
 */
@WebServlet("/Insert_Sessions")
public class Insert_Sessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_Sessions() {
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
		String message="";	
		int grade_id = Integer.parseInt(request.getParameter("Grade"));
		String Sessions_qty = request.getParameter("Sessions_qty");
		//int Sessions = -1;
		//if(Sessions_qty!=null)
		//{
			int Sessions = Integer.parseInt(Sessions_qty); 
		//}

		for (int i=1; i < Sessions+1; i++)
		{
			int session_id = 0;
			if(request.getParameter("Session_id"+i)!=null)
			{
				session_id = Integer.parseInt(request.getParameter("Session_id"+i));
			}
			
			String Time_From = request.getParameter("Time_From"+i);
			String Time_To  = request.getParameter("Time_To"+i);
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			if ((Time_From!=null)&&(Time_To!=null))
			{
				Time dTime_From=null;
				Time dTime_To=null;
				try {
					dTime_From = new Time(formater.parse(Time_From).getTime());
					dTime_To = new Time(formater.parse(Time_To).getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (dTime_To.compareTo(dTime_From)>0)
				{	
					int updateQuery = 0;
					SessionManagement sessionManagement = new SessionManagement();
					
					boolean Session_Allowed= sessionManagement.isSessionAllowed(dTime_From, dTime_To, session_id, grade_id);
					if (Session_Allowed==true)
					{
						Sessions sessionBean = new Sessions();
						sessionBean.setGrade_id(grade_id);
						sessionBean.setTime_From(dTime_From);
						sessionBean.setTime_To(dTime_To);
						if (session_id==0)
						{
							updateQuery = sessionManagement.createSession(sessionBean);
							if(updateQuery!=0)
							{
								message = message + "Session"+i+" Entered Successfully  <br>";
							}
							else
							{
								message = message + "Session"+i+" Not Entered <br>";
							}
								
						}
						else
						{
							updateQuery = sessionManagement.updateSession(sessionBean, session_id);
							if(updateQuery!=0)
							{
								message = message + "Session"+i+" Updated Successfully  <br>";
							}
							else
							{
								message = message + "Session"+i+" Not Updated <br>";
							}
								
							
						}							
					}
					else
					{
						message = message + "Session"+i+" Invalid Session time<br/>";
					}

											
				}
				else
				{
					message = message +"Session"+i+"Time_To must be after Time_From, session not entered <br>";
				}

			}

		}						

  		 String address = "/Admin/Schedule/Display_Insert_Sessions.jsp?message="+message;
  		 RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		 dispatcher.forward(request, response);
  		 return;	
	    }

}
