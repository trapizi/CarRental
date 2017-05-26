package controller;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Payment;
import mainApp.SelenaMain;
import model.ConsultationPayment;
import model.ConsultationPaymentDAO;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 *
 * @author selena
 */
public class ConsultationPaymentController extends EditControllerBase{
    
    //VIEWS
        //Payment    
        @FXML
        private Label paymentIDLabel;    

        @FXML
        private Label amountDueLabel;

        @FXML
        private Label paymentDateLabel;

        //Billing Information
        @FXML
        private Label accountOwnerNameField;

        @FXML
        private ChoiceBox paymentTypeChoiceBox;   

        @FXML
        private TextField paymentAccountField;

        @FXML
        private TextField accountExpiryField;

        //Confirm Payment 
        @FXML
        private Button confirmPaymentButton;   
    
        
    private ConsultationPayment consultationPayment;
        
    //reference mainApp for alerts
    private SelenaMain mainApp;
    
     
    public void setMain(SelenaMain mainApp){ 
        this.mainApp = mainApp;
    }  

    //paymentType choice box
    ObservableList<String> paymentTypeList = FXCollections
                .observableArrayList("Visa", "MasterCard", "American Express");

    
    //method to initialise elements
    @FXML
    private void initialize(){    
        paymentTypeChoiceBox.setItems(paymentTypeList);
        ConsultationPayment cpDAO = new ConsultationPaymentDAO();
        ConsultationPayment cp = cpDAO.findById(1); 
        showPaymentAutofill(cp);
    }
    
    
    //showPaymentAutofill()
    private void showPaymentAutofill(ConsultationPayment consultationPayment){
        //for paymentID
        paymentIDLabel.setText(Integer.toString(consultationPayment.getConsultationPayment_id()));
        
        //for amountDue
        amountDueLabel.setText(Float.toString((float) consultationPayment.getPaymentAmount()));
        
        //for paymentDate
        try {
            paymentDateLabel.setText(consultationPayment.getPaymentDate().toString());
        } catch (NullPointerException e){
            paymentDateLabel.setText("");
        }     
    }
   
    
    //setObject()
    public void setObject(Object o){
        this.consultationPayment = (ConsultationPayment) o;
        this.accountOwnerNameField.setText(consultationPayment.getAccountOwnerName());
        this.paymentTypeChoiceBox.setValue(consultationPayment.getPaymentType());
        this.paymentAccountField.setText(consultationPayment.getPaymentAccount());
        this.accountExpiryField.setText(date.toString(consultationPayment.getAccountExpiry()));      
    }
    
    
    //when user clicks "Confirm Payment"
    @FXML
    private void handleConfirmPaymentButton() throws InvalidInputException, SQLException, ClassNotFoundException { 
        /*
        //(1)Check for valid input
        try {        
            ConsultationPayment.validateInput(
                this.accountOwnerNameField.getText(),
                this.paymentAccountField.getText());
            
        } catch (InvalidInputException e){
            //create and display alert for incorrect input
            Alert alert = AlertBuilder.createAlert(
            	Alert.AlertType.WARNING, dialogStage, "Invalid Input", "Invalid input entered!", e.getMessage()); 
        
                alert.showAndWait();
                    // TODO: throw exception as well for debugging purposes
            throw e;
    	} catch (SQLException | ClassNotFoundException e) {
    		
    		// Create and display alert for database related exceptions
    		Alert alert = AlertBuilder.createAlert(
            		Alert.AlertType.WARNING, dialogStage, "Database Error", "Database could not complete query", e.getMessage()); 
            
            alert.showAndWait();
            
            throw e;
    	} 
        */
        
        //(2) modify agreementPayment fields if valid input entered
        consultationPayment.setAccountOwnerName(this.accountOwnerNameField.getText());
        consultationPayment.setPaymentType(this.paymentTypeOptionBox);
        consultationPayment.setPaymentAccount(this.paymentAccountField.getText());
        consultationPayment.setAccountExpiry(this.accountExpiryField.getText());
        
        //(3) close edit window and set OkClicked to true
        // close edit window
        okClicked = true;
        dialogStage.close();
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    
} 
