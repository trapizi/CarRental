package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Staff;

/**
 * Handles navigation controller
 * @author Bing Wen (z3463269)
 */
public class NavigationPanelController extends ControllerBase {
	@FXML
	private Button homePageButton;
	
    @FXML
    private void initialize () {  
    }
    
    @FXML
    private void handleHomeButton() {
    	if (this.mainApp.getLoggedInAs() instanceof Staff) {   		
    		this.mainApp.showStaffHomePage();	
    	} else {    		
    		this.mainApp.showMemberHomePage();
    	}
    }
    
    @FXML
    private void handleLogoutButton() {
    	this.mainApp.clearNavigationPanel();
		this.mainApp.showLoginPage();
    }
    
}