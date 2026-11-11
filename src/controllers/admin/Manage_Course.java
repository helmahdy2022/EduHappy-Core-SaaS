package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Course_Details;
import model.dbHelpers.admin.CourseManagement;

/**
 * Servlet implementation class Manage_Course
 */
@WebServlet("/Manage_Course")
public class Manage_Course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Course() {
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
		request.setCharacterEncoding("UTF-8");
		int course_id=0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
		}
		String message = null;

		CourseManagement courseManagement = new CourseManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		if(ConfirmDelete==null)
		{
			int	grade_id = Integer.parseInt(request.getParameter("Grade"));
			String course_Name = request.getParameter("Course_Name");
			int course_Grade = Integer.parseInt(request.getParameter("Course_Grade"));
			int attendance_Weight = Integer.parseInt(request.getParameter("Attendance_Weight"));
			int attendance_Grade = Integer.parseInt(request.getParameter("Attendance_Grade"));
			int present_Grade = Integer.parseInt(request.getParameter("Present_Grade"));
			int absent_Grade =Integer.parseInt(request.getParameter("Absent_Grade"));
			int late_Grade = Integer.parseInt(request.getParameter("Late_Grade"));
			int excused_Grade = Integer.parseInt(request.getParameter("Excused_Grade"));
			int price = 0;
			if(request.getParameter("Price")!=null && request.getParameter("Price")!="")
			{
				price = Integer.parseInt(request.getParameter("Price")); 
			}			
			int contribute = 0;
			if(request.getParameter("Contribute")!=null)
			{
				contribute = 1;
			}
			int report_Display = 0;
			if(request.getParameter("Report_Display")!=null || contribute == 1)
			{
				report_Display = 1;
			}
			int subject_id = 0;
			if(request.getParameter("Subject_id")!=null && request.getParameter("Subject_id")!="")
			{
				subject_id = Integer.parseInt(request.getParameter("Subject_id"));
			}

			int elective = Integer.parseInt(request.getParameter("Elective"));
			if(attendance_Grade < present_Grade || attendance_Grade < absent_Grade || attendance_Grade < late_Grade || attendance_Grade < excused_Grade)
			{
				message = "Attendance Grade cannot be less than any one of the satuses grades. Please correct the numbers and try again.";
			}
			else
			{
				///////////////populate the bean ///////////////////////
				
				Course_Details course_Details = new Course_Details();
				course_Details.setCourse_id(course_id);
				course_Details.setGrade_id(grade_id);
				course_Details.setCourse_Name(course_Name);
				course_Details.setCourse_Grade(course_Grade);
				course_Details.setAttendance_Weight(attendance_Weight);
				course_Details.setAttendance_Grade(attendance_Grade);
				course_Details.setPresent_Grade(present_Grade);
				course_Details.setAbsent_Grade(absent_Grade);
				course_Details.setLate_Grade(late_Grade);
				course_Details.setExcused_Grade(excused_Grade);
				course_Details.setContribute(contribute);
				course_Details.setReport_Display(report_Display);
				course_Details.setSubject_id(subject_id);
				course_Details.setElective(elective);
				course_Details.setPrice(price);
				///////////////end populate the bean ///////////////////////
				if (course_id==0)
				{
					updateQuery = courseManagement.createCourse(course_Details);
			        if (updateQuery!=0 && updateQuery!=-1)
			        {
			        	message = "New Course created successfully";
			        }
			        else
			        {
			        	if(updateQuery == -1)
			        	{
			        		message = "Duplicate Entry for the Course for the same Grade. Course was not created";
			        	}
			        	else
			        	{
			        		message = "New Course was not created";
			        	}
			        }
				}
				else
				{
					updateQuery = courseManagement.updateCourse(course_Details, course_id);
			    	if (updateQuery!=0 && updateQuery!=-1)
			    	{
			    		message = "Course updated successfully";
			    	}	
			    	else
			    	{
			        	if(updateQuery == -1)
			        	{
			        		message = "Duplicate Entry for the Course for the same Grade. Course was not updated";
			        	}
			        	else
			        	{
			        		message = "Course was not updated";
			        	}
			    	}
				}
			}	
		}
		else
		{
			updateQuery = courseManagement.deleteCourse(course_id);
			if (updateQuery!=0)
				message = "Course successfully deleted";
			else
				message = "Course was not deleted";
		}
   		String address = "/Admin/Courses/Display_Manage_Course.jsp?message="+message;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

}
