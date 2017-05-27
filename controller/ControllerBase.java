package controller;

import mainApp.SUber;
import model.CorporateMember;
import model.Member;
import model.Staff;

/**
 * Abstract class that contains a reference back to the main app
 * @author Bing Wen (z3463269)
 */
public abstract class ControllerBase {
	// used to get a reference back to the root layout's primary stage
    protected SUber mainApp;
    
    public void setMainApp (SUber mainApp) {
        this.mainApp = mainApp;
    }
    
    public boolean isStaffLoggedIn() {
    	return (this.mainApp.getLoggedInAs() instanceof Staff);
    }
    
    public boolean isMemberLoggedIn() {
    	return (this.mainApp.getLoggedInAs() instanceof Member &&
    			!(this.mainApp.getLoggedInAs() instanceof CorporateMember));
    }
    
    public boolean isCorporateMemberLoggedIn() {
    	return this.mainApp.getLoggedInAs() instanceof CorporateMember;
    }
}