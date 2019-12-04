package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.ShowData;
import application.model.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	private ArrayList<Users> users;
	private Users currUser;
	private Stage primaryStage;
	
	
	//Main View
	public static final String mainView = "../view/POSMainTemplate.fxml";
	public static final int mainX = 831;
	public static final int mainY = 600;
	
	
	//Add Account View
	public static final String addAcctView = "../view/AddAccount.fxml";
	public static final int addAcctX = 393;
	public static final int addAcctY = 200;
	
	
	//Add Transaction View
	public static final String addTanView = "../view/AddTransaction.fxml";
	public static final int addTanX = 400;
	public static final int addTanY = 243;
	
	//Login Screen View
	public static final String loginScreenView = "../view/LoginScreen.fxml";
	public static final int loginScreenX = 800;
	public static final int loginScreenY = 600;
	
	//Add User Screen View
	public static final String addUserView = "../view/AddUser.fxml";
	public static final int addUserX = 475;
	public static final int addUserY = 180;
	
	
	public MainController(ArrayList<Users>users, Stage primaryStage) {
		this.users = users;
		this.primaryStage = primaryStage;

	}
	
	
	public Users getCurrUser() {
		return this.currUser;
	}
	
	public void setCurrUser(Users user) {
		this.currUser = user;
	}
	
	public ArrayList<Users>getUsers() {
		return this.users;
	}
	
	public void addUser(Users user) {
		this.users.add(user);
	}
	
	
	
	
	public void start() {
		this.updateView(null, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
	}
	
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
