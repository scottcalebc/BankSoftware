package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.ShowData;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


/**
 * Controller for Adding User accounts view
 * @author Luke DeGoes
 *
 */
public class AddUserController implements Initializable, SubController{
	/**
	 * Main Controller reference
	 */
	MainController mc;
	/**
	 * Reference to the created user to return
	 */
	Users newUser;
	
	/**
	 * TextField for username
	 */
	@FXML
	private TextField userNameNewUser;
	
	/**
	 * Buttons to create or cancel from view
	 */
	@FXML
	private Button submitNewUser, cancelNewUser;
	
	/**
	 * password field for user's password
	 */
	@FXML
	private PasswordField passwordNewUser;
	
	/**
	 * Hidden text to inform user
	 */
	@FXML
	private Label invalidUser;
	
	/**
	 *FXML Event Hanlder for validating fields and adding user
	 *ensures username is not in user; creates user;
	 *then updates View to login screen 
	 * @param event
	 */
	public void addUser(ActionEvent event) {
		for (Users currUser : mc.getUsers()) {
			if (userNameNewUser.getText().equals(currUser.getName())) {
				invalidUser.setVisible(true);
				userNameNewUser.requestFocus();
				return;
			}
		}
		this.newUser = new Users(userNameNewUser.getText(), passwordNewUser.getText());
		mc.addUser(this.newUser);
		mc.updateView(this, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
	}
	
	/**
	 * FXML Event to change view to login screen
	 * @param e
	 */
	public void cancelUser(ActionEvent e) {
		mc.updateView(null, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
	}
	
	@Override
	/**
	 * Interace method to set up view with data and functionality
	 */
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
	}

	@Override
	/**
	 * Interface method to return data to Main Controller
	 * @return
	 */
	public ShowData onExit() {
		return this.newUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
