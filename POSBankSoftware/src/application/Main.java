package application;
	
import java.io.IOException;
import java.util.ArrayList;
import application.controller.MainController;
import application.model.ApplicationFileWriter;
import application.model.Users;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	MainController mc;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		ArrayList<Users> users = ApplicationFileWriter.readUserObjects();
		
		System.out.println(users);
		
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
