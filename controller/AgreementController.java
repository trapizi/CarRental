package controller;

import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mainApp.SUber;
import model.Agreement;
import model.AgreementDAO;
import model.Member;
import util.AlertBuilder;

/**
 * @author Sarina Lee (z5020069)
 * Code skeleton adapted from http://code.makery.ch/library/javafx-8-tutorial
 */
public class AgreementController extends ControllerBase {

	@FXML
	private TableView<Agreement> agreementTable;
	@FXML
	private TableColumn<Agreement, Integer> seekerColumn;
	@FXML
	private TableColumn<Agreement, Integer> offererColumn;
	@FXML
	private TableColumn<Agreement, Date> dateColumn;
	@FXML
	private TableColumn<Agreement, Long> postcodeToColumn;
	@FXML
	private TableColumn<Agreement, Long> postcodeFromColumn;
	@FXML
	private TableColumn<Agreement, Float> priceColumn;

	@FXML
	private Label seekerLabel;
	@FXML
	private Label offererLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label locationFromLabel;
	@FXML
	private Label locationToLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label dayCreatedLabel;

	// list to display onto the UI's table
	private ObservableList<Agreement> agmtList;
	private AgreementDAO agmtDAO;

	// reference to mainApp 
	private SUber mainApp;

	/**
	 * Initialises columns in agreement table
	 */
	@FXML
	private void initialize () {
		seekerColumn.setCellValueFactory(cellData -> cellData.getValue().seekerIDProperty().asObject());
		offererColumn.setCellValueFactory(cellData -> cellData.getValue().offererIDProperty().asObject());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().agreeDateProperty());
		postcodeToColumn.setCellValueFactory(cellData -> cellData.getValue().toPostcodeProperty().asObject());
		postcodeFromColumn.setCellValueFactory(cellData -> cellData.getValue().fromPostcodeProperty().asObject());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().payAmtProperty().asObject());       

		// Clear agreement details
		showAgreementDetails(null);

		// Listen for selection changes and show the agreement details when changed.
		this.agreementTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showAgreementDetails((Agreement) newValue));

		this.agmtList = FXCollections.observableArrayList();
		this.agmtDAO = new AgreementDAO();
	}

	/**
	 * Called when user clicks on the search button
	 * displays a list of agreements in the agreement table UI
	 */
	@FXML
	private void search() {
		try {
			//finds a list of all agreements that the current member has
			Member currentMember = (Member) (this.mainApp.getLoggedInAs());
			agmtList = this.agmtDAO.findMemberAgreements(currentMember.getMemberID());

			// display results in the table
			agreementTable.setItems(agmtList);

		} catch (SQLException e) {            
			// Create and display alert for the database exception
			Alert alert = AlertBuilder.createAlert(
					AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
					"Database could not complete search!", e.getMessage()); 

			alert.showAndWait();
		}
	}

	/**
	 * Fills all text fields to show details about the agreement.
	 * If the specified agreement is null, all text fields are cleared.
	 */
	private void showAgreementDetails(Agreement agmt) {
		if (agmt != null) {

			seekerLabel.setText(Integer.toString(agmt.getSeeker()));
			offererLabel.setText(Integer.toString(agmt.getOfferer()));

			try {
				dateLabel.setText(agmt.getAgreeDate().toString());
			} catch (NullPointerException e) {
				dateLabel.setText("");
			}

			locationFromLabel.setText(Long.toString(agmt.getFromPostcode()));
			locationToLabel.setText(Long.toString(agmt.getToPostcode()));
			priceLabel.setText(Float.toString(agmt.getPayAmt()));

			try {
				dayCreatedLabel.setText(agmt.getCreateDay().toString());
			} catch (NullPointerException e) {
				dayCreatedLabel.setText("");
			}

		} else {
			seekerLabel.setText("");
			offererLabel.setText("");
			dateLabel.setText("");
			locationFromLabel.setText("");
			locationToLabel.setText("");
			priceLabel.setText("");
			dayCreatedLabel.setText("");
		}
	}

	public void setMainApp(SUber mainApp) {
		this.mainApp = mainApp;
	}
}
