package com.paymob.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.beans.admin.Course_Details;
import model.beans.admin.Student_Details;
import model.beans.parent.Child;
import model.dbHelpers.DbConnector;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;

public class ManualRenewal {
    public String PayManual(int parent_id) {
    	String payment_token = null;
    	Date javaToday = new Date();
    	java.sql.Date today = new java.sql.Date(javaToday.getTime());
    	String sStudentDueRenewal = "select DISTINCT Student_id from Student_Electives where canceled <> 1 and Payment_Renewal_Date<='"+today+"' and Parent_id="+parent_id;

    	try (Connection connection = DbConnector.connectToDb();
    			 Statement statementStudentDueRenewal = connection.createStatement();
    			Statement statementSubscribedCourses = connection.createStatement();
    			Statement statementCourseDetails = connection.createStatement();){
    		
    		ResultSet resultStudentDueRenewal = statementStudentDueRenewal.executeQuery(sStudentDueRenewal);
       	 System.out.println(sStudentDueRenewal);
    		while(resultStudentDueRenewal.next())
        	{
        		
        		int student_id = resultStudentDueRenewal.getInt("Student_id");
        		System.out.println(student_id);
        		Student_Details student_Details = new Student_Details();
        		StudentManagement studentManagement = new StudentManagement();
        		student_Details = studentManagement.getStudent(student_id);
        		student_Details.setParent_id(parent_id);
        		ParentManagement parentManagement = new ParentManagement();      		
        		String saved_token = parentManagement.getParentSavedToken(parent_id);
        		
        		ArrayList<Integer> selectedCourseList = new ArrayList<Integer>();
        		String sSubscribedCourses = "select course_id from Student_Electives where canceled <> 1 and student_id="+student_id+" and Payment_Renewal_Date<='"+today+"'";
        		ResultSet resultSubscribedCourses = statementSubscribedCourses.executeQuery(sSubscribedCourses);
        		while(resultSubscribedCourses.next())
        		{
        			selectedCourseList.add(resultSubscribedCourses.getInt("Course_id"));
        		}
        		student_Details.setSelectedCourseList(selectedCourseList);
        		PaymentManagement paymentManagement = new PaymentManagement();
        		boolean bad_saved_token = parentManagement.checkBadtSavedToken(saved_token);
        		/*if(saved_token!=null && !saved_token.isEmpty() && bad_saved_token==false)
        		{
        			int integration_id=411799;
        			paymentManagement.RecurringPayment(student_Details, saved_token, integration_id, false);		
        		}*/
        		if(bad_saved_token==true)
        		{
        			//int integration_id=406614; //////testing
        			int integration_id=406943; ////////Production
        			payment_token = paymentManagement.FirstTimePayment(student_Details, integration_id, false);
        		}
        	}
    	}
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return payment_token;
    }
    
    public Child GetDueRenewal(int parent_id) {
    	Date javaToday = new Date();
    	java.sql.Date today = new java.sql.Date(javaToday.getTime());
    	String sStudentDueRenewal = "select DISTINCT Student_id from Student_Electives where canceled <> 1 and Payment_Renewal_Date<='"+today+"' and Parent_id="+parent_id;
    	//String sStudentDueRenewal = "select DISTINCT Student_id from Student_Electives where Parent_id="+parent_id;
		Child child = new Child();
    	try (Connection connection = DbConnector.connectToDb();
    			 Statement statementStudentDueRenewal = connection.createStatement();
    			Statement statementSubscribedCourses = connection.createStatement();
    			Statement statementCourseDetails = connection.createStatement();){
    		
    		ResultSet resultStudentDueRenewal = statementStudentDueRenewal.executeQuery(sStudentDueRenewal);
       	 	System.out.println(sStudentDueRenewal);
       	 	HashMap<Integer, ArrayList<ArrayList<String>>> dueRenewal = new HashMap<Integer, ArrayList<ArrayList<String>>>(); 
       		ArrayList<Integer> student_id_List = new ArrayList<Integer>();
       		ArrayList<String> first_Name = new ArrayList<String>();
       		ArrayList<String> last_Name = new ArrayList<String>();
       		while(resultStudentDueRenewal.next())
       	 	{
        		int student_id = resultStudentDueRenewal.getInt("Student_id");
        		System.out.println(student_id);
        		Student_Details student_Details = new Student_Details();
        		StudentManagement studentManagement = new StudentManagement();
        		student_Details = studentManagement.getStudent(student_id);
        		student_Details.setParent_id(parent_id);       		
        		ArrayList<ArrayList<String>>  dueCourses = new ArrayList<ArrayList<String>> ();
        		String sSubscribedCourses = "select course_id, payment_renewal_date from Student_Electives where canceled <> 1 and student_id="+student_id+" and Payment_Renewal_Date<='"+today+"'";
        		//String sSubscribedCourses = "select course_id from Student_Electives where student_id="+student_id;
        		ResultSet resultSubscribedCourses = statementSubscribedCourses.executeQuery(sSubscribedCourses);
        		CourseManagement courseManagement = new CourseManagement();
    			Course_Details course_Details = new Course_Details();
	    		int j=0;
	    		while(resultSubscribedCourses.next())
        		{
		    		dueCourses.add(new ArrayList<String>());
	    			course_Details = courseManagement.getCourse(resultSubscribedCourses.getInt("Course_id"));
        			String course_Name = course_Details.getCourse_Name();
        			String course_Price = Integer.toString(course_Details.getPrice());
        			dueCourses.get(j).add(0,null);
        			dueCourses.get(j).set(0, resultSubscribedCourses.getString("Course_id"));
        			dueCourses.get(j).add(1,null);
        			dueCourses.get(j).set(1, course_Name);
        			dueCourses.get(j).add(2,null);
        			dueCourses.get(j).set(2, course_Price);

        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        			String payment_renewal_Date = sdf.format(resultSubscribedCourses.getDate("payment_renewal_date"));    			
        			dueCourses.get(j).add(3,null);
        			dueCourses.get(j).set(3, payment_renewal_Date);
            		j=j+1;
        		}
        		dueRenewal.put(student_id, dueCourses);
        		student_id_List.add(student_id);
        		first_Name.add(student_Details.getFirst_Name());
        		last_Name.add(student_Details.getLast_Name());
        	}
    		child.setDueRenewal(dueRenewal);
       	 	child.setStudent_id(student_id_List);
    		child.setFirst_Name(first_Name);
    		child.setLast_Name(last_Name);
    	}
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return child;
    }
}
