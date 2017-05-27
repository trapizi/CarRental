/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.DriverManager;
import java.sql.Time;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Consultation;
import model.ConsultationDAO;
import model.Corporate;
import model.CorporateMember;
import util.DBTablePrinter;

/**
 *
 * @author elizabeth
 */
public class RequestConsultationController extends ControllerBase {
    
    ObservableList<String> timePickerList = FXCollections.observableArrayList(
            "10:00AM","11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM");
    

    @FXML
    private Label welcomeCorpLabel;
    private final String profileViewFileName;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField dateText;
    @FXML
    private ChoiceBox timePicker;
    @FXML
    private TextField enterCompanyID; 
    
    private CorporateMember corporateMember;
    private Corporate corporate;

    public RequestConsultationController() {
        this.profileViewFileName = "CorporateMemberProfile.fxml";
    }
    
        @FXML
    private void initialize() {				
		this.priceLabel.setText("$" + "100.00");
		this.timePicker.setItems(timePickerList);
	    this.timePicker.setValue("12:00PM");
    }
     
    @FXML
    private void handleMakePayment() {
    	//boolean okClicked = mainApp.showEditDialog(new MembershipPayment(), viewFileName)
        //mainApp.showView("PaymentView.fxml");
    	ConsultationDAO cDao = new ConsultationDAO();
    	Consultation consult1 = new Consultation();
    	//consult1.setCorporateID(1);
    	consult1.setConsultationPrice(100.00f);
    	//consult1.setConsultationTime((Time) this.timePicker.getValue());
    	consult1.setConsultationDate(this.dateText.getText());
    	consult1.setCorporateID(Integer.parseInt(this.enterCompanyID.getText()));
    	
    	try {
    		final String url = "jdbc:derby:DBforDEMO;create=true";
    		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
    		
        	cDao.insert(consult1);

    		//final String url = "jdbc:derby:DBforDEMO;create=true";
    		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");
    		
    	} catch (Exception e) {
    		
    	}
    }
}
