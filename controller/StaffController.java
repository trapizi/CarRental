package controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Staff;
import model.StaffDAO;

public class StaffController {
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneNoText;
    @FXML
    private TextField homeAddressText;
    @FXML
    private TextField salaryText;    
    @FXML
    private TextArea resultArea;
    
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, Integer>  IDColumn;
    @FXML
    private TableColumn<Staff, String>  userNameColumn;
    @FXML
    private TableColumn<Staff, String>  firstNameColumn;
    @FXML
    private TableColumn<Staff, String> lastNameColumn;
    @FXML
    private TableColumn<Staff, String> emailColumn;
    @FXML
    private TableColumn<Staff, Long> phoneNumberColumn;
    @FXML
    private TableColumn<Staff, String> addressColumn;
    @FXML
    private TableColumn<Staff, Double> salaryColumn;

    @FXML
    private void initialize () {    	
    	IDColumn.setCellValueFactory(cellData -> cellData.getValue().staff_idProperty().asObject());
    	userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
    	firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    	phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNoProperty().asObject());
    	addressColumn.setCellValueFactory(cellData -> cellData.getValue().homeAddressProperty());
    	salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
    }
    
    @FXML
    private void searchAll() {
    	try {
    		StaffDAO staffDAO = new StaffDAO();
    		ObservableList<Staff> list = staffDAO.findAll();
    		
    		staffTable.setItems(list);
    		
    	} catch (SQLException | ClassNotFoundException e) {
    		resultArea.setText("Problem searching all employees\n");
    	}
    }
    
    @FXML
    private void insertStaff(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {    	
    	/* construct staff object for insertion */
    	StaffDAO staffDAO = new StaffDAO();
    	boolean invalidInput = false;
    	
		/* clear text area for errors */
		resultArea.clear();
		
		/* check valid numbers for phone number and salary are entered */    	
    	if (!isValidPhoneNo(phoneNoText.getText())) {
    		invalidInput = true;
    	} else if (!isValidSalary(salaryText.getText())) {
    		invalidInput = true;
    	}
    	
    	/* construct staff object and try insert if valid data */
    	if (!invalidInput) {
    		
        	Staff staff = new Staff();
    		staff.setFirstName(firstNameText.getText());
    		staff.setUserName(userNameText.getText());
    		staff.setPassword(passwordText.getText());
    		staff.setLastName(lastNameText.getText());
    		staff.setEmail(emailText.getText());
    		staff.setHomeAddress(homeAddressText.getText());
    		staff.setPhoneNo(Long.parseLong(phoneNoText.getText()));
    		staff.setSalary(Double.parseDouble(salaryText.getText()));
    		
	        try {	   	
	            staffDAO.insert(staff);
	            resultArea.setText("Employee inserted! \n");
	        } catch (SQLException | ClassNotFoundException e) {
	            resultArea.setText("Problem occurred while inserting employee " + e);
	            throw e;
	        }
    	}
    }
    
    private boolean isValidPhoneNo(String phoneNoText) {    	
    	try {
    		Long.parseLong(phoneNoText);
    	} catch (NumberFormatException e) {
    		resultArea.appendText("Invalid phone number entered!\n");
    		return false;
    	}
    	    	
    	return true;
    }
    
    private boolean isValidSalary(String salaryText) {
    	try {
    		Double.parseDouble(salaryText);
    	} catch (NumberFormatException e) {
    		resultArea.appendText("Invalid salary entered!\n");
    		return false;
    	}
    	return true;
    }
}