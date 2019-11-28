package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.Accounts;
import application.model.ShowData;
import application.model.Transaction;
import application.model.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;

public class POSViewController implements Initializable, SubController {
	
	MainController mc;
	
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
	
	@FXML
	private PieChart piChart;
	
	@FXML
	private Button addAccountBtn;
	
	@FXML
	private RadioButton piChartTransactions;
	
	@FXML
	private RadioButton piChartAccounts;
	
	@FXML 
	private ToggleGroup piChartRadioBtns;
	
	@FXML
	private Button DeleteTreeItemBtn, addTransactionBtn;
	
	@FXML
	private Label totalAmount, totalAmountpiChart, lblMoneyIn, lblMoneyOut;
	
	private Users user;
	
	private Accounts rootnode;
	
	
	public void addTransaction(ActionEvent event) {
		mc.updateView(this, MainController.addTanView, MainController.addTanX, MainController.addTanY);
	}
	
	public void DeleteTreeItem(ActionEvent event) {
		System.out.println("Delete Button Pressed");
		TreeTableViewSelectionModel<ShowData> selectionModel = AccountTable.getSelectionModel();
		
		if (selectionModel.isEmpty()) {
			System.out.println("Nothing to delete.");
			return;
		}
		
		System.out.println(selectionModel);
		int rowIndex = selectionModel.getSelectedIndex();
		TreeItem<ShowData> data = selectionModel.getModelItem(rowIndex);
		TreeItem<ShowData> parent = data.getParent();
		
		Object parentvalue = parent.getValue();
		
		if (parentvalue instanceof Accounts) {
			Accounts pacct = ((Accounts)parentvalue);
			if (!pacct.getName().equals("rootnode")) {
				Accounts tmp = user.getAccounts().stream().filter(x -> x.equals(pacct)).findFirst().get();
				System.out.println(tmp.getTransactions());
				Transaction tmptran = (Transaction)data.getValue();
				pacct.removeTransaction(tmptran);
				parent.getChildren().remove(data);
				
				if (piChartTransactions.isSelected()) 
					setPiChartTransactions(new ActionEvent());
				
				
				System.out.println(tmp.getTransactions());
			} else {
				System.out.println("Must remove from user arraylist acct");
				user.removeAccount((Accounts)data.getValue());
				parent.getChildren().remove(data);
				
				if (piChartAccounts.isSelected()) 
					setPiChartAccounts(new ActionEvent());
			}
		}
		
		updateAmounts();
		
	}
	
	public void setPiChartTransactions(ActionEvent event) {
		ArrayList<ShowData> data = new ArrayList<ShowData>();
		for(Accounts acct : user.getAccounts()) {
			data.addAll(acct.getTransactions());
		}
		setPieChart(data);
		
	}
	
	public void setPiChartAccounts(ActionEvent event) {
		setPieChart(new ArrayList<ShowData>(this.user.getAccounts()));
	}
	
	public void addAccount(ActionEvent event) {
		mc.updateView(this, MainController.addAcctView, MainController.addAcctX, MainController.addAcctY);
	}
	
	private void setCellFactory() {
		AccountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		DateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
		DateColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		AmountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
		AmountColumn.setStyle("-fx-alignment: CENTER;");
		TotalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));
		TotalColumn.setStyle("-fx-alignment: CENTER;");
	}
	
	private void setAmounts(double total, double moneyIn, double moneyOut) {
		totalAmount.setText("$" + String.valueOf(total));
		totalAmountpiChart.setText("$" + String.valueOf(total));
		lblMoneyIn.setText("$" + String.valueOf(moneyIn));
		lblMoneyOut.setText("-$" + String.valueOf(Math.abs(moneyOut)));
	}
	
	private void updateAmounts() {

		double total = 0;
		double moneyIn = 0;
		double moneyOut = 0;
		for(Accounts acct : user.getAccounts()) {
			total += acct.getCurrBalance();
			moneyIn += acct.getMoneyIn();
			moneyOut += acct.getMoneyOut();
		}
		
		setAmounts(total, moneyIn, moneyOut);
		
	}
	
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
		setCellFactory();
		this.rootnode = new Accounts("rootnode", "rootnode", 0, "rootnode");
		TreeItem<ShowData> root = new TreeItem<>(this.rootnode);
		
		if (data instanceof Users) {
			this.user = (Users)data;
			for (Accounts accnt : this.user.getAccounts()) {
				TreeItem<ShowData>acctnode = new TreeItem<>(accnt);
				for(Transaction tans : accnt.getTransactions()) {
					TreeItem<ShowData>tansnode = new TreeItem<>(tans);
					acctnode.getChildren().add(tansnode);
				}
				root.getChildren().add(acctnode);
			}
		} else if (data instanceof Accounts) {
			System.out.println(this.user);
		}
		
		root.setExpanded(true);
		AccountTable.setRoot(root);
		
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
		
		
		//Pie Char Data
		setPieChart(new ArrayList<ShowData>(user.getAccounts()));
		
		
		updateAmounts();
	}
	
	public ShowData onExit() {
		return this.user;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		//TreeTableView Data
		
		
	}
	
	public void addPieChart(Accounts acct) {
	    piChart.getData().add(new PieChart.Data(acct.getName(), Math.abs(acct.getAmountDouble())));
	  }
	
	public void setPieChart(ArrayList<ShowData> data) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(ShowData item : data) {
			pieChartData.add(new PieChart.Data(item.getName(), Math.abs(item.getAmountDouble())));
		}
		
		piChart.setData(pieChartData);
		
	}

	
}
