package controller;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Member;
import util.AlertBuilder;
import util.InvalidInputException;

public class MemberEditController extends EditControllerBase {
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;    
    @FXML
    private TextField emailTextField;    
    @FXML
    private TextField phoneNoTextField;
    @FXML
    private TextField accountExpiryTextField;    
    @FXML
    private TextField homeAddressTextField;
    @FXML
    private TextField creditCardTextField;
    
    @FXML
    private Label expiryDateLabel;
    
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
		
		// handle case where default constructor is called and we .toString() it
		try {
			this.accountExpiryTextField.setText(member.getAccountExpiry().toString());	
		} catch (NullPointerException e) {
			this.accountExpiryTextField.setText("");
		}
		
		this.homeAddressTextField.setText(member.getHomeAddress());
		this.creditCardTextField.setText(member.getCreditCard());
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
    		
    		Member.validateInput(
    				this.userNameTextField.getText(), this.passwordTextField.getText(), this.firstNameTextField.getText(), 
    				this.lastNameTextField.getText(), this.emailTextField.getText(), this.phoneNoTextField.getText(),
    				this.accountExpiryTextField.getText(), this.homeAddressTextField.getText(), this.creditCardTextField.getText(),
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
    	} 

    	// set member fields if valid input entered    	
    	member.setUserName(this.userNameTextField.getText());
    	member.setPassword(this.passwordTextField.getText());
    	member.setFirstName(this.firstNameTextField.getText());
    	member.setLastName(this.lastNameTextField.getText());
    	member.setEmail(this.emailTextField.getText());
		member.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
		
		// TODO: overload setAccountExpiry to accept a string or this will expect a java.sql.Date variable
		member.setAccountExpiry(this.accountExpiryTextField.getText());		
		member.setHomeAddress(this.homeAddressTextField.getText());
		member.setCreditCard(this.creditCardTextField.getText());
		
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
