package com.paymob.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import model.beans.admin.Course_Details;
import model.beans.admin.Parent_Details;
import model.beans.admin.Student_Details;
import model.dbHelpers.admin.CourseManagement;
import model.dbHelpers.admin.ParentManagement;

public class  PaymentManagement {

	public String FirstTimePayment(Student_Details student_Details, int integration_id, boolean autoRenewal)
	{
    String Auth_url = "https://accept.paymob.com/api/auth/tokens";
    String Order_url = "https://accept.paymob.com/api/ecommerce/orders";
    String Payment_url = "https://accept.paymob.com/api/acceptance/payment_keys";
    String api_key = "{\"api_key\": \"ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpiR0Z6Y3lJNklrMWxjbU5vWVc1MElpd2ljSEp2Wm1sc1pWOXdheUk2TVRBNU1qRTJMQ0p1WVcxbElqb2lhVzVwZEdsaGJDSjkuM2xneVlFbkx4bzZLajRHbFlIUzZyX3VocVNOMUVNOC14eDIzbjNWT0ZNWlFpTjBJU3VlSFJ0ZHNDc3NLZm1LbEhWWE5fS0tQU3Q5SVNzRm9DMWc2Rnc=\"}";
    String token = null;
    String order = null;
    String paymentKey = null;
    int order_id = 0;
    String payment_token = null;

    {
    
    try {

    URL auth_url = new URL(Auth_url);
    HttpURLConnection conn = (HttpURLConnection) auth_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setRequestProperty("api_key", api_key);
    OutputStream os = conn.getOutputStream();
    os.write(api_key.getBytes("UTF-8"));
    os.close(); 
    // read the response
    InputStream in = new BufferedInputStream(conn.getInputStream());
    String result = IOUtils.toString(in, "UTF-8");
    JSONObject myResponse = new JSONObject(result);
    token = myResponse.getString("token");
    /////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////Send Order and get Order ID/////////////////////////////////////////////
    URL order_url = new URL(Order_url);
    conn = (HttpURLConnection) order_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    
    JSONArray array = new JSONArray();

    int fees = 0;
	CourseManagement courseManagement = new CourseManagement();
	Course_Details course_Details = new Course_Details();
    for (Integer subject : student_Details.getSelectedCourseList())
    {
    	course_Details = courseManagement.getCourse(subject);
        JSONObject item = new JSONObject();
    	item.put("name", Integer.toString(subject));
        item.put("amount_cents", Integer.toString(course_Details.getPrice()*100));
        item.put("description", course_Details.getCourse_Name());
        item.put("quantity", "1");
        fees = fees + course_Details.getPrice();
        array.put(item);
    }
    order = new JSONObject()
            .put("auth_token", token)
            .put("delivery_needed", autoRenewal)
            .put("amount_cents", Integer.toString(fees*100))
            .put("currency", "EGP")
            .put("items", array)
            .toString();
    
    System.out.println(order);
    conn.setRequestProperty("order", order);
    os = conn.getOutputStream();
    os.write(order.getBytes("UTF-8"));
    os.close(); 
    // read the response
    in = new BufferedInputStream(conn.getInputStream());
    result = IOUtils.toString(in, "UTF-8");
     myResponse = new JSONObject(result);
    order_id = myResponse.getInt("id");
    //////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////Request Payment Key and get Payment Token///////////////////////////
    URL payment_url = new URL(Payment_url);
    conn = (HttpURLConnection) payment_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    
    ParentManagement parentManagement = new ParentManagement();
    Parent_Details parent_Details = new Parent_Details();
    parent_Details = parentManagement.getParent(student_Details.getParent_id());
    JSONObject billing_data = new JSONObject();
    billing_data.put("email", parent_Details.getEmail());
    billing_data.put("first_name", student_Details.getFirst_Name());
    billing_data.put("last_name", student_Details.getLast_Name());
    billing_data.put("phone_number", parent_Details.getMobile());
    billing_data.put("apartment", student_Details.getGender());
    billing_data.put("floor", student_Details.getBirth_Date());
    billing_data.put("street", student_Details.getUsername());
    billing_data.put("building", student_Details.getPassword());
    billing_data.put("shipping_method", "NA");
    billing_data.put("postal_code", student_Details.getMobile());
    billing_data.put("city", student_Details.getGrade_id());
    billing_data.put("country", student_Details.getParent_id());
    billing_data.put("state", student_Details.getSchool_id());
    paymentKey = new JSONObject()
            .put("auth_token", token)
            .put("amount_cents", Integer.toString(fees*100))
            .put("expiration", 3600)
            .put("order_id", order_id)
            .put("currency", "EGP")
            .put("integration_id", integration_id)
            .put("lock_order_when_paid", "false")
            .put("billing_data", billing_data)
            .toString();
    
    System.out.println(paymentKey);
    conn.setRequestProperty("Payment_Key", paymentKey);
    os = conn.getOutputStream();
    os.write(paymentKey.getBytes("UTF-8"));
    os.close(); 
    // read the response
    in = new BufferedInputStream(conn.getInputStream());
    result = IOUtils.toString(in, "UTF-8");
    myResponse = new JSONObject(result);
    payment_token = myResponse.getString("token");

    ////////////////////////////////////////////////////////////////////////////////////////////////

    } catch (Exception e) {
		System.out.println(e);
    }
		
	}
	return payment_token;
	}
	public String  RecurringPayment(Student_Details student_Details, String saved_token, int integration_id, boolean autoRenewal)
	{
    String Recurring_Payment_url = "https://accept.paymobsolutions.com/api/acceptance/payments/pay";
    String payment_token = null;
    String redirection_url = null;
    {
    
    try {

    payment_token = FirstTimePayment(student_Details, integration_id, autoRenewal); 
    //////////////////////////Proceed to the recurring payment /////////////////////////////////////
    URL recurring_payment_url = new URL(Recurring_Payment_url);
    HttpURLConnection conn = (HttpURLConnection) recurring_payment_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setRequestProperty("Accept","application/json");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    JSONObject recurring_source = new JSONObject();
    recurring_source.put("identifier", saved_token);
    recurring_source.put("subtype", "TOKEN");
    String recurring_data = new JSONObject()
    .put("source", recurring_source)
    .put("payment_token", payment_token)
    .toString();
    conn.setRequestProperty("Recurring Data", recurring_data);
    OutputStream os = conn.getOutputStream();
    os = conn.getOutputStream();
    os.write(recurring_data.getBytes("UTF-8"));
    os.close(); 
    // read the response
    InputStream in = new BufferedInputStream(conn.getInputStream());
    String result = IOUtils.toString(in, "UTF-8");
    JSONObject myResponse = new JSONObject(result);
    redirection_url = myResponse.getString("redirection_url");
    System.out.println(redirection_url);
    /////////////////////////////////////////////////////////////////////////////////////////////////

    } catch (Exception e) {
		System.out.println(e);
		
    }
		
	}
	return redirection_url;
	}

