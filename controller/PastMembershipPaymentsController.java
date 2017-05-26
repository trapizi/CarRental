package controller;

import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.MembershipPayment;
import model.MembershipPaymentDAO;
import mainApp.SelenaMain; 

/**
 *
 * @author selena
 */
public class PastMembershipPaymentsController {
    
    @FXML
    private TableView<MembershipPayment> membershipPaymentTable;
    
    @FXML 
    private TableColumn<MembershipPayment, Integer> membershipPayment_idColumn; 
     
    @FXML
    private TableColumn<MembershipPayment, Float> paymentAmountColumn;
    
    @FXML
    private TableColumn<MembershipPayment, Date> paymentDateColumn;
    
    @FXML
    private TableColumn<MembershipPayment, Long> accountOwnerNameColumn;
    
    @FXML
    private TableColumn<MembershipPayment, Long> paymentTypeColumn;
   
    @FXML
    private TableColumn<MembershipPayment, Long> paymentAccountColumn;   

    @FXML
    private TableColumn<MembershipPayment, Date> accountExpiryColumn;
    
    @FXML
    private TableColumn<MembershipPayment, Integer> durationToExpiryColumn;
    
    @FXML
    private TableColumn<MembershipPayment, Boolean> refundFlagColumn;
    
    
    
    
    //list to display
    private ObservableList<MembershipPayment> membershipPaymentList;
    private MembershipPaymentDAO membershipPaymentDAO;
    
    
    //method to initialise elements
    @FXML
    private void initialize(){
        membershipPayment_idColumn.setCellValueFactory(cellData -> cellData.getValue().membershipPayment_idProperty().asObject());
        paymentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAmountProperty().asObject());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty();
        accountOwnerNameColumn.setCellValueFactory(cellData -> cellData.getValue().accountOwnerNameProperty().asObject());
        paymentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty().asObject());
        paymentAccountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAccountProperty().asObject());
        accountExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty();
        durationToExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().durationToExpiryProperty().asObject());
        refundFlagColumn.setCellValueFactory(cellData -> cellData.getValue().refundFlagProperty().asObject());
        
        // We must tell the cell how we want dates to be displayed
    	// section below adapted from http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/
    	this.accountExpiryDateColumn.setCellFactory(column -> {
    		return new TableCell<MembershipPayment, java.sql.Date>() {
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
