package model;

import java.sql.SQLException;
import java.sql.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.InputValidator;
import util.InvalidInputException;

//PAYMENT is abstract as we never instantiate this class
public abstract class Payment { 
    
    //PAYMENT table columns
    private IntegerProperty payment_id;
    private FloatProperty paymentAmount;
    private SimpleObjectProperty<Date> paymentDate;    
    private StringProperty paymentAccount;
    private StringProperty paymentType;
    private SimpleObjectProperty<Date> accountExpiry;
    private StringProperty accountOwnerName;
    private IntegerProperty memberID;

    //PAYMENT default constructor
    public Payment() {
        this.payment_id = new SimpleIntegerProperty();
        this.paymentAmount = new SimpleFloatProperty();
        this.paymentDate = new SimpleObjectProperty<Date>();  
        this.paymentAccount = new SimpleStringProperty();
        this.paymentType = new SimpleStringProperty();
        this.accountExpiry = new SimpleObjectProperty<Date>();
        this.accountOwnerName = new SimpleStringProperty();
        this.memberID = new SimpleIntegerProperty();
    }
    
    public Payment(int payment_id, float paymentAmount, Date paymentDate, String paymentAccount, String paymentType, Date accountExpiry, String accountOwnerName, String paymentMedia){
        this.payment_id.set(payment_id);
        this.paymentAmount.set(paymentAmount);
        this.paymentDate.set(paymentDate);
        this.paymentAccount.set(paymentAccount);
        this.paymentType.set(paymentType);
        this.accountExpiry.set(accountExpiry);
        this.accountOwnerName.set(accountOwnerName);
    }
    
    @Override
    public String toString(){
        return "Payment_id: " + this.getPayment_id() + " " + "paymentAmount: " + this.getPaymentAmount();
    }
    
	public static void validateInput(String fullNameTextField, String accountTextField, String accountExpiryTextField) 
			throws InvalidInputException, SQLException, ClassNotFoundException {	
		
		try {
			InputValidator.validateDate(accountExpiryTextField);
			InputValidator.validateName(fullNameTextField);
			InputValidator.validateAccount(accountTextField);
		} catch (Exception e) {
			throw e;
		}
	}
    
    //paymentID   
    public int getPayment_id(){
        return payment_id.get();
    }
    
    public void setPayment_id(int payment_id){
        this.payment_id.set(payment_id);
    }
    
    public IntegerProperty payment_idProperty(){
        return payment_id;
    }
    
    //paymentAmount
    public double getPaymentAmount(){
        return paymentAmount.get();
    }
    
    public void setPaymentAmount(float paymentAmount){
        this.paymentAmount.set(paymentAmount);
    }
    
    public FloatProperty paymentAmountProperty(){
        return paymentAmount; 
    }
    
    //paymentDate
    public Date getPaymentDate(){
        return paymentDate.get();
    }
    
    public void setPaymentDate(Date paymentDate){
        this.paymentDate.set(paymentDate);
    }
    
    public SimpleObjectProperty<Date> paymentDateProperty(){
        return paymentDate;
    }

    //paymentAccount  
    public String getPaymentAccount(){
        return paymentAccount.get();
    }
    
    public void setPaymentAccount(String paymentAccount){
        this.paymentAccount.set(paymentAccount);
    }
    
    public StringProperty paymentAccountProperty(){
        return paymentAccount;
    }
    
    //paymentType  
    public String getPaymentType(){
        return paymentType.get();
    }
    
    public void setPaymentType(String paymentType){
        this.paymentType.set(paymentType);
    }
    
    public StringProperty paymentTypeProperty(){
        return paymentType;
    }
    
    //accountExpiry  
    public Date getAccountExpiry(){
        return accountExpiry.get();
    }
    
    public void setAccountExpiry(Date accountExpiry){
        this.accountExpiry.set(accountExpiry);
    }
    
    public SimpleObjectProperty<Date> accountExpiryProperty(){
        return accountExpiry;
    }

    
    //accountOwnerName
    public String getAccountOwnerName(){
        return accountOwnerName.get();
    }
    
    public void setAccountOwnerName(String accountOwnerName){
        this.accountOwnerName.set(accountOwnerName);
    }
    
    public StringProperty accountOwnerNameProperty(){
        return accountOwnerName;
    }
    
    //paymentID   
    public int getMemberID(){
        return memberID.get();
    }
    
    public void setMemberID(int MemberID){
        this.memberID.set(MemberID);
    }
    
    public IntegerProperty MemberIDProperty(){
        return memberID;
    }
}
