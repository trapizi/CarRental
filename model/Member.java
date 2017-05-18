package model;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member extends User {		
	private IntegerProperty memberID;
	private SimpleObjectProperty<Date> lastMatchDate;
	private SimpleObjectProperty<Date> accountExpiry;
	private FloatProperty commissionRate;
	private StringProperty creditCard;
	private StringProperty homeAddress;
		
	/*
	 * TODO: uncomment when other classes made
	 */
	
	//private ArrayList<CarOffer> offerList;
	//private ArrayList<CarSeek> seekList;
	//private ArrayList<Agreement> agreementList;
	//private CorporateMember corporateMem;
	
	@Override
	/**
	 * @return a string containing the member's ID and user details
	 */
	public String toString() {
		return "MemberID: " + this.getMemberID() + " | " + super.toString(); 
	}
		
	/**
	 * Default constructor for member
	 */
	public Member() {
		super();
		this.memberID = new SimpleIntegerProperty();
		this.lastMatchDate = new SimpleObjectProperty<Date>();
		this.accountExpiry = new SimpleObjectProperty<Date>(); 
		this.commissionRate = new SimpleFloatProperty(); 
		this.creditCard = new SimpleStringProperty(); 
		this.homeAddress = new SimpleStringProperty();
	}
	
	public int getMemberID() {
		return memberID.get();
	}

	public void setMemberID(int memberID) {
		this.memberID.set(memberID);
	}
	
	public IntegerProperty memberIDProperty() {
		return memberID;
	}

	public Object getLastMatchDate() {
		return lastMatchDate.get();
	}
	
	public void setLastMatchDate(Date lastMatchDate) {
		this.lastMatchDate.set(lastMatchDate);
	}
	
	public SimpleObjectProperty<Date> lastMatchDateProperty() {
		return lastMatchDate;
	}
	
	public Date getAccountExpiry() {
		return accountExpiry.get();
	}
	
	public void setAccountExpiry(Date accountExpiry) {
		this.accountExpiry.set(accountExpiry);
	}
	
	public SimpleObjectProperty<Date> accountExpiryProperty() {
		return accountExpiry;
	} 
	
	public float getCommissionRate() {
		return commissionRate.get();
	}
	
	public void setCommissionRate(float commissionRate) {
		this.commissionRate.set(commissionRate);
	}
	
	public FloatProperty commissionRateProperty() {
		return commissionRate;
	}
	
	public String getCreditCard() {
		return creditCard.get();
	}
	
	public void setCreditCard(String creditCard) {
		this.creditCard.set(creditCard);
	}
	
	public StringProperty creditCardProperty() {
		return creditCard;
	}
	
	public String getHomeAddress() {
		return homeAddress.get();
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress.set(homeAddress);
	}
	
	public StringProperty homeAddressProperty() {
		return homeAddress;
	}
}
