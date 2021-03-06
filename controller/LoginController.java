package controller;

import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.CorporateMember;
import model.CorporateMemberDAO;
import model.Member;
import model.MemberDAO;
import model.MembershipPayment;
import model.MembershipPaymentDAO;
import model.StaffDAO;
import model.User;
import util.AlertBuilder;
import util.DBTablePrinter;

/**
 * Controller to handle login
 * @author Bing Wen (z3463269)
 */
public class LoginController extends ControllerBase {
	@FXML
	private TextField userNameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private RadioButton staffRadioButton;
	@FXML
	private RadioButton memberRadioButton;
	@FXML
	private RadioButton registerMemberRadioButton;
	@FXML
	private RadioButton registerCorporateMemberRadioButton;
	@FXML
	private Label statusLabel;
	
	private StaffDAO staffDAO;
	private MemberDAO memberDAO;
	private CorporateMemberDAO corporateMemberDAO;
	private MembershipPaymentDAO membershipPaymentDAO;
	
	// make page names public static final so we don't have to re-declare every time we want to use them
	public static final String LOGIN_PAGE = "Login.fxml";
	public static final String MEMBER_HOME_PAGE = "MemberHome.fxml";
	public static final String REGISTRATION_PAGE = "MemberRegistrationDialog.fxml";
	public static final String C_MEMBER_REGISTRATION_PAGE = "CorporateMemberRegistrationDialog.fxml";
	public static final String STAFF_HOME_PAGE = "StaffHome.fxml";
	public static final String NAVIGATION_PANEL = "NavigationPanel.fxml";
	public static final String PAYMENT_PAGE = "PaymentView.fxml";
	
	private final float monthlyMembershipCost = 15.0f;
	
    @FXML
    private void initialize () {      	
    	this.staffDAO = new StaffDAO();
    	this.memberDAO = new MemberDAO();
    	this.corporateMemberDAO = new CorporateMemberDAO();
    	this.membershipPaymentDAO = new MembershipPaymentDAO();    	
    }
    
    @FXML
    private void handleLogin() throws Exception {   
    	
    	// checks database for an existing user with the username entered 
    	User user = this.getUser();
    	
    	try {
    		boolean validLogin = user.getPassword().equals(this.passwordField.getText());
    		
    		if (!validLogin) { 
        		this.statusLabel.setText("Incorrect username or password entered.");
    		} else {
    			
    			// display navigation panel on the left
    	    	this.mainApp.showNavigationPanel(NAVIGATION_PANEL);

    			// remember who logged in
    			mainApp.setLoggedInAs(user);
    			
        		// displays a suitable home page based on member / corporate member / staff 
        		this.displayHomePage(user);
    		}
    		
    	// user doesn't exist
    	} catch (NullPointerException e) {
    		this.statusLabel.setText("Incorrect username or password entered.");
    	}
    }
    
    /**
     * Displays home page
     * @param user The logged in user
     * @throws SQLException Throws when a database error occurs
     * @throws ClassNotFoundException Throws when a database error occurs
     */
    private void displayHomePage(User user) throws SQLException, ClassNotFoundException {
		// bring them to the member home page
		if (this.memberRadioButton.isSelected()) {
			
			try {
				// check if corporate member				
				mainApp.showView(MEMBER_HOME_PAGE, new MemberHomeController());
				
			// let parent function handle exceptions
			} catch (Exception e) {
				throw e;
			}
		
		// bring them to the staff home page
		} else if (this.staffRadioButton.isSelected()) {

			mainApp.showView(STAFF_HOME_PAGE, new StaffHomeController());
			
		} 
    }
    
    @FXML
    private void handleRegister() throws Exception {
    	// show member registration screen
    	if (this.registerMemberRadioButton.isSelected()) {
	        this.registerMember();
	        
	    // show corporate member registration screen
    	} else {
    		this.registerCorporateMember();
    	}
    }
    
    /**
     * Fetches a user from the database if it exists
     * @return A user based on the username entered in the textfield
     * @throws Exception Thrown if there's an exception thrown from the database
     */
    private User getUser() throws Exception {
    	User user;
    	
    	// check if existing staff
    	if (this.staffRadioButton.isSelected()) {
    		try {
    			user = staffDAO.findByUsername(this.userNameTextField.getText());
    		} catch (Exception e) {
    			throw e;
    		}
    		
    	// check if existing (corporate) member
    	} else {
    		try {
    			user = this.getMember();	
    		} catch (Exception e) {
    			throw e;
    		}
    	}
    	
    	return user;
    }
    
