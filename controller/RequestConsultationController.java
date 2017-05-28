/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.AgreementPayment;
import model.Consultation;
import model.ConsultationDAO;
import model.ConsultationPayment;
import model.ConsultationPaymentDAO;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 *
 * @author elizabeth
 */
public class RequestConsultationController extends ControllerBase {
    
    ObservableList<String> timePickerList = FXCollections.observableArrayList(
            "10:00","11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    
    @FXML
    private Label welcomeCorpLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField dateText;
    @FXML
    private ChoiceBox timePicker;
    @FXML
    private TextField enterCompanyID; 
        
    private ConsultationPaymentDAO paymentDAO;
	private ConsultationDAO cDao;
	private Consultation consult1;
    private final float defaultConsultationPrice = 100.0f;
 
    @FXML
    private void initialize() {				
		this.priceLabel.setText("$" + "100.00");
		this.timePicker.setItems(timePickerList);
	    this.timePicker.setValue("12:00");
	    this.paymentDAO = new ConsultationPaymentDAO();
	    this.cDao = new ConsultationDAO();
	    this.consult1 = new Consultation();
    }
     
    @FXML
    private void handleMakePayment() {
    	boolean validInput = true;
    	
    	// validate input before running payment
		try {        
			Consultation.validateInput(this.dateText.getText(), this.enterCompanyID.getText());
		} catch (InvalidInputException e){
			//create and display alert for incorrect input
			Alert alert = AlertBuilder.createAlert(
					Alert.AlertType.WARNING, this.mainApp.getPrimaryStage(), "Invalid Input", "Invalid input entered!", e.getMessage()); 

			alert.showAndWait();
			validInput = false;
		} catch (Exception e) {
			//create and display alert for incorrect input
			Alert alert = AlertBuilder.createAlert(
					Alert.AlertType.WARNING, this.mainApp.getPrimaryStage(), "Database Error", "Database Error Occurred!", e.getMessage()); 

			alert.showAndWait();
			validInput = false;
		}
    	
		// handle payment only if valid input was entered
		if (validInput) {
			// set up payment amount for payment screen
			ConsultationPayment tempPayment = new ConsultationPayment();
			tempPayment.setPaymentAmount(this.defaultConsultationPrice);

			boolean validPayment = mainApp.showEditDialog(tempPayment, LoginController.PAYMENT_PAGE);

			if (validPayment) {     		

				consult1.setConsultationPrice(defaultConsultationPrice);

				// convert from hh:mm:ss to java.sql.Time
				consult1.setConsultationTime(java.sql.Time.valueOf(this.timePicker.getValue().toString() + ":00"));
				consult1.setConsultationDate(this.dateText.getText());
				consult1.setCorporateID(Integer.parseInt(this.enterCompanyID.getText()));

				// insert consultation to database
				try {	    		
					cDao.insert(consult1);	    		
				} catch (Exception e) {
					// Create and display alert for database related exceptions
					Alert alert = AlertBuilder.createAlert(
							AlertType.WARNING, this.mainApp.getPrimaryStage() , "Database Error", "Database could not complete query", e.getMessage()); 

					alert.showAndWait();
				}

				// insert payment into database
				try {	    
					// find the most recent payment and set its consultation num
					consult1 = cDao.findMostRecent();

					tempPayment.setConsultationNum(consult1.getConsultationNum());

					// set payment price as default price
					tempPayment.setPaymentAmount(defaultConsultationPrice);
					paymentDAO.insert(tempPayment);
					
				} catch (Exception e) {
					// Create and display alert for database related exceptions
					Alert alert = AlertBuilder.createAlert(
							AlertType.WARNING, this.mainApp.getPrimaryStage() , "Database Error", "Database could not complete query", e.getMessage()); 

					alert.showAndWait();
				}
				
				// bring them back to member home page once payment is complete
				this.mainApp.showMemberHomePage();	
			}
		}
    }
}
