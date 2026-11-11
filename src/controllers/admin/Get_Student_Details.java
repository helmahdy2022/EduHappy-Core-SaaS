package controllers.admin;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Classes;
import model.beans.admin.Grades;
import model.beans.admin.Routes_List;
import model.beans.admin.Student_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.GetClassGrade;
import model.dbHelpers.admin.GetClasses;
import model.dbHelpers.admin.GetGrades;
import model.dbHelpers.admin.GetSchoolGrade;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.admin.TransportManagement;

/**
 * Servlet implementation class Get_Student_Details
 */
@WebServlet("/Get_Student_Details")
public class Get_Student_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Student_Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int student_id = Integer.parseInt(request.getParameter("Student_id")) ;
		StudentManagement studentManagement = new StudentManagement();
		Student_Details Student_Details = new Student_Details();
		Student_Details = studentManagement.getStudent(student_id);
		
		/////////////////Retrieve Class Grade/////////////////////////////////
    	GetClassGrade classGrade = new GetClassGrade();
    	int classGrade_id = classGrade.doGetClassGrade(Student_Details.getClass_id());
		if(classGrade_id==0)
		{
			classGrade_id = studentManagement.getStudentGrade(student_id);
		}
    	//////////////////////////////////////////////////////////////
    	
       	/////////////////////////////Retrieve Classes///////////////////////////////
    	GetClasses classes = new GetClasses();
    	Classes Classes = new Classes();
    	Classes = classes.doGetClasses(classGrade_id);
    	request.setAttribute("Classes", Classes);
    	
		/////////////////Retrieve School Garde/////////////////////////////////
    	GetSchoolGrade schoolGrade = new GetSchoolGrade();
    	int schoolGrade_id = schoolGrade.doGetSchoolGrade(classGrade_id);
		//////////////////////////////////////////////////////////////
    	
    	/////////////////////////////Retrieve Grades///////////////////////////////
    	GetGrades grades = new GetGrades();
    	Grades Grades = new Grades();
    	Grades = grades.doGetGrades(schoolGrade_id);
    	request.setAttribute("Grades", Grades);	
		
    	///////////////////////////Retrieve School Routes////////////////////////////////////////////////
    	int school_id = studentManagement.getStudentSchool(student_id);
    	TransportManagement transportManagement = new TransportManagement();
		Routes_List Routes_List = new Routes_List(); 
		Routes_List = transportManagement.getRoutesListBySchool(school_id);
		request.setAttribute("Routes_List", Routes_List);
    	//////////////////////////Retrieve the student photo //////////////////////////////////////   	
    	AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getSchoolAccount(school_id);
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");
		File jsp = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Students/Photos/"+student_id);
		File[] list = jsp.listFiles();
		String fileName = null;
		 if (list!=null)
		 {
			 for(int i=0;i<list.length;i++)
			 {
				 fileName= list[i].getName();
			 }
		 }	
		Student_Details.setFileName(fileName);
		Student_Details.setAccount_id(account_id);		
    	Student_Details.setGrade_id(classGrade_id);
    	Student_Details.setSchool_id(school_id);		
		request.setAttribute("Student_Details", Student_Details);
   		
   		String address =null;
		String source = request.getParameter("Source");
		if (source!=null&&source.equalsIgnoreCase("view"))
   		{
   			address = "/Student/Profile/View_Profile.jsp";
   		}
   		else
   		{
   			address = "/Admin/Students/Edit_Student.jsp?Student_id="+student_id+"&&Graduated="+request.getParameter("Graduated");
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
