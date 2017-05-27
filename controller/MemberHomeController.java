package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.CorporateMember;

public class MemberHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button viewConsultationButton;
	
	private final String profileViewFileName = "MemberProfile.fxml";
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");

		// TODO: hide any buttons not related to corporate member here if user not a corporate member
		if (!(this.mainApp.getLoggedInAs() instanceof CorporateMember)) {
			// display consultation button
			//this.viewConsultationButton.setDisable(true);
			this.viewConsultationButton.setVisible(false);
		}
    }
    
	// TODO: add more functions to change scenes with buttons
	@FXML
	private void handleViewProfile() {
		mainApp.showView(profileViewFileName, new MemberProfileController());
	}
	
	@FXML
	private void handleStuffView() {
		mainApp.showView("StuffView.fxml");
	}
	
	@FXML
	private void handleAgreementsView() {
		mainApp.showView("AgreementView.fxml");
	}
}
