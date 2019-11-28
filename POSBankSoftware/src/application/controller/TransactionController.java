package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Accounts;
import application.model.ShowData;
import application.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TransactionController implements SubController, Initializable{
	private MainController mc;
	private Users user;
	
	
	@FXML
	private TextField TransactionName, TransactionDate, TransactionAmount;
	
	@FXML
	private TextArea Memo;
	
	
	public void addTransaction(ActionEvent event) {
		//Add transaction to User
		
		
		
		mc.updateView(this, MainController.mainView, MainController.mainX, MainController.mainY);
	}
	
	@Override
	public void onLoad(ShowData data, MainController mc) {
		// TODO Auto-generated method stub
		this.mc = mc;
		this.user = (Users)data;
		
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
