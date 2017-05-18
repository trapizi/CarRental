package controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import mainApp.SUber;
import model.Staff;
import model.StaffDAO;
import util.AlertBuilder;

/**
 * @author Bing Wen (z3463269)
 * Code skeleton adapted from http://code.makery.ch/library/javafx-8-tutorial
 */
public class StaffController {
    @FXML
    private Label resultText;	// used to display success/failure messages for functions    
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

    // list to display onto the UI's table
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
    	
    	// check which row is being selected
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
    		
    		resultText.setText("Search complete!\n");
    	} catch (SQLException | ClassNotFoundException e) {            
            // Create and display alert for the database exception
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
            		"Database could not complete search!", e.getMessage()); 
            
            alert.showAndWait();
            
    		throw e;
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
            phoneNoLabel.setText(Double.toString(staff.getPhoneNo()));
        } else {
        	staffIDLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            userNameLabel.setText("");
            emailLabel.setText("");
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
    	
    	try {
        	int selectedIndex = staffTable.getSelectionModel().getSelectedIndex();

    		try {
    			StaffDAO staffDAO = new StaffDAO();

        		// triggers exception if nothing selected
    			// remove the staff member selected by user from the database
    			staffDAO.delete("STAFF_ID=" + staffTable.getItems().get(selectedIndex).getStaff_id());

        		resultText.setText("Delete complete!\n");
    			
    		} catch (SQLException | ClassNotFoundException e) {
    			resultText.setText("Problem deleting selected staff from database!\n");
    			throw e;
    		}
    		
            staffTable.getItems().remove(selectedIndex); 
    		
    	} catch (ArrayIndexOutOfBoundsException e) {
    		
    		// Create and display alert when no staff is selected
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Person Selected", "Select a person in the table"); 
            
            alert.showAndWait(); 
            
    	}
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

	        	// TODO: ensure that staffID gets updated on the staff details section after insert
	        	staffList.add(tempStaff);

	            resultText.setText("Employee inserted! \n");
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
	            		"Database could not complete search!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
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
        resultText.setText("Edit called!\n");
        
        try {
            Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
            boolean okClicked = mainApp.showPersonEditDialog(selectedStaff);
            
            if (okClicked) {
            	
            	// triggers null pointer exception from setters if nothing is selected
                showStaffDetails(selectedStaff);
                
                try {
                	staffDAO.update(selectedStaff);
                    resultText.setText("Edit successful!\n");
                } catch (Exception e) {
                    resultText.setText("Update to database failed!\n");
                }
                
            }
        } catch (NullPointerException e) {
        	// Create and display alert when no staff is selected
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Person Selected", "Select a person in the table"); 
            
            alert.showAndWait();
            
            System.out.println("COULD NOT EDIT -- NOTHING SELECTED");
        }
    }
    
    public void setMainApp(SUber mainApp) {
        this.mainApp = mainApp;
    }
}