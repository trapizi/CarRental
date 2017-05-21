package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Corporate {	
	public IntegerProperty corporateID;
    private StringProperty companyName, companyAddr;
    private LongProperty companyPhoneNumber, companyPCode;
    private IntegerProperty customerType;
     
    public Corporate() {
    	this.corporateID = new SimpleIntegerProperty();
        this.companyName = new SimpleStringProperty();
        this.companyAddr = new SimpleStringProperty();
        this.companyPhoneNumber = new SimpleLongProperty();
        this.companyPCode = new SimpleLongProperty();
        this.customerType = new SimpleIntegerProperty();
    }
    
	public int getCorporateID() {
		return corporateID.get();
	}
	
	public void setCorporateID(int corporateID) {
		this.corporateID.set(corporateID);
	}
	
	public IntegerProperty corporateIDProperty() {
		return corporateID;
	}
	
	public String getCompanyName() {
		return companyName.get();
	}
	
	public void setCompanyName(String companyName) {
		this.companyName.set(companyName);
	}
	
	public StringProperty companyNameProperty() {
		return companyName;
	}
	
	public String getCompanyAddr() {
		return companyAddr.get();
	}
	
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr.set(companyAddr);
	}
	
	public StringProperty companyAddrProperty() {
		return companyAddr;
	}
	
	public long getCompanyPhoneNumber() {
		return companyPhoneNumber.get();
	}
	
	public void setCompanyPhoneNumber(long companyPhoneNumber) {
		this.companyPhoneNumber.set(companyPhoneNumber);
	}
	
	public LongProperty companyPhoneNumberProperty() {
		return companyPhoneNumber;
	}
	
	public long getCompanyPCode() {
		return companyPCode.get();
	}
	
	public void setCompanyPCode(long companyPCode) {
		this.companyPCode.set(companyPCode);
	}
	
	public LongProperty companyPCodeProperty() {
		return companyPCode;
	}
	
	public int getCustomerType() {
		return customerType.get();
	}
	
	public void setCustomerType(int customerType) {
		this.customerType.set(customerType);
	}
	
	public IntegerProperty customerTypeProperty() {
		return customerType;
	}
}