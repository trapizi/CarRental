package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Staff;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 * 
 * @author Bing Wen (z3463269)
 * Code skeleton adapted from http://code.makery.ch/library/javafx-8-tutorial
 */
public class StaffEditController {
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

    // the edit page opens in another window which requires another stage
    private Stage dialogStage;
    
    // holds the staff member being created / edited
    private Staff staff;
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
    
    public void setStaff(Staff staff) {
    	this.staff = staff;
    	this.firstNameTextField.setText(staff.getFirstName());
    	this.lastNameTextField.setText(staff.getLastName());
    	this.userNameTextField.setText(staff.getUserName());
    	this.passwordTextField.setText(staff.getPassword());
    	this.emailTextField.setText(staff.getEmail());
		this.phoneNoTextField.setText(Integer.toString(staff.getPhoneNo()));
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
    private void handleOk() throws InvalidInputException {
    	// check for valid input here
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
    	}

    	// set staff fields if valid input entered
		staff.setFirstName(this.firstNameTextField.getText());
		staff.setUserName(this.userNameTextField.getText());
		staff.setPassword(this.passwordTextField.getText());
		staff.setLastName(this.lastNameTextField.getText());
		staff.setEmail(this.emailTextField.getText());
		staff.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
		
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