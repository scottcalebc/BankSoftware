package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.Accounts;
import application.model.ShowData;
import application.model.Transaction;
import application.model.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * Controller for Add Transaction View
 * @author Christopher Caleb Scott
 *
 */
public class TransactionController implements SubController, Initializable{
	/**
	 * Reference to Main Controller
	 */
	private MainController mc;
	/**
	 * Data passed to controller
	 */
	private ShowData data;
	/**
	 * parent account if editing transaction data
	 */
	private Accounts parent;
	/**
	 * Flag to check if user passed or transaction
	 */
	private boolean userFlag = false;
	
	
	/**
	 * Text fields for transaction data
	 */
	@FXML
	private TextField TransactionName, TransactionDate, TransactionAmount;
	
	/**
	 * ComboBox for account names
	 */
	@FXML
	private ComboBox<ShowData> accountComboBox;
	
	/**
	 * Buttons to add transaction or cancel
	 */
	@FXML
	private Button AddTransaction, cancelBtn;
	
	
	/**
	 * Add Transaction Handler
	 * Validates text fields and then adds new transaction to account selected in combobox
	 * Changes view back to main view
	 * @param event
	 */
	public void addTransaction(ActionEvent event) {
		//Add transaction to User
		if (!validateTextFields(TransactionName, "[a-zA-z]+") && !validateTextFields(TransactionDate, "[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}") && !validateTextFields(TransactionAmount, "[0-9]+(.[0-9]{2})*")) {
			TransactionName.requestFocus();
			return;
		}
		
		if (accountComboBox.getValue() != null && userFlag) {
			Transaction tmp = new Transaction(TransactionName.getText(), Double.parseDouble(TransactionAmount.getText()), TransactionDate.getText());
			ShowData acct = accountComboBox.getValue();
			acct.addChild((ShowData)tmp);
		} else {
			
		}
		
		
		mc.updateView(this, MainController.mainView, MainController.mainX, MainController.mainY);
	}
	
	/**
	 * Updates Transaction data
	 * if transaction was passed to controller instead of user
	 */
	public void updateTransaction() {
		Transaction tans = (Transaction)this.data;
		tans.setName(TransactionName.getText());
		tans.setDate(TransactionDate.getText());
		tans.setTransactionValue(Double.parseDouble(TransactionAmount.getText()));
		
		if (this.parent != accountComboBox.getValue()) {
			ShowData acct = accountComboBox.getValue();
			this.parent.removeChild(tans);
			acct.addChild(tans);
		}
		
		
		mc.updateView(this, MainController.mainView, MainController.mainX, MainController.mainY);
	}
	
	/**
	 * Helper function to validate textfields
	 * @param field
	 * @param fmt
	 * @return
	 */
	public boolean validateTextFields(TextField field, String fmt) {
		if (field.getText().matches(fmt))
			return true;
		return false;
	}
	
	@Override
	/**
	 * Interface method to load data in controller and update view
	 */
	public void onLoad(ShowData data, MainController mc) {
		// TODO Auto-generated method stub
		this.mc = mc;
		this.data = data;
		
		if (data instanceof Users) {
			this.userFlag = true;
			accountComboBox.setItems(FXCollections.observableArrayList(data.getChildren()));
		} else {
			ShowData parent = mc.getCurrUser().getAccounts().stream()
					.filter(x -> 
						x.getTransactions().stream().filter(t ->
							t.equals(TransactionController.this.data)
						).findFirst().orElse(null) != null
					).findFirst().get();
			
			accountComboBox.setItems(FXCollections.observableArrayList(mc.getCurrUser().getChildren()));
			accountComboBox.setValue(parent);
			this.parent = (Accounts)parent;
			
			TransactionName.setText(data.getName());
			TransactionAmount.setText(data.getAmount());
			TransactionDate.setText(data.getDate());
		}
		
		accountComboBox.setCellFactory(new Callback<ListView<ShowData>, ListCell<ShowData>>() {
			@Override
			public ListCell<ShowData> call(ListView<ShowData> param) {
				// TODO Auto-generated method stub
				final ListCell<ShowData>  cell = new ListCell<ShowData> () {
					
					protected void updateItem(ShowData data, boolean bool) {
						super.updateItem(data, bool);
						
						if (data != null) {
							setText(data.getName());
						} else {
							setText(null);
						}
					}
				};
				return cell;
			}
		});
		
		accountComboBox.setButtonCell(new ListCell<ShowData>() {
			protected void updateItem(ShowData data, boolean bool) {
				super.updateItem(data, bool);
				if (data != null) {
					setText(data.getName());
				} else {
					setText(null);
				}
			}
		});
		
		AddTransaction.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (userFlag)
					addTransaction(event);
				else
					updateTransaction();
			}
		});
		
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mc.updateView(new SubController() {
					@Override
					public void onLoad(ShowData data, MainController mc) {
						
					}
					public ShowData onExit() {
						if (userFlag)
							return data;
						return mc.getCurrUser();
					}
				}, MainController.mainView, MainController.mainX, MainController.mainY);
			}
		});
		
	}

	@Override
	/**
	 * Returns data to main controller
	 */
	public ShowData onExit() {
		// TODO Auto-generated method stub
		if (userFlag) {
			return this.data;
		} else {
			return mc.getCurrUser();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
