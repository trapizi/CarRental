package controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Offer;
import util.AlertBuilder;
import util.InvalidInputException;

public class OfferEditController extends EditControllerBase{

	ObservableList<String> carTypeList = FXCollections
			.observableArrayList("Sedan", "Hatchback", "Ute");
	
	@FXML
	private TextField brandTextField;
	@FXML
	private TextField modelTextField;
	@FXML
	private ChoiceBox<String> carTypeChoiceBox;
	@FXML
	private ChoiceBox seatsChoiceBox;
	@FXML
	private ChoiceBox transmissionChoiceBox;
	@FXML
	private ChoiceBox fuelTypeChoiceBox;
	@FXML
	private TextField postcodeTextField;
	@FXML
	private ChoiceBox priceChoiceBox;
	
	private Offer offer;
	
	@FXML
	private void initialize() {
		carTypeChoiceBox.setValue("Sedan");
		carTypeChoiceBox.setItems(carTypeList);
	}
	
	@Override
	public void setObject(Object o) {
		this.offer= (Offer) o;
		this.brandTextField.setText(offer.getBrand());
		this.modelTextField.setText(offer.getModel());
		
		
	}
}
