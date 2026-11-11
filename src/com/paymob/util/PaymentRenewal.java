package com.paymob.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.beans.admin.Student_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.account.AcademyEmails;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;

public class PaymentRenewal implements Runnable {
    @Override

       public void run() {
    	Date javaToday = new Date();
    	java.sql.Date today = new java.sql.Date(javaToday.getTime());
    	String sStudentDueRenewal = "select DISTINCT Student_id, Parent_id from Student_Electives where canceled <> 1 and Payment_Renewal_Date<='"+today+"'";

    	try (Connection connection = DbConnector.connectToDb();
    			 Statement statementStudentDueRenewal = connection.createStatement();
    			Statement statementSubscribedCourses = connection.createStatement();
    			Statement statementCourseDetails = connection.createStatement();
    			Statement statementGetExpiredCourses = connection.createStatement();
    			Statement statementDeleteExpiredSubjects = connection.createStatement();
    			Statement statementDeleteExpiredGroups = connection.createStatement();
    			Statement statementsRenewalReminder = connection.createStatement();
    			Statement statementsFinalRenewalReminder = connection.createStatement();){
    		
    		ResultSet resultStudentDueRenewal = statementStudentDueRenewal.executeQuery(sStudentDueRenewal);
       	 System.out.println(sStudentDueRenewal);
    		while(resultStudentDueRenewal.next())
        	{
        		
        		int student_id = resultStudentDueRenewal.getInt("Student_id");
        		System.out.println(student_id);
        		int parent_id = resultStudentDueRenewal.getInt("Parent_id");
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
        		if(saved_token!=null && !saved_token.isEmpty() && bad_saved_token==false)
        		{
        			//int integration_id=411799; //Moto testing
        			int integration_id=516077;   //Moto Production
        			paymentManagement.RecurringPayment(student_Details, saved_token, integration_id, true);		
        		}
        	}
    		//////////Delete canceled subscription as well as not renewed ones//////////////////////
    		String sGetExpiredCourses = "select * from Student_Electives where payment_renewal_date<'"+today+"' and course_end_date<'"+today+"'";
    		ResultSet resultGetExpiredCourses = statementGetExpiredCourses.executeQuery(sGetExpiredCourses);
    		while(resultGetExpiredCourses.next())
    		{
    			String sDeleteExpiredSubjects = "Delete from Student_Electives where student_id="+resultGetExpiredCourses.getInt("Student_id")+" and course_id="+resultGetExpiredCourses.getInt("Course_id");
        		statementDeleteExpiredSubjects.executeUpdate(sDeleteExpiredSubjects);
        		String sDeleteExpiredGroups = "Delete from Student_Groups where student_id="+resultGetExpiredCourses.getInt("Student_id")+" and group_id in (select group_id from "+"`Groups`"+" where course_id="+resultGetExpiredCourses.getInt("Course_id")+")";
        		statementDeleteExpiredGroups.executeUpdate(sDeleteExpiredGroups);
    		}
    		/////////////////////////////////////////////////////////////////////////////////////////////  		
    		///////////////////////////////Reminder for subscriptions coming 5 days ahead and 2 days ahead///////////////////////////////////
    		Calendar calFive = Calendar.getInstance();
    		calFive.add(Calendar.DAY_OF_MONTH, 5);  
    		Date FiveDaysReminderDateJava = calFive.getTime();
    		java.sql.Date FiveDaysReminderDate = new java.sql.Date(FiveDaysReminderDateJava.getTime());
    		Calendar calTwo = Calendar.getInstance();
    		calTwo.add(Calendar.DAY_OF_MONTH, 2);   
    		Date TwoDaysReminderDateJava = calTwo.getTime();
    		java.sql.Date TwoDaysReminderDate =  new java.sql.Date(TwoDaysReminderDateJava.getTime());
    		String sRenewalReminder = "select DISTINCT Student_id, Parent_id from Student_Electives where canceled <> 1 and (Payment_Renewal_Date='"+FiveDaysReminderDate+"' or Payment_Renewal_Date='"+TwoDaysReminderDate+"')";
    		System.out.println(sRenewalReminder);
    		
    		ResultSet resultsRenewalReminder = statementsRenewalReminder.executeQuery(sRenewalReminder);
    		while(resultsRenewalReminder.next())
        	{     		
        		int student_id = resultsRenewalReminder.getInt("Student_id");
        		int parent_id = resultsRenewalReminder.getInt("Parent_id");
        		ArrayList<Integer> selectedCourseList = new ArrayList<Integer>();
        		String sSubscribedCourses = "select course_id from Student_Electives where canceled <> 1 and student_id="+student_id+" and (Payment_Renewal_Date='"+FiveDaysReminderDate+"' or Payment_Renewal_Date='"+TwoDaysReminderDate+"')";
        		ResultSet resultSubscribedCourses = statementSubscribedCourses.executeQuery(sSubscribedCourses);
        		while(resultSubscribedCourses.next())
        		{
        			selectedCourseList.add(resultSubscribedCourses.getInt("Course_id"));
        		}
        		AcademyEmails academyEmails = new AcademyEmails();
        		academyEmails.RenewalReminder(parent_id, student_id, selectedCourseList);
        		
        	}
    		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		//////////Reminder just one day before deleting the child from subscribed but not yet renewed subjects//////////////////////
    		String sFinalRenewalReminder = "select DISTINCT Student_id, Parent_id from Student_Electives where canceled <> 1 and payment_renewal_date<'"+today+"' and course_end_date='"+today+"'";   		
    		ResultSet resultsFinalRenewalReminder = statementsFinalRenewalReminder.executeQuery(sFinalRenewalReminder);
    		while(resultsFinalRenewalReminder.next())
        	{     		
        		int student_id = resultsFinalRenewalReminder.getInt("Student_id");
        		int parent_id = resultsFinalRenewalReminder.getInt("Parent_id");
        		ArrayList<Integer> selectedCourseList = new ArrayList<Integer>();
        		String sSubscribedCourses = "select course_id from Student_Electives where canceled <> 1 and student_id="+student_id+" and payment_renewal_date<'"+today+"' and course_end_date='"+today+"'";   
        		ResultSet resultSubscribedCourses = statementSubscribedCourses.executeQuery(sSubscribedCourses);
        		while(resultSubscribedCourses.next())
        		{
        			selectedCourseList.add(resultSubscribedCourses.getInt("Course_id"));
        		}
        		AcademyEmails academyEmails = new AcademyEmails();
        		academyEmails.FinalRenewalReminder(parent_id, student_id, selectedCourseList);
        		
        	}
    		/////////////////////////////////////////////////////////////////////////////////////////////  		
    		AcademyEmails academyEmails = new AcademyEmails();
    		academyEmails.confirmEmail();
    	}
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
/*    public static void main(String[] args) {
    	PaymentRenewal ste = new PaymentRenewal();
        ste.run();
    }*/
}
