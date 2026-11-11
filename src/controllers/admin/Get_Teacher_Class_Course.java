package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Teacher_Details;
import model.dbHelpers.admin.ScheduleManagement;

/**
 * Servlet implementation class Get_Teacher_Class_Course
 */
@WebServlet("/Get_Teacher_Class_Course")
public class Get_Teacher_Class_Course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Teacher_Class_Course() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sCourse_id = null;
		String sGroup_id = null;
		int course_id = 0;
		int group_id = 0;
		
		String CG = request.getParameter("CG");
		if (CG!=null && !CG.equals(null) && !CG.equals("Break") && !CG.equals("Select Course"))
		{
			String[] parts = CG.split(",");
			sCourse_id = parts[0]; 
			sGroup_id = parts[1]; 
			//sCourse_id=sCourse_id.substring(1, sCourse_id.length()-1);
			//sGroup_id=sGroup_id.substring(1, sGroup_id.length()-1);	
			course_id = Integer.parseInt(sCourse_id);
			if(sGroup_id!=null && !sGroup_id.equals(""))
			{
				group_id = Integer.parseInt(sGroup_id);
			}			
		}		
		int  class_id = 0;
		if(request.getParameter("Class_id")!=null)
		{
			class_id = Integer.parseInt(request.getParameter("Class_id"));
		}		
		String  n = request.getParameter("n");
		String message = "" ; 
		if (CG!=null && !CG.equals("Select Course")&&!CG.equals("Break"))
		{
			ScheduleManagement scheduleManagement = new ScheduleManagement();
			Teacher_Details Teacher_Details = new Teacher_Details();
			Teacher_Details = scheduleManagement.getScheduleTeacher(class_id, course_id, group_id);
			int teacher_id = 0;
			teacher_id = Teacher_Details.getTeacher_id();
			if (teacher_id == 0)
			{
				message = message + "no teacher assigned";
			}
			else
			{
				message = message + Teacher_Details.getFirst_Name()+" "+ Teacher_Details.getLast_Name();
			}
			String address = null;
			address = "/Admin/Schedule/Display_Teacher_Class_Course.jsp?message="+message+"&&n="+n+"&&Teacher_id="+teacher_id;
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
