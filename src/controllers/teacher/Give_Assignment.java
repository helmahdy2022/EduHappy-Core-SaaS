package controllers.teacher;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.School_Year_Dates;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.teacher.AssignmentManagement;

/**
 * Servlet implementation class Give_Assignment
 */
@WebServlet("/Give_Assignment")
public class Give_Assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Give_Assignment() {
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
		int grade_id=Integer.parseInt(request.getParameter("Grade_id"));
		int class_id=Integer.parseInt(request.getParameter("Class_id"));
		int course_id=Integer.parseInt(request.getParameter("Course_id"));
		int group_id=0;
		if(request.getParameter("Group_id")!=null && !request.getParameter("Group_id").equals(""))
		{
			group_id = Integer.parseInt(request.getParameter("Group_id"));
		}	
		int lesson_id=0;
		if(request.getParameter("Lesson_id")!=null)
		{
			lesson_id  = Integer.parseInt(request.getParameter("Lesson_id"));
		}
		int category_id=0;
		if(request.getParameter("Category_id")!=null)
		{
			category_id = Integer.parseInt(request.getParameter("Category_id"));
		}
		int online_Submission_Option = 0;
		if(request.getParameter("Online_Submission_Option")!=null)
		{
			online_Submission_Option = 1;
		}
		String Submit=request.getParameter("Submit");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String Display_Date= request.getParameter("Display_Date");

		String Start_Date= request.getParameter("Start_Date");	
		String Due_Date= request.getParameter("Due_Date");
		String Disappear_Date= request.getParameter("Disappear_Date");
		Date display_Date=null;
		Date start_Date = null;
		Date due_Date = null;
		Date disappear_Date = null;
		try 
		{
			if(Display_Date!=null&&!Display_Date.equals(""))
				display_Date = formater.parse(Display_Date);
			if(Start_Date!=null&&!Start_Date.equals(""))
				start_Date = formater.parse(Start_Date);
			if(Due_Date!=null&&!Due_Date.equals(""))
				due_Date = formater.parse(Due_Date);
			if(Disappear_Date!=null&&!Disappear_Date.equals(""))
				disappear_Date = formater.parse(Disappear_Date);
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}

