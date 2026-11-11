package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Grade_Terms;
import model.dbHelpers.admin.GradeManagement;


/**
 * Servlet implementation class Get_Grade_Terms_Details
 */
@WebServlet("/Get_Grade_Terms_Details")
public class Get_Grade_Terms_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Grade_Terms_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int grade_id=0;
		if(request.getParameter("Grade_id")!=null)
		{
			grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		}
		int school_Year_id=0;
		if(request.getParameter("School_Year_id")!=null)
		{
			school_Year_id = Integer.parseInt(request.getParameter("School_Year_id"));
		}	
		int  terms_no =0;
		if(request.getParameter("Terms_no")!=null)
		{
			terms_no = Integer.parseInt(request.getParameter("Terms_no"));
		}
		int last_i=0;
		if(request.getParameter("last_i")!=null)
		{
			last_i=Integer.parseInt(request.getParameter("last_i"));
		}		
		String  Add_New =null;
		Add_New = request.getParameter("Add_New");		
		String  Delete = request.getParameter("Delete");			
		int n;
		int Terms;
		String address;			
		int updateQuery=0;
		boolean Existing_Terms = true;
		if ((Add_New!=null || terms_no!=0)&&Delete == null)
		{
			if (Add_New!=null)
			{
				terms_no=1;
			}				

			n = 1+ last_i;
			Terms = terms_no+last_i;	
			
			address = "/Admin/Grades/Display_Grade_Terms_Options.jsp?n="+n+"&&Term_qty="+Terms+"&&Grade_id="+grade_id+"&&Existing_Terms="+Existing_Terms;
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	 		dispatcher.forward(request, response);			
		}
		else
		{

			if (Delete!=null)
			{
				//String Row_no = request.getParameter("i");
				int grade_Term_id= 0;
				if(request.getParameter("Grade_Term_id")!=null&&!request.getParameter("Grade_Term_id").equals("undefined"))
				{
					grade_Term_id = Integer.parseInt(request.getParameter("Grade_Term_id"));
					//Delete
					GradeManagement gradeManagement = new GradeManagement();
					updateQuery = 0;
					updateQuery = gradeManagement.deleteGradeTerm(grade_Term_id);
					if(updateQuery!=0)
					{
						address = "/Admin/Grades/Display_Grade_Terms_Options.jsp?Function=Delete&&Grade_id="+grade_id+"&&Existing_Terms="+Existing_Terms;
				 		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				 		dispatcher.forward(request, response);
					}
				}
				//if (Integer.parseInt(Row_no)<=last_i)
				//{

				//}
			}
			else
			{

				Grade_Terms Grade_Terms = new Grade_Terms();

	
        		GradeManagement gradeManagement = new GradeManagement();
        		Grade_Terms = gradeManagement.getGradeTermsDetails(grade_id, school_Year_id);
        		
        		int term_qty = 0;
        		
        		term_qty = Grade_Terms.getGrade_Terms_List().size();
        		if (term_qty==0)
        		{
        			Existing_Terms = false;
        		}

        		request.setAttribute("Grade_Terms", Grade_Terms);
				address = "/Admin/Grades/Display_Grade_Terms_Options.jsp?Term_qty="+term_qty+"&&Grade_id="+grade_id+"&&School_Year_id="+school_Year_id+"&&Existing_Terms="+Existing_Terms;
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
