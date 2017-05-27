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
import model.Offer;
import util.AlertBuilder;
import util.InvalidInputException;

public class OfferEditController extends EditControllerBase {
	
	@FXML
	private TextField brandTextField;
	@FXML
	private TextField modelTextField;
	@FXML
	private TextField carTypeTextField;
	@FXML
	private TextField seatsTextField;
	@FXML
	private TextField transmissionTextField;
	@FXML
	private TextField fuelTypeTextField;
	@FXML
	private TextField postcodeTextField;
	@FXML
	private TextField priceTextField;
	@FXML
	private TextField driveDayTextField;
	
	private Offer offer;
	
	@FXML
	private void initialize() {
	}
	
	
	public void setObject(Object o) {
		this.offer= (Offer) o;
		this.brandTextField.setText(offer.getBrand());
		this.modelTextField.setText(offer.getModel());
		this.carTypeTextField.setText(offer.getCarType());
		this.seatsTextField.setText(Integer.toString(offer.getSeats()));
		this.transmissionTextField.setText(offer.getTransmission());
		this.fuelTypeTextField.setText(offer.getFuelType());
		this.postcodeTextField.setText(Long.toString(offer.getPostcode()));
		this.priceTextField.setText(Double.toString(offer.getPrice()));
		
		try{
			this.driveDayTextField.setText(offer.getDriveDay().toString());
		} catch (NullPointerException e) {
			this.driveDayTextField.setText("");
		}
	}
	
	@FXML
	private void handleOk() throws InvalidInputException, SQLException, ClassNotFoundException{
		
		
		// Check for valid input
		try {
			Offer.validateInput(
					this.brandTextField.getText(), this.modelTextField.getText(), this.carTypeTextField.getText(),
					this.seatsTextField.getText(), this.transmissionTextField.getText(), this.fuelTypeTextField.getText(),
					this.postcodeTextField.getText(), this.priceTextField.getText(), this.driveDayTextField.getText()
					);

		} catch (InvalidInputException e) {
			Alert alert = AlertBuilder.createAlert(AlertType.WARNING, dialogStage, "Invalid Input", "Invalid input entered!", e.getMessage());
			
			alert.showAndWait();
			throw e;
		} catch (SQLException | ClassNotFoundException e) {
			Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, dialogStage, "Database Error", "Database could not complete query", e.getMessage()); 
            
            alert.showAndWait();
            throw e;
		}
		
		offer.setBrand(this.brandTextField.getText());
		offer.setModel(this.modelTextField.getText());
		offer.setCarType(this.carTypeTextField.getText());
		offer.setSeats(Integer.parseInt(this.seatsTextField.getText()));
		offer.setTransmission(this.transmissionTextField.getText());
		offer.setFuelType(this.fuelTypeTextField.getText());
		offer.setPostcode(Long.parseLong(this.postcodeTextField.getText()));
		offer.setPrice(Float.parseFloat(this.priceTextField.getText()));
		offer.setDriveDay(this.driveDayTextField.getText());
		
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
}
