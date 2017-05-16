

import java.sql.SQLException;

import model.*;
import util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Main extends Application {

	// Create a primary stage that contains everything
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage){

		this.primaryStage = primaryStage;

		//Optional: Set a title for primary stage
		this.primaryStage.setTitle("Demo for tutorial 8");

		//2) Initialize RootLayout
		initRootLayout();

		//3) Display the EmployeeOperations View
		//showEmployeeView();
	}

	//Initializes the root layout.
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
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	/*System.out.println("Put your CREATE TABLE .txt files in this folder --> " + System.getProperty("user.dir"));

		// initialise tables for db
		try {			 
			DBUtil.dbInitAllTables();

			Main.testAgreementTable();

			DBUtil.dbShutdown();	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/

		launch(args);
	}

public static void testAgreementTable() {
	//Initialise your DAO objects to test your tables here 
	AgreementDAO agmtDAO = new AgreementDAO();

	Agreement agmt1 = new Agreement();
	agmt1.setStatus("pending");
	agmt1.setPayAmt("50");

	Agreement agmt2 = new Agreement();
	agmt2.setStatus("accepted");
	agmt2.setPayAmt("100");

	try {
		agmtDAO.insert(agmt1);
		agmtDAO.insert(agmt2);

		final String url = "jdbc:derby:DBforDEMO;create=true";
		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");


		//	agmt1.setPayAmt("20");
		Agreement a = agmtDAO.findById(1);
		//a.setAgreement_id(a.getAgreement_id());
		a.setPayAmt("20");
		a.setStatus("test_status");
		agmtDAO.update(a);

		//agmtDAO.delete("AGREEMENT_ID = 1");

		System.out.println(a.toString());

		Agreement aCopy = agmtDAO.findById(1);
		System.out.println("aCopy payamt: " + aCopy.getPayAmt());

		DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "AGREEMENT");

		// RESTART NUMBERING AFTER DELETING ROWS FROM TABLE
		DBUtil.clearTable("AGREEMENT");
		DBUtil.dropTable("AGREEMENT");
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
}
}
