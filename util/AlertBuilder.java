package util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/*
 * Builds a dialog window
 */
public class AlertBuilder {
	public static Alert createAlert(Alert.AlertType alertType,  Stage owner, String titleText, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.initOwner(owner);
		alert.setTitle(titleText);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert;
	}
}
