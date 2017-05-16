package model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * User is abstract as we never instantiate this class
 */
public abstract class User {	
	private StringProperty userName;
	private StringProperty password;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private LongProperty phoneNo;
	private StringProperty homeAddress;
	
	public User() {
		this.userName = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.firstName= new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.phoneNo = new SimpleLongProperty();
		this.homeAddress = new SimpleStringProperty();
	}
	
	@Override
	public String toString() {
		return "Name: " + this.getFirstName() + " " + this.getLastName() + " | Username: " + this.getUserName();
	}
	
	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName.set(userName);
	}
	
	public StringProperty userNameProperty() {
		return userName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getPassword() {
		return password.get();
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public StringProperty passwordProperty() {
		return password;
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public long getPhoneNo() {
		return phoneNo.get();
	}
	
	public void setPhoneNo(long phoneNo) {
		this.phoneNo.set(phoneNo);
	}
	
	public LongProperty phoneNoProperty() {
		return phoneNo;
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
