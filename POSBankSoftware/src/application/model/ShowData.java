package application.model;

import java.util.ArrayList;

public interface ShowData {
	
	public abstract String getName();
	
	public abstract String getDate();
	
	public abstract String getAmount();
	
	public abstract String getTotal();
	
	public abstract double getAmountDouble();
	
	public abstract ArrayList<ShowData> getChildren();
	
	public abstract void removeChild(ShowData child);
	
	public abstract void addChild(ShowData child);
	
	public abstract double[] getTotals();
}
