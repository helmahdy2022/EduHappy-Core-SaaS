package mynaparrot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpEntityContainer;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.*;
import org.apache.hc.client5.http.entity.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MynaParrotApi {

    
    @SuppressWarnings("unchecked")
	public static String createMeeting(String meeting_id, String attendeeName, String meetingName, String teacherPass, String studentPass, String jointType) throws org.apache.hc.core5.http.ParseException {
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.mynaparrot.com/class/joinClass");
        String randomKey = String.format("%d", System.currentTimeMillis());
        String url = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("clientId", "helmahdy"));
        params.add(new BasicNameValuePair("meetingId", meeting_id));
        params.add(new BasicNameValuePair("attendeeName", attendeeName));
        params.add(new BasicNameValuePair("meetingName", meetingName));
        params.add(new BasicNameValuePair("teacherPass", teacherPass));
        params.add(new BasicNameValuePair("studentPass", studentPass));
        params.add(new BasicNameValuePair("capacity", "5"));
        params.add(new BasicNameValuePair("joinType", jointType)); // or student
        params.add(new BasicNameValuePair("randomKey", randomKey));
        //params.add(new BasicNameValuePair("logOut", "https://api.mynaparrot.com"));
        //params.add(new BasicNameValuePair("duration", "60")); // duration in minutes; 1 hour = 60 minutes
        //params.add(new BasicNameValuePair("welcomePdf", "https://api.mynaparrot.com/class/files/mynadefault.pdf"));
        
        // extra 
        JSONObject extra = new JSONObject();
        extra.put("allowRecording", false); 
        extra.put("allowModsToUnmuteUsers", true); 
        
        params.add(new BasicNameValuePair("extra", extra.toJSONString()));
        
        Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {  
            public int compare(NameValuePair p1, NameValuePair p2) {
              return p1.getName().compareTo(p2.getName());
            }
        };
        
        Collections.sort(params, comp);
                        
        params.add(new BasicNameValuePair("checkSum", addSign(params, "jYgcHXQeVLOZyaLKXZyW"))); //The API Key 
        
        System.out.println(params);

        UrlEncodedFormEntity entityParams = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
        
        httppost.setEntity(entityParams);
        
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = ((HttpEntityContainer) response).getEntity();
            
            
            String output = EntityUtils.toString(entity);
            System.out.println(output);
            
            try {
                Object obj = new JSONParser().parse(output);
                JSONObject jo = (JSONObject) obj; 
                //System.out.println(jo.get("msg"));
                //System.out.println(jo.get("url"));
                url = (String) jo.get("url");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      return url;
    }
    
    public static void getRecordings() throws org.apache.hc.core5.http.ParseException {
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.mynaparrot.com/class/getRecordings");
        String randomKey = String.format("%d", System.currentTimeMillis());
        
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("clientId", "demoapp"));
        params.add(new BasicNameValuePair("meetingId", "demoapp_test1"));
        params.add(new BasicNameValuePair("randomKey", randomKey));
        
        Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {  
            public int compare(NameValuePair p1, NameValuePair p2) {
              return p1.getName().compareTo(p2.getName());
            }
        };
        
        Collections.sort(params, comp);
                        
        params.add(new BasicNameValuePair("checkSum", addSign(params, "My API Key")));
        
        System.out.println(params);

        UrlEncodedFormEntity entityParams = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
        
        httppost.setEntity(entityParams);
        
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = ((BasicClassicHttpRequest) response).getEntity();
            
            String output = EntityUtils.toString(entity);
            System.out.println(output);
            
            try {
                Object obj = new JSONParser().parse(output);
                JSONObject jo = (JSONObject) obj; 
                System.out.println(jo);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static String addSign(List<NameValuePair> data, String api_key) {
        
        String sig = "";
        
        for (NameValuePair pair : data) {
            sig += pair.getName() + "=" + pair.getValue() + "&";
        }
        
        sig = sig.substring(0, sig.length() - 1) + api_key;
                        
        sig = DigestUtils.md5Hex(sig);

        return sig;
    }
}