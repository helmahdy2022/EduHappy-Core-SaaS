package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dbHelpers.admin.FeesManagement;

/**
 * Servlet implementation class Manage_Fee_Structure
 */
@WebServlet("/Manage_Fee_Structure")
public class Manage_Fee_Structure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Fee_Structure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int fees_Count=0;
		if(request.getParameter("Fees_Count")!=null)
		{
			fees_Count = Integer.parseInt(request.getParameter("Fees_Count"));
		}
		int grades_Count=0;
		if(request.getParameter("Grades_Count")!=null)
		{
			grades_Count = Integer.parseInt(request.getParameter("Grades_Count"));
		}
		int school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		int x;
		x =fees_Count*grades_Count;
		for (int i=1; i < x+1; i++)
		{		
			int grade_id=0;
			if(request.getParameter("Grade_id"+i)!=null)
			{
				grade_id = Integer.parseInt(request.getParameter("Grade_id"+i));
			}
			int fee_id=0;
			if(request.getParameter("Fee_id"+i)!=null)
			{
				fee_id = Integer.parseInt(request.getParameter("Fee_id"+i));
			}
			double fee_Amount = -1;
			if(request.getParameter("Fee_Amount"+i)!=null && !request.getParameter("Fee_Amount"+i).equals(""))
			{
				fee_Amount = Double.parseDouble(request.getParameter("Fee_Amount"+i));
			}
			
			FeesManagement feesManagement = new FeesManagement();
			feesManagement.manageFeeStructure(fee_id, grade_id, school_Year_id, fee_Amount);
		
		}
		String address = "/Admin/Fees/Fee_Structure.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	
	}

}
