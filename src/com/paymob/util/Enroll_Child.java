package com.paymob.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import model.beans.admin.Student_Details;
import model.dbHelpers.account.AcademyEmails;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.GroupManagement;
import model.dbHelpers.admin.ParentManagement;
import model.dbHelpers.admin.StudentManagement;

/**
 * Servlet implementation class Enroll_Child
 */
@WebServlet("/Enroll_Child")
public class Enroll_Child extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enroll_Child() {
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

		StringBuilder builder = new StringBuilder();
		String aux = "";
		while ((aux = request.getReader().readLine()) != null) {
			builder.append(aux);
		}		
		String text = builder.toString();
		String card_token = null;
		try {
			JSONObject json = new JSONObject(text);
			JSONObject obj = json.getJSONObject("obj");
			String type = json.getString("type");
			if(type.equals("TOKEN"))
			{
				card_token = obj.getString("token");
				System.out.println("card_token:" + card_token);
				System.out.println("whole text:" + text);
				///////Save the token in Order_Token table as temp token///////////////////////
				int order_id = Integer.parseInt(obj.getString("order_id"));
				ParentManagement parentManagement = new ParentManagement();
				parentManagement.createOrderToken(order_id, card_token);
				////////////////////////////////////////////////////////////////////////////////
			}
			if(type.equals("TRANSACTION"))
			{
				JSONObject data = obj.getJSONObject("data");
				String txn_response_code = data.getString("txn_response_code");
				JSONObject order = obj.getJSONObject("order");
				int order_id = order.getInt("id");	
				JSONObject shipping_data = order.getJSONObject("shipping_data");
				StudentManagement studentManagement = new StudentManagement();
			    int student_id = studentManagement.getStudentByUsername(shipping_data.getString("street"));
				boolean autoRenewal = order.getBoolean("delivery_needed");
				int parent_id = Integer.parseInt(shipping_data.getString("country"));
				ParentManagement parentManagement = new ParentManagement();
				parentManagement.createParentOrder(parent_id, order_id);
				String permenant_saved_token = parentManagement.getParentSavedToken(parent_id);
				boolean first_time_payment = false;
				//////////////////////////Retrieve selected subject/////////////////////////
				ArrayList<Integer> selectedCourseList = new ArrayList<Integer>();
				JSONArray jsonArray = new JSONArray();
				jsonArray = order.getJSONArray("items");
			    for (int i = 0, size = jsonArray.length(); i < size; i++)
			    {
			      JSONObject objectInArray = jsonArray.getJSONObject(i);
			      String[] elementNames = JSONObject.getNames(objectInArray);
			      for (String elementName : elementNames)
			      {
			        if(elementName.equals("name"))
			        {
			        	String course_id = objectInArray.getString(elementName);
			        	selectedCourseList.add(Integer.parseInt(course_id));
			        }
			    	  
			      }

			    }
			    //////////////////////////////////////////////////////////////////////////	
				if(permenant_saved_token==null)
				{
					first_time_payment = true;
				}				
				boolean success = obj.getBoolean("success");
				System.out.println("success:" + success);
				System.out.println("Whole Transaction:" + text);
				System.out.println("first time payment:" + first_time_payment);
				System.out.println("auto renewal:" + autoRenewal);
				
				if(success == true)
				{				
					boolean successful_transaction = true;
					String event = null;
					String saved_token = parentManagement.getTokenByOrder(order_id);
					if(saved_token!=null && !saved_token.isEmpty())
					{					
						parentManagement.setParentSavedToken(parent_id, saved_token);
						parentManagement.setBadSavedToken(parent_id, 0);
						//parentManagement.deleteUnusedToken();
					}								
					/////////////Retrieve Student Details //////////////////////////////				
					if(student_id==0)
					{
						String first_Name = shipping_data.getString("first_name");
						String last_Name = shipping_data.getString("last_name");
						String gender = shipping_data.getString("apartment");
						String birth_Date = shipping_data.getString("floor");
						String username = shipping_data.getString("street");
						String password = shipping_data.getString("building");
						String mobile = shipping_data.getString("postal_code");
						int grade_id = Integer.parseInt(shipping_data.getString("city"));
						int school_id = Integer.parseInt(shipping_data.getString("state"));
						AccountManagement accountManagement = new AccountManagement();    		
						int account_id = accountManagement.getSchoolAccount(school_id);
						int class_id = 0; //classManagement.getClassByGrade(grade_id);
						/////////////////////Populate the bean////////////////////////////////////
						Student_Details student_Details = new Student_Details();
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
						student_Details.setSelectedCourseList(selectedCourseList);
						student_Details.setFirst_Login(0);
					    /////////////////////////////////////////////////////////////////////////
						
						///////////////////Create the student and enroll///////////////////////////
						
						int autoIncValue = studentManagement.createStudent(student_Details);
	    		        if (autoIncValue!=-1 && autoIncValue!=-2)
	    		        {
	    		        	student_id = autoIncValue;
	    					//////////////////Assign the child to his parent///////////////////////////////////
	    					int updateQuery = parentManagement.linkParentStudent(student_id, parent_id);
	    					if(updateQuery==0)
	    					{
	    						successful_transaction = false;
	    					}	    						
	    					///////////////////////////////////////////////////////////////////////////////////
	    		        }
	    		        else
	    		        {
	    		        	successful_transaction = false;
	    		        }
	    		        /////////////////////////////////////////////////////////////////////////////////////
					}
		        	/////////////////////Add Student to Elective ///////////////////////////////////    		        	
					CourseManagement courseManagement = new CourseManagement();
					for (Integer course_id : selectedCourseList) 
					{  						
							boolean studentExistsInElective = false;
							studentExistsInElective = courseManagement.checkStudentElective(student_id, course_id);
							if (studentExistsInElective==false)
							{
								int updateQuery = courseManagement.addParentChildToElective(student_id, course_id, parent_id);
								if(updateQuery!=0)
								{
									/////////////////////////Assign Student to Group///////////////////////////////
		        					GroupManagement groupManagement = new GroupManagement();
		        					Date start_date = groupManagement.addParentChildToGroup(student_id, course_id); 
		        					if(start_date!=null)
		        					{
		        						courseManagement.setCourseEndDate(course_id, student_id, start_date);
		        						event = "New_Subscription";
		        					} 
		        					else
		        					{
		        						successful_transaction = false;
		        						break;
		        					}
								}
							}	
							else
							{
								int updateQuery = courseManagement.setCourseRenewalDates(course_id, student_id);
								if(updateQuery==0)
								{
									successful_transaction = false;
									break;
								}
								event = "Subscription_Renewal";
							}
					}
					if(successful_transaction == false)
					{
						////////In order to refund the transaction (later) and return the money back to the parent///
						parentManagement.updateOrderToken(order_id, "Transaction_Refunded", 4);
					}
					else
					{
						parentManagement.updateOrderToken(order_id, txn_response_code, 0);
						AcademyEmails academyEmails = new AcademyEmails();
						if(event!=null && event.equals("New_Subscription"))
						{
							academyEmails.uponSubscription(parent_id, student_id, selectedCourseList, order_id);
						}
						else
						{
							academyEmails.uponRenewal(parent_id, student_id, selectedCourseList, order_id);
						}
						
					}
				}
				else
				{
					if(first_time_payment==true)
					{
						//////failed during payment with new card//////////
						parentManagement.updateOrderToken(order_id, txn_response_code, 1);
					}
					else
					{
						//payment failed with saved token//////////
						parentManagement.setBadSavedToken(parent_id, 1);
						if(autoRenewal==false)
						{
							/////failed when parent himself is adding a new child or new subjects////////						
							parentManagement.updateOrderToken(order_id, txn_response_code, 2);
						}
						else
						{
	
							/////////failed during Auto renewal by the scheduler///////////////////////////
							parentManagement.updateOrderToken(order_id, txn_response_code, 3);
							///////////Get base URL //////////////////////////////////////////////////////////////////////
							StringBuffer url = request.getRequestURL();
							if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
							{
								url.replace(0, 4, "https");
							}
							String uri = request.getRequestURI();
							String ctx = request.getContextPath();
							String base = url.substring(0, url.length() - uri.length() + ctx.length());							
							/////////////////////////////////////////////////////////////////////////////////////////////
							AcademyEmails academyEmails = new AcademyEmails();
							academyEmails.uponFailedRenewal(parent_id, student_id, selectedCourseList, base);							
						}
					}
				}

			}

		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

