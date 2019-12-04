package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction implements ShowData, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -850213061847820920L;
	private String name;
    private double transactionValue;
    private String date;


    public Transaction(String name, double transactionValue, String date) {
        this.name = name;
    	this.transactionValue = transactionValue;
        this.date = date;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    //Interface methods
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getAmount() {
    	return String.format("%.2f", this.transactionValue);
    }
    
    public String getTotal() {
    	return null;
    }
    
    public double getAmountDouble() {
    	return this.transactionValue;
    }

	@Override
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChild(ShowData child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChild(ShowData child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double[] getTotals() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String toString() {
		return this.name + " " + this.getAmountDouble() + "\n";
	}
}
