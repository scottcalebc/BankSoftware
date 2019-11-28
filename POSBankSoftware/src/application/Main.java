package application;
	
import java.io.IOException;
import java.util.ArrayList;

import application.controller.MainController;
import application.model.Accounts;
import application.model.Transaction;
import application.model.Users;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	MainController mc;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Users user = new Users("username", "password");
		user.addAccount(new Accounts("account 1", "type", 100.0, "11/23/19"));
		user.addAccount(new Accounts("account 2", "type", 10.0, "11/27/19"));
		Accounts tmp = new Accounts("account 3", "type", 120.0, "12/1/19");
		tmp.addTransaction(new Transaction("purchase 1", -20.0, "12/1/19"));
		user.addAccount(tmp);
		ArrayList<Users> users = new ArrayList<Users>();
		users.add(user);
		this.mc = new MainController(users, primaryStage);
		
		mc.start();
	}
	
	public void stop() {
		System.out.println("Application closing");
	}
	
	
	public static void main(String[] args) {
		launch(args);
//		
//		Users user = new Users("username", "password");
//		Users user1 = new Users("username1", "pa1ssword");
//		Account acct = new Account("w", 1, "12");
//		acct.addTransaction(new Transaction("transaction", 1, "12"));
//		acct.addTransaction(new Transaction("transaction 2.0", 1, "12"));
//		user.addAccount(acct);
//		user.addAccount(new Account("name", 100, "date"));
//		user.addAccount(new Account("debit", 200, "11/25/19"));
//		user1.addAccount(acct);
//		ArrayList<Users>usr = new ArrayList<Users>();
//		usr.add(user);
//		usr.add(user1);
//		ApplicationFileWriter.writeUserObjects(usr);
//		ArrayList<Users> users = ApplicationFileWriter.readUserObjects();
//		users.forEach(userr -> System.out.println(userr));
	}
}
