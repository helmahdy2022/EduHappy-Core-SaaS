/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.paypal.api.payments.ipn;

/**
 * Model class to hold Paypal IPN Notification related information
 *
 * User: smhumayun
 * Date: 7/6/13
 * Time: 6:15 PM
 */
public class IpnInfo {

    private String itemName;
    private String itemNumber;
    private String paymentStatus;
    private String paymentAmount;
    private String paymentCurrency;
    private String txnId;
    private String receiverEmail;
    private String payerEmail;
    private String response;
    private String requestParams;
    private String error;
    private Long logTime;
    private String nextPaymentDate;
    private String paymentCycle;
    private String paymentDate;
    private String recurringPaymentId;
    private String payerId;
    private String profile_status;
    private String paymentType;
    private String amount_per_cycle;
    private String txn_type;
 
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Long getLogTime() {
        return logTime;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    /**
	 * @return the nextPaymentDate
	 */
	public String getNextPaymentDate() {
		return nextPaymentDate;
	}

	/**
	 * @param nextPaymentDate the nextPaymentDate to set
	 */
	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	
	
	/**
	 * @return the paymentCycle
	 */
	public String getPaymentCycle() {
		return paymentCycle;
	}

	/**
	 * @param paymentCycle the paymentCycle to set
	 */
	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	
	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	

	/**
	 * @return the recurringPaymentId
	 */
	public String getRecurringPaymentId() {
		return recurringPaymentId;
	}

	/**
	 * @param recurringPaymentId the recurringPaymentId to set
	 */
	public void setRecurringPaymentId(String recurringPaymentId) {
		this.recurringPaymentId = recurringPaymentId;
	}
	  
	/**
	 * @return the payerId
	 */
	public String getPayerId() {
		return payerId;
	}

	/**
	 * @param payerId the payerId to set
	 */
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}


	/**
	 * @return the profile_status
	 */
	public String getProfile_status() {
		return profile_status;
	}

	/**
	 * @param profile_status the profile_status to set
	 */
	public void setProfile_status(String profile_status) {
		this.profile_status = profile_status;
	}
	
	/**
	 * @return the amount_per_cycle
	 */
	public String getAmount_per_cycle() {
		return amount_per_cycle;
	}

	/**
	 * @param amount_per_cycle the amount_per_cycle to set
	 */
	public void setAmount_per_cycle(String amount_per_cycle) {
		this.amount_per_cycle = amount_per_cycle;
	}

	/**
	 * @return the txn_type
	 */
	public String getTxn_type() {
		return txn_type;
	}

	/**
	 * @param txn_type the txn_type to set
	 */
	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	
    @Override
    public String toString() {
        return "txn_id = " + this.getTxnId()
                + ", response = " + this.getResponse()
                + ", payment_status = " + this.getPaymentStatus()
                + ", payer_email = " + this.getPayerEmail()
                + ", item_name = " + this.getItemName()
                + ", item_number = " + this.getItemNumber()
                + ", payment_amount = " + this.getPaymentAmount()
                + ", payment_currency = " + this.getPaymentCurrency()
                + ", receiver_email = " + this.getReceiverEmail()
                + ", request_params = " + this.getRequestParams()
                + ", log_time = " + this.getLogTime()
                + ", Next_Payment_Date = " + this.getNextPaymentDate()
                + ", Payment_Cycle = " + this.getPaymentCycle()
                + ", Payment_Date = " + this.getPaymentDate()
                + ", Recurring_Payment_id = " + this.getRecurringPaymentId()
                + ", Payer_id = " + this.getPayerId()
                + ", Profile_Status = " + this.getProfile_status()
                + ", Amount_per_cycle = " + this.getAmount_per_cycle()
                + ", Txn_Type = " + this.getTxn_type()
                + ", error = " + this.getError();
    }

}