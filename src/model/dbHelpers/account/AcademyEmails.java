package model.dbHelpers.account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.beans.admin.Course_Details;
import model.beans.admin.Parent_Details;
import model.beans.admin.Student_Details;
import model.dbHelpers.DbConnector;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;

public class AcademyEmails {

	public void uponSubscription(int parent_id, int student_id, ArrayList<Integer> selectedCourseList, int order_id) {
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGroupDetails = connection.createStatement();
				Statement statementElectiveDetails = connection.createStatement();){
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details parent_Details = new Parent_Details();
		StudentManagement studentManagement = new StudentManagement();
		Student_Details student_Details = new Student_Details();
		parent_Details = parentManagement.getParent(parent_id);
		student_Details = studentManagement.getStudent(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();
		
		String msg_subject = "EduHappy Academy - Thank you for your subscription - Order#"+order_id;
		String msg_body = "Hi "+parent_Details.getFirst_Name()+",<br/><br/>";
		msg_body = msg_body+ "This is to inform you that your new subscription is successful via order#"+order_id+".<br/><br/>";
		msg_body = msg_body+ "Please login to your parent portal to get more details about the subscription.<br/><br/>";
		msg_body = msg_body+  "Please provide your child with the following link to login to his/her student portal using the username and password you've just created:<br/>";
		msg_body = msg_body+  "<a href='https://eduhappylms.com/student' target='_blank'>https://eduhappylms.com/student</a><br/><br/>";
		msg_body = msg_body+ "Subscription Details:<br/><br/>";
		msg_body = msg_body+ "Child Name: <b>"+student_Details.getFirst_Name()+" "+student_Details.getLast_Name()+"</b>";
		msg_body = msg_body+ "<br/><br/>";
		msg_body = msg_body+ "School: <b>"+student_Details.getSchool_Name()+ "</b> - Grade: <b>"+student_Details.getGrade_Name()+"</b>";
		for(Integer course_id : selectedCourseList)
		{
			course_Details = courseManagement.getCourse(course_id);
			String sGroupDetails = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultGroupDetails = statementGroupDetails.executeQuery(sGroupDetails);
			String group_Name = null;
			String start_Date = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(resultGroupDetails.next())
			{
				group_Name = resultGroupDetails.getString("Group_Name");
				start_Date = sdf.format(resultGroupDetails.getDate("Start_Date"));
			}
			String sElectiveDetails = "select * from Student_Electives where course_id="+course_id+" and student_id="+student_id;
			ResultSet resultElectiveDetails = statementElectiveDetails.executeQuery(sElectiveDetails);
			String course_end_date = null;
			String payment_renewal_date = null;
			while(resultElectiveDetails.next())
			{
				course_end_date = sdf.format(resultElectiveDetails.getDate("course_end_date"));
				payment_renewal_date = sdf.format(resultElectiveDetails.getDate("payment_renewal_date"));
			}
			msg_body = msg_body+ "<br/><br/>";
			msg_body = msg_body+ "Subject: <b>"+course_Details.getCourse_Name()+"</b>.<br/><br/>";
			msg_body = msg_body+ "Your child has been assigned to <b>"+course_Details.getCourse_Name()+" "+group_Name+ "</b> which will start on <b>"+ start_Date+ "</b> and will end on <b>"+course_end_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "This end date will be extended 4 weeks after we autorenew the subscription every four weeks unless you cancel before the subscription renewal date.<br/><br/>";
			msg_body = msg_body+ "Next Subscription Renewal Date will be on <b>"+payment_renewal_date+"</b>.<br/><br/>";	
		}
		msg_body = msg_body+ "You can manage the subscriptions from Parent Portal -> Manage Subscriptions where you can cancel anytime or add new subjects.<br/><br/>";
		msg_body = msg_body+ "You can add another child with another subscription from Parent Portal -> Add Child.<br/><br/>";
		msg_body = msg_body+ "We will prepare the schedule with the time and days per week for the online sessions for the enrolled subjects and we will send you the schedule once ready.<br/><br/>";
		msg_body = msg_body+ "Best of Luck,<br/><br/>";
		msg_body = msg_body+ "EduHappy Academy Team<br/><br/>";
		msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
		msg_body = msg_body+ "Email: info@edu-happy.com";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
		emailsender.sendemail("hossam_amin2000@hotmail.com", "Congratulations! New Subscription Notification Order#"+order_id, msg_body);
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public void uponRenewal(int parent_id, int student_id, ArrayList<Integer> selectedCourseList, int order_id) {
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGroupDetails = connection.createStatement();
				Statement statementElectiveDetails = connection.createStatement();){
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details parent_Details = new Parent_Details();
		StudentManagement studentManagement = new StudentManagement();
		Student_Details student_Details = new Student_Details();
		parent_Details = parentManagement.getParent(parent_id);
		student_Details = studentManagement.getStudent(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();
		
		String msg_subject = "EduHappy Academy - Subscription renewal is successful - Order#"+order_id;
		String msg_body = "Hi "+parent_Details.getFirst_Name()+",<br/><br/>";
		msg_body = msg_body+ "This is to inform you that we have successfully renewed your subscription. Order#"+order_id+"<br/><br/>";
		msg_body = msg_body+ "Please login to your parent portal to get more details about the subscription.<br/><br/>";
		msg_body = msg_body+ "Subscription Renewal Details:<br/><br/>";
		msg_body = msg_body+ "Child Name: <b>"+student_Details.getFirst_Name()+" "+student_Details.getLast_Name()+"</b>";
		msg_body = msg_body+ "<br/><br/>";
		msg_body = msg_body+ "School: <b>"+student_Details.getSchool_Name()+ "</b> - Grade: <b>"+student_Details.getGrade_Name()+"</b>";
		for(Integer course_id : selectedCourseList)
		{
			course_Details = courseManagement.getCourse(course_id);
			String sGroupDetails = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultGroupDetails = statementGroupDetails.executeQuery(sGroupDetails);
			String group_Name = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(resultGroupDetails.next())
			{
				group_Name = resultGroupDetails.getString("Group_Name");
			}
			String sElectiveDetails = "select * from Student_Electives where course_id="+course_id+" and student_id="+student_id;
			ResultSet resultElectiveDetails = statementElectiveDetails.executeQuery(sElectiveDetails);
			String course_end_date = null;
			String payment_renewal_date = null;
			while(resultElectiveDetails.next())
			{
				course_end_date = sdf.format(resultElectiveDetails.getDate("course_end_date"));
				payment_renewal_date = sdf.format(resultElectiveDetails.getDate("payment_renewal_date"));
			}
			msg_body = msg_body+ "<br/><br/>";
			msg_body = msg_body+ "Subject: <b>"+course_Details.getCourse_Name()+"</b>.<br/><br/>";
			msg_body = msg_body+ "Course End date for your child in <b>"+course_Details.getCourse_Name()+" "+group_Name+ "</b> has been extended 4 weeks and will end on <b>"+course_end_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "This end date will be extended 4 weeks after we autorenew the subscription every 4 weeks unless you cancel before the subscription renewal date.<br/><br/>";
			msg_body = msg_body+ "Next Subscription Renewal Date will be on <b>"+payment_renewal_date+"</b>.<br/><br/>";	
		}
		msg_body = msg_body+ "You can manage the subscriptions from Parent Portal -> Manage Subscriptions where you can cancel anytime or add new subjects.<br/><br/>";
		msg_body = msg_body+ "You can add another child with another subscription from Parent Portal -> Add Child.<br/><br/>";
		msg_body = msg_body+ "Best of Luck,<br/><br/>";
		msg_body = msg_body+ "EduHappy Academy Team<br/><br/>";
		msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
		msg_body = msg_body+ "Email: info@edu-happy.com";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public void uponFailedRenewal(int parent_id, int student_id, ArrayList<Integer> selectedCourseList, String base) {
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGroupDetails = connection.createStatement();
				Statement statementElectiveDetails = connection.createStatement();){
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details parent_Details = new Parent_Details();
		StudentManagement studentManagement = new StudentManagement();
		Student_Details student_Details = new Student_Details();
		parent_Details = parentManagement.getParent(parent_id);
		student_Details = studentManagement.getStudent(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();		
		String msg_subject = "EduHappy Academy - Failed to renew your subscription";
		String msg_body = "Hi "+parent_Details.getFirst_Name()+",<br/><br/>";
		msg_body = msg_body+ "This is to inform you that we, unfortunately, failed to renew your subscription due to some issues regarding your billing information.<br/><br/>";
		msg_body = msg_body+ "Please click on the following link to renew manually.<br/><br/>";
		msg_body = msg_body+ "<a href='"+base+"/Get_Due_Renewal'>Manual Subscription Renewal</a><br/><br/>";
		msg_body = msg_body+ "Subscription Renewal Details:<br/><br/>";
		msg_body = msg_body+ "Child Name: <b>"+student_Details.getFirst_Name()+" "+student_Details.getLast_Name()+"</b>";
		msg_body = msg_body+ "<br/><br/>";
		msg_body = msg_body+ "School: <b>"+student_Details.getSchool_Name()+ "</b> - Grade: <b>"+student_Details.getGrade_Name()+"</b>";
		for(Integer course_id : selectedCourseList)
		{
			course_Details = courseManagement.getCourse(course_id);
			String sGroupDetails = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultGroupDetails = statementGroupDetails.executeQuery(sGroupDetails);
			String group_Name = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(resultGroupDetails.next())
			{
				group_Name = resultGroupDetails.getString("Group_Name");
			}
			String sElectiveDetails = "select * from Student_Electives where course_id="+course_id+" and student_id="+student_id;
			ResultSet resultElectiveDetails = statementElectiveDetails.executeQuery(sElectiveDetails);
			String course_end_date = null;
			String payment_renewal_date = null;
			while(resultElectiveDetails.next())
			{
				course_end_date = sdf.format(resultElectiveDetails.getDate("course_end_date"));
				payment_renewal_date = sdf.format(resultElectiveDetails.getDate("payment_renewal_date"));
			}
			msg_body = msg_body+ "<br/><br/>";
			msg_body = msg_body+ "Subject: <b>"+course_Details.getCourse_Name()+"</b>.<br/><br/>";
			msg_body = msg_body+ "Course End date for your child in <b>"+course_Details.getCourse_Name()+" "+group_Name+ "</b> cannot be extended due to subscription renewal failure. Course end date will remain the same on <b>"+course_end_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "This end date will be extended 4 weeks after you manually renew the subscription from the above link. <br/><br/>";
			msg_body = msg_body+ "If you don't renew before the course end date your child will be removed from the subject and the group and will not be able to participate in its online sessions anymore.<br/><br/>";
			msg_body = msg_body+ "Overdue Subscription Renewal Date: <b>"+payment_renewal_date+"</b>.<br/><br/>";	
		}
		msg_body = msg_body+ "You can manage the subscriptions from Parent Portal -> Manage Subscriptions where you can cancel anytime or add new subjects.<br/><br/>";
		msg_body = msg_body+ "You can add another child with another subscription from Parent Portal -> Add Child.<br/><br/>";
		msg_body = msg_body+ "Best of Luck,<br/><br/>";
		msg_body = msg_body+ "EduHappy Academy Team<br/><br/>";
		msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
		msg_body = msg_body+ "Email: info@edu-happy.com";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public void RenewalReminder(int parent_id, int student_id, ArrayList<Integer> selectedCourseList) {
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGroupDetails = connection.createStatement();
				Statement statementElectiveDetails = connection.createStatement();){
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details parent_Details = new Parent_Details();
		StudentManagement studentManagement = new StudentManagement();
		Student_Details student_Details = new Student_Details();
		parent_Details = parentManagement.getParent(parent_id);
		student_Details = studentManagement.getStudent(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();		
		String msg_subject = "EduHappy Academy - Subscription renewal Reminder";
		String msg_body = "Hi "+parent_Details.getFirst_Name()+",<br/><br/>";
		msg_body = msg_body+ "This is to remind you that your subscription renewal will be due soon.<br/><br/>";
		msg_body = msg_body+ "Renewal will be done automatically so please make sure that your billing data we have is still valid.<br/><br/>";
		msg_body = msg_body+ "Subscription Renewal Details:<br/><br/>";
		msg_body = msg_body+ "Child Name: <b>"+student_Details.getFirst_Name()+" "+student_Details.getLast_Name()+"</b>";
		msg_body = msg_body+ "<br/><br/>";
		msg_body = msg_body+ "School: <b>"+student_Details.getSchool_Name()+ "</b> - Grade: <b>"+student_Details.getGrade_Name()+"</b>";
		for(Integer course_id : selectedCourseList)
		{
			course_Details = courseManagement.getCourse(course_id);
			String sGroupDetails = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultGroupDetails = statementGroupDetails.executeQuery(sGroupDetails);
			String group_Name = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(resultGroupDetails.next())
			{
				group_Name = resultGroupDetails.getString("Group_Name");
			}
			String sElectiveDetails = "select * from Student_Electives where course_id="+course_id+" and student_id="+student_id;
			ResultSet resultElectiveDetails = statementElectiveDetails.executeQuery(sElectiveDetails);
			String course_end_date = null;
			String payment_renewal_date = null;
			while(resultElectiveDetails.next())
			{
				course_end_date = sdf.format(resultElectiveDetails.getDate("course_end_date"));
				payment_renewal_date = sdf.format(resultElectiveDetails.getDate("payment_renewal_date"));
			}
			msg_body = msg_body+ "<br/><br/>";
			msg_body = msg_body+ "Subscription Renewal Date: <b>"+payment_renewal_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "Subject: <b>"+course_Details.getCourse_Name()+"</b>.<br/><br/>";
			msg_body = msg_body+ "Course End date for your child in <b>"+course_Details.getCourse_Name()+" "+group_Name+ "</b> is <b>"+course_end_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "This end date will be extended 4 weeks after successful renewal on the subscription renewal date on <b>"+payment_renewal_date+"</b>. <br/><br/>";
		}
		msg_body = msg_body+ "Meanwhile, you can manage the subscriptions from Parent Portal -> Manage Subscriptions where you can cancel anytime or add new subjects.<br/><br/>";
		msg_body = msg_body+ "You can add another child with another subscription from Parent Portal -> Add Child.<br/><br/>";
		msg_body = msg_body+ "Best of Luck,<br/><br/>";
		msg_body = msg_body+ "EduHappy Academy Team<br/><br/>";
		msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
		msg_body = msg_body+ "Email: info@edu-happy.com";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
		}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void FinalRenewalReminder(int parent_id, int student_id, ArrayList<Integer> selectedCourseList) {
		try (Connection connection = DbConnector.connectToDb();
				Statement statementGroupDetails = connection.createStatement();
				Statement statementElectiveDetails = connection.createStatement();){
		ParentManagement parentManagement = new ParentManagement();
		Parent_Details parent_Details = new Parent_Details();
		StudentManagement studentManagement = new StudentManagement();
		Student_Details student_Details = new Student_Details();
		parent_Details = parentManagement.getParent(parent_id);
		student_Details = studentManagement.getStudent(student_id);
		CourseManagement courseManagement = new CourseManagement();
		Course_Details course_Details = new Course_Details();		
		String msg_subject = "EduHappy Academy - Final Reminder - Subscription renewal";
		String msg_body = "Hi "+parent_Details.getFirst_Name()+",<br/><br/>";
		msg_body = msg_body+ "This is to remind you that your subscription renewal date was passed a while ago and until now you haven't renewed the subscription.<br/><br/>";
		msg_body = msg_body+ "Your kind attention is required to renew the subscription today to avoid removing the child tomorrow from the enrolled subjects.<br/><br/>";
		//msg_body = msg_body+ "Please click on the following link to renew manually.<br/><br/>";
		//msg_body = msg_body+ "<a href='"+base+"/Get_Due_Renewal'>Manual Subscription Renewal</a><br/><br/>";
		msg_body = msg_body+ "Subscription Renewal Details:<br/><br/>";
		msg_body = msg_body+ "Child Name: <b>"+student_Details.getFirst_Name()+" "+student_Details.getLast_Name()+"</b>";
		msg_body = msg_body+ "<br/><br/>";
		msg_body = msg_body+ "School: <b>"+student_Details.getSchool_Name()+ "</b> - Grade: <b>"+student_Details.getGrade_Name()+"</b>";
		for(Integer course_id : selectedCourseList)
		{
			course_Details = courseManagement.getCourse(course_id);
			String sGroupDetails = "select * from "+"`Groups`"+" where course_id="+course_id+" and group_id in (select group_id from student_groups where student_id="+student_id+")";
			ResultSet resultGroupDetails = statementGroupDetails.executeQuery(sGroupDetails);
			String group_Name = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(resultGroupDetails.next())
			{
				group_Name = resultGroupDetails.getString("Group_Name");
			}
			String sElectiveDetails = "select * from Student_Electives where course_id="+course_id+" and student_id="+student_id;
			ResultSet resultElectiveDetails = statementElectiveDetails.executeQuery(sElectiveDetails);
			String course_end_date = null;
			String payment_renewal_date = null;
			while(resultElectiveDetails.next())
			{
				course_end_date = sdf.format(resultElectiveDetails.getDate("course_end_date"));
				payment_renewal_date = sdf.format(resultElectiveDetails.getDate("payment_renewal_date"));
			}
			msg_body = msg_body+ "<br/><br/>";
			msg_body = msg_body+ "Subject: <b>"+course_Details.getCourse_Name()+"</b>.<br/><br/>";
			msg_body = msg_body+ "Course End date for your child in <b>"+course_Details.getCourse_Name()+" "+group_Name+ "</b> will be today <b>"+course_end_date+"</b>.<br/><br/>";
			msg_body = msg_body+ "This end date will be extended 4 weeks after you manually renew the subscription. <br/><br/>";
			msg_body = msg_body+ "You kindly need to renew today or otherwise your child will be removed from the subject and the group and will not be able to participate in its online sessions anymore.<br/><br/>";
			msg_body = msg_body+ "Overdue Subscription Renewal Date: <b>"+payment_renewal_date+"</b>.<br/><br/>";	
		}
		msg_body = msg_body+ "You can manage the subscriptions from Parent Portal -> Manage Subscriptions where you can cancel anytime or add new subjects.<br/><br/>";
		msg_body = msg_body+ "You can add another child with another subscription from Parent Portal -> Add Child.<br/><br/>";
		msg_body = msg_body+ "Best of Luck,<br/><br/>";
		msg_body = msg_body+ "EduHappy Academy Team<br/><br/>";
		msg_body = msg_body+ "<a href='https://edu-happy.com/academy'>edu-happy.com/academy</a><br/><br/>";
		msg_body = msg_body+ "Email: info@edu-happy.com";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail(parent_Details.getEmail(), msg_subject, msg_body);
		}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void confirmEmail()
	{
		String msg_subject = "EduHappy Academy - Daily Reminder Confirmation";
		String msg_body = "Hi Hossam,<br/><br/>";
		msg_body = msg_body+ "This is to confirm that daily task has been successfully run at the scheduled time<br/><br/>";
		EmailSender emailsender = new EmailSender();
		emailsender.sendemail("hossam_amin2000@hotmail.com", msg_subject, msg_body);
	}
}
