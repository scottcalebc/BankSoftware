package application.controller;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import application.model.ShowData;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginScreenController implements Initializable, SubController {
	
	MainController mc;
	
	Users user;
	
	@FXML
	private Button Login;
	
	@FXML
	private TextField LoginId, LoginPassword;
	
	@FXML
	private Button AddNewUser;
	
	
	public void attemptLogin(ActionEvent event) {
		Users tmp = null;
		try {
			tmp = mc.getUsers().stream().filter(x -> x.getName().equals(LoginId.getText())).findFirst().get();
		} catch(NoSuchElementException e) {
			//produce login error on screen 
			return;
		}
		if (tmp != null) {
			if (tmp.getPassword().equals(LoginPassword.getText())) {
				this.user = tmp;
				mc.updateView(this, MainController.mainView, MainController.mainX, MainController.mainY);
			} 
		}
		
		LoginId.requestFocus();
	}
	

	@Override
	public void onLoad(ShowData data, MainController mc) {
		// TODO Auto-generated method stub
		this.mc = mc;
		
		
		AddNewUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				mc.updateView(null, MainController.addUserView, MainController.addUserX, MainController.addUserY);
			}
			
		});
		
	}

	@Override
	public ShowData onExit() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
