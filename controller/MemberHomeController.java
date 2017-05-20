package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MemberHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
		
	private final String profileViewFileName = "MemberProfile.fxml";
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
    }
    
	// TODO: add more functions to change scenes with buttons
	@FXML
	private void handleViewProfile() {
		mainApp.showView(profileViewFileName, new MemberProfileController());
	}
}
