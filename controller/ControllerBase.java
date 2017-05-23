package controller;

import mainApp.SUber;

public abstract class ControllerBase {
	// used to get a reference back to the root layout's primary stage
    protected SUber mainApp;
    
    public void setMainApp (SUber mainApp) {
        this.mainApp = mainApp;
    }
}