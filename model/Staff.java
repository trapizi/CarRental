package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import util.InvalidInputException;

/**
 * @author Bing Wen (z3463269)
 */
public class Staff extends User {	
	private IntegerProperty staff_id;
	
	public Staff() {
		super();
		this.staff_id = new SimpleIntegerProperty();
	}
	
	public Staff(String userName, String password, String firstName, String lastName, String email, Integer phoneNo) {
		// call user constructor
    	super(userName, password, firstName, lastName, email, phoneNo);
    	this.staff_id = new SimpleIntegerProperty();
	}
	
	public static void validateInput(String userName, String password, String firstName, String lastName, String email, 
			String phoneNoText, int ID) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		
		try {
			// TODO: if this isn't a superclass, follow how to validate input in User.java
			User.validateInput(userName, password, firstName, lastName, email, phoneNoText, "STAFF", ID);
		} catch (Exception e) {
			throw e;
		}
	}
		
	public String toString() {
		return "id: " + this.getStaff_id() + " first_name: " + this.getFirstName() + " last_name: " + this.getLastName() + " username: " + this.getUserName();
	}
	
	public int getStaff_id() {
		return staff_id.get();
	}

	public void setStaff_id(int staff_id) {
		this.staff_id.set(staff_id);
	}
	
	public IntegerProperty staff_idProperty() {
		return staff_id;
	}
}