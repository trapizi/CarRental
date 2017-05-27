/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.Time;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mainApp.LizMain;
import model.Consultation;
import model.ConsultationPayment;

/**
 *
 * @author elizabeth
 */
public class PastConsultationController extends ControllerBase {
    @FXML
    private TableView pastConsultationTable;
    
    @FXML 
    private TableColumn<ConsultationPayment, Integer> consultPayment_idColumn; 
    
    @FXML
    private TableColumn<Consultation, Float> consultPriceColumn;
    
    @FXML
    private TableColumn<Consultation, Date> consultationDateColumn;
    
    @FXML
    private TableColumn<Consultation, Time> consultationTimeColumn;
    
    @FXML
    private TableColumn<Consultation, Integer> consultNumColumn;
    
    //list to display
    ///////////DO THIS
    
    //reference mainApp
    private LizMain mainApp;

    //initialize elements
    private void initialize(){
        consultPayment_idColumn.setCellValueFactory(cellData -> cellData.getValue().consultationPayment_idProperty().asObject());
        consultPriceColumn.setCellValueFactory(cellData -> cellData.getValue().consultationPriceProperty().asObject());
        consultationDateColumn.setCellValueFactory(cellData -> cellData.getValue().consultationDateProperty());
        consultationTimeColumn.setCellValueFactory(cellData -> cellData.getValue().consultationTimeProperty());
        consultNumColumn.setCellValueFactory(cellData -> cellData.getValue().consultationNumProperty().asObject());
    }
    
    public void setMainApp(LizMain mainApp) {
        this.mainApp = mainApp;
    }
}