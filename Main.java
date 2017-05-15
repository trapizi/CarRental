import java.sql.SQLException;

import model.*;
import util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.*;

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
        DBUtil.dbInitAllTables();

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        showStaffView();
    }
	
	public static void main(String[] args) {
		/* add stuff to this function if you want to test tables */
		//Main.testTables();
		
		/* add stuff to start() if you want to test UI */
        launch(args);		
	}
	
	public static void testTables() {
		System.out.println("Put your CREATE TABLE .txt files in this folder --> " + System.getProperty("user.dir") + "\\src\\table");

		// initialise tables for db        
        System.out.println(System.getProperty("user.dir"));
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
			MemberDAO mDAO = new MemberDAO();
			mDAO.insert(new Member());
			
			CorporateDAO cDAO = new CorporateDAO();
			cDAO.insert(new Corporate());
			
			CorporateMemberDAO cmDAO = new CorporateMemberDAO();
			
			Member m = mDAO.findById(1);
			m.setFirstName("test");
			
			Corporate c = cDAO.findById(1);
			cmDAO.insert(m, c);
			
			cDAO.insert(new Corporate());
			c = cDAO.findById(2);
			cmDAO.insert(m, c);
			
			String url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE_MEMBER");
		
			url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBER");

		    url = "jdbc:derby:DBforDEMO;create=true";
			DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "CORPORATE");
			
			ObservableList<CorporateMember> list = cmDAO.findAll();
			for (CorporateMember cm: list) {
				System.out.println(cm.toString());
			}
			
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