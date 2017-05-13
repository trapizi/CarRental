package model;

public class Corporate {
	protected int corporateID;
    protected String companyName, companyAddr;
    private long companyPhoneNumber, companyPCode;
    private int customerType;
    
	public int getCorporateID() {
		return corporateID;
	}
	
	public void setCorporateID(int corporateID) {
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
	
	public long getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}
	
	public void setCompanyPhoneNumber(long companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}
	
	public long getCompanyPCode() {
		return companyPCode;
	}
	
	public void setCompanyPCode(long companyPCode) {
		this.companyPCode = companyPCode;
	}
	
	public int getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	} 
}