package controller;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Agreement;
import model.Member;
import util.AlertBuilder;
import util.InvalidInputException;

/**
 * @author Sarina Lee (z5020069)
 */
public class StaffEditAgreementController extends EditControllerBase {

	@FXML
	private TextField seekerTextField;
	@FXML
	private TextField offererTextField;
	@FXML
	private TextField dateTextField;
	@FXML
	private TextField pickupTextField;
	@FXML
	private TextField destinationTextField;
	@FXML
	private TextField priceTextField;

	private Agreement agmt;

	@FXML
	private void initialize() {
	}

	@Override
	public void setObject(Object o) {
		this.agmt= (Agreement) o;
		this.seekerTextField.setText(Integer.toString(agmt.getSeeker()));
		this.offererTextField.setText(Integer.toString(agmt.getOfferer()));

		// handle case where default constructor is called and we .toString() it
		try {
			this.dateTextField.setText(agmt.getAgreeDate().toString());	
		} catch (NullPointerException e) {
			this.dateTextField.setText("");
		}

		this.pickupTextField.setText(Long.toString(agmt.getFromPostcode()));
		this.destinationTextField.setText(Long.toString(agmt.getToPostcode()));
		this.priceTextField.setText(Float.toString(agmt.getPayAmt()));
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() throws InvalidInputException, SQLException, ClassNotFoundException {

		// check for valid input here
		try {
			Agreement.validateInput(
					this.seekerTextField.getText(), this.offererTextField.getText(), this.dateTextField.getText(), 
					this.pickupTextField.getText(), this.destinationTextField.getText(), this.priceTextField.getText());
		} catch (InvalidInputException e) {  

			// Create and display alert for incorrect input
			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, dialogStage, "Invalid Input", "Invalid input entered!", e.getMessage()); 

			alert.showAndWait();

			// throw exception as well for debugging purposes
			throw e;
		} catch (SQLException | ClassNotFoundException e) {

			// Create and display alert for database related exceptions
			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, dialogStage, "Database Error", "Database could not complete query", e.getMessage()); 

			alert.showAndWait();

			throw e;
		} 

		// set agreement fields if a valid input is entered    
		agmt.setSeeker(Integer.parseInt(this.seekerTextField.getText()));
		agmt.setOfferer(Integer.parseInt(this.offererTextField.getText()));
		agmt.setAgreeDate(this.dateTextField.getText());
		agmt.setFromPostcode(Long.parseLong(this.pickupTextField.getText()));
		agmt.setToPostcode(Long.parseLong(this.destinationTextField.getText()));
		agmt.setPayAmt(Float.parseFloat(this.priceTextField.getText()));

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
