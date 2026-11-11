/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.paypal.api.payments.ipn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dbHelpers.DbConnector;


/**
 * Arbitrary service class to simulate the storage and retrieval of Paypal IPN Notification related information
 *
 * User: smhumayun
 * Date: 7/6/13
 * Time: 6:23 PM
 */
public class IpnInfoService {

    /**
     * Store Paypal IPN Notification related information for future use
     *
     * @param ipnInfo {@link IpnInfo}
     * @throws IpnException
     */
    public void log (final IpnInfo ipnInfo) throws IpnException {
        /**
         * Implementation...
         */
    }

    /**
     * Fetch Paypal IPN Notification related information saved earlier
     *
     * @param txnId Paypal IPN Notification's Transaction ID
     * @return {@link IpnInfo}
     * @throws IpnException
     */
    public IpnInfo getIpnInfo (final String txnId) throws IpnException {
        IpnInfo ipnInfo = new IpnInfo();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementtxnID = connection.createStatement();){
			String stxnID = "select * from Account_Payment where txn_id='"+txnId+"'";
			ResultSet resulttxnID = statementtxnID.executeQuery(stxnID);
			while (resulttxnID.next())
			{
				ipnInfo.setTxnId(resulttxnID.getString("txn_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ipnInfo;
    }

}
