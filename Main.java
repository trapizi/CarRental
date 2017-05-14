import java.sql.SQLException;

import model.*;
import util.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        //DBUtil.dbInitTable();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        //showEmployeeView();
    }
	
	public static void main(String[] args) {
		/* add stuff to this function if you want to test tables */
		//Main.testTables();
		
		/* add stuff to start() if you want to test UI */
        launch(args);		
	}
	
	public static void testTables() {
		System.out.println("Put your CREATE TABLE .txt files in this folder --> " + System.getProperty("user.dir"));
		
		// initialise tables for db        
        System.out.println(System.getProperty("user.dir"));
                
		try {
			/* Note: You need to add your file containing your CREATE TABLE statement in the function below.
			 *       More information given in the function. 
			 */
			DBUtil.dbInitAllTables();
			
			/* add test functions for your tables here */
			Main.testStaffTable();
			Main.testCorporateMemberTable();

			/* DO NOT DELETE THIS LINE BELOW OR IT'LL FUCK UP YOUR PRIMARY KEY NUMBERING */
			DBUtil.dbShutdown();
			/* DO NOT DELETE THIS LINE ABOVE */
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void testStaffTable() {
		/* Initialise your DAO objects to test your tables here */
		StaffDAO staffDAO = new StaffDAO();
		
		Staff staff1 = new Staff();
		staff1.setUserName("alice");
		staff1.setPassword("xd");
		
		Staff staff2 = new Staff();
		staff2.setUserName("bob");
		staff2.setPassword("lul");
		
		try {
			staffDAO.insert(staff1);
			staffDAO.insert(staff2);
							
			/* print the staff table out */			
		    final String url = "jdbc:derby:DBforDEMO;create=true";

			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "STAFF");
			
			// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
			DBUtil.clearTable("STAFF");
			DBUtil.dropTable("STAFF");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void testCorporateMemberTable() {
		try {
			String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
		
			url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBER");

		    url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			/* does not work on CORPORATE_MEMBER table due to the naming conventions of the fields in there */
			//DBUtil.clearTable("CORPORATE_MEMBER");
			DBUtil.dropTable("CORPORATE_MEMBER");
			
			DBUtil.clearTable("MEMBER");
			DBUtil.dropTable("MEMBER");
			
			DBUtil.clearTable("CORPORATE");
			DBUtil.dropTable("CORPORATE");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
}