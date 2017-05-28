package controller;

import java.awt.Label;

import model.Consultation;

public class ConsultationInvoiceController  extends ControllerBase {
	
	@FXML 
	private Label consultationNumLabel;
	@FXML
	private Label paymentIDLabel;
	@FXML
	private Label dateCreatedLabel;
	@FXML
	private Label consultationPriceLabel;
	@FXML
	private Label corporateIDLabel;
	
	private Consultation consultation;
	private int paymentID;
	
	
	@FXML
	private void initialize() throws SQLException, ClassNotFoundException {
		showConsultationInvoice(this.consultation);
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
		
	}
	
	//show all details of invoice 
	
	private void showConsultationInvoice(Consultation consultation) {
		consultationNumLabel.setText(Integer.toString(this.consultationNum));
	}