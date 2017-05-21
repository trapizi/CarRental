package suber.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class PaymentController {
    
    //payment type
    ObservableList<String> paymentTypeLst = FXCollections
                .observableArrayList("Visa", "MasterCard", "Bank");
    
    @FXML
    private Label paymentTypeLabel;
    
    @FXML
    private ChoiceBox paymentTypeChoiceBox;
    
    @FXML
    private void initialise(){
        paymentTypeChoiceBox.setValue("Visa");
    }

    
}
