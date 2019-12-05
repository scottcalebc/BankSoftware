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

/**
 * Controller for the Login view
 * @author Luke DeGoes
 *
 */
public class LoginScreenController implements Initializable, SubController {
	
	/**
	 * Main Controller reference
	 */
	MainController mc;
	
	/**
	 * Reference to user to return
	 */
	Users user;
	
	/**
	 * Buttons to login or add user
	 */
	@FXML
	private Button Login, AddNewUser;
	
	/**
	 * Fields to capture user input (username and password)
	 */
	@FXML
	private TextField LoginId, LoginPassword;
		
	/**
	 * Hidden text to inform user of invalid login
	 */
	@FXML
	private Label invalidUserInfo;
	
	
	/**
	 * Changes view to add user view
	 */
	public void newUserClick() {
		mc.updateView(null, MainController.addUserView, MainController.addUserX, MainController.addUserY);
	}
	
	/**
	 * Checks login attempt with user that matches username if fails informs users to try again
	 * otherwise changes view to main view
	 * @param event
	 */
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
	/**
	 * Interface Method to load data on view and enable functionality
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
		return this.user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
