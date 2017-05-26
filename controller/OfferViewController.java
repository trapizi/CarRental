package controller;

import java.sql.SQLException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import model.Offer;
import model.OfferDAO;

import util.AlertBuilder;
import util.InvalidInputException;
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
	    @FXML
	    private TextField filterField;

	    
	    private ObservableList<Offer> offerList = FXCollections.observableArrayList();
	    private OfferDAO offerDAO;
	    private SortedList<Offer> sortedData;
	    
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
	    	offerTable.setItems(offerList);
	    	offerList = this.offerDAO.findAll();
	    	
	    	/**
	    	 * Filter function
	    	 */
	    	FilteredList<Offer> filteredData = new FilteredList<>(offerList, p -> true);
        	sortedData = new SortedList<>(filteredData);
	        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	        	filteredData.setPredicate(offer -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compare first name and last name of every person with filter text.
	                String postcodeFilter = newValue.toLowerCase();
	                offerTable.setItems(sortedData);
	                if (((Long.toString(offer.getPostcode()).contains(postcodeFilter)))) {
	                    return true; // Filter matches postcode.
	                } return false; // Does not match.
	            });
	        });

	        // 3. Wrap the FilteredList in a SortedList. 
	        //SortedList<Offer> sortedData = new SortedList<>(filteredData);

	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(offerTable.comparatorProperty());

	        // 5. Add sorted (and filtered) data to the table.
	        offerTable.setItems(sortedData);
	    	

	    	this.offerTable.getSelectionModel().selectedItemProperty().addListener(
	    		(observable, oldValue, newValue) -> showOfferDetails((Offer) newValue));
	    
	    	this.offerList = FXCollections.observableArrayList();

	    }
	    

	    @FXML
	    private void refreshTable() throws SQLException, ClassNotFoundException {
	    	try {
	    		offerList = this.offerDAO.findAll();
	    		
	    		offerTable.setItems(offerList);
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		throw e;
	    	} 
	    }
	    
	    /**
	     * Search nearby cars within postcode +- 1 range.
	     */
	    
	    
	    @FXML
	    private void searchPostcode() throws SQLException, ClassNotFoundException {
	    		offerList = this.offerDAO.findByPostcode(Long.parseLong((this.filterField.getCharacters()).toString()));
	    		offerTable.setItems(offerList);
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
	    	mainApp.showView("Payment.fxml");
	    	}
	    
		public void setMainApp(SUber mainApp) {
			this.mainApp = mainApp;
		}
}
