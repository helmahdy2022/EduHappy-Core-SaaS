package com.moosend.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class MoosendIntegration {
	public static void AddSubscriber(String name, String email)
	{
    String Auth_url = "https://api.moosend.com/v3/subscribers/b1ae1072-e419-49cb-9c96-5b2399957980/subscribe.json?apikey=cbc96303-9ad5-4c56-8ba1-08464134f90a";
    int code = 0;
    {
	    
	    try {
	
	    URL auth_url = new URL(Auth_url);
	    HttpURLConnection conn = (HttpURLConnection) auth_url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    conn.setConnectTimeout(5000);
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    //conn.setRequestProperty("api_key", api_key);

	    /////////////////////////////////////////////////////////////////////////////
	    //name="Basma";
	    //email = "basmalino@yahoo.com";
	    /////////////////////////Send Subscriber data and get Response Code/////////////////////////////////////////////
	    String subscriber = new JSONObject()
	            .put("Name", name)
	            .put("Email", email)
	            .toString();
	    
	    System.out.println(subscriber);
	    conn.setRequestProperty("subscriber", subscriber);
	    OutputStream os = conn.getOutputStream();
	    os.write(subscriber.getBytes("UTF-8"));
	    os.close(); 
	    // read the response
	    InputStream in = new BufferedInputStream(conn.getInputStream());
	    String result = IOUtils.toString(in, "UTF-8");
	    JSONObject myResponse = new JSONObject(result);
	    code = myResponse.getInt("Code");
	    //////////////////////////////////////////////////////////////////////////////
	    System.out.println(code);
	    } 
	    catch (Exception e) {
			System.out.println(e);
	    }
			
		}
	}

	public static void main(String[] args) {
		AddSubscriber("basma", "basma@basmalino.com");	
}
}

