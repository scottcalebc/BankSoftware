package application;
	
import java.io.IOException;
import application.model.ApplicationFileWriter;
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
	}
}
