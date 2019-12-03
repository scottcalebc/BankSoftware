package application.controller;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import application.model.ShowData;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginScreenController implements Initializable, SubController {
	
	MainController mc;
	
	Users user;
	
	@FXML
	private Button Login, AddNewUser;
	
	@FXML
	private TextField LoginId, LoginPassword;
		
	@FXML
	private Label invalidUserInfo;
	
	public void newUserClick() {
		mc.updateView(null, MainController.addUserView, MainController.addUserX, MainController.addUserY);
	}
	
	public void attemptLogin(ActionEvent event) {
		if (LoginId.getText() == null || LoginId.getText().contentEquals("")) {
			LoginId.requestFocus();
			return;
		}
		Users tmp = null;
		try {
			tmp = mc.getUsers().stream().filter(x -> x.getName().equals(LoginId.getText())).findFirst().get();
		} catch(NoSuchElementException e) {
			invalidUserInfo.setVisible(true);
			return;
		}
		if (tmp != null) {
			if (tmp.getPassword().equals(LoginPassword.getText())) {
				this.user = tmp;
				mc.updateView(this, MainController.mainView, MainController.mainX, MainController.mainY);
			}
			else {
				invalidUserInfo.setVisible(true);
			}
		}
		
		LoginId.requestFocus();
	}
	

	@Override
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
	}

	@Override
	public ShowData onExit() {
		return this.user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
