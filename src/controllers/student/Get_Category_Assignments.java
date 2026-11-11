package controllers.student;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.student.Category_Assignments;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.student.ServiceAssignment;


/**
 * Servlet implementation class Get_Category_Assignments
 */
@WebServlet("/Get_Category_Assignments")
public class Get_Category_Assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Category_Assignments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int category_id= Integer.parseInt(request.getParameter("Category_id"));
		HttpSession session= request.getSession();
		int student_id = 0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");
		}
		
		int school_year_id=0;
		if(request.getParameter("School_year_id")!=null)
		{
			school_year_id = Integer.parseInt(request.getParameter("School_year_id"));
		}
		String promoted = request.getParameter("Promoted");
		if(promoted==null)
		{
			promoted = "false";
		}
		boolean student_Promoted=false;
		if(session.getAttribute("Student_Account_Details")!=null)
		{
			Account_Details Student_Account_Details = (Account_Details) session.getAttribute("Student_Account_Details");
			student_Promoted = Student_Account_Details.isStudent_Promoted();
		}
		ServiceAssignment serviceAssignment = new ServiceAssignment();
		Category_Assignments Category_Assignments = new Category_Assignments();
/*		if(promoted!=null&&promoted.equals("false")&&!student_Promoted)
		{
			school_year_id = 0;
		}*/
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		int current_school_year_id = schoolYearManagement.getCurrentSchoolYearID(school_id);
		if(promoted!=null&&promoted.equals("false")&&!student_Promoted&&current_school_year_id==school_year_id)
		{
			Category_Assignments = serviceAssignment.getCatAssignment(student_id, category_id, current_school_year_id);
		}
		else
		{
			Category_Assignments = serviceAssignment.getFinalCatAssignment(student_id, category_id, school_year_id);
		}
  		
		request.setAttribute("Category_Assignments", Category_Assignments);
		String address = "/Student/Grading/Display_Category_Assignments.jsp";
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
