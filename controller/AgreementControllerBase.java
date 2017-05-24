package controller;

import mainApp.SarinaMain;

public abstract class AgreementControllerBase {
	// used to get a reference back to the root layout's primary stage
    protected SarinaMain mainApp;
    
    public void setMainApp (SarinaMain mainApp) {
        this.mainApp = mainApp;
    }
    
}