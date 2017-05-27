/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp;


import controller.CorpMemberControllerBase;
import controller.PastConsultationController;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import test.LizTest;
import util.DBUtil;

/**
 *
 * @author elizabeth
 */
public class LizMain extends Application {
       
    // Create a primary stage that contains everything
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{

        this.primaryStage = primaryStage;
        
        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Testing LizMain");
        
        // init table
   		DBUtil.dbInitAllTables();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the corporate member View
        showView("PastConsultationPayment.fxml");
        

        }  
   
             
    
    //Initializes the root layout.
 public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LizMain.class.getResource("../view/RootLayout.fxml"));
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
    		LizTest.testConsultationTable();
    		launch(args);

    		DBUtil.clearTable("CONSULTATION");
    		DBUtil.dropTable("CONSULTATION");
    		DBUtil.dbShutdown();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    	
    }
    
    public void showView(String viewFileName) {
    	try {
    		System.out.println("HELLOOOOOOOOOOOOOOOO");
    		final String dir = "../view/";

    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(LizMain.class.getResource(dir + viewFileName));

    		AnchorPane view = (AnchorPane) loader.load();

    		// Set view into the center of root layout.
    		rootLayout.setCenter(view);
    	
    		PastConsultationController controller = (PastConsultationController) loader.getController();
    		controller.setMainApp(this);

    	} catch (IOException e) {
    		System.out.println("BYEEEEEEEEE");
    		e.printStackTrace();
    	}
    }
    
    


}

