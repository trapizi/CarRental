package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import util.DBUtil;
import test.StevenTest;
import java.io.IOException;
import java.sql.SQLException;

import controller.ControllerBase;
//import controller.ControllerBase;
import controller.EditControllerBase;
import controller.OfferControllerBase;
import controller.OfferViewController;

import controller.SeekController;
import javafx.scene.Parent;
import javafx.stage.Modality;

/**
 *
 * @author TiM
 */
public class Stevenmain extends Application {
    
    // Create a primary stage that contains everything
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{

        this.primaryStage = primaryStage;
        
        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Testing main");
        
        // init table
   		DBUtil.dbInitAllTables();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the Offer View
        showOfferView();
    }
    
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Stevenmain.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/

            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    public void showOfferView() {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Stevenmain.class.getResource("../view/OfferViewEditable.fxml"));
            AnchorPane OfferView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            rootLayout.setCenter(OfferView);

            OfferControllerBase controller = (OfferControllerBase) loader.getController();
            controller.setMainApp(this); 
            
        } catch (IOException e) {
            e.printStackTrace();
        }	
    }
    
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

			OfferControllerBase controller = (OfferControllerBase) loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * @param args the command line arguments
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
    	
    	try {
    		
    		DBUtil.dbInitAllTables();
    		StevenTest.testOfferTable();
    		launch(args);

    		DBUtil.clearTable("OFFER");
    		DBUtil.dropTable("OFFER");
    		DBUtil.dbShutdown();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    }
        
        
        public boolean showEditDialog(Object object, String viewFileName) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Stevenmain.class.getResource("../view/" + viewFileName));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditControllerBase controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setObject(object);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}  
