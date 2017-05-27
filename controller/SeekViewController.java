package controller;

import model.Seek;
import model.SeekDAO;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mainApp.Stevenmain;

public class SeekViewController extends ControllerBase{

	@FXML
	private TableView<Seek> seekTable;
	@FXML
	private TableColumn<Seek, Integer> seekIDColumn;
	@FXML
	private TableColumn<Seek, Date> bookDayColumn;
	@FXML
	private TableColumn<Seek, Date> bookTimeColumn;
	
	@FXML
	private Label seekIDLabel;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label bookDayLabel;
	@FXML
	private Label bookTimeLabel;
	
	private ObservableList<Seek> seekList;
	private SeekDAO seekDAO;
	
	private Stevenmain mainApp;
	
	public SeekViewController() {
		
	}
	
	@FXML
	private void intialize() {
		//Initialize the seek table with the 3 columns
		seekIDColumn.setCellValueFactory(cellData -> cellData.getValue().seekIDProperty().asObject());
		bookDayColumn.setCellValueFactory(cellData -> cellData.getValue().bookDayProperty());
		bookTimeColumn.setCellValueFactory(cellData -> cellData.getValue().bookTimeProperty());
		
		this.seekDAO = new SeekDAO();
		
		showSeekDetails(null);
		
		this.seekTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSeekDetails((Seek) newValue));
		this.seekList = FXCollections.observableArrayList();
	}
	
	private void showSeekDetails(Seek seek) {
		if (seek != null) {
			
			seekIDLabel.setText(Integer.toString(seek.getSeekID()));
			usernameLabel.setText(seek.getUsername());
			bookDayLabel.setText(seek.getBookDay().toString());
			bookTimeLabel.setText(seek.getBookTime().toString());
		} else {
			seekIDLabel.setText("");
			usernameLabel.setText("");
			bookDayLabel.setText("");
			bookTimeLabel.setText("");
		}
	}
	
	public void setMainApp(Stevenmain mainApp) {
		this.mainApp = mainApp;
	}
}
