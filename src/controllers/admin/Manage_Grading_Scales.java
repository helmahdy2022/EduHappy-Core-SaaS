package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grading_Scale_Details;
import model.dbHelpers.admin.GradeManagement;

/**
 * Servlet implementation class Manage_Grading_Scale
 */
@WebServlet("/Manage_Grading_Scales")
public class Manage_Grading_Scales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Grading_Scales() {
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
		int grading_Scale_id=0;
		if(request.getParameter("Grading_Scale_id")!=null)
		{
			grading_Scale_id = Integer.parseInt(request.getParameter("Grading_Scale_id"));
		}
		int grade_id=Integer.parseInt(request.getParameter("Grade"));
		int low=Integer.parseInt(request.getParameter("low"));
		int high=Integer.parseInt(request.getParameter("high"));
		String letter = request.getParameter("letter");
		String description = request.getParameter("description");
		String message = null;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		////validate that high is higher than low /////
		if(low>high)
		{
			message="Please ensure that the high value is higher than the low value";
		}
		else
		{
			Grading_Scale_Details Grading_Scale_Details = new Grading_Scale_Details();
			///populate the bean //
			Grading_Scale_Details.setGrading_Scale_id(grading_Scale_id);
			Grading_Scale_Details.setGrade_id(grade_id);
			Grading_Scale_Details.setLow(low);
			Grading_Scale_Details.setHigh(high);
			Grading_Scale_Details.setLetter(letter);
			Grading_Scale_Details.setDescription(description);
			///////////////////////
			////intiate the model////
			GradeManagement gradeManagement = new GradeManagement();
			////////////////////////
			if(ConfirmDelete==null)
			{	
				if(grading_Scale_id==0)
				{
					//create
					message = gradeManagement.createGradingScale(Grading_Scale_Details);
				}
				else
				{
					//update
					message = gradeManagement.updateGradingScale(Grading_Scale_Details);
				}				
			}
			else
			{
				//delete
				message = gradeManagement.deleteGradingScale(grading_Scale_id);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/Grades/Display_Manage_Grading_Scales.jsp?message="+message);
		dispatcher.forward(request, response);
		
	}

}
