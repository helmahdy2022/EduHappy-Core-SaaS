package com.sendpulse.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Subscribers {
	public void AddSubscriber(String name, String email)
	{
    String Auth_url = "https://api.sendpulse.com/oauth/access_token";
    String Add_Email_url = "https://api.sendpulse.com/addressbooks/599346/emails";
    
    String client_id = "4a0766ffcd61062c3cbcf4b4095bed25";
    String client_secret = "9f0bb3eee176fd49344463c23e94c629";
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
    String auth_param = new JSONObject()
            .put("grant_type", "client_credentials")
            .put("client_id", client_id)
            .put("client_secret", client_secret)
            .toString();
    conn.setRequestProperty("api_key", auth_param);
    OutputStream os = conn.getOutputStream();
    os.write(auth_param.getBytes("UTF-8"));
    os.close(); 
    // read the response
    InputStream in = new BufferedInputStream(conn.getInputStream());
    String result = IOUtils.toString(in, "UTF-8");
    JSONObject myResponse = new JSONObject(result);
    token = myResponse.getString("access_token");
    /////////////////////////////////////////////////////////////////////////////
    URL add_Email_url = new URL(Add_Email_url);
    conn = (HttpURLConnection) add_Email_url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setRequestProperty("Accept","application/json");
    conn.setRequestProperty("Authorization", "Bearer " +token);
    conn.setConnectTimeout(5000);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    JSONArray variables = new JSONArray();
    JSONObject var_element = new JSONObject();
    var_element.put("name", name);
    variables.put(var_element);

    JSONArray emails = new JSONArray();
    JSONObject email_element = new JSONObject();
    
    email_element.put("email", email);
    email_element.put("variables", var_element);
    emails.put(email_element);
    JSONObject subscriber_email = new JSONObject();
    String subscriber = subscriber_email.put("emails", emails).toString(); 

    System.out.println(subscriber);
    conn.setRequestProperty("subscriber", subscriber);
    os = conn.getOutputStream();
    os.write(subscriber.getBytes("UTF-8"));
    os.close(); 
    // read the response
    in = new BufferedInputStream(conn.getInputStream());
    result = IOUtils.toString(in, "UTF-8");
     myResponse = new JSONObject(result);
    boolean result_res = myResponse.getBoolean("result");
    System.out.println(result_res);
 
    } catch (Exception e) {
		System.out.println(e);
    }
		
	}
	}

/*	public static void main(String[] args) {
	Subscribers s = new Subscribers();
    s.AddSubscriber("basma", "basma@basmalino.com");;
}*/
}
