package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AgreementPayment extends Payment{

    //AGREEMENT table columns
    private StringProperty agreementPayment_id;
    
    //AGREEMENT constructor
    public AgreementPayment() {
        this.agreementPayment_id = new SimpleStringProperty();
    }

    //return string containing agreementPaymentID + other payment details
    public String toString(){
        return "AgreementPayment_id: " + this.getAgreementPayment_id() + " | " + super.toString();
    }
    
    //agreementPaymentID
    public String getAgreementPayment_id() {
	return agreementPayment_id.get();
    }

    public void setAgreementPayment_id(String agreementPayment_id) {
	this.agreementPayment_id.set(agreementPayment_id);
    }
    
    public StringProperty agreementPayment_idProperty(){
        return agreementPayment_id;
    }

}
