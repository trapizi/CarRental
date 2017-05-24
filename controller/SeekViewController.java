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

public class SeekViewController {

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
	
	@FXML
	private void intialize() {
		seekIDColumn.setCellValueFactory(cellData -> cellData.getValue().seekIDProperty().asObject());
		bookDayColumn.setCellValueFactory(cellData -> cellData.getValue().bookDayProperty());
		bookTimeColumn.setCellValueFactory(cellData -> cellData.getValue().bookTimeProperty());
		
		showSeekDetails(null);
	}
	
	private void showSeekDetails(Seek seek) {
		if (seek != null) {
			
			seekIDLabel.setText(Integer.toString(seek.getSeekID()));
			usernameLabel.setText(seek.getUsername());
			
			try{
				bookDayLabel.setText(seek.getBookDay().toString());
				
			} catch (NullPointerException e) {
				bookDayLabel.setText("");
				
			}

			try {
				bookTimeLabel.setText(seek.getBookTime().toString());
			} catch (NullPointerException e) {
				bookTimeLabel.setText("");
			}
			
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
