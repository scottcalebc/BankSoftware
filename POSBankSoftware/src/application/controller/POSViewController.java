package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class POSViewController implements Initializable {
	
	@FXML
	private Label lblHiddenText;
	
	@FXML
	private Button btnDisplayText;
	
	
	@FXML
	public void displayText( ActionEvent event) {
		lblHiddenText.setVisible(true);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		 
	       // TODO (don't really need to do anything here).
	      
	}
	
}
