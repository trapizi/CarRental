package controller;

import java.io.File;
import java.io.FileWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.CorporateMemberDAO;
import model.Member;
import model.MemberDAO;
import util.AlertBuilder;

public class StaffHomeController extends ControllerBase {
	@FXML
	private Label welcomeLabel;
	@FXML
	private Button viewConsultationButton;
	
	private final String manageMemberPage = "MemberView.fxml";
	private final String manageStaffPage = "StaffView.fxml";
	
	private MemberDAO memberDAO;
	private CorporateMemberDAO corporateMemberDAO;
		
    @FXML
    private void initialize() {				
		this.welcomeLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
		// TODO: hide any buttons not related to corporate member here if user not a corporate member
		this.memberDAO = new MemberDAO();
		this.corporateMemberDAO = new CorporateMemberDAO();
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
	private void handleGenerateCorporateMailingList() throws Exception {
		File outputFile  = new File("corporateMailingList.txt");
		FileWriter writer = null;
		
		ObservableList<Member> list = FXCollections.observableArrayList();
		
		// query database for corporateMember details
		try {
			list = memberDAO.findAll();
		} catch (Exception e) {
			Alert alert = AlertBuilder.createAlert(
		            AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "Database Error", e.getMessage());
			
            alert.showAndWait();       
		}
			
		try {
			writer = new FileWriter(outputFile);
			
			// each line in the mailing list will be in the format [email], [username]
			for (Member m: list) {
				writer.write(m.getEmail() + ", " + m.getUserName() + "\n");
			}
			
		} catch (Exception e) {
			
			Alert alert = AlertBuilder.createAlert(
		            AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "I/O Error", e.getMessage());
			
            alert.showAndWait();  
            
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					
					Alert alert = AlertBuilder.createAlert(
				            AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "I/O Error", e.getMessage());
					
		            alert.showAndWait(); 
				}
			}
		}
	}
	
	@FXML
	private void handleGenerateMemberMailingList() {
		System.out.println("MEMBER MAILING LIST");
	}
}
