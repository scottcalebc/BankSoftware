package application.model;

import java.io.Serializable;

public class Transaction implements ShowData, Serializable {
	private String name;
	private String date;
	private int amount;
	
	public Transaction(String name, int amount, String date) {
		this.name = name;
		this.date = date;
		this.amount = amount;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return this.date;
	}

	@Override
	public String getAmount() {
		// TODO Auto-generated method stub
		return String.valueOf(this.amount);
	}

	@Override
	public String getTotal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getSomeOtherData() {
		return "";
	}
	
	public String toString() {
		return name + " " + String.valueOf(amount) + " " + date + "\n";
	}

}
