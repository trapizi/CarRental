package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StaffHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button viewConsultationButton;
	
	private final String manageMemberPage = "MemberView.fxml";
	private final String manageStaffPage = "StaffView.fxml";
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
		// TODO: hide any buttons not related to corporate member here if user not a corporate member
    }
    
	// TODO: add more functions to change scenes with buttons
	@FXML
	private void handleManageMembers() {
		this.mainApp.showView(manageMemberPage);
	}
	
	@FXML
	private void handleManageStaff() {
		this.mainApp.showView(manageStaffPage);
	}
	
	@FXML
	private void handleStuffView() {
		mainApp.showView("StuffView.fxml");
	}
	
	@FXML
	private void handleGenerateCorporateMailingList() {
		System.out.println("CORPORATE MAILING LIST");
	}
	
	@FXML
	private void handleGenerateMemberMailingList() {
		System.out.println("MEMBER MAILING LIST");
	}
}
