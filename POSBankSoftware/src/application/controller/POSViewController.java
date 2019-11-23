package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Account;
import application.model.ApplicationFileWriter;
import application.model.ShowData;
import application.model.Transaction;
import application.model.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;

public class POSViewController implements Initializable {
	
	@FXML
	private TreeTableView<ShowData> AccountTable;
	
	@FXML
	private TreeTableColumn<ShowData, String> AccountColumn;
	
	@FXML
	private TreeTableColumn<ShowData, String> DateColumn;
	
	@FXML
	private TreeTableColumn<ShowData, String> AmountColumn;
	
	@FXML
	private TreeTableColumn<ShowData, String> TotalColumn;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		 
	       // TODO (don't really need to do anything here).
		ApplicationFileWriter.addUser(new Users("someone", "password"));
		
		AccountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		DateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
		DateColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		AmountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
		AmountColumn.setStyle("-fx-alignment: CENTER;");
		TotalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));
		TotalColumn.setStyle("-fx-alignment: CENTER;");
		
		Account acct = new Account("first account", 100, "11/22/19");
		
		Transaction tans1 = new Transaction("bought 1", "11/22/19", 10);
		
		Account acct2 = new Account("second account", 10, "11/30/19");
		
		Account accounts = new Account("Accounts", 0, "");
		
		TreeItem<ShowData> root = new TreeItem<>(accounts);
		
		TreeItem<ShowData> acctnode2 = new TreeItem<>(acct2);
		TreeItem<ShowData> acctnode1 = new TreeItem<>(acct);
		TreeItem<ShowData> trannode1 = new TreeItem<>(tans1);
		
		acctnode1.getChildren().add(trannode1);

		root.setExpanded(true);
		root.getChildren().add(acctnode1);
		root.getChildren().add(acctnode2);
		
		
		
		
		AccountTable.setId("AccountTable");
		AccountTable.setRoot(root);
//		AccountTable.setShowRoot(false);
		
		AccountTable.widthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
	            // Get the table header
	            Pane header = (Pane)AccountTable.lookup("TableHeaderRow");
	            if(header!=null && header.isVisible()) {
	              header.setMaxHeight(0);
	              header.setMinHeight(0);
	              header.setPrefHeight(0);
	              header.setVisible(false);
	              header.setManaged(false);
	            }
	        }
	    });
	}
	
}
