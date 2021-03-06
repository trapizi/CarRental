package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Corporate;
import model.CorporateDAO;

/**
 * Check input from textField is not stupid
 * @author Bing
 */
public final class InputValidator {	
	/**
	 * Check that username given is unique and not empty
	 * @param userName Username to check
	 * @throws InvalidInputException Thrown if username not unique or empty
	 * @throws SQLException Thrown if exception occurred during database query
	 * @throws ClassNotFoundException Thrown if exception occurred during connection / disconnection to database
	 */
	public static void validateUsername(String userName, String tableName, int ID) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		// check if empty
		isEmpty(userName, "Username");

		// check if username contains spaces
		if (InputValidator.containsSpaces(userName)) {
			throw new InvalidInputException("Invalid username. Username may not contain spaces.");
		}
		
		// validate username, ensure unique
		try {
			// remember to put userName in quotes to treat it as a VARCHAR value
			// Query checks if any other members/staff have taken the username
			ResultSet rs = DBUtil.dbExecuteQuery("SELECT COUNT(*) AS COUNT FROM " + tableName + " WHERE USERNAME=" + "'"+userName+"'" 
					+ " AND NOT " + tableName + "_ID=" + ID);
	
			// count occurrences of the username in the staff table
			Long userNameCount;
			if (rs.next()) {
				userNameCount = rs.getLong("COUNT");
			} else {
				userNameCount = 0L;
			}
			
			// throw exception if username already in database or empty
			if (userNameCount > 0) {
				throw new InvalidInputException("Invalid username entered. Username has already been taken.");
			} 
		} catch (SQLException e) {
			throw new SQLException("Failed to query database!");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Failed to connect to database!");
		}
	}
	
	public static void validateCompanyName(String companyName) 
			throws InvalidInputException, SQLException, ClassNotFoundException {
		// check if empty
		isEmpty(companyName, "Company Name");
		
		// validate company name, ensure it exists
		try {
			// remember to put company name in quotes to treat it as a VARCHAR value
			// check if any company exists with the given companyName
			ResultSet rs = DBUtil.dbExecuteQuery("SELECT COUNT(*) AS COUNT FROM CORPORATE" + " WHERE COMPANY_NAME=" + "'"+companyName+"'");
	
			// count occurrences of the company in the staff table
			Long userNameCount;
			if (rs.next()) {
				userNameCount = rs.getLong("COUNT");
			} else {
				userNameCount = 0L;
			}
			
			// throw exception if company name not in the table
			if (userNameCount == 0) {
				throw new InvalidInputException("Invalid company name entered. Company is not registered with SUber.");
			} 
		} catch (SQLException e) {
			throw new SQLException("Failed to query database!");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Failed to connect to database!");
		}
	}
	
	/**
	 * Check phone number given does not contain non-digits or is empty
	 * @param phoneNoText Phone number entered
	 * @throws InvalidInputException Thrown when phoneNoText is not an integer or empty
	 */
	public static void validatePhoneNo(String phoneNoText) throws InvalidInputException {
		isEmpty(phoneNoText, "Phone number");
		
		// validate phone number entered
    	try {
    		Integer.parseInt(phoneNoText);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid phone entered. Phone number only contains digits.");
    	}
	}
	
	public static void validateEmail(String email) throws InvalidInputException {
		isEmpty(email, "Email");
		
		try {
			if (!InputValidator.regexMatch(email, "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}")) {
				throw new InvalidInputException("Invalid email format. Email must be in the format name@domain!");
			}
		} catch (InvalidInputException e) {
			throw e;
		}
	}
	
	public static void validatePassword(String password) throws InvalidInputException {
		isEmpty(password, "Password");
	}
	
	public static void validateFirstName(String firstName) throws InvalidInputException {		
		isEmpty(firstName, "First Name");
	}
	
	public static void validateLastName(String lastName) throws InvalidInputException {		
		isEmpty(lastName, "Last Name");
	}
	
	public static void validateHomeAddress(String homeAddress) throws InvalidInputException {		
		isEmpty(homeAddress, "Home Address");
	}
	
	public static void validateCreditCard(String creditCard) throws InvalidInputException {		
		isEmpty(creditCard, "Credit Card");
	}
	
	public static void validateAccount(String account) throws InvalidInputException {		
		isEmpty(account, "Account");
	}
	
	public static void validateDate(String date) throws InvalidInputException {
		isEmpty(date, "Date");
		
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	format.parse(date);
        } catch (ParseException e) {
        	throw new InvalidInputException("Wrong date format entered. Date must be in the yyyy-MM-dd format.");
        }
	}
	
	public static void validateName(String name) throws InvalidInputException {		
		isEmpty(name, "Name");
	}
	
	public static void validateBrand(String brand) throws InvalidInputException {		
		isEmpty(brand, "Brand");
	}
	
	public static void validateModel(String model) throws InvalidInputException {		
		isEmpty(model, "Model");
	}
	
	public static void validateCarType (String carType) throws InvalidInputException {
		isEmpty(carType, "Car Type");
	}
	
	public static void validateSeats (String seats) throws InvalidInputException {
		isEmpty(seats, "Seats");
		positiveCheck(seats, "Seats");
		try{
			Integer.parseInt(seats);
		} catch (NumberFormatException e) {
			throw new InvalidInputException("Invalid amount of seats. Seats field can only caontains digits.");
		}
	}
	
	public static void validateTransmission(String transmission) throws InvalidInputException {		
		isEmpty(transmission, "Transmission");
	}
	
	public static void validateFuelType(String fuelType) throws InvalidInputException {		
		isEmpty(fuelType, "Fuel Type");
	}
	
	public static void validatePostcode(String postcode) throws InvalidInputException {
		isEmpty(postcode, "Postcode");
		
    	try {
    		Long.parseLong(postcode);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid postcode entered. Postcode can only contains digits.");
    	}
	}
	
	public static void validatePrice(String price) throws InvalidInputException {
		isEmpty(price, "Price");
		
    	try {
    		Float.parseFloat(price);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid price entered. Price can only contains digits.");
    	}
	}
	
	public static void validateSeeker(String seeker) throws InvalidInputException {
		isEmpty(seeker, "Seeker");
		positiveCheck(seeker, "Seeker");
		
    	try {
    		Integer.parseInt(seeker);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid seeker entered. Seeker can only contains digits for ID.");
    	}
	}
	
	public static void validateOfferer(String offerer) throws InvalidInputException {
		isEmpty(offerer, "Offerer");
		positiveCheck(offerer, "Offerer");
		
    	try {
    		Integer.parseInt(offerer);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid offerer entered. Offerer can only contains digits for ID.");
    	}
	}
	
	public static void validatePickup(String pickup) throws InvalidInputException {
		isEmpty(pickup, "Pick-up");
		positiveCheck(pickup, "Pick-up");
		
    	try {
    		Long.parseLong(pickup);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid pick-up place entered. Pick-up can only contains digits for postcode.");
    	}
	}
	
	public static void validateDestination(String dest) throws InvalidInputException {
		isEmpty(dest, "Destination");
		positiveCheck(dest, "Destination");
		
    	try {
    		Long.parseLong(dest);
    	} catch (NumberFormatException e) {
    		throw new InvalidInputException("Invalid destination entered. Destination can only contains digits for postcode.");
    	}
	}
	
	/**
	 * Checks if textField is empty
	 * @param s Text to check if empty
	 * @param fieldName The name of the field being checked
	 * @throws InvalidInputException Thrown if s is null or its length is 0
	 */
	private static void isEmpty(String s, String fieldName) throws InvalidInputException{
		if (s == null) {
			throw new InvalidInputException(fieldName + " can't be empty.");
		
		// separate check for null and length as NullPointerException will be thrown 
		// if we try to check s.length when s is null
		} else if (s.length() == 0) {
			throw new InvalidInputException(fieldName + " can't be empty.");
		}
	}

	private static void positiveCheck(String s, String fieldName) throws InvalidInputException {
		if (Integer.parseInt(s) <= 0 || Long.parseLong(s) <= 0) {
			throw new InvalidInputException(fieldName + " must be greater than 0.");
		} else;
	}
	
	/**
	 * Returns true if the string contains spaces
	 * @param s The string to check
	 * @return True if s contains space(s)
	 */
	private static boolean containsSpaces(String s) {
		return InputValidator.regexMatch(s, "\\s+");
	}
	
	private static boolean regexMatch(String s, String pattern) {
		Pattern regex = Pattern.compile(pattern);
		Matcher regexMatch = regex.matcher(s);
		
		return regexMatch.find();
	}
	
	/**
	 * Throws InvalidInputException if corporation doesn't exist in the corporate table
	 * @param ID The ID to check if it belongs to a corporation
	 * @throws Exception InvalidInputException, SQLException
	 */
	public static void validateCorporateID(String ID) throws Exception {
		try {
			CorporateDAO corporateDAO = new CorporateDAO();
			Corporate corporate = corporateDAO.findById(Integer.parseInt(ID));
			
			// null is returned if no corporation is in the database with the given ID
			if (corporate == null) {
				throw new InvalidInputException();
			}
		} catch (InvalidInputException e) {
			throw new InvalidInputException("Invalid corporateID entered. Check that your corporateID is correct!");
		} catch (Exception e) {
			throw new InvalidInputException("Database error occurred!");
		}
	}	
}