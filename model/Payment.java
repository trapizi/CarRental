package model;

public class Payment {
    
    //PAYMENT table columns
    private double amount;
    private String date;
    private String id;
    private String paymentAccount;
    private String paymentType;
    private String accountExpiry;
    private String accountOwnerName;
    private String paymentMedia;

    //PAYMENT constructor
    /*public Payment() {
        this.amount = new amount();
        this.first_name = new SimpleStringProperty();
        this.last_name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone_number = new SimpleStringProperty();
        this.hire_date = new SimpleObjectProperty<>();
        this.job_id = new SimpleStringProperty();
        this.salary = new SimpleIntegerProperty();
        this.commission_pct = new SimpleDoubleProperty();
        this.manager_id = new SimpleIntegerProperty();
        this.department_id = new SimpleIntegerProperty();
    } */
    
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
        
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
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
