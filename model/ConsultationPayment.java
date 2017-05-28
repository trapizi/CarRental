package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ConsultationPayment extends Payment {
    
    //CONSULTATION_PAYMENT table columns
    private IntegerProperty consultationPayment_id;
    private IntegerProperty consultationNum;
    
    //CONSULTATION_PAYMENT default constructor
    public ConsultationPayment() {
        this.consultationPayment_id = new SimpleIntegerProperty();
        this.consultationNum = new SimpleIntegerProperty();
    }
    
    //return string containing consultationPaymentID + other payment details
    public String toString(){
        return "ConsultationPayment_id: " + this.getConsultationPayment_id() + " | " + super.toString();
    }
    
    //agreementPaymentID
    public Integer getConsultationPayment_id() {
    	return consultationPayment_id.get();
    }

    public void setConsultationPayment_id(Integer consultationPayment_id) {
	this.consultationPayment_id.set(consultationPayment_id);
    }
    
    public IntegerProperty consultationPayment_idProperty(){
        return consultationPayment_id;
    }
    
    public Integer getConsultationNum() {
    	return consultationNum.get();
    }

    public void setConsultationNum(Integer consultationNum) {
    	this.consultationNum.set(consultationNum);
    }
    
    public IntegerProperty consultationNumProperty(){
        return consultationNum;
    }

}