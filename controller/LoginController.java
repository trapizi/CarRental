package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.CorporateMember;
import model.CorporateMemberDAO;
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
	private RadioButton registerMemberRadioButton;
	@FXML
	private RadioButton registerCorporateMemberRadioButton;
	@FXML
	private Label statusLabel;
	
	private StaffDAO staffDAO;
	private MemberDAO memberDAO;
	private CorporateMemberDAO corporateMemberDAO;
	
	private final String memberHomePage = "MemberHome.fxml";
	//private final String corporateMemberHomePage = "";
	private final String registrationPage = "MemberRegistrationDialog.fxml";
	private final String corporateMemberRegistrationPage = "CorporateMemberRegistrationDialog.fxml";
	// private final String staffHomePage = "";
	
    @FXML
    private void initialize () {      	
    	this.staffDAO = new StaffDAO();
    	this.memberDAO = new MemberDAO();
    	this.corporateMemberDAO = new CorporateMemberDAO();
    	
    	assert(this.mainApp != null);
    }
    
    // TODO: do better exception handling
    @FXML
    private void handleLogin() throws Exception {   
    	
    	// checks database for an existing user with the username entered 
    	User user = this.getUser();
    	
    	try {
    		boolean validLogin = user.getPassword().equals(this.passwordField.getText());
    		
    		if (!validLogin) { 
    			// TODO: set appropriate error message on unsuccessful login
        		this.statusLabel.setText(user.getUserName() + " " + user.getPassword());
    		} else {
    			// remember who logged in
    			mainApp.setLoggedInAs(user);
    			
    			// TODO: remove this after next page implemented
    			System.out.println("LOGGED IN AS: " + mainApp.getLoggedInAs().getUserName());
        		this.statusLabel.setText("Login successful.");

        		// displays a suitable home page based on member / corporate member / staff 
        		this.displayHomePage(user);
    		}
    		
    	// user doesn't exist
    	} catch (NullPointerException e) {
    		this.statusLabel.setText("Incorrect username or password entered. No user found");
    	} 
    }
    
    @FXML
    private void handleRegister() {
    	// show member registration screen
    	if (this.registerMemberRadioButton.isSelected()) {
	        this.registerMember();
	        
	    // show corporate member registration screen
    	} else {
    		this.registerCorporateMember();
    	}
    }
    
    /**
     * Fetches a user from the database if it exists
     * @return A user based on the username entered in the textfield
     * @throws Exception Thrown if there's an exception thrown from the database
     */
    private User getUser() throws Exception {
    	User user;
    	
    	// check if existing staff
    	if (this.staffRadioButton.isSelected()) {
    		try {
    			user = staffDAO.findByUsername(this.userNameTextField.getText());
    		} catch (Exception e) {
    			throw e;
    		}
    		
    	// check if existing (corporate) member
    	} else {
    		try {
    			user = this.getMember();	
    		} catch (Exception e) {
    			throw e;
    		}
    	}
    	
    	return user;
    }
    
    /**
     * Displays a pop-up screen to enter member details
     */
    private void registerMember() {
        Member tempMember = new Member();
        boolean okClicked = mainApp.showEditDialog(tempMember, "MemberEditDialog.fxml");
        
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
    
    /**
     * Displays a pop-up screen to enter corporate member details
     */
    private void registerCorporateMember() {
        CorporateMember tempMember = new CorporateMember();
        boolean okClicked = mainApp.showEditDialog(tempMember, corporateMemberRegistrationPage);
        
        if (okClicked) {
	        try {	
	        	// NOTE: before inserting tempMember into the database does not have a memberID
	        	// memberID is assigned after it has been inserted
	        	memberDAO.insert(tempMember);
	        	
	        	// We query the database for tempMember to retrieve its memberID and update it
	        	tempMember.setMemberID(memberDAO.findByUserName(tempMember.getUserName()).getMemberID());
	        	
	        	corporateMemberDAO.insert(tempMember);
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Registration error", 
	            		"Could not register corporate member!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
	        }
        }
    }
    
    private void displayHomePage(User user) throws SQLException, ClassNotFoundException {
		// bring them to the next page
		// display different pages depending on whether they logged in as user or member
		if (this.memberRadioButton.isSelected()) {
			
			try {
				// check if corporate member				
				mainApp.showView(memberHomePage, new MemberHomeController());
				
			// let parent function handle exceptions
			} catch (Exception e) {
				throw e;
			}
			
		} else if (this.staffRadioButton.isSelected()) {

			// mainApp.showView(staffHomePage);
			mainApp.showView("StaffView.fxml");
			
		} else {
    		this.statusLabel.setText("SHOULD NEVER REACH THIS POINT -- A RADIO BUTTON SHOULD BE SELECTED!!!.");
		}
    }
    
    /**
     * Returns a corporate member if they exist in the corporate member table, otherwise a member
     * @return A corporate member or member 
     * @throws Exception Thrown if the database throws an exception
     */
    private User getMember() throws Exception {
    	User user = memberDAO.findByUserName(this.userNameTextField.getText());
    	try {
			// check if corporate member
    		if (user != null) {
    			User temp = corporateMemberDAO.findById(((Member) user).getMemberID());
			
				if (temp != null) {
					user = temp;
				}
    		}
    	} catch (Exception e) {
    		throw e;
    	}
			
		return user;
    }
}
