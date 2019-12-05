package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.ShowData;
import application.model.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Controller to handle switching of views and inflation of FXML files
 * @author Christopher Caleb Scott, Luke DeGoes
 *
 */
public class MainController {
	
	/**
	 * List of all users in program
	 */
	private ArrayList<Users> users;
	/**
	 * Used to hold current user if SubController is passed non user data
	 */
	private Users currUser;
	
	/**
	 * Holds the stage to enable switching of view
	 */
	private Stage primaryStage;
	
	
	//Main View
	/**
	 * Main view FXML file
	 */
	public static final String mainView = "../view/POSMainTemplate.fxml";
	/**
	 * Main view X dimension
	 */
	public static final int mainX = 831;
	/**
	 * Main View Y dimension
	 */
	public static final int mainY = 600;
	
	
	//Add Account View
	/**
	 * Add account view FXML file
	 */
	public static final String addAcctView = "../view/AddAccount.fxml";
	/**
	 * Add account view X dimension
	 */
	public static final int addAcctX = 393;
	/**
	 * Add account view Y dimension
	 */
	public static final int addAcctY = 200;
	
	
	//Add Transaction View
	/**
	 * Add Transaction view FXML file
	 */
	public static final String addTanView = "../view/AddTransaction.fxml";
	/**
	 * Add Transaction view X dimension
	 */
	public static final int addTanX = 400;
	/**
	 * Add Transaction view Y dimension
	 */
	public static final int addTanY = 243;
	
	//Login Screen View
	/**
	 * Login View FXML file
	 */
	public static final String loginScreenView = "../view/LoginScreen.fxml";
	/**
	 * Login view X dimension
	 */
	public static final int loginScreenX = 800;
	/**
	 * Login view Y dimension
	 */
	public static final int loginScreenY = 600;
	
	//Add User Screen View
	/**
	 * Add user view FXML file
	 */
	public static final String addUserView = "../view/AddUser.fxml";
	/**
	 * Add user view X dimension
	 */
	public static final int addUserX = 475;
	/**
	 * Add user view Y dimension
	 */
	public static final int addUserY = 180;
	
	
	/**
	 * Constructor to build main controller
	 * @param users
	 * @param primaryStage
	 */
	public MainController(ArrayList<Users>users, Stage primaryStage) {
		this.users = users;
		this.primaryStage = primaryStage;

	}
	
	
	/**
	 * gets the current user for SubController
	 * @return
	 */
	public Users getCurrUser() {
		return this.currUser;
	}
	
	/**
	 * Sets the current user for SubController
	 * @param user
	 */
	public void setCurrUser(Users user) {
		this.currUser = user;
	}
	
	/**
	 * Gets all users for Login Controller
	 * @return
	 */
	public ArrayList<Users>getUsers() {
		return this.users;
	}
	
	/**
	 * Adds a user to user list for Add user Controller
	 * @param user
	 */
	public void addUser(Users user) {
		this.users.add(user);
	}
	
	
	
	/**
	 * Starts application with Login Screen View
	 */
	public void start() {
		this.updateView(null, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
	}
	
	/**
	 * Handles changing of view for subcontroller; pulls data from previous subcontroller before inflating next fxml file
	 * @param sc
	 * @param filetoOpen
	 * @param sizeX
	 * @param sizeY
	 */
	public void updateView(SubController sc, String filetoOpen, int sizeX, int sizeY) {
		ShowData data = null;
		
		if (sc != null)
			data = sc.onExit();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filetoOpen));
			Parent root = loader.load();
			
			SubController nsc = loader.<SubController>getController();
			nsc.onLoad(data, this);
			
			Scene scene = new Scene(root, sizeX, sizeY);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
