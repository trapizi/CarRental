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
	private RadioButton memberRadioButton;
	@FXML
	private Label statusLabel;
	
	private StaffDAO staffDAO;
	private MemberDAO memberDAO;
	
	private final String memberHomePage = "MemberHome.fxml";
	private final String registrationPage = "RegistrationEditDialog.fxml";
	// private final String staffHomePage = "";
	
    @FXML
    private void initialize () {      	
    	this.staffDAO = new StaffDAO();
    	this.memberDAO = new MemberDAO();
    	
    	assert(this.mainApp != null);
    }
    
    // TODO: do better exception handling
    @FXML
    private void handleLogin() throws Exception {    	
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
    		
    		System.out.println(user.getPassword() + " " + this.passwordField.getText());
    		
    		if (!validLogin) { 
    			// TODO: set appropriate error message on unsuccessful login
        		this.statusLabel.setText(user.getUserName() + " " + user.getPassword());
    		} else {
    			
    			// remember who logged in
    			mainApp.setLoggedInAs(user);
    			
    			System.out.println("LOGGED IN AS: " + mainApp.getLoggedInAs().getUserName());
    			
    			// TODO: remove this after next page implemented
        		this.statusLabel.setText("Login successful.");

    			// bring them to the next page
        		// display different pages depending on whether they logged in as user or member
        		if (this.memberRadioButton.isSelected()) {

        			mainApp.showView(memberHomePage, new MemberHomeController());
        			//mainApp.showView("MemberView.fxml");
        			
        		} else if (this.staffRadioButton.isSelected()) {
        			System.out.println("LOGIN SUCCESSFUL");

        			// mainApp.showView(staffHomePage);
        			mainApp.showView("StaffView.fxml");
        			
        		} else {
            		this.statusLabel.setText("SHOULD NEVER REACH THIS POINT -- A RADIO BUTTON SHOULD BE SELECTED!!!.");
        		}
    		}
    		
    	// user doesn't exist
    	} catch (NullPointerException e) {
    		this.statusLabel.setText("Incorrect username or password entered. No user found");
    	}
    }
    
    @FXML
    private void handleRegister() {
        Member tempMember = new Member();
        boolean okClicked = mainApp.showEditDialog(tempMember, registrationPage);
        
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
