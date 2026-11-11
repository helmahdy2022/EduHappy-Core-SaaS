package controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import model.beans.account.Account_Details;
import model.beans.admin.Student_Details;
import model.beans.parent.Login;
import model.dbHelpers.Parent.CheckLoginQuery;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.ClassManagement;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.GroupManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.teacher.FileUploadManagement;

/**
 * Servlet implementation class Manage_Student
 */
@WebServlet("/Manage_Student")
public class Manage_Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		long totalFilesSize = 1024*1024*50;
		long fileSize = 1024*1024*10;
		int fileQty = 10;
		
		
        @SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");	
		ArrayList<String> uploadMessage = new ArrayList<String>();
		int school_id =0;
		int grade_id = 0;
		int by_Parent = 0;
		school_id = Integer.parseInt(request.getParameter("School"));
		grade_id = Integer.parseInt(request.getParameter("Grade"));
		AccountManagement accountManagement = new AccountManagement();    		
		int account_id = accountManagement.getSchoolAccount(school_id);
		if(request.getParameter("By_Parent")!=null && !request.getParameter("By_Parent").equals(""))
		{
			by_Parent = Integer.parseInt(request.getParameter("By_Parent"));
		}
		int student_id=0;
		if(request.getParameter("Student_id")!=null)
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}

		String message = null;

		StudentManagement studentManagement = new StudentManagement();
		
		int updateQuery = 0;
		String ConfirmDelete = request.getParameter("ConfirmDelete");
		
		if(ConfirmDelete==null)
		{
			
			String first_Name = request.getParameter("First_Name");
			String last_Name = request.getParameter("Last_Name");
			int class_id = 0;
			if(request.getParameter("Class")!=null && !request.getParameter("Class").equals(""))
			{
				class_id = Integer.parseInt(request.getParameter("Class"));
			}	
			/////////////////Adding the class in case Parent is enrolling his child in the academy///////////////////////
			if(class_id==0 &&by_Parent==1)
			{
				ClassManagement classManagement = new ClassManagement();
				class_id = classManagement.getClassByGrade(grade_id);
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			int country_id = 0;
			if(request.getParameter("Country_id")!=null && !request.getParameter("Country_id").equals(""))
			{
				country_id = Integer.parseInt(request.getParameter("Country_id"));
			}
			String city = request.getParameter("City");
			int pO_Box = 0;
			if(request.getParameter("PO_Box")!=null && !request.getParameter("PO_Box").equals(""))
			{
				pO_Box = Integer.parseInt(request.getParameter("PO_Box"));	
			}			
			String full_Address = request.getParameter("Full_Address");	
			String gender = request.getParameter("Gender");
			String email = null;
			if(request.getParameter("Email")!=null && !request.getParameter("Email").equals(""))
			{
				email = request.getParameter("Email");
			}	
			String tel = null;
			if(request.getParameter("Tel")!=null && !request.getParameter("Tel").equals(""))
			{
				tel = request.getParameter("Tel");
			}
			String mobile = null;
			if(request.getParameter("Mobile")!=null && !request.getParameter("Mobile").equals(""))
			{
				mobile = request.getParameter("Mobile");
			}
			String birth_Date = request.getParameter("Birth_Date");
			int route_id = 0;
			if(request.getParameter("Route_id")!=null && !request.getParameter("Route_id").equals(""))
			{
				route_id = Integer.parseInt(request.getParameter("Route_id"));
			}
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");

			///////////////populate the bean ///////////////////////
			Student_Details student_Details = new Student_Details();
			student_Details.setSchool_id(school_id);
			student_Details.setGrade_id(grade_id);
			student_Details.setFirst_Name(first_Name);
			student_Details.setLast_Name(last_Name);
			student_Details.setClass_id(class_id);
			student_Details.setCountry_id(country_id);
			student_Details.setCity(city);
			student_Details.setPO_Box(pO_Box);
			student_Details.setFull_Address(full_Address);
			student_Details.setGender(gender);
			student_Details.setEmail(email);
			student_Details.setTel(tel);
			student_Details.setMobile(mobile);
			student_Details.setBirth_Date(birth_Date);
			student_Details.setRoute_id(route_id);
			student_Details.setUsername(username);
			student_Details.setPassword(password);
			student_Details.setAccount_id(account_id);
			///////////////end populate the bean ///////////////////////
			int old_school_id = 0;
    		if (student_id==0)
			{
    			Account_Details account_Details = new Account_Details();
    			account_Details = accountManagement.getAccountDetails(account_id);
    			int no_Of_Students = account_Details.getNo_Of_Students();
    			int no_Of_Created_Students = account_Details.getNo_Of_Created_Students();
    			if(no_Of_Created_Students < no_Of_Students)
    			{
        			if(by_Parent==1)
        			{
        				int autoIncValue = studentManagement.createStudent(student_Details);
        		        if (autoIncValue!=-1 && autoIncValue!=-2)
        		        {
        		        	message = "New Student Profile created successfully";
        		        	student_id = autoIncValue;
        					//////////////////Assign the child to his parent///////////////////////////////////
        					HttpSession session= request.getSession();
        					int parent_id = 0 ;
        					if(session.getAttribute("Parent_Session")!=null)
        					{
        						parent_id = (Integer) session.getAttribute("Parent_Session");
        					}
        					ParentManagement parentManagement = new ParentManagement();
        					parentManagement.linkParentStudent(student_id, parent_id);
        					CheckLoginQuery c = new CheckLoginQuery();
        					Login Parent_Old_Session = (Login) session.getAttribute("Parent_Login");
        					Account_Details Parent_New_Account_Details =c.doCheckLogin(Parent_Old_Session);
        					session.setAttribute("child",Parent_New_Account_Details.getChild());
       					
        					///////////////////////////////////////////////////////////////////////////////////
        		        	/////////////////////Add Student to Elective ///////////////////////////////////
        		        	
        					ArrayList<Integer> CourseList = new ArrayList<Integer>();
        					CourseManagement courseManagement = new CourseManagement();
        					CourseList = courseManagement.getCourseList(grade_id).getCourse_id();
        					int Course_id = 0;
        					for (Integer course_id : CourseList) 
        					{
        						if(request.getParameter("Course"+course_id)!=null && !request.getParameter("Course"+course_id).equals(""))
        						{
        							Course_id = Integer.parseInt(request.getParameter("Course"+course_id));
        						}
        						
        						if(Course_id!=0)
        						{
        							boolean studentExistsInElective = false;
        							studentExistsInElective = courseManagement.checkStudentElective(student_id, Course_id);
        							if (studentExistsInElective==false)
        							{
        								courseManagement.addParentChildToElective(student_id, Course_id, parent_id);

        								
        								/////////////////////////Assign Student to Group///////////////////////////////
        	        					GroupManagement groupManagement = new GroupManagement();
        	        					groupManagement.addParentChildToGroup(student_id, course_id);
        	        					///////////////////////////////////////////////////////////////////////////////
        							}
        							
        						}
        					}
        		        	///////////////////////////////////////////////////////////////////////////////
        		        }
        		        else
        		        {
        		        	if(autoIncValue==-2)
        		        	{
        		        		message = "Duplicate Entry for the Student Username. Student Profile was not created";
        		        	}
        		        	else
        		        	{
        		        		message = "Failed to create new Student Profile";
        		        	}		        	
        		        }
        			}
        			else
        			{
        				student_Details.setFirst_Login(1);
        				int autoIncValue = studentManagement.createStudent(student_Details);
        		        if (autoIncValue!=-1 && autoIncValue!=-2)
        		        {
        		        	message = "New Student Profile created successfully";
        		        	student_id = autoIncValue;
        		        }
        		        else
        		        {
        		        	if(autoIncValue==-2)
        		        	{
        		        		message = "Duplicate Entry for the Student Username. Student Profile was not created";
        		        	}
        		        	else
        		        	{
        		        		message = "Failed to create new Student Profile";
        		        	}		        	
        		        }
        			}
    			}
    			else
    			{
    				message = "Sorry, you can not create student profile since you have exceeded the number of students you have subscribed to ("+no_Of_Students+"). Please update your subscription with extra students licenses in order to be able to create more students.";
    			}
			}
			else
			{
				old_school_id = studentManagement.getStudentSchool(student_id);
				updateQuery = studentManagement.updateStudent(student_Details, student_id);
		    	if (updateQuery!=0 && updateQuery!=-1)
		    		message = "Student Profile updated successfully";
		    	else
		    	{
		        	if(updateQuery==-1)
		        	{
		        		message = "Duplicate Entry for the Student Username. Student was not updated";
		        	}
		        	else
		        	{
		        		message = "Failed to update Student Profile";
		        	}	
		    	}    	
			}
			
    		///////////////////////////deal with file upload here////////////////////////////////////////////////
    		if(student_id!=0 && student_id!=-1)
    		{			    		

    			try {
    				String oldfileName = studentManagement.getStudent(student_id).getFileName();
    				
    				uploadMessage = FileUploadManagement.fileUpload(appPath, account_id, school_id, "Students","Photos", "Student_id", null, student_id, -1, items, totalFilesSize, fileSize, fileQty);
        			
    				String newfileName = studentManagement.getStudent(student_id).getFileName();
    				String savePathOld = appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+old_school_id+"/Students/Photos/" +student_id;
    				File old_file = new File(savePathOld + "/" + oldfileName);
        			if(old_school_id!=school_id&&oldfileName!=null&&newfileName!=null&&oldfileName.equals(newfileName))
	    			{
	        			String savePathNew = appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Students/Photos/" +student_id;
	          			File savePathNewDir = new File(savePathNew);
	        			if (!savePathNewDir.exists()) {
	        				savePathNewDir.mkdir();
	        			}
	        			File new_file = new File(savePathNew + "/" + oldfileName);
	    				Files.move(old_file.toPath(), new_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    			}
        			if(oldfileName!=null&&(newfileName==null||!oldfileName.equals(newfileName)))
        			{
        				//delete old file///
        				old_file.delete();
        				File savePathNewDir = new File(savePathOld);
        				savePathNewDir.delete();
        			}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}  		
		}
		else
		{
			updateQuery = studentManagement.deleteStudent(student_id, account_id);
			if (updateQuery!=0)
			{

				
				File directory = new File(appPath+"uploadFiles/Accounts/"+account_id+"/Schools/"+school_id+"/Students/Photos/" +student_id);
				String[]entries = directory.list();
				if(entries!=null)
				{
					for(String s: entries){
					    File currentFile = new File(directory.getPath(),s);
					    currentFile.delete();
					}
				}
				directory.delete(); 	 
				message = "Student Profile successfully deleted";	
			}
				
			else
			{
				message = "Failed to delete Student Profile";
			}
		}
		
	    String address = null;
	    if(by_Parent==0)
	    {
	    	address = "/Admin/Students/Manage_Student.jsp";
	    }
	    else
	    {
	    	address = "/Parent/Child/Display_Add_Child.jsp";
	    }
	    
	    request.setAttribute("message", message);
		request.setAttribute("uploadMessage", uploadMessage);
		request.setAttribute("Graduated", request.getParameter("Graduated"));
		
		
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	
	}

}
