/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.paypal.api.payments.ipn;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Paypal IPN Notification Handler Class
 *
 */
public class IpnHandler
{

    private Logger logger;
    private IpnConfig ipnConfig;
    private IpnInfoService ipnInfoService;
    /**
     * This method handles the Paypal IPN Notification as follows:
     *      1. Read all posted request parameters
     *      2. Prepare 'notify-validate' command with exactly the same parameters
     *      3. Post above command to Paypal IPN URL {@link IpnConfig#ipnUrl}
     *      4. Read response from Paypal
     *      5. Capture Paypal IPN information
     *      6. Validate captured Paypal IPN Information
     *          6.1. Check that paymentStatus=Completed
     *          6.2. Check that txnId has not been previously processed
     *          6.3. Check that receiverEmail matches with configured {@link IpnConfig#receiverEmail}
     *          6.4. Check that paymentAmount matches with configured {@link IpnConfig#paymentAmount}
     *          6.5. Check that paymentCurrency matches with configured {@link IpnConfig#paymentCurrency}
     *      7. In case of any failed validation checks, throw {@link IpnException}
     *      8. If all is well, return {@link IpnInfo} to the caller for further business logic execution
     *
     * @param request {@link HttpServletRequest}
     * @return {@link IpnInfo}
     * @throws IpnException
     */
    public IpnInfo handleIpn (HttpServletRequest request) throws IpnException {
    	
    	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    	logger.info("inside ipn");
        
    	IpnInfoService ipnInfoServiceTxnID = new IpnInfoService();
    	IpnInfo ipnInfo = new IpnInfo();
        try{
        	
        	//1. Read all posted request parameters
            String requestParams = this.getAllRequestParams(request);
            //logger.info(requestParams);

            //2. Prepare 'notify-validate' command with exactly the same parameters
            Enumeration en = request.getParameterNames();
            StringBuilder cmd = new StringBuilder("cmd=_notify-validate");
            String paramName;
            String paramValue;
            //String charset = "windows-1252";//request.getParameter("charset");
            //String charset = "UTF-8";
            String charset = request.getParameter("charset");
            while (en.hasMoreElements()) {
                paramName = (String) en.nextElement();
                paramValue = request.getParameter(paramName);
                
                cmd.append("&").append(paramName).append("=").append(URLEncoder.encode(paramValue, charset));
                //cmd.append("&").append(paramName).append("=").append(paramValue);
            }

            //3. Post above command to Paypal IPN URL {@link IpnConfig#ipnUrl}
            URL u = new URL("https://www.paypal.com/cgi-bin/webscr");
            //URL u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
            HttpsURLConnection uc = (HttpsURLConnection) u.openConnection();
            uc.setDoOutput(true);
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            uc.setRequestProperty("Host", "www.paypal.com");
            //uc.setRequestProperty("Host", "www.sandbox.paypal.com");
            //uc.setRequestMethod("POST");
            PrintWriter pw = new PrintWriter(uc.getOutputStream());
            pw.println(cmd.toString());
            pw.close();

            //4. Read response from Paypal
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = in.readLine();
            in.close();
 
            //5. Capture Paypal IPN information
            ipnInfo.setLogTime(System.currentTimeMillis());
            if(request.getParameter("initial_payment_txn_id")!=null || (request.getParameter("txn_type").equalsIgnoreCase("recurring_payment_profile_created")&&request.getParameter("initial_payment_amount").equals("0.00")))
            {
            	ipnInfo.setTxnId(request.getParameter("initial_payment_txn_id"));
            	ipnInfo.setPaymentType("Initial");
            }
            else
            {
            	ipnInfo.setTxnId(request.getParameter("txn_id"));
            	ipnInfo.setPaymentType("Recurring");
            }
            
            if(request.getParameter("initial_payment_status")!=null)
            {
            	ipnInfo.setPaymentStatus(request.getParameter("initial_payment_status"));
            }
            else
            {
            	ipnInfo.setPaymentStatus(request.getParameter("payment_status"));
            }  
            ipnInfo.setItemName(request.getParameter("product_name"));
            ipnInfo.setAmount_per_cycle(request.getParameter("amount_per_cycle"));
            if(request.getParameter("initial_payment_amount")!=null)
            {
            	ipnInfo.setPaymentAmount(request.getParameter("initial_payment_amount"));
            }
            else
            {
            	ipnInfo.setPaymentAmount(request.getParameter("mc_gross"));
            }
            
            ipnInfo.setPaymentCurrency(request.getParameter("currency_code"));
           
            if(request.getParameter("payment_date")!=null)
            {
            	ipnInfo.setPaymentDate(request.getParameter("payment_date"));	
            }
            {
            	ipnInfo.setPaymentDate(request.getParameter("time_created"));
            }
            ipnInfo.setNextPaymentDate(request.getParameter("next_payment_date"));
            ipnInfo.setPaymentCycle(request.getParameter("payment_cycle"));
            ipnInfo.setRecurringPaymentId(request.getParameter("recurring_payment_id"));
            ipnInfo.setPayerId(request.getParameter("payer_id"));
            if(request.getParameter("profile_status")!=null && request.getParameter("profile_status").equalsIgnoreCase("Cancelled"))
            {
            	ipnInfo.setProfile_status(request.getParameter("profile_status"));
            }
            ipnInfo.setTxn_type(request.getParameter("txn_type")); 
            ipnInfo.setResponse(res);
            ipnInfo.setRequestParams(requestParams);
            
            ///////////////////////////////////////////////////
            //6. Validate captured Paypal IPN Information
            if (res.equals("VERIFIED")) {

                //6.1. Check that paymentStatus=Completed
                if(!(request.getParameter("txn_type").equalsIgnoreCase("recurring_payment_profile_created")&&ipnInfo.getPaymentAmount().equals("0.00")))
                {
                	if((ipnInfo.getPaymentStatus() == null || !ipnInfo.getPaymentStatus().equalsIgnoreCase("COMPLETED")) && !ipnInfo.getProfile_status().equalsIgnoreCase("Cancelled"))
                        ipnInfo.setError("payment_status IS NOT COMPLETED {" + ipnInfo.getPaymentStatus() + "}");
                }
                //6.2. Check that txnId has not been previously processed
                IpnInfo oldIpnInfo = ipnInfoServiceTxnID.getIpnInfo(ipnInfo.getTxnId());
                if(oldIpnInfo.getTxnId() != null)
                    ipnInfo.setError("txn_id is already processed {old ipn_info " + oldIpnInfo);
            }
            else
                ipnInfo.setError("Inavlid response {" + res + "} expecting {VERIFIED}");

            logger.info("ipnInfo = " + ipnInfo);

            this.getIpnInfoService().log(ipnInfo);

            //7. In case of any failed validation checks, throw {@link IpnException}
            if(ipnInfo.getError() != null)
                throw new IpnException(ipnInfo.getError());
        }
        catch(Exception e)
        {
/*			int account_id = 0;
        	String sAccountID = "Select * from Account_Agreement where agreement_id='"+request.getParameter("recurring_payment_id")+"'";     	
			try (Connection connection = DbConnector.connectToDb();
					
					Statement statementAccountID = connection.createStatement();
					Statement statementPreviousAgreementState = connection.createStatement();){
				ResultSet resultAccountID = statementAccountID.executeQuery(sAccountID);
				while(resultAccountID.next())
				{
					account_id = resultAccountID.getInt("Account_id");
				}
				String sDeletePendingState = "Delete from Account_Agreement where account_id="+account_id+" and state='Pending'";
				PreparedStatement pstatementDeletePendingState  = connection.prepareStatement(sDeletePendingState);
				pstatementDeletePendingState.executeUpdate();	
				pstatementDeletePendingState.close();
				/////If update - Get previous Agreement_id////////////

				String sPreviousAgreementState = "select * from Account_Payment where agreement_id = (select Agreement_id from Account_Agreement where account_id = "+account_id+" and state='Updating')";	    		
	    		ResultSet resultPreviousAgreementState = statementPreviousAgreementState.executeQuery(sPreviousAgreementState);
	    		while(resultPreviousAgreementState.next())
	    		{
	    			String previous_Agreement_id = resultPreviousAgreementState.getString("Agreement_id");
	    			String state = resultPreviousAgreementState.getString("State");
	    			String sUpdatePreviouseState = "update Account_Agreement set state = '"+state+"' where agreement_id='"+previous_Agreement_id+"'";
	    			PreparedStatement pstatementUpdatePreviouseState  = connection.prepareStatement(sUpdatePreviouseState);
	    			pstatementUpdatePreviouseState.executeUpdate();
	    			pstatementUpdatePreviouseState.close();
	    		}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/	
        	if(e instanceof IpnException)
                throw (IpnException) e;
            logger.log(Level.SEVERE, e.toString(), e);
           throw new IpnException(e.toString());
        }

        //8. If all is well, return {@link IpnInfo} to the caller for further business logic execution
        return ipnInfo;
    }

    /**
     * Utility method to extract all request parameters and their values from request object
     *
     * @param request {@link HttpServletRequest}
     * @return all request parameters in the form:
     *                                              param-name 1
     *                                                  param-value
     *                                              param-name 2
     *                                                  param-value
     *                                                  param-value (in case of multiple values)
     */
    private String getAllRequestParams(HttpServletRequest request)
    {
        Map map = request.getParameterMap();
        StringBuilder sb = new StringBuilder("\nREQUEST PARAMETERS\n");
        for (Iterator it = map.keySet().iterator(); it.hasNext();)
        {
            String pn = (String)it.next();
            sb.append(pn).append("\n");
            String[] pvs = (String[]) map.get(pn);
            for (int i = 0; i < pvs.length; i++) {
                String pv = pvs[i];
                sb.append("\t").append(pv).append("\n");
            }
        }
        return sb.toString();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public IpnConfig getIpnConfig() {
        return ipnConfig;
    }

    public void setIpnConfig(IpnConfig ipnConfig) {
        this.ipnConfig = ipnConfig;
    }

    public IpnInfoService getIpnInfoService() {
    	ipnInfoService = new IpnInfoService();
    	return ipnInfoService;
    }

    public void setIpnInfoService(IpnInfoService ipnInfoService) {
        this.ipnInfoService = ipnInfoService;
    }
}
