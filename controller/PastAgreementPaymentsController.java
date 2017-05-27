package controller;

import java.sql.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.AgreementPayment;
import model.AgreementPaymentDAO;
import mainApp.SelenaMain;

/**
 *
 * @author selena
 */
public class PastAgreementPaymentsController {
    
    @FXML
    private TableView<AgreementPayment> agreementPaymentTable;
    
    @FXML 
    private TableColumn<AgreementPayment, Integer> agreementPayment_idColumn; 
     
    @FXML
    private TableColumn<AgreementPayment, Float> paymentAmountColumn;
    
    @FXML
    private TableColumn<AgreementPayment, Date> paymentDateColumn;
    
    @FXML
    private TableColumn<AgreementPayment, Long> accountOwnerNameColumn;
    
    @FXML
    private TableColumn<AgreementPayment, Long> paymentTypeColumn;
   
    @FXML
    private TableColumn<AgreementPayment, Long> paymentAccountColumn;   

    @FXML
    private TableColumn<AgreementPayment, Date> accountExpiryColumn;
    
    
    //list to display
    private ObservableList<AgreementPayment> agreementPaymentList;
    private AgreementPaymentDAO agreementPaymentDAO;
    
    
    //method to initialise elements
    @FXML
    private void initialize(){
        agreementPayment_idColumn.setCellValueFactory(cellData -> cellData.getValue().agreementPayment_idProperty().asObject());
        paymentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAmountProperty().asObject());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());
        accountOwnerNameColumn.setCellValueFactory(cellData -> cellData.getValue().accountOwnerNameProperty();
        paymentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty().asObject());
        paymentAccountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAccountProperty();
        accountExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty().asObject());
    
        // We must tell the cell how we want dates to be displayed
    	// section below adapted from http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/
    	this.accountExpiryColumn.setCellFactory(column -> {
    		return new TableCell<AgreementPayment, java.sql.Date>() {
    			@Override
    			protected void updateItem(java.sql.Date item, boolean empty) {    			
    	            super.updateItem(item, empty);

    	            if (item == null || empty) {
    	            	this.setText(null);
    	            } else {
    	            	// TODO: default format is yyyy-MM-dd -- use a date formatter to change if required
    	                this.setText(item.toString());
    	            }
    			}
    		};
    	});
    	
    	showMemberDetails(null);
    
    }
    
    public void setMainApp(SelenaMain mainApp){
        this.mainApp = mainApp; 
    }
} 
