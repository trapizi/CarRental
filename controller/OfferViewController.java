package controller;

import java.sql.SQLException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import model.Offer;
import model.OfferDAO;

import util.AlertBuilder;

import mainApp.SUber;
import mainApp.Stevenmain;

public class OfferViewController extends ControllerBase {
	
		@FXML
		private Label resultText;
	   	@FXML
	    private TableView<Offer> offerTable;
	    @FXML
	    private TableColumn<Offer, String> availableCarColumn;
	    @FXML
	    private TableColumn<Offer, Long> postcodeColumn;
	    @FXML
	    private TableColumn<Offer, String> rateColumn;
	    

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
	    //private SUber mainApp;
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
	     * @throws ClassNotFoundException 
	     * @throws SQLException 
	     */
	    @FXML
	    private void initialize() throws ClassNotFoundException, SQLException{
	        // Initialize the person table with the 3 columns.
	    	availableCarColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
	    	postcodeColumn.setCellValueFactory(cellData -> cellData.getValue().postcodeProperty().asObject());
	    	rateColumn.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().priceProperty()));
	    	
	    	this.offerDAO = new OfferDAO();
	    	offerList = this.offerDAO.findAll();

            offerTable.setItems(offerList);
    		
	    	this.offerTable.getSelectionModel().selectedItemProperty().addListener(
	    		(observable, oldValue, newValue) -> showOfferDetails((Offer) newValue));
	    
	    	this.offerList = FXCollections.observableArrayList();
	    

	    }
	    
	    /**
	     * Displays staff details on the right hand side of the UI when a row is selected in the table
	     * @param staff the staff member to display
	     */
	    
	    @FXML
	    private void search() throws SQLException, ClassNotFoundException {
	    	try {
	    		offerList = this.offerDAO.findAll();
	    		
	    		offerTable.setItems(offerList);
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		throw e;
	    	} 
	    }
	    
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
	    					AlertType.WARNING, mainApp.getPrimaryStage(), "Search Error", 
	    					"Database could not complete seasrch!", e.getMessage());
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
	    			//Delete the selected offer from the database
	    			offerDAO.delete("OFFER_ID=" + ((Offer)offerTable.getItems().get(selectedIndex)).getOfferID());
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
	    		Offer selectedOffer = (Offer)offerTable.getSelectionModel().getSelectedItem();
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
	    
	    @FXML
	    private void handleBook() throws SQLException, ClassNotFoundException {
	    	if (mainApp == null) {
	    		System.out.println("NULL");
	    	}
	    	//THIS WILL CHANGE
	    	mainApp.showView("SeekView.fxml");
	    	}
	    
		public void setMainApp(SUber mainApp) {
			this.mainApp = mainApp;
		}
}
