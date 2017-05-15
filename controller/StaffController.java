package controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Staff;
import model.StaffDAO;

public class StaffController {
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneNoText;
    @FXML
    private TextField homeAddressText;
    @FXML
    private TextField salaryText;    
    @FXML
    private TextArea resultArea;

    @FXML
    private void initialize () {
    	/* TODO: initialise if needed */
    }
    
    //Insert a staff member to the DB
    @FXML
    private void insertStaff(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	System.out.println("Calling insertStaff()");
    	
    	/* construct staff object for insertion */
    	StaffDAO staffDAO = new StaffDAO();
    	Staff staff = new Staff();
    	boolean invalidInput = false;
    	
		staff.setFirstName(firstNameText.getText());
		staff.setPassword(passwordText.getText());
		staff.setLastName(lastNameText.getText());
		staff.setEmail(emailText.getText());
		staff.setHomeAddress(homeAddressText.getText());
		
		/* clear text area for errors */
		resultArea.clear();
		
		/* check valid numbers for phone number and salary are entered */
    	try {
    		staff.setPhoneNo(Long.parseLong(phoneNoText.getText()));
    	} catch (NumberFormatException e) {
    		invalidInput = true;
    		resultArea.appendText("Invalid phone number entered!\n");
    	}
    	
    	try {
    		staff.setSalary(Double.parseDouble(salaryText.getText()));
    	} catch (NumberFormatException e) {
    		invalidInput = true;
    		resultArea.appendText("Invalid salary entered!\n");
    	}
    	
    	if (!invalidInput) {
	        try {	   	
	            staffDAO.insert(staff);
	            resultArea.setText("Employee inserted! \n");
	        } catch (SQLException e) {
	            resultArea.setText("Problem occurred while inserting employee " + e);
	            throw e;
	        }
    	}
    }
}