    /**
     * Displays a pop-up screen to enter member details
     */
    private void registerMember() {
        Member tempMember = new Member();
        boolean okClicked = mainApp.showEditDialog(tempMember, REGISTRATION_PAGE, "Registration");

        MembershipPayment membershipPayment = new MembershipPayment();
        boolean paid = false;
        
        // check that registration page has been completed 
        if (okClicked) {
        	// setup and show payment page
        	membershipPayment.setPaymentAmount(this.monthlyMembershipCost);
        	paid = mainApp.showEditDialog(membershipPayment, PAYMENT_PAGE, "Membership Payment");	
        }
        
        // insert member and payment details in database if valid data was entered
        if (okClicked && paid) {
        	// member insertion
        	try {	   		        	
	        	memberDAO.insert(tempMember);
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Registration error", 
	            		"Could not register member!", e.getMessage()); 
	            alert.showAndWait();	        	
	        }
	        
        	// payment insertion
	        try {
	        	// get member ID of the registered member
	        	int memberID = (memberDAO.findByUserName(tempMember.getUserName())).getMemberID();
	        		        	
	        	// attach it to the payment
	        	membershipPayment.setMemberID(memberID);
	        	membershipPaymentDAO.insert(membershipPayment);				
	        	
		        // TODO: remove this later
		        String url = "jdbc:derby:DBforDEMO;create=true";
				DBTablePrinter.printTable(DriverManager.getConnection(url, "demo", "demo"), "MEMBERSHIP_PAYMENT");
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Payment insertion error", 
	            		"Could not register member!", e.getMessage()); 
	            
	            alert.showAndWait();	   
	        }
        }
    }
    
    /**
     * Displays a pop-up screen to enter corporate member details
     */
    private void registerCorporateMember() {
        CorporateMember tempMember = new CorporateMember();
        boolean okClicked = mainApp.showEditDialog(tempMember, C_MEMBER_REGISTRATION_PAGE, "Registration");

        // setup and show payment page
        MembershipPayment membershipPayment = new MembershipPayment();
        membershipPayment.setPaymentAmount(this.monthlyMembershipCost);
        boolean paid = false;
        
        if (okClicked) {
        	paid = mainApp.showEditDialog(membershipPayment, PAYMENT_PAGE, "Membership Payment");
        }
        	
        if (okClicked && paid) {
	        try {	
	        	// NOTE: before inserting tempMember into the database does not have a memberID
	        	// memberID is assigned after it has been inserted
	        	memberDAO.insert(tempMember);
	        	
	        	// We query the database for tempMember to retrieve its memberID and update it
	        	tempMember.setMemberID(memberDAO.findByUserName(tempMember.getUserName()).getMemberID());
	        	
	        	corporateMemberDAO.insert(tempMember);
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Registration error", 
	            		"Could not register corporate member!", e.getMessage()); 
	            
	            alert.showAndWait();	        	
	        }
	        
	        // insert payment into database
	        try {
	        	// get member ID of the registered member
	        	int memberID = memberDAO.findByUserName(tempMember.getUserName()).getMemberID();
	        	
	        	// attach it to the payment
	        	membershipPayment.setMemberID(memberID);
	        	membershipPaymentDAO.insert(membershipPayment);				
	        } catch (SQLException | ClassNotFoundException e) {	        	
	            // Create and display alert for the database exception
	            Alert alert = AlertBuilder.createAlert(
	            		AlertType.WARNING, mainApp.getPrimaryStage(), "Payment insertion error", 
	            		"Could not register member!", e.getMessage()); 
	            
	            alert.showAndWait();	   
	        }
        }
    }
        
    /**
     * Returns a corporate member if they exist in the corporate member table, otherwise a member
     * @return A corporate member or member 
     * @throws Exception Thrown if the database throws an exception
     */
    private User getMember() throws Exception {
    	User user = memberDAO.findByUserName(this.userNameTextField.getText());
    	try {
			// check if corporate member
    		if (user != null) {
    			User temp = corporateMemberDAO.findById(((Member) user).getMemberID());
			
				if (temp != null) {
					user = temp;
				}
    		}
    	} catch (Exception e) {
    		throw e;
    	}
			
		return user;
    }
}