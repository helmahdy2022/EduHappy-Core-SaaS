package controllers.admin;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Parent_Details;
import model.dbHelpers.admin.ParentManagement;


/**
 * Servlet implementation class Get_Parent_Details
 */
@WebServlet("/Get_Parent_Details")
public class Get_Parent_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Parent_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int parent_id = 0;
		if (request.getParameter("Parent_id")!=null && request.getParameter("Parent_id")!="")
		{
			parent_id = Integer.parseInt(request.getParameter("Parent_id")) ;
		}			
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details Parent_Details = new Parent_Details();
		Parent_Details = parentManagement.getParent(parent_id);
		int account_id = Parent_Details.getAccount_id();
    	//////////////////////////Retrieve the student photo //////////////////////////////////////   	
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Parents/Photos/"+parent_id);
		File[] list = jsp.listFiles();
		String fileName = null;
		 if (list!=null)
		 {
			 for(int i=0;i<list.length;i++)
			 {
				 fileName= list[i].getName();
			 }
		 }	
		 Parent_Details.setFileName(fileName);	
		request.setAttribute("Parent_Details", Parent_Details);
   		
   		String address =null;
		String source = request.getParameter("Source");
		if (source!=null&&source.equalsIgnoreCase("view"))
   		{
   			address = "/Parent/Profile/View_Profile.jsp";
   		}
   		else
   		{
   			address = "/Admin/Parents/Edit_Parent.jsp?Parent_id="+parent_id;
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
		doGet(request, response);
	}

}
