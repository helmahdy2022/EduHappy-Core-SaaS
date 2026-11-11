package mynaparrot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.core5.http.ParseException;

/**
 * Servlet implementation class CreateMeetingURL
 */
@WebServlet("/CreateMeetingURL")
public class CreateMeetingURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMeetingURL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String client_id = "helmahdy_";
		String meeting_id_Without_Client_Id = request.getParameter("meeting_id_Without_Client_Id");
		String meeting_id = client_id+ meeting_id_Without_Client_Id; 
		String attendeeName = request.getParameter("attendeeName");
		String meetingName = request.getParameter("meetingName");
		String teacherPass = request.getParameter("teacherPass");
		String studentPass = request.getParameter("studentPass");
		String jointType = request.getParameter("jointType");
    	MynaParrotApi mynaParrotApi = new MynaParrotApi();
		try {
			String url = mynaParrotApi.createMeeting(meeting_id, attendeeName, meetingName, teacherPass, studentPass, jointType);
			response.sendRedirect(url);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
