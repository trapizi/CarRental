

import java.sql.SQLException;
import test.BingTest;
import util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    // ClassNotFoundException from DBUtil
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{
   
        this.primaryStage = primaryStage;
        
        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Demo for tutorial 8");

        // init table
        DBUtil.dbInitAllTables();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        showStaffView();
    }
	
	public static void main(String[] args) throws Exception {
		/* add stuff to this function if you want to test tables */
		//Main.testTables();

		/*
		try {
			BingTest.initMyTables();
			BingTest.testStaffTable();
			//BingTest.testCorporateMemberTable();
			
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.dbShutdown();
		}
		*/
		
		/* add stuff to start() if you want to test UI */
        //launch(args);	
      //  try { 
       //     Main.testTables();
        //        DBUtil.dbInitAllTables();
        //} catch (Exception e) {
	//} 
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
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            rootLayout.minWidthProperty().bind(scene.widthProperty());
            rootLayout.minHeightProperty().bind(scene.heightProperty());
            
            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/

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
            loader.setLocation(Main.class.getResource("view/StaffView.fxml"));
            AnchorPane staffOperationsView = (AnchorPane) loader.load();
            
            /*
            staffOperationsView.minWidthProperty().bind(rootLayout.minWidthProperty());
            staffOperationsView.minHeightProperty().bind(rootLayout.minHeightProperty());
            */
            
            //staffOperationsView.minWidthProperty().bind(rootLayout.minWidthProperty());
            //staffOperationsView.minHeightProperty().bind(rootLayout.minHeightProperty().multiply(0.95));

            // Set Employee Operations view into the center of root layout.
            rootLayout.setCenter(staffOperationsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}