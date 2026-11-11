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
import model.beans.parent.Child;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Manual_Renewal
 */
@WebServlet("/Manual_Renewal")
public class Manual_Renewal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manual_Renewal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int parent_id = 0 ;
		if(session.getAttribute("Parent_Session")!=null)
		{
			parent_id = (Integer) session.getAttribute("Parent_Session");
		}
		System.out.println(parent_id);
		ManualRenewal manualRenewal = new ManualRenewal();
		Child childrenewal = new Child();
		childrenewal = manualRenewal.GetDueRenewal(parent_id);
		//String payment_token = manualRenewal.PayManual(parent_id);
		ParentManagement parentManagement = new ParentManagement();      		
		String saved_token = parentManagement.getParentSavedToken(parent_id);
		boolean bad_saved_token = parentManagement.checkBadtSavedToken(saved_token);
		PaymentManagement paymentManagement = new PaymentManagement();
		String payment_token = null;
		StudentManagement studentManagement = new StudentManagement();
		ArrayList<Integer> Student_id = childrenewal.getStudent_id();
		String address = null;
		for (int student_id : Student_id)
		{
			int hidden_student_id = 0;
			if(request.getParameter("Student_id"+student_id)!=null && !request.getParameter("Student_id"+student_id).isEmpty())
			{
				hidden_student_id = Integer.parseInt(request.getParameter("Student_id"+student_id));
				
			}			
			if(student_id == hidden_student_id)
			{
				Student_Details student_Details = studentManagement.getStudent(student_id);
				student_Details.setParent_id(parent_id);
				ArrayList<ArrayList<String>> courseMultpleList = childrenewal.getDueRenewal().get(student_id);
				ArrayList<Integer> selectedCourses = new ArrayList<Integer>();
				
				for(ArrayList<String> selectedCoursesString : courseMultpleList)
				{
					selectedCourses.add(Integer.parseInt(selectedCoursesString.get(0)));
				}
				student_Details.setSelectedCourseList(selectedCourses);

				if(saved_token!=null && !saved_token.isEmpty() && bad_saved_token==false)
				{
					//int integration_id=411799; //Moto testing
					int integration_id=516077;   //Moto Production
					String redirection_url = paymentManagement.RecurringPayment(student_Details, saved_token, integration_id, false);	
					session.setAttribute("Parent_Session", parent_id);
					address = redirection_url;
				}
				if(bad_saved_token==true)
				{
					//int integration_id=406614;   ///testing
					int integration_id=406943;     ///Production 
					payment_token = paymentManagement.FirstTimePayment(student_Details, integration_id, false);
					address = "https://accept.paymob.com/api/acceptance/iframes/251870?payment_token="+payment_token;
					
				}					
			}		
		}
		response.sendRedirect(address);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
