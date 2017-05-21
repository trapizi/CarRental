package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import model.OfferDAO;
import model.Staff;
import mainApp.SUber;

public class OfferViewController {
	   @FXML
	    private TableView<Offer> offerTable;
	    @FXML
	    private TableColumn<Offer, String> availableCarColumn;
	    @FXML
	    private TableColumn<Offer, String> locationColumn;
	    @FXML
	    private TableColumn<Offer, Float> rateColumn;
	    

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
	    	locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
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
