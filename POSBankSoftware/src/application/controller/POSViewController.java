package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.ShowData;
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
	
	private MainController mc;	
	
	private ShowData data;
	
	
	@FXML
	private TreeTableView<ShowData> AccountTable;
	
	@FXML
	private TreeTableColumn<ShowData, String> AccountColumn, DateColumn, AmountColumn, TotalColumn;
	
	@FXML
	private PieChart piChart;
	
	@FXML
	private RadioButton piChartTransactions, piChartAccounts;
	
	@FXML 
	private ToggleGroup piChartRadioBtns;
	
	@FXML
	private Button DeleteTreeItemBtn, addTransactionBtn, addAccountBtn;
	
	@FXML
	private Label totalAmount, totalAmountpiChart, lblMoneyIn, lblMoneyOut;

	
	public void addTransaction(ActionEvent event) {
		mc.updateView(this, MainController.addTanView, MainController.addTanX, MainController.addTanY);
	}
	
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
		
		System.out.println(par);
		
		par.removeChild(obj);
		parent.getChildren().remove(data);
		updateAmounts();
		piChart.getData().removeIf(x -> x.getName().equals(obj.getName()));
		
		System.out.println(this.data);
	}
	
	public void setPiChartTransactions(ActionEvent event) {
		ArrayList<ShowData> data = new ArrayList<ShowData>();
		for(ShowData sub : this.data.getChildren()) {
			data.addAll(sub.getChildren());
		}
		setPieChart(data);
		
	}
	
	public void setPiChartAccounts(ActionEvent event) {
		setPieChart(this.data.getChildren());
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
		totalAmount.setText(String.format("$%.2f", total));
		totalAmountpiChart.setText("$" + String.valueOf(total));
		lblMoneyIn.setText("$" + String.valueOf(moneyIn));
		lblMoneyOut.setText("-$" + String.valueOf(Math.abs(moneyOut)));
	}
	
	private void updateAmounts() {
		double amounts[] = data.getTotals();
		System.out.println(amounts[0] + " " + amounts[1] + " " + amounts[2]);
		setAmounts(amounts[0], amounts[1], amounts[2]);
	}
	
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
	
	public void onLoad(ShowData data, MainController mc) {
		this.mc = mc;
		this.data = data;
		System.out.println(data);
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
		
		//Pie Char Data
		setPieChart(data.getChildren());
		updateAmounts();
	}
	
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
