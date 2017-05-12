package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Agreement;
import model.AgreementDAO;
import model.Member;
import model.Agreement;
import model.Adjustment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AgreementController {
	
	//private TextArea agmtResults;
	//private TextArea pAgmtResults;
	private TableColumn<Member, Integer> agmtMemberColumn;
	private TableColumn<Agreement, String> agmtDateColumn;
	private TableColumn<Agreement, Integer> agmtPostcodeColumn;
	private TableColumn<Agreement, Integer> agmtCostColumn;
	private TableColumn<Agreement, Integer> pAgmtMemberColumn;
	private TableColumn<Agreement, String> pAgmtDateColumn;
	private TableColumn<Agreement, Integer> pAgmtPostcodeColumn;
	private TableColumn<Agreement, Integer> pAgmtCostColumn;
	
/*	private void initialize () {

		//agmtMemberColumn.setCellValueFactory(cellData -> cellData.getValue().asObject());
		agmtDateColumn.setCellValueFactory(cellData -> cellData.getValue().getAgreeDate());
		//agmtPostcodeColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		agmtCostColumn.setCellValueFactory(cellData -> cellData.getValue().getPayAmt());
       
    }
*/	
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
