package application;
	
import java.io.IOException;
import java.util.ArrayList;
import application.controller.MainController;
import application.model.ApplicationFileWriter;
import application.model.Users;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main method of application
 * @author Chrisotpher Caleb Scott
 *
 */
public class Main extends Application {
	
	/**
	 * Main controller reference
	 */
	MainController mc;
	
	@Override
	/**
	 * Used to instantiate controller and pass in data from object file
	 */
	public void start(Stage primaryStage) throws IOException {
		ArrayList<Users> users = ApplicationFileWriter.readUserObjects();
		
		this.mc = new MainController(users, primaryStage);
		
		mc.start();
	}
	
	/**
	 * Used to write to data file any new objects/updated objects
	 */
	public void stop() {
		ApplicationFileWriter.writeUserObjects(mc.getUsers());
	}
	
	/**
	 * Main Starts application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
