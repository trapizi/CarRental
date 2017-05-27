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
	@FXML
	private Button requestConsultationButton;
	
	private final String profileViewFileName = "MemberProfile.fxml";
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");

		// TODO: hide any buttons not related to corporate member here if user not a corporate member
		if (!(this.mainApp.getLoggedInAs() instanceof CorporateMember)) {
			this.viewConsultationButton.setVisible(false);
			this.requestConsultationButton.setVisible(false);
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
	
	//For seeker
	@FXML
	private void handleOfferView() {
		mainApp.showView("OfferView.fxml");
	}
	//For SUber to edit offers
	@FXML
	private void handleOfferViewEditable() {
		mainApp.showView("OfferViewEditable.fxml");
	}
	
	@FXML
	private void handleViewPastConsultations() {		
		mainApp.showView("PastConsultations.fxml");
	}
	
	@FXML
	private void handleRequestConsultation() {
		mainApp.showView("RequestConsultationView.fxml");
	}
}
