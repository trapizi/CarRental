package controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import mainApp.SUber;

public class RootLayoutController extends ControllerBase {
    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Title of your program");
        alert.setHeaderText("This is a sample JAVAFX app");
        alert.setContentText("You can search, delete, update, insert a new employee with this program.");
        alert.show();
    }
}

