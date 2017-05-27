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
    
    @FXML
    private void handleViewConsultation() {
    	this.mainApp.showView("StaffViewConsultation.fxml");
    }
        
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
		// generate mailing list and display location in alert
		String pathFileName = System.getProperty("user.dir") + "\\" + this.generateMailingList(true);
		
        // Create and display alert for the database exception
        Alert alert = AlertBuilder.createAlert(
        		AlertType.INFORMATION, mainApp.getPrimaryStage(), "Status", 
        		"Mailing List Generated!", "Mailing list can be found at: " + pathFileName); 
        
        alert.showAndWait();
		
		
	}
	
	@FXML
	private void handleGenerateMemberMailingList() {
		// generate mailing list and display location in alert
		String pathFileName = System.getProperty("user.dir") + "\\" + this.generateMailingList(false);
		
        // Create and display alert for the database exception
        Alert alert = AlertBuilder.createAlert(
        		AlertType.INFORMATION, mainApp.getPrimaryStage(), "Status", 
        		"Mailing List Generated!", "Mailing list can be found at: " + pathFileName); 
        
        alert.showAndWait();
	}
	
	/**
	 * Generates a mailing list given a boolean value
	 * @param isCorporateMember True if a mailing list for corporate members wants to be generated
	 * @return The mailing list's file name
	 */
	private String generateMailingList(boolean isCorporateMember) {
		String outputFileName;
		
		if (isCorporateMember) {
			outputFileName = "corporateMailingList.txt";
		} else {
			outputFileName = "memberMailingList.txt";
		}
		
		// grab list of corporate members to make a mailing list
		ObservableList<Member> list = FXCollections.observableArrayList();
		
		// query database for corporateMember details
		try {
			if (isCorporateMember) {
				list = memberDAO.findAllCorporateMembers();
			} else {
				list = memberDAO.findAll();
			}
		} catch (Exception e) {
			Alert alert = AlertBuilder.createAlert(
		            AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "Database Error", e.getMessage());
			
            alert.showAndWait();       
		}
		
		File outputFile  = new File(outputFileName);
		FileWriter writer = null;
			
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
			// close fileWriter 
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
		
		return outputFileName;
	}
}
