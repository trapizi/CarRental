package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AgreementPayment extends Payment{

    //AGREEMENT_PAYMENT table columns
    private IntegerProperty agreementPayment_id;
    
    //AGREEMENT_PAYMENT default constructor
    public AgreementPayment() {
        this.agreementPayment_id = new SimpleIntegerProperty();
    }

    //return string containing agreementPaymentID + other payment details
    public String toString(){
        return "AgreementPayment_id: " + this.getAgreementPayment_id() + " | " + super.toString();
    }
    
    //agreementPaymentID
    public Integer getAgreementPayment_id() {
	return agreementPayment_id.get();
    }

    public void setAgreementPayment_id(Integer agreementPayment_id) {
	this.agreementPayment_id.set(agreementPayment_id);
    }
    
    public IntegerProperty agreementPayment_idProperty(){
        return agreementPayment_id;
    }

}
