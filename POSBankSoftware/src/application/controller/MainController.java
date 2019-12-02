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
	public static final int mainX = 800;
	public static final int mainY = 600;
	
	
	//Add Account View
	public static final String addAcctView = "../view/AddAccount.fxml";
	public static final int addAcctX = 400;
	public static final int addAcctY = 250;
	
	
	//Add Transaction View
	public static final String addTanView = "../view/AddTransaction.fxml";
	public static final int addTanX = 400;
	public static final int addTanY = 420;
	
	
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
	
	
	
	
	public void start() {
		System.out.println("Main Controlelr started");
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/POSMainTemplate.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800, 600);
			
			POSViewController sc = loader.<POSViewController>getController();
			this.currUser = this.users.get(0);
			sc.onLoad(this.currUser, this);
			
			scene.getStylesheets().add("application/application.css");
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateView(SubController sc, String filetoOpen, int sizeX, int sizeY) {
		ShowData data = sc.onExit();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filetoOpen));
			Parent root = loader.load();
			
			SubController nsc = loader.<SubController>getController();
			nsc.onLoad(data, this);
			
			Scene scene = new Scene(root, sizeX, sizeY);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
