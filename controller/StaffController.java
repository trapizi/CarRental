package controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mainApp.SUber;
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
    private TextArea resultArea;
    
    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, String>  firstNameColumn;
    @FXML
    private TableColumn<Staff, String> lastNameColumn;
    
    @FXML
    private Label staffIDLabel;
    @FXML
    private Label userNameLabel;    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;    
    @FXML
    private Label emailLabel;    
    @FXML
    private Label phoneNoLabel;
    @FXML
    private Label homeAddressLabel;
    
    // list of staff members
    private ObservableList<Staff> staffList;
    private StaffDAO staffDAO;
    
    // reference to mainApp for alerts
    private SUber mainApp;
    
    @FXML
    private void initialize () {    	
    	this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	
    	// clear labels on right side of UI
    	showStaffDetails(null);
    	
    	// listen to which row is being selected
        this.staffTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> showStaffDetails(newValue));
        
        this.staffList = FXCollections.observableArrayList();
        
        this.staffDAO = new StaffDAO();
    }
    
    @FXML
    private void searchAll() throws SQLException, ClassNotFoundException {
    	try {
    		staffList = this.staffDAO.findAll();
    		
    		// display results in the table
    		staffTable.setItems(staffList);
    		
    		resultArea.setText("Search complete!\n");
    		
    	} catch (SQLException | ClassNotFoundException e) {
    		resultArea.setText("Problem searching all employees\n");
    		throw e;
    	}
    }
    
    @FXML
    private void insertStaff(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {    	
    	boolean invalidInput = false;
    	
		// clear text area for errors 
		resultArea.clear();
		
		// TODO: add another check to validate input here or delete function
		
    	/* construct staff object and try insert if valid data */
    	if (!invalidInput) {
    		
        	Staff staff = new Staff();
    		staff.setFirstName(firstNameText.getText());
    		staff.setUserName(userNameText.getText());
    		staff.setPassword(passwordText.getText());
    		staff.setLastName(lastNameText.getText());
    		staff.setEmail(emailText.getText());
    		staff.setHomeAddress(homeAddressText.getText());
    		staff.setPhoneNo(Integer.parseInt(phoneNoText.getText()));
    		
	        try {	   	
	            staffDAO.insert(staff);
	            resultArea.setText("Employee inserted! \n");
	        } catch (SQLException | ClassNotFoundException e) {
	            resultArea.setText("Problem occurred while inserting employee " + e);
	            throw e;
	        }
    	}
    }
    
    /**
     * Displays staff details on the right hand side of the UI when a row is selected in the table
     * @param staff the staff member to display
     */
    private void showStaffDetails(Staff staff) {
        if (staff != null) {
        	staffIDLabel.setText(Integer.toString(staff.getStaff_id()));
            firstNameLabel.setText(staff.getFirstName());
            lastNameLabel.setText(staff.getLastName());
            userNameLabel.setText(staff.getUserName());
            emailLabel.setText(staff.getEmail());
            homeAddressLabel.setText(staff.getHomeAddress());
            phoneNoLabel.setText(Double.toString(staff.getPhoneNo()));
        } else {
        	staffIDLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            userNameLabel.setText("");
            emailLabel.setText("");
            homeAddressLabel.setText("");
            phoneNoLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     * 
     * Removes the staff member from the database and the UI.
     */
    @FXML
    private void deleteStaff() throws SQLException, ClassNotFoundException {
    	int selectedIndex = staffTable.getSelectionModel().getSelectedIndex();
    	
    	if (selectedIndex >= 0) {
    		try {
    			StaffDAO staffDAO = new StaffDAO();

    			/* remove the staff member selected by user from the database */
    			staffDAO.delete("STAFF_ID=" + staffTable.getItems().get(selectedIndex).getStaff_id());

        		resultArea.setText("Delete complete!\n");
    			
    		} catch (SQLException | ClassNotFoundException e) {
    			resultArea.setText("Problem deleting selected staff from database!\n");
    			throw e;
    		}
    	} else {
    		// Nothing selected.
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(mainApp.getPrimaryStage());
    		alert.setTitle("No Selection");
    		alert.setHeaderText("No Person Selected");
    		alert.setContentText("Please select a person in the table.");

    		alert.showAndWait();
    	}
        staffTable.getItems().remove(selectedIndex);  
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewStaff() throws SQLException, ClassNotFoundException {    	
        Staff tempStaff = new Staff();
        boolean okClicked = mainApp.showPersonEditDialog(tempStaff);
        if (okClicked) {
            //mainApp.getPersonData().add(tempStaff);
	        try {	   	
	        	// add new staff member to the list
	        	staffDAO.insert(tempStaff);
	        	//tempStaff = staffDAO.find
	        	staffList.add(tempStaff);

	            resultArea.setText("Employee inserted! \n");
	        } catch (SQLException | ClassNotFoundException e) {
	        	// revert the addition to staffList if failed to insert into database
	        	staffList.remove(tempStaff);
	            resultArea.setText("Problem occurred while inserting employee " + e);
	            throw e;
	        }
        }
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditStaff() {
        resultArea.setText("Edit called!\n");

        Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedStaff);
            if (okClicked) {
                showStaffDetails(selectedStaff);
                
                try {
                	staffDAO.update(selectedStaff);
                    resultArea.setText("Edit successful!\n");
                } catch (Exception e) {
                    resultArea.setText("Update to database failed!\n");
                }
                
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Staff Selected");
            alert.setContentText("Please select a staff member in the table.");

            alert.showAndWait();
        }
    }
    
    public void setMainApp(SUber mainApp) {
        this.mainApp = mainApp;
    }
}