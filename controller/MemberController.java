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
import mainApp.SUber;
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
    private void initialize () {    	
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
            phoneNoLabel.setText(Double.toString(member.getPhoneNo()));
            
            // TODO: use input validation to prevent empty date being inserted
            // replace try, catch later
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
    private void handleNewStaff() {    	
        Member tempMember = new Member();
        boolean okClicked = mainApp.showMemberEditDialog(tempMember);
        
        if (okClicked) {
            //mainApp.getPersonData().add(tempMember);
	        try {	   	
	        	// add new member member to the list
	        	memberDAO.insert(tempMember);

	        	// TODO: ensure that memberID gets updated on the member details section after insert
	        	memberList.add(tempMember);

	            resultText.setText("Employee inserted! \n");
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
	            		"Database could not complete search!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
	        }
        }
    }
    
    // TODO: make this
    @FXML
    private void handleEditStaff() { 
    	
    }
    
    // TODO: make this
    @FXML
    private void deleteStaff() {
    	
    }
}
