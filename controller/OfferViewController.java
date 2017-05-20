package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import model.OfferDAO;
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
	    private Label tranmissionLabel;
	    @FXML
	    private Label fuelTypeLabel;
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
