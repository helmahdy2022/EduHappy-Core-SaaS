package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.School_Year;
import model.dbHelpers.admin.SchoolYearManagement;

/**
 * Servlet implementation class Get_School_Year_Details
 */
@WebServlet("/Get_School_Year_Details")
public class Get_School_Year_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_School_Year_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//int i = 0;		
		String  Terms_no =null;
		Terms_no = request.getParameter("Terms_no");
		String last_i;
		last_i=request.getParameter("last_i");
		String  Add_New =null;
		Add_New = request.getParameter("Add_New");
		int school_Year_id = 0;
		if(request.getParameter("School_Year_id")!=null)
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}
		String  Delete = request.getParameter("Delete");
		int n=0;;
		int Terms=0;
		String address=null;
		if (Add_New!=null)
		{
			Terms_no="1";
			if(last_i!=null&&!last_i.equals("")&&!last_i.equals("NaN"))
			{
				n = 1+ Integer.parseInt(last_i);
				Terms = Integer.parseInt(Terms_no)+Integer.parseInt(last_i);
			}
			else
			{
				n=1;
				Terms=1;
			}	
			address = "/Admin/Academic_Year/Display_School_Year_Details.jsp?n="+n+"&&Terms="+Terms;
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	 		dispatcher.forward(request, response);
		}
		else
		{
			if (Delete!=null)
			{
				//String Row_no = request.getParameter("i");
				//resultYearTerms.absolute(Integer.parseInt(Row_no));
				int term_id= 0;
				if(request.getParameter("Term_id")!=null&&!request.getParameter("Term_id").equals("undefined"))
				{
					term_id = Integer.parseInt(request.getParameter("Term_id"));
					//Delete
					SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
					int updateQuery = schoolYearManagement.deleteYearTerm(term_id);
					if(updateQuery!=0)
					{
						address = "/Admin/Academic_Year/Display_School_Year_Details.jsp?Function=Delete";
				 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				 		dispatcher.forward(request, response);
					}
				}
				//if (Integer.parseInt(Row_no)<=Integer.parseInt(last_i))
				//{

				//}
			}
			else
			{
				SchoolYearManagement schoolYearManagement = new SchoolYearManagement();
				School_Year School_Year = new School_Year();
				School_Year = schoolYearManagement.getSchoolYearDetails(school_Year_id,0);
				
				request.setAttribute("School_Year", School_Year);
				address = "/Admin/Academic_Year/Display_School_Year_Details.jsp?Term_qty="+School_Year.getYear_Terms().size();;
		 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		 		dispatcher.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
