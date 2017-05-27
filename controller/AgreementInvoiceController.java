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
import model.Member;
import model.MemberDAO;
import util.AlertBuilder;

public class AgreementInvoiceController extends ControllerBase {

	@FXML
	private Label seekerLabel;
	@FXML
	private Label offererLabel;
	@FXML
	private Label paymentIDLabel;
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
	
	private Agreement agreement;

	@FXML
	private void initialize() throws SQLException, ClassNotFoundException {
		AgreementDAO aDAO = new AgreementDAO();
		Agreement a = new Agreement();
		MemberDAO mDAO = new MemberDAO();
		Member m = mDAO.findByUserName(this.mainApp.getLoggedInAs().getUserName());
		a.setSeeker(m.getMemberID());
		
		showAgreementInvoice(a);
	}
	
	private void setAgreement(Agreement agmt) {
		this.agreement = agmt;
	}

	private void showAgreementInvoice(Agreement agmt) {

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
	}
	


}
