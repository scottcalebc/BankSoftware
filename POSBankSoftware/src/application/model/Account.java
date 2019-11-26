package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements ShowData, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7681351177121479611L;
	private String name;
	private int balance;
	private String date;
	private ArrayList<Transaction> trans;
	
	public Account(String name, int amount, String date) {
		this.name = name;
		this.balance = amount;
		this.date = date;
		this.trans = new ArrayList<Transaction>();
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
	
	public ArrayList<Transaction> getTransactions() {
		return this.trans;
	}
	public void addTransaction(Transaction trans) {
		this.trans.add(trans);
	}
	
	public String toString() {
		String out = name + " " + String.valueOf(this.balance) + " " + this.date + "\n";
		
		if (!trans.isEmpty()) {
			for(Transaction tran : trans) {
				out += "|\t|_____" + tran.toString();
			}
		}
		
		return out;
		
	}
}
