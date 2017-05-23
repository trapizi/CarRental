package controller;

import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Member;
import model.MemberDAO;
import util.AlertBuilder;

public class MemberController extends ControllerBase {
    @FXML
    private Label resultText;	// used to display success/failure messages for functions 
	@FXML
    private TableView<Member> memberTable;
    
    @FXML
    private TableColumn<Member, String> firstNameColumn;
    @FXML
    private TableColumn<Member, String> lastNameColumn;
    @FXML
    private TableColumn<Member, Date> accountExpiryDateColumn;
    
    @FXML
    private Label memberIDLabel;
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
    private Label accountExpiryLabel;    
    @FXML
    private Label homeAddressLabel;
    
    // list to display onto the UI's table
    private ObservableList<Member> memberList;
    private MemberDAO memberDAO;
    
    @FXML
    private void initialize() {    	
    	this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	this.accountExpiryDateColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty());

    	// We must tell the cell how we want dates to be displayed
    	// section below adapted from http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/
    	this.accountExpiryDateColumn.setCellFactory(column -> {
    		return new TableCell<Member, Date>() {
    			@Override
    			protected void updateItem(Date item, boolean empty) {    			
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
    	
    	// check which row is being selected
    	this.memberTable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showMemberDetails(newValue));
    			
    	this.memberList = FXCollections.observableArrayList();
    	
    	this.memberDAO = new MemberDAO();
    }
    
    @FXML
    private void searchAll() {
    	try {
    		memberList = this.memberDAO.findAll();
    		
    		// display results in the table
    		memberTable.setItems(memberList);
    		
    		resultText.setText("Search complete!\n");
    	} catch (SQLException | ClassNotFoundException e) {            
            // Create and display alert for the database exception
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
            		"Database could not complete search!", e.getMessage()); 
            
            alert.showAndWait();            
    	}
    }
    
    /**
     * Displays staff details on the right hand side of the UI when a row is selected in the table
     * @param staff the staff member to display
     */
    private void showMemberDetails(Member member) {
        if (member != null) {
        	memberIDLabel.setText(Integer.toString(member.getMemberID()));
            firstNameLabel.setText(member.getFirstName());
            lastNameLabel.setText(member.getLastName());
            userNameLabel.setText(member.getUserName());
            emailLabel.setText(member.getEmail());
            phoneNoLabel.setText(Integer.toString(member.getPhoneNo()));
            
            // string will be null when entering a new member
            try {
            	accountExpiryLabel.setText(member.getAccountExpiry().toString());
            } catch (NullPointerException e) {
            	accountExpiryLabel.setText("");
            }
            
            homeAddressLabel.setText(member.getHomeAddress());
        } else {
        	memberIDLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            userNameLabel.setText("");
            emailLabel.setText("");
            phoneNoLabel.setText("");
            accountExpiryLabel.setText("");
            homeAddressLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewMember() {    	
        Member tempMember = new Member();
        boolean okClicked = mainApp.showEditDialog(tempMember, "MemberEditDialog.fxml");
        
        if (okClicked) {
            //mainApp.getPersonData().add(tempMember);
	        try {	   	
	        	// add new member member to the list
	        	// this member being inserted will not have an ID until it is inserted into the database
	        	memberDAO.insert(tempMember);

	        	// need to retrieve the inserted member from the database to get the ID assigned to it
	        	tempMember = memberDAO.findByUserName(tempMember.getUserName());
	        	
	        	// TODO: ensure that memberID gets updated on the member details section after insert
	        	memberList.add(tempMember);

	            resultText.setText("Insert successful!\n");
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
	            		"Database could not complete search!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
	        }
        }
    }
    
    @FXML
    private void handleEditMember() { 
        resultText.setText("Editing...\n");
        
        try {
            Member selectedMember = memberTable.getSelectionModel().getSelectedItem();
            boolean okClicked = mainApp.showEditDialog(selectedMember, "MemberEditDialog.fxml");
            
            if (okClicked) {
            	
            	// triggers null pointer exception from setters if nothing is selected
                showMemberDetails(selectedMember);
                
                try {
                	memberDAO.update(selectedMember);
                } catch (Exception e) {
                    resultText.setText("Update to database failed!\n");
                }
                
            }
            
            resultText.setText("Edit complete!\n");
        } catch (NullPointerException e) {
        	// Create and display alert when no staff is selected
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Member Selected", "Select a member in the table"); 
            
            alert.showAndWait();       
        }
    }
    
    @FXML
    private void deleteMember() throws SQLException, ClassNotFoundException {   	
    	try {
        	int selectedIndex = memberTable.getSelectionModel().getSelectedIndex();

    		try {
    			MemberDAO memberDAO = new MemberDAO();

        		// triggers exception if nothing selected
    			// remove the member member selected by user from the database
    			memberDAO.delete("STAFF_ID=" + memberTable.getItems().get(selectedIndex).getMemberID());

        		resultText.setText("Delete complete!\n");
    		} catch (SQLException | ClassNotFoundException e) {
    			resultText.setText("Problem deleting selected member from database!\n");
    			throw e;
    		}
    		
            memberTable.getItems().remove(selectedIndex); 
    		
    	} catch (ArrayIndexOutOfBoundsException e) {
    		
    		// Create and display alert when no member is selected
            Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Member Selected", "Select a member in the table"); 
            
            alert.showAndWait(); 
    	}
    }
}
