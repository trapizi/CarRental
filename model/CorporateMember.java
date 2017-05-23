package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import util.InputValidator;
import util.InvalidInputException;

/**
 * @author Bing Wen (z3463269)
 */
public class CorporateMember extends Member {
	private Corporate corporation;

	@Override
	public String toString() {
		return "CorporateID: " + this.corporation.getCorporateID() + " | " + super.toString();
	}
	
	/**
	 * Checks that the company name entered is an existing company
	 * @param companyName The company to check if it exists 
	 * @throws InvalidInputException Thrown if no company exists with the companyName
	 * @throws SQLException Thrown if an error occurs during the query
	 * @throws ClassNotFoundException Thrown if an error occurs during connection to the database
	 */
	public static void validateCompanyName(String companyName) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		
		try {
			InputValidator.validateCompanyName(companyName);
					
		// throw exception and let the controller deal with it
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CorporateMember() {
		super();
		//this.corporateID = new SimpleIntegerProperty();
	}
	
	public Corporate getCorporation() {
		return corporation;
	}
	
	public void setCorporation(Corporate corporation) {
		this.corporation = corporation;
	}
}
