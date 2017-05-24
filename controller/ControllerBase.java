package controller;

//import mainApp.SUber;
import mainApp.SarinaMain;

public abstract class ControllerBase {
	// used to get a reference back to the root layout's primary stage
    protected SarinaMain mainApp;
    
    public void setMainApp (SarinaMain mainApp) {
        this.mainApp = mainApp;
    }
    
}