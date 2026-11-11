package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Payment_Amount;
import model.beans.admin.Fee_Structure;
import model.beans.admin.Fee_Types_List;
import model.beans.admin.School_Years;
import model.beans.admin.Transport_Fees;
import model.dbHelpers.admin.FeesManagement;
import model.dbHelpers.admin.SchoolYearManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.admin.TransportManagement;

/**
 * Servlet implementation class Get_Fee_Payments
 */
@WebServlet("/Get_Fee_Payments")
public class Get_Fee_Payments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Fee_Payments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int student_id = Integer.parseInt(request.getParameter("Student_id"));
		StudentManagement studentManagement = new StudentManagement();
		int school_id = studentManagement.getStudentSchool(student_id);
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
		SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
		int school_Year_id = 0;
		String current_Year = null;
		if(request.getParameter("School_Year_id")!=null && !request.getParameter("School_Year_id").equals(""))
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
			current_Year = schoolYearManagement.getSchoolYearName(school_Year_id);
		}
		else
		{
			school_Year_id = schoolYearManagement.getCurrentSchoolYearID(school_id);
			current_Year = schoolYearManagement.getCurrentSchoolYear(school_id);
		}		
		
		int grade_id = studentManagement.getStudentGrade(student_id);
    	///////////////Get Fee Structure //////////////////////////////////////////////////////
    	Fee_Structure Fee_Structure = new Fee_Structure();
    	Fee_Structure = feesManagement.getFeeStructurePerGrade(school_id, grade_id, school_Year_id);
    	request.setAttribute("Fee_Structure", Fee_Structure);
    	///////////////End Get Fee Structure //////////////////////////////////////////////////
    	
    	////////////////////Get Transport Fees ////////////////////////////////////////////
    	TransportManagement transportManagement = new TransportManagement();
    	Transport_Fees Transport_Fees = new Transport_Fees();
    	Transport_Fees = transportManagement.getStudentTransportFees(school_Year_id, student_id);
    	request.setAttribute("Transport_Fees", Transport_Fees);	
    	////////////////////////////////////////////////////////////////////////////////////////////
    	
    	/////////////////Get Fee Payments ////////////////////////////////////////////////////////
    	Fee_Payment_Amount Fee_Payment_Amount = new Fee_Payment_Amount();
    	Fee_Payment_Amount = feesManagement.getFeePaymentAmount(school_id, grade_id, student_id, school_Year_id);
    	request.setAttribute("Fee_Payment_Amount", Fee_Payment_Amount);
    	/////////////////////////////////////////////////////////////////////////////////////////
    	
    	/////////////////////////Get Academic Years List////////////////////School_Years School_Years////////////////////////
    	School_Years School_Years = new School_Years();
    	School_Years  = schoolYearManagement.getSchoolYearsList(school_id);
    	request.setAttribute("School_Years", School_Years);
    	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	String address = "/Admin/Fees/Fee_Payments_Per_Student.jsp?School_Year_id="+school_Year_id+"&&Current_Year="+current_Year;
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
