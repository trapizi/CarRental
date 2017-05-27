/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Corporate;
import model.CorporateMember;

/**
 *
 * @author elizabeth
 */
public class CorporateMemberHomeController extends ControllerBase {
    
    ObservableList<String> timePickerList = FXCollections.observableArrayList(
            "10:00AM","11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM");
    

    @FXML
    private Label welcomeCorpLabel;
    private final String profileViewFileName;
    @FXML
    private TextField priceField;
    @FXML
    private final DatePicker datePicker = new DatePicker();
    @FXML
    private ChoiceBox timePicker;
    @FXML
    private TextField enterCompanyID; 
    
    private CorporateMember corporateMember;
    private Corporate corporate;

    public CorporateMemberHomeController() {
        this.profileViewFileName = "CorporateMemberProfile.fxml";
    }
    
        @FXML
    private void initialize() {				
		this.welcomeCorpLabel.setText("Welcome, " + mainApp.getLoggedInAs().getFirstName() + "!");
                
                timePicker.setItems(timePickerList);
                timePicker.setValue("12:00PM");
                

                
                

    }
    @FXML
   /* private void handleCorpMemberAccount() {
        mainApp.showView(profileViewFileName, new MemberProfileController());
    } */
    
    private void handlePaymentHistory() {
        mainApp.showView("PastConsultationPayment.fxml");
    }
    
    private void handleSeek() {
        mainApp.showView("Seek.fxml");
       
    }
    private void handleOffer() {
        mainApp.showView("Offer.fxml");
    }
    private void handleMakePayment() {
        mainApp.showView("Payment.fxml");
    }
}
