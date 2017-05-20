package model;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MembershipPayment extends Payment {
    
    //MEMBERSHIP_PAYMENT table columns
    private IntegerProperty membershipPayment_id;
    private BooleanProperty status;
    private SimpleObjectProperty<Date> lastMatchDate;
    private SimpleObjectProperty<Date> nextExpiry;
    private IntegerProperty durationToExpiry;
    private BooleanProperty refundFlag;
    	
    //return string containing membershipPaymentID + other payment details
    public String toString(){
        return "MembershipPayment_id: " + this.getMembershipPayment_id() + " | " + super.toString();
    }
    
    //MEMBERSHIP_PAYMENT default constructor
    public MembershipPayment(){
        super();
        this.membershipPayment_id = new SimpleIntegerProperty();
        this.status = new SimpleBooleanProperty();
        this.lastMatchDate = new SimpleObjectProperty<Date>();
        this.nextExpiry = new SimpleObjectProperty<Date>();
        this.durationToExpiry = new SimpleIntegerProperty();
        this.refundFlag = new SimpleBooleanProperty();
    }
    
    //membershipPaymentID
    public int getMembershipPayment_id(){
        return membershipPayment_id.get();
    }
    
    public void setMembershipPayment_id(int membershipPayment_id){
        this.membershipPayment_id.set(membershipPayment_id);
    }
    
    public IntegerProperty membershipPayment_idProperty(){
        return membershipPayment_id;
    }
    
    //status
    public boolean getStatus(){
        return status.get();
    }
    
    public void setStatus(boolean status){
        this.status.set(status);
    }
    
    public BooleanProperty status(){
        return status;
    }
    
    //lastMatchDate
    public Date getLastMatchDate(){
        return lastMatchDate.get();
    }
    
    public void setLastMatchDate(Date lastMatchDate){
        this.lastMatchDate.set(lastMatchDate);
    }
    
    public SimpleObjectProperty<Date> lastMatchDate(){
        return lastMatchDate;
    }
    
    //nextExpiry
    public Date getNextExpiry(){
        return nextExpiry.get();
    }
    
    public void setNextExpiry(Date nextExpiry){
        this.nextExpiry.set(nextExpiry);
    }
    
    public SimpleObjectProperty<Date> nextExpiry(){
        return nextExpiry;
    }
    
    //durationToExpiry
    public int getDurationToExpiry(){
        return durationToExpiry.get();
    }
    
    public void setDurationToExpiry(int durationToExpiry){
        this.durationToExpiry.set(durationToExpiry);
    }
    
    public IntegerProperty durationToExpiry(){
        return durationToExpiry;
    }
    
    //refundFlag
    public boolean getRefundFlag(){
        return refundFlag.get();
    }
    
    public void setRefundFlag(boolean refundFlag){
        this.refundFlag.set(refundFlag);
    }
    
    public BooleanProperty refundFlag(){
        return refundFlag;
    }
}