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
import mainApp.SarinaMain;
import model.Agreement;
import model.AgreementDAO;
import util.AlertBuilder;

public class AgreementInvoiceController extends AgreementControllerBase {

	@FXML
	private Label agmtIDLabel;
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

	@FXML
	private void initialize() throws SQLException {
		AgreementDAO aDAO = new AgreementDAO();
		Agreement a = aDAO.findById(2);
		showAgreementInvoice(a);
	}

	private void showAgreementInvoice(Agreement agmt) {

		agmtIDLabel.setText(Integer.toString(agmt.getAgreement_id()));

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
	}
	


}
