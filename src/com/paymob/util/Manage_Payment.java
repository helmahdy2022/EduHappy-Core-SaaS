package com.paymob.util;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Student_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.ClassManagement;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;


/**
 * Servlet implementation class Manage_Payment
 */
@WebServlet("/Manage_Payment")
public class Manage_Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		////////Get Parent Session///////////////////////////////////
		HttpSession session = request.getSession();
		int parent_id = 0 ;
		if(session.getAttribute("Parent_Session")!=null)
		{
			parent_id = (Integer) session.getAttribute("Parent_Session");
		}
		/////////////////////////////////////////////////////////////
		Student_Details student_Details = new Student_Details();
		int student_id = 0;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
		}	
		if(student_id==0)
		{
			//////////////Save Child data in a bean//////////////////////    
			int school_id =0;
			int grade_id = 0;
			int by_Parent = 0;
			school_id = Integer.parseInt(request.getParameter("School"));
			grade_id = Integer.parseInt(request.getParameter("Grade"));
			AccountManagement accountManagement = new AccountManagement();    		
			int account_id = accountManagement.getSchoolAccount(school_id);
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
			String gender = request.getParameter("Gender");
			String mobile = null;
			if(request.getParameter("Mobile")!=null && !request.getParameter("Mobile").equals(""))
			{
				mobile = request.getParameter("Mobile");
			}
			String birth_Date = request.getParameter("Birth_Date");
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");
			////////////////////Subjects///////////////////////////

			///////////////populate the bean ///////////////////////
			student_Details.setSchool_id(school_id);
			student_Details.setGrade_id(grade_id);
			student_Details.setFirst_Name(first_Name);
			student_Details.setLast_Name(last_Name);
			student_Details.setClass_id(class_id);
			student_Details.setGender(gender);
			student_Details.setMobile(mobile);
			student_Details.setBirth_Date(birth_Date);
			student_Details.setUsername(username);
			student_Details.setPassword(password);
			student_Details.setAccount_id(account_id);
			student_Details.setParent_id(parent_id);
			///////////////end populate the bean ///////////////////////
		}
		else
		{
			StudentManagement studentManagement = new StudentManagement();			
			student_Details = studentManagement.getStudent(student_id);
			student_Details.setParent_id(parent_id);
		}
		ArrayList<Integer> CourseList = new ArrayList<Integer>();
		ArrayList<Integer> selectedCourseList = new ArrayList<Integer>();
		CourseManagement courseManagement = new CourseManagement();
		CourseList = courseManagement.getCourseList(student_Details.getGrade_id()).getCourse_id();
		int Course_id = 0;
		for (Integer course_id : CourseList) 
		{
			if(request.getParameter("Course"+course_id)!=null && !request.getParameter("Course"+course_id).equals(""))
			{
				Course_id = Integer.parseInt(request.getParameter("Course"+course_id));
				selectedCourseList.add(Course_id);
			}
		}
	student_Details.setSelectedCourseList(selectedCourseList);
	/////////////Prepare the order for Paymob payment //////////
	PaymentManagement paymentManagement = new PaymentManagement();
	String saved_token = null;
	boolean autoRenewal = false;
	boolean bad_saved_token = false;
	ParentManagement parentManagement = new ParentManagement();
	saved_token = parentManagement.getParentSavedToken(parent_id);
	bad_saved_token = parentManagement.checkBadtSavedToken(saved_token);
	if(saved_token!=null && !saved_token.isEmpty() && bad_saved_token==false)
	{
		//int integration_id=411799;    //Moto testing
		int integration_id=516077;      //Moto Production
		autoRenewal = false;
		String redirection_url = paymentManagement.RecurringPayment(student_Details, saved_token, integration_id, autoRenewal);
		session.setAttribute("Parent_Session", parent_id);
		response.sendRedirect(redirection_url);
		
	}
	else
	{
		//int integration_id=406614;   ///testing
		int integration_id=406943;    ///Production
		autoRenewal = false;
		String payment_token = paymentManagement.FirstTimePayment(student_Details, integration_id, autoRenewal);
		////////////Send parent to the payment for checkout ////////
		String address = "https://accept.paymob.com/api/acceptance/iframes/251870?payment_token="+payment_token;
		response.sendRedirect(address);
	}

}
}