   		///////////////Get the current school year dates //////////////////////////////
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		School_Year_Dates school_Year_Dates = new School_Year_Dates();
		school_Year_Dates = schoolYearManagement.getSchoolYearDates(grade_id);
    	Date year_Start = school_Year_Dates.getYear_Start();
    	Date year_End = school_Year_Dates.getYear_End();
		//////////////////////////////////////////////////////////////////////////////
		///////////////Validate Dates ///////////////
		Boolean date_Valid = false;
		Boolean dateInYearScope = false;
		if(year_Start!=null && year_End!=null)
		{
			if(display_Date!=null && start_Date!=null && due_Date!=null && disappear_Date!=null)
			{

				if (disappear_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_Start)>=0 && start_Date.compareTo(year_Start)>=0 && display_Date.compareTo(year_Start)>=0
						&& disappear_Date.compareTo(year_End)<=0 && due_Date.compareTo(year_End)<=0 && start_Date.compareTo(year_End)<=0 && display_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				
				if (disappear_Date.compareTo(due_Date)>=0 && due_Date.compareTo(start_Date)>=0 && start_Date.compareTo(display_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(display_Date==null && start_Date==null && disappear_Date==null)
			{
				date_Valid = true;
				if (due_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
			}
			if(display_Date==null && disappear_Date==null && start_Date!=null)
			{
				if (due_Date.compareTo(year_Start)>=0 && start_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_End)<=0 && start_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (due_Date.compareTo(start_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(start_Date==null && disappear_Date==null && display_Date!=null)
			{
				if (due_Date.compareTo(year_Start)>=0 && display_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_End)<=0 && display_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (due_Date.compareTo(display_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(display_Date==null && start_Date==null && disappear_Date!=null)
			{
				if (disappear_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_Start)>=0 && disappear_Date.compareTo(year_End)<=0 && due_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (disappear_Date.compareTo(due_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(display_Date==null && disappear_Date!=null && start_Date!=null)
			{
				if (disappear_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_Start)>=0 && start_Date.compareTo(year_Start)>=0 && disappear_Date.compareTo(year_End)<=0 && due_Date.compareTo(year_End)<=0 && start_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (disappear_Date.compareTo(due_Date)>=0 && due_Date.compareTo(start_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(start_Date==null && disappear_Date!=null && display_Date!=null)
			{
				if (disappear_Date.compareTo(year_Start)>=0 && due_Date.compareTo(year_Start)>=0 && display_Date.compareTo(year_Start)>=0
						&& disappear_Date.compareTo(year_End)<=0 && due_Date.compareTo(year_End)<=0 && display_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (disappear_Date.compareTo(due_Date)>=0  && due_Date.compareTo(display_Date)>=0)
				{
					date_Valid = true;
				}
			}
			if(disappear_Date==null && start_Date!=null && display_Date!=null)
			{
				if (due_Date.compareTo(year_Start)>=0 && start_Date.compareTo(year_Start)>=0 && display_Date.compareTo(year_Start)>=0
						&& due_Date.compareTo(year_End)<=0 && start_Date.compareTo(year_End)<=0 && display_Date.compareTo(year_End)<=0)
				{
					dateInYearScope = true;
				}
				if (due_Date.compareTo(start_Date)>=0 && start_Date.compareTo(display_Date)>=0)
				{
					date_Valid = true;
				}
			}			
		}
		else
		{
			date_Valid = true;
		}
		////////////////////////////////////////////
		int assignment_id = Integer.parseInt(request.getParameter("Assignment_id"));			
		String message=null;
		int updateQuery = 0;
		AssignmentManagement assignmentManagement = new AssignmentManagement();
		boolean datesExist = true;
		datesExist = assignmentManagement.isAssignmentDatesExist(assignment_id, class_id, course_id, group_id);
		
    	if (!Submit.equals("Delete Dates"))
    	{
    		if(dateInYearScope==true)
    		{
        		if (date_Valid == true)
    			{
    	    		if (datesExist==false)
    		    	{
    	    			updateQuery = assignmentManagement.createAssignmentDates(display_Date, start_Date, due_Date, disappear_Date, assignment_id, class_id, course_id, group_id, online_Submission_Option);
    	    					
    					if (updateQuery!=0)
    					{
    						message = "Assignment has been given successfully <br>"
    								+ "<a href='javascript:giveAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+")'><font color='blue'>Back</font></a>";
    					}
    					else
    					{
    						message = "Failed to create Assignment Dates";
    					}	
    		    	}
    		    	else
    		    	{
    		    		updateQuery = assignmentManagement.updateAssignmentDates(display_Date, start_Date, due_Date, disappear_Date, assignment_id, class_id, course_id, group_id, online_Submission_Option);
    					if(updateQuery!=0)
    					{
    	    				message = "Assignment Dates Updated Successfully <br>"
    						+ "<a href='javascript:giveAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+")'><font color='blue'>Back</font></a>";						
    					}
    					else
    					{
    						message = "Failed to update Assignment Dates";
    					}
    		    	}
    			}
    			else
    			{
    				message = "Assignment Disappear Date must be  after Assignment Due Date <br>"
    						+ "and Assignment Due Date must be after Assignment Start Date <br>"
    						+ "and Assignment Start Date must be after Assignment Display Date.<br>"
    						+ " Assignment not given <br>"
    						+ "<a href='javascript:giveAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+")'><font color='blue'>Back</font></a>";
    			}    			
    		}
    		else
    		{
    			message="One or more of the assignment dates is outside the scope of the current school year <br>"
    					+ "<a href='javascript:giveAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+")'><font color='blue'>Back</font></a>";
    		}
    	}
    	else
    	{
			//Delete
    		updateQuery = assignmentManagement.deleteAssignmentDates(assignment_id, class_id, course_id, group_id);
			if(updateQuery!=0)
			{
		   		message="Assignment Dates Successfully Deleted <br>"
						+ "<a href='javascript:giveAssignment("+assignment_id+","+grade_id+","+class_id+","+course_id+",\""+group_id+"\","+lesson_id+","+category_id+")'><font color='blue'>Back</font></a>";				
			}
			else
			{
				message = "Failed to delete Assignment Dates";
			}
     	}	 	
    	String address = "/Teacher/Assignments/Display_Insert_Give_Assignment.jsp?message="+message+"&&Page=New";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		 return;
	}

}
