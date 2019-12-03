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


public class AddUserController implements Initializable, SubController{
	MainController mc;
	Users newUser;
	
	@FXML
	private TextField userNameNewUser;
	
	@FXML
	private Button submitNewUser, cancelNewUser;
	
	@FXML
	private PasswordField passwordNewUser;
	
	@FXML
	private Label invalidUser;
	
	//FXML Event Hanlder for validating fields and adding user
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
	
	public void cancelUser(ActionEvent e) {
		mc.updateView(null, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
	}

	@Override
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
	}

	@Override
	public ShowData onExit() {
		return this.newUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
