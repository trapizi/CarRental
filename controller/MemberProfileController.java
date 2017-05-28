package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.Member;
import model.MemberDAO;
import model.User;
import util.AlertBuilder;

/**
 * Handles member profile
 * @author Bing Wen (z3463269)
 */
public class MemberProfileController extends ControllerBase {
    @FXML
    private Label resultText;	// used to display success/failure messages for functions
    
    @FXML
    private Label memberIDLabel;
    @FXML
    private Label userNameLabel;    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;    
    @FXML
    private Label emailLabel;    
    @FXML
    private Label phoneNoLabel;
    @FXML
    private Label accountExpiryLabel;    
    @FXML
    private Label homeAddressLabel;
    
    private MemberDAO memberDAO;

    @FXML
    private void initialize () {  
    	this.memberDAO = new MemberDAO();
    	
    	this.showMemberDetails(this.mainApp.getLoggedInAs()); 
    }
    
    @FXML
    private void handleEditMember() { 
        resultText.setText("Editing...\n");
        
        try {
            Member selectedMember = (Member) this.mainApp.getLoggedInAs();
            // Note: LoginController.REGISTRATION_PAGE displays the view to edit 
            boolean okClicked = mainApp.showEditDialog(selectedMember, LoginController.REGISTRATION_PAGE);
            
            if (okClicked) {
            	
            	// triggers null pointer exception from setters if nothing is selected
                showMemberDetails(selectedMember);
                
                try {
                	memberDAO.update(selectedMember);
                } catch (Exception e) {
                    resultText.setText("Update to database failed!\n");
                }
            }
            
            resultText.setText("Edit complete!\n");
            
        } catch (NullPointerException e) {
        	// Create and display alert when no staff is selected
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Member Selected", "Select a member in the table"); 
            
            alert.showAndWait();       
        }
    }
    
    /**
     * Displays member details 
     * @param member the member member to display
     */
    private void showMemberDetails(Member member) {
        if (member != null) {
        	memberIDLabel.setText(Integer.toString(member.getMemberID()));
            firstNameLabel.setText(member.getFirstName());
            lastNameLabel.setText(member.getLastName());
            userNameLabel.setText(member.getUserName());
            emailLabel.setText(member.getEmail());
            phoneNoLabel.setText(Integer.toString(member.getPhoneNo()));
            
            // string will be null when entering a new member
            try {
            	accountExpiryLabel.setText(member.getAccountExpiry().toString());
            } catch (NullPointerException e) {
            	accountExpiryLabel.setText("");
            }
            
            homeAddressLabel.setText(member.getHomeAddress());
        } else {
        	memberIDLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            userNameLabel.setText("");
            emailLabel.setText("");
            phoneNoLabel.setText("");
            accountExpiryLabel.setText("");
            homeAddressLabel.setText("");
        }
    }
    
    /**
     * showMemberDetails overload
     * @param user the member to view in the profile
     */
    private void showMemberDetails(User user) {
    	showMemberDetails((Member) user);
    }
}
