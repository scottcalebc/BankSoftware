package application.model;

public class Account implements ShowData {
	private String name;
	private int balance;
	private String date;
	
	public Account(String name, int amount, String date) {
		this.name = name;
		this.balance = amount;
		this.date = date;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getAmount() {
		return String.valueOf(this.balance);
	}
	
	public String getDate() {
		return this.date;
	}


	@Override
	public String getTotal() {
		// TODO Auto-generated method stub
		return null;
	}
}
