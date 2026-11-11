package controllers.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Delete_File
 */
@WebServlet("/Delete_File")
public class Delete_File extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_File() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = null;
		int file_id=0;
		if(request.getParameter("Directory").equals("Students") || request.getParameter("Directory").equals("Parents") || request.getParameter("Directory").equals("Teachers")|| request.getParameter("Directory").equals("Admins"))
		{
			file_id = Integer.parseInt(request.getParameter("Entity_id"));
			String fakeFileName = FileUploadManagement.getFakeFileName(file_id, request.getParameter("Directory"), request.getParameter("EntityIdName"));
			if(request.getParameter("Directory").equals("Teachers")|| request.getParameter("Directory").equals("Admins") || request.getParameter("Directory").equals("Parents"))
			{
				jsp = new File(appPath+"uploadFiles/Accounts/"+request.getParameter("Account_id")+"/"+request.getParameter("Directory")+"/Photos/"+request.getParameter("Entity_id")+"/"+fakeFileName);

			}
			else
			{
				jsp = new File(appPath+"uploadFiles/Accounts/"+request.getParameter("Account_id")+"/Schools/"+request.getParameter("School_id")+"/"+request.getParameter("Directory")+"/Photos/"+request.getParameter("Entity_id")+"/"+fakeFileName);

			}
		}
		else
		{
			file_id = Integer.parseInt(request.getParameter("File_id"));
			String fakeFileName = FileUploadManagement.getFakeFileName(file_id, request.getParameter("Directory"), request.getParameter("EntityIdName"));
			if(request.getParameter("SubDirectory")!=null && !request.getParameter("SubDirectory").equals(""))
			{
				jsp = new File(appPath+"uploadFiles/Accounts/"+request.getParameter("Account_id")+"/Schools/"+request.getParameter("School_id")+"/"+request.getParameter("Directory")+"/"+request.getParameter("Entity_id")+"/"+request.getParameter("SubDirectory")+"/"+request.getParameter("SubEntity_id")+"/"+fakeFileName);
			}
			else
			{
				jsp = new File(appPath+"uploadFiles/Accounts/"+request.getParameter("Account_id")+"/Schools/"+request.getParameter("School_id")+"/"+request.getParameter("Directory")+"/"+request.getParameter("Entity_id")+"/"+fakeFileName);
			}
			
		}
		 jsp.delete();
		 
		 if(request.getParameter("File_Name").equals("OnlineQuiz"))
		 {
			FileUtils.deleteQuietly(jsp); 
		 }		
		 FileUploadManagement.deleteMappedFile(file_id, request.getParameter("Directory"), request.getParameter("EntityIdName"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
