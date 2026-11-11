package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check_Session
 */
@WebServlet("/Check_Session")
public class Check_Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = request.getParameter("User");
		if((session.getAttribute("Teacher_Session")==null&&user!=null&&user.equals("Teacher"))
			||(session.getAttribute("Admin_Session")==null&&user!=null&&user.equals("Admin"))
			||(session.getAttribute("Student_Session")==null&&user!=null&&user.equals("Student"))
			||(session.getAttribute("Parent_Session")==null&&user!=null&&user.equals("Parent")))
		{
			PrintWriter out= response.getWriter();
			out.print("SessionExpired");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
