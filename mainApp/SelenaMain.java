package mainApp;

import controller.RootLayoutController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import suber.controller.PaymentController;
import util.DBUtil;

/**
 *
 * @author selena
 */

public class SelenaMain extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{
        //create primary stage that contains everything
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SelenaMain");
             
        //initialise table
        DBUtil.dbInitAllTables();
        
        //initialise RootLayout
        initRootLayout();
        
        //diaplay payment view
        showPaymentView();
    }

    //INITIALISE ROOT LAYOUT
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SelenaMain.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); 
            //Set the scene in primary stage.
            primaryStage.setScene(scene);
 
            /*
            //Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);  
            */
 
            //Third, display the primary stage
            primaryStage.show(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    

    
    public void showPaymentView(){
         try {
             
            //(1) Load PaymentView from PaymentView.FXML
            FXMLLoader loader = new FXMLLoader(SelenaMain.class.getResource("Payment.fxml"));
        
            AnchorPane pane = loader.load();
        /*
            //connect to controller
            PaymentController paymentController = loader.getController();
            PaymentController.setMain(this);  
            */
            Scene scene = new Scene(pane);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
         } catch (IOException ex) {
                Logger.getLogger(SelenaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
}