	public void RefundTransaction(int transaction_id, int fees)
	{
    String Auth_url = "https://accept.paymob.com/api/auth/tokens";
    String api_key = "{\"api_key\": \"ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpiR0Z6Y3lJNklrMWxjbU5vWVc1MElpd2ljSEp2Wm1sc1pWOXdheUk2TVRBNU1qRTJMQ0p1WVcxbElqb2lhVzVwZEdsaGJDSjkuM2xneVlFbkx4bzZLajRHbFlIUzZyX3VocVNOMUVNOC14eDIzbjNWT0ZNWlFpTjBJU3VlSFJ0ZHNDc3NLZm1LbEhWWE5fS0tQU3Q5SVNzRm9DMWc2Rnc=\"}";
    String token = null;
    {
    
    try {

    URL auth_url = new URL(Auth_url);
    HttpURLConnection conn = (HttpURLConnection) auth_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setRequestProperty("Accept","application/json");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setRequestProperty("api_key", api_key);
    OutputStream os = conn.getOutputStream();
    os.write(api_key.getBytes("UTF-8"));
    os.close(); 
    // read the response
    InputStream in = new BufferedInputStream(conn.getInputStream());
    String result = IOUtils.toString(in, "UTF-8");
    JSONObject myResponse = new JSONObject(result);
    token = myResponse.getString("token");
    
    URL refund_url = new URL("https://accept.paymob.com/api/acceptance/void_refund/refund");
    conn = (HttpURLConnection) refund_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setRequestProperty("Accept","application/json");
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    String transaction_refund = new JSONObject()
    .put("auth_token", token)
    .put("transaction_id", transaction_id)
    .put("amount_cents", fees)
    .toString();
    conn.setRequestProperty("transaction_refund", transaction_refund);
    os = conn.getOutputStream();
    os.write(transaction_refund.getBytes("UTF-8"));
    os.close(); 
    // read the response
    in = new BufferedInputStream(conn.getInputStream());
    result = IOUtils.toString(in, "UTF-8");
    myResponse = new JSONObject(result);
    //int new_trans_id = myResponse.getInt("id");
    System.out.println("result = "+result);
    /////////////////////////////////////////////////////////////////////////////
    } catch (Exception e) {
		System.out.println(e);
		
    }
		
	}
	//return result;
	} 
	
/*	public static void main(String[] args) {
	PaymentManagement ste = new PaymentManagement();
    ste.RefundTransaction(10151092, 500);
}*/
}
