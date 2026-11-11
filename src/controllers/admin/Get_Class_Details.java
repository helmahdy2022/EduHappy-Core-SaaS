package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Class_Details;
import model.dbHelpers.admin.ClassManagement;

/**
 * Servlet implementation class Get_Class_Details
 */
@WebServlet("/Get_Class_Details")
public class Get_Class_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Class_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int class_id = Integer.parseInt(request.getParameter("Class_id")) ;
		ClassManagement classManagement = new ClassManagement();
		Class_Details Class_Details = new Class_Details();
		Class_Details = classManagement.getClass(class_id);
		request.setAttribute("Class_Details", Class_Details);
   		String address =null;
		String Print = request.getParameter("Print");
		if (Print!=null)
   		{
   			address = "/Admin/Classes/Print_Class.jsp";
   		}
   		else
   		{
   			address = "/Admin/Classes/Display_Class_Details.jsp?Class_id="+class_id;
   		}  		
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
  		return;	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
