package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MembershipPayment extends Payment {
    
    //MEMBERSHIP_PAYMENT table columns
    private IntegerProperty membershipPayment_id;
    private BooleanProperty refundFlag;
    	 
    //return string containing membershipPaymentID + other payment details
    public String toString(){
        return "MembershipPayment_id: " + this.getMembershipPayment_id() + " | " + super.toString();
    }
    
    //MEMBERSHIP_PAYMENT default constructor
    public MembershipPayment(){
        super();
        this.membershipPayment_id = new SimpleIntegerProperty();
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
            
    //refundFlag
    public boolean getRefundFlag(){
        return refundFlag.get();
    }
    
    public void setRefundFlag(boolean refundFlag){
        this.refundFlag.set(refundFlag);
    }
    
    public BooleanProperty refundFlagProperty(){
        return refundFlag;
    }
}