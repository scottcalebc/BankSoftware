package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.Account;
import application.model.ShowData;
import application.model.Transaction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
	
	Account acct = new Account("first account", 100, "11/22/19");
	
	Transaction tans1 = new Transaction("bought 1", 10, "11/22/19");
	
	Account acct2 = new Account("second account", 10, "11/30/19");
	
	Account accounts = new Account("Accounts", 0, "");
	
	
	public void setPiChartTransactions(ActionEvent event) {
		
			ArrayList<ShowData> data = new ArrayList<ShowData>();
			data.add(tans1);
			setPieChart(data);
		
	}
	
	public void setPiChartAccounts(ActionEvent event) {
		
		piChartTransactions.setSelected(false);
		ArrayList<ShowData> data = new ArrayList<ShowData>();
		data.add(acct);
		data.add(acct2);
		data.add(accounts);
		setPieChart(data);
	}
	
	public void addAccount(ActionEvent event) {
		System.out.println(piChart);
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		//TreeTableView Data
		AccountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		DateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
		DateColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		AmountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
		AmountColumn.setStyle("-fx-alignment: CENTER;");
		TotalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));
		TotalColumn.setStyle("-fx-alignment: CENTER;");
		
		
		
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
		setPiChartAccounts(new ActionEvent());

		
		
		
	}
	
	public void addPieChart(Account acct) {
	    piChart.getData().add(new PieChart.Data(acct.getName(), Integer.parseInt(acct.getAmount())));
	  }
	
	public void setPieChart(ArrayList<ShowData> data) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(ShowData item : data) {
			pieChartData.add(new PieChart.Data(item.getName(), Integer.parseInt(item.getAmount())));
		}
		
		piChart.setData(pieChartData);
		
	}

	
}
