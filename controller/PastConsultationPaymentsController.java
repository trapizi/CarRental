package controller;

import java.sql.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell; 
import javafx.scene.control.TableColumn; 
import javafx.scene.control.TableView;
import model.ConsultationPayment;
import model.ConsultationPaymentDAO;
import mainApp.SelenaMain;

/**
 *
 * @author selena
 */
public class PastConsultationPaymentsController {
    
    @FXML
    private TableView<ConsultationPayment> consultationPaymentTable;
    
    @FXML 
    private TableColumn<ConsultationPayment, Integer> consultationPayment_idColumn; 
    
    @FXML
    private TableColumn<ConsultationPayment, Float> paymentAmountColumn;
    
    @FXML
    private TableColumn<ConsultationPayment, Date> paymentDateColumn;
    
    @FXML
    private TableColumn<ConsultationPayment, String> accountOwnerNameColumn;
    
    @FXML
    private TableColumn<ConsultationPayment, String> paymentTypeColumn;
   
    @FXML
    private TableColumn<ConsultationPayment, String> paymentAccountColumn;   

    @FXML
    private TableColumn<ConsultationPayment, Date> accountExpiryColumn;
    
    
    //list to display
    private ObservableList<ConsultationPayment> consultationPaymentList;
    private ConsultationPaymentDAO consultationPaymentDAO; 
    
    
    //method to initialise elements
    @FXML
    private void initialize(){
        consultationPayment_idColumn.setCellValueFactory(cellData -> cellData.getValue().consultationPayment_idProperty().asObject());
        paymentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAmountProperty().asObject());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());
        accountOwnerNameColumn.setCellValueFactory(cellData -> cellData.getValue().accountOwnerNameProperty());
        paymentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty());
        paymentAccountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAccountProperty());
        accountExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty());
    
        // We must tell the cell how we want dates to be displayed
    	// section below adapted from http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/
    	this.accountExpiryColumn.setCellFactory(column -> { 
    		return new TableCell<ConsultationPayment, java.sql.Date>() {
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
    	
    }
    
    public void setMainApp(SelenaMain mainApp){
        this.mainApp = mainApp; 
    }
} 
