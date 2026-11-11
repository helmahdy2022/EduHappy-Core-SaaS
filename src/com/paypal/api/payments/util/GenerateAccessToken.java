package com.paypal.api.payments.util;

import java.util.Map;
import java.util.Properties;

import com.paypal.base.ConfigManager;
import com.paypal.base.Constants;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class GenerateAccessToken { 

	@SuppressWarnings("deprecation")
	public static String getAccessToken() throws PayPalRESTException {

		// ###AccessToken
		
		Properties properties = new Properties();
	    
		//Sandbox
		//properties.setProperty("service.EndPoint", "https://api.sandbox.paypal.com");
		//Live
		properties.setProperty("service.EndPoint", "https://api.paypal.com");
	    ConfigManager.getInstance().load(properties);
		
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		
		//String clientID = ConfigManager.getInstance().getValue(Constants.CLIENT_ID);
		//String clientSecret = ConfigManager.getInstance().getValue(Constants.CLIENT_SECRET);
		
		//Sandbox
		//String clientID = "AVmXxkqp0MfXeXRFmKzcwSJkBA9VfupuBNcL4KuvJdtnIo3ClxKBg08wLMMtFdJHDYt_Svmzmeq2uLb0";
		//String clientSecret = "EExyBE3GfPG4RXxr2x3bevWZfVClLXpsRmQindSSQMzkgASZGYjqlPZr6gwzFhwMD8imMjT9XtoFwAuH";
	    //String clientID = "ARVoz0x7YcJSbpCE44sDDgb-1XMmcKqqUGuPwvy-Z1qasF0PMtM6J292b5JYlqJYdSKYsRaBaaBBd5Md";
	    //String clientSecret = "EPRc1W4OVOuCN0Y0BKB_2rFVq0-pgGbY_clxvm9Sw3PCBCgvwsYNvf45cVONwUiW7IzPCRN6oT9vM7Ru";
	    
	    //Live
	    String clientID = "ASFjvnAIx67nvkifkNd4Ad22jD0DIbSosXDiF9do_97NfDA45nBRmK-fcahOiklNB3xURKaue08SQTM1";
		String clientSecret = "EJMejrQX_waEAzygoC-nHQZPlRInFg0SR0bMPE2Q-nrMTzgs8HlsQqxc18QY8Y-FviKKXgefrYL5vyXj";
		String s = new OAuthTokenCredential(clientID, clientSecret).getAccessToken();
		return s;
	}
	
	public static String getAccessToken(Map<String, String> headers) throws PayPalRESTException {

		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		@SuppressWarnings("deprecation")
		String clientID = ConfigManager.getInstance().getValue(Constants.CLIENT_ID);
		@SuppressWarnings("deprecation")
		String clientSecret = ConfigManager.getInstance().getValue(Constants.CLIENT_SECRET);

		return new OAuthTokenCredential(clientID, clientSecret).setHeaders(headers).getAccessToken();
	}
}
