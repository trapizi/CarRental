/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the Agreement View
        showAgreementView();
    }
    
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SarinaMain.class.getResource("view/RootLayout.fxml"));
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
    
    
    public void showAgreementView() {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SarinaMain.class.getResource("view/AgreementView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            rootLayout.setCenter(employeeOperationsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    	
    	try {
    		
    		DBUtil.dbInitAllTables();
    		SarinaTest.testAgreementTable();
    		DBUtil.dbShutdown();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    }
    
}
