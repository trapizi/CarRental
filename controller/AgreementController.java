package controller;

import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mainApp.SUber;
import mainApp.SarinaMain;
import model.Agreement;
import model.AgreementDAO;
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
	
	 @FXML
	 private Label agmtIDLabel;
	 @FXML
	 private Label dateLabel;
	 @FXML
	 private Label locationFromLabel;
	 @FXML
	 private Label locationToLabel;
	 @FXML
	 private Label priceLabel;
	 @FXML
	 private Label dayCreatedLabel;
	
	 
    @FXML
    private TextField offererField;
    @FXML
    private TextField seekerField;

    @FXML
    private TextField dateField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField createDayField;
    
    
	 // list to display onto the UI's table
    private ObservableList<Agreement> agmtList;
    private AgreementDAO agmtDAO;
	
    // reference to mainApp for alerts
    private SarinaMain mainApp;
    
	@FXML
	private void initialize () {
		memberColumn.setCellValueFactory(cellData -> cellData.getValue().offererIDProperty().asObject());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().agreeDateProperty());
		postcodeToColumn.setCellValueFactory(cellData -> cellData.getValue().toPostcodeProperty().asObject());
		postcodeFromColumn.setCellValueFactory(cellData -> cellData.getValue().fromPostcodeProperty().asObject());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().payAmtProperty().asObject());       
   
		// Clear agreement details
		showAgreementDetails(null);
		
		// Listen for selection changes and show the person details when changed.
        this.agreementTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> showAgreementDetails((Agreement) newValue));
		
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
	
	 /*  * Fills all text fields to show details about the agreement.
	  	 * If the specified agreement is null, all text fields are cleared.
	 */
    private void showAgreementDetails(Agreement agmt) {
        if (agmt != null) {
        	agmtIDLabel.setText(Integer.toString(agmt.getAgreement_id()));

        	 try {
             	dateLabel.setText(agmt.getAgreeDate().toString());
             } catch (NullPointerException e) {
             	dateLabel.setText("");
             }

        	locationFromLabel.setText(Long.toString(agmt.getFromPostcode()));
        	locationToLabel.setText(Long.toString(agmt.getToPostcode()));
        	priceLabel.setText(Float.toString(agmt.getPayAmt()));
        
        	 try {
              	dayCreatedLabel.setText(agmt.getCreateDay().toString());
              } catch (NullPointerException e) {
              	dayCreatedLabel.setText("");
              }

        } else {
        	agmtIDLabel.setText("");
        	dateLabel.setText("");
        	locationFromLabel.setText("");
        	locationToLabel.setText("");
        	priceLabel.setText("");
        	dayCreatedLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     * 
     * Removes an agreement from the database and the UI.
     */
    @FXML
    private void deleteAgreement() throws SQLException, ClassNotFoundException {
    	int selectedIndex = agreementTable.getSelectionModel().getSelectedIndex();
    	
    	if (selectedIndex >= 0) {
    		try {
    			AgreementDAO agmtDAO = new AgreementDAO();
    			//deletes the selected agreement from the database
    			agmtDAO.delete("AGREEMENT_ID=" + ((Agreement) agreementTable.getItems().get(selectedIndex)).getAgreement_id());
    			
    		} catch (SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    			throw e;
    		}
    	} else {
    		// Create and display alert when no staff is selected
     /*       Alert alert = AlertBuilder.createAlert(
            		AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Agreement Selected", "Select an agreement in the table"); 
            
            alert.showAndWait();  
      */  		
    	}
    	
    	//deletes the selected agreement from the UI
        agreementTable.getItems().remove(selectedIndex);  
    }
    
    /**
     * Called when the user clicks the insert button. 
     * details for a new agreement.
     */
    @FXML
    private void insertAgreement() throws SQLException, ClassNotFoundException {    	
    	Agreement tempAgreement = new Agreement();

    	tempAgreement.setOfferer(Integer.parseInt(offererField.getText()));
    	tempAgreement.setSeeker(Integer.parseInt(seekerField.getText()));
    	tempAgreement.setAgreeDate(dateField.getText());
    	tempAgreement.setFromPostcode(Long.parseLong(fromField.getText()));
    	tempAgreement.setToPostcode(Long.parseLong(toField.getText()));
    	tempAgreement.setPayAmt(Float.parseFloat(priceField.getText()));
    	tempAgreement.setCreateDay(createDayField.getText());
    	
    	try {	   	
    		// add new agreement to the database
    		agmtDAO.insert(tempAgreement);

    		// ensure that agmtID gets updated 
    		agmtList.add(tempAgreement);

    	} catch (SQLException | ClassNotFoundException e) {	        	
    		// Create and display alert for the database exception
    		/*    Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
	            		"Database could not complete search!", e.getMessage()); 

	            alert.showAndWait();	
    		 */
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    public void setMainApp(SarinaMain mainApp) {
        this.mainApp = mainApp;
    }
    
 	
}
