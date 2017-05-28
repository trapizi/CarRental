package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import model.Member;
import util.AlertBuilder;
import util.InvalidInputException;

public class RegistrationDialogController extends EditControllerBase {
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
    
    // holds the staff member being created / edited
    private Member member;
    
    @FXML
    private void initialize() {
    }
    
    @Override
    public void setObject(Object o) {
    	this.member = (Member) o;
    	this.userNameTextField.setText(member.getUserName());
    	this.passwordTextField.setText(member.getPassword());
    	this.firstNameTextField.setText(member.getFirstName());
    	this.lastNameTextField.setText(member.getLastName());
    	this.emailTextField.setText(member.getEmail());
		this.phoneNoTextField.setText(Integer.toString(member.getPhoneNo()));		
		this.homeAddressTextField.setText(member.getHomeAddress());
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
    				this.member.getMemberID());
    		
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

    	// set member fields if valid input entered    	
    	member.setUserName(this.userNameTextField.getText());
    	member.setPassword(this.passwordTextField.getText());
    	member.setFirstName(this.firstNameTextField.getText());
    	member.setLastName(this.lastNameTextField.getText());
    	member.setEmail(this.emailTextField.getText());
		member.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
		
		// set expiry date to 1 month from today
		final int membershipLength = 1;
		Date date = Date.valueOf(LocalDate.now().plusMonths(membershipLength));
		
		member.setAccountExpiry(date);
		member.setHomeAddress(this.homeAddressTextField.getText());
		
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
