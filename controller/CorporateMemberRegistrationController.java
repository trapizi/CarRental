package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import model.Corporate;
import model.CorporateDAO;
import model.CorporateMember;
import model.Member;
import util.AlertBuilder;
import util.InvalidInputException;

public class CorporateMemberRegistrationController extends EditControllerBase {
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;    
    @FXML
    private TextField emailTextField;    
    @FXML
    private TextField phoneNoTextField; 
    @FXML
    private TextField homeAddressTextField;
    @FXML
    private TextField creditCardTextField;	
	@FXML
	private TextField companyNameTextField;
	
	private CorporateMember corporateMember;
	
    @FXML
    private void initialize() {
    }
    
    @Override
    public void setObject(Object o) {
    	this.corporateMember = (CorporateMember) o;
    	this.userNameTextField.setText(corporateMember.getUserName());
    	this.passwordTextField.setText(corporateMember.getPassword());
    	this.firstNameTextField.setText(corporateMember.getFirstName());
    	this.lastNameTextField.setText(corporateMember.getLastName());
    	this.emailTextField.setText(corporateMember.getEmail());
		this.phoneNoTextField.setText(Integer.toString(corporateMember.getPhoneNo()));		
		this.homeAddressTextField.setText(corporateMember.getHomeAddress());
		this.creditCardTextField.setText(corporateMember.getCreditCard());
		
		if (corporateMember.getCorporation() == null) {
			this.companyNameTextField.setText("");
		} else {
			this.companyNameTextField.setText(corporateMember.getCorporation().getCompanyName());
		}
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws InvalidInputException, SQLException, ClassNotFoundException {
    	/* TODO: follow these steps for this section
    	 * 1. check that input entered is valid
    	 * 2. use setters for the object you're modifying
    	 * 3. close the edit window and set OkClicked to true
    	 */
    	
    	// check for valid input here
    	try {
    		
    		// doesn't validate expiry date as it's set by the system
    		Member.validateRegistrationInput(
    				this.userNameTextField.getText(), this.passwordTextField.getText(), this.firstNameTextField.getText(), 
    				this.lastNameTextField.getText(), this.emailTextField.getText(), this.phoneNoTextField.getText(),
    				this.homeAddressTextField.getText(), this.creditCardTextField.getText(),
    				this.corporateMember.getMemberID());
    		
    		CorporateMember.validateCompanyName(this.companyNameTextField.getText());
    		
    	} catch (InvalidInputException e) {  
    		
    		// Create and display alert for incorrect input
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, dialogStage, "Invalid Input", "Invalid input entered!", e.getMessage()); 
            
            alert.showAndWait();
            
            // throw exception as well for debugging purposes
            throw e;
    	} catch (SQLException | ClassNotFoundException e) {
    		
    		// Create and display alert for database related exceptions
    		Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, dialogStage, "Database Error", "Database could not complete query", e.getMessage()); 
            
            alert.showAndWait();
            
            throw e;
    	} catch (NullPointerException e) {
    		
    		// Expect to catch an exception from expiry date being null
    	}

    	// set corporateMember fields if valid input entered    	
    	corporateMember.setUserName(this.userNameTextField.getText());
    	corporateMember.setPassword(this.passwordTextField.getText());
    	corporateMember.setFirstName(this.firstNameTextField.getText());
    	corporateMember.setLastName(this.lastNameTextField.getText());
    	corporateMember.setEmail(this.emailTextField.getText());
		corporateMember.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
		
		// set expiry date to 1 month from today
		final int corporateMembershipLength = 1;
		Date date = Date.valueOf(LocalDate.now().plusMonths(corporateMembershipLength));
		
		corporateMember.setAccountExpiry(date);
		corporateMember.setHomeAddress(this.homeAddressTextField.getText());
		corporateMember.setCreditCard(this.creditCardTextField.getText());
		
		CorporateDAO corporateDAO = new CorporateDAO();
		Corporate corporation = corporateDAO.findByName(this.companyNameTextField.getText());
		
		corporateMember.setCorporation(corporation);
		
		// close edit window
        okClicked = true;
        dialogStage.close();
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
