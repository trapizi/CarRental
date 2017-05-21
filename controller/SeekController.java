package controller;

import model.Seek;
import model.seekDAO;
import util.AlertBuilder;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import mainApp.SUber;


public class SeekController {

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
	private seekDAO seekDAO;
	
	private SUber mainApp;
	
	private SeekController(){
	}
	@FXML
	private void initialize (){
		seekIDColumn.setCellValueFactory(cellData -> cellData.getValue().seekIDProperty().asObject());
		bookDayColumn.setCellValueFactory(cellData -> cellData.getValue().getBookDay().asObject());
		bookTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getBookTime().asObject());
	}
	
	public void setMainApp(SUber mainApp) {
		this.mainApp = mainApp;
	}
}
