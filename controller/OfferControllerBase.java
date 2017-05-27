package controller;

import mainApp.Stevenmain;

public abstract class OfferControllerBase {
	// used to get a reference back to the root layout's primary stage
    protected Stevenmain mainApp;
    
    public void setMainApp (Stevenmain mainApp) {
        this.mainApp = mainApp;
    }
    
}