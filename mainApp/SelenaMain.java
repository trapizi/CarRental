package mainApp;

import controller.AgreementControllerBase;
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
import controller.PaymentController; 
import static javafx.application.Application.launch;
import test.SelenaTest;
import util.DBUtil;
import controller.ControllerBase;
import controller.EditControllerBase;
import javafx.stage.Modality;
/**
 *
 * @author selena
 */

public class SelenaMain extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
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
    
 
    
    public void showAgrementPaymentView(){
         try {
             
            //(1) Load PaymentView from PaymentView.FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SelenaMain.class.getResource("../view/AgreementPaymentView.fxml"));
            AnchorPane AgreementPaymentView= (AnchorPane) loader.load();
            
            rootLayout.setCenter(AgreementPaymentView);
            
         } catch (IOException e){
                 e.printStackTrace();
                 }
        /*
            //connect to controller
            PaymentController paymentController = loader.getController();
            PaymentController.setMain(this);  
            
            Scene scene = new Scene(pane);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
         } catch (IOException ex) {
                Logger.getLogger(SelenaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        */    
    }
 
    public static void main(String[] args) {
            	try {
    		
    		DBUtil.dbInitAllTables();
    		SelenaTest.testAgreementPaymentTable();
    		launch(args);

    		DBUtil.clearTable("AGREEMENT_PAYMENT");
    		DBUtil.dropTable("AGREEMENT_PAYMENT");
    		DBUtil.dbShutdown();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    }
    
    public void showView(Object object, String viewFileName){
        try{
            System.out.println("HELLOOOOOOOOOOOOOOOO");
    		final String dir = "../view/";
                
                //load the fxml file and create new stage for the pop- up
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(SelenaMain.class.getResource(dir + viewFileName));
    		AnchorPane view = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(view);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                EditControllerBase controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setObject(object);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

    	} catch (IOException e) {
    		System.out.println("BYEEEEEEEEE");
    		e.printStackTrace();
    	}
    }
    
}   