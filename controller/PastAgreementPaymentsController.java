package controller;

import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private TableView paymentsTable;
    
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
    
    
    //reference mainApp
    private SelenaMain mainApp;
    
    
    //method to initialise elements
    @FXML
    private void initialize(){
        agreementPayment_idColumn.setCellValueFactory(cellData -> cellData.getValue().agreementPayment_idProperty().asObject());
        paymentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAmountProperty().asObject());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty();
        accountOwnerNameColumn.setCellValueFactory(cellData -> cellData.getValue().accountOwnerNameProperty().asObject());
        paymentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty().asObject());
        paymentAccountColumn.setCellValueFactory(cellData -> cellData.getValue().paymentAccountProperty().asObject());
        accountExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty();
    
    
    
    }
    
    public void setMainApp(SelenaMain mainApp){
        this.mainApp = mainApp; 
    }
}
