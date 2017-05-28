/**
 * @author Xuan Huy Ngo z5076470
 * 
 * This controller is for Members to create, edit and delete their offers
 */

package controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Agreement;
import model.AgreementDAO;
import model.AgreementPayment;
import model.AgreementPaymentDAO;
import model.Member;
import model.MemberDAO;
import model.Offer;
import model.OfferDAO;
import model.Seek;

import util.AlertBuilder;
import util.DBTablePrinter;
import util.InvalidInputException;
import mainApp.SUber;
import mainApp.Stevenmain;

public class OfferViewController2 extends ControllerBase {

	@FXML
	private Label resultText;
	@FXML
	private TableView<Offer> offerTable;
	@FXML
	private TableColumn<Offer, String> availableCarColumn;
	@FXML
	private TableColumn<Offer, Long> postcodeColumn;	    
	@FXML
	private TableColumn<Offer, Date> driveDayColumn;

	@FXML
	private Label brandLabel;
	@FXML
	private Label modelLabel;
	@FXML
	private Label carTypeLabel;
	@FXML
	private Label seatsLabel;
	@FXML
	private Label transmissionLabel;
	@FXML
	private Label fuelTypeLabel;
	@FXML
	private Label postcodeLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label driveDayLabel;
	@FXML
	private TextField filterField;
	@FXML
	private TextField destinationField;
	


	private ObservableList<Offer> offerList = FXCollections.observableArrayList();
	private OfferDAO offerDAO;

	//public static String locationTo; 

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * @throws Exception 
	 */
	@FXML
	private void initialize() throws Exception{

		// Initialize the person table with the 3 columns.
		availableCarColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		postcodeColumn.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty().asObject());
		driveDayColumn.setCellValueFactory(cellData -> cellData.getValue().driveDayProperty());
		this.offerDAO = new OfferDAO();
		showOfferDetails(null);

		//Check which row is being selected
		this.offerTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showOfferDetails((Offer) newValue));

		this.offerList = FXCollections.observableArrayList();

	}

	//This function shows the currently logged in Offerer's Offers
	@FXML
	private void fetchOffers() throws SQLException, ClassNotFoundException, Exception {
		try {
			MemberDAO mDAO = new MemberDAO();
			Member m = mDAO.findByUserName(((Member) (this.mainApp.getLoggedInAs())).getUserName()); 
			offerList = this.offerDAO.findMyOffer(m.getMemberID());
			
			offerTable.setItems(offerList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//Show offer detail on the right side of the UI
	private void showOfferDetails(Offer offer) {
		if (offer != null) {
			brandLabel.setText(offer.getBrand());
			modelLabel.setText(offer.getModel());
			carTypeLabel.setText(offer.getCarType());
			seatsLabel.setText(Integer.toString(offer.getSeats()));
			transmissionLabel.setText(offer.getTransmission());
			fuelTypeLabel.setText(offer.getFuelType());
			postcodeLabel.setText(Long.toString(offer.getPostcode()));
			
		} else {
			brandLabel.setText("");
			modelLabel.setText("");
			carTypeLabel.setText("");
			seatsLabel.setText("");
			transmissionLabel.setText("");
			fuelTypeLabel.setText("");
			priceLabel.setText("");
			postcodeLabel.setText("");
		}
	}	    

	//Add new offer to the OFFER table
	@FXML
	private void handleNewOffer() throws Exception{
		Offer tempOffer = new Offer();

		if (mainApp == null) {
			System.out.println("MY MAIN APP IS NULL\n");
		}

		boolean okClicked = mainApp.showEditDialog(tempOffer, "OfferEdit.fxml");

		//set valid memberID in offer
		MemberDAO memberDAO = new MemberDAO();
		tempOffer.setMemberID(memberDAO.findByUserName(this.mainApp.getLoggedInAs().getUserName()).getMemberID());

		if (okClicked) {
			try{
				//add new offer to the list
				offerDAO.insert(tempOffer);


				tempOffer = offerDAO.findById(tempOffer.getOfferID());

				//ensure offerID gets updated on the offer details section after insert
				offerList.add(tempOffer);

				resultText.setText("Insert complete! \n");
				this.fetchOffers();
			} catch (SQLException | ClassNotFoundException e) {
				Alert alert = AlertBuilder.createAlert(
						AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
						"Database could not complete search!", e.getMessage());
				alert.showAndWait();
				throw e;
			}
		}
	}

	//Delete offer function
	@FXML
	private void deleteOffer() throws SQLException, ClassNotFoundException{
		try {
			int selectedIndex = offerTable.getSelectionModel().getSelectedIndex();

			try {
				OfferDAO offerDAO = new OfferDAO();

				//Delete the selected offer from the database
				offerDAO.delete("OFFER_ID=" + ((Offer)offerTable.getItems().get(selectedIndex)).getOfferID());

				resultText.setText("Delete complete!\n");
			} catch (SQLException | ClassNotFoundException e) {
				resultText.setText("Problem deleting selected offer from database!\n");
				throw e;
			}
			offerTable.getItems().remove(selectedIndex);

		} catch (ArrayIndexOutOfBoundsException e) {

			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Offer Selected", "Select an Offer in the table");

			alert.showAndWait();
		}

	}
	//This function is used to edit an offer
	@FXML
	private void handleEditOffer() {
		resultText.setText("Editing...!\n");

		try {
			Offer selectedOffer = (Offer)offerTable.getSelectionModel().getSelectedItem();
			boolean okClicked = mainApp.showEditDialog(selectedOffer, "OfferEdit.fxml");

			if (okClicked) {

				try{
					offerDAO.update(selectedOffer);
				} catch (Exception e) {
					resultText.setText("Failed to update offer!\n");
				}
			}
			resultText.setText("Offer udpated!\n");
		} catch (NullPointerException e) {
			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Offer Selected", "Select an Offer in the table");

			alert.showAndWait();

			System.out.println("COULD NOT EDIT -- PLEASE SELECT AN OFFER");
		}
	}
}