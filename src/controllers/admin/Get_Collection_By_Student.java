package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Payment_Amount;
import model.beans.admin.Fee_Types_List;
import model.beans.admin.Students;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class Get_Collection_By_Student
 */
@WebServlet("/Get_Collection_By_Student")
public class Get_Collection_By_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Collection_By_Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int school_id = 0;
		if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
		{
			school_id = Integer.parseInt(request.getParameter("School_id"));
		}
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null && !request.getParameter("School_Year_id").equals(""))
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		int grade_id = 0;
		if(request.getParameter("Grade_id")!=null && !request.getParameter("Grade_id").equals(""))
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		int student_id = 0;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}
		/////////////////////////////Retrieve Grade Students ////////////////////////////////////
		Students Students = new Students();
		Students = feesManagement.getGradeStudents(grade_id);
		request.setAttribute("Students", Students);
		//////////////////////////////////////////////////////////////////////////////////////////
    	
    	/////////////////Get Fee Payments ////////////////////////////////////////////////////////
    	Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount();
    	Fee_Payment_Amount = feesManagement.getCollectionsByStudent(school_id, grade_id, school_Year_id, student_id);
    	request.setAttribute("Fee_Payment_Amount", Fee_Payment_Amount);
    	/////////////////////////////////////////////////////////////////////////////////////////

    	String address = "/Admin/Fees/Display_Collection_By_Student.jsp";
    	RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
