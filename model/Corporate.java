package model;

public class Corporate {
    protected String corporateID, companyName, companyAddr;
    private long companyPhoneNumber, companyPCode;
    private int customerType;
   
    public String getCorporateId() {
        return corporateID;
    }
    
    public void setCorporateId(String corporateID) {
        this.corporateID = corporateID; 
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getCompanyAddr() {
        return companyAddr;
    }
    
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
    
    public long getCompPhoneNumber() {
        return companyPhoneNumber;
    }
    
    public void setCompPhoneNumber(long compPhoneNumber) {
        this.companyPhoneNumber = compPhoneNumber;
    }
    
    public long getCompanyPCode() {
        return companyPCode;
    }
    
    public void setCompanyPCode(long companyPCode) {
        this.companyPCode = companyPCode;
    }
    
    public int getCustomerType()  {
        return customerType;
    }
}