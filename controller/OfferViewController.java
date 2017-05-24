package controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Offer;
import model.OfferDAO;
import model.Staff;
import util.AlertBuilder;
import mainApp.SUber;

public class OfferViewController {
	
		@FXML
		private Label resultText;
	   	@FXML
	    private TableView<Offer> offerTable;
	    @FXML
	    private TableColumn<Offer, String> availableCarColumn;
	    @FXML
	    private TableColumn<Offer, Long> postcodeColumn;
	    @FXML
	    private TableColumn<Offer, Double> rateColumn;
	    

	    @FXML
	    private Label brandLabel;
	    @FXML
	    private Label modelLabel;
	    @FXML
	    private Label carTypeLabel;
	    @FXML
	    private Label seatsLabel;
	    @FXML
	    private Label transmissionLabel;
	    @FXML
	    private Label fuelTypeLabel;
	    @FXML
	    private Label postcodeLabel;
	    @FXML
	    private Label priceLabel;
	    
	    private ObservableList<Offer> offerList;
	    private OfferDAO offerDAO;
	    
	    // Reference to the main application.
	    private SUber mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public OfferViewController() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	    	availableCarColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
	    	postcodeColumn.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty().asObject());
	    	rateColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
	    }
	    
	    /**
	     * Displays staff details on the right hand side of the UI when a row is selected in the table
	     * @param staff the staff member to display
	     */
	    private void showOfferDetails(Offer offer) {
	        if (offer != null) {
	        	brandLabel.setText(offer.getBrand());
	            modelLabel.setText(offer.getModel());
	            carTypeLabel.setText(offer.getCarType());
	            seatsLabel.setText(Integer.toString(offer.getSeats()));
	            transmissionLabel.setText(offer.getTransmission());
	            fuelTypeLabel.setText(offer.getFuelType());
	            priceLabel.setText(Double.toString(offer.getPrice()));
	            postcodeLabel.setText(Long.toString(offer.getPostcode()));
	        } else {
	        	brandLabel.setText("");
	            modelLabel.setText("");
	            carTypeLabel.setText("");
	            seatsLabel.setText("");
	            transmissionLabel.setText("");
	            fuelTypeLabel.setText("");
	            priceLabel.setText("");
	            postcodeLabel.setText("");
	        }
	    }	    
	    
	    @FXML
	    private void handleNewOffer() throws SQLException, ClassNotFoundException{
	    	Offer tempOffer = new Offer();
	    	boolean okClicked = mainApp.showEditDialog(tempOffer, "OfferEdit.fxml");
	    	
	    	if (okClicked) {
	    		try{
	    			//add new offer to the list
	    			offerDAO.insert(tempOffer);
	    			
	    			//
	    			tempOffer = offerDAO.findById(tempOffer.getOfferID());
	    			
	    			//ensure offerID gets updated on the offer details section after insert
	    			offerList.add(tempOffer);
	    			
	    			resultText.setText("Insert complete! \n");
	    		} catch (SQLException | ClassNotFoundException e) {
	    			Alert alert = AlertBuilder.createAlert(
	    					AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", "Database could not complete seasrch!", e.getMessage());
	    			alert.showAndWait();
	    			throw e;
	    		}
	    	}
	    }
	    
	    @FXML
	    private void deleteOffer() throws SQLException, ClassNotFoundException{
	    	try {
	    		int selectedIndex = offerTable.getSelectionModel().getSelectedIndex();
	    		
	    		try {
	    			OfferDAO offer = new OfferDAO();
	    			offerDAO.delete("OFFER_ID=" + offerTable.getItems().get(selectedIndex).getOfferID());
	    			resultText.setText("Delete complete!\n");
	    		} catch (SQLException | ClassNotFoundException e) {
	    			resultText.setText("Problem deleting selected offer from database!\n");
	    			throw e;
	    		}
	    		offerTable.getItems().remove(selectedIndex);
	    	} catch (ArrayIndexOutOfBoundsException e) {
	    		Alert alert = AlertBuilder.createAlert(
	    				AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Offer Selected", "Select an Offer in the table");
	    				
	    		alert.showAndWait();
	    		throw e;
	    	}
	    }
	    
	    @FXML
	    private void handleEditOffer() {
	    	resultText.setText("Edit called!\n");
	    	
	    	try {
	    		Offer selectedOffer = offerTable.getSelectionModel().getSelectedItem();
	    		boolean okClicked = mainApp.showEditDialog(selectedOffer, "OfferEdit.fxml");
	    		
	    		if (okClicked) {
	    			
	    			try{
	    				offerDAO.update(selectedOffer);
	    			} catch (Exception e) {
	    				resultText.setText("Failed to update offer!\n");
	    			}
	    		}
	    		resultText.setText("Offer udpated!\n");
	    	} catch (NullPointerException e) {
	    		Alert alert = AlertBuilder.createAlert(
	    				AlertType.WARNING, mainApp.getPrimaryStage(), "No Selection", "No Offer Selected", "Select an Offer in the table");
	    				
	    		alert.showAndWait();
	    		
	    		System.out.println("COULD NOT EDIT -- PLEASE SELECT AN OFFER");
	    	}
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(SUber mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        //offerTable.setItems(mainApp.getOfferData());
	    }
}
