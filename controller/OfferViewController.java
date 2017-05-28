/**
 * @author Xuan Huy Ngo z5076470
 */

package controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


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

public class OfferViewController extends ControllerBase {

	@FXML
	private Label resultText;
	@FXML
	private TableView<Offer> offerTable;
	@FXML
	private TableColumn<Offer, String> availableCarColumn;
	@FXML
	private TableColumn<Offer, Long> postcodeColumn;	    

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
	private TextField filterField;
	@FXML
	private TextField destinationField;

	private ObservableList<Offer> offerList = FXCollections.observableArrayList();
	private OfferDAO offerDAO;
	private Offer offer;

	// magic numbers
	private final float costPerPostcode = 8; 
	private final int maxPostcodeDifference = 10;

	private void setObject (Object o) {
		this.offer = (Offer) o;

	}

	//public static String locationTo; 

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@FXML
	private void initialize() throws ClassNotFoundException, SQLException{

		// Initialize the person table with the 3 columns.
		availableCarColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());

		postcodeColumn.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty().asObject());

		this.offerDAO = new OfferDAO();

		showOfferDetails(null);

		//Check which row is being selected
		this.offerTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showOfferDetails((Offer) newValue));

		this.offerList = FXCollections.observableArrayList();

	}

	//This function is used to refresh table when initiate UI and when a record is created or edited.
	@FXML
	private void refreshTable() throws SQLException, ClassNotFoundException {
		try {
			offerList = this.offerDAO.findAll();

			offerTable.setItems(offerList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/**
	 * Search nearby cars within postcode +- 2 range.
	 */

	//This function promts the input postcode to search for nearby cars in the range between (postcode-1) AND (postcode+1)
	@FXML
	private void searchPostcode() throws SQLException, ClassNotFoundException, Exception {	    	
		try {
			offerList = this.offerDAO.findByPostcode(Long.parseLong((this.filterField.getCharacters()).toString()));

			if (this.destinationField.getText().isEmpty()) {
				throw new NullPointerException("Destination Field is null!");
			} else if (Math.abs(Integer.parseInt(this.destinationField.getText()) - Integer.parseInt(this.filterField.getText())) > this.maxPostcodeDifference)  {
				throw new InvalidInputException("Destination is too far!");
			} else {
				// only display offer list when both fields not empty
				offerTable.setItems(offerList);
			}

		} catch (Exception e) {
			Alert alert = AlertBuilder.createAlert(AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "Invalid Input", e.getMessage()); 

			alert.showAndWait();

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

			// each unit in the postcode costs $8 on top of minimum of $8
			Float price = this.costPerPostcode;

			try {
				// add to price based on postcode difference
				price += Math.abs(((Integer.parseInt(this.destinationField.getText().toString()) - Integer.parseInt(this.filterField.getText().toString())) * this.costPerPostcode));
				this.priceLabel.setText("$" + price.toString());
				offer.setPrice(price);

				// TODO: fix this from popping up in offer page
			} catch (NullPointerException e) {	            	
				Alert alert = AlertBuilder.createAlert(AlertType.WARNING, mainApp.getPrimaryStage(), "No Destination", "No Destination Selected", "Select a destination."); 

				alert.showAndWait();
			} catch (NumberFormatException e) {	 
				// TODO: remove dodgy shit
				Alert alert = AlertBuilder.createAlert(AlertType.WARNING, mainApp.getPrimaryStage(), "Error", "Invalid Destination", "Enter a valid destination."); 

				alert.showAndWait();
				e.printStackTrace();
			}

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
	private void handleNewOffer() throws SQLException, ClassNotFoundException{
		Offer tempOffer = new Offer();

		if (mainApp == null) {
			System.out.println("MY MAIN APP IS NULL\n");
		}

		boolean okClicked = mainApp.showEditDialog(tempOffer, "OfferEdit.fxml");

		// set valid memberID in offer
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
				this.refreshTable();
			} catch (SQLException | ClassNotFoundException e) {
				Alert alert = AlertBuilder.createAlert(
						AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
						"Database could not complete search!", e.getMessage());
				alert.showAndWait();
				throw e;
			}
		}
	}

	//Delete Function
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

	@FXML
	private Offer extractOffer() throws SQLException, ClassNotFoundException {
		try {
			int selectedIndex = offerTable.getSelectionModel().getSelectedIndex();

			return offerTable.getItems().get(selectedIndex);
		} catch (ArrayIndexOutOfBoundsException e) {

			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Offer Selected", "Select an Offer in the table");

			alert.showAndWait();
		}

		return null;
	}

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


	/**
	 * View changing functions
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void handleBook() throws SQLException, ClassNotFoundException {
		AgreementPaymentDAO apDAO = new AgreementPaymentDAO();

		Agreement agmt = new Agreement();
		AgreementDAO agreeDAO = new AgreementDAO();

		MemberDAO mDAO = new MemberDAO();
		Member m = mDAO.findByUserName(this.mainApp.getLoggedInAs().getUserName());

		System.out.println("------------ " + m.getMemberID() + " --------------");

		int selectedIndex = offerTable.getSelectionModel().getSelectedIndex();
		Offer o = offerTable.getItems().get(selectedIndex);

		AgreementPayment agreementPayment = new AgreementPayment();
		agreementPayment.setPaymentAmount(o.getPrice());

		boolean validPayment = this.mainApp.showEditDialog(agreementPayment, LoginController.PAYMENT_PAGE);

		if (validPayment) {
			agmt.setSeeker(m.getMemberID());
			agmt.setOfferer(o.getMemberID());
			agmt.setAgreeDate(o.getDriveDay());
			agmt.setFromPostcode(Long.parseLong(this.filterField.getText()));
			agmt.setToPostcode(Long.parseLong(this.destinationField.getText()));
			agmt.setPayAmt(o.getPrice());

			// get today's date
			Date date = Date.valueOf(LocalDate.now());
			agmt.setCreateDay(date);

			// insert agreementPayment into database
			try {
				apDAO.insert(agreementPayment);

				final String url = "jdbc:derby:DBforDEMO;create=true";
				DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT_PAYMENT");

			} catch (SQLException | ClassNotFoundException e) {	        	
				// Create and display alert for the database exception
				Alert alert = AlertBuilder.createAlert(
						AlertType.WARNING, mainApp.getPrimaryStage(), "Payment insertion error", 
						"Could not complete agreement!", e.getMessage()); 

				alert.showAndWait();	   
			}

			// insert agreement into database
			try {
				agreeDAO.insert(agmt);

			} catch (SQLException | ClassNotFoundException e) {	        	
				// Create and display alert for the database exception
				Alert alert = AlertBuilder.createAlert(
						AlertType.WARNING, mainApp.getPrimaryStage(), "Agreement insertion error", 
						"Could not complete agreement!", e.getMessage()); 

				alert.showAndWait();	   
			}



			// setup and show invoice page
			AgreementInvoiceController aic = new AgreementInvoiceController();
			aic.setPaymentID(apDAO.findMostRecent().getAgreementPayment_id());
			aic.setAgreement(agmt);			

			System.out.println("PAYMENT ID = " + apDAO.findMostRecent().getAgreementPayment_id());


			mainApp.showView("AgreementInvoiceView.fxml", aic);

			final String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");
		}
	}
}