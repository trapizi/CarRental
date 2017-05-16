package model;

import javafx.beans.property.IntegerProperty;

public class AgreementPayment extends Payment{
    	
    private IntegerProperty agreementPaymentID;

	public IntegerProperty getAgreementPaymentID() {
		return agreementPaymentID;
	}

	public void setAgreementPaymentID(IntegerProperty agreementPaymentID) {
		this.agreementPaymentID = agreementPaymentID;
	}
    
}
