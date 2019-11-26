package application;
	
import java.io.IOException;
import java.util.ArrayList;

import application.model.Account;
import application.model.ApplicationFileWriter;
import application.model.Transaction;
import application.model.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		ApplicationFileWriter.getUsers();
		
		Parent root = FXMLLoader.load(getClass().getResource("view/POSMainTemplate.fxml"));
		Scene scene = new Scene(root, 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void stop() {
		System.out.println("Application closing");
		System.out.println(ApplicationFileWriter.getState());
		ApplicationFileWriter.close();
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
