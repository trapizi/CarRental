package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import test.SarinaTest;
import util.DBUtil;

import java.io.IOException;
import java.sql.SQLException;

import controller.AgreementController;
import controller.AgreementControllerBase;
import controller.AgreementInvoiceController;
import controller.ControllerBase;
import controller.RootLayoutController;
import controller.StaffController;
import javafx.scene.Parent;

/**
 *
 * @author TiM
 */
public class SarinaMain extends Application {
    
    // Create a primary stage that contains everything
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{

        this.primaryStage = primaryStage;
        
        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Testing main");
        
        // init table
   		DBUtil.dbInitAllTables();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the Agreement View
        showView("AgreementView.fxml");
    }
    
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SarinaMain.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            //Give the controller access to the main.
           // RootLayoutController controller = loader.getController();
           // controller.setMainApp(this);

            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
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
    		SarinaTest.testAgreementTable();
    		launch(args);

    		DBUtil.clearTable("AGREEMENT");
    		DBUtil.dropTable("AGREEMENT");
    		DBUtil.dbShutdown();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    	
    }
    
    public void showView(String viewFileName) {
    	try {
    		final String dir = "../view/";

    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(SarinaMain.class.getResource(dir + viewFileName));

    		AnchorPane view = (AnchorPane) loader.load();
    		
    		// Set view into the center of root layout.
    		rootLayout.setCenter(view);
    	
    		AgreementControllerBase controller = (AgreementControllerBase) loader.getController();
    		controller.setMainApp(this);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}