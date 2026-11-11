package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Fee_Structure;
import model.beans.admin.Fee_Types_List;
import model.beans.admin.Grades;
import model.dbHelpers.admin.FeesManagement;
import model.dbHelpers.admin.GetGrades;

/**
 * Servlet implementation class Get_Fee_Structure
 */
@WebServlet("/Get_Fee_Structure")
public class Get_Fee_Structure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Fee_Structure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	int school_id=0;
    	if(request.getParameter("School_id")!=null && !request.getParameter("School_id").equals(""))
    	{
    		school_id = Integer.parseInt(request.getParameter("School_id"));
    	}
		FeesManagement feesManagement = new FeesManagement();
		Fee_Types_List Fee_Types_List = new Fee_Types_List(); 
		Fee_Types_List = feesManagement.getFeeTypesList(school_id);
		request.setAttribute("Fee_Types_List", Fee_Types_List);
    	/////////////////////////////End retrieve List of Fee Types///////////////////////////////  	
		
		//////////////////Get Grades ////////////////////////////////////////////////////////////
		int school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		/////////////////////////////Retrieve Grades///////////////////////////////
    	GetGrades grades = new GetGrades();
    	Grades Grades = new Grades();
    	Grades = grades.doGetGrades(school_id);
    	request.setAttribute("Grades", Grades);			
		////////////////End Get Grades//////////////////////////////////////////////////////////

    	///////////////Get Fee Structure //////////////////////////////////////////////////////
    	Fee_Structure Fee_Structure = new Fee_Structure();
    	Fee_Structure = feesManagement.getFeeStructure(school_id, school_Year_id);
    	request.setAttribute("Fee_Structure", Fee_Structure);	
    	///////////////End Get Fee Structure //////////////////////////////////////////////////
    	String address = "/Admin/Fees/Display_Fee_Structure.jsp";
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
