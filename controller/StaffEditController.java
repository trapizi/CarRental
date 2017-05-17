package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Staff;
import util.InvalidInputException;

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
    @FXML
    private TextField homeAddressTextField;
    @FXML
    private TextField salaryTextField;
    
    private Stage dialogStage;
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
    	this.homeAddressTextField.setText(staff.getHomeAddress());    
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
    				this.lastNameTextField.getText(), this.emailTextField.getText(), this.phoneNoTextField.getText(), 
    				this.homeAddressTextField.getText());
    	} catch (InvalidInputException e) {    		
    		// Display alert for incorrect input
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid input entered!");
            alert.setHeaderText("Invalid input entered!");
            alert.setContentText(e.getMessage());
            
            alert.showAndWait();
            
            // TODO: what do we want to do with this exception?
            throw e;
    	}

    	// TODO: create function within staff to do call setters below
		staff.setFirstName(this.firstNameTextField.getText());
		staff.setUserName(this.userNameTextField.getText());
		staff.setPassword(this.passwordTextField.getText());
		staff.setLastName(this.lastNameTextField.getText());
		staff.setEmail(this.emailTextField.getText());
		staff.setHomeAddress(this.homeAddressTextField.getText());
		staff.setPhoneNo(Integer.parseInt(this.phoneNoTextField.getText()));
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