package controller;

import javafx.stage.Stage;

public abstract class EditControllerBase {
    protected boolean okClicked = false;
    
    // the edit page opens in another window which requires another stage
    protected Stage dialogStage;
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Must override for each class that extends it 
     * @param o the object to set
     */
    public void setObject(Object o) {
    }
}
