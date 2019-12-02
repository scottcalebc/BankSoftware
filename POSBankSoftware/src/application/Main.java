package application;
	
import java.io.IOException;
import java.util.ArrayList;

import application.controller.MainController;
import application.model.Accounts;
import application.model.ApplicationFileWriter;
import application.model.Transaction;
import application.model.Users;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	MainController mc;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		ArrayList<Users> users;
		
		if ((users = ApplicationFileWriter.readUserObjects()).size() == 0) {
			Users user = new Users("username", "password");
			user.addAccount(new Accounts("account 1", "type", 100.0, "11/23/19"));
			user.addAccount(new Accounts("account 2", "type", 10.0, "11/27/19"));
			Accounts tmp = new Accounts("account 3", "type", 120.0, "12/1/19");
			tmp.addTransaction(new Transaction("purchase 1", -20.0, "12/1/19"));
			user.addAccount(tmp);
			
			users.add(user);
		}
		this.mc = new MainController(users, primaryStage);
		
		mc.start();
	}
	
	public void stop() {
		System.out.println("Application closing");
		System.out.println(mc.getUsers());
		ApplicationFileWriter.writeUserObjects(mc.getUsers());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
