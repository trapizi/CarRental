package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StaffHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button viewConsultationButton;
	
	//private final String profileViewFileName = "MemberProfile.fxml";
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
		// TODO: hide any buttons not related to corporate member here if user not a corporate member

    }
    
	// TODO: add more functions to change scenes with buttons
	@FXML
	private void handleViewProfile() {
		//mainApp.showView(profileViewFileName, new MemberProfileController());
		
	}
	
	@FXML
	private void handleStuffView() {
		mainApp.showView("StuffView.fxml");
	}
}
