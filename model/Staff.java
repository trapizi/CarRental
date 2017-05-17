package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import util.DBUtil;
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
	
	public static void validateInput(String userName, String password, String firstName, String lastName, String email, String phoneNoText) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		
		// validate phone number entered
    	try {
    		Integer.parseInt(phoneNoText);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid phone entered. Ensure phone number only contains digits.");
    	}
    	
    	// validate username, ensure unique
    	try {
    		// remember to put userName in quotes to treat it as a VARCHAR value
    		ResultSet rs = DBUtil.dbExecuteQuery("SELECT COUNT(*) AS COUNT FROM STAFF WHERE USERNAME=" + "'"+userName+"'");

    		// count occurrences of the username in the staff table
    		Long userNameCount;
    		if (rs.next()) {
    			userNameCount = rs.getLong("COUNT");
    		} else {
    			userNameCount = 0L;
    		}
    		
    		// throw exception if username already in database
    		if (userNameCount > 0) {
    			throw new InvalidInputException("Invalid username entered. Username has already been taken.");
    		}
    	} catch (SQLException e) {
    		throw e;
    		//throw new SQLException("Failed to query database!");
    	} catch (ClassNotFoundException e) {
    		throw new ClassNotFoundException("Failed to connect to database!");
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