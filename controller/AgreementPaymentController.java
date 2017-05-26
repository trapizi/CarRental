package controller;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Payment;
import mainApp.SelenaMain;
import model.AgreementPayment;
import model.AgreementPaymentDAO;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 *
 * @author selena
 */
public class AgreementPaymentController extends EditControllerBase{
    
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
    
        
    private AgreementPayment agreementPayment;
        
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
        AgreementPaymentDAO apDAO = new AgreementPaymentDAO();
        AgreementPayment ap = apDAO.findById(1);
        showPaymentAutofill(ap);
    }
    
    
    //showPaymentAutofill()
    private void showPaymentAutofill(AgreementPayment agreementPayment){
        //for paymentID
        paymentIDLabel.setText(Integer.toString(agreementPayment.getAgreementPayment_id()));
        
        //for amountDue
        amountDueLabel.setText(Float.toString((float) agreementPayment.getPaymentAmount()));
        
        //for paymentDate
        try {
            paymentDateLabel.setText(agreementPayment.getPaymentDate().toString());
        } catch (NullPointerException e){
            paymentDateLabel.setText("");
        }     
    }
   
    
    //setObject()
    public void setObject(Object o){
        this.agreementPayment = (AgreementPayment) o;
        this.accountOwnerNameField.setText(agreementPayment.getAccountOwnerName());
        this.paymentTypeChoiceBox.setValue(agreementPayment.getPaymentType());
        this.paymentAccountField.setText(agreementPayment.getPaymentAccount());
        this.accountExpiryField.setText(date.toString(agreementPayment.getAccountExpiry()));      
    }
    
    
    //when user clicks "Confirm Payment"
    @FXML
    private void handleConfirmPaymentButton() throws InvalidInputException, SQLException, ClassNotFoundException { 
        /*
        //(1)Check for valid input
        try {        
            AgreementPayment.validateInput(
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
        agreementPayment.setAccountOwnerName(this.accountOwnerNameField.getText());
        agreementPayment.setPaymentType(this.paymentTypeOptionBox);
        agreementPayment.setPaymentAccount(this.paymentAccountField.getText());
        agreementPayment.setAccountExpiry(this.accountExpiryField.getText());
        
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
