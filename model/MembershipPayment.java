package model;

import javafx.beans.property.IntegerProperty;

public class MembershipPayment extends Payment {
    
    //MEMBERSHIP PAYMENT table columns
    private IntegerProperty membershipPaymentID;
    private String status;
    private String lastMatch;
    private String nextExpiry;
    private int durationToExpiry;
    private int refundFlag;
    	
    
    public IntegerProperty getMembershipPaymentID() {
	return membershipPaymentID;
    }

    public void setMembershipPaymentID(IntegerProperty membershipPaymentID) {
	this.membershipPaymentID = membershipPaymentID;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getLastMatch(){
        return lastMatch;
    }
    
    public void setLastMatch(String lastMatch){
        this.lastMatch = lastMatch;
    }
        
    public String getNextExpiry(){
        return nextExpiry;
    }
    
    public void setNextExpiry(String nextExpiry){
        this.nextExpiry = nextExpiry;
    }
    
    public int getDurationToExpiry(){
        return durationToExpiry;
    }
    
    public void setDurationToExpiry(int durationToExpiry){
        this.durationToExpiry = durationToExpiry;
    }
    
    public int getRefundFlag(){
        return refundFlag;
    }
    
    public void setRefundFlag(int refundFlag){
        this.refundFlag = refundFlag;
    }   
    
}
