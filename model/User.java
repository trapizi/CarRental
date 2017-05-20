package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.InputValidator;
import util.InvalidInputException;

/**
 * User is abstract as we never instantiate this class
 */
public abstract class User {	
	private StringProperty userName;
	private StringProperty password;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private IntegerProperty phoneNo;
	
	public User() {		
		this.userName = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.phoneNo = new SimpleIntegerProperty();	
	}
	
	public User(String userName, String password, String firstName, String lastName, String email, Integer phoneNo) {		
		this.userName.set(userName);
		this.password.set(password);
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.email.set(email);
		this.phoneNo.set(phoneNo);
	}
	
	public static void validateInput(String userName, String password, String firstName, String lastName, String email, String phoneNoText,
			String tableName, int ID) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		
		// validate information entered
		// do bare minimum checks to ensure that database won't crash upon inserting data
    	try {
    		InputValidator.validateUsername(userName, tableName, ID);
    		InputValidator.validatePassword(password);
    		InputValidator.validateFirstName(firstName);
    		InputValidator.validateLastName(lastName);
    		InputValidator.validateEmail(email);
    		InputValidator.validatePhoneNo(phoneNoText);
    	
    	// Throw exceptions back up to controller to handle
    	} catch (Exception e) {
    		throw e;
    	}
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
	
	public int getPhoneNo() {
		return phoneNo.get();
	}
	
	public void setPhoneNo(int phoneNo) {
		this.phoneNo.set(phoneNo);
	}
	
	public IntegerProperty phoneNoProperty() {
		return phoneNo;
	}
}