package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.ShowData;
import application.model.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

/**
 * Controller for main view 
 * @author Christopher Caleb Scott
 *
 */
public class POSViewController implements Initializable, SubController {
	
	/**
	 * Main Controller reference
	 */
	private MainController mc;	
	
	/**
	 * data reference passed from main controller
	 */
	private ShowData data;
	
	
	/**
	 * Tree Table
	 */
	@FXML
	private TreeTableView<ShowData> AccountTable;
	
	/**
	 * Tree Table Columns
	 */
	@FXML
	private TreeTableColumn<ShowData, String> AccountColumn, DateColumn, AmountColumn, TotalColumn;
	
	/**
	 * Pie Chart reference
	 */
	@FXML
	private PieChart piChart;
	
	/**
	 * Radio Buttons to change Pie Chart data
	 */
	@FXML
	private RadioButton piChartTransactions, piChartAccounts;
	
	/**
	 * Toggle group for radio buttons
	 */
	@FXML 
	private ToggleGroup piChartRadioBtns;
	
	/**
	 * buttons to add/remove/edit transactions and accounts; logout
	 */
	@FXML
	private Button DeleteTreeItemBtn, addTransactionBtn, addAccountBtn, logoutBtn, editBtn;
	
	/**
	 * Labels to show totals from data passed
	 */
	@FXML
	private Label totalAmount, totalAmountpiChart, lblMoneyIn, lblMoneyOut;
	
	
	/**
	 * Edit Button Handler to get selected item from tree table and pass to 
	 * respective controller depending on what was selected (Account or Transaction)
	 * @param event
	 */
	public void editShowData(ActionEvent event) {
		TreeTableViewSelectionModel<ShowData> selectionModel = AccountTable.getSelectionModel();
		
		if (selectionModel.isEmpty()) {
			return;
		}
		
		int rowIndex = selectionModel.getSelectedIndex();
		TreeItem<ShowData> data = selectionModel.getModelItem(rowIndex);
		TreeItem<ShowData> parent = data.getParent();
		
		mc.setCurrUser((Users)this.data);
		if (parent.getValue() == this.data) {
			
			mc.updateView(new SubController() {
				public void onLoad(ShowData data, MainController mc) {
					
				}
				
				public ShowData onExit() {
					return data.getValue();
				}
			}, MainController.addAcctView, MainController.addAcctX, MainController.addAcctY);
		} else {
			mc.updateView(new SubController() {
				public void onLoad(ShowData data, MainController mc) {
					
				}
				
				public ShowData onExit() {
					return data.getValue();
				}
			}, MainController.addTanView, MainController.addTanX, MainController.addTanY);
		}
	}

	
	/**
	 * Add Transaction Button Handler
	 * updates view to Transaction view
	 * @param event
	 */
	public void addTransaction(ActionEvent event) {
		mc.updateView(this, MainController.addTanView, MainController.addTanX, MainController.addTanY);
	}
	
	/**
	 * Delete Button Handler
	 * Removes an item from Tree Table based on what was selected 
	 * then removes the (Account/Transaction) from (User/Account) respectively
	 * @param event
	 */
	public void DeleteTreeItem(ActionEvent event) {
		TreeTableViewSelectionModel<ShowData> selectionModel = AccountTable.getSelectionModel();
		
		if (selectionModel.isEmpty()) {
			return;
		}
		
		int rowIndex = selectionModel.getSelectedIndex();
		TreeItem<ShowData> data = selectionModel.getModelItem(rowIndex);
		TreeItem<ShowData> parent = data.getParent();
		
		ShowData par = parent.getValue();
		ShowData obj = data.getValue();
		
		par.removeChild(obj);
		parent.getChildren().remove(data);
		updateAmounts();
		piChart.getData().removeIf(x -> x.getName().equals(obj.getName()));
	}
	
	/**
	 * Transaction radio button handler
	 * Loads Pie Chart with Transaction data
	 * @param event
	 */
	public void setPiChartTransactions(ActionEvent event) {
		ArrayList<ShowData> data = new ArrayList<ShowData>();
		for(ShowData sub : this.data.getChildren()) {
			data.addAll(sub.getChildren());
		}
		setPieChart(data);
		
	}
	
	/**
	 * Account radio button handler
	 * Loads pie chart with account data
	 * @param event
	 */
	public void setPiChartAccounts(ActionEvent event) {
		setPieChart(this.data.getChildren());
	}
	
	/**
	 * Add account button handler
	 * Changes view to add account
	 * @param event
	 */
	public void addAccount(ActionEvent event) {
		mc.updateView(this, MainController.addAcctView, MainController.addAcctX, MainController.addAcctY);
	}
	
	/**
	 * Helper function to set cell factory of Tree table to correctly load data
	 */
	private void setCellFactory() {
		AccountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		DateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
		DateColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		AmountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
		AmountColumn.setStyle("-fx-alignment: CENTER;");
		TotalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));
		TotalColumn.setStyle("-fx-alignment: CENTER;");
	}
	
	/**
	 * Helper function to write total amounts to view 
	 * @param total
	 * @param moneyIn
	 * @param moneyOut
	 */
	private void setAmounts(double total, double moneyIn, double moneyOut) {
		totalAmount.setText(String.format("$%.2f", total));
		totalAmountpiChart.setText(String.format("$%.2f", total));
		lblMoneyIn.setText(String.format("$%.2f", moneyIn));
		lblMoneyOut.setText(String.format("-$%.2f", Math.abs(moneyOut)));
	}
	
	/**
	 * Handler to retrieve new totals from user and write to view
	 */
	private void updateAmounts() {
		double amounts[] = data.getTotals();
		setAmounts(amounts[0], amounts[1], amounts[2]);
	}
	
	/**
	 * Helper function to build Tree Table recursively
	 * @param data
	 * @return
	 */
	public TreeItem<ShowData> buildTree(ShowData data) {
		if (data.getChildren() == null) {
			return new TreeItem<>(data);
		} else {
			TreeItem<ShowData>par = new TreeItem<>(data);
			for (ShowData sub : data.getChildren()) {
				par.getChildren().add(buildTree(sub));
			}
			
			return par;
		}
	}
	
	/**
	 * Interface method to get data from Main Controller load data onto view and enable functionality
	 */
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
		this.data = data;
		setCellFactory();
		
		TreeItem<ShowData>parent = buildTree(data);
		parent.setExpanded(false);
		AccountTable.setRoot(parent);
		
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
		
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mc.updateView(null, MainController.loginScreenView, MainController.loginScreenX, MainController.loginScreenY);
			}
		});
		
		//Pie Char Data
		setPieChart(data.getChildren());
		updateAmounts();
	}
	
	/**
	 * Interface method to return data to main controller
	 * @return
	 */
	public ShowData onExit() {
		return this.data;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void addPieChart(ShowData data) {
	    piChart.getData().add(new PieChart.Data(data.getName(), Math.abs(data.getAmountDouble())));
	  }
	
	public void setPieChart(ArrayList<ShowData> data) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(ShowData item : data) {
			pieChartData.add(new PieChart.Data(item.getName(), Math.abs(item.getAmountDouble())));
		}
		
		piChart.setData(pieChartData);
		
	}

	
}
