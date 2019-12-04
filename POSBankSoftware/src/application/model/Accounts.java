package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Accounts implements ShowData, Serializable{
    
	private static final long serialVersionUID = -8762598452815091385L;
	private String accName;
    private String accType;
    private double currBalance;

    //transaction array for transaction object
    private ArrayList<Transaction> transactions;

    public Accounts(String accName, String accType, double currBalance, String userID){
        this.accName = accName;
        this.accType = accType;
        this.currBalance = currBalance;
        this.transactions = new ArrayList<Transaction>();
    }
    public String getAccName() {
        return accName;
    }
    public void setAccNum(String accName) {
        this.accName = accName;
    }
    public double getCurrBalance() {
        return currBalance;
    }
    public void setCurrBalance(double currBalance) {
        this.currBalance = currBalance;
    }
    
    public String getAccType() {
        return accType;
    }
    public void setAccType(String accType) {
        this.accType = accType;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        
        for (Transaction tans : transactions) {
        	this.currBalance += tans.getTransactionValue();
        }
    }
    
    public void addTransaction(Transaction transaction) {
    	this.transactions.add(transaction); 
    	
    	this.currBalance += transaction.getTransactionValue();
    }
    
    public void removeTransaction(Transaction transaction) {
    	this.transactions.remove(transaction);
    	
    	this.currBalance -= transaction.getTransactionValue();
    }
    
    
    public double getMoneyIn() {
    	double out = 0;
    	for (Transaction tans : this.transactions) {
    		if (tans.getTransactionValue() > 0) 
    			out += tans.getTransactionValue();
    	}
    	
    	return out;
    }
    
    public double getMoneyOut() {
    	double out = 0;
    	for (Transaction tans : this.transactions) {
    		if (tans.getTransactionValue() < 0) 
    			out += tans.getTransactionValue();
    	}
    	return out;
    }
    
    //Implemented Methods
    public String getName() {
    	return this.accName;
    }
    
    public String getAmount() {
    	return null;
    }
    
    public String getDate() {
    	return null;
    }
    
    public String getTotal() {
    	return String.format("%.2f", this.currBalance);
    }
    
    public double getAmountDouble() {
    	return this.currBalance;
    }
	@Override
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return new ArrayList<ShowData>(this.getTransactions());
	}
	@Override
	public void removeChild(ShowData child) {
		// TODO Auto-generated method stub
		if (child instanceof Transaction) {
			this.removeTransaction((Transaction)child);
		}
		
	}
	@Override
	public void addChild(ShowData child) {
		// TODO Auto-generated method stub
		if (child instanceof Transaction) {
			this.addTransaction((Transaction)child);
		}
		
	}
	@Override
	public double[] getTotals() {
		// TODO Auto-generated method stub
		
		return new double[] {this.getAmountDouble(), this.getMoneyIn(), this.getMoneyOut()};
	}
	
	
	public String toString() {
		String out = this.accName + " " + this.getAmountDouble() + "\n";
		for(Transaction tans : this.transactions) {
			out += "    |_____" + tans.toString();
		}
		
		return out;
	}

}
