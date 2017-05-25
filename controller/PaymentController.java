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
import util.AlertBuilder;
import util.InvalidInputException;

/**
 *
 * @author selena
 */
public class PaymentController {
    
    //VIEWS
    //Payment
    @FXML
    private Label paymentHeadingLabel;
    
    @FXML
    private Label paymentIDLabel;
    
    @FXML
    private Label amountDueLabel;
    
    @FXML
    private Label paymentDateLabel;
    
    
    //Billing Information
    @FXML
    private Label billingInformationHeadingLabel;
    
    @FXML
    private Label accountOwnerNameLabel;
    
    @FXML
    private Label accountOwnerNameField;
    
    @FXML 
    private Label paymentTypeLabel;
   
    @FXML
    private ChoiceBox paymentTypeChoiceBox;
    
    @FXML
    private Label paymentAccountLabel;
    
    @FXML
    private TextField paymentAccountField;
    
    @FXML
    private Label accountExpiryLabel;
    
    @FXML
    private DatePicker accountExpiryDatePicker;
   
    
    //Confirm Payment 
    @FXML
    private Button confirmPaymentButton;   
    
    
    
    private SelenaMain mainApp;
    
    
    
    public void setMain(SelenaMain mainApp){ 
        this.mainApp = mainApp;
    }  

    
    //paymentType choice box
    ObservableList<String> paymentTypeList = FXCollections
                .observableArrayList("Visa", "MasterCard", "American Express");
    
 
    
    
    //holds the payment being created
    private Payment payment;
     
    
    //method to initialise elements
    @FXML
    private void initialise(){    
        paymentTypeChoiceBox.setItems(paymentTypeList);
    }
    
    
    
    
    public void setObject(Object o){
        this.payment = (Payment) o;
        this.accountOwnerNameField.setText(payment.getAccountOwnerName());
        //this.paymentTypeChoiceBox.setValue("Visa");
        this.paymentAccountField.setText(payment.getPaymentAccount());
        
    }
    
    
    //when user clicks "Confirm Payment"
    @FXML
    private void handleConfirmPaymentButton() throws InvalidInputException, SQLException, ClassNotFoundException { 
        /*
        try{
            
            //check for valid input
            Payment.validateInput(
                    this.accountOwnerNameField.getText(),
                    this.paymentAccountField.getText());
            
        } catch (InvalidInputException e){
            //create and display aler for incorrect input
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
            
            // TODO: throw exception for debugging
            throw e;
    	} 
        
        //set payment fields if valid input entered
        payment.setAccountOwnerName(this.accountOwnerNameField.getText());
        payment.setPaymentAccount(this.paymentAccountField.getText());
        
        
        //TO DO GO TO NEXT PAGE??!
        */
    }
    
    
} 
