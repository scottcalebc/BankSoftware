package application.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Account class to hold account information
 * @author Nikki Thanapaisal
 * @author Jonathan Becker
 * @author Christopher Caleb Scott
 *
 */
public class Accounts implements ShowData, Serializable{
    
	/**
	 * UID for serializaiton of object
	 */
	private static final long serialVersionUID = -8762598452815091385L;
	/**
	 * Name of the account
	 */
	private String accName;
	/**
	 * Type of account; unused currently
	 */
    private String accType;
    /**
     * balance of account
     */
    private double currBalance;

    /**
     * List of transactions for account
     */
    private ArrayList<Transaction> transactions;

    /**
     * Contstructor
     * @param accName
     * @param accType
     * @param currBalance
     * @param userID
     */
    public Accounts(String accName, String accType, double currBalance, String userID){
        this.accName = accName;
        this.accType = accType;
        this.currBalance = currBalance;
        this.transactions = new ArrayList<Transaction>();
    }
    
    /**
     * Gets Account Name
     * @return
     */
    public String getAccName() {
        return accName;
    }
    
    /**
     * Sets account name
     * @param accName
     */
    public void setAccNum(String accName) {
        this.accName = accName;
    }
    
    /**
     * Gets balance
     * @return
     */
    public double getCurrBalance() {
        return currBalance;
    }
    
    /**
     * Sets balance
     * @param currBalance
     */
    public void setCurrBalance(double currBalance) {
        this.currBalance = currBalance;
    }
    
    /**
     * Gets the type
     * @return
     */
    public String getAccType() {
        return accType;
    }
    
    /**
     * Sets the type
     * @param accType
     */
    public void setAccType(String accType) {
        this.accType = accType;
    }
    
    /**
     * Get full list of transactions
     * @return
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    /**
     * Sets entire transaction list
     * updates balance based on amount of each transaction
     * @param transactions
     */
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        
        for (Transaction tans : transactions) {
        	this.currBalance += tans.getTransactionValue();
        }
    }
    
    /**
     * Adds a transaction to list then updates balance
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
    	this.transactions.add(transaction); 
    	
    	this.currBalance += transaction.getTransactionValue();
    }
    
    /**
     * Removes transaction from list then updates balance
     * @param transaction
     */
    public void removeTransaction(Transaction transaction) {
    	this.transactions.remove(transaction);
    	
    	this.currBalance -= transaction.getTransactionValue();
    }
    
    
    /**
     * Finds sum of all transactions with amount > 0
     * @return
     */
    public double getMoneyIn() {
    	double out = 0;
    	for (Transaction tans : this.transactions) {
    		if (tans.getTransactionValue() > 0) 
    			out += tans.getTransactionValue();
    	}
    	
    	return out;
    }
    
    /**
     * Finds sum of all transaction with amount < 0
     * @return
     */
    public double getMoneyOut() {
    	double out = 0;
    	for (Transaction tans : this.transactions) {
    		if (tans.getTransactionValue() < 0) 
    			out += tans.getTransactionValue();
    	}
    	return out;
    }
    
    /**
     * Implemented to return name of object
     * @return
     */
    public String getName() {
    	return this.accName;
    }
    
    /**
     * Interface method not used
     * @return
     */
    public String getAmount() {
    	return null;
    }
    
    /**
     * Interface method not used
     * @return
     */
    public String getDate() {
    	return null;
    }
    
    /**
     * Interface method returns formatted string of balance (i.e. 100.00)
     * @return
     */
    public String getTotal() {
    	return String.format("%.2f", this.currBalance);
    }
    
    /**
     * Interface method to get balance or amount in double format
     * @return
     */
    public double getAmountDouble() {
    	return this.currBalance;
    }
    
	@Override
	/**
	 * Interface method to return children objects
	 * Cast childrent to ShowData objects then add to list
	 */
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return new ArrayList<ShowData>(this.getTransactions());
	}
	
	@Override
	/**
	 * Interface method to remove a child object
	 * if child is instance of transaction find object in list of transactions and removes
	 * @param child
	 * 
	 */
	public void removeChild(ShowData child) {
		// TODO Auto-generated method stub
		if (child instanceof Transaction) {
			this.removeTransaction((Transaction)child);
		}
		
	}
	
	@Override
	/**
	 * Interface method to add a child object
	 * if child is instance of transaction add to list
	 * @param child
	 */
	public void addChild(ShowData child) {
		// TODO Auto-generated method stub
		if (child instanceof Transaction) {
			this.addTransaction((Transaction)child);
		}
		
	}
	
	@Override
	/**
	 * Interface method to return array of totals
	 * Helper method to find total amounts and return
	 * @return
	 */
	public double[] getTotals() {
		// TODO Auto-generated method stub
		
		return new double[] {this.getAmountDouble(), this.getMoneyIn(), this.getMoneyOut()};
	}
	
	
	/**
	 * Debug output to show Hierarchy of account with all transactions
	 * @return
	 */
	public String toString() {
		String out = this.accName + " " + this.getAmountDouble() + "\n";
		for(Transaction tans : this.transactions) {
			out += "    |_____" + tans.toString();
		}
		
		return out;
	}

}
