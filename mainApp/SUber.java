package mainApp;

import java.sql.DriverManager;
import java.sql.SQLException;

import controller.RootLayoutController;
import controller.StaffController;
import controller.StaffEditController;
import test.BingTest;
import util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Staff;
import javafx.application.Application;
import java.io.IOException;

public class SUber extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{
		try {
			this.primaryStage = primaryStage;
	
			//Optional: Set a title for primary stage
			this.primaryStage.setTitle("Demo for tutorial 8");
	
			// init table
			DBUtil.dbInitAllTables();
	
			//2) Initialize RootLayout
			initRootLayout();
	
			//3) Display the EmployeeOperations View
			showStaffView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("java version: "+System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));

		/* add stuff to this function if you want to test tables */
		SUber.testTables();

		/* add stuff to start() if you want to test UI */
		try {
			BingTest.initMyTables();
			//BingTest.testCorporateMemberTable();
			
			launch(args);	
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			BingTest.clearTables();
			DBUtil.dbShutdown();
		}
	}

	public static void testTables() {
		System.out.println("Put your CREATE TABLE .txt files in this folder --> " + System.getProperty("user.dir") + "\\src\\table");

		// initialise tables for db        
		System.out.println(System.getProperty("user.dir"));
	} 

	public void initRootLayout() {
		try {
			//First, load root layout from RootLayout.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SUber.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			//Second, show the scene containing the root layout.
			Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
			primaryStage.setScene(scene); //Set the scene in primary stage.

			//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

			//Third, show the primary stage
			primaryStage.show(); //Display the primary stage
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	//Shows the employee operations view inside the root layout.
	public void showStaffView() {
		try {
			//First, load EmployeeView from EmployeeView.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SUber.class.getResource("../view/StaffView.fxml"));
			AnchorPane staffOperationsView = (AnchorPane) loader.load();

			// Set Employee Operations view into the center of root layout.
			rootLayout.setCenter(staffOperationsView);

			StaffController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Staff staff) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SUber.class.getResource("../view/StaffEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            StaffEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staff);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
