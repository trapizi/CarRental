package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AgreementPayment; 
import model.AgreementPaymentDAO;
import model.Payment;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 *
 * @author selena
 */
public class PaymentDialogController extends EditControllerBase {
	@FXML
	private Label paymentIDLabel;    

	@FXML
	private Label amountDueLabel;

	@FXML
	private Label paymentDateLabel;

	//Billing Information
	@FXML
	private TextField accountOwnerNameField;

	@FXML
	private ChoiceBox<String> paymentTypeChoiceBox;   

	@FXML
	private TextField paymentAccountField;

	@FXML
	private TextField accountExpiryField;

	//Confirm Payment 
	@FXML
	private Button confirmPaymentButton;   

	private Payment payment;

	//paymentType choice box
	ObservableList<String> paymentTypeList = FXCollections
			.observableArrayList("Visa", "MasterCard", "American Express");
	
	//method to initialise elements
	@FXML
	private void initialize(){    
		paymentTypeChoiceBox.setItems(paymentTypeList);
	}

	public void setObject(Object o){
		this.payment = (Payment) o;
		this.accountOwnerNameField.setText(payment.getAccountOwnerName());
		this.paymentAccountField.setText(payment.getPaymentAccount());
		this.amountDueLabel.setText("$" + Double.toString(payment.getPaymentAmount()));
                //this.paymentDateLabel.setText(payment.getPaymentDate(Calendar.getInstance())); 
                
		// TODO: decide whether to remove later
		//this.accountExpiryField.setText(date.toString(agreementPayment.getAccountExpiry()));      
	}


	//when user clicks "Confirm Payment"
	@FXML
	private void handleConfirmPaymentButton() throws InvalidInputException, SQLException, ClassNotFoundException { 
		//(1)Check for valid input
		try {        
			AgreementPayment.validateInput(
					this.accountOwnerNameField.getText(),
					this.paymentAccountField.getText(),
					this.accountExpiryField.getText());

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

		//(2) modify Payment fields if valid input entered
		payment.setAccountOwnerName(this.accountOwnerNameField.getText());
		payment.setPaymentAccount(this.paymentAccountField.getText());
		payment.setPaymentType(this.paymentTypeChoiceBox.getValue());
                payment.setAccountExpiry(this.accountExpiryField.getText());
			

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
 