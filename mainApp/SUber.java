package mainApp;

import java.sql.SQLException;

import controller.ControllerBase;
import controller.EditControllerBase;
import controller.LoginController;
import controller.MemberHomeController;
import controller.RootLayoutController;
import controller.StaffHomeController;
//import test.BingTest;
import util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Agreement;
import model.AgreementDAO;
import model.User;
import test.BingTest;
import test.SarinaTest;
import javafx.application.Application;
import java.io.IOException;

public class SUber extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private User loggedInAs;
	
	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{
		try {
			this.primaryStage = primaryStage;
	
			this.primaryStage.setTitle("SUber");
			
			this.setLoggedInAs(null);

			// init table
			//DBUtil.dbInitAllTables();
	
			//2) Initialize RootLayout
			initRootLayout();

			// display login page
			this.showLoginPage();
			
			//showView("StaffAgreementView.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("java version: "+System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));

		// add stuff to this function if you want to test tables
		SUber.testTables();
		
		// add stuff to start() if you want to test UI

		try {
			BingTest.clearTables();
			DBUtil.dbInitAllTables();
			
			//BingTest.initMyTables();
			//DBUtil.dropAllTables();
			
			DBUtil.insertDummyData();		
			launch(args);	
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			DBUtil.dropAllTables();
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
	
	/**
	 * Loads the view specified in viewFileName onto the main app
	 * @param viewFileName the filename of the view to load
	 */
	public void showView(String viewFileName) {
		try {
			// path to file from the current file
			// TODO: forward slashes may not work with macs -- change as required
			final String dir = "../view/";
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SUber.class.getResource(dir + viewFileName));
			
			AnchorPane view = (AnchorPane) loader.load();

			// Set view into the center of root layout.
			rootLayout.setCenter(view);

			ControllerBase controller = (ControllerBase) loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * USE THIS IF YOUR CONTROLLER'S INITIALIZE FUNCTION REQUIRES A REFERENCE TO MAINAPP
	 * @Pre .fxml file doesn't have a controller attached
	 * @param viewFileName the view to load
	 * @param controller the controller to attach to the view
	 */
	public void showView(String viewFileName, ControllerBase controller) {
		try {
			final String dir = "../view/";
			
			// attach reference of mainApp to the controller
			controller.setMainApp(this);
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SUber.class.getResource(dir + viewFileName));
			
			// set controller here
			loader.setController(controller);
			
			AnchorPane view = (AnchorPane) loader.load();
			
			// Set view into the center of root layout.
			rootLayout.setCenter(view);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Opens a dialog to edit details for the specified object. If the user
     * clicks OK, the changes are saved into the provided object and true
     * is returned.
     * 
     * @param object the object to be edited
     * @param viewFileName the view to display
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showEditDialog(Object object, String viewFileName) {
    	return showEditDialog(object, viewFileName, "Edit");
    }
    
    public boolean showEditDialog(Object object, String viewFileName, String dialogName) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SUber.class.getResource("../view/" + viewFileName));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(dialogName);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditControllerBase controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setObject(object);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
	/**
	 * Displays a navigation panel on the left of the screen
	 * @param viewFileName the navigation panel view to load
	 */
	public void showNavigationPanel(String viewFileName) {
		try {
			// path to file from the current file
			// TODO: forward slashes may not work with macs -- change as required
			final String dir = "../view/";
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SUber.class.getResource(dir + viewFileName));
			
			AnchorPane view = (AnchorPane) loader.load();

			// Set view into the center of root layout.
			rootLayout.setLeft(view);

			ControllerBase controller = (ControllerBase) loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearNavigationPanel() {
		rootLayout.setLeft(null);			
	}
	
    public void showMemberHomePage() {
		this.showView(LoginController.MEMBER_HOME_PAGE, new MemberHomeController());
    }
    
    public void showStaffHomePage() {
		this.showView(LoginController.STAFF_HOME_PAGE, new StaffHomeController());
    }
    
    public void showLoginPage() {
		this.showView(LoginController.LOGIN_PAGE);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void setLoggedInAs(User user) {
    	this.loggedInAs = user;
    }
    
    public User getLoggedInAs() {
    	return this.loggedInAs;
    }
}