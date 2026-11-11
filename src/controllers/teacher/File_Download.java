package controllers.teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class File_Download
 */
@WebServlet("/File_Download")
public class File_Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public File_Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath"); 		
		int file_id = Integer.parseInt(request.getParameter("File_id"));
		String account_id = null;
		if(request.getParameter("Account_id")!=null)
		{
			account_id = request.getParameter("Account_id");
		}
		else
		{
			account_id = (String) getServletContext().getAttribute("Account_id");
		}
		
		String school_id = null;
		if(request.getParameter("School_id")!=null)
		{
			school_id = request.getParameter("School_id");
		}
		else
		{
			school_id = (String) getServletContext().getAttribute("School_id");
		}
		
		AccountManagement accountManagement = new AccountManagement();

		int course_id=0;
		if(request.getParameter("Course_id")!=null)
		{
			course_id = Integer.parseInt(request.getParameter("Course_id"));
			//if(account_id==null)
				account_id = String.valueOf(accountManagement.getAccountID(course_id));
			//if(school_id==null)
				school_id = String.valueOf(accountManagement.getSchoolID(course_id));
		}

		int entity_id  = 0;
		int subEntity_id=-1;
		if(request.getParameter("Entity_id")!=null)
		{
			entity_id = Integer.parseInt(request.getParameter("Entity_id"));
		}
		else
		{
			entity_id = (Integer) getServletContext().getAttribute("Entity_id");
		}
		if(request.getParameter("SubEntity_id")!=null)
		{
			subEntity_id = Integer.parseInt(request.getParameter("SubEntity_id"));
		}
		else
		{
			if(getServletContext().getAttribute("SubEntity_id")!=null)
			{
				subEntity_id = (Integer) getServletContext().getAttribute("SubEntity_id");
			}			
		}
		String entity = request.getParameter("Entity");
		String entityIdName = request.getParameter("EntityIdName");
		String subEntity = request.getParameter("SubEntity");
		String subEntityIdName = request.getParameter("SubEntityIdName");
		String address = null;
		if(subEntity!=null&&!subEntity.equals(""))
		{
			address = appPath+ "uploadFiles/Accounts/" +account_id+ "/Schools/" + school_id + "/"+entity+"/" + entity_id + "/"+subEntity+"/"+subEntity_id+"/"+ FileUploadManagement.getFakeFileName(file_id, entity, entityIdName);
		}
		else
		{
			address = appPath+ "uploadFiles/Accounts/" +account_id+ "/Schools/" + school_id + "/"+entity+"/" + entity_id + "/"+ FileUploadManagement.getFakeFileName(file_id, entity, entityIdName);
		}
		
		String realFileName = FileUploadManagement.getRealFileName(entity, entityIdName, entity_id, subEntityIdName, subEntity_id, FileUploadManagement.getFakeFileName(file_id, entity, entityIdName));
		response.setHeader("Content-Disposition", "attachment; filename="+MimeUtility.encodeWord(realFileName, "utf-8", "Q")+"");
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(address);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0){
		    out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
