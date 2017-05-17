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

public class AgreementController {
	
	@FXML
	private TableView agreementTable;
	@FXML
	private TableColumn<Agreement, Integer> memberColumn;
	@FXML
	private TableColumn<Agreement, Date> dateColumn;
	@FXML
	private TableColumn<Agreement, Long> postcodeColumn;
	@FXML
	private TableColumn<Agreement, String> priceColumn;
	
	@FXML
	private void initialize () {
		memberColumn.setCellValueFactory(cellData -> cellData.getValue().offererIDProperty().asObject());
		this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().agreeDateProperty());
		postcodeColumn.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty().asObject());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().payAmtProperty());       
    }
	
	//Search an employee
    @FXML
    private void viewAgreements (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
    	Agreement agmt1 = new Agreement();
    	/*  try {
            //Get Employee information
            //Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }*/
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
