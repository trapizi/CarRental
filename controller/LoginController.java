package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Member;
import model.MemberDAO;
import model.StaffDAO;
import model.User;
import util.AlertBuilder;

/**
 * Controller to handle login
 * @author Bing Wen (z3463269)
 */
public class LoginController extends ControllerBase {
	@FXML
	private TextField userNameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private RadioButton staffRadioButton;
	@FXML
	private RadioButton memberButton;
	@FXML
	private Label statusLabel;
	
	private StaffDAO staffDAO;
	private MemberDAO memberDAO;
	
    @FXML
    private void initialize () {      	
    	this.staffDAO = new StaffDAO();
    	this.memberDAO = new MemberDAO();
    }
    
    // TODO: do better exception handling
    @FXML
    private void handleLogin() throws Exception {
    	
    	// READ IN USERNAME AND PASSWORD AND BUTTON SELECTED
    	// QUERY DATABASE FOR MATCH
    	// BRING TO STAFF PAGE?
    	
    	User user;
    	
    	// grab user from database
    	if (this.staffRadioButton.isSelected()) {
    		try {
    			user = staffDAO.findByUsername(this.userNameTextField.getText());
    		} catch (Exception e) {
    			throw e;
    		}
    	} else {
    		try {
    			user = memberDAO.findByUserName(this.userNameTextField.getText());
    		} catch (Exception e) {
    			throw e;
    		}
    	}
    	
    	try {
    		boolean validLogin = user.getPassword().equals(this.passwordField.getText());
    		
    		if (!validLogin) {
        		this.statusLabel.setText("Incorrect username or password entered.");
    		} else {
    			// bring them to the next page
        		this.statusLabel.setText("Login successful.");
        		
        		// display different pages depending on whether they logged in as user or member
        		if (this.memberButton.isSelected()) {
        			
        		} else if (this.staffRadioButton.isSelected()) {
        			
        		} else {
            		this.statusLabel.setText("SHOULD NEVER REACH THIS POINT -- A RADIO BUTTON SHOULD BE SELECTED!!!.");
        		}
    		}
    		
    	// user doesn't exist
    	} catch (NullPointerException e) {
    		this.statusLabel.setText("Incorrect username or password entered.");
    	}
    }
    
    @FXML
    private void handleRegister() {
        Member tempMember = new Member();
        boolean okClicked = mainApp.showMemberEditDialog(tempMember);
        
        if (okClicked) {
	        try {	   	
	        	memberDAO.insert(tempMember);
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Registration error", 
	            		"Could not register member!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
	        }
        }
    }
}
