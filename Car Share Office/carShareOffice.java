public class carShareOffice {
    private int userID, seekID, agreementID, adjustmentID, consultantID,
                coporateID, paymentID;
    private User currentAccountID;
            
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID){
        this.userID = userID; 
    }
    
    public int getSeekID(){
        return seekID;
    } 
    
    public void setSeekID(int seekID){
        this.seekID = seekID;
    }
    
    public int getAgreementID(){
        return agreementID;
    }
    
    public void setAgreementID (int agreementID){
        this.agreementID = agreementID;
    }
    
    public int getAdjustmentID(){
        return adjustmentID;
    }
    
    public void setAdjustmentID (int adjustmentID){
        this.adjustmentID = adjustmentID;
    }
    public int getConsultantID (){
        return consultantID;
    }
    
    public void setConsultantID (int consultantID){
        this.consultantID = consultantID;
    }
    
    public int getCoporateID(){
        return coporateID;
    }
    
    public void setCoporateID (int CoporateID){
        this.coporateID = coporateID;
    }
    
    public int getPaymentID(){
        return paymentID;
    }
    
    public void setPaymentID (int paymentID){
        this.paymentID = paymentID;
    }
    
    public User getCurrentAccountID(){
        return currentAccountID;
    }
    public void setCurrentAccountID(User currentAccountID){
        this.currentAccountID = currentAccountID;
    }   
}