package model;

public class Payment {
    
    //PAYMENT table columns
    private String paymentID;
    private double amount;
    private String date;
    private String paymentAccount;
    private String paymentType;
    private String accountExpiry;
    private String accountOwnerName;
    private String paymentMedia;

    public String getPaymentID(){
        return paymentID;
    }
    
    public void setPaymentID(String PaymentID){
        this.paymentID = paymentID;
    }

    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }      
    
    public String getPaymentAccount(){
        return paymentAccount;
    }
    
    public void setPaymentAccount(String paymentAccount){
        this.paymentAccount = paymentAccount;
    }
    
    public String getPaymentType(){
        return paymentType;
    }
    
    public void setPaymentType(String paymentType){
        this.paymentType = paymentType;
    }
    
    public String getAccountExpiry(){
        return accountExpiry;
    }
    
    public void setAccountExpiry(String accountExpiry){
        this.accountExpiry = accountExpiry;
    }
    
    public String getAccountOwnerName(){
        return accountOwnerName;
    }
    
    public void setAccountOwnerName(String accountOwnerName){
        this.accountOwnerName = accountOwnerName;
    }
    
    public String getPaymentMedia(){
        return paymentMedia;
    }
    
    public void setPaymentMedia(String paymentMedia){
        this.paymentMedia = paymentMedia;
    }
}
