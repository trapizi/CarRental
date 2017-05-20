package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.MemberDAO;

public class MemberProfileController extends ControllerBase {
    @FXML
    private Label resultText;	// used to display success/failure messages for functions
    
    @FXML
    private Label memberIDLabel;
    @FXML
    private Label userNameLabel;    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;    
    @FXML
    private Label emailLabel;    
    @FXML
    private Label phoneNoLabel;
    @FXML
    private Label accountExpiryLabel;    
    @FXML
    private Label homeAddressLabel;
    
    private MemberDAO memberDAO;

    @FXML
    private void initialize () {  
    	this.memberDAO = new MemberDAO();
    }
    
    @FXML
    private void handleEditMember() {
    	
    }
}
