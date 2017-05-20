package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Member;
import model.Staff;
import util.AlertBuilder;
import util.InvalidInputException;

public class MemberEditController {
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
    
    // the edit page opens in another window which requires another stage
    private Stage dialogStage;
    
    // holds the staff member being created / edited
    private Member member;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setMember(Member member) {
    	this.member = member;
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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws InvalidInputException, SQLException, ClassNotFoundException {
    	// check for valid input here
    	/*
    	try {
    		Staff.validateInput(
    				this.userNameTextField.getText(), this.passwordTextField.getText(), this.firstNameTextField.getText(), 
    				this.lastNameTextField.getText(), this.emailTextField.getText(), this.phoneNoTextField.getText());
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
    	*/

    	// set member fields if valid input entered    	
    	member.setUserName(this.userNameTextField.getText());
    	member.setPassword(this.passwordTextField.getText());
    	member.setFirstName(this.firstNameTextField.getText());
    	member.setLastName(this.lastNameTextField.getText());
    	member.setEmail(this.emailTextField.getText());
		member.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
		
        
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	java.util.Date temp = format.parse(this.accountExpiryTextField.getText());
    		member.setAccountExpiry(new Date(temp.getTime()));
        } catch (ParseException e) {
        	// TODO: input validation should catch this and create dialog
        	// Move code section above to member class later
        	System.out.println("WRONG DATE FORMAT YOU DICKHEAD");
        }
		
		// TODO: set up date input
		System.out.println(this.accountExpiryTextField.getText());
		
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
