package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.InputValidator;
import util.InvalidInputException;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Bing Wen (z3463269)
 */
public class Member extends User {		
	private IntegerProperty memberID;
	private ObjectProperty<Date> accountExpiry;
	private StringProperty homeAddress;
			
	/**
	 * Default constructor for member
	 */
	public Member() {
		super();
		this.memberID = new SimpleIntegerProperty();
		this.accountExpiry = new SimpleObjectProperty<Date>(); 
		this.homeAddress = new SimpleStringProperty();
	}
	
	public Member(String userName, String password, String firstName, String lastName, String email, String phoneNo, 
					String accountExpiry, String homeAddress) {		
		this.setUserName(userName);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhoneNo(Integer.parseInt(phoneNo));
		this.setAccountExpiry(accountExpiry);
		this.setHomeAddress(homeAddress);
	}
	
	/**
	 * @return a string containing the member's ID and user details
	 */
	@Override
	public String toString() {
		return "MemberID: " + this.getMemberID() + " | " + super.toString(); 
	}
	
	/**
	 * Validate member registration input
	 */
	public static void validateInput(String userName, String password, String firstName, String lastName, String email, String phoneNoText,
			String accountExpiryDate, String homeAddress, int ID) 
			throws InvalidInputException, SQLException, ClassNotFoundException {	
		
		try {	
			validateRegistrationInput(userName, password, firstName, lastName, email, phoneNoText, homeAddress, ID);

			InputValidator.validateDate(accountExpiryDate);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Validates member registration input
	 */
	public static void validateRegistrationInput(String userName, String password, String firstName, String lastName, String email, String phoneNoText,
			String homeAddress, int ID) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		
		try {
			User.validateInput(userName, password, firstName, lastName, email, phoneNoText, "MEMBER", ID);
			
			// TODO: validate accountExpiry, homeAddress and creditCard here
			InputValidator.validateHomeAddress(homeAddress);
			
		} catch (Exception e) {
			throw e;
		}
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
	
	public Date getAccountExpiry() {
		return accountExpiry.get();
	}
	
	public void setAccountExpiry(Date accountExpiry) {
		this.accountExpiry.set(accountExpiry);
	}
	
	public void setAccountExpiry(String date) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	java.util.Date temp = format.parse(date);
			this.setAccountExpiry(new Date(temp.getTime()));
	    } catch (ParseException e) {
	    	// exception should never be triggered as we validated it before
	    }
	}
	
	public ObjectProperty<Date> accountExpiryProperty() {
		return accountExpiry;
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