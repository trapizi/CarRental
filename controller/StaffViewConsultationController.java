/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import mainApp.LizMain;
import model.Consultation;
import model.ConsultationDAO;
import model.ConsultationPayment;
import util.AlertBuilder;
import util.DBTablePrinter;

/**
 *
 * @author elizabeth
 */
public class StaffViewConsultationController extends ControllerBase {
    @FXML
    private TableView ConsultationTable;
    @FXML
    private TableColumn<Consultation, Integer> consultNumColumn;    
    @FXML
    private TableColumn<Consultation, Float> consultPriceColumn;
    
    @FXML
    private TableColumn<Consultation, Date> consultationDateColumn;
    
    @FXML
    private TableColumn<Consultation, Time> consultationTimeColumn;
    
    @FXML
    private TableColumn<Consultation, Integer> corporateIDColumn;
   
    private ObservableList<Consultation> consultationList;
    private ConsultationDAO consultationDAO;
    
    //initialize elements
    @FXML
    private void initialize(){
        consultPriceColumn.setCellValueFactory(cellData -> cellData.getValue().consultationPriceProperty().asObject());
        consultationDateColumn.setCellValueFactory(cellData -> cellData.getValue().consultationDateProperty());
        consultationTimeColumn.setCellValueFactory(cellData -> cellData.getValue().consultationTimeProperty());
        consultNumColumn.setCellValueFactory(cellData -> cellData.getValue().consultationNumProperty().asObject());
        corporateIDColumn.setCellValueFactory(cellData -> cellData.getValue().corporateIDProperty().asObject());
        
        this.consultationList = FXCollections.observableArrayList();
        this.consultationDAO = new ConsultationDAO();

        this.searchAll();
        
        System.out.println("DONE SEARCHING\n");
    }
    
    @FXML
    private void searchAll() {
    	try {

    		final String url = "jdbc:derby:DBforDEMO;create=true";
    		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CONSULTATION");
    		
    		this.consultationList = this.consultationDAO.findAll();
    		
    		this.ConsultationTable.setItems(this.consultationList);
    	} catch (SQLException | ClassNotFoundException e) {            
            // Create and display alert for the database exception
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
            		"Database could not complete search!", e.getMessage()); 
            
            alert.showAndWait();            
    	}
    }  
}
