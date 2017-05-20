package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MemberHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
	
	// TODO: add more buttons as more sections are completed
	@FXML
	private Button viewProfileButton;
	
	private final String profileViewFileName = "MemberProfileView.fxml";
	
	@FXML
	private void intialize() {
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
	}
	
	@FXML
	private void handleViewProfile() {
		mainApp.showView(profileViewFileName);
	}
}
