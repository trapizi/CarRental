package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Agreement;
import model.AgreementDAO;
import model.Staff;
import model.StaffDAO;
import util.AlertBuilder;

public class AgreementController {
	
	@FXML
	private TableView agreementTable;
	@FXML
	private TableColumn<Agreement, Integer> memberColumn;
	@FXML
	private TableColumn<Agreement, Date> dateColumn;
	@FXML
	private TableColumn<Agreement, Long> postcodeToColumn;
	@FXML
	private TableColumn<Agreement, Long> postcodeFromColumn;
	@FXML
	private TableColumn<Agreement, Float> priceColumn;
	
	 // list to display onto the UI's table
    private ObservableList<Agreement> agmtList;
    private AgreementDAO agmtDAO;
	
	@FXML
	private void initialize () {
		memberColumn.setCellValueFactory(cellData -> cellData.getValue().offererIDProperty().asObject());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().agreeDateProperty());
		postcodeToColumn.setCellValueFactory(cellData -> cellData.getValue().toPostcodeProperty().asObject());
		postcodeFromColumn.setCellValueFactory(cellData -> cellData.getValue().fromPostcodeProperty().asObject());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().payAmtProperty().asObject());       
   
		this.agmtList = FXCollections.observableArrayList();
        this.agmtDAO = new AgreementDAO();
	}
	
	 @FXML
    private void search() throws SQLException, ClassNotFoundException {
    	try {
    		agmtList = this.agmtDAO.findAll();
    		
    		// display results in the table
    		agreementTable.setItems(agmtList);
    		
    	} catch (SQLException e) {            
            // Create and display alert for the database exception
         /*   Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
            		"Database could not complete search!", e.getMessage()); 
            
            alert.showAndWait();
          */
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    /*private void fillEmployeeTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Employee>> task = new Task<List<Employee>>(){
            @Override
            public ObservableList<Employee> call() throws Exception{
                return EmployeeDAO.searchEmployees();
            }
        };

        task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> employeeTable.setItems((ObservableList<Employee>) task.getValue()));
        exec.execute(task);
    }*/
	
}
