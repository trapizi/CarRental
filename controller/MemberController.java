package controller;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.Member;

public class MemberController {
	/*
	private IntegerProperty memberID;
	private SimpleObjectProperty<Date> lastMatchDate;
	private SimpleObjectProperty<Date> accountExpiry;
	private FloatProperty commissionRate;
	private StringProperty creditCard;
	private StringProperty paymentMedia;
	private StringProperty homeAddress;
	 */
    @FXML
    private TableView<Member> staffTable;
    @FXML
    private TableColumn<Member, Date> accountExpiryDateColumn;
    @FXML
    private TableColumn<Member, String> firstNameColumn;
    @FXML
    private TableColumn<Member, String> lastNameColumn;
    
    @FXML
    private void initialize () {    	
    	this.accountExpiryDateColumn.setCellValueFactory(cellData -> cellData.getValue().accountExpiryProperty());
    	this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }
    
    
}
